/**
 * 
 */
package com.seda.commons.properties.tree;

import java.util.Properties;

import com.seda.commons.string.Convert;

/**
 * @author dbadm
 *
 */
public class PropertiesTree {

	private PropertiesNode root;
	
	public PropertiesNode getRoot() {
		return root;
	}

	public String getRootPath() {
		return getRoot().getPath();
	}

	// constructor
	public PropertiesTree(String rootPath) throws PropertiesNodeException {
		root = new PropertiesNode(rootPath);
	}
	
	public PropertiesTree(Properties rootProperties) throws PropertiesNodeException {
		root = new PropertiesNode(rootProperties);		
	}

	/**
	 * @return all properties of the root node
	 */
	public Properties getProterties() {
		return root.getProperties();
	}
	
	/**
	 *  Deep search in the external child node hashmap. The pPath must respect the following rules<br> 
	 *  <code>childKeyNodes</code>/<code>childKeyNodes</code>/../<code>keyProperty</code><br>
	 *  <code>childKeyNodes</code>/<code>childKeyNodes</code>/../<code><i>keyLocalChild</i>:<i>keyProperty</i></code><br>  
	 *  The method returns the default value null if the property or an external child node
	 *  is not found.	  
	 *  
	 * @param pPath the pPath of the node to be searched
	 * @return <code>String</code> the value in this property pPath corresponding the specified key value 
	 */	
	public String getProperty(String pPath) {
		return getProperty(pPath, null);
	}
	
	/**
	 *  Deep search in the external child node hashmap. The pPath must respect the following rules<br> 
	 *  <code>childKeyNodes</code>/<code>childKeyNodes</code>/../<code>key</code><br>
	 *  <code>childKeyNodes</code>/<code>childKeyNodes</code>/../<code><i>keySubSet</i>:<i>key</i></code><br>  
	 *  The method returns the default value argument if the property or an external child node
	 *  is not found.	  
	 *  
	 * @param pPath the pPath of the node to be searched
	 * @param defaultValue the default value if the key is not found
	 * @return <code>String</code> the value in this property pPath corresponding the specified key value 
	 */	
	public String getProperty(String pPath, String defaultValue) {
		// if the properties path is null or has a zero length return default value
		if (pPath==null || pPath.length()==0) return defaultValue;
		// split the properties path in to array string
		String[] paths = Convert.stringToArray(pPath,'/');
		// if the path length is greater than 1 searches for the value
		PropertiesNode node = getRoot();
		for (int i=0, j=paths.length; i<j; i++) {
			// is is the last element return the property value in according to the specified key
			// Attention: the array is zero based; so we subtract one from j
			if (i==j-1) {
				// gets the pPath subPath
				String[] subPath = Convert.stringToArray(paths[i], ':');
				// if the last key refers to pPath subPath			
				try {
					if (subPath.length==2) {
						// get the property from the subset properties
						return node.getSubSetProperty(subPath[0], subPath[1], defaultValue);
					} else {
						return node.getProperty(paths[i], defaultValue);					
					}					
				} finally {
					node=null;
				}
			}
			// get the next child
			node=node.getNode(paths[i]);
			// if the child is not found return the default value
			if (node==null) return defaultValue;
		}
		return null;
	}	
	
	/**
	 *  Deep search in the external child node hashmap. The pPath must respect the following rules<br> 
	 *  <code>childKeyNodes</code>/<code>childKeyNodes</code>/../<code>keyNode</code><br>
	 *  <code>childKeyNodes</code>/<code>childKeyNodes</code>/../<code>keyNode:keySubSet</code><br>  
	 *  The method returns a null properties object if the properties or an external node 
	 *  is not found.	  
	 *  
	 * @param pPath the pPath of the node to be searched
	 * @return <code>Properties</code> the properties in this property pPath with the specified key value 
	 */	
	public Properties getProperties(String pPath) {
		return getProperties(pPath,null);
	}
	
	/**
	 *  Deep search in the external child node hashmap. The pPath must respect the following rules<br> 
	 *  <code>childKeyNodes</code>/<code>childKeyNodes</code>/../<code>keyNode</code><br>
	 *  <code>childKeyNodes</code>/<code>childKeyNodes</code>/../<code>keyNode:keySubSet</code><br>  
	 *  The method returns the default properties object an external node 
	 *  is not found or null if the subset properties is not found in the node.	  
	 *  
	 * @param pPath the pPath of the node to be searched
	 * @param defaultProperties the default properties object if the key is not found 
	 * @return <code>Properties</code> the properties in this property pPath with the specified key value 
	 */	
	public Properties getProperties(String pPath, Properties defaultProperties) {
		// if the properties path is null or has a zero length return null
		if (pPath==null || pPath.length()==0) return defaultProperties;
		// split the properties path in to array string
		String[] paths = Convert.stringToArray(pPath,'/');
		// if the path length is greater than 1 searches for the value
		PropertiesNode node = getRoot();
		for (int i=0, j=paths.length; i<j; i++) {
			// if is the last element return the property value in according to the specified key
			// Attention: the array is zero based; so we subtract one from j
			if (i==j-1) {
				// gets the pPath subPath
				String[] subPath = Convert.stringToArray(paths[i], ':');
				try {
					// if the last key refers to pPath subPath				
					if (subPath.length==2) {
						// if pPath is equal to Prop4j.THIS.value() return node properties
						if (subPath[0].equals(Prop4j.THIS.value())) 
							return node.getSubSet(subPath[1]);
						// if the child is not found return default properties
						if (node.getNode(subPath[0])==null) 
							return defaultProperties;
						//return the subset properties of the child node
						return node.getNode(subPath[0]).getSubSet(subPath[1]);
					} else {
						// if the child is not found return default properties					
						if (node.getNode(paths[i])==null) 
							return defaultProperties;
						//return the child properties					
						return node.getNode(paths[i]).getProperties();					
					}					
				} finally {
					node=null;
				}				
			}
			// get the next child
			node=node.getNode(paths[i]);
			// if the child is not found return the default value
			if (node==null) return defaultProperties;
		}
		return null;
	}		
	
}
