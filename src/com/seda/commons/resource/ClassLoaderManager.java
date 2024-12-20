/**
 * 
 */
package com.seda.commons.resource;

import java.io.InputStream;
import java.net.URL;

/**
 * A class to wrap access to multiple class loaders making them work as one
 * 
 * @author f.ricci
 *
 */
public class ClassLoaderManager {

	ClassLoader defaultClassLoader;

	ClassLoaderManager() {
	}

	/**
	 * Get a resource as a URL using the current class path
	 *
	 * @param resource - the resource to locate
	 * @return the resource or null
	 */
	public URL getResourceAsURL(String resource) {
		return getResourceAsURL(resource, new ClassLoader[]{
				defaultClassLoader,
				Thread.currentThread().getContextClassLoader(),
				getClass().getClassLoader(),
				ClassLoader.getSystemClassLoader()
		});
	}

	/**
	 * Get a resource from the classpath, starting with a specific class loader
	 *
	 * @param resource    - the resource to find
	 * @param classLoader - the first classloader to try
	 * @return the stream or null
	 */
	public URL getResourceAsURL(String resource, ClassLoader classLoader) {
		return getResourceAsURL(resource, new ClassLoader[]{
				classLoader,
				defaultClassLoader,
				Thread.currentThread().getContextClassLoader(),
				getClass().getClassLoader(),
				ClassLoader.getSystemClassLoader()
		});
	}

	/**
	 * Get a resource from the classpath
	 *
	 * @param resource - the resource to find
	 * @return the stream or null
	 */
	public InputStream getResourceAsStream(String resource) {
		return getResourceAsStream(resource, new ClassLoader[]{
				defaultClassLoader,
				Thread.currentThread().getContextClassLoader(),
				getClass().getClassLoader(),
				ClassLoader.getSystemClassLoader()
		});
	}

	/**
	 * Get a resource from the classpath, starting with a specific class loader
	 *
	 * @param resource    - the resource to find
	 * @param classLoader - the first class loader to try
	 * @return the stream or null
	 */
	public InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
		return getResourceAsStream(resource, new ClassLoader[]{
				classLoader,
				defaultClassLoader,
				Thread.currentThread().getContextClassLoader(),
				getClass().getClassLoader(),
				ClassLoader.getSystemClassLoader()
		});
	}

	/**
	 * Find a class on the classpath (or die trying)
	 *
	 * @param name - the class to look for
	 * @return - the class
	 * @throws ClassNotFoundException Duh.
	 */
	public Class<?> classForName(String name) throws ClassNotFoundException {
		return classForName(name, new ClassLoader[]{
				defaultClassLoader,
				Thread.currentThread().getContextClassLoader(),
				getClass().getClassLoader(),
				ClassLoader.getSystemClassLoader()
		});
	}

	/**
	 * Find a class on the classpath, starting with a specific classloader (or die trying)
	 *
	 * @param name        - the class to look for
	 * @param classLoader - the first classloader to try
	 * @return - the class
	 * @throws ClassNotFoundException Duh.
	 */
	public Class<?> classForName(String name, ClassLoader classLoader) throws ClassNotFoundException {
		return classForName(name, new ClassLoader[]{
				classLoader,
				defaultClassLoader,
				Thread.currentThread().getContextClassLoader(),
				getClass().getClassLoader(),
				ClassLoader.getSystemClassLoader()
		});
	}

	/**
	 * Try to get a resource from a group of classloaders
	 *
	 * @param resource    - the resource to get
	 * @param classLoader - the classloaders to examine
	 * @return the resource or null
	 */
	private InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
		for (ClassLoader cl : classLoader) {
			if (null != cl) {
				// try to find the resource as passed
				InputStream returnValue = cl.getResourceAsStream(resource);
				// now, some class loaders want this leading "/", so we'll add it and try again if we didn't find the resource
				if (null == returnValue) returnValue = cl.getResourceAsStream("/" + resource);

				if (null != returnValue) return returnValue;
			}
		}
		return null;
	}

	/**
	 * Get a resource as a URL using the current class path
	 *
	 * @param resource    - the resource to locate
	 * @param classLoader - the class loaders to examine
	 * @return the resource or null
	 */
	private URL getResourceAsURL(String resource, ClassLoader[] classLoader) {
		URL url;
		for (ClassLoader cl : classLoader) {
			if (null != cl) {
				// look for the resource as passed in...
				url = cl.getResource(resource);
				// ...but some class loaders want this leading "/", so we'll add it
				// and try again if we didn't find the resource
				if (url == null) url = cl.getResource("/" + resource);
				// "It's always in the last place I look for it!"
				// ... because only an idiot would keep looking for it after finding it, so stop looking already.
				if (url != null) return url;
			}
		}
		// didn't find it anywhere.
		return null;
	}

	/**
	 * Attempt to load a class from a group of classloaders
	 *
	 * @param name        - the class to load
	 * @param classLoader - the group of classloaders to examine
	 * @return the class
	 * @throws ClassNotFoundException - Remember the wisdom of Judge Smails: Well, the world needs ditch diggers, too.
	 */
	private Class<?> classForName(String name, ClassLoader[] classLoader) throws ClassNotFoundException {
		for (ClassLoader cl : classLoader) {
			if (null != cl) {
				try {
					Class<?> c = Class.forName(name, true, cl);
					if (null != c) return c;
				} catch (ClassNotFoundException e) {}
			}
		}
		throw new ClassNotFoundException("Class not found: " + name);
	}	
	
}
