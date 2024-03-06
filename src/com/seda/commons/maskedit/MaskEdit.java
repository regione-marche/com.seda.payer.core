/**
 * 
 */
package com.seda.commons.maskedit;

import static com.seda.commons.maskedit.MaskMetaData.ALPHA;
import static com.seda.commons.maskedit.MaskMetaData.ALPHANUMERIC;
import static com.seda.commons.maskedit.MaskMetaData.BLANK;
import static com.seda.commons.maskedit.MaskMetaData.NUMERIC;

import java.util.HashMap;
import java.util.Map;

import com.seda.commons.string.Pad;

/**
 * @author dbadm
 *
 */
public class MaskEdit {

	private String mask;
	private String value;
	private boolean ignoreBlank=false;
	private boolean ignoreLength=true;
	
	private Map<String, MaskDefinition> definitions = new HashMap<String, MaskDefinition>();
	private MaskBaseDefinitions mbs = new MaskBaseDefinitions();	
	
	private boolean isAlphaMask(char c) {
		return c==mbs.getAlpha();
	}
	private boolean isNumericMask(char c) {
		return c==mbs.getNumeric();
	}
	private boolean isAlphaNumericMask(char c) {
		return c==mbs.getAlphaNumeric();
	}
	private boolean isDefinitionMask(char c) {
		return definitions.containsKey(String.valueOf(c));
	}			

	private boolean isOutOfMask(char c) {
		if (isAlphaMask(c)) return false;
		if (isNumericMask(c)) return false;		
		if (isAlphaNumericMask(c)) return false;
		if (isDefinitionMask(c)) return false;
		return true;
	}
	
	private boolean isAlphaChar(char c) {
		return ALPHA.contains(String.valueOf(c));
	}
	private boolean isNumericChar(char c) {
		return NUMERIC.contains(String.valueOf(c));
	}
	private boolean isAlphaNumericChar(char c) {
		return ALPHANUMERIC.contains(String.valueOf(c));
	}
	private boolean isDefinitionChar(char d, char c) {
		return definitions.get(String.valueOf(d)).contains(c);
	}		
	
	public void setIgnoreBlank(boolean ignoreBlank) {
		this.ignoreBlank=ignoreBlank;
	}
	public boolean isIgnoreBlank() {
		return ignoreBlank;
	}
	public boolean isIgnoreLength() {
		return ignoreLength;
	}
	public void setIgnoreLength(boolean ignoreLength) {
		this.ignoreLength = ignoreLength;
	}
	
	public void replaceMaskBaseDefinitions(char alpha, char numeric, char alphaNumeric) throws MaskEditException {
		if (alpha==numeric || alpha == alphaNumeric || numeric==alphaNumeric) {
			throw new MaskEditException(Messages.BASE_DEFINITION_ERROR.format(alpha,numeric, alphaNumeric));
		}
		mbs.setAlpha(alpha);
		mbs.setNumeric(numeric);		
		mbs.setAlphaNumeric(alphaNumeric);
	}

	public MaskDefinition addMaskDefinition(String key, MaskDefinition maskDefinition) throws MaskEditException {
		return addMaskDefinition(key, maskDefinition, false);
	}
	
    public MaskDefinition addMaskDefinition(String key, MaskDefinition maskDefinition, boolean replace) throws MaskEditException {
    	if (key.length() != 1) throw new MaskEditException(Messages.DEFINITION_KEY_LENGTH.format(key.length()));
    	if (maskDefinition==null) throw new MaskEditException(Messages.DEFINITION_NULL.format(key.length()));
    	if (replace) {
    		if (definitions.containsKey(key)) {
        		return definitions.put(key, maskDefinition);
    		} else {
    			definitions.put(key, maskDefinition);
    			return maskDefinition;
    		}
    	} 
		if (definitions.containsKey(key)) {
	    	return null;
		} 
		definitions.put(key, maskDefinition);    			
		return maskDefinition;
    }
	
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

 	public MaskEdit() {}
 	
 	
 	public MaskEdit(char alpha, char numeric, char alphaNumeric) throws MaskEditException {
 		replaceMaskBaseDefinitions(alpha, numeric, alphaNumeric);
 	}
 	
 	public MaskEdit(String mask) {
 		setMask(mask);
 	}
 	
 	public MaskEdit(String value, String mask) {
 		setValue(value);
 		setMask(mask);
 	}
	
	private boolean preValidation(String value, String mask) {
		// if length of value is not present return false		
		if (value==null || value.length()==0) return false;
		// if length of mask and value are not the same return false 
		if (!ignoreLength && value.length() != mask.length()) return false;
		if (mask==null || mask.length()==0) return true;
		
		return true;
	}
	@Deprecated
	public boolean validate() {
		return validate(getValue(),getMask(),false);
	}
	public boolean validate(String value) {
		return validate(value,getMask(),false);		
	}
	public boolean validate(String value, String mask) {
		return validate(value,mask,true);		
	}
	public boolean validate(String value, String mask, boolean replace) {
		// override existing value and mask
		if (replace) {
			setValue(value);
			setMask(mask);			
		}
		// execute pre-validation
		if (preValidation(value, mask)) {
			if (ignoreLength && mask.length()!=value.length()) {
				// adjust lengths
				if (mask.length()<value.length()) {
					value=value.substring(0,mask.length());
				} else {
					value=Pad.right(value, mask.length());
				}
			}
			// create arrays of char for mask and value to be compared
			return validate(value.toCharArray(), mask.toCharArray());
		}
		//
		return false;
	}
	
	private boolean validate(char[] valueChar,char[] maskChar) {
		// loop through the mask and check the value		
		for (int i=0, j=maskChar.length; i<j; i++) {
			if (isOutOfMask(maskChar[i])) {
				if (maskChar[i]==valueChar[i]) continue;
				else return false;
			}
			if (isDefinitionMask(maskChar[i])) {
				if (isDefinitionChar(maskChar[i], valueChar[i])) continue;
				if (valueChar[i]==BLANK && ignoreBlank) continue;
				return false;
			}			
			if (isAlphaMask(maskChar[i])) {
				if (isAlphaChar(valueChar[i])) continue;
				if (valueChar[i]==BLANK && ignoreBlank) continue;
				return false;
			}
			if (isNumericMask(maskChar[i]))	{
				if (isNumericChar(valueChar[i])) continue;
				if (valueChar[i]==BLANK && ignoreBlank) continue;
				return false;				
			}
			if (isAlphaNumericMask(maskChar[i])) {
				if (isAlphaNumericChar(valueChar[i])) continue;
				if (valueChar[i]==BLANK && ignoreBlank) continue;
				return false;				
			}
		}
		return true;		
	}
	
	public static void main(String[] args) throws MaskEditException {
		String[] tests = new String[]{"081010 0","081010 A 12345","081 10 A 12 45","AA012","A","9"};
		MaskEdit editor = new MaskEdit('^', '#', '*');
		editor.setMask("###### * *****");

		editor.setIgnoreBlank(false);
		editor.setIgnoreLength(false);
		doTests(editor,tests);

		editor.setIgnoreBlank(true);
		editor.setIgnoreLength(false);
		doTests(editor,tests);
		
		editor.setIgnoreBlank(true);
		editor.setIgnoreLength(true);
		doTests(editor,tests);
	}
	
	private static void doTests(MaskEdit editor, String[] tests) {
		System.out.println("> ignoreLenght(" + editor.isIgnoreLength() + ") ignoreBlank ("+editor.isIgnoreBlank()+")");
		for (String t : tests) {
			System.out.println("test '"+t+"' = " + editor.validate(t));
		}
	}
}
