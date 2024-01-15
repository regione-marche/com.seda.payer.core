/**
 * 
 */
package com.seda.tag.core;

/**
 * @author f.ricci
 *
 */
public class HtmlUtil {
	
	public static boolean isRequired(String sValidator) {
		if (sValidator!=null) {
			return sValidator.contains("required");
		}
		return false;		
	}
	
	public static String getRequired() {
		return "<strong title=\"Richiesto\" class=\"seda-ui-required\">*</strong>";
	}
	
}
