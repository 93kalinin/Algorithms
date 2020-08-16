package line.pathfind;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Find the two endpoints marked with 'X' and ensure that boundaries of the grid will not be crossed. <br>
 * Find and store coordinates of every walkable tile i.e. - | + <br>
 * The task requires that every walkable tile in the grid is a part of a valid path,
 * so keep track of them to verify that later.
 */
public class Grid {
    private final char[][] grid;
    public final IntCoordinate firstEndpoint, secondEndpoint;
    public final Set<IntCoordinate> everyWalkableTile;

    public Grid(char[][] grid) {
        this.grid = grid;
        IntCoordinate firstEndpointTmp = null;
        IntCoordinate secondEndpointTmp = null;
        Set<IntCoordinate> pathTiles = new HashSet<>();

        for (int y = 0; y < grid.length; ++y) {
            char[] row = grid[y];
            for (int x = 0; x < row.length; ++x) {
                switch (Tile.resolve(row[x])) {
                    case ENDPOINT:
                        if (firstEndpointTmp == null) firstEndpointTmp = new IntCoordinate(x, y);
                        else secondEndpointTmp = new IntCoordinate(x, y);
                        break;
                    case HORIZONTAL:
                    case VERTICAL:
                    case TURN:
                        IntCoordinate coordinate = new IntCoordinate(x, y);
                        pathTiles.add(coordinate);
                }
            }
        }
        if (firstEndpointTmp == null || secondEndpointTmp == null) {
            throw new IllegalArgumentException("Failed to find two endpoints");
        }
        firstEndpoint = firstEndpointTmp;
        secondEndpoint = secondEndpointTmp;
        everyWalkableTile = Collections.unmodifiableSet(pathTiles);
    }

    /**
     * @return the tile at the given coordinates or an empty tile if coordinates are out of bounds
     */
    public Tile get(int x, int y) {
        boolean coordinateIsInvalid = x < 0 || y < 0
                || y >= grid.length
                || x >= grid[y].length;
        char charToResolve = (coordinateIsInvalid) ? ' ' : grid[y][x];
        return Tile.resolve(charToResolve);
    }
}
