package trade.invision.num;

import com.google.errorprone.annotations.Immutable;
import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Supplier;

import static java.math.RoundingMode.HALF_EVEN;

/**
 * {@link Num}, short for "number", is an interface for performing mathematical operations on real decimal numbers.
 * Implementations wrap a {@link Number} instance so that performing mathematical operations on floating-point binary
 * numbers ({@link Double} via {@link DoubleNum}) or arbitrary-precision decimal numbers ({@link BigDecimal} via
 * {@link DecimalNum}) is simple. All implementations of this interface are interoperable with each other. Operations
 * involving different implementations will result in a {@link Num} that trends towards an increase in precision. For
 * example, subtracting a {@link DecimalNum} from a {@link DoubleNum} will result in a {@link DecimalNum}. For another
 * example, subtracting a {@link DecimalNum} with a {@link DecimalNum#getContextPrecision()} of <code>16</code> from a
 * {@link DecimalNum} with a {@link DecimalNum#getContextPrecision()} of <code>32</code> will result in a
 * {@link DecimalNum} with a {@link DecimalNum#getContextPrecision()} of <code>32</code>. Mathematical operations that
 * result in <code>NaN</code>, <code>+Infinity</code>, <code>-Infinity</code>, or throw an {@link ArithmeticException}
 * will yield {@link NaNNum#NaN}.
 *
 * @see DoubleNum
 * @see DecimalNum
 * @see NaNNum
 * @see <a href="https://en.wikipedia.org/wiki/Computer_algebra">Wikipedia</a>
 */
@NullMarked
@Immutable
public sealed interface Num extends Comparable<Num> permits DoubleNum, DecimalNum, NaNNum {

    /**
     * @return {@link #add(Num)} {@link NumFactory#of(Number)}
     */
    default Num add(final Number addend) {
        return add(factory().of(addend));
    }

    /**
     * @return {@link #add(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num add(final BigDecimal addend) {
        return add(factory().of(addend));
    }

    /**
     * @return {@link #add(Num)} {@link NumFactory#of(String)}
     */
    default Num add(final String addend) {
        return add(factory().of(addend));
    }

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
    Num add(final Num addend);

    /**
     * @return {@link #subtract(Num)} {@link NumFactory#of(Number)}
     */
    default Num subtract(final Number subtrahend) {
        return subtract(factory().of(subtrahend));
    }

    /**
     * @return {@link #subtract(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num subtract(final BigDecimal subtrahend) {
        return subtract(factory().of(subtrahend));
    }

    /**
     * @return {@link #subtract(Num)} {@link NumFactory#of(String)}
     */
    default Num subtract(final String subtrahend) {
        return subtract(factory().of(subtrahend));
    }

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
    Num subtract(final Num subtrahend);

    /**
     * @return {@link #multiply(Num)} {@link NumFactory#of(Number)}
     */
    default Num multiply(final Number multiplier) {
        return multiply(factory().of(multiplier));
    }

    /**
     * @return {@link #multiply(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num multiply(final BigDecimal multiplier) {
        return multiply(factory().of(multiplier));
    }

    /**
     * @return {@link #multiply(Num)} {@link NumFactory#of(String)}
     */
    default Num multiply(final String multiplier) {
        return multiply(factory().of(multiplier));
    }

    /**
     * Performs a multiplication (times) operation by multiplying this {@link Num} by the given {@link Num}: <code>this
     * * multiplier</code>.
     *
     * @param multiplier the {@link Num} to multiply by
     *
     * @return the product {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Multiplication">Wikipedia</a>
     */
    Num multiply(final Num multiplier);

    /**
     * @return {@link #divide(Num)} {@link NumFactory#of(Number)}
     */
    default Num divide(final Number divisor) {
        return divide(factory().of(divisor));
    }

    /**
     * @return {@link #divide(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num divide(final BigDecimal divisor) {
        return divide(factory().of(divisor));
    }

    /**
     * @return {@link #divide(Num)} {@link NumFactory#of(String)}
     */
    default Num divide(final String divisor) {
        return divide(factory().of(divisor));
    }

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
    Num divide(final Num divisor);

    /**
     * @return {@link #remainder(Num)} {@link NumFactory#of(Number)}
     */
    default Num remainder(final Number divisor) {
        return remainder(factory().of(divisor));
    }

    /**
     * @return {@link #remainder(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num remainder(final BigDecimal divisor) {
        return remainder(factory().of(divisor));
    }

    /**
     * @return {@link #remainder(Num)} {@link NumFactory#of(String)}
     */
    default Num remainder(final String divisor) {
        return remainder(factory().of(divisor));
    }

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
    Num remainder(final Num divisor);

