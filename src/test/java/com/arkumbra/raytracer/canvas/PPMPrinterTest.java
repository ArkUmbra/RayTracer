package com.arkumbra.raytracer.canvas;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PPMPrinterTest {

  private PPMPrinter sut;

  @Before
  public void setup() {
    sut = new PPMPrinter();
  }

  @Test
  public void testPPMHeader() {
    int width = 3, height = 5;
    SceneCanvas sceneCanvas = new SceneCanvas(width, height);
    String ppmFile = sut.convertToPPMString(sceneCanvas);

    String[] lines = ppmFile.split("\n");
    assertEquals("P3", lines[0]);
    assertEquals(width + " " + height, lines[1]);
    assertEquals("255", lines[2]);
  }


  @Test
  public void testPPMBodyAndColoursCappedAt255() {
    int width = 5, height = 3;
    SceneCanvas sceneCanvas = new SceneCanvas(width, height);

    Colour c1 = new Colour(1.5, 0, 0);
    Colour c2 = new Colour(0, 0.5, 0);
    Colour c3 = new Colour(-0.5, 0, 1);
    sceneCanvas.write(0,0, c1);
    sceneCanvas.write(2,1, c2);
    sceneCanvas.write(4,2, c3);

    String ppmFile = sut.convertToPPMString(sceneCanvas);
    String[] lines = ppmFile.split("\n");

    assertEquals("255 0 0 0 0 0 0 0 0 0 0 0 0 0 0", lines[3]);
    assertEquals("0 0 0 0 0 0 0 128 0 0 0 0 0 0 0", lines[4]);
    assertEquals("0 0 0 0 0 0 0 0 0 0 0 0 0 0 255", lines[5]);
  }

  @Test
  public void testPPMBodyLinesDoNotExceed70() {
    int width = 10, height = 2;
    Colour background = new Colour(1, 0.8, 0.6);
    SceneCanvas sceneCanvas = new SceneCanvas(width, height, background);

    String ppmFile = sut.convertToPPMString(sceneCanvas);
    String[] lines = ppmFile.split("\n");

    /**
     255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
     153 255 204 153 255 204 153 255 204 153 255 204 153
     255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204
     153 255 204 153 255 204 153 255 204 153 255 204 153
     */

    assertEquals("255 204 153 255 204 153 255 204 153 255 204 153 255 204 153", lines[3]);
    assertEquals("255 204 153 255 204 153 255 204 153 255 204 153 255 204 153", lines[4]);
    assertEquals("255 204 153 255 204 153 255 204 153 255 204 153 255 204 153", lines[5]);
    assertEquals("255 204 153 255 204 153 255 204 153 255 204 153 255 204 153", lines[6]);
  }

  @Test
  public void testPPMContentsEndWithNewlineCharacter() {
    int width = 10, height = 2;
    SceneCanvas sceneCanvas = new SceneCanvas(width, height);

    String ppmFile = sut.convertToPPMString(sceneCanvas);
    assertTrue(ppmFile.endsWith("\n"));
  }

  @Test
  public void testWritePPMFile() {
    int width = 800, height = 400;
    Colour background = new Colour(0, 0, 0.6);
    SceneCanvas sceneCanvas = new SceneCanvas(width, height, background);

    sut.writePPMFile("build", "file.ppm", sceneCanvas);
  }

}
