package com.mattbolt.javaray.primitives;

import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.render.Material;

import java.awt.*;

/**
 *
 */
public abstract class AbstractPrimitive {
    protected Vector3 position;
    protected Material material;
    protected Vector3 lastHitPoint;
    protected Vector3 lastNormal;

    public AbstractPrimitive(Vector3 position, Material material) {
        this.position = position;
        this.material = material;
        this.lastHitPoint = new Vector3();
        this.lastNormal = new Vector3();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Material getMaterial() {
        return material;
    }

    public Vector3 getLastHitPoint() {
        return lastHitPoint;
    }

    public Vector3 getLastNormal() {
        return lastNormal;
    }

    public boolean isEmittingLight() {
        Color emissivity = material.getEmissivity();
        return emissivity.getRed() > 0 || emissivity.getGreen() > 0 || emissivity.getBlue() > 0;
    }
}
