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

import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.render.ColorMagnitude;
import com.mattbolt.javaray.render.Material;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This abstract class should be used to as a base class for a primitive shape. It provides the basic getter/setter methods
 * for position, materials, and lights.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public abstract class AbstractPrimitive {
    private static final AtomicLong idFactory = new AtomicLong();

    protected final long id;
    protected final Vector3 position;
    protected final Material material;

    public AbstractPrimitive(Vector3 position, Material material) {
        this.id = idFactory.incrementAndGet();
        this.position = position;
        this.material = material;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isEmittingLight() {
        ColorMagnitude emissivity = material.getEmissivity();
        return emissivity.getRed() > 0 || emissivity.getGreen() > 0 || emissivity.getBlue() > 0;
    }

    public boolean isVisible() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPrimitive)) return false;

        AbstractPrimitive that = (AbstractPrimitive) o;

        return id == that.id
            && !(material != null ? !material.equals(that.material) : that.material != null)
            && !(position != null ? !position.equals(that.position) : that.position != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        return result;
    }
}
