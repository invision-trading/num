package trade.invision.num;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.MathContext.DECIMAL128;
import static java.math.MathContext.DECIMAL32;
import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.HALF_EVEN;
import static trade.invision.num.NaNNum.NaN;

/**
 * {@link DecimalNum} is a {@link Num} implementation using arbitrary-precision decimal-represented numbers via
 * {@link BigDecimal}.
 *
 * @see Num
 * @see BigDecimal
 * @see BigDecimalMath
 * @see <a href="https://en.wikipedia.org/wiki/Arbitrary-precision_arithmetic">Wikipedia</a>
 * @see <a href="https://github.com/eobermuhlner/big-math">big-math GitHub</a>
 */
public final class DecimalNum implements Num {

    /**
     * @see #decimalNum32(Number)
     */
    public static Num valueOf32(Number number) {
        return decimalNum32(number);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(Number number) {
        return decimalNum(number, DECIMAL32);
    }

    /**
     * @see #decimalNum32(Number, RoundingMode)
     */
    public static Num valueOf32(Number number, RoundingMode roundingMode) {
        return decimalNum32(number, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(Number number, RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum64(Number)
     */
    public static Num valueOf64(Number number) {
        return decimalNum64(number);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(Number number) {
        return decimalNum(number, DECIMAL64);
    }

    /**
     * @see #decimalNum64(Number, RoundingMode)
     */
    public static Num valueOf64(Number number, RoundingMode roundingMode) {
        return decimalNum64(number, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(Number number, RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum128(Number)
     */
    public static Num valueOf128(Number number) {
        return decimalNum128(number);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(Number number) {
        return decimalNum(number, DECIMAL128);
    }

    /**
     * @see #decimalNum128(Number, RoundingMode)
     */
    public static Num valueOf128(Number number, RoundingMode roundingMode) {
        return decimalNum128(number, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(Number number, RoundingMode roundingMode) {
        return decimalNum(number, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum(Number, int)
     */
    public static Num valueOf(Number number, int significantFigures) {
        return decimalNum(number, significantFigures);
    }

    /**
     * Calls {@link #decimalNum(Number, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(Number number, int significantFigures) {
        return decimalNum(number, significantFigures, HALF_EVEN);
    }

    /**
     * @see #decimalNum(Number, int, RoundingMode)
     */
    public static Num valueOf(Number number, int significantFigures, RoundingMode roundingMode) {
        return decimalNum(number, significantFigures, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Number, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(Number number, int significantFigures, RoundingMode roundingMode) {
        return decimalNum(number, new MathContext(significantFigures, roundingMode));
    }

    /**
     * @see #decimalNum(Number, MathContext)
     */
    public static Num valueOf(Number number, MathContext context) {
        return decimalNum(number, context);
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
     * @see #decimalNum32(String)
     */
    public static Num valueOf32(String string) {
        return decimalNum32(string);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(String string) {
        return decimalNum(string, DECIMAL32);
    }

    /**
     * @see #decimalNum32(String, RoundingMode)
     */
    public static Num valueOf32(String string, RoundingMode roundingMode) {
        return decimalNum32(string, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(String string, RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum64(String)
     */
    public static Num valueOf64(String string) {
        return decimalNum64(string);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(String string) {
        return decimalNum(string, DECIMAL64);
    }

    /**
     * @see #decimalNum64(String, RoundingMode)
     */
    public static Num valueOf64(String string, RoundingMode roundingMode) {
        return decimalNum64(string, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(String string, RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum128(String)
     */
    public static Num valueOf128(String string) {
        return decimalNum128(string);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(String string) {
        return decimalNum(string, DECIMAL128);
    }

    /**
     * @see #decimalNum128(String, RoundingMode)
     */
    public static Num valueOf128(String string, RoundingMode roundingMode) {
        return decimalNum128(string, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(String string, RoundingMode roundingMode) {
        return decimalNum(string, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum(String, int)
     */
    public static Num valueOf(String string, int significantFigures) {
        return decimalNum(string, significantFigures);
    }

    /**
     * Calls {@link #decimalNum(String, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(String string, int significantFigures) {
        return decimalNum(string, significantFigures, HALF_EVEN);
    }

    /**
     * @see #decimalNum(String, int, RoundingMode)
     */
    public static Num valueOf(String string, int significantFigures, RoundingMode roundingMode) {
        return decimalNum(string, significantFigures, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(String, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(String string, int significantFigures, RoundingMode roundingMode) {
        return decimalNum(string, new MathContext(significantFigures, roundingMode));
    }

    /**
     * @see #decimalNum(String, MathContext)
     */
    public static Num valueOf(String string, MathContext context) {
        return decimalNum(string, context);
    }

    /**
     * Creates a new {@link DecimalNum} using the given {@link String} representing a number and {@link MathContext}.
     *
     * @param string  the {@link String}
     * @param context the {@link MathContext}
     *
     * @return the {@link Num}
     */
    public static Num decimalNum(String string, MathContext context) {
        return new DecimalNum(new BigDecimal(string, context), context);
    }

    /**
     * @see #decimalNum32(BigDecimal)
     */
    public static Num valueOf32(BigDecimal bigDecimal) {
        return decimalNum32(bigDecimal);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with <code>context</code> set to
     * {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(BigDecimal bigDecimal) {
        return decimalNum(bigDecimal, DECIMAL32);
    }

    /**
     * @see #decimalNum32(BigDecimal, RoundingMode)
     */
    public static Num valueOf32(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return decimalNum32(bigDecimal, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return decimalNum(bigDecimal, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum64(BigDecimal)
     */
    public static Num valueOf64(BigDecimal bigDecimal) {
        return decimalNum64(bigDecimal);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with <code>context</code> set to
     * {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(BigDecimal bigDecimal) {
        return decimalNum(bigDecimal, DECIMAL64);
    }

    /**
     * @see #decimalNum64(BigDecimal, RoundingMode)
     */
    public static Num valueOf64(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return decimalNum64(bigDecimal, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return decimalNum(bigDecimal, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum128(BigDecimal)
     */
    public static Num valueOf128(BigDecimal bigDecimal) {
        return decimalNum128(bigDecimal);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with <code>context</code> set to
     * {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(BigDecimal bigDecimal) {
        return decimalNum(bigDecimal, DECIMAL128);
    }

    /**
     * @see #decimalNum128(BigDecimal, RoundingMode)
     */
    public static Num valueOf128(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return decimalNum128(bigDecimal, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return decimalNum(bigDecimal, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum(BigDecimal, int)
     */
    public static Num valueOf(BigDecimal bigDecimal, int significantFigures) {
        return decimalNum(bigDecimal, significantFigures);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(BigDecimal bigDecimal, int significantFigures) {
        return decimalNum(bigDecimal, significantFigures, HALF_EVEN);
    }

    /**
     * @see #decimalNum(BigDecimal, int, RoundingMode)
     */
    public static Num valueOf(BigDecimal bigDecimal, int significantFigures, RoundingMode roundingMode) {
        return decimalNum(bigDecimal, significantFigures, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(BigDecimal, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(BigDecimal bigDecimal, int significantFigures, RoundingMode roundingMode) {
        return decimalNum(bigDecimal, new MathContext(significantFigures, roundingMode));
    }

    /**
     * @see #decimalNum(BigDecimal, MathContext)
     */
    public static Num valueOf(BigDecimal bigDecimal, MathContext context) {
        return decimalNum(bigDecimal, context);
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
     * @see #decimalNum32(Num)
     */
    public static Num valueOf32(Num num) {
        return decimalNum32(num);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL32}.
     */
    public static Num decimalNum32(Num num) {
        return decimalNum(num, DECIMAL32);
    }

    /**
     * @see #decimalNum32(Num, RoundingMode)
     */
    public static Num valueOf32(Num num, RoundingMode roundingMode) {
        return decimalNum32(num, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL32} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum32(Num num, RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL32.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum64(Num)
     */
    public static Num valueOf64(Num num) {
        return decimalNum64(num);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL64}.
     */
    public static Num decimalNum64(Num num) {
        return decimalNum(num, DECIMAL64);
    }

    /**
     * @see #decimalNum64(Num, RoundingMode)
     */
    public static Num valueOf64(Num num, RoundingMode roundingMode) {
        return decimalNum64(num, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL64} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum64(Num num, RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL64.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum128(Num)
     */
    public static Num valueOf128(Num num) {
        return decimalNum128(num);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with <code>context</code> set to {@link MathContext#DECIMAL128}.
     */
    public static Num decimalNum128(Num num) {
        return decimalNum(num, DECIMAL128);
    }

    /**
     * @see #decimalNum128(Num, RoundingMode)
     */
    public static Num valueOf128(Num num, RoundingMode roundingMode) {
        return decimalNum128(num, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>significantFigures</code> set to
     * {@link MathContext#DECIMAL128} {@link MathContext#getPrecision()}.
     */
    public static Num decimalNum128(Num num, RoundingMode roundingMode) {
        return decimalNum(num, DECIMAL128.getPrecision(), roundingMode);
    }

    /**
     * @see #decimalNum(Num, int)
     */
    public static Num valueOf(Num num, int significantFigures) {
        return decimalNum(num, significantFigures);
    }

    /**
     * Calls {@link #decimalNum(Num, int, RoundingMode)} with <code>roundingMode</code> set to
     * {@link RoundingMode#HALF_EVEN}.
     */
    public static Num decimalNum(Num num, int significantFigures) {
        return decimalNum(num, significantFigures, HALF_EVEN);
    }

    /**
     * @see #decimalNum(Num, int, RoundingMode)
     */
    public static Num valueOf(Num num, int significantFigures, RoundingMode roundingMode) {
        return decimalNum(num, significantFigures, roundingMode);
    }

    /**
     * Calls {@link #decimalNum(Num, MathContext)} with {@link MathContext#getPrecision()} set to
     * <code>significantFigures</code> and {@link MathContext#getRoundingMode()} set to <code>roundingMode</code>.
     */
    public static Num decimalNum(Num num, int significantFigures, RoundingMode roundingMode) {
        return decimalNum(num, new MathContext(significantFigures, roundingMode));
    }

    /**
     * @see #decimalNum(Num, MathContext)
     */
    public static Num valueOf(Num num, MathContext context) {
        return decimalNum(num, context);
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

    private static final Num NEGATIVE_ONE = decimalNum(-1, 1);
    private static final Num ZERO = decimalNum(0, 1);
    private static final Num ONE = decimalNum(1, 1);
    private static final Num TWO = decimalNum(2, 1);
    private static final Num THREE = decimalNum(3, 1);
    private static final Num HALF = decimalNum("0.5", 1);
    private static final Num TENTH = decimalNum("0.1", 1);
    private static final Num HUNDREDTH = decimalNum("0.01", 1);
    private static final Num THOUSANDTH = decimalNum("0.001", 1);
    private static final Num TEN = decimalNum(10, 1);
    private static final Num HUNDRED = decimalNum(100, 1);
    private static final Num THOUSAND = decimalNum(1000, 1);

    private static class Factory implements NumFactory {

        private final MathContext context;

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
    }

    private final BigDecimal wrapped;
    private final MathContext context;
    private NumFactory factory;

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

    // TODO

    @Override
    public boolean isNaN() {
        return false;
    }

    @Override
    public BigDecimal asBigDecimal() {
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
        return wrapped.hashCode();
    }

    @Override
    public String toString() {
        return wrapped.toString();
    }

    @Override
    public int compareTo(@NotNull Num o) {
        return o.isNaN() ? 0 : wrapped
                .compareTo(((DecimalNum) (o instanceof DoubleNum oDoubleNum ? factory().of(oDoubleNum) : o)).wrapped);
    }
}
