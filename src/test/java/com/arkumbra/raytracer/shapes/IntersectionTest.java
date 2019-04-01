package com.arkumbra.raytracer.shapes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntersectionTest {

  /*
    Scenario: An intersection encapsulates t and object
    Given s ← sphere()
    When i ← intersection(3.5, s)
    Then i.t = 3.5
    And i.object = s
  */
  @Test
  public void testIntersectionContainsTAndObject() {
    Sphere s = new Sphere();
    Intersection i = new Intersection(3.5d, s);

    assertEquals(3.5d, i.getT(), 0.0001d);
    assertEquals(s, i.getObject());
  }

  /**
   * Scenario: Aggregating intersections
   * Given s ← sphere()
   * And i1 ← intersection(1, s)
   * And i2 ← intersection(2, s)
   * When xs ← intersections(i1, i2)
   * Then xs.count = 2
   * And xs[0].t = 1
   * And xs[1].t = 2
   */
  @Test
  public void testAggregatingIntersections() {
    Sphere s = new Sphere();
    Intersection i1 = new Intersection(1, s);
    Intersection i2 = new Intersection(2, s);

    Intersections intersections = new Intersections(i1, i2);
    assertEquals(2, intersections.length());
    assertEquals(1, intersections.get(0).getT(), 0.0001d);
    assertEquals(2, intersections.get(1).getT(), 0.0001d);
  }

}
