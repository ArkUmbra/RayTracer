package com.arkumbra.raytracer.datastructure;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import com.arkumbra.raytracer.matrices.Matrix;
import com.arkumbra.raytracer.matrices.Matrix2;
import com.arkumbra.raytracer.matrices.Matrix3;
import com.arkumbra.raytracer.matrices.Matrix4;
import com.arkumbra.raytracer.geometry.Tuple;
import org.junit.Test;

public class MatrixTest {
  
  private static final Double DELTA = 0.0001d;


  @Test
  public void testBuild4x4Matrix() {
    Matrix matrix = new Matrix4(new Double[][]{
        {1d,2d,3d,4d},
        {5.5, 6.5, 7.5, 8.5},
        {9d, 10d, 11d, 12d},
        {13.5, 14.5, 15.5, 16.5}
    });

    assertEquals(1d, matrix.get(0,0), DELTA);
    assertEquals(4d, matrix.get(0,3), DELTA);
    assertEquals(5.5, matrix.get(1,0), DELTA);
    assertEquals(7.5, matrix.get(1,2), DELTA);
    assertEquals(11d, matrix.get(2, 2), DELTA);
    assertEquals(13.5, matrix.get(3,0), DELTA);
    assertEquals(15.5, matrix.get(3,2), DELTA);

  }

  @Test
  public void testTwoMatricesAreEqual() {
    Matrix matrix = new Matrix4(new Double[][]{
        {1d,2d,3d,4d},
        {5.5, 6.5, 7.5, 8.5},
        {9d, 10d, 11d, 12d},
        {13.5, 14.5, 15.5, 16.5}
    });

    Matrix matrixB = new Matrix4(new Double[][]{
        {1d,2d,3d,4d},
        {5.5, 6.5, 7.5, 8.5},
        {9d, 10d, 11d, 12d},
        {13.5, 14.5, 15.5, 16.5}
    });

    assertEquals(matrix, matrixB);
  }

  @Test
  public void testTwoMatricesAreNotEqual() {
    Matrix matrix = new Matrix4(new Double[][]{
        {1d,2d,3d,4d},
        {5.5, 6.5, 7.5, 8.5},
        {9d, 10d, 11d, 12d},
        {13.5, 14.5, 15.5, 16.5}
    });

    Matrix matrixB = new Matrix4(new Double[][]{
        {1d,2d,3d,4d},
        {5.5, 6.5, 7.5, 8.5},
        {9d, 10d, 11d, 12d},
        {13.5, 14.5, 15.5, 1000.5}
    });

    assertNotEquals(matrix, matrixB);
  }


  /*
  Given the following matrix A:
      |1|2|3|4|
      |5|6|7|8|
      |9|8|7|6|
      |5|4|3|2|
  And the following matrix B: |
     -2|1|2| 3|
      3|2|1|-1|
    | 4|3|6| 5|
    | 1|2|7| 8|
  Then A * B is the following 4x4 matrix:
    |20| 22| 50| 48|
    |44| 54|114|108|
    |40| 58|110|102|
    |16| 26| 46| 42|
   */
  @Test
  public void testMultiplyTwoMatrices() {
    Matrix matrix = new Matrix4(new Double[][]{
        {1d,2d,3d,4d},
        {5d,6d,7d,8d},
        {9d,8d,7d,6d},
        {5d,4d,3d,2d}
    });

    Matrix matrixB = new Matrix4(new Double[][]{
        {-2d,1d,2d,3d},
        {3d,2d,1d,-1d},
        {4d,3d,6d,5d},
        {1d,2d,7d,8d}
    });

    Matrix expected = new Matrix4(new Double[][]{
        {20d,22d,50d,48d},
        {44d,54d,114d,108d},
        {40d,58d,110d,102d},
        {16d,26d,46d,42d}
    });

    assertEquals(expected, matrix.multiply(matrixB));
  }

