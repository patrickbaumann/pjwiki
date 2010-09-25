/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Patrick
 */
public class WikiSyntaxManager {
    public WikiSyntaxManager()
    {
        parsers = new ArrayList<WikiSyntaxParser>();

        parsers.add(new WikiSyntaxParserHeaders());
        parsers.add(new WikiSyntaxParserFormatting());
    }

    public String format(String text)
    {
        ListIterator<WikiSyntaxParser> i = parsers.listIterator();

        while(i.hasNext())
        {
            text = i.next().prepare(text);
        }
        while(i.hasPrevious())
        {
            text = i.previous().execute(text);
        }
        return text;
    }

    private List<WikiSyntaxParser> parsers;
}
