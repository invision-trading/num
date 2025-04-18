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
     */
    Num negativeOne();

    /**
     * @return the {@link Num} of <code>0</code>
     */
    Num zero();

    /**
     * @return the {@link Num} of <code>1</code>
     */
    Num one();

    /**
     * @return the {@link Num} of <code>2</code>
     */
    Num two();

    /**
     * @return the {@link Num} of <code>3</code>
     */
    Num three();

    /**
     * @return the {@link Num} of <code>4</code>
     */
    Num four();

    /**
     * @return the {@link Num} of <code>5</code>
     */
    Num five();

    /**
     * @return the {@link Num} of <code>6</code>
     */
    Num six();

    /**
     * @return the {@link Num} of <code>7</code>
     */
    Num seven();

    /**
     * @return the {@link Num} of <code>8</code>
     */
    Num eight();

    /**
     * @return the {@link Num} of <code>9</code>
     */
    Num nine();

    /**
     * @return the {@link Num} of <code>10</code>
     */
    Num ten();

    /**
     * @return the {@link Num} of <code>100</code>
     */
    Num hundred();

    /**
     * @return the {@link Num} of <code>1000</code>
     */
    Num thousand();

    /**
     * @return the {@link Num} of <code>0.1</code>
     */
    Num tenth();

    /**
     * @return the {@link Num} of <code>0.01</code>
     */
    Num hundredth();

    /**
     * @return the {@link Num} of <code>0.001</code>
     */
    Num thousandth();

    /**
     * @return the {@link Num} of <code>0.5</code>
     */
    Num half();

    /**
     * Generates a random number between <code>0</code> (inclusive) and <code>1</code> (exclusive).
     *
     * @return the random {@link Num}
     */
    Num random();
}
