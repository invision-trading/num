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
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trade.invision.num.DecimalNum.decimalNum64;
import static trade.invision.num.DoubleNum.doubleNum;

@NullMarked
public final class DoubleNumTest {

    private static final double EPSILON = 1e-15;

    public static final class FactoryTest {

        @Test
        public void ofNumber() {
            assertTrue(DoubleNum.FACTORY.of(1).isEqual(1));
        }

        @Test
        public void ofString() {
            assertTrue(DoubleNum.FACTORY.of("1").isEqual(1));
        }

        @Test
        public void ofNum() {
            assertTrue(DoubleNum.FACTORY.of(decimalNum64(1)).isEqual(1));
        }

        @Test
        public void random() {
            assertTrue(DoubleNum.FACTORY.random().isLessThan(1));
        }

        @Test
        public void pi() {
            assertTrue(DoubleNum.FACTORY.pi().isEqual(Math.PI));
        }

        @Test
        public void e() {
            assertTrue(DoubleNum.FACTORY.e().isEqual(Math.E));
        }
    }

    @Test
    public void doubleNumDouble() {
        assertTrue(doubleNum(1.0).isEqual(1));
    }

    @Test
    public void doubleNumNumber() {
        assertTrue(doubleNum(1L).isEqual(1));
    }

    @Test
    public void doubleNumString() {
        assertTrue(doubleNum("1").isEqual(1));
    }

    @Test
    public void doubleNumNum() {
        assertTrue(doubleNum(decimalNum64(1)).isEqual(1));
    }

