package com.arkumbra.raytracer.geometry;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TupleGeometryTest {

  @Test
  public void testPointHasWOf1() {
    Tuple p = new Point(1.0,4.2, -9.3);

    assertEquals(1, p.w(), 0.0001);
  }

  @Test
  public void testVectorHasWOf0() {
    Tuple p = new Vector(1.0,4.2, -9.3);

    assertEquals(0, p.w(), 0.0001);
  }

  @Test
  public void testEqualityOfTwoTuples() {
    Tuple p1 = TupleFactory.create(1.0,4.2, -9.3, 1.0);
    Tuple p2 = TupleFactory.create(1.0,4.2, -9.3, 1.0);

    assertEquals(p1, p2);
  }

  @Test
  public void testAddingPointToVector() {
    Tuple p1 = new Point(1.0,2.0, 4.0);
    Tuple v1 = new Vector(2.0,4.0, 8.0);

    Tuple expected = new Point(3.0, 6.0, 12.0);

    Tuple result = p1.add(v1);

    assertTrue(result instanceof Point);
    assertEquals(expected, result);
  }

  @Test
  public void testAddingVectorToVector() {
    Tuple v1 = new Vector(1.0,2.0, 4.0);
    Tuple v2 = new Vector(2.0,4.0, 8.0);

    Tuple expected = new Vector(3.0, 6.0, 12.0);

    Tuple result = v1.add(v2);

    assertTrue(result instanceof Vector);
    assertEquals(expected, result);
  }

//  @Test(expected = RuntimeException.class)
//  public void testAddingPointToPoint() {
//    Tuple p1 = new Point(1.0,2.0, 4.0);
//    Tuple p2 = new Point(2.0,4.0, 8.0);
//
//    p1.add(p2);
//  }

  @Test
  public void testSubtractingPointFromPoint() {
    Tuple p1 = new Point(3.0,2.0, 1.0);
    Tuple p2 = new Point(5.0,6.0, 7.0);

    Tuple expected = new Vector(-2, -4, -6);

    Tuple result = p1.minus(p2);

    assertTrue(result instanceof Vector);
    assertEquals(expected, result);
  }

  @Test
  public void testSubtractingVectorFromPoint() {
    Tuple p1 = new Point(-2.0,-4.0, -8.0);
    Tuple v1 = new Vector(1.0,2.0, 4.0);

    Tuple expected = new Point(-3.0, -6.0, -12.0);

    Tuple result = p1.minus(v1);

    assertTrue(result instanceof Point);
    assertEquals(expected, result);
  }

  @Test
  public void testSubtractingVectorFromVector() {
    Tuple v1 = new Vector(3.0,2.0, 1.0);
    Tuple v2 = new Vector(5.0,6.0, 7.0);

    Tuple expected = new Vector(-2, -4, -6);

    Tuple result = v1.minus(v2);

    assertTrue(result instanceof Vector);
    assertEquals(expected, result);
  }

//  @Test(expected = RuntimeException.class)
//  public void testSubtractingPointFromVector() {
//    Tuple v1 = new Vector(1.0,2.0, 4.0);
//    Tuple p1 = new Point(-2.0,-4.0, -8.0);
//
//    v1.minus(p1);
//  }

  @Test
  public void testNegatePoint() {
    Tuple p1 = new Point(3.0,2.0, 1.0, 6.0);

    Tuple expected = new Point(-3, -2, -1, -6);

    Tuple result = p1.negate();

    assertTrue(result instanceof Point);
    assertEquals(expected, result);
  }

  @Test
  public void testMultiplyVector() {
    Tuple v1 = new Vector(1,-2, 3, -4);
    double scalar = 3.5;

    Tuple expected = new Vector(3.5, -7, 10.5, -14);

    Tuple result = v1.multiple(scalar);

    assertTrue(result instanceof Vector);
    assertEquals(expected, result);
  }

  @Test
  public void testDivideVector() {
    Tuple v1 = new Vector(18, 33, 3, -9);
    double scalar = 3;

    Tuple expected = new Vector(6, 11, 1, -3);

    Tuple result = v1.divide(scalar);

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
    Tuple expected = new Vector((1/Math.sqrt(14)),
        (2/Math.sqrt(14)),
        (3/Math.sqrt(14)));

    Tuple normalized = t.normalize();

    assertEquals(expected, normalized);
  }

  @Test
  public void testDotProduct() {
    Tuple v1 = new Vector(1, 2, 3);
    Tuple v2 = new Vector(2, 3, 4);

    double expected = 20;
    double answer = v1.dotProd(v2);

    assertEquals(expected, answer, 0.00001d);
  }

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
