package trade.invision.num;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_EVEN;

/**
 * {@link Num}, short for "number", is an interface for performing mathematical operations on real numbers in decimal.
 * Object instances of this interface are immutable. All methods in this interface return non-<code>null</code> values
 * or throw a {@link RuntimeException}. All implementations of this interface are interoperable with each other.
 *
 * @see DoubleNum
 * @see DecimalNum
 * @see NaNNum
 * @see Math
 * @see <a href="https://github.com/ta4j/ta4j">ta4j GitHub</a>
 * @see <a href="https://en.wikipedia.org/wiki/Computer_algebra">Wikipedia</a>
 */
public sealed interface Num extends Comparable<Num> permits DoubleNum, DecimalNum, NaNNum {

    /**
     * Creates a new {@link Num} with the same type of this {@link Num} and with the value of the given {@link Number}.
     * <br>
     * Do not use this method for non-integer decimal numbers. Use {@link #numOf(String)} instead.
     *
     * @param number the {@link Number}
     *
     * @return the {@link Num}
     */
    Num numOf(Number number);

    /**
     * Creates a new {@link Num} with the same type of this {@link Num} and with the value of the given {@link String}
     * representing a number.
     *
     * @param number the {@link String} representing a number
     *
     * @return the {@link Num}
     */
    Num numOf(String number);

    /**
     * @return the {@link Num} of <code>0.1</code>
     *
     * @see #numOf(String)
     */
    Num numOfTenth();

    /**
     * @return the {@link Num} of <code>0.01</code>
     *
     * @see #numOf(String)
     */
    Num numOfHundredth();

    /**
     * @return the {@link Num} of <code>0.001</code>
     *
     * @see #numOf(String)
     */
    Num numOfThousandth();

    /**
     * @return the {@link Num} of <code>-1</code>
     *
     * @see #numOf(Number)
     */
    Num numOfNegativeOne();

    /**
     * @return the {@link Num} of <code>0</code>
     *
     * @see #numOf(Number)
     */
    Num numOfZero();

    /**
     * @return the {@link Num} of <code>1</code>
     *
     * @see #numOf(Number)
     */
    Num numOfOne();

    /**
     * @return the {@link Num} of <code>2</code>
     *
     * @see #numOf(Number)
     */
    Num numOfTwo();

    /**
     * @return the {@link Num} of <code>3</code>
     *
     * @see #numOf(Number)
     */
    Num numOfThree();

    /**
     * @return the {@link Num} of <code>10</code>
     *
     * @see #numOf(Number)
     */
    Num numOfTen();

    /**
     * @return the {@link Num} of <code>100</code>
     *
     * @see #numOf(Number)
     */
    Num numOfHundred();

    /**
     * @return the {@link Num} of <code>1000</code>
     *
     * @see #numOf(Number)
     */
    Num numOfThousand();

    /**
     * Performs an addition (plus) operation by adding the given {@link Num} to this {@link Num}: <code>this +
     * addend</code>.
     *
     * @param addend the {@link Num} to add
     *
     * @return the sum {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Addition">Wikipedia</a>
     */
    Num add(Num addend);

    /**
     * Performs a subtraction (minus) operation by subtracting the given {@link Num} from this {@link Num}: <code>this -
     * subtrahend</code>.
     *
     * @param subtrahend the {@link Num} to subtract
     *
     * @return the difference {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Subtraction">Wikipedia</a>
     */
    Num subtract(Num subtrahend);

    /**
     * Performs a multiplication (times) operation by multiplying this {@link Num} by the given {@link Num}: <code>this
     * * multiplicand</code>.
     *
     * @param multiplicand the {@link Num} to multiply by
     *
     * @return the product {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Multiplication">Wikipedia</a>
     */
    Num multiply(Num multiplicand);

    /**
     * Performs a division (divided by) operation by dividing this {@link Num} by the given {@link Num}: <code>this /
     * divisor</code>.
     *
     * @param divisor the {@link Num} to divide by
     *
     * @return the quotient {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Division_(mathematics)">Wikipedia</a>
     */
    Num divide(Num divisor);

    /**
     * Performs a modulo (remainder of) operation by dividing this {@link Num} by the given {@link Num} and yielding the
     * remainder: <code>this % divisor</code>.
     *
     * @param divisor the {@link Num} to divide by
     *
     * @return the remainder {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Remainder">Wikipedia</a>
     */
    Num remainder(Num divisor);

