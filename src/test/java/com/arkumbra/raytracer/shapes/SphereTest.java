package com.arkumbra.raytracer.shapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Tuple;
import com.arkumbra.raytracer.geometry.Vector;
import com.arkumbra.raytracer.light.Material;
import com.arkumbra.raytracer.matrices.Matrix;
import com.arkumbra.raytracer.matrices.Matrix4;
import com.arkumbra.raytracer.matrices.MatrixFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class SphereTest {

  @Test
  public void testSphereIDsAreUnique() {
    List<Long> ids = new ArrayList<>();

    for (int i = 0; i < 10000; i++) {
      Sphere sphere = new Sphere();

      if (ids.contains(sphere.getID())) {
        fail("Non-unique ID generated");
      }

      ids.add(sphere.getID());
    }
  }

  /**
   * Scenario: A sphere's default transformation
   * Given s ← sphere()
   * Then s.transform = identity_matrix
   */
  @Test
  public void testSpheresDefaultTransformIsIdentityMatrix() {
    Sphere s = new Sphere();

    assertEquals(Matrix4.getIdent(), s.getTransform());
  }

  /**
   * Scenario: Changing a sphere's transformation
   * Given s ← sphere()
   * And t ← translation(2, 3, 4)
   * When set_transform(s, t)
   * Then s.transform = t
   */
  @Test
  public void testSpheresDefaultTransformCanBeChanged() {
    Sphere s = new Sphere();
    Matrix t = MatrixFactory.translation(2,3,4);
    s.setTransform(t);

    assertEquals(t, s.getTransform());
  }

  /**
   * Scenario: The normal on a sphere at a point on the x axis
   * Given s ← sphere()
   * When n ← normal_at(s, point(1, 0, 0))
   * Then n = vector(1, 0, 0)
   */
  @Test
  public void testFindSpheresNormalAtPointOnXAxis() {
    Sphere s = new Sphere();
    Tuple normal = s.normalAt(new Point(1,0,0));

    assertEquals(new Vector(1,0,0), normal);
  }

  /**
   * Scenario: The normal on a sphere at a point on the y axis
   * Given s ← sphere()
   * When n ← normal_at(s, point(0, 1, 0))
   * Then n = vector(0, 1, 0)
   */
  @Test
  public void testFindSpheresNormalAtPointOnYAxis() {
    Sphere s = new Sphere();
    Tuple normal = s.normalAt(new Point(0,1,0));

    assertEquals(new Vector(0,1,0), normal);
  }

  /**
   * Scenario: The normal on a sphere at a point on the z axis
   * Given s ← sphere()
   * When n ← normal_at(s, point(0, 0, 1))
   * Then n = vector(0, 0, 1)
   */
  @Test
  public void testFindSpheresNormalAtPointOnZAxis() {
    Sphere s = new Sphere();
    Tuple normal = s.normalAt(new Point(0,0,1));

    assertEquals(new Vector(0,0,1), normal);
  }

  /**
   * Scenario: The normal on a sphere at a nonaxial point
   * Given s ← sphere()
   * When n ← normal_at(s, point(√3/3, √3/3, √3/3))
   * Then n = vector(√3/3, √3/3, √3/3)
   */
  @Test
  public void testFindSpheresNormalOnANonaxialPoint() {
    double val = Math.sqrt(3) / 3;

    Sphere s = new Sphere();
    Tuple normal = s.normalAt(new Point(val,val,val));

    assertEquals(new Vector(val,val,val), normal);
  }

  /**
   * Scenario: The normal is a normalized vector
   * Given s ← sphere()
   * When n ← normal_at(s, point(√3/3, √3/3, √3/3))
   * Then n = normalize(n)
   */
  @Test
  public void testSpheresNormalIsNormalizedVector() {
    double val = Math.sqrt(3) / 3;

    Sphere s = new Sphere();
    Tuple normal = s.normalAt(new Point(val,val,val));

    assertEquals(normal, normal.normalize());
  }

  /**
   * Scenario: Computing the normal on a translated sphere
   * Given s ← sphere()
   * And set_transform(s, translation(0, 1, 0))
   * When n ← normal_at(s, point(0, 1.70711, -0.70711))
   * Then n = vector(0, 0.70711, -0.70711)
   */
  @Test
  public void testFindNormalOnTranslatedSphere() {
    Sphere s = new Sphere();
    s.setTransform(MatrixFactory.translation(0,1,0));

    Tuple normal = s.normalAt(new Point(0,1.70711,-0.70711));

    assertEquals(new Vector(0,0.70711,-0.70711), normal);
  }

  /**
   * Scenario: Computing the normal on a transformed sphere
   * Given s ← sphere()
   * And m ← scaling(1, 0.5, 1) * rotation_z(π/5)
   * And set_transform(s, m)
   * When n ← normal_at(s, point(0, √2/2, -√2/2))
   * Then n = vector(0, 0.97014, -0.24254)
   */
  @Test
  public void testFindNormalOnTransformedSphere() {
    Sphere s = new Sphere();
    s.setTransform(
        MatrixFactory.scaling(1,0.5,1).multiply(
            MatrixFactory.rotationZ(Math.PI/5)
        ));

    double val = Math.sqrt(2)/2;
    Tuple normal = s.normalAt(new Point(0,val,-val));

    assertEquals(new Vector(0,0.97014,-0.24254), normal);
  }

  /**
   * Scenario: A sphere has a default material
   * Given s ← sphere()
   * When m ← s.material
   * Then m = material()
   */
  @Test
  public void testSphereHasDefaultMaterial() {
    Sphere s = new Sphere();

    assertEquals(new Material(), s.getMaterial());
  }

  /**
   * Scenario: A sphere may be assigned a material
   * Given s ← sphere()
   * And m ← material()
   * And m.ambient ← 1
   * When s.material ← m
   * Then s.material = m
   */
  @Test
  public void testSphereMaterialCanBeSet() {
    Sphere s = new Sphere();
    Material m = new Material();
    m.setAmbient(1.0d);
    s.setMaterial(m);

    assertEquals(m, s.getMaterial());
  }

}
