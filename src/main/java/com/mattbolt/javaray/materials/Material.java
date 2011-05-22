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

package com.mattbolt.javaray.materials;


/**
 * This class contains information pertaining to the material used in the rendering of the shape this material is
 * applied to.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class Material {
    private ColorMagnitude ambient;
    private ColorMagnitude diffuse;
    private ColorMagnitude specular;
    private ColorMagnitude emissivity;

    public Material(ColorMagnitude emissivity) {
        this.ambient = new ColorMagnitude(0, 0, 0);
        this.diffuse = new ColorMagnitude(0, 0, 0);
        this.specular = new ColorMagnitude(0, 0, 0);
        this.emissivity = emissivity;
    }

    public Material(ColorMagnitude ambient, ColorMagnitude diffuse, ColorMagnitude specular) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.emissivity = new ColorMagnitude(0, 0, 0);
    }

    public ColorMagnitude getAmbient() {
        return ambient;
    }

    public ColorMagnitude getDiffuse() {
        return diffuse;
    }

    public ColorMagnitude getSpecular() {
        return specular;
    }

    public ColorMagnitude getEmissivity() {
        return emissivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;

        Material material = (Material) o;

        return !(ambient != null ? !ambient.equals(material.ambient) : material.ambient != null)
            && !(diffuse != null ? !diffuse.equals(material.diffuse) : material.diffuse != null)
            && !(emissivity != null ? !emissivity.equals(material.emissivity) : material.emissivity != null)
            && !(specular != null ? !specular.equals(material.specular) : material.specular != null);

    }

    @Override
    public int hashCode() {
        int result = ambient != null ? ambient.hashCode() : 0;
        result = 31 * result + (diffuse != null ? diffuse.hashCode() : 0);
        result = 31 * result + (specular != null ? specular.hashCode() : 0);
        result = 31 * result + (emissivity != null ? emissivity.hashCode() : 0);
        return result;
    }
}
