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
        /*
        try {
            Resource r = new ClassPathResource( "log4j.properties" );
            PropertyConfigurator.configure(r.getURL());
        } catch (IOException e) {
            System.out.println("Failed load Log4J Configuration");
        }
        */

        logger.info( "Starting JavaRay Ray-Tracer by: Matt Bolt [mbolt35@gmail.com]" );

        ApplicationContext appContext = new ClassPathXmlApplicationContext("/JavaRayApplicationContext.xml");
        JavaRayConfiguration configuration = (JavaRayConfiguration) appContext.getBean("javaRayConfiguration");

        View view = new View(configuration);
        logger.debug("Configuration: [View: {}x{}], [Anti-Alias: {}]", new Object[] {
            configuration.getViewWidth(),
            configuration.getViewHeight(),
            configuration.getAntiAlias()
        });

        /*
        11              sphere
        4 3 -5.0        center
        2               radius

        0.0 0.0 0.0     ambient
        5 5 5      diffuse
        .5 .5 .5     specular

        10              light
        4 3 5           center
        1               radius
        12 12 12        emissivity
         */

        Camera camera = new Camera(new Vector3(4, 3, 3));

        Scene scene = new Scene(configuration);
        scene.add( new Sphere(new Vector3(4, 3, -5), newMat(2, 2, 2, 5, 5, 5), 2) );
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
