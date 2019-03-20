package com.arkumbra.raytracer.canvas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CanvasTest {

  @Test
  public void testCanvasPixelsAreInitialisedToBlack() {
    int width = 1862;
    int height = 1200;

    SceneCanvas c = new SceneCanvas(width, height);
    Colour defaultExpectedColour = new Colour(0,0,0);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Colour colour = c.pixelAt(x, y);
        assertEquals(defaultExpectedColour, colour);
      }
    }
  }

  @Test
  public void testCanvasWritePixel() {
    int width = 1862;
    int height = 1200;

    SceneCanvas c = new SceneCanvas(width, height);
    Colour red = new Colour(1, 0, 0);
    Colour black = new Colour(0,0,0);

    // Colour this pixel red
    c.write(100, 101, red);
    Colour colour = c.pixelAt(100, 101);
    assertEquals(red, colour);

    // Ensure other pixel is still black
    colour = c.pixelAt(100, 100);
    assertEquals(black, colour);
  }



}
