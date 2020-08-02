package binomial.expansion;

import codewars.util.IntBinomial;

/** Perform binomial expansion e.g. "(p-1)^3" => "p^3-3p^2+3p-1" */
public class Expander {
    public static String expand(String expr) {
        StringBuilder expression = new StringBuilder();
        IntBinomial constants = new Parser(expr).parse();
        PascalTriangleRow row = new PascalTriangleRow(constants.exponent +1);

        int variableDegree = constants.exponent;
        int coefficient2Degree = 0;
        while (row.hasNext()) {
            int coefficient = (int) (row.nextInt() * Math.pow(constants.coeff2, coefficient2Degree));
            expression.append(coefficient);
            expression.append(constants.variableName);
            expression.append('^');
            expression.append(variableDegree);
        }

        return expression.toString();
    }
}