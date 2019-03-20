package com.arkumbra.raytracer.geometry;

public class Point extends Tuple {

  private static final float POINT_W = 1;

  public Point(float x, float y, float z) {
    super(x, y, z, POINT_W);
  }

  public Point(float x, float y, float z, float w) {
    super(x, y, z, w);
  }

}
