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

import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.render.ColorMagnitude;

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

    public static Vector3 toVector3(ColorMagnitude color) {
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
