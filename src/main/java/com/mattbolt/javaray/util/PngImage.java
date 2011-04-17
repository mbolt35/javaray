package com.mattbolt.javaray.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PngImage {
    private static final Logger logger = LoggerFactory.getLogger(PngImage.class);

    private final BufferedImage bi;
    private final Graphics2D graphics;

    public PngImage(int width, int height) {
        this.bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.graphics = bi.createGraphics();
    }

    public void setPixelAt(int x, int y, Color color) {
        graphics.setColor(color);
        graphics.fillRect(x, y, 1, 1);
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

    /*
    graphics.setColor(Color.red);
            graphics.fillRect(50, 50, 100, 100);
    */
}
