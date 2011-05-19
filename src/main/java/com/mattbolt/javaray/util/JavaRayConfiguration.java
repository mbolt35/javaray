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

package com.mattbolt.javaray.util;

/**
 * This class is used to hold the configuration values for JavaRay.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public final class JavaRayConfiguration {

    private final int worldWidth;
    private final int worldHeight;
    private final int viewWidth;
    private final int viewHeight;
    private final int antiAlias;
    private final int threadPoolSize;
    private final int xChunkSize;
    private final int yChunkSize;

    public JavaRayConfiguration( int worldWidth,
                                 int worldHeight,
                                 int viewWidth,
                                 int viewHeight,
                                 int antiAlias,
                                 int threadPoolSize,
                                 int xChunkSize,
                                 int yChunkSize )
    {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        this.antiAlias = antiAlias;
        this.threadPoolSize = threadPoolSize;
        this.xChunkSize = xChunkSize;
        this.yChunkSize = yChunkSize;
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

    public int getXChunkSize() {
        return xChunkSize;
    }

    public int getYChunkSize() {
        return yChunkSize;
    }
}
