package trade.invision.num;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.MathContext.DECIMAL64;

/**
 * {@link NaNNum}, short for Not a Number (NaN), is a {@link Num} that represents an undefined and unrepresentable
 * numerical value. It is not equal to itself or any other value. It compares unordered with itself and all other
 * values. It is not negative, positive, or zero. It has no integer or {@link BigDecimal} representation.
 *
 * @see <a href="https://en.wikipedia.org/wiki/NaN">Wikipedia</a>
 * @see <a href="https://stackoverflow.com/a/1573715/4352701">Stack Overflow</a>
 */
public final class NaNNum implements Num {

    /**
     * Global static instance of {@link NaNNum}.
     */
    public static NaNNum NaN = new NaNNum();

    private NaNNum() {}

    @Override
    public Num numOf(Number number) {
        return this;
    }

    @Override
    public Num numOf(String number) {
        return this;
    }

    @Override
    public Num numOfTenth() {
        return this;
    }

    @Override
    public Num numOfHundredth() {
        return this;
    }

    @Override
    public Num numOfThousandth() {
        return this;
    }

    @Override
    public Num numOfNegativeOne() {
        return this;
    }

    @Override
    public Num numOfZero() {
        return this;
    }

    @Override
    public Num numOfOne() {
        return this;
    }

    @Override
    public Num numOfTwo() {
        return this;
    }

    @Override
    public Num numOfThree() {
        return this;
    }

    @Override
    public Num numOfTen() {
        return this;
    }

    @Override
    public Num numOfHundred() {
        return this;
    }

    @Override
    public Num numOfThousand() {
        return this;
    }

    @Override
    public Num add(Num addend) {
        return this;
    }

    @Override
    public Num subtract(Num subtrahend) {
        return this;
    }

    @Override
    public Num multiply(Num multiplicand) {
        return this;
    }

    @Override
    public Num divide(Num divisor) {
        return this;
    }

    @Override
    public Num remainder(Num divisor) {
        return this;
    }

    @Override
    public Num power(int exponent) {
        return this;
    }

    @Override
    public Num power(Num exponent) {
        return this;
    }

    @Override
    public Num square() {
        return this;
    }

    @Override
    public Num cube() {
        return this;
    }

    @Override
    public Num exponential() {
        return this;
    }

    @Override
    public Num root(Num degree) {
        return this;
    }

    @Override
    public Num squareRoot() {
        return this;
    }

    @Override
    public Num cubeRoot() {
        return this;
    }

    @Override
    public Num naturalLogarithm() {
        return this;
    }

    @Override
    public Num logarithm() {
        return this;
    }

    @Override
    public Num logarithm(int base) {
        return this;
    }

    @Override
    public Num logarithm(Num base) {
        return this;
    }

    @Override
    public Num absoluteValue() {
        return this;
    }

    @Override
    public Num negate() {
        return this;
    }

    @Override
    public Num reciprocal() {
        return this;
    }

    @Override
    public Num floor() {
        return this;
    }

    @Override
    public Num ceil() {
        return this;
    }

    @Override
    public Num degrees() {
        return this;
    }

    @Override
    public Num radians() {
        return this;
    }

    @Override
    public Num sine() {
        return this;
    }

    @Override
    public Num cosine() {
        return this;
    }

    @Override
    public Num tangent() {
        return this;
    }

    @Override
    public Num inverseSine() {
        return this;
    }

    @Override
    public Num inverseCosine() {
        return this;
    }

    @Override
    public Num inverseTangent() {
        return this;
    }

    @Override
    public Num inverseTangent2(Num x) {
        return this;
    }

    @Override
    public Num hyperbolicSine() {
        return this;
    }

    @Override
    public Num hyperbolicCosine() {
        return this;
    }

    @Override
    public Num hyperbolicTangent() {
        return this;
    }

    @Override
    public Num inverseHyperbolicSine() {
        return this;
    }

    @Override
    public Num inverseHyperbolicCosine() {
        return this;
    }

    @Override
    public Num inverseHyperbolicTangent() {
        return this;
    }

    @Override
    public Num hypotenuse(Num y) {
        return this;
    }

    @Override
    public Num average(Num other) {
        return this;
    }

    @Override
    public Num minimum(Num other) {
        return this;
    }

    @Override
    public Num maximum(Num other) {
        return this;
    }

    @Override
    public Num integralPart() {
        return this;
    }

    @Override
    public Num fractionalPart() {
        return this;
    }

    @Override
    public Num round(int scale, RoundingMode roundingMode) {
        return this;
    }

    @Override
    public Num precision(MathContext mathContext) {
        return this;
    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public boolean isNegativeOrZero() {
        return false;
    }

    @Override
    public boolean isPositive() {
        return false;
    }

    @Override
    public boolean isPositiveOrZero() {
        return false;
    }

    @Override
    public boolean isZero() {
        return false;
    }

    @Override
    public boolean isEqual(Num other) {
        return false;
    }

    @Override
    public boolean isLessThan(Num other) {
        return false;
    }

    @Override
    public boolean isLessThanOrEqual(Num other) {
        return false;
    }

    @Override
    public boolean isGreaterThan(Num other) {
        return false;
    }

    @Override
    public boolean isGreaterThanOrEqual(Num other) {
        return false;
    }

    @Override
    public boolean isNaN() {
        return true;
    }

    @Override
    public byte asByte() {
        throw new ArithmeticException("No NaN representation for 'byte'!");
    }

    @Override
    public short asShort() {
        throw new ArithmeticException("No NaN representation for 'short'!");
    }

    @Override
    public int asInt() {
        throw new ArithmeticException("No NaN representation for 'int'!");
    }

    @Override
    public long asLong() {
        throw new ArithmeticException("No NaN representation for 'long'!");
    }

    @Override
    public float asFloat() {
        return Float.NaN;
    }

    @Override
    public double asDouble() {
        return Double.NaN;
    }

    @Override
    public BigDecimal asBigDecimal() {
        throw new ArithmeticException("No NaN representation for 'BigDecimal'!");
    }

    @Override
    public Number unwrap() {
        return Double.NaN;
    }

    @Override
    public MathContext getContext() {
        return DECIMAL64;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public String toString() {
        return "NaN";
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(@NotNull Num o) {
        return 0;
    }
}
