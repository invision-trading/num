package trade.invision.num;

import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.CEILING;
import static java.math.RoundingMode.FLOOR;
import static java.math.RoundingMode.HALF_DOWN;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trade.invision.num.DecimalNum.decimalNum128;
import static trade.invision.num.DecimalNum.decimalNum64;
import static trade.invision.num.DecimalNum.decimalNum64Factory;
import static trade.invision.num.DoubleNum.doubleNum;

@NullMarked
public final class DecimalNumTest {

    public static final class FactoryTest {

        @Test
        public void ofNumber() {
            assertTrue(decimalNum64Factory().of(1).isEqual(1));
        }

        @Test
        public void ofString() {
            assertTrue(decimalNum64Factory().of("1").isEqual(1));
        }

        @Test
        public void ofNum() {
            assertTrue(decimalNum64Factory().of(doubleNum(1)).isEqual(1));
        }

        @Test
        public void random() {
            assertTrue(decimalNum64Factory().random().isLessThan(1));
        }

        @Test
        public void pi() {
            assertTrue(decimalNum64Factory().pi().isEqual(Math.PI));
        }

        @Test
        public void e() {
            assertTrue(decimalNum64Factory().e().isEqual(Math.E));
        }
    }

    @Test
    public void decimalNumNumber() {
        assertTrue(decimalNum64((byte) 1).isEqual(1));
        assertTrue(decimalNum64((short) 1).isEqual(1));
        assertTrue(decimalNum64(1).isEqual(1));
        assertTrue(decimalNum64(1L).isEqual(1));
        assertTrue(decimalNum64(1f).isEqual(1));
        assertTrue(decimalNum64(1d).isEqual(1));
        assertTrue(decimalNum64(Float.NaN).isNaN());
        assertTrue(decimalNum64(Double.NaN).isNaN());
        assertTrue(decimalNum64(new BigDecimal(1)).isEqual(1));
    }

    @Test
    public void decimalNumString() {
        assertTrue(decimalNum64("1").isEqual(1));
        assertTrue(decimalNum64("NaN").isNaN());
    }

    @Test
    public void decimalNumNum() {
        assertTrue(decimalNum64(doubleNum(1)).isEqual(1));
    }

