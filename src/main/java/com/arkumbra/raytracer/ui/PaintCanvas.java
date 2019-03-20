package com.arkumbra.raytracer.ui;

import com.arkumbra.raytracer.canvas.Colour;
import com.arkumbra.raytracer.canvas.SceneCanvas;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class PaintCanvas extends Canvas {

  private SceneCanvas sceneCanvas;

  public void setSceneCanvas(SceneCanvas sceneCanvas) {
    this.sceneCanvas = sceneCanvas;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    if (sceneCanvas == null)
      return;


    int width = sceneCanvas.getWidth();
    int height = sceneCanvas.getHeight();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Colour colour = sceneCanvas.pixelAt(x, y);
        g.setColor(toAwtColor(colour));

        g.drawRect(x, y, 1, 1);
      }
    }

    g.drawRect(100, 100, 50, 50);
  }

  private Color toAwtColor(Colour colour) {
    return new Color(
        (int)(colour.r() * 255),
        (int)(colour.g() * 255),
        (int)(colour.b() * 255)
    );
  }
}
