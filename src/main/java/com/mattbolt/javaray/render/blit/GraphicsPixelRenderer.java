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
import com.mattbolt.javaray.util.ColorHelper;

import java.awt.*;

/**
 * This {@link com.mattbolt.javaray.render.PixelRenderer} implementation draws pixels to a {@code java.awt.Graphics}
 * object.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class GraphicsPixelRenderer implements PixelRenderer {
    private final Graphics graphics;

    public GraphicsPixelRenderer(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void renderPixel(Pixel p) {
        graphics.setColor(ColorHelper.toColor(p.color));
        graphics.fillRect(p.x, p.y, 1, 1);
    }
}
