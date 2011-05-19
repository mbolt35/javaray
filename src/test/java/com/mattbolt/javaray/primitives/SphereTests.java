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

package com.mattbolt.javaray.primitives;

import com.mattbolt.javaray.geom.Ray;
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.render.ColorMagnitude;
import com.mattbolt.javaray.render.Material;
import com.mattbolt.javaray.util.JavaRayConfiguration;
import com.mattbolt.javaray.util.RenderHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:JavaRayApplicationContext.xml"})
public class SphereTests {
    //private static final Logger logger = LoggerFactory.getLogger(SphereTests.class);

    @Autowired
    JavaRayConfiguration configuration;

    @Test
    public void renderHelperTest() {
        System.out.println("GCD(300, 200) = " + RenderHelper.gcd(300, 200));

        printChunkSize(300, 200);
    }

    private void printChunkSize(int width, int height) {
        int gcd = RenderHelper.gcd(width, height);
        int numXChunks = width /  gcd;
        int numYChunks = height / gcd;

        System.out.println("x-chunks: " + numXChunks + ", y-chunks: " + numYChunks);
    }

    @Test
    public void vecTest() {
        Vector3 v1 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v2 = new Vector3(6.0, 5.0, 4.0);

        Vector3 v1Norm = new Vector3(v1);
        v1Norm.normalize();
        Vector3 v2Norm = new Vector3(v2);
        v2Norm.normalize();

        Vector3 scaledV1 = new Vector3(v1);
        scaledV1.scale(5.0);
        Vector3 scaledV2 = new Vector3(v2);
        scaledV2.scale(5.0);
        /*
        logger.debug("v2 - v1: {}", Vector3.subtract(v2, v1));
        logger.debug("v1 + v2: {}", Vector3.add(v1, v2));
        logger.debug("v1 dot v2: {}", Vector3.dotProduct(v1, v2));
        logger.debug("length(v1): {}", v1.getLength());
        logger.debug("length(v2): {}", v2.getLength());
        logger.debug("5.0 * v1: {}", scaledV1);
        logger.debug("5.0 * v2: {}", scaledV2);
        logger.debug("unit(v1): {}", v1Norm);
        logger.debug("unit(v2): {}", v2Norm);
        logger.debug("reflect(v1, v2): {}", Vector3.reflection(v1Norm, v2Norm));
        */
    }

    @Test
    public void sphereHitTest() {
        Material mat = new Material(new ColorMagnitude(5, 5, 5), new ColorMagnitude(8, 8, 8), new ColorMagnitude(0, 0, 0));
        Sphere sphere = new Sphere(new Vector3(4, 4, -5), mat, 2);

        Vector3 eyePt = new Vector3(4, 4, 5);
        Vector3 ptBehind = new Vector3(4, 4, -7);

        Vector3 dir = Vector3.subtract(ptBehind, eyePt);
        dir.normalize();

        assert( sphere.getGeometry().hits(new Ray(eyePt, dir)).getHitPoint() != null );
    }


}
