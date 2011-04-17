package com.mattbolt.javaray.geom;

/**
 * This class represents a ray that is "shot" from the eye point through the scene. It collects composite intensity
 * data used to render it.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public final class Ray {
    private final Vector3 position;
    private final Vector3 direction;

    public Ray() {
        this(new Vector3());
    }

    public Ray(Vector3 position) {
        this(position, new Vector3());
    }

    public Ray(Vector3 position, Vector3 direction) {
        this.position = new Vector3(position);
        this.direction = new Vector3(direction);
    }

    public Vector3 getPosition() {
        return new Vector3(position);
    }

    public Vector3 getDirection() {
        return new Vector3(direction);
    }
}
