/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.xml.XmlParserSupport;
import com.seda.j2ee5.maf.components.captcha.CaptchaManager;
import com.seda.j2ee5.maf.core.action.ActionData;
import com.seda.j2ee5.maf.core.action.ActionManager;
import com.seda.j2ee5.maf.core.action.ActionsXmlDao;
import com.seda.j2ee5.maf.core.action.ComplexActionTable;
import com.seda.j2ee5.maf.core.menu.MenuData;
import com.seda.j2ee5.maf.core.menu.MenuDataMap;
import com.seda.j2ee5.maf.core.menu.MenuItemData;
import com.seda.j2ee5.maf.core.screen.ExceptionManager;
import com.seda.j2ee5.maf.core.screen.ExceptionXmlDao;
import com.seda.j2ee5.maf.core.screen.ScreenManager;
import com.seda.j2ee5.maf.core.security.ProtectedApplication;
import com.seda.j2ee5.maf.core.security.ProtectedResource;
import com.seda.j2ee5.maf.core.security.RealmProfilesManager;
import com.seda.j2ee5.maf.core.security.RealmRolesManager;
import com.seda.j2ee5.maf.core.security.SecurityData;
import com.seda.j2ee5.maf.core.security.RealmManager;
import com.seda.j2ee5.maf.core.security.SecuritySSO;
import com.seda.j2ee5.maf.core.security.SecuritySignOn;
import com.seda.j2ee5.maf.core.security.SecurityXmlDao;
import com.seda.j2ee5.maf.core.security.SignOnRules;
import com.seda.j2ee5.maf.defender.repository.GlobalRuleSet;
import com.seda.j2ee5.maf.template.TemplateScreenMap;
import com.seda.j2ee5.maf.template.TemplateMap;
import com.seda.j2ee5.maf.template.TemplateXmlDao;
import com.seda.j2ee5.maf.util.MAFContext;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.URLUtil;
/**
 * 
 * 
 * @author Seda Lab
 *
 */
