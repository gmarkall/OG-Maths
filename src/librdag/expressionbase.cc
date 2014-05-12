/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */

#include <iostream>
#include "expressionbase.hh"
#include "expression.hh"
#include "terminal.hh"
#include "exceptions.hh"

using namespace std;

namespace librdag
{

/*
 * OGExpr
 */

OGExpr::OGExpr() {}

OGExpr::~OGExpr() {}

const ArgContainer&
OGExpr::getArgs() const
{
  return _args;
}

size_t
OGExpr::getNArgs() const
{
  return _args.size();
}

void
OGExpr::accept(Visitor& v) const
{
  v.visit(this);
}

const OGExpr*
OGExpr::asOGExpr() const
{
  return this;
}

RegContainer&
OGExpr::getRegs() const
{
  return _regs;
}

void OGExpr::debug_print() const
{
  cout << "OGExpr::debug_print()" << std::endl;
}


/**
 * Things that extend OGExpr
 */

OGUnaryExpr::OGUnaryExpr(pOGNumeric arg): OGExpr{}
{
  if (arg == nullptr)
  {
    throw rdag_error("Null operand passed to unary expression");
  }
  _args.push_back(arg);
}

OGBinaryExpr::OGBinaryExpr(pOGNumeric arg0, pOGNumeric arg1): OGExpr{}
{
  if (arg0 == nullptr)
  {
    throw rdag_error("Null operand passed to binary expression in arg0");
  }
  if (arg1 == nullptr)
  {
    throw rdag_error("Null operand passed to binary expression in arg1");
  }
  _args.push_back(arg0);
  _args.push_back(arg1);
}

/**
 * Non autogenerated nodes
 */

/**
 * COPY node
 */

COPY::COPY(pOGNumeric arg): OGUnaryExpr{arg} {}

pOGNumeric
COPY::copy() const
{
  return pOGNumeric{new COPY(_args[0]->copy())};
}

const COPY*
COPY::asCOPY() const
{
  return this;
}

void
COPY::debug_print() const
{
        cout << "COPY base class" << endl;
}

ExprType_t
COPY::getType() const
{
  return COPY_ENUM;
}

/**
 * SELECTRESULT node
 */
SELECTRESULT::SELECTRESULT(pOGNumeric arg0, pOGNumeric arg1): OGExpr{}
{
  if (arg0 == nullptr)
  {
    throw rdag_error("Null operand passed to binary expression in arg0");
  }
  if (arg1 == nullptr)
  {
    throw rdag_error("Null operand passed to binary expression in arg1");
  }
  if (arg1->getType() != INTEGER_SCALAR_ENUM)
  {
    throw rdag_error("Second argument of SelectResult is not an integer");
  }
  _args.push_back(arg0);
  _args.push_back(arg1);
}

pOGNumeric
SELECTRESULT::copy() const
{
  return pOGNumeric{new SELECTRESULT(_args[0]->copy(), _args[1]->copy())};
}

const SELECTRESULT*
SELECTRESULT::asSELECTRESULT() const
{
  return this;
}

void
SELECTRESULT::debug_print() const
{
        printf("SELECTRESULT base class\n");
}

ExprType_t
SELECTRESULT::getType() const
{
  return SELECTRESULT_ENUM;
}


/**
 * NORM2 node
 */

NORM2::NORM2(pOGNumeric arg): OGUnaryExpr{arg} {}

pOGNumeric
NORM2::copy() const
{

  return pOGNumeric{new NORM2(_args[0]->copy())};
}

const NORM2*
NORM2::asNORM2() const
{
  return this;
}

void
NORM2::debug_print() const
{
        cout << "NORM2 base class" << endl;
}

ExprType_t
NORM2::getType() const
{
  return NORM2_ENUM;
}

/**
 * PINV node
 */

PINV::PINV(pOGNumeric arg): OGUnaryExpr{arg} {}

pOGNumeric
PINV::copy() const
{

  return pOGNumeric{new PINV(_args[0]->copy())};
}

const PINV*
PINV::asPINV() const
{
  return this;
}

void
PINV::debug_print() const
{
        cout << "PINV base class" << endl;
}

ExprType_t
PINV::getType() const
{
  return PINV_ENUM;
}


/**
 * TRANSPOSE node
 */

TRANSPOSE::TRANSPOSE(pOGNumeric arg): OGUnaryExpr{arg} {}

pOGNumeric
TRANSPOSE::copy() const
{

  return pOGNumeric{new TRANSPOSE(_args[0]->copy())};
}

const TRANSPOSE*
TRANSPOSE::asTRANSPOSE() const
{
  return this;
}

void
TRANSPOSE::debug_print() const
{
        cout << "TRANSPOSE base class" << endl;
}

ExprType_t
TRANSPOSE::getType() const
{
  return TRANSPOSE_ENUM;
}


/**
 * CTRANSPOSE node
 */

CTRANSPOSE::CTRANSPOSE(pOGNumeric arg): OGUnaryExpr{arg} {}

pOGNumeric
CTRANSPOSE::copy() const
{

  return pOGNumeric{new CTRANSPOSE(_args[0]->copy())};
}

const CTRANSPOSE*
CTRANSPOSE::asCTRANSPOSE() const
{
  return this;
}

void
CTRANSPOSE::debug_print() const
{
        cout << "CTRANSPOSE base class" << endl;
}

ExprType_t
CTRANSPOSE::getType() const
{
  return CTRANSPOSE_ENUM;
}

/**
 * SVD node
 */

SVD::SVD(pOGNumeric arg): OGUnaryExpr{arg} {}

pOGNumeric
SVD::copy() const
{
  return pOGNumeric{new SVD(_args[0]->copy())};
}

const SVD*
SVD::asSVD() const
{
  return this;
}

void
SVD::debug_print() const
{
  cout << "SVD node" << endl;
}

ExprType_t
SVD::getType() const
{
  return SVD_ENUM;
}

/**
 * LU node
 */

LU::LU(pOGNumeric arg): OGUnaryExpr{arg} {}

pOGNumeric
LU::copy() const
{
  return pOGNumeric{new LU(_args[0]->copy())};
}

const LU*
LU::asLU() const
{
  return this;
}

void
LU::debug_print() const
{
  cout << "LU node" << endl;
}

ExprType_t
LU::getType() const
{
  return LU_ENUM;
}


/**
 * MTIMES node
 */

MTIMES::MTIMES(pOGNumeric arg0, pOGNumeric arg1): OGBinaryExpr{arg0, arg1} {}

pOGNumeric
MTIMES::copy() const
{
  return pOGNumeric{new MTIMES(_args[0]->copy(), _args[1]->copy())};
}

const MTIMES*
MTIMES::asMTIMES() const
{
  return this;
}

void
MTIMES::debug_print() const
{
        cout << "MTIMES base class" << endl;
}

ExprType_t
MTIMES::getType() const
{
  return MTIMES_ENUM;
}

} // namespace librdag
