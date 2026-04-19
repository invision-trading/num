package trade.invision.num;

import com.google.errorprone.annotations.Immutable;
import lombok.Generated;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

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
 * will yield a {@link NaNNum}.
 *
 * @see DoubleNum
 * @see DecimalNum
 * @see NaNNum
 * @see <a href="https://en.wikipedia.org/wiki/Computer_algebra">
 * wikipedia.org/wiki/Computer_algebra</a>
 */
@NullMarked
@Immutable
public sealed interface Num extends Comparable<Num> permits DoubleNum, DecimalNum, NaNNum {

    /**
     * @return {@link #add(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num add(final Number addend) {
        return add(getFactory().of(addend));
    }

    /**
     * @return {@link #add(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num add(final String addend) {
        return add(getFactory().of(addend));
    }

    /**
     * Performs an addition (plus) operation by adding the given {@link Num} to this {@link Num}: <code>this +
     * addend</code>.
     *
     * @param addend the {@link Num} to add
     *
     * @return the sum {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Addition">
     * wikipedia.org/wiki/Addition</a>
     */
    Num add(final Num addend);

    /**
     * @return {@link #subtract(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num subtract(final Number subtrahend) {
        return subtract(getFactory().of(subtrahend));
    }

    /**
     * @return {@link #subtract(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num subtract(final String subtrahend) {
        return subtract(getFactory().of(subtrahend));
    }

    /**
     * Performs a subtraction (minus) operation by subtracting the given {@link Num} from this {@link Num}: <code>this -
     * subtrahend</code>.
     *
     * @param subtrahend the {@link Num} to subtract
     *
     * @return the difference {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Subtraction">
     * wikipedia.org/wiki/Subtraction</a>
     */
    Num subtract(final Num subtrahend);

    /**
     * @return {@link #multiply(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num multiply(final Number multiplier) {
        return multiply(getFactory().of(multiplier));
    }

    /**
     * @return {@link #multiply(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num multiply(final String multiplier) {
        return multiply(getFactory().of(multiplier));
    }

    /**
     * Performs a multiplication (times) operation by multiplying this {@link Num} by the given {@link Num}: <code>this
     * * multiplier</code>.
     *
     * @param multiplier the {@link Num} to multiply by
     *
     * @return the product {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Multiplication">
     * wikipedia.org/wiki/Multiplication</a>
     */
    Num multiply(final Num multiplier);

    /**
     * @return {@link #divide(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num divide(final Number divisor) {
        return divide(getFactory().of(divisor));
    }

    /**
     * @return {@link #divide(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num divide(final String divisor) {
        return divide(getFactory().of(divisor));
    }

    /**
     * Performs a division (divided by) operation by dividing this {@link Num} by the given {@link Num}: <code>this /
     * divisor</code>.
     *
     * @param divisor the {@link Num} to divide by
     *
     * @return the quotient {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Division_(mathematics)">
     * wikipedia.org/wiki/Division_(mathematics)</a>
     */
    Num divide(final Num divisor);

    /**
     * @return {@link #remainder(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num remainder(final Number divisor) {
        return remainder(getFactory().of(divisor));
    }

    /**
     * @return {@link #remainder(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num remainder(final String divisor) {
        return remainder(getFactory().of(divisor));
    }

    /**
     * Performs a modulo (remainder of) operation by dividing this {@link Num} by the given {@link Num} and yielding the
     * remainder: <code>this % divisor</code>.
     *
     * @param divisor the {@link Num} to divide by
     *
     * @return the remainder {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Remainder">
     * wikipedia.org/wiki/Remainder</a>
     */
    Num remainder(final Num divisor);

    /**
     * @return {@link #power(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num power(final Number exponent) {
        return power(getFactory().of(exponent));
    }

    /**
     * @return {@link #power(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num power(final String exponent) {
        return power(getFactory().of(exponent));
    }

    /**
     * Performs an exponentiation (power) operation by raising this {@link Num} to the given {@link Num}: <code>this ^
     * exponent</code> or <code>this<sup>exponent</sup></code>.
     *
     * @param exponent the {@link Num} to raise to
     *
     * @return the power {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Exponentiation">
     * wikipedia.org/wiki/Exponentiation</a>
     */
    Num power(final Num exponent);

