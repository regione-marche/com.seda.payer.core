/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.captcha.CaptchaManager;
import com.seda.j2ee5.maf.core.action.ActionManager;
import com.seda.j2ee5.maf.core.action.ComplexActionTable;
import com.seda.j2ee5.maf.core.menu.MenuData;
import com.seda.j2ee5.maf.core.menu.MenuDataMap;
import com.seda.j2ee5.maf.core.menu.MenuItemData;
import com.seda.j2ee5.maf.core.screen.ScreenManager;
import com.seda.j2ee5.maf.core.security.RealmManager;
import com.seda.j2ee5.maf.core.security.RealmProfilesManager;
import com.seda.j2ee5.maf.core.security.RealmRolesManager;
import com.seda.j2ee5.maf.core.security.SecurityData;
import com.seda.j2ee5.maf.defender.repository.GlobalRuleSet;
import com.seda.j2ee5.maf.template.TemplateMap;
import com.seda.j2ee5.maf.template.TemplateRuntimeSupport;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author Seda Lab
 *
 */
public class ApplicationsData implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ApplicationsData.class);
	
	private Map<String, ApplicationData> applicationMap;
	
	private MenuDataMap menuDataMap;
	
	private SecurityData securityData;
	private RealmProfilesManager realmProfilesManager;
	private RealmRolesManager realmRolesManager;
	
	private String defaultConfigpath;
	private String defaultApplication;	
	private String defaultIgnoredExt;	
	private String defaultIgnoredSubcontext;	
	
	public ApplicationsData(String defaultConfigpath, String defaultApplication, String defaultIgnoredExt, String defaultIgnoredSubcontext, SecurityData securityData) {		
		this.applicationMap = Collections.synchronizedMap(new HashMap<String, ApplicationData>());
		this.defaultConfigpath=defaultConfigpath;
		this.defaultApplication=defaultApplication;
		this.defaultIgnoredExt=defaultIgnoredExt;
		this.defaultIgnoredSubcontext=defaultIgnoredSubcontext;
		this.securityData=securityData;
    }
	
	public String getDefaultConfigpath() {return defaultConfigpath;}
	public String getDefaultIgnoredExt() {return defaultIgnoredExt;}
	public String getDefaultIgnoredSubcontext() {return defaultIgnoredSubcontext;}
	public String getDefaultApplication() {return defaultApplication;}

	public SecurityData getSecurityData() {return securityData;}	

	public void addRealmProfilesManager(RealmProfilesManager realmProfilesManager) {
		this.realmProfilesManager=realmProfilesManager;
	}
	public RealmProfilesManager getRealmProfilesManager() {
		return realmProfilesManager;
	}	
	
	public void addRealmRolesManager(RealmRolesManager realmRolesManager) {
		this.realmRolesManager=realmRolesManager;
	}
	public RealmRolesManager getRealmRolesManager() {
		return realmRolesManager;
	}	
	
	public void addMenuDataMap(MenuDataMap menuDataMap) {this.menuDataMap=menuDataMap;}

	public ArrayList<MenuItemData> getMenu(HttpServletRequest request, String application, String profile) {
		if (menuDataMap!=null) {
			MenuData menuData = TemplateRuntimeSupport.resolveMenuApplicationData(menuDataMap, request, application);
			return menuData.getMenu(profile);			
		}
		return null;
	}
	public MenuDataMap getMenuDataMap() {
		return menuDataMap;
	}
	
	public void addApplication(String applicationName, ApplicationData application) {
        if (applicationMap.containsKey(applicationName)) {
        	applicationMap.remove(applicationName);
        }
        applicationMap.put(applicationName, application);
    }	
	
	public ApplicationData getApplication(String applicationName) {
        if (applicationMap.containsKey(applicationName)) {
            return applicationMap.get(applicationName);
        } else {
            logger.error(MAFLogger.getMessage("applications_data_not_found",applicationName));
           return null;
        }
    }
	
	public boolean containsApplication(String applicationName) {
        return applicationMap.containsKey(applicationName);
    }
	
	public Set<String> getApplicationsKeySet() {
		return applicationMap.keySet();
	}
	
	public Iterator<String> getApplicationsIterator() {
		Iterator<String> iterator = getApplicationsKeySet().iterator();
		return iterator;
	}
	
	public HashMap<String, MenuData> getApplicationsMenu() {
		HashMap<String, MenuData> applicationsMenu = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (applicationsMenu==null) applicationsMenu = new HashMap<String, MenuData>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	MenuData menuData = application.getMenu();
        	
        	applicationsMenu.put(applicationName, menuData);
        }    			
		return applicationsMenu;
	}	
	
	public HashMap<String, ComplexActionTable> getComplexActionTableMap() {
		return getComplexActionTableMap(true);
	}		
	
	public HashMap<String, ComplexActionTable> getComplexActionTableMap(boolean byname) {
		HashMap<String, ComplexActionTable> complexActionTableMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (complexActionTableMap==null) 
        		complexActionTableMap = new HashMap<String, ComplexActionTable>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String key;
        	if (byname) key = application.getName();
        	else key = application.getSubcontext();
       	
        	ComplexActionTable complexActionTable = application.getComplexActionTable();
        	
        	complexActionTableMap.put(key, complexActionTable);
        }    			
		return complexActionTableMap;
	}	
	
	public HashMap<String, ActionManager> getActionManagers() {
		HashMap<String, ActionManager> actionManagers = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (actionManagers==null) actionManagers = new HashMap<String, ActionManager>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	ActionManager actionManager = application.getActionManager();
        	
        	actionManagers.put(applicationName, actionManager);
        }    			
		return actionManagers;
	}
	
	public HashMap<String, ScreenManager> getScreenManagers() {
		HashMap<String, ScreenManager> screenManagers = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (screenManagers==null) screenManagers = new HashMap<String, ScreenManager>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	ScreenManager screenManager = application.getScreenManager();
        	
        	screenManagers.put(applicationName, screenManager);
        }    			
		return screenManagers;		
	}		
	
	public HashMap<String, CaptchaManager> getCaptchaManagers() {
		HashMap<String, CaptchaManager> captchaManagers = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (captchaManagers==null) captchaManagers = new HashMap<String, CaptchaManager>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	CaptchaManager captchaManager = application.getCaptchaManager();
        	
        	captchaManagers.put(applicationName, captchaManager);
        }    			
		return captchaManagers;
	}	
	
	public HashMap<String, TemplateMap> getTemplateMapping() {
    	HashMap<String, TemplateMap> templateMapping = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (templateMapping==null) templateMapping = new HashMap<String, TemplateMap>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	TemplateMap templateMap = application.getTemplateMap();
        	
        	templateMapping.put(applicationName, templateMap);
        }    			
		return templateMapping;
	}    
    
	public HashMap<String, String> getConfigpathMap() {
    	HashMap<String, String> configpathMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (configpathMap==null) configpathMap = new HashMap<String, String>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	String configpath = application.getConfigpath();
        	
        	configpathMap.put(applicationName, configpath);
        }    			
		return configpathMap;
	}
	
	public HashMap<String, String> getControllerServletMap() {
    	HashMap<String, String> controllerServletMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (controllerServletMap==null) controllerServletMap = new HashMap<String, String>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();
        	String controllerServlet = application.getControllerServlet();
        	if (application.hasControllerServlet()) {
            	controllerServletMap.put(applicationName, controllerServlet);        		
        	} else {
        		controllerServletMap.put(applicationName, null);
        	}
        }    			
		return controllerServletMap;
	} 	

	
	public HashMap<String, String> getSubcontextMap() {
    	HashMap<String, String> subcontextMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (subcontextMap==null) subcontextMap = new HashMap<String, String>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	String subcontext = application.getSubcontext();
        	
        	subcontextMap.put(subcontext, applicationName);
        }    			
		return subcontextMap;
	}        
    
	public HashMap<String, String> getApplContextMap() {
    	HashMap<String, String> applContextMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (applContextMap==null) applContextMap = new HashMap<String, String>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	String subcontext = application.getSubcontext();
        	
        	applContextMap.put(applicationName, subcontext);
        }    			
		return applContextMap;
	}
	
	public HashMap<String, String> getWelcomeMap() {
    	HashMap<String, String> welcomeMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (welcomeMap==null) welcomeMap = new HashMap<String, String>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	String welcome = application.getWelcomeaction();
        	
        	welcomeMap.put(applicationName, welcome);
        }    			
		return welcomeMap;
	}  	
	
	
	public HashMap<String, Boolean> getActivationMap() {
    	HashMap<String, Boolean> activationMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (activationMap==null) activationMap = new HashMap<String, Boolean>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	Boolean active = application.isActive();
        	
        	activationMap.put(applicationName, active);
        }    			
		return activationMap;
	} 	
	
	public HashMap<String, Boolean> getRememberProtectedURLMap() {
    	HashMap<String, Boolean> activationMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (activationMap==null) activationMap = new HashMap<String, Boolean>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	Boolean remember = application.isRememberProtectedURL();
        	
        	activationMap.put(applicationName, remember);
        }    			
		return activationMap;
	} 	
	
	public HashMap<String, Boolean> getProtectedApplicationsMap() {
    	HashMap<String, Boolean> protectedResourcesMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (protectedResourcesMap==null) protectedResourcesMap = new HashMap<String, Boolean>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();
        	protectedResourcesMap.put(applicationName, application.isProtected());

        }    			
		return protectedResourcesMap;
	} 		
	
	public HashMap<String, RealmManager> getRealmManagers() {
		HashMap<String, RealmManager> realmManagers = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (realmManagers==null) realmManagers = new HashMap<String, RealmManager>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	RealmManager realmManager = application.getRealmManager();
        	
        	realmManagers.put(applicationName, realmManager);
        }    			
		return realmManagers;
	}	
	
	public HashMap<String, String> getBinderClasses() {
    	HashMap<String, String> binderClasses = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (binderClasses==null) binderClasses = new HashMap<String, String>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	String binder = application.getBinderClass();
        	
        	binderClasses.put(applicationName, binder);
        }    			
		return binderClasses;
	}

	public HashMap<String, String> getSessionClasses() {
    	HashMap<String, String> sessionClasses = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (sessionClasses==null) sessionClasses = new HashMap<String, String>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationName = application.getName();        	
        	String handler = application.getSessionClass();
        	
        	sessionClasses.put(applicationName, handler);
        }    			
		return sessionClasses;
	}	
	
	public HashMap<String, GlobalRuleSet> getGlobalRuleSetMap() {
    	HashMap<String, GlobalRuleSet> globalRuleSetMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (globalRuleSetMap==null) globalRuleSetMap = new HashMap<String, GlobalRuleSet>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationContext = application.getSubcontext();        	
        	GlobalRuleSet globalRuleSet = application.getGlobalRuleSet();
        	globalRuleSetMap.put(applicationContext, globalRuleSet);
        }    			
		return globalRuleSetMap;
	}    
	
	public HashMap<String, ActionManager> getActionManagerMap() {
		HashMap<String, ActionManager> actionManagerMap = null;
		// loops through the application
        Iterator<String> iterator = getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {
        	if (actionManagerMap==null) actionManagerMap = new HashMap<String, ActionManager>();
        	
        	ApplicationData application = applicationMap.get(iterator.next());
        	String applicationContext = application.getSubcontext();        	
        	ActionManager actionManager = application.getActionManager();
        	
        	actionManagerMap.put(applicationContext, actionManager);
        }    			
		return actionManagerMap;
	}
	
	@Override
	public String toString() {
		return "ApplicationsData [applicationMap=" + applicationMap
				+ ", defaultApplication=" + defaultApplication
				+ ", defaultConfigpath=" + defaultConfigpath
				+ ", defaultIgnoredExt=" + defaultIgnoredExt + ", menuData="
				+ menuDataMap + ", realmProfilesManager=" + realmProfilesManager
				+ ", realmRolesManager=" + realmRolesManager
				+ ", securityData=" + securityData + "]";
	} 
		
	
}
