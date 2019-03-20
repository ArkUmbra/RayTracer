package com.arkumbra.raytracer.canvas;

import com.arkumbra.raytracer.datastructures.Grid;

public class SceneCanvas {

  private static final Colour DEFAULT = new Colour(0,0,0);

  private final int width;
  private final int height;
  private Grid<Colour> grid;

  public SceneCanvas(int width, int height) {
    this(width, height, DEFAULT);
  }

  public SceneCanvas(int width, int height, Colour background) {
    this.width = width;
    this.height = height;
    grid = new Grid<>(Colour.class, width, height, background);
  }

  public void write(int x, int y, Colour colour) {
    if (x > width || x < 0 ||
        y > height || y < 0) {
      return;
    }

    grid.put(colour, x, y);
  }

  public void write(Double x, Double y, Colour colour) {
    write((int)Math.round(x), (int)Math.round(y), colour);
  }

  public Colour pixelAt(int x, int y) {
    return grid.get(x, y);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

}