  /*
  Scenario: A matrix multiplied by a tuple
  Given the following matrix A:
    |1|2|3|4|
    |2|4|4|2|
    |8|6|4|1|
    |0|0|0|1|
  And b ← tuple(1, 2, 3, 1)
  Then A * b = tuple(18, 24, 33, 1)
   */
  @Test
  public void testMultiplyMatrixByTuple() {
    Matrix matrix = new Matrix4(new Double[][]{
        {1d,2d,3d,4d},
        {2d,4d,4d,2d},
        {8d,6d,4d,1d},
        {0d,0d,0d,1d}
    });

    Tuple tuple = new Tuple(1d,2d,3d,1d);
    Tuple expected = new Tuple(18d,24d,33d,1d);

    assertEquals(expected, matrix.multiply(tuple));
  }


  /*
  Scenario: Multiplying a matrix by the identity matrix
  Given the following matrix A:
    |0|1| 2| 4|
    |1|2| 4| 8|
    |2|4| 8|16|
    |4|8|16|32|
  Then A * identity_matrix = A
  */
  @Test
  public void testMultiplyMatrixeByIdentityMatrixRetainsSameValue() {
    Matrix matrix = new Matrix4(new Double[][]{
        {0d,1d,2d,4d},
        {1d,2d,4d,8d},
        {2d,4d,8d,16d},
        {4d,8d,16d,32d}
    });

    assertEquals(matrix, matrix.multiply(matrix.getIdentity()));
  }

  /*
  Scenario: Multiplying the identity matrix by a tuple
    Given a ← tuple(1, 2, 3, 4)
    Then identity_matrix * a = a
   */
  @Test
  public void testMultiplyTupleByIdentityMatrixRetainsSameValue() {
    Tuple tuple = new Tuple(1d,2d,3d,4d);

    assertEquals(tuple, Matrix4.IDENTITY.multiply(tuple));
  }

  /*
  Scenario: Transposing a matrix Given the following matrix A:
    |0|9|3|0|
    |9|8|0|8|
    |1|8|5|3|
    |0|0|5|8|
  Then transpose(A) is the following matrix:
    |0|9|1|0|
    |9|8|8|0|
    |3|0|5|5|
    |0|8|3|8|
   */
  @Test
  public void testTransposeMatrix() {
    Matrix matrix = new Matrix4(new Double[][]{
        {0d,9d,3d,0d},
        {9d,8d,0d,8d},
        {1d,8d,5d,3d},
        {0d,0d,5d,8d}
    });
    Matrix expected = new Matrix4(new Double[][]{
        {0d,9d,1d,0d},
        {9d,8d,8d,0d},
        {3d,0d,5d,5d},
        {0d,8d,3d,8d}
    });

    assertEquals(expected, matrix.transpose());
  }

  /**
   * Scenario: Transposing the identity matrix
   * Given A ← transpose(identity_matrix)
   * Then A = identity_matrix
   */
  @Test
  public void testTransposingIdentityMatrixReturnsSame() {
    assertEquals(Matrix4.IDENTITY, Matrix4.IDENTITY.transpose());
  }


  /**
   * Scenario: Calculating the determinant of a 2x2 matrix Given the following 2x2 matrix A:
   * | 1  | 5 |
   * | -3 | 2 |
   * Then determinant(A) = 17
   */
  @Test
  public void testFindDeterminantOf2x2Matrix() {
    Matrix matrix = new Matrix2(new Double[][]{
        {1d,5d},
        {-3d,2d}
    });

    double expected = 17d;
    assertEquals(expected, matrix.determinant(), DELTA);
  }

  /**
   * Scenario: A submatrix of a 3x3 matrix is a 2x2 matrix
   * Given the following 3x3 matrix A:
      | 1|5| 0|
      |-3|2| 7|
      | 0|6|-3|
   Then submatrix(A, 0, 2) is the following 2x2 matrix:
      | -3 | 2 |
      | 0  | 6 |
   */
  @Test
  public void testFindSubmatrixOf3x3Matrix() {
    Matrix matrixA = new Matrix3(new Double[][]{
        {1d, 5d, 8d},
        {-3d,2d, 7d},
        {0d, 6d, -3d}
    });

    Matrix expected = new Matrix2(new Double[][]{
        {-3d,2d},
        {0d, 6d}
    });

    assertEquals(expected, matrixA.submatrix(0, 2));
  }




