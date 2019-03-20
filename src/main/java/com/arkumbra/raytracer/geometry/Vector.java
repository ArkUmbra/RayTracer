package com.arkumbra.raytracer.geometry;

public class Vector extends Tuple {

  private static final double VECTOR_W = 0;

  public Vector(double x, double y, double z) {
    super(x, y, z, VECTOR_W);
  }

  public Vector(double x, double y, double z, double w) {
    super(x, y, z, w);
  }

}
