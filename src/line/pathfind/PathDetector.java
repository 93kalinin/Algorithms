package line.pathfind;

import codewars.util.IntCoordinate;

import java.util.HashSet;

/**
 * Decide if the path can be traversed according to the rules on a given grid
 */
public class PathDetector {
    private final Grid grid;

    private HashSet<IntCoordinate> path = new HashSet<>();
    private IntCoordinate currentCoordinate;

    public PathDetector(char[][] gridArg) {
        this.grid = new Grid(gridArg);
        currentCoordinate = grid.firstEndpoint;
        path.add(currentCoordinate);
    }

    private boolean isValidFor(IntCoordinate coordinate) {
        return false;
    }

    public boolean validate() {
        return isValidFor(grid.firstEndpoint)
                || isValidFor(grid.secondEndpoint);
    }

    public static boolean line(final char[][] grid) {
        PathDetector pathDetector = new PathDetector(grid);
        return pathDetector.validate();
    }
}