    /**
     * Performs an exponentiation (power) operation by raising this {@link Num} to the given <code>int</code>:
     * <code>this ^ exponent</code> or <code>this<sup>exponent</sup></code>.
     *
     * @param exponent the <code>int</code> to raise to
     *
     * @return the power {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Exponentiation">Wikipedia</a>
     */
    Num power(int exponent);

    /**
     * Performs an exponentiation (power) operation by raising this {@link Num} to the given {@link Num}: <code>this ^
     * exponent</code> or <code>this<sup>exponent</sup></code>.
     *
     * @param exponent the {@link Num} to raise to
     *
     * @return the power {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Exponentiation">Wikipedia</a>
     */
    Num power(Num exponent);

    /**
     * Performs a square (raise to the power of two) operation by multiplying this {@link Num} by itself: <code>this *
     * this</code> or <code>this ^ 2</code> or <code>this<sup>2</sup></code>.
     *
     * @return the squared {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Square_(algebra)">Wikipedia</a>
     */
    Num square();

    /**
     * Performs a cube (raise to the power of three) operation by multiplying three instances of this {@link Num}
     * together: <code>this * this * this</code> or <code>this ^ 3</code> or <code>this<sup>3</sup></code>.
     *
     * @return the cubed {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Cube_(algebra)">Wikipedia</a>
     */
    Num cube();

    /**
     * Performs an exponential (<code>e</code> raised to the power of) operation by raising <a
     * href="https://en.wikipedia.org/wiki/Euler%27s_number"><i>e</i> (Euler's number)</a> to this {@link Num}:
     * <code><i>e</i> ^ this</code> or <code>e<sup>this</sup></code>.
     *
     * @return the exponential {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Exponential_function">Wikipedia</a>
     */
    Num exponential();

    /**
     * Performs an <i>n</i>th root (radical) operation using this {@link Num} as the radicand and the given {@link Num}
     * as the degree in the radical: <code><sup>n</sup>√this</code>.
     *
     * @param degree the {@link Num} to use for <i>n</i>
     *
     * @return the root {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Nth_root">Wikipedia</a>
     */
    Num root(Num degree);

    /**
     * Performs a square root (root two) operation using this {@link Num} as the radicand and <code>2</code> as the
     * degree in the radical: <code><sup>2</sup>√this</code>.
     *
     * @return the square root {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Square_root">Wikipedia</a>
     */
    Num squareRoot();

    /**
     * Performs a cube root (root three) operation using this {@link Num} as the radicand and <code>3</code> as the
     * degree in the radical: <code><sup>3</sup>√this</code>.
     *
     * @return the cube root {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Cube_root">Wikipedia</a>
     */
    Num cubeRoot();

    /**
     * Performs a natural logarithm (logarithm with a base of <code>e</code>) operation using this {@link Num} as the
     * anti-logarithm and <a href="https://en.wikipedia.org/wiki/Euler%27s_number"><i>e</i> (Euler's number)</a> as the
     * base: <code>log<sub>e</sub>this</code> or <code>ln(this)</code>.
     *
     * @return the natural logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Natural_logarithm">Wikipedia</a>
     */
    Num naturalLogarithm();

    /**
     * Performs a common logarithm (logarithm with a base of ten) operation using this {@link Num} as the anti-logarithm
     * and <code>10</code> as the base: <code>log<sub>10</sub>this</code> or <code>log(this)</code>.
     *
     * @return the common logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Common_logarithm">Wikipedia</a>
     */
    Num logarithm();

    /**
     * Performs a logarithm (log) operation using this {@link Num} as the anti-logarithm and the given <code>int</code>
     * as the base: <code>log<sub>base</sub>this</code>.
     *
     * @return the logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Logarithm">Wikipedia</a>
     */
    Num logarithm(int base);

    /**
     * Performs a logarithm (log) operation using this {@link Num} as the anti-logarithm and the given {@link Num} as
     * the base: <code>log<sub>base</sub>this</code>.
     *
     * @return the logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Logarithm">Wikipedia</a>
     */
    Num logarithm(Num base);

