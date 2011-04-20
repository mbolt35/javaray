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

import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.primitives.SceneObject;
import com.mattbolt.javaray.primitives.Sphere;
import com.mattbolt.javaray.render.ColorMagnitude;
import com.mattbolt.javaray.render.Material;

/**
 *
 */
public class Light extends Sphere implements SceneObject {

    public Light(Vector3 position, double intensity, double radius) {
        super(position, new Material(new ColorMagnitude(intensity, intensity, intensity)), radius);
    }

    @Override
    public String toString() {
        return new StringBuilder("Light[").append(id).append("]").toString();
    }
}
