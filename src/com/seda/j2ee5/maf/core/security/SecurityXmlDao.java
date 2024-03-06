/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import java.net.URL;
import java.util.ArrayList;
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
public class SecurityXmlDao extends XmlParserSupport {
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(SecurityXmlDao.class);
	
    // xml tags
    public static final String CONSTRAINT = "constraint";
    public static final String RESOURCE_COLLECTION = "resource-collection";
    public static final String RESOURCE_ITEM = "resource";    
    public static final String RESOURCE_NAME = "name";
    public static final String URL_PATTERN = "url-pattern";
    public static final String ROLE_COLLECTION = "role-collection";
    public static final String ROLE_ITEM = "role";    
    public static final String ROLE_NAME = "name";	
	
    public static HashMap<String, ProtectedResource> loadProtectedResources(String location) {
        Element root = loadDocument(location);
        return getProtectedResources(root);
    }	
	
	public static HashMap<String, ProtectedResource> loadProtectedResources(URL location) {
        Element root = loadDocument(location);
        return getProtectedResources(root);
    }
	
    public static HashMap<String, ProtectedResource> getProtectedResources(Element root) {
    	
        HashMap<String, ProtectedResource> resources = new HashMap<String, ProtectedResource>();

        // get protected pages //
        NodeList constraintList = root.getElementsByTagName(CONSTRAINT);
        for (int constraintLoop = 0; constraintLoop < constraintList.getLength(); constraintLoop++) {
        	
        	Element elementConstraint = (Element)constraintList.item(constraintLoop);
        	// get  roles that can see this page
        	ArrayList<String> roles = new ArrayList<String>();
        	NodeList roleCollectionList = elementConstraint.getElementsByTagName(ROLE_COLLECTION);
        	Node roleCollectionNode=null;
            if (roleCollectionList.getLength() >= 1) {
            	roleCollectionNode = roleCollectionList.item(0);
            }
            if (roleCollectionList.getLength() > 1) {
            	logger.info(MAFLogger.getMessage("security_dao_only_error", ROLE_COLLECTION, CONSTRAINT));
            }  
        	
            if (roleCollectionNode!=null) {
            	if (roleCollectionNode instanceof Element) {
            		Element roleCollectionElement = (Element)roleCollectionNode;
            		NodeList rolesList = roleCollectionElement.getElementsByTagName(ROLE_ITEM);
            		for (int rolesLoop = 0; rolesLoop < rolesList.getLength(); rolesLoop++) {
                		Node roleNode = rolesList.item(rolesLoop);
                		String roleName = getTagValue(roleNode);
                		if ((roleName != null) && !roleName.equals("")) roles.add(roleName);
                	}            		
            	}
            }

        	NodeList resourceCollectionList = elementConstraint.getElementsByTagName(RESOURCE_COLLECTION);
        	Node resourceCollectionNode=null;
        	if (resourceCollectionList.getLength() >= 1) {
        		resourceCollectionNode = resourceCollectionList.item(0);
            }
            if (resourceCollectionList.getLength() > 1) {
            	logger.info(MAFLogger.getMessage("security_dao_only_error", RESOURCE_COLLECTION, CONSTRAINT));
            } 
            if (resourceCollectionNode!=null) {
            	if (resourceCollectionNode instanceof Element) { 
            		Element resourceCollectionElement = (Element)resourceCollectionNode;
            		NodeList resourceList = resourceCollectionElement.getElementsByTagName(RESOURCE_ITEM);
            		for (int loop = 0; loop < resourceList.getLength(); loop++) {
            			Node node = resourceList.item(loop);
            			String resourceName = getNodeAttribute(node, RESOURCE_NAME);
            			String urlPattern = getNodeAttribute(node, URL_PATTERN);
            			ProtectedResource resource = new ProtectedResource(resourceName, urlPattern, roles);
            			if (!resources.containsKey(resourceName)) {
            				resources.put(resourceName, resource);
            			} else {
                        	logger.info(MAFLogger.getMessage("security_dao_defined_more", resourceName));            				
            			}            			
            		}
            	}
            }            	
        }
        return resources;
    }    
}
