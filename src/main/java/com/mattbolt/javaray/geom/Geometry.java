package com.mattbolt.javaray.geom;

import com.mattbolt.javaray.primitives.SceneObject;

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
     *
     * @param position
     * @param sceneObject
     */
    void normal(Vector3 position, SceneObject sceneObject);

    /**
     * This class is used to represent a hit result.
     */
    class HitResult {
        public final double t;
        public final Vector3 hitPoint;

        public HitResult(double t, Vector3 hitPoint) {
            this.t = t;
            this.hitPoint = hitPoint;
        }
    }
}
