package line.pathfind.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * !! READ package-info.java !!
 * Read grids from a file.
 * The constructor expects a function which is able to supply
 * instances of data classes (grid + related information from the data line).
 * The FileReader will not be closed unless this iterator is fully exhausted.
 */
public class GridReader<T> implements Iterator<T> {

    private final BufferedReader reader;
    private final String DATA_LINE_REGEX;
    private final BiFunction<char[][], String, T> gridParser;
    private T nextGrid;
    private final Matcher matcher;

    public GridReader(String path, String dataLineRegex, BiFunction<char[][], String, T> gridParser) {
        DATA_LINE_REGEX = dataLineRegex;
        this.gridParser = gridParser;
        try {
            this.reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        matcher = Pattern.compile(dataLineRegex).matcher("");
        tryAdvance();
    }

    @Override
    public boolean hasNext() {
        boolean isEmpty = nextGrid == null;
        if (isEmpty) {
            try {
                reader.close();
            } catch (IOException exception) {
                throw new IllegalStateException(exception);
            }
        }
        return !isEmpty;
    }

    @Override
    public T next() {
        if (nextGrid == null) {
            throw new IllegalStateException("This iterator is either exhausted or empty");
        }
        T tmp = nextGrid;
        nextGrid = null;
        tryAdvance();
        return tmp;
    }

    private void tryAdvance() {
        List<char[]> rows = new ArrayList<>();
        String dataLine = null;
        boolean linesRead = false;

        try {
            String line = reader.readLine();
            while (line != null && !line.isBlank()) {
                if (line.startsWith("#")) {
                    line = reader.readLine();
                    continue;
                }
                linesRead = true;
                matcher.reset(line);
                if (matcher.matches()) {
                    dataLine = line;
                    reader.readLine();
                    break;
                }
                rows.add(line.toCharArray());
                line = reader.readLine();
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException(exception);
        }

        if (linesRead && dataLine == null) {
            throw new IllegalArgumentException("Failed to find a data line right after the grid " +
                    "which matches the given pattern "
                    + DATA_LINE_REGEX
                    + " Read package-info.java.");
        }
        char[][] grid = rows.toArray(new char[][] {});
        nextGrid = (rows.isEmpty()) ? null : gridParser.apply(grid, dataLine);
    }
}
