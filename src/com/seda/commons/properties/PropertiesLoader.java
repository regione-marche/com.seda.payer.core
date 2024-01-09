package com.seda.commons.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author SEDA Lab
 */
public class PropertiesLoader {

	/**
	 * Loads {@link Properties} object with key-value pair from properties file.
	 * @param fullPath - It's the path of the properties file.
	 * @return <code>Properties</code> - The object of type {@link Properties}.
	 * @throws FileNotFoundException In case the file doesn't exist we catch this exception.
	 * @throws IOException Catches generic input-output exceptions.
	 */
	public synchronized final static Properties load(String fullPath) throws FileNotFoundException, IOException {
		return PropertiesLoader.load(new FileInputStream(fullPath));		
	}
	
	/**
	 * Loads {@link Properties} object with key-value pair from properties file.
	 * @param file - The object type {@link File} of the properties file.
	 * @return <code>Properties</code> - The object of type {@link Properties}.
	 * @throws FileNotFoundException In case the file doesn't exist we catch this exception.
	 * @throws IOException Catches generic input-output exceptions.
	 */
	public synchronized final static Properties load(File file) throws FileNotFoundException, IOException {
		return PropertiesLoader.load(new FileInputStream(file));
	}
	
	/**
	 * Loads {@link Properties} object with key-value pair from properties file.
	 * @param inStream - The object type {@link FileInputStream} of the properties file.
	 * @return <code>Properties</code> - The object of type {@link Properties}.
	 * @throws FileNotFoundException In case the file doesn't exist we catch this exception.
	 * @throws IOException Catches generic input-output exceptions.
	 */
	public synchronized final static Properties load(FileInputStream inStream) throws FileNotFoundException, IOException {
		Properties properties=null;
		properties = new Properties();
		properties.load(inStream);
		return properties;
	}
	
}
