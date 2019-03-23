package com.arkumbra.raytracer.matrices;

import com.arkumbra.raytracer.geometry.Tuple;

public class Matrix3 extends Matrix {
  private static final int WIDTH = 3;
  private static final int HEIGHT = 3;

  public static final Matrix3 IDENTITY = new Matrix3(new Double[][]{
      {1d,0d,0d},
      {0d,1d,0d},
      {0d,0d,1d}
  });

  public Matrix3() {
    super(WIDTH, HEIGHT);
  }

  public Matrix3(Double[][] cells) {
    super(WIDTH, HEIGHT, cells);
  }

  @Override
  public Matrix getIdentity() {
    return IDENTITY;
  }

  @Override
  public Matrix multiply(Matrix other) {
    Double[][] newData = new Double[WIDTH][HEIGHT];

    for (int row = 0; row <= 2; row++) {
      for (int col = 0; col <= 2; col++) {
        newData[row][col] =
          this.get(row, 0) * other.get(0, col) +
          this.get(row, 1) * other.get(1, col) +
          this.get(row, 2) * other.get(2, col);
      }
    }

    return new Matrix3(newData);
  }

  @Override
  public Tuple multiply(Tuple tuple) {
    throw new UnsupportedOperationException("Don't yet know how to multiple Matrix2 by Tuple");
  }

  @Override
  public double determinant() {
    return determinantForNon2XMatrices();
  }

}
