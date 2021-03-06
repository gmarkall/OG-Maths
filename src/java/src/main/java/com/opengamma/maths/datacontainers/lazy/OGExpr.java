/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

package com.opengamma.maths.datacontainers.lazy;

import com.opengamma.maths.datacontainers.OGNumeric;
import com.opengamma.maths.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.nodes.NODE;

/**
 * Holds a late evaluated Expr
 */
public abstract class OGExpr implements NODE {

  private OGNumeric[] _args;

  public OGExpr(OGNumeric... args) {
    if (args == null) {
      throw new RuntimeException("args are null");
    }
    int len = args.length;
    _args = new OGNumeric[len];
    for (int i = 0; i < len; i++) {
      if (args[i] == null) {
        throw new RuntimeException("args are null");
      }
      _args[i] = args[i];
    }
  };

  @Override
  public OGNumeric[] getExprs() {
    return _args;
  }

  @Override
  public int getNExprs() {
    return _args.length;
  }

  @Override
  public String toString() {
    return this.getClass().getName();
  }

  public OGNumeric getArg(int index) {
    int lastarg = _args.length - 1;
    if (index > lastarg) {
      throw new MathsExceptionIllegalArgument("Index " + index + " exceeds index of last arg " + lastarg);
    }
    return _args[index];
  }

}
