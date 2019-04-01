package com.arkumbra.raytracer.shapes;

import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Tuple;
import com.arkumbra.raytracer.light.Ray;
import com.arkumbra.raytracer.matrices.Matrix;
import com.arkumbra.raytracer.matrices.Matrix4;

public class Sphere {

  private static long currentCumulativeID = 0l;
  private final long ID;

  private final Point position = new Point(0,0,0);
  private int radius = 1;

  private Matrix transform = Matrix4.getIdent();

  public Sphere() {
    this.ID = currentCumulativeID;
    currentCumulativeID++;
  }

  /**
   *
   * @param rayIn
   * @return array of t units at which ray intersects this sphere
   */
  public Intersections intersect(Ray rayIn) {
    Matrix invertedSphereTransform = this.transform.invert();
    Ray ray = rayIn.transform(invertedSphereTransform);

    Tuple sphereToRay = ray.getOrigin().minus(position);

    double a = ray.getDirection().dotProd(ray.getDirection());
    double b = 2 * ray.getDirection().dotProd(sphereToRay);
    double c = sphereToRay.dotProd(sphereToRay) - 1;

    double discriminant = b*b - 4 * a * c;

    if (discriminant < 0) {
      return new Intersections();
    }

    double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
    double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

    return new Intersections(
        new Intersection(t1, this),
        new Intersection(t2, this)
    );
  }

  public long getID() {
    return ID;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Sphere) {
      Sphere other = (Sphere) obj;
      return other.ID == this.ID;
    }

    return false;
  }

  public void setTransform(Matrix transform) {
    this.transform = transform;
  }

  public Matrix getTransform() {
    return transform;
  }

}
