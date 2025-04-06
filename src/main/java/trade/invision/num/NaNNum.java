package trade.invision.num;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.MathContext.DECIMAL64;

/**
 * {@link NaNNum}, short for Not a Number (NaN), is a {@link Num} that represents an undefined and unrepresentable
 * numerical value. All operations result in itself. It is not equal to itself or any other value. It compares unordered
 * with itself and all other values. It is not negative, positive, or zero. It has no integer or {@link BigDecimal}
 * representation.
 *
 * @see <a href="https://en.wikipedia.org/wiki/NaN">Wikipedia</a>
 * @see <a href="https://stackoverflow.com/a/1573715/4352701">Stack Overflow</a>
 */
public final class NaNNum implements Num {

    /**
     * Global static instance of {@link NaNNum}.
     */
    public static NaNNum NaN = new NaNNum();

    /**
     * A {@link NumFactory} for {@link NaNNum}. All methods return {@link #NaN}.
     */
    public NumFactory FACTORY = new NumFactory() {
        @Override
        public NaNNum of(Number number) {
            return NaN;
        }

        @Override
        public NaNNum of(String number) {
            return NaN;
        }

        @Override
        public NaNNum tenth() {
            return NaN;
        }

        @Override
        public NaNNum hundredth() {
            return NaN;
        }

        @Override
        public NaNNum thousandth() {
            return NaN;
        }

        @Override
        public NaNNum negativeOne() {
            return NaN;
        }

        @Override
        public NaNNum zero() {
            return NaN;
        }

        @Override
        public NaNNum one() {
            return NaN;
        }

        @Override
        public NaNNum two() {
            return NaN;
        }

        @Override
        public NaNNum three() {
            return NaN;
        }

        @Override
        public NaNNum ten() {
            return NaN;
        }

        @Override
        public NaNNum hundred() {
            return NaN;
        }

        @Override
        public NaNNum thousand() {
            return NaN;
        }
    };

    private NaNNum() {}

    @Override
    public Number unwrap() {
        return Double.NaN;
    }

    @Override
    public NumFactory factory() {
        return FACTORY;
    }

    @Override
    public NaNNum add(Num addend) {
        return this;
    }

    @Override
    public NaNNum subtract(Num subtrahend) {
        return this;
    }

    @Override
    public NaNNum multiply(Num multiplicand) {
        return this;
    }

    @Override
    public NaNNum divide(Num divisor) {
        return this;
    }

    @Override
    public NaNNum remainder(Num divisor) {
        return this;
    }

    @Override
    public NaNNum power(Num exponent) {
        return this;
    }

    @Override
    public NaNNum square() {
        return this;
    }

    @Override
    public NaNNum cube() {
        return this;
    }

    @Override
    public NaNNum exponential() {
        return this;
    }

    @Override
    public NaNNum root(Num degree) {
        return this;
    }

    @Override
    public NaNNum squareRoot() {
        return this;
    }

    @Override
    public NaNNum cubeRoot() {
        return this;
    }

    @Override
    public NaNNum naturalLogarithm() {
        return this;
    }

    @Override
    public NaNNum logarithm() {
        return this;
    }

    @Override
    public NaNNum logarithm(Num base) {
        return this;
    }

    @Override
    public NaNNum absoluteValue() {
        return this;
    }

    @Override
    public NaNNum negate() {
        return this;
    }

    @Override
    public NaNNum reciprocal() {
        return this;
    }

    @Override
    public NaNNum floor() {
        return this;
    }

    @Override
    public NaNNum ceil() {
        return this;
    }

    @Override
    public NaNNum degrees() {
        return this;
    }

    @Override
    public NaNNum radians() {
        return this;
    }

    @Override
    public NaNNum sine() {
        return this;
    }

    @Override
    public NaNNum cosine() {
        return this;
    }

    @Override
    public NaNNum tangent() {
        return this;
    }

    @Override
    public NaNNum inverseSine() {
        return this;
    }

    @Override
    public NaNNum inverseCosine() {
        return this;
    }

    @Override
    public NaNNum inverseTangent() {
        return this;
    }

    @Override
    public NaNNum inverseTangent2(Num x) {
        return this;
    }

    @Override
    public NaNNum hyperbolicSine() {
        return this;
    }

    @Override
    public NaNNum hyperbolicCosine() {
        return this;
    }

    @Override
    public NaNNum hyperbolicTangent() {
        return this;
    }

    @Override
    public NaNNum inverseHyperbolicSine() {
        return this;
    }

    @Override
    public NaNNum inverseHyperbolicCosine() {
        return this;
    }

    @Override
    public NaNNum inverseHyperbolicTangent() {
        return this;
    }

    @Override
    public NaNNum hypotenuse(Num y) {
        return this;
    }

    @Override
    public NaNNum average(Num other) {
        return this;
    }

    @Override
    public NaNNum minimum(Num other) {
        return this;
    }

    @Override
    public NaNNum maximum(Num other) {
        return this;
    }

    @Override
    public NaNNum integralPart() {
        return this;
    }

    @Override
    public NaNNum fractionalPart() {
        return this;
    }

    @Override
    public NaNNum round(int scale, RoundingMode roundingMode) {
        return this;
    }

    @Override
    public NaNNum precision(MathContext mathContext) {
        return this;
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
    public boolean isPositive() {
        return false;
    }

    @Override
    public boolean isPositiveOrZero() {
        return false;
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public boolean isEqual(Num other) {
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
    public boolean isGreaterThan(Num other) {
        return false;
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other) {
        return false;
    }

    @Override
    public boolean isNaN() {
        return true;
    }

    @Override
    public byte asByte() {
        throw new ArithmeticException("No NaN representation for 'byte'!");
    }

    @Override
    public short asShort() {
        throw new ArithmeticException("No NaN representation for 'short'!");
    }

    @Override
    public int asInt() {
        throw new ArithmeticException("No NaN representation for 'int'!");
    }

    @Override
    public long asLong() {
        throw new ArithmeticException("No NaN representation for 'long'!");
    }

    @Override
    public float asFloat() {
        return Float.NaN;
    }

    @Override
    public double asDouble() {
        return Double.NaN;
    }

    @Override
    public BigDecimal asBigDecimal() {
        throw new ArithmeticException("No NaN representation for 'BigDecimal'!");
    }

    @Override
    public MathContext getContext() {
        return DECIMAL64;
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
