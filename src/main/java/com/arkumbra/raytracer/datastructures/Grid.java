package com.arkumbra.raytracer.datastructures;

import java.lang.reflect.Array;

public class Grid <T> {

  private T[][] data;

  @SuppressWarnings("unchecked")
  public Grid(Class clazz, int width, int height, T initValue) {
    this.data = (T[][])Array.newInstance(clazz, width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        data[x][y] = initValue;
      }
    }
  }

  public T get(int x, int y) {
    return data[x][y];
  }

  public void put(T t, int x, int y) {
    data[x][y] = t;
  }

}
