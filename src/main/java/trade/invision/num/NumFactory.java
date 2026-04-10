package trade.invision.num;

import com.google.errorprone.annotations.Immutable;
import org.jspecify.annotations.NullMarked;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

/**
 * {@link NumFactory} is an interface for getting {@link Num} instances.
 */
@NullMarked
@Immutable
public interface NumFactory {

    /**
     * Gets a {@link Num} with the value of the given {@link Number}.
     *
     * @param number the {@link Number}
     *
     * @return the {@link Num}
     */
    Num of(final Number number);

    /**
     * Gets a {@link Num} with the value of the given {@link BigDecimal}.
     *
     * @param bigDecimal the {@link BigDecimal}
     *
     * @return the {@link Num}
     */
    Num of(final BigDecimal bigDecimal);

    /**
     * Gets a {@link Num} with the value of the given number {@link String}.
     *
     * @param string the number {@link String}
     *
     * @return the {@link Num}
     */
    Num of(final String string);

    /**
     * Gets a {@link Num} with the value of the given {@link Num}.
     *
     * @param num the {@link Num}
     *
     * @return the {@link Num}
     */
    Num of(final Num num);

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
     * @return {@link #random(RandomGenerator)} {@link ThreadLocalRandom#current()}
     */
    default Num random() {
        return random(ThreadLocalRandom.current());
    }

    /**
     * Generates a random number between <code>0</code> (inclusive) and <code>1</code> (exclusive).
     *
     * @param randomGenerator the {@link RandomGenerator}
     *
     * @return the random {@link Num}
     */
    Num random(final RandomGenerator randomGenerator);
}
