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

import com.mattbolt.javaray.materials.RayColor;


/**
 * This interface defines an implementation prototype for a target object that a scene can be rendered to.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public interface RenderTarget {

    /**
     * This method initializes the render target to prepare of receiving pixel data.
     */
    void init();

    /**
     * This method is used to update the render target mid-render. This is especially useful when rendering to a live
     * canvas.
     */
    void refresh();

    /**
     * This method notifies the render target of completion, and allows any final IO to process.
     */
    void complete();

    /**
     * This method pushes the coordinate and color data to the render target queue for processing.
     *
     * @param x The x-axis position of the pixel.
     *
     * @param y The y-axis position of the pixel.
     *
     * @param color The {@code ARGB} color value of the pixel.
     */
    void setPixelAt(int x, int y, RayColor color);
}
