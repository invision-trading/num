package trade.invision.num;

import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;

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
 * @see <a href="https://en.wikipedia.org/wiki/Floating-point_arithmetic">
 * wikipedia.org/wiki/Floating-point_arithmetic</a>
 * @see <a href="https://en.wikipedia.org/wiki/Double-precision_floating-point_format">
 * wikipedia.org/wiki/Double-precision_floating-point_format</a>
 */
@NullMarked
public final class DoubleNum implements Num {

    /**
     * Creates a new {@link DoubleNum} from the given {@link Number}.
     *
     * @param number the {@link Number}
     *
     * @return the {@link Num}
     */
    public static Num doubleNum(final Number number) {
        final var aDouble = number.doubleValue();
        return !isFinite(aDouble) ? NaN : new DoubleNum(aDouble);
    }

    /**
     * Creates a new {@link DoubleNum} from the given {@link BigDecimal}.
     *
     * @param bigDecimal the {@link BigDecimal}
     *
     * @return the {@link Num}
     */
    public static Num doubleNum(final BigDecimal bigDecimal) {
        return doubleNum((Number) bigDecimal);
    }

    /**
     * Creates a new {@link DoubleNum} from the given {@link String} representing a number.
     *
     * @param string the {@link String}
     *
     * @return the {@link Num}
     *
     * @throws NumberFormatException thrown for {@link NumberFormatException}s
     */
    public static Num doubleNum(final String string) throws NumberFormatException {
        final var aDouble = parseDouble(string);
        return !isFinite(aDouble) ? NaN : new DoubleNum(aDouble);
    }

    /**
     * Creates a new {@link DoubleNum} from the given {@link Num}.
     *
     * @param num the {@link Num}
     *
     * @return the {@link Num}
     */
    public static Num doubleNum(final Num num) {
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
        public Num of(final Number number) {
            if (number instanceof final Integer aInteger) {
                // Only using sequential numbers enables `TABLESWITCH` usage.
                return switch (aInteger) {
                    case -1 -> negativeOne();
                    case 0 -> zero();
                    case 1 -> one();
                    case 2 -> two();
                    case 3 -> three();
                    case 4 -> four();
                    case 5 -> five();
                    case 6 -> six();
                    case 7 -> seven();
                    case 8 -> eight();
                    case 9 -> nine();
                    case 10 -> ten();
                    default -> doubleNum(number);
                };
            }
            return doubleNum(number);
        }

        @Override
        public Num of(final BigDecimal bigDecimal) {
            return doubleNum(bigDecimal);
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
        public Num random(final RandomGenerator randomGenerator) {
            return of(randomGenerator.nextDouble());
        }
    };

    private static final double NATURAL_LOGARITHM_OF_2 = 0.6931471805599453;
    private static final Num PI = doubleNum(Math.PI);
    private static final Num E = doubleNum(Math.E);

    private final double wrapped;

    private DoubleNum(final double wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Num add(final Num addend) {
        if (addend.isNaN()) {
            return NaN;
        }
        if (addend instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).add(decimalNum);
        }
        return new DoubleNum(wrapped + ((DoubleNum) addend).wrapped);
    }

    @Override
    public Num subtract(final Num subtrahend) {
        if (subtrahend.isNaN()) {
            return NaN;
        }
        if (subtrahend instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).subtract(decimalNum);
        }
        return new DoubleNum(wrapped - ((DoubleNum) subtrahend).wrapped);
    }

