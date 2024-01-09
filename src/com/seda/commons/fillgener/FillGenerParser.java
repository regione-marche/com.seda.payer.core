/**
 * 
 */
package com.seda.commons.fillgener;

import java.util.Collection;
import java.util.LinkedList;

import com.seda.commons.string.Remove;

/**
 * @author dbadm
 *
 */
public class FillGenerParser {

	public static final char[] CHARS_OUT_DEFINITION = {'[',']'};

	public static final String SUBMASK_SEPARATOR = ";";	
	public static final String SUBMASK_REGEX = "\\" + SUBMASK_SEPARATOR;	
	
	public static final String BYTE_SEPARATOR = ",";
	public static final String BYTE_REGEX = "\\" + BYTE_SEPARATOR;
	
	private static String removeOutDefinition(String mask) {
		// remove the character out of valid definition		
		return Remove.charArray(mask, FillGenerParser.CHARS_OUT_DEFINITION);
	}
	
	private static boolean isFixedValue(String mask) {
		// check if it starts and ends with brackets		
		return mask.startsWith("[") && mask.endsWith("]");
	}
	private static boolean isMultipleDefinition(String mask) {
		// check if it contains sub mask separator		
		return mask.contains(FillGenerParser.SUBMASK_SEPARATOR);
	}
	private static boolean isByteDefinition(String mask) {
		// check if it contains byte separator		
		return mask.contains(FillGenerParser.BYTE_SEPARATOR);
	}			
	
	private static FillDefinition doByteDefinition(String mask) throws FillGenerException {
		// find and return the two integer of a byte definition
		String[] s = mask.split(FillGenerParser.BYTE_REGEX);
		// if the length of array is two (the two integer byte)
		if (s.length == 2) {
			// parse the integer in the array and return
			try {
				// return array
				return new FillDefinition(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
			} catch (Exception e) {
				throw new FillGenerException(e);
			}
		} else {
			// this means that the array contains more than two hypothetical byte indicators
			throw new FillGenerException(Messages.INVALID_SUBMASKDEFINITION.format(mask));			
		}
	}
	
	private static FillDefinition doFixedDefinition(String mask) throws FillGenerException {
		// remove from fixed mask all chars out definition
		return new FillDefinition(removeOutDefinition(mask));
	}	
	
	private static FillDefinition doFillDefinition(String mask) throws FillGenerException {
		// if is fixed value
		if (isFixedValue(mask)) {
			// add a new fixed value definition to the e collection
			return doFixedDefinition(mask);						
		} else {
			// check if is an hypothetical byte definition
			if (isByteDefinition(mask)) {
				// return byte definition
				return doByteDefinition(mask);							
			} else {
				// this means that the sub mask definition is not a valid definition
				throw new FillGenerException(Messages.INVALID_SUBMASKDEFINITION.format(mask));							
			}
		}		
	}
	
	private static void validate(String maskDefinition) throws FillGenerException {
		// if provided definition is null
		if (maskDefinition==null)
			// throw exception
			throw new FillGenerException(Messages.MASKDEFINITION_NULL.format(maskDefinition));
	}
	
	public static Collection<FillDefinition> parse(String maskDefinition) throws FillGenerException {
		// Validate the provided mask definition
		validate(maskDefinition);
		// define the return collection
		Collection<FillDefinition> collection = new LinkedList<FillDefinition>();
		// if is a fixed value
		if (isFixedValue(maskDefinition)) {
			// this means that the definition is a only one fixed value generator definition
			collection.add(doFixedDefinition(maskDefinition));
		} else {
			// check if it is a multiple definition with sub mask rules
			if (isMultipleDefinition(maskDefinition)) {
				// split the string in an array of hypothetical generator definitions
				String[] def = maskDefinition.split(FillGenerParser.SUBMASK_REGEX);
				// loop through the array definitions
				for (int i = 0, j=def.length; i < j; i++) {
					// add the new sub mask definition to the collection
					collection.add(doFillDefinition(def[i]));
				}
			} else {
				// check if is a byte definition
				if (isByteDefinition(maskDefinition)) {	
					collection.add(doByteDefinition(maskDefinition));
				} else {
					// this means that the maskDefinition is not a valid definition
					throw new FillGenerException(Messages.MASKDEFINITION_NULL.format(maskDefinition));					
				}
			}
		}
		// return the collection
		return collection;
	}
}
