package trade.invision.num;

import lombok.Generated;
import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;

import static java.math.MathContext.DECIMAL64;

/**
 * {@link NaNNum}, short for "Not a Number" (NaN), is a {@link Num} that represents an undefined and unrepresentable
 * numerical value. All operations result in itself or <code>0</code>. It is not equal to itself or any other value. It
 * compares unordered with itself and all other values. It is not negative, positive, or zero.
 *
 * @see #NaN
 * @see <a href="https://en.wikipedia.org/wiki/NaN">wikipedia.org/wiki/NaN</a>
 * @see <a href="https://stackoverflow.com/a/1573715/4352701">stackoverflow.com - What is the rationale for all
 * comparisons returning false for IEEE754 NaN values?</a>
 */
@NullMarked
public final class NaNNum implements Num {

    /**
     * The singleton instance of {@link NaNNum}.
     */
    @SuppressWarnings("IdentifierName")
    public static final NaNNum NaN = new NaNNum();

    /**
     * The {@link NumFactory} for {@link NaNNum}.
     *
     * @return the {@link NumFactory}
     */
    public static NumFactory nanNumFactory() {
        return FACTORY;
    }

    private static final NumFactory FACTORY = new NumFactory() {

        @Override
        public Num of(final Number number) {
            return NaN;
        }

        @Override
        public Num of(final String string) {
            return NaN;
        }

        @Override
        public Num of(final Num num) {
            return NaN;
        }

        @Override
        public Num negativeOne() {
            return NaN;
        }

        @Override
        public Num zero() {
            return NaN;
        }

        @Override
        public Num one() {
            return NaN;
        }

        @Override
        public Num two() {
            return NaN;
        }

        @Override
        public Num three() {
            return NaN;
        }

        @Override
        public Num four() {
            return NaN;
        }

        @Override
        public Num five() {
            return NaN;
        }

        @Override
        public Num six() {
            return NaN;
        }

        @Override
        public Num seven() {
            return NaN;
        }

        @Override
        public Num eight() {
            return NaN;
        }

        @Override
        public Num nine() {
            return NaN;
        }

        @Override
        public Num ten() {
            return NaN;
        }

        @Override
        public Num hundred() {
            return NaN;
        }

        @Override
        public Num thousand() {
            return NaN;
        }

        @Override
        public Num tenth() {
            return NaN;
        }

        @Override
        public Num hundredth() {
            return NaN;
        }

        @Override
        public Num thousandth() {
            return NaN;
        }

        @Override
        public Num half() {
            return NaN;
        }

        @Override
        public Num random(final RandomGenerator randomGenerator) {
            return NaN;
        }
    };

    private NaNNum() {}

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

