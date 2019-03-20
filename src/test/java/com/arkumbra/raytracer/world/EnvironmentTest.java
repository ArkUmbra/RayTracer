package com.arkumbra.raytracer.world;

import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Vector;
import org.junit.Test;

public class EnvironmentTest {

  @Test
  public void testProjectileFallsToGround() {
    Vector gravity = new Vector(0f, -0.1f, 0f);
    Vector wind = new Vector(-0.01f, 0f, 0f);
    Environment env = new Environment(gravity, wind);

    Point position = new Point(0, 1, 0);
    Vector velocity = new Vector(1, 2, 0);
    Projectile projectile = new Projectile(position, velocity);

    int ticksToReachGround = 0;
    while (projectile.getPosition().y() > 0) {
      System.out.println(projectile);

      projectile = env.tick(projectile);
      ticksToReachGround++;
    }

    System.out.println("Took " + ticksToReachGround + " ticks to reach the ground");

  }

}
