package trade.invision.num;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_EVEN;

/**
 * {@link Num}, short for "number", is an interface for performing mathematical operations on real decimal numbers.
 * Implementations wrap a {@link Number} instance so that performing mathematical operations on floating-point binary
 * numbers ({@link Double} via {@link DoubleNum}) or arbitrary-precision decimal numbers ({@link BigDecimal} via
 * {@link DecimalNum}) is simple. Object instances of this interface are immutable. All methods in this interface return
 * non-<code>null</code> values or throw a {@link RuntimeException} (usually an {@link ArithmeticException}). All
 * implementations of this interface are interoperable with each other. Operations involving different implementations
 * will result in a {@link Num} that trends towards an increase in precision. For example, subtracting a
 * {@link DecimalNum} from a {@link DoubleNum} will result in a {@link DecimalNum}. For another example, subtracting a
 * {@link DecimalNum} with a {@link DecimalNum#getContextPrecision()} of <code>16</code> from a {@link DecimalNum} with
 * a {@link DecimalNum#getContextPrecision()} of <code>32</code> will result in a {@link DecimalNum} with a
 * {@link DecimalNum#getContextPrecision()} of <code>32</code>. Mathematical operations that result in <code>NaN</code>,
 * <code>+Infinity</code>, <code>-Infinity</code>, or {@link ArithmeticException} will yield {@link NaNNum}.
 *
 * @see DoubleNum
 * @see DecimalNum
 * @see NaNNum
 * @see <a href="https://en.wikipedia.org/wiki/Computer_algebra">Wikipedia</a>
 */
public sealed interface Num extends Comparable<Num> permits DoubleNum, DecimalNum, NaNNum {

    /**
     * Gets the wrapped {@link Number} value of this {@link Num}.
     *
     * @return the {@link Number}
     */
    Number unwrap();

    /**
     * Gets the {@link NumFactory} to create {@link Num} instances with the same type as this {@link Num}.
     *
     * @return the {@link NumFactory}
     */
    NumFactory factory();

    /**
     * @see #add(Num)
     * @see NumFactory#of(Number)
     */
    default Num add(Number addend) {
        return add(factory().of(addend));
    }

