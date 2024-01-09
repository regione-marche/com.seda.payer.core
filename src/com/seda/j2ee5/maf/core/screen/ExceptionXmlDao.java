/**
 * 
 */
package com.seda.j2ee5.maf.core.screen;

import java.net.URL;
import java.util.HashMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.seda.commons.xml.XmlParserSupport;

/**
 * @author Seda Lad
 *
 */
public class ExceptionXmlDao extends XmlParserSupport {

	public static final String DEFAULT = "default";
	public static final String EXCEPTION = "exception";
    public static final String CLASS = "class";
    public static final String SCREEN= "screen";
    public static final String APPLID="applid";
    
	public static ExceptionManager loadExceptions(String applid, String location) {
        Element root = loadDocument(location);
        HashMap<String, ExceptionData> exceptions = getExceptions(applid, root);
        return new ExceptionManager(exceptions);
    }	
	
	public static ExceptionManager loadExceptions(String applid, URL location) {
        Element root = loadDocument(location);
        HashMap<String, ExceptionData> exceptions = getExceptions(applid,root);
        return new ExceptionManager(exceptions);
    }	
	
	public static HashMap<String, ExceptionData> getExceptions(String application, Element root) {
        HashMap<String, ExceptionData> exceptions = new HashMap<String, ExceptionData>();
        NodeList list = root.getElementsByTagName(EXCEPTION);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                String className = "";
                String screen = null;
                String applid = null;
                // get exception nodes
                // need to be a element to get attributes
                if (node instanceof Element) {
                    Element element = ((Element)node);
                    className = element.getAttribute(CLASS);
                    screen = element.getAttribute(SCREEN);
                    applid = element.getAttribute(APPLID);
                    if (applid==null || applid.trim().length()==0) applid=application;
                    exceptions.put(className, new ExceptionData(className, screen, applid));
                }
            }
        }
        return exceptions;
    }	
		
	
	
}
