/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aq615c
 */
public class WikiSyntaxParserLinks extends WikiSyntaxParserBase{
    @Override
    public String execute(String text)
    {
        Matcher m = link.matcher(text);
        StringBuffer sb = new StringBuffer();
        while(m.find())
        {
            String path = m.group(1);
            String name = "";
            String html = "";


            if(path.matches(WikiWord.validWikiPath.toString()))
            {
                WikiWord w = new WikiWord(path, WikiWord.current);
                name = (m.group(2) ==  null ? w.name() : m.group(2));
                html = "<a class='"+(w.cacheExists() ? "intexist" : "intmissing")+"' " +
                        "href='#' wiki='"+path+"'>"+name+"</a>";
                m.appendReplacement(sb, html);
            }
            else if(path.charAt(0) == '#')
            {
                name = (m.group(2) ==  null ? path.substring(1) : m.group(2));
                html = "<a href='"+path+"'>"+name+"</a>";
                m.appendReplacement(sb, html);
            }
            else if(path.matches(externalLink.pattern()))
            {
                name = (m.group(2) ==  null ? path : m.group(2));
                html = "<a class='external' href='"+path+"' " +
                        "target='_blank'>"+name+"</a>";
                m.appendReplacement(sb, html);
            }
            else if(path.matches(windowsLink.pattern()))
            {
                name = (m.group(2) ==  null ? path.replace("\\", "\\\\") : m.group(2));
                if(path.charAt(0) == '\\') // if the first char is \ we can assume a network path
                {
                    path = "file:" + path.replace("\\", "/");
                }
                else // we can assume this is a drive letter path as it doesn't start with \, e.g.: C:\
                {
                    path = "file:///" + path.replace("\\", "/");
                }

                html = "<a class='file' href='"+path+"' " +
                        "target='_blank'>"+name+"</a>";
                m.appendReplacement(sb, html);
            }
            else
            {
                // do nothing for now...
            }
        }
        m.appendTail(sb);

        return sb.toString();
    }

                                                  // [[anything(|something)?]]
    private static Pattern link = Pattern.compile("\\[\\[([^\\]\\|]+?)(?:\\|([^\\]]+?))?\\]\\]");
    private static Pattern externalLink = Pattern.compile("^(ht|f)(tps?|ile)\\:\\//[^><]*$");
    private static Pattern windowsLink = Pattern.compile("^(?:[a-zA-Z]\\:\\\\|\\\\\\\\)[^!<>:\"?*]+[A-Za-z0-9\\\\/]$");
}
