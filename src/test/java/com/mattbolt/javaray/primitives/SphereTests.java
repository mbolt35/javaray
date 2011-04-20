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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:JavaRayApplicationContext.xml"})
public class SphereTests {
    private static final Logger logger = LoggerFactory.getLogger(SphereTests.class);

    @Autowired
    JavaRayConfiguration configuration;

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
