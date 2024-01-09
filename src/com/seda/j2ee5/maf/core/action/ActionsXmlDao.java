/**
 * 
 */
package com.seda.j2ee5.maf.core.action;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.xml.XmlParserSupport;
import com.seda.j2ee5.maf.core.flow.FlowData;
import com.seda.j2ee5.maf.core.flow.FlowManager;
import com.seda.j2ee5.maf.core.menu.MenuData;
import com.seda.j2ee5.maf.core.menu.MenuItemData;
import com.seda.j2ee5.maf.core.screen.ScreenRedirectData;
import com.seda.j2ee5.maf.core.screen.ScreenWriter;
import com.seda.j2ee5.maf.core.screen.ScreenWriterData;
import com.seda.j2ee5.maf.defender.action.AbstractAction;
import com.seda.j2ee5.maf.defender.action.RuleActionData;
import com.seda.j2ee5.maf.defender.repository.GlobalRuleSet;
import com.seda.j2ee5.maf.defender.repository.RegexSet;
import com.seda.j2ee5.maf.defender.repository.RuleSet;
import com.seda.j2ee5.maf.defender.rule.Category;
import com.seda.j2ee5.maf.defender.rule.Severity;
import com.seda.j2ee5.maf.util.MAFContext;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.URLUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
/**
 * @author Seda Lab
 *
 */
