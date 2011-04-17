package com.mattbolt.javaray.util;

import com.mattbolt.javaray.geom.Vector3;

import java.awt.*;

/**
 * This class contains useful methods to convert {@code Color} and {@link com.mattbolt.javaray.geom.Vector3} instances.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public final class ColorHelper {

    public static Vector3 toVector3(Color color) {
        return new Vector3(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static Color toColor(Vector3 vector) {
        return new Color((int) vector.getX(), (int) vector.getY(), (int) vector.getZ());
    }

    public static Color randomColor() {
        return new Color(randomColorValue(), randomColorValue(), randomColorValue());
    }

    private static int randomColorValue() {
        return (int) (Math.random() * 255);
    }

}
