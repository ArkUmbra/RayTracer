package com.arkumbra.raytracer.ui;

import java.awt.Canvas;
import java.awt.Graphics;

public class PaintCanvas extends Canvas {

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    g.drawRect(100, 100, 50, 50);
  }
}