    @Override
    public Num multiply(final Num multiplier) {
        if (multiplier.isNaN()) {
            return NaN;
        }
        if (multiplier instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).multiply(decimalNum);
        }
        return new DoubleNum(wrapped * ((DoubleNum) multiplier).wrapped);
    }

    @Override
    public Num divide(final Num divisor) {
        if (divisor.isNaN()) {
            return NaN;
        }
        if (divisor instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).divide(decimalNum);
        }
        final var quotient = wrapped / ((DoubleNum) divisor).wrapped;
        return !isFinite(quotient) ? NaN : new DoubleNum(quotient);
    }

    @Override
    public Num remainder(final Num divisor) {
        if (divisor.isNaN()) {
            return NaN;
        }
        if (divisor instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).remainder(decimalNum);
        }
        final var remainder = wrapped % ((DoubleNum) divisor).wrapped;
        return !isFinite(remainder) ? NaN : new DoubleNum(remainder);
    }

    @Override
    public Num power(final Num exponent) {
        if (exponent.isNaN()) {
            return NaN;
        }
        if (exponent instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).power(decimalNum);
        }
        final var power = Math.pow(wrapped, ((DoubleNum) exponent).wrapped);
        return !isFinite(power) ? NaN : new DoubleNum(power);
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
    public Num nthRoot(final Num degree) {
        if (degree.isNaN()) {
            return NaN;
        }
        if (degree instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).nthRoot(decimalNum);
        }
        final var nthRoot = Math.pow(wrapped, 1.0 / ((DoubleNum) degree).wrapped);
        return !isFinite(nthRoot) ? NaN : new DoubleNum(nthRoot);
    }

    @Override
    public Num squareRoot() {
        final var squareRoot = Math.sqrt(wrapped);
        return !isFinite(squareRoot) ? NaN : new DoubleNum(squareRoot);
    }

    @Override
    public Num cubeRoot() {
        final var cubeRoot = Math.cbrt(wrapped);
        return !isFinite(cubeRoot) ? NaN : new DoubleNum(cubeRoot);
    }

    @Override
    public Num ln() {
        final var naturalLog = Math.log(wrapped);
        return !isFinite(naturalLog) ? NaN : new DoubleNum(naturalLog);
    }

    @Override
    public Num log10() {
        final var commonLog = Math.log10(wrapped);
        return !isFinite(commonLog) ? NaN : new DoubleNum(commonLog);
    }

    @Override
    public Num log2() {
        final var numerator = Math.log(wrapped);
        return !isFinite(numerator) ? NaN : new DoubleNum(numerator / NATURAL_LOGARITHM_OF_2);
    }

    @Override
    public Num log(final Num base) {
        if (base.isNaN()) {
            return NaN;
        }
        if (base instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).log(decimalNum);
        }
        final var log = Math.log(wrapped) / Math.log(((DoubleNum) base).wrapped);
        return !isFinite(log) ? NaN : new DoubleNum(log);
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
        final var reciprocal = 1.0 / wrapped;
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
    public Num sin() {
        final var sine = Math.sin(wrapped);
        return !isFinite(sine) ? NaN : new DoubleNum(sine);
    }

    @Override
    public Num cos() {
        final var cosine = Math.cos(wrapped);
        return !isFinite(cosine) ? NaN : new DoubleNum(cosine);
    }

    @Override
    public Num tan() {
        final var tangent = Math.tan(wrapped);
        return !isFinite(tangent) ? NaN : new DoubleNum(tangent);
    }

    @Override
    public Num asin() {
        final var inverseSine = Math.asin(wrapped);
        return !isFinite(inverseSine) ? NaN : new DoubleNum(inverseSine);
    }

    @Override
    public Num acos() {
        final var inverseCosine = Math.acos(wrapped);
        return !isFinite(inverseCosine) ? NaN : new DoubleNum(inverseCosine);
    }

    @Override
    public Num atan() {
        final var inverseTangent = Math.atan(wrapped);
        return !isFinite(inverseTangent) ? NaN : new DoubleNum(inverseTangent);
    }

    @Override
    public Num atan2(final Num x) {
        if (x.isNaN()) {
            return NaN;
        }
        if (x instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).atan2(decimalNum);
        }
        final var inverseTangent2 = Math.atan2(wrapped, ((DoubleNum) x).wrapped);
        return !isFinite(inverseTangent2) ? NaN : new DoubleNum(inverseTangent2);
    }

    @Override
    public Num sinh() {
        final var hyperbolicSine = Math.sinh(wrapped);
        return !isFinite(hyperbolicSine) ? NaN : new DoubleNum(hyperbolicSine);
    }

    @Override
    public Num cosh() {
        final var hyperbolicCosine = Math.cosh(wrapped);
        return !isFinite(hyperbolicCosine) ? NaN : new DoubleNum(hyperbolicCosine);
    }

    @Override
    public Num tanh() {
        final var hyperbolicTangent = Math.tanh(wrapped);
        return !isFinite(hyperbolicTangent) ? NaN : new DoubleNum(hyperbolicTangent);
    }

    @Override
    public Num asinh() {
        final var inverseHyperbolicSine = Math.log(wrapped + Math.sqrt(wrapped * wrapped + 1.0));
        return !isFinite(inverseHyperbolicSine) ? NaN : new DoubleNum(inverseHyperbolicSine);
    }

    @Override
    public Num acosh() {
        final var inverseHyperbolicCosine = Math.log(wrapped + Math.sqrt(wrapped * wrapped - 1.0));
        return !isFinite(inverseHyperbolicCosine) ? NaN : new DoubleNum(inverseHyperbolicCosine);
    }

    @Override
    public Num atanh() {
        final var inverseHyperbolicTangent = 0.5 * Math.log((1.0 + wrapped) / (1.0 - wrapped));
        return !isFinite(inverseHyperbolicTangent) ? NaN : new DoubleNum(inverseHyperbolicTangent);
    }

    @Override
    public Num hypotenuse(final Num y) {
        if (y.isNaN()) {
            return NaN;
        }
        if (y instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).hypotenuse(decimalNum);
        }
        final var hypotenuse = Math.hypot(wrapped, ((DoubleNum) y).wrapped);
        return !isFinite(hypotenuse) ? NaN : new DoubleNum(hypotenuse);
    }

    @Override
    public Num average(final Num other) {
        if (other.isNaN()) {
            return NaN;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).average(decimalNum);
        }
        return new DoubleNum((wrapped + ((DoubleNum) other).wrapped) * 0.5);
    }

    @Override
    public Num min(final Num other) {
        if (other.isNaN()) {
            return NaN;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).min(decimalNum);
        }
        return wrapped <= ((DoubleNum) other).wrapped ? this : other;
    }

    @Override
    public Num max(final Num other) {
        if (other.isNaN()) {
            return NaN;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).max(decimalNum);
        }
        return wrapped >= ((DoubleNum) other).wrapped ? this : other;
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
    public Num round(final int scale, final RoundingMode roundingMode) {
        if (roundingMode == HALF_UP) {
            if (scale == 0) {
                return new DoubleNum(Math.rint(wrapped));
            }
            final var multiplier = switch (scale) {
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
        return new DoubleNum(decimalNum(this, getContext()).round(scale, roundingMode).toDouble());
    }

    @Override
    public Num significantFigures(final MathContext context) {
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
    public boolean isNegativeOrZero(final Num epsilon) {
        if (epsilon.isNaN()) {
            return false;
        }
        if (epsilon instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isNegativeOrZero(decimalNum);
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
            return decimalNum.factory().of(this).isPositiveOrZero(decimalNum);
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
            return decimalNum.factory().of(this).isZero(decimalNum);
        }
        return Math.abs(wrapped) <= ((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isEqual(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isEqual(decimalNum);
        }
        return wrapped == ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isEqual(final Num other, final Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        if (other instanceof DecimalNum || epsilon instanceof DecimalNum) {
            return other.factory().of(this).isEqual(other, epsilon);
        }
        return Math.abs(wrapped - ((DoubleNum) other).wrapped) <= ((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isLessThan(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isLessThan(decimalNum);
        }
        return wrapped < ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isLessThanOrEqual(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isLessThanOrEqual(decimalNum);
        }
        return wrapped <= ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isLessThanOrEqual(final Num other, final Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        if (other instanceof DecimalNum || epsilon instanceof DecimalNum) {
            return other.factory().of(this).isLessThanOrEqual(other, epsilon);
        }
        return ((DoubleNum) other).wrapped - wrapped >= -((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isGreaterThan(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isGreaterThan(decimalNum);
        }
        return wrapped > ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isGreaterThanOrEqual(final Num other) {
        if (other.isNaN()) {
            return false;
        }
        if (other instanceof final DecimalNum decimalNum) {
            return decimalNum.factory().of(this).isGreaterThanOrEqual(decimalNum);
        }
        return wrapped >= ((DoubleNum) other).wrapped;
    }

    @Override
    public boolean isGreaterThanOrEqual(final Num other, final Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        if (other instanceof DecimalNum || epsilon instanceof DecimalNum) {
            return other.factory().of(this).isGreaterThanOrEqual(other, epsilon);
        }
        return wrapped - ((DoubleNum) other).wrapped >= -((DoubleNum) epsilon).wrapped;
    }

    @Override
    public boolean isNaN() {
        return false;
    }

    @Override
    public Num ifNaN(final Number replacement) {
        return this;
    }

    @Override
    public Num ifNaN(final BigDecimal replacement) {
        return this;
    }

    @Override
    public Num ifNaN(final String replacement) {
        return this;
    }

    @Override
    public Num ifNaN(final Function<NumFactory, Num> replacement) {
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
    public Num ifNaNThrow(final RuntimeException runtimeException) {
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
        return DECIMAL64;
    }

    @Override
    public NumFactory factory() {
        return FACTORY;
    }

    @Override
    public boolean equals(final Object obj) {
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
            return decimalNum.factory().of(this).compareTo(o);
        }
        return Double.compare(wrapped, ((DoubleNum) o).wrapped);
    }
}
