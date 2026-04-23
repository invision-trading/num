package trade.invision.num;

import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.MathContext.DECIMAL64;
import static java.math.RoundingMode.HALF_EVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trade.invision.num.DoubleNum.doubleNum;

@NullMarked
public final class NaNNumTest {

    @Test
    public void add() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.add(doubleNum(0)));
    }

    @Test
    public void subtract() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.subtract(doubleNum(0)));
    }

    @Test
    public void multiply() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.multiply(doubleNum(0)));
    }

    @Test
    public void divide() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.divide(doubleNum(0)));
    }

    @Test
    public void remainder() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.remainder(doubleNum(0)));
    }

    @Test
    public void power() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.power(doubleNum(0)));
    }

    @Test
    public void square() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.square());
    }

    @Test
    public void cube() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.cube());
    }

    @Test
    public void exponential() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.exponential());
    }

    @Test
    public void nthRoot() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.nthRoot(doubleNum(0)));
    }

    @Test
    public void squareRoot() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.squareRoot());
    }

    @Test
    public void cubeRoot() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.cubeRoot());
    }

    @Test
    public void ln() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.ln());
    }

    @Test
    public void log10() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.log10());
    }

    @Test
    public void log2() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.log2());
    }

    @Test
    public void log() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.log(doubleNum(0)));
    }

    @Test
    public void absoluteValue() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.absoluteValue());
    }

    @Test
    public void negate() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.negate());
    }

    @Test
    public void reciprocal() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.reciprocal());
    }

    @Test
    public void increment() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.increment());
    }

    @Test
    public void decrement() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.decrement());
    }

    @Test
    public void floor() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.floor());
    }

    @Test
    public void ceil() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.ceil());
    }

    @Test
    public void degrees() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.degrees());
    }

    @Test
    public void radians() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.radians());
    }

    @Test
    public void sin() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.sin());
    }

    @Test
    public void cos() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.cos());
    }

    @Test
    public void tan() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.tan());
    }

    @Test
    public void asin() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.asin());
    }

    @Test
    public void acos() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.acos());
    }

    @Test
    public void atan() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.atan());
    }

    @Test
    public void atan2() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.atan2(doubleNum(0)));
    }

    @Test
    public void sinh() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.sinh());
    }

    @Test
    public void cosh() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.cosh());
    }

    @Test
    public void tanh() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.tanh());
    }

    @Test
    public void asinh() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.asinh());
    }

    @Test
    public void acosh() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.acosh());
    }

    @Test
    public void atanh() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.atanh());
    }

    @Test
    public void hypotenuse() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.hypotenuse(doubleNum(0)));
    }

    @Test
    public void average() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.average(doubleNum(0)));
    }

    @Test
    public void min() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.min(doubleNum(0)));
    }

    @Test
    public void max() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.max(doubleNum(0)));
    }

    @Test
    public void clamp() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.clamp(doubleNum(0), doubleNum(1)));
    }

    @Test
    public void integerPart() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.integerPart());
    }

    @Test
    public void fractionalPart() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.fractionalPart());
    }

    @Test
    public void round() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.round());
    }

    @Test
    public void roundRoundingMode() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.round(HALF_EVEN));
    }

    @Test
    public void roundInt() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.round(0));
    }

    @Test
    public void roundIntRoundingMode() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.round(0, HALF_EVEN));
    }

    @Test
    public void significantFiguresMathContext() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.significantFigures(DECIMAL64));
    }

    @Test
    public void significantFigures() {
        assertEquals(0, DoubleNum.NaN.significantFigures());
    }

    @Test
    public void mantissa() {
        assertInstanceOf(NaNNum.class, DoubleNum.NaN.mantissa());
    }

    @Test
    public void exponent() {
        assertEquals(0, DoubleNum.NaN.exponent());
    }

    @Test
    public void signum() {
        assertEquals(0, DoubleNum.NaN.signum());
    }

    @Test
    public void isNegative() {
        assertFalse(DoubleNum.NaN.isNegative());
    }

    @Test
    public void isNegativeOrZero() {
        assertFalse(DoubleNum.NaN.isNegativeOrZero());
    }

    @Test
    public void isNegativeOrZeroNum() {
        assertFalse(DoubleNum.NaN.isNegativeOrZero(doubleNum(0.1)));
    }

    @Test
    public void isPositive() {
        assertFalse(DoubleNum.NaN.isPositive());
    }

    @Test
    public void isPositiveOrZero() {
        assertFalse(DoubleNum.NaN.isPositiveOrZero());
    }

    @Test
    public void isPositiveOrZeroNum() {
        assertFalse(DoubleNum.NaN.isPositiveOrZero(doubleNum(0.1)));
    }

    @Test
    public void isZero() {
        assertFalse(DoubleNum.NaN.isZero());
    }

    @Test
    public void isZeroNum() {
        assertFalse(DoubleNum.NaN.isZero(doubleNum(0.1)));
    }

    @Test
    public void isEqual() {
        assertFalse(DoubleNum.NaN.isEqual(DoubleNum.NaN));
        assertFalse(DoubleNum.NaN.isEqual(doubleNum(0)));
    }

    @Test
    public void isEqualNum() {
        assertFalse(DoubleNum.NaN.isEqual(DoubleNum.NaN, doubleNum(0.1)));
        assertFalse(DoubleNum.NaN.isEqual(doubleNum(0), doubleNum(0.1)));
    }

    @Test
    public void isLessThan() {
        assertFalse(DoubleNum.NaN.isLessThan(doubleNum(0)));
    }

    @Test
    public void isLessThanOrEqual() {
        assertFalse(DoubleNum.NaN.isLessThanOrEqual(doubleNum(0)));
    }

    @Test
    public void isLessThanOrEqualNum() {
        assertFalse(DoubleNum.NaN.isLessThanOrEqual(doubleNum(0), doubleNum(0.1)));
    }

    @Test
    public void isGreaterThan() {
        assertFalse(DoubleNum.NaN.isGreaterThan(doubleNum(0)));
    }

    @Test
    public void isGreaterThanOrEqual() {
        assertFalse(DoubleNum.NaN.isGreaterThanOrEqual(doubleNum(0)));
    }

    @Test
    public void isGreaterThanOrEqualNum() {
        assertFalse(DoubleNum.NaN.isGreaterThanOrEqual(doubleNum(0), doubleNum(0.1)));
    }

    @Test
    public void isNaN() {
        assertTrue(DoubleNum.NaN.isNaN());
    }

    @Test
    public void ifNaN() {
        assertTrue(DoubleNum.NaN.ifNaN(doubleNum(0)).isEqual(doubleNum(0)));
    }

    @Test
    public void ifNaNThrow() {
        assertThrows(ArithmeticException.class, DoubleNum.NaN::ifNaNThrow);
    }

    @Test
    public void ifNaNThrowSupplier() {
        assertThrows(RuntimeException.class, () -> DoubleNum.NaN.ifNaNThrow(RuntimeException::new));
    }

    @Test
    public void unwrap() {
        assertEquals(Double.NaN, DoubleNum.NaN.unwrap());
    }

    @Test
    public void toBigDecimal() {
        assertEquals(0, DoubleNum.NaN.toBigDecimal().compareTo(BigDecimal.ZERO));
    }

    @Test
    public void getContext() {
        assertEquals(DECIMAL64, DoubleNum.NaN.getContext());
    }

    @Test
    public void getFactory() {
        assertEquals(DoubleNum.FACTORY, DoubleNum.NaN.getFactory());
    }

    @Test
    public void _toString() {
        assertEquals("NaN", DoubleNum.NaN.toString());
    }

    @Test
    public void compareTo() {
        assertEquals(0, DoubleNum.NaN.compareTo(doubleNum(0)));
        assertEquals(0, DoubleNum.NaN.compareTo(-1));
        assertEquals(0, DoubleNum.NaN.compareTo(1));
    }
}
