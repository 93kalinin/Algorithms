package line.pathfind;

/**
 * Find the two endpoints marked with 'X' and ensure that boundaries of the grid will not be crossed.
 * Hides the array which represents the grid ank makes it immutable from the outside.
 */
public class Grid {
    private final char[][] grid;
    public final IntCoordinate firstEndpoint, secondEndpoint;

    public Grid(char[][] grid) {
        this.grid = grid;
        IntCoordinate firstEndpointTmp = null;
        IntCoordinate secondEndpointTmp = null;

        for (int y = 0; y < yLength(); ++y) {
            for (int x = 0; x < xLength(); ++x) {

                if (grid[y][x] == 'X') {
                    if (firstEndpointTmp == null) {
                        firstEndpointTmp = new IntCoordinate(x, y);
                    } else {
                        secondEndpointTmp = new IntCoordinate(x, y);
                    }
                }
            }
        }
        if (firstEndpointTmp == null || secondEndpointTmp == null) {
            throw new IllegalArgumentException("Failed to find two endpoints");
        }
        firstEndpoint = firstEndpointTmp;
        secondEndpoint = secondEndpointTmp;
    }

    /**
     * @return the tile at the given coordinates or an empty tile if coordinates are out of bounds
     */
    public Tile get(int x, int y) {
        boolean coordinateIsInvalid = x < 0 || y < 0
                || x >= xLength()
                || y >= yLength();
        char charToResolve = (coordinateIsInvalid) ? ' ' : grid[y][x];
        return Tile.resolve(charToResolve);
    }

    public int xLength() {
        return grid[0].length;
    }

    public int yLength() {
        return grid.length;
    }
}
