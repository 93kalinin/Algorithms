package line.pathfind;

public class Strings {
    public static char[][] stringArrayToCharArrays(String[] strings) {
        int columns = strings[0].length();
        for (String string : strings) {
            if (string.length() != columns) {
                throw new IllegalArgumentException("Lengths of all strings must be the same");
            }
        }

        int rows = strings.length;
        char[][] result = new char[rows][columns];
        for (int i = 0; i < rows; ++i) {
            result[i] = strings[i].toCharArray();
        }
        return result;
    }
}