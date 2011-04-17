package com.mattbolt.javaray.camera;

import com.mattbolt.javaray.geom.Vector3;

/**
 * This class represents a basic eye point camera used with the view to render the scene.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class Camera {

    private final Vector3 position;

    public Camera() {
        this(new Vector3());
    }

    public Camera(Vector3 position) {
        this.position = position;
    }

    public Vector3 getPosition() {
        return position;
    }
}