    /**
     * @return {@link #power(Num)} {@link NumFactory#of(Number)}
     */
    default Num power(final Number exponent) {
        return power(factory().of(exponent));
    }

    /**
     * @return {@link #power(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num power(final BigDecimal exponent) {
        return power(factory().of(exponent));
    }

    /**
     * @return {@link #power(Num)} {@link NumFactory#of(String)}
     */
    default Num power(final String exponent) {
        return power(factory().of(exponent));
    }

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
    Num power(final Num exponent);

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
     * Performs an exponential (<code>e</code> raised to the power of) operation by raising
     * <a href="https://en.wikipedia.org/wiki/Euler%27s_number"><i>e</i> (Euler's number)</a> to this {@link Num}:
     * <code><i>e</i> ^ this</code> or <code>e<sup>this</sup></code>.
     *
     * @return the exponential {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Exponential_function">Wikipedia</a>
     */
    Num exponential();

    /**
     * @return {@link #nthRoot(Num)} {@link NumFactory#of(Number)}
     */
    default Num nthRoot(final Number degree) {
        return nthRoot(factory().of(degree));
    }

    /**
     * @return {@link #nthRoot(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num nthRoot(final BigDecimal degree) {
        return nthRoot(factory().of(degree));
    }

    /**
     * @return {@link #nthRoot(Num)} {@link NumFactory#of(String)}
     */
    default Num nthRoot(final String degree) {
        return nthRoot(factory().of(degree));
    }

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
    Num nthRoot(final Num degree);

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
     * Performs a natural logarithm (logarithm with a base of <i>e</i>) operation using this {@link Num} as the
     * anti-logarithm and <a href="https://en.wikipedia.org/wiki/Euler%27s_number"><i>e</i> (Euler's number)</a> as the
     * base: <code>log<sub>e</sub>this</code> or <code>ln(this)</code>.
     *
     * @return the natural logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Natural_logarithm">Wikipedia</a>
     */
    Num naturalLog();

    /**
     * Performs a common logarithm (logarithm with a base of ten) operation using this {@link Num} as the anti-logarithm
     * and <code>10</code> as the base: <code>log<sub>10</sub>this</code> or <code>log10(this)</code>.
     *
     * @return the common logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Common_logarithm">Wikipedia</a>
     */
    Num commonLog();

    /**
     * Performs a binary logarithm (logarithm with a base of two) operation using this {@link Num} as the anti-logarithm
     * and <code>2</code> as the base: <code>log<sub>2</sub>this</code> or <code>log2(this)</code>.
     *
     * @return the binary logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Binary_logarithm">Wikipedia</a>
     */
    Num binaryLog();

    /**
     * @return {@link #log(Num)} {@link NumFactory#of(Number)}
     */
    default Num log(final Number base) {
        return log(factory().of(base));
    }

    /**
     * @return {@link #log(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num log(final BigDecimal base) {
        return log(factory().of(base));
    }

    /**
     * @return {@link #log(Num)} {@link NumFactory#of(String)}
     */
    default Num log(final String base) {
        return log(factory().of(base));
    }

    /**
     * Performs a logarithm (log) operation using this {@link Num} as the anti-logarithm and the given {@link Num} as
     * the base: <code>log<sub>base</sub>this</code>.
     *
     * @return the logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Logarithm">Wikipedia</a>
     */
    Num log(final Num base);

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
     * Performs an increment operation by adding one to this {@link Num}: <code>this + 1</code>.
     *
     * @return the incremented {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Increment_and_decrement_operators">Wikipedia</a>
     */
    Num increment();

    /**
     * Performs a decrement operation by subtracting one from this {@link Num}: <code>this - 1</code>.
     *
     * @return the decremented {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Increment_and_decrement_operators">Wikipedia</a>
     */
    Num decrement();

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
     * Returns the π (pi) mathematical constant with a precision of {@link #getContextPrecision()}.
     *
     * @return the pi {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Pi">Wikipedia</a>
     */
    Num pi();

    /**
     * Returns the <i>e</i> (Euler's number) mathematical constant with a precision of {@link #getContextPrecision()}.
     *
     * @return the <i>e</i> {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/E_(mathematical_constant)">Wikipedia</a>
     */
    Num e();

    /**
     * Performs a trigonometric sine operation of this {@link Num} in radians: <code>sin(this)</code>.
     *
     * @return the sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">Wikipedia</a>
     */
    Num sin();

    /**
     * Performs a trigonometric cosine operation of this {@link Num} in radians: <code>cos(this)</code>.
     *
     * @return the cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">Wikipedia</a>
     */
    Num cos();

    /**
     * Performs a trigonometric tangent operation of this {@link Num} in radians: <code>tan(this)</code>.
     *
     * @return the tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">Wikipedia</a>
     */
    Num tan();

    /**
     * Performs a trigonometric inverse sine operation of this {@link Num} in radians: <code>asin(this)</code>.
     *
     * @return the inverse sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Wikipedia</a>
     */
    Num asin();

    /**
     * Performs a trigonometric inverse cosine operation of this {@link Num} in radians: <code>acos(this)</code>.
     *
     * @return the inverse cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Wikipedia</a>
     */
    Num acos();

    /**
     * Performs a trigonometric inverse tangent operation of this {@link Num} in radians: <code>atan(this)</code>.
     *
     * @return the inverse tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Wikipedia</a>
     */
    Num atan();

    /**
     * @return {@link #atan2(Num)} {@link NumFactory#of(Number)}
     */
    default Num atan2(final Number x) {
        return atan2(factory().of(x));
    }

    /**
     * @return {@link #atan2(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num atan2(final BigDecimal x) {
        return atan2(factory().of(x));
    }

    /**
     * @return {@link #atan2(Num)} {@link NumFactory#of(String)}
     */
    default Num atan2(final String x) {
        return atan2(factory().of(x));
    }

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
    Num atan2(final Num x);

    /**
     * Performs a trigonometric hyperbolic sine operation of this {@link Num} in radians: <code>sinh(this)</code>.
     *
     * @return the hyperbolic sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">Wikipedia</a>
     */
    Num sinh();

    /**
     * Performs a trigonometric hyperbolic cosine operation of this {@link Num} in radians: <code>cosh(this)</code>.
     *
     * @return the hyperbolic cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">Wikipedia</a>
     */
    Num cosh();

    /**
     * Performs a trigonometric hyperbolic tangent operation of this {@link Num} in radians: <code>tanh(this)</code>.
     *
     * @return the hyperbolic tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">Wikipedia</a>
     */
    Num tanh();

    /**
     * Performs a trigonometric inverse hyperbolic sine operation of this {@link Num} in radians:
     * <code>asinh(this)</code>.
     *
     * @return the inverse hyperbolic sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">Wikipedia</a>
     */
    Num asinh();

    /**
     * Performs a trigonometric inverse hyperbolic cosine operation of this {@link Num} in radians:
     * <code>acosh(this)</code>.
     *
     * @return the inverse hyperbolic cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">Wikipedia</a>
     */
    Num acosh();

    /**
     * Performs a trigonometric inverse hyperbolic tangent operation of this {@link Num} in radians:
     * <code>atanh(this)</code>.
     *
     * @return the inverse hyperbolic tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">Wikipedia</a>
     */
    Num atanh();

    /**
     * @return {@link #hypotenuse(Num)} {@link NumFactory#of(Number)}
     */
    default Num hypotenuse(final Number y) {
        return hypotenuse(factory().of(y));
    }

    /**
     * @return {@link #hypotenuse(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num hypotenuse(final BigDecimal y) {
        return hypotenuse(factory().of(y));
    }

    /**
     * @return {@link #hypotenuse(Num)} {@link NumFactory#of(String)}
     */
    default Num hypotenuse(final String y) {
        return hypotenuse(factory().of(y));
    }

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
    Num hypotenuse(final Num y);

    /**
     * @return {@link #average(Num)} {@link NumFactory#of(Number)}
     */
    default Num average(final Number other) {
        return average(factory().of(other));
    }

    /**
     * @return {@link #average(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num average(final BigDecimal other) {
        return average(factory().of(other));
    }

    /**
     * @return {@link #average(Num)} {@link NumFactory#of(String)}
     */
    default Num average(final String other) {
        return average(factory().of(other));
    }

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
    Num average(final Num other);

    /**
     * @return {@link #min(Num)} {@link NumFactory#of(Number)}
     */
    default Num min(final Number other) {
        return min(factory().of(other));
    }

    /**
     * @return {@link #min(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num min(final BigDecimal other) {
        return min(factory().of(other));
    }

    /**
     * @return {@link #min(Num)} {@link NumFactory#of(String)}
     */
    default Num min(final String other) {
        return min(factory().of(other));
    }

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
    default Num min(final Num other) {
        return isLessThan(other) ? this : other; // NaN check not necessary when `other` is on RHS
    }

    /**
     * @return {@link #max(Num)} {@link NumFactory#of(Number)}
     */
    default Num max(final Number other) {
        return max(factory().of(other));
    }

    /**
     * @return {@link #max(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num max(final BigDecimal other) {
        return max(factory().of(other));
    }

    /**
     * @return {@link #max(Num)} {@link NumFactory#of(String)}
     */
    default Num max(final String other) {
        return max(factory().of(other));
    }

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
    default Num max(final Num other) {
        return isGreaterThan(other) ? this : other; // NaN check not necessary when `other` is on RHS
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(Number)} {@link NumFactory#of(Number)}
     */
    default Num clamp(final Number min, final Number max) {
        return clamp(factory().of(min), factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(Number)} {@link NumFactory#of(BigDecimal)}
     */
    default Num clamp(final Number min, final BigDecimal max) {
        return clamp(factory().of(min), factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(Number)} {@link NumFactory#of(String)}
     */
    default Num clamp(final Number min, final String max) {
        return clamp(factory().of(min), factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(Number)}
     */
    default Num clamp(final Number min, final Num max) {
        return clamp(factory().of(min), max);
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(Number)}
     */
    default Num clamp(final BigDecimal min, final Number max) {
        return clamp(factory().of(min), factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(BigDecimal)}
     */
    default Num clamp(final BigDecimal min, final BigDecimal max) {
        return clamp(factory().of(min), factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(String)}
     */
    default Num clamp(final BigDecimal min, final String max) {
        return clamp(factory().of(min), factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num clamp(final BigDecimal min, final Num max) {
        return clamp(factory().of(min), max);
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(String)} {@link NumFactory#of(Number)}
     */
    default Num clamp(final String min, final Number max) {
        return clamp(factory().of(min), factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(String)} {@link NumFactory#of(BigDecimal)}
     */
    default Num clamp(final String min, final BigDecimal max) {
        return clamp(factory().of(min), factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(String)} {@link NumFactory#of(String)}
     */
    default Num clamp(final String min, final String max) {
        return clamp(factory().of(min), factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(String)}
     */
    default Num clamp(final String min, final Num max) {
        return clamp(factory().of(min), max);
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(Number)}
     */
    default Num clamp(final Num min, final Number max) {
        return clamp(min, factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num clamp(final Num min, final BigDecimal max) {
        return clamp(min, factory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(String)}
     */
    default Num clamp(final Num min, final String max) {
        return clamp(min, factory().of(max));
    }

    /**
     * Performs a clamp operation by limiting this value to the range between the given <code>min</code> and
     * <code>max</code>: <code>min(max, max(this, min))</code>.
     *
     * @param min the minimum {@link Num}
     * @param max the maximum {@link Num}
     *
     * @return the clamped {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Clamp_(function)">Wikipedia</a>
     */
    default Num clamp(final Num min, final Num max) {
        return max.min(max(min));
    }

    /**
     * Performs a truncation operation by removing the fractional part (digits to the right of the decimal point) of
     * this {@link Num} and returning the integer part: <code>int(this)</code>.
     *
     * @return the integer part {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Integer_part">Wikipedia</a>
     */
    Num integerPart();

    /**
     * Performs a truncation operation by removing the integer part (digits to the left of the decimal point) of this
     * {@link Num} and returning the fractional part: <code>frac(this)</code>.
     *
     * @return the fractional part {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Fractional_part">Wikipedia</a>
     */
    Num fractionalPart();

    /**
     * @return {@link #round(int, RoundingMode)} with <code>scale</code> set to <code>0</code> and
     * <code>roundingMode</code> set to {@link RoundingMode#HALF_EVEN}
     */
    default Num round() {
        return round(0, HALF_EVEN);
    }

    /**
     * @return {@link #round(int, RoundingMode)} with <code>scale</code> set to <code>0</code>
     */
    default Num round(final RoundingMode roundingMode) {
        return round(0, roundingMode);
    }

    /**
     * @return {@link #round(int, RoundingMode)} with <code>roundingMode</code> set to {@link RoundingMode#HALF_EVEN}
     */
    default Num round(final int scale) {
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
    Num round(final int scale, final RoundingMode roundingMode);

    /**
     * @return {@link #sigFigs(int, RoundingMode)} with <code>roundingMode</code> set to {@link RoundingMode#HALF_EVEN}
     */
    default Num sigFigs(final int significantFigures) {
        return sigFigs(significantFigures, HALF_EVEN);
    }

    /**
     * @return {@link #sigFigs(MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    default Num sigFigs(final int significantFigures, final RoundingMode roundingMode) {
        return sigFigs(new MathContext(significantFigures, roundingMode));
    }

    /**
     * Performs a precision modification operation by setting the number of significant figures in this {@link Num} to
     * the given {@link MathContext#getPrecision()} and rounding excess significant figures according to the given
     * {@link MathContext#getRoundingMode()}.
     *
     * @param context the {@link MathContext}
     *
     * @return the precision-modified {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Significant_figures">Wikipedia</a>
     */
    Num sigFigs(final MathContext context);

    /**
     * Performs a significant figures (sig figs) count operation on this {@link Num}.
     *
     * @return the significant figures count <code>int</code>
     *
     * @see <a href="https://en.wikipedia.org/wiki/Significant_figures">Wikipedia</a>
     */
    int sigFigs();

    /**
     * Performs a mantissa retrieval operation by computing the <code>mantissa</code> of this {@link Num} as defined by
     * the scientific notation: <code>mantissa * 10<sup>exponent</sup></code>.
     *
     * @return the mantissa {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Scientific_notation">Wikipedia</a>
     * @see #exponent()
     */
    Num mantissa();

    /**
     * Performs an exponent retrieval operation by computing the <code>exponent</code> of this {@link Num} as defined by
     * the scientific notation: <code>mantissa * 10<sup>exponent</sup></code>.
     *
     * @return the exponent <code>int</code>
     *
     * @see <a href="https://en.wikipedia.org/wiki/Scientific_notation">Wikipedia</a>
     * @see #mantissa()
     */
    int exponent();

    /**
     * Performs a signum operation on this {@link Num}, yielding <code>-1</code> for negative numbers, <code>1</code>
     * for positive numbers, and <code>0</code> for <code>0</code> or <code>NaN</code>.
     *
     * @return the signum <code>int</code>
     *
     * @see <a href="https://en.wikipedia.org/wiki/Sign_function">Wikipedia</a>
     */
    int signum();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than zero: <code>this &lt;
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
     * <code>this &lt;= 0</code> or <code>this ≤ 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is less than or equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Negative_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isNegativeOrZero();

    /**
     * @return {@link #isNegativeOrZero(Num)} {@link NumFactory#of(Number)}
     */
    default boolean isNegativeOrZero(final Number epsilon) {
        return isNegativeOrZero(factory().of(epsilon));
    }

    /**
     * @return {@link #isNegativeOrZero(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isNegativeOrZero(final BigDecimal epsilon) {
        return isNegativeOrZero(factory().of(epsilon));
    }

    /**
     * @return {@link #isNegativeOrZero(Num)} {@link NumFactory#of(String)}
     */
    default boolean isNegativeOrZero(final String epsilon) {
        return isNegativeOrZero(factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly less than or equal to
     * zero: <code>this &lt;= epsilon</code> or <code>this ≤ epsilon</code>.
     *
     * @param epsilon the epsilon (tolerance) {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is tolerantly less than or equal to zero, <code>false</code>
     * otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Negative_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">Wikipedia</a>
     */
    boolean isNegativeOrZero(final Num epsilon);

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than zero: <code>this
     * &gt; 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is greater than zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isPositive();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than or equal to zero:
     * <code>this &gt;= 0</code> or <code>this ≥ 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is greater than or equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isPositiveOrZero();

    /**
     * @return {@link #isPositiveOrZero(Num)} {@link NumFactory#of(Number)}
     */
    default boolean isPositiveOrZero(final Number epsilon) {
        return isPositiveOrZero(factory().of(epsilon));
    }

    /**
     * @return {@link #isPositiveOrZero(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isPositiveOrZero(final BigDecimal epsilon) {
        return isPositiveOrZero(factory().of(epsilon));
    }

    /**
     * @return {@link #isPositiveOrZero(Num)} {@link NumFactory#of(String)}
     */
    default boolean isPositiveOrZero(final String epsilon) {
        return isPositiveOrZero(factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly greater than or equal
     * to zero: <code>this &gt;= -epsilon</code> or <code>this ≥ -epsilon</code>.
     *
     * @param epsilon the epsilon (tolerance) {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is tolerantly greater than or equal to zero, <code>false</code>
     * otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">Wikipedia</a>
     */
    boolean isPositiveOrZero(final Num epsilon);

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
     * @return {@link #isZero(Num)} {@link NumFactory#of(Number)}
     */
    default boolean isZero(final Number epsilon) {
        return isZero(factory().of(epsilon));
    }

    /**
     * @return {@link #isZero(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isZero(final BigDecimal epsilon) {
        return isZero(factory().of(epsilon));
    }

    /**
     * @return {@link #isZero(Num)} {@link NumFactory#of(String)}
     */
    default boolean isZero(final String epsilon) {
        return isZero(factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly equal to zero:
     * <code>|this| &lt;= epsilon</code> or <code>|this| ≤ epsilon</code>.
     *
     * @param epsilon the epsilon (tolerance) {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is tolerantly equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/0">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">Wikipedia</a>
     */
    boolean isZero(final Num epsilon);

    /**
     * @return {@link #isEqual(Num)} {@link NumFactory#of(Number)}
     */
    default boolean isEqual(final Number other) {
        return isEqual(factory().of(other));
    }

    /**
     * @return {@link #isEqual(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isEqual(final BigDecimal other) {
        return isEqual(factory().of(other));
    }

    /**
     * @return {@link #isEqual(Num)} {@link NumFactory#of(String)}
     */
    default boolean isEqual(final String other) {
        return isEqual(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is equal to the given {@link Num}:
     * <code>this == other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is equal to <code>other</code>, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     */
    boolean isEqual(final Num other);

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(Number)}
     */
    default boolean isEqual(final Number other, final Number epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isEqual(final Number other, final BigDecimal epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(String)}
     */
    default boolean isEqual(final Number other, final String epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    default boolean isEqual(final Number other, final Num epsilon) {
        return isEqual(factory().of(other), epsilon);
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(Number)}
     */
    default boolean isEqual(final BigDecimal other, final Number epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isEqual(final BigDecimal other, final BigDecimal epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(String)}
     */
    default boolean isEqual(final BigDecimal other, final String epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isEqual(final BigDecimal other, final Num epsilon) {
        return isEqual(factory().of(other), epsilon);
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(Number)}
     */
    default boolean isEqual(final String other, final Number epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isEqual(final String other, final BigDecimal epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(String)}
     */
    default boolean isEqual(final String other, final String epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    default boolean isEqual(final String other, final Num epsilon) {
        return isEqual(factory().of(other), epsilon);
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    default boolean isEqual(final Num other, final Number epsilon) {
        return isEqual(other, factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isEqual(final Num other, final BigDecimal epsilon) {
        return isEqual(other, factory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    default boolean isEqual(final Num other, final String epsilon) {
        return isEqual(other, factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly equal to the given
     * {@link Num}: <code>|this - other| &lt;= epsilon</code>.
     *
     * @param other   the other {@link Num}
     * @param epsilon the epsilon (tolerance) {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is tolerantly equal to <code>other</code>, <code>false</code>
     * otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">Wikipedia</a>
     */
    boolean isEqual(final Num other, final Num epsilon);

    /**
     * @return {@link #isLessThan(Num)} {@link NumFactory#of(Number)}
     */
    default boolean isLessThan(final Number other) {
        return isLessThan(factory().of(other));
    }

    /**
     * @return {@link #isLessThan(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isLessThan(final BigDecimal other) {
        return isLessThan(factory().of(other));
    }

    /**
     * @return {@link #isLessThan(Num)} {@link NumFactory#of(String)}
     */
    default boolean isLessThan(final String other) {
        return isLessThan(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than the given {@link Num}:
     * <code>this &lt; other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is less than <code>other</code>, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isLessThan(final Num other);

    /**
     * @return {@link #isLessThanOrEqual(Num)} {@link NumFactory#of(Number)}
     */
    default boolean isLessThanOrEqual(final Number other) {
        return isLessThanOrEqual(factory().of(other));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isLessThanOrEqual(final BigDecimal other) {
        return isLessThanOrEqual(factory().of(other));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num)} {@link NumFactory#of(String)}
     */
    default boolean isLessThanOrEqual(final String other) {
        return isLessThanOrEqual(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than or equal to the given
     * {@link Num}: <code>this &lt;= other</code> or <code>this ≤ other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is less than or equal to <code>other</code>, <code>false</code>
     * otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isLessThanOrEqual(final Num other);

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(Number)}
     */
    default boolean isLessThanOrEqual(final Number other, final Number epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isLessThanOrEqual(final Number other, final BigDecimal epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(String)}
     */
    default boolean isLessThanOrEqual(final Number other, final String epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    default boolean isLessThanOrEqual(final Number other, final Num epsilon) {
        return isLessThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(Number)}
     */
    default boolean isLessThanOrEqual(final BigDecimal other, final Number epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isLessThanOrEqual(final BigDecimal other, final BigDecimal epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(String)}
     */
    default boolean isLessThanOrEqual(final BigDecimal other, final String epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isLessThanOrEqual(final BigDecimal other, final Num epsilon) {
        return isLessThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(Number)}
     */
    default boolean isLessThanOrEqual(final String other, final Number epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isLessThanOrEqual(final String other, final BigDecimal epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(String)}
     */
    default boolean isLessThanOrEqual(final String other, final String epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    default boolean isLessThanOrEqual(final String other, final Num epsilon) {
        return isLessThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    default boolean isLessThanOrEqual(final Num other, final Number epsilon) {
        return isLessThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isLessThanOrEqual(final Num other, final BigDecimal epsilon) {
        return isLessThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    default boolean isLessThanOrEqual(final Num other, final String epsilon) {
        return isLessThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly less than or equal to
     * the given {@link Num}: <code>other - this &gt;= -epsilon</code> or <code>other - this ≥ -epsilon</code>.
     *
     * @param other   the other {@link Num}
     * @param epsilon the epsilon (tolerance) {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is tolerantly less than or equal to <code>other</code>,
     * <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">Wikipedia</a>
     */
    boolean isLessThanOrEqual(final Num other, final Num epsilon);

    /**
     * @return {@link #isGreaterThan(Num)} {@link NumFactory#of(Number)}
     */
    default boolean isGreaterThan(final Number other) {
        return isGreaterThan(factory().of(other));
    }

    /**
     * @return {@link #isGreaterThan(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isGreaterThan(final BigDecimal other) {
        return isGreaterThan(factory().of(other));
    }

    /**
     * @return {@link #isGreaterThan(Num)} {@link NumFactory#of(String)}
     */
    default boolean isGreaterThan(final String other) {
        return isGreaterThan(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than the given
     * {@link Num}: <code>this &gt; other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is greater than <code>other</code>, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isGreaterThan(final Num other);

    /**
     * @return {@link #isGreaterThanOrEqual(Num)} {@link NumFactory#of(Number)}
     */
    default boolean isGreaterThanOrEqual(final Number other) {
        return isGreaterThanOrEqual(factory().of(other));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isGreaterThanOrEqual(final BigDecimal other) {
        return isGreaterThanOrEqual(factory().of(other));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num)} {@link NumFactory#of(String)}
     */
    default boolean isGreaterThanOrEqual(final String other) {
        return isGreaterThanOrEqual(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than or equal to the
     * given {@link Num}: <code>this &gt;= other</code> or <code>this ≥ other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is greater than or equal to <code>other</code>, <code>false</code>
     * otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isGreaterThanOrEqual(final Num other);

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(Number)}
     */
    default boolean isGreaterThanOrEqual(final Number other, final Number epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isGreaterThanOrEqual(final Number other, final BigDecimal epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(String)}
     */
    default boolean isGreaterThanOrEqual(final Number other, final String epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    default boolean isGreaterThanOrEqual(final Number other, final Num epsilon) {
        return isGreaterThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(Number)}
     */
    default boolean isGreaterThanOrEqual(final BigDecimal other, final Number epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isGreaterThanOrEqual(final BigDecimal other, final BigDecimal epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(BigDecimal)} {@link NumFactory#of(String)}
     */
    default boolean isGreaterThanOrEqual(final BigDecimal other, final String epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isGreaterThanOrEqual(final BigDecimal other, final Num epsilon) {
        return isGreaterThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(Number)}
     */
    default boolean isGreaterThanOrEqual(final String other, final Number epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isGreaterThanOrEqual(final String other, final BigDecimal epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(String)}
     */
    default boolean isGreaterThanOrEqual(final String other, final String epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    default boolean isGreaterThanOrEqual(final String other, final Num epsilon) {
        return isGreaterThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    default boolean isGreaterThanOrEqual(final Num other, final Number epsilon) {
        return isGreaterThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(BigDecimal)}
     */
    default boolean isGreaterThanOrEqual(final Num other, final BigDecimal epsilon) {
        return isGreaterThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    default boolean isGreaterThanOrEqual(final Num other, final String epsilon) {
        return isGreaterThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly greater than or equal
     * to the given {@link Num}: <code>this - other &gt;= -epsilon</code> or <code>this - other ≥ -epsilon</code>.
     *
     * @param other   the other {@link Num}
     * @param epsilon the epsilon (tolerance) {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is tolerantly greater than or equal to <code>other</code>,
     * <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">Wikipedia</a>
     */
    boolean isGreaterThanOrEqual(final Num other, final Num epsilon);

    /**
     * Checks if this {@link Num} is {@link NaNNum#NaN}.
     *
     * @return <code>true</code> if {@link NaNNum#NaN}, <code>false</code> otherwise
     */
    boolean isNaN();

    /**
     * @return {@link #ifNaN(Num)} {@link NumFactory#of(Number)}
     */
    default Num ifNaN(final Number replacement) {
        return ifNaN(factory().of(replacement));
    }

    /**
     * @return {@link #ifNaN(Num)} {@link NumFactory#of(BigDecimal)}
     */
    default Num ifNaN(final BigDecimal replacement) {
        return ifNaN(factory().of(replacement));
    }

    /**
     * @return {@link #ifNaN(Num)} {@link NumFactory#of(String)}
     */
    default Num ifNaN(final String replacement) {
        return ifNaN(factory().of(replacement));
    }

    /**
     * Returns <code>replacement</code> if {@link #isNaN()}, otherwise returns this {@link Num}.
     *
     * @param replacement the replacement {@link Num}
     *
     * @return the {@link Num}
     */
    Num ifNaN(final Num replacement);

    /**
     * Throws an {@link ArithmeticException} if {@link #isNaN()}, otherwise returns this {@link Num}.
     *
     * @throws ArithmeticException the {@link ArithmeticException}
     */
    Num ifNaNThrow() throws ArithmeticException;

    /**
     * Throws the given {@link RuntimeException} from {@link Supplier#get()} if {@link #isNaN()}, otherwise returns this
     * {@link Num}.
     *
     * @param runtimeException the {@link RuntimeException} {@link Supplier}
     *
     * @throws RuntimeException the given {@link RuntimeException}
     */
    Num ifNaNThrow(final Supplier<RuntimeException> runtimeException) throws RuntimeException;

    /**
     * Throws the given {@link RuntimeException} if {@link #isNaN()}, otherwise returns this {@link Num}.
     *
     * @param runtimeException the {@link RuntimeException}
     *
     * @throws RuntimeException the given {@link RuntimeException}
     */
    Num ifNaNThrow(final RuntimeException runtimeException) throws RuntimeException;

    /**
     * Gets the wrapped {@link Number} value of this {@link Num}.
     *
     * @return the {@link Number}
     */
    Number unwrap();

    /**
     * @return {@link #unwrap()} {@link Number#byteValue()}
     */
    default byte toByte() {
        return unwrap().byteValue();
    }

    /**
     * @return {@link #unwrap()} {@link Number#shortValue()}
     */
    default short toShort() {
        return unwrap().shortValue();
    }

    /**
     * @return {@link #unwrap()} {@link Number#intValue()}
     */
    default int toInt() {
        return unwrap().intValue();
    }

    /**
     * @return {@link #unwrap()} {@link Number#longValue()}
     */
    default long toLong() {
        return unwrap().longValue();
    }

    /**
     * @return {@link #unwrap()} {@link Number#floatValue()}
     */
    default float toFloat() {
        return unwrap().floatValue();
    }

    /**
     * @return {@link #unwrap()} {@link Number#doubleValue()}
     */
    default double toDouble() {
        return unwrap().doubleValue();
    }

    /**
     * Converts this {@link Num} to a {@link BigDecimal}.
     *
     * @return the {@link BigDecimal}
     */
    BigDecimal toBigDecimal();

    /**
     * Gets the {@link MathContext} representing the <code>precision</code> and {@link RoundingMode} used by
     * mathematical operations of this {@link Num}.
     *
     * @return the {@link MathContext}
     */
    MathContext getContext();

    /**
     * Returns {@link MathContext#getPrecision()} from {@link #getContext()}.
     */
    default int getContextPrecision() {
        return getContext().getPrecision();
    }

    /**
     * Returns {@link MathContext#getRoundingMode()} from {@link #getContext()}.
     */
    default RoundingMode getContextRoundingMode() {
        return getContext().getRoundingMode();
    }

    /**
     * Gets the {@link NumFactory} to get {@link Num} instances with the same type as this {@link Num}.
     *
     * @return the {@link NumFactory}
     */
    NumFactory factory();

    /**
     * Performs an {@link Object} equivalence operation. To perform a numerical equivalence operation, use
     * {@link #isEqual(Num)} instead of this method.
     *
     * @param obj the {@link Object} to compare
     *
     * @return <code>true</code> for {@link Object} equality, <code>false</code> otherwise
     */
    @Override
    boolean equals(final Object obj);

    @Override
    int hashCode();

    @Override
    String toString();
}
