/**
 * 
 */
package com.seda.j2ee5.maf.template;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Seda Lab
 *
 */
public class TemplateScreen implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String applid;
	private String name = null;
	private String menuActionUrl;	
    private String templateName = null;
    private HashMap<String, TemplateParameter> parameters;

    public TemplateScreen(String applid, String name, String menuActionUrl, HashMap<String, TemplateParameter> parameters) {
        this.applid = applid;
        this.name = name;
        this.menuActionUrl = (menuActionUrl==null||menuActionUrl.trim().length()==0)?name:menuActionUrl;
        this.parameters = parameters;
    }

    public TemplateScreen(String applid, String name, String menuActionUrl, String templateName,
                  HashMap<String, TemplateParameter> parameters) {
        this.applid = applid;    	
        this.name = name;
        this.menuActionUrl = (menuActionUrl==null||menuActionUrl.trim().length()==0)?name:menuActionUrl;        
        this.templateName = templateName;
        this.parameters = parameters;
    }

    public String getApplid() {
        return applid;
    }    
    
    public String getName() {
		return name;
	}        
    
    public String getMenuActionUrl() {
		return menuActionUrl;
	}    
    
    public String getTemplate() {
        return templateName;
    }

    public HashMap<String, TemplateParameter> getParameters() {
        return parameters;
    }

    public TemplateParameter getParameter(String key) {
        return parameters.get(key);
    }

	@Override
	public String toString() {
		return "TemplateScreen [applid=" + applid + ", menuActionUrl="
				+ menuActionUrl + ", name=" + name + ", parameters="
				+ parameters + ", templateName=" + templateName + "]";
	}

}
