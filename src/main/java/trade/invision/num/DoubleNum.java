package trade.invision.num;

import lombok.Generated;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;

import static java.lang.Double.isFinite;
import static java.lang.Double.parseDouble;
import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.CEILING;
import static java.math.RoundingMode.FLOOR;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;
import static trade.invision.num.DecimalNum.decimalNum;
import static trade.invision.num.NaNNum.nanNum;

/**
 * {@link DoubleNum} is a {@link Num} implementation using floating-point binary numbers via {@link Double}. The
 * <code>double</code> primitive type is used where possible in order to avoid the {@link Double} boxed type.
 *
 * @see Double
 * @see Math
 * @see <a href="https://en.wikipedia.org/wiki/Floating-point_arithmetic">
 * wikipedia.org/wiki/Floating-point_arithmetic</a>
 * @see <a href="https://en.wikipedia.org/wiki/Double-precision_floating-point_format">
 * wikipedia.org/wiki/Double-precision_floating-point_format</a>
 */
@NullMarked
public final class DoubleNum implements Num {

    /**
     * The {@link NumFactory} for {@link DoubleNum}.
     */
    public static final NumFactory FACTORY = new NumFactory() {

        private static final Num PI = doubleNum(Math.PI);
        private static final Num E = doubleNum(Math.E);

        @Override
        public Num of(final Number number) {
            return doubleNum(number);
        }

        @Override
        public Num of(final String string) {
            return doubleNum(string);
        }

        @Override
        public Num of(final Num num) {
            return doubleNum(num);
        }

        @Override
        public Num random(final RandomGenerator randomGenerator) {
            return of(randomGenerator.nextDouble());
        }

        @Override
        public Num pi() {
            return PI;
        }

        @Override
        public Num e() {
            return E;
        }
    };

    private static final MathContext CONTEXT = DECIMAL64;

    /**
     * The {@link NaNNum} constant for this {@link DoubleNum}.
     */
    public static final @SuppressWarnings("IdentifierName") Num NaN = nanNum(CONTEXT, FACTORY);

    /**
     * Creates a new {@link DoubleNum} for the given <code>double</code>.
     *
     * @param aDouble the <code>double</code>
     *
     * @return the {@link Num}
     */
    public static Num doubleNum(final double aDouble) {
        return !isFinite(aDouble) ? NaN : new DoubleNum(aDouble);
    }

    /**
     * @return {@link #doubleNum(double)} {@link Number#doubleValue()}
     */
    public static Num doubleNum(final Number number) {
        return doubleNum(number.doubleValue());
    }

    /**
     * @return {@link #doubleNum(double)} {@link Double#parseDouble(String)}
     *
     * @throws NumberFormatException thrown for {@link NumberFormatException}s
     */
    public static Num doubleNum(final String string) throws NumberFormatException {
        return doubleNum(parseDouble(string));
    }

    /**
     * @return {@link #doubleNum(Number)} {@link Num#unwrap()}
     */
    public static Num doubleNum(final Num num) {
        return doubleNum(num.unwrap());
    }

    private static final double NATURAL_LOGARITHM_OF_2 = 0.6931471805599453;

    private final double wrapped;

    private DoubleNum(final double wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Num add(final Num addend) {
        if (addend.isNaN()) {
            return addend;
        }
        if (addend instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).add(decimalNum);
        }
        return doubleNum(wrapped + ((DoubleNum) addend).wrapped);
    }

