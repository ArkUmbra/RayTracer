package com.arkumbra.raytracer.challenge;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.canvas.PPMPrinter;
import com.arkumbra.raytracer.canvas.SceneCanvas;
import com.arkumbra.raytracer.geometry.Point;
import com.arkumbra.raytracer.geometry.Tuple;
import com.arkumbra.raytracer.matrices.Matrix;
import com.arkumbra.raytracer.matrices.MatrixFactory;
import org.junit.Test;

public class AnalogClockTest {

  private int width = 100;
  private int height = 80;
  private Colour clockPointColour = Colour.WHITE;

  @Test
  public void drawAnalogClockThroughTransformationsUsingXY() {
    SceneCanvas sceneCanvas = new SceneCanvas(width, height, Colour.BLACK);

    Point base = new Point(0,0,0);
    writeFromXY(sceneCanvas, base);

    Matrix translateTo12 = MatrixFactory.translation(0, height * 3/ 8, 0);

    Tuple twelve = translateTo12.multiply(base);
    writeFromXY(sceneCanvas, twelve);

    drawRotateZ(twelve, -Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, -2 * Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, -3 * Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, -4 * Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, -5 * Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, -6 * Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, 6 *Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, 5 * Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, 4 * Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, 3 * Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, 2 * Math.PI / 6, sceneCanvas);
    drawRotateZ(twelve, Math.PI / 6, sceneCanvas);

    new PPMPrinter().writePPMFile("build", "clockxy.ppm", sceneCanvas);
  }

  @Test
  public void drawAnalogClockThroughTransformationsUsingZX() {
    SceneCanvas sceneCanvas = new SceneCanvas(width, height, Colour.BLACK);

    Point base = new Point(0,0,0);
    writeFromXZ(sceneCanvas, base);

    Matrix translateTo12 = MatrixFactory.translation(0, 0,height * 3/ 8);

    Tuple twelve = translateTo12.multiply(base);
    writeFromXZ(sceneCanvas, twelve);

    drawRotateY(twelve, Math.PI / 6, sceneCanvas);
    drawRotateY(twelve, 2 * Math.PI / 6, sceneCanvas);
    drawRotateY(twelve, 3 * Math.PI / 6, sceneCanvas);
    drawRotateY(twelve, 4 * Math.PI / 6, sceneCanvas);
    drawRotateY(twelve, 5 * Math.PI / 6, sceneCanvas);
    drawRotateY(twelve, 6 * Math.PI / 6, sceneCanvas);

    new PPMPrinter().writePPMFile("build", "clockxz.ppm", sceneCanvas);
  }


  private void drawRotateZ(Tuple pointToTranslate, double rotation, SceneCanvas sceneCanvas) {
    Tuple one = MatrixFactory.rotationZ(rotation).multiply(pointToTranslate);
    writeFromXY(sceneCanvas, one);
  }

  private void drawRotateY(Tuple pointToTranslate, double rotation, SceneCanvas sceneCanvas) {
    Tuple one = MatrixFactory.rotationY(rotation).multiply(pointToTranslate);
    writeFromXZ(sceneCanvas, one);
  }

  private void writeFromXY(SceneCanvas canvas, Tuple tuple) {
    System.out.println(tuple);

    canvas.write(
        convertX(tuple.x(), width),
        convertY(tuple.y(), height),
        clockPointColour);
  }

  private void writeFromXZ(SceneCanvas canvas, Tuple tuple) {
    System.out.println(tuple);

    canvas.write(
        convertX(tuple.x(), width),
        convertY(tuple.z(), height),
        clockPointColour);
  }


  /**
   * Our 'world' is centred at 0,0,0 so we need to convert to the canvas dimension which
   * has 0,0 at the corner
   */
  private int convertX(double x, int canvasWidth) {
    return (int)(x + (canvasWidth / 2));
  }

  private int convertY(double y, int canvasHeight) {
    return (int)(-1*y + (canvasHeight / 2));
  }

}
