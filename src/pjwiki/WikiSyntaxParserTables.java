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
public class WikiSyntaxParserTables extends WikiSyntaxParser{
    @Override
    public String execute(String text)
    {
        String lines[] = text.split("[\r\n]+");

        boolean intable = false;
        for(int i = 0; i < lines.length ; i++)
        {
            if(lines[i].matches(tableLine.pattern()))
            {
                String lineReplacement = "";
                Matcher m = lineField.matcher(lines[i]);
                while(m.find())
                {
                    String tag = (m.group(1).contentEquals("^") ? "th" : "td" );
                    String align = null;
                    if(m.group(2).length() > 1 && m.group(4).length() > 1)
                    {    align = "center";}
                    else if(m.group(2).length() > 1)
                    {    align = "right";}
                    else if(m.group(4).length() > 1)
                    {    align = "left";}

                    lineReplacement +=
                            "    <"+tag+(align == null ? "" : " align='"+align+"'")+">"+
                            m.group(3)+"</"+tag+">\r\n";
                }

                lines[i] = rowTags[0] + lineReplacement + rowTags[1];

                if(!intable){ lines[i] = tableTags[0] + lines[i]; intable = true;}
            }
            else
            {
                if(intable) { lines[i] = tableTags[1] + lines[i]; intable = false;}
            }
        }
        if (intable) { lines[lines.length-1] += tableTags[1]; }
        return StringUtils.join(lines, "\r\n")+"\r\n";
    }

    private static Pattern tableLine = Pattern.compile("^([|^][^|^]+)+[|^]\\s*$");
    private static Pattern lineField = Pattern.compile("([|^])(\\s+)([^|^]*?[^\\s]?)(\\s+)(?=[|^])");

    private static String[] tableTags = {"<table>\r\n", "</table>\r\n"};
    private static String[] rowTags = {"  <tr>\r\n","  </tr>"};

}
