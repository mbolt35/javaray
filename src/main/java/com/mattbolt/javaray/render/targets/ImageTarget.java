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
import com.mattbolt.javaray.render.blit.GraphicsPixelRenderer;
import com.mattbolt.javaray.render.GraphicsWorker;
import com.mattbolt.javaray.render.blit.Pixel;
import com.mattbolt.javaray.render.RenderTarget;
import com.mattbolt.javaray.render.RenderWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is used as a wrapper for PNG ImageIO. It simply exposes a simple pixel writing method and an image
 * generation utility.
 *
 * All concurrency structures and designed handled by realjenius - thanks sir!
 *
 * @author Matt Bolt, mbolt35@gmail.com
 * @author realjenius
 */
public class ImageTarget implements RenderTarget {
    private static final Logger logger = LoggerFactory.getLogger(ImageTarget.class);

    private final BufferedImage bi;
    private final ImageType imageType;
    private final RenderWorker renderWorker;
    private final File file;

    private final int width;
    private final int height;

    public ImageTarget(String fileName, ImageType imageType, int width, int height) {
        this.file = new File(fileName + "." + imageType.getType().toLowerCase());
        this.imageType = imageType;
        this.width = width;
        this.height = height;

        this.bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        renderWorker = new GraphicsWorker(new GraphicsPixelRenderer(bi.createGraphics()));
    }

    @Override
    public void setPixelAt(int x, int y, RayColor color) {
        renderWorker.pushPixel(new Pixel(x, height - y, color));
    }

    @Override
    public void init() {
        renderWorker.start();
    }

    @Override
    public void refresh() {

    }
    
    @Override
    public void complete() {
        renderWorker.complete();

        try {
            if (file.exists()) {
                boolean fileDeleted = file.delete();
            }

            ImageIO.write(bi, imageType.getType(), file);

        } catch (IOException e) {
            logger.error("Failed to create PNG image.");
        }
    }

    public File getFile() {
        return file;
    }

    /**
     * This enumeration type is passed to the image target to denote the type of image that will be encoded upon
     * ray-trace completion.
     *
     * @author Matt Bolt, mbolt35@gmail.com
     */
    public static enum ImageType {
        PNG("PNG"),
        JPG("JPG"),
        BMP("BMP");

        private String type;

        ImageType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
