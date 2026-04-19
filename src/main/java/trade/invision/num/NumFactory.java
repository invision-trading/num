package trade.invision.num;

import com.google.errorprone.annotations.Immutable;
import org.jspecify.annotations.NullMarked;

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
