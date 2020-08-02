package binomial.expansion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import codewars.util.IntBinomial;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @ParameterizedTest
    @CsvSource({
            "(2x+1)^2, x, 2, 1, 2",
            "(-11y-9)^7, y, -11, -9, 7",
            "(p-2)^1, p, 1, -2, 1",
            "(-m+3)^0, m, -1, 3, 0",
            "(K+0)^1, K, 1, 0, 1"
    })
    void parseValidStringExpression(String expression, char variableName, int coeff1, int coeff2, int exponent) {
        Parser parser = new Parser(expression);
        IntBinomial expected = new IntBinomial(variableName, coeff1, coeff2, exponent);

        IntBinomial actual = parser.parse();

        assertEquals(expected, actual);
    }
}