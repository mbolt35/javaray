package com.mattbolt.javaray.render;

/**
 * This class represents the magnitude of color to use in a material.  
 */
public class ColorMagnitude {
    private final double red;
    private final double green;
    private final double blue;

    public ColorMagnitude(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorMagnitude)) return false;

        ColorMagnitude that = (ColorMagnitude) o;

        return Double.compare(that.blue, blue) == 0
            && Double.compare(that.green, green) == 0
            && Double.compare(that.red, red) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = red != +0.0d ? Double.doubleToLongBits(red) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = green != +0.0d ? Double.doubleToLongBits(green) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = blue != +0.0d ? Double.doubleToLongBits(blue) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
