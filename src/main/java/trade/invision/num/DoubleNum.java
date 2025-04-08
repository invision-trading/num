package trade.invision.num;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Double.isFinite;
import static java.lang.Double.parseDouble;
import static java.math.MathContext.DECIMAL64;
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
        if (!isFinite(aDouble)) {
            return NaN;
        }
        return new DoubleNum(aDouble);
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
        if (!isFinite(aDouble)) {
            return NaN;
        }
        return new DoubleNum(aDouble);
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

    // TODO

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
