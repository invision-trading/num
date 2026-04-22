package trade.invision.num;

import lombok.Generated;
import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Supplier;

/**
 * {@link NaNNum}, short for "Not a Number" (NaN), is a {@link Num} that represents an undefined and unrepresentable
 * numerical value. All operations result in itself or <code>0</code>. It is not equal to itself or any other value. It
 * compares unordered with itself and all other values. It is not negative, positive, or zero.
 *
 * @see <a href="https://en.wikipedia.org/wiki/NaN">wikipedia.org/wiki/NaN</a>
 * @see <a href="https://stackoverflow.com/a/1573715/4352701">stackoverflow.com - What is the rationale for all
 * comparisons returning false for IEEE754 NaN values?</a>
 */
@NullMarked
public final class NaNNum implements Num {

    /**
     * The {@link #toString()} constant: <code>"NaN"</code>
     */
    public static final String STRING = "NaN";

    /**
     * Creates a new {@link NaNNum}.
     * <p>
     * The given {@link MathContext} and {@link NumFactory} should be set to the {@link #getContext()} and
     * {@link #getFactory()} of the {@link Num} instance that caused the NaN result.
     *
     * @param context the {@link MathContext} to return for {@link #getContext()}
     * @param factory the {@link NumFactory} to return for {@link #getFactory()}
     *
     * @return the {@link NaNNum}
     */
    public static Num nanNum(final MathContext context, final NumFactory factory) {
        return new NaNNum(context, factory);
    }

    private final @SuppressWarnings("Immutable") MathContext context;
    private final NumFactory factory;

    private NaNNum(final MathContext context, final NumFactory factory) {
        this.context = context;
        this.factory = factory;
    }

    @Generated
    @Override
    public Num add(final Number addend) {
        return this;
    }

    @Generated
    @Override
    public Num add(final String addend) {
        return this;
    }

    @Override
    public Num add(final Num addend) {
        return this;
    }

    @Generated
    @Override
    public Num subtract(final Number subtrahend) {
        return this;
    }

    @Generated
    @Override
    public Num subtract(final String subtrahend) {
        return this;
    }

    @Override
    public Num subtract(final Num subtrahend) {
        return this;
    }

    @Generated
    @Override
    public Num multiply(final Number multiplier) {
        return this;
    }

    @Generated
    @Override
    public Num multiply(final String multiplier) {
        return this;
    }

    @Override
    public Num multiply(final Num multiplier) {
        return this;
    }

    @Generated
    @Override
    public Num divide(final Number divisor) {
        return this;
    }

    @Generated
    @Override
    public Num divide(final String divisor) {
        return this;
    }

    @Override
    public Num divide(final Num divisor) {
        return this;
    }

    @Generated
    @Override
    public Num remainder(final Number divisor) {
        return this;
    }

    @Generated
    @Override
    public Num remainder(final String divisor) {
        return this;
    }

    @Override
    public Num remainder(final Num divisor) {
        return this;
    }

    @Generated
    @Override
    public Num power(final Number exponent) {
        return this;
    }

    @Generated
    @Override
    public Num power(final String exponent) {
        return this;
    }

    @Override
    public Num power(final Num exponent) {
        return this;
    }

    @Override
    public Num square() {
        return this;
    }

    @Override
    public Num cube() {
        return this;
    }

    @Override
    public Num exponential() {
        return this;
    }

    @Generated
    @Override
    public Num nthRoot(final Number degree) {
        return this;
    }

    @Generated
    @Override
    public Num nthRoot(final String degree) {
        return this;
    }

    @Override
    public Num nthRoot(final Num degree) {
        return this;
    }

    @Override
    public Num squareRoot() {
        return this;
    }

    @Override
    public Num cubeRoot() {
        return this;
    }

    @Override
    public Num ln() {
        return this;
    }

    @Override
    public Num log10() {
        return this;
    }

    @Override
    public Num log2() {
        return this;
    }

    @Generated
    @Override
    public Num log(final Number base) {
        return this;
    }

    @Generated
    @Override
    public Num log(final String base) {
        return this;
    }

    @Override
    public Num log(final Num base) {
        return this;
    }

    @Override
    public Num absoluteValue() {
        return this;
    }

    @Override
    public Num negate() {
        return this;
    }

    @Override
    public Num reciprocal() {
        return this;
    }

    @Override
    public Num increment() {
        return this;
    }

    @Override
    public Num decrement() {
        return this;
    }

    @Override
    public Num floor() {
        return this;
    }

    @Override
    public Num ceil() {
        return this;
    }

    @Override
    public Num degrees() {
        return this;
    }

    @Override
    public Num radians() {
        return this;
    }

    @Override
    public Num sin() {
        return this;
    }

    @Override
    public Num cos() {
        return this;
    }

    @Override
    public Num tan() {
        return this;
    }

    @Override
    public Num asin() {
        return this;
    }

    @Override
    public Num acos() {
        return this;
    }

    @Override
    public Num atan() {
        return this;
    }

    @Generated
    @Override
    public Num atan2(final Number x) {
        return this;
    }

    @Generated
    @Override
    public Num atan2(final String x) {
        return this;
    }

    @Override
    public Num atan2(final Num x) {
        return this;
    }

    @Override
    public Num sinh() {
        return this;
    }

    @Override
    public Num cosh() {
        return this;
    }

    @Override
    public Num tanh() {
        return this;
    }

