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

package com.mattbolt.javaray.geom;

/**
 * This interface defines the implementation prototype for an object that calculates the geometrical properties of an
 * renderable object.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public interface Geometry {

    /**
     * This method detects a hit from the ray.
     *
     * @param ray
     *
     * @return
     */
    HitResult hits(Ray ray);

    /**
     * This method finds
     * @param
     */
    Vector3 normalTo(Vector3 point);

    /**
     * This class is used to represent a hit result.
     */
    final class HitResult {
        private final double t;
        private final Vector3 hitPoint;

        public HitResult(double t, Vector3 hitPoint) {
            this.t = t;
            this.hitPoint = hitPoint != null ? new Vector3(hitPoint) : null;
        }

        public double getT() {
            return t;
        }

        public Vector3 getHitPoint() {
            return null != hitPoint ? new Vector3(hitPoint) : null;
        }
    }
}
