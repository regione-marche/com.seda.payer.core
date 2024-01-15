package com.seda.commons.properties.bundle;

/**
 * @author SEDA Lab
 */
public class SingletonPropertiesBundle extends PropertiesBundle{

	static private SingletonPropertiesBundle INSTANCE;
	
	/**
	 * Protected constructor is sufficient to suppress unauthorized calls to the constructor.
	 */
	protected SingletonPropertiesBundle() {}
	/**
	 * Creates Instance, if doesn't exist.
	 * @return <code>SingletonPropertiesBundle</code> - PropertiesBundle is returned (SingletonPropertiesBundle extends PropertiesBundle).
	 */
	private static synchronized SingletonPropertiesBundle tryCreateInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SingletonPropertiesBundle();
	    }
	    return INSTANCE;
	}
	/**
	 * Gets Instance. If doesn't exist, it creates it.
	 * @return <code>SingletonPropertiesBundle</code> - PropertiesBundle is returned (SingletonPropertiesBundle extends PropertiesBundle).
	 */
	public static SingletonPropertiesBundle getInstance() {
		// use local variable, don't issue 2 reads (memory fences) to 'INSTANCE'
		SingletonPropertiesBundle s = INSTANCE;
		if (s == null) {
			//check under lock; move creation logic to a separate method to allow inlining of getInstance()
		    s = tryCreateInstance();
		}
		return s;
	}
}
