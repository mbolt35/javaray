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

import com.mattbolt.javaray.render.blit.Pixel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This {@code Runnable} implementation uses a {@code BlockingQueue} of {@code Pixel} to write pixels to a canvas
 * concurrently.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class GraphicsWorker implements RenderWorker {
    private static final Logger logger = LoggerFactory.getLogger(GraphicsWorker.class);

    private final Thread consumer;
    private final GraphicsRunnable runnable;

    public GraphicsWorker(PixelRenderer pixelRenderer) {
        runnable = new GraphicsRunnable(pixelRenderer);
        consumer = new Thread(runnable);
    }

    @Override
    public void start() {
        consumer.start();
    }

    @Override
    public void pushPixel(Pixel pixel) {
        runnable.pixels.add(pixel);
    }

    @Override
    public void complete() {
        runnable.latch.set(false);
        consumer.interrupt();
    }


    /**
     * The {@code Runnable} implementation used for the worker thread.
     *
     * @author Matt Bolt, mbolt35@gmail.com
     */
    private static class GraphicsRunnable implements Runnable {
        private final LinkedBlockingQueue<Pixel> pixels;
        private final AtomicBoolean latch;
        private final PixelRenderer pixelRenderer;

        public GraphicsRunnable(PixelRenderer pixelRenderer) {
            this.pixels = new LinkedBlockingQueue<Pixel>();
            this.latch = new AtomicBoolean(true);
            this.pixelRenderer = pixelRenderer;
        }

        @Override
        public void run() {
            try {
                while (latch.get()) {
                    Pixel p = pixels.take();

                    pixelRenderer.renderPixel(p);
                }
            } catch (InterruptedException e) {
                logger.debug("GraphicsWorker Completed...");
            }
        }

    }
}
