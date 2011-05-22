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

package com.mattbolt.javaray.primitives;

import com.mattbolt.javaray.geom.Geometry;
import com.mattbolt.javaray.geom.PlaneGeometry;
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.materials.Material;


/**
 * This class represents a plane model in the scene.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class Plane extends AbstractPrimitive implements SceneObject {
    private final PlaneGeometry geometry;

    public Plane(Vector3 position, Vector3 normal, Material material) {
        super(position, material);
        this.geometry = new PlaneGeometry(position, normal);
    }

    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plane) || !super.equals(o)) {
            return false;
        }

        Plane plane = (Plane) o;

        return !(geometry != null ? !geometry.equals(plane.geometry) : plane.geometry != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (geometry != null ? geometry.hashCode() : 0);
        return result;
    }

}
