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

import com.mattbolt.javaray.geom.Vector3;


/**
 * This class is used to remove a dependency on {@code java.awt.Color}.
 *
 * @author Matt Bolt, mbolt35@gmail.com
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
