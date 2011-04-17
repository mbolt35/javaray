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
