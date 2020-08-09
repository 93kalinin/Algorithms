package line.pathfind;

import java.util.EnumSet;

/** The task requires to navigate through a path which consists of three types of tiles with certain properties.
 * Keep track of those properties.
 */
public enum Tile {
    HORIZONTAL('-', Direction.NONE_SET, Direction.LEFT_SET, Direction.NONE_SET, Direction.RIGHT_SET),

    VERTICAL('|', Direction.DOWN_SET, Direction.NONE_SET, Direction.UP_SET, Direction.NONE_SET),

    TURN('+', Direction.LEFT_OR_RIGHT_SET, Direction.UP_OR_DOWN_SET,
            Direction.LEFT_OR_RIGHT_SET, Direction.UP_OR_DOWN_SET);

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
     */
    EnumSet<Direction> resolveNextStep(Tile standingOn, Direction cameFrom) {
        EnumSet<Direction> result = (cameFrom.equals(Direction.UP)) ? standingOn.ifCameFromUp
                : (cameFrom.equals(Direction.RIGHT)) ? standingOn.ifCameFromRight
                : (cameFrom.equals(Direction.DOWN)) ? standingOn.ifCameFromDown
                : standingOn.ifCameFromLeft;
        if (result.isEmpty()) {
            throw new IllegalStateException("The tile " + char_
                    + " cannot be entered from this direction " + cameFrom);
        }
        return result;
    }
}
