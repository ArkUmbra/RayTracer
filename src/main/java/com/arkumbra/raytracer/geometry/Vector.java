package com.arkumbra.raytracer.geometry;

public class Vector extends Tuple {

  private static final double VECTOR_W = 0;

  public Vector(double x, double y, double z) {
    super(x, y, z, VECTOR_W);
  }

  public Vector(double x, double y, double z, double w) {
    super(x, y, z, w);
  }

  public Vector reflect(Vector normal) {
    // return in - normal * 2 * dot(in, normal)
    return (Vector)this.minus(
        normal.multiply(2).multiply(
          this.dotProd(normal)
    ));
  }

//  @Override
//  public Vector normalize() {
//    return (Vector)super.normalize();
//  }
//
//  @Override
//  public Vector negate() {
//    return (Vector)super.negate();
//  }

}
