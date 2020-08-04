package codewars.util;

import java.util.Objects;

/** Hold constants a, b, n of a term like (ax+b)^n */
public class IntBinomial {
    public final String variableName;
    public final int coeff1, coeff2, exponent;

    public IntBinomial(String variableName, int coeff1, int coeff2, int exponent) {
        this.variableName = variableName;
        this.coeff1 = coeff1;
        this.coeff2 = coeff2;
        this.exponent = exponent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntBinomial that = (IntBinomial) o;
        return variableName.equals(that.variableName) &&
                coeff1 == that.coeff1 &&
                coeff2 == that.coeff2 &&
                exponent == that.exponent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName, coeff1, coeff2, exponent);
    }

    @Override
    public String toString() {
        return "IntBinomial  " +
                "variableName=" + variableName +
                " coeff1=" + coeff1 +
                " coeff2=" + coeff2 +
                " exponent=" + exponent;
    }
}