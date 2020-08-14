package line.pathfind;

import java.util.Collections;
import java.util.EnumSet;

/**
 * Keep track of directions and changes in coordinates which have to be made in order to move in a given direction.
 */
public enum Direction {
    UP(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),
    DOWN(0, 1);

    static final EnumSet<Direction>
            RIGHT_SET = immutable(EnumSet.of(RIGHT)),
            LEFT_SET = immutable(EnumSet.of(LEFT)),
            DOWN_SET = immutable(EnumSet.of(DOWN)),
            UP_SET = immutable(EnumSet.of(UP)),
            UP_DOWN_SET = immutable(EnumSet.of(UP, DOWN)),
            LEFT_RIGHT_SET = immutable(EnumSet.of(LEFT, RIGHT)),
            ALL_SET = immutable(EnumSet.allOf(Direction.class)),
            NONE_SET = immutable(EnumSet.noneOf(Direction.class));

    final int xShift, yShift;

    Direction(int xShift, int yShift) {
        this.xShift = xShift;
        this.yShift = yShift;
    }

    Direction opposite() {
        return (this == UP) ? DOWN
                : (this == RIGHT) ? LEFT
                : (this == LEFT) ? RIGHT
                : UP;
    }

    private static EnumSet<Direction> immutable(EnumSet<Direction> arg) {
        return (EnumSet<Direction>) Collections.unmodifiableSet(arg);
    }
}
