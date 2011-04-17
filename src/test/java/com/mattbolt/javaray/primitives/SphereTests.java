package com.mattbolt.javaray.primitives;

import com.mattbolt.javaray.geom.Ray;
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.render.Material;
import com.mattbolt.javaray.util.JavaRayConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:JavaRayApplicationContext.xml"})
public class SphereTests {
    private static final Logger logger = LoggerFactory.getLogger(SphereTests.class);

    @Autowired
    JavaRayConfiguration configuration;

    @Test
    public void sphereHitTest() {
        Material mat = new Material(new Color(5, 5, 5), new Color(8, 8, 8), new Color(0, 0, 0));
        Sphere sphere = new Sphere(new Vector3(4, 4, -5), mat, 2);

        Vector3 eyePt = new Vector3(4, 4, 5);
        Vector3 ptBehind = new Vector3(4, 4, -7);

        Vector3 dir = Vector3.subtract(ptBehind, eyePt);
        dir.normalize();
        logger.debug("Direction: {}", dir);

        System.out.println(sphere.getGeometry().hits(new Ray(eyePt, dir)).hitPoint);
        //Sphere sphere = new Sphere();
    }
}
