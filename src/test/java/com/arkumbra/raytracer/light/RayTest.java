package com.arkumbra.raytracer.light;

import static org.junit.Assert.assertEquals;

import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Vector;
import com.arkumbra.raytracer.matrices.Matrix;
import com.arkumbra.raytracer.matrices.MatrixFactory;
import com.arkumbra.raytracer.shapes.Intersection;
import com.arkumbra.raytracer.shapes.Intersections;
import com.arkumbra.raytracer.shapes.Sphere;
import org.junit.Ignore;
import org.junit.Test;

public class RayTest {

  @Test
  public void testCanGetOriginAndDirectionFromRay() {
    Point origin = new Point(1,2,3);
    Vector direction = new Vector(4,5,6);
    Ray ray = new Ray(origin, direction);

    assertEquals(direction, ray.getDirection());
    assertEquals(origin, ray.getOrigin());
  }

  /*
  Scenario: Computing a point from a distance
    Given r ← ray(point(2, 3, 4), vector(1, 0, 0))

    Then position(r, 0) = point(2, 3, 4)
    And position(r, 1) = point(3, 3, 4)
    And position(r, -1) = point(1, 3, 4)
    And position(r, 2.5) = point(4.5, 3, 4)
   */
  @Test
  public void testComputingPointFromADistance() {
    Point origin = new Point(2,3,4);
    Vector direction = new Vector(1,0,0);
    Ray ray = new Ray(origin, direction);

    assertEquals(new Point(2,3,4), ray.calcPosition(0));
    assertEquals(new Point(3,3,4), ray.calcPosition(1));
    assertEquals(new Point(1,3,4), ray.calcPosition(-1));
    assertEquals(new Point(4.5,3,4), ray.calcPosition(2.5));
  }

  /*
  features/spheres.feature
    Scenario: A ray intersects a sphere at two points
    Given r ← ray(point(0, 0, -5), vector(0, 0, 1))
    And s ← sphere()
    When xs ← intersect(s, r)
    Then xs.count = 2
    And xs[0] = 4.0
    And xs[1] = 6.0
   */
  @Test
  public void testRayIntersectsSphereAtTwoPoints() {
    Ray ray = new Ray(new Point(0,0,-5), new Vector(0,0,1));
    Sphere sphere = new Sphere();

    Intersections intersections = sphere.intersect(ray);
    assertEquals(2, intersections.length());
    assertEquals(4.0, intersections.get(0).getT(), 0.0001d);
    assertEquals(6.0, intersections.get(1).getT(), 0.0001d);
  }

  /*
  Scenario: A ray intersects a sphere at a tangent
    Given r ← ray(point(0, 1, -5), vector(0, 0, 1))
    And s ← sphere()
    When xs ← intersect(s, r)
    Then xs.count = 2
    And xs[0] = 5.0
    And xs[1] = 5.0
   */
  @Test
  public void testRayIntersectsWithSphereAtATangentAndReturnsTwoIdenticalValues() {
    Ray ray = new Ray(new Point(0,1,-5), new Vector(0,0,1));
    Sphere sphere = new Sphere();

    Intersections intersections = sphere.intersect(ray);
    assertEquals(2, intersections.length());
    assertEquals(5.0, intersections.get(0).getT(), 0.0001d);
    assertEquals(5.0, intersections.get(1).getT(), 0.0001d);
  }

  /*
  Scenario: A ray misses a sphere
    Given r ← ray(point(0, 2, -5), vector(0, 0, 1))
    And s ← sphere()
    When xs ← intersect(s, r)
    Then xs.count = 0
   */
  @Test
  public void testRayMissesASphereReturnsNoIntersections() {
    Ray ray = new Ray(new Point(0,2,-5), new Vector(0,0,1));
    Sphere sphere = new Sphere();

    Intersections intersections = sphere.intersect(ray);
    assertEquals(0, intersections.length());
  }

  /*
  Scenario: A ray originates inside a sphere
    Given r ← ray(point(0, 0, 0), vector(0, 0, 1))
    And s ← sphere()
    When xs ← intersect(s, r)
    Then xs.count = 2
    And xs[0] = -1.0
    And xs[1] = 1.0
   */
  @Test
  public void testWhenRayOriginatesInsideSphere_thenStillReturnTwoIntersections() {
    Ray ray = new Ray(new Point(0,0,0), new Vector(0,0,1));
    Sphere sphere = new Sphere();

    Intersections intersections = sphere.intersect(ray);
    assertEquals(2, intersections.length());
    assertEquals(-1.0, intersections.get(0).getT(), 0.0001d);
    assertEquals(1.0, intersections.get(1).getT(), 0.0001d);
  }

