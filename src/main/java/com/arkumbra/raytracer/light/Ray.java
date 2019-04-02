package com.arkumbra.raytracer.light;

import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Tuple;
import com.arkumbra.raytracer.geometry.Vector;
import com.arkumbra.raytracer.matrices.Matrix;

public class Ray {

  private final Point origin;
  private final Vector direction;

  public Ray(Point origin, Vector direction) {
    this.origin = origin;
    this.direction = direction;
  }

  public Point getOrigin() {
    return origin;
  }

  public Vector getDirection() {
    return direction;
  }

  public Tuple calcPosition(double unitsOfTime) {
    return origin.add(direction.multiply(unitsOfTime));
  }

  public Ray transform(Matrix matrix) {
    Point transformedOrigin = (Point) matrix.multiply(this.origin);
    Vector transformedDirection = (Vector) matrix.multiply(this.direction);

    return new Ray(transformedOrigin, transformedDirection);
  }

}
