// THIS CODE IS AUTOGENERATED FROM "CatchersTestAutoGenerated.jpp" via cpp -E "CatchersTestAutoGenerated.jpp"|sed -e '/^#/d' -e 's////\/\//g'Followed by Shift+Ctrl+F to make the formatting more suitable for mortals
// invokes function subst on primitives
// NULL type checks first
// Numeric type checks second
// Numeric comparison checks third
// write out functions
/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.longdog.helpers;

import org.testng.annotations.Test;

/**
 * Tests that the code that catches bad inputs or nasty conditions works. 
 * This contents is autogenerated from CatchersTestAutogenerated.jpp DOGMA release V1.
 */
@Test
public class CatchersTest {
  // invoke macros
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void booleanNullPtrTest() {
    boolean[] tmp = null;
    Catchers.catchNull(tmp);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void booleanNullPtrTestWStrTest() {
    boolean[] tmp = null;
    Catchers.catchNull(tmp, "STR");
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void booleanCatchNullFromArgListTest() {
    boolean[] tmp = null;
    Catchers.catchNullFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void ObjectNullPtrTest() {
    Object[] tmp = null;
    Catchers.catchNull(tmp);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void ObjectNullPtrTestWStrTest() {
    Object[] tmp = null;
    Catchers.catchNull(tmp, "STR");
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void ObjectCatchNullFromArgListTest() {
    Object[] tmp = null;
    Catchers.catchNullFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void shortNullPtrTest() {
    short[] tmp = null;
    Catchers.catchNull(tmp);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void shortNullPtrTestWStrTest() {
    short[] tmp = null;
    Catchers.catchNull(tmp, "STR");
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void shortCatchNullFromArgListTest() {
    short[] tmp = null;
    Catchers.catchNullFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void intNullPtrTest() {
    int[] tmp = null;
    Catchers.catchNull(tmp);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void intNullPtrTestWStrTest() {
    int[] tmp = null;
    Catchers.catchNull(tmp, "STR");
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void intCatchNullFromArgListTest() {
    int[] tmp = null;
    Catchers.catchNullFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void longNullPtrTest() {
    long[] tmp = null;
    Catchers.catchNull(tmp);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void longNullPtrTestWStrTest() {
    long[] tmp = null;
    Catchers.catchNull(tmp, "STR");
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void longCatchNullFromArgListTest() {
    long[] tmp = null;
    Catchers.catchNullFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void floatNullPtrTest() {
    float[] tmp = null;
    Catchers.catchNull(tmp);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void floatNullPtrTestWStrTest() {
    float[] tmp = null;
    Catchers.catchNull(tmp, "STR");
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void floatCatchNullFromArgListTest() {
    float[] tmp = null;
    Catchers.catchNullFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void doubleNullPtrTest() {
    double[] tmp = null;
    Catchers.catchNull(tmp);
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void doubleNullPtrTestWStrTest() {
    double[] tmp = null;
    Catchers.catchNull(tmp, "STR");
  }

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public static void doubleCatchNullFromArgListTest() {
    double[] tmp = null;
    Catchers.catchNullFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldNotBeNegativeFromArgListMinusOneTest() {
    short tmp = (short) -1;
    Catchers.catchValueShouldNotBeNegativeFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldNotBeNegativeOrZeroFromArgListZeroTest() {
    short tmp = (short) 0;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldNotBeNegativeOrZeroFromArgListMinusOneTest() {
    short tmp = (short) -1;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldNotBePositiveFromArgListPlusOneTest() {
    short tmp = (short) +1;
    Catchers.catchValueShouldNotBePositiveFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldNotBePositiveOrZeroFromArgListPlusOneTest() {
    short tmp = (short) +1;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldNotBePositiveOrZeroFromArgListZeroTest() {
    short tmp = (short) 0;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldNotBeNegativeFromArgListMinusOneTest() {
    int tmp = (int) -1;
    Catchers.catchValueShouldNotBeNegativeFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldNotBeNegativeOrZeroFromArgListZeroTest() {
    int tmp = (int) 0;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldNotBeNegativeOrZeroFromArgListMinusOneTest() {
    int tmp = (int) -1;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldNotBePositiveFromArgListPlusOneTest() {
    int tmp = (int) +1;
    Catchers.catchValueShouldNotBePositiveFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldNotBePositiveOrZeroFromArgListPlusOneTest() {
    int tmp = (int) +1;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldNotBePositiveOrZeroFromArgListZeroTest() {
    int tmp = (int) 0;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldNotBeNegativeFromArgListMinusOneTest() {
    long tmp = (long) -1;
    Catchers.catchValueShouldNotBeNegativeFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldNotBeNegativeOrZeroFromArgListZeroTest() {
    long tmp = (long) 0;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldNotBeNegativeOrZeroFromArgListMinusOneTest() {
    long tmp = (long) -1;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldNotBePositiveFromArgListPlusOneTest() {
    long tmp = (long) +1;
    Catchers.catchValueShouldNotBePositiveFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldNotBePositiveOrZeroFromArgListPlusOneTest() {
    long tmp = (long) +1;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldNotBePositiveOrZeroFromArgListZeroTest() {
    long tmp = (long) 0;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldNotBeNegativeFromArgListMinusOneTest() {
    float tmp = (float) -1;
    Catchers.catchValueShouldNotBeNegativeFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldNotBeNegativeOrZeroFromArgListZeroTest() {
    float tmp = (float) 0;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldNotBeNegativeOrZeroFromArgListMinusOneTest() {
    float tmp = (float) -1;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldNotBePositiveFromArgListPlusOneTest() {
    float tmp = (float) +1;
    Catchers.catchValueShouldNotBePositiveFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldNotBePositiveOrZeroFromArgListPlusOneTest() {
    float tmp = (float) +1;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldNotBePositiveOrZeroFromArgListZeroTest() {
    float tmp = (float) 0;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldNotBeNegativeFromArgListMinusOneTest() {
    double tmp = (double) -1;
    Catchers.catchValueShouldNotBeNegativeFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldNotBeNegativeOrZeroFromArgListZeroTest() {
    double tmp = (double) 0;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldNotBeNegativeOrZeroFromArgListMinusOneTest() {
    double tmp = (double) -1;
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldNotBePositiveFromArgListPlusOneTest() {
    double tmp = (double) +1;
    Catchers.catchValueShouldNotBePositiveFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldNotBePositiveOrZeroFromArgListPlusOneTest() {
    double tmp = (double) +1;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldNotBePositiveOrZeroFromArgListZeroTest() {
    double tmp = (double) 0;
    Catchers.catchValueShouldNotBePositiveOrZeroFromArgList(tmp, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldBeLessThanXFromArgListOneZeroTest() {
    short number = (short) 1;
    short comparedTo = (short) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldBeLessThanXFromArgListZeroZeroTest() {
    short number = (short) 0;
    short comparedTo = (short) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldBeGreaterThanXFromArgListZeroOneTest() {
    short number = (short) 0;
    short comparedTo = (short) 1;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldBeGreaterThanXFromArgListZeroZeroTest() {
    short number = (short) 0;
    short comparedTo = (short) 0;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldBeLessThanOrEqualToXFromArgListOneZeroTest() {
    short number = (short) 1;
    short comparedTo = (short) 0;
    Catchers.catchValueShouldBeLessThanOrEqualToXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void shortcatchValueShouldBeGreaterThanOrEqualToXFromArgListZeroOneTest() {
    short number = (short) 0;
    short comparedTo = (short) 1;
    Catchers.catchValueShouldBeGreaterThanOrEqualToXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldBeLessThanXFromArgListOneZeroTest() {
    int number = (int) 1;
    int comparedTo = (int) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldBeLessThanXFromArgListZeroZeroTest() {
    int number = (int) 0;
    int comparedTo = (int) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldBeGreaterThanXFromArgListZeroOneTest() {
    int number = (int) 0;
    int comparedTo = (int) 1;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldBeGreaterThanXFromArgListZeroZeroTest() {
    int number = (int) 0;
    int comparedTo = (int) 0;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldBeLessThanOrEqualToXFromArgListOneZeroTest() {
    int number = (int) 1;
    int comparedTo = (int) 0;
    Catchers.catchValueShouldBeLessThanOrEqualToXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void intcatchValueShouldBeGreaterThanOrEqualToXFromArgListZeroOneTest() {
    int number = (int) 0;
    int comparedTo = (int) 1;
    Catchers.catchValueShouldBeGreaterThanOrEqualToXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldBeLessThanXFromArgListOneZeroTest() {
    long number = (long) 1;
    long comparedTo = (long) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldBeLessThanXFromArgListZeroZeroTest() {
    long number = (long) 0;
    long comparedTo = (long) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldBeGreaterThanXFromArgListZeroOneTest() {
    long number = (long) 0;
    long comparedTo = (long) 1;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldBeGreaterThanXFromArgListZeroZeroTest() {
    long number = (long) 0;
    long comparedTo = (long) 0;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldBeLessThanOrEqualToXFromArgListOneZeroTest() {
    long number = (long) 1;
    long comparedTo = (long) 0;
    Catchers.catchValueShouldBeLessThanOrEqualToXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void longcatchValueShouldBeGreaterThanOrEqualToXFromArgListZeroOneTest() {
    long number = (long) 0;
    long comparedTo = (long) 1;
    Catchers.catchValueShouldBeGreaterThanOrEqualToXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldBeLessThanXFromArgListOneZeroTest() {
    float number = (float) 1;
    float comparedTo = (float) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldBeLessThanXFromArgListZeroZeroTest() {
    float number = (float) 0;
    float comparedTo = (float) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldBeGreaterThanXFromArgListZeroOneTest() {
    float number = (float) 0;
    float comparedTo = (float) 1;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldBeGreaterThanXFromArgListZeroZeroTest() {
    float number = (float) 0;
    float comparedTo = (float) 0;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldBeLessThanOrEqualToXFromArgListOneZeroTest() {
    float number = (float) 1;
    float comparedTo = (float) 0;
    Catchers.catchValueShouldBeLessThanOrEqualToXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void floatcatchValueShouldBeGreaterThanOrEqualToXFromArgListZeroOneTest() {
    float number = (float) 0;
    float comparedTo = (float) 1;
    Catchers.catchValueShouldBeGreaterThanOrEqualToXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldBeLessThanXFromArgListOneZeroTest() {
    double number = (double) 1;
    double comparedTo = (double) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldBeLessThanXFromArgListZeroZeroTest() {
    double number = (double) 0;
    double comparedTo = (double) 0;
    Catchers.catchValueShouldBeLessThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldBeGreaterThanXFromArgListZeroOneTest() {
    double number = (double) 0;
    double comparedTo = (double) 1;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldBeGreaterThanXFromArgListZeroZeroTest() {
    double number = (double) 0;
    double comparedTo = (double) 0;
    Catchers.catchValueShouldBeGreaterThanXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldBeLessThanOrEqualToXFromArgListOneZeroTest() {
    double number = (double) 1;
    double comparedTo = (double) 0;
    Catchers.catchValueShouldBeLessThanOrEqualToXFromArgList(number, comparedTo, 1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public static void doublecatchValueShouldBeGreaterThanOrEqualToXFromArgListZeroOneTest() {
    double number = (double) 0;
    double comparedTo = (double) 1;
    Catchers.catchValueShouldBeGreaterThanOrEqualToXFromArgList(number, comparedTo, 1);
  }
} // endclass
