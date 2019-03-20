package com.arkumbra.raytracer.world;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.canvas.PPMPrinter;
import com.arkumbra.raytracer.canvas.SceneCanvas;
import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Vector;
import org.junit.Test;

public class EnvironmentTest {

  @Test
  public void testProjectileFallsToGround() {
    int width = 800, height = 400;
    Colour background = new Colour(0, 0, 0.6);
    SceneCanvas sceneCanvas = new SceneCanvas(width, height, background);

    Vector gravity = new Vector(0f, -0.1f, 0f);
    Vector wind = new Vector(-0.25f, 0f, 0f);
    Environment env = new Environment(gravity, wind);

    Point position = new Point(width/2, 1, 0);
    Vector velocity = new Vector(12, 6, 0);
    Projectile projectile = new Projectile(position, velocity);

    Colour colour = new Colour(1,1,1);
    int ticksToReachGround = 0;
    while (projectile.getPosition().y() > 0) {
      System.out.println(projectile);

      Point pos = projectile.getPosition();

      sceneCanvas.write(pos.x(), height - pos.y(), colour);
      projectile = env.tick(projectile);
      ticksToReachGround++;
    }

    System.out.println("Took " + ticksToReachGround + " ticks to reach the ground");

    new PPMPrinter().writePPMFile("build", "projectile.ppm", sceneCanvas);
  }

}