    /**
     * Performs an absolute value (abs) operation by computing the non-negative value of this {@link Num}:
     * <code>|this|</code>.
     *
     * @return the absolute value {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Absolute_value">Wikipedia</a>
     */
    Num absoluteValue();

    /**
     * Performs a negation (additive inverse) operation by multiplying this {@link Num} by negative one:
     * <code>this * -1</code> or <code>-this</code>.
     *
     * @return the negated {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Additive_inverse">Wikipedia</a>
     */
    Num negate();

    /**
     * Performs a reciprocal (multiplicative inverse) operation by dividing one by this {@link Num}:
     * <code>1 / this</code> or <code>this<sup>-1</sup></code>.
     *
     * @return the reciprocal {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Multiplicative_inverse">Wikipedia</a>
     */
    Num reciprocal();

    /**
     * Performs a floor operation by computing the largest (closest to positive infinity) integer that is less than or
     * equal to this {@link Num}: <code>⌊this⌋</code>.
     *
     * @return the floored {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Floor_and_ceiling_functions">Wikipedia</a>
     */
    Num floor();

    /**
     * Performs a ceil operation by computing the smallest (closest to negative infinity) integer that greater than or
     * equal to this {@link Num}: <code>⌈this⌉</code>.
     *
     * @return the ceiled {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Floor_and_ceiling_functions">Wikipedia</a>
     */
    Num ceil();

    /**
     * Performs a trigonometric angle conversion operation by converting this {@link Num} in radians to degrees:
     * <code>this°</code>.
     *
     * @return the degrees {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Degree_(angle)">Wikipedia</a>
     */
    Num degrees();

    /**
     * Performs a trigonometric angle conversion operation by converting this {@link Num} in degrees to radians:
     * <code>this <i>rad</i></code>.
     *
     * @return the radians {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Radian">Wikipedia</a>
     */
    Num radians();

    /**
     * Performs a trigonometric sine operation of this {@link Num} in radians: <code>sin(this)</code>.
     *
     * @return the sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">Wikipedia</a>
     */
    Num sine();

    /**
     * Performs a trigonometric cosine operation of this {@link Num} in radians: <code>cos(this)</code>.
     *
     * @return the cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">Wikipedia</a>
     */
    Num cosine();

    /**
     * Performs a trigonometric tangent operation of this {@link Num} in radians: <code>tan(this)</code>.
     *
     * @return the tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">Wikipedia</a>
     */
    Num tangent();

    /**
     * Performs a trigonometric inverse sine operation of this {@link Num} in radians: <code>asin(this)</code>.
     *
     * @return the inverse sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Wikipedia</a>
     */
    Num inverseSine();

    /**
     * Performs a trigonometric inverse cosine operation of this {@link Num} in radians: <code>acos(this)</code>.
     *
     * @return the inverse cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Wikipedia</a>
     */
    Num inverseCosine();

    /**
     * Performs a trigonometric inverse tangent operation of this {@link Num} in radians: <code>atan(this)</code>.
     *
     * @return the inverse tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Wikipedia</a>
     */
    Num inverseTangent();

    /**
     * Performs a trigonometric 2-argument inverse tangent operation using this {@link Num} in radians as <i>y</i> and
     * the given {@link Num} in radians as <i>x</i>: <code>atan2(y,x)</code>.
     *
     * @param x the {@link Num} to use for <i>x</i>
     *
     * @return the 2-argument inverse tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Atan2">Wikipedia</a>
     */
    Num inverseTangent2(Num x);

    /**
     * Performs a trigonometric hyperbolic sine operation of this {@link Num} in radians: <code>sinh(this)</code>.
     *
     * @return the hyperbolic sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">Wikipedia</a>
     */
    Num hyperbolicSine();

    /**
     * Performs a trigonometric hyperbolic cosine operation of this {@link Num} in radians: <code>cosh(this)</code>.
     *
     * @return the hyperbolic cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">Wikipedia</a>
     */
    Num hyperbolicCosine();

    /**
     * Performs a trigonometric hyperbolic tangent operation of this {@link Num} in radians: <code>tanh(this)</code>.
     *
     * @return the hyperbolic tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">Wikipedia</a>
     */
    Num hyperbolicTangent();

    /**
     * Performs a trigonometric inverse hyperbolic sine operation of this {@link Num} in radians:
     * <code>asinh(this)</code>.
     *
     * @return the inverse hyperbolic sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">Wikipedia</a>
     */
    Num inverseHyperbolicSine();

