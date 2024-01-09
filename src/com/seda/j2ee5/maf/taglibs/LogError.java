/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

/**
 * @author f.ricci
 *
 */
public class LogError extends LogSupport {

	@Override
	protected void setLevel() {
		this.level=Level.ERROR;
	}

}
