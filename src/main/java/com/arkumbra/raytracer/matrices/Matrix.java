package com.arkumbra.raytracer.matrices;

import com.arkumbra.raytracer.geometry.Calc;
import com.arkumbra.raytracer.geometry.Tuple;

// TODO I have a feeling 'width' and 'height' are being used in reverse in a couple of places
// TODO Either take a look or just replace with 'dimension' for both
public abstract class Matrix {

  private final int width, height;
  private final Double[][] data;

  public Matrix(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new Double[width][height];
  }

  public Matrix(int width, int height, Double[][] cells) {
    this.width = width;
    this.height = height;
    data = cells;

    if (width != cells[0].length ||
        height != cells.length) {
      throw new RuntimeException("Incorrectly sized data for this matrix width/height");
    }
  }

  public final Double get(int row, int column) {
    return data[row][column];
  }

  public Matrix4 transpose() {
    Double[][] newData = new Double[width][height];

    for (int row = 0; row < width; row++) {
      for (int col = 0; col < height; col++) {
        newData[row][col] = this.get(col, row);
      }
    }

    return new Matrix4(newData);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Matrix) {
      return contentsAreEqual((Matrix)obj);
    }

    return false;
  }

  protected boolean contentsAreEqual(Matrix other) {
    if (height != other.height || width != other.width)
      return false;

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Double thisCell = this.get(y, x);
        Double otherCell = other.get(y, x);

        if (! Calc.approxEqual(thisCell, otherCell))
          return false;
      }
    }

    return true;
  }

  public final Matrix submatrix(int rowToRemove, int colToRemove) {
    if (rowToRemove + 1 > height || rowToRemove < 0 ||
        colToRemove + 1 > width || colToRemove < 0) {
      throw new RuntimeException("Cannot delete row/column outside the bounds of this matrix");
    }

    Double[][] newData = new Double[width -1][height - 1];

    int oldRowOffset = 0;
    for (int row = 0; row < height - 1; row++) {
      if (row == rowToRemove) {
        oldRowOffset = 1; // skip over this column
      }

      int oldColOffset = 0;
      for (int col = 0; col < width - 1; col++) {
        if (col == colToRemove) {
          oldColOffset = 1; // skip over this column
        }

        Double thisCell = this.get(row + oldRowOffset, col + oldColOffset);
        newData[row][col] = thisCell;
      }
    }

    return MatrixFactory.generateStandardMatrix(newData);
  }

  public final Double minor(int row, int col) {
    return submatrix(row, col).determinant();
  }

  public final Double cofactor(int row, int col) {
    Double minor = minor(row, col);

    if ((row + col) % 2 == 0) {
      return minor;
    } else {
      return -minor;
    }
  }

  public final boolean isInvertible() {
    return determinant() != 0;
  }

  public final Matrix invert() {
    if (! isInvertible()) {
      throw new RuntimeException("This matrix cannot be inverted");
    }

    double determinant = determinant();

    Double[][] newData = new Double[width][height];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {

        double cofac = cofactor(row, col);

        // note that "col, row" here, instead of "row, col",
        // accomplishes the transpose operation!
        double inverted = cofac / determinant;
        newData[col][row] = inverted;
      }
    }

    return MatrixFactory.generateStandardMatrix(newData);
  }

  public abstract Matrix getIdentity();

  public abstract Matrix multiply(Matrix other);

  public abstract Tuple multiply(Tuple tuple);

  public abstract double determinant();

  protected final double determinantForNon2XMatrices() {
    double total = 0d;

    for (int col = 0; col < width; col++) {
      double element = get(0, col);
      double cofactor = cofactor(0, col);
      total += (element * cofactor);
    }

    return total;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int col = 0; col < height; col++) {
      sb.append("{");
      for (int row = 0; row < width; row++) {
        sb.append(get(row, col));

        if (row + 1 < width) {
          sb.append(",");
        }
      }
      sb.append("}");
      if (col + 1 < height) {
        sb.append("\n");
      }
    }

    return sb.toString();
  }
}