    @Test
    public void add() {
        assertTrue(doubleNum(0).add(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(1).add(decimalNum64(2)));
        assertTrue(doubleNum(1).add(2).isEqual(3));
    }

    @Test
    public void subtract() {
        assertTrue(doubleNum(0).subtract(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(3).subtract(decimalNum64(2)));
        assertTrue(doubleNum(3).subtract(2).isEqual(1));
    }

    @Test
    public void multiply() {
        assertTrue(doubleNum(0).multiply(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(5).multiply(decimalNum64(5)));
        assertTrue(doubleNum(5).multiply(5).isEqual(25));
        assertTrue(doubleNum(0.5).multiply(-5).isEqual(-2.5));
    }

    @Test
    public void divide() {
        assertTrue(doubleNum(0).divide(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(10).divide(decimalNum64(2)));
        assertTrue(doubleNum(10).divide(2).isEqual(5));
        assertTrue(doubleNum(10).divide(0).isNaN());
    }

    @Test
    public void remainder() {
        assertTrue(doubleNum(0).remainder(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(10).remainder(decimalNum64(2)));
        assertTrue(doubleNum(10).remainder(2).isEqual(0));
        assertTrue(doubleNum(10).remainder(0).isNaN());
    }

    @Test
    public void power() {
        assertTrue(doubleNum(0).power(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(10).power(decimalNum64(2)));
        assertTrue(doubleNum(10).power(2).isEqual(100));
    }

    @Test
    public void square() {
        assertTrue(doubleNum(10).square().isEqual(100));
    }

    @Test
    public void cube() {
        assertTrue(doubleNum(10).cube().isEqual(1000));
    }

    @Test
    public void exponential() {
        assertTrue(doubleNum(2).exponential().isEqual(7.38905609893065, EPSILON));
    }

    @Test
    public void nthRoot() {
        assertTrue(doubleNum(0).nthRoot(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(32).nthRoot(decimalNum64(5)));
        assertTrue(doubleNum(32).nthRoot(5).isEqual(2));
    }

    @Test
    public void squareRoot() {
        assertTrue(doubleNum(4).squareRoot().isEqual(2));
    }

    @Test
    public void cubeRoot() {
        assertTrue(doubleNum(27).cubeRoot().isEqual(3));
    }

    @Test
    public void ln() {
        assertTrue(doubleNum(Math.E).ln().isEqual(1));
    }

    @Test
    public void log10() {
        assertTrue(doubleNum(100).log10().isEqual(2));
    }

    @Test
    public void log2() {
        assertTrue(doubleNum(8).log2().isEqual(3));
    }

    @Test
    public void log() {
        assertTrue(doubleNum(0).log(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(256).log(decimalNum64(16)));
        assertTrue(doubleNum(256).log(16).isEqual(2));
    }

    @Test
    public void absoluteValue() {
        assertTrue(doubleNum(1).absoluteValue().isEqual(1));
        assertTrue(doubleNum(-1).absoluteValue().isEqual(1));
    }

    @Test
    public void negate() {
        assertTrue(doubleNum(1).negate().isEqual(-1));
        assertTrue(doubleNum(-1).negate().isEqual(1));
    }

    @Test
    public void reciprocal() {
        assertTrue(doubleNum(1).reciprocal().isEqual(1));
        assertTrue(doubleNum(10).reciprocal().isEqual(0.1));
    }

    @Test
    public void increment() {
        assertTrue(doubleNum(1).increment().isEqual(2));
    }

    @Test
    public void decrement() {
        assertTrue(doubleNum(1).decrement().isEqual(0));
    }

    @Test
    public void floor() {
        assertTrue(doubleNum(1.1).floor().isEqual(1));
        assertTrue(doubleNum(1.9).floor().isEqual(1));
        assertTrue(doubleNum(-1.1).floor().isEqual(-2));
        assertTrue(doubleNum(-1.9).floor().isEqual(-2));
    }

    @Test
    public void ceil() {
        assertTrue(doubleNum(1.1).ceil().isEqual(2));
        assertTrue(doubleNum(1.9).ceil().isEqual(2));
        assertTrue(doubleNum(-1.1).ceil().isEqual(-1));
        assertTrue(doubleNum(-1.9).ceil().isEqual(-1));
    }

    @Test
    public void degrees() {
        assertTrue(doubleNum(1).degrees().isEqual(57.29577951308232, EPSILON));
    }

    @Test
    public void radians() {
        assertTrue(doubleNum(180).radians().isEqual(Math.PI));
    }

    @Test
    public void sin() {
        assertTrue(doubleNum(1).sin().isEqual(0.8414709848078965, EPSILON));
    }

    @Test
    public void cos() {
        assertTrue(doubleNum(1).cos().isEqual(0.5403023058681398, EPSILON));
    }

    @Test
    public void tan() {
        assertTrue(doubleNum(1).tan().isEqual(1.5574077246549023, EPSILON));
    }

    @Test
    public void asin() {
        assertTrue(doubleNum(1).asin().isEqual(1.5707963267948966, EPSILON));
    }

    @Test
    public void acos() {
        assertTrue(doubleNum(0).acos().isEqual(1.5707963267948966, EPSILON));
    }

    @Test
    public void atan() {
        assertTrue(doubleNum(1).atan().isEqual(0.7853981633974483, EPSILON));
    }

    @Test
    public void atan2() {
        assertTrue(doubleNum(1).atan2(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(1).atan2(decimalNum64(1)));
        assertTrue(doubleNum(1).atan2(1).isEqual(0.7853981633974483, EPSILON));
    }

    @Test
    public void sinh() {
        assertTrue(doubleNum(1).sinh().isEqual(1.1752011936438014, EPSILON));
    }

    @Test
    public void cosh() {
        assertTrue(doubleNum(1).cosh().isEqual(1.543080634815244, EPSILON));
    }

    @Test
    public void tanh() {
        assertTrue(doubleNum(1).tanh().isEqual(0.7615941559557649, EPSILON));
    }

    @Test
    public void asinh() {
        assertTrue(doubleNum(2).asinh().isEqual(1.4436354751788103, EPSILON));
    }

    @Test
    public void acosh() {
        assertTrue(doubleNum(2).acosh().isEqual(1.3169578969248166, EPSILON));
    }

    @Test
    public void atanh() {
        assertTrue(doubleNum(0.5).atanh().isEqual(0.5493061443340548, EPSILON));
    }

    @Test
    public void hypotenuse() {
        assertTrue(doubleNum(2).hypotenuse(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(2).hypotenuse(decimalNum64(2)));
        assertTrue(doubleNum(2).hypotenuse(2).isEqual(2.8284271247461903, EPSILON));
    }

    @Test
    public void average() {
        assertTrue(doubleNum(2).average(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(2).average(decimalNum64(4)));
        assertTrue(doubleNum(2).average(4).isEqual(3));
    }

    @Test
    public void min() {
        assertTrue(doubleNum(2).min(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(2).min(decimalNum64(4)));
        assertTrue(doubleNum(2).min(4).isEqual(2));
        assertTrue(doubleNum(4).min(2).isEqual(2));
    }

    @Test
    public void max() {
        assertTrue(doubleNum(2).max(DoubleNum.NaN).isNaN());
        assertInstanceOf(DecimalNum.class, doubleNum(2).max(decimalNum64(4)));
        assertTrue(doubleNum(2).max(4).isEqual(4));
        assertTrue(doubleNum(4).max(2).isEqual(4));
    }

    @Test
    public void clamp() {
        assertTrue(doubleNum(2).clamp(1, 4).isEqual(2));
        assertTrue(doubleNum(0).clamp(1, 4).isEqual(1));
        assertTrue(doubleNum(5).clamp(1, 4).isEqual(4));
    }

    @Test
    public void integerPart() {
        assertTrue(doubleNum(2.5).integerPart().isEqual(2));
    }

    @Test
    public void fractionalPart() {
        assertTrue(doubleNum(2.5).fractionalPart().isEqual(0.5));
    }

    @Test
    public void round() {
        assertTrue(doubleNum(2.5).round().isEqual(2));
        assertTrue(doubleNum(3.5).round().isEqual(4));
    }

    @Test
    public void roundRoundingMode() {
        assertTrue(doubleNum(2.5).round(HALF_UP).isEqual(3));
    }

    @Test
    public void roundInt() {
        assertTrue(doubleNum(2.555).round(2).isEqual(2.56));
    }

    @Test
    public void roundIntRoundingMode() {
        assertTrue(doubleNum(2.555).round(2, CEILING).isEqual(2.56));
        assertTrue(doubleNum(2.555).round(2, FLOOR).isEqual(2.55));
        assertTrue(doubleNum(2.555).round(2, HALF_UP).isEqual(2.56));
        assertTrue(doubleNum(2.555).round(2, HALF_EVEN).isEqual(2.56));
        assertTrue(doubleNum(2.555).round(2, HALF_DOWN).isEqual(2.55));
    }

    @Test
    public void significantInt() {
        assertTrue(doubleNum(3.5).significantFigures(1).isEqual(4));
    }

    @Test
    public void significantIntRoundingMode() {
        assertTrue(doubleNum(3.5).significantFigures(1, FLOOR).isEqual(3));
    }

    @Test
    public void significantFiguresMathContext() {
        assertTrue(doubleNum(3.5).significantFigures(new MathContext(1, FLOOR)).isEqual(3));
    }

    @Test
    public void significantFigures() {
        assertEquals(2, doubleNum(3.5).significantFigures());
    }

    @Test
    public void mantissa() {
        assertTrue(doubleNum(2.555).mantissa().isEqual(2.555));
        assertTrue(doubleNum(0.00001).mantissa().isEqual(1));
    }

    @Test
    public void exponent() {
        assertEquals(0, doubleNum(2.555).exponent());
        assertEquals(3, doubleNum(2000).exponent());
    }

    @Test
    public void signum() {
        assertEquals(1, doubleNum(5).signum());
        assertEquals(0, doubleNum(0).signum());
        assertEquals(-1, doubleNum(-5).signum());
    }

    @Test
    public void isNegative() {
        assertTrue(doubleNum(-1).isNegative());
        assertFalse(doubleNum(0).isNegative());
        assertFalse(doubleNum(1).isNegative());
    }

    @Test
    public void isNegativeOrZero() {
        assertTrue(doubleNum(-1).isNegativeOrZero());
        assertTrue(doubleNum(0).isNegativeOrZero());
        assertFalse(doubleNum(1).isNegativeOrZero());
    }

    @Test
    public void isNegativeOrZeroNum() {
        assertFalse(doubleNum(0).isNegativeOrZero(DoubleNum.NaN));
        assertTrue(doubleNum(0).isNegativeOrZero(decimalNum64(0.1)));
        assertTrue(doubleNum(-1).isNegativeOrZero(0.1));
        assertTrue(doubleNum(-0.1).isNegativeOrZero(0.1));
        assertTrue(doubleNum(-0.01).isNegativeOrZero(0.1));
        assertTrue(doubleNum(0).isNegativeOrZero(0.1));
        assertTrue(doubleNum(0.01).isNegativeOrZero(0.1));
        assertTrue(doubleNum(0.1).isNegativeOrZero(0.1));
        assertFalse(doubleNum(1).isNegativeOrZero(0.1));
    }

    @Test
    public void isPositive() {
        assertFalse(doubleNum(-1).isPositive());
        assertFalse(doubleNum(0).isPositive());
        assertTrue(doubleNum(1).isPositive());
    }

    @Test
    public void isPositiveOrZero() {
        assertFalse(doubleNum(-1).isPositiveOrZero());
        assertTrue(doubleNum(0).isPositiveOrZero());
        assertTrue(doubleNum(1).isPositiveOrZero());
    }

    @Test
    public void isPositiveOrZeroNum() {
        assertFalse(doubleNum(0).isPositiveOrZero(DoubleNum.NaN));
        assertTrue(doubleNum(0).isPositiveOrZero(decimalNum64(0.1)));
        assertFalse(doubleNum(-1).isPositiveOrZero(0.1));
        assertTrue(doubleNum(-0.1).isPositiveOrZero(0.1));
        assertTrue(doubleNum(-0.01).isPositiveOrZero(0.1));
        assertTrue(doubleNum(0).isPositiveOrZero(0.1));
        assertTrue(doubleNum(0.01).isPositiveOrZero(0.1));
        assertTrue(doubleNum(0.1).isPositiveOrZero(0.1));
        assertTrue(doubleNum(1).isPositiveOrZero(0.1));
    }

    @Test
    public void isZero() {
        assertFalse(doubleNum(-1).isZero());
        assertTrue(doubleNum(0).isZero());
        assertFalse(doubleNum(1).isZero());
    }

    @Test
    public void isZeroNum() {
        assertFalse(doubleNum(0).isZero(DoubleNum.NaN));
        assertTrue(doubleNum(0).isZero(decimalNum64(0.1)));
        assertFalse(doubleNum(-1).isZero(0.1));
        assertTrue(doubleNum(-0.1).isZero(0.1));
        assertTrue(doubleNum(-0.01).isZero(0.1));
        assertTrue(doubleNum(0).isZero(0.1));
        assertTrue(doubleNum(0.01).isZero(0.1));
        assertTrue(doubleNum(0.1).isZero(0.1));
        assertFalse(doubleNum(1).isZero(0.1));
    }

    @Test
    public void isEqual() {
        assertFalse(doubleNum(0).isEqual(DoubleNum.NaN));
        assertTrue(doubleNum(0).isEqual(decimalNum64(0)));
        assertTrue(doubleNum(1).isEqual(1));
        assertFalse(doubleNum(1).isEqual(0));
    }

    @Test
    public void isEqualNum() {
        assertFalse(doubleNum(0).isEqual(0, DoubleNum.NaN));
        assertFalse(doubleNum(0).isEqual(DoubleNum.NaN, 0));
        assertTrue(doubleNum(0).isEqual(0, decimalNum64(0.1)));
        assertTrue(doubleNum(0).isEqual(decimalNum64(0), 0.1));
        assertFalse(doubleNum(-1).isEqual(0, 0.1));
        assertTrue(doubleNum(-0.1).isEqual(0, 0.1));
        assertTrue(doubleNum(-0.01).isEqual(0, 0.1));
        assertTrue(doubleNum(0).isEqual(0, 0.1));
        assertTrue(doubleNum(0.01).isEqual(0, 0.1));
        assertTrue(doubleNum(0.1).isEqual(0, 0.1));
        assertFalse(doubleNum(1).isEqual(0, 0.1));
    }

    @Test
    public void isLessThan() {
        assertFalse(doubleNum(0).isLessThan(DoubleNum.NaN));
        assertTrue(doubleNum(0).isLessThan(decimalNum64(1)));
        assertTrue(doubleNum(0).isLessThan(1));
        assertFalse(doubleNum(0).isLessThan(0));
        assertFalse(doubleNum(0).isLessThan(-1));
    }

    @Test
    public void isLessThanOrEqual() {
        assertFalse(doubleNum(0).isLessThanOrEqual(DoubleNum.NaN));
        assertTrue(doubleNum(0).isLessThanOrEqual(decimalNum64(1)));
        assertTrue(doubleNum(0).isLessThanOrEqual(1));
        assertTrue(doubleNum(0).isLessThanOrEqual(0));
        assertFalse(doubleNum(0).isLessThanOrEqual(-1));
    }

    @Test
    public void isLessThanOrEqualNum() {
        assertFalse(doubleNum(0).isLessThanOrEqual(0, DoubleNum.NaN));
        assertFalse(doubleNum(0).isLessThanOrEqual(DoubleNum.NaN, 0));
        assertTrue(doubleNum(0).isLessThanOrEqual(0, decimalNum64(0.1)));
        assertTrue(doubleNum(0).isLessThanOrEqual(decimalNum64(0), 0.1));
        assertTrue(doubleNum(-1).isLessThanOrEqual(0, 0.1));
        assertTrue(doubleNum(-0.1).isLessThanOrEqual(0, 0.1));
        assertTrue(doubleNum(-0.01).isLessThanOrEqual(0, 0.1));
        assertTrue(doubleNum(0).isLessThanOrEqual(0, 0.1));
        assertTrue(doubleNum(0.01).isLessThanOrEqual(0, 0.1));
        assertTrue(doubleNum(0.1).isLessThanOrEqual(0, 0.1));
        assertFalse(doubleNum(1).isLessThanOrEqual(0, 0.1));
    }

    @Test
    public void isGreaterThan() {
        assertFalse(doubleNum(0).isGreaterThan(DoubleNum.NaN));
        assertTrue(doubleNum(0).isGreaterThan(decimalNum64(-1)));
        assertFalse(doubleNum(0).isGreaterThan(1));
        assertFalse(doubleNum(0).isGreaterThan(0));
        assertTrue(doubleNum(0).isGreaterThan(-1));
    }

    @Test
    public void isGreaterThanOrEqual() {
        assertFalse(doubleNum(0).isGreaterThanOrEqual(DoubleNum.NaN));
        assertFalse(doubleNum(0).isGreaterThanOrEqual(decimalNum64(1)));
        assertFalse(doubleNum(0).isGreaterThanOrEqual(1));
        assertTrue(doubleNum(0).isGreaterThanOrEqual(0));
        assertTrue(doubleNum(0).isGreaterThanOrEqual(-1));
    }

    @Test
    public void isGreaterThanOrEqualNum() {
        assertFalse(doubleNum(0).isGreaterThanOrEqual(0, DoubleNum.NaN));
        assertFalse(doubleNum(0).isGreaterThanOrEqual(DoubleNum.NaN, 0));
        assertTrue(doubleNum(0).isGreaterThanOrEqual(0, decimalNum64(0.1)));
        assertTrue(doubleNum(0).isGreaterThanOrEqual(decimalNum64(0), 0.1));
        assertFalse(doubleNum(-1).isGreaterThanOrEqual(0, 0.1));
        assertTrue(doubleNum(-0.1).isGreaterThanOrEqual(0, 0.1));
        assertTrue(doubleNum(-0.01).isGreaterThanOrEqual(0, 0.1));
        assertTrue(doubleNum(0).isGreaterThanOrEqual(0, 0.1));
        assertTrue(doubleNum(0.01).isGreaterThanOrEqual(0, 0.1));
        assertTrue(doubleNum(0.1).isGreaterThanOrEqual(0, 0.1));
        assertTrue(doubleNum(1).isGreaterThanOrEqual(0, 0.1));
    }

    @Test
    public void isNaN() {
        assertFalse(doubleNum(1).isNaN());
    }

    @Test
    public void ifNaN() {
        assertTrue(doubleNum(1).ifNaN(doubleNum(0)).isEqual(doubleNum(1)));
    }

    @Test
    public void ifNaNThrow() {
        assertDoesNotThrow(() -> doubleNum(1).ifNaNThrow());
    }

    @Test
    public void ifNaNThrowSupplier() {
        assertDoesNotThrow(() -> doubleNum(1).ifNaNThrow(RuntimeException::new));
    }

    @Test
    public void toByte() {
        assertEquals((byte) 1.0, doubleNum(1).toByte());
    }

    @Test
    public void toShort() {
        assertEquals((short) 1.0, doubleNum(1).toShort());
    }

    @Test
    public void toInt() {
        assertEquals((int) 1.0, doubleNum(1).toInt());
    }

    @Test
    public void toLong() {
        assertEquals((long) 1.0, doubleNum(1).toLong());
    }

    @Test
    public void toFloat() {
        assertEquals((float) 1.0, doubleNum(1).toFloat());
    }

    @Test
    public void toDouble() {
        assertEquals(1.0, doubleNum(1).toDouble());
    }

    @Test
    public void unwrap() {
        assertEquals(1.0, doubleNum(1).unwrap());
    }

    @Test
    public void toBigDecimal() {
        assertEquals(0, doubleNum(1).toBigDecimal().compareTo(new BigDecimal(1)));
    }

    @Test
    public void getContext() {
        assertEquals(DECIMAL64, doubleNum(1).getContext());
    }

    @Test
    public void getContextPrecision() {
        assertEquals(16, doubleNum(1).getContextPrecision());
    }

    @Test
    public void getContextRoundingMode() {
        assertEquals(HALF_EVEN, doubleNum(1).getContextRoundingMode());
    }

    @Test
    public void getFactory() {
        assertInstanceOf(DoubleNum.class, doubleNum(1).getFactory().of(1));
    }

    @Test
    public void _equals() {
        assertEquals(doubleNum(1), doubleNum(1));
        assertNotEquals(doubleNum(1), doubleNum(2));
        assertNotEquals(doubleNum(1), decimalNum64(1));
    }

    @Test
    public void _hashCode() {
        assertEquals(doubleNum(1).hashCode(), doubleNum(1).hashCode());
        assertNotEquals(doubleNum(1).hashCode(), doubleNum(2).hashCode());
    }

    @Test
    public void _toString() {
        assertTrue(doubleNum(doubleNum(1).toString()).isEqual(1));
    }

    @Test
    public void compareTo() {
        assertEquals(0, doubleNum(1).compareTo(DoubleNum.NaN));
        assertEquals(0, doubleNum(1).compareTo(decimalNum64(1)));
        assertEquals(0, doubleNum(1).compareTo(doubleNum(1)));
        assertEquals(1, doubleNum(1).compareTo(doubleNum(-1)));
        assertEquals(-1, doubleNum(1).compareTo(doubleNum(2)));
    }
}
