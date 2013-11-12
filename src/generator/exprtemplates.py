#
# Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
#
# Please see distribution for license.
#

# Expressions header

expression_hh = """\
/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 *
 * This file is autogenerated during the DOGMA2 build process - src/generator/generator.py
 */

#ifndef _EXPRESSION_HH
#define _EXPRESSION_HH

// bindings
#include <iostream>
#include <cstring>
#include <cstdlib>
#include <vector>
#include <memory>

#include "expressionbase.hh"
#include "numeric.hh"
#include "visitor.hh"
#include "exceptions.hh"
#include "containers.hh"

using namespace std;


/**
 * The namespace for the DAG library
 */
namespace librdag
{

%(expression_classes)s

} // namespace librdag

#endif
"""

expr_class = """\
class %(classname)s: public %(parentclass)s
{
  public:
    %(classname)s(ArgContainer *args);
    virtual OGNumeric* copy() const override;
    virtual const %(classname)s* as%(classname)s() const override;
    virtual void debug_print() const override;
    virtual ExprType_t getType() const override;
};
"""

# Expressions cc

expression_cc = """\
/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 *
 * This file is autogenerated during the DOGMA2 build process - src/generator/generator.py
 */

#include <iostream>
#include "expression.hh"
#include "terminal.hh"
#include "exceptions.hh"
#include "exprtypeenum.h"

using namespace std;

namespace librdag
{

%(expression_methods)s

} // namespace librdag
"""

expr_methods = """\
/**
 * %(classname)s node
 */

%(classname)s::%(classname)s(ArgContainer* args): %(parentclass)s(args) {}

OGNumeric*
%(classname)s::copy() const
{
  return new %(classname)s(this->getArgs()->copy());
}

const %(classname)s*
%(classname)s::as%(classname)s() const
{
  return this;
}

void
%(classname)s::debug_print() const
{
        cout << "%(classname)s class" << endl;
}

ExprType_t
%(classname)s::getType() const
{
  return %(classname)s_ENUM;
}

"""

# Numeric header file

numeric_hh = """\
/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 *
 * This file is autogenerated during the DOGMA2 build process - src/generator/generator.py
 */

#ifndef _NUMERIC_HH
#define _NUMERIC_HH

#include "uncopyable.hh"
#include "exprtypeenum.h"

namespace librdag {

// fwd decl
class OGTerminal;
class OGRealScalar;
class OGComplexScalar;
class OGIntegerScalar;
class OGRealMatrix;
class OGComplexMatrix;
class OGLogicalMatrix;
class OGRealDiagonalMatrix;
class OGComplexDiagonalMatrix;
class OGRealSparseMatrix;
class OGComplexSparseMatrix;
class OGOwningRealMatrix;
class OGOwningComplexMatrix;
class ConvertTo;
class Visitor;
class OGExpr;
class COPY;
class SELECTRESULT;
%(fwd_decls)s

/*
 * Base class for absolutely everything!
 */
class OGNumeric: private Uncopyable
{
  public:
    virtual ~OGNumeric();
    virtual void debug_print() const = 0;
    virtual void accept(Visitor &v) const = 0;
    virtual OGNumeric* copy() const = 0;
    virtual const OGExpr* asOGExpr() const;
    virtual const COPY* asCOPY() const;
    virtual const SELECTRESULT* asSELECTRESULT() const;
%(cast_methods)s
    virtual const OGTerminal* asOGTerminal() const;
    virtual const OGRealScalar* asOGRealScalar() const;
    virtual const OGComplexScalar* asOGComplexScalar() const;
    virtual const OGIntegerScalar* asOGIntegerScalar() const;
    virtual const OGRealMatrix* asOGRealMatrix() const;
    virtual const OGComplexMatrix* asOGComplexMatrix() const;
    virtual const OGLogicalMatrix* asOGLogicalMatrix() const;
    virtual const OGRealDiagonalMatrix* asOGRealDiagonalMatrix() const;
    virtual const OGComplexDiagonalMatrix* asOGComplexDiagonalMatrix() const;
    virtual const OGRealSparseMatrix* asOGRealSparseMatrix() const;
    virtual const OGComplexSparseMatrix* asOGComplexSparseMatrix() const;
    virtual ExprType_t getType() const;
};

}
#endif // _NUMERIC_HH
"""

numeric_fwd_decl = """\
class %(classname)s;
"""

numeric_cast_method =  """\
    virtual const %(classname)s* as%(classname)s() const;
"""

# Numeric cc file

numeric_cc = """\
/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 *
 * This file is autogenerated during the DOGMA2 build process - src/generator/generator.py
 */

#include <iostream>
#include "numeric.hh"
#include "expression.hh"
#include "terminal.hh"

using namespace std;

namespace librdag
{

/*
 * Autogenerated OGNumeric methods
 */

%(numeric_methods)s

} // namespace librdag
"""

numeric_method = """\
const %(classname)s*
OGNumeric::as%(classname)s() const
{
  return nullptr;
}
"""
