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
     * @param root
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
     * @param args
     */
    public static void main(String[] args) {
        launch(PjWikiApp.class, args);
    }

    static Pattern dataPathPattern = Pattern.compile("^<datapath>([^<]+)</datapath>$");
    /**
     * 
     * @param settingsFile
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void loadSettings(String settingsFile) throws FileNotFoundException, IOException, ParseException, Exception
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
                WikiWordFile.setDataRoot(new File(m.group(1)));
            }
        }
        in.close();

        if(WikiWordFile.getDataRoot() == null)
        {
            ParseException e = new ParseException("Settings file does not contain a datapath tag", 0);
            throw e;
        }
    }
}
