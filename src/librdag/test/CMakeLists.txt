#
# Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
#
# Please see distribution for license.
#

include_directories (${NATIVE_HEADER_DIR} ${CMAKE_SOURCE_DIR}/include ${JNIDIR} ${JNI_INCLUDE_DIRS})
SET(CMAKE_FC_FLAGS  "${CMAKE_FC_FLAGS} ${GCC_COVERAGE_COMPILE_FLAGS} -cpp" )

link_directories(${longdog_BINARY_DIR}/src/librdag})

set(TESTS check_entrypt)

# Compile and link each test and add to the list of tests
foreach(TEST ${TESTS})
  add_executable(${TEST} ${TEST}.c)
  target_link_libraries(${TEST} rdag)
  add_test(${TEST} ${TEST})
endforeach()