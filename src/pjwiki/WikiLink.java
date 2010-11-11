/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aq615c
 */
public class WikiLink{
    public WikiLink(){}
    public WikiLink(WikiWord fromword, WikiWord toword)
    { from = fromword; to = toword;}

    public WikiWord from;
    public WikiWord to;

    @Override
    public String toString()
    {
        return from.toString()+"->"+to.toString();
    }

    @Override
    public boolean equals(Object other)
    {
        return other != null && other.getClass() == WikiLink.class && other.toString().compareTo(this.toString()) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.from != null ? this.from.hashCode() : 0);
        hash = 79 * hash + (this.to != null ? this.to.hashCode() : 0);
        return hash;
    }

    private static Map<String, WikiLink> linkCache =
            new HashMap<String, WikiLink>();

    public void cacheAdd()
    {
        linkCache.put(this.toString(), this);
    }
    public boolean cacheRemove()
    {
        return null != linkCache.remove(this.toString());
    }
    public boolean cacheExist()
    {
        return linkCache.containsKey(this.toString());
    }
}