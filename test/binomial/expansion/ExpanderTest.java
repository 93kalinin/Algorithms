package binomial.expansion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpanderTest {
    @ParameterizedTest
    @CsvSource({
            "(x+1)^2, x^2+2x+1",
            "(p-1)^3, p^3-3p^2+3p-1",
            "(2f+4)^6, 64f^6+768f^5+3840f^4+10240f^3+15360f^2+12288f+4096",
            "(-2a-4)^0, 1",
            "(-12t+43)^2, 144t^2-1032t+1849",
            "(r+0)^203, r^203",
            "(-x-1)^2, x^2+2x+1",
            "(-x-1)^3, -x^3-3x^2-3x-1"
    })
    void expand(String nonexpanded, String expected) {
        String actual = Expander.expand(nonexpanded);
        assertEquals(expected, actual);
    }
}
