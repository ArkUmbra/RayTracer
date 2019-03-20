package com.arkumbra.raytracer.geometry;

public class Point extends Tuple {

  private static final double POINT_W = 1;

  public Point(double x, double y, double z) {
    super(x, y, z, POINT_W);
  }

  public Point(double x, double y, double z, double w) {
    super(x, y, z, w);
  }

}
