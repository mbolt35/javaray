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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class is used as a wrapper for PNG ImageIO. It simply exposes a simple pixel writing method and an image
 * generation utility.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class PngImage {
    private static final Logger logger = LoggerFactory.getLogger(PngImage.class);
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

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
    }

    public void setPixelAt(int x, int y, Color color) {
        lock.writeLock().lock();
        
        try {
            graphics.setColor(color);
            graphics.fillRect(x, height - y, 1, 1);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void createPngImage(String fileName) {
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
