package trade.invision.num;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

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
public final class DecimalNum implements Num {

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(Number number) {
        return decimalNum(number, DECIMAL32);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(Number number, RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(Number number) {
        return decimalNum(number, DECIMAL64);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(Number number, RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(Number number) {
        return decimalNum(number, DECIMAL128);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(Number number, RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(Number number, int significantFigures) {
        return decimalNum(number, significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(Number number, int significantFigures, RoundingMode roundingMode) {
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
    public static Num decimalNum(Number number, MathContext context) {
        return switch (number) {
            case Integer aInt -> new DecimalNum(new BigDecimal(aInt, context), context);
            case Long aLong -> new DecimalNum(new BigDecimal(aLong, context), context);
            case Float aFloat -> !Float.isFinite(aFloat) ? NaN :
                    new DecimalNum(new BigDecimal(aFloat.toString(), context), context);
            case Double aDouble -> !Double.isFinite(aDouble) ? NaN :
                    new DecimalNum(new BigDecimal(aDouble.toString(), context), context);
            case BigDecimal bigDecimal -> decimalNum(bigDecimal, context);
            default -> new DecimalNum(new BigDecimal(number.toString(), context), context);
        };
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(String string) {
        return decimalNum(string, DECIMAL32);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(String string, RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(String string) {
        return decimalNum(string, DECIMAL64);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(String string, RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(String string) {
        return decimalNum(string, DECIMAL128);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(String string, RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(String string, int significantFigures) {
        return decimalNum(string, significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(String string, int significantFigures, RoundingMode roundingMode) {
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
    public static Num decimalNum(String string, MathContext context) {
        return string.equals(NaN.toString()) ? NaN : new DecimalNum(new BigDecimal(string, context), context);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with <code>context</code> set to
     * {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(BigDecimal bigDecimal) {
        return decimalNum(bigDecimal, DECIMAL32);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return decimalNum(bigDecimal, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with <code>context</code> set to
     * {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(BigDecimal bigDecimal) {
        return decimalNum(bigDecimal, DECIMAL64);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return decimalNum(bigDecimal, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with <code>context</code> set to
     * {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(BigDecimal bigDecimal) {
        return decimalNum(bigDecimal, DECIMAL128);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return decimalNum(bigDecimal, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(BigDecimal bigDecimal, int significantFigures) {
        return decimalNum(bigDecimal, significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(BigDecimal bigDecimal, int significantFigures, RoundingMode roundingMode) {
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
    public static Num decimalNum(BigDecimal bigDecimal, MathContext context) {
        return new DecimalNum(bigDecimal.round(context), context);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(Num num) {
        return decimalNum(num, DECIMAL32);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(Num num, RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(Num num) {
        return decimalNum(num, DECIMAL64);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(Num num, RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(Num num) {
        return decimalNum(num, DECIMAL128);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(Num num, RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(Num num, int significantFigures) {
        return decimalNum(num, significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(Num num, int significantFigures, RoundingMode roundingMode) {
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
    public static Num decimalNum(Num num, MathContext context) {
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
    public static NumFactory decimalNum32Factory(RoundingMode roundingMode) {
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
    public static NumFactory decimalNum64Factory(RoundingMode roundingMode) {
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
    public static NumFactory decimalNum128Factory(RoundingMode roundingMode) {
        return decimalNumFactory(DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * Calls {@link #decimalNumFactory(int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static NumFactory decimalNumFactory(int significantFigures) {
        return decimalNumFactory(significantFigures, HALF_EVEN);
    }

    /**
     * Calls {@link #decimalNumFactory(MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static NumFactory decimalNumFactory(int significantFigures, RoundingMode roundingMode) {
        return decimalNumFactory(new MathContext(significantFigures, roundingMode));
    }

    /**
     * Creates a new {@link NumFactory} for {@link DecimalNum} using the given {@link MathContext}.
     *
     * @param context the {@link MathContext}
     *
     * @return the {@link DecimalNum} {@link NumFactory}
     */
    public static NumFactory decimalNumFactory(MathContext context) {
        return new Factory(context);
    }

    private static class Factory implements NumFactory {

        private final MathContext context;
        private @Nullable Num negativeOne;
        private @Nullable Num zero;
        private @Nullable Num one;
        private @Nullable Num two;
        private @Nullable Num three;
        private @Nullable Num four;
        private @Nullable Num five;
        private @Nullable Num six;
        private @Nullable Num seven;
        private @Nullable Num eight;
        private @Nullable Num nine;
        private @Nullable Num ten;
        private @Nullable Num hundred;
        private @Nullable Num thousand;
        private @Nullable Num tenth;
        private @Nullable Num hundredth;
        private @Nullable Num thousandth;
        private @Nullable Num half;

        private Factory(MathContext context) {
            this.context = context;
        }

        @Override
        public Num of(Number number) {
            return decimalNum(number, context);
        }

        @Override
        public Num of(BigDecimal bigDecimal) {
            return decimalNum(bigDecimal, context);
        }

        @Override
        public Num of(String string) {
            return decimalNum(string, context);
        }

        @Override
        public Num of(Num num) {
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
            final StringBuilder stringBuilder = new StringBuilder("0.");
            for (int digitIndex = 0; digitIndex < context.getPrecision(); digitIndex++) {
                stringBuilder.append(ThreadLocalRandom.current().nextInt(10));
            }
            return of(stringBuilder.toString());
        }
    }

    private static final BigDecimal THREE = new BigDecimal(3);
    private static final BigDecimal HALF = new BigDecimal("0.5");

    private final BigDecimal wrapped;
    private final MathContext context;
    private @Nullable NumFactory factory;

    private DecimalNum(BigDecimal bigDecimal, MathContext context) {
        wrapped = bigDecimal;
        this.context = context;
    }

    @Override
    public Number unwrap() {
        return wrapped;
    }

    @Override
    public NumFactory factory() {
        if (factory == null) {
            factory = new Factory(context);
        }
        return factory;
    }

    @Override
    public Num add(Num addend) {
        if (addend.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(addend);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        return new DecimalNum(wrapped.add(decimalNum.wrapped, context), context);
    }

    @Override
    public Num subtract(Num subtrahend) {
        if (subtrahend.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(subtrahend);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        return new DecimalNum(wrapped.subtract(decimalNum.wrapped, context), context);
    }

    @Override
    public Num multiply(Num multiplier) {
        if (multiplier.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(multiplier);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        return new DecimalNum(wrapped.multiply(decimalNum.wrapped, context), context);
    }

    @Override
    public Num divide(Num divisor) {
        if (divisor.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(divisor);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        try {
            return new DecimalNum(wrapped.divide(decimalNum.wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num remainder(Num divisor) {
        if (divisor.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(divisor);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        try {
            return new DecimalNum(wrapped.remainder(decimalNum.wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num power(Num exponent) {
        if (exponent.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(exponent);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        try {
            return new DecimalNum(BigDecimalMath.pow(wrapped, decimalNum.wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
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
    public Num nthRoot(Num degree) {
        if (degree.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(degree);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        try {
            return new DecimalNum(BigDecimalMath.root(wrapped, decimalNum.wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num squareRoot() {
        try {
            return new DecimalNum(BigDecimalMath.sqrt(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num cubeRoot() {
        try {
            return new DecimalNum(BigDecimalMath.root(wrapped, THREE, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num naturalLogarithm() {
        try {
            return new DecimalNum(BigDecimalMath.log(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num commonLogarithm() {
        try {
            return new DecimalNum(BigDecimalMath.log10(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num binaryLogarithm() {
        try {
            return new DecimalNum(BigDecimalMath.log2(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num logarithm(Num base) {
        if (base.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(base);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        try {
            final BigDecimal numerator = BigDecimalMath.log(wrapped, context);
            final BigDecimal denominator = BigDecimalMath.log(decimalNum.wrapped, context);
            return new DecimalNum(numerator.divide(denominator, context), context);
        } catch (ArithmeticException arithmeticException) {
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
        } catch (ArithmeticException arithmeticException) {
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
    public Num sine() {
        try {
            return new DecimalNum(BigDecimalMath.sin(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num cosine() {
        try {
            return new DecimalNum(BigDecimalMath.cos(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num tangent() {
        try {
            return new DecimalNum(BigDecimalMath.tan(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num inverseSine() {
        try {
            return new DecimalNum(BigDecimalMath.asin(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num inverseCosine() {
        try {
            return new DecimalNum(BigDecimalMath.acos(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num inverseTangent() {
        try {
            return new DecimalNum(BigDecimalMath.atan(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num inverseTangent2(Num x) {
        if (x.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(x);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        try {
            return new DecimalNum(BigDecimalMath.atan2(wrapped, decimalNum.wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num hyperbolicSine() {
        try {
            return new DecimalNum(BigDecimalMath.sinh(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num hyperbolicCosine() {
        try {
            return new DecimalNum(BigDecimalMath.cosh(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num hyperbolicTangent() {
        try {
            return new DecimalNum(BigDecimalMath.tanh(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num inverseHyperbolicSine() {
        try {
            return new DecimalNum(BigDecimalMath.asinh(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num inverseHyperbolicCosine() {
        try {
            return new DecimalNum(BigDecimalMath.acosh(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num inverseHyperbolicTangent() {
        try {
            return new DecimalNum(BigDecimalMath.atanh(wrapped, context), context);
        } catch (ArithmeticException arithmeticException) {
            return NaN;
        }
    }

    @Override
    public Num hypotenuse(Num y) {
        if (y.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(y);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        final BigDecimal xSquared = wrapped.multiply(wrapped, context);
        final BigDecimal ySquared = decimalNum.wrapped.multiply(decimalNum.wrapped, context);
        return new DecimalNum(BigDecimalMath.sqrt(xSquared.add(ySquared, context), context), context);
    }

    @Override
    public Num average(Num other) {
        if (other.isNaN()) {
            return NaN;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(other);
        final MathContext context = highestPrecisionContext(this, decimalNum);
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
    public Num round(int scale, RoundingMode roundingMode) {
        return new DecimalNum(wrapped.setScale(scale, roundingMode), context);
    }

    @Override
    public Num significantFigures(MathContext context) {
        return new DecimalNum(wrapped.round(context), highestPrecisionContext(this.context, context));
    }

    @Override
    public int significantFigures() {
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
    public boolean isNegativeOrZero(Num epsilon) {
        if (epsilon.isZero()) {
            return isNegativeOrZero();
        } else {
            return !epsilon.isNaN() && wrapped.compareTo(toDecimalNumAsNeeded(epsilon).wrapped) <= 0;
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
    public boolean isPositiveOrZero(Num epsilon) {
        if (epsilon.isZero()) {
            return isPositiveOrZero();
        } else {
            return !epsilon.isNaN() && wrapped.compareTo(toDecimalNumAsNeeded(epsilon).wrapped.negate()) >= 0;
        }
    }

    @Override
    public boolean isZero() {
        return wrapped.signum() == 0;
    }

    @Override
    public boolean isZero(Num epsilon) {
        if (epsilon.isZero()) {
            return isZero();
        } else {
            return !epsilon.isNaN() && wrapped.abs().compareTo(toDecimalNumAsNeeded(epsilon).wrapped) <= 0;
        }
    }

    @Override
    public boolean isEqual(Num other) {
        return !other.isNaN() && wrapped.compareTo(toDecimalNumAsNeeded(other).wrapped) == 0;
    }

    @Override
    public boolean isEqual(Num other, Num epsilon) {
        if (epsilon.isZero()) {
            return isEqual(other);
        }
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(other);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        return wrapped.subtract(decimalNum.wrapped, context).abs()
                .compareTo(toDecimalNumAsNeeded(epsilon).wrapped) <= 0;
    }

    @Override
    public boolean isLessThan(Num other) {
        return !other.isNaN() && wrapped.compareTo(toDecimalNumAsNeeded(other).wrapped) < 0;
    }

    @Override
    public boolean isLessThanOrEqual(Num other) {
        return !other.isNaN() && wrapped.compareTo(toDecimalNumAsNeeded(other).wrapped) <= 0;
    }

    @Override
    public boolean isLessThanOrEqual(Num other, Num epsilon) {
        if (epsilon.isZero()) {
            return isLessThanOrEqual(other);
        }
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(other);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        return decimalNum.wrapped.subtract(wrapped, context)
                .compareTo(toDecimalNumAsNeeded(epsilon).wrapped.negate()) >= 0;
    }

    @Override
    public boolean isGreaterThan(Num other) {
        return !other.isNaN() && wrapped.compareTo(toDecimalNumAsNeeded(other).wrapped) > 0;
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other) {
        return !other.isNaN() && wrapped.compareTo(toDecimalNumAsNeeded(other).wrapped) >= 0;
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other, Num epsilon) {
        if (epsilon.isZero()) {
            return isGreaterThanOrEqual(other);
        }
        if (other.isNaN() || epsilon.isNaN()) {
            return false;
        }
        final DecimalNum decimalNum = toDecimalNumAsNeeded(other);
        final MathContext context = highestPrecisionContext(this, decimalNum);
        return wrapped.subtract(decimalNum.wrapped, context)
                .compareTo(toDecimalNumAsNeeded(epsilon).wrapped.negate()) >= 0;
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
    public BigDecimal toBigDecimal() {
        return wrapped;
    }

    @Override
    public MathContext getContext() {
        return context;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DecimalNum decimalNum && wrapped.equals(decimalNum.wrapped) &&
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
    public int compareTo(@NotNull Num o) {
        return o.isNaN() ? 0 : wrapped.compareTo(toDecimalNumAsNeeded(o).wrapped);
    }

    private DecimalNum toDecimalNumAsNeeded(Num num) {
        return (DecimalNum) (num instanceof DoubleNum doubleNum ? factory().of(doubleNum) : num);
    }

    private MathContext highestPrecisionContext(DecimalNum first, DecimalNum second) {
        return highestPrecisionContext(first.context, second.context);
    }

    private MathContext highestPrecisionContext(MathContext first, MathContext second) {
        return first.getPrecision() > second.getPrecision() ? first : second;
    }
}
