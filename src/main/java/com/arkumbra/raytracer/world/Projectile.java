package com.arkumbra.raytracer.world;

import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Vector;

public class Projectile {

  private final Point position;
  private final Vector velocity;

  public Projectile(Point position, Vector velocity) {
    this.position = position;
    this.velocity = velocity;
  }

  public Point getPosition() {
    return position;
  }

  public Vector getVelocity() {
    return velocity;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Position [x: ");
    sb.append(position.x());
    sb.append(", y:");
    sb.append(position.y());
    sb.append(", z:");
    sb.append(position.z());
    sb.append("]");

    return sb.toString();
  }
}
