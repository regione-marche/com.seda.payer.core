/**
 * 
 */
package com.seda.commons.properties.tree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.seda.commons.properties.PropertiesLoader;
import com.seda.commons.string.Convert;

/**
 * @author dbadm
 *
 */
public class PropertiesNode {
	
	/* Manage External Node*/
	private StringBuffer nodeKeysBuffer;	
	private Map<String, PropertiesNode> nodes; // external properties child
	/* Manage Local Node*/	
	private StringBuffer subSetsKeysBuffer;	
	private Map<String, Properties> subSets; // local properties child
	/* Manage own properties */	
	private Properties nodeProperties = new Properties();
	/* Manage looping call */
	private String nodePropertiesPath;
	private StringBuffer treePath;
	/* ========================================== */	
	// Private Getters and Setters
	/* ========================================== */
	/* Local own properties */
	private Properties getNodeProperties() {
		return this.nodeProperties;
	}
	private void setNodeProperties(Properties nodeProperties) {
		this.nodeProperties = nodeProperties;
	}
	
	private Enumeration<Object> getNodePropertiesKeys() {
		return getNodeProperties().keys();
	}	
	/* ----------------------------------------- */	
	/* Local children nodes properties */	
	private StringBuffer getSubSetsKeysBuffer() {
		if (subSetsKeysBuffer==null) 
			subSetsKeysBuffer = new StringBuffer();
		return subSetsKeysBuffer;
	}	
	
	private Map<String, Properties> getSubSets() {
		if (subSets==null) 
			subSets = new HashMap<String, Properties>();
		return this.subSets;
	}
	
	private Iterator<String> getSubSetsIterator() {
		return getSubSets().keySet().iterator();
	}		
	
	private Enumeration<Object> getSubSetsKeys(String key) {
		return getSubSet(key).keys();
	}
	/* ----------------------------------------- */	
	/* External children tree nodes */
	private StringBuffer getNodeKeysBuffer() {
		if (nodeKeysBuffer==null) 
			nodeKeysBuffer = new StringBuffer();
		return nodeKeysBuffer;
	}	
	
	private Map<String, PropertiesNode> getNodes() {
		if (nodes==null) 
			nodes = new HashMap<String, PropertiesNode>();
		return this.nodes;
	}
	/* ----------------------------------------- */	
	/* Manage looping call */
	public String getPath() {
		return this.nodePropertiesPath;
	} 
	private void setNodePropertiesPath(String nodePropertiesPath) throws PropertiesNodeException {
		if (existsNodePath(nodePropertiesPath)) {
			throw new PropertiesNodeException(Messages.LOOP_INJECTION.format(nodePropertiesPath,getTreePath()));
		}
		this.nodePropertiesPath=nodePropertiesPath;
		addNodePath(nodePropertiesPath);		
	}
	
	private StringBuffer getTreePath() {
		if (treePath==null) treePath = new StringBuffer();
		return treePath;
	}
	private void setTreePath(StringBuffer treePath) {
		this.treePath=treePath;
	}	
	private void addNodePath(String nodePath) {
		if (getTreePath().length() > 0) {
			getTreePath().append(';').append(nodePath);			
		} else {
			getTreePath().append(nodePath);
		}
	}
	private boolean existsNodePath(String nodePath) {
		return getTreePath().indexOf(nodePath)>0;
		
	}
	/* ========================================== */
	// Private methods for add resources
	/* ========================================== */	
	private void putSubSet(String key, Properties value){
		getSubSets().put(key, value);
	}
	
	private void putNode(String key, PropertiesNode value) {
		getNodes().put(key, value);
	}
	/* ========================================== */
	// Public methods
	/* ========================================== */
	/**
	 * Returns an array String of the keys of the external nodes
	 * @return <code>String[]</code> the array of String of the keys in the external nodes hashtable
	 */
	public String[] getNodeKeys() {
		if (nodeKeysBuffer!=null)
			return Convert.stringToArray(getNodeKeysBuffer().toString(),',');
		return null;
	}		
	/**
	 * Returns the number of keys in the external node hashtable.
	 * @return <code>int</code> the number of keys in this hashtable
	 */	
	public int getNodeKeysLength() {
		if (nodeKeysBuffer!=null)
			return getNodeKeys().length;
		return 0;		
	}
	/**
	 * Searches in the stored nodes hashtable with the specified key.
	 * 
	 * @param key the hashtable key of the properties node
	 * @return <code>PropertiesNode</code> the properties node in the external properties node hashtable with the specified key value.
	 */
	public PropertiesNode getNode(String key) {
		return getNodes().get(key);
	}	
	/**
	 * Returns an array String of the keys of the subset of properties
	 * @return <code>String[]</code> the array of String of the keys in the properties sets hashtable
	 */
	public String[] getSubSetKeys() {
		if (subSetsKeysBuffer!=null)
			return Convert.stringToArray(getSubSetsKeysBuffer().toString(),',');
		return null;
	}		
	/**
	 * Returns the number of keys in the subset of properties hashtable.
	 * @return <code>int</code> the number of keys in this hashtable
	 */
	public int getSubSetKeysLength() {
		if (subSetsKeysBuffer!=null)
			return getSubSetKeys().length;
		return 0;		
	}
	/**
	 * Gets the properties of this node
	 * @return <code>Properties</code> the {@link Properties} object
	 */
	public Properties getProperties() {
		return getNodeProperties();
	}	
	/**
	 * Gets the subset of properties for the given keySubSet of the stored {@link Properties} object
	 * @param keySubSet The key of the local child {@link Properties} object
	 * @return <code>Properties</code> the stored {@link Properties} object
	 */
	public Properties getSubSet(String keySubSet) {
		return getSubSets().get(keySubSet);
	}
	/**
	 * Searches in the stored subset of properties specified by the keySubSet for the property 
	 * with the specified key in its property list. 
	 * The method returns the default value argument if the property is not found.
	 *  
	 * @param keySubSet the hashtable key of the stored properties
	 * @param key the hashtable key of the properties value
	 * @param defaultValue the default value if the key is not found
	 * @return <code>String</code> the value in this property list with the specified key value. 
	 */
	public String getSubSetProperty(String keySubSet, String key, String defaultValue) {
		return getSubSet(keySubSet).getProperty(key, defaultValue);
	}			
	/**
	 * Searches for the property with the specified key in this property list. 
     * The method returns the default value argument if the property 
	 * is not found.
	 *  
	 * @param key the hashtable key of the properties value
	 * @return <code>String</code> the value in this property list with the specified key value 
	 */
	public String getProperty(String key) {
		return getProperty(key, null);
	}
	/**
	 * Searches for the property with the specified key in this property list. 
	 * The method returns the default value argument if the property 
	 * is not found.
	 *  
	 * @param key the hashtable key of the properties value
	 * @param defaultValue the default value if the key is not found
	 * @return <code>String</code> the value in this property list with the specified key value 
	 */	
	public String getProperty(String key, String defaultValue) {
		return getNodeProperties().getProperty(key, defaultValue);
	}		
	
