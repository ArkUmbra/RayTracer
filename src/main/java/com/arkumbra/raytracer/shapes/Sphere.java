package com.arkumbra.raytracer.shapes;

import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Tuple;
import com.arkumbra.raytracer.geometry.Vector;
import com.arkumbra.raytracer.light.Material;
import com.arkumbra.raytracer.light.Ray;
import com.arkumbra.raytracer.matrices.Matrix;
import com.arkumbra.raytracer.matrices.Matrix4;

public class Sphere {

  private static long currentCumulativeID = 0l;
  private final long ID;

  public static final Point ORIGIN = new Point(0,0,0);

  private final Point position = ORIGIN;
  private int radius = 1;

  private Matrix transform = Matrix4.getIdent();
  private Material material;

  public Sphere() {
    this.ID = currentCumulativeID;
    currentCumulativeID++;

    this.material = new Material();
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

  /* Book notes:
   *
   * 1. Technically, you should be finding submatrix(transform, 3, 3) (from Spotting Sub- matrices,
   * on page 34) first, and multiplying by the inverse and transpose of that. Otherwise, if your
   * transform includes any kind of translation, then multiplying by its transpose will wind up
   * mucking with the w coordinate in your vector, which will wreak all kinds of havoc in later
   * computations. But if you don’t mind a bit of a hack, you can avoid all that by just setting
   * world_normal.w to 0 after multiplying by the 4x4 inverse transpose matrix.
   *
   * 2. The inverse transpose matrix may change the length of your vector, so if you feed it a
   * vector of length 1 (a normalized vector), you may not get a normalized vector out! It’s best
   * to be safe, and always normalize the result.
   */
  public Vector normalAt(Point worldPoint) {
    Tuple objectPoint = transform.invert().multiply(worldPoint);
    Tuple objectNormal = objectPoint.minus(position).normalize();

    Tuple worldNormal = transform.invert().transpose().multiply(objectNormal);
    worldNormal.setW(0);

    return (Vector) worldNormal.normalize();
  }


  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Sphere) {
      Sphere other = (Sphere) obj;
      return other.ID == this.ID;
    }

    return false;
  }

  public long getID() {
    return ID;
  }

  public void setTransform(Matrix transform) {
    this.transform = transform;
  }

  public Matrix getTransform() {
    return transform;
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }
}
