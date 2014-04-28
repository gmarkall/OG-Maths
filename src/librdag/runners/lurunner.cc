/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for licence.
 *
 */

#include "dispatch.hh"
#include "runners.hh"
#include "expression.hh"
#include "iss.hh"
#include "terminal.hh"
#include "uncopyable.hh"
#include "lapack.hh"
#include "debug.h"


/**
 *  Unit contains code for LU node runners
 */
namespace librdag {

template<typename T> void lu_dense_runner(RegContainer& reg, const OGMatrix<T>* arg)
{
  int m = arg->getRows();
  int n = arg->getCols();
  int lda = m > 1 ? m : 1;
  int minmn = m > n ? n : m;
  int mn = m * n;
  int info = 0;

  T * L = new T[mn]();
  T * U = new T[mn]();

  // copy A else it's destroyed
  T * A = new T[mn];
  std::memcpy(A, arg->getData(), sizeof(T)*mn);

  // create pivot vector
  int * ipiv = new int[minmn];

  // call lapack
  try
  {
    lapack::xgetrf(&m, &n, A, &lda, ipiv, &info);
  }
  catch (exception& e)
  {
    if(info < 0) // illegal arg
    {
      delete[] ipiv;
      delete[] A;
      delete[] U;
      delete[] L;
      throw e;
    }
    else // failed as system is singular TODO: this will end up in logs (MAT-369) and userland (MAT-370).
    {
      cerr << e.what() << std::endl;
    }
  }

  // The following is from DOGMAv1

  // extract U
  for (int i = 0; i < n; i++)
  {
    int mi = m * i;
    int ni = n * i;
    for (int j = 0; j <= i; j++)
    {
      U[ni + j] = A[mi + j];
    }
  }

  // Transpose the pivot...
  int * perm = new int[minmn];
  // turn into 0 based indexing, write 1 onto diag of A too, will need later
  for (int i = 0; i < minmn; i++)
  {
    ipiv[i] -= 1;
    A[m*i+i] = 1.e0;
  }
  // 0:minmn range vector, will be permuted in a tick
  for (int i = 0; i < minmn; i++)
  {
    perm[i] = i;
  }
  // apply permutation to range indexed vector, just walk through in order and apply the swaps
  int swp;
  for (int i = 0; i < minmn; i++)
  {
    int piv = ipiv[i]; // get pivot at index "i"
    // apply the pivot by swapping the corresponding "row" indices in the perm index vector
    if (piv != i)
    {
      swp = perm[piv];
      perm[piv] = perm[i];
      perm[i] = swp;
    }
  }

  // apply pivot during assign to L
  for (int i = 0; i < minmn; i++)
  {
    int im = i * m;
    for (int j = i; j < n; j++)
    {
      L[im + perm[j]] = A[im + j];
    }
  }
  // copy in rest of decomposed matrix
  int remaining = m - minmn;
  for (int i = 0; i < n; i++)
  {
      std::copy(&A[minmn+i*m],&A[minmn+i*m+remaining],&L[minmn+i*m]);
  }

  delete[] ipiv;
  delete[] A;
  delete[] perm;

  reg.push_back(makeConcreteDenseMatrix(L, m, n, OWNER));
  reg.push_back(makeConcreteDenseMatrix(U, n, n, OWNER));
}

void *
LURunner::run(RegContainer& reg, OGRealScalar const *arg) const
{
  // single real space lu is just l=1, u=value
  reg.push_back(new OGRealScalar(1.e0));
  if(arg->getValue()==0.e0)
  {
    cerr << "Warning: singular system detected in LU decomposition" << std::endl;
  }
  reg.push_back(new OGRealScalar(arg->getValue()));
  return nullptr;
}
void *
LURunner::run(RegContainer& reg, OGRealMatrix const *arg) const
{
  lu_dense_runner(reg, arg);
  return nullptr;
}

void *
LURunner::run(RegContainer& reg, OGComplexMatrix const *arg) const
{
  lu_dense_runner(reg, arg);
  return nullptr;
}

} // end namespace
