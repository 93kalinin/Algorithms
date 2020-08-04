package binomial.expansion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PascalTriangleRowTest {
    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 11",
            "3, 121",
            "4, 1331",
            "5, 14641",
            "6, 15101051",
            "7, 1615201561"
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
