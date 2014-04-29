/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.maths.testnodes;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opengamma.maths.datacontainers.OGNumeric;
import com.opengamma.maths.datacontainers.OGTerminal;
import com.opengamma.maths.datacontainers.lazy.OGExpr;
import com.opengamma.maths.datacontainers.matrix.OGComplexDenseMatrix;
import com.opengamma.maths.datacontainers.matrix.OGRealDenseMatrix;
import com.opengamma.maths.datacontainers.other.ComplexArrayContainer;
import com.opengamma.maths.exceptions.MathsException;
import com.opengamma.maths.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.helpers.FuzzyEquals;
import com.opengamma.maths.materialisers.Materialisers;
import com.opengamma.maths.nodes.LU;
import com.opengamma.maths.nodes.MTIMES;

/**
 * A very basic numerical test of the LU decomposition.
 */
public class TestLUMaterialise {

  static OGRealDenseMatrix real_matrix = new OGRealDenseMatrix(new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } });
  static OGComplexDenseMatrix complex_matrix = new OGComplexDenseMatrix(new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } }, new double[][] { { 10, 20 }, { 30, 40 }, { 50, 60 } });

  @DataProvider
  public Object[][] realdataContainer() {
    Object[][] obj = new Object[1][3];
    obj[0][0] = new LU(real_matrix);
    obj[0][1] = new OGRealDenseMatrix(new double[][] { { 0.2000000000000000, 1.0000000000000000 }, { 0.6000000000000001, 0.4999999999999994 }, { 1.0000000000000000, 0.0000000000000000 } });
    obj[0][2] = new OGRealDenseMatrix(new double[][] { { 5.0000000000000000, 6.0000000000000000 }, { 0.0000000000000000, 0.7999999999999998 } });
    return obj;
  };

  @DataProvider
  public Object[][] complexdataContainer() {
    Object[][] obj = new Object[1][3];
    obj[0][0] = new LU(complex_matrix);
    double[][] rp, ip;
    rp = new double[][] { { 0.2000000000000000, 1.0000000000000000 }, { 0.6000000000000000, 0.5000000000000000 }, { 1.0000000000000000, 0.0000000000000000 } };
    ip = new double[][] { { 0.0000000000000000, 0.0000000000000000 }, { 0.0000000000000000, -0.0000000000000001 }, { 0.0000000000000000, 0.0000000000000000 } };
    obj[0][1] = new OGComplexDenseMatrix(rp, ip);
    rp = new double[][] { { 5.0000000000000000, 6.0000000000000000 }, { 0.0000000000000000, 0.7999999999999998 } };
    ip = new double[][] { { 50.0000000000000000, 60.0000000000000000 }, { 0.0000000000000000, 8.0000000000000000 } };
    obj[0][2] = new OGComplexDenseMatrix(rp, ip);
    return obj;
  };

  @DataProvider
  public Object[][] jointdataContainer() {
    Object[][] obj = new Object[2][];
    obj[0] = realdataContainer()[0];
    obj[1] = complexdataContainer()[0];
    return obj;
  }

  /**
   * Check LU will materialise to a double[][] correctly from a real space result.
   */
  @Test(dataProvider = "realdataContainer")
  public void materialiseToJDoubleArray(LU input, OGTerminal expectedL, OGTerminal expectedU) {
    double[][] L = Materialisers.toDoubleArrayOfArrays(input.getL());
    double[][] U = Materialisers.toDoubleArrayOfArrays(input.getU());

    if (!FuzzyEquals.ArrayFuzzyEquals(Materialisers.toDoubleArrayOfArrays(expectedL), L)) {
      throw new MathsException("L not equal. Got: " + L[0][0] + " Expected: " + expectedL.getData()[0]);
    }

    if (!FuzzyEquals.ArrayFuzzyEquals(Materialisers.toDoubleArrayOfArrays(expectedU), U)) {
      throw new MathsException("U not equal. Got: " + U[0][0] + " Expected: " + expectedU.getData()[0]);
    }
  }

  /**
   * Check LU will materialise to a {@code ComplexArrayContainer} correctly from a complex space result.
   */
  @Test(dataProvider = "complexdataContainer")
  public void materialiseToComplexArrayContainer(LU input, OGTerminal expectedL, OGTerminal expectedU) {
    ComplexArrayContainer L = Materialisers.toComplexArrayContainer(input.getL());
    ComplexArrayContainer U = Materialisers.toComplexArrayContainer(input.getU());

    if (!FuzzyEquals.ArrayFuzzyEquals(Materialisers.toComplexArrayContainer(expectedL).getReal(), L.getReal())) {
      throw new MathsException("U not equal. Got: " + L.toString() + " Expected: " + expectedL.toString());
    }

    if (!FuzzyEquals.ArrayFuzzyEquals(Materialisers.toComplexArrayContainer(expectedL).getImag(), L.getImag())) {
      throw new MathsException("U not equal. Got: " + L.toString() + " Expected: " + expectedL.toString());
    }

    if (!FuzzyEquals.ArrayFuzzyEquals(Materialisers.toComplexArrayContainer(expectedU).getReal(), U.getReal())) {
      throw new MathsException("U not equal. Got: " + U.toString() + " Expected: " + expectedU.toString());
    }

    if (!FuzzyEquals.ArrayFuzzyEquals(Materialisers.toComplexArrayContainer(expectedU).getImag(), U.getImag())) {
      throw new MathsException("U not equal. Got: " + U.toString() + " Expected: " + expectedU.toString());
    }
  }

  /**
   * Check LU will materialise to a OGTerminal correctly.
   */
  @Test(dataProvider = "jointdataContainer")
  public void materialiseToOGTerminal(LU input, OGTerminal expectedL, OGTerminal expectedU) {
    OGTerminal L = Materialisers.toOGTerminal(input.getL());
    OGTerminal U = Materialisers.toOGTerminal(input.getU());

    if (!expectedL.mathsequals(L)) {
      throw new MathsException("L not equal. Got: " + L.toString() + " Expected: " + expectedL.toString());
    }

    if (!expectedU.mathsequals(U)) {
      throw new MathsException("U not equal. Got: " + U.toString() + " Expected: " + expectedU.toString());
    }
  }

  /**
   * Check LU will reconstruct correctly. i.e. A = L*U;
   */
  @Test(dataProvider = "jointdataContainer")
  public void reconstructionTest(LU input, OGTerminal expectedL, OGTerminal expectedU) {

    OGNumeric L = input.getL();
    OGNumeric U = input.getU();

    OGTerminal reconstructedMatrix = Materialisers.toOGTerminal(new MTIMES(L, U));
    if (!Materialisers.toOGTerminal(input.getArg(0)).mathsequals(Materialisers.toOGTerminal(reconstructedMatrix))) {
      throw new MathsException("Arrays not equal. Got: " + reconstructedMatrix.toString() + "\n Expected: " + Materialisers.toOGTerminal(input.getArg(0)).toString());
    }
  }

  /**
   * Check LU will throw on access to illegal arg position.
   */
  @Test(dataProvider = "jointdataContainer", expectedExceptions = MathsExceptionIllegalArgument.class)
  public void outsideArgBound(OGExpr input, OGTerminal expectedL, OGTerminal expectedU) {
    input.getArg(1);
  }

}
