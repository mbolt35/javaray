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
import com.mattbolt.javaray.render.Material;

import java.awt.*;

/**
 *
 */
public abstract class AbstractPrimitive {
    protected Vector3 position;
    protected Material material;
    protected Vector3 lastHitPoint;
    protected Vector3 lastNormal;

    public AbstractPrimitive(Vector3 position, Material material) {
        this.position = position;
        this.material = material;
        this.lastHitPoint = new Vector3();
        this.lastNormal = new Vector3();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Material getMaterial() {
        return material;
    }

    public Vector3 getLastHitPoint() {
        return lastHitPoint;
    }

    public Vector3 getLastNormal() {
        return lastNormal;
    }

    public boolean isEmittingLight() {
        Color emissivity = material.getEmissivity();
        return emissivity.getRed() > 0 || emissivity.getGreen() > 0 || emissivity.getBlue() > 0;
    }
}
