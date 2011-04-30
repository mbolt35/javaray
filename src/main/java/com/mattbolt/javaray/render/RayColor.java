package com.mattbolt.javaray.render;

import com.mattbolt.javaray.geom.Vector3;


/**
 * This class is used to remove a dependency on {@code java.awt.Color}. If the {@code java.awt.Color} class is needed,
 * 
 */
public class RayColor {
    private int red;
    private int green;
    private int blue;
    private int alpha;

    public RayColor(Vector3 intensity) {
        this((int) intensity.getX(), (int) intensity.getY(), (int) intensity.getZ());
    }

    public RayColor(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    public RayColor(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getARGB() {
        return ((alpha & 0xFF) << 24)
             | ((red & 0xFF) << 16)
             | ((green & 0xFF) << 8)
             | ((blue & 0xFF) << 0);
    }
}
