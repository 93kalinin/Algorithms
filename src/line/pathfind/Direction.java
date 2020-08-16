package line.pathfind;

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
            RIGHT_SET = EnumSet.of(RIGHT),
            LEFT_SET = EnumSet.of(LEFT),
            DOWN_SET = EnumSet.of(DOWN),
            UP_SET = EnumSet.of(UP),
            UP_DOWN_SET = EnumSet.of(UP, DOWN),
            LEFT_RIGHT_SET = EnumSet.of(LEFT, RIGHT),
            ALL_SET = EnumSet.allOf(Direction.class),
            NONE_SET = EnumSet.noneOf(Direction.class);

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
}
