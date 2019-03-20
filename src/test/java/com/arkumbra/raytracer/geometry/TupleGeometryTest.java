package com.arkumbra.raytracer.geometry;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TupleGeometryTest {

  @Test
  public void testPointHasWOf1() {
    Tuple p = new Point(1.0f,4.2f, -9.3f);

    assertEquals(1, p.w(), 0.0001);
  }

  @Test
  public void testVectorHasWOf0() {
    Tuple p = new Vector(1.0f,4.2f, -9.3f);

    assertEquals(0, p.w(), 0.0001);
  }

  @Test
  public void testEqualityOfTwoTuples() {
    Tuple p1 = TupleFactory.create(1.0f,4.2f, -9.3f, 1.0f);
    Tuple p2 = TupleFactory.create(1.0f,4.2f, -9.3f, 1.0f);

    assertEquals(p1, p2);
  }

  @Test
  public void testAddingPointToVector() {
    Tuple p1 = new Point(1.0f,2.0f, 4.0f);
    Tuple v1 = new Vector(2.0f,4.0f, 8.0f);

    Tuple expected = new Point(3.0f, 6.0f, 12.0f);

    Tuple result = p1.add(v1);

    assertTrue(result instanceof Point);
    assertEquals(expected, result);
  }

  @Test
  public void testAddingVectorToVector() {
    Tuple v1 = new Vector(1.0f,2.0f, 4.0f);
    Tuple v2 = new Vector(2.0f,4.0f, 8.0f);

    Tuple expected = new Vector(3.0f, 6.0f, 12.0f);

    Tuple result = v1.add(v2);

    assertTrue(result instanceof Vector);
    assertEquals(expected, result);
  }

//  @Test(expected = RuntimeException.class)
//  public void testAddingPointToPoint() {
//    Tuple p1 = new Point(1.0f,2.0f, 4.0f);
//    Tuple p2 = new Point(2.0f,4.0f, 8.0f);
//
//    p1.add(p2);
//  }

  @Test
  public void testSubtractingPointFromPoint() {
    Tuple p1 = new Point(3.0f,2.0f, 1.0f);
    Tuple p2 = new Point(5.0f,6.0f, 7.0f);

    Tuple expected = new Vector(-2f, -4f, -6f);

    Tuple result = p1.minus(p2);

    assertTrue(result instanceof Vector);
    assertEquals(expected, result);
  }

  @Test
  public void testSubtractingVectorFromPoint() {
    Tuple p1 = new Point(-2.0f,-4.0f, -8.0f);
    Tuple v1 = new Vector(1.0f,2.0f, 4.0f);

    Tuple expected = new Point(-3.0f, -6.0f, -12.0f);

    Tuple result = p1.minus(v1);

    assertTrue(result instanceof Point);
    assertEquals(expected, result);
  }

  @Test
  public void testSubtractingVectorFromVector() {
    Tuple v1 = new Vector(3.0f,2.0f, 1.0f);
    Tuple v2 = new Vector(5.0f,6.0f, 7.0f);

    Tuple expected = new Vector(-2f, -4f, -6f);

    Tuple result = v1.minus(v2);

    assertTrue(result instanceof Vector);
    assertEquals(expected, result);
  }

//  @Test(expected = RuntimeException.class)
//  public void testSubtractingPointFromVector() {
//    Tuple v1 = new Vector(1.0f,2.0f, 4.0f);
//    Tuple p1 = new Point(-2.0f,-4.0f, -8.0f);
//
//    v1.minus(p1);
//  }

  @Test
  public void testNegatePoint() {
    Tuple p1 = new Point(3.0f,2.0f, 1.0f, 6.0f);

    Tuple expected = new Point(-3f, -2f, -1f, -6f);

    Tuple result = p1.negate();

    assertTrue(result instanceof Point);
    assertEquals(expected, result);
  }

  @Test
  public void testMultiplyVector() {
    Tuple v1 = new Vector(1f,-2f, 3f, -4f);
    float scalar = 3.5f;

    Tuple expected = new Vector(3.5f, -7f, 10.5f, -14f);

    Tuple result = v1.multiple(scalar);

    assertTrue(result instanceof Vector);
    assertEquals(expected, result);
  }

  @Test
  public void testDivideVector() {
    Tuple v1 = new Vector(1f,-2f, 3f, -4f);
    float scalar = 3.5f;

    Tuple expected = new Vector(3.5f, -7f, 10.5f, -14f);

    Tuple result = v1.multiple(scalar);

    assertTrue(result instanceof Vector);
    assertEquals(expected, result);
  }

  @Test
  public void testMagnitude() {
    Tuple t = new Vector(-1, -2, -3);
    double expected = Math.sqrt(14);

    double mag = t.magnitude();

    assertEquals(expected, mag, 0.0001d);
  }

  @Test
  public void testNormalize() {
    Tuple t = new Vector(4, 0, 0);
    Tuple expected = new Vector(1, 0, 0);

    Tuple normalized = t.normalize();

    assertEquals(expected, normalized);
  }

  @Test
  public void testNormalizeWithNonIntegerResults() {
    Tuple t = new Vector(1, 2, 3);
    Tuple expected = new Vector((float)(1/Math.sqrt(14)),
        (float)(2/Math.sqrt(14)),
        (float)(3/Math.sqrt(14)));

    Tuple normalized = t.normalize();

    assertEquals(expected, normalized);
  }

  @Test
  public void testDotProduct() {
    Tuple v1 = new Vector(1, 2, 3);
    Tuple v2 = new Vector(2, 3, 4);

    float expected = 20f;
    float answer = v1.dotProd(v2);

    assertEquals(expected, answer, 0.00001d);
  }

  /*Scenario: The cross product of two vectors Given a ← vector(1, 2, 3)
And b ← vector(2, 3, 4)
Then cross(a, b) = vector(-1, 2, -1)
And cross(b, a) = vector(1, -2, 1)*/

  @Test
  public void testCrossProductOfTwoVectors() {
    Tuple a = new Vector(1, 2, 3);
    Tuple b = new Vector(2, 3, 4);

    Tuple aCrossB = new Vector(-1, 2, -1);
    Tuple bCrossA = new Vector(1, -2, 1);

    assertEquals(aCrossB, a.cross(b));
    assertEquals(bCrossA, b.cross(a));
  }


}
