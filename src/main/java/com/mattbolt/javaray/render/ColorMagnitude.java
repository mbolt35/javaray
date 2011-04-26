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

package com.mattbolt.javaray.render;

/**
 * This class represents the magnitude of color to use in a material.
 *
 * @author Matt Bolt, mbolt35@gmail.com
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
