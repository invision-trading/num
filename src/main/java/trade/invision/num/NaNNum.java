package trade.invision.num;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Supplier;

import static java.math.MathContext.DECIMAL64;

/**
 * {@link NaNNum}, short for "Not a Number" (NaN), is a {@link Num} that represents an undefined and unrepresentable
 * numerical value. All operations result in itself or <code>0</code>. It is not equal to itself or any other value. It
 * compares unordered with itself and all other values. It is not negative, positive, or zero.
 *
 * @see <a href="https://en.wikipedia.org/wiki/NaN">Wikipedia</a>
 * @see <a href="https://stackoverflow.com/a/1573715/4352701">Stack Overflow</a>
 */
public final class NaNNum implements Num {

    /**
     * Singleton instance of {@link NaNNum}.
     */
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
        public Num of(Number number) {
            return NaN;
        }

        @Override
        public Num of(BigDecimal bigDecimal) {
            return NaN;
        }

        @Override
        public Num of(String string) {
            return NaN;
        }

        @Override
        public Num of(Num num) {
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
        public Num random() {
            return NaN;
        }
    };

    private NaNNum() {}

    @Override
    public Num add(Num addend) {
        return this;
    }

    @Override
    public Num subtract(Num subtrahend) {
        return this;
    }

    @Override
    public Num multiply(Num multiplier) {
        return this;
    }

    @Override
    public Num divide(Num divisor) {
        return this;
    }

    @Override
    public Num remainder(Num divisor) {
        return this;
    }

    @Override
    public Num power(Num exponent) {
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

    @Override
    public Num nthRoot(Num degree) {
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
    public Num naturalLog() {
        return this;
    }

    @Override
    public Num commonLog() {
        return this;
    }

    @Override
    public Num binaryLog() {
        return this;
    }

    @Override
    public Num log(Num base) {
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

    @Override
    public Num atan2(Num x) {
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

    @Override
    public Num hypotenuse(Num y) {
        return this;
    }

    @Override
    public Num average(Num other) {
        return this;
    }

    @Override
    public Num min(Num other) {
        return this;
    }

    @Override
    public Num max(Num other) {
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
    public Num round(int scale, RoundingMode roundingMode) {
        return this;
    }

    @Override
    public Num sigFigs(MathContext context) {
        return this;
    }

    @Override
    public int sigFigs() {
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

    @Override
    public boolean isNegativeOrZero(Num epsilon) {
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

    @Override
    public boolean isPositiveOrZero(Num epsilon) {
        return false;
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public boolean isZero(Num epsilon) {
        return false;
    }

    @Override
    public boolean isEqual(Num other) {
        return false;
    }

    @Override
    public boolean isEqual(Num other, Num epsilon) {
        return false;
    }

    @Override
    public boolean isLessThan(Num other) {
        return false;
    }

    @Override
    public boolean isLessThanOrEqual(Num other) {
        return false;
    }

    @Override
    public boolean isLessThanOrEqual(Num other, Num epsilon) {
        return false;
    }

    @Override
    public boolean isGreaterThan(Num other) {
        return false;
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other) {
        return false;
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other, Num epsilon) {
        return false;
    }

    @Override
    public boolean isNaN() {
        return true;
    }

    @Override
    public Num ifNaN(Num replacement) {
        return replacement;
    }

    @Override
    public Num ifNaNThrow() {
        throw new ArithmeticException(toString());
    }

    @Override
    public Num ifNaNThrow(Supplier<RuntimeException> runtimeException) {
        throw runtimeException.get();
    }

    @Override
    public Num ifNaNThrow(RuntimeException runtimeException) {
        throw runtimeException;
    }

    @Override
    public Number unwrap() {
        return Double.NaN;
    }

    @Override
    public BigDecimal toBigDecimal() {
        return new BigDecimal(0);
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
    public boolean equals(Object obj) {
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
    public int compareTo(@NotNull Num o) {
        return 0;
    }
}
