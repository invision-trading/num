# Num

[![Maven Central Version](https://img.shields.io/maven-central/v/trade.invision/num)](https://central.sonatype.com/artifact/trade.invision/num)
[![javadoc](https://javadoc.io/badge2/trade.invision/num/javadoc.svg)](https://javadoc.io/doc/trade.invision/num)
[![GitHub License](https://img.shields.io/github/license/invision-trading/num)](https://github.com/invision-trading/num/blob/main/LICENSE.txt)

A Java library that abstracts the mathematical operations on real decimal numbers represented in computer memory as
floating-point binary numbers
([`Double`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Double.html)) or arbitrary-precision
decimal numbers
([`BigDecimal`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/math/BigDecimal.html)).

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
immutable. All methods in this interface return non-`null` values or throw a `RuntimeException` (usually an [
`ArithmeticException`](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/ArithmeticException.html)).
All implementations of this interface are interoperable with each other. Operations involving different
implementations will result in a `Num` that trends towards an increase in precision. For example, subtracting a
`DecimalNum` from a `DoubleNum` will result in a `DecimalNum`. Similarly, adding a `DecimalNum` with a `precision` of
`16` to a `DecimalNum`  with a `precision` of `32` will result in a `DecimalNum`  with a `precision` of `32`.
Mathematical operations that result in `NaN`, `+Infinity`, `-Infinity`, or `ArithmeticException` will yield
[`NaNNum`](src/main/java/trade/invision/num/NaNNum.java).

## Usage

First, add the dependency to your project:

For `build.gradle.kts`:

```kotlin
implementation("trade.invision", "num", "1.7.1")
```

For `build.gradle`:

```groovy
implementation group: 'trade.invision', name: 'num', version: '1.7.1'
```

For `pom.xml`:

```xml

<dependency>
    <groupId>trade.invision</groupId>
    <artifactId>num</artifactId>
    <version>1.7.1</version>
</dependency>
```

To create a `DoubleNum`, use one of the static methods, such as `DoubleNum.valueOf()` or `DoubleNum.doubleNum()`. Your
code may look cleaner if you use the `doubleNum()` static import instead of `DoubleNum.valueOf()`. Creating a
`DecimalNum` is the same as `DoubleNum`, but requires you to specify a precision and rounding mode. Use
`DecimalNum.decimalNum(Num, MathContext)` or use one of the convenience methods, such as `decimalNum64()` which
provides approximately the same precision as `double`, allowing up to 16 significant figures of precision, and the same
[rounding policy](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/math/RoundingMode.html#HALF_EVEN) as
`double`.

Check out the [Javadoc](https://javadoc.io/doc/trade.invision/num) for all classes and method signatures, but here's a
quick reference:
- [`unwrap`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#unwrap())
- [`factory`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#factory())
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
- [`naturalLogarithm`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#naturalLogarithm())
- [`commonLogarithm`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#commonLogarithm())
- [`binaryLogarithm`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#binaryLogarithm())
- [`logarithm`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#logarithm(trade.invision.num.Num))
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
- [`sine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#sine())
- [`cosine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#cosine())
- [`tangent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#tangent())
- [`inverseSine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseSine())
- [`inverseCosine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseCosine())
- [`inverseTangent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseTangent())
- [`inverseTangent2`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseTangent2(trade.invision.num.Num))
- [`hyperbolicSine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hyperbolicSine())
- [`hyperbolicCosine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hyperbolicCosine())
- [`hyperbolicTangent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hyperbolicTangent())
- [`inverseHyperbolicSine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseHyperbolicSine())
- [`inverseHyperbolicCosine`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseHyperbolicCosine())
- [`inverseHyperbolicTangent`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#inverseHyperbolicTangent())
- [`hypotenuse`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hypotenuse(trade.invision.num.Num))
- [`average`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#average(trade.invision.num.Num))
- [`minimum`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#minimum(trade.invision.num.Num))
- [`maximum`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#maximum(trade.invision.num.Num))
- [`clamp`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#clamp(trade.invision.num.Num,trade.invision.num.Num))
- [`integerPart`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#integerPart())
- [`fractionalPart`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#fractionalPart())
- [`round`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#round())
- [`precision`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#precision(int))
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
- [`isBetween`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isBetween(trade.invision.num.Num,trade.invision.num.Num))
- [`isNaN`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#isNaN())
- [`ifNaN`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#ifNaN(trade.invision.num.Num))
- [`asByte`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#asByte())
- [`asShort`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#asShort())
- [`asInt`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#asInt())
- [`asLong`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#asLong())
- [`asFloat`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#asFloat())
- [`asDouble`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#asDouble())
- [`asBigDecimal`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#asBigDecimal())
- [`getContext`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#getContext())
- [`getPrecision`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#getPrecision())
- [`getRoundingMode`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#getRoundingMode())
- [`withContext`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#withContext(int))
- [`equals`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#equals(java.lang.Object))
- [`hashCode`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#hashCode())
- [`toString`](https://javadoc.io/doc/trade.invision/num/latest/trade/invision/num/Num.html#toString())

## Acknowledgement

This library's `Num` interface was inspired by the `Num` interface of the excellent [ta4j](https://github.com/ta4j/ta4j)
library. There are several improvements and additions that this library's `Num` interface provides:

- Interoperability between `DoubleNum` and `DecimalNum`.
- `reciprocal()`, `root(base)`, `logarithm(base)`, `average()`, `precision()`, `round()`, etc.
- Trigonometry functions via [Math](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Math.html) in
  `DoubleNum` and via [big-math](https://github.com/eobermuhlner/big-math) in `DecimalNum`.
- No default precision for `DecimalNum` (see [issue](https://github.com/ta4j/ta4j/issues/1086)).
- Configurable epsilon for tolerant comparison operations (see [ta4j
  `DoubleNum`](https://github.com/ta4j/ta4j/blob/1101dbe059cda92d7dd1f86e755b0466782911d5/ta4j-core/src/main/java/org/ta4j/core/num/DoubleNum.java#L53)).
- `Number` used instead of primitive overloads.
- Documentation improvements.

Big thanks to [Eric Obermühlner](https://github.com/eobermuhlner) for the excellent
[big-math](https://github.com/eobermuhlner/big-math) library.

This project is sponsored by [Invision](https://invision.trade).
