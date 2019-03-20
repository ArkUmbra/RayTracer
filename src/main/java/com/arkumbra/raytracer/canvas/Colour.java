package com.arkumbra.raytracer.canvas;

import com.arkumbra.raytracer.geometry.Tuple;

public class Colour extends Tuple {

  private static final double COLOUR_W = 0;

  protected Colour(double x, double y, double z) {
    super(x, y, z, COLOUR_W);
  }
}
