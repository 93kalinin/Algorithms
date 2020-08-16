package line.pathfind;

import line.pathfind.util.GridCoordinates;
import line.pathfind.util.GridReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.Iterator;

/**
 * !! READ package-info.java !!
 */
public class GridTest {
    static final String GRIDS_PATH = System.getProperty("user.dir")
            + path(" test line pathfind resources grid_test.csv");
    static final String DATA_LINE_REGEX = "(?:\\d+,\\d+ ){2}S (?:\\d+,\\d+ ?)*";

    @ParameterizedTest
    @MethodSource("gridProvider")
    void checkValid(GridCoordinates expected) {

        Grid actual = new Grid(expected.grid);

        Assertions.assertTrue(expected.hasSameCoordinatesAs(actual));
    }

    static Iterator<GridCoordinates> gridProvider() {
        return new GridReader<>(GRIDS_PATH, DATA_LINE_REGEX, GridCoordinates::new);
    }

    public static String path(String directories) {
        String separator = File.separator;
        return directories.replaceAll(" ", separator);
    }
}