	/* ========================================== */
	// Constructors
	/* ========================================== */
	public PropertiesNode(Properties properties) throws PropertiesNodeException {
		this(null, properties);		
	}
	
	public PropertiesNode(StringBuffer treePath, Properties properties) throws PropertiesNodeException {
		setTreePath(treePath);		
		setNodePropertiesPath(Prop4j.ROOT.value());
		setNodeProperties(properties);
		loadProperties();		
	}	
	
	public PropertiesNode(String nodePropertiesPath) throws PropertiesNodeException {
		this(null,nodePropertiesPath);
	}
	
	public PropertiesNode(StringBuffer treePath, String nodePropertiesPath) throws PropertiesNodeException {
		setTreePath(treePath);
		setNodePropertiesPath(nodePropertiesPath);
		try {
			setNodeProperties(PropertiesLoader.load(getPath()));
		} catch (FileNotFoundException e) {
			throw new PropertiesNodeException(e);
		} catch (IOException e) {
			throw new PropertiesNodeException(e);
		}
		loadProperties();		
	}	
	/* ------------------------------------------ */	
	// Loader Method
	/* ------------------------------------------ */
	private void loadProperties() throws PropertiesNodeException {
		loadSubSets();		
		loadNodes();		
	}

	/* ------------------------------------------ */
	//Create local subsets Properties
	/* ------------------------------------------ */	
	private void loadSubSets() {
		// checks for local children nodes separates by comma (ex.: shared, local, 58, 59, ...) 
		String[] nodes=Convert.stringToArray(getProperty(Prop4j.NODES.value(),null),',');
		// if there are local nodes with prop4j conf
		if (nodes.length>0) {
			// loop through the key nodes and add element to the specified propeties
			String prefix, prefixDot;			
			for (int i=0, j=nodes.length;i<j;i++) {
				prefix = nodes[i];
				prefixDot = prefix+"."; 				
				Properties tmp = new Properties();
				//save the local nodes in separate properties removing the prefix of local properties key
				Enumeration<Object> e = getNodePropertiesKeys();
				// loop through the element (every time we must use a scan table)
				// Idea: for performance we must create a clone and remove from it each element that match the searched key
				while (e.hasMoreElements()) {
					// get the key of the element
					String key = (String) e.nextElement();
					// if the key start with the saved prefixDot
					if (key.startsWith(prefixDot)) {
						// save the property in the temporary properties object
						tmp.setProperty(key.substring(prefixDot.length()), getNodeProperties().getProperty(key));
					}
				}
				// save local key
				getSubSetsKeysBuffer().append(',').append(prefix);				
				// put the new local child property object in the hashMap
				putSubSet(prefix, tmp);
				tmp=null;
			}
		}
		// remove the first comma char from the local key buffer
		if (subSetsKeysBuffer!=null) getSubSetsKeysBuffer().deleteCharAt(0);		
	}
	/* ------------------------------------------ */
	// Loader of external node Properties
	/* ------------------------------------------ */	
	private void loadNodes() throws PropertiesNodeException {
		// loops through the LocalChildrenNode properties and looking for prop4j.path keyword
		Iterator<String> i = getSubSetsIterator();
		String localKey; // local child node key
		String tempKey; // temporary key for the local property of the child node
		String externalKey; // external child node key		
		String path; // temporary string path 
		while ( i.hasNext ()  )  {
			localKey = i.next(); // store the key of this local node property
			// get the properties enumeration from the local child HashMap
			Enumeration<Object> e = getSubSetsKeys(localKey);
			// loop through the element (every time we must use a scan table for every local child property)
			while (e.hasMoreElements()) {
				// get the key of the element
				tempKey = (String) e.nextElement();
				// if the key ends with the prop4j.path words
				if (tempKey.endsWith(Prop4j.PATH.value())) {
					// save the external child properties key=localKey + tempKey without ".prop4j.path" value
					externalKey=localKey+"."+tempKey.substring(0, tempKey.length()-Prop4j.PATH.value().length());
					// save the external key tree node
					getNodeKeysBuffer().append(',').append(externalKey);;					
					// we have an external child properties
					path=getSubSetProperty(localKey,tempKey,null);
					// save the external child properties
					putNode(externalKey,new PropertiesNode(path));
				}
			}			
	    }
		// remove the first comma char from the external key buffer
		if (nodeKeysBuffer!=null) getNodeKeysBuffer().deleteCharAt(0);		
	}
}
