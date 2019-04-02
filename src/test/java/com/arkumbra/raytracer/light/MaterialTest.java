package com.arkumbra.raytracer.light;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.geometry.Calc;
import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Vector;
import org.junit.Before;
import org.junit.Test;

public class MaterialTest {

  private Material m;
  private Point p;

  /**  Background:
   * Given m ← material()
   * And position ← point(0, 0, 0)
   */
  @Before
  public void setUp() {
    this.m = new Material();
    this.p = new Point(0,0,0);
  }

  /**
   * Scenario: The default material
   * Given m ← material()
   * Then m.color = color(1, 1, 1)
   * And m.ambient = 0.1
   * And m.diffuse = 0.9
   * And m.specular = 0.9
   * And m.shininess = 200.0
   */

  @Test
  public void testMaterialHasColourAndLightPropertiesSetByDefault() {
    assertEquals(new Colour(1,1,1), m.getColour());
    assertTrue(Calc.approxEqual(0.1, m.getAmbient()));
    assertTrue(Calc.approxEqual(0.9, m.getDiffuse()));
    assertTrue(Calc.approxEqual(0.9, m.getSpecular()));
    assertTrue(Calc.approxEqual(200.0, m.getShininess()));
  }

  /**
   * Scenario: Lighting with the eye between the light and the surface
   * Given eyev ← vector(0, 0, -1)
   * And normalv ← vector(0, 0, -1)
   * And light ← point_light(point(0, 0, -10), color(1, 1, 1))
   * When result ← lighting(m, light, position, eyev, normalv)
   * Then result = color(1.9, 1.9, 1.9)
  */
  @Test
  public void testLightingWithTheEyeBetweenLightAndSurface() {
    Vector eyeV = new Vector(0,0,-1);
    Vector normalV = new Vector(0,0,-1);

    PointLight light = new PointLight(new Point(0,0,-10), Colour.WHITE);

    Colour result = m.lighting(light, p, eyeV, normalV);
    assertEquals(new Colour(1.9, 1.9, 1.9), result);
  }

  /**
   * Scenario: Lighting with the eye between light and surface, eye offset 45°
   * Given eyev ← vector(0, √2/2, -√2/2)
   * And normalv ← vector(0, 0, -1)
   * And light ← point_light(point(0, 0, -10), color(1, 1, 1))
   * When result ← lighting(m, light, position, eyev, normalv)
   * Then result = color(1.0, 1.0, 1.0)
   */
  @Test
  public void testLightingWithTheEyeBetweenLightAndSurfaceEyeOffset45() {
    double val = Math.sqrt(2)/2;
    Vector eyeV = new Vector(0,val,-val);
    Vector normalV = new Vector(0,0,-1);

    PointLight light = new PointLight(new Point(0,0,-10), Colour.WHITE);

    Colour result = m.lighting(light, p, eyeV, normalV);
    assertEquals(new Colour(1, 1, 1), result);
  }

  /**
   * Scenario: Lighting with eye opposite surface, light offset 45°
   * Given eyev ← vector(0, 0, -1)
   * And normalv ← vector(0, 0, -1)
   * And light ← point_light(point(0, 10, -10), color(1, 1, 1))
   * When result ← lighting(m, light, position, eyev, normalv)
   * Then result = color(0.7364, 0.7364, 0.7364)
   */
  @Test
  public void testLightingEyeOppositeSurfaceLightOffset45() {
    Vector eyeV = new Vector(0,0,-1);
    Vector normalV = new Vector(0,0,-1);

    PointLight light = new PointLight(new Point(0,10,-10), Colour.WHITE);

    Colour result = m.lighting(light, p, eyeV, normalV);
    assertEquals(new Colour(0.7364, 0.7364, 0.7364), result);
  }

  /**
   * Scenario: Lighting with eye in the path of the reflection vector
   * Given eyev ← vector(0, -√2/2, -√2/2)
   * And normalv ← vector(0, 0, -1)
   * And light ← point_light(point(0, 10, -10), color(1, 1, 1))
   * When result ← lighting(m, light, position, eyev, normalv)
   * Then result = color(1.6364, 1.6364, 1.6364)
   */
  @Test
  public void testLightingWithEyeInReflectionPathThenFullSpecular() {
    double val = Math.sqrt(2)/2;
    Vector eyeV = new Vector(0,-val,-val);
    Vector normalV = new Vector(0,0,-1);

    PointLight light = new PointLight(new Point(0,10,-10), Colour.WHITE);

    Colour result = m.lighting(light, p, eyeV, normalV);
    assertEquals(new Colour(1.6364, 1.6364, 1.6364), result);
  }

  /**
   * Scenario: Lighting with the light behind the surface
   * Given eyev ← vector(0, 0, -1)
   * And normalv ← vector(0, 0, -1)
   * And light ← point_light(point(0, 0, 10), color(1, 1, 1))
   * When result ← lighting(m, light, position, eyev, normalv)
   * Then result = color(0.1, 0.1, 0.1)
   */
  @Test
  public void testLightingWithLightBehindSurface() {
    Vector eyeV = new Vector(0,0,-1);
    Vector normalV = new Vector(0,0,-1);

    PointLight light = new PointLight(new Point(0,0,10), Colour.WHITE);

    Colour result = m.lighting(light, p, eyeV, normalV);
    assertEquals(new Colour(0.1, 0.1, 0.1), result);
  }

}
