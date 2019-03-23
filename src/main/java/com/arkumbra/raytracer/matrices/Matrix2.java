package com.arkumbra.raytracer.matrices;

import com.arkumbra.raytracer.geometry.Tuple;

public class Matrix2 extends Matrix {
  private static final int WIDTH = 2;
  private static final int HEIGHT = 2;

  public static final Matrix2 IDENTITY = new Matrix2(new Double[][]{
      {1d,0d},
      {0d,1d}
  });

  public Matrix2() {
    super(WIDTH, HEIGHT);
  }

  public Matrix2(Double[][] cells) {
    super(WIDTH, HEIGHT, cells);
  }


  @Override
  public Matrix getIdentity() {
    return IDENTITY;
  }

  @Override
  public Matrix multiply(Matrix other) {
    Double[][] newData = new Double[WIDTH][HEIGHT];

    for (int row = 0; row <= 1; row++) {
      for (int col = 0; col <= 1; col++) {
        newData[row][col] =
          this.get(row, 0) * other.get(0, col) +
          this.get(row, 1) * other.get(1, col);
      }
    }

    return new Matrix2(newData);
  }

  // TODO errr
  @Override
  public Tuple multiply(Tuple tuple) {
    throw new UnsupportedOperationException("Don't yet know how to multiple Matrix2 by Tuple");
  }

  /*
   * determinant | a b |  = ad − bc
   *             | c d |
   */
  @Override
  public double determinant() {
    return get(0,0) * get(1,1) -
        get(1, 0) * get(0, 1);
  }

}
