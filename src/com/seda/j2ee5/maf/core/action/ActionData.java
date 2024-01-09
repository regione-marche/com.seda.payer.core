/**
 * 
 */
package com.seda.j2ee5.maf.core.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.seda.j2ee5.maf.core.flow.FlowData;
import com.seda.j2ee5.maf.core.screen.ScreenRedirectData;
import com.seda.j2ee5.maf.core.screen.ScreenWriterData;
import com.seda.j2ee5.maf.defender.repository.RuleSet;
/**
 * @author Seda Lab
 *
 */
public class ActionData implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String applid;
	private String url;
	private String menuActionUrl;
    private boolean isAction = false;
    private String actionClass = null;
    private String screen;
    private String description;    
    private boolean enforceCsrf;
    
	private Properties actionSettings;    
    
    private boolean hasFlow = false;
    private String flowClass = null;
    private HashMap<String, FlowData> flowResultMap = null;
    private Properties flowProperties = null;    
    private Properties flowSettings = null;
    
    private ArrayList<String> roles = null;    
    
    private boolean useWriter = false;
    private ScreenWriterData writerData = null;  

    private boolean useRedirect = false;
    private ScreenRedirectData redirectData = null; 
    
    private RuleSet ruleSet=null;

    public ActionData(String applid,
    		String url,
    		String menuActionUrl,
    		String description,
    		ArrayList<String> roles,
    		String screen,
    		boolean isAction,
    		String actionClass,
    		boolean enforceCsrf,
    		Properties actionSettings,
    		boolean hasFlow,
    		String flowClass,
    		HashMap<String, FlowData> flowResultMap,
    		Properties flowProperties,
    		Properties flowSettings,
    		boolean useWriter,
    		ScreenWriterData writerData,
    		boolean useRedirect,
    		ScreenRedirectData redirectData,
    		RuleSet ruleSet) {
    	
    	this.applid = applid;
    	
        this.url = url;
        this.menuActionUrl = (menuActionUrl==null||menuActionUrl.trim().length()==0)?url:menuActionUrl;
        this.roles = roles;        
        this.actionClass = actionClass;
        this.isAction = isAction;
        this.screen = screen;
        this.enforceCsrf=enforceCsrf;
        this.actionSettings = actionSettings;
        
        this.hasFlow=hasFlow;
        this.flowClass=flowClass;
        this.flowResultMap=flowResultMap;
        this.flowProperties=flowProperties;
        this.flowSettings=flowSettings;
        
        this.useWriter=useWriter;
        this.writerData=writerData;
        
        this.useRedirect=useRedirect;
        this.redirectData=redirectData;
        
        this.ruleSet=ruleSet;
        
    }

    public ArrayList<String> getRoles() {
        return roles;
    }    
    
    public String getApplid() {
		return applid;
	}

    public String getUrl() {
		return url;
	}    
    
    public String getMenuActionUrl() {
		return menuActionUrl;
	}
    
	public String getDescription() {
        return description;
    }    
    
    public boolean isAction() {
        return isAction;
    }

    public String getActionClass() {
        return actionClass;
    }

    public boolean isEnforceCsrf() {
    	return enforceCsrf;
    }
    
    public Properties getActionSettings() {
        return actionSettings;
    }    
    
    public String getScreen() {
    	if (screen!=null)
			return screen.startsWith("/")?screen:"/"+screen;
        return screen;
    }

    public boolean hasFlow() {
    	return hasFlow;
    }
    
    public String getFlowClass() {
    	return flowClass;
    }
    public FlowData getResultScreen(String key) {
    	if (flowResultMap == null) {
            return null;
        }
        return flowResultMap.get(key);    	
    }

    public HashMap<String, FlowData> getFlowResultMap() {
        return flowResultMap;
    }    
    public Properties getFlowProperties() {
        return flowProperties;
    }
    public Properties getFlowSettings() {
        return flowSettings;
    }    

    public boolean useWriter() {
    	return useWriter;
    }
    public ScreenWriterData getWriterData() {
    	return writerData;
    }

    public boolean useRedirect() {
    	return useRedirect;
    }    
    public ScreenRedirectData getRedirectData() {
    	return redirectData;
    }
    public RuleSet getRuleSet() {
    	return ruleSet;
    }

	@Override
	public String toString() {
		return "ActionData [applid=" + applid + ", url=" + url
				+ ", actionClass=" + actionClass + ", menuActionUrl="
				+ menuActionUrl + ", isAction=" + isAction + ", screen="
				+ screen + ", description=" + description + ", enforceCsrf="
				+ enforceCsrf + ", actionSettings=" + actionSettings
				+ ", hasFlow=" + hasFlow + ", flowClass=" + flowClass
				+ ", flowResultMap=" + flowResultMap + ", flowProperties="
				+ flowProperties + ", flowSettings=" + flowSettings
				+ ", roles=" + roles + ", useWriter=" + useWriter
				+ ", writerData=" + writerData + ", useRedirect=" + useRedirect
				+ ", redirectData=" + redirectData + ", ruleSet=" + ruleSet
				+ "]";
	}

}