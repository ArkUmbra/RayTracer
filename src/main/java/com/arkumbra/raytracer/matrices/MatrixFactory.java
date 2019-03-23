package com.arkumbra.raytracer.matrices;

public class MatrixFactory {

  public static Matrix generateStandardMatrix(Double[][] data) {
    if (lengthAndHeightOf(4, data)) {
      return new Matrix4(data);

    } else if (lengthAndHeightOf(3, data)) {
      return new Matrix3(data);

    } else if (lengthAndHeightOf(2, data)) {
      return new Matrix2(data);

    } else {
      throw new UnsupportedOperationException("Not valid matrix size " +
        "width " + width(data) + ", height " + height(data));
    }
  }

  private static boolean lengthAndHeightOf(int size, Double[][] data) {
    return width(data) == size && height(data) == size;
  }

  private static int width(Double[][] data) {
    return data[0].length;
  }

  private static int height(Double[][] data) {
    return data.length;
  }

}