    /**
     * Performs a trigonometric inverse hyperbolic cosine operation of this {@link Num} in radians:
     * <code>acosh(this)</code>.
     *
     * @return the inverse hyperbolic cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">Wikipedia</a>
     */
    Num inverseHyperbolicCosine();

    /**
     * Performs a trigonometric inverse hyperbolic tangent operation of this {@link Num} in radians:
     * <code>atanh(this)</code>.
     *
     * @return the inverse hyperbolic tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">Wikipedia</a>
     */
    Num inverseHyperbolicTangent();

    /**
     * Performs a trigonometric hypotenuse (distance formula) operation using this {@link Num} as <i>x</i> and the given
     * {@link Num} as <i>y</i>: <code>√(<i>x</i><sup>2</sup> + <i>y</i><sup>2</sup>)</code>.
     *
     * @param y the {@link Num} to use for <i>y</i>
     *
     * @return the hypotenuse {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hypotenuse">Wikipedia</a>
     */
    Num hypotenuse(Num y);

    /**
     * Performs an average (mean) operation by dividing the sum of this {@link Num} and the given {@link Num} by two:
     * <code>(this + other) / 2</code>.
     *
     * @param other the other {@link Num}
     *
     * @return the average {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Mean">Wikipedia</a>
     */
    Num average(Num other);

    /**
     * Performs a minimum (minima extrema) operation by computing the lesser of this {@link Num} and the given
     * {@link Num}: <code>min(this, other)</code>.
     *
     * @param other the other {@link Num}
     *
     * @return the minimum {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Maximum_and_minimum">Wikipedia</a>
     */
    Num minimum(Num other);

    /**
     * Performs a maximum (maxima extrema) operation by computing the greater of this {@link Num} and the given
     * {@link Num}: <code>max(this, other)</code>.
     *
     * @param other the other {@link Num}
     *
     * @return the maximum {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Maximum_and_minimum">Wikipedia</a>
     */
    Num maximum(Num other);

    /**
     * Performs a truncation operation by removing the fractional (digits to the right of the decimal point) part of
     * this {@link Num}: <code>int(this)</code>.
     *
     * @return the integral part {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/Integral_part">Wikipedia</a>
     */
    Num integralPart();

    /**
     * Performs a truncation operation by removing the integral (digits to the left of the decimal point) part of this
     * {@link Num}: <code>frac(this)</code>.
     *
     * @return the fractional part {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Fractional_part">Wikipedia</a>
     */
    Num fractionalPart();

    /**
     * Calls {@link #round(int, RoundingMode)} with <code>scale</code> set to <code>0</code> and
     * <code>roundingMode</code> set to {@link RoundingMode#HALF_EVEN}.
     */
    default Num round() {
        return round(0, HALF_EVEN);
    }

    /**
     * Calls {@link #round(int, RoundingMode)} with <code>scale</code> set to <code>0</code>.
     */
    default Num round(RoundingMode roundingMode) {
        return round(0, roundingMode);
    }

    /**
     * Calls {@link #round(int, RoundingMode)} with <code>roundingMode</code> set to {@link RoundingMode#HALF_EVEN}.
     */
    default Num round(int scale) {
        return round(scale, HALF_EVEN);
    }

    /**
     * Performs a rounding operation on this {@link Num} using the given <code>scale</code> and {@link RoundingMode}.
     *
     * @param scale        the number of digits to right of the decimal point
     * @param roundingMode the {@link RoundingMode}
     *
     * @return the rounded {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Rounding">Wikipedia</a>
     */
    Num round(int scale, RoundingMode roundingMode);

