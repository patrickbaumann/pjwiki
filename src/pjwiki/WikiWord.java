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
        init();

    }
    WikiWord(String rawString, WikiWord parent)
    {
        if(rawString.charAt(0) == DELIMITER)
        {
            input = rawString;
        }
        else if(!parent.isValid())
        {
            input = rawString;
        }
        else
        {
            List<String> parentPath = parent.getWikiPath();
            parentPath.remove(parentPath.size() - 1); // remove the current word of the parent
            Matcher m = relativeWikiPathFinder.matcher(rawString);
            if(m.matches())
            {
                for(int i = 1; i <= m.groupCount(); i++)
                {
                    String temp = m.group(i);
                    int groupsize = m.groupCount();
                    if(m.group(i) != null && m.group(i).contentEquals(".."+DELIMITER) && parentPath.size() > 0)
                    {
                        parentPath.remove(parentPath.size() - 1);
                    }
                }
                input = ""+ DELIMITER;
                for(String word : parentPath)
                {
                    input += word + DELIMITER;
                }
                input += m.group(m.groupCount());
            }
        }
        init();
    }

    void init()
    {
        wikiPath = new ArrayList<String>();
        Matcher m = validWikiPath.matcher(input);
        if(m.matches())
        {
            valid = true;
            String[] full = m.group(0).split(""+DELIMITER);
            for(String word:full)
            {
                if(word.length() > 1) wikiPath.add(word);
            }
            if(input.charAt(input.length()-1) == DELIMITER)
            {
                wikiPath.add(WikiWord.INDEX_TEXT);
            }
        }
        else
        {
            valid = false;
        }
    }

    private String input;
    private List<String> wikiPath;

    /**
     *
     * @return
     */
    public List<String> getWikiPath() {
        List<String> temp = new ArrayList<String>(wikiPath);
        return temp;
    }
    private boolean valid;

    /**
     *
     * @return
     */
    public boolean isValid() {
        return valid;
    }

    @Override
    public String toString()
    {
        String fullpath = "";
        for(String word : wikiPath)
        {
            fullpath += DELIMITER + word;
        }
        return fullpath;
    }

    /**
     *
     * @return
     */
    public String toFilePath(String seperator)
    {
        String filePath = "";
        for(String word : wikiPath)
        {
            filePath += seperator + word;
        }
        return filePath;
    }

    public String name()
    {
        return wikiPath.get(wikiPath.size()-1);
    }

    // caching
    public void cacheAdd()
    {
        if(!existingWords.contains(this)) existingWords.add(this);
    }
    public void cacheRemove()
    {
        if(existingWords.contains(this)) existingWords.remove(this);
    }
    public boolean cacheExists()
    {
        return existingWords.contains(this);
    }

    @Override
    public boolean equals(Object other)
    {
        return other != null && other.getClass() == WikiWord.class && ((WikiWord) other).toString().compareTo(this.toString()) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.wikiPath != null ? this.wikiPath.hashCode() : 0);
        return hash;
    }

    private static List<WikiWord> existingWords = new ArrayList<WikiWord>();

    public static char DELIMITER = ':';
    public static String INDEX_TEXT = "index";
    public static String validWord = "[a-zA-Z][-a-zA-Z0-9_ ]*[a-zA-Z0-9]";
    public static Pattern validWikiPath = Pattern.compile("^"+DELIMITER+"?("+validWord+DELIMITER+")*("+validWord+")"+DELIMITER+"?(?:#[A-Za-z0-9_-])?$");
    public static Pattern relativeWikiPathFinder = Pattern.compile("^([.]{1,2}"+DELIMITER+")*(.*)$");
    public static Pattern invalidCharFinder = Pattern.compile("[^-a-zA-Z0-9 ]");

    public static WikiWord current = new WikiWord(":");
}