  /**
   *    Scenario: A submatrix of a 4x4 matrix is a 3x3 matrix
   *    Given the following 4x4 matrix A:
          |-6| 1| 1| 6|
          |-8| 5| 8| 6|
          |-1| 0| 8| 2|
          |-7| 1|-1| 1|
        Then submatrix(A, 2, 1) is the following 3x3 matrix:
          |-6 | 1 | 6|
          |-8 | 8 | 6|
          | -7| -1| 1 |
   */
  @Test
  public void testFindSubmatrixOf4x4Matrix() {
    Matrix matrixA = new Matrix4(new Double[][]{
        {-6d, 1d, 1d, 6d},
        {-8d, 5d, 9d, 4d},
        {-1d, 0d, 8d, 2d},
        {-7d, 1d, -1d, 1d}
    });

    Matrix expected = new Matrix3(new Double[][]{
        {-6d, 1d, 6d},
        {-8d, 9d, 4d},
        {-7d, -1d, 1d}
    });

    assertEquals(expected, matrixA.submatrix(2, 1));
  }

  /*
    Scenario: Calculating a minor of a 3x3 matrix
    Given the following 3x3 matrix A:
      | 3| 5| 0|
      | 2|-1|-7|
      | 6|-1| 5|
    And B ← submatrix(A, 1, 0)

    Then determinant(B) = 25
    And minor(A, 1, 0) = 25
   */
  @Test
  public void testFindMinorOfMatrix() {
    Matrix matrixA = new Matrix3(new Double[][]{
      {3d, 5d, 0d},
      {2d, -1d, -7d},
      {6d, -1d, 5d}
    });
    Matrix matrixB = matrixA.submatrix(1, 0);
    double expectedDeterminant = 25d;

    assertEquals(expectedDeterminant, matrixB.determinant(), DELTA);
    assertEquals(expectedDeterminant, matrixA.minor(1, 0), DELTA);
  }

  /*
  Scenario: Calculating a cofactor of a 3x3 matrix
    Given the following 3x3 matrix A:
      |3 | 5| 0|
      | 2|-1|-7|
      | 6|-1| 5|
    Then minor(A, 0, 0) = -12
      And cofactor(A, 0, 0) = -12 
      And minor(A, 1, 0) = 25
      And cofactor(A, 1, 0) = -25
   */
  @Test
  public void testComputeCofactorOfMatrix() {
    Matrix matrixA = new Matrix3(new Double[][]{
        {3d, 5d, 0d},
        {2d, -1d, -7d},
        {6d, -1d, 5d}
    });
    
    assertEquals(-12, matrixA.minor(0, 0), DELTA);
    assertEquals(-12, matrixA.cofactor(0, 0), DELTA);
    assertEquals(25, matrixA.minor(1, 0), DELTA);
    assertEquals(-25, matrixA.cofactor(1, 0), DELTA);
  }


  /*
  Scenario: Calculating the determinant of a 3x3 matrix
    Given the following 3x3 matrix A:
      | 1| 2| 6|
      |-5| 8|-4|
      | 2| 6| 4|
    Then cofactor(A, 0, 0) = 56
      And cofactor(A, 0, 1) = 12
      And cofactor(A, 0, 2) = -46
      And determinant(A) = -196
  */
  @Test
  public void testCalculateDeterminantOf3x3Matrix() {
    Matrix matrixA = new Matrix3(new Double[][]{
        {1d, 2d, 6d},
        {-5d, 8d, -4d},
        {2d, 6d, 4d}
    });

    assertEquals(56, matrixA.cofactor(0, 0), DELTA);
    assertEquals(12, matrixA.cofactor(0, 1), DELTA);
    assertEquals(-46, matrixA.cofactor(0, 2), DELTA);
    assertEquals(-196, matrixA.determinant(), DELTA);
  }


