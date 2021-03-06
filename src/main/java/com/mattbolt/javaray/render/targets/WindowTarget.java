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

package com.mattbolt.javaray.render.targets;

import com.mattbolt.javaray.materials.RayColor;
import com.mattbolt.javaray.render.GraphicsWorker;
import com.mattbolt.javaray.render.blit.Pixel;
import com.mattbolt.javaray.render.blit.RasterPixelRenderer;
import com.mattbolt.javaray.render.RenderTarget;
import com.mattbolt.javaray.render.RenderWorker;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is used to render the scene to a native OS window.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class WindowTarget implements RenderTarget {
    private static final AtomicLong windowIdFactory = new AtomicLong(1);

    private final Frame windowFrame;
    private final Canvas canvas;
    private final RenderWorker renderWorker;

    private final int width;
    private final int height;

    public WindowTarget(int width, int height) {
        this(0, 0, width, height);
    }

    public WindowTarget(int x, int y, int width, int height) {
        long windowId = windowIdFactory.getAndIncrement();
        this.width = width;
        this.height = height;

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        renderWorker = new GraphicsWorker(new RasterPixelRenderer(bi.getRaster()));

        canvas = new RenderCanvas(bi);
        canvas.setSize(width, height);
        canvas.setVisible(true);

        windowFrame = new Frame("Render " + windowId);
        windowFrame.setLocation(x, y);
        windowFrame.setSize(width, height);
        windowFrame.add(canvas, "Center");
        windowFrame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                windowFrame.dispose();
                System.exit(0);
            }
        });
    }

    @Override
    public void setPixelAt(int x, int y, RayColor color) {
        renderWorker.pushPixel(new Pixel(x, height - y - 1, color));
    }

    @Override
    public void init() {
        windowFrame.setVisible(true);
        renderWorker.start();
    }

    @Override
    public void refresh() {
        canvas.repaint();
    }

    @Override
    public void complete() {
        renderWorker.complete();
    }

    /**
     * This custom {@code Canvas} sub-class draws the contents of the render to the window frame.
     *
     * @author Matt Bolt, mbolt35@gmail.com
     */
    private static class RenderCanvas extends Canvas {
        private final BufferedImage bi;
        
        private RenderCanvas(BufferedImage bi) {
            this.bi = bi;
        }

        @Override
        public void paint(Graphics graphics) {
            graphics.drawImage(bi, 0, 0, null);
        }
    }
}
