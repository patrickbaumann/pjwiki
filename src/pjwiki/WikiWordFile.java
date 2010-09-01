package pjwiki;

import java.io.File;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrick
 */
public class WikiWordFile {

    private WikiWord word;

    static String wordFileExtension = ".pwk";
    static String backupFileExtension = ".bak";
    static String lockFileExtension = ".lock";

    private static File dataRoot;

    WikiWordFile(WikiWord word) throws Exception
    {
        this.word = word;
        validate();
    }
    WikiWordFile(String wikiWordString) throws Exception
    {
        this.word = new WikiWord(wikiWordString);
        validate();
    }

    /**
     *
     * @throws Exception
     */
    public void validate() throws Exception
    {
        Exception e = null;
        if(dataRoot == null)
        {
            e = new Exception("Root data path is not configured");
        }
        else if(word == null)
        {
            e = new Exception("A wiki page is attempting to be read without a WikiWord provided");
        }
        else if(!word.isValid())
        {
            e = new Exception("The WikiWord associated with this wikipage is invalid");
        }
        if(e != null) throw e;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String getWikiMarkup() throws Exception
    {
        validate();
        String wordText = "";


        return wordText;
    }

    /**
     *
     * @param contents
     * @return
     * @throws Exception
     */
    public boolean saveWikiMarkup(String contents) throws Exception
    {
        validate();
        return false;
    }

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public boolean tryLockFor(String username) throws Exception
    {
        validate();
        return false;
    }
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public boolean isModifiableFor(String username) throws Exception
    {
        validate();
        return false;
    }
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public boolean unlockFor(String username) throws Exception
    {
        validate();
        return true;
    }
    
    /**
     *
     * @return
     */
    public static File getDataRoot() {
        return dataRoot;
    }
    /**
     *
     * @param dataRoot
     * @throws Exception
     */
    public static void setDataRoot(File dataRoot) throws Exception {
        if(!dataRoot.isDirectory())
        {
            Exception e = new Exception("path is not a directory");
            throw e;
        }
        else
        {
            WikiWordFile.dataRoot = dataRoot;
        }
    }
}
