package polynomial.derivative.evaluation;

/** Hold values for an algebraic term such as 9x^2, where 9 is coefficient and 2 is exponent */
public class IntTerm {
    public final int coefficient, exponent;

    public IntTerm(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    @Override
    public String toString() {
        String signedCoefficient = (coefficient >= 0) ? "+" + coefficient
                : "" + coefficient;
        return signedCoefficient + "x^" + exponent;
    }
}