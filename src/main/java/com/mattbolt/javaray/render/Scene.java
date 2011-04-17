package com.mattbolt.javaray.render;

import com.mattbolt.javaray.primitives.SceneObject;
import com.mattbolt.javaray.util.JavaRayConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains different components used to render an image.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class Scene {

    private final int worldWidth;
    private final int worldHeight;

    private List<SceneObject> sceneObjects = new ArrayList<SceneObject>();

    public Scene(JavaRayConfiguration configuration) {
        this.worldWidth = configuration.getWorldWidth();
        this.worldHeight = configuration.getWorldHeight();
    }

    public void add(SceneObject object) {
        sceneObjects.add(object);
    }

    public List<SceneObject> getSceneObjects() {
        return sceneObjects;
    }

    public int size() {
        return sceneObjects.size();
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }
}
