package polynomial.derivative.evaluation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.math.BigInteger;

public class EvaluatorTest {
    enum TestParam {
        ONE_NO_COEFFICIENT_NO_EXPONENT("x", "1", 1, "1"),
        ONE_NEGATIVE_NO_EXPONENT("-33x", "-33", -12, "-33"),
        ONE_NO_COEFFICIENT("x^7", "7x^6", 22, "793659328"),
        ONE_SIMPLE("30x^2", "60x", 2, "120"),
        ONE_NEGATIVE_LARGE("-12x^8", "-96x^7", 11, "-1870768416"),
        TWO_SIMPLE("30x^2+11x^3", "60x+33x^2", 3, "477"),
        TWO_FIRST_IS_A_NUMBER("4-x", "-1", -12, "-1"),
        SEVERAL_SIMPLE("-30x^2+11x^3+2-92x", "33x^2-60x-92", -12, "5380"),
        SEVERAL_NO_COEFFICIENTS("x^7-x^4+x", "7x^6-4x^3+1", 1, "4"),
        SEVERAL_NO_EXPONENTS("-30x+11x+2-92x", "-30+11-92", -12, "-111"),
        SEVERAL_EXTREMELY_LARGE("-92x^6-94x^5+12x^4+64x^3+35x^2+65x^1+96",
                "-552x^5 -470x^4 +48x^3 +192x^2 +70x + 65",
                295,
                "-1236802641565235");

        final String polynomial;
        final String derivative;
        final int x;
        final BigInteger expected;

        TestParam(String polynomial, String derivative, int x, String expected) {
            this.polynomial = polynomial;
            this.derivative = derivative;
            this.x = x;
            this.expected = new BigInteger(expected);
        }
    }

    @ParameterizedTest
    @EnumSource(TestParam.class)
    void emuns(TestParam arg) {
        BigInteger actual = Evaluator.differentiate(arg.polynomial, arg.x);
        Assertions.assertEquals(arg.expected, actual);
    }
}