public class ApplicationsXmlDao extends XmlParserSupport {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ApplicationsXmlDao.class);
	
	public static final String APPLICATIONS_MAPPING = "applications-mapping";
	
	public static final String DEFAULT_CONFIG = "default";
	public static final String DEFAULT_CONFIG_PATH = "config-path";	
	public static final String DEFAULT_APPLICATION = "applid";	
	public static final String DEFAULT_IGNORED_EXT = "ignored-ext";	
	public static final String DEFAULT_IGNORED_SUBCONTEXT = "ignored-subcontext";	
	
	public static final String SECURITY = "security";
	public static final String SECURITY_ENABLED = "enabled";	
	public static final String LOGIN = "login";
	public static final String LOGIN_PAGE = "login-page";
	public static final String SECURITY_ERROR_PAGE = "security-error-page";
	public static final String LOGIN_SSO_CLASS = "sso-class";
	public static final String LOGIN_SIGNON_CLASS = "signon-class";
	public static final String AFTER_LOGIN = "after-login";	
	
	public static final String LOGIN_RULESET = "login-ruleset";
	public static final String LOGIN_USER_REGEX = "username-regex";
	public static final String LOGIN_PASS_REGEX = "password-regex";	
	public static final String LOGIN_ATTEMPTS = "attempts";
	public static final String LOGIN_LOCKED_TIMEOUT = "locked-timeout";
	public static final String LOGIN_MESSAGE_INVALID = "msg-invalid";
	public static final String LOGIN_MESSAGE_LOCKED = "msg-locked";
	public static final String LOGIN_MESSAGE_NOTFOUND = "msg-notfound";
	public static final String LOGIN_MESSAGE_NOTSIGNEDON = "msg-notsignedon";	
	public static final String LOGIN_MESSAGE_LOWPROFILE = "msg-lowprofile";
	public static final String LOGIN_MESSAGE_LOWROLE = "msg-lowrole";
	
	public static final String APPLICATION = "application";
	public static final String APPLICATION_PROFILE_COLLECTION = "profile-collection";
	public static final String APPLICATION_PROFILE = "profile";	
	public static final String APPLICATION_DESCRIPTION = "description";	
	public static final String APPLICATION_REMEBER_PROTECTED = "remember-protected-url";	
	public static final String APPLICATION_CONTEXT = "subcontext";
	public static final String APPLICATION_WELCOME = "welcome";	
	public static final String APPLICATION_CONFIG = "config-path";
	public static final String APPLICATION_TEMPLATE_COLLECTION = "template-collection";
	public static final String APPLICATION_TEMPLATE = "template";	
	public static final String APPLICATION_TEMPLATE_NAME = "name";	
	public static final String APPLICATION_CONTROLLER = "controller-servlet";
	public static final String APPLICATION_STARTER = "starter-class";
	public static final String APPLICATION_LIFECYCLE = "lifecycle-class";
	public static final String APPLICATION_BINDER_CLASS = "binder-class";
	public static final String APPLICATION_SESSION_CLASS = "session-class";	
	
	public static final String APPLICATION_CAPTCHA = "captcha";
	public static final String APPLICATION_CAPTCHA_URL = "captcha-url";		
	public static final String APPLICATION_CAPTCHA_CLASS = "captcha-class";	
    
	public static final String MENU_COLLECTION = "menu-collection";	
	public static final String MENU = "menu";
	public static final String MENU_TEMPLATE = "template";	
	public static final String MENU_LINK = "link";	
	public static final String MENU_LINK_TEXT = "text";	
	public static final String MENU_LINK_APPLID = "applid";	
	public static final String MENU_LINK_ENABLED = "enabled";	
	
	public static final String ACTIVATE = "activate";	
	public static final String PROTECTED = "protected";	
	public static final String NAME = "name";

	public static ApplicationsData loadApplications(String location, ServletContext context) {
        Element root = loadDocument(location);
        if (root!=null)
        	return getApplicationsData(root, context);
        else 
        	return null;
    }
	
	public static ApplicationsData loadApplications(URL location, ServletContext context) {
        Element root = loadDocument(location);
        if (root!=null)
        	return getApplicationsData(root, context);
        else 
        	return null;
    }		
	
	public static ApplicationsData getApplicationsData(Element root, ServletContext context) {
		String mafActions = context.getInitParameter(MAFContext.CONTEXT_maf_actions);
        if (mafActions==null || mafActions.trim().length()==0) {
        	mafActions=MAFContext.DEFAULT_maf_actions;
        } 		

        String mafMenu = context.getInitParameter(MAFContext.CONTEXT_maf_menu);
        if (mafMenu==null || mafMenu.trim().length()==0) {
        	mafMenu=MAFContext.DEFAULT_maf_menu;
        }         
        
		String mafTemplates = context.getInitParameter(MAFContext.CONTEXT_maf_templates);
        if (mafTemplates==null || mafTemplates.trim().length()==0) {
        	mafTemplates=MAFContext.DEFAULT_maf_templates;
        }
        
		String mafSecurity = context.getInitParameter(MAFContext.CONTEXT_maf_security);
        if (mafSecurity==null || mafSecurity.trim().length()==0) {
        	mafSecurity=MAFContext.DEFAULT_maf_security;
        } 		
        
		String mafExceptions = context.getInitParameter(MAFContext.CONTEXT_maf_exceptions);
        if (mafExceptions==null || mafExceptions.trim().length()==0) {
        	mafExceptions=MAFContext.DEFAULT_maf_exceptions;
        }
        
		// default configuration 
        NodeList defaultList = root.getElementsByTagName(DEFAULT_CONFIG);
        Node defaultNode = null;
        if (defaultList.getLength() >= 1) {
            defaultNode = defaultList.item(0);
        }
        if (defaultList.getLength() > 1) {
        	logger.error(MAFLogger.getMessage("applications_dao_only_error", DEFAULT_CONFIG));
        }             
        String defaultConfigpath= null;
        String defaultApplication=null;
        String defaultIgnoredExt=null;        
        String defaultIgnoredSubcontext=null;        
        if (defaultNode!=null) {
        	defaultConfigpath = getSubTagValue(defaultNode, DEFAULT_CONFIG_PATH);
        	defaultApplication = getSubTagValue(defaultNode, DEFAULT_APPLICATION);
        	defaultIgnoredExt = getSubTagValue(defaultNode, DEFAULT_IGNORED_EXT);        	
        	defaultIgnoredSubcontext = getSubTagValue(defaultNode, DEFAULT_IGNORED_SUBCONTEXT);        	
        } 
        if (defaultConfigpath == null) {
        	logger.error(MAFLogger.getMessage("applications_dao_default_not_defined", DEFAULT_CONFIG_PATH));        	
        	return null;
        }
        if (defaultApplication == null) {
        	logger.error(MAFLogger.getMessage("applications_dao_default_not_defined", DEFAULT_APPLICATION));        	
        	return null;
        }
        if (defaultIgnoredExt == null) {
        	defaultIgnoredExt="";
        	logger.warn(MAFLogger.getMessage("applications_dao_default_not_defined", DEFAULT_IGNORED_EXT));        	
        }
        if (defaultIgnoredSubcontext == null) {
        	defaultIgnoredSubcontext="";
        	logger.warn(MAFLogger.getMessage("applications_dao_default_not_defined", DEFAULT_IGNORED_SUBCONTEXT));
        }
        // Security implementation
        SecurityData securityData = loadSecurityData(root);
        // application configuration data object
        ApplicationsData applicationsData = new ApplicationsData(defaultConfigpath, defaultApplication, defaultIgnoredExt, defaultIgnoredSubcontext, securityData);
        //	Profiles realm manager
        HashMap<String, ProtectedApplication> realmProfiles = new HashMap<String, ProtectedApplication>(); 
        // Role realm manager 
        RealmRolesManager realmRolesManager = new RealmRolesManager();
        // get applications
        NodeList list = root.getElementsByTagName(APPLICATION);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if ((node != null) && node instanceof Element) {

            	String name = getNodeAttribute(node,NAME);
                if (name==null || name.length()<1 || name.equals("")) {
                	logger.error(MAFLogger.getMessage("applications_dao_application_not_defined", NAME));
                	return null;                	
                }
                
            	boolean startupActivation = true;
            	String activationString = getNodeAttribute(node,ACTIVATE);
            	if (activationString!=null)
            		startupActivation=Boolean.parseBoolean(activationString);
            	
            	ProtectedApplication protectedApplication=null;
            	boolean isProtected = false;
            	String isProtectedString = getNodeAttribute(node,PROTECTED);
            	if (isProtectedString!=null)
            		isProtected=Boolean.parseBoolean(isProtectedString);

            	// load the profile list of this application
            	ArrayList<String> profiles = loadProfiles((Element)node);
            	protectedApplication=new ProtectedApplication(name, profiles);
            	realmProfiles.put(name, protectedApplication);
            	// get the description
            	String description = getSubTagValue(node, APPLICATION_DESCRIPTION);
            	String remeberString = getSubTagValue(node, APPLICATION_REMEBER_PROTECTED);
            	boolean remember=Boolean.parseBoolean(remeberString);

            	String configpath = getSubTagValue(node, APPLICATION_CONFIG);
                if (configpath==null || configpath.length()<1 || configpath.equals("")) {
                	configpath=defaultConfigpath;
                	logger.debug(MAFLogger.getMessage("applications_dao_application_default", name,APPLICATION_CONFIG,defaultConfigpath));
                }            	
                List<TemplateItem> templateItems = loadTemplateItemList((Element)node,name,configpath);
                if (templateItems.size()>0) {
                	logger.debug("["+name+"] Template items loaded "+templateItems.size());
                }
                HashMap<String, TemplateScreenMap> templateCollection = new HashMap<String, TemplateScreenMap>(templateItems.isEmpty()?1:templateItems.size());
                
            	String subcontext = getSubTagValue(node, APPLICATION_CONTEXT);
                if (subcontext==null || subcontext.length()<1 || subcontext.equals("")) {
                	logger.debug(MAFLogger.getMessage("applications_dao_application_not_defined2", name,APPLICATION_CONTEXT));                	
                	return null;                	
                }

            	String welcome = getSubTagValue(node, APPLICATION_WELCOME);
                if (welcome==null || welcome.length()<1 || welcome.equals("")) {
                	logger.debug(MAFLogger.getMessage("applications_dao_application_not_defined2", name,APPLICATION_WELCOME));                	
                	return null;                	
                }                
                
                String controllerServlet=getSubTagValue(node, APPLICATION_CONTROLLER);
                	
                String starterClass=getSubTagValue(node, APPLICATION_STARTER);
                
                String lifecycleClass=getSubTagValue(node, APPLICATION_LIFECYCLE);
                
                String binderClass=getSubTagValue(node, APPLICATION_BINDER_CLASS);    
                
                String sessionClass=getSubTagValue(node, APPLICATION_SESSION_CLASS);                
                CaptchaManager captchaManager =  getCaptchaManager(name, (Element)node);
        		ActionManager actionManager=null;
        		ScreenManager screenManager = null;
        		MenuData menuData = null;
        		ComplexActionTable complexActionTable = null;

        		String actionsConfigUrl = URLUtil.urlRedirect(configpath, mafActions);
                HashMap<String, ActionData> actionsData = loadActionData(name, actionsConfigUrl, context); 
                boolean wfound=false; // welcome found
                if (actionsData!=null) {
                    // load action address table for complex target url
                    complexActionTable = loadComplexActionTable(name, URLUtil.urlRedirect(configpath, mafActions), actionsData, context);
                    if (complexActionTable == null) {
                    	// what can i do?
                    }
                    // define this action manager
                	actionManager = new ActionManager();
                	actionManager.init(actionsData, complexActionTable, captchaManager);
                	if (actionsData.containsKey(welcome)) wfound=true; // welcome found in the action mapping
                    // add the application actions roles to the roles manager
                    realmRolesManager.putRelamActionsData(name, actionsData);
                    // load this application menu action using the previous actions.xml data
                    menuData = loadActionMenuData(name, actionsConfigUrl, actionsData, 
                    		subcontext, context);                
                    if (menuData==null) {
                    	// what can i do?
                    } 

                    // load exception configuration
                    String exceptionsConfigUrl = URLUtil.urlRedirect(configpath, mafExceptions);
                    ExceptionManager exceptionManager = loadExceptionData(name, exceptionsConfigUrl, context);                
                    if (exceptionManager==null) {
                    	// what can i do?
                    } else {
                    	screenManager = new ScreenManager();
                    	screenManager.init(exceptionManager, actionsData, complexActionTable);
                    }
                    
                }
                
                String globalRuleSetConfigUrl = URLUtil.urlRedirect(configpath, mafActions);
                GlobalRuleSet globalRuleSet = loadGlobalRuleSet(name, globalRuleSetConfigUrl, context);
                boolean defaultfound =false;
                // load all templates in the template-collection
                if (!templateItems.isEmpty()) {
                	for (TemplateItem templateItem : templateItems) {
                        TemplateScreenMap templateScreenMap = null;
                        String templatesConfigUrl = URLUtil.urlRedirect(configpath, templateItem.configName);
                        templateScreenMap = loadTemplateScreenMap(name, templatesConfigUrl, context, templateItem.name);
                        if (templateScreenMap!=null && !wfound) {
                        	if (templateScreenMap.getScreen(welcome)!=null) wfound=true;
                        }
                        if (templateScreenMap!=null) {
                        	if (!defaultfound) {
                        		defaultfound=true;
                            	templateScreenMap.setDefaultTemplateScreenMap(true);                        		
                        	} else {
                        		templateScreenMap.setDefaultTemplateScreenMap(false);
                        	}
                        	templateCollection.put(templateItem.name, templateScreenMap);
                        }
					}
                } else {
                	TemplateScreenMap templateScreenMap = null;
                    String templatesConfigUrl = URLUtil.urlRedirect(configpath, mafTemplates);
                    templateScreenMap = loadTemplateScreenMap(name, templatesConfigUrl, context, "default");                
                    if (templateScreenMap!=null && !wfound) {
                    	if (templateScreenMap.getScreen(welcome)!=null) wfound=true;
                    }
                    if (templateScreenMap!=null) {
                    	templateScreenMap.setDefaultTemplateScreenMap(true);
                    	templateCollection.put("default", templateScreenMap);                    	
                    }
                }

                if (!wfound) {
                	// if the welcome was not found in the action mapping try to find it in the screen map
               		logger.error(MAFLogger.getMessage("applications_dao_welcome_not_found", name, welcome));
                  	// if an error occurred loop to the next application node
               		continue;                		
                }                    

                TemplateMap templateMap = new TemplateMap(name, templateCollection);
                if (templateMap.getDefaultTemplateScreenMap()==null && templateItems.size()>0) {
                	// set the first template as default if not set before
                	templateMap.getTemplateScreenMap(templateItems.get(0).name).setDefaultTemplateScreenMap(true);
                	logger.info("["+name+"] Template '"+ templateMap.getDefaultTemplateScreenMap().getName()+"', set to default");
                }
                
                RealmManager realmManager=null;
                String securityConfigUrl = URLUtil.urlRedirect(configpath, mafSecurity);
                HashMap<String, ProtectedResource> realmRoles = loadProtectedResources(name, securityConfigUrl, context); 
                if (realmRoles==null) {
                	// what can i do?
                } else {
                	realmManager = new RealmManager();
                	realmManager.init(realmRoles);
                }
                
                ApplicationData application = 
                	new ApplicationData(name, description, remember, subcontext, configpath, 
                			controllerServlet, welcome, starterClass, lifecycleClass, 
                			startupActivation, false, isProtected, 
                			realmManager, binderClass, sessionClass,
                			actionManager, captchaManager, menuData, complexActionTable, screenManager, templateMap, globalRuleSet);
                
                if (!applicationsData.containsApplication(name)) {
                	applicationsData.addApplication(name, application);
                	logger.debug(String.format("[%s] Application config: %s", name, application));
                } else {
                	logger.error(MAFLogger.getMessage("applications_dao_duplicate_application", name));
                }
            }
        }
        // check for at least one application
        if (applicationsData.getApplication(defaultApplication)==null) {
        	logger.error(MAFLogger.getMessage("applications_dao_default_applid_not_found", defaultApplication));        	
            applicationsData=null;
        }
        
        // load the profiles manager
        if (applicationsData != null) {
        	RealmProfilesManager realmProfilesManager = new RealmProfilesManager();
        	realmProfilesManager.init(realmProfiles);
       		applicationsData.addRealmProfilesManager(realmProfilesManager);
        }
        
        // load the roles manager
        if (applicationsData != null) {
       		applicationsData.addRealmRolesManager(realmRolesManager);
        }        
        
        // load the main menu information
        if (applicationsData != null) {
            MenuDataMap menuDataMap = loadMenuDataMap(root, applicationsData, context);
        	if (menuDataMap!=null) {
        		applicationsData.addMenuDataMap(menuDataMap);
        	}
        }
        
        // load the ApplcaitionBinderFactory
        if (applicationsData != null) {
        	ApplicationBinderFactory.instance().loadBinderMap(applicationsData.getBinderClasses());
        }
        
        return applicationsData;        
    }

	private static SecurityData loadSecurityData(Element root) {
        SecurityData securityData = new SecurityData();
        boolean enabled=false;
        String loginPage= null;
        String securityErrorPage=null;
        String signOnClass=null;
        String ssoClass=null;
        String afterLogin=null;    
        SignOnRules signOnRules=null;

        NodeList securityList = root.getElementsByTagName(SECURITY);
        Node securityNode = null;
        if (securityList.getLength() >= 1) {
            securityNode = securityList.item(0);
        }
        if (securityList.getLength() > 1) {
        	logger.error(MAFLogger.getMessage("applications_dao_only_error", SECURITY));
        }        
        
        if (securityNode!=null) {
        	enabled=true;
        	String enabledString = getNodeAttribute(securityNode,SECURITY_ENABLED);
        	if (enabledString!=null && !enabledString.equals("")) {
            	enabled = Boolean.parseBoolean(enabledString);        		
        	}
    		loginPage = getSubTagValue(securityNode, LOGIN_PAGE);
        	securityErrorPage = getSubTagValue(securityNode, SECURITY_ERROR_PAGE);
        	signOnClass=getSubTagValue(securityNode, LOGIN_SIGNON_CLASS);
        	ssoClass=getSubTagValue(securityNode, LOGIN_SSO_CLASS);        	
        	afterLogin=getSubTagValue(securityNode, AFTER_LOGIN);
        	signOnRules = getSignOnRules((Element)securityNode);
        	
        	if (enabled) {
            	if (signOnClass==null && ssoClass==null) {
            		enabled=false;
                	logger.error(MAFLogger.getMessage("applications_dao_sec_class_not_found"));
            	}
            	if (signOnClass==null && ssoClass!=null) {
            		logger.error(MAFLogger.getMessage("applications_dao_sec_soclass_not_found"));            		
            	}
            	if (signOnClass!=null) {
            		if (loginPage==null) {
            			if (ssoClass==null) {
            				enabled=false;
            				logger.error(MAFLogger.getMessage("applications_dao_sec_soclass_not_login"));
            			} else {
            				logger.warn(MAFLogger.getMessage("applications_dao_sec_soclass_only_sso"));            				
            			}
            		}
            	}
        	}
           	
           	if (securityErrorPage==null || securityErrorPage.equals("")) {
           		if (loginPage==null || loginPage.equals("")) {
           			enabled=false;
    				logger.error(MAFLogger.getMessage("applications_dao_sec_login_security_not_found"));           			
           		} else {
    				logger.error(MAFLogger.getMessage("applications_dao_sec_security_page_not_found"));
           		}
           	}            	

            if (afterLogin==null || afterLogin.equals("")) {
				logger.debug(MAFLogger.getMessage("applications_dao_sec_after_not_found"));
            }
        }
    	     
        securityData.setEnabled(enabled);        
        securityData.setLoginPage(loginPage);
        securityData.setSecurityErrorPage(securityErrorPage);
        securityData.setSignOnClass(checkSecurityClass(signOnClass,SecuritySignOn.class));
        securityData.setSsoClass(checkSecurityClass(ssoClass,SecuritySSO.class));        
        securityData.setAfterLogin(afterLogin);
        securityData.setSignOnRules(signOnRules);
        return securityData;
	}	
	
	private static String checkSecurityClass(String clazz,Class<?> zuper) {
		try {
    		if (clazz!=null && zuper!=null) {
    			if (!zuper.isAssignableFrom(Class.forName(clazz))) {
    				logger.error(String.format("Class '%s' does not extend %s",clazz,zuper));
    			}
    		}
		
		} catch (ClassNotFoundException e) {
			logger.error(String.format("Class '%s' not found in this classloader",clazz));
		}
		return clazz;
	}
	
	private static SignOnRules getSignOnRules(Element securityNode) {
		SignOnRules signOnRules=null;
		
		NodeList loginRulesetList = securityNode.getElementsByTagName(LOGIN_RULESET);
    	Node loginRulesetNode=null;
        if (loginRulesetList.getLength() >= 1) {
        	loginRulesetNode = loginRulesetList.item(0);
        }
        if (loginRulesetList.getLength() > 1) {
        	logger.error(MAFLogger.getMessage("applications_dao_only_error2",LOGIN_RULESET,SECURITY));
        }  
    	 
        if (loginRulesetNode!=null) {
        	int loginAttempts;
        	int loginLockedTimeout;
        	String loginAttemptsString = getSubTagValue(loginRulesetNode, LOGIN_ATTEMPTS);
        	try {
            	loginAttempts=Integer.parseInt(loginAttemptsString);
            } catch (NumberFormatException x) {
                loginAttempts=SignOnRules.ATTEMPTS;            	
            }
        	String loginLockedTimeoutString = getSubTagValue(loginRulesetNode, LOGIN_LOCKED_TIMEOUT);
            loginLockedTimeoutString=getSubTagValue(securityNode, LOGIN_LOCKED_TIMEOUT);
            try {
            	loginLockedTimeout=Integer.parseInt(loginLockedTimeoutString);
            } catch (NumberFormatException x) {
            	loginLockedTimeout=SignOnRules.LOCKED_TIMEOUT;            	
            }
            String pswRegex = getSubTagValue(loginRulesetNode, LOGIN_PASS_REGEX);
            String usrRegex = getSubTagValue(loginRulesetNode, LOGIN_USER_REGEX);
            String msginvalid = getSubTagValue(loginRulesetNode, LOGIN_MESSAGE_INVALID);
            String msglocked = getSubTagValue(loginRulesetNode, LOGIN_MESSAGE_LOCKED);
            String msgnotfound = getSubTagValue(loginRulesetNode, LOGIN_MESSAGE_NOTFOUND);
            String msgnotsignedon = getSubTagValue(loginRulesetNode, LOGIN_MESSAGE_NOTSIGNEDON);
            String msglowprofile = getSubTagValue(loginRulesetNode, LOGIN_MESSAGE_LOWPROFILE);
            String msglowrole = getSubTagValue(loginRulesetNode, LOGIN_MESSAGE_LOWROLE);            
            
            signOnRules = new SignOnRules(loginAttempts, loginLockedTimeout, usrRegex, pswRegex);
            if (msginvalid!=null && msginvalid.trim().length()>0) signOnRules.setInvalidMessage(msginvalid);
            if (msglocked!=null && msglocked.trim().length()>0) signOnRules.setLockedMessage(msglocked);
            if (msgnotfound!=null && msgnotfound.trim().length()>0) signOnRules.setNotfoundMessage(msgnotfound);
            if (msgnotsignedon!=null && msgnotsignedon.trim().length()>0) signOnRules.setNotSignedonMessage(msgnotsignedon);            
            if (msglowprofile!=null && msglowprofile.trim().length()>0) signOnRules.setLowProfileMessage(msglowprofile);
            if (msglowrole!=null && msglowrole.trim().length()>0) signOnRules.setLowRoleMessage(msglowrole);
        }
		return signOnRules;
	}

	private static List<TemplateItem> loadTemplateItemList(Element elementApplication, String name, String configpath) {
		List<TemplateItem> templateItemList = new ArrayList<TemplateItem>();
		
		NodeList templateCollectionList = elementApplication.getElementsByTagName(APPLICATION_TEMPLATE_COLLECTION);
    	Node templateCollectionNode=null;
        if (templateCollectionList.getLength() >= 1) {
        	templateCollectionNode = templateCollectionList.item(0);
        }
        if (templateCollectionList.getLength() > 1) {
        	logger.error(MAFLogger.getMessage("applications_dao_only_error2",APPLICATION_TEMPLATE_COLLECTION,APPLICATION));
        }  
    	 
        if (templateCollectionNode!=null) {
        	if (templateCollectionNode instanceof Element) {
        		Element templateCollectionElement = (Element)templateCollectionNode;
        		NodeList templateList = templateCollectionElement.getElementsByTagName(APPLICATION_TEMPLATE);
    			ApplicationsXmlDao xmlDao = new ApplicationsXmlDao();        		
        		for (int templateLoop = 0; templateLoop < templateList.getLength(); templateLoop++) {
            		Node templateNode = templateList.item(templateLoop);
            		String templateConfigName = getTagValue(templateNode);
            		String templateName = getElementAttribute((Element)templateNode, APPLICATION_TEMPLATE_NAME);
            		if ((templateConfigName != null) && templateConfigName.trim().length()>0
            				&& templateName !=null && templateName.trim().length()>0) {
            			templateItemList.add(xmlDao.new TemplateItem(templateName, templateConfigName));
            		}
            	}            		
        	}
        }
        return templateItemList;
	}
	private static CaptchaManager getCaptchaManager(String applid, Element elementApplication) {
		CaptchaManager captchaManager=null;
		NodeList captchaList = elementApplication.getElementsByTagName(APPLICATION_CAPTCHA);
    	Node captchaNode=null;
        if (captchaList.getLength() >= 1) {
        	captchaNode = captchaList.item(0);
        }
        if (captchaList.getLength() > 1) {
        	logger.error(MAFLogger.getMessage("applications_dao_only_error2",APPLICATION_CAPTCHA,APPLICATION));
        }  
    	String captchaClass=null;
    	String captchaURL=null;
    	
        if (captchaNode!=null) {
    		captchaClass=getSubTagValue(captchaNode, APPLICATION_CAPTCHA_CLASS);
    		captchaURL=getSubTagValue(captchaNode, APPLICATION_CAPTCHA_URL);
        }
        
        captchaManager=new CaptchaManager(applid, captchaURL, captchaClass);

        return captchaManager;
	}	
	private static ArrayList<String> loadProfiles(Element elementApplication) {
		ArrayList<String> profiles = new ArrayList<String>();
		
		NodeList profileCollectionList = elementApplication.getElementsByTagName(APPLICATION_PROFILE_COLLECTION);
    	Node profileCollectionNode=null;
        if (profileCollectionList.getLength() >= 1) {
        	profileCollectionNode = profileCollectionList.item(0);
        }
        if (profileCollectionList.getLength() > 1) {
        	logger.error(MAFLogger.getMessage("applications_dao_only_error2",APPLICATION_PROFILE_COLLECTION,APPLICATION));
        }  
    	 
        if (profileCollectionNode!=null) {
        	if (profileCollectionNode instanceof Element) {
        		Element profileCollectionElement = (Element)profileCollectionNode;
        		NodeList profilesList = profileCollectionElement.getElementsByTagName(APPLICATION_PROFILE);
        		for (int profilesLoop = 0; profilesLoop < profilesList.getLength(); profilesLoop++) {
            		Node profileNode = profilesList.item(profilesLoop);
            		String profileName = getTagValue(profileNode);
            		if ((profileName != null) && !profileName.equals("")) 
            			profiles.add(profileName);
            	}            		
        	}
        }
        return profiles;
	}
	
	private static MenuDataMap loadMenuDataMap(Element root, ApplicationsData applicationsData, ServletContext context) {
		MenuDataMap menuDataMap = new MenuDataMap();
		String guestProfile = context.getInitParameter(MAFContext.CONTEXT_GUEST);
		if (guestProfile==null || guestProfile.trim().length()==0) {
			guestProfile=MAFContext.DEFAULT_GUEST;
		} else {
			guestProfile=guestProfile.trim();
		}		
		NodeList menuCollectionList = root.getElementsByTagName(MENU_COLLECTION);
        Node menuCollectionNode = null;
        if (menuCollectionList.getLength() >= 1) {
        	menuCollectionNode = menuCollectionList.item(0);
        }
        if (menuCollectionList.getLength() > 1) {
        	logger.error(MAFLogger.getMessage("applications_dao_only_error",MENU_COLLECTION));
        }

        if (menuCollectionNode!=null && menuCollectionNode instanceof Element) {
        	boolean defaultfound =false;        	
        	Element menuCollectionElement = (Element) menuCollectionNode;
        	NodeList menuList = menuCollectionElement.getElementsByTagName(MENU);
        	for (int menuLoop = 0; menuLoop < menuList.getLength(); menuLoop++) {
            	Element elementMenu = (Element)menuList.item(menuLoop);
        		String template=getElementAttribute(elementMenu, MENU_TEMPLATE);
        		MenuData menuData=loadMenuData(elementMenu, applicationsData, context, guestProfile);
        		if (!defaultfound) {
        			menuData.setDefaultMenuData(true);
        			defaultfound=true;
        		}
        		menuDataMap.addMenuData(template, menuData);
        	}
        }
        return menuDataMap;
	}
	
	private static MenuData loadMenuData(Element menuElement, ApplicationsData applicationsData, ServletContext context, String guestProfile) {
		MenuData menuData = new MenuData();
//		String guestProfile = context.getInitParameter(MAFContext.CONTEXT_GUEST);
//		if (guestProfile==null || guestProfile.trim().length()==0) {
//			guestProfile=MAFContext.DEFAULT_GUEST;
//		} else {
//			guestProfile=guestProfile.trim();
//		}				
		ArrayList<MenuItemData> guestMenuItems = new ArrayList<MenuItemData>();
		
//        NodeList menuList = root.getElementsByTagName(MENU);
//        Node menuNode = null;
//        if (menuList.getLength() >= 1) {
//        	menuNode = menuList.item(0);
//        }
//        if (menuList.getLength() > 1) {
//        	logger.error(MAFLogger.getMessage("applications_dao_only_error",MENU));
//        }        
        
//        if (menuNode!=null && menuNode instanceof Element) {
//        	Element menuElement = (Element) menuNode;
        	NodeList linkList = menuElement.getElementsByTagName(MENU_LINK);
        	for (int linkLoop = 0; linkLoop < linkList.getLength(); linkLoop++) {
            	Element elementLink = (Element)linkList.item(linkLoop);
        		String text=getElementAttribute(elementLink, MENU_LINK_TEXT);
        		String applid=getElementAttribute(elementLink, MENU_LINK_APPLID);
        		boolean enabled = true;
        		String enabledString=getElementAttribute(elementLink, MENU_LINK_ENABLED);
        		if (enabledString!=null && enabledString.trim().length()>0)
        			enabled = Boolean.parseBoolean(enabledString);
        		
        		ApplicationData application = applicationsData.getApplication(applid); 
        		if (application==null) {
        			logger.error(MAFLogger.getMessage("applications_dao_menu_applid_not_found",applid,text));
        			continue;
        		}
        		String subcontext = application.getSubcontext();
        		String title = application.getDescription();
        		String url = URLUtil.setBothSlash(subcontext);
        		
        		MenuItemData menuItemData = new MenuItemData(url, text, title, enabled, applid, null);
        		
        		ArrayList<String> profiles = applicationsData.getRealmProfilesManager().getProfiles(applid);
        		
        		if (profiles!=null && profiles.size()>0) {
        			// loop through the profiles and add the item menu for each profile
        			for (Iterator<String> iterator = profiles.iterator(); iterator.hasNext();) {
						String profile = iterator.next();
						// if the profile match the guest string add it to the guest profile
						if (profile.equals(guestProfile)) {
							guestMenuItems.add(menuItemData);
						} else {
							menuData.putMenuItem(profile,menuItemData);							
						}
					}
        		} else {
        			// loop through all profile in the menuData
        			Set<String> menuDataSet = menuData.getMenuMap().keySet();
        			for (Iterator<String> iterator = menuDataSet.iterator(); iterator.hasNext();) {
						String profile = iterator.next();
						menuData.putMenuItem(profile,menuItemData);
					}
        			// add it to the guest menu
        			guestMenuItems.add(menuItemData);
        		}
        	} // end loop link
        	menuData.putMenu(guestProfile, guestMenuItems);
//        }
        return menuData;
	}		

	private static HashMap<String, ActionData> loadActionData(String name, String configpath, 
			ServletContext context) {
		HashMap<String, ActionData> actionsData = null;		
		URL actionsResourceURL = null;
        try {
        	actionsResourceURL = context.getResource(configpath);
        } catch (java.net.MalformedURLException ex) {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_malformed",name),ex);
        }
        if (actionsResourceURL!=null) {
            actionsData=ActionsXmlDao.loadActions(actionsResourceURL, name);
    		if (actionsData == null) {
    			logger.error(MAFLogger.getMessage("applications_dao_application_url_confirm",name,configpath));    			
    		}		                    
        } else {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_not_found",name,configpath));        	
		}

        return actionsData;
	}		

	private static MenuData loadActionMenuData(String name,
			String configpath, HashMap<String, ActionData> actionsData,
			String subcontext, ServletContext context) {

		MenuData menuData = null;
		URL actionsResourceURL = null;
		try {
			actionsResourceURL = context.getResource(configpath);
		} catch (java.net.MalformedURLException ex) {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_malformed",name),ex);
		}
		if (actionsResourceURL!=null) {
			menuData = ActionsXmlDao.loadActionMenu(actionsResourceURL, subcontext, actionsData, context, name);
			if (menuData == null) {
    			logger.error(MAFLogger.getMessage("applications_dao_application_url_confirm",name,configpath));
			}			
		} else {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_not_found",name,configpath));
		}
	
		return menuData;
	}	
	
	private static ComplexActionTable loadComplexActionTable(String name, String configpath, HashMap<String,ActionData> actionsData, ServletContext context) {
		ComplexActionTable complexTable = null;
		
		URL actionResourceURL = null;
		try {
			actionResourceURL = context.getResource(configpath);
		} catch (java.net.MalformedURLException ex) {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_malformed",name),ex);
		}
		if (actionResourceURL!=null) {
			complexTable = ActionsXmlDao.loadComplexActionTable(actionResourceURL, actionsData, name);
			if (complexTable == null) {
    			logger.error(MAFLogger.getMessage("applications_dao_application_url_confirm",name,configpath));
			}			
		} else {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_not_found",name,configpath));
		}
		
		return complexTable;
	}
	
	private static GlobalRuleSet loadGlobalRuleSet(String name, String configpath, ServletContext context) {
		GlobalRuleSet globalRuleSet = null;
		
		URL globalRuleSetResourceURL = null;
		try {
			globalRuleSetResourceURL = context.getResource(configpath);
		} catch (java.net.MalformedURLException ex) {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_malformed",name),ex);
		}
		if (globalRuleSetResourceURL!=null) {
			globalRuleSet = ActionsXmlDao.loadGlobalRuleSet(globalRuleSetResourceURL, name);
			if (globalRuleSet == null) {
    			logger.error(MAFLogger.getMessage("applications_dao_application_url_confirm",name,configpath));
			}			
		} else {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_not_found",name,configpath));
		}
		
		return globalRuleSet;
	}
	private static ExceptionManager loadExceptionData(String name, String configpath,
			ServletContext context) {
		ExceptionManager exceptionData = null;
		URL exceptionResourceURL = null;
		try {
			exceptionResourceURL = context.getResource(configpath);
		} catch (java.net.MalformedURLException ex) {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_malformed",name),ex);
		}
		if (exceptionResourceURL!=null) {
			exceptionData = ExceptionXmlDao.loadExceptions(name, exceptionResourceURL);
			if (exceptionData == null) {
    			logger.error(MAFLogger.getMessage("applications_dao_application_url_confirm",name,configpath));				
			}			
		} else {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_not_found",name,configpath));
		}
	
		return exceptionData;
	}
	
	private static TemplateScreenMap loadTemplateScreenMap(String applid, String configpath,
			ServletContext context, String name) {
		TemplateScreenMap templateScreenMap=null;
		URL templateScreenMapURL = null;
        try {
        	templateScreenMapURL = context.getResource(configpath);
        } catch (java.net.MalformedURLException ex) {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_malformed",applid),ex);
        }
        if (templateScreenMapURL != null) {
        	templateScreenMap = TemplateXmlDao.loadTemplateScreenMap(templateScreenMapURL, applid, name);
            if (templateScreenMap == null) {
    			logger.error(MAFLogger.getMessage("applications_dao_application_url_confirm",applid,configpath));
            } 
        } else {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_not_found",applid,configpath));
        }		
		return templateScreenMap;
	}
	
	private static HashMap<String, ProtectedResource> loadProtectedResources(String name, String configpath,
			ServletContext context) {
		HashMap<String, ProtectedResource> protectedResources = null;		
		URL protectedResourceURL = null;
        try {
        	protectedResourceURL = context.getResource(configpath);
        } catch (java.net.MalformedURLException ex) {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_malformed",name),ex);
        }
        if (protectedResourceURL != null) {
        	protectedResources = SecurityXmlDao.loadProtectedResources(protectedResourceURL);
            if (protectedResources == null) {
    			logger.error(MAFLogger.getMessage("applications_dao_application_url_confirm",name,configpath));
            }
        } else {
			logger.error(MAFLogger.getMessage("applications_dao_application_url_not_found",name,configpath));
        }		
		return protectedResources;
	}
	
	
	public class TemplateItem {
		private String name;
		private String configName;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getConfigName() {
			return configName;
		}
		public void setConfigName(String configName) {
			this.configName = configName;
		}
		public TemplateItem(String name, String configName) {
			super();
			this.name = name;
			this.configName = configName;
		}
	}
}
