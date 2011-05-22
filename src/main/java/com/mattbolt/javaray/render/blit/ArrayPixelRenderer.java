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

package com.mattbolt.javaray.render.blit;

import com.mattbolt.javaray.render.PixelRenderer;


/**
 * This {@link com.mattbolt.javaray.render.PixelRenderer} implementation writes pixels to an {@code int[]}.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class ArrayPixelRenderer implements PixelRenderer {

    private final int width;
    private final int height;

    private final int[] pixels;

    public ArrayPixelRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }

    @Override
    public void renderPixel(Pixel p) {
        pixels[width * (p.y - 1) + p.x] = p.color.getARGB();
    }

    public int[] getPixels() {
        return pixels;
    }
}
