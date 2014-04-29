/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.maths.datacontainers.other;

import com.opengamma.maths.datacontainers.OGNumeric;
import com.opengamma.maths.datacontainers.lazy.OGExprVariadicReturn;

/**
 * Holds the result of a LU Decomposition computation.
 */
public class OGLUResult {

  /**
   * Holds the result reference.
   */
  private OGResult _result;

  /**
   * Create a LU decomposition of some data.
   * The data, 'A', is decomposed as 'A=L*U'.
   * Where 'L' is a lower triangular matrix with unit diagonals
   * and 'U' is an upper triangular matrix.
   * @param computeNode the data on which the
   * LU decomposition shall be performed.
   */
  public OGLUResult(final OGExprVariadicReturn computeNode) {
    _result = new OGResult(computeNode);
  }

  /**
   * Gets L, the lower triangular matrix with unit diagonals.
   * @return the lower triangular matrix with unit diagonals.
   */
  public final OGNumeric getL() {
    return this._result.get(0);
  }

  /**
   * Gets U, the upper triangular matrix.
   * @return the upper triangular matrix.
   */
  public final OGNumeric getS() {
    return this._result.get(1);
  }

}