    /**
     * @see #add(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num add(BigDecimal addend) {
        return add(factory().of(addend));
    }

    /**
     * @see #add(Num)
     * @see NumFactory#of(String)
     */
    default Num add(String addend) {
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
    Num add(Num addend);

    /**
     * @see #subtract(Num)
     * @see NumFactory#of(Number)
     */
    default Num subtract(Number subtrahend) {
        return subtract(factory().of(subtrahend));
    }

    /**
     * @see #subtract(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num subtract(BigDecimal subtrahend) {
        return subtract(factory().of(subtrahend));
    }

    /**
     * @see #subtract(Num)
     * @see NumFactory#of(String)
     */
    default Num subtract(String subtrahend) {
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
    Num subtract(Num subtrahend);

    /**
     * @see #multiply(Num)
     * @see NumFactory#of(Number)
     */
    default Num multiply(Number multiplier) {
        return multiply(factory().of(multiplier));
    }

    /**
     * @see #multiply(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num multiply(BigDecimal multiplier) {
        return multiply(factory().of(multiplier));
    }

    /**
     * @see #multiply(Num)
     * @see NumFactory#of(String)
     */
    default Num multiply(String multiplier) {
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
    Num multiply(Num multiplier);

    /**
     * @see #divide(Num)
     * @see NumFactory#of(Number)
     */
    default Num divide(Number divisor) {
        return divide(factory().of(divisor));
    }

    /**
     * @see #divide(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num divide(BigDecimal divisor) {
        return divide(factory().of(divisor));
    }

    /**
     * @see #divide(Num)
     * @see NumFactory#of(String)
     */
    default Num divide(String divisor) {
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
    Num divide(Num divisor);

    /**
     * @see #remainder(Number)
     */
    default Num mod(Number divisor) {
        return remainder(divisor);
    }

    /**
     * @see #remainder(BigDecimal)
     */
    default Num mod(BigDecimal divisor) {
        return remainder(divisor);
    }

    /**
     * @see #remainder(String)
     */
    default Num mod(String divisor) {
        return remainder(divisor);
    }

    /**
     * @see #remainder(Num)
     */
    default Num mod(Num divisor) {
        return remainder(divisor);
    }

    /**
     * @see #remainder(Num)
     * @see NumFactory#of(Number)
     */
    default Num remainder(Number divisor) {
        return remainder(factory().of(divisor));
    }

    /**
     * @see #remainder(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num remainder(BigDecimal divisor) {
        return remainder(factory().of(divisor));
    }

    /**
     * @see #remainder(Num)
     * @see NumFactory#of(String)
     */
    default Num remainder(String divisor) {
        return remainder(factory().of(divisor));
    }

    /**
     * Performs a modulo (remainder of) operation by dividing this {@link Num} by the given {@link Num} and yielding the
     * remainder: <code>this % divisor</code>.
     * <br>
     * Shorthand method: {@link #mod(Num)}
     *
     * @param divisor the {@link Num} to divide by
     *
     * @return the remainder {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Remainder">Wikipedia</a>
     */
    Num remainder(Num divisor);

    /**
     * @see #power(Number)
     */
    default Num pow(Number exponent) {
        return power(exponent);
    }

    /**
     * @see #power(BigDecimal)
     */
    default Num pow(BigDecimal exponent) {
        return power(exponent);
    }

    /**
     * @see #power(String)
     */
    default Num pow(String exponent) {
        return power(exponent);
    }

    /**
     * @see #power(Num)
     */
    default Num pow(Num exponent) {
        return power(exponent);
    }

    /**
     * @see #power(Num)
     * @see NumFactory#of(Number)
     */
    default Num power(Number exponent) {
        return power(factory().of(exponent));
    }

    /**
     * @see #power(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num power(BigDecimal exponent) {
        return power(factory().of(exponent));
    }

    /**
     * @see #power(Num)
     * @see NumFactory#of(String)
     */
    default Num power(String exponent) {
        return power(factory().of(exponent));
    }

    /**
     * Performs an exponentiation (power) operation by raising this {@link Num} to the given {@link Num}: <code>this ^
     * exponent</code> or <code>this<sup>exponent</sup></code>.
     * <br>
     * Shorthand method: {@link #pow(Num)}
     *
     * @param exponent the {@link Num} to raise to
     *
     * @return the power {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Exponentiation">Wikipedia</a>
     */
    Num power(Num exponent);

    /**
     * @see #square()
     */
    default Num sq() {
        return square();
    }

    /**
     * Performs a square (raise to the power of two) operation by multiplying this {@link Num} by itself: <code>this *
     * this</code> or <code>this ^ 2</code> or <code>this<sup>2</sup></code>.
     * <br>
     * Shorthand method: {@link #sq()}
     *
     * @return the squared {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Square_(algebra)">Wikipedia</a>
     */
    Num square();

    /**
     * @see #cube()
     */
    default Num cb() {
        return cube();
    }

    /**
     * Performs a cube (raise to the power of three) operation by multiplying three instances of this {@link Num}
     * together: <code>this * this * this</code> or <code>this ^ 3</code> or <code>this<sup>3</sup></code>.
     * <br>
     * Shorthand method: {@link #cb()}
     *
     * @return the cubed {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Cube_(algebra)">Wikipedia</a>
     */
    Num cube();

    /**
     * @see #exponential()
     */
    default Num exp() {
        return exponential();
    }

    /**
     * Performs an exponential (<code>e</code> raised to the power of) operation by raising <a
     * href="https://en.wikipedia.org/wiki/Euler%27s_number"><i>e</i> (Euler's number)</a> to this {@link Num}:
     * <code><i>e</i> ^ this</code> or <code>e<sup>this</sup></code>.
     * <br>
     * Shorthand method: {@link #exp()}
     *
     * @return the exponential {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Exponential_function">Wikipedia</a>
     */
    Num exponential();

    /**
     * @see #nthRoot(Number)
     */
    default Num rt(Number degree) {
        return nthRoot(degree);
    }

    /**
     * @see #nthRoot(BigDecimal)
     */
    default Num rt(BigDecimal degree) {
        return nthRoot(degree);
    }

    /**
     * @see #nthRoot(String)
     */
    default Num rt(String degree) {
        return nthRoot(degree);
    }

    /**
     * @see #nthRoot(Num)
     */
    default Num rt(Num degree) {
        return nthRoot(degree);
    }

    /**
     * @see #nthRoot(Num)
     * @see NumFactory#of(Number)
     */
    default Num nthRoot(Number degree) {
        return nthRoot(factory().of(degree));
    }

    /**
     * @see #nthRoot(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num nthRoot(BigDecimal degree) {
        return nthRoot(factory().of(degree));
    }

    /**
     * @see #nthRoot(Num)
     * @see NumFactory#of(String)
     */
    default Num nthRoot(String degree) {
        return nthRoot(factory().of(degree));
    }

    /**
     * Performs an <i>n</i>th root (radical) operation using this {@link Num} as the radicand and the given {@link Num}
     * as the degree in the radical: <code><sup>n</sup>√this</code>.
     * <br>
     * Shorthand method: {@link #rt(Num)}
     *
     * @param degree the {@link Num} to use for <i>n</i>
     *
     * @return the root {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Nth_root">Wikipedia</a>
     */
    Num nthRoot(Num degree);

    /**
     * @see #squareRoot()
     */
    default Num sqrt() {
        return squareRoot();
    }

    /**
     * Performs a square root (root two) operation using this {@link Num} as the radicand and <code>2</code> as the
     * degree in the radical: <code><sup>2</sup>√this</code>.
     * <br>
     * Shorthand method: {@link #sqrt()}
     *
     * @return the square root {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Square_root">Wikipedia</a>
     */
    Num squareRoot();

    /**
     * @see #cubeRoot()
     */
    default Num cbrt() {
        return cubeRoot();
    }

    /**
     * Performs a cube root (root three) operation using this {@link Num} as the radicand and <code>3</code> as the
     * degree in the radical: <code><sup>3</sup>√this</code>.
     * <br>
     * Shorthand method: {@link #cbrt()}
     *
     * @return the cube root {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Cube_root">Wikipedia</a>
     */
    Num cubeRoot();

    /**
     * @see #naturalLogarithm()
     */
    default Num ln() {
        return naturalLogarithm();
    }

    /**
     * Performs a natural logarithm (logarithm with a base of <i>e</i>) operation using this {@link Num} as the
     * anti-logarithm and <a href="https://en.wikipedia.org/wiki/Euler%27s_number"><i>e</i> (Euler's number)</a> as the
     * base: <code>log<sub>e</sub>this</code> or <code>ln(this)</code>.
     * <br>
     * Shorthand method: {@link #ln()}
     *
     * @return the natural logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Natural_logarithm">Wikipedia</a>
     */
    Num naturalLogarithm();

    /**
     * @see #commonLogarithm()
     */
    default Num log10() {
        return commonLogarithm();
    }

    /**
     * Performs a common logarithm (logarithm with a base of ten) operation using this {@link Num} as the anti-logarithm
     * and <code>10</code> as the base: <code>log<sub>10</sub>this</code> or <code>log10(this)</code>.
     * <br>
     * Shorthand method: {@link #log10()}
     *
     * @return the common logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Common_logarithm">Wikipedia</a>
     */
    Num commonLogarithm();

    /**
     * @see #binaryLogarithm()
     */
    default Num log2() {
        return binaryLogarithm();
    }

    /**
     * Performs a binary logarithm (logarithm with a base of two) operation using this {@link Num} as the anti-logarithm
     * and <code>2</code> as the base: <code>log<sub>2</sub>this</code> or <code>log2(this)</code>.
     * <br>
     * Shorthand method: {@link #log2()}
     *
     * @return the binary logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Binary_logarithm">Wikipedia</a>
     */
    Num binaryLogarithm();

    /**
     * @see #logarithm(Number)
     */
    default Num log(Number base) {
        return logarithm(base);
    }

    /**
     * @see #logarithm(BigDecimal)
     */
    default Num log(BigDecimal base) {
        return logarithm(base);
    }

    /**
     * @see #logarithm(String)
     */
    default Num log(String base) {
        return logarithm(base);
    }

    /**
     * @see #logarithm(Num)
     */
    default Num log(Num base) {
        return logarithm(base);
    }

    /**
     * @see #logarithm(Num)
     * @see NumFactory#of(Number)
     */
    default Num logarithm(Number base) {
        return logarithm(factory().of(base));
    }

    /**
     * @see #logarithm(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num logarithm(BigDecimal base) {
        return logarithm(factory().of(base));
    }

    /**
     * @see #logarithm(Num)
     * @see NumFactory#of(String)
     */
    default Num logarithm(String base) {
        return logarithm(factory().of(base));
    }

    /**
     * Performs a logarithm (log) operation using this {@link Num} as the anti-logarithm and the given {@link Num} as
     * the base: <code>log<sub>base</sub>this</code>.
     * <br>
     * Shorthand method: {@link #log(Num)}
     *
     * @return the logarithm {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Logarithm">Wikipedia</a>
     */
    Num logarithm(Num base);

    /**
     * @see #absoluteValue()
     */
    default Num abs() {
        return absoluteValue();
    }

    /**
     * Performs an absolute value (abs) operation by computing the non-negative value of this {@link Num}:
     * <code>|this|</code>.
     * <br>
     * Shorthand method: {@link #abs()}
     *
     * @return the absolute value {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Absolute_value">Wikipedia</a>
     */
    Num absoluteValue();

    /**
     * @see #negate()
     */
    default Num neg() {
        return negate();
    }

    /**
     * Performs a negation (additive inverse) operation by multiplying this {@link Num} by negative one:
     * <code>this * -1</code> or <code>-this</code>.
     * <br>
     * Shorthand method: {@link #neg()}
     *
     * @return the negated {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Additive_inverse">Wikipedia</a>
     */
    Num negate();

    /**
     * @see #reciprocal()
     */
    default Num recip() {
        return reciprocal();
    }

    /**
     * Performs a reciprocal (multiplicative inverse) operation by dividing one by this {@link Num}:
     * <code>1 / this</code> or <code>this<sup>-1</sup></code>.
     * <br>
     * Shorthand method: {@link #recip()}
     *
     * @return the reciprocal {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Multiplicative_inverse">Wikipedia</a>
     */
    Num reciprocal();

    /**
     * @see #increment()
     */
    default Num inc() {
        return increment();
    }

    /**
     * Performs an increment operation by adding one to this {@link Num}: <code>this + 1</code>.
     * <br>
     * Shorthand method: {@link #inc()}
     *
     * @return the incremented {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Increment_and_decrement_operators">Wikipedia</a>
     */
    Num increment();

    /**
     * @see #decrement()
     */
    default Num dec() {
        return decrement();
    }

    /**
     * Performs a decrement operation by subtracting one from this {@link Num}: <code>this - 1</code>.
     * <br>
     * Shorthand method: {@link #dec()}
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
     * @see #degrees()
     */
    default Num deg() {
        return degrees();
    }

    /**
     * Performs a trigonometric angle conversion operation by converting this {@link Num} in radians to degrees:
     * <code>this°</code>.
     * <br>
     * Shorthand method: {@link #deg()}
     *
     * @return the degrees {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Degree_(angle)">Wikipedia</a>
     */
    Num degrees();

    /**
     * @see #radians()
     */
    default Num rad() {
        return radians();
    }

    /**
     * Performs a trigonometric angle conversion operation by converting this {@link Num} in degrees to radians:
     * <code>this <i>rad</i></code>.
     * <br>
     * Shorthand method: {@link #rad()}
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
     * @see #sine()
     */
    default Num sin() {
        return sine();
    }

    /**
     * Performs a trigonometric sine operation of this {@link Num} in radians: <code>sin(this)</code>.
     * <br>
     * Shorthand method: {@link #sin()}
     *
     * @return the sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">Wikipedia</a>
     */
    Num sine();

    /**
     * @see #cosine()
     */
    default Num cos() {
        return cosine();
    }

    /**
     * Performs a trigonometric cosine operation of this {@link Num} in radians: <code>cos(this)</code>.
     * <br>
     * Shorthand method: {@link #cos()}
     *
     * @return the cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">Wikipedia</a>
     */
    Num cosine();

    /**
     * @see #tangent()
     */
    default Num tan() {
        return tangent();
    }

    /**
     * Performs a trigonometric tangent operation of this {@link Num} in radians: <code>tan(this)</code>.
     * <br>
     * Shorthand method: {@link #tan()}
     *
     * @return the tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Trigonometric_functions">Wikipedia</a>
     */
    Num tangent();

    /**
     * @see #inverseSine()
     */
    default Num asin() {
        return inverseSine();
    }

    /**
     * Performs a trigonometric inverse sine operation of this {@link Num} in radians: <code>asin(this)</code>.
     * <br>
     * Shorthand method: {@link #asin()}
     *
     * @return the inverse sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Wikipedia</a>
     */
    Num inverseSine();

    /**
     * @see #inverseCosine()
     */
    default Num acos() {
        return inverseCosine();
    }

    /**
     * Performs a trigonometric inverse cosine operation of this {@link Num} in radians: <code>acos(this)</code>.
     * <br>
     * Shorthand method: {@link #acos()}
     *
     * @return the inverse cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Wikipedia</a>
     */
    Num inverseCosine();

    /**
     * @see #inverseTangent()
     */
    default Num atan() {
        return inverseTangent();
    }

    /**
     * Performs a trigonometric inverse tangent operation of this {@link Num} in radians: <code>atan(this)</code>.
     * <br>
     * Shorthand method: {@link #atan()}
     *
     * @return the inverse tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Wikipedia</a>
     */
    Num inverseTangent();

    /**
     * @see #inverseTangent2(Number)
     */
    default Num atan2(Number x) {
        return inverseTangent2(x);
    }

    /**
     * @see #inverseTangent2(BigDecimal)
     */
    default Num atan2(BigDecimal x) {
        return inverseTangent2(x);
    }

    /**
     * @see #inverseTangent2(String)
     */
    default Num atan2(String x) {
        return inverseTangent2(x);
    }

    /**
     * @see #inverseTangent2(Num)
     */
    default Num atan2(Num x) {
        return inverseTangent2(x);
    }

    /**
     * @see #inverseTangent2(Num)
     * @see NumFactory#of(Number)
     */
    default Num inverseTangent2(Number x) {
        return inverseTangent2(factory().of(x));
    }

    /**
     * @see #inverseTangent2(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num inverseTangent2(BigDecimal x) {
        return inverseTangent2(factory().of(x));
    }

    /**
     * @see #inverseTangent2(Num)
     * @see NumFactory#of(String)
     */
    default Num inverseTangent2(String x) {
        return inverseTangent2(factory().of(x));
    }

    /**
     * Performs a trigonometric 2-argument inverse tangent operation using this {@link Num} in radians as <i>y</i> and
     * the given {@link Num} in radians as <i>x</i>: <code>atan2(y,x)</code>.
     * <br>
     * Shorthand method: {@link #atan2(Num)}
     *
     * @param x the {@link Num} to use for <i>x</i>
     *
     * @return the 2-argument inverse tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Atan2">Wikipedia</a>
     */
    Num inverseTangent2(Num x);

    /**
     * @see #hyperbolicSine()
     */
    default Num sinh() {
        return hyperbolicSine();
    }

    /**
     * Performs a trigonometric hyperbolic sine operation of this {@link Num} in radians: <code>sinh(this)</code>.
     * <br>
     * Shorthand method: {@link #sinh()}
     *
     * @return the hyperbolic sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">Wikipedia</a>
     */
    Num hyperbolicSine();

    /**
     * @see #hyperbolicCosine()
     */
    default Num cosh() {
        return hyperbolicCosine();
    }

    /**
     * Performs a trigonometric hyperbolic cosine operation of this {@link Num} in radians: <code>cosh(this)</code>.
     * <br>
     * Shorthand method: {@link #cosh()}
     *
     * @return the hyperbolic cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">Wikipedia</a>
     */
    Num hyperbolicCosine();

    /**
     * @see #hyperbolicTangent()
     */
    default Num tanh() {
        return hyperbolicTangent();
    }

    /**
     * Performs a trigonometric hyperbolic tangent operation of this {@link Num} in radians: <code>tanh(this)</code>.
     * <br>
     * Shorthand method: {@link #tanh()}
     *
     * @return the hyperbolic tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hyperbolic_functions">Wikipedia</a>
     */
    Num hyperbolicTangent();

    /**
     * @see #inverseHyperbolicSine()
     */
    default Num asinh() {
        return inverseHyperbolicSine();
    }

    /**
     * Performs a trigonometric inverse hyperbolic sine operation of this {@link Num} in radians:
     * <code>asinh(this)</code>.
     * <br>
     * Shorthand method: {@link #asinh()}
     *
     * @return the inverse hyperbolic sine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">Wikipedia</a>
     */
    Num inverseHyperbolicSine();

    /**
     * @see #inverseHyperbolicCosine()
     */
    default Num acosh() {
        return inverseHyperbolicCosine();
    }

    /**
     * Performs a trigonometric inverse hyperbolic cosine operation of this {@link Num} in radians:
     * <code>acosh(this)</code>.
     * <br>
     * Shorthand method: {@link #acosh()}
     *
     * @return the inverse hyperbolic cosine {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">Wikipedia</a>
     */
    Num inverseHyperbolicCosine();

    /**
     * @see #inverseHyperbolicTangent()
     */
    default Num atanh() {
        return inverseHyperbolicTangent();
    }

    /**
     * Performs a trigonometric inverse hyperbolic tangent operation of this {@link Num} in radians:
     * <code>atanh(this)</code>.
     * <br>
     * Shorthand method: {@link #atanh()}
     *
     * @return the inverse hyperbolic tangent {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_hyperbolic_functions">Wikipedia</a>
     */
    Num inverseHyperbolicTangent();

    /**
     * @see #hypotenuse(Number)
     */
    default Num hypot(Number y) {
        return hypotenuse(y);
    }

    /**
     * @see #hypotenuse(BigDecimal)
     */
    default Num hypot(BigDecimal y) {
        return hypotenuse(y);
    }

    /**
     * @see #hypotenuse(String)
     */
    default Num hypot(String y) {
        return hypotenuse(y);
    }

    /**
     * @see #hypotenuse(Num)
     */
    default Num hypot(Num y) {
        return hypotenuse(y);
    }

    /**
     * @see #hypotenuse(Num)
     * @see NumFactory#of(Number)
     */
    default Num hypotenuse(Number y) {
        return hypotenuse(factory().of(y));
    }

    /**
     * @see #hypotenuse(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num hypotenuse(BigDecimal y) {
        return hypotenuse(factory().of(y));
    }

    /**
     * @see #hypotenuse(Num)
     * @see NumFactory#of(String)
     */
    default Num hypotenuse(String y) {
        return hypotenuse(factory().of(y));
    }

    /**
     * Performs a trigonometric hypotenuse (distance formula) operation using this {@link Num} as <i>x</i> and the given
     * {@link Num} as <i>y</i>: <code>√(<i>x</i><sup>2</sup> + <i>y</i><sup>2</sup>)</code>.
     * <br>
     * Shorthand method: {@link #hypot(Num)}
     *
     * @param y the {@link Num} to use for <i>y</i>
     *
     * @return the hypotenuse {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Hypotenuse">Wikipedia</a>
     */
    Num hypotenuse(Num y);

    /**
     * @see #average(Number)
     */
    default Num avg(Number other) {
        return average(other);
    }

    /**
     * @see #average(BigDecimal)
     */
    default Num avg(BigDecimal other) {
        return average(other);
    }

    /**
     * @see #average(String)
     */
    default Num avg(String other) {
        return average(other);
    }

    /**
     * @see #average(Num)
     */
    default Num avg(Num other) {
        return average(other);
    }

    /**
     * @see #average(Num)
     * @see NumFactory#of(Number)
     */
    default Num average(Number other) {
        return average(factory().of(other));
    }

    /**
     * @see #average(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num average(BigDecimal other) {
        return average(factory().of(other));
    }

    /**
     * @see #average(Num)
     * @see NumFactory#of(String)
     */
    default Num average(String other) {
        return average(factory().of(other));
    }

    /**
     * Performs an average (mean) operation by dividing the sum of this {@link Num} and the given {@link Num} by two:
     * <code>(this + other) / 2</code>.
     * <br>
     * Shorthand method: {@link #avg(Num)}
     *
     * @param other the other {@link Num}
     *
     * @return the average {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Mean">Wikipedia</a>
     */
    Num average(Num other);

    /**
     * @see #minimum(Number)
     */
    default Num min(Number other) {
        return minimum(other);
    }

    /**
     * @see #minimum(BigDecimal)
     */
    default Num min(BigDecimal other) {
        return minimum(other);
    }

    /**
     * @see #minimum(String)
     */
    default Num min(String other) {
        return minimum(other);
    }

    /**
     * @see #minimum(Num)
     */
    default Num min(Num other) {
        return minimum(other);
    }

    /**
     * @see #minimum(Num)
     * @see NumFactory#of(Number)
     */
    default Num minimum(Number other) {
        return minimum(factory().of(other));
    }

    /**
     * @see #minimum(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num minimum(BigDecimal other) {
        return minimum(factory().of(other));
    }

    /**
     * @see #minimum(Num)
     * @see NumFactory#of(String)
     */
    default Num minimum(String other) {
        return minimum(factory().of(other));
    }

    /**
     * Performs a minimum (minima extrema) operation by computing the lesser of this {@link Num} and the given
     * {@link Num}: <code>min(this, other)</code>.
     * <br>
     * Shorthand method: {@link #min(Num)}
     *
     * @param other the other {@link Num}
     *
     * @return the minimum {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Maximum_and_minimum">Wikipedia</a>
     */
    default Num minimum(Num other) {
        return isLessThan(other) ? this : other; // 'NaN' check not necessary when 'other' is on RHS
    }

    /**
     * @see #maximum(Number)
     */
    default Num max(Number other) {
        return maximum(other);
    }

    /**
     * @see #maximum(BigDecimal)
     */
    default Num max(BigDecimal other) {
        return maximum(other);
    }

    /**
     * @see #maximum(String)
     */
    default Num max(String other) {
        return maximum(other);
    }

    /**
     * @see #maximum(Num)
     */
    default Num max(Num other) {
        return maximum(other);
    }

    /**
     * @see #maximum(Num)
     * @see NumFactory#of(Number)
     */
    default Num maximum(Number other) {
        return maximum(factory().of(other));
    }

    /**
     * @see #maximum(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num maximum(BigDecimal other) {
        return maximum(factory().of(other));
    }

    /**
     * @see #maximum(Num)
     * @see NumFactory#of(String)
     */
    default Num maximum(String other) {
        return maximum(factory().of(other));
    }

    /**
     * Performs a maximum (maxima extrema) operation by computing the greater of this {@link Num} and the given
     * {@link Num}: <code>max(this, other)</code>.
     * <br>
     * Shorthand method: {@link #max(Num)}
     *
     * @param other the other {@link Num}
     *
     * @return the maximum {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Maximum_and_minimum">Wikipedia</a>
     */
    default Num maximum(Num other) {
        return isGreaterThan(other) ? this : other; // 'NaN' check not necessary when 'other' is on RHS
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(Number)
     */
    default Num clamp(Number minimum, Number maximum) {
        return clamp(factory().of(minimum), factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(BigDecimal)
     */
    default Num clamp(Number minimum, BigDecimal maximum) {
        return clamp(factory().of(minimum), factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(String)
     */
    default Num clamp(Number minimum, String maximum) {
        return clamp(factory().of(minimum), factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(Number)
     */
    default Num clamp(Number minimum, Num maximum) {
        return clamp(factory().of(minimum), maximum);
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(Number)
     */
    default Num clamp(BigDecimal minimum, Number maximum) {
        return clamp(factory().of(minimum), factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(BigDecimal)
     */
    default Num clamp(BigDecimal minimum, BigDecimal maximum) {
        return clamp(factory().of(minimum), factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(String)
     */
    default Num clamp(BigDecimal minimum, String maximum) {
        return clamp(factory().of(minimum), factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num clamp(BigDecimal minimum, Num maximum) {
        return clamp(factory().of(minimum), maximum);
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(Number)
     */
    default Num clamp(String minimum, Number maximum) {
        return clamp(factory().of(minimum), factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(BigDecimal)
     */
    default Num clamp(String minimum, BigDecimal maximum) {
        return clamp(factory().of(minimum), factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(String)
     */
    default Num clamp(String minimum, String maximum) {
        return clamp(factory().of(minimum), factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(String)
     */
    default Num clamp(String minimum, Num maximum) {
        return clamp(factory().of(minimum), maximum);
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(Number)
     */
    default Num clamp(Num minimum, Number maximum) {
        return clamp(minimum, factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num clamp(Num minimum, BigDecimal maximum) {
        return clamp(minimum, factory().of(maximum));
    }

    /**
     * @see #clamp(Num, Num)
     * @see NumFactory#of(String)
     */
    default Num clamp(Num minimum, String maximum) {
        return clamp(minimum, factory().of(maximum));
    }

    /**
     * Performs a clamp operation by limiting this value to the range between the given <code>minimum</code> and
     * <code>maximum</code>: <code>min(maximum, max(this, minimum))</code>.
     *
     * @param minimum the minimum {@link Num}
     * @param maximum the maximum {@link Num}
     *
     * @return the maximum {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Clamp_(function)">Wikipedia</a>
     */
    default Num clamp(Num minimum, Num maximum) {
        return maximum.minimum(maximum(minimum));
    }

    /**
     * @see #integerPart()
     */
    default Num intPart() {
        return integerPart();
    }

    /**
     * Performs a truncation operation by removing the fractional part (digits to the right of the decimal point) of
     * this {@link Num} and returning the integer part: <code>int(this)</code>.
     * <br>
     * Shorthand method: {@link #intPart()}
     *
     * @return the integer part {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Integer_part">Wikipedia</a>
     */
    Num integerPart();

    /**
     * @see #fractionalPart()
     */
    default Num fracPart() {
        return fractionalPart();
    }

    /**
     * Performs a truncation operation by removing the integer part (digits to the left of the decimal point) of this
     * {@link Num} and returning the fractional part: <code>frac(this)</code>.
     * <br>
     * Shorthand method: {@link #fracPart()}
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
     * @see #significantFigures(int)
     */
    default Num sigFigs(int significantFigures) {
        return significantFigures(significantFigures);
    }

    /**
     * @see #significantFigures(int, RoundingMode)
     */
    default Num sigFigs(int significantFigures, RoundingMode roundingMode) {
        return significantFigures(significantFigures, roundingMode);
    }

    /**
     * @see #significantFigures(MathContext)
     */
    default Num sigFigs(MathContext context) {
        return significantFigures(context);
    }

    /**
     * Calls {@link #significantFigures(int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    default Num significantFigures(int significantFigures) {
        return significantFigures(significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #significantFigures(MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    default Num significantFigures(int significantFigures, RoundingMode roundingMode) {
        return significantFigures(new MathContext(significantFigures, roundingMode));
    }

    /**
     * Performs a precision modification operation by setting the number of significant figures in this {@link Num} to
     * the given {@link MathContext#getPrecision()} and rounding excess significant figures according to the given
     * {@link MathContext#getRoundingMode()}.
     * <br>
     * Shorthand method: {@link #sigFigs(MathContext)}
     *
     * @param context the {@link MathContext}
     *
     * @return the precision-modified {@link Num}
     *
     * @see <a href="https://en.wikipedia.org/wiki/Significant_figures">Wikipedia</a>
     */
    Num significantFigures(MathContext context);

    /**
     * @see #significantFigures()
     */
    default int sigFigs() {
        return significantFigures();
    }

    /**
     * Performs a significant figures (sig figs) count operation on this {@link Num}.
     * <br>
     * Shorthand method: {@link #sigFigs()}
     *
     * @return the significant figures count <code>int</code>
     *
     * @see <a href="https://en.wikipedia.org/wiki/Significant_figures">Wikipedia</a>
     */
    int significantFigures();

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
     * for positive numbers, and <code>0</code> for all other values (<code>0</code> or <code>NaN</code>).
     *
     * @return the signum <code>int</code>
     *
     * @see <a href="https://en.wikipedia.org/wiki/Sign_function">Wikipedia</a>
     */
    int signum();

    /**
     * @see #isNegative()
     */
    default boolean lt0() {
        return isNegative();
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than zero: <code>this &lt;
     * 0</code>.
     * <br>
     * Shorthand method: {@link #lt0()}
     *
     * @return <code>true</code> if this {@link Num} is less than zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Negative_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isNegative();

    /**
     * @see #isNegativeOrZero()
     */
    default boolean le0() {
        return isNegativeOrZero();
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than or equal to zero:
     * <code>this &lt;= 0</code> or <code>this ≤ 0</code>.
     * <br>
     * Shorthand method: {@link #le0()}
     *
     * @return <code>true</code> if this {@link Num} is less than or equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Negative_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isNegativeOrZero();

    /**
     * @see #isNegativeOrZero(Number)
     */
    default boolean le0(Number epsilon) {
        return isNegativeOrZero(epsilon);
    }

    /**
     * @see #isNegativeOrZero(BigDecimal)
     */
    default boolean le0(BigDecimal epsilon) {
        return isNegativeOrZero(epsilon);
    }

    /**
     * @see #isNegativeOrZero(String)
     */
    default boolean le0(String epsilon) {
        return isNegativeOrZero(epsilon);
    }

    /**
     * @see #isNegativeOrZero(Num)
     */
    default boolean le0(Num epsilon) {
        return isNegativeOrZero(epsilon);
    }

    /**
     * @see #isNegativeOrZero(Num)
     * @see NumFactory#of(Number)
     */
    default boolean isNegativeOrZero(Number epsilon) {
        return isNegativeOrZero(factory().of(epsilon));
    }

    /**
     * @see #isNegativeOrZero(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isNegativeOrZero(BigDecimal epsilon) {
        return isNegativeOrZero(factory().of(epsilon));
    }

    /**
     * @see #isNegativeOrZero(Num)
     * @see NumFactory#of(String)
     */
    default boolean isNegativeOrZero(String epsilon) {
        return isNegativeOrZero(factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly less than or equal to
     * zero: <code>this &lt;= epsilon</code> or <code>this ≤ epsilon</code>.
     * <br>
     * Shorthand method: {@link #le0(Num)}
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
    boolean isNegativeOrZero(Num epsilon);

    /**
     * @see #isPositive()
     */
    default boolean gt0() {
        return isPositive();
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than zero: <code>this
     * &gt; 0</code>.
     * <br>
     * Shorthand method: {@link #gt0()}
     *
     * @return <code>true</code> if this {@link Num} is greater than zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isPositive();

    /**
     * @see #isPositiveOrZero()
     */
    default boolean ge0() {
        return isPositiveOrZero();
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than or equal to zero:
     * <code>this &gt;= 0</code> or <code>this ≥ 0</code>.
     * <br>
     * Shorthand method: {@link #ge0()}
     *
     * @return <code>true</code> if this {@link Num} is greater than or equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Positive_number">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isPositiveOrZero();

    /**
     * @see #isPositiveOrZero(Number)
     */
    default boolean ge0(Number epsilon) {
        return isPositiveOrZero(epsilon);
    }

    /**
     * @see #isPositiveOrZero(BigDecimal)
     */
    default boolean ge0(BigDecimal epsilon) {
        return isPositiveOrZero(epsilon);
    }

    /**
     * @see #isPositiveOrZero(String)
     */
    default boolean ge0(String epsilon) {
        return isPositiveOrZero(epsilon);
    }

    /**
     * @see #isPositiveOrZero(Num)
     */
    default boolean ge0(Num epsilon) {
        return isPositiveOrZero(epsilon);
    }

    /**
     * @see #isPositiveOrZero(Num)
     * @see NumFactory#of(Number)
     */
    default boolean isPositiveOrZero(Number epsilon) {
        return isPositiveOrZero(factory().of(epsilon));
    }

    /**
     * @see #isPositiveOrZero(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isPositiveOrZero(BigDecimal epsilon) {
        return isPositiveOrZero(factory().of(epsilon));
    }

    /**
     * @see #isPositiveOrZero(Num)
     * @see NumFactory#of(String)
     */
    default boolean isPositiveOrZero(String epsilon) {
        return isPositiveOrZero(factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly greater than or equal
     * to zero: <code>this &gt;= -epsilon</code> or <code>this ≥ -epsilon</code>.
     * <br>
     * Shorthand method: {@link #ge0(Num)}
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
    boolean isPositiveOrZero(Num epsilon);

    /**
     * @see #isZero()
     */
    default boolean eq0() {
        return isZero();
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is equal to zero:
     * <code>this == 0</code>.
     * <br>
     * Shorthand method: {@link #eq0()}
     *
     * @return <code>true</code> if this {@link Num} is equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/0">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     */
    boolean isZero();

    /**
     * @see #isZero(Number)
     */
    default boolean eq0(Number epsilon) {
        return isZero(epsilon);
    }

    /**
     * @see #isZero(BigDecimal)
     */
    default boolean eq0(BigDecimal epsilon) {
        return isZero(epsilon);
    }

    /**
     * @see #isZero(String)
     */
    default boolean eq0(String epsilon) {
        return isZero(epsilon);
    }

    /**
     * @see #isZero(Num)
     */
    default boolean eq0(Num epsilon) {
        return isZero(epsilon);
    }

    /**
     * @see #isZero(Num)
     * @see NumFactory#of(Number)
     */
    default boolean isZero(Number epsilon) {
        return isZero(factory().of(epsilon));
    }

    /**
     * @see #isZero(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isZero(BigDecimal epsilon) {
        return isZero(factory().of(epsilon));
    }

    /**
     * @see #isZero(Num)
     * @see NumFactory#of(String)
     */
    default boolean isZero(String epsilon) {
        return isZero(factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly equal to zero:
     * <code>|this| &lt;= epsilon</code> or <code>|this| ≤ epsilon</code>.
     * <br>
     * Shorthand method: {@link #eq0(Num)}
     *
     * @param epsilon the epsilon (tolerance) {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is tolerantly equal to zero, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/0">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     * @see <a href="https://en.wikipedia.org/wiki/Machine_epsilon">Wikipedia</a>
     */
    boolean isZero(Num epsilon);

    /**
     * @see #isEqual(Number)
     */
    default boolean eq(Number other) {
        return isEqual(other);
    }

    /**
     * @see #isEqual(BigDecimal)
     */
    default boolean eq(BigDecimal other) {
        return isEqual(other);
    }

    /**
     * @see #isEqual(String)
     */
    default boolean eq(String other) {
        return isEqual(other);
    }

    /**
     * @see #isEqual(Num)
     */
    default boolean eq(Num other) {
        return isEqual(other);
    }

    /**
     * @see #isEqual(Num)
     * @see NumFactory#of(Number)
     */
    default boolean isEqual(Number other) {
        return isEqual(factory().of(other));
    }

    /**
     * @see #isEqual(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isEqual(BigDecimal other) {
        return isEqual(factory().of(other));
    }

    /**
     * @see #isEqual(Num)
     * @see NumFactory#of(String)
     */
    default boolean isEqual(String other) {
        return isEqual(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is equal to the given {@link Num}:
     * <code>this == other</code>.
     * <br>
     * Shorthand method: {@link #eq(Num)}
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is equal to <code>other</code>, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Equality_(mathematics)">Wikipedia</a>
     */
    boolean isEqual(Num other);

    /**
     * @see #isEqual(Number, Number)
     */
    default boolean eq(Number other, Number epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(Number, BigDecimal)
     */
    default boolean eq(Number other, BigDecimal epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(Number, String)
     */
    default boolean eq(Number other, String epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(Number, Num)
     */
    default boolean eq(Number other, Num epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(BigDecimal, Number)
     */
    default boolean eq(BigDecimal other, Number epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(BigDecimal, BigDecimal)
     */
    default boolean eq(BigDecimal other, BigDecimal epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(BigDecimal, String)
     */
    default boolean eq(BigDecimal other, String epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(BigDecimal, Num)
     */
    default boolean eq(BigDecimal other, Num epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(String, Number)
     */
    default boolean eq(String other, Number epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(String, BigDecimal)
     */
    default boolean eq(String other, BigDecimal epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(String, String)
     */
    default boolean eq(String other, String epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(String, Num)
     */
    default boolean eq(String other, Num epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(Num, Number)
     */
    default boolean eq(Num other, Number epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(Num, BigDecimal)
     */
    default boolean eq(Num other, BigDecimal epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(Num, String)
     */
    default boolean eq(Num other, String epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(Num, Num)
     */
    default boolean eq(Num other, Num epsilon) {
        return isEqual(other, epsilon);
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(Number)
     */
    default boolean isEqual(Number other, Number epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isEqual(Number other, BigDecimal epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(String)
     */
    default boolean isEqual(Number other, String epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(Number)
     */
    default boolean isEqual(Number other, Num epsilon) {
        return isEqual(factory().of(other), epsilon);
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(Number)
     */
    default boolean isEqual(BigDecimal other, Number epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isEqual(BigDecimal other, BigDecimal epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(String)
     */
    default boolean isEqual(BigDecimal other, String epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isEqual(BigDecimal other, Num epsilon) {
        return isEqual(factory().of(other), epsilon);
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(Number)
     */
    default boolean isEqual(String other, Number epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isEqual(String other, BigDecimal epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(String)
     */
    default boolean isEqual(String other, String epsilon) {
        return isEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(String)
     */
    default boolean isEqual(String other, Num epsilon) {
        return isEqual(factory().of(other), epsilon);
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(Number)
     */
    default boolean isEqual(Num other, Number epsilon) {
        return isEqual(other, factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isEqual(Num other, BigDecimal epsilon) {
        return isEqual(other, factory().of(epsilon));
    }

    /**
     * @see #isEqual(Num, Num)
     * @see NumFactory#of(String)
     */
    default boolean isEqual(Num other, String epsilon) {
        return isEqual(other, factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly equal to the given
     * {@link Num}: <code>|this - other| &lt;= epsilon</code>.
     * <br>
     * Shorthand method: {@link #eq(Num, Num)}
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
    boolean isEqual(Num other, Num epsilon);

    /**
     * @see #isLessThan(Number)
     */
    default boolean lt(Number other) {
        return isLessThan(other);
    }

    /**
     * @see #isLessThan(BigDecimal)
     */
    default boolean lt(BigDecimal other) {
        return isLessThan(other);
    }

    /**
     * @see #isLessThan(String)
     */
    default boolean lt(String other) {
        return isLessThan(other);
    }

    /**
     * @see #isLessThan(Num)
     */
    default boolean lt(Num other) {
        return isLessThan(other);
    }

    /**
     * @see #isLessThan(Num)
     * @see NumFactory#of(Number)
     */
    default boolean isLessThan(Number other) {
        return isLessThan(factory().of(other));
    }

    /**
     * @see #isLessThan(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isLessThan(BigDecimal other) {
        return isLessThan(factory().of(other));
    }

    /**
     * @see #isLessThan(Num)
     * @see NumFactory#of(String)
     */
    default boolean isLessThan(String other) {
        return isLessThan(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than the given {@link Num}:
     * <code>this &lt; other</code>.
     * <br>
     * Shorthand method: {@link #lt(Num)}
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is less than <code>other</code>, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isLessThan(Num other);

    /**
     * @see #isLessThanOrEqual(Number)
     */
    default boolean le(Number other) {
        return isLessThanOrEqual(other);
    }

    /**
     * @see #isLessThanOrEqual(BigDecimal)
     */
    default boolean le(BigDecimal other) {
        return isLessThanOrEqual(other);
    }

    /**
     * @see #isLessThanOrEqual(String)
     */
    default boolean le(String other) {
        return isLessThanOrEqual(other);
    }

    /**
     * @see #isLessThanOrEqual(Num)
     */
    default boolean le(Num other) {
        return isLessThanOrEqual(other);
    }

    /**
     * @see #isLessThanOrEqual(Num)
     * @see NumFactory#of(Number)
     */
    default boolean isLessThanOrEqual(Number other) {
        return isLessThanOrEqual(factory().of(other));
    }

    /**
     * @see #isLessThanOrEqual(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isLessThanOrEqual(BigDecimal other) {
        return isLessThanOrEqual(factory().of(other));
    }

    /**
     * @see #isLessThanOrEqual(Num)
     * @see NumFactory#of(String)
     */
    default boolean isLessThanOrEqual(String other) {
        return isLessThanOrEqual(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is less than or equal to the given
     * {@link Num}: <code>this &lt;= other</code> or <code>this ≤ other</code>.
     * <br>
     * Shorthand method: {@link #le(Num)}
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is less than or equal to <code>other</code>, <code>false</code>
     * otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isLessThanOrEqual(Num other);

    /**
     * @see #isLessThanOrEqual(Number, Number)
     */
    default boolean le(Number other, Number epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Number, BigDecimal)
     */
    default boolean le(Number other, BigDecimal epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Number, String)
     */
    default boolean le(Number other, String epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Number, Num)
     */
    default boolean le(Number other, Num epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(BigDecimal, Number)
     */
    default boolean le(BigDecimal other, Number epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(BigDecimal, BigDecimal)
     */
    default boolean le(BigDecimal other, BigDecimal epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(BigDecimal, String)
     */
    default boolean le(BigDecimal other, String epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(BigDecimal, Num)
     */
    default boolean le(BigDecimal other, Num epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(String, Number)
     */
    default boolean le(String other, Number epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(String, BigDecimal)
     */
    default boolean le(String other, BigDecimal epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(String, String)
     */
    default boolean le(String other, String epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(String, Num)
     */
    default boolean le(String other, Num epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Num, Number)
     */
    default boolean le(Num other, Number epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Num, BigDecimal)
     */
    default boolean le(Num other, BigDecimal epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Num, String)
     */
    default boolean le(Num other, String epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     */
    default boolean le(Num other, Num epsilon) {
        return isLessThanOrEqual(other, epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(Number)
     */
    default boolean isLessThanOrEqual(Number other, Number epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isLessThanOrEqual(Number other, BigDecimal epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(String)
     */
    default boolean isLessThanOrEqual(Number other, String epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     */
    default boolean isLessThanOrEqual(Number other, Num epsilon) {
        return isLessThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(Number)
     */
    default boolean isLessThanOrEqual(BigDecimal other, Number epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isLessThanOrEqual(BigDecimal other, BigDecimal epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(String)
     */
    default boolean isLessThanOrEqual(BigDecimal other, String epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isLessThanOrEqual(BigDecimal other, Num epsilon) {
        return isLessThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(Number)
     */
    default boolean isLessThanOrEqual(String other, Number epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isLessThanOrEqual(String other, BigDecimal epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(String)
     */
    default boolean isLessThanOrEqual(String other, String epsilon) {
        return isLessThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     */
    default boolean isLessThanOrEqual(String other, Num epsilon) {
        return isLessThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     */
    default boolean isLessThanOrEqual(Num other, Number epsilon) {
        return isLessThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isLessThanOrEqual(Num other, BigDecimal epsilon) {
        return isLessThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * @see #isLessThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     */
    default boolean isLessThanOrEqual(Num other, String epsilon) {
        return isLessThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly less than or equal to
     * the given {@link Num}: <code>other - this &gt;= -epsilon</code> or <code>other - this ≥ -epsilon</code>.
     * <br>
     * Shorthand method: {@link #le(Num, Num)}
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
    boolean isLessThanOrEqual(Num other, Num epsilon);

    /**
     * @see #isGreaterThan(Number)
     */
    default boolean gt(Number other) {
        return isGreaterThan(other);
    }

    /**
     * @see #isGreaterThan(BigDecimal)
     */
    default boolean gt(BigDecimal other) {
        return isGreaterThan(other);
    }

    /**
     * @see #isGreaterThan(String)
     */
    default boolean gt(String other) {
        return isGreaterThan(other);
    }

    /**
     * @see #isGreaterThan(Num)
     */
    default boolean gt(Num other) {
        return isGreaterThan(other);
    }

    /**
     * @see #isGreaterThan(Num)
     * @see NumFactory#of(Number)
     */
    default boolean isGreaterThan(Number other) {
        return isGreaterThan(factory().of(other));
    }

    /**
     * @see #isGreaterThan(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isGreaterThan(BigDecimal other) {
        return isGreaterThan(factory().of(other));
    }

    /**
     * @see #isGreaterThan(Num)
     * @see NumFactory#of(String)
     */
    default boolean isGreaterThan(String other) {
        return isGreaterThan(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than the given
     * {@link Num}: <code>this &gt; other</code>.
     * <br>
     * Shorthand method: {@link #gt(Num)}
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is greater than <code>other</code>, <code>false</code> otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isGreaterThan(Num other);

    /**
     * @see #isGreaterThanOrEqual(Number)
     */
    default boolean ge(Number other) {
        return isGreaterThanOrEqual(other);
    }

    /**
     * @see #isGreaterThanOrEqual(BigDecimal)
     */
    default boolean ge(BigDecimal other) {
        return isGreaterThanOrEqual(other);
    }

    /**
     * @see #isGreaterThanOrEqual(String)
     */
    default boolean ge(String other) {
        return isGreaterThanOrEqual(other);
    }

    /**
     * @see #isGreaterThanOrEqual(Num)
     */
    default boolean ge(Num other) {
        return isGreaterThanOrEqual(other);
    }

    /**
     * @see #isGreaterThanOrEqual(Num)
     * @see NumFactory#of(Number)
     */
    default boolean isGreaterThanOrEqual(Number other) {
        return isGreaterThanOrEqual(factory().of(other));
    }

    /**
     * @see #isGreaterThanOrEqual(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isGreaterThanOrEqual(BigDecimal other) {
        return isGreaterThanOrEqual(factory().of(other));
    }

    /**
     * @see #isGreaterThanOrEqual(Num)
     * @see NumFactory#of(String)
     */
    default boolean isGreaterThanOrEqual(String other) {
        return isGreaterThanOrEqual(factory().of(other));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is greater than or equal to the
     * given {@link Num}: <code>this &gt;= other</code> or <code>this ≥ other</code>.
     * <br>
     * Shorthand method: {@link #ge(Num)}
     *
     * @param other the other {@link Num}
     *
     * @return <code>true</code> if this {@link Num} is greater than or equal to <code>other</code>, <code>false</code>
     * otherwise
     *
     * @see <a href="https://en.wikipedia.org/wiki/Inequality_(mathematics)">Wikipedia</a>
     */
    boolean isGreaterThanOrEqual(Num other);

    /**
     * @see #isGreaterThanOrEqual(Number, Number)
     */
    default boolean ge(Number other, Number epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Number, BigDecimal)
     */
    default boolean ge(Number other, BigDecimal epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Number, String)
     */
    default boolean ge(Number other, String epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Number, Num)
     */
    default boolean ge(Number other, Num epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(BigDecimal, Number)
     */
    default boolean ge(BigDecimal other, Number epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(BigDecimal, BigDecimal)
     */
    default boolean ge(BigDecimal other, BigDecimal epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(BigDecimal, String)
     */
    default boolean ge(BigDecimal other, String epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(BigDecimal, Num)
     */
    default boolean ge(BigDecimal other, Num epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(String, Number)
     */
    default boolean ge(String other, Number epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(String, BigDecimal)
     */
    default boolean ge(String other, BigDecimal epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(String, String)
     */
    default boolean ge(String other, String epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(String, Num)
     */
    default boolean ge(String other, Num epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Number)
     */
    default boolean ge(Num other, Number epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Num, BigDecimal)
     */
    default boolean ge(Num other, BigDecimal epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Num, String)
     */
    default boolean ge(Num other, String epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     */
    default boolean ge(Num other, Num epsilon) {
        return isGreaterThanOrEqual(other, epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(Number)
     */
    default boolean isGreaterThanOrEqual(Number other, Number epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isGreaterThanOrEqual(Number other, BigDecimal epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     * @see NumFactory#of(String)
     */
    default boolean isGreaterThanOrEqual(Number other, String epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     */
    default boolean isGreaterThanOrEqual(Number other, Num epsilon) {
        return isGreaterThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(Number)
     */
    default boolean isGreaterThanOrEqual(BigDecimal other, Number epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isGreaterThanOrEqual(BigDecimal other, BigDecimal epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     * @see NumFactory#of(String)
     */
    default boolean isGreaterThanOrEqual(BigDecimal other, String epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isGreaterThanOrEqual(BigDecimal other, Num epsilon) {
        return isGreaterThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(Number)
     */
    default boolean isGreaterThanOrEqual(String other, Number epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isGreaterThanOrEqual(String other, BigDecimal epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     * @see NumFactory#of(String)
     */
    default boolean isGreaterThanOrEqual(String other, String epsilon) {
        return isGreaterThanOrEqual(factory().of(other), factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     */
    default boolean isGreaterThanOrEqual(String other, Num epsilon) {
        return isGreaterThanOrEqual(factory().of(other), epsilon);
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(Number)
     */
    default boolean isGreaterThanOrEqual(Num other, Number epsilon) {
        return isGreaterThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(BigDecimal)
     */
    default boolean isGreaterThanOrEqual(Num other, BigDecimal epsilon) {
        return isGreaterThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * @see #isGreaterThanOrEqual(Num, Num)
     * @see NumFactory#of(String)
     */
    default boolean isGreaterThanOrEqual(Num other, String epsilon) {
        return isGreaterThanOrEqual(other, factory().of(epsilon));
    }

    /**
     * Performs a mathematical comparison operation to determine if this {@link Num} is tolerantly greater than or equal
     * to the given {@link Num}: <code>this - other &gt;= -epsilon</code> or <code>this - other ≥ -epsilon</code>.
     * <br>
     * Shorthand method: {@link #ge(Num, Num)}
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
    boolean isGreaterThanOrEqual(Num other, Num epsilon);

    /**
     * Checks if this {@link Num} is {@link NaNNum#NaN}.
     *
     * @return <code>true</code> if {@link NaNNum#NaN}, <code>false</code> otherwise
     */
    boolean isNaN();

    /**
     * @see #ifNaN(Num)
     * @see NumFactory#of(Number)
     */
    default Num ifNaN(Number replacement) {
        return ifNaN(factory().of(replacement));
    }

    /**
     * @see #ifNaN(Num)
     * @see NumFactory#of(BigDecimal)
     */
    default Num ifNaN(BigDecimal replacement) {
        return ifNaN(factory().of(replacement));
    }

    /**
     * @see #ifNaN(Num)
     * @see NumFactory#of(String)
     */
    default Num ifNaN(String replacement) {
        return ifNaN(factory().of(replacement));
    }

    /**
     * Returns <code>replacement</code> if this {@link Num} is {@link NaNNum#NaN}, otherwise, returns this {@link Num}.
     *
     * @param replacement the replacement {@link Num}
     *
     * @return the {@link Num}
     */
    Num ifNaN(Num replacement);

    /**
     * Converts this {@link Num} to a <code>byte</code>.
     *
     * @return the <code>byte</code>
     *
     * @see #unwrap()
     * @see Number#byteValue()
     */
    default byte toByte() {
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
    default short toShort() {
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
    default int toInt() {
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
    default long toLong() {
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
    default float toFloat() {
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
