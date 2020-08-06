package robo.script;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {
    @ParameterizedTest
    @CsvFileSource(resources = "resources/robo_script_syntax_highlight.csv")
    void highlightSyntax(String code, String expected) {
        String actual = Util.highlight(code);
        assertEquals(expected, actual);
    }
}
