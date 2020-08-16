package line.pathfind.util;

import line.pathfind.Grid;
import line.pathfind.IntCoordinate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * !! READ package-info.java !!
 */
public class GridCoordinates {
    public final char[][] grid;
    public final IntCoordinate firstEndpoint, secondEndpoint;
    public final Set<IntCoordinate> everyWalkableTile;

    final Pattern endpointsPattern =
            Pattern.compile("(?<endpoint1>\\d+,\\d+) (?<endpoint2>\\d+,\\d+)");
    final Pattern walkablesPattern = Pattern.compile("\\d+,\\d+");

    public GridCoordinates(char[][] grid, String dataLine) {
        this.grid = grid;
        int splitIndex = dataLine.indexOf('S');

        Matcher endpointsMatcher = endpointsPattern.matcher(dataLine);
        endpointsMatcher.region(0, splitIndex);
        if (!endpointsMatcher.find()) {
            throw new IllegalArgumentException("Failed to parse endpoints coordinates");
        }
        String firstEndpointStr = endpointsMatcher.group("endpoint1");
        String secondEndpointStr = endpointsMatcher.group("endpoint2");
        firstEndpoint = parseCoordinate(firstEndpointStr);
        secondEndpoint = parseCoordinate(secondEndpointStr);

        Matcher walkablesMatcher = walkablesPattern.matcher(dataLine);
        walkablesMatcher.region(splitIndex, dataLine.length());
        Set<IntCoordinate> walkables = new HashSet<>();
        while (walkablesMatcher.find()) {
            String strCoordinate = walkablesMatcher.group();
            IntCoordinate intCoordinate = parseCoordinate(strCoordinate);
            walkables.add(intCoordinate);
        }
        everyWalkableTile = Collections.unmodifiableSet(walkables);
    }

    public GridCoordinates(char[][] grid, IntCoordinate firstEndpoint, IntCoordinate secondEndpoint,
                           Set<IntCoordinate> everyWalkableTile) {
        this.grid = grid;
        this.firstEndpoint = firstEndpoint;
        this.secondEndpoint = secondEndpoint;
        this.everyWalkableTile = everyWalkableTile;
    }

    static IntCoordinate parseCoordinate(String arg) {
        String[] arr = arg.split(",");
        int x = Integer.parseInt(arr[0]);
        int y = Integer.parseInt(arr[1]);
        return new IntCoordinate(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridCoordinates that = (GridCoordinates) o;
        return Arrays.equals(grid, that.grid) &&
                firstEndpoint.equals(that.firstEndpoint) &&
                secondEndpoint.equals(that.secondEndpoint) &&
                everyWalkableTile.equals(that.everyWalkableTile);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(firstEndpoint, secondEndpoint, everyWalkableTile);
        result = 31 * result + Arrays.hashCode(grid);
        return result;
    }

    public boolean hasSameCoordinatesAs(Grid otherGrid) {
        return this.firstEndpoint.equals(otherGrid.firstEndpoint)
                && this.secondEndpoint.equals(otherGrid.secondEndpoint)
                && this.everyWalkableTile.equals(otherGrid.everyWalkableTile);
    }
}
