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
import com.mattbolt.javaray.primitives.Plane;
import com.mattbolt.javaray.primitives.Sphere;
import com.mattbolt.javaray.render.ColorMagnitude;
import com.mattbolt.javaray.render.Material;
import com.mattbolt.javaray.render.RayTracer;
import com.mattbolt.javaray.render.Scene;
import com.mattbolt.javaray.render.View;
import com.mattbolt.javaray.util.JavaRayConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

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
        Scene scene = getSceneTwo(configuration);

        long t = new Date().getTime();

        try {
            new RayTracer(configuration).render(scene, view, camera);
        } catch (Exception e) {
            logger.error("Error", e);
        }

        logger.debug("Took: {} seconds", ((new Date().getTime() - t) / 1000));
    }

    private static Scene getSceneOne(JavaRayConfiguration configuration) {
        Scene scene = new Scene(configuration);
        scene.add( new Sphere(new Vector3(4, 3, -5), newMat(0, 0, 0, 6, 6, 6, 10, 10, 10), 2.5) );
        scene.add( new Sphere(new Vector3(2.75, 4, -1), newMat(2, 0, 0, 5, 0, 0, 0, 0, 2), 0.5) );
        scene.add( new Sphere(new Vector3(5.25, 4, -1), newMat(0, 2, 0, 0, 5, 0, 0, 2, 0), 0.5) );
        scene.add( new Light(new Vector3(4, 3, 10), 5, 1) );
        //scene.add( new Light(new Vector3(6, 1, 10), 3, 1) );

        return scene;
    }

    private static Scene getSceneTwo(JavaRayConfiguration configuration) {
        Scene scene = new Scene(configuration);

        // Lights
        scene.add( new Light(new Vector3(4, 10, -1), 8, 1) );
        scene.add( new Light(new Vector3(4, 3, 2), 5, 1) );
        scene.add( new Light(new Vector3(4, 16, -12), 8, 0.2) );
        scene.add( new Light(new Vector3(-1, 10, -1), 9, 1) );

        // Planes
        scene.add( new Plane(new Vector3(1, 0, 1), new Vector3(0, 1, 0), newMat(0, 0, 0, 2, 2, 2, 0.1, 0.1, 0.1)) );
        scene.add( new Plane(new Vector3(0, 0, -16), new Vector3(0, 0, 1), newMat(0, 0, 0, 6, 6, 6, 0.2, 0.2, 0.2)) );

        // Spheres
        scene.add( new Sphere(new Vector3(4, 4.1, -10), newMat(0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 5.0, 5.0, 5.0), 4.0) );
        scene.add( new Sphere(new Vector3(2, 7, -2), newMat(0.2, 0.2, 0.2, 2.6, 2.6, 2.6, 0, 0, 0), 1.0) );
        scene.add( new Sphere(new Vector3(4, 7, -3), newMat(0.2, 0.2, 0.2, 2.6, 2.6, 2.6, 0, 0, 0), 1.0) );
        scene.add( new Sphere(new Vector3(6, 7, -5), newMat(0.2, 0.2, 0.2, 2.4, 2.4, 2.4, 0, 0, 0), 1.0) );
        scene.add( new Sphere(new Vector3(2, 1, -2), newMat(0.2, 0.2, 0.2, 2.6, 2.6, 2.6, 0, 0, 0), 1.0) );
        scene.add( new Sphere(new Vector3(4, 1, -3), newMat(0.2, 0.2, 0.2, 2.4, 2.4, 2.4, 0, 0, 0), 1.0) );
        scene.add( new Sphere(new Vector3(6, 1, -5), newMat(0.2, 0.2, 0.2, 2.4, 2.4, 2.4, 0, 0, 0), 1.0) );

        return scene;
    }

    private static Material newMat( double ar,
                                    double ag,
                                    double ab,
                                    double dr,
                                    double dg,
                                    double db,
                                    double sr,
                                    double sg,
                                    double sb )
    {
        return new Material(
            new ColorMagnitude(ar, ag, ab),
            new ColorMagnitude(dr, dg, db),
            new ColorMagnitude(sr, sg, sb));
    }
}
