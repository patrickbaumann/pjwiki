package pjwiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
    static String lockFileDelimeter = "|";

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

    public boolean exists() throws Exception
    {
        validate();
        return path().exists();
    }

    public void save(String content) throws Exception
    {
        validate();
        if(content == null) content = "";
        writefile(path(), content);
    }

    public String load() throws Exception
    {
        validate();
        if(exists())
        {
            return readfile(path());
        }
        else
        {
            return null;
        }
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
        if(isModifiableFor(username))
        {
            writefile(path(lockFileExtension),
                    username +
                    lockFileDelimeter + "0" +
                    lockFileDelimeter + "0"
                    );
            return true;
        }
        else
        {
            return false;
        }
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
        File lockfile = path(lockFileExtension);

        if(!lockfile.exists())
        {
            return true;
        }
        else
        {
            String lockContents = readfile(lockfile);
            String[] lockColumns = lockContents.split("\\"+lockFileDelimeter);
            // USER|DATE|TIME
            if(lockColumns[0].contentEquals(username))
                return true;
            else
                return false;
        }
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
        if(isModifiableFor(username))
        {
            path(lockFileExtension).delete();
            return true;
        }
        else
        {
            return false;
        }
    }


    private File path(String extension) throws Exception
    {
        validate();
        if (extension == null) extension = wordFileExtension;
        return new File(dataRoot + word.toFilePath(File.separator) + extension);
    }
    private File path() throws Exception{return path(null);}

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

    private String readfile(File f) throws Exception
    {
        BufferedReader in = new BufferedReader( new FileReader(f));
        String ret = "";
        String line;
        String lineend = System.getProperty("line.separator");
        while(null != (line = in.readLine()))
        {
            ret += line + lineend;
        }
        in.close();
        return ret;
        
    }
    private void writefile(File f, String content) throws Exception
    {
        FileWriter fw = new FileWriter(f); // do not append
        fw.write(content);
        fw.close();
    }
}
