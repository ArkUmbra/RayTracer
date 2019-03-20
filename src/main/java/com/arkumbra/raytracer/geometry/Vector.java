package com.arkumbra.raytracer.geometry;

public class Vector extends Tuple {

  private static final float VECTOR_W = 0;

  public Vector(float x, float y, float z) {
    super(x, y, z, VECTOR_W);
  }

  public Vector(float x, float y, float z, float w) {
    super(x, y, z, w);
  }

}