    @Test
    public void add() {
        assertTrue(decimalNum64(0).add(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(1).add(2).isEqual(3));
    }

    @Test
    public void subtract() {
        assertTrue(decimalNum64(0).subtract(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(3).subtract(2).isEqual(1));
    }

    @Test
    public void multiply() {
        assertTrue(decimalNum64(0).multiply(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(5).multiply(5).isEqual(25));
        assertTrue(decimalNum64(0.5).multiply(-5).isEqual(-2.5));
    }

    @Test
    public void divide() {
        assertTrue(decimalNum64(0).divide(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(10).divide(2).isEqual(5));
        assertTrue(decimalNum64(10).divide(0).isNaN());
    }

    @Test
    public void remainder() {
        assertTrue(decimalNum64(0).remainder(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(10).remainder(2).isEqual(0));
        assertTrue(decimalNum64(10).remainder(0).isNaN());
    }

    @Test
    public void power() {
        assertTrue(decimalNum64(0).power(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(10).power(2).isEqual(100));
    }

    @Test
    public void square() {
        assertTrue(decimalNum64(10).square().isEqual(100));
    }

    @Test
    public void cube() {
        assertTrue(decimalNum64(10).cube().isEqual(1000));
    }

    @Test
    public void exponential() {
        assertTrue(decimalNum64(2).exponential().isEqual(7.38905609893065));
    }

    @Test
    public void nthRoot() {
        assertTrue(decimalNum64(0).nthRoot(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(32).nthRoot(5).isEqual(2));
    }

    @Test
    public void squareRoot() {
        assertTrue(decimalNum64(4).squareRoot().isEqual(2));
    }

    @Test
    public void cubeRoot() {
        assertTrue(decimalNum64(27).cubeRoot().isEqual(3));
    }

    @Test
    public void ln() {
        assertTrue(decimalNum64(2).ln().isEqual(0.6931471805599453));
    }

    @Test
    public void log10() {
        assertTrue(decimalNum64(100).log10().isEqual(2));
    }

    @Test
    public void log2() {
        assertTrue(decimalNum64(8).log2().isEqual(3));
    }

    @Test
    public void log() {
        assertTrue(decimalNum64(0).log(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(256).log(16).isEqual(2));
    }

    @Test
    public void absoluteValue() {
        assertTrue(decimalNum64(1).absoluteValue().isEqual(1));
        assertTrue(decimalNum64(-1).absoluteValue().isEqual(1));
    }

    @Test
    public void negate() {
        assertTrue(decimalNum64(1).negate().isEqual(-1));
        assertTrue(decimalNum64(-1).negate().isEqual(1));
    }

    @Test
    public void reciprocal() {
        assertTrue(decimalNum64(1).reciprocal().isEqual(1));
        assertTrue(decimalNum64(10).reciprocal().isEqual(0.1));
    }

    @Test
    public void increment() {
        assertTrue(decimalNum64(1).increment().isEqual(2));
    }

    @Test
    public void decrement() {
        assertTrue(decimalNum64(1).decrement().isEqual(0));
    }

    @Test
    public void floor() {
        assertTrue(decimalNum64(1.1).floor().isEqual(1));
        assertTrue(decimalNum64(1.9).floor().isEqual(1));
        assertTrue(decimalNum64(-1.1).floor().isEqual(-2));
        assertTrue(decimalNum64(-1.9).floor().isEqual(-2));
    }

    @Test
    public void ceil() {
        assertTrue(decimalNum64(1.1).ceil().isEqual(2));
        assertTrue(decimalNum64(1.9).ceil().isEqual(2));
        assertTrue(decimalNum64(-1.1).ceil().isEqual(-1));
        assertTrue(decimalNum64(-1.9).ceil().isEqual(-1));
    }

    @Test
    public void degrees() {
        assertTrue(decimalNum64(1).degrees().isEqual(57.29577951308232));
    }

    @Test
    public void radians() {
        assertTrue(decimalNum64(180).radians().isEqual(Math.PI));
    }

    @Test
    public void sin() {
        assertTrue(decimalNum64(1).sin().isEqual(0.8414709848078965));
    }

    @Test
    public void cos() {
        assertTrue(decimalNum64(1).cos().isEqual(0.5403023058681397));
    }

    @Test
    public void tan() {
        assertTrue(decimalNum64(1).tan().isEqual(1.5574077246549023));
    }

    @Test
    public void asin() {
        assertTrue(decimalNum64(1).asin().isEqual(1.5707963267948966));
    }

    @Test
    public void acos() {
        assertTrue(decimalNum64(0).acos().isEqual(1.5707963267948966));
    }

    @Test
    public void atan() {
        assertTrue(decimalNum64(1).atan().isEqual(0.7853981633974483));
    }

    @Test
    public void atan2() {
        assertTrue(decimalNum64(1).atan2(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(1).atan2(1).isEqual(0.7853981633974483));
    }

    @Test
    public void sinh() {
        assertTrue(decimalNum64(1).sinh().isEqual(1.1752011936438014));
    }

    @Test
    public void cosh() {
        assertTrue(decimalNum64(1).cosh().isEqual(1.543080634815244));
    }

    @Test
    public void tanh() {
        assertTrue(decimalNum64(1).tanh().isEqual(0.7615941559557649));
    }

    @Test
    public void asinh() {
        assertTrue(decimalNum64(2).asinh().isEqual(1.4436354751788103));
    }

    @Test
    public void acosh() {
        assertTrue(decimalNum64(2).acosh().isEqual(1.3169578969248166));
    }

    @Test
    public void atanh() {
        assertTrue(decimalNum64(0.5).atanh().isEqual(0.5493061443340548));
    }

    @Test
    public void hypotenuse() {
        assertTrue(decimalNum64(2).hypotenuse(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(2).hypotenuse(2).isEqual(2.8284271247461903));
    }

    @Test
    public void average() {
        assertTrue(decimalNum64(2).average(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(2).average(4).isEqual(3));
    }

    @Test
    public void min() {
        assertTrue(decimalNum64(2).min(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(2).min(4).isEqual(2));
        assertTrue(decimalNum64(4).min(2).isEqual(2));
    }

    @Test
    public void max() {
        assertTrue(decimalNum64(2).max(DecimalNum.nanNum64()).isNaN());
        assertTrue(decimalNum64(2).max(4).isEqual(4));
        assertTrue(decimalNum64(4).max(2).isEqual(4));
    }

    @Test
    public void clamp() {
        assertTrue(decimalNum64(2).clamp(1, 4).isEqual(2));
        assertTrue(decimalNum64(0).clamp(1, 4).isEqual(1));
        assertTrue(decimalNum64(5).clamp(1, 4).isEqual(4));
    }

    @Test
    public void integerPart() {
        assertTrue(decimalNum64(2.5).integerPart().isEqual(2));
    }

    @Test
    public void fractionalPart() {
        assertTrue(decimalNum64(2.5).fractionalPart().isEqual(0.5));
    }

    @Test
    public void round() {
        assertTrue(decimalNum64(2.5).round().isEqual(2));
        assertTrue(decimalNum64(3.5).round().isEqual(4));
    }

    @Test
    public void roundRoundingMode() {
        assertTrue(decimalNum64(2.5).round(HALF_UP).isEqual(3));
    }

    @Test
    public void roundInt() {
        assertTrue(decimalNum64(2.555).round(2).isEqual(2.56));
    }

    @Test
    public void roundIntRoundingMode() {
        assertTrue(decimalNum64(2.555).round(2, CEILING).isEqual(2.56));
        assertTrue(decimalNum64(2.555).round(2, FLOOR).isEqual(2.55));
        assertTrue(decimalNum64(2.555).round(2, HALF_UP).isEqual(2.56));
        assertTrue(decimalNum64(2.555).round(2, HALF_EVEN).isEqual(2.56));
        assertTrue(decimalNum64(2.555).round(2, HALF_DOWN).isEqual(2.55));
    }

    @Test
    public void significantInt() {
        assertTrue(decimalNum64(3.5).significantFigures(1).isEqual(4));
    }

    @Test
    public void significantIntRoundingMode() {
        assertTrue(decimalNum64(3.5).significantFigures(1, FLOOR).isEqual(3));
    }

    @Test
    public void significantFiguresMathContext() {
        assertTrue(decimalNum64(3.5).significantFigures(new MathContext(1, FLOOR)).isEqual(3));
    }

    @Test
    public void significantFigures() {
        assertEquals(2, decimalNum64(3.5).significantFigures());
    }

    @Test
    public void mantissa() {
        assertTrue(decimalNum64(2.555).mantissa().isEqual(2.555));
        assertTrue(decimalNum64(0.00001).mantissa().isEqual(1));
    }

    @Test
    public void exponent() {
        assertEquals(0, decimalNum64(2.555).exponent());
        assertEquals(3, decimalNum64(2000).exponent());
    }

    @Test
    public void signum() {
        assertEquals(1, decimalNum64(5).signum());
        assertEquals(0, decimalNum64(0).signum());
        assertEquals(-1, decimalNum64(-5).signum());
    }

    @Test
    public void isNegative() {
        assertTrue(decimalNum64(-1).isNegative());
        assertFalse(decimalNum64(0).isNegative());
        assertFalse(decimalNum64(1).isNegative());
    }

    @Test
    public void isNegativeOrZero() {
        assertTrue(decimalNum64(-1).isNegativeOrZero());
        assertTrue(decimalNum64(0).isNegativeOrZero());
        assertFalse(decimalNum64(1).isNegativeOrZero());
    }

    @Test
    public void isNegativeOrZeroNum() {
        assertFalse(decimalNum64(0).isNegativeOrZero(DecimalNum.nanNum64()));
        assertTrue(decimalNum64(0).isNegativeOrZero(decimalNum64(0.1)));
        assertTrue(decimalNum64(-1).isNegativeOrZero(0.1));
        assertTrue(decimalNum64(-0.1).isNegativeOrZero(0.1));
        assertTrue(decimalNum64(-0.01).isNegativeOrZero(0.1));
        assertTrue(decimalNum64(0).isNegativeOrZero(0.1));
        assertTrue(decimalNum64(0.01).isNegativeOrZero(0.1));
        assertTrue(decimalNum64(0.1).isNegativeOrZero(0.1));
        assertFalse(decimalNum64(1).isNegativeOrZero(0.1));
    }

    @Test
    public void isPositive() {
        assertFalse(decimalNum64(-1).isPositive());
        assertFalse(decimalNum64(0).isPositive());
        assertTrue(decimalNum64(1).isPositive());
    }

    @Test
    public void isPositiveOrZero() {
        assertFalse(decimalNum64(-1).isPositiveOrZero());
        assertTrue(decimalNum64(0).isPositiveOrZero());
        assertTrue(decimalNum64(1).isPositiveOrZero());
    }

    @Test
    public void isPositiveOrZeroNum() {
        assertFalse(decimalNum64(0).isPositiveOrZero(DecimalNum.nanNum64()));
        assertTrue(decimalNum64(0).isPositiveOrZero(decimalNum64(0.1)));
        assertFalse(decimalNum64(-1).isPositiveOrZero(0.1));
        assertTrue(decimalNum64(-0.1).isPositiveOrZero(0.1));
        assertTrue(decimalNum64(-0.01).isPositiveOrZero(0.1));
        assertTrue(decimalNum64(0).isPositiveOrZero(0.1));
        assertTrue(decimalNum64(0.01).isPositiveOrZero(0.1));
        assertTrue(decimalNum64(0.1).isPositiveOrZero(0.1));
        assertTrue(decimalNum64(1).isPositiveOrZero(0.1));
    }

    @Test
    public void isZero() {
        assertFalse(decimalNum64(-1).isZero());
        assertTrue(decimalNum64(0).isZero());
        assertFalse(decimalNum64(1).isZero());
    }

    @Test
    public void isZeroNum() {
        assertFalse(decimalNum64(0).isZero(DecimalNum.nanNum64()));
        assertTrue(decimalNum64(0).isZero(decimalNum64(0.1)));
        assertFalse(decimalNum64(-1).isZero(0.1));
        assertTrue(decimalNum64(-0.1).isZero(0.1));
        assertTrue(decimalNum64(-0.01).isZero(0.1));
        assertTrue(decimalNum64(0).isZero(0.1));
        assertTrue(decimalNum64(0.01).isZero(0.1));
        assertTrue(decimalNum64(0.1).isZero(0.1));
        assertFalse(decimalNum64(1).isZero(0.1));
    }

    @Test
    public void isEqual() {
        assertFalse(decimalNum64(0).isEqual(DecimalNum.nanNum64()));
        assertTrue(decimalNum64(0).isEqual(decimalNum64(0)));
        assertTrue(decimalNum64(1).isEqual(1));
        assertFalse(decimalNum64(1).isEqual(0));
    }

    @Test
    public void isEqualNum() {
        assertFalse(decimalNum64(0).isEqual(0, DecimalNum.nanNum64()));
        assertFalse(decimalNum64(0).isEqual(DecimalNum.nanNum64(), 0));
        assertTrue(decimalNum64(0).isEqual(0, decimalNum64(0.1)));
        assertTrue(decimalNum64(0).isEqual(decimalNum64(0), 0.1));
        assertFalse(decimalNum64(-1).isEqual(0, 0.1));
        assertTrue(decimalNum64(-0.1).isEqual(0, 0.1));
        assertTrue(decimalNum64(-0.01).isEqual(0, 0.1));
        assertTrue(decimalNum64(0).isEqual(0, 0.1));
        assertTrue(decimalNum64(0.01).isEqual(0, 0.1));
        assertTrue(decimalNum64(0.1).isEqual(0, 0.1));
        assertFalse(decimalNum64(1).isEqual(0, 0.1));
    }

    @Test
    public void isLessThan() {
        assertFalse(decimalNum64(0).isLessThan(DecimalNum.nanNum64()));
        assertTrue(decimalNum64(0).isLessThan(decimalNum64(1)));
        assertTrue(decimalNum64(0).isLessThan(1));
        assertFalse(decimalNum64(0).isLessThan(0));
        assertFalse(decimalNum64(0).isLessThan(-1));
    }

    @Test
    public void isLessThanOrEqual() {
        assertFalse(decimalNum64(0).isLessThanOrEqual(DecimalNum.nanNum64()));
        assertTrue(decimalNum64(0).isLessThanOrEqual(decimalNum64(1)));
        assertTrue(decimalNum64(0).isLessThanOrEqual(1));
        assertTrue(decimalNum64(0).isLessThanOrEqual(0));
        assertFalse(decimalNum64(0).isLessThanOrEqual(-1));
    }

    @Test
    public void isLessThanOrEqualNum() {
        assertFalse(decimalNum64(0).isLessThanOrEqual(0, DecimalNum.nanNum64()));
        assertFalse(decimalNum64(0).isLessThanOrEqual(DecimalNum.nanNum64(), 0));
        assertTrue(decimalNum64(0).isLessThanOrEqual(0, decimalNum64(0.1)));
        assertTrue(decimalNum64(0).isLessThanOrEqual(decimalNum64(0), 0.1));
        assertTrue(decimalNum64(-1).isLessThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(-0.1).isLessThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(-0.01).isLessThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(0).isLessThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(0.01).isLessThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(0.1).isLessThanOrEqual(0, 0.1));
        assertFalse(decimalNum64(1).isLessThanOrEqual(0, 0.1));
    }

    @Test
    public void isGreaterThan() {
        assertFalse(decimalNum64(0).isGreaterThan(DecimalNum.nanNum64()));
        assertTrue(decimalNum64(0).isGreaterThan(decimalNum64(-1)));
        assertFalse(decimalNum64(0).isGreaterThan(1));
        assertFalse(decimalNum64(0).isGreaterThan(0));
        assertTrue(decimalNum64(0).isGreaterThan(-1));
    }

    @Test
    public void isGreaterThanOrEqual() {
        assertFalse(decimalNum64(0).isGreaterThanOrEqual(DecimalNum.nanNum64()));
        assertFalse(decimalNum64(0).isGreaterThanOrEqual(decimalNum64(1)));
        assertFalse(decimalNum64(0).isGreaterThanOrEqual(1));
        assertTrue(decimalNum64(0).isGreaterThanOrEqual(0));
        assertTrue(decimalNum64(0).isGreaterThanOrEqual(-1));
    }

    @Test
    public void isGreaterThanOrEqualNum() {
        assertFalse(decimalNum64(0).isGreaterThanOrEqual(0, DecimalNum.nanNum64()));
        assertFalse(decimalNum64(0).isGreaterThanOrEqual(DecimalNum.nanNum64(), 0));
        assertTrue(decimalNum64(0).isGreaterThanOrEqual(0, decimalNum64(0.1)));
        assertTrue(decimalNum64(0).isGreaterThanOrEqual(decimalNum64(0), 0.1));
        assertFalse(decimalNum64(-1).isGreaterThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(-0.1).isGreaterThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(-0.01).isGreaterThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(0).isGreaterThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(0.01).isGreaterThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(0.1).isGreaterThanOrEqual(0, 0.1));
        assertTrue(decimalNum64(1).isGreaterThanOrEqual(0, 0.1));
    }

    @Test
    public void isNaN() {
        assertFalse(decimalNum64(1).isNaN());
    }

    @Test
    public void ifNaN() {
        assertTrue(decimalNum64(1).ifNaN(decimalNum64(0)).isEqual(decimalNum64(1)));
    }

    @Test
    public void ifNaNThrow() {
        assertDoesNotThrow(() -> decimalNum64(1).ifNaNThrow());
    }

    @Test
    public void ifNaNThrowSupplier() {
        assertDoesNotThrow(() -> decimalNum64(1).ifNaNThrow(RuntimeException::new));
    }

    @Test
    public void toByte() {
        assertEquals((byte) 1.0, decimalNum64(1).toByte());
    }

    @Test
    public void toShort() {
        assertEquals((short) 1.0, decimalNum64(1).toShort());
    }

    @Test
    public void toInt() {
        assertEquals((int) 1.0, decimalNum64(1).toInt());
    }

    @Test
    public void toLong() {
        assertEquals((long) 1.0, decimalNum64(1).toLong());
    }

    @Test
    public void toFloat() {
        assertEquals((float) 1.0, decimalNum64(1).toFloat());
    }

    @Test
    public void toDouble() {
        assertEquals(1.0, decimalNum64(1).toDouble());
    }

    @Test
    public void unwrap() {
        assertEquals(new BigDecimal(1), decimalNum64(1).unwrap());
    }

    @Test
    public void toBigDecimal() {
        assertEquals(0, decimalNum64(1).toBigDecimal().compareTo(new BigDecimal(1)));
    }

    @Test
    public void getContext() {
        assertEquals(DECIMAL64, decimalNum64(1).getContext());
    }

    @Test
    public void getContextPrecision() {
        assertEquals(16, decimalNum64(1).getContextPrecision());
    }

    @Test
    public void getContextRoundingMode() {
        assertEquals(HALF_EVEN, decimalNum64(1).getContextRoundingMode());
    }

    @Test
    public void getFactory() {
    }

    @Test
    public void _equals() {
        assertEquals(decimalNum64(1), decimalNum64(1));
        assertNotEquals(decimalNum64(1), decimalNum64(2));
        assertNotEquals(decimalNum64(1), decimalNum128(1));
        assertNotEquals(decimalNum64(1), doubleNum(1));
    }

    @Test
    public void _hashCode() {
        assertEquals(decimalNum64(1).hashCode(), decimalNum64(1).hashCode());
        assertNotEquals(decimalNum64(1).hashCode(), decimalNum64(2).hashCode());
    }

    @Test
    public void _toString() {
        assertTrue(decimalNum64(decimalNum64(1).toString()).isEqual(1));
    }

    @Test
    public void compareTo() {
        assertEquals(0, decimalNum64(1).compareTo(DecimalNum.nanNum64()));
        assertEquals(0, decimalNum64(1).compareTo(decimalNum64(1)));
        assertEquals(0, decimalNum64(1).compareTo(decimalNum64(1)));
        assertEquals(1, decimalNum64(1).compareTo(decimalNum64(-1)));
        assertEquals(-1, decimalNum64(1).compareTo(decimalNum64(2)));
    }
}
