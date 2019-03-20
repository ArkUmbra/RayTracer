package com.arkumbra.raytracer.canvas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ColourTest {

  /*
  Scenario: Adding colors
Given c1 ← color(0.9, 0.6, 0.75)
And c2 ← color(0.7, 0.1, 0.25) Then c1 + c2 = color(1.6, 0.7, 1.0)

Scenario: Subtracting colors
Given c1 ← color(0.9, 0.6, 0.75) And c2 ← color(0.7, 0.1, 0.25)
Then c1 - c2 = color(0.2, 0.5, 0.5)

Scenario: Multiplying a color by a scalar Given c ← color(0.2, 0.3, 0.4)
Then c * 2 = color(0.4, 0.6, 0.8)
   */

  @Test
  public void testAddColours() {
    Colour c1 = new Colour(0.9f, 0.6f, 0.75f);
    Colour c2 = new Colour(0.7f, 0.1f, 0.25f);

    Colour expected = new Colour(1.6f, 0.7f, 1.0f);
    assertEquals(expected, c1.add(c2));
  }

  @Test
  public void testSubtractColours() {
    Colour c1 = new Colour(0.9f, 0.6f, 0.75f);
    Colour c2 = new Colour(0.7f, 0.1f, 0.25f);

    Colour expected = new Colour(0.2f, 0.5f, 0.5f);
    assertEquals(expected, c1.minus(c2));
  }

  @Test
  public void testMultiplyColourByScalar() {
    Colour c1 = new Colour(0.2f, 0.3f, 0.4f);
    float scalar = 2;

    Colour expected = new Colour(0.4f, 0.6f, 0.8f);
    assertEquals(expected, c1.multiple(scalar));
  }


}
