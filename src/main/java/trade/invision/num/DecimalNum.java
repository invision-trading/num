package trade.invision.num;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.google.errorprone.annotations.concurrent.LazyInit;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.random.RandomGenerator;

import static java.math.BigDecimal.ONE;
import static java.math.MathContext.DECIMAL128;
import static java.math.MathContext.DECIMAL32;
import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.CEILING;
import static java.math.RoundingMode.FLOOR;
import static java.math.RoundingMode.HALF_EVEN;

/**
 * {@link DecimalNum} is a {@link Num} implementation using arbitrary-precision decimal numbers via {@link BigDecimal}.
 *
 * @see BigDecimal
 * @see BigDecimalMath
 * @see <a href="https://en.wikipedia.org/wiki/Arbitrary-precision_arithmetic">
 * wikipedia.org/wiki/Arbitrary-precision_arithmetic</a>
 * @see <a href="https://github.com/eobermuhlner/big-math">github.com/eobermuhlner/big-math</a>
 */
@NullMarked
public final class DecimalNum implements Num {

    private static class Factory implements NumFactory {

        private final @SuppressWarnings("Immutable") MathContext context;
        private @LazyInit @Nullable Num negativeOne;
        private @LazyInit @Nullable Num zero;
        private @LazyInit @Nullable Num one;
        private @LazyInit @Nullable Num two;
        private @LazyInit @Nullable Num three;
        private @LazyInit @Nullable Num four;
        private @LazyInit @Nullable Num five;
        private @LazyInit @Nullable Num six;
        private @LazyInit @Nullable Num seven;
        private @LazyInit @Nullable Num eight;
        private @LazyInit @Nullable Num nine;
        private @LazyInit @Nullable Num ten;
        private @LazyInit @Nullable Num hundred;
        private @LazyInit @Nullable Num thousand;
        private @LazyInit @Nullable Num tenth;
        private @LazyInit @Nullable Num hundredth;
        private @LazyInit @Nullable Num thousandth;
        private @LazyInit @Nullable Num half;

        private Factory(final MathContext context) {
            this.context = context;
        }

        @Override
        public Num of(final Number number) {
            if (number instanceof final Integer aInteger) {
                // Only using sequential numbers enables `TABLESWITCH` bytecode instruction.
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
                    default -> decimalNum(number, context);
                };
            }
            return decimalNum(number, context);
        }

        @Override
        public Num of(final String string) {
            return decimalNum(string, context);
        }

        @Override
        public Num of(final Num num) {
            return decimalNum(num, context);
        }

        @Override
        public Num negativeOne() {
            if (negativeOne == null) {
                negativeOne = of(-1);
            }
            return negativeOne;
        }

        @Override
        public Num zero() {
            if (zero == null) {
                zero = of(0);
            }
            return zero;
        }

        @Override
        public Num one() {
            if (one == null) {
                one = of(1);
            }
            return one;
        }

        @Override
        public Num two() {
            if (two == null) {
                two = of(2);
            }
            return two;
        }

        @Override
        public Num three() {
            if (three == null) {
                three = of(3);
            }
            return three;
        }

        @Override
        public Num four() {
            if (four == null) {
                four = of(4);
            }
            return four;
        }

        @Override
        public Num five() {
            if (five == null) {
                five = of(5);
            }
            return five;
        }

        @Override
        public Num six() {
            if (six == null) {
                six = of(6);
            }
            return six;
        }

        @Override
        public Num seven() {
            if (seven == null) {
                seven = of(7);
            }
            return seven;
        }

        @Override
        public Num eight() {
            if (eight == null) {
                eight = of(8);
            }
            return eight;
        }

        @Override
        public Num nine() {
            if (nine == null) {
                nine = of(9);
            }
            return nine;
        }

        @Override
        public Num ten() {
            if (ten == null) {
                ten = of(10);
            }
            return ten;
        }

        @Override
        public Num hundred() {
            if (hundred == null) {
                hundred = of(100);
            }
            return hundred;
        }

        @Override
        public Num thousand() {
            if (thousand == null) {
                thousand = of(1000);
            }
            return thousand;
        }

        @Override
        public Num tenth() {
            if (tenth == null) {
                tenth = of(0.1);
            }
            return tenth;
        }

        @Override
        public Num hundredth() {
            if (hundredth == null) {
                hundredth = of(0.01);
            }
            return hundredth;
        }

