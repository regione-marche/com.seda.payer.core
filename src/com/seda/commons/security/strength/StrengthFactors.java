/**
 * 
 */
package com.seda.commons.security.strength;
/**
 * @author f.ricci
 *
 */
public enum StrengthFactors {

	LENGTH(StrengthLength.class.getName()),
	NUMBER(StrengthNumber.class.getName()),	
	LOW_CASE(StrengthLowcase.class.getName()),
	UPPER_CASE(StrengthUppercase.class.getName()),
	SPECIAL(StrengthSpecial.class.getName())
	;	
	
	private String classname;
	private Class<StrengthSupport> clazz;
	private StrengthSupport support;	
	
	public String getClassName() {
		return classname;
	}
	
	public Class<StrengthSupport> getStrengthSupportClass() {
		return clazz;
	}	
	
	public StrengthSupport getStrengthSupport() {
		return support;
	}	
	
	StrengthFactors(String classname) {
		this.classname=classname;
		try {
			this.clazz = (Class<StrengthSupport>)Class.forName(classname);
			this.support = clazz.newInstance();
		} catch (Exception e) {}
	}
	
	
}
