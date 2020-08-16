package line.pathfind.util;

import line.pathfind.IntCoordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class GridCoordinatesTest {
    @Test
    void parseSimple() {
        String dataLine = "0,0 11,0 S 1,0 2,0 3,0 4,0 5,0 6,0 7,0 8,0 9,0 10,0";
        String walkables = "1,0 2,0 3,0 4,0 5,0 6,0 7,0 8,0 9,0 10,0";
        IntCoordinate expectedFirstEndpoint = new IntCoordinate(0, 0);
        IntCoordinate expectedSecondEndpoint = new IntCoordinate(11, 0);
        Set<IntCoordinate> expectedWalkables = new HashSet<>();
        for (String coordinate : walkables.split(" ")) {
            IntCoordinate parsed = GridCoordinates.parseCoordinate(coordinate);
            expectedWalkables.add(parsed);
        }
        char[][] gridMock = new char[][] {};
        GridCoordinates expected =
                new GridCoordinates(gridMock, expectedFirstEndpoint, expectedSecondEndpoint, expectedWalkables);

        GridCoordinates actual = new GridCoordinates(gridMock, dataLine);

        Assertions.assertEquals(expected, actual);
    }
}
