package binomial.expansion;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Perform binomial expansion e.g. "(p-1)^3" => "p^3-3p^2+3p-1"<br>
 * Build expanded expression and enforce the following formatting:
 * <ul>
 *   <li> If the coefficient of a term is zero, the term should not be included;
 *   <li> If the coefficient of a term is one, the coefficient should not be included;
 *   <li> If the coefficient of a term is -1, only the "-" should be included;
 *   <li> If the power of the term is 0, only the coefficient should be included;
 *   <li> If the power of the term is 1, the caret and power should be excluded.
 * <ul/>
 */
public class Expander {
    static final Pattern expressionPattern =
            Pattern.compile("\\((?<coeff1>[+-]?\\d+)(?<varName>\\p{Alpha})(?<coeff2>[+-]\\d+)\\)\\^(?<exp>\\+?\\d+)");

    public static String expand(String expr) {
        StringBuilder expression = new StringBuilder();
        IntBinomial constants = parse(expr);
        BigInteger coefficient1 = new BigInteger(String.valueOf(constants.coeff1));
        BigInteger coefficient2 = new BigInteger(String.valueOf(constants.coeff2));
        PascalTriangleRow row = new PascalTriangleRow(constants.exponent +1);

        int coefficient1Degree = constants.exponent;
        int coefficient2Degree = 0;
        while (row.hasNext()) {
            BigInteger nextPascalTriangleRowValue = new BigInteger(String.valueOf(row.nextInt()));
            BigInteger coefficient = nextPascalTriangleRowValue
                    .multiply(coefficient1.pow(coefficient1Degree))
                    .multiply(coefficient2.pow(coefficient2Degree));
            String formattedTerm = termFormat(constants.variableName, coefficient, coefficient1Degree);

            expression.append(formattedTerm);
            coefficient1Degree--;
            coefficient2Degree++;
        }
        String stringExpr = expression.toString();
        return (stringExpr.charAt(0) == '+') ? stringExpr.substring(1) : stringExpr;
    }

    private static String termFormat(String varName, BigInteger coefficient, int exponent) {
        if (coefficient.equals(BigInteger.ZERO)) return "";
        boolean coefficientIsPositive = coefficient.compareTo(BigInteger.ZERO) > 0;
        String signedCoefficient = (coefficientIsPositive) ? "+" + coefficient
                : coefficient.toString();
        if (exponent == 0) return signedCoefficient;
        BigInteger MINUS_ONE = new BigInteger("-1");
        String sign = (coefficientIsPositive) ? "+" : "-";
        String formattedCoefficient = (coefficient.equals(BigInteger.ONE) || coefficient.equals(MINUS_ONE)) ?
                sign : signedCoefficient;
        String formattedVariableAndExponent = (exponent == 1) ? varName
                : varName + "^" + exponent;
        return formattedCoefficient + formattedVariableAndExponent;
    }

    static IntBinomial parse(String input) {
        String norma = normalizeInput(input);
        Matcher matcher = expressionPattern.matcher(norma);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Failed to parse the expression." + System.lineSeparator()
                    + "An expression in the form (ax+b)^n is expected. Got " + input
                    + ". a, b and n should be integers; n is non-negative.");
        }
        String variableName = matcher.group("varName");

        int coeff1 = Integer.parseInt(matcher.group("coeff1"));
        int coeff2 = Integer.parseInt(matcher.group("coeff2"));
        int exponent = Integer.parseInt(matcher.group("exp"));
        return new IntBinomial(variableName, coeff1, coeff2, exponent);
    }

    /** Make sure that the variable has a coefficient, e.g. (-x+4)^2 => (-1x+4)^2 */
    private static String normalizeInput(String input) {
        return input.replaceFirst("\\(([-+])?(\\p{Alpha})", "($11$2");
    }
}