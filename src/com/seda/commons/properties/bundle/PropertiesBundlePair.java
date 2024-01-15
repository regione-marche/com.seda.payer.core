/**
 * 
 */
package com.seda.commons.properties.bundle;

/**
 * @author dbadm
 *
 */
public class PropertiesBundlePair {

	public String key;
	public String path;
	
	public PropertiesBundlePair(){};
	
	public PropertiesBundlePair(String key, String path) {
		this.key=key;
		this.path=path;
	}
	
}
