package com.mattbolt.javaray.geom;

/**
 * This class represents a point in 3D space.
 *
 * @author Matt Bolt, mbolt35@gmail.com
 */
public class Vector3 {
    private double x;
    private double y;
    private double z;

    public Vector3() {
        this(0.0, 0.0, 0.0);
    }

    public Vector3(Vector3 vector) {
        this(vector.x, vector.y, vector.z);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vector3 vector) {
        add(vector.x, vector.y, vector.z);
    }

    public void add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void subtract(Vector3 vector) {
        subtract(vector.x, vector.y, vector.z);
    }

    public void subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }

    public void setToOrigin() {
        this.x = this.y = this.z = 0.0;
    }

    /**
     * This method scales the vector by -1.
      */
    public void negate() {
        scale(-1);
    }

    /**
     * This method normalizes the vector such that the Length = 1.
     */
    public void normalize() {
        scale(1.0 / getLength());
    }

    /**
     * This method uses a scalar values to adjust the x, y, and z coordinates.
     * @param scalar
     */
    public void scale(double scalar) {
        x *= scalar;
        y *= scalar;
        z *= scalar;
    }

    /**
     * This method copies the values from the {@code source} parameter into this instance.
     * @param source
     */
    public void copyFrom(Vector3 source) {
        x = source.x;
        y = source.y;
        z = source.z;
    }

    /**
     * This method creates a new {@code Vector3} instance containing identical data.
     *
     * @return A new {@code Vector3} instance containing the same x, y, and z values.
     */
    public Vector3 copy() {
        return new Vector3(x, y, z);
    }

    /**
     * This property contains the length of the vector.
     * @return
     */
    public double getLength() {
        return Math.sqrt(getLengthSquared());
    }

    /**
     * This property contains the length of the vector before the square root is taken.
     * @return
     */
    public double getLengthSquared() {
        return Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return new StringBuilder("[x: ")
            .append(x)
            .append(", y: ")
            .append(y)
            .append(", z: ")
            .append(z)
            .append("]")
            .toString();
    }

    /**
     * This method simply creates a new cloned instance of {@code Vector3} scaled by the {@code scalar} value.
     *
     * @param scalar The value to scale by.
     *
     * @param v The {@code Vector3} to clone and scale.
     *
     * @return
     */
    public static Vector3 multiply(double scalar, Vector3 v) {
        Vector3 result = v.copy();
        result.scale(scalar);

        return result;
    }

    /**
     * This method calculates and returns the dot product of two {@code Vector3} instances.
     *
     * @param v1 The first {@code Vector3} instance to use in the calculation.
     *
     * @param v2 The second {@code Vector3} instance to use in the calculation.
     *
     * @return The dot product between the two {@code Vector3} instances
     */
    public static double dotProduct(Vector3 v1, Vector3 v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    /**
     * This method calculates and returns the cross product of two {@code Vector3} instances.
     *
     * @param v1 The first {@code Vector3} instance to use in the calculation.
     *
     * @param v2 The second {@code Vector3} instance to use in the calculation.
     *
     * @return The cross product between the two {@code Vector3} instances
     */
    public static Vector3 crossProduct(Vector3 v1, Vector3 v2) {
        return new Vector3(
            v1.y * v2.z - v1.z * v2.y,
            v1.z * v2.x - v1.x * v2.z,
            v1.x * v2.y - v1.y * v2.x);
    }

    /**
     * This method adds the two {@code Vector3} instances and returns a new instance containing the result.
     *
     * @param v1 The first {@code Vector3} in the sum.
     *
     * @param v2 The second {@code Vector3} in the sum.
     *
     * @return A new {@code Vector3} containing the sum of the two parameters.
     */
    public static Vector3 add(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    /**
     * This method subtracts the two {@code Vector3} instances and returns a new instance containing the result.
     *
     * @param v1 The first {@code Vector3} in the sum.
     *
     * @param v2 The second {@code Vector3} in the sum.
     *
     * @return A new {@code Vector3} containing the sum of the two parameters.
     */
    public static Vector3 subtract(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static Vector3 origin() {
        return new Vector3(0.0, 0.0, 0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector3)) return false;

        Vector3 vector3 = (Vector3) o;

        if (Double.compare(vector3.x, x) != 0) return false;
        if (Double.compare(vector3.y, y) != 0) return false;
        if (Double.compare(vector3.z, z) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = z != +0.0d ? Double.doubleToLongBits(z) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
