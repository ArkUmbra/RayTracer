package com.arkumbra.raytracer.world;

import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Vector;

public class Environment {

  private final Vector wind;
  private final Vector gravity;

  public Environment(Vector wind, Vector gravity) {
    this.wind = wind;
    this.gravity = gravity;
  }

  public Projectile tick(Projectile proj) {
    Point newPosition = (Point) proj.getPosition().add(proj.getVelocity());
    Vector newVelocity = (Vector) proj.getVelocity().add(wind).add(gravity);

    return new Projectile(newPosition, newVelocity);
  }

}