    @Override
    public Num subtract(final Num subtrahend) {
        if (subtrahend.isNaN()) {
            return subtrahend;
        }
        if (subtrahend instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).subtract(decimalNum);
        }
        return doubleNum(wrapped - ((DoubleNum) subtrahend).wrapped);
    }

    @Override
    public Num multiply(final Num multiplier) {
        if (multiplier.isNaN()) {
            return multiplier;
        }
        if (multiplier instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).multiply(decimalNum);
        }
        return doubleNum(wrapped * ((DoubleNum) multiplier).wrapped);
    }

    @Override
    public Num divide(final Num divisor) {
        if (divisor.isNaN()) {
            return divisor;
        }
        if (divisor instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).divide(decimalNum);
        }
        return doubleNum(wrapped / ((DoubleNum) divisor).wrapped);
    }

    @Override
    public Num remainder(final Num divisor) {
        if (divisor.isNaN()) {
            return divisor;
        }
        if (divisor instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).remainder(decimalNum);
        }
        return doubleNum(wrapped % ((DoubleNum) divisor).wrapped);
    }

    @Override
    public Num power(final Num exponent) {
        if (exponent.isNaN()) {
            return exponent;
        }
        if (exponent instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).power(decimalNum);
        }
        return doubleNum(Math.pow(wrapped, ((DoubleNum) exponent).wrapped));
    }

    @Override
    public Num square() {
        return doubleNum(wrapped * wrapped);
    }

    @Override
    public Num cube() {
        return doubleNum(wrapped * wrapped * wrapped);
    }

    @Override
    public Num exponential() {
        return doubleNum(Math.exp(wrapped));
    }

    @Override
    public Num nthRoot(final Num degree) {
        if (degree.isNaN()) {
            return degree;
        }
        if (degree instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).nthRoot(decimalNum);
        }
        return doubleNum(Math.pow(wrapped, 1.0 / ((DoubleNum) degree).wrapped));
    }

    @Override
    public Num squareRoot() {
        return doubleNum(Math.sqrt(wrapped));
    }

    @Override
    public Num cubeRoot() {
        return doubleNum(Math.cbrt(wrapped));
    }

    @Override
    public Num ln() {
        return doubleNum(Math.log(wrapped));
    }

    @Override
    public Num log10() {
        return doubleNum(Math.log10(wrapped));
    }

    @Override
    public Num log2() {
        return doubleNum(Math.log(wrapped) / NATURAL_LOGARITHM_OF_2);
    }

    @Override
    public Num log(final Num base) {
        if (base.isNaN()) {
            return base;
        }
        if (base instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).log(decimalNum);
        }
        return doubleNum(Math.log(wrapped) / Math.log(((DoubleNum) base).wrapped));
    }

    @Override
    public Num absoluteValue() {
        return doubleNum(Math.abs(wrapped));
    }

    @Override
    public Num negate() {
        return doubleNum(-wrapped);
    }

    @Override
    public Num reciprocal() {
        return doubleNum(1.0 / wrapped);
    }

    @Override
    public Num increment() {
        return doubleNum(wrapped + 1.0);
    }

    @Override
    public Num decrement() {
        return doubleNum(wrapped - 1.0);
    }

    @Override
    public Num floor() {
        return doubleNum(Math.floor(wrapped));
    }

    @Override
    public Num ceil() {
        return doubleNum(Math.ceil(wrapped));
    }

    @Override
    public Num degrees() {
        return doubleNum(Math.toDegrees(wrapped));
    }

    @Override
    public Num radians() {
        return doubleNum(Math.toRadians(wrapped));
    }

    @Override
    public Num sin() {
        return doubleNum(Math.sin(wrapped));
    }

    @Override
    public Num cos() {
        return doubleNum(Math.cos(wrapped));
    }

    @Override
    public Num tan() {
        return doubleNum(Math.tan(wrapped));
    }

    @Override
    public Num asin() {
        return doubleNum(Math.asin(wrapped));
    }

    @Override
    public Num acos() {
        return doubleNum(Math.acos(wrapped));
    }

    @Override
    public Num atan() {
        return doubleNum(Math.atan(wrapped));
    }

    @Override
    public Num atan2(final Num x) {
        if (x.isNaN()) {
            return x;
        }
        if (x instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).atan2(decimalNum);
        }
        return doubleNum(Math.atan2(wrapped, ((DoubleNum) x).wrapped));
    }

    @Override
    public Num sinh() {
        return doubleNum(Math.sinh(wrapped));
    }

    @Override
    public Num cosh() {
        return doubleNum(Math.cosh(wrapped));
    }

    @Override
    public Num tanh() {
        return doubleNum(Math.tanh(wrapped));
    }

    @Override
    public Num asinh() {
        return doubleNum(Math.log(wrapped + Math.sqrt(wrapped * wrapped + 1.0)));
    }

    @Override
    public Num acosh() {
        return doubleNum(Math.log(wrapped + Math.sqrt(wrapped * wrapped - 1.0)));
    }

    @Override
    public Num atanh() {
        return doubleNum(0.5 * Math.log((1.0 + wrapped) / (1.0 - wrapped)));
    }

    @Override
    public Num hypotenuse(final Num y) {
        if (y.isNaN()) {
            return y;
        }
        if (y instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).hypotenuse(decimalNum);
        }
        return doubleNum(Math.hypot(wrapped, ((DoubleNum) y).wrapped));
    }

    @Override
    public Num average(final Num other) {
        if (other.isNaN()) {
            return other;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).average(decimalNum);
        }
        return doubleNum((wrapped + ((DoubleNum) other).wrapped) * 0.5);
    }

    @Override
    public Num min(final Num other) {
        if (other.isNaN()) {
            return other;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).min(decimalNum);
        }
        return wrapped <= ((DoubleNum) other).wrapped ? this : other;
    }

    @Override
    public Num max(final Num other) {
        if (other.isNaN()) {
            return other;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).max(decimalNum);
        }
        return wrapped >= ((DoubleNum) other).wrapped ? this : other;
    }

    @Override
    public Num integerPart() {
        return doubleNum((int) wrapped);
    }

    @Override
    public Num fractionalPart() {
        return doubleNum(wrapped - ((int) wrapped));
    }

    @Override
    public Num round(final int scale, final RoundingMode roundingMode) {
        if (roundingMode == CEILING || roundingMode == FLOOR || roundingMode == HALF_UP || roundingMode == HALF_EVEN) {
            if (scale == 0) {
                return doubleNum(doRound(wrapped, roundingMode));
            }
            final var multiplier = switch (scale) {
                case -15 -> 1e-15;
                case -14 -> 1e-14;
                case -13 -> 1e-13;
                case -12 -> 1e-12;
                case -11 -> 1e-11;
                case -10 -> 1e-10;
                case -9 -> 1e-9;
                case -8 -> 1e-8;
                case -7 -> 1e-7;
                case -6 -> 1e-6;
                case -5 -> 1e-5;
                case -4 -> 1e-4;
                case -3 -> 1e-3;
                case -2 -> 1e-2;
                case -1 -> 1e-1;
                case 1 -> 1e1;
                case 2 -> 1e2;
                case 3 -> 1e3;
                case 4 -> 1e4;
                case 5 -> 1e5;
                case 6 -> 1e6;
                case 7 -> 1e7;
                case 8 -> 1e8;
                case 9 -> 1e9;
                case 10 -> 1e10;
                case 11 -> 1e11;
                case 12 -> 1e12;
                case 13 -> 1e13;
                case 14 -> 1e14;
                case 15 -> 1e15;
                default -> Math.pow(10, scale);
            };
            return doubleNum(doRound(wrapped * multiplier, roundingMode) / multiplier);
        }
        return doubleNum(decimalNum(this, getContext()).round(scale, roundingMode).toDouble());
    }

    private double doRound(final double number, final RoundingMode roundingMode) {
        return switch (roundingMode) {
            case CEILING -> Math.ceil(number);
            case FLOOR -> Math.floor(number);
            case HALF_UP -> Math.round(number);
            case HALF_EVEN -> Math.rint(number);
            default -> throw new UnsupportedOperationException();
        };
    }

    @Override
    public Num significantFigures(final MathContext context) {
        return doubleNum(decimalNum(this, getContext()).significantFigures(context).toDouble());
    }

    @Override
    public int significantFigures() {
        return decimalNum(this, getContext()).significantFigures();
    }

    @Override
    public Num mantissa() {
        return doubleNum(decimalNum(this, getContext()).mantissa().toDouble());
    }

    @Override
    public int exponent() {
        return decimalNum(this, getContext()).exponent();
    }

    @Override
    public int signum() {
        return (int) Math.signum(wrapped);
    }

    @Override
    public boolean isNegative() {
        return wrapped < 0.0;
    }

    @Override
    public boolean isNegativeOrZero() {
        return wrapped <= 0.0;
    }

    @Override
    public boolean isNegativeOrZero(final Num epsilon) {
        if (epsilon.isNaN()) {
            return false;
        }
        if (epsilon instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).isNegativeOrZero(decimalNum);
        }
        return wrapped <= ((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isPositive() {
        return wrapped > 0.0;
    }

    @Override
    public boolean isPositiveOrZero() {
        return wrapped >= 0.0;
    }

    @Override
    public boolean isPositiveOrZero(final Num epsilon) {
        if (epsilon.isNaN()) {
            return false;
        }
        if (epsilon instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).isPositiveOrZero(decimalNum);
        }
        return wrapped >= -((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isZero() {
        return wrapped == 0.0;
    }

    @Override
    public boolean isZero(final Num epsilon) {
        if (epsilon.isNaN()) {
            return false;
        }
        if (epsilon instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).isZero(decimalNum);
        }
        return Math.abs(wrapped) <= ((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isEqual(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).isEqual(decimalNum);
        }
        return wrapped == ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isEqual(final Num other, final Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        if (other instanceof DecimalNum || epsilon instanceof DecimalNum) {
            return other.getFactory().of(this).isEqual(other, epsilon);
        }
        return Math.abs(wrapped - ((DoubleNum) other).wrapped) <= ((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isLessThan(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).isLessThan(decimalNum);
        }
        return wrapped < ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isLessThanOrEqual(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).isLessThanOrEqual(decimalNum);
        }
        return wrapped <= ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isLessThanOrEqual(final Num other, final Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        if (other instanceof DecimalNum || epsilon instanceof DecimalNum) {
            return other.getFactory().of(this).isLessThanOrEqual(other, epsilon);
        }
        return ((DoubleNum) other).wrapped - wrapped >= -((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isGreaterThan(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).isGreaterThan(decimalNum);
        }
        return wrapped > ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isGreaterThanOrEqual(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).isGreaterThanOrEqual(decimalNum);
        }
        return wrapped >= ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isGreaterThanOrEqual(final Num other, final Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        if (other instanceof DecimalNum || epsilon instanceof DecimalNum) {
            return other.getFactory().of(this).isGreaterThanOrEqual(other, epsilon);
        }
        return wrapped - ((DoubleNum) other).wrapped >= -((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isNaN() {
        return false;
    }

    @Generated
    @Override
    public Num ifNaN(final Number replacement) {
        return this;
    }

    @Generated
    @Override
    public Num ifNaN(final String replacement) {
        return this;
    }

    @Override
    public Num ifNaN(final Num replacement) {
        return this;
    }

    @Override
    public Num ifNaNThrow() {
        return this;
    }

    @Override
    public Num ifNaNThrow(final Supplier<RuntimeException> runtimeException) {
        return this;
    }

    @Override
    public Number unwrap() {
        return wrapped;
    }

    @Override
    public BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(wrapped);
    }

    @Override
    public MathContext getContext() {
        return CONTEXT;
    }

    @Override
    public NumFactory getFactory() {
        return FACTORY;
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        return obj instanceof final DoubleNum doubleNum && ((Double) wrapped).equals(doubleNum.wrapped);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(wrapped);
    }

    @Override
    public String toString() {
        return Double.toString(wrapped);
    }

    @Override
    public int compareTo(final Num o) {
        if (o.isNaN()) {
            return 0;
        }
        if (o instanceof final DecimalNum decimalNum) {
            return decimalNum.getFactory().of(this).compareTo(o);
        }
        return Double.compare(wrapped, ((DoubleNum) o).wrapped);
    }
}
