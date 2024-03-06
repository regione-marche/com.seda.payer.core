/**
 * 
 */
package com.seda.j2ee5.maf.template;

import java.net.URL;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.xml.XmlParserSupport;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * @author Seda Lab
 *
 */
public class TemplateXmlDao extends XmlParserSupport {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(TemplateXmlDao.class);
	
	public static final String DEFAULT="default";
	public static final String BUNDLE="bundle";	
    public static final String TEMPLATE="template";
    public static final String NAME="name";
    public static final String URL="url";
    public static final String DIRECT="direct";
    public static final String ARGS="args";    
    public static final String SCREEN="screen";
	public static final String MENU_ACTION_URL = "menuaction";
    
    public static final String PARAMETER="parameter";
    public static final String KEY="key";
    public static final String VALUE="value";    
	
	public static TemplateScreenMap loadTemplateScreenMap(URL location, String applid, String name) {
        Element root = loadDocument(location);
        if (root != null) return getTemplateScreenMap(root, applid, name);
        else return null;
    }	

	public static TemplateScreenMap getTemplateScreenMap(Element root, String applid, String name) {
        // get the template
        String defaultTemplate = getTagValue(root, DEFAULT);
        if (defaultTemplate == null) {
        	logger.error(MAFLogger.getMessage("template_dao_default_missing"));
            return null;
        }

        // get the resource bundle name for direct message 
        String resourceBundle = getTagValue(root, BUNDLE);
        
        if (resourceBundle != null) {
        	logger.debug(MAFLogger.getMessage("template_dao_resource_bundle", resourceBundle));
        }        
        
        TemplateScreenMap templateScreenMap = new TemplateScreenMap(applid, defaultTemplate, resourceBundle);
        loadTemplates(root, templateScreenMap);
        // get screens
        NodeList list = root.getElementsByTagName(SCREEN);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if ((node != null) && node instanceof Element) {
            	Element element = (Element)node;
                String templateName = element.getAttribute(TEMPLATE);
                String screenName = element.getAttribute(NAME);
                String menuaction = element.getAttribute(MENU_ACTION_URL);
                HashMap<String, TemplateParameter> parameters = getParameters(node);
                TemplateScreen screen = new TemplateScreen(name, screenName, menuaction, templateName, parameters);
                if (!templateScreenMap.containsScreen(screenName)) {
                	templateScreenMap.addScreen(screenName, screen);
                } else {
                	logger.warn(MAFLogger.getMessage("template_dao_duplicate_screen", screenName));
                }
            }
        }
        return templateScreenMap;
    }	
	
	/**
     *    Load the templates into the TemplateScreenMap object
     */

    public static void loadTemplates(Element root, TemplateScreenMap templateScreenMap) {
        NodeList list = root.getElementsByTagName(TEMPLATE);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            String templateName = null;
            if (node != null) {
                if (node instanceof Element) {
                    templateName = ((Element)node).getAttribute(NAME);
                }
                String templateURL = getSubTagValue(node, URL);

                if (!templateScreenMap.containsTemplate(templateName)) {
                	templateScreenMap.addTemplate(templateName, templateURL);
                } else {
                	logger.warn(MAFLogger.getMessage("template_dao_duplicate_template", templateName));                	
                }
            }
        }
    }	
	
    
    private static HashMap<String, TemplateParameter> getParameters(Node node) {
        HashMap<String, TemplateParameter> params = new HashMap<String, TemplateParameter>();
        if (node != null) {
            NodeList  children = node.getChildNodes();
            for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                Node  child = children.item(innerLoop);
                if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(PARAMETER) ) {
                    if (child instanceof Element) {
                        Element childElement = ((Element)child);
                        String key = childElement.getAttribute(KEY);
                        String value = childElement.getAttribute(VALUE);
                        String directString = childElement.getAttribute(DIRECT);
                        String args = childElement.getAttribute(ARGS);
                        if (args!=null && args.trim().length()==0) args=null;
                        boolean direct = Boolean.parseBoolean(directString);
                        if (!params.containsKey(key)) {
                            params.put(key, new TemplateParameter(key, value, direct, args));
                        } else {
                        	logger.warn(MAFLogger.getMessage("template_dao_duplicate_parameter", key));
                        }
                    }
                }
            } // end inner loop
        }
        return params;
    }    
    
}
