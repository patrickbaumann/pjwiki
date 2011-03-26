/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki.syntax;

import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author aq615c
 */
public class WikiSyntaxParserNoWiki extends WikiSyntaxParserBase {
    @Override
    public String prepare(String text)
    {
        captures = new Vector<String>();
        Matcher m = findNoWiki.matcher(text);
        StringBuffer sb = new StringBuffer();
        while(m.find())
        {
            captures.add(m.group(0));
            m.appendReplacement(sb, findNoWikiReplace.pattern());
        }
        m.appendTail(sb);
        String temp = sb.toString();
        return sb.toString();
    }

    @Override
    public String execute(String text)
    {
        Matcher m = findNoWikiReplace.matcher(text);
        StringBuffer sb = new StringBuffer();
        while(m.find())
        {
            m.appendReplacement(sb, captures.remove(0));
        }
        m.appendTail(sb);
        return sb.toString();
    }
    private List<String> captures;
    private static Pattern findNoWiki = Pattern.compile("<nowiki>(.*?)<\\/nowiki>", Pattern.DOTALL|Pattern.MULTILINE);
    private static Pattern findNoWikiReplace = Pattern.compile("<nowiki \\/>");

}
