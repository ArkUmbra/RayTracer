package com.arkumbra.raytracer.geometry;

/**
 * Immutable
 */
public class Tuple {
  private double x;
  private double y;
  private double z;
  private double w;

  public Tuple(double x, double y, double z, double w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  public double x() {
    return x;
  }

  public double y() {
    return y;
  }

  public double z() {
    return z;
  }

  public double w() {
    return w;
  }

  /**
   *
   * @param other
   * @return new Tuple instance
   */
  public Tuple add(Tuple other) {
    double newX = this.x() + other.x();
    double newY = this.y() + other.y();
    double newZ = this.z() + other.z();
    double newW = this.w() + other.w();

    return TupleFactory.create(newX, newY, newZ, newW);
  }

  /**
   *
   * @param other
   * @return new Tuple instance
   */
  public Tuple minus(Tuple other) {
    double newX = this.x() - other.x();
    double newY = this.y() - other.y();
    double newZ = this.z() - other.z();
    double newW = this.w() - other.w();

    return TupleFactory.create(newX, newY, newZ, newW);
  }

  /**
   *
   * @param scalar
   * @return new Tuple instance
   */
  public Tuple multiple(double scalar) {
    double newX = this.x() * scalar;
    double newY = this.y() * scalar;
    double newZ = this.z() * scalar;
    double newW = this.w() * scalar;

    return TupleFactory.create(getClass(), newX, newY, newZ, newW);
  }

  /**
   *
   * @param scalar
   * @return new Tuple instance
   */
  public Tuple divide(double scalar) {
    double newX = this.x() / scalar;
    double newY = this.y() / scalar;
    double newZ = this.z() / scalar;
    double newW = this.w() / scalar;

    return TupleFactory.create(getClass(), newX, newY, newZ, newW);
  }

  /**
   *
   * @return new Tuple instance
   */
  public Tuple negate() {
    double newX = - this.x();
    double newY = - this.y();
    double newZ = - this.z();
    double newW = - this.w();

    return TupleFactory.create(getClass(), newX, newY, newZ, newW);
  }

  public double magnitude() {
    return Math.sqrt(
        x*x + y*y + z*z + w*w
    );
  }

  public Tuple normalize() {
    double mag = magnitude();

    return TupleFactory.create(
        x / mag,
        y / mag,
        z / mag,
        w / mag);
  }

  public double dotProd(Tuple other) {
    return x * other.x +
        y * other.y +
        z * other.z +
        w * other.w;
  }

  public Tuple cross(Tuple other) {
    return TupleFactory.create(
        y * other.z - z * other.y,
        z * other.x - x * other.z,
        x * other.y - y * other.x,
        0
        );
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Tuple) {
      Tuple other = (Tuple) obj;

      if (Calc.approxEqual(this.x ,other.x) &&
          Calc.approxEqual(this.y, other.y) &&
          Calc.approxEqual(this.z ,other.z) &&
          Calc.approxEqual(this.w ,other.w)) {

        return true;
      }
    }

    return false;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("x: ");
    sb.append(x);
    sb.append(", y: ");
    sb.append(y);
    sb.append(", z: ");
    sb.append(z);
    return sb.toString();
  }
}
