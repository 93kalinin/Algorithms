package binomial.expansion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Parse an expression in the form "(ax+b)^n" and put its constants a, b and n in a Constants instance */
class Parser {
    static final Pattern aPattern = Pattern.compile("^\\((\\d+)?\\w");
    static final Pattern bPattern = Pattern.compile("[+-](\\d+)\\)");
    static final Pattern nPattern = Pattern.compile("\\)(?:\\^(\\d+))?");

    private final Matcher aMatcher, bMatcher, nMatcher;

    Parser(String input) {
        this.aMatcher = aPattern.matcher(input);
        this.bMatcher = bPattern.matcher(input);
        this.nMatcher = nPattern.matcher(input);
    }

    Constants parse() {
        checkForParseFail(aMatcher.find(), bMatcher.find(), nMatcher.find());
        int a = Integer.parseInt(aMatcher.group(1));
        int b = Integer.parseInt(bMatcher.group(1));
        int n = Integer.parseInt(nMatcher.group(1));
        return new Constants(a, b, n);
    }

    private static void checkForParseFail(boolean aIsParsed, boolean bIsParsed, boolean nIsParsed) {
        if (aIsParsed && bIsParsed && nIsParsed) {
            return;
        }
        boolean multipleFailures = (!aIsParsed && !bIsParsed)
                || (!bIsParsed && !nIsParsed)
                || (!aIsParsed && !nIsParsed);
        String pluralEnding = (multipleFailures) ? "s" : "";
        String aFail = (aIsParsed) ? "" : "a ";
        String bFail = (bIsParsed) ? "" : "b ";
        String nFail = (nIsParsed) ? "" : "c ";

        throw new IllegalArgumentException("Parsing failed for the following constant" + pluralEnding + ": "
                + aFail + bFail + nFail + System.lineSeparator()
                + "An expression in the form (ax+b)^n is expected. a and b are integers, n is non-negative.");
    }
}