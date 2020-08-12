/**
 * You are given a grid, which always includes exactly two end-points indicated by X.
 * You simply need to return true/false if you can detect a one and only one "valid" line joining those points.
 * A line can have the following characters:
 * <ul>
 *     <li> - = left / right
 *     <li> | = up / down
 *     <li> + = corner
 * </ul>
 * Rules for valid lines
 * <ul>
 *     <li> The most basic kind of valid line is when the end-points are already adjacent
 *     <li> The corner character (+) must be used for all corners (but only for corners).
 *     <li> It must be possible to follow the line with no ambiguity (lookahead of just one step, and never treading on the same spot twice).
 *     <li> The line may take any path between the two points.
 *     <li> Sometimes a line may be valid in one direction but not the other. Such a line is still considered valid.
 *     <li> Every line "character" found in the grid must be part of the line. If extras are found then the line is not valid.
 * </ul>
 * @see <a href="https://www.codewars.com/kata/59c5d0b0a25c8c99ca000237">kata at codewars.com</a>
 * @author Mikhail Kalinin
 * @version 1.0
 */
package line.pathfind;