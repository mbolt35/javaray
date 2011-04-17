package com.mattbolt.javaray.render;

import com.mattbolt.javaray.util.JavaRayConfiguration;

/**
 * This class represents the basic view of the scene. It defines the area displayed as well as the point of view the
 * scene is rendered from.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class View {

    private final int width;
    private final int height;

    public View(JavaRayConfiguration configuration) {
        this.width = configuration.getViewWidth();
        this.height = configuration.getViewHeight();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