    /**
     * Performs a square (raise to the power of two) operation by multiplying this {@link Num} by itself: <code>this *
     * this</code> or <code>this ^ 2</code> or <code>this<sup>2</sup></code>.
     *
     * @return the squared {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Square_(algebra)">
     * wikipedia.org/wiki/Square_(algebra)</a>
     */
    Num square();

    /**
     * Performs a cube (raise to the power of three) operation by multiplying three instances of this {@link Num}
     * together: <code>this * this * this</code> or <code>this ^ 3</code> or <code>this<sup>3</sup></code>.
     *
     * @return the cubed {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Cube_(algebra)">
     * wikipedia.org/wiki/Cube_(algebra)</a>
     */
    Num cube();

    /**
     * Performs an exponential (<code>e</code> raised to the power of) operation by raising
     * <a href="https://en.wikipedia.org/wiki/Euler%27s_number"><i>e</i> (Euler's number)</a> to this {@link Num}:
     * <code><i>e</i> ^ this</code> or <code>e<sup>this</sup></code>.
     *
     * @return the exponential {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Exponential_function">
     * wikipedia.org/wiki/Exponential_function</a>
     */
    Num exponential();

    /**
     * @return {@link #nthRoot(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num nthRoot(final Number degree) {
        return nthRoot(getFactory().of(degree));
    }

    /**
     * @return {@link #nthRoot(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num nthRoot(final String degree) {
        return nthRoot(getFactory().of(degree));
    }

    /**
     * Performs an <i>n</i>th root (radical) operation using this {@link Num} as the radicand and the given {@link Num}
     * as the degree in the radical: <code><sup>n</sup>√this</code>.
     *
     * @param degree the {@link Num} to use for <i>n</i>
     *
     * @return the root {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Nth_root">
     * wikipedia.org/wiki/Nth_root</a>
     */
    Num nthRoot(final Num degree);

    /**
     * Performs a square root (root two) operation using this {@link Num} as the radicand and <code>2</code> as the
     * degree in the radical: <code><sup>2</sup>√this</code>.
     *
     * @return the square root {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Square_root">
     * wikipedia.org/wiki/Square_root</a>
     */
    Num squareRoot();

    /**
     * Performs a cube root (root three) operation using this {@link Num} as the radicand and <code>3</code> as the
     * degree in the radical: <code><sup>3</sup>√this</code>.
     *
     * @return the cube root {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Cube_root">
     * wikipedia.org/wiki/Cube_root</a>
     */
    Num cubeRoot();

    /**
     * Performs a natural logarithm (logarithm with a base of <i>e</i>) operation using this {@link Num} as the
     * anti-logarithm and <a href="https://en.wikipedia.org/wiki/Euler%27s_number"><i>e</i> (Euler's number)</a> as the
     * base: <code>log<sub>e</sub>this</code> or <code>ln(this)</code>.
     *
     * @return the natural logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Natural_logarithm">
     * wikipedia.org/wiki/Natural_logarithm</a>
     */
    Num ln();

    /**
     * Performs a common logarithm (logarithm with a base of ten) operation using this {@link Num} as the anti-logarithm
     * and <code>10</code> as the base: <code>log<sub>10</sub>this</code> or <code>log10(this)</code>.
     *
     * @return the common logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Common_logarithm">
     * wikipedia.org/wiki/Common_logarithm</a>
     */
    Num log10();

    /**
     * Performs a binary logarithm (logarithm with a base of two) operation using this {@link Num} as the anti-logarithm
     * and <code>2</code> as the base: <code>log<sub>2</sub>this</code> or <code>log2(this)</code>.
     *
     * @return the binary logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Binary_logarithm">
     * wikipedia.org/wiki/Binary_logarithm</a>
     */
    Num log2();

