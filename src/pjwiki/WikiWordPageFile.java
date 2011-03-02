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
public class WikiWordPageFile extends WikiWordPageBase {
    
    WikiWordPageFile(WikiWord word) throws Exception
    {
        super(word);
    }
    WikiWordPageFile(String word) throws Exception
    {
        super(word);
    }

    static String wordFileExtension = ".pwk";
    static String backupFileExtension = ".bak";
    static String lockFileExtension = ".lock";
    static String lockFileDelimeter = "|";

    private static File dataRoot;

    @Override
    public void validate() throws Exception
    {
        super.validate();
        if(dataRoot == null)
        {
            throw new Exception("Root data path is not configured");
        }
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String getWikiMarkup() throws Exception
    {
        String wordText = "";
        return wordText;
    }

    public boolean exists() throws Exception
    {
        return path().exists();
    }

    public void save(String content) throws Exception
    {
        if(content == null) content = "";
        writefile(path(), content);
    }

    public String load() throws Exception
    {
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
            WikiWordPageFile.dataRoot = dataRoot;
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

    @Override
    public Integer[] getVersions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
