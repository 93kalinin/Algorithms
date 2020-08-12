package robo.script;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Highlight {
    public static String highlight(String code) {
        Pattern syntaxHighlightingTarget = Pattern.compile("(F+|L+|R+|\\d+)");
        Matcher matcher = syntaxHighlightingTarget.matcher(code);

        return matcher.replaceAll(match -> {
            char matchedSymbol = match.group().charAt(0);
            return (matchedSymbol == 'F') ? "<span style=\"color: pink\">$1</span>"
                    : (matchedSymbol == 'L') ? "<span style=\"color: red\">$1</span>"
                    : (matchedSymbol == 'R') ? "<span style=\"color: green\">$1</span>"
                    : "<span style=\"color: orange\">$1</span>";
        });
    }
}