package line.pathfind;

import java.util.Objects;

public class IntCoordinate {
    public final int x, y;

    public IntCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    IntCoordinate getNeighbor(Direction direction) {
        int newX = this.x + direction.xShift;
        int newY = this.y + direction.yShift;
        return new IntCoordinate(newX, newY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntCoordinate that = (IntCoordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "IntCoordinate " +
                "x=" + x +
                " y=" + y;
    }
}
