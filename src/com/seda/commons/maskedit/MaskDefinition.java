/**
 * 
 */
package com.seda.commons.maskedit;

import com.seda.commons.string.Remove;

/**
 * @author dbadm
 *
 */
public class MaskDefinition {

	private char[] definition;
	private String maskRule;

	public char[] getDefinition() {
		return definition;
	}
	public String getMaskRule() {
		return maskRule;
	}

	
	public MaskDefinition() {
	}	
	
	public MaskDefinition(String maskRule) throws MaskEditException {
		setMaskRule(maskRule);
	}
	
	public boolean contains(char c) {
		return String.valueOf(definition).contains(String.valueOf(c));
	}
	
	public void setMaskRule(String maskRule) throws MaskEditException {
		// checks if maskRule starts with [ and ends with ] 
		if (maskRule.startsWith("[") && maskRule.endsWith("]")) {
			// normalize the string, removing all bad chars
			String m = normalize(maskRule);
			// checks if maskRule length is greater than 2
			if (m.length() == 0) throw new MaskEditException(Messages.MASKRULE_EMPTY.format());
			// load the definition of maskRule
			loadDefinition(m);
		} else {
			throw new MaskEditException(Messages.MASKRULE_FORMAT.format());
		}
		this.maskRule = maskRule;
	}

	private void loadDefinition(String rules) throws MaskEditException {
		definition = rules.toCharArray();
	}
	
	private String normalize(String maskRule) {
		return Remove.charArray(maskRule,MaskMetaData.CHARS_OUT_DEFINITION);
	}
}
