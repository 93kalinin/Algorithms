package binomial.expansion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PascalTriangleRowTest {
    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 11",
            "2, 121",
            "3, 1331",
            "4, 14641",
            "5, 15101051",
            "6, 1615201561"
    })
    void parseValidStringExpression(int rowNumber, String expected) {
        PascalTriangleRow row = new PascalTriangleRow(rowNumber);
        String actual = "";

        while (row.hasNext()) {
            actual += row.nextInt();
        }

        assertEquals(expected, actual);
    }
}
