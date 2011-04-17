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

    /**
     * This last point a collision with a ray occurred.
     *
     * @return {@link Vector3} where the hit occurred; otherwise, {@code null}
     */
    Vector3 getLastHitPoint();

    /**
     * The last normal check.
     *
     * @return {@link Vector3} normal; otherwise, {@code null}
     */
    Vector3 getLastNormal();

}