  /*Scenario: Calculating the determinant of a 4x4 matrix
      Given the following 4x4 matrix A:
        |-2|-8| 3| 5|
        |-3| 1| 7| 3|
        | 1| 2|-9| 6|
        |-6| 7| 7|-9|
      Then cofactor(A, 0, 0) = 690
        And cofactor(A, 0, 1) = 447
        And cofactor(A, 0, 2) = 210
        And cofactor(A, 0, 3) = 51
        And determinant(A) = -4071
  */
  @Test
  public void testCalculateDeterminantOf4x4Matrix() {
    Matrix matrixA = new Matrix4(new Double[][]{
        {-2d, -8d, 3d, 5d},
        {-3d, 1d, 7d, 3d},
        {1d, 2d, -9d, 6d},
        {-6d, 7d, 7d, -9d}
    });

    assertEquals(690, matrixA.cofactor(0, 0), DELTA);
    assertEquals(447, matrixA.cofactor(0, 1), DELTA);
    assertEquals(210, matrixA.cofactor(0, 2), DELTA);
    assertEquals(51, matrixA.cofactor(0, 3), DELTA);
    assertEquals(-4071, matrixA.determinant(), DELTA);
  }

  /*
  Scenario: Testing an invertible matrix for invertibility
    Given the following 4x4 matrix A:
      | 6| 4| 4| 4|
      | 5| 5| 7| 6|
      | 4|-9| 3|-7|
      | 9| 1| 7|-6|
    Then determinant(A) = -2120
      And A is invertible
   */
  @Test
  public void testMatrixIsInvertible() {
    Matrix matrixA = new Matrix4(new Double[][]{
        {6d, 4d, 4d, 4d},
        {5d, 5d, 7d, 6d},
        {4d, -9d, 3d, -7d},
        {9d, 1d, 7d, -6d}
    });

    assertEquals(-2120d, matrixA.determinant(), DELTA);
    assertTrue(matrixA.isInvertible());
  }

  /*
  Scenario: Testing a noninvertible matrix for invertibility
    Given the following 4x4 matrix A:
      |-4| 2|-2|-3|
      | 9| 6| 2| 6|
      | 0|-5| 1|-5|
      | 0| 0| 0| 0|
    Then determinant(A) = 0
      And A is not invertible
   */
  @Test
  public void testMatrixIsNotInvertible() {
    Matrix matrixA = new Matrix4(new Double[][]{
        {-4d, -2d, 2d, -3d},
        {9d, 6d, 2d, 6d},
        {0d, -5d, 1d, -5d},
        {0d, 0d, 0d, 0d}
    });

    assertEquals(-0d, matrixA.determinant(), DELTA);
    assertFalse(matrixA.isInvertible());
  }

  /*
  Scenario: Calculating the inverse of a matrix
    Given the following 4x4 matrix A:
      |-5| 2| 6|-8|
      | 1|-5| 1| 8|
      | 7| 7|-6|-7|
      | 1|-3| 7| 4|
    And B ← inverse(A)

    Then determinant(A) = 532
      And cofactor(A, 2, 3) = -160
      And B[3,2] = -160/532
      And cofactor(A, 3, 2) = 105
      And B[2,3] = 105/532
      And B is the following 4x4 matrix:
        |  0.21805 | 0.45113  |  0.24060 |-0.04511 |
        | -0.80827 | -1.45677 | -0.44361 | 0.52068 |
        | -0.07895 | -0.22368 | -0.05263 | 0.19737 |
        | -0.52256 | -0.81391 | -0.30075 | 0.30639 |
   */
  @Test
  public void testCalculateInverseOfMatrix() {
    Matrix matrixA = new Matrix4(new Double[][]{
        {-5d, 2d, 6d, -8d},
        {1d, -5d, 1d, 8d},
        {7d, 7d, -6d, -7d},
        {1d, -3d, 7d, 4d}
    });

    Matrix expectedInverse = new Matrix4(new Double[][]{
        {0.21805d, 0.45113d, 0.24060d, -0.04511d},
        {-0.80827d, -1.45677d, -0.44361d, 0.52068d},
        {-0.07895d, -0.22368d, -0.05263d, 0.19737d},
        {-0.52256d, -0.81391d, -0.30075d, 0.30639d}
    });


    Matrix matrixB = matrixA.invert();

    assertEquals(-775, matrixA.cofactor(1,1), DELTA);
    assertEquals(532d, matrixA.determinant(), DELTA);
    assertEquals(-160d, matrixA.cofactor(2,3), DELTA);
    assertEquals(-160d/532d, matrixB.get(3, 2), DELTA);
    assertEquals(105, matrixA.cofactor(3, 2), DELTA);
    assertEquals(105d/532d, matrixB.get(2, 3), DELTA);
    assertEquals(expectedInverse, matrixB);
  }

