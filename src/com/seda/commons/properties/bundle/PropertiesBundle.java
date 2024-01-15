package com.seda.commons.properties.bundle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import com.seda.commons.properties.PropertiesLoader;
/**
 * @author SEDA Lab
 */
public class PropertiesBundle {
	/**
	 * It's a HashMap.
	 */
	private Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
	/**
	 * Loads into HashMap a system variable (operating system or websphere environment variable) as key and it's Properties object as value.
	 * @param sysVariable - An operating system or websphere environment variable as key.
	 * @throws PropertiesBundleException In case file not found or input-output generic exception.
	 */
	public void loadProperties(String sysVariable) throws PropertiesBundleException {
		try {
			propertiesMap.put(sysVariable, PropertiesLoader.load(System.getenv().get(sysVariable)));
		} catch (FileNotFoundException e) {
			throw new PropertiesBundleException(e);
		} catch (IOException e) {
			throw new PropertiesBundleException(e);
		}
	}

	/**
	 * Loads into HashMap a properties object from a operating system file.
	 * @param key - The key of the properties object.	  
	 * @param propertiesPath - An operating system path that refer to the properties file.
	 * @throws PropertiesBundleException In case file not found or input-output generic exception.
	 */
	public void loadProperties(String key, String propertiesPath) throws PropertiesBundleException {
		try {
			propertiesMap.put(key, PropertiesLoader.load(propertiesPath));
		} catch (FileNotFoundException e) {
			throw new PropertiesBundleException(e);
		} catch (IOException e) {
			throw new PropertiesBundleException(e);
		}
	}	
	
	/**
	 * Loads into HashMap system variables (operating system or websphere environment variables) as keys and it's Properties objects as values.
	 * @param sysVariable - An array of operating system or websphere environment variables as keys.
	 * @throws PropertiesBundleException In case file or files not found or input-output generic exception.
	 */
	public void loadProperties(String... sysVariable) throws PropertiesBundleException {
		if (sysVariable == null) return;
		for (int i=0, j=sysVariable.length;i<j;i++){
			loadProperties(sysVariable[i]);
		}					
	}
	
	/**
	 * Loads into HashMap a list of properties object from operating system files
	 * @param bundlePairs - An array of {@link PropertiesBundlePair} key-path.
	 * @throws PropertiesBundleException In case file or files not found or input-output generic exception.
	 */
	public void loadProperties(PropertiesBundlePair... bundlePairs) throws PropertiesBundleException {
		if (bundlePairs == null) return;
		for (PropertiesBundlePair propertiesBundlePair : bundlePairs) {
			loadProperties(propertiesBundlePair.key, propertiesBundlePair.path);
		}
	}	
	/**
	 * Gets Properties object of it's corresponding key 
	 * @param keyBundle - The key of the stored bundler
	 * @return <code>Properties</code> - The Properties object type (contains pairs key-value that were read from a properties file).
	 * @throws PropertiesBundleException In case of properties not found for a key.
	 */
	public Properties getProperties(String keyBundle) throws PropertiesBundleException {
		if (propertiesMap.containsKey(keyBundle)) {
			return (Properties)propertiesMap.get(keyBundle);			
		}
		throw new PropertiesBundleException(Messages.PROPERTIES_NOT_FOUND.format(keyBundle));
	}
}
