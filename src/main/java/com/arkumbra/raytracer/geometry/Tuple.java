package com.arkumbra.raytracer.geometry;

// TODO double might end up being cleaner in the end... have had a few casts to float so far..

/**
 * Immutable
 */
public class Tuple {
  private float x, y, z;
  private float w;

  protected Tuple(float x, float y, float z, float w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  public float x() {
    return x;
  }

  public float y() {
    return y;
  }

  public float z() {
    return z;
  }

  public float w() {
    return w;
  }

  /**
   *
   * @param other
   * @return new Tuple instance
   */
  public Tuple add(Tuple other) {
    float newX = this.x() + other.x();
    float newY = this.y() + other.y();
    float newZ = this.z() + other.z();
    float newW = this.w() + other.w();

    return TupleFactory.create(newX, newY, newZ, newW);
  }

  /**
   *
   * @param other
   * @return new Tuple instance
   */
  public Tuple minus(Tuple other) {
    float newX = this.x() - other.x();
    float newY = this.y() - other.y();
    float newZ = this.z() - other.z();
    float newW = this.w() - other.w();

    return TupleFactory.create(newX, newY, newZ, newW);
  }

  /**
   *
   * @param scalar
   * @return new Tuple instance
   */
  public Tuple multiple(float scalar) {
    float newX = this.x() * scalar;
    float newY = this.y() * scalar;
    float newZ = this.z() * scalar;
    float newW = this.w() * scalar;

    return TupleFactory.create(getClass(), newX, newY, newZ, newW);
  }

  /**
   *
   * @param scalar
   * @return new Tuple instance
   */
  public Tuple divide(float scalar) {
    float newX = this.x() * scalar;
    float newY = this.y() * scalar;
    float newZ = this.z() * scalar;
    float newW = this.w() * scalar;

    return TupleFactory.create(getClass(), newX, newY, newZ, newW);
  }

  /**
   *
   * @return new Tuple instance
   */
  public Tuple negate() {
    float newX = - this.x();
    float newY = - this.y();
    float newZ = - this.z();
    float newW = - this.w();

    return TupleFactory.create(getClass(), newX, newY, newZ, newW);
  }

  public float magnitude() {
    return (float) Math.sqrt(
        x*x + y*y + z*z + w*w
    );
  }

  public Tuple normalize() {
    float mag = magnitude();

    return TupleFactory.create(
        x / mag,
        y / mag,
        z / mag,
        w / mag);
  }

  public float dotProd(Tuple other) {
    return x * other.x +
        y * other.y +
        z * other.z +
        w * other.w;
  }


  /*
  vector(a.y * b.z - a.z * b.y,
    a.z * b.x - a.x * b.z,
    a.x * b.y - a.y * b.x)

   */
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

}
