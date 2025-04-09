package trade.invision.num;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.lang.Double.isFinite;
import static java.lang.Double.parseDouble;
import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;
import static trade.invision.num.NaNNum.NaN;

/**
 * {@link DoubleNum} is a {@link Num} implementation using floating-point binary-represented numbers via {@link Double}.
 * The <code>double</code> primitive type is used where possible in order to avoid the {@link Double} boxed type.
 *
 * @see Num
 * @see Double
 * @see Math
 * @see <a href="https://en.wikipedia.org/wiki/Floating-point_arithmetic">Wikipedia</a>
 * @see <a href="https://en.wikipedia.org/wiki/Double-precision_floating-point_format">Wikipedia</a>
 */
public final class DoubleNum implements Num {

    /**
     * @see #doubleNum(Number)
     */
    public static Num valueOf(Number number) {
        return doubleNum(number);
    }

    /**
     * Creates a new {@link DoubleNum} from the given {@link Number}.
     *
     * @param number the {@link Number}
     *
     * @return the {@link Num}
     */
    public static Num doubleNum(Number number) {
        final double aDouble = number.doubleValue();
        return !isFinite(aDouble) ? NaN : new DoubleNum(aDouble);
    }

    /**
     * @see #doubleNum(BigDecimal)
     */
    public static Num valueOf(BigDecimal bigDecimal) {
        return doubleNum(bigDecimal);
    }

    /**
     * Creates a new {@link DoubleNum} from the given {@link BigDecimal}.
     *
     * @param bigDecimal the {@link BigDecimal}
     *
     * @return the {@link Num}
     */
    public static Num doubleNum(BigDecimal bigDecimal) {
        return doubleNum((Number) bigDecimal);
    }

    /**
     * @see #doubleNum(String)
     */
    public static Num valueOf(String string) {
        return doubleNum(string);
    }

    /**
     * Creates a new {@link DoubleNum} from the given {@link String} representing a number.
     *
     * @param string the {@link String}
     *
     * @return the {@link Num}
     */
    public static Num doubleNum(String string) {
        final double aDouble = parseDouble(string);
        return !isFinite(aDouble) ? NaN : new DoubleNum(aDouble);
    }

    /**
     * @see #doubleNum(Num)
     */
    public static Num valueOf(Num num) {
        return doubleNum(num);
    }

    /**
     * Creates a new {@link DoubleNum} from the given {@link Num}.
     *
     * @param num the {@link Num}
     *
     * @return the {@link Num}
     */
    public static Num doubleNum(Num num) {
        return doubleNum(num.unwrap());
    }

    /**
     * Gets the {@link NumFactory} for {@link DoubleNum}.
     *
     * @return the {@link DoubleNum} {@link NumFactory}
     */
    public static NumFactory doubleNumFactory() {
        return FACTORY;
    }

    private static final DoubleNum NEGATIVE_ONE = (DoubleNum) doubleNum(-1);
    private static final DoubleNum ZERO = (DoubleNum) doubleNum(0);
    private static final DoubleNum ONE = (DoubleNum) doubleNum(1);
    private static final DoubleNum TWO = (DoubleNum) doubleNum(2);
    private static final DoubleNum THREE = (DoubleNum) doubleNum(3);
    private static final DoubleNum HALF = (DoubleNum) doubleNum(0.5);
    private static final DoubleNum TENTH = (DoubleNum) doubleNum(0.1);
    private static final DoubleNum HUNDREDTH = (DoubleNum) doubleNum(0.01);
    private static final DoubleNum THOUSANDTH = (DoubleNum) doubleNum(0.001);
    private static final DoubleNum TEN = (DoubleNum) doubleNum(10);
    private static final DoubleNum HUNDRED = (DoubleNum) doubleNum(100);
    private static final DoubleNum THOUSAND = (DoubleNum) doubleNum(1000);

