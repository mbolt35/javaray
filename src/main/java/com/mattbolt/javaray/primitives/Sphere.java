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

    public Sphere(Vector3 position, Material material, int radius) {
        super(position, material);
        this.geometry = new SphereGeometry(position, radius);
    }

    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    public static class SphereGeometry implements Geometry {
        private final Vector3 position;
        private final int radius;
        private final double radiusSquared;

        public SphereGeometry(Vector3 position, int radius) {
            this.position = position.copy();
            this.radius = radius;
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
                if (t > t2) {
                    t = t2;
                }

                hitLocation.copyFrom(hitsSphere(ray.getPosition(), direction, t));

                if (hitLocation.getZ() > 0 && ray.getPosition().getZ() > 0) {
                    return new HitResult(-1, null);
                }

                return new HitResult(t, hitLocation);
            }

            return new HitResult(-1, null);
        }

        private Vector3 hitsSphere(Vector3 base, Vector3 direction, double t) {
            Vector3 d = direction.copy();
            d.scale(t);

            return Vector3.add(base, d);
        }

        @Override
        public void normal(Vector3 position, SceneObject sceneObject) {
            Vector3 normal = Vector3.subtract(sceneObject.getLastHitPoint(), position);
            normal.normalize();

            sceneObject.getLastNormal().copyFrom(normal);
        }
    }
}