////////////////////////////////////////////////////////////////////////////////
//
//  MATTBOLT.BLOGSPOT.COM
//  Copyright(C) 2011 Matt Bolt
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at:
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//
////////////////////////////////////////////////////////////////////////////////

package com.mattbolt.javaray.render;

import com.mattbolt.javaray.camera.Camera;
import com.mattbolt.javaray.geom.Geometry;
import com.mattbolt.javaray.geom.Ray;
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.primitives.SceneObject;
import com.mattbolt.javaray.util.ColorHelper;
import com.mattbolt.javaray.util.JavaRayConfiguration;
import com.mattbolt.javaray.util.JavaRayExecutorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;


/**
 * This renderer implementation sends lots of rays from the eye point, and magnifies intensity of the hit locations
 * based on their distance from the eye point.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class RayTracer {

    private static final Logger logger = LoggerFactory.getLogger(RayTracer.class);

    private final int totalSamples;
    private final int xChunkSize;
    private final int yChunkSize;
    private final ExecutorService imageSegments;

    public RayTracer(JavaRayConfiguration configuration) {
        totalSamples = configuration.getAntiAlias();
        xChunkSize = configuration.getXChunkSize();
        yChunkSize = configuration.getYChunkSize();
        imageSegments = JavaRayExecutorFactory.newFixedThreadPool(configuration.getThreadPoolSize());
    }

    public void synchronousRender(Scene scene, View view, Camera camera, final RenderTarget renderTarget) {
        Rectangle rect = new Rectangle(0, 0, view.getWidth(), view.getHeight());
        final CountDownLatch latch = new CountDownLatch(1);

        renderTarget.start();

        imageSegments.submit( new ImageSegmentWorker(scene, view, camera, renderTarget, rect, totalSamples, new GenericCallback() {
            @Override
            public void onComplete() {
                latch.countDown();
                logger.debug("count: {}", latch.getCount());
            }
        }) );

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error(e.getStackTrace().toString());
        } finally {
            renderTarget.complete();
        }

        logger.debug("Finished!");
        imageSegments.shutdown();
    }

    public void render(Scene scene, View view, Camera camera, final RenderTarget renderTarget) {
        int chunkWidth = (int) Math.floor(view.getWidth() / xChunkSize);
        int chunkHeight = (int) Math.floor(view.getHeight() / yChunkSize);
        int totals = (view.getWidth() / chunkWidth) * (view.getHeight() / chunkHeight);

        final CountDownLatch latch = new CountDownLatch(totals);

        renderTarget.start();
        
        for (int x = 0; x < view.getWidth(); x += chunkWidth) {
            for (int y = 0; y < view.getHeight(); y += chunkHeight) {
                Rectangle rect = new Rectangle(x, y, chunkWidth, chunkHeight);

                imageSegments.submit( new ImageSegmentWorker(scene, view, camera, renderTarget, rect, totalSamples,
                    new GenericCallback() {
                        @Override
                        public void onComplete() {
                            latch.countDown();
                            logger.debug("count: {}", latch.getCount());
                            renderTarget.refresh();
                        }
                    }) );
            }
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error(e.getStackTrace().toString());
        } finally {
            renderTarget.complete();
        }

        logger.debug("Finished!");
        imageSegments.shutdown();
    }

    private static interface GenericCallback {
        void onComplete();
    }

    /**
     * This class is used to perform the ray-trace on a separate thread
     */
    private static class ImageSegmentWorker implements Runnable {
        private final Scene scene;
        private final View view;
        private final Camera camera;

        private final RenderTarget renderTarget;
        private final Rectangle renderRect;
        private final double sizeX, sizeY;
        private final int totalSamples;

        private final GenericCallback callback;

        ImageSegmentWorker( Scene scene,
                            View view,
                            Camera camera,
                            RenderTarget renderTarget,
                            Rectangle renderRect,
                            int samples,
                            GenericCallback callback )
        {
            this.scene = scene;
            this.view = view;
            this.camera = camera;
            this.renderTarget = renderTarget;
            this.renderRect = renderRect;
            this.sizeX = ((double) scene.getWorldWidth()) / ((double) view.getWidth());
            this.sizeY = ((double) scene.getWorldHeight()) / ((double) view.getHeight());
            this.totalSamples = samples;
            this.callback = callback;
        }

        @Override
        public void run() {
            try {
                performRayTrace();
            } catch (Exception e) {
                logger.error("Error: {}", e);
            }
            
            callback.onComplete();
        }

        private void performRayTrace() {
            int posX = (int) renderRect.getX();
            int posY = (int) renderRect.getY();
            int width = posX + (int) renderRect.getWidth();
            int height = posY + (int) renderRect.getHeight();

            for (int x = posX; x < width; ++x) {
                for (int y = posY; y < height; ++y) {
                    Vector3 position = pixelToWorld(scene, view, x, y);
                    Vector3 intensities = new Vector3();

                    for (int i = 0; i < totalSamples; ++i) {
                        Vector3 newPosition = i == 0
                            ? new Vector3(position)
                            : new Vector3(
                                position.getX() + (Math.random() - 0.5) * sizeX,
                                position.getY() + (Math.random() - 0.5) * sizeY,
                                0.0);

                        Vector3 vec = Vector3.subtract(newPosition, camera.getPosition());

                        intensities.add(rayTrace(scene.getSceneObjects(), camera.getPosition(), vec));
                    }

                    intensities.scale(255.0 / totalSamples);
                    clamp(intensities);
                    
                    renderTarget.setPixelAt(x, y, ColorHelper.toColor(intensities));
                }
            }
        }

        private Vector3 rayTrace( List<SceneObject> objects, Vector3 viewPoint, Vector3 direction ) {
            return rayTrace(objects, viewPoint, direction, 0.0, null);
        }

        private Vector3 rayTrace( List<SceneObject> objects,
                                  Vector3 viewPoint,
                                  Vector3 direction,
                                  double totalDistance,
                                  SceneObject lastHit )
        {
            Map<SceneObject, Vector3> hitMap = new HashMap<SceneObject, Vector3>();
            double min = Double.MAX_VALUE;
            
            SceneObject closeObject = null;
            Vector3 intensity = new Vector3();
            Ray ray = new Ray(viewPoint, direction);

            for (SceneObject object : objects) {
                if (!object.equals(lastHit)) {
                    Geometry objectGeometry = object.getGeometry();
                    Geometry.HitResult result = objectGeometry.hits(ray);
                    Vector3 hitPoint = result.getHitPoint();

                    if (result.getT() > 0) {
                        hitMap.put(object, hitPoint);

                        Vector3 temp = Vector3.subtract(hitPoint, ray.getPosition());
                        if (temp.getLength() < min) {
                            min = temp.getLength();
                            closeObject = object;
                        }
                    }
                }
            }

            if (null == closeObject) {
                return Vector3.origin();
            }

            totalDistance += min;

            if (closeObject.isEmittingLight()) {
                Vector3 emissivity = ColorHelper.toVector3(closeObject.getMaterial().getEmissivity());
                emissivity.scale(1.0 / totalDistance);

                return emissivity;
            }

            Vector3 ambient = ColorHelper.toVector3(closeObject.getMaterial().getAmbient());
            Vector3 diffuse = diffuseFor(objects, closeObject, hitMap.get(closeObject));

            intensity.add(Vector3.add(ambient, diffuse));
            intensity.scale(1.0 / totalDistance);

            // Now, we can determine specular intensity
            intensity.add(specularFor(objects, closeObject, hitMap.get(closeObject), ray.getDirection(), totalDistance));

            return intensity;
        }

        private Vector3 diffuseFor(List<SceneObject> objects, SceneObject hitObject, Vector3 hitPoint) {
            Vector3 diffuse = new Vector3();

            for (SceneObject object : objects) {
                if (object.isEmittingLight()) {
                    // Find the vector between the center of the object and the hit point
                    Vector3 direction = Vector3.subtract(object.getPosition(), hitPoint);

                    // Get the distance (for amount of intensity to use)
                    double distance = direction.getLength();

                    // Normalize *after* getting the length
                    direction.normalize();

                    if (isLightVisible(objects, hitObject, hitPoint, object, distance)) {
                        double cos = Vector3.dotProduct(direction, hitObject.getGeometry().normalTo(hitPoint));

                        if (cos > 0) {
                            ColorMagnitude diff = hitObject.getMaterial().getDiffuse();
                            ColorMagnitude emissivity = object.getMaterial().getEmissivity();

                            diffuse.add(
                                (cos * diff.getRed() * emissivity.getRed()) / distance,
                                (cos * diff.getGreen() * emissivity.getGreen()) / distance,
                                (cos * diff.getBlue() * emissivity.getBlue()) / distance );
                        }
                    }
                }
            }

            return diffuse;
        }

        private Vector3 specularFor( List<SceneObject> objects,
                                     SceneObject closeObject,
                                     Vector3 hitPoint,
                                     Vector3 direction,
                                     double totalDistance )
        {
            ColorMagnitude spec = closeObject.getMaterial().getSpecular();

            if (spec.getRed() > 0 || spec.getGreen() > 0 || spec.getBlue() > 0) {
                Vector3 normal = closeObject.getGeometry().normalTo(hitPoint);
                Vector3 reflect = Vector3.reflection(direction, normal);

                if (totalDistance >= 0 && totalDistance < 30) {
                    Vector3 specular = rayTrace(
                        objects,
                        hitPoint,
                        reflect,
                        totalDistance,
                        closeObject );

                    return new Vector3(
                        specular.getX() * spec.getRed(),
                        specular.getY() * spec.getGreen(),
                        specular.getZ() * spec.getBlue() );
                }
            }

            return new Vector3();
        }

        private boolean isLightVisible( List<SceneObject> objects,
                                        SceneObject hitObject,
                                        Vector3 hitPoint,
                                        SceneObject lightSource,
                                        double distance )
        {
            Vector3 difference = Vector3.subtract(lightSource.getPosition(), hitPoint);
            difference.normalize();

            Ray ray = new Ray(hitPoint, difference);

            for (SceneObject object : objects) {
                if (!object.equals(hitObject) && !object.equals(lightSource)) {
                    Geometry.HitResult result = object.getGeometry().hits(ray);

                    if (result.getT() > 0 && result.getT() <= distance) {
                        return false;
                    }
                }
            }

            return true;
        }

        private Vector3 pixelToWorld(Scene scene, View view, int x, int y) {
            return new Vector3(
                (1.0 * x / view.getWidth()) * scene.getWorldWidth(),
                (1.0 * y / view.getHeight()) * scene.getWorldHeight(),
                0.0);
        }

        private void clamp(Vector3 vector) {
            vector.setX(clamp(vector.getX()));
            vector.setY(clamp(vector.getY()));
            vector.setZ(clamp(vector.getZ()));
        }

        private double clamp(double value) {
            if (value > 255) {
                return 255;
            }

            if (value < 0) {
                return 0;
            }

            return value;
        }

    }
    
}
