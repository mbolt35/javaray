package com.mattbolt.javaray.geom;

/**
 * This class specifies a region of the entire scene to render.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class Rect {

    public int x;
    public int y;
    public int width;
    public int height;

    public Rect() {
    	this(0, 0, 0, 0);
    }

    public Rect(Rect r) {
    	this(r.x, r.y, r.width, r.height);
    }

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
