/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import java.net.URL;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.seda.commons.xml.XmlParserSupport;

/**
 * 
 * 
 * @author Seda Lab
 *
 */
public class WebXmlDao extends XmlParserSupport {

//	private static final Logger logger = MAFLogger.getLogger("WebXmlDao");
	
	public static final String SERVLET = "servlet";
	public static final String SERVLET_NAME = "servlet-name";	
	public static final String SERVLET_CLASS = "servlet-class";	
	public static final String SERVLET_MAPPING = "servlet-mapping";
	public static final String URL_PATTERN = "url-pattern";	

	public static final String SERVLET_DESCRIPTION  = "description";
	public static final String SERVLET_DISPLAY_NAME  = "display-name";
	public static final String SERVLET_LOAD_ON_STARTUP  = "load-on-startup";
	
	
	public static WebData loadWebData(String location) {
		Element root = loadDocument(location);
		if (root!=null)
			return getWebData(root);
		else 
			return null;
	}

	public static WebData loadWebData(URL location) {
		Element root = loadDocument(location);
		if (root!=null)
			return getWebData(root);
		else 
			return null;
	}			

	public static WebData getWebData(Element root) {
		String controllerServlet=null;
		String controllerURL=null;
		String templateServlet=null;
		String templateURL=null; 
		HashMap<String, WebServlet> servletMap = new HashMap<String, WebServlet>();
		// loop through the servlet configuration 
        NodeList servletList = root.getElementsByTagName(SERVLET);
        for (int loop = 0; loop < servletList.getLength(); loop++) {
            Node servlet = servletList.item(loop);
            if ((servlet != null) && servlet instanceof Element) {
            	// get the class name
            	String servletClass = clean(getSubTagValue(servlet, SERVLET_CLASS));
            	if (servletClass!=null) {
            		if (servletClass.equals(WebData.CONTROLLER_CLASS)) {
            			controllerServlet=clean(getSubTagValue(servlet, SERVLET_NAME));
            			continue;
            		} else if (servletClass.equals(WebData.TEMPLATE_CLASS)) {
            			templateServlet=clean(getSubTagValue(servlet, SERVLET_NAME));
            			continue;
            		} 
            		String description = clean(getSubTagValue(servlet, SERVLET_DESCRIPTION));
            		String displayName = clean(getSubTagValue(servlet, SERVLET_DISPLAY_NAME));
            		String servletName = clean(getSubTagValue(servlet, SERVLET_NAME));
            		int loadOnStartup=-1; 
            		String loadOnStartupString = clean(getSubTagValue(servlet, SERVLET_LOAD_ON_STARTUP));
            		if (loadOnStartupString!=null) {
            			loadOnStartup=Integer.parseInt(loadOnStartupString);
            		}
            		servletMap.put(servletName, new WebServlet(description, displayName, servletName, servletClass, loadOnStartup));
            	}
            }
        }
		// loop through the servlet configuration 
        NodeList servletMappingList = root.getElementsByTagName(SERVLET_MAPPING);
        for (int loop = 0; loop < servletMappingList.getLength(); loop++) {
            Node servletMapping = servletMappingList.item(loop);
            if ((servletMapping != null) && servletMapping instanceof Element) {
            	// get the class name
            	String servletName = clean(getSubTagValue(servletMapping, SERVLET_NAME));
            	if (servletName!=null) {
            		String urlPattern = clean(getSubTagValue(servletMapping, URL_PATTERN));
            		if (servletName.equals(controllerServlet)) {
            			controllerURL=urlPattern;
            		} else if (servletName.equals(templateServlet)) {
            			templateURL=urlPattern;
            		}
            		if (servletMap.containsKey(servletName)) {
            			servletMap.get(servletName).addUrlPattern(urlPattern);
            		}
            	}
            }
        }        
		return new WebData(controllerServlet, controllerURL, templateServlet, templateURL, servletMap);
	}
	
}
