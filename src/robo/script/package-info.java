/**
 * Your MyRobot-specific (esoteric) scripting language called RoboScript only ever contains
 * the following characters: F, L, R, the digits 0-9 and brackets (( and )). Your goal is to write a function
 * highlight() which accepts 1 required argument code which is the RoboScript program passed in as a string and
 * returns the script with syntax highlighting. The following commands/characters should have the following colors:
 * <ul>
 *     <li> F - Wrap this command around {@code <span style="color: pink"> and </span>} tags so that it is highlighted pink in our editor
 *     <li> L - Wrap this command around {@code <span style="color: red"> and </span>} tags so that it is highlighted red in our editor
 *     <li> R - Wrap this command around {@code <span style="color: green"> and </span>} tags so that it is highlighted green in our editor
 *     <li> Digits from 0 through 9 - Wrap these around {@code <span style="color: orange"> and </span>} tags so that they are highlighted orange in our editor
 *     <li> Round Brackets - Do not apply any syntax highlighting to these characters
 * </ul>
 * <br>
 * @see <a href="https://www.codewars.com/kata/58708934a44cfccca60000c4">kata at codewars.com</a>
 */
package robo.script;