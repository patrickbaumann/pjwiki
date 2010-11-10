/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Patrick
 */
public class WikiSyntaxParserLists extends WikiSyntaxParserBase{
    @Override
    public String execute(String text)
    {
        String[] lines = text.split("[\r\n]+");
        int depth = 0;
        char currentTagType = '*';
        for(int i = 0; i < lines.length; i++)
        {
            Matcher m = listLine.matcher(lines[i]);
            if(m.find())
            {
                lines[i] = "";
                if(depth > 0 && m.group(2).charAt(0) != currentTagType)
                {
                    lines[i] += drawListItem(depth,0,null,listTagLookup(currentTagType));
                    depth = 0;
                    currentTagType = m.group(2).charAt(0);
                }
                int olddepth = depth;
                depth = m.group(1).length() / 2;
                String tagType = listTagLookup(currentTagType);
                lines[i] += drawListItem(olddepth,depth,m.group(3), tagType);
            }
            else if(depth > 0)
            {
                lines[i] = drawListItem(depth, 0, null, listTagLookup(currentTagType)) + lines[i];
            }
        }
        return StringUtils.join(lines, "\r\n")+"\r\n";
    }

    private static String drawListItem(int fromdepth, int todepth, String contents, String listTag)
    {
        String ret = "";

        while(todepth < fromdepth)
        {
            fromdepth--;
            ret += indent(fromdepth)+"</"+listTag+">\r\n";
        }
        while(todepth > fromdepth)
        {
            ret += indent(fromdepth)+"<"+listTag+">\r\n";
            fromdepth++;
        }
        if(contents != null)
        {
            ret += indent(fromdepth)+"<li>"+contents+"</li>";
        }

        return ret;
    }

    private static String indent(int depth)
    {
        String ret = "";
        for(int i = 0; i < depth; i++) {ret += "  ";}
        return ret;
    }

    private static String listTagLookup(char type)
    {
        return (type == '*' ? listTags[0] : listTags[1]);
    }

    private static Pattern listLine = Pattern.compile("^(\\s{2,})([*-]) (.*)$");
    private static String[] listTags = {"ul", "ol"};
}
