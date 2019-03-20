package com.arkumbra.raytracer.geometry;

public class Calc {
  private static final float ACCEPTABLE_FLOAT_DELTA = 0.00001f;

  public static boolean approxEqual(double a, double b) {
    return (Math.abs(a - b) < ACCEPTABLE_FLOAT_DELTA);
  }

}