    /**
     * Calls {@link #precision(int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    default Num precision(int significantFigures) {
        return precision(significantFigures, HALF_EVEN);
    }

    /**
     * Performs a precision modification operation by rounding to the number of the given
     * <code>significantFigures</code> of this {@link Num} without modifying the scale.
     *
     * @param significantFigures the significant figures
     * @param roundingMode       the {@link RoundingMode}
     *
     * @return the precise {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Significant_figures">Wikipedia</a>
     */
    Num precision(int significantFigures, RoundingMode roundingMode);

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than zero: <code>this <
     * 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is less than zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Negative_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isNegative();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than or equal to zero:
     * <code>this <= 0</code> or <code>this ≤ 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is less than or equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Negative_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isNegativeOrZero();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than zero: <code>this >
     * 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is greater than zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isPositive();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than or equal to zero:
     * <code>this >= 0</code> or <code>this ≥ 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is greater than or equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isPositiveOrZero();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is equal to zero:
     * <code>this == 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/0">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     */
    boolean isZero();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is not equal to zero:
     * <code>this != 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is not equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/0">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     */
    default boolean isNotZero() {
        return !isZero();
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is equal to the given {@link Num}:
     * <code>this == other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is equal to the <code>other</code> {@link Num}, <code>false</code>
     * otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     */
    boolean isEqual(Num other);

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is not equal to the given
     * {@link Num}: <code>this != other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is not equal to the <code>other</code> {@link Num},
     * <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     */
    default boolean isNotEqual(Num other) {
        return !isEqual(other);
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than the given
     * {@link Num}:
     * <code>this < other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is less than the <code>other</code> {@link Num}, <code>false</code>
     * otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isLessThan(Num other);

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than or equal to the given
     * {@link Num}: <code>this <= other</code> or <code>this ≤ other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is less than or equal to the <code>other</code> {@link Num},
     * <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isLessThanOrEqual(Num other);

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than the given
     * {@link Num}: <code>this > other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is greater than the <code>other</code> {@link Num},
     * <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isGreaterThan(Num other);

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than or equal to the
     * given {@link Num}: <code>this >= other</code> or <code>this ≥ other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is greater than or equal to the <code>other</code> {@link Num},
     * <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isGreaterThanOrEqual(Num other);

    /**
     * Checks if this {@link Num} is {@link NaNNum#NaN}.
     *
     * @return <code>true</code> if {@link NaNNum#NaN}, <code>false</code> otherwise
     */
    boolean isNaN();

    /**
     * Converts this {@link Num} to a <code>byte</code>.
     *
     * @return the <code>byte</code>
     *
     * @see #unwrap()
     * @see Number#byteValue()
     */
    default byte asByte() {
        return unwrap().byteValue();
    }

    /**
     * Converts this {@link Num} to a <code>short</code>.
     *
     * @return the <code>short</code>
     *
     * @see #unwrap()
     * @see Number#shortValue()
     */
    default short asShort() {
        return unwrap().shortValue();
    }

    /**
     * Converts this {@link Num} to an <code>int</code>.
     *
     * @return the <code>int</code>
     *
     * @see #unwrap()
     * @see Number#intValue()
     */
    default int asInt() {
        return unwrap().intValue();
    }

    /**
     * Converts this {@link Num} to a <code>long</code>.
     *
     * @return the <code>long</code>
     *
     * @see #unwrap()
     * @see Number#longValue()
     */
    default long asLong() {
        return unwrap().longValue();
    }

    /**
     * Converts this {@link Num} to a <code>float</code>.
     *
     * @return the <code>float</code>
     *
     * @see #unwrap()
     * @see Number#floatValue()
     */
    default float asFloat() {
        return unwrap().floatValue();
    }

    /**
     * Converts this {@link Num} to a <code>double</code>.
     *
     * @return the <code>double</code>
     *
     * @see #unwrap()
     * @see Number#doubleValue()
     */
    default double asDouble() {
        return unwrap().doubleValue();
    }

    /**
     * Converts this {@link Num} to a {@link BigDecimal}.
     *
     * @return the {@link BigDecimal}
     */
    BigDecimal asBigDecimal();

    /**
     * Gets the wrapped {@link Number} value of this {@link Num}.
     *
     * @return the {@link Number}
     */
    Number unwrap();

    /**
     * Gets the {@link MathContext} representing the <code>precision</code> and {@link RoundingMode} used by
     * mathematical operations of this {@link Num}.
     *
     * @return the {@link MathContext}
     */
    MathContext getContext();

    /**
     * Performs an {@link Object} equivalence operation. To perform a numerical equivalence operation, use
     * {@link #isEqual(Num)} instead of this method.
     *
     * @param obj the {@link Object} to compare
     *
     * @return <code>true</code> for {@link Object} equality, <code>false</code> otherwise
     */
    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

    @Override
    String toString();
}
