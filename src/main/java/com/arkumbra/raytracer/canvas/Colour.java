package com.arkumbra.raytracer.canvas;

import com.arkumbra.raytracer.geometry.Tuple;

public class Colour extends Tuple {

  private static final float COLOUR_W = 0;

  protected Colour(float x, float y, float z) {
    super(x, y, z, COLOUR_W);
  }
}
