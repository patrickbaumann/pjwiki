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
        parsers.add(new WikiSyntaxParserLists());
        parsers.add(new WikiSyntaxParserTables());

        // TODO: WikiLinks Internal, External
        // TODO: Parser for macros
        // TODO: Wishlist: JRuby interpreter integration and ability to use ruby to add custom parsers
        // TODO: Images
        // TODO: Code, nowiki and html parsers (look into https://jhighlight.dev.java.net/ for code highlighting)
        // TODO: Forced newlines: \\<sp> => <br />
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
