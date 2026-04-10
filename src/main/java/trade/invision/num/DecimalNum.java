package trade.invision.num;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.google.errorprone.annotations.concurrent.LazyInit;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static java.math.BigDecimal.ONE;
import static java.math.MathContext.DECIMAL128;
import static java.math.MathContext.DECIMAL32;
import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.CEILING;
import static java.math.RoundingMode.FLOOR;
import static java.math.RoundingMode.HALF_EVEN;
import static trade.invision.num.NaNNum.NaN;

/**
 * {@link DecimalNum} is a {@link Num} implementation using arbitrary-precision decimal numbers via {@link BigDecimal}.
 *
 * @see Num
 * @see BigDecimal
 * @see BigDecimalMath
 * @see <a href="https://en.wikipedia.org/wiki/Arbitrary-precision_arithmetic">Wikipedia</a>
 * @see <a href="https://github.com/eobermuhlner/big-math">big-math GitHub</a>
 */
@NullMarked
public final class DecimalNum implements Num {

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(final Number number) {
        return decimalNum(number, DECIMAL32);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(final Number number, final RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(final Number number) {
        return decimalNum(number, DECIMAL64);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(final Number number, final RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(final Number number) {
        return decimalNum(number, DECIMAL128);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(final Number number, final RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(final Number number, final int significantFigures) {
        return decimalNum(number, significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(final Number number, final int significantFigures, final RoundingMode roundingMode) {
        return decimalNum(number, new MathContext(significantFigures, roundingMode));
    }

    /**
     * Creates a new {@link DecimalNum} using the given {@link Number} and {@link MathContext}.
     *
     * @param number  the {@link Number}
     * @param context the {@link MathContext}
     *
     * @return the {@link Num}
     */
    public static Num decimalNum(final Number number, final MathContext context) {
        return switch (number) {
            case final Integer aInt -> new DecimalNum(new BigDecimal(aInt, context), context);
            case final Long aLong -> new DecimalNum(new BigDecimal(aLong, context), context);
            case final Float aFloat -> !Float.isFinite(aFloat) ? NaN :
                    new DecimalNum(new BigDecimal(aFloat.toString(), context), context);
            case final Double aDouble -> !Double.isFinite(aDouble) ? NaN :
                    new DecimalNum(new BigDecimal(aDouble.toString(), context), context);
            case final BigDecimal bigDecimal -> decimalNum(bigDecimal, context);
            default -> new DecimalNum(new BigDecimal(number.toString(), context), context);
        };
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(final String string) {
        return decimalNum(string, DECIMAL32);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(final String string, final RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(final String string) {
        return decimalNum(string, DECIMAL64);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(final String string, final RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(final String string) {
        return decimalNum(string, DECIMAL128);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(final String string, final RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(final String string, final int significantFigures) {
        return decimalNum(string, significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(final String string, final int significantFigures, final RoundingMode roundingMode) {
        return decimalNum(string, new MathContext(significantFigures, roundingMode));
    }

    /**
     * Creates a new {@link DecimalNum} using the given {@link String} representing a number and the given
     * {@link MathContext}.
     *
     * @param string  the {@link String}
     * @param context the {@link MathContext}
     *
     * @return the {@link Num}
     */
    public static Num decimalNum(final String string, final MathContext context) {
        return string.equals(NaN.toString()) ? NaN : new DecimalNum(new BigDecimal(string, context), context);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with <code>context</code> set to
     * {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(final BigDecimal bigDecimal) {
        return decimalNum(bigDecimal, DECIMAL32);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(final BigDecimal bigDecimal, final RoundingMode roundingMode) {
        return decimalNum(bigDecimal, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with <code>context</code> set to
     * {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(final BigDecimal bigDecimal) {
        return decimalNum(bigDecimal, DECIMAL64);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(final BigDecimal bigDecimal, final RoundingMode roundingMode) {
        return decimalNum(bigDecimal, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with <code>context</code> set to
     * {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(final BigDecimal bigDecimal) {
        return decimalNum(bigDecimal, DECIMAL128);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(final BigDecimal bigDecimal, final RoundingMode roundingMode) {
        return decimalNum(bigDecimal, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(final BigDecimal bigDecimal, final int significantFigures) {
        return decimalNum(bigDecimal, significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(final BigDecimal bigDecimal, final int significantFigures,
            final RoundingMode roundingMode) {
        return decimalNum(bigDecimal, new MathContext(significantFigures, roundingMode));
    }

    /**
     * Creates a new {@link DecimalNum} using the given {@link BigDecimal} and {@link MathContext}.
     *
     * @param bigDecimal the {@link BigDecimal}
     * @param context    the {@link MathContext}
     *
     * @return the {@link Num}
     */
    public static Num decimalNum(final BigDecimal bigDecimal, final MathContext context) {
        return new DecimalNum(bigDecimal.round(context), context);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(final Num num) {
        return decimalNum(num, DECIMAL32);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(final Num num, final RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(final Num num) {
        return decimalNum(num, DECIMAL64);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(final Num num, final RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(final Num num) {
        return decimalNum(num, DECIMAL128);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(final Num num, final RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(final Num num, final int significantFigures) {
        return decimalNum(num, significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(final Num num, final int significantFigures, final RoundingMode roundingMode) {
        return decimalNum(num, new MathContext(significantFigures, roundingMode));
    }

    /**
     * Creates a new {@link DecimalNum} using the given {@link Num} and {@link MathContext}.
     *
     * @param num     the {@link Num}
     * @param context the {@link MathContext}
     *
     * @return the {@link Num}
     */
    public static Num decimalNum(final Num num, final MathContext context) {
        return decimalNum(num.unwrap(), context);
    }

    /**
     * Calls {@link #decimalNumFactory(MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static NumFactory decimalNum32Factory() {
        return decimalNumFactory(DECIMAL32);
    }

    /**
     * Calls {@link #decimalNumFactory(int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static NumFactory decimalNum32Factory(final RoundingMode roundingMode) {
        return decimalNumFactory(DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNumFactory(MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static NumFactory decimalNum64Factory() {
        return decimalNumFactory(DECIMAL64);
    }

    /**
     * Calls {@link #decimalNumFactory(int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static NumFactory decimalNum64Factory(final RoundingMode roundingMode) {
        return decimalNumFactory(DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNumFactory(MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static NumFactory decimalNum128Factory() {
        return decimalNumFactory(DECIMAL128);
    }

    /**
     * Calls {@link #decimalNumFactory(int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static NumFactory decimalNum128Factory(final RoundingMode roundingMode) {
        return decimalNumFactory(DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNumFactory(int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static NumFactory decimalNumFactory(final int significantFigures) {
        return decimalNumFactory(significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNumFactory(MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static NumFactory decimalNumFactory(final int significantFigures, final RoundingMode roundingMode) {
        return decimalNumFactory(new MathContext(significantFigures, roundingMode));
    }

    /**
     * Creates a new {@link NumFactory} for {@link DecimalNum} using the given {@link MathContext}.
     *
     * @param context the {@link MathContext}
     *
     * @return the {@link DecimalNum} {@link NumFactory}
     */
    public static NumFactory decimalNumFactory(final MathContext context) {
        return new Factory(context);
    }

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
            return decimalNum(number, context);
        }

        @Override
        public Num of(final BigDecimal bigDecimal) {
            return decimalNum(bigDecimal, context);
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
                tenth = of("0.1");
            }
            return tenth;
        }

        @Override
        public Num hundredth() {
            if (hundredth == null) {
                hundredth = of("0.01");
            }
            return hundredth;
        }

        @Override
        public Num thousandth() {
            if (thousandth == null) {
                thousandth = of("0.001");
            }
            return thousandth;
        }

        @Override
        public Num half() {
            if (half == null) {
                half = of("0.5");
            }
            return half;
        }

        @Override
        public Num random() {
            // https://gist.github.com/Petersoj/749b4ac7906054242ea2a2089a2e5b2d
            final var prefix = "0.";
            final var stringBuilder = new StringBuilder(prefix.length() + context.getPrecision());
            stringBuilder.append(prefix);
            for (var digitIndex = 0; digitIndex < context.getPrecision(); digitIndex++) {
                stringBuilder.append(ThreadLocalRandom.current().nextInt(10));
            }
            return of(stringBuilder.toString());
        }
    }

    private static final Factory FACTORY_DECIMAL32 = new Factory(DECIMAL32);
    private static final Factory FACTORY_DECIMAL64 = new Factory(DECIMAL64);
    private static final Factory FACTORY_DECIMAL128 = new Factory(DECIMAL128);
    private static final BigDecimal THREE = new BigDecimal(3);
    private static final BigDecimal HALF = new BigDecimal("0.5");

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
            return NaN;
        }
        final var decimalNum = asDecimalNum(addend);
        final var context = precisestContext(decimalNum.context);
        return new DecimalNum(wrapped.add(decimalNum.wrapped, context), context);
    }

    @Override
    public Num subtract(final Num subtrahend) {
        if (subtrahend.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(subtrahend);
        final var context = precisestContext(decimalNum.context);
        return new DecimalNum(wrapped.subtract(decimalNum.wrapped, context), context);
    }

    @Override
    public Num multiply(final Num multiplier) {
        if (multiplier.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(multiplier);
        final var context = precisestContext(decimalNum.context);
        return new DecimalNum(wrapped.multiply(decimalNum.wrapped, context), context);
    }

    @Override
    public Num divide(final Num divisor) {
        if (divisor.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(divisor);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(wrapped.divide(decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num remainder(final Num divisor) {
        if (divisor.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(divisor);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(wrapped.remainder(decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num power(final Num exponent) {
        if (exponent.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(exponent);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(BigDecimalMath.pow(wrapped, decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num square() {
        return new DecimalNum(wrapped.multiply(wrapped, context), context);
    }

    @Override
    public Num cube() {
        return new DecimalNum(wrapped.multiply(wrapped, context).multiply(wrapped, context), context);
    }

    @Override
    public Num exponential() {
        return new DecimalNum(BigDecimalMath.exp(wrapped, context), context);
    }

    @Override
    public Num nthRoot(final Num degree) {
        if (degree.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(degree);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(BigDecimalMath.root(wrapped, decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num squareRoot() {
        try {
            return new DecimalNum(BigDecimalMath.sqrt(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num cubeRoot() {
        try {
            return new DecimalNum(BigDecimalMath.root(wrapped, THREE, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num naturalLog() {
        try {
            return new DecimalNum(BigDecimalMath.log(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num commonLog() {
        try {
            return new DecimalNum(BigDecimalMath.log10(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num binaryLog() {
        try {
            return new DecimalNum(BigDecimalMath.log2(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num log(final Num base) {
        if (base.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(base);
        final var context = precisestContext(decimalNum.context);
        try {
            final var numerator = BigDecimalMath.log(wrapped, context);
            final var denominator = BigDecimalMath.log(decimalNum.wrapped, context);
            return new DecimalNum(numerator.divide(denominator, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
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
            return NaN;
        }
    }

    @Override
    public Num increment() {
        return new DecimalNum(wrapped.add(ONE, context), context);
    }

    @Override
    public Num decrement() {
        return new DecimalNum(wrapped.subtract(ONE, context), context);
    }

    @Override
    public Num floor() {
        return new DecimalNum(wrapped.setScale(0, FLOOR), context);
    }

    @Override
    public Num ceil() {
        return new DecimalNum(wrapped.setScale(0, CEILING), context);
    }

    @Override
    public Num degrees() {
        return new DecimalNum(BigDecimalMath.toDegrees(wrapped, context), context);
    }

    @Override
    public Num radians() {
        return new DecimalNum(BigDecimalMath.toRadians(wrapped, context), context);
    }

    @Override
    public Num pi() {
        return new DecimalNum(BigDecimalMath.pi(context), context);
    }

    @Override
    public Num e() {
        return new DecimalNum(BigDecimalMath.e(context), context);
    }

    @Override
    public Num sin() {
        try {
            return new DecimalNum(BigDecimalMath.sin(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num cos() {
        try {
            return new DecimalNum(BigDecimalMath.cos(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num tan() {
        try {
            return new DecimalNum(BigDecimalMath.tan(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num asin() {
        try {
            return new DecimalNum(BigDecimalMath.asin(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num acos() {
        try {
            return new DecimalNum(BigDecimalMath.acos(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num atan() {
        try {
            return new DecimalNum(BigDecimalMath.atan(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num atan2(final Num x) {
        if (x.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(x);
        final var context = precisestContext(decimalNum.context);
        try {
            return new DecimalNum(BigDecimalMath.atan2(wrapped, decimalNum.wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num sinh() {
        try {
            return new DecimalNum(BigDecimalMath.sinh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num cosh() {
        try {
            return new DecimalNum(BigDecimalMath.cosh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num tanh() {
        try {
            return new DecimalNum(BigDecimalMath.tanh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num asinh() {
        try {
            return new DecimalNum(BigDecimalMath.asinh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num acosh() {
        try {
            return new DecimalNum(BigDecimalMath.acosh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num atanh() {
        try {
            return new DecimalNum(BigDecimalMath.atanh(wrapped, context), context);
        } catch (final ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num hypotenuse(final Num y) {
        if (y.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(y);
        final var context = precisestContext(decimalNum.context);
        final var xSquared = wrapped.multiply(wrapped, context);
        final var ySquared = decimalNum.wrapped.multiply(decimalNum.wrapped, context);
        return new DecimalNum(BigDecimalMath.sqrt(xSquared.add(ySquared, context), context), context);
    }

    @Override
    public Num average(final Num other) {
        if (other.isNaN()) {
            return NaN;
        }
        final var decimalNum = asDecimalNum(other);
        final var context = precisestContext(decimalNum.context);
        return new DecimalNum(wrapped.add(decimalNum.wrapped, context).multiply(HALF, context), context);
    }

    @Override
    public Num integerPart() {
        return new DecimalNum(BigDecimalMath.integralPart(wrapped), context);
    }

    @Override
    public Num fractionalPart() {
        return new DecimalNum(BigDecimalMath.fractionalPart(wrapped), context);
    }

    @Override
    public Num round(final int scale, final RoundingMode roundingMode) {
        return new DecimalNum(wrapped.setScale(scale, roundingMode), context);
    }

    @Override
    public Num sigFigs(final MathContext context) {
        return new DecimalNum(wrapped.round(context), precisestContext(context));
    }

    @Override
    public int sigFigs() {
        return wrapped.stripTrailingZeros().precision();
    }

    @Override
    public Num mantissa() {
        return new DecimalNum(BigDecimalMath.mantissa(wrapped), context);
    }

    @Override
    public int exponent() {
        return BigDecimalMath.exponent(wrapped);
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
        if (epsilon.isZero()) {
            return isNegativeOrZero();
        } else {
            return !epsilon.isNaN() && wrapped.compareTo(asDecimalNum(epsilon).wrapped) <= 0;
        }
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
        if (epsilon.isZero()) {
            return isPositiveOrZero();
        } else {
            return !epsilon.isNaN() && wrapped.compareTo(asDecimalNum(epsilon).wrapped.negate()) >= 0;
        }
    }

    @Override
    public boolean isZero() {
        return wrapped.signum() == 0;
    }

    @Override
    public boolean isZero(final Num epsilon) {
        if (epsilon.isZero()) {
            return isZero();
        } else {
            return !epsilon.isNaN() && wrapped.abs().compareTo(asDecimalNum(epsilon).wrapped) <= 0;
        }
    }

    @Override
    public boolean isEqual(final Num other) {
        return !other.isNaN() && wrapped.compareTo(asDecimalNum(other).wrapped) == 0;
    }

    @Override
    public boolean isEqual(final Num other, final Num epsilon) {
        if (epsilon.isZero()) {
            return isEqual(other);
        }
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        final var decimalNum = asDecimalNum(other);
        return wrapped.subtract(decimalNum.wrapped, precisestContext(decimalNum.context)).abs()
                .compareTo(asDecimalNum(epsilon).wrapped) <= 0;
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
        if (epsilon.isZero()) {
            return isLessThanOrEqual(other);
        }
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        final var decimalNum = asDecimalNum(other);
        return decimalNum.wrapped.subtract(wrapped, precisestContext(decimalNum.context))
                .compareTo(asDecimalNum(epsilon).wrapped.negate()) >= 0;
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
        if (epsilon.isZero()) {
            return isGreaterThanOrEqual(other);
        }
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        final var decimalNum = asDecimalNum(other);
        return wrapped.subtract(decimalNum.wrapped, precisestContext(decimalNum.context))
                .compareTo(asDecimalNum(epsilon).wrapped.negate()) >= 0;
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

    @SuppressWarnings("ReferenceEquality")
    @Override
    public NumFactory factory() {
        if (factory == null) {
            // Use reference equality instead of object equality for performance.
            if (context == DECIMAL32) {
                factory = FACTORY_DECIMAL32;
            } else if (context == DECIMAL64) {
                factory = FACTORY_DECIMAL64;
            } else if (context == DECIMAL128) {
                factory = FACTORY_DECIMAL128;
            } else {
                factory = new Factory(context);
            }
        }
        return factory;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof final DecimalNum decimalNum &&
                wrapped.equals(decimalNum.wrapped) &&
                context.equals(decimalNum.context);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wrapped, context);
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
        return (DecimalNum) (num instanceof final DoubleNum doubleNum ? factory().of(doubleNum) : num);
    }

    private MathContext precisestContext(final MathContext other) {
        return context.getPrecision() > other.getPrecision() ? context : other;
    }
}
