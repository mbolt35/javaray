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

package com.mattbolt.javaray.geom;

/**
* This class is used to represent the {@link Geometry} for a {@link com.mattbolt.javaray.primitives.Plane}.
 *
 * @author Matt Bolt, mbolt35@gmail.com
*/
public class PlaneGeometry implements Geometry {
    private final Vector3 position;
    private final Vector3 planeNormal;

    public PlaneGeometry(Vector3 position, Vector3 planeNormal) {
        this.position = position;
        this.planeNormal = planeNormal;
    }

    @Override
    public HitResult hits(Ray ray) {
        Vector3 rayPosition = ray.getPosition();
        Vector3 direction = ray.getDirection();
        direction.normalize();

        double a = Vector3.dotProduct(planeNormal, position);
        double b = a - Vector3.dotProduct(planeNormal, rayPosition);
        double c = Vector3.dotProduct(planeNormal, direction);

        if (c == 0) {
            return new HitResult(-1, null);
        }

        double t = b / c;

        if (t > rayPosition.getZ()) {
            Vector3 hitLocation = hitPoint(rayPosition, direction, t);
            if (hitLocation.getZ() > 0) {
                return HitResult.MISS;
            }

            return new HitResult(t, hitLocation);
        }

        return HitResult.MISS;
    }

    private Vector3 hitPoint(Vector3 base, Vector3 direction, double t) {
        Vector3 scaledDirection = new Vector3(direction);
        scaledDirection.scale(t);

        return Vector3.add(base, scaledDirection);
    }

    @Override
    public Vector3 normalTo(Vector3 point) {
        return planeNormal;
    }

    @Override
    public boolean isCollidable(Ray ray) {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof PlaneGeometry)) {
            return false;
        }

        PlaneGeometry that = (PlaneGeometry) o;

        return !(planeNormal != null ? !planeNormal.equals(that.planeNormal) : that.planeNormal != null)
            && !(position != null ? !position.equals(that.position) : that.position != null);

    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (planeNormal != null ? planeNormal.hashCode() : 0);
        return result;
    }
}
