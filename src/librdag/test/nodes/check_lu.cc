/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */


#include "gtest/gtest.h"
#include "terminal.hh"
#include "execution.hh"
#include "dispatch.hh"
#include "testnodes.hh"
#include "equals.hh"
#include "runtree.hh"

using namespace std;
using namespace librdag;
using namespace testnodes;
using ::testing::TestWithParam;
using ::testing::Values;

/*
 * Check LU node behaves correctly
 */

// ok cond 5x4
real16 rcondok5x4[20] =  {5,9,10,8,1,9,17,19,15,2,10,19,29,21,3,8,15,21,28,4};
complex16 ccondok5x4[20] = {{5.,-10.}, {9.,-18.}, {10.,-20.}, {8.,-16.}, {1.,-2.}, {9.,-18.}, {17.,-34.}, {19.,-38.}, {15.,-30.}, {2.,-4.}, {10.,-20.}, {19.,-38.}, {29.,-58.}, {21.,-42.}, {3.,-6.}, {8.,-16.}, {15.,-30.}, {21.,-42.}, {28.,-56.}, {4.,-8.}};

// singular 3x3
real16 rsingular3x3[9] = {1,10,1,2,20,2,3,30,3};
complex16 csingular3x3[9] = {{1,10},{10,100},{1,10},{2,20},{20,200},{2,20},{3,30},{30,300},{3,30}};


TEST(LUTests,CheckRealScalar)
{

  real16 value = 13e0;

  // input
  OGTerminal* M = new OGRealScalar(value);

  // answers
  OGTerminal * expectedL = new OGRealScalar(1.e0);
  OGTerminal * expectedU = new OGRealScalar(value);

  LU* lu = new LU(M);

  // computed answer pointers
  const OGTerminal * L, * U, * reconstruct;
  runtree(lu);
  L = lu->getRegs()[0]->asOGTerminal();
  U = lu->getRegs()[1]->asOGTerminal();

  // check numerical answer
  EXPECT_TRUE(L->mathsequals(expectedL,1e-14,1e-14));
  EXPECT_TRUE(U->mathsequals(expectedU,1e-14,1e-14));

  MTIMES * mt = new MTIMES(L->createOwningCopy(),U->createOwningCopy());
  runtree(mt);
  reconstruct = mt->getRegs()[0]->asOGTerminal();

  // check L*U == A
  EXPECT_TRUE(M->mathsequals(reconstruct));

  delete lu;
  delete mt;
  delete expectedU;
  delete expectedL;

  // check warn on singular input
  M = new OGRealScalar(0.e0);
  lu = new LU(M);
  runtree(lu);

  // TODO: CHECK WARNING

  delete lu;
}


TEST(LUTests,CheckRealMatrix)
{

  // answers
  OGTerminal * expectedL = new OGRealMatrix(new real16[20] {0.5,0.9,1,0.8,0.1,1,0.2,0,0.4,-0.2,0,1,0,0.0645161290322571,0.1290322580645162,0,0,0,1,0.1480519480519481},5,4,OWNER);
  OGTerminal * expectedU = new OGRealMatrix(new real16[16]{10,0,0,0,19,-0.5,0,0,29,-4.5,-6.2,0,21,-2.5,-3.4,12.4193548387096779}, 4,4, OWNER);

  // input
  OGTerminal* M = new OGRealMatrix(rcondok5x4,5,4,VIEWER);
  LU* lu = new LU(M);

  // computed answer pointers
  const OGTerminal * L, * U, * reconstruct;
  runtree(lu);
  L = lu->getRegs()[0]->asOGTerminal();
  U = lu->getRegs()[1]->asOGTerminal();

  // check numerical answer
  EXPECT_TRUE(L->mathsequals(expectedL,1e-14,1e-14));
  EXPECT_TRUE(U->mathsequals(expectedU,1e-14,1e-14));

  MTIMES * mt = new MTIMES(L->createOwningCopy(),U->createOwningCopy());
  runtree(mt);
  reconstruct = mt->getRegs()[0]->asOGTerminal();

  // check L*U == A
  EXPECT_TRUE(M->mathsequals(reconstruct));

  delete lu;
  delete mt;
  delete expectedU;
  delete expectedL;

  // check warn on singular input
  M = new OGRealMatrix(rsingular3x3,3,3,VIEWER);
  lu = new LU(M);
  runtree(lu);

  // TODO: CHECK WARNING

  delete lu;
}


TEST(LUTests,CheckComplexMatrix)
{
  // answers
  OGTerminal * expectedL = new OGRealMatrix(new real16[20] {0.5,0.9,1.,0.8,0.1,1.,0.2,0.,0.4,-0.2,0.,1.,0.,0.0645161290322568,0.1290322580645157,0.,0.,0.,1.,0.1480519480519480},5,4,OWNER);
  OGTerminal * expectedU = new OGComplexMatrix(new complex16[16]{{10,-20}, { 0,  0}, { 0,  0}, { 0,  0}, {19,-38}, {-0.5,  1}, { 0,  0}, { 0,  0}, {29,-58}, {-4.5000000000000000,  9}, {-6.2000000000000171, 12.4000000000000341}, { 0,  0}, {21,-42}, {-2.5000000000000000,  5}, {-3.4000000000000092,  6.8000000000000185}, {12.4193548387096779,-24.8387096774193559}}, 4,4, OWNER);

  // input
  OGTerminal* M = new OGComplexMatrix(ccondok5x4,5,4,VIEWER);
  LU* lu = new LU(M);

  // computed answer pointers
  const OGTerminal * L, * U, * reconstruct;
  runtree(lu);
  L = lu->getRegs()[0]->asOGTerminal();
  U = lu->getRegs()[1]->asOGTerminal();

  // check numerical answer
  EXPECT_TRUE(L->mathsequals(expectedL,1e-14,1e-14));
  EXPECT_TRUE(U->mathsequals(expectedU,1e-14,1e-14));

  MTIMES * mt = new MTIMES(L->createOwningCopy(),U->createOwningCopy());
  runtree(mt);
  reconstruct = mt->getRegs()[0]->asOGTerminal();

  // check L*U == A
  EXPECT_TRUE(M->mathsequals(reconstruct));

  delete lu;
  delete mt;
  delete expectedU;
  delete expectedL;

  // check warn on singular input
  M = new OGComplexMatrix(csingular3x3,3,3,VIEWER);
  lu = new LU(M);
  runtree(lu);

  // TODO: CHECK WARNING

  delete lu;
}