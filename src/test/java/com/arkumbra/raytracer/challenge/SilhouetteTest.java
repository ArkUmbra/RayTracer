package com.arkumbra.raytracer.challenge;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.canvas.PPMPrinter;
import com.arkumbra.raytracer.canvas.SceneCanvas;
import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Vector;
import com.arkumbra.raytracer.light.Ray;
import com.arkumbra.raytracer.matrices.MatrixFactory;
import com.arkumbra.raytracer.shapes.Intersections;
import com.arkumbra.raytracer.shapes.Sphere;
import org.junit.Test;

public class SilhouetteTest {

  @Test
  public void testCastRayAgainstSphereForShadow() {
    Point rayOrigin = new Point(0,0,-7);

    double wallZ = 10d;
    double wallSize = 7;

    int canvasSize = 100;
    double pixelSize = wallSize / canvasSize;
    double half = wallSize / 2;

    SceneCanvas canvas = new SceneCanvas(canvasSize, canvasSize);
    Colour colour = new Colour(0.1,0.2,0.9);
    Sphere sphere = new Sphere();

    sphere.setTransform(
        MatrixFactory.shearing(1,0,0,0,0,0).multiply(
            MatrixFactory.scaling(0.5, 1, 1))
    );


    for (int y = 0; y < canvasSize; y++) {
      double worldY = half - (pixelSize * y);

      for (int x = 0; x < canvasSize; x++) {
        double worldX = -half + (pixelSize * x);

        Point positionOnWall = new Point(worldX, worldY, wallZ);

        Ray r = new Ray(rayOrigin, (Vector)positionOnWall.minus(rayOrigin).normalize());

        Intersections xs = sphere.intersect(r);
        if (xs.getHit() != null) {
          canvas.write(x, y, colour);
        }
      }
    }


    new PPMPrinter().writePPMFile("build", "silhouette.ppm", canvas);
  }

}