public class ActionsXmlDao extends XmlParserSupport {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ActionsXmlDao.class);
	
    public static final String URL_MAPPING = "action";
    public static final String URL = "url";
    public static final String SCREEN = "screen";
    public static final String DO_CSRF="csrf";
    public static final String IS_ACTION = "isAction";    
    public static final String ACTION_CLASS = "action-class";
	public static final String MENU_ACTION_URL = "menuaction";
    
    public static final String DESCRIPTION = "description";    
    
    public static final String FLOW = "flow";
    public static final String HAS_FLOW = "flow";
    public static final String FLOW_CLASS = "class";
    public static final String FLOW_CLASS_RESULT = "result"; 
    public static final String RESULT_VALUE = "value";
    public static final String RESULT_APPLID = "applid";

    public static final String SETTINGS = "settings";
    public static final String PROPERTIES = "properties";
    public static final String PROPERTY = "property";
    public static final String PROPERTY_KEY = "key";
    public static final String PROPERTY_VALUE = "value";
    
	public static final String GLOBAL_RULESET = "global-ruleset";
	public static final String RULESET = "ruleset";
	public static final String RULE = "rule";
	public static final String RULE_NAME = "name";
	public static final String RULE_REGEX = "regex";
	public static final String RULE_VALIDATION = "validation";	
	public static final String RULE_MISSING = "missing";
	public static final String RULE_MALFORMED = "malformed";
	public static final String RULE_SEVERITY = "severity";
	public static final String RULE_ACTION = "rule-action";
	public static final String RULE_ACTION_CLASS = "class";
	public static final String RULE_ACTION_PARAM = "parameter";
	public static final String RULE_PARAM_NAME = "name";
	public static final String RULE_PARAM_VALUE = "value";
	
	public static final String REGEXSET = "regexset";
	public static final String REGEX = "regex";
	public static final String REGEX_NAME = "name";
	public static final String REGEX_PATTERN = "pattern";
	public static final String REGEX_DESCRIPTION = "description";
    
    
    public static final String ACTION_ROLE_COLLECTION = "role-collection";
	public static final String ACTION_ROLE = "role";	    
    
	public static final String MENU = "menu";
	public static final String MENU_LINK = "link";	
	public static final String MENU_LINK_URL = "actionurl";	
	public static final String MENU_LINK_TEXT = "text";	
	public static final String MENU_LINK_TITLE = "title";	
	public static final String MENU_LINK_ENABLED = "enabled";
	public static final String MENU_GROUP = "menu-group";
	public static final String MENU_GROUP_ID = "id";
	
	public static final String COMPLEX = "complex-actions";
	public static final String COMPLEX_TARGET = "complex-target";	
	public static final String COMPLEX_VALUE = "value";
	public static final String COMPLEX_URL = "actionurl";	
	
    public static final String USE_WRITER = "writer";
    public static final String WRITER = "writer";    
    public static final String WRITER_ID = "id";    
    public static final String WRITER_ID_SCOPE = "scope";
    public static final String WRITER_IN_SCOPE = "inScope";    
    public static final String WRITER_ATTACH = "attach";    
    public static final String WRITER_CONTENT_TYPE = "content-type";    
    
    public static final String USE_REDIRECT = "redirect";
    public static final String REDIRECT = "redirect";    
    public static final String REDIRECT_ID = "id";    
    public static final String REDIRECT_URL = "url";
    
	public static HashMap<String, ActionData> loadActions(String location, String applid) {
        Element root = loadDocument(location);
        return getActionsData(root, applid);
    }	
	
	public static HashMap<String, ActionData> loadActions(URL location, String applid) {
        Element root = loadDocument(location);
        return getActionsData(root, applid);
    }
	
	public static HashMap<String, ActionData> getActionsData(Element root, String applid) {
        HashMap<String, ActionData> actionMap = new HashMap<String, ActionData>();
        NodeList list = root.getElementsByTagName(URL_MAPPING);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                String url = "";
                String menuaction = null;
                String description = "";                
                String screen = null;
                String actionClass =null;
                boolean isAction = false;
                
                String enforceCsrfString=null;
                boolean enforceCsrf = true;
                Properties actionSettings=null;
                
                String hasFlowString = null;
                boolean hasFlow = false;
                String flowClass=null;
                HashMap<String, FlowData> flowResultMap = null;
                Properties flowProperties=null;
                Properties flowSettings=null;
                
                String useWriterString = null;
                boolean useWriter = false;
                ScreenWriterData writerData = null;
                
                String useRedirectString = null;
                boolean useRedirect = false;
                ScreenRedirectData redirectData = null;

                RuleSet ruleSet = null;
                // get the roles collection
                ArrayList<String> roles = loadRoles((Element)node, applid);
                // get url mapping attributes
                // need to be a element to get attributes
                if (node instanceof Element) {
                    Element element = ((Element)node);
                    url = element.getAttribute(URL);
                    menuaction = element.getAttribute(MENU_ACTION_URL);
                    screen = element.getAttribute(SCREEN);
                    hasFlowString = element.getAttribute(HAS_FLOW);
                    useWriterString = element.getAttribute(USE_WRITER);
                    useRedirectString = element.getAttribute(USE_REDIRECT);
                    enforceCsrfString = element.getAttribute(DO_CSRF);
                    actionSettings=loadChildAsProperties(element,SETTINGS,applid);
                }
                if (enforceCsrfString!=null && enforceCsrfString.trim().length()>0)
                	enforceCsrf=Boolean.parseBoolean(enforceCsrfString);
                
                description = getSubTagValue(node, DESCRIPTION);
                if (description == null) description="";
                
                actionClass = getSubTagValue(node, ACTION_CLASS);
                if (actionClass != null) {
                	try {
						if (HtmlAction.class.isAssignableFrom(Class.forName(actionClass))) {
		                	isAction= true;							
						} else {
							logger.error(String.format("[%s] <%s> Action class does not extend %s: '%s'",applid,url,HtmlAction.class,actionClass));
		                    continue;													
						}
					} catch (Throwable e) {
						logger.error(String.format("[%s] <%s> Action class loading error: '%s'",applid,url,actionClass),e);
	                    continue;						
					}

                }
                
                hasFlow=Boolean.parseBoolean(hasFlowString);
                useWriter=Boolean.parseBoolean(useWriterString);
                useRedirect=Boolean.parseBoolean(useRedirectString);
                
                if (hasFlow && useWriter) {
                	logger.warn(MAFLogger.getMessage("actions_dao_exclusive_error",applid, USE_WRITER,HAS_FLOW,url));
                    continue;
                }
                
                if (hasFlow && useRedirect) {
                	logger.warn(MAFLogger.getMessage("actions_dao_exclusive_error",applid, USE_REDIRECT,HAS_FLOW,url));
                    continue;
                }

                if (useWriter && useRedirect) {
                	logger.warn(MAFLogger.getMessage("actions_dao_exclusive_error",applid, USE_WRITER,USE_REDIRECT,url));
                    continue;
                }
                
                if (hasFlow) {
                	Element flowRoot = (Element)node;
                    // need to be a element to find sub nodes by name
            		NodeList children = flowRoot.getElementsByTagName(FLOW);
            		Node flowNode = null;
            		if (children.getLength() >= 1) {
            			flowNode = children.item(0);
            		}
            		if (children.getLength() > 1) {
            			logger.info(MAFLogger.getMessage("actions_dao_only_error", applid, FLOW,URL_MAPPING, url));
            		}
            		if (flowNode!=null && flowNode instanceof Element) {
            			Element flowElement = (Element)flowNode;
        				flowClass = flowElement.getAttribute(FLOW_CLASS);
                    	try {
    						if (!FlowManager.class.isAssignableFrom(Class.forName(flowClass))) {
    							logger.error(String.format("[%s] <%s> Flow manager class does not extend %s: '%s'",applid,url,FlowManager.class,flowClass));
    		                    continue;													
    						}
    					} catch (ClassNotFoundException e) {
    						logger.error(String.format("[%s] <%s> Flow class not found in this classloader: '%s'",applid,url,actionClass));
    	                    continue;						
    					}        				
                    	flowResultMap=getFlowResultMap(flowElement, applid, url);
                    	flowProperties=loadChildAsProperties(flowElement,PROPERTIES,applid);
                    	flowSettings=loadChildAsProperties(flowElement,SETTINGS,applid);
            		}
                } // end if (hasFlow)

                if (useWriter) {
                	// need to be a element to find sub nodes by name
                    if (node instanceof Element) {
                    	Element element = (Element)node;
                        NodeList children = element.getElementsByTagName(WRITER);
                        Node writerNode = null;
                        if (children.getLength() >= 1) {
                        	writerNode = children.item(0);
                        }
                        if (children.getLength() > 1) {
                        	logger.info(MAFLogger.getMessage("actions_dao_only_error", applid, WRITER,URL_MAPPING,url));
                        }
                        // get the writer details
                        if (writerNode != null) {
                        	if (writerNode instanceof Element) {
                        		String id = getNodeAttribute(writerNode, WRITER_ID);
                        		String scope = getNodeAttribute(writerNode, WRITER_ID_SCOPE);
                        		if (scope==null || scope.trim().length()==0) scope=ScreenWriter.REQUEST;
                        		String contentType = getNodeAttribute(writerNode, WRITER_CONTENT_TYPE);
                        		String inScopeString = getNodeAttribute(writerNode, WRITER_IN_SCOPE);
                        		boolean inScope = Boolean.parseBoolean(inScopeString);
                        		String attach = getNodeAttribute(writerNode, WRITER_ATTACH);
                        		writerData=new ScreenWriterData(id, scope, contentType, inScope, attach);                        		
                        	}
                        } // end if (writerNode != null)
                    }
                } // end if (useWriter)
                
                if (useRedirect) {
                	// need to be a element to find sub nodes by name
                    if (node instanceof Element) {
                    	Element element = (Element)node;
                        NodeList children = element.getElementsByTagName(REDIRECT);
                        Node redirectNode = null;
                        if (children.getLength() >= 1) {
                        	redirectNode = children.item(0);
                        }
                        if (children.getLength() > 1) {
                        	logger.info(MAFLogger.getMessage("actions_dao_only_error", applid, REDIRECT,URL_MAPPING,url));
                        }
                        // get the writer details
                        if (redirectNode != null) {
                        	if (redirectNode instanceof Element) {
                        		String id = emptyToNull(getNodeAttribute(redirectNode, REDIRECT_ID));
                        		String redirectUrl = emptyToNull(getNodeAttribute(redirectNode, REDIRECT_URL));
                        		if (redirectUrl!=null && id!=null) {
                                	logger.warn(MAFLogger.getMessage("actions_dao_exclusive_error",applid, REDIRECT_ID,REDIRECT_URL, url + " - " + REDIRECT));
                                	continue;
                        		}
                        		redirectData=new ScreenRedirectData(id, redirectUrl);                        		
                        	}
                        } // end if (redirectNode != null)
                    }
                } // end if (useRedirect)
                
                // load Defender ruleset
                ruleSet = loadRuleSet((Element)node, applid, URL_MAPPING);
                // add this action to the mappings
                if (!actionMap.containsKey(url)) {
                	ActionData actionData = new ActionData(applid, url, menuaction, description, roles, screen, 
                			isAction, actionClass, enforceCsrf, actionSettings, 
                    		hasFlow, flowClass, flowResultMap, flowProperties, flowSettings,
                    		useWriter, writerData,
                    		useRedirect, redirectData, 
                    		ruleSet); 
                    actionMap.put(url, actionData);
            		logger.debug(String.format("[%s] Action url %s config: %s", applid,url,actionData.toString()));                    
                } else {
                	logger.error(MAFLogger.getMessage("actions_dao_duplicate_url",applid, url));
                }
            } 
        } // end loop url mapping
        return actionMap;
    }	

	private static HashMap<String, FlowData> getFlowResultMap(Element flowElement, 
			String applid, String url) {
		HashMap<String, FlowData> flowResultMap=null;
		// get the flow handler details
		NodeList results = flowElement.getElementsByTagName(FLOW_CLASS_RESULT);
		if (results.getLength() > 0){
			flowResultMap = new HashMap<String, FlowData>();
		}
		for (int resultLoop=0, resultLoopMax=results.getLength(); resultLoop < resultLoopMax; resultLoop++) {
			Node resultNode = results.item(resultLoop);
			if (resultNode instanceof Element) {
				Element resultElement = (Element)resultNode;
				String value = resultElement.getAttribute(RESULT_VALUE);
				String screen = emptyToNull(resultElement.getAttribute(SCREEN));
				String writer = emptyToNull(resultElement.getAttribute(WRITER));
				String redirect = emptyToNull(resultElement.getAttribute(REDIRECT));
				
				if ((screen!=null && writer!=null) ||
						(screen!=null && redirect!=null) ||
						(writer!=null && redirect!=null)) {
                	logger.warn(MAFLogger.getMessage("actions_dao_exclusive_error",applid, SCREEN + ">, <"+WRITER,REDIRECT, FLOW + " - " + FLOW_CLASS_RESULT));
                	continue;
				}
				
				FlowData flowData = new FlowData();
				flowData.setApplid(applid);
				if (screen!=null && screen.trim().length()>0) {
					flowData.setScreen(screen);
					String nextApplid = resultElement.getAttribute(RESULT_APPLID);
					if (nextApplid!=null && nextApplid.trim().length()>0) {
						flowData.setApplid(nextApplid);
					} 
				} else if (writer!=null && writer.trim().length()>0) {
					String contentType = resultElement.getAttribute(WRITER_CONTENT_TYPE);
					if (contentType==null || contentType.trim().length()==0 ) {
						logger.warn(String.format("[%s] <{%s}> attribute \"%s\" is required in flow map result -%s- for \"%s\"",  applid, FLOW, WRITER_CONTENT_TYPE, value, url));
						continue;
					}
					boolean inScope = Boolean.parseBoolean(resultElement.getAttribute(WRITER_IN_SCOPE));
					String attachId = resultElement.getAttribute(WRITER_ATTACH);
					if (attachId==null || attachId.trim().length()==0 ) {
						attachId=null;
					}
					ScreenWriterData screenWriterData = new ScreenWriterData(writer, ScreenWriter.REQUEST, contentType, inScope, attachId);
					flowData.setScreenWriterData(screenWriterData);
				} else if (redirect!=null && redirect.trim().length()>0) {
					ScreenRedirectData screenRedirectData = new ScreenRedirectData(redirect, null);
					flowData.setScreenRedirectData(screenRedirectData);
				} else {
					logger.warn(String.format("[%s] <{%s}> flow map result -%s- is not propertly configured for \"%s\"",  applid, FLOW, value, url));
					continue;
				}
				
				if (!flowResultMap.containsKey(value)) {
					flowResultMap.put(value,flowData);
				} else {
					logger.warn(MAFLogger.getMessage("actions_dao_duplicate_flow_key", applid, FLOW, value, url));
				}
			}
		} // end for
		return flowResultMap;
	}

	private static ArrayList<String> loadRoles(Element elementAction, String applid) {
		ArrayList<String> roles = new ArrayList<String>();
		
		NodeList roleCollectionList = elementAction.getElementsByTagName(ACTION_ROLE_COLLECTION);
    	Node roleCollectionNode=null;
        if (roleCollectionList.getLength() >= 1) {
        	roleCollectionNode = roleCollectionList.item(0);
        }
        if (roleCollectionList.getLength() > 1) {
        	logger.warn(MAFLogger.getMessage("actions_dao_only_error",applid, ACTION_ROLE_COLLECTION,URL_MAPPING));
        }  
    	 
        if (roleCollectionNode!=null) {
        	if (roleCollectionNode instanceof Element) {
        		Element roleCollectionElement = (Element)roleCollectionNode;
        		NodeList rolesList = roleCollectionElement.getElementsByTagName(ACTION_ROLE);
        		for (int rolesLoop = 0; rolesLoop < rolesList.getLength(); rolesLoop++) {
            		Node roleNode = rolesList.item(rolesLoop);
            		String roleName = getTagValue(roleNode);
            		if ((roleName != null) && (roleName.trim().length()!=0)) 
            			roles.add(roleName);
            	}            		
        	}
        }
        return roles;
	}	
	
	private static Properties loadChildAsProperties(Element elementAction, String parent, String applid) {
		Properties properties = new Properties();
		
		NodeList propertiesList = elementAction.getElementsByTagName(parent);
    	Node propertiesNode=null;
        if (propertiesList.getLength() >= 1) {
        	propertiesNode = propertiesList.item(0);
        }
        if (propertiesList.getLength() > 1) {
        	logger.warn(MAFLogger.getMessage("actions_dao_only_error",applid, parent,URL_MAPPING));
        }  
    	 
        if (propertiesNode!=null) {
        	if (propertiesNode instanceof Element) {
        		Element propertyElement = (Element)propertiesNode;
        		NodeList propertyList = propertyElement.getElementsByTagName(PROPERTY);
        		for (int propertyLoop = 0; propertyLoop < propertyList.getLength(); propertyLoop++) {
            		Node propertyNode = propertyList.item(propertyLoop);
            		String key = getNodeAttribute(propertyNode, PROPERTY_KEY);
            		String value = getNodeAttribute(propertyNode, PROPERTY_VALUE);
            		properties.put(key,value);
            	}            		
        	}
        }
        return properties;
	}		
	
	
	
	public static GlobalRuleSet loadGlobalRuleSet(String location, String applid) {
        Element root = loadDocument(location);
        return loadGlobalRuleSet(root, applid);
    }	
	
	public static GlobalRuleSet loadGlobalRuleSet(URL location, String applid) {
        Element root = loadDocument(location);
        return loadGlobalRuleSet(root, applid);
    }
	
	private static GlobalRuleSet loadGlobalRuleSet(Element root, String applid) {
		
		GlobalRuleSet globalRuleSet=null; 
		RegexSet regexSet=null;
		RuleSet ruleSet=null;
		
		NodeList globalRuleSetList = root.getElementsByTagName(GLOBAL_RULESET);
        Node globalRuleSetNode = null;
        if (globalRuleSetList.getLength() >= 1) {
        	globalRuleSetNode = globalRuleSetList.item(0);
        }
        if (globalRuleSetList.getLength() > 1) {
        	logger.warn(MAFLogger.getMessage("actions_dao_only_error2", applid, GLOBAL_RULESET));
        } 
		
        if (globalRuleSetNode!=null && globalRuleSetNode instanceof Element) {
        	Element globalRuleSetElement = (Element) globalRuleSetNode;
        	regexSet = loadRegexSet(globalRuleSetElement, applid);	
        	ruleSet = loadRuleSet(globalRuleSetElement, applid,GLOBAL_RULESET);
        }
        
        globalRuleSet = new GlobalRuleSet(regexSet, ruleSet);
		
		return globalRuleSet;
	}

	private static RegexSet loadRegexSet(Element root, String applid) {
		RegexSet regexSet = new RegexSet();
		
		NodeList regexsetList = root.getElementsByTagName(REGEXSET);
		Node regexsetNode = null;
		if (regexsetList.getLength()>=1) {
			regexsetNode = regexsetList.item(0);
		}
		if (regexsetList.getLength()>1) {
			logger.warn(MAFLogger.getMessage("actions_dao_only_error", applid, REGEXSET, GLOBAL_RULESET));
		}
		if (regexsetNode!=null && regexsetNode instanceof Element) {
			Element regexsetElement = (Element) regexsetNode;
			NodeList regexList = regexsetElement.getElementsByTagName(REGEX);
			for (int regexLoop = 0; regexLoop < regexList.getLength(); regexLoop++) {
				Element regexElement = (Element)regexList.item(regexLoop);
				String name = getElementAttribute(regexElement, REGEX_NAME);
				String pattern = getSubTagValue(regexElement, REGEX_PATTERN);
				String description=getSubTagValue(regexElement, REGEX_DESCRIPTION);
				regexSet.addRegex(name, pattern, description);
			}
		}

		return regexSet;
	}

	
	private static RuleSet loadRuleSet(Element root, String applid, String parentNode) {
		RuleSet ruleSet=new RuleSet();
		NodeList rulesetList = root.getElementsByTagName(RULESET);
		Node rulesetNode = null;
		if (rulesetList.getLength()>=1) {
			rulesetNode = rulesetList.item(0);
		}
		if (rulesetList.getLength()>1) {
			logger.warn(MAFLogger.getMessage("actions_dao_only_error", applid, RULESET, parentNode));
		}
		if (rulesetNode!=null && rulesetNode instanceof Element) {
			Element rulesetElement = (Element) rulesetNode;
			NodeList ruleList = rulesetElement.getElementsByTagName(RULE);
			for (int ruleLoop = 0; ruleLoop < ruleList.getLength(); ruleLoop++) {
				Element ruleElement = (Element)ruleList.item(ruleLoop);
				
				String name = getElementAttribute(ruleElement, RULE_NAME);
				String regex = getElementAttribute(ruleElement, RULE_REGEX);
				String validation=getSubTagValue(ruleElement, RULE_VALIDATION);
				
				if ((regex==null || regex.trim().length()==0) && 
						(validation==null || validation.trim().length()==0)) {
					logger.warn(MAFLogger.getMessage("actions_dao_rule", applid, name));
				} else {
					Category missing = loadRuleCategory(ruleElement, applid, RULE_MISSING);
					Category malformed = loadRuleCategory(ruleElement, applid, RULE_MALFORMED);
					
					ruleSet.addRule(name, regex, validation, missing, malformed);					
				}
			}
		}
		return ruleSet;
	}

	private static Category loadRuleCategory(Element ruleElement, String applid,
			String categoryType) {
		
		Category category=null;
		
		List<RuleActionData> actions=new ArrayList<RuleActionData>();
		
		NodeList categoryList = ruleElement.getElementsByTagName(categoryType);
		Node categoryNode = null;
		if (categoryList.getLength()>=1) {
			categoryNode = categoryList.item(0);
		}
		if (categoryList.getLength()>1) {
			logger.warn(MAFLogger.getMessage("actions_dao_only_error", applid, categoryType, RULE));
		}
		
		if (categoryNode!=null && categoryNode instanceof Element) {
			Element categoryElement = (Element) categoryNode;
			String severityString = getElementAttribute(categoryElement,RULE_SEVERITY);
			Severity severity = new Severity(severityString);
			
			NodeList actionList = categoryElement.getElementsByTagName(RULE_ACTION);
			for (int actionLoop = 0; actionLoop < actionList.getLength(); actionLoop++) {
				Element actionElement = (Element)actionList.item(actionLoop);
				String actionClass = getElementAttribute(actionElement, RULE_ACTION_CLASS);
            	try {
					if (!AbstractAction.class.isAssignableFrom(Class.forName(actionClass))) {
						logger.error(String.format("[%s] <%s> Rule action class does not extend %s: '%s'",applid,categoryType,AbstractAction.class,actionClass));
					}
				} catch (ClassNotFoundException e) {
					logger.error(String.format("[%s] <%s> Rule action class not found in this classloader: '%s'",applid,categoryType,actionClass));
                    continue;						
				}
				
				Map<String,String> parameters = loadRuleActionParameter(actionElement);
				actions.add(new RuleActionData(parameters,actionClass));
			}
			category = new Category(categoryType, severity, actions);
		}
		
		return category;
	}

	private static Map<String, String> loadRuleActionParameter(
			Element actionElement) {
		
		Map<String, String> parameters = new HashMap<String, String>();
		
		NodeList paramList = actionElement.getElementsByTagName(RULE_ACTION_PARAM);
		for (int paramLoop = 0; paramLoop < paramList.getLength(); paramLoop++) {
			Element paramElement = (Element)paramList.item(paramLoop);
			String name = getElementAttribute(paramElement, RULE_PARAM_NAME);
			String value = getElementAttribute(paramElement, RULE_PARAM_VALUE);			
			parameters.put(name,value);
		}
		
		return parameters;
	}

	public static MenuData loadActionMenu(String location, String subcontext,
			HashMap<String, ActionData> actionsData, ServletContext context, String applid) {
        Element root = loadDocument(location);
        return loadActionMenu(root, subcontext,	actionsData, context, applid);
    }	
	
	public static MenuData loadActionMenu(URL location, String subcontext,
			HashMap<String, ActionData> actionsData, ServletContext context, String applid) {
        Element root = loadDocument(location);
        return loadActionMenu(root, subcontext,	actionsData, context, applid);
    }
	
	public static MenuData loadActionMenu(Element root, String subcontext,
			HashMap<String, ActionData> actionsData, ServletContext context, String applid) {
		
		MenuData menuData = new MenuData();
        
        String anonymousRole = context.getInitParameter(MAFContext.CONTEXT_ANONYMOUS);
		if (anonymousRole==null || anonymousRole.trim().length()==0) {
			anonymousRole=MAFContext.DEFAULT_ANONYMOUS;
		} else {
			anonymousRole=anonymousRole.trim();
		}
		logger.debug(MAFLogger.getMessage("actions_dao_anonym_role", applid, anonymousRole));
		
		ArrayList<MenuItemData> anonymousMenuItems = new ArrayList<MenuItemData>();  
		
		NodeList menuList = root.getElementsByTagName(MENU);
        Node menuNode = null;
        if (menuList.getLength() >= 1) {
        	menuNode = menuList.item(0);
        }
        if (menuList.getLength() > 1) {
        	logger.warn(MAFLogger.getMessage("actions_dao_only_error2", applid, MENU));
        }        
        
        if (menuNode!=null && menuNode instanceof Element) {
        	Element menuElement = (Element) menuNode;
        	NodeList linkList = menuElement.getElementsByTagName(MENU_LINK);
        	for (int linkLoop = 0; linkLoop < linkList.getLength(); linkLoop++) {
            	Element elementLink = (Element)linkList.item(linkLoop);
        		String text=getElementAttribute(elementLink, MENU_LINK_TEXT);
        		String url=getElementAttribute(elementLink, MENU_LINK_URL);
        		boolean enabled = true;
        		String enabledString=getElementAttribute(elementLink, MENU_LINK_ENABLED);
        		if (enabledString!=null && enabledString.trim().length()>0)
        			enabled = Boolean.parseBoolean(enabledString);

        		String linkURL=URLUtil.urlRedirect(subcontext, url);
        		String target = URLUtil.getTargetUrl(linkURL);
        		ActionData action = actionsData.get(target);        		
        		if (action==null) {
        			logger.warn(MAFLogger.getMessage("actions_dao_actionurl_not_found", applid, target));
        			continue;
        		}

        		String title=action.getDescription();        		
        		
        		MenuItemData menuItemData = new MenuItemData(linkURL, text, title, enabled, applid, target);        		
        		
        		ArrayList<String> roles = action.getRoles();
        		
        		if (roles!=null && roles.size()>0) {
        			// loop through the roles and add the item menu for each role
        			for (Iterator<String> iterator = roles.iterator(); iterator.hasNext();) {
						String role = iterator.next();
						// if the role match the anonymous string add it to the anonymous role
						if (role.equals(anonymousRole)) {
							anonymousMenuItems.add(menuItemData);
						} else {
							menuData.putMenuItem(role,menuItemData);							
						}
					}
        		} else {
        			// loop through all roles in the menuData and add the item menu for each stored role
        			Set<String> menuDataSet = menuData.getMenuMap().keySet();
        			for (Iterator<String> iterator = menuDataSet.iterator(); iterator.hasNext();) {
						String role = iterator.next();
						menuData.putMenuItem(role,menuItemData);
					}
        			// add it to the anonymous menu
        			anonymousMenuItems.add(menuItemData);
        		}
        	} // end loop link
        	menuData.putMenu(anonymousRole, anonymousMenuItems);
        }
        return menuData;
	}
	
	
	public static ComplexActionTable loadComplexActionTable(String location, HashMap<String, ActionData> actionsData, String applid) {
        Element root = loadDocument(location);
        return loadComplexActionTable(root, actionsData, applid);
    }	
	
	public static ComplexActionTable loadComplexActionTable(URL location, HashMap<String, ActionData> actionsData, String applid) {
        Element root = loadDocument(location);
        return loadComplexActionTable(root, actionsData, applid);
    }

	private static ComplexActionTable loadComplexActionTable(Element root, HashMap<String, ActionData> actionsData,
			String applid) {
		ComplexActionTable complexActionTable = new ComplexActionTable();
		
		NodeList complexList = root.getElementsByTagName(COMPLEX);
        Node complexNode = null;
        if (complexList.getLength() >= 1) {
        	complexNode = complexList.item(0);
        }
        if (complexList.getLength() > 1) {
        	logger.warn(MAFLogger.getMessage("actions_dao_only_error2", applid, COMPLEX));
        }        
        
        if (complexNode!=null && complexNode instanceof Element) {
        	Element complexElement = (Element) complexNode;
        	NodeList complexTargetList = complexElement.getElementsByTagName(COMPLEX_TARGET);
        	for (int complexTargetLoop = 0; complexTargetLoop < complexTargetList.getLength(); complexTargetLoop++) {
               	Element elementTarget = (Element)complexTargetList.item(complexTargetLoop);
        		String value=getElementAttribute(elementTarget, COMPLEX_VALUE);
        		String actionurl=getElementAttribute(elementTarget, COMPLEX_URL);
        		ActionData action = actionsData.get(actionurl);        		
        		if (action==null) {
        			logger.warn(String.format("[%s] Complex target '%s' has action url '%s' missing in the action url mapping", applid, value, actionurl));
        			continue;
        		}
        		if (complexActionTable.contains(value)) {
        			MAFLogger.getMessage("actions_dao_duplicate_flow_key", applid, COMPLEX_TARGET, value, COMPLEX);        			
        		} else {
            		complexActionTable.add(value, actionurl);        			
        		}
        	}
        }
		return complexActionTable;
	}
	
}
