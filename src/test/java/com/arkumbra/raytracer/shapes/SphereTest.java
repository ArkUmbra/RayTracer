package com.arkumbra.raytracer.shapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

}
