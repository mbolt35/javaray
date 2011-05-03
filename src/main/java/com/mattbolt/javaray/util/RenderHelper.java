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

package com.mattbolt.javaray.util;

/**
 * This class contains useful image and render calculation helper methods.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public final class RenderHelper {

    private RenderHelper() {
        // Prevent Instantiation
    }

    /**
     * This method finds the greatest common divisor: The largest integer or the polynomial (monomial) of highest degree
     * that is an exact divisor or each of two or more integers or polynomials.
     *
     * @param a The first multiple.
     *
     * @param b The second multiple.
     *
     * @return
     */
    public static int gcd(int a, int b) {
        int t;

        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }

        return a;
    }

}
