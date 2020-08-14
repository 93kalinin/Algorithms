package line.pathfind;

import java.util.EnumSet;
import java.util.HashSet;

/**
 * Keep track of the current position on the grid and the visited tiles.
 * Traverse the grid according to the task rules.
 */
public class Path {
    public enum Result { DEAD_END, AMBIGUITY, FINISHED }

    private final Grid grid;
    private final HashSet<IntCoordinate> visited;
    private final IntCoordinate finish;

    private IntCoordinate currentCoordinate;
    private Direction cameFrom;

    public Path(Grid grid, IntCoordinate start, IntCoordinate finish) {
        this.grid = grid;
        this.visited = new HashSet<>();
        this.finish = finish;
        this.currentCoordinate = start;
    }

    /**
     * Try reaching finish unless faced with a dead end or ambiguity
     */
    public Result go() {
        while (!currentCoordinate.equals(finish)) {
            Tile currentTile = grid.get(currentCoordinate.x, currentCoordinate.y);
            visited.add(currentCoordinate);
            EnumSet<Direction> possibleDirections = currentTile.resolveNextStep(cameFrom);

            possibleDirections.removeIf(direction -> {
                IntCoordinate neighborCoordinate = currentCoordinate.getNeighbor(direction);
                return visited.contains(neighborCoordinate);
            });
            if (possibleDirections.isEmpty()) return Result.DEAD_END;
            if (possibleDirections.size() > 1) return Result.AMBIGUITY;

            Direction theOnlyPossibleDirection = (Direction) possibleDirections.toArray()[0];
            currentCoordinate = currentCoordinate.getNeighbor(theOnlyPossibleDirection);
            cameFrom = theOnlyPossibleDirection.opposite();
        }
        return Result.FINISHED;
    }

    private static boolean isValidFor(Grid grid, IntCoordinate start) {
        IntCoordinate finish = (start.equals(grid.firstEndpoint)) ?
                grid.secondEndpoint : grid.firstEndpoint;
        Path path = new Path(grid, start, finish);
        return path.go() == Path.Result.FINISHED;
    }

    public static boolean line(final char[][] gridArg) {
        Grid grid = new Grid(gridArg);
        return isValidFor(grid, grid.firstEndpoint)
                || isValidFor(grid, grid.secondEndpoint);
    }
}
