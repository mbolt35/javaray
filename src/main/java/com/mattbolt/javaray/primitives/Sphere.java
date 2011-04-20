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
import com.mattbolt.javaray.geom.Ray;
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.render.Material;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class Sphere extends AbstractPrimitive implements SceneObject {
    private static final Logger logger = LoggerFactory.getLogger(Sphere.class);

    private final Geometry geometry;

    public Sphere(Vector3 position, Material material, double radius) {
        super(position, material);
        this.geometry = new SphereGeometry(position, radius);
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
        if (this == o) return true;
        if (!(o instanceof Sphere)) return false;
        if (!super.equals(o)) return false;

        Sphere sphere = (Sphere) o;

        return !(geometry != null ? !geometry.equals(sphere.geometry) : sphere.geometry != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (geometry != null ? geometry.hashCode() : 0);
        return result;
    }

    /**
     * This class is used to represent a sphere geometrically.
     */
    public static class SphereGeometry implements Geometry {
        private final Vector3 position;
        private final double radiusSquared;

        public SphereGeometry(Vector3 position, double radius) {
            this.position = position.copy();
            this.radiusSquared = Math.pow(radius, 2.0);
        }

        @Override
        public HitResult hits(Ray ray) {
            Vector3 hitLocation = new Vector3();
            Vector3 direction = ray.getDirection();
            direction.normalize();

            Vector3 start = Vector3.subtract(ray.getPosition(), position);

            double a = Vector3.dotProduct(direction, direction);
            double b = Vector3.dotProduct(direction, start) * 2.0;
            double c = Vector3.dotProduct(start, start) - radiusSquared;

            double disc = Math.pow(b, 2.0) - (4.0 * a * c);

            if (disc > 0) {
                double sqrtDisc = Math.sqrt(disc);

                double t = (-b - sqrtDisc) / (2.0 * a);
                double t2 = (-b + sqrtDisc) / (2.0 * a);

                // Choose the closest point
                t = Math.min(t, t2);

                hitLocation.copyFrom(hitsSphere(ray.getPosition(), direction, t));

                if (hitLocation.getZ() > 0 && ray.getPosition().getZ() > 0) {
                    return new HitResult(-1, null);
                }

                return new HitResult(t, hitLocation);
            }

            return new HitResult(-1, null);
        }

        private Vector3 hitsSphere(Vector3 base, Vector3 direction, double t) {
            Vector3 d = new Vector3(direction);
            d.normalize();
            d.scale(t);

            return Vector3.add(base, d);
        }

        @Override
        public Vector3 normalTo(Vector3 point) {
            Vector3 normal = Vector3.subtract(point, position);
            normal.normalize();
            return normal;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SphereGeometry)) return false;

            SphereGeometry that = (SphereGeometry) o;

            return Double.compare(that.radiusSquared, radiusSquared) == 0
                && !(position != null ? !position.equals(that.position) : that.position != null);

        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = position != null ? position.hashCode() : 0;
            temp = radiusSquared != +0.0d ? Double.doubleToLongBits(radiusSquared) : 0L;
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }
}
