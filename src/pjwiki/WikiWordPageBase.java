/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Patrick
 */
public abstract class WikiWordPageBase {
    
    public WikiWordPageBase(WikiWord word) throws Exception
    {
        this.word = word;
        validate();
    }
    public WikiWordPageBase(String word) throws Exception
    {
        this.word = new WikiWord(word);
        validate();
    }
    
    protected WikiWord word;

    public WikiWord getWord() {
        return word;
    }
    
    public void validate() throws Exception
    {
        Exception e = null;
        if(word == null)
        {
            e = new Exception("A wiki page is attempting to be read without a WikiWord provided");
        }
        else if(!word.isValid())
        {
            e = new Exception("The WikiWord associated with this wikipage is invalid");
        }
        if(e != null) throw e;
    }

    public abstract boolean exists();

    public abstract void save(String content) throws Exception;

    public abstract String load() throws Exception;
    
    public abstract boolean tryLockFor(String username);

    public abstract boolean isModifiableFor(String username);

    public abstract boolean unlockFor(String username);
    
    public abstract Integer[] getVersions();
    
    protected Integer getNextVersion()
    {
        return Collections.max(Arrays.asList(getVersions())) + 1;
    }
    
}
