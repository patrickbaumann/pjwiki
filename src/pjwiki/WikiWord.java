/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Patrick
 */
public class WikiWord {

    WikiWord(String rawString)
    {
        input = rawString;
        wikiPath = new ArrayList<String>();
        Matcher m = validWikiPath.matcher(input);
        if(m.matches())
        {
            valid = true;
            for(int i = 1; i <= m.groupCount(); i++)
            {
                String wikiword = m.group(i);
                if(wikiword != null) wikiPath.add(wikiword);
            }
        }
        else
        {
            valid = false;
        }
    }
    WikiWord(String rawString, WikiWord parent)
    {
        // need to produce an absolute path based on the parent
        // e.g. :path:to = parent
        //      test = thisGuy
        // should end up with :path:test
    }

    private String input;
    private List<String> wikiPath;
    private boolean valid;

    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString()
    {
        String fullpath = "";
        for(String word : wikiPath)
        {
            fullpath += ":" + word;
        }
        return fullpath;
    }

    static String validWord = "([a-zA-Z][-a-zA-Z0-9_ ]*[a-zA-Z0-9])";
    static Pattern validWikiPath = Pattern.compile("^:?(?:"+validWord+":)*"+validWord+":?$");
}