  /*
   * Scenario: A sphere is behind a ray
   Given r ← ray(point(0, 0, 5), vector(0, 0, 1))
   And s ← sphere()
   When xs ← intersect(s, r)
   Then xs.count = 2
   And xs[0] = -6.0
   And xs[1] = -4.0
   */
  @Test
  public void testWhenRayOriginatesAheadOfSphere_thenStillReturnTwoIntersections() {
    Ray ray = new Ray(new Point(0,0,5), new Vector(0,0,1));
    Sphere sphere = new Sphere();

    Intersections intersections = sphere.intersect(ray);
    assertEquals(2, intersections.length());
    assertEquals(-6.0, intersections.get(0).getT(), 0.0001d);
    assertEquals(-4.0, intersections.get(1).getT(), 0.0001d);
  }


  /**
   * Scenario: Intersect sets the object on the intersection
   * Given r ← ray(point(0, 0, -5), vector(0, 0, 1)
   * And s ← sphere()
   * When xs ← intersect(s, r)
   * Then xs.count = 2
   * And xs[0].object = s
   * And xs[1].object = s
   */
  @Test
  public void testIntersectSetsTheObjectOnTheIntersection() {
    Ray ray = new Ray(new Point(0,0,-5), new Vector(0,0,1));
    Sphere sphere = new Sphere();

    Intersections intersections = sphere.intersect(ray);
    assertEquals(2, intersections.length());
    assertEquals(sphere, intersections.get(0).getObject());
    assertEquals(sphere, intersections.get(1).getObject());
  }

  /**
   *  Scenario: The hit, when all intersections have positive t
   *  Given s ← sphere()
   *  And i1 ← intersection(1, s)
   *  And i2 ← intersection(2, s)
   *  And xs ← intersections(i2, i1)
   *  When i ← hit(xs)
   *  Then i = i1
   */
  @Test
  public void testFindHitWhenAllIntersectionsHavePositiveT() {
    Sphere sphere = new Sphere();

    Intersection i1 = new Intersection(1, sphere);
    Intersection i2 = new Intersection(2, sphere);
    Intersections xs = new Intersections(i2, i1);

    assertEquals(i1, xs.getHit());
  }


   /**
    * Scenario: The hit, when some intersections have negative t
    * Given s ← sphere()
    * And i1 ← intersection(-1, s)
    * And i2 ← intersection(1, s)
    * And xs ← intersections(i2, i1)
    * When i ← hit(xs)
    * Then i = i2
    *
    */
   @Test
   public void testFindHitWhenSomeIntersectionsHaveNegativeT() {
     Sphere sphere = new Sphere();

     Intersection i1 = new Intersection(-1, sphere);
     Intersection i2 = new Intersection(1, sphere);
     Intersections xs = new Intersections(i2, i1);

     assertEquals(i2, xs.getHit());
   }

   /**
    * Scenario: The hit, when all intersections have negative t
    * Given s ← sphere()
    * And i1 ← intersection(-2, s)
    * And i2 ← intersection(-1, s)
    * And xs ← intersections(i2, i1)
    * When i ← hit(xs)
    * Then i is nothing
   */
   @Test
   public void testFindHitWhenAllIntersectionsHaveNegativeT() {
     Sphere sphere = new Sphere();

     Intersection i1 = new Intersection(-2, sphere);
     Intersection i2 = new Intersection(-1, sphere);
     Intersections xs = new Intersections(i2, i1);

     assertEquals(null, xs.getHit());
   }

   /**
    * Scenario: The hit is always the lowest nonnegative intersection
    * Given s ← sphere()
    * And i1 ← intersection(5, s)
    * And i2 ← intersection(7, s)
    * And i3 ← intersection(-3, s)
    * And i4 ← intersection(2, s)
    * And xs ← intersections(i1, i2, i3, i4)
    * When i ← hit(xs) Then i = i4
   */
   @Test
   public void testHitIsAlwaysLowestNonNegativeIntersection() {
     Sphere sphere = new Sphere();

     Intersection i1 = new Intersection(5, sphere);
     Intersection i2 = new Intersection(7, sphere);
     Intersection i3 = new Intersection(-3, sphere);
     Intersection i4 = new Intersection(2, sphere);
     Intersections xs = new Intersections(i1, i2, i3, i4);

     assertEquals(i4, xs.getHit());
   }

