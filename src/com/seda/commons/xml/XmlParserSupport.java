/**
 * 
 */
package com.seda.commons.xml;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
/**
 * @author Seda Lab
 *
 */
public abstract class XmlParserSupport {

	public final static Element loadDocument(String location) {
		try {
			return loadDocument(new URL(location));
		} catch (MalformedURLException x) {
			System.err.println("XmlParserSupport error: " + x);
		}
        return null;
    }		
	
	public final static Element loadDocument(URL url) {
        Document doc = null;
        try {
            InputSource xmlInp = new InputSource(url.openStream());

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = docBuilderFactory.newDocumentBuilder();
            doc = parser.parse(xmlInp);
            Element root = doc.getDocumentElement();
            root.normalize();
            return root;
        } catch (SAXParseException x) {
            System.err.println ("XmlParserSupport ** Parsing error" + ", line " +
                        x.getLineNumber () + ", uri " + x.getSystemId ());
            System.err.println("XmlParserSupport error: " + x.getMessage ());
        } catch (SAXException x) {
            System.err.println("XmlParserSupport error: " + x);
        } catch (java.net.MalformedURLException x) {
            System.err.println("XmlParserSupport error: " + x);
        } catch (java.io.IOException x) {
            System.err.println("XmlParserSupport error: " + x);
        } catch (Exception x) {
            System.err.println("XmlParserSupport error: " + x);
        }
        return null;
    }			
	
    public static String getTagValue(Element root, String tagName) {
        String returnString = null;
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                Node child = node.getFirstChild();
                if ((child != null) && child.getNodeValue() != null) return child.getNodeValue();
            }
        }
        return returnString;
    }	
	
	public static String getSubTagValue(Node node, String subTagName) {
        String returnString = null;
        if (node != null) {
            NodeList  children = node.getChildNodes();
            for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                Node  child = children.item(innerLoop);
                if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName) ) {
                    Node grandChild = child.getFirstChild();
                    if ((grandChild!=null) && grandChild.getNodeValue() != null) return grandChild.getNodeValue();
                }
            } // end inner loop
        }
        return returnString;
    }
	
	public static String getSubTagValue(Element root, String tagName, String subTagName) {
        String returnString = null;
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                NodeList  children = node.getChildNodes();
                for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                    Node  child = children.item(innerLoop);
                    if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName) ) {
                        Node grandChild = child.getFirstChild();
                        if ((grandChild!=null) && grandChild.getNodeValue() != null) return grandChild.getNodeValue();
                    }
                } // end inner loop
            }
        }
        return returnString;
    }

	public static String getSubTagAttribute(Element root, String tagName, String subTagName, String attribute) {
        String returnString = null;
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                NodeList  children = node.getChildNodes();
                for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                    Node  child = children.item(innerLoop);
                    if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName) ) {
                        if (child instanceof Element) {
                            return ((Element)child).getAttribute(attribute);
                        }
                    }
                } // end inner loop
            }
        }
        return returnString;
    }	
	
	public static String getElementAttribute(Element element, String attribute) {
        String returnString = null;
        if (element.getAttribute(attribute)!=null) {
        	returnString=element.getAttribute(attribute);
        }
        return returnString;
	}
	public static String getNodeAttribute(Node node, String attribute) {
		return getElementAttribute((Element)node, attribute);
    }	
	
	public static String getTagValue(Node node) {
		String returnString=null;
		Node grandChild = node.getFirstChild();
        if ((grandChild!=null) && grandChild.getNodeValue() != null) 
        	returnString=grandChild.getNodeValue();
        return returnString;
	}
	
	public static String clean(String value) {
		String newValue=null;
		if (value!=null) {
			newValue=value.replace('\r', ' ').replace('\t', ' ').replace('\n', ' ').trim();
		}
		return newValue;
		
	}
	
	public static String emptyToNull(String value) {
		return emptyToDefault(value, null);
	}
	
	public static String emptyToDefault(String value, String defaultValue) {
		return value.trim().length()==0?defaultValue:value;
	}
	
}
