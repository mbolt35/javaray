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
 * This {@link Geometry} implementation only allows a specific angle'd {@link Ray} to collide with it.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class SpotGeometry extends SphereGeometry implements Geometry {
    private final Vector3 lightDirection;
    private final double theta;

    public SpotGeometry(Vector3 position, Vector3 target, double radius, double theta) {
        super(position, radius);

        this.lightDirection = Vector3.subtract(position, target);
        this.theta = Math.cos(Math.toRadians(theta));

        lightDirection.normalize();
    }

    @Override
    public boolean isCollidable(Ray ray) {
        double angle = Vector3.dotProduct(ray.getDirection(), lightDirection);

        return angle > theta;
    }

    @Override
    public HitResult hits(Ray ray) {
        return super.hits(ray);
    }
    
    @Override
    public Vector3 normalTo(Vector3 point) {
        return super.normalTo(point);
    }
}