    @Override
    public Num asinh() {
        return this;
    }

    @Override
    public Num acosh() {
        return this;
    }

    @Override
    public Num atanh() {
        return this;
    }

    @Generated
    @Override
    public Num hypotenuse(final Number y) {
        return this;
    }

    @Generated
    @Override
    public Num hypotenuse(final String y) {
        return this;
    }

    @Override
    public Num hypotenuse(final Num y) {
        return this;
    }

    @Generated
    @Override
    public Num average(final Number other) {
        return this;
    }

    @Generated
    @Override
    public Num average(final String other) {
        return this;
    }

    @Override
    public Num average(final Num other) {
        return this;
    }

    @Generated
    @Override
    public Num min(final Number other) {
        return this;
    }

    @Generated
    @Override
    public Num min(final String other) {
        return this;
    }

    @Override
    public Num min(final Num other) {
        return this;
    }

    @Generated
    @Override
    public Num max(final Number other) {
        return this;
    }

    @Generated
    @Override
    public Num max(final String other) {
        return this;
    }

    @Override
    public Num max(final Num other) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final Number min, final Number max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final Number min, final String max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final Number min, final Num max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final String min, final Number max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final String min, final String max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final String min, final Num max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final Num min, final Number max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final Num min, final String max) {
        return this;
    }

    @Override
    public Num clamp(final Num min, final Num max) {
        return this;
    }

    @Override
    public Num integerPart() {
        return this;
    }

    @Override
    public Num fractionalPart() {
        return this;
    }

    @Override
    public Num round() {
        return this;
    }

    @Override
    public Num round(final RoundingMode roundingMode) {
        return this;
    }

    @Override
    public Num round(final int scale) {
        return this;
    }

    @Override
    public Num round(final int scale, final RoundingMode roundingMode) {
        return this;
    }

    @Generated
    @Override
    public Num significantFigures(final int significantFigures) {
        return this;
    }

    @Generated
    @Override
    public Num significantFigures(final int significantFigures, final RoundingMode roundingMode) {
        return this;
    }

    @Override
    public Num significantFigures(final MathContext context) {
        return this;
    }

    @Override
    public int significantFigures() {
        return 0;
    }

    @Override
    public Num mantissa() {
        return this;
    }

    @Override
    public int exponent() {
        return 0;
    }

    @Override
    public int signum() {
        return 0;
    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public boolean isNegativeOrZero() {
        return false;
    }

    @Generated
    @Override
    public boolean isNegativeOrZero(final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isNegativeOrZero(final String epsilon) {
        return false;
    }

    @Override
    public boolean isNegativeOrZero(final Num epsilon) {
        return false;
    }

    @Override
    public boolean isPositive() {
        return false;
    }

    @Override
    public boolean isPositiveOrZero() {
        return false;
    }

    @Generated
    @Override
    public boolean isPositiveOrZero(final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isPositiveOrZero(final String epsilon) {
        return false;
    }

    @Override
    public boolean isPositiveOrZero(final Num epsilon) {
        return false;
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Generated
    @Override
    public boolean isZero(final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isZero(final String epsilon) {
        return false;
    }

    @Override
    public boolean isZero(final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Number other) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final String other) {
        return false;
    }

    @Override
    public boolean isEqual(final Num other) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Number other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Number other, final String epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Number other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final String other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final String other, final String epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final String other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Num other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Num other, final String epsilon) {
        return false;
    }

    @Override
    public boolean isEqual(final Num other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThan(final Number other) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThan(final String other) {
        return false;
    }

    @Override
    public boolean isLessThan(final Num other) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Number other) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final String other) {
        return false;
    }

    @Override
    public boolean isLessThanOrEqual(final Num other) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Number other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Number other, final String epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Number other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final String other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final String other, final String epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final String other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Num other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Num other, final String epsilon) {
        return false;
    }

    @Override
    public boolean isLessThanOrEqual(final Num other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThan(final Number other) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThan(final String other) {
        return false;
    }

    @Override
    public boolean isGreaterThan(final Num other) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Number other) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final String other) {
        return false;
    }

    @Override
    public boolean isGreaterThanOrEqual(final Num other) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Number other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Number other, final String epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Number other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final String other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final String other, final String epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final String other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Num other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Num other, final String epsilon) {
        return false;
    }

    @Override
    public boolean isGreaterThanOrEqual(final Num other, final Num epsilon) {
        return false;
    }

    @Override
    public boolean isNaN() {
        return true;
    }

    @Override
    public Num ifNaN(final Num replacement) {
        return replacement;
    }

    @Override
    public Num ifNaNThrow() {
        throw new ArithmeticException(STRING);
    }

    @Override
    public Num ifNaNThrow(final Supplier<RuntimeException> runtimeException) {
        throw runtimeException.get();
    }

    @Override
    public Number unwrap() {
        return Double.NaN;
    }

    @Override
    public BigDecimal toBigDecimal() {
        return BigDecimal.ZERO;
    }

    @Override
    public MathContext getContext() {
        return context;
    }

    @Override
    public NumFactory getFactory() {
        return factory;
    }

    @Override
    public String toString() {
        return STRING;
    }

    @Generated
    @Override
    public int compareTo(final Number o) {
        return 0;
    }

    @Generated
    @Override
    public int compareTo(final String o) {
        return 0;
    }

    @Override
    public int compareTo(final Num o) {
        return 0;
    }
}
