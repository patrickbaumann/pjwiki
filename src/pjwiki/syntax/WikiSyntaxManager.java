/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki.syntax;

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
        parsers = new ArrayList<WikiSyntaxParserBase>();

        parsers.add(new WikiSyntaxParserNoWiki());
        parsers.add(new WikiSyntaxParserLists());
        parsers.add(new WikiSyntaxParserLinks());
        parsers.add(new WikiSyntaxParserTables());
        parsers.add(new WikiSyntaxParserFormatting());
        parsers.add(new WikiSyntaxParserHeaders());

        // TODO: Parser for macros
        // TODO: Wishlist: JRuby interpreter integration and ability to use ruby to add custom parsers
        // TODO: Images
        // TODO: Code parser (look into https://jhighlight.dev.java.net/ for code highlighting)
    }

    public String format(String text)
    {
        ListIterator<WikiSyntaxParserBase> i = parsers.listIterator();

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

    private List<WikiSyntaxParserBase> parsers;
}
