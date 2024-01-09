/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import java.io.Serializable;
import java.util.ArrayList;

import com.seda.j2ee5.maf.components.captcha.CaptchaManager;
import com.seda.j2ee5.maf.core.action.ActionManager;
import com.seda.j2ee5.maf.core.action.ComplexActionTable;
import com.seda.j2ee5.maf.core.menu.MenuData;
import com.seda.j2ee5.maf.core.menu.MenuItemData;
import com.seda.j2ee5.maf.core.screen.ScreenManager;
import com.seda.j2ee5.maf.template.TemplateScreenMap;
import com.seda.j2ee5.maf.template.TemplateMap;
import com.seda.j2ee5.maf.core.security.RealmManager;
import com.seda.j2ee5.maf.defender.repository.GlobalRuleSet;
/**
 * @author Seda Lab
 *
 */
public class ApplicationData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private boolean rememberProtectedURL;
	private String subcontext;
	private String configpath;	
	private String controllerServlet;
	private String welcomeaction;	
	private String starterClass;
	private String lifeCycleClass;
	private boolean startupActivation;
	private boolean isActive;
	private boolean isProtected;
	
	private RealmManager realmManager; 
	private String binderClass;
	
	private String sessionClass;
	
	private ActionManager actionManager;
	private CaptchaManager captchaManager;
	private MenuData menuData;	
	private ScreenManager screenManager;
	private TemplateMap templateMap;

	private GlobalRuleSet globalRuleSet;
	
	private ComplexActionTable complexActionTable;
	
	public ApplicationData(String name, String description, boolean rememberProtectedURL, 
			String subcontext, String configpath, 
			String controllerServlet,  			
			String welcomeaction, String starterClass, String lifeCycleClass, 
			boolean startupActivation, boolean isActive, boolean isProtected,
			RealmManager realmManager, String binderClass, String sessionClass,
			ActionManager actionManager, CaptchaManager captchaManager, MenuData menuData, ComplexActionTable complexActionTable, 
			ScreenManager screenManager, TemplateMap templateMap, GlobalRuleSet globalRuleSet) {
		
		this.name=name;
		this.description=description;
		this.rememberProtectedURL=rememberProtectedURL;
		
		this.subcontext=subcontext;
		this.welcomeaction=welcomeaction;
		
		this.configpath=configpath;
		
		this.controllerServlet=controllerServlet;
		
		this.starterClass=starterClass;
		this.lifeCycleClass=lifeCycleClass;

		this.startupActivation=startupActivation;
		this.isActive=isActive;		
		
		this.isProtected=isProtected;

		this.sessionClass=sessionClass;		
		
		this.realmManager=realmManager;
		this.binderClass=binderClass;
		
		this.actionManager=actionManager;
		this.captchaManager=captchaManager;
		this.menuData=menuData;
		this.complexActionTable=complexActionTable;
		this.screenManager=screenManager;
		this.templateMap=templateMap;
		
		this.globalRuleSet=globalRuleSet;
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	public boolean isRememberProtectedURL() {
		return rememberProtectedURL;
	}	
	public String getSubcontext() {
		return subcontext;
	}
	public String getControllerServlet() {
		return controllerServlet;
	}
	public String getWelcomeaction() {
		return welcomeaction;
	}
	public String getConfigpath() {
		return configpath;
	}
	public boolean hasControllerServlet() {
		return controllerServlet!=null;
	}
	public String getStarterClass() {
		return starterClass;
	}
	public String getLifeCycleClass() {
		return lifeCycleClass;
	}
	public boolean hasStarter() {
		return starterClass!=null;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public boolean setActive(boolean active) {
		return this.isActive=active;
	}		
	
	public boolean isProtected() {
		return isProtected;
	}
	
	public boolean startupActivation() {
		return startupActivation;
	}
	
	public RealmManager getRealmManager() {
		return realmManager;
	}
	
	public String getBinderClass() {
		return binderClass;
	}
	
	public String getSessionClass() {
		return sessionClass;
	}	
	
	public MenuData getMenu() {
		return menuData;
	}	
	
	public ArrayList<MenuItemData> getMenu(String key) {
		if (menuData!=null)
			return menuData.getMenu(key);
		return null;
	}
	
	public ComplexActionTable getComplexActionTable() {
		return complexActionTable;
	}	
	
	public ActionManager getActionManager() {
		return actionManager;
	}

	public CaptchaManager getCaptchaManager() {
		return captchaManager;
	}
	
	public ScreenManager getScreenManager() {
		return screenManager;
	}
	
	public TemplateMap getTemplateMap() {
		return templateMap;
	}

	
	public GlobalRuleSet getGlobalRuleSet() {
		return globalRuleSet;
	}

	@Override
	public String toString() {
		return "ApplicationData [name=" + name + ", description=" + description
				+ ", rememberProtectedURL=" + rememberProtectedURL
				+ ", subcontext=" + subcontext + ", configpath=" + configpath
				+ ", controllerServlet=" + controllerServlet
				+ ", welcomeaction=" + welcomeaction + ", starterClass="
				+ starterClass + ", lifeCycleClass=" + lifeCycleClass
				+ ", startupActivation=" + startupActivation + ", isActive="
				+ isActive + ", isProtected=" + isProtected + ", realmManager="
				+ realmManager + ", binderClass=" + binderClass
				+ ", sessionClass=" + sessionClass + ", actionManager="
				+ actionManager + ", menuData=" + menuData + ", screenManager="
				+ screenManager + ", templateMap=" + templateMap
				+ ", globalRuleSet=" + globalRuleSet + ", complexActionTable="
				+ complexActionTable + "]";
	}
	
}
