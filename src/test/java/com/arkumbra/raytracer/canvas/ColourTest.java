package com.arkumbra.raytracer.canvas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ColourTest {

  @Test
  public void testAddColours() {
    Colour c1 = new Colour(0.9, 0.6, 0.75);
    Colour c2 = new Colour(0.7, 0.1, 0.25);

    Colour expected = new Colour(1.6, 0.7, 1.0);
    assertEquals(expected, c1.add(c2));
  }

  @Test
  public void testSubtractColours() {
    Colour c1 = new Colour(0.9, 0.6, 0.75);
    Colour c2 = new Colour(0.7, 0.1, 0.25);

    Colour expected = new Colour(0.2, 0.5, 0.5);
    assertEquals(expected, c1.minus(c2));
  }

  @Test
  public void testMultiplyColourByScalar() {
    Colour c1 = new Colour(0.2, 0.3, 0.4);
    float scalar = 2;

    Colour expected = new Colour(0.4, 0.6, 0.8);
    assertEquals(expected, c1.multiply(scalar));
  }

  @Test
  public void testMultiplyColourByColour() {
    Colour c1 = new Colour(1, 0.2, 0.4);
    Colour c2 = new Colour(0.9, 1, 0.1);

    Colour expected = new Colour(0.9, 0.2, 0.04);
    assertEquals(expected, c1.hadamardProduct(c2));
  }


}
