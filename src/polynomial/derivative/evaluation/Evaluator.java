package polynomial.derivative.evaluation;

import java.math.BigInteger;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

/**
 * Find a value of a derivative of a given expression at a certain point
 */
public class Evaluator {
    public static BigInteger differentiate(String polynomial, long x) {
        String normalized = normalize(polynomial);
        TermExtractor extractor = new TermExtractor(normalized);
        Spliterator<IntTerm> spliterator = Spliterators.spliteratorUnknownSize(extractor,
                Spliterator.NONNULL | Spliterator.IMMUTABLE);

        return StreamSupport.stream(spliterator, false)
                .map(Evaluator::findDerivative)
                .map(term -> evaluateForX(term, x))
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

    private static IntTerm findDerivative(IntTerm arg) {
        int newCoefficient = arg.coefficient * arg.exponent;
        int newExponent = (arg.exponent > 0) ? arg.exponent -1 : 0;
        return new IntTerm(newCoefficient, newExponent);
    }

    private static BigInteger evaluateForX(IntTerm term, long x) {
        BigInteger bigCoeff = new BigInteger(String.valueOf(term.coefficient));
        BigInteger bigX = new BigInteger(String.valueOf(x));
        BigInteger power = bigX.pow(term.exponent);
        return bigCoeff.multiply(power);
    }
}