package pjwiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrick
 */
public class WikiWordPageFile extends WikiWordPageBase {
    
    WikiWordPageFile(WikiWord word, WikiWordPageFileFactory factory) throws Exception
    {
        super(word);
        this.factory = factory;
    }
    WikiWordPageFile(String word, WikiWordPageFileFactory factory) throws Exception
    {
        super(word);
        this.factory = factory;
    }

    public String getWikiMarkup()
    {
        String wordText = "";
        return wordText;
    }

    public boolean exists()
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
    public boolean tryLockFor(String username)
    {
        if(isModifiableFor(username))
        {
            try {
                writefile(path(factory.lockFileExtension),
                        username +
                        factory.lockFileDelimeter + "0" +
                        factory.lockFileDelimeter + "0"
                        );
            } catch (IOException ex) {
                return false;
            }
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
    public boolean isModifiableFor(String username)
    {
        File lockfile = path(factory.lockFileExtension);

        if(!lockfile.exists())
        {
            return true;
        }
        else
        {
            String lockContents;
            try {
                lockContents = readfile(lockfile);
                String[] lockColumns = lockContents.split("\\"+factory.lockFileDelimeter);
                // USER|DATE|TIME
                if(lockColumns[0].contentEquals(username))
                    return true;
                else
                    return false;
            } catch (Exception ex) {
                return false;
            }
        }
    }
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public boolean unlockFor(String username)
    {
        if(isModifiableFor(username))
        {
            path(factory.lockFileExtension).delete();
            return true;
        }
        else
        {
            return false;
        }
    }


    private File path(String extension)
    {
        if (extension == null) extension = factory.wordFileExtension;
        return new File(factory.dataRoot + word.toFilePath(File.separator) + extension);
    }
    private File path() {return path(null);}
            
    /**
     *
     * @param dataRoot
     * @throws Exception
     */

    private String readfile(File f) throws FileNotFoundException, IOException
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
    private void writefile(File f, String content) throws IOException
    {
        FileWriter fw = new FileWriter(f); // do not append
        fw.write(content);
        fw.close();
    }

    @Override
    public Integer[] getVersions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private WikiWordPageFileFactory factory;
}
