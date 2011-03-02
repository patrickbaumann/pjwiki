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

    public abstract WikiWordPageBase getWikiWordPage(WikiWord word);
    public abstract WikiWordPageBase getWikiWordPage(String word);
    
    
    public Map<String, String> getOptions() {
        return options;
    }
    public void setOptions(Map<String, String> options) {
        this.options = options;
    }   
    public Map<String, String> getDefaultOptions() {
        return defaultOptions;
    }
    protected Map<String, String> options;
    protected Map<String, String> defaultOptions;

}