    @Generated
    @Override
    public Num add(final Function<NumFactory, Num> addend) {
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

    @Generated
    @Override
    public Num subtract(final Function<NumFactory, Num> subtrahend) {
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

    @Generated
    @Override
    public Num multiply(final Function<NumFactory, Num> multiplier) {
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

    @Generated
    @Override
    public Num divide(final Function<NumFactory, Num> divisor) {
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

    @Generated
    @Override
    public Num remainder(final Function<NumFactory, Num> divisor) {
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

    @Generated
    @Override
    public Num power(final Function<NumFactory, Num> exponent) {
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

    @Generated
    @Override
    public Num nthRoot(final Function<NumFactory, Num> degree) {
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

    @Generated
    @Override
    public Num log(final Function<NumFactory, Num> base) {
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
    public Num pi() {
        return this;
    }

    @Override
    public Num e() {
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

    @Generated
    @Override
    public Num atan2(final Function<NumFactory, Num> x) {
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

    @Generated
    @Override
    public Num hypotenuse(final Function<NumFactory, Num> y) {
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

    @Generated
    @Override
    public Num average(final Function<NumFactory, Num> other) {
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

    @Generated
    @Override
    public Num min(final Function<NumFactory, Num> other) {
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

    @Generated
    @Override
    public Num max(final Function<NumFactory, Num> other) {
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
    public Num clamp(final Number min, final Function<NumFactory, Num> max) {
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
    public Num clamp(final String min, final Function<NumFactory, Num> max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final String min, final Num max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final Function<NumFactory, Num> min, final Number max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final Function<NumFactory, Num> min, final String max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final Function<NumFactory, Num> min, final Function<NumFactory, Num> max) {
        return this;
    }

    @Generated
    @Override
    public Num clamp(final Function<NumFactory, Num> min, final Num max) {
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

    @Generated
    @Override
    public Num clamp(final Num min, final Function<NumFactory, Num> max) {
        return this;
    }

    @Generated
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

    @Generated
    @Override
    public Num round() {
        return this;
    }

    @Generated
    @Override
    public Num round(final RoundingMode roundingMode) {
        return this;
    }

    @Generated
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

    @Generated
    @Override
    public boolean isNegativeOrZero(final Function<NumFactory, Num> epsilon) {
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

    @Generated
    @Override
    public boolean isPositiveOrZero(final Function<NumFactory, Num> epsilon) {
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

    @Generated
    @Override
    public boolean isZero(final Function<NumFactory, Num> epsilon) {
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

    @Generated
    @Override
    public boolean isEqual(final Function<NumFactory, Num> other) {
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
    public boolean isEqual(final Number other, final Function<NumFactory, Num> epsilon) {
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
    public boolean isEqual(final String other, final Function<NumFactory, Num> epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final String other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Function<NumFactory, Num> other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Function<NumFactory, Num> other, final String epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Function<NumFactory, Num> other, final Function<NumFactory, Num> epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isEqual(final Function<NumFactory, Num> other, final Num epsilon) {
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

    @Generated
    @Override
    public boolean isEqual(final Num other, final Function<NumFactory, Num> epsilon) {
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

    @Generated
    @Override
    public boolean isLessThan(final Function<NumFactory, Num> other) {
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

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Function<NumFactory, Num> other) {
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
    public boolean isLessThanOrEqual(final Number other, final Function<NumFactory, Num> epsilon) {
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
    public boolean isLessThanOrEqual(final String other, final Function<NumFactory, Num> epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final String other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Function<NumFactory, Num> other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Function<NumFactory, Num> other, final String epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Function<NumFactory, Num> other, final Function<NumFactory, Num> epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Function<NumFactory, Num> other, final Num epsilon) {
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

    @Generated
    @Override
    public boolean isLessThanOrEqual(final Num other, final Function<NumFactory, Num> epsilon) {
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

    @Generated
    @Override
    public boolean isGreaterThan(final Function<NumFactory, Num> other) {
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

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Function<NumFactory, Num> other) {
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
    public boolean isGreaterThanOrEqual(final Number other, final Function<NumFactory, Num> epsilon) {
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
    public boolean isGreaterThanOrEqual(final String other, final Function<NumFactory, Num> epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final String other, final Num epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Function<NumFactory, Num> other, final Number epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Function<NumFactory, Num> other, final String epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Function<NumFactory, Num> other,
            final Function<NumFactory, Num> epsilon) {
        return false;
    }

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Function<NumFactory, Num> other, final Num epsilon) {
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

    @Generated
    @Override
    public boolean isGreaterThanOrEqual(final Num other, final Function<NumFactory, Num> epsilon) {
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
        throw new ArithmeticException(toString());
    }

    @Override
    public Num ifNaNThrow(final Supplier<RuntimeException> runtimeException) {
        throw runtimeException.get();
    }

    @Override
    public Num ifNaNThrow(final RuntimeException runtimeException) {
        throw runtimeException;
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
        return DECIMAL64;
    }

    @Override
    public NumFactory factory() {
        return FACTORY;
    }

    @Override
    public boolean equals(final Object obj) {
        return this == obj;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "NaN";
    }

    @Override
    public int compareTo(final Num o) {
        return 0;
    }
}