   /**
    *Scenario: Translating a ray
    * Given r ← ray(point(1, 2, 3), vector(0, 1, 0))
    * And m ← translation(3, 4, 5)
    *
    * When r2 ← transform(r, m)
    * Then r2.origin = point(4, 6, 8)
    * And r2.direction = vector(0, 1, 0)
    */
   @Test
   public void testRayCanBeTranslated() {
     Ray r = new Ray(new Point(1, 2,3), new Vector(0,1,0));
     Matrix translation = MatrixFactory.translation(3,4,5);

     Ray r2 = r.transform(translation);

     assertEquals(new Point(4,6,8), r2.getOrigin());
     assertEquals(r.getDirection(), r2.getDirection());
   }

    /**
     * Scenario: Scaling a ray
     * Given r ← ray(point(1, 2, 3), vector(0, 1, 0))
     * And m ← scaling(2, 3, 4)
     * When r2 ← transform(r, m)
     * Then r2.origin = point(2, 6, 12)
     * And r2.direction = vector(0, 3, 0)
    */
    @Test
    public void testRayCanBeScaled() {
      Ray r = new Ray(new Point(1, 2,3), new Vector(0,1,0));
      Matrix translation = MatrixFactory.scaling(2,3,4);

      Ray r2 = r.transform(translation);

      assertEquals(new Point(2,6,12), r2.getOrigin());
      assertEquals(new Vector(0,3,0), r2.getDirection());
    }

    /**
     * Scenario: Intersecting a scaled sphere with a ray
     * Given r ← ray(point(0, 0, -5), vector(0, 0, 1))
     * And s ← sphere()
     * When set_transform(s, scaling(2, 2, 2))
     * And xs ← intersect(s, r)
     * Then xs.count = 2
     * And xs[0].t = 3
     * And xs[1].t = 7
     */
    @Test
    public void testIntersectingScaledSphereWithRay() {
      Ray r = new Ray(new Point(0, 0,-5), new Vector(0,0,1));
      Sphere s = new Sphere();

      Matrix scaling = MatrixFactory.scaling(2,2,2);
      s.setTransform(scaling);

      Intersections xs = s.intersect(r);

      assertEquals(2, xs.length());
      assertEquals(3, xs.get(0).getT(), 0.0001d);
      assertEquals(7, xs.get(1).getT(), 0.0001d);
    }

  @Test
  public void testIntersectingSphereWithFastRay() {
    Ray r = new Ray(new Point(0, 0,-5), new Vector(0,0,2));
    Sphere s = new Sphere();

    Intersections xs = s.intersect(r);

    assertEquals(2, xs.length());
    assertEquals(2, xs.get(0).getT(), 0.0001d);
    assertEquals(3, xs.get(1).getT(), 0.0001d);
  }

    @Test
    public void testIntersectingSphereWithRayWhenRayDirectionHasValuesLessThanOne() {
      Ray r = new Ray(new Point(-4, 0,0), new Vector(0.5,0,0));
      Sphere s = new Sphere();

      Intersections xs = s.intersect(r);

      assertEquals(2, xs.length());
      assertEquals(6, xs.get(0).getT(), 0.0001d);
      assertEquals(10, xs.get(1).getT(), 0.0001d);
    }

    /**
     * Scenario: Intersecting a translated sphere with a ray
     * Given r ← ray(point(0, 0, -5), vector(0, 0, 1))
     * And s ← sphere()
     * When set_transform(s, translation(5, 0, 0))
     * And xs ← intersect(s, r)
     * Then xs.count = 0
     */
    @Test
    public void testIntersectingTranslatedSphereWithRay() {
      Ray r = new Ray(new Point(0, 0,-5), new Vector(0,0,1));
      Sphere s = new Sphere();

      Matrix scaling = MatrixFactory.translation(5,0,0);
      s.setTransform(scaling);

      Intersections xs = s.intersect(r);

      assertEquals(0, xs.length());
    }

}
