package line.pathfind.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * !! READ package-info.java !!
 */
public class GridWalkability {
    public final char[][] grid;
    public final boolean expected;

    public GridWalkability(char[][] grid, String walkability) {
        Objects.requireNonNull(grid);
        Objects.requireNonNull(walkability);

        this.grid = grid;
        this.expected = Boolean.parseBoolean(walkability);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridWalkability that = (GridWalkability) o;
        return expected == that.expected &&
                Arrays.deepEquals(grid, that.grid);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(expected);
        result = 31 * result + Arrays.hashCode(grid);
        return result;
    }
}
