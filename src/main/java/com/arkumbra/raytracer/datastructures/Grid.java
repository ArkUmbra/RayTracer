package com.arkumbra.raytracer.datastructures;

import java.lang.reflect.Array;

public class Grid <T> {

  private T[][] data;
//  private final Object[][] data;

  @SuppressWarnings("unchecked")
  public Grid(Class clazz, int width, int height) {
    this.data = (T[][])Array.newInstance(clazz, width, height);
//    this.data = new Object[width][height];

  }

  public T get(int x, int y) {
    return data[x][y];
  }

  public void put(T t, int x, int y) {
    data[x][y] = t;
  }

}
