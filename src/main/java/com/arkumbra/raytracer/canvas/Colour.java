package com.arkumbra.raytracer.canvas;

import com.arkumbra.raytracer.geometry.Tuple;

public class Colour extends Tuple {

  public static final Colour BLACK = new Colour(0, 0, 0);
  public static final Colour WHITE = new Colour(1, 1, 1);

  private static final String SPACE = " ";
  private static final double COLOUR_W = 0;

  public Colour(double r, double g, double b) {
    super(r, g, b, COLOUR_W);
  }

  public Colour hadamardProduct(Colour c2) {
    double r = r() * c2.r();
    double g = g() * c2.g();
    double b = b() * c2.b();

    return new Colour(r, g, b);
  }

  public double r() {
    return x();
  }

  public double g() {
    return y();
  }

  public double b() {
    return z();
  }

  public double alpha() {
    return w();
  }

}
