package polynomial.derivative.evaluation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

public class EvaluatorTest {
    @ParameterizedTest
    @CsvSource({
            "x, 1, 1",
            "-33x, -12, -33",
            "x^7, 22, 793659328",
            "30x^2, 2, 120",
            "-12x^8, 11, -1870768416",
            "30x^2+11x^3, 3, 477",
            "4-x, -12, -1",
            "-30x^2+11x^3+2-92x, -12, 5380",
            "x^7-x^4+x, 1, 4",
            "-30x+11x+2-92x, -12, -111",
            "-92x^6-94x^5+12x^4+64x^3+35x^2+65x^1+96, 295, -1236802641565235"
    })
    void emuns(String polynomial, int xValue, BigInteger expected) {
        BigInteger actual = Evaluator.differentiate(polynomial, xValue);
        Assertions.assertEquals(expected, actual);
    }
}
