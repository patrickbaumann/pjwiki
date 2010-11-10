/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Patrick
 */
public class WikiSyntaxParserFormatting extends WikiSyntaxParser{
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

        return text;
    }

    private static Pattern bold = Pattern.compile("\\*\\*(.+?)\\*\\*");
    private static String boldReplace = "<b>$1</b>";

    private static Pattern italics = Pattern.compile("//(.+?)//");
    private static String italicsReplace = "<i>$1</i>";

    private static Pattern underline = Pattern.compile("__(.+?)__");
    private static String underlineReplace = "<u>$1</u>";

    private static Pattern strike = Pattern.compile("--(.+?)--");
    private static String strikeReplace = "<strike>$1</strike>";

    // TODO: ''monospaced'' => <pre>monospaced</pre>
    //private static Pattern stuff = Pattern.compile("");
    //private static Pattern strike = Pattern.compile("");

}
