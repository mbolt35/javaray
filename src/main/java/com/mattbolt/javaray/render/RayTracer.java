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
import com.mattbolt.javaray.geom.Rect;
import com.mattbolt.javaray.util.GenericCallback;
import com.mattbolt.javaray.util.JavaRayConfiguration;
import com.mattbolt.javaray.util.JavaRayExecutorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final int threadPoolSize;

    private ExecutorService imageSegments = null;

    public RayTracer(JavaRayConfiguration configuration) {
        totalSamples = configuration.getAntiAlias();
        xChunkSize = configuration.getXChunkSize();
        yChunkSize = configuration.getYChunkSize();
        threadPoolSize = configuration.getThreadPoolSize();
    }

    public void synchronousRender(Scene scene, View view, Camera camera, final RenderTarget renderTarget) {
        Rect rect = new Rect(0, 0, view.getWidth(), view.getHeight());
        final CountDownLatch latch = new CountDownLatch(1);

        renderTarget.init();

        new ImageSegmentWorker(scene, view, camera, renderTarget, rect, totalSamples, new GenericCallback() {
            @Override
            public void onComplete() {
                latch.countDown();
                logger.debug("count: " + latch.getCount());
            }
        }).run();

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Thread interrupted!", e);
        } finally {
            renderTarget.complete();
        }

        logger.debug("Finished!");
    }

    public void render(Scene scene, View view, Camera camera, final RenderTarget renderTarget) {
        if (null == imageSegments) {
            imageSegments = JavaRayExecutorFactory.newFixedThreadPool(threadPoolSize);
        }
        
        int chunkWidth = (int) Math.floor(view.getWidth() / xChunkSize);
        int chunkHeight = (int) Math.floor(view.getHeight() / yChunkSize);
        int totals = (view.getWidth() / chunkWidth) * (view.getHeight() / chunkHeight);

        final CountDownLatch latch = new CountDownLatch(totals);

        renderTarget.init();

        for (int x = 0; x < view.getWidth(); x += chunkWidth) {
            for (int y = 0; y < view.getHeight(); y += chunkHeight) {
                Rect chunk = new Rect(x, y, chunkWidth, chunkHeight);

                imageSegments.submit(new ImageSegmentWorker(scene, view, camera, renderTarget, chunk,
                    totalSamples,
                    new GenericCallback() {
                        @Override
                        public void onComplete() {
                            latch.countDown();
                            logger.debug("count: " + latch.getCount());
                            renderTarget.refresh();
                        }
                    }));
            }
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Thread interrupted!", e);
        } finally {
            renderTarget.complete();
        }

        logger.debug("Finished!");
        imageSegments.shutdown();
    }

}
