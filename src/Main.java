import java.math.BigInteger;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import static java.lang.Math.pow;

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
            int newExponent = exponent -1;
            return new Term(newCoefficient, newExponent);
        }

        BigInteger evaluateForX(long x) {
            int value = (int) (coefficient * pow(x, exponent));
            String string = String.valueOf(value);
            return new BigInteger(string);
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