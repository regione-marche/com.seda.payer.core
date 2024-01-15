/**
 * 
 */
package com.seda.commons.properties.tree;

/**
 * @author dbadm
 *
 */
public enum Prop4j { NODES("prop4j.nodes"), PATH(".prop4j.path"), ROOT("prop4j.root"), THIS("prop4j.this");
	
	
	private String value;
	public String value() {return value;}

	Prop4j(String value) {this.value=value;}
}
