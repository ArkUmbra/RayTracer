package com.arkumbra.raytracer.matrices;

public class MatrixFactory {

  public static Matrix shearing(double xy, double xz, double yx, double yz, double zx, double zy) {
    Matrix identity = Matrix4.getIdent();
    identity.set(0, 1, xy);
    identity.set(0, 2, xz);
    identity.set(1, 0, yx);
    identity.set(1, 2, yz);
    identity.set(2, 0, zx);
    identity.set(2, 1, zy);
    return identity;
  }

  public static Matrix rotationZ(double radians) {
    Matrix identity = Matrix4.getIdent();
    identity.set(0, 0, Math.cos(radians));
    identity.set(1, 0, Math.sin(radians));
    identity.set(0, 1, -Math.sin(radians));
    identity.set(1, 1, Math.cos(radians));
    return identity;
  }

  public static Matrix rotationX(double radians) {
    Matrix identity = Matrix4.getIdent();
    identity.set(1, 1, Math.cos(radians));
    identity.set(1, 2, -Math.sin(radians));
    identity.set(2, 1, Math.sin(radians));
    identity.set(2, 2, Math.cos(radians));
    return identity;
  }

  public static Matrix rotationY(double radians) {
    Matrix identity = Matrix4.getIdent();
    identity.set(0, 0, Math.cos(radians));
    identity.set(0, 2, Math.sin(radians));
    identity.set(2, 0, -Math.sin(radians));
    identity.set(2, 2, Math.cos(radians));
    return identity;
  }

  public static Matrix translation(double x, double y, double z) {
    Matrix identity = Matrix4.getIdent();
    identity.set(0, 3, x);
    identity.set(1, 3, y);
    identity.set(2, 3, z);
    return identity;
  }

  public static Matrix scaling(double x, double y, double z) {
    Matrix identity = Matrix4.getIdent();
    identity.set(0, 0, x);
    identity.set(1, 1, y);
    identity.set(2, 2, z);
    return identity;
  }

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
