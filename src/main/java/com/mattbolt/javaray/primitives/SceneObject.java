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
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.render.Material;

/**
 * This interface defines an implementation prototype for an object that can be rendered in the scene.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public interface SceneObject {

    /**
     * This property contains the position of the shape in 3D-space.
     */
    Vector3 getPosition();

    /**
     * This property contains the {@link com.mattbolt.javaray.render.Material} used to render the shape.
     */
    Material getMaterial();

    /**
     * This property contains the {@link Geometry} used to determine hit collisions.
     */
    Geometry getGeometry();

    /**
     * This method returns {@code true} if the object is emitting light.
     *
     * @return {@code true} if the object is emitting light
     */
    boolean isEmittingLight();

}
