package binomial.expansion;

import codewars.util.IntBinomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Parse an expression in the form "(ax+b)^n" and put its constants a, b and n in a Constants instance */
class Parser {
    static final Pattern expressionPattern =
            Pattern.compile("\\((?<coeff1>[+-]?\\d+)(?<varName>\\p{Alpha})(?<coeff2>[+-]\\d+)\\)\\^(?<exp>\\+?\\d+)");

    private final Matcher matcher;

    Parser(String input) {
        String norma = normalizeInput(input);
        matcher = expressionPattern.matcher(norma);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Failed to parse the expression." + System.lineSeparator()
                    + "An expression in the form (ax+b)^n is expected. Got " + input
                    + ". a, b and n should be integers; n is non-negative.");
        }
    }

    IntBinomial parse() {
        char variableName = matcher.group("varName").charAt(0);
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