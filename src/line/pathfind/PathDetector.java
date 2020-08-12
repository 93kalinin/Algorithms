package line.pathfind;

/**
 * Decide if the path can be traversed according to the rules on a given grid
 */
public class PathDetector {
    private final Grid grid;

    public PathDetector(char[][] gridArg) {
        this.grid = new Grid(gridArg);
    }

    private boolean isValidFor(IntCoordinate start) {
        IntCoordinate finish = (start.equals(grid.firstEndpoint)) ?
                grid.secondEndpoint : grid.firstEndpoint;
        Path path = new Path(grid, start, finish);
        return path.go() == Path.Result.FINISHED;
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
