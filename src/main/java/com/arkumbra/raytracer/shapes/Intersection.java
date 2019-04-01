package com.arkumbra.raytracer.shapes;

public class Intersection implements Comparable {

  private double t;
  private Sphere object;

  public Intersection(double t, Sphere object) {
    this.t = t;
    this.object = object;
  }

  public double getT() {
    return t;
  }

  public Sphere getObject() {
    return object;
  }

  @Override
  public int compareTo(Object o) {
    if (o instanceof Intersection) {
      Intersection other = (Intersection) o;

      double diff = this.t - other.t;
      if (diff > 0)
        return 1;
      else if (diff < 0)
        return -1;
      else
        return 0;
    }

    return -1;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Intersection) {
      Intersection other = (Intersection) obj;

      return t == t && object.equals(other.object);
    }

    return false;
  }
}
