/**
 * 
 */
package com.seda.commons.properties.tree;

import java.util.Properties;

/**
 * @author Seda labs
 *
 */
public class TestProp4j {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(args[0]);		
		PropertiesTree tree=null;
		try {
			tree = new PropertiesTree(args[0]);
		} catch (PropertiesNodeException e) {
			e.printStackTrace();
		}
		
		String value = tree.getProperty("bapEntry.datasource/TO:base.name");
		Properties propA = tree.getProperties("bapEntry.log4j/TO.base");
		Properties propB = tree.getProperties("prop4j.this:bapWs");
		
		System.out.println(value);
		System.out.println(propA);		
		System.out.println(propB);
		
		tree=null;
	}

}
