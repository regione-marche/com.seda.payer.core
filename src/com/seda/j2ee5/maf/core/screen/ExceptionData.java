/**
 * 
 */
package com.seda.j2ee5.maf.core.screen;

import java.io.Serializable;
/**
 * @author Seda Lab
 *
 */
public class ExceptionData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String className;
	private String screen;
	private String applid;

	public String getClassName() {
		return className;
	}
	public String getScreen() {
		if (screen!=null) {
			if (screen.startsWith("/")) return screen;
			return "/".concat(screen);			
		}
		return null;
	}
	public String getApplid() {
		return applid;
	}

	public ExceptionData(String className, String screen, String applid) {
		super();
		this.className = className;
		this.screen = screen;
		this.applid = applid;
	}

	@Override
	public String toString() {
		return "ExceptionData [applid=" + applid + ", className=" + className
				+ ", screen=" + screen + "]";
	}
}
