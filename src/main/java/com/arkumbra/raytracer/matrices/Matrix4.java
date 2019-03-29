package com.arkumbra.raytracer.matrices;

import com.arkumbra.raytracer.geometry.Tuple;
import com.arkumbra.raytracer.geometry.TupleFactory;

public class Matrix4 extends Matrix {
  private static final int WIDTH = 4;
  private static final int HEIGHT = 4;

  private static final Matrix4 IDENTITY = new Matrix4(new Double[][]{
      {1d,0d,0d,0d},
      {0d,1d,0d,0d},
      {0d,0d,1d,0d},
      {0d,0d,0d,1d},
  });

  public static Matrix getIdent() {
    return IDENTITY.clone();
  }

  public Matrix4() {
    super(WIDTH, HEIGHT);
  }

  public Matrix4(Double[][] cells) {
    super(WIDTH, HEIGHT, cells);
  }

  @Override
  protected Matrix identity() {
    return IDENTITY;
  }

  @Override
  public Matrix multiply(Matrix other) {
    Double[][] newData = new Double[WIDTH][HEIGHT];

    for (int row = 0; row <= 3; row++) {
      for (int col = 0; col <= 3; col++) {
        newData[row][col] =
          this.get(row, 0) * other.get(0, col) +
          this.get(row, 1) * other.get(1, col) +
          this.get(row, 2) * other.get(2, col) +
          this.get(row, 3) * other.get(3, col);
      }
    }

    return new Matrix4(newData);
  }

  @Override
  public Tuple multiply(Tuple tuple) {
    double[] tupleData = new double[4];

    for (int row = 0; row <= 3; row++) {
        tupleData[row] =
            this.get(row, 0) * tuple.x() +
                this.get(row, 1) * tuple.y() +
                this.get(row, 2) * tuple.z() +
                this.get(row, 3) * tuple.w();
    }

    return TupleFactory.create(tupleData[0], tupleData[1],tupleData[2],tupleData[3]);
  }

  @Override
  public double determinant() {
    return determinantForNon2XMatrices();
  }

}
