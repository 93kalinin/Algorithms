package line.pathfind;

import java.util.EnumSet;
import java.util.HashSet;

/**
 * Keep track of the current position on the grid and the visited tiles.
 * Traverse the grid according to the task rules.
 */
public class Path {
    public enum Result { DEAD_END, AMBIGUITY, PARTIAL, FINISHED }

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
     * Try reaching finish unless faced with a dead end or ambiguity.
     * Make sure that every walkable tile (- + |) is a part of the path.
     */
    public Result go() {

        while (true) {
            Tile currentTile = grid.get(currentCoordinate.x, currentCoordinate.y);
            EnumSet<Direction> possibleDirections = currentTile.resolveNextStep(cameFrom);
            possibleDirections.removeIf(this::filter);
            if (possibleDirections.isEmpty()) {
                System.out.print("dead end at " + currentCoordinate);
                return Result.DEAD_END;
            }
            if (possibleDirections.size() > 1) {
                System.out.print("ambiguity end at " + currentCoordinate + " " + possibleDirections);
                return Result.AMBIGUITY;
            }

            Direction theOnlyPossibleDirection = (Direction) possibleDirections.toArray()[0];
            currentCoordinate = currentCoordinate.getNeighbor(theOnlyPossibleDirection);
            if (currentCoordinate.equals(finish)) break;
            visited.add(currentCoordinate);
            cameFrom = theOnlyPossibleDirection.opposite();
        }

        return (visited.equals(grid.everyWalkableTile)) ?
                Result.FINISHED : Result.PARTIAL;
    }

    private boolean filter(Direction direction) {
        IntCoordinate neighborCoordinate = currentCoordinate.getNeighbor(direction);
        Tile neighbor = grid.get(neighborCoordinate.x, neighborCoordinate.y);
        Direction mayEnterNeighborFrom = direction.opposite();

        boolean neighborIsUnwalkable = neighbor.chr == ' ';
        boolean neighborIsNotEnterableFromThisDirection =
                neighbor.resolveNextStep(direction.opposite()).equals(Direction.NONE_SET);
        boolean justBeenThere = direction.equals(cameFrom);

        return visited.contains(neighborCoordinate)
                || neighborIsUnwalkable
                || neighborIsNotEnterableFromThisDirection
                || justBeenThere;
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
