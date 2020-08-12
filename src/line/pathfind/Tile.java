package line.pathfind;

import java.util.EnumSet;

/**
 * The task requires to navigate through a path which consists of five types of tiles with certain properties.
 * Keep track of those properties.
 */
public enum Tile {
    HORIZONTAL('-', Direction.NONE_SET, Direction.LEFT_SET, Direction.NONE_SET, Direction.RIGHT_SET),

    VERTICAL('|', Direction.DOWN_SET, Direction.NONE_SET, Direction.UP_SET, Direction.NONE_SET),

    TURN('+', Direction.LEFT_OR_RIGHT_SET, Direction.UP_OR_DOWN_SET,
            Direction.LEFT_OR_RIGHT_SET, Direction.UP_OR_DOWN_SET),

    EMPTY(' ', Direction.NONE_SET, Direction.NONE_SET, Direction.NONE_SET, Direction.NONE_SET),

    ENDPOINT('X', Direction.ALL_SET, Direction.ALL_SET, Direction.ALL_SET, Direction.ALL_SET);

    private final char char_;
    private final EnumSet<Direction> ifCameFromUp, ifCameFromRight, ifCameFromDown, ifCameFromLeft;

    Tile(char char_, EnumSet<Direction> ifCameFromUp, EnumSet<Direction> ifCameFromRight,
         EnumSet<Direction> ifCameFromDown, EnumSet<Direction> ifCameFromLeft) {
        this.char_ = char_;
        this.ifCameFromUp = ifCameFromUp;
        this.ifCameFromRight = ifCameFromRight;
        this.ifCameFromDown = ifCameFromDown;
        this.ifCameFromLeft = ifCameFromLeft;
    }

    /** Return possible directions for the next step from the given tile type.
     * Take into consideration the direction you came from but not the tiles you have already visited
     * or the borders of the grid. Therefore, there is always at least one direction returned.
     * Will throw an exception if you came to the current tile from an impossible direction. <br><br>
     * Examples:
     * <ul>
     *     <li>standing on |, came from the top (UP) -> can only go DOWN;
     *     <li>standing on +, came from the left -> can either go UP or DOWN (you have to turn at '+');
     *     <li>standing on -, came from the bottom (DOWN) -> exception: that tile cannot be entered from the bottom;
     * </ul>
     * There are two endpoints in relation to any path -- start and finish. This method should never be
     * called on the finish point because it will yield an invalid result. Furthermore, resolving
     * the next step while the finish endpoint has been reached is pointless in this task. <br>
     * <br> @param if cameFrom is null, then default to LEFT;
     * <br> @param if standingOn is null, will throw NPE.
     */
    EnumSet<Direction> resolveNextStep(Direction cameFrom) {
        EnumSet<Direction> result = (cameFrom.equals(Direction.UP)) ? this.ifCameFromUp
                : (cameFrom.equals(Direction.RIGHT)) ? this.ifCameFromRight
                : (cameFrom.equals(Direction.DOWN)) ? this.ifCameFromDown
                : this.ifCameFromLeft;
        if (result.isEmpty()) {
            throw new IllegalStateException("The tile " + this.char_
                    + " cannot be entered from this direction " + cameFrom);
        }
        return result;
    }

    static Tile resolve(char tileChar) {
        for (Tile tile : Tile.values()) {
            if (tile.char_ == tileChar) {
                return tile;
            }
        }

        StringBuilder errorMessage = new StringBuilder("Cannot resolve the tile for the given character ");
        errorMessage.append(tileChar);
        errorMessage.append(System.lineSeparator());
        errorMessage.append("Only the following tiles are supported: ");
        errorMessage.append(System.lineSeparator());
        for (Tile tile : Tile.values()) {
            errorMessage.append(tile.toString())
                    .append(" ")
                    .append(tile.char_)
                    .append(System.lineSeparator());
        }
        throw new IllegalArgumentException(errorMessage.toString());
    }
}