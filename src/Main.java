import java.math.BigInteger;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

/**
 * The task is as follows:<br>
 * Create a function that differentiates a polynomial for a given value of x.
 * Your function will receive 2 arguments: a polynomial as a string, and a point
 * to evaluate the equation as an integer.<br>
 *
 * Assumptions:
 * <ul>
 * <li>There will be a coefficient near each x, unless the coefficient equals 1 or -1.
 * <li>There will be an exponent near each x, unless the exponent equals 0 or 1.
 * <li>All exponents will be greater or equal to zero
 * <li>Both exponents and coefficients are integers
 * </ul>
 *
 * Examples:
 * <ul>
 * <li>Main.differenatiate("12x+2", 3)      ==>   12
 * <li>Main.differenatiate("x^2+3x+2", 3)   ==>   9
 * </ul>
 *
 * @see <a href="https://www.codewars.com/kata/566584e3309db1b17d000027">Kata at codewars.com</a>
 * @author Mikhail Kalinin
 * @version 1.0
 */
public class Main {
    static final Pattern termPattern = Pattern.compile("[-+]\\d*x?(?:\\^\\d+)?");
    static final Pattern coefficientPattern = Pattern.compile("([+-]\\d+)");
    static final Pattern exponentPattern = Pattern.compile("x\\^(\\d+)");

    private static class Term {
        final int coefficient;
        final int exponent;

        public Term(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }

        Term findDerivative() {
            int newCoefficient = coefficient * exponent;
            int newExponent = (exponent > 0) ? exponent -1 : 0;
            return new Term(newCoefficient, newExponent);
        }

        BigInteger evaluateForX(long x) {
            BigInteger bigCoeff = new BigInteger(String.valueOf(coefficient));
            BigInteger bigX = new BigInteger(String.valueOf(x));
            BigInteger power = bigX.pow(exponent);
            return bigCoeff.multiply(power);
        }

        @Override
        public String toString() {
            String signedCoefficient = (coefficient >= 0) ? "+" + coefficient
                    : "" + coefficient;
            return signedCoefficient + "x^" + exponent;
        }
    }

    private static class TermExtractor implements Iterator<Term> {
        static final int DEFAULT_COEFFICIENT = 1;
        static final int DEFAULT_EXPONENT = 0;

        final Matcher termMatcher;
        final Matcher coefficientMatcher;
        final Matcher exponentMatcher;

        private boolean hasNext;
        private int currentTermLeftmostIndex;
        private int currentTermRightmostIndex;

        public TermExtractor(String polynomial) {
            this.termMatcher = termPattern.matcher(polynomial);
            this.coefficientMatcher = coefficientPattern.matcher(polynomial);
            this.exponentMatcher = exponentPattern.matcher(polynomial);
            tryAdvance();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public Term next() {
            if (!hasNext) {
                throw new IllegalStateException("This iterator is either empty or exhausted");
            }
            coefficientMatcher.region(currentTermLeftmostIndex, currentTermRightmostIndex);
            exponentMatcher.region(currentTermLeftmostIndex, currentTermRightmostIndex);
            int coefficient = (coefficientMatcher.find()) ? Integer.parseInt(coefficientMatcher.group(1))
                    : DEFAULT_COEFFICIENT;
            int exponent = (exponentMatcher.find()) ? Integer.parseInt(exponentMatcher.group(1))
                    : DEFAULT_EXPONENT;
            tryAdvance();
            return new Term(coefficient, exponent);
        }

        private void tryAdvance() {
            hasNext = termMatcher.find();
            if (hasNext) {
                currentTermLeftmostIndex = termMatcher.start();
                currentTermRightmostIndex = termMatcher.end();
            }
        }
    }

    public static BigInteger differentiate(String polynomial, long x) {
        String normalized = normalize(polynomial);
        System.out.println(normalized);
        TermExtractor extractor = new TermExtractor(normalized);
        Spliterator<Term> spliterator = Spliterators.spliteratorUnknownSize(extractor,
                Spliterator.NONNULL | Spliterator.IMMUTABLE | Spliterator.CONCURRENT);

        return StreamSupport.stream(spliterator, false)
                .map(Term::findDerivative)
                .map(term -> term.evaluateForX(x))
                .reduce(BigInteger::add)
                .orElse(BigInteger.ZERO);
    }

    /* first term is always signed; x always has an exponent and a coefficient */
    private static String normalize(String polynomial) {
        String result = (polynomial.charAt(0) == '-') ? polynomial
                : "+".concat(polynomial);
        return result.replaceAll("x(\\+|-|$)", "x^1$1")
                .replaceAll("([+-])x", "$11x");
    }
}