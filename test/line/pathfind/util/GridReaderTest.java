package line.pathfind.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static line.pathfind.PathTest.path;

public class GridReaderTest {
    static final String GRIDS_PATH = System.getProperty("user.dir")
            + path(" test line pathfind resources grid_reader_test.csv");
    static final String DATA_LINE_REGEX = "true|false";
    static GridReader<GridWalkability> reader;

    @BeforeAll
    static void readerInit() {
        reader = new GridReader<>(GRIDS_PATH, DATA_LINE_REGEX, GridWalkability::new);
    }

    @Test
    void readFirst() {
        char[][] expectedGrid = new char[][] {
                {' ', ' ', ' ', '+', '-', '-', '-', '-', '-', '-', '-', '-', '+'},
                {'X', '-', '-', '+', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '+', '-', '-', '+'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
        };
        GridWalkability expected = new GridWalkability(expectedGrid, "true");

        GridWalkability actual = reader.next();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readSecond() {
        char[][] expectedGrid = new char[][] {
                {'X', '-', '-', '-', '-', '-', '|', '-', '-', '-', '-', 'X'}};
        GridWalkability expected = new GridWalkability(expectedGrid, "false");

        GridWalkability actual = reader.next();

        Assertions.assertEquals(expected, actual);
    }
}
