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

package com.mattbolt.javaray.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used as a wrapper for PNG ImageIO. It simply exposes a simple pixel writing method and an image
 * generation utility.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class PngImage {
    private static final Logger logger = LoggerFactory.getLogger(PngImage.class);

    private LinkedBlockingQueue<Pixel> pixels = new LinkedBlockingQueue<Pixel>();
    private AtomicBoolean accepting = new AtomicBoolean(true);
    private final BufferedImage bi;
    private final Graphics2D graphics;
    private final Color[][] imageMap;

    private final int width;
    private final int height;

    public PngImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.imageMap = new Color[width + 1][height + 1];
        this.bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.graphics = bi.createGraphics();
        Thread t = new Thread(new Runnable() {
        	public void run() {
        		try {
        			while(accepting.get()) {
	        			Pixel p = pixels.take();
	        			renderPixel( p );
	        		}
        		}
        		catch(InterruptedException e) {
        			logger.debug("Someone must want us to stop!");
        			throw new RuntimeException("Image render thread stopped unexpectedly.");
        		}
        	}
        });
        t.start();
    }

    private static class Pixel {
    	int x;
    	int y;
    	Color color;
    	
    	private Pixel( int x, int y, Color color ) {
    		this.x = x;
    		this.y = y;
    		this.color = color;
    	}
    }
    public void setPixelAt(int x, int y, Color color) {
        pixels.add(new Pixel(x, y, color));
    }
    
    private void renderPixel( Pixel p ) {
    	graphics.setColor(p.color);
        graphics.fillRect(p.x, height - p.y, 1, 1);
    }

    public void createPngImage(String fileName) {
    	accepting.set(false);
        try {
            File file = new File(fileName);

            if (file.exists()) {
                file.delete();
            }

            ImageIO.write(bi, "PNG", file);
        } catch (IOException e) {
            logger.error("Failed to create PNG image.");
        }
    }
}
