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
import com.mattbolt.javaray.geom.SphereGeometry;
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.render.Material;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a sphere model in the scene.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class Sphere extends AbstractPrimitive implements SceneObject {
    private static final Logger logger = LoggerFactory.getLogger(Sphere.class);

    private final Geometry geometry;

    public Sphere(Vector3 position, Material material, double radius) {
        super(position, material);

        geometry = new SphereGeometry(position, radius);
    }

    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    public String toString() {
        return new StringBuilder("Sphere[").append(id).append("]").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Sphere) || !super.equals(o)) {
            return false;
        }

        Sphere sphere = (Sphere) o;

        return !(geometry != null ? !geometry.equals(sphere.geometry) : sphere.geometry != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (geometry != null ? geometry.hashCode() : 0);
        return result;
    }

}
