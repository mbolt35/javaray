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
import com.mattbolt.javaray.util.PngImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;


/**
 * This renderer implementation sends lots of rays from the eye point, and magnifies intensity of the hit locations
 * based on their distance from the eye point.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class RayTracer {

    private static final Logger logger = LoggerFactory.getLogger(RayTracer.class);

    private final int totalSamples;

    public RayTracer(JavaRayConfiguration configuration) {
        totalSamples = configuration.getAntiAlias();
    }

    public void render(Scene scene, View view, Camera camera) {
        PngImage pngImage = new PngImage(view.getWidth(), view.getHeight());

        for (int x = 0; x < view.getWidth(); ++x) {
            for (int y = 0; y < view.getHeight(); ++y) {
                Vector3 position = pixelToWorld(scene, view, x, y);

                Vector3 intensity = rayTrace(
                        scene.getSceneObjects(),
                        camera.getPosition(),
                        Vector3.subtract(position, camera.getPosition()),
                        0.0,
                        null);

                intensity.scale(255.0);
                clamp(intensity);

                pngImage.setPixelAt(x, y, ColorHelper.toColor(intensity));
            }
        }


        pngImage.createPngImage("test.png");
    }

    private Vector3 rayTrace( List<SceneObject> objects,
                              Vector3 viewPoint,
                              Vector3 direction,
                              double totalDistance,
                              SceneObject lastHit )
    {
        double min = Double.MAX_VALUE;
        SceneObject closeObject = null;
        Vector3 intensity = new Vector3();

        for (SceneObject object : objects) {
            if (!object.equals(lastHit)) {
                Vector3 hitPoint = object.getGeometry().hits(new Ray(viewPoint, direction)).hitPoint;

                if (null != hitPoint) {
                    object.getLastHitPoint().copyFrom(hitPoint);

                    object.getGeometry().normal(object.getPosition(), object);

                    Vector3 temp = Vector3.subtract(hitPoint, viewPoint);
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
        Vector3 diffuse = diffuseFor(objects, closeObject);

        intensity.add(Vector3.add(ambient, diffuse));
        intensity.scale(1.0 / totalDistance);

        /*
            1. Check closeObject specular properties
            2. Unit Vector normal
            3. Unit Vector direction
            4. Angle of Reflection distance along normal
            5. check total distance for something reasonable (0 - 50?)
            6. multiply specular * intensity
            7. add specular back to composite data
        */

        return intensity;
    }

    private Vector3 diffuseFor(List<SceneObject> objects, SceneObject hitObject) {
        Vector3 diffuse = new Vector3();

        for (SceneObject object : objects) {
            if (object.isEmittingLight()) {
                // Find the vector between the center of the object and the hit point
                Vector3 direction = Vector3.subtract(object.getPosition(), hitObject.getLastHitPoint());

                // Get the distance (for amount of intensity to use)
                double distance = direction.getLength();

                // Normalize *after* getting the length
                direction.normalize();

                if (isLightVisible(objects, hitObject, object, distance)) {
                    double cos = Vector3.dotProduct(direction, hitObject.getLastNormal());

                    if (cos > 0) {
                        Color diff = hitObject.getMaterial().getDiffuse();
                        Color emissivity = object.getMaterial().getEmissivity();

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

    private boolean isLightVisible( List<SceneObject> objects,
                                    SceneObject hitObject,
                                    SceneObject lightSource,
                                    double distance )
    {
        Vector3 difference = Vector3.subtract(lightSource.getPosition(), hitObject.getLastHitPoint());

        for (SceneObject object : objects) {
            if (!object.equals(hitObject) && !object.equals(lightSource)) {
                Vector3 unitDifference = difference.copy();
                unitDifference.normalize();

                Geometry.HitResult result = object.getGeometry().hits(
                    new Ray(hitObject.getLastHitPoint(), unitDifference));

                if (result.t > 0 && result.t <= distance) {
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