    /**
     * @return {@link #log(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num log(final Number base) {
        return log(getFactory().of(base));
    }

    /**
     * @return {@link #log(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num log(final String base) {
        return log(getFactory().of(base));
    }

    /**
     * Performs a logarithm (log) operation using this {@link Num} as the anti-logarithm and the given {@link Num} as
     * the base: <code>log<sub>base</sub>this</code>.
     *
     * @return the logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Logarithm">
     * wikipedia.org/wiki/Logarithm</a>
     */
    Num log(final Num base);

    /**
     * Performs an absolute value (abs) operation by computing the non-negative value of this {@link Num}:
     * <code>|this|</code>.
     *
     * @return the absolute value {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Absolute_value">
     * wikipedia.org/wiki/Absolute_value</a>
     */
    Num absoluteValue();

    /**
     * Performs a negation (additive inverse) operation by multiplying this {@link Num} by negative one:
     * <code>this * -1</code> or <code>-this</code>.
     *
     * @return the negated {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Additive_inverse">
     * wikipedia.org/wiki/Additive_inverse</a>
     */
    Num negate();

    /**
     * Performs a reciprocal (multiplicative inverse) operation by dividing one by this {@link Num}:
     * <code>1 / this</code> or <code>this<sup>-1</sup></code>.
     *
     * @return the reciprocal {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Multiplicative_inverse">
     * wikipedia.org/wiki/Multiplicative_inverse</a>
     */
    Num reciprocal();

    /**
     * Performs an increment operation by adding one to this {@link Num}: <code>this + 1</code>.
     *
     * @return the incremented {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Increment_and_decrement_operators">
     * wikipedia.org/wiki/Increment_and_decrement_operators</a>
     */
    Num increment();

    /**
     * Performs a decrement operation by subtracting one from this {@link Num}: <code>this - 1</code>.
     *
     * @return the decremented {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Increment_and_decrement_operators">
     * wikipedia.org/wiki/Increment_and_decrement_operators</a>
     */
    Num decrement();

    /**
     * Performs a floor operation by computing the largest (closest to positive infinity) integer that is less than or
     * equal to this {@link Num}: <code>⌊this⌋</code>.
     *
     * @return the floored {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Floor_and_ceiling_functions">
     * wikipedia.org/wiki/Floor_and_ceiling_functions</a>
     */
    Num floor();

    /**
     * Performs a ceil operation by computing the smallest (closest to negative infinity) integer that greater than or
     * equal to this {@link Num}: <code>⌈this⌉</code>.
     *
     * @return the ceiled {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Floor_and_ceiling_functions">
     * wikipedia.org/wiki/Floor_and_ceiling_functions</a>
     */
    Num ceil();

    /**
     * Performs a trigonometric angle conversion operation by converting this {@link Num} in radians to degrees:
     * <code>this°</code>.
     *
     * @return the degrees {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Degree_(angle)">
     * wikipedia.org/wiki/Degree_(angle)</a>
     */
    Num degrees();

    /**
     * Performs a trigonometric angle conversion operation by converting this {@link Num} in degrees to radians:
     * <code>this <i>rad</i></code>.
     *
     * @return the radians {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Radian">
     * wikipedia.org/wiki/Radian</a>
     */
    Num radians();

    /**
     * Performs a trigonometric sine operation of this {@link Num} in radians: <code>sin(this)</code>.
     *
     * @return the sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">
     * wikipedia.org/wiki/Trigonometric_functions</a>
     */
    Num sin();

    /**
     * Performs a trigonometric cosine operation of this {@link Num} in radians: <code>cos(this)</code>.
     *
     * @return the cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">
     * wikipedia.org/wiki/Trigonometric_functions</a>
     */
    Num cos();

    /**
     * Performs a trigonometric tangent operation of this {@link Num} in radians: <code>tan(this)</code>.
     *
     * @return the tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">
     * wikipedia.org/wiki/Trigonometric_functions</a>
     */
    Num tan();

