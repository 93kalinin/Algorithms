/**
 * The task is as follows:<br>
 * Create a function that differentiates a polynomial for a given value of x.
 * Your function will receive 2 arguments: a polynomial as a string, and a point
 * to evaluate the equation as an integer.<br>
 * <br>
 * Assumptions:
 * <ul>
 *     <li>There will be a coefficient near each x, unless the coefficient equals 1 or -1.
 *     <li>There will be an exponent near each x, unless the exponent equals 0 or 1.
 *     <li>All exponents will be greater or equal to zero
 *     <li>Both exponents and coefficients are integers
 * </ul>
 * <br>
 * Examples:
 * <ul>
 *     <li>Evaluator.differentiate("12x+2", 3)      ==>   12
 *     <li>Evaluator.differentiate("x^2+3x+2", 3)   ==>   9
 * </ul>
 * <br>
 * @see <a href="https://www.codewars.com/kata/566584e3309db1b17d000027">kata at codewars.com</a>
 */
package polynomial.derivative.evaluation;