    private static final NumFactory FACTORY = new NumFactory() {

        @Override
        public Num of(Number number) {
            return doubleNum(number);
        }

        @Override
        public Num of(BigDecimal bigDecimal) {
            return doubleNum(bigDecimal);
        }

        @Override
        public Num of(String string) {
            return doubleNum(string);
        }

        @Override
        public Num of(Num num) {
            return doubleNum(num);
        }

        @Override
        public Num negativeOne() {
            return NEGATIVE_ONE;
        }

        @Override
        public Num zero() {
            return ZERO;
        }

        @Override
        public Num one() {
            return ONE;
        }

        @Override
        public Num two() {
            return TWO;
        }

        @Override
        public Num three() {
            return THREE;
        }

        @Override
        public Num half() {
            return HALF;
        }

        @Override
        public Num tenth() {
            return TENTH;
        }

        @Override
        public Num hundredth() {
            return HUNDREDTH;
        }

        @Override
        public Num thousandth() {
            return THOUSANDTH;
        }

        @Override
        public Num ten() {
            return TEN;
        }

        @Override
        public Num hundred() {
            return HUNDRED;
        }

        @Override
        public Num thousand() {
            return THOUSAND;
        }
    };

    private static final double NATURAL_LOGARITHM_OF_2 = Math.log(2.0);

    private final double wrapped;

    private DoubleNum(double aDouble) {
        wrapped = aDouble;
    }

    @Override
    public Number unwrap() {
        return wrapped;
    }

    @Override
    public NumFactory factory() {
        return FACTORY;
    }