        @Override
        public Num thousandth() {
            if (thousandth == null) {
                thousandth = of(0.001);
            }
            return thousandth;
        }

        @Override
        public Num half() {
            if (half == null) {
                half = of(0.5);
            }
            return half;
        }

        @Override
        public Num random(final RandomGenerator randomGenerator) {
            // https://gist.github.com/Petersoj/749b4ac7906054242ea2a2089a2e5b2d
            final var prefix = "0.";
            final var precision = context.getPrecision();
            final var stringBuilder = new StringBuilder(prefix.length() + precision);
            stringBuilder.append(prefix);
            for (var digitIndex = 0; digitIndex < precision; digitIndex++) {
                stringBuilder.append(randomGenerator.nextInt(10));
            }
            return of(stringBuilder.toString());
        }
    }

    /**
     * @return {@link #decimalNumFactory(MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}
     */
    public static NumFactory decimalNum32Factory() {
        return decimalNumFactory(DECIMAL32);
    }

    /**
     * @return {@link #decimalNumFactory(int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}
     */
    public static NumFactory decimalNum32Factory(final RoundingMode roundingMode) {
        return decimalNumFactory(DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNumFactory(MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}
     */
    public static NumFactory decimalNum64Factory() {
        return decimalNumFactory(DECIMAL64);
    }

    /**
     * @return {@link #decimalNumFactory(int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}
     */
    public static NumFactory decimalNum64Factory(final RoundingMode roundingMode) {
        return decimalNumFactory(DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNumFactory(MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}
     */
    public static NumFactory decimalNum128Factory() {
        return decimalNumFactory(DECIMAL128);
    }

    /**
     * @return {@link #decimalNumFactory(int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}
     */
    public static NumFactory decimalNum128Factory(final RoundingMode roundingMode) {
        return decimalNumFactory(DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNumFactory(int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}
     */
    public static NumFactory decimalNumFactory(final int significantFigures) {
        return decimalNumFactory(significantFigures, HALF_EVEN);
    }

    /**
     * @return {@link #decimalNumFactory(MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>
     */
    public static NumFactory decimalNumFactory(final int significantFigures, final RoundingMode roundingMode) {
        return decimalNumFactory(new MathContext(significantFigures, roundingMode));
    }

    private static final Factory FACTORY_DECIMAL32 = new Factory(DECIMAL32);
    private static final Factory FACTORY_DECIMAL64 = new Factory(DECIMAL64);
    private static final Factory FACTORY_DECIMAL128 = new Factory(DECIMAL128);

    /**
     * Creates a new {@link NumFactory} for {@link DecimalNum} using the given {@link MathContext}.
     *
     * @param context the {@link MathContext}
     *
     * @return the {@link DecimalNum} {@link NumFactory}
     */
    public static NumFactory decimalNumFactory(final MathContext context) {
        if (context.equals(DECIMAL32)) {
            return FACTORY_DECIMAL32;
        }
        if (context.equals(DECIMAL64)) {
            return FACTORY_DECIMAL64;
        }
        if (context.equals(DECIMAL128)) {
            return FACTORY_DECIMAL128;
        }
        return new Factory(context);
    }

    /**
     * @return {@link #nanNum(MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}
     */
    public static Num nanNum32() {
        return nanNum(DECIMAL32);
    }

    /**
     * @return {@link #nanNum(int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}
     */
    public static Num nanNum32(final RoundingMode roundingMode) {
        return nanNum(DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #nanNum(MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}
     */
    public static Num nanNum64() {
        return nanNum(DECIMAL64);
    }

    /**
     * @return {@link #nanNum(int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}
     */
    public static Num nanNum64(final RoundingMode roundingMode) {
        return nanNum(DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #nanNum(MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}
     */
    public static Num nanNum128() {
        return nanNum(DECIMAL128);
    }

    /**
     * @return {@link #nanNum(int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}
     */
    public static Num nanNum128(final RoundingMode roundingMode) {
        return nanNum(DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #nanNum(int, RoundingMode)} with <code>roundingMode</code> set to {@link RoundingMode#HALF_EVEN}
     */
    public static Num nanNum(final int significantFigures) {
        return nanNum(significantFigures, HALF_EVEN);
    }

    /**
     * @return {@link #nanNum(MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>
     */
    public static Num nanNum(final int significantFigures, final RoundingMode roundingMode) {
        return nanNum(new MathContext(significantFigures, roundingMode));
    }

    private static final @SuppressWarnings("IdentifierName") Num NaN_DECIMAL32 =
            NaNNum.nanNum(DECIMAL32, FACTORY_DECIMAL32);
    private static final @SuppressWarnings("IdentifierName") Num NaN_DECIMAL64 =
            NaNNum.nanNum(DECIMAL64, FACTORY_DECIMAL64);
    private static final @SuppressWarnings("IdentifierName") Num NaN_DECIMAL128 =
            NaNNum.nanNum(DECIMAL128, FACTORY_DECIMAL128);

    /**
     * @return {@link NaNNum#nanNum(MathContext, NumFactory)} with {@link #decimalNumFactory(MathContext)}
     */
    public static Num nanNum(final MathContext context) {
        final var factory = decimalNumFactory(context);
        if (factory == FACTORY_DECIMAL32) {
            return NaN_DECIMAL32;
        }
        if (factory == FACTORY_DECIMAL64) {
            return NaN_DECIMAL64;
        }
        if (factory == FACTORY_DECIMAL128) {
            return NaN_DECIMAL128;
        }
        return NaNNum.nanNum(context, factory);
    }

    /**
     * @return {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}
     */
    public static Num decimalNum32(final Number number) {
        return decimalNum(number, DECIMAL32);
    }

    /**
     * @return {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}
     */
    public static Num decimalNum32(final Number number, final RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}
     */
    public static Num decimalNum64(final Number number) {
        return decimalNum(number, DECIMAL64);
    }

    /**
     * @return {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}
     */
    public static Num decimalNum64(final Number number, final RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}
     */
    public static Num decimalNum128(final Number number) {
        return decimalNum(number, DECIMAL128);
    }

    /**
     * @return {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}
     */
    public static Num decimalNum128(final Number number, final RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNum(Number, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}
     */
    public static Num decimalNum(final Number number, final int significantFigures) {
        return decimalNum(number, significantFigures, HALF_EVEN);
    }

    /**
     * @return {@link #decimalNum(Number, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>
     */
    public static Num decimalNum(final Number number, final int significantFigures, final RoundingMode roundingMode) {
        return decimalNum(number, new MathContext(significantFigures, roundingMode));
    }

    /**
     * Creates a new {@link DecimalNum} for the given {@link Number} and {@link MathContext}.
     *
     * @param number  the {@link Number}
     * @param context the {@link MathContext}
     *
     * @return the {@link Num}
     *
     * @throws NumberFormatException thrown for {@link NumberFormatException}s
     */
    public static Num decimalNum(final Number number, final MathContext context) throws NumberFormatException {
        return switch (number) {
            case final Integer aInteger -> new DecimalNum(new BigDecimal(aInteger, context), context);
            case final Long aLong -> new DecimalNum(new BigDecimal(aLong, context), context);
            case final Float aFloat -> !Float.isFinite(aFloat) ? nanNum(context) :
                    new DecimalNum(BigDecimal.valueOf(aFloat).round(context), context);
            case final Double aDouble -> !Double.isFinite(aDouble) ? nanNum(context) :
                    new DecimalNum(BigDecimal.valueOf(aDouble).round(context), context);
            case final BigDecimal bigDecimal -> new DecimalNum(bigDecimal.round(context), context);
            default -> new DecimalNum(new BigDecimal(number.toString(), context), context);
        };
    }

    /**
     * @return {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}
     */
    public static Num decimalNum32(final String string) {
        return decimalNum(string, DECIMAL32);
    }

    /**
     * @return {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}
     */
    public static Num decimalNum32(final String string, final RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}
     */
    public static Num decimalNum64(final String string) {
        return decimalNum(string, DECIMAL64);
    }

    /**
     * @return {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}
     */
    public static Num decimalNum64(final String string, final RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}
     */
    public static Num decimalNum128(final String string) {
        return decimalNum(string, DECIMAL128);
    }

    /**
     * @return {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}
     */
    public static Num decimalNum128(final String string, final RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNum(String, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}
     */
    public static Num decimalNum(final String string, final int significantFigures) {
        return decimalNum(string, significantFigures, HALF_EVEN);
    }

    /**
     * @return {@link #decimalNum(String, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>
     */
    public static Num decimalNum(final String string, final int significantFigures, final RoundingMode roundingMode) {
        return decimalNum(string, new MathContext(significantFigures, roundingMode));
    }

    /**
     * Creates a new {@link DecimalNum} for the given number {@link String} and {@link MathContext}.
     *
     * @param string  the number {@link String}
     * @param context the {@link MathContext}
     *
     * @return the {@link Num}
     *
     * @throws NumberFormatException thrown for {@link NumberFormatException}s
     */
    public static Num decimalNum(final String string, final MathContext context) throws NumberFormatException {
        return string.equals(NaNNum.STRING) ? nanNum(context) :
                new DecimalNum(new BigDecimal(string, context), context);
    }

    /**
     * @return {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}
     */
    public static Num decimalNum32(final Num num) {
        return decimalNum(num, DECIMAL32);
    }

    /**
     * @return {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}
     */
    public static Num decimalNum32(final Num num, final RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}
     */
    public static Num decimalNum64(final Num num) {
        return decimalNum(num, DECIMAL64);
    }

    /**
     * @return {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}
     */
    public static Num decimalNum64(final Num num, final RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}
     */
    public static Num decimalNum128(final Num num) {
        return decimalNum(num, DECIMAL128);
    }

    /**
     * @return {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}
     */
    public static Num decimalNum128(final Num num, final RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * @return {@link #decimalNum(Num, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}
     */
    public static Num decimalNum(final Num num, final int significantFigures) {
        return decimalNum(num, significantFigures, HALF_EVEN);
    }

    /**
     * @return {@link #decimalNum(Num, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>
     */
    public static Num decimalNum(final Num num, final int significantFigures, final RoundingMode roundingMode) {
        return decimalNum(num, new MathContext(significantFigures, roundingMode));
    }

    /**
     * @return {@link #decimalNum(Number, MathContext)} {@link Num#unwrap()}
     */
    public static Num decimalNum(final Num num, final MathContext context) {
        return decimalNum(num.unwrap(), context);
    }

    private static final BigDecimal THREE = new BigDecimal(3);
    private static final BigDecimal HALF = BigDecimal.valueOf(0.5);

    private final BigDecimal wrapped;
    private final @SuppressWarnings("Immutable") MathContext context;
    private @LazyInit @Nullable NumFactory factory;

    private DecimalNum(final BigDecimal wrapped, final MathContext context) {
        this.wrapped = wrapped;
        this.context = context;
    }

    @Override
    public Num add(final Num addend) {
        if (addend.isNaN()) {
            return addend;
        }
        final var decimalNum = asDecimalNum(addend);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(wrapped.add(decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num subtract(final Num subtrahend) {
        if (subtrahend.isNaN()) {
            return subtrahend;
        }
        final var decimalNum = asDecimalNum(subtrahend);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(wrapped.subtract(decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num multiply(final Num multiplier) {
        if (multiplier.isNaN()) {
            return multiplier;
        }
        final var decimalNum = asDecimalNum(multiplier);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(wrapped.multiply(decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num divide(final Num divisor) {
        if (divisor.isNaN()) {
            return divisor;
        }
        final var decimalNum = asDecimalNum(divisor);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(wrapped.divide(decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num remainder(final Num divisor) {
        if (divisor.isNaN()) {
            return divisor;
        }
        final var decimalNum = asDecimalNum(divisor);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(wrapped.remainder(decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num power(final Num exponent) {
        if (exponent.isNaN()) {
            return exponent;
        }
        final var decimalNum = asDecimalNum(exponent);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(BigDecimalMath.pow(wrapped, decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num square() {
        try {
            return new DecimalNum(wrapped.multiply(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num cube() {
        try {
            return new DecimalNum(wrapped.multiply(wrapped, context).multiply(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num exponential() {
        try {
            return new DecimalNum(BigDecimalMath.exp(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num nthRoot(final Num degree) {
        if (degree.isNaN()) {
            return degree;
        }
        final var decimalNum = asDecimalNum(degree);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(BigDecimalMath.root(wrapped, decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num squareRoot() {
        try {
            return new DecimalNum(BigDecimalMath.sqrt(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num cubeRoot() {
        try {
            return new DecimalNum(BigDecimalMath.root(wrapped, THREE, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num ln() {
        try {
            return new DecimalNum(BigDecimalMath.log(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num log10() {
        try {
            return new DecimalNum(BigDecimalMath.log10(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num log2() {
        try {
            return new DecimalNum(BigDecimalMath.log2(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num log(final Num base) {
        if (base.isNaN()) {
            return base;
        }
        final var decimalNum = asDecimalNum(base);
        final var context = precisestContext(decimalNum.context);
        try {
            final var numerator = BigDecimalMath.log(wrapped, context);
            final var denominator = BigDecimalMath.log(decimalNum.wrapped, context);
            return new DecimalNum(numerator.divide(denominator, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num absoluteValue() {
        return new DecimalNum(wrapped.abs(), context);
    }

    @Override
    public Num negate() {
        return new DecimalNum(wrapped.negate(), context);
    }

    @Override
    public Num reciprocal() {
        try {
            return new DecimalNum(BigDecimalMath.reciprocal(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num increment() {
        try {
            return new DecimalNum(wrapped.add(ONE, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num decrement() {
        try {
            return new DecimalNum(wrapped.subtract(ONE, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num floor() {
        try {
            return new DecimalNum(wrapped.setScale(0, FLOOR), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num ceil() {
        try {
            return new DecimalNum(wrapped.setScale(0, CEILING), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num degrees() {
        try {
            return new DecimalNum(BigDecimalMath.toDegrees(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num radians() {
        try {
            return new DecimalNum(BigDecimalMath.toRadians(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num pi() {
        try {
            return new DecimalNum(BigDecimalMath.pi(context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num e() {
        try {
            return new DecimalNum(BigDecimalMath.e(context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num sin() {
        try {
            return new DecimalNum(BigDecimalMath.sin(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num cos() {
        try {
            return new DecimalNum(BigDecimalMath.cos(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num tan() {
        try {
            return new DecimalNum(BigDecimalMath.tan(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num asin() {
        try {
            return new DecimalNum(BigDecimalMath.asin(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num acos() {
        try {
            return new DecimalNum(BigDecimalMath.acos(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num atan() {
        try {
            return new DecimalNum(BigDecimalMath.atan(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num atan2(final Num x) {
        if (x.isNaN()) {
            return x;
        }
        final var decimalNum = asDecimalNum(x);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(BigDecimalMath.atan2(wrapped, decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num sinh() {
        try {
            return new DecimalNum(BigDecimalMath.sinh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num cosh() {
        try {
            return new DecimalNum(BigDecimalMath.cosh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num tanh() {
        try {
            return new DecimalNum(BigDecimalMath.tanh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num asinh() {
        try {
            return new DecimalNum(BigDecimalMath.asinh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num acosh() {
        try {
            return new DecimalNum(BigDecimalMath.acosh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num atanh() {
        try {
            return new DecimalNum(BigDecimalMath.atanh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num hypotenuse(final Num y) {
        if (y.isNaN()) {
            return y;
        }
        final var decimalNum = asDecimalNum(y);
        final var context = precisestContext(decimalNum.context);
        try {
            final var xSquared = wrapped.multiply(wrapped, context);
            final var ySquared = decimalNum.wrapped.multiply(decimalNum.wrapped, context);
            return new DecimalNum(BigDecimalMath.sqrt(xSquared.add(ySquared, context), context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num average(final Num other) {
        if (other.isNaN()) {
            return other;
        }
        final var decimalNum = asDecimalNum(other);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(wrapped.add(decimalNum.wrapped, context).multiply(HALF, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num min(final Num other) {
        if (other.isNaN()) {
            return other;
        }
        final var decimalNum = asDecimalNum(other);
        return wrapped.compareTo(decimalNum.wrapped) <= 0 ? this : decimalNum;
    }

    @Override
    public Num max(final Num other) {
        if (other.isNaN()) {
            return other;
        }
        final var decimalNum = asDecimalNum(other);
        return wrapped.compareTo(decimalNum.wrapped) >= 0 ? this : decimalNum;
    }

    @Override
    public Num integerPart() {
        try {
            return new DecimalNum(BigDecimalMath.integralPart(wrapped), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num fractionalPart() {
        try {
            return new DecimalNum(BigDecimalMath.fractionalPart(wrapped), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num round(final int scale, final RoundingMode roundingMode) {
        try {
            return new DecimalNum(wrapped.setScale(scale, roundingMode), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public Num significantFigures(final MathContext context) {
        try {
            return new DecimalNum(wrapped.round(context), precisestContext(context));
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public int significantFigures() {
        try {
            return wrapped.stripTrailingZeros().precision();
        } catch (final ArithmeticException arithmeticException) {
            return 0;
        }
    }

    @Override
    public Num mantissa() {
        try {
            return new DecimalNum(BigDecimalMath.mantissa(wrapped), context);
        } catch (final ArithmeticException arithmeticException) {
            return nanNum(context);
        }
    }

    @Override
    public int exponent() {
        try {
            return BigDecimalMath.exponent(wrapped);
        } catch (final ArithmeticException arithmeticException) {
            return 0;
        }
    }

    @Override
    public int signum() {
        return wrapped.signum();
    }

    @Override
    public boolean isNegative() {
        return wrapped.signum() < 0;
    }

    @Override
    public boolean isNegativeOrZero() {
        return wrapped.signum() <= 0;
    }

    @Override
    public boolean isNegativeOrZero(final Num epsilon) {
        return !epsilon.isNaN() && wrapped.compareTo(asDecimalNum(epsilon).wrapped) <= 0;
    }

    @Override
    public boolean isPositive() {
        return wrapped.signum() > 0;
    }

    @Override
    public boolean isPositiveOrZero() {
        return wrapped.signum() >= 0;
    }

    @Override
    public boolean isPositiveOrZero(final Num epsilon) {
        return !epsilon.isNaN() && wrapped.compareTo(asDecimalNum(epsilon).wrapped.negate()) >= 0;
    }

    @Override
    public boolean isZero() {
        return wrapped.signum() == 0;
    }

    @Override
    public boolean isZero(final Num epsilon) {
        return !epsilon.isNaN() && wrapped.abs().compareTo(asDecimalNum(epsilon).wrapped) <= 0;
    }

    @Override
    public boolean isEqual(final Num other) {
        return !other.isNaN() && wrapped.compareTo(asDecimalNum(other).wrapped) == 0;
    }

    @Override
    public boolean isEqual(final Num other, final Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        final var decimalNum = asDecimalNum(other);
        try {
            return wrapped.subtract(decimalNum.wrapped, precisestContext(decimalNum.context)).abs()
                    .compareTo(asDecimalNum(epsilon).wrapped) <= 0;
        } catch (final ArithmeticException arithmeticException) {
            return false;
        }
    }

    @Override
    public boolean isLessThan(final Num other) {
        return !other.isNaN() && wrapped.compareTo(asDecimalNum(other).wrapped) < 0;
    }

    @Override
    public boolean isLessThanOrEqual(final Num other) {
        return !other.isNaN() && wrapped.compareTo(asDecimalNum(other).wrapped) <= 0;
    }

    @Override
    public boolean isLessThanOrEqual(final Num other, final Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        final var decimalNum = asDecimalNum(other);
        try {
            return decimalNum.wrapped.subtract(wrapped, precisestContext(decimalNum.context))
                    .compareTo(asDecimalNum(epsilon).wrapped.negate()) >= 0;
        } catch (final ArithmeticException arithmeticException) {
            return false;
        }
    }

    @Override
    public boolean isGreaterThan(final Num other) {
        return !other.isNaN() && wrapped.compareTo(asDecimalNum(other).wrapped) > 0;
    }

    @Override
    public boolean isGreaterThanOrEqual(final Num other) {
        return !other.isNaN() && wrapped.compareTo(asDecimalNum(other).wrapped) >= 0;
    }

    @Override
    public boolean isGreaterThanOrEqual(final Num other, final Num epsilon) {
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        final var decimalNum = asDecimalNum(other);
        try {
            return wrapped.subtract(decimalNum.wrapped, precisestContext(decimalNum.context))
                    .compareTo(asDecimalNum(epsilon).wrapped.negate()) >= 0;
        } catch (final ArithmeticException arithmeticException) {
            return false;
        }
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
        return wrapped;
    }

    @Override
    public MathContext getContext() {
        return context;
    }

    @Override
    public NumFactory getFactory() {
        if (factory == null) {
            factory = decimalNumFactory(context);
        }
        return factory;
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        return obj instanceof final DecimalNum decimalNum &&
                wrapped.equals(decimalNum.wrapped) && context.equals(decimalNum.context);
    }

    @Override
    public int hashCode() {
        var result = wrapped.hashCode();
        result = 31 * result + context.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return wrapped.toString();
    }

    @Override
    public int compareTo(final Num o) {
        return o.isNaN() ? 0 : wrapped.compareTo(asDecimalNum(o).wrapped);
    }

    private DecimalNum asDecimalNum(final Num num) {
        return (DecimalNum) (num instanceof final DoubleNum doubleNum ? getFactory().of(doubleNum) : num);
    }

    private MathContext precisestContext(final MathContext other) {
        return context.getPrecision() > other.getPrecision() ? context : other;
    }
}
