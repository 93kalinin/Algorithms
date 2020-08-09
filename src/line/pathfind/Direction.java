package line.pathfind;

import java.util.Collections;
import java.util.EnumSet;

/**
 * Keep track of directions and changes in coordinates which have to be made in order to move in a given direction.
 */
public enum Direction { UP(0, -1), RIGHT(1, 0), LEFT(-1, 0), DOWN(0, 1);

    static final EnumSet<Direction> RIGHT_SET = (EnumSet<Direction>) Collections.unmodifiableSet(
            EnumSet.of(Direction.RIGHT));
    static final EnumSet<Direction> LEFT_SET = (EnumSet<Direction>) Collections.unmodifiableSet(
            EnumSet.of(Direction.LEFT));
    static final EnumSet<Direction> DOWN_SET = (EnumSet<Direction>) Collections.unmodifiableSet(
            EnumSet.of(Direction.DOWN));
    static final EnumSet<Direction> UP_SET = (EnumSet<Direction>) Collections.unmodifiableSet(
            EnumSet.of(Direction.UP));
    static final EnumSet<Direction> UP_OR_DOWN_SET = (EnumSet<Direction>) Collections.unmodifiableSet(
            EnumSet.of(Direction.UP, Direction.DOWN));
    static final EnumSet<Direction> LEFT_OR_RIGHT_SET = (EnumSet<Direction>) Collections.unmodifiableSet(
            EnumSet.of(Direction.LEFT, Direction.RIGHT));
    static final EnumSet<Direction> ANY_SET = (EnumSet<Direction>) Collections.unmodifiableSet(
            EnumSet.allOf(Direction.class));
    static final EnumSet<Direction> NONE_SET = (EnumSet<Direction>) Collections.unmodifiableSet(
            EnumSet.noneOf(Direction.class));

    int xShift, yShift;

    Direction(int xShift, int yShift) {
        this.xShift = xShift;
        this.yShift = yShift;
    }


}
