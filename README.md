# Num

[![Maven Central](https://img.shields.io/badge/Maven_Central-2.0.0-blue?logo=apachemaven)](https://central.sonatype.com/artifact/trade.invision/num)
[![Javadoc](https://img.shields.io/badge/javadoc-2.0.0-brightgreen)](https://javadoc.io/doc/trade.invision/num)
[![Java Version](https://img.shields.io/badge/Java_Version-25-orange?logo=java)](https://openjdk.org/projects/jdk/25)
[![GitHub License](https://img.shields.io/github/license/Petersoj/jet)](https://github.com/Petersoj/jet/blob/main/LICENSE.txt)

A Java library that abstracts the mathematical operations on real decimal numbers represented in computer memory as
floating-point binary numbers
([`Double`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Double.html)) or arbitrary-precision
decimal numbers
([`BigDecimal`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/math/BigDecimal.html)).

## Installation

For `build.gradle.kts`:

```kotlin
implementation("trade.invision:num:2.0.0")
```

For `build.gradle`:

```groovy
implementation 'trade.invision:num:2.0.0'
```

For `pom.xml`:

```xml
<dependency>
    <groupId>trade.invision</groupId>
    <artifactId>num</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Motivation

[IEEE 754](https://en.wikipedia.org/wiki/IEEE_754) floating-point binary numbers
[cannot accurately represent](https://en.wikipedia.org/wiki/Floating-point_arithmetic#Accuracy_problems)
non-integer real decimal numbers in computer memory and [FPUs](https://en.wikipedia.org/wiki/Floating-point_unit). In
applications that require accurate mathematical operations with non-integer (fractional) decimal numbers, such as
finance programs involving money transactions and currency, using
[arbitrary-precision](https://en.wikipedia.org/wiki/Arbitrary-precision_arithmetic) decimal numbers is best practice.
Using floating-point numbers (e.g. `float` or `double`) to represent currency can lead to undesirable
[inequality calculations](https://0.30000000000000004.com/),
[rounding errors](https://stackoverflow.com/a/3730040/4352701), and
[precision loss](https://ta4j.github.io/ta4j-wiki/Num.html#choosing-the-right-num-implementation). Thankfully, Java
provides the [`BigDecimal`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/math/BigDecimal.html)
class for working with arbitrary-precision decimal numbers, though it has some
[quirks](https://blogs.oracle.com/javamagazine/post/four-common-pitfalls-of-the-bigdecimal-class-and-how-to-avoid-them)
and doesn't provide many of the functions that the
[Math](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Math.html) class has for `float` and
`double` types. Additionally, mathematical operations on `BigDecimal` objects can be
[significantly slower](http://blog.vanillajava.blog/2024/11/overview-many-developers-consider.html) than the equivalent
mathematical operations on floating-point numbers. This can be problematic in applications such as quantitative finance
and algorithmic trading programs where fast execution time of mathematical operations on currency values is more
desirable and the tradeoff of worse accuracy and lower precision is worth it. Using integer types as a
[fixed-point number](https://en.wikipedia.org/wiki/Fixed-point_arithmetic) to represent a currency's minor unit is
another approach, but it can be unintuitive to work with and is still subject to
[precision loss](https://news.ycombinator.com/item?id=15811730). The side effects of using floating-point numbers for
currency may be negligible in certain contexts, such as working with relatively low precision numbers (like stock
prices) or measurements (like technical indicators). So, using floating-point numbers for currency values isn't _always_
a bad idea. Even Microsoft Excel, a program widely used in finance,
[uses floating-point numbers](https://learn.microsoft.com/en-us/office/troubleshoot/excel/floating-point-arithmetic-inaccurate-result).
You can really go back-and-forth on the tradeoffs between floating-point, fixed-point, and arbitrary-precision numbers.
Enter, the `Num` interface: an intuitive interface that allows you to focus on using currency values in your application
without constantly worrying about the underlying number representation and easily switch between various number
representations at runtime.

## The `Num` Interface

[`Num`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html), short for "number", is an
interface for performing mathematical operations on real decimal numbers. Implementations wrap a
[`Number`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Number.html) instance so that
performing mathematical operations on floating-point binary numbers
([`Double`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Double.html) via
[`DoubleNum`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/DoubleNum.html)) or
arbitrary-precision decimal numbers
([`BigDecimal`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/math/BigDecimal.html) via
[`DecimalNum`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/DecimalNum.html)) is simple. All
implementations of this interface are interoperable with each other. Operations involving different implementations will
result in a `Num` that trends towards an increase in precision. For example, subtracting a `DecimalNum` from a
`DoubleNum` will result in a `DecimalNum`. For another example, subtracting a `DecimalNum` with a context precision of
`16` from a `DecimalNum` with a context precision of `32` will result in a `DecimalNum` with a context precision of
`32`. Mathematical operations that result in `NaN`, `+Infinity`, `-Infinity`, or throw an `ArithmeticException` will
yield a [`NaNNum`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/NaNNum.html).

## Usage

To get a `DoubleNum` instance, provide a `Number` (`byte`, `short`, `int`, `long`, `float`, `double`, `BigDecimal`),
`String`, or existing `Num` to the `DoubleNum.doubleNum()` static method. Statically importing `doubleNum()` is
preferred as your code will likely look cleaner.

Getting a `DecimalNum` instance is similar to `DoubleNum`, but requires you to specify a precision and rounding mode via
a [`MathContext`](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/math/MathContext.html). Use
`DecimalNum.decimalNum(String, MathContext)` or use one of the convenience methods, such as `decimalNum64()` which
provides approximately the same precision as `double`, allowing up to 16 significant figures of precision and the same
[rounding policy](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/math/RoundingMode.html#HALF_EVEN) as
`double`. Again, statically importing `decimalNum()` is preferred as your code will likely look cleaner.

The [`NumFactory`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/NumFactory.html) interface makes
it easy to get `Num` instances from a given `Number`, `String`, or existing `Num` by simply using `NumFactory.of(...)`
instead of calling the `DoubleNum` or `DecimalNum` static methods directly. To get a `NumFactory` instance for
`DoubleNum`, use `DoubleNum.FACTORY`. To get a `NumFactory` instance for `DecimalNum`, use one of the static methods
such as `DecimalNum.decimalNum64Factory()`.

Check out the [Javadoc](https://javadoc.io/doc/trade.invision/num) for all classes and method signatures, but here's a
quick reference:

- [`add`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#add(trade.invision.num.Num))
- [`subtract`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#subtract(trade.invision.num.Num))
- [`multiply`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#multiply(trade.invision.num.Num))
- [`divide`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#divide(trade.invision.num.Num))
- [`remainder`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#remainder(trade.invision.num.Num))
- [`power`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#power(trade.invision.num.Num))
- [`square`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#square())
- [`cube`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#cube())
- [`exponential`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#exponential())
- [`nthRoot`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#nthRoot(trade.invision.num.Num))
- [`squareRoot`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#squareRoot())
- [`cubeRoot`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#cubeRoot())
- [`ln`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#ln())
- [`log10`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#log10())
- [`log2`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#log2())
- [`log`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#log(trade.invision.num.Num))
- [`absoluteValue`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#absoluteValue())
- [`negate`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#negate())
- [`reciprocal`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#reciprocal())
- [`increment`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#increment())
- [`decrement`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#decrement())
- [`floor`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#floor())
- [`ceil`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#ceil())
- [`degrees`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#degrees())
- [`radians`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#radians())
- [`pi`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#pi())
- [`e`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#e())
- [`sin`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#sin())
- [`cos`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#cos())
- [`tan`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#tan())
- [`asin`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#asin())
- [`acos`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#acos())
- [`atan`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#atan())
- [`atan2`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#atan2(trade.invision.num.Num))
- [`sinh`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#sinh())
- [`cosh`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#cosh())
- [`tanh`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#tanh())
- [`asinh`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#asinh())
- [`acosh`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#acosh())
- [`atanh`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#atanh())
- [`hypotenuse`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hypotenuse(trade.invision.num.Num))
- [`average`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#average(trade.invision.num.Num))
- [`min`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#min(trade.invision.num.Num))
- [`max`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#max(trade.invision.num.Num))
- [`clamp`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#clamp(trade.invision.num.Num,trade.invision.num.Num))
- [`integerPart`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#integerPart())
- [`fractionalPart`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#fractionalPart())
- [`round`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#round())
- [`significantFigures`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#significantFigures())
- [`mantissa`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#mantissa())
- [`exponent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#exponent())
- [`signum`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#signum())
- [`isNegative`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isNegative())
- [`isNegativeOrZero`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isNegativeOrZero())
- [`isPositive`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isPositive())
- [`isPositiveOrZero`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isPositiveOrZero())
- [`isZero`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isZero())
- [`isEqual`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isEqual(trade.invision.num.Num))
- [`isLessThan`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isLessThan(trade.invision.num.Num))
- [`isLessThanOrEqual`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isLessThanOrEqual(trade.invision.num.Num))
- [`isGreaterThan`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isGreaterThan(trade.invision.num.Num))
- [`isGreaterThanOrEqual`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isGreaterThanOrEqual(trade.invision.num.Num))
- [`isNaN`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isNaN())
- [`ifNaN`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#ifNaN(trade.invision.num.Num))
- [`ifNaNThrow`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#ifNaNThrow())
- [`unwrap`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#unwrap())
- [`toByte`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toByte())
- [`toShort`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toShort())
- [`toInt`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toInt())
- [`toLong`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toLong())
- [`toFloat`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toFloat())
- [`toDouble`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toDouble())
- [`toBigDecimal`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toBigDecimal())
- [`getContext`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#getContext())
- [`getContextPrecision`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#getContextPrecision())
- [`getContextRoundingMode`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#getContextRoundingMode())
- [`getFactory`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#getFactory())
- [`equals`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#equals(java.lang.Object))
- [`hashCode`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hashCode())
- [`toString`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toString())

## Acknowledgement

This library's `Num` interface was inspired by the `Num` interface of the excellent [ta4j](https://github.com/ta4j/ta4j)
library. There are several improvements and additions that this library's `Num` interface provides:

- Interoperability between `DoubleNum` and `DecimalNum`.
- Several more mathematical operations (e.g. trigonometry functions) via
  [Math](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/Math.html) in `DoubleNum` and via
  [big-math](https://github.com/eobermuhlner/big-math) in `DecimalNum`.
- No default precision for `DecimalNum` (see [ta4j issue](https://github.com/ta4j/ta4j/issues/1086)).
- Configurable epsilon for tolerant comparison operations (see [ta4j
  `DoubleNum`](https://github.com/ta4j/ta4j/blob/1101dbe059cda92d7dd1f86e755b0466782911d5/ta4j-core/src/main/java/org/ta4j/core/num/DoubleNum.java#L53)).
- `Number` used instead of primitive overloads.
- Documentation improvements.

Big thanks to [Eric Obermühlner](https://github.com/eobermuhlner) for the excellent
[big-math](https://github.com/eobermuhlner/big-math) library.

## Maintained by Invision

This project is maintained by [Invision](https://invision.trade). Invision enables you to automate and test your
investment and trading strategies across billions of data points in seconds using quantitative finance and algorithmic
trading programs you build on our code or no-code platform.
