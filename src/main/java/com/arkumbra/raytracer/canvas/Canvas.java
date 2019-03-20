package com.arkumbra.raytracer.canvas;

import com.arkumbra.raytracer.datastructures.Grid;

public class Canvas {

  private Grid<Colour> grid;

  public Canvas(int width, int height) {
    grid = new Grid<>(Colour.class, width, height);
  }

  public void write(int x, int y, Colour colour) {
    grid.put(colour, x, y);
  }

  public Colour pixelAt(int x, int y) {
    return grid.get(x, y);
  }

}
