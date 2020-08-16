/**
 * Grids are necessary to test classes from this package. They are read from text files. In those files they
 * are expected to be separated by a blank line and have a data line
 * right after them for supplementary data, e.g. to indicate whether they are walkable or not.
 * The data line may also be empty. For example, consider a file
 * <br><br><pre>
 *    +--------+
 * X--+        +--+
 *                |
 *                X
 * true
 *
 * X-----|----X
 * false
 * </pre>
 * We should be able to get instances of classes which contain both the grid itself and a boolean
 * value which indicates whether the grid is walkable or not. See GridReader javadoc for further info<br>
 */
package line.pathfind;