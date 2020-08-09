package line.pathfind;

import codewars.util.IntCoordinate;

import java.util.HashSet;

/**
 * Keep track of the current position on the grid and the visited tiles
 */
public class Path {
    private final Grid grid;

    private HashSet<IntCoordinate> path;
    private int currentX, currentY;
    private Direction cameFrom;

    public Path(Grid grid, IntCoordinate position) {
        this.grid = grid;
        path = new HashSet<>();
        currentX = position.x;
        currentY = position.y;
    }


}
