# Num

[![Maven Central Version](https://img.shields.io/maven-central/v/trade.invision/num)](https://central.sonatype.com/artifact/trade.invision/num)
[![javadoc](https://javadoc.io/badge2/trade.invision/num/javadoc.svg)](https://javadoc.io/doc/trade.invision/num)
[![GitHub License](https://img.shields.io/github/license/invision-trading/num)](https://github.com/invision-trading/num/blob/main/LICENSE.txt)

A Java library that abstracts the mathematical operations on real decimal numbers represented in computer memory as
floating-point binary numbers
([`Double`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Double.html)) or arbitrary-precision
decimal numbers
([`BigDecimal`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/math/BigDecimal.html)).

## Installation

For `build.gradle.kts`:

```kotlin
implementation("trade.invision", "num", "1.11.0")
```

For `build.gradle`:

```groovy
implementation group: 'trade.invision', name: 'num', version: '1.11.0'
```

For `pom.xml`:

```xml
<dependency>
    <groupId>trade.invision</groupId>
    <artifactId>num</artifactId>
    <version>1.11.0</version>
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
provides the [`BigDecimal`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/math/BigDecimal.html)
class for working with arbitrary-precision decimal numbers, though it has some
[quirks](https://blogs.oracle.com/javamagazine/post/four-common-pitfalls-of-the-bigdecimal-class-and-how-to-avoid-them)
and doesn't provide many of the functions that the
[Math](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Math.html) class has for `float` and
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
without worrying about the underlying number representation and easily switch between various number representations
at runtime.

## The `Num` Interface

[`Num`](src/main/java/trade/invision/num/Num.java), short for "number", is an interface for performing mathematical
operations on real decimal numbers. Implementations wrap a
[`Number`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Number.html) instance so that
performing mathematical operations on floating-point binary numbers
([`Double`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Double.html) via
[`DoubleNum`](src/main/java/trade/invision/num/DoubleNum.java)) or arbitrary-precision decimal numbers
([`BigDecimal`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/math/BigDecimal.html) via
[`DecimalNum`](src/main/java/trade/invision/num/DecimalNum.java)) is simple. Object instances of this interface are
immutable. All methods in this interface return non-`null` values or throw a `RuntimeException` (usually an
[`ArithmeticException`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/ArithmeticException.html)).
All implementations of this interface are interoperable with each other. Operations involving different
implementations will result in a `Num` that trends towards an increase in precision. For example, subtracting a
`DecimalNum` from a `DoubleNum` will result in a `DecimalNum`. For another example, subtracting a `DecimalNum` with a
context precision of `16` from a `DecimalNum` with a context precision of `32` will result in a `DecimalNum` with a
context precision of `32`. Mathematical operations that result in `NaN`, `+Infinity`, `-Infinity`, or
`ArithmeticException` will yield [`NaNNum`](src/main/java/trade/invision/num/NaNNum.java).

## Usage

To create a `DoubleNum`, provide an existing `Number` (`byte`, `short`, `int`, `long`, `float`, `double`), `BigDecimal`,
`String`, or `Num` to the `DoubleNum.doubleNum()` static method. Statically importing `doubleNum()` is preferred as your
code will likely look cleaner. Creating a `DecimalNum` is similar to `DoubleNum`, but requires you to specify a
precision and rounding mode via
[`MathContext`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/math/MathContext.html). Use
`DecimalNum.decimalNum(String, MathContext)` or use one of the convenience methods, such as `decimalNum64()` which
provides approximately the same precision as `double`, allowing up to 16 significant figures of precision and the same
[rounding policy](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/math/RoundingMode.html#HALF_EVEN) as
`double`. Again, statically importing `decimalNum()` is preferred as your code will likely look cleaner.

You can create a [`NumFactory`](src/main/java/trade/invision/num/NumFactory.java) instance to abstract the `Num`
creation process by using one of the `NumFactory` creation methods available in `DoubleNum` or `DecimalNum` (e.g.
`DecimalNum.decimalNum64Factory()`). The `NumFactory` defines the underlying number representation for new `Num`
instances, so you can create a `Num` from an existing `Number`, `BigDecimal`, `String`, or `Num` by simply using
`NumFactory.of(...)`.

For mathematical operations that have well-known abbreviations, such as `log` for `logarithm` or `atanh` for
`inverseHyperbolicTangent`, the `Num` interface provides methods for such abbreviations, also known as shorthand
methods. The shorthand methods should be preferred to the full name of the mathematical operation; the full name
exists for completeness and as a reference for the shorthand.

Check out the [Javadoc](https://javadoc.io/doc/trade.invision/num) for all classes and method signatures, but here's a
quick reference:

- [`add`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#add(trade.invision.num.Num))
- [`subtract`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#subtract(trade.invision.num.Num))
- [`multiply`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#multiply(trade.invision.num.Num))
- [`divide`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#divide(trade.invision.num.Num))
- [`remainder`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#remainder(trade.invision.num.Num)) (shorthand: `mod`)
- [`power`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#power(trade.invision.num.Num)) (shorthand: `pow`)
- [`square`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#square()) (shorthand: `sq`)
- [`cube`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#cube()) (shorthand: `cb`)
- [`exponential`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#exponential()) (shorthand: `exp`)
- [`nthRoot`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#nthRoot(trade.invision.num.Num)) (shorthand: `rt`)
- [`squareRoot`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#squareRoot()) (shorthand: `sqrt`)
- [`cubeRoot`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#cubeRoot()) (shorthand: `cbrt`)
- [`naturalLogarithm`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#naturalLogarithm()) (shorthand: `ln`)
- [`commonLogarithm`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#commonLogarithm()) (shorthand: `log10`)
- [`binaryLogarithm`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#binaryLogarithm()) (shorthand: `log2`)
- [`logarithm`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#logarithm(trade.invision.num.Num)) (shorthand: `log`)
- [`absoluteValue`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#absoluteValue()) (shorthand: `abs`)
- [`negate`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#negate()) (shorthand: `neg`)
- [`reciprocal`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#reciprocal()) (shorthand: `recip`)
- [`increment`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#increment()) (shorthand: `inc`)
- [`decrement`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#decrement()) (shorthand: `dec`)
- [`floor`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#floor())
- [`ceil`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#ceil())
- [`degrees`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#degrees()) (shorthand: `deg`)
- [`radians`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#radians()) (shorthand: `rad`)
- [`pi`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#pi())
- [`e`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#e())
- [`sine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#sine()) (shorthand: `sin`)
- [`cosine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#cosine()) (shorthand: `cos`)
- [`tangent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#tangent()) (shorthand: `tan`)
- [`inverseSine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseSine()) (shorthand: `asin`)
- [`inverseCosine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseCosine()) (shorthand: `acos`)
- [`inverseTangent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseTangent()) (shorthand: `atan`)
- [`inverseTangent2`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseTangent2(trade.invision.num.Num)) (shorthand: `atan2`)
- [`hyperbolicSine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hyperbolicSine()) (shorthand: `sinh`)
- [`hyperbolicCosine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hyperbolicCosine()) (shorthand: `cosh`)
- [`hyperbolicTangent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hyperbolicTangent()) (shorthand: `tanh`)
- [`inverseHyperbolicSine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseHyperbolicSine()) (shorthand: `asinh`)
- [`inverseHyperbolicCosine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseHyperbolicCosine()) (shorthand: `acosh`)
- [`inverseHyperbolicTangent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseHyperbolicTangent()) (shorthand: `atanh`)
- [`hypotenuse`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hypotenuse(trade.invision.num.Num)) (shorthand: `hypot`)
- [`average`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#average(trade.invision.num.Num)) (shorthand: `avg`)
- [`minimum`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#minimum(trade.invision.num.Num)) (shorthand: `min`)
- [`maximum`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#maximum(trade.invision.num.Num)) (shorthand: `max`)
- [`clamp`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#clamp(trade.invision.num.Num,trade.invision.num.Num))
- [`integerPart`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#integerPart()) (shorthand: `intPart`)
- [`fractionalPart`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#fractionalPart()) (shorthand: `fracPart`)
- [`round`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#round())
- [`significantFigures`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#significantFigures()) (shorthand: `sigFigs`)
- [`mantissa`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#mantissa())
- [`exponent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#exponent())
- [`signum`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#signum())
- [`isNegative`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isNegative()) (shorthand: `lt0`)
- [`isNegativeOrZero`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isNegativeOrZero()) (shorthand: `le0`)
- [`isPositive`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isPositive()) (shorthand: `gt0`)
- [`isPositiveOrZero`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isPositiveOrZero()) (shorthand: `ge0`)
- [`isZero`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isZero()) (shorthand: `eq0`)
- [`isEqual`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isEqual(trade.invision.num.Num)) (shorthand: `eq`)
- [`isLessThan`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isLessThan(trade.invision.num.Num)) (shorthand: `lt`)
- [`isLessThanOrEqual`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isLessThanOrEqual(trade.invision.num.Num)) (shorthand: `le`)
- [`isGreaterThan`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isGreaterThan(trade.invision.num.Num)) (shorthand: `gt`)
- [`isGreaterThanOrEqual`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isGreaterThanOrEqual(trade.invision.num.Num)) (shorthand: `ge`)
- [`isNaN`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isNaN())
- [`ifNaN`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#ifNaN(trade.invision.num.Num))
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
- [`factory`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#factory())
- [`equals`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#equals(java.lang.Object))
- [`hashCode`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hashCode())
- [`toString`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toString())

## Acknowledgement

This library's `Num` interface was inspired by the `Num` interface of the excellent [ta4j](https://github.com/ta4j/ta4j)
library. There are several improvements and additions that this library's `Num` interface provides:

- Interoperability between `DoubleNum` and `DecimalNum`.
- Several more mathematical operations (e.g. trigonometry functions) via
  [Math](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Math.html) in `DoubleNum` and via
  [big-math](https://github.com/eobermuhlner/big-math) in `DecimalNum`.
- No default precision for `DecimalNum` (see [ta4j issue](https://github.com/ta4j/ta4j/issues/1086)).
- Configurable epsilon for tolerant comparison operations (see [ta4j
  `DoubleNum`](https://github.com/ta4j/ta4j/blob/1101dbe059cda92d7dd1f86e755b0466782911d5/ta4j-core/src/main/java/org/ta4j/core/num/DoubleNum.java#L53)).
- `Number` used instead of primitive overloads.
- Documentation improvements.

Big thanks to [Eric Obermühlner](https://github.com/eobermuhlner) for the excellent
[big-math](https://github.com/eobermuhlner/big-math) library.

This project is sponsored by [Invision](https://invision.trade).
