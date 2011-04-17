package com.mattbolt.javaray.light;

import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.primitives.SceneObject;
import com.mattbolt.javaray.primitives.Sphere;
import com.mattbolt.javaray.render.Material;

import java.awt.*;

/**
 *
 */
public class Light extends Sphere implements SceneObject {

    public Light(Vector3 position, int intensity, int radius) {
        super(position, new Material(new Color(intensity, intensity, intensity)), radius);
    }
}
