package binomial.expansion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    enum Expression {
        SIMPLE("(2x+1)^2", new Constants(2, 1, 2));

        final String stringExpression;
        final Constants expectedConstants;

        Expression(String stringExpression, Constants expectedConstants) {
            this.stringExpression = stringExpression;
            this.expectedConstants = expectedConstants;
        }
    }

    @ParameterizedTest
    @EnumSource(Expression.class)
    void parseStringExpression(Expression arg) {
        Parser parser = new Parser(arg.stringExpression);

        Constants actual = parser.parse();

        assertEquals(arg.expectedConstants, actual);
    }
}
