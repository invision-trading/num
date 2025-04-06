package trade.invision.num;

import java.math.BigDecimal;

/**
 * {@link NumFactory} is an interface for creating {@link Num} instances.
 */
public interface NumFactory {

    /**
     * Creates a new {@link Num} with the value of the given {@link Number}.
     * <br>
     * Do not use this method for non-integer decimal numbers. Use {@link #of(String)} instead.
     *
     * @param number the {@link Number}
     *
     * @return the {@link Num}
     */
    Num of(Number number);

    /**
     * Creates a new {@link Num} with the value of the given {@link BigDecimal}.
     *
     * @param bigDecimal the {@link BigDecimal}
     *
     * @return the {@link Num}
     */
    Num of(BigDecimal bigDecimal);

    /**
     * Creates a new {@link Num} with the value of the given {@link String} representing a number.
     *
     * @param string the {@link String} representing a number
     *
     * @return the {@link Num}
     */
    Num of(String string);

    /**
     * Creates a new {@link Num} with the value of the given {@link Num}.
     *
     * @param num the {@link Num}
     *
     * @return the {@link Num}
     */
    Num of(Num num);

    /**
     * @return the {@link Num} of <code>-1</code>
     *
     * @see #of(Number)
     */
    Num negativeOne();

    /**
     * @return the {@link Num} of <code>0</code>
     *
     * @see #of(Number)
     */
    Num zero();

    /**
     * @return the {@link Num} of <code>1</code>
     *
     * @see #of(Number)
     */
    Num one();

    /**
     * @return the {@link Num} of <code>2</code>
     *
     * @see #of(Number)
     */
    Num two();

    /**
     * @return the {@link Num} of <code>3</code>
     *
     * @see #of(Number)
     */
    Num three();

    /**
     * @return the {@link Num} of <code>0.1</code>
     *
     * @see #of(String)
     */
    Num tenth();

    /**
     * @return the {@link Num} of <code>0.01</code>
     *
     * @see #of(String)
     */
    Num hundredth();

    /**
     * @return the {@link Num} of <code>0.001</code>
     *
     * @see #of(String)
     */
    Num thousandth();

    /**
     * @return the {@link Num} of <code>10</code>
     *
     * @see #of(Number)
     */
    Num ten();

    /**
     * @return the {@link Num} of <code>100</code>
     *
     * @see #of(Number)
     */
    Num hundred();

    /**
     * @return the {@link Num} of <code>1000</code>
     *
     * @see #of(Number)
     */
    Num thousand();
}
