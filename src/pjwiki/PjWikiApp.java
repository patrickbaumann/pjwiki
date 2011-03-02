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
import javax.swing.JOptionPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

//TODO: add all syntax options to task list: http://www.dokuwiki.org/syntax

//TODO: Use sqlite3 or similar for link caching

/**
 * The main class of the application.
 */
public class PjWikiApp extends SingleFrameApplication {
 
    @Override protected void initialize(String[] args)
    {
        // set default options
        optionsPath = "./settings.conf";
        launchWithWikiWord = null;

        // parse command line arguments
        for(int i = 0; i < args.length; i++)
        {
            if((args[i].contentEquals("-o") || args[i].contentEquals("--options"))
                    && i+1 < args.length)
            {
                ++i;
                optionsPath = args[i];
            }
            else if((args[i].contentEquals("-w") || args[i].contentEquals("--word"))
                    && i+1 < args.length)
            {
                ++i;
                launchWithWikiWord = new WikiWord(args[i]);
            }
            else if((args[i].contentEquals("-u") || args[i].contentEquals("--username"))
                    && i+1 < args.length)
            {
                ++i;
                username = args[i];
            }

            // TODO: authentication system (dummy for now)
            if(username == null)
            username = System.getProperty("user.name");

        }
    }

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        try {
            loadSettings(optionsPath);
            show(new PjWikiView(this, launchWithWikiWord));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Launch issue: " + ex.getMessage());
            this.exit();
        }
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
                WikiWordPageFile.setDataRoot(new File(m.group(1)));
            }
            // TODO: load authentication driver setting
            // potential drivers:
                // plain text username / dropdown
                // System.getProperty("user.name")
                // oauth?
                // etc.
        }
        in.close();

        if(WikiWordPageFile.getDataRoot() == null)
        {
            ParseException e = new ParseException("Settings file does not contain a datapath tag", 0);
            throw e;
        }
    }

    private String optionsPath;
    private WikiWord launchWithWikiWord;

    private String username;
    public String getUsername() {
        return username;
    }
}
