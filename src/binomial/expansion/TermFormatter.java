package binomial.expansion;

import codewars.util.IntTerm;

import java.util.List;

/**
 * Build expanded expression and enforce the following formatting:
 * <ul>
 *     <li> If the coefficient of a term is zero, the term should not be included;
 *     <li> If the coefficient of a term is one, the coefficient should not be included;
 *     <li> If the coefficient of a term is -1, only the "-" should be included
 * <ul/>
 */
public class TermFormatter {
    private final StringBuilder expression = new StringBuilder();

    TermFormatter(List<IntTerm> terms) {
        for (IntTerm term : terms) {
            String strTerm = (term.coefficient == 0) ? ""
                    : (term.coefficient == 1) ? "x"    //FIXME actual term here
                    : (term.coefficient == -1) ? "-x"
                    : (term.coefficient > 0) ? "+" + term.coefficient + "x"
                    : term.coefficient + "x";
            expression.append(strTerm);
        }
    }
}
