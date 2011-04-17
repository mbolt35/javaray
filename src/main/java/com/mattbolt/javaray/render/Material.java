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

import java.awt.*;

/**
 * This class contains information pertaining to the material used in the rendering of the shape this material is
 * applied to.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class Material {
    private Color ambient;
    private Color diffuse;
    private Color specular;
    private Color emissivity;

    public Material(Color emissivity) {
        this.ambient = new Color(0, 0, 0);
        this.diffuse = new Color(0, 0, 0);
        this.specular = new Color(0, 0, 0);
        this.emissivity = emissivity;
    }

    public Material(Color ambient, Color diffuse, Color specular) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.emissivity = new Color(0, 0, 0);
    }

    public Color getAmbient() {
        return ambient;
    }

    public Color getDiffuse() {
        return diffuse;
    }

    public Color getSpecular() {
        return specular;
    }

    public Color getEmissivity() {
        return emissivity;
    }
}
