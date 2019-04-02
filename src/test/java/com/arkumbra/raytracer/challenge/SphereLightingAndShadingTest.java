package com.arkumbra.raytracer.challenge;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.canvas.PPMPrinter;
import com.arkumbra.raytracer.canvas.SceneCanvas;
import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Vector;
import com.arkumbra.raytracer.light.Material;
import com.arkumbra.raytracer.light.PointLight;
import com.arkumbra.raytracer.light.Ray;
import com.arkumbra.raytracer.matrices.MatrixFactory;
import com.arkumbra.raytracer.shapes.Intersection;
import com.arkumbra.raytracer.shapes.Intersections;
import com.arkumbra.raytracer.shapes.Sphere;
import org.junit.Test;

public class SphereLightingAndShadingTest {

  private double ambient = 0.3;
  private double diffuse = 0.4;
  private double specular = 0.9;
  private double shininess = 100;

  private Colour sphereMatColour = new Colour(0.2, 0.8, 0.5);
  private Colour lightColour = new Colour(0.7, 1, 0.7);
  private Material sphereMat = new Material(sphereMatColour, ambient, diffuse, specular, shininess);

  private Point lightPos = new Point(7, 3, -10);

  @Test
  public void testCastRayAgainstSphereForShadow() {
    Point rayOrigin = new Point(0,0,-7);

    double wallZ = 10d;
    double wallSize = 7;

    int canvasSize = 300;
    double pixelSize = wallSize / canvasSize;
    double half = wallSize / 2;

    SceneCanvas canvas = new SceneCanvas(canvasSize, canvasSize);
    Sphere sphere = new Sphere();

//    sphere.setTransform(
//        MatrixFactory.shearing(1,0,0,0,0,0).multiply(
//            MatrixFactory.scaling(0.5, 1, 1))
//    );

    // sphere material
    sphere.setMaterial(sphereMat);

    // lighting
    PointLight pointLight = new PointLight(lightPos, lightColour);


    for (int y = 0; y < canvasSize; y++) {
      double worldY = half - (pixelSize * y);

      for (int x = 0; x < canvasSize; x++) {
        double worldX = -half + (pixelSize * x);

        Point positionOnWall = new Point(worldX, worldY, wallZ);

        Ray r = new Ray(rayOrigin, (Vector)positionOnWall.minus(rayOrigin).normalize());

        Intersections xs = sphere.intersect(r);
        Intersection hit = xs.getHit();
        if (hit != null) {
          Point posToBeLit = (Point) r.calcPosition(hit.getT());
          Vector normal = hit.getObject().normalAt(posToBeLit);
          Vector eye = (Vector)r.getDirection().negate();

          Material hitMaterial = xs.getHit().getObject().getMaterial();
          Colour appliedColour = hitMaterial.lighting(pointLight, posToBeLit, eye, normal);
          canvas.write(x, y, appliedColour);
        }
      }
    }


    new PPMPrinter().writePPMFile("build", "shadedSphere.ppm", canvas);
  }

}
