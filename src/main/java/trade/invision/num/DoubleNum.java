package trade.invision.num;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Double.isFinite;
import static java.lang.Double.parseDouble;
import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.HALF_UP;
import static trade.invision.num.DecimalNum.decimalNum;
import static trade.invision.num.NaNNum.NaN;

/**
 * {@link DoubleNum} is a {@link Num} implementation using floating-point binary numbers via {@link Double}. The
 * <code>double</code> primitive type is used where possible in order to avoid the {@link Double} boxed type.
 *
 * @see Num
 * @see Double
 * @see Math
 * @see <a href="https://en.wikipedia.org/wiki/Floating-point_arithmetic">Wikipedia</a>
 * @see <a href="https://en.wikipedia.org/wiki/Double-precision_floating-point_format">Wikipedia</a>
 */
public final class DoubleNum implements Num {

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

    private static final Num NEGATIVE_ONE = doubleNum(-1);
    private static final Num ZERO = doubleNum(0);
    private static final Num ONE = doubleNum(1);
    private static final Num TWO = doubleNum(2);
    private static final Num THREE = doubleNum(3);
    private static final Num FOUR = doubleNum(4);
    private static final Num FIVE = doubleNum(5);
    private static final Num SIX = doubleNum(6);
    private static final Num SEVEN = doubleNum(7);
    private static final Num EIGHT = doubleNum(8);
    private static final Num NINE = doubleNum(9);
    private static final Num TEN = doubleNum(10);
    private static final Num HUNDRED = doubleNum(100);
    private static final Num THOUSAND = doubleNum(1000);
    private static final Num TENTH = doubleNum(0.1);
    private static final Num HUNDREDTH = doubleNum(0.01);
    private static final Num THOUSANDTH = doubleNum(0.001);
    private static final Num HALF = doubleNum(0.5);

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
        public Num four() {
            return FOUR;
        }

        @Override
        public Num five() {
            return FIVE;
        }

        @Override
        public Num six() {
            return SIX;
        }

        @Override
        public Num seven() {
            return SEVEN;
        }

        @Override
        public Num eight() {
            return EIGHT;
        }

        @Override
        public Num nine() {
            return NINE;
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
        public Num half() {
            return HALF;
        }

        @Override
        public Num random() {
            return of(ThreadLocalRandom.current().nextDouble());
        }
    };

    private static final double NATURAL_LOGARITHM_OF_2 = 0.6931471805599453;
    private static final Num PI = doubleNum(Math.PI);
    private static final Num E = doubleNum(Math.E);

    private final double wrapped;

    private DoubleNum(double aDouble) {
        wrapped = aDouble;
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
    public Num multiply(Num multiplier) {
        if (multiplier.isNaN()) {
            return NaN;
        } else if (multiplier instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).multiply(decimalNum);
        } else {
            return new DoubleNum(wrapped * ((DoubleNum) multiplier).wrapped);
        }
    }

    @Override
    public Num divide(Num divisor) {
        if (divisor.isNaN()) {
            return NaN;
        } else if (divisor instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).divide(decimalNum);
        } else {
            final double quotient = wrapped / ((DoubleNum) divisor).wrapped;
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
            final double logarithm = Math.log(wrapped) / Math.log(((DoubleNum) base).wrapped);
            if (!isFinite(logarithm)) {
                return NaN;
            }
            return new DoubleNum(logarithm);
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
    public Num increment() {
        return new DoubleNum(wrapped + 1.0);
    }

    @Override
    public Num decrement() {
        return new DoubleNum(wrapped - 1.0);
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
    public Num pi() {
        return PI;
    }

    @Override
    public Num e() {
        return E;
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
        if (roundingMode == HALF_UP) {
            if (scale == 0) {
                return new DoubleNum(Math.rint(wrapped));
            } else {
                final double multiplier = switch (scale) {
                    case -9 -> 0.000000001;
                    case -8 -> 0.00000001;
                    case -7 -> 0.0000001;
                    case -6 -> 0.000001;
                    case -5 -> 0.00001;
                    case -4 -> 0.0001;
                    case -3 -> 0.001;
                    case -2 -> 0.01;
                    case -1 -> 0.1;
                    case 1 -> 10.0;
                    case 2 -> 100.0;
                    case 3 -> 1000.0;
                    case 4 -> 10000.0;
                    case 5 -> 100000.0;
                    case 6 -> 1000000.0;
                    case 7 -> 10000000.0;
                    case 8 -> 100000000.0;
                    case 9 -> 1000000000.0;
                    default -> Math.pow(10, scale);
                };
                return new DoubleNum(Math.rint(wrapped * multiplier) / multiplier);
            }
        } else {
            return new DoubleNum(decimalNum(this, getContext()).round(scale, roundingMode).toDouble());
        }
    }

    @Override
    public Num significantFigures(MathContext context) {
        return new DoubleNum(decimalNum(this, getContext()).significantFigures(context).toDouble());
    }

    @Override
    public int significantFigures() {
        return decimalNum(this, getContext()).significantFigures();
    }

    @Override
    public Num mantissa() {
        return new DoubleNum(decimalNum(this, getContext()).mantissa().toDouble());
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
    public boolean isNegativeOrZero(Num epsilon) {
        if (epsilon.isZero()) {
            return isNegativeOrZero();
        } else if (epsilon.isNaN()) {
            return false;
        } else if (epsilon instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isNegativeOrZero(decimalNum);
        } else {
            return wrapped <= ((DoubleNum) epsilon).wrapped;
        }
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
    public boolean isPositiveOrZero(Num epsilon) {
        if (epsilon.isZero()) {
            return isPositiveOrZero();
        } else if (epsilon.isNaN()) {
            return false;
        } else if (epsilon instanceof DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isPositiveOrZero(decimalNum);
        } else {
            return wrapped >= -((DoubleNum) epsilon).wrapped;
        }
    }

    @Override
    public boolean isZero() {
        return wrapped == 0.0;
    }

    @Override
    public boolean isZero(Num epsilon) {
        if (epsilon.isZero()) {
            return isZero();
        } else if (epsilon.isNaN()) {
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
        if (epsilon.isZero()) {
            return isEqual(other);
        } else if (other.isNaN() || epsilon.isNaN()) {
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
        if (epsilon.isZero()) {
            return isLessThanOrEqual(other);
        } else if (other.isNaN() || epsilon.isNaN()) {
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
        if (epsilon.isZero()) {
            return isGreaterThanOrEqual(other);
        } else if (other.isNaN() || epsilon.isNaN()) {
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
    public Num ifNaN(Num replacement) {
        return this;
    }

    @Override
    public Number unwrap() {
        return wrapped;
    }

    @Override
    public BigDecimal toBigDecimal() {
        return new BigDecimal(toString(), getContext());
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
