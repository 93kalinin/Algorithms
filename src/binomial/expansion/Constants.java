package binomial.expansion;

import java.util.Objects;

/* Hold constants of an expression in the form (ax+b)^n where x is any one-character-long variable */
class Constants {
    final int a, b, n;

    public Constants(int a, int b, int n) {
        this.a = a;
        this.b = b;
        this.n = n;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Constants constants = (Constants) that;
        return a == constants.a
                && b == constants.b
                && n == constants.n;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, n);
    }

    @Override
    public String toString() {
        return "Constants " + "a=" + a + " b=" + b + " n=" + n;
    }
}