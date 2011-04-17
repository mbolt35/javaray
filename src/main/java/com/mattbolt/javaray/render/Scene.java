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
