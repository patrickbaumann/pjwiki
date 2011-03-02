/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.io.File;
import java.util.Map;

/**
 *
 * @author Patrick
 */
public class WikiWordPageFileFactory extends WikiWordPageFactoryBase{

    public static final String wordFileExtension = ".pwk";
    public static final String backupFileExtension = ".bak";
    public static final String lockFileExtension = ".lock";
    public static final String lockFileDelimeter = "|";
    
    public static File dataRoot;    
    
    public WikiWordPageFileFactory(Map<String, String> options) throws Exception
    {
        super(options);
    }
    
    @Override
    public WikiWordPageBase getWikiWordPage(WikiWord word) throws Exception {
        return new WikiWordPageFile(word, this);
    }

    @Override
    public WikiWordPageBase getWikiWordPage(String word) throws Exception {
        return new WikiWordPageFile(word, this);
    }

    @Override
    public void validateOptions() throws Exception {
        if(this.options.containsKey("datapath"))
        {
            dataRoot = new File(this.options.get("datapath"));
            if(dataRoot.exists() && dataRoot.isDirectory())
            {
                return;
            }
            else if(dataRoot.mkdirs())
            {
                return;
            }
        }
        throw new Exception("dataroot not defined");
    }
    

}
