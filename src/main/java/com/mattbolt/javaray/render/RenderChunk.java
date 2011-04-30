package com.mattbolt.javaray.render;

/**
 * This class specifies a region of the entire scene to render.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class RenderChunk {

    public int x;
    public int y;
    public int width;
    public int height;

    public RenderChunk() {
    	this(0, 0, 0, 0);
    }

    public RenderChunk(RenderChunk r) {
    	this(r.x, r.y, r.width, r.height);
    }

    public RenderChunk(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
