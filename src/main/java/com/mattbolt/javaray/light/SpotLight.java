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

package com.mattbolt.javaray.light;

import com.mattbolt.javaray.geom.Geometry;
import com.mattbolt.javaray.geom.SpotGeometry;
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.materials.ColorMagnitude;
import com.mattbolt.javaray.materials.Material;
import com.mattbolt.javaray.primitives.AbstractPrimitive;
import com.mattbolt.javaray.primitives.SceneObject;


/**
 * This class represents an {@code ARGB} spot light which only emits light particles at a specific {@code theta} angle.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class SpotLight extends AbstractPrimitive implements SceneObject {
    private final Geometry geometry;

    public SpotLight(Vector3 position, Vector3 target, double intensity, double radius, double theta) {
        this(position, target, new ColorMagnitude(intensity, intensity, intensity), radius, theta);
    }

    public SpotLight(Vector3 position, Vector3 target, ColorMagnitude emissivity, double radius, double theta) {
        super(position, new Material(emissivity));
        
        geometry = new SpotGeometry(position, target, radius, theta);
    }

    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

}
