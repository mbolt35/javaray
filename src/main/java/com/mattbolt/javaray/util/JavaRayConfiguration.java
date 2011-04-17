package com.mattbolt.javaray.util;

/**
 * This class is used to hold the configuration values for JavaRay.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class JavaRayConfiguration {

    private final int worldWidth;
    private final int worldHeight;
    private final int viewWidth;
    private final int viewHeight;
    private final int antiAlias;
    private final int threadPoolSize;

    public JavaRayConfiguration( int worldWidth,
                                 int worldHeight,
                                 int viewWidth,
                                 int viewHeight,
                                 int antiAlias,
                                 int threadPoolSize )
    {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        this.antiAlias = antiAlias;
        this.threadPoolSize = threadPoolSize;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public int getAntiAlias() {
        return antiAlias;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }
}
