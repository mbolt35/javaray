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
 * This class represents a
 */
public class Matrix3D {

    private static final Matrix3D IDENTITY = new Matrix3D();

    private double m11, m12, m13, tx;
    private double m21, m22, m23, ty;
    private double m31, m32, m33, tz;
    private double m41, m42, m43, tw;

    public Matrix3D() {
        init(1, 0, 0, 0,
             0, 1, 0, 0,
             0, 0, 1, 0,
             0, 0, 0, 1);
    }

    public Matrix3D( double m11, double m12, double m13, double tx,
                     double m21, double m22, double m23, double ty,
                     double m31, double m32, double m33, double tz,
                     double m41, double m42, double m43, double tw )
    {
        init(m11, m12, m13, tx,
             m21, m22, m23, ty,
             m31, m32, m33, tz,
             m41, m42, m43, tw);

    }

    private void init( double m11, double m12, double m13, double tx,
                       double m21, double m22, double m23, double ty,
                       double m31, double m32, double m33, double tz,
                       double m41, double m42, double m43, double tw )
    {
        this.m11 = m11; this.m12 = m12; this.m13 = m13; this.tx = tx;
        this.m21 = m21; this.m22 = m22; this.m23 = m23; this.ty = ty;
        this.m31 = m31; this.m32 = m32; this.m33 = m33; this.tz = tz;
        this.m41 = m41; this.m42 = m42; this.m43 = m43; this.tw = tw;
    }

    public void copy(Matrix3D m) {
        m11 = m.m11; m12 = m.m12; m13 = m.m13; tx = m.tx;
        m21 = m.m21; m22 = m.m22; m23 = m.m23; ty = m.ty;
        m31 = m.m31; m32 = m.m32; m33 = m.m33; tz = m.tz;
        m41 = m.m41; m42 = m.m42; m43 = m.m43; tw = m.tw;
    }

    public Vector3 transform(Vector3 v) {
        return new Vector3(
            v.getX() * this.m11 + v.getY() * this.m21 + v.getZ() * this.m31,
            v.getX() * this.m12 + v.getY() * this.m22 + v.getZ() * this.m32,
            v.getX() * this.m13 + v.getY() * this.m23 + v.getZ() * this.m33);
    }

    public static Matrix3D multiply(Matrix3D m1, Matrix3D m2) {
        return new Matrix3D(
            m1.m11 * m2.m11 + m1.m12 * m2.m21 + m1.m13 * m2.m31 + m1.tx * m2.m41,
            m1.m11 * m2.m12 + m1.m12 * m2.m22 + m1.m13 * m2.m32 + m1.tx * m2.m42,
            m1.m11 * m2.m13 + m1.m12 * m2.m23 + m1.m13 * m2.m33 + m1.tx * m2.m43,
            m1.m11 * m2.tx + m1.m12 * m2.ty + m1.m13 * m2.tz + m1.tx * m2.tw,
            m1.m21 * m2.m11 + m1.m22 * m2.m21 + m1.m23 * m2.m31 + m1.ty * m2.m41,
            m1.m21 * m2.m12 + m1.m22 * m2.m22 + m1.m23 * m2.m32 + m1.ty * m2.m42,
            m1.m21 * m2.m13 + m1.m22 * m2.m23 + m1.m23 * m2.m33 + m1.ty * m2.m43,
            m1.m21 * m2.tx + m1.m22 * m2.ty + m1.m23 * m2.tz + m1.ty * m2.tw,
            m1.m31 * m2.m11 + m1.m32 * m2.m21 + m1.m33 * m2.m31 + m1.tz * m2.m41,
            m1.m31 * m2.m12 + m1.m32 * m2.m22 + m1.m33 * m2.m32 + m1.tz * m2.m42,
            m1.m31 * m2.m13 + m1.m32 * m2.m23 + m1.m33 * m2.m33 + m1.tz * m2.m43,
            m1.m31 * m2.tx + m1.m32 * m2.ty + m1.m33 * m2.tz + m1.tz * m2.tw,
            m1.m41 * m2.m11 + m1.m42 * m2.m21 + m1.m43 * m2.m31 + m1.tw * m2.m41,
            m1.m41 * m2.m12 + m1.m42 * m2.m22 + m1.m43 * m2.m32 + m1.tw * m2.m42,
            m1.m41 * m2.m13 + m1.m42 * m2.m23 + m1.m43 * m2.m33 + m1.tw * m2.m43,
            m1.m41 * m2.tx + m1.m42 * m2.ty + m1.m43 * m2.tz + m1.tw * m2.tw);
    }
}