  /*
  Scenario: Calculating the inverse of another matrix
    Given the following 4x4 matrix A:
      | 8|-5| 9| 2|
      | 7| 5| 6| 1|
      |-6| 0| 9| 6|
      |-3| 0|-9|-4|
    Then inverse(A) is the following 4x4 matrix:
      | -0.15385 | -0.15385 | -0.28205 | -0.53846 |
      | -0.07692 | 0.12308 | 0.02564 | 0.03077 |
      | 0.35897 | 0.35897 | 0.43590 | 0.92308 |
      | -0.69231 | -0.69231 | -0.76923 | -1.92308 |
   */
  @Test
  public void testCalculateInverseOfAnotherMatrix() {
    Matrix matrixA = new Matrix4(new Double[][]{
        {8d, -5d, 9d, 2d},
        {7d, 5d, 6d, 1d},
        {-6d, 0d, 9d, 6d},
        {-3d, 0d, -9d, -4d}
    });

    Matrix expectedInverse = new Matrix4(new Double[][]{
        {-0.15385, -0.15385, -0.28205, -0.53846},
        {-0.07692, 0.12308, 0.02564, 0.03077},
        {0.35897, 0.35897, 0.43590, 0.92308},
        {-0.69231, -0.69231, -0.76923, -1.92308}
    });


    Matrix matrixB = matrixA.invert();
    assertEquals(expectedInverse, matrixB);
  }


  /*
  Scenario: Calculating the inverse of a third matrix
    Given the following 4x4 matrix A:
      | 9| 3| 0| 9|
      |-5|-2|-6|-3|
      |-4| 9| 6| 4|
      |-7| 6| 6| 2|
    Then inverse(A) is the following 4x4 matrix:
      | -0.04074 | -0.07778 | 0.14444 | -0.22222 |
      | -0.07778 | 0.03333 | 0.36667 | -0.33333 |
      | -0.02901 | -0.14630 | -0.10926 | 0.12963 |
      | 0.17778 | 0.06667 | -0.26667 | 0.33333 |
   */
  @Test
  public void testCalculateInverseOfThirdMatrix() {
    Matrix matrixA = new Matrix4(new Double[][]{
        {9d, 3d, 0d, 9d},
        {-5d, -2d, -6d, -3d},
        {-4d, 9d, 6d, 4d},
        {-7d, 6d, 6d, 2d}
    });

    Matrix expectedInverse = new Matrix4(new Double[][]{
        {-0.04074, -0.07778, 0.14444, -0.22222},
        {-0.07778, 0.03333, 0.36667, -0.33333},
        {-0.02901, -0.14630, -0.10926, 0.12963},
        {0.17778, 0.06667, -0.26667, 0.33333}
    });


    Matrix matrixB = matrixA.invert();
    assertEquals(expectedInverse, matrixB);
  }

  /*
  Scenario: Multiplying a product by its inverse
    Given the following 4x4 matrix A:
      | 3|-9| 7| 3|
      | 3|-8| 2|-9|
      |-4| 4| 4| 1|
      |-6| 5|-1| 1|
    And the following 4x4 matrix B:
      | 8| 2| 2| 2|
      | 3|-1| 7| 0|
      | 7| 0| 5| 4|
      | 6|-2| 0| 5|
    And C ← A * B
Then C * inverse(B) = A
   */
  @Test
  public void testMultiplyMatrixProductByInverse() {
    Matrix matrixA = new Matrix4(new Double[][]{
        {3d, -9d, 7d, 3d},
        {3d, -8d, 2d, -9d},
        {-4d, 4d, 4d, 1d},
        {-6d, 5d, -1d, 1d}
    });

    Matrix matrixB = new Matrix4(new Double[][]{
        {8d, 2d, 2d, 2d},
        {3d, -1d, 7d, 0d},
        {7d, 0d, 5d, 4d},
        {6d, -2d, 0d, 5d}
    });

    Matrix matrixC = matrixA.multiply(matrixB);
    assertEquals(matrixA, matrixC.multiply(matrixB.invert()));

  }
}
