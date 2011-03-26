/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki.syntax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Patrick
 */
public class WikiSyntaxParserFormatting extends WikiSyntaxParserBase{
    @Override
    public String execute(String text)
    {
        //BOLD
        Matcher matcher = bold.matcher(text);
        text = matcher.replaceAll(boldReplace);

        //ITALICS
        matcher.reset(text);
        matcher.usePattern(italics);
        text = matcher.replaceAll(italicsReplace);

        //underline
        matcher.reset(text);
        matcher.usePattern(underline);
        text = matcher.replaceAll(underlineReplace);

        //strike through
        matcher.reset(text);
        matcher.usePattern(strike);
        text = matcher.replaceAll(strikeReplace);

        //strike through
        matcher.reset(text);
        matcher.usePattern(monospace);
        text = matcher.replaceAll(monospaceReplace);

        matcher.reset(text);
        matcher.usePattern(linebreak);
        text = matcher.replaceAll(linebreakReplace);

        return text;
    }

    private static Pattern bold = Pattern.compile("\\*\\*(.+?)\\*\\*");
    private static String boldReplace = "<b>$1</b>";

    private static Pattern italics = Pattern.compile("//([^\\[\\]]+?)//");
    private static String italicsReplace = "<i>$1</i>";

    private static Pattern underline = Pattern.compile("__(.+?)__");
    private static String underlineReplace = "<u>$1</u>";

    private static Pattern strike = Pattern.compile("--(.+?)--");
    private static String strikeReplace = "<strike>$1</strike>";

    private static Pattern monospace = Pattern.compile("''(.+?)''", Pattern.DOTALL);
    private static String monospaceReplace = "<pre>$1</pre>";

    private static Pattern linebreak = Pattern.compile("\\\\\\\\\\s+");
    private static String linebreakReplace = "<br />";

}
