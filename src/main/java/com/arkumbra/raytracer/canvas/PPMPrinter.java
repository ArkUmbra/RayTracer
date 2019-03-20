package com.arkumbra.raytracer.canvas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.apache.commons.io.FileUtils;

public class PPMPrinter {
  private static final String PPM_HEADER = "P3";
  private static final String NEWLINE = "\n";
  private static final String SPACE = " ";
  private static final int MAX_COLOUR_BOUND = 255;

  public void writePPMFile(String path, String filename, SceneCanvas canvas) {

    try (PrintWriter out = new PrintWriter(path + "/" + filename)) {
      out.println(convertToPPMString(canvas));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public String convertToPPMString(SceneCanvas canvas) {
    StringBuilder sb = new StringBuilder();

    // HEADER
    sb.append(PPM_HEADER);
    sb.append(NEWLINE);
    sb.append(canvas.getWidth());
    sb.append(SPACE);
    sb.append(canvas.getHeight());
    sb.append(NEWLINE);
    sb.append(MAX_COLOUR_BOUND);

    for (int y = 0; y < canvas.getHeight(); y++) {
      sb.append(NEWLINE);
      for (int x = 0; x < canvas.getWidth(); x++) {
        Colour c = canvas.pixelAt(x, y);
        sb.append(colourToString(c));

        // PPM files should only have 70 characters per line...
        // just split every 4 for consistency and to make it easier to read
        // 255 255 255.255 255 255.255 255 255.255 255 255.255 255 255
        if (x + 1 < canvas.getWidth()) {
          if ((x + 1) % 5 == 0) {
            sb.append(NEWLINE);
          } else {
            sb.append(SPACE);
          }
        }
      }
    }

    // "Some image programs (notably ImageMagick2) won’t process PPM files correctly unless
    // the files are terminated by a newline character."
    sb.append(NEWLINE);
    return sb.toString();
  }

  private String colourToString(Colour colour) {
    StringBuffer sb = new StringBuffer();

    sb.append(clampColourWithBounds(colour.r()));
    sb.append(SPACE);
    sb.append(clampColourWithBounds(colour.g()));
    sb.append(SPACE);
    sb.append(clampColourWithBounds(colour.b()));
    return sb.toString();
  }

  private String clampColourWithBounds(Double colour) {
    colour = colour * MAX_COLOUR_BOUND;

    if (colour > MAX_COLOUR_BOUND) {
      return "" + MAX_COLOUR_BOUND;
    } else if (colour < 0) {
      return "" + 0;
    } else {
      return "" + Math.round(colour);
    }
  }


}
