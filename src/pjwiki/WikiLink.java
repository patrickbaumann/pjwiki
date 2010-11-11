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

    private static Map<String, WikiLink> linkCache =
            new HashMap<String, WikiLink>();
    public static void cachedLinkAdd(WikiWord from, WikiWord to)
    {
        WikiLink wl = new WikiLink(from, to);
        linkCache.put(wl.toString(), wl);
    }
    public static boolean cachedLinkRemove(WikiWord from, WikiWord to)
    {
        return null != linkCache.remove(
                (new WikiLink(from, to)).toString()
                );
    }
    public static boolean cachedLinkExist(WikiWord from, WikiWord to)
    {
        return linkCache.containsKey((new WikiLink(from, to)).toString());
    }
}