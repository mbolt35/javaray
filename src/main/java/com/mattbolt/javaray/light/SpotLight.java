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

package com.mattbolt.javaray.light;

import com.mattbolt.javaray.geom.Geometry;
import com.mattbolt.javaray.geom.Ray;
import com.mattbolt.javaray.geom.SphereGeometry;
import com.mattbolt.javaray.geom.Vector3;
import com.mattbolt.javaray.primitives.AbstractPrimitive;
import com.mattbolt.javaray.primitives.SceneObject;
import com.mattbolt.javaray.render.ColorMagnitude;
import com.mattbolt.javaray.render.Material;
import org.apache.log4j.Logger;


/**
 *
 */
public class SpotLight extends AbstractPrimitive implements SceneObject {

    private static final Logger logger = Logger.getLogger(SpotLight.class);

    private final Geometry geometry;

    public SpotLight(Vector3 position, Vector3 target, double intensity, double radius, double theta) {
        this(position, target, new ColorMagnitude(intensity, intensity, intensity), radius, theta);
    }

    public SpotLight(Vector3 position, Vector3 target, ColorMagnitude emissivity, double radius, double theta) {
        super(position, new Material(emissivity));
        
        geometry = new SpotGeometry(position, target, radius, theta);
    }

    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * spotlight geometry
     */
    private static class SpotGeometry extends SphereGeometry implements Geometry {
        private final Vector3 position;
        private final Vector3 target;
        private final double theta;

        private SpotGeometry(Vector3 position, Vector3 target, double radius, double theta) {
            super(position, radius);

            this.position = new Vector3(position);
            this.target = new Vector3(target);
            this.theta = theta;
        }

        @Override
        public HitResult hits(Ray ray) {
            Vector3 difference = Vector3.subtract(position, ray.getPosition());
            Vector3 direction = Vector3.subtract(position, target);
            difference.normalize();
            direction.normalize();
            
            double angle = Vector3.dotProduct(difference, direction);
            //logger.debug("angle: {}", angle);
            
            if (angle < theta) {
                return new HitResult(-1, null);
            }

            return super.hits(ray);
        }

        @Override
        public Vector3 normalTo(Vector3 point) {
            return super.normalTo(point);
        }
    }
}
