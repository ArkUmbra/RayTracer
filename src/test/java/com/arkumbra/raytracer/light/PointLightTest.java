package com.arkumbra.raytracer.light;

import static org.junit.Assert.assertEquals;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.geometry.Point;
import org.junit.Test;

public class PointLightTest {

  @Test
  public void testPointLightContainsPositionAndIntensity() {
    Point position = new Point(0,0,0);
    Colour intensity = new Colour(1,1,1);

    PointLight pl = new PointLight(position, intensity);
    assertEquals(position, pl.getPosition());
  }

}
