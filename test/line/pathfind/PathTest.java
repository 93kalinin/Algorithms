package line.pathfind;

import line.pathfind.util.GridReader;
import line.pathfind.util.GridWalkability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.Iterator;

/**
 * !! READ package-info.java !!
 */
public class PathTest {
    static final String GRIDS_PATH = System.getProperty("user.dir")
            + path(" test line pathfind resources path_test.csv");
    static final String DATA_LINE_REGEX = "true|false";

    @ParameterizedTest
    @MethodSource("gridProvider")
    void checkValid(GridWalkability grid) {

        boolean actual = Path.line(grid.grid);

        Assertions.assertEquals(grid.expected, actual);
    }

    static Iterator<GridWalkability> gridProvider() {
        return new GridReader<>(GRIDS_PATH, DATA_LINE_REGEX, GridWalkability::new);
    }

    public static String path(String directories) {
        String separator = File.separator;
        return directories.replaceAll(" ", separator);
    }
}
