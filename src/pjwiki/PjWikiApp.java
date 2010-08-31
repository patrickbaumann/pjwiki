/*
 * PjWikiApp.java
 */

package pjwiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class PjWikiApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new PjWikiView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of PjWikiApp
     */
    public static PjWikiApp getApplication() {
        return Application.getInstance(PjWikiApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(PjWikiApp.class, args);
    }

    static String wordFileExtension = ".pwk";
    static String backupFileExtension = ".bak";
    static String lockFileExtension = ".lock";

    private File dataPath;
    static Pattern dataPathPattern = Pattern.compile("^<datapath>([^<]+)</datapath>$");
    public void loadSettings(String settingsFile) throws FileNotFoundException, IOException, ParseException
    {
        BufferedReader in;
        String line;

        // Load target connections
        in = new BufferedReader( new FileReader(settingsFile));
        while(null != (line = in.readLine()))
        {
            Matcher m = dataPathPattern.matcher(line);
            if(m.matches())
            {
                dataPath = new File(m.group(1));
            }
        }
        in.close();

        if(dataPath == null)
        {
            ParseException e = new ParseException("", 0);
            throw e;
        }
        else if(!dataPath.isDirectory())
        {
            TypeNotPresentException e = new TypeNotPresentException("directory", new Throwable("path is not a directory"));
            throw e;
        }
    }

    public String getWikiWordText(WikiWord word)
    {
        String wordText = "";
        


        return wordText;
    }

    public boolean saveWikiWord(WikiWord word, String contents)
    {
        return false;
    }

    public boolean tryLock(WikiWord word)
    {
        return false;
    }
    public boolean isModifiable(WikiWord word, String username)
    {
        return false;
    }
    public boolean unlock(WikiWord word)
    {
        return true;
    }

}
