package com.codewars;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TermExtractor implements Iterator<TermExtractor.Term> {
    static final Pattern termPattern = Pattern.compile("[-+]\\d*x?(?:\\^\\d+)?");
    static final Pattern coefficientPattern = Pattern.compile("([+-]\\d+)");
    static final Pattern exponentPattern = Pattern.compile("x\\^(\\d+)");
    static final int DEFAULT_COEFFICIENT = 1;
    static final int DEFAULT_EXPONENT = 0;

    final Matcher termMatcher;
    final Matcher coefficientMatcher;
    final Matcher exponentMatcher;

    private boolean hasNext;
    private int currentTermLeftmostIndex;
    private int currentTermRightmostIndex;

    static class Term {
        final int coefficient;
        final int exponent;

        public Term(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }

        @Override
        public String toString() {
            String signedCoefficient = (coefficient >= 0) ? "+" + coefficient
                    : "" + coefficient;
            return signedCoefficient + "x^" + exponent;
        }
    }

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