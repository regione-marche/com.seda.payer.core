/**
 * 
 */
package com.seda.commons.fillgener;

/**
 * @author dbadm
 *
 */
public class FillDefinition {

	private int beginByte=-1;
	private int endByte=-1;
	
	private String fixedValue=null;
	public boolean isFixedValue() {
		return fixedValue==null?false:true;
	}

	public int getBeginByte() {
		return this.beginByte;
	}
	public void setBeginByte(int beginByte) throws FillGenerException {
		// if provided begin byte is a valid begin byte
		if (beginByte > 0) {
			// save the begin byte
			this.beginByte = beginByte;			
			// if end byte is present
			if (this.endByte > 0) {
				// check if begin byte is not greater than end byte
				if (beginByte > this.endByte) {
					// set the end byte to the begin byte
					setEndByte(beginByte);
				}
			} else {
				// set the end byte to the begin byte
				setEndByte(beginByte);				
			}
		} else {
			// throw exception
			throw new FillGenerException(Messages.INVALID_BYTE.format("begin"));			
		}
		// set the value to null
		resetDefaultFixedValue();		
	}
	
	public int getGapByte() {
		// if is a fixed value return the length of the value string
		if (isFixedValue()) {
			return this.fixedValue.length();
		}
		// if is not a fixed value return the difference between end byte and begin byte
		if (this.endByte>0 && this.beginByte>0) {
			return this.endByte - this.beginByte;			
		}
		// if we don't have begin and end byte return -1
		return -1;
	}

	public void setGapByte(int gapByte) throws FillGenerException {
		// if provided gap byte is a valid gap byte		
		if (gapByte>0) {
			// if begin byte is not present
			if (this.beginByte < 0) {
				// set the begin byte to 1
				setBeginByte(1);	
			}
			// calculate new end byte
			setEndByte(this.beginByte + gapByte - 1);
		} else {
			// throw exception
			throw new FillGenerException(Messages.INVALID_BYTE.format("gap"));			
		}
	}

	public int getEndByte() {
		return this.endByte;
	}
	public void setEndByte(int endByte) throws FillGenerException {
		// if provided end byte is a valid end byte		
		if (endByte > 0) {
			// save the end byte
			this.endByte = endByte;			
			// if begin byte is present 		
			if (this.beginByte > 0) {
				// check if begin byte is not greater than end byte
				if (this.beginByte > endByte) {
					// set the begin byte to the end byte				
					setBeginByte(endByte);					
				}
			} else {
				// if the begin byte is not present set the begin byte to the end byte 
				setBeginByte(endByte);							
			}
		} else {
			// throw exception
			throw new FillGenerException(Messages.INVALID_BYTE.format("end"));			
		}
	}
	
	public String getFixedValue() {
		return this.fixedValue;
	}
	
	public void setFixedValue(String fixedValue) throws FillGenerException {
		// if the passed value is null
		if (fixedValue==null) {
			// throw exception
			throw new FillGenerException(Messages.INVALID_VALUE.format());
		}
		// reset the positional byte
		resetDefaultByte();
		// save the provided value
		this.fixedValue = fixedValue;
	}

	public int beginIndex() {
		if (beginByte > 0) return beginByte - 1;
		return -1;
	}
	public int endIndex() {
		if (endByte > 0) return endByte;
		return -1;
	}
	
	private void resetDefaultByte() {
		this.beginByte = -1;
		this.endByte = -1;
	}
	
	private void resetDefaultFixedValue() {
		this.fixedValue = null;
	}	
	
	public FillDefinition(int beginByte, int gapByte) throws FillGenerException {
		setBeginByte(beginByte);
		setGapByte(gapByte);		
	}
	
	public FillDefinition(String fixedValue) throws FillGenerException {
		setFixedValue(fixedValue);
	}
	
	public FillDefinition() {}
	
	private void preApplicationByte(String s) throws FillGenerException {
		// if the provided string is null
		if (s==null) {
			// throw exception
			throw new FillGenerException(Messages.INPUT_STRING_NULL.format());
		}
		// if the begin or end byte are not specified
		if (beginByte < 1) {
			// throw exception
			throw new FillGenerException(Messages.BYTE_NOT_ALLOWED.format("begin"));			
		}
		if (endByte < 1) {
			// throw exception
			throw new FillGenerException(Messages.BYTE_NOT_ALLOWED.format("end"));			
		}		
		// if the begin byte is not included in the string length
		if (beginByte > s.length()) {
			// throw exception
			throw new FillGenerException(Messages.BYTE_NOT_INCLUDED.format("begin", beginByte, s.length()));			
		}
		// if the end byte is not included in the string length
		if (endByte > s.length()) {
			// throw exception
			throw new FillGenerException(Messages.BYTE_NOT_INCLUDED.format("end", endByte, s.length()));			
		}		
	}	
	
	public String apply(String s) throws FillGenerException {
		// if the definition is a definition with fixed value
		// ignore the input string and return the fixed value
		if (isFixedValue())	return getFixedValue();
		// execute pre-application byte modification controls
		preApplicationByte(s);
		// apply the filler generation definition
		return applyByte(s);
	}
	
	private String applyByte(String s) {
		return s.substring(beginIndex(), endIndex());
	}

}
