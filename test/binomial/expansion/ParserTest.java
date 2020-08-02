package binomial.expansion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @ParameterizedTest
    @CsvSource({
            "(2x+1)^2, 2, 1, 2",
            "(-11x-9)^7, -11, -9, 7",
            "(x-2)^1, 1, -2, 1",
            "(-x+3)^0, -1, 3, 0",
            "(x+0)^1, 1, 0, 1"
    })
    void parseValidStringExpression(String expression, int a, int b, int n) {
        Parser parser = new Parser(expression);
        Constants expected = new Constants(a, b, n);

        Constants actual = parser.parse();

        assertEquals(expected, actual);
    }
}