    /**
     * Performs a trigonometric inverse sine operation of this {@link Num} in radians: <code>asin(this)</code>.
     *
     * @return the inverse sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">
     * wikipedia.org/wiki/Inverse_trigonometric_functions</a>
     */
    Num asin();

    /**
     * Performs a trigonometric inverse cosine operation of this {@link Num} in radians: <code>acos(this)</code>.
     *
     * @return the inverse cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">
     * wikipedia.org/wiki/Inverse_trigonometric_functions</a>
     */
    Num acos();

    /**
     * Performs a trigonometric inverse tangent operation of this {@link Num} in radians: <code>atan(this)</code>.
     *
     * @return the inverse tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">
     * wikipedia.org/wiki/Inverse_trigonometric_functions</a>
     */
    Num atan();

    /**
     * @return {@link #atan2(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num atan2(final Number x) {
        return atan2(getFactory().of(x));
    }

    /**
     * @return {@link #atan2(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num atan2(final String x) {
        return atan2(getFactory().of(x));
    }

    /**
     * Performs a trigonometric 2-argument inverse tangent operation using this {@link Num} in radians as <i>y</i> and
     * the given {@link Num} in radians as <i>x</i>: <code>atan2(y,x)</code>.
     *
     * @param x the {@link Num} to use for <i>x</i>
     *
     * @return the 2-argument inverse tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Atan2">
     * wikipedia.org/wiki/Atan2</a>
     */
    Num atan2(final Num x);

    /**
     * Performs a trigonometric hyperbolic sine operation of this {@link Num} in radians: <code>sinh(this)</code>.
     *
     * @return the hyperbolic sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">
     * wikipedia.org/wiki/Hyperbolic_functions</a>
     */
    Num sinh();

    /**
     * Performs a trigonometric hyperbolic cosine operation of this {@link Num} in radians: <code>cosh(this)</code>.
     *
     * @return the hyperbolic cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">
     * wikipedia.org/wiki/Hyperbolic_functions</a>
     */
    Num cosh();

    /**
     * Performs a trigonometric hyperbolic tangent operation of this {@link Num} in radians: <code>tanh(this)</code>.
     *
     * @return the hyperbolic tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">
     * wikipedia.org/wiki/Hyperbolic_functions</a>
     */
    Num tanh();

    /**
     * Performs a trigonometric inverse hyperbolic sine operation of this {@link Num} in radians:
     * <code>asinh(this)</code>.
     *
     * @return the inverse hyperbolic sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">
     * wikipedia.org/wiki/Inverse_hyperbolic_functions</a>
     */
    Num asinh();

    /**
     * Performs a trigonometric inverse hyperbolic cosine operation of this {@link Num} in radians:
     * <code>acosh(this)</code>.
     *
     * @return the inverse hyperbolic cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">
     * wikipedia.org/wiki/Inverse_hyperbolic_functions</a>
     */
    Num acosh();

    /**
     * Performs a trigonometric inverse hyperbolic tangent operation of this {@link Num} in radians:
     * <code>atanh(this)</code>.
     *
     * @return the inverse hyperbolic tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">
     * wikipedia.org/wiki/Inverse_hyperbolic_functions</a>
     */
    Num atanh();

    /**
     * @return {@link #hypotenuse(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num hypotenuse(final Number y) {
        return hypotenuse(getFactory().of(y));
    }

    /**
     * @return {@link #hypotenuse(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num hypotenuse(final String y) {
        return hypotenuse(getFactory().of(y));
    }

    /**
     * Performs a trigonometric hypotenuse (distance formula) operation using this {@link Num} as <i>x</i> and the given
     * {@link Num} as <i>y</i>: <code>√(<i>x</i><sup>2</sup> + <i>y</i><sup>2</sup>)</code>.
     *
     * @param y the {@link Num} to use for <i>y</i>
     *
     * @return the hypotenuse {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hypotenuse">
     * wikipedia.org/wiki/Hypotenuse</a>
     */
    Num hypotenuse(final Num y);

