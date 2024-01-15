/**
 * 
 */
package com.seda.commons.properties;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Seda Lab
 *
 */
public class ResourceManager {

	/**
	 * Returns an {@link InputStream} object that refers to an internal jar resource.
	 * 
	 * @param jarPath the path of an internal jar resource
	 * @return <code>{@link InputStream}</code> the input stream object
	 * @throws IOException if an I/O exception occurs
	 */
	public InputStream getInputStreamFromJar(String jarPath) throws IOException {
    	URL url = getClass().getResource(jarPath);
    	InputStream is = null;
   		is = url.openStream();
    	return is;
	}	

}
