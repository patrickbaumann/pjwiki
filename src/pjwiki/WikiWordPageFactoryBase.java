/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pjwiki;

import java.util.Map;

/**
 *
 * @author Patrick
 */
public abstract class WikiWordPageFactoryBase {
    
    public WikiWordPageFactoryBase(Map<String, String> options) throws Exception
    {   
        setOptions(options);
    }

    public abstract WikiWordPageBase getWikiWordPage(WikiWord word) throws Exception;
    public abstract WikiWordPageBase getWikiWordPage(String word) throws Exception;
    
    public Map<String, String> getOptions() {
        return options;
    }
    public void setOptions(Map<String, String> options) throws Exception
    {
        this.options = options;
        validateOptions();
    }
    
    public abstract void validateOptions() throws Exception;
    
    public Map<String, String> getDefaultOptions() {
        return defaultOptions;
    }
    protected Map<String, String> options;
    protected Map<String, String> defaultOptions;
}