    @Override
    public Num add(Num addend) {
        if (addend.isNaN()) {
            return NaN;
        } else if (addend instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).add(decimalNum);
        } else {
            return new DoubleNum(wrapped + ((DoubleNum) addend).wrapped);
        }
    }

    @Override
    public Num subtract(Num subtrahend) {
        if (subtrahend.isNaN()) {
            return NaN;
        } else if (subtrahend instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).subtract(decimalNum);
        } else {
            return new DoubleNum(wrapped - ((DoubleNum) subtrahend).wrapped);
        }
    }

    @Override
    public Num multiply(Num multiplicand) {
        if (multiplicand.isNaN()) {
            return NaN;
        } else if (multiplicand instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).multiply(decimalNum);
        } else {
            return new DoubleNum(wrapped * ((DoubleNum) multiplicand).wrapped);
        }
    }

    @Override
    public Num divide(Num divisor) {
        if (divisor.isNaN()) {
            return NaN;
        } else if (divisor instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).divide(decimalNum);
        } else {
            final double quotient = wrapped * ((DoubleNum) divisor).wrapped;
            return !isFinite(quotient) ? NaN : new DoubleNum(quotient);
        }
    }

    @Override
    public Num remainder(Num divisor) {
        if (divisor.isNaN()) {
            return NaN;
        } else if (divisor instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).remainder(decimalNum);
        } else {
            final double remainder = wrapped % ((DoubleNum) divisor).wrapped;
            return !isFinite(remainder) ? NaN : new DoubleNum(remainder);
        }
    }

    @Override
    public Num power(Num exponent) {
        if (exponent.isNaN()) {
            return NaN;
        } else if (exponent instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).power(decimalNum);
        } else {
            final double power = Math.pow(wrapped, ((DoubleNum) exponent).wrapped);
            return !isFinite(power) ? NaN : new DoubleNum(power);
        }
    }

    @Override
    public Num square() {
        return new DoubleNum(wrapped * wrapped);
    }

    @Override
    public Num cube() {
        return new DoubleNum(wrapped * wrapped * wrapped);
    }

    @Override
    public Num exponential() {
        return new DoubleNum(Math.exp(wrapped));
    }

    @Override
    public Num nthRoot(Num degree) {
        if (degree.isNaN()) {
            return NaN;
        } else if (degree instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).nthRoot(decimalNum);
        } else {
            final double nthRoot = Math.pow(wrapped, 1.0 / ((DoubleNum) degree).wrapped);
            return !isFinite(nthRoot) ? NaN : new DoubleNum(nthRoot);
        }
    }

    @Override
    public Num squareRoot() {
        final double squareRoot = Math.sqrt(wrapped);
        return !isFinite(squareRoot) ? NaN : new DoubleNum(squareRoot);
    }

    @Override
    public Num cubeRoot() {
        final double cubeRoot = Math.cbrt(wrapped);
        return !isFinite(cubeRoot) ? NaN : new DoubleNum(cubeRoot);
    }

    @Override
    public Num naturalLogarithm() {
        final double naturalLogarithm = Math.log(wrapped);
        return !isFinite(naturalLogarithm) ? NaN : new DoubleNum(naturalLogarithm);
    }

    @Override
    public Num commonLogarithm() {
        final double commonLogarithm = Math.log10(wrapped);
        return !isFinite(commonLogarithm) ? NaN : new DoubleNum(commonLogarithm);
    }

    @Override
    public Num binaryLogarithm() {
        final double numerator = Math.log(wrapped);
        return !isFinite(numerator) ? NaN : new DoubleNum(numerator / NATURAL_LOGARITHM_OF_2);
    }

    @Override
    public Num logarithm(Num base) {
        if (base.isNaN()) {
            return NaN;
        } else if (base instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).logarithm(decimalNum);
        } else {
            final double numerator = Math.log(wrapped);
            if (!isFinite(numerator)) {
                return NaN;
            }
            final double denominator = Math.log(((DoubleNum) base).wrapped);
            if (!isFinite(denominator)) {
                return NaN;
            }
            return new DoubleNum(numerator / denominator);
        }
    }

    @Override
    public Num absoluteValue() {
        return new DoubleNum(Math.abs(wrapped));
    }

    @Override
    public Num negate() {
        return new DoubleNum(-wrapped);
    }

    @Override
    public Num reciprocal() {
        final double reciprocal = 1.0 / wrapped;
        return !isFinite(reciprocal) ? NaN : new DoubleNum(reciprocal);
    }

    @Override
    public Num floor() {
        return new DoubleNum(Math.floor(wrapped));
    }

    @Override
    public Num ceil() {
        return new DoubleNum(Math.ceil(wrapped));
    }

    @Override
    public Num degrees() {
        return new DoubleNum(Math.toDegrees(wrapped));
    }

    @Override
    public Num radians() {
        return new DoubleNum(Math.toRadians(wrapped));
    }

    @Override
    public Num sine() {
        final double sine = Math.sin(wrapped);
        return !isFinite(sine) ? NaN : new DoubleNum(sine);
    }

    @Override
    public Num cosine() {
        final double cosine = Math.cos(wrapped);
        return !isFinite(cosine) ? NaN : new DoubleNum(cosine);
    }

    @Override
    public Num tangent() {
        final double tangent = Math.tan(wrapped);
        return !isFinite(tangent) ? NaN : new DoubleNum(tangent);
    }

    @Override
    public Num inverseSine() {
        final double inverseSine = Math.asin(wrapped);
        return !isFinite(inverseSine) ? NaN : new DoubleNum(inverseSine);
    }

    @Override
    public Num inverseCosine() {
        final double inverseCosine = Math.acos(wrapped);
        return !isFinite(inverseCosine) ? NaN : new DoubleNum(inverseCosine);
    }

    @Override
    public Num inverseTangent() {
        final double inverseTangent = Math.atan(wrapped);
        return !isFinite(inverseTangent) ? NaN : new DoubleNum(inverseTangent);
    }

    @Override
    public Num inverseTangent2(Num x) {
        if (x.isNaN()) {
            return NaN;
        } else if (x instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).inverseTangent2(decimalNum);
        } else {
            final double inverseTangent2 = Math.atan2(wrapped, ((DoubleNum) x).wrapped);
            return !isFinite(inverseTangent2) ? NaN : new DoubleNum(inverseTangent2);
        }
    }

    @Override
    public Num hyperbolicSine() {
        final double hyperbolicSine = Math.sinh(wrapped);
        return !isFinite(hyperbolicSine) ? NaN : new DoubleNum(hyperbolicSine);
    }

    @Override
    public Num hyperbolicCosine() {
        final double hyperbolicCosine = Math.cosh(wrapped);
        return !isFinite(hyperbolicCosine) ? NaN : new DoubleNum(hyperbolicCosine);
    }

    @Override
    public Num hyperbolicTangent() {
        final double hyperbolicTangent = Math.tanh(wrapped);
        return !isFinite(hyperbolicTangent) ? NaN : new DoubleNum(hyperbolicTangent);
    }

    @Override
    public Num inverseHyperbolicSine() {
        final double inverseHyperbolicSine = Math.log(wrapped + Math.sqrt(wrapped * wrapped + 1.0));
        return !isFinite(inverseHyperbolicSine) ? NaN : new DoubleNum(inverseHyperbolicSine);
    }

    @Override
    public Num inverseHyperbolicCosine() {
        final double inverseHyperbolicCosine = Math.log(wrapped + Math.sqrt(wrapped * wrapped - 1.0));
        return !isFinite(inverseHyperbolicCosine) ? NaN : new DoubleNum(inverseHyperbolicCosine);
    }

    @Override
    public Num inverseHyperbolicTangent() {
        final double inverseHyperbolicTangent = 0.5 * Math.log((1.0 + wrapped) / (1.0 - wrapped));
        return !isFinite(inverseHyperbolicTangent) ? NaN : new DoubleNum(inverseHyperbolicTangent);
    }

    @Override
    public Num hypotenuse(Num y) {
        if (y.isNaN()) {
            return NaN;
        } else if (y instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).hypotenuse(decimalNum);
        } else {
            final double hypotenuse = Math.hypot(wrapped, ((DoubleNum) y).wrapped);
            return !isFinite(hypotenuse) ? NaN : new DoubleNum(hypotenuse);
        }
    }

    @Override
    public Num average(Num other) {
        if (other.isNaN()) {
            return NaN;
        } else if (other instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).average(decimalNum);
        } else {
            return new DoubleNum((wrapped + ((DoubleNum) other).wrapped) * 0.5);
        }
    }

    @Override
    public Num integerPart() {
        return new DoubleNum((int) wrapped);
    }

    @Override
    public Num fractionalPart() {
        return new DoubleNum(wrapped - ((int) wrapped));
    }

    @Override
    public Num round(int scale, RoundingMode roundingMode) {
        if (roundingMode == HALF_UP || roundingMode == HALF_EVEN) {
            if (scale == 0) {
                return new DoubleNum(roundingMode == HALF_UP ? Math.round(wrapped) : Math.rint(wrapped));
            } else {
                final double multiplier = switch (scale) {
                    case 1 -> 10.0;
                    case 2 -> 100.0;
                    case 3 -> 1000.0;
                    case 4 -> 10000.0;
                    case 8 -> 100000000.0;
                    default -> Math.pow(10, scale);
                };
                final double toRound = wrapped * multiplier;
                return new DoubleNum((roundingMode == HALF_UP ? Math.rint(toRound) : Math.round(toRound)) / multiplier);
            }
        } else {
            return new DoubleNum(asBigDecimal().setScale(scale, roundingMode).doubleValue());
        }
    }

    @Override
    public Num precision(MathContext context) {
        return new DoubleNum(asBigDecimal().round(context).doubleValue());
    }

    @Override
    public int signum() {
        return (int) Math.signum(wrapped);
    }

    @Override
    public boolean isNegative() {
        return wrapped < 0;
    }

    @Override
    public boolean isNegativeOrZero() {
        return wrapped <= 0;
    }

    @Override
    public boolean isNegativeOrZero(Num epsilon) {
        if (epsilon.isNaN()) {
            return false;
        } else if (epsilon instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isNegativeOrZero(decimalNum);
        } else {
            return wrapped <= ((DoubleNum) epsilon).wrapped;
        }
    }

    @Override
    public boolean isPositive() {
        return wrapped > 0;
    }

    @Override
    public boolean isPositiveOrZero() {
        return wrapped >= 0;
    }

    @Override
    public boolean isPositiveOrZero(Num epsilon) {
        if (epsilon.isNaN()) {
            return false;
        } else if (epsilon instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isPositiveOrZero(decimalNum);
        } else {
            return wrapped >= -((DoubleNum) epsilon).wrapped;
        }
    }

    @Override
    public boolean isZero() {
        return wrapped == 0;
    }

    @Override
    public boolean isZero(Num epsilon) {
        if (epsilon.isNaN()) {
            return false;
        } else if (epsilon instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isZero(decimalNum);
        } else {
            return Math.abs(wrapped) <= ((DoubleNum) epsilon).wrapped;
        }
    }

    @Override
    public boolean isEqual(Num other) {
        if (other.isNaN()) {
            return false;
        } else if (other instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isEqual(decimalNum);
        } else {
            return wrapped == ((DoubleNum) other).wrapped;
        }
    }

    @Override
    public boolean isEqual(Num other, Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        } else if (other instanceof DecimalNum || epsilon instanceof DecimalNum) {
            return other.factory().of(this).isEqual(other, epsilon);
        } else {
            return Math.abs(wrapped - ((DoubleNum) other).wrapped) <= ((DoubleNum) epsilon).wrapped;
        }
    }

    @Override
    public boolean isLessThan(Num other) {
        if (other.isNaN()) {
            return false;
        } else if (other instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isLessThan(decimalNum);
        } else {
            return wrapped < ((DoubleNum) other).wrapped;
        }
    }

    @Override
    public boolean isLessThanOrEqual(Num other) {
        if (other.isNaN()) {
            return false;
        } else if (other instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isLessThanOrEqual(decimalNum);
        } else {
            return wrapped <= ((DoubleNum) other).wrapped;
        }
    }

    @Override
    public boolean isLessThanOrEqual(Num other, Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        } else if (other instanceof DecimalNum || epsilon instanceof DecimalNum) {
            return other.factory().of(this).isLessThanOrEqual(other, epsilon);
        } else {
            return ((DoubleNum) other).wrapped - wrapped >= -((DoubleNum) epsilon).wrapped;
        }
    }

    @Override
    public boolean isGreaterThan(Num other) {
        if (other.isNaN()) {
            return false;
        } else if (other instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isGreaterThan(decimalNum);
        } else {
            return wrapped > ((DoubleNum) other).wrapped;
        }
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other) {
        if (other.isNaN()) {
            return false;
        } else if (other instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isGreaterThanOrEqual(decimalNum);
        } else {
            return wrapped >= ((DoubleNum) other).wrapped;
        }
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other, Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        } else if (other instanceof DecimalNum || epsilon instanceof DecimalNum) {
            return other.factory().of(this).isGreaterThanOrEqual(other, epsilon);
        } else {
            return wrapped - ((DoubleNum) other).wrapped >= -((DoubleNum) epsilon).wrapped;
        }
    }

    @Override
    public boolean isNaN() {
        return false;
    }

    @Override
    public BigDecimal asBigDecimal() {
        return new BigDecimal(toString(), getContext());
    }

    @Override
    public MathContext getContext() {
        return DECIMAL64;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DoubleNum doubleNum && ((Double) wrapped).equals(doubleNum.wrapped);
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
    public int compareTo(@NotNull Num o) {
        if (o.isNaN()) {
            return 0;
        } else if (o instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).compareTo(o);
        } else {
            return Double.compare(wrapped, ((DoubleNum) o).wrapped);
        }
    }
}
