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

package com.mattbolt.javaray;

import com.mattbolt.javaray.camera.Camera;
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.light.Light;
import com.mattbolt.javaray.primitives.Sphere;
import com.mattbolt.javaray.render.Material;
import com.mattbolt.javaray.render.RayTracer;
import com.mattbolt.javaray.render.Scene;
import com.mattbolt.javaray.render.View;
import com.mattbolt.javaray.util.JavaRayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;

/**
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class JavaRay {
    private static final Logger logger = LoggerFactory.getLogger(JavaRay.class);

    public static void main(String[] args) {
        logger.info( "Starting JavaRay Ray-Tracer by: Matt Bolt [mbolt35@gmail.com]" );

        ApplicationContext appContext = new ClassPathXmlApplicationContext("/JavaRayApplicationContext.xml");
        JavaRayConfiguration configuration = (JavaRayConfiguration) appContext.getBean("javaRayConfiguration");

        View view = new View(configuration);
        logger.debug("Configuration: [View: {}x{}], [Anti-Alias: {}]", new Object[] {
            configuration.getViewWidth(),
            configuration.getViewHeight(),
            configuration.getAntiAlias()
        });

        Camera camera = new Camera(new Vector3(4, 3, 3));

        Scene scene = new Scene(configuration);
        scene.add( new Sphere(new Vector3(4, 3, -5), newMat(0, 0, 2, 0, 0, 5), 2) );
        scene.add( new Sphere(new Vector3(2, 5, -3), newMat(2, 0, 0, 5, 0, 0), 1) );
        scene.add( new Sphere(new Vector3(6, 5, -3), newMat(0, 2, 0, 0, 5, 0), 1) );
        scene.add( new Light(new Vector3(2, 1, 10), 3, 1) );
        scene.add( new Light(new Vector3(6, 1, 10), 3, 1) );

        new RayTracer(configuration).render(scene, view, camera);
    }

    private static Material newMat(int ar, int ag, int ab, int dr, int dg, int db) {
        return new Material(
            new Color(ar, ag, ab),
            new Color(dr, dg, db),
            new Color(0, 0, 0));
    }
}