    /**
     * @return {@link #average(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num average(final Number other) {
        return average(getFactory().of(other));
    }

    /**
     * @return {@link #average(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num average(final String other) {
        return average(getFactory().of(other));
    }

    /**
     * Performs an average (mean) operation by dividing the sum of this {@link Num} and the given {@link Num} by two:
     * <code>(this + other) / 2</code>.
     *
     * @param other the other {@link Num}
     *
     * @return the average {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Mean">
     * wikipedia.org/wiki/Mean</a>
     */
    Num average(final Num other);

    /**
     * @return {@link #min(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num min(final Number other) {
        return min(getFactory().of(other));
    }

    /**
     * @return {@link #min(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num min(final String other) {
        return min(getFactory().of(other));
    }

    /**
     * Performs a minimum (minima extrema) operation by computing the lesser of this {@link Num} and the given
     * {@link Num}: <code>min(this, other)</code>.
     *
     * @param other the other {@link Num}
     *
     * @return the minimum {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Maximum_and_minimum">
     * wikipedia.org/wiki/Maximum_and_minimum</a>
     */
    Num min(final Num other);

    /**
     * @return {@link #max(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num max(final Number other) {
        return max(getFactory().of(other));
    }

    /**
     * @return {@link #max(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num max(final String other) {
        return max(getFactory().of(other));
    }

    /**
     * Performs a maximum (maxima extrema) operation by computing the greater of this {@link Num} and the given
     * {@link Num}: <code>max(this, other)</code>.
     *
     * @param other the other {@link Num}
     *
     * @return the maximum {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Maximum_and_minimum">
     * wikipedia.org/wiki/Maximum_and_minimum</a>
     */
    Num max(final Num other);

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(Number)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num clamp(final Number min, final Number max) {
        return clamp(getFactory().of(min), getFactory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(Number)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num clamp(final Number min, final String max) {
        return clamp(getFactory().of(min), getFactory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num clamp(final Number min, final Num max) {
        return clamp(getFactory().of(min), max);
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(String)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num clamp(final String min, final Number max) {
        return clamp(getFactory().of(min), getFactory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(String)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num clamp(final String min, final String max) {
        return clamp(getFactory().of(min), getFactory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num clamp(final String min, final Num max) {
        return clamp(getFactory().of(min), max);
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num clamp(final Num min, final Number max) {
        return clamp(min, getFactory().of(max));
    }

    /**
     * @return {@link #clamp(Num, Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num clamp(final Num min, final String max) {
        return clamp(min, getFactory().of(max));
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
     * @see <a href="https://en.wikipedia.org/wiki/Clamp_(function)">
     * wikipedia.org/wiki/Clamp_(function)</a>
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
     * @see <a href="https://en.wikipedia.org/wiki/Integer_part">
     * wikipedia.org/wiki/Integer_part</a>
     */
    Num integerPart();

    /**
     * Performs a truncation operation by removing the integer part (digits to the left of the decimal point) of this
     * {@link Num} and returning the fractional part: <code>frac(this)</code>.
     *
     * @return the fractional part {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Fractional_part">
     * wikipedia.org/wiki/Fractional_part</a>
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
     * @see <a href="https://en.wikipedia.org/wiki/Rounding">
     * wikipedia.org/wiki/Rounding</a>
     */
    Num round(final int scale, final RoundingMode roundingMode);

    /**
     * @return {@link #significantFigures(int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}
     */
    default Num significantFigures(final int significantFigures) {
        return significantFigures(significantFigures, HALF_EVEN);
    }

    /**
     * @return {@link #significantFigures(MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>
     */
    default Num significantFigures(final int significantFigures, final RoundingMode roundingMode) {
        return significantFigures(new MathContext(significantFigures, roundingMode));
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
     * @see <a href="https://en.wikipedia.org/wiki/Significant_figures">
     * wikipedia.org/wiki/Significant_figures</a>
     */
    Num significantFigures(final MathContext context);

    /**
     * Performs a significant figures count operation on this {@link Num}.
     *
     * @return the significant figures count <code>int</code>
     *
     * @see <a href="https://en.wikipedia.org/wiki/Significant_figures">
     * wikipedia.org/wiki/Significant_figures</a>
     */
    int significantFigures();

    /**
     * Performs a mantissa retrieval operation by computing the <code>mantissa</code> of this {@link Num} as defined by
     * the scientific notation: <code>mantissa * 10<sup>exponent</sup></code>.
     *
     * @return the mantissa {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Scientific_notation">
     * wikipedia.org/wiki/Scientific_notation</a>
     * @see #exponent()
     */
    Num mantissa();

    /**
     * Performs an exponent retrieval operation by computing the <code>exponent</code> of this {@link Num} as defined by
     * the scientific notation: <code>mantissa * 10<sup>exponent</sup></code>.
     *
     * @return the exponent <code>int</code>
     *
     * @see <a href="https://en.wikipedia.org/wiki/Scientific_notation">
     * wikipedia.org/wiki/Scientific_notation</a>
     * @see #mantissa()
     */
    int exponent();

    /**
     * Performs a signum operation on this {@link Num}, yielding <code>-1</code> for negative numbers, <code>1</code>
     * for positive numbers, and <code>0</code> for <code>0</code> or <code>NaN</code>.
     *
     * @return the signum <code>int</code>
     *
     * @see <a href="https://en.wikipedia.org/wiki/Sign_function">
     * wikipedia.org/wiki/Sign_function</a>
     */
    int signum();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than zero: <code>this &lt;
     * 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is less than zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Negative_number">
     * wikipedia.org/wiki/Negative_number</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     */
    boolean isNegative();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than or equal to zero:
     * <code>this &lt;= 0</code> or <code>this ≤ 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is less than or equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Negative_number">
     * wikipedia.org/wiki/Negative_number</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     */
    boolean isNegativeOrZero();

    /**
     * @return {@link #isNegativeOrZero(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isNegativeOrZero(final Number epsilon) {
        return isNegativeOrZero(getFactory().of(epsilon));
    }

    /**
     * @return {@link #isNegativeOrZero(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isNegativeOrZero(final String epsilon) {
        return isNegativeOrZero(getFactory().of(epsilon));
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
     * @see <a href="https://en.wikipedia.org/wiki/Negative_number">
     * wikipedia.org/wiki/Negative_number</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">
     * wikipedia.org/wiki/Machine_epsilon</a>
     */
    boolean isNegativeOrZero(final Num epsilon);

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than zero: <code>this
     * &gt; 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is greater than zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">
     * wikipedia.org/wiki/Positive_number</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     */
    boolean isPositive();

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than or equal to zero:
     * <code>this &gt;= 0</code> or <code>this ≥ 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is greater than or equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">
     * wikipedia.org/wiki/Positive_number</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     */
    boolean isPositiveOrZero();

    /**
     * @return {@link #isPositiveOrZero(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isPositiveOrZero(final Number epsilon) {
        return isPositiveOrZero(getFactory().of(epsilon));
    }

    /**
     * @return {@link #isPositiveOrZero(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isPositiveOrZero(final String epsilon) {
        return isPositiveOrZero(getFactory().of(epsilon));
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
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">
     * wikipedia.org/wiki/Positive_number</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">
     * wikipedia.org/wiki/Machine_epsilon</a>
     */
    boolean isPositiveOrZero(final Num epsilon);

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is equal to zero:
     * <code>this == 0</code>.
     *
     * @return <code>true</code> if this {@link Num} is equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/0">
     * wikipedia.org/wiki/0</a>
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">
     * wikipedia.org/wiki/Equality_(mathematics)</a>
     */
    boolean isZero();

    /**
     * @return {@link #isZero(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isZero(final Number epsilon) {
        return isZero(getFactory().of(epsilon));
    }

    /**
     * @return {@link #isZero(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isZero(final String epsilon) {
        return isZero(getFactory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly equal to zero:
     * <code>|this| &lt;= epsilon</code> or <code>|this| ≤ epsilon</code>.
     *
     * @param epsilon the epsilon (tolerance) {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is tolerantly equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/0">
     * wikipedia.org/wiki/0</a>
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">
     * wikipedia.org/wiki/Equality_(mathematics)</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">
     * wikipedia.org/wiki/Machine_epsilon</a>
     */
    boolean isZero(final Num epsilon);

    /**
     * @return {@link #isEqual(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isEqual(final Number other) {
        return isEqual(getFactory().of(other));
    }

    /**
     * @return {@link #isEqual(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isEqual(final String other) {
        return isEqual(getFactory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is equal to the given {@link Num}:
     * <code>this == other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is equal to <code>other</code>, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">
     * wikipedia.org/wiki/Equality_(mathematics)</a>
     */
    boolean isEqual(final Num other);

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isEqual(final Number other, final Number epsilon) {
        return isEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isEqual(final Number other, final String epsilon) {
        return isEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isEqual(final Number other, final Num epsilon) {
        return isEqual(getFactory().of(other), epsilon);
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isEqual(final String other, final Number epsilon) {
        return isEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isEqual(final String other, final String epsilon) {
        return isEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isEqual(final String other, final Num epsilon) {
        return isEqual(getFactory().of(other), epsilon);
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isEqual(final Num other, final Number epsilon) {
        return isEqual(other, getFactory().of(epsilon));
    }

    /**
     * @return {@link #isEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isEqual(final Num other, final String epsilon) {
        return isEqual(other, getFactory().of(epsilon));
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
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">
     * wikipedia.org/wiki/Equality_(mathematics)</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">
     * wikipedia.org/wiki/Machine_epsilon</a>
     */
    boolean isEqual(final Num other, final Num epsilon);

    /**
     * @return {@link #isLessThan(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isLessThan(final Number other) {
        return isLessThan(getFactory().of(other));
    }

    /**
     * @return {@link #isLessThan(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isLessThan(final String other) {
        return isLessThan(getFactory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than the given {@link Num}:
     * <code>this &lt; other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is less than <code>other</code>, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     */
    boolean isLessThan(final Num other);

    /**
     * @return {@link #isLessThanOrEqual(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isLessThanOrEqual(final Number other) {
        return isLessThanOrEqual(getFactory().of(other));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isLessThanOrEqual(final String other) {
        return isLessThanOrEqual(getFactory().of(other));
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
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     */
    boolean isLessThanOrEqual(final Num other);

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isLessThanOrEqual(final Number other, final Number epsilon) {
        return isLessThanOrEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isLessThanOrEqual(final Number other, final String epsilon) {
        return isLessThanOrEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isLessThanOrEqual(final Number other, final Num epsilon) {
        return isLessThanOrEqual(getFactory().of(other), epsilon);
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isLessThanOrEqual(final String other, final Number epsilon) {
        return isLessThanOrEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isLessThanOrEqual(final String other, final String epsilon) {
        return isLessThanOrEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isLessThanOrEqual(final String other, final Num epsilon) {
        return isLessThanOrEqual(getFactory().of(other), epsilon);
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isLessThanOrEqual(final Num other, final Number epsilon) {
        return isLessThanOrEqual(other, getFactory().of(epsilon));
    }

    /**
     * @return {@link #isLessThanOrEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isLessThanOrEqual(final Num other, final String epsilon) {
        return isLessThanOrEqual(other, getFactory().of(epsilon));
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
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">
     * wikipedia.org/wiki/Machine_epsilon</a>
     */
    boolean isLessThanOrEqual(final Num other, final Num epsilon);

    /**
     * @return {@link #isGreaterThan(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isGreaterThan(final Number other) {
        return isGreaterThan(getFactory().of(other));
    }

    /**
     * @return {@link #isGreaterThan(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isGreaterThan(final String other) {
        return isGreaterThan(getFactory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than the given
     * {@link Num}: <code>this &gt; other</code>.
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is greater than <code>other</code>, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     */
    boolean isGreaterThan(final Num other);

    /**
     * @return {@link #isGreaterThanOrEqual(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final Number other) {
        return isGreaterThanOrEqual(getFactory().of(other));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final String other) {
        return isGreaterThanOrEqual(getFactory().of(other));
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
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     */
    boolean isGreaterThanOrEqual(final Num other);

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final Number other, final Number epsilon) {
        return isGreaterThanOrEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(Number)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final Number other, final String epsilon) {
        return isGreaterThanOrEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final Number other, final Num epsilon) {
        return isGreaterThanOrEqual(getFactory().of(other), epsilon);
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final String other, final Number epsilon) {
        return isGreaterThanOrEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)}
     * {@link NumFactory#of(String)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final String other, final String epsilon) {
        return isGreaterThanOrEqual(getFactory().of(other), getFactory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final String other, final Num epsilon) {
        return isGreaterThanOrEqual(getFactory().of(other), epsilon);
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final Num other, final Number epsilon) {
        return isGreaterThanOrEqual(other, getFactory().of(epsilon));
    }

    /**
     * @return {@link #isGreaterThanOrEqual(Num, Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default boolean isGreaterThanOrEqual(final Num other, final String epsilon) {
        return isGreaterThanOrEqual(other, getFactory().of(epsilon));
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
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">
     * wikipedia.org/wiki/Inequality_(mathematics)</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">
     * wikipedia.org/wiki/Machine_epsilon</a>
     */
    boolean isGreaterThanOrEqual(final Num other, final Num epsilon);

    /**
     * Checks if this {@link Num} is a {@link NaNNum}.
     *
     * @return <code>true</code> if a {@link NaNNum}, <code>false</code> otherwise
     */
    boolean isNaN();

    /**
     * @return {@link #ifNaN(Num)} {@link NumFactory#of(Number)}
     */
    @Generated
    default Num ifNaN(final Number replacement) {
        return ifNaN(getFactory().of(replacement));
    }

    /**
     * @return {@link #ifNaN(Num)} {@link NumFactory#of(String)}
     */
    @Generated
    default Num ifNaN(final String replacement) {
        return ifNaN(getFactory().of(replacement));
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
     * mathematical operations on this {@link Num}.
     *
     * @return the {@link MathContext}
     */
    MathContext getContext();

    /**
     * @return {@link #getContext()} {@link MathContext#getPrecision()}
     */
    default int getContextPrecision() {
        return getContext().getPrecision();
    }

    /**
     * @return {@link #getContext()} {@link MathContext#getRoundingMode()}
     */
    default RoundingMode getContextRoundingMode() {
        return getContext().getRoundingMode();
    }

    /**
     * Gets the {@link NumFactory} to get {@link Num} instances with the same type as this {@link Num} (except if this
     * {@link Num} is a {@link NaNNum}, see {@link NaNNum#nanNum(MathContext, NumFactory)}).
     *
     * @return the {@link NumFactory}
     */
    NumFactory getFactory();

    /**
     * Performs an {@link Object} equivalence operation. To perform a numerical equivalence operation, use
     * {@link #isEqual(Num)} instead of this method.
     *
     * @param obj the {@link Object} to compare
     *
     * @return <code>true</code> for {@link Object} equality, <code>false</code> otherwise
     */
    @Override
    boolean equals(final @Nullable Object obj);

    @Override
    int hashCode();

    @Override
    String toString();

    /**
     * @return {@link #compareTo(Object)} {@link NumFactory#of(Number)}
     */
    @Generated
    default int compareTo(final Number o) {
        return compareTo(getFactory().of(o));
    }

    /**
     * @return {@link #compareTo(Object)} {@link NumFactory#of(String)}
     */
    @Generated
    default int compareTo(final String o) {
        return compareTo(getFactory().of(o));
    }
}
