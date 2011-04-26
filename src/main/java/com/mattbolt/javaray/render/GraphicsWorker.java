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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This
 */
public class GraphicsWorker implements Runnable {

    private final BlockingQueue<Pixel> pixels;
    private final AtomicBoolean latch;
    private final PixelRenderer pixelRenderer;

    public GraphicsWorker(BlockingQueue<Pixel> pixels, AtomicBoolean latch, PixelRenderer pixelRenderer) {
        this.pixels = pixels;
        this.latch = latch;
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

        }
    }
}
