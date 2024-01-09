/**
 * 
 */
package com.seda.j2ee5.maf.template;

import java.io.Serializable;
import java.util.HashMap;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author Seda Lab
 *
 */
public class TemplateScreenMap implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(TemplateScreenMap.class);
	
	private HashMap<String, TemplateScreen> screenMap;
	private HashMap<String, String> templateMap;
	private String defaultTemplate;
	private String resourceBundle;
	private String name;
	private boolean defaultTemplateScreenMap;

    public TemplateScreenMap(String name, String defaultTemplate, String resourceBundle) {
    	this.name = name; 
        screenMap = new HashMap<String, TemplateScreen>();
        templateMap = new HashMap<String, String>();
        this.defaultTemplate = defaultTemplate;
        this.resourceBundle = resourceBundle;
    }
    
    public String getName() {
		return name;
	}

    public boolean isDefaultTemplateScreenMap() {
    	return defaultTemplateScreenMap;
    }
    
	public String getDefaultTemplate() {
        return defaultTemplate;
    }
    
    public String getResourceBundle() {
        return resourceBundle;
    }    

    public void setDefaultTemplateScreenMap(boolean defaultTemplateScreenMap) {
		this.defaultTemplateScreenMap = defaultTemplateScreenMap;
	}

	public void addScreen(String screenName, TemplateScreen screen) {
        if (screenMap.containsKey(screenName)) {
            screenMap.remove(screenName);
        }
        screenMap.put(screenName, screen);
    }

    public void addTemplate(String templateName, String templateURL) {
        if (templateMap.containsKey(templateName)) {
            templateMap.remove(templateName);
        }
        templateMap.put(templateName, templateURL);
    }

    public TemplateScreen getScreen(String screenName) {
    	if (screenMap.containsKey(screenName)) {
    		return screenMap.get(screenName);
    	} else {
    		logger.error(MAFLogger.getMessage("template_screens_templatescreen_not_found", name, screenName));        	
    		return null;
    	}
    }

    public boolean containsScreen(String screenName) {
        return screenMap.containsKey(screenName);
    }

    public boolean containsTemplate(String templateName) {
        return templateMap.containsKey(templateName);
    }

    public String getTemplate(String screenName) {
    	if (screenMap.containsKey(screenName)) {
    		String templateName = screenMap.get(screenName).getTemplate();
    		if ((templateName != null) && templateMap.containsKey(templateName)) {
    			return templateMap.get(templateName);
    		} else {
    			// return the default if template not defined for screen
    			return defaultTemplate;
    		}
    	} else {
    		logger.error(MAFLogger.getMessage("template_screens_templatescreen_not_found2", name, screenName));
    		return null;
    	}
    }

	@Override
	public String toString() {
		return "TemplateScreenMap [applid=" + name + ", defaultTemplate="
				+ defaultTemplate + ", resourceBundle=" + resourceBundle
				+ ", screenMap=" + screenMap + ", templateMap=" + templateMap
				+ "]";
	}

}
