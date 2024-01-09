/**
 * 
 */
package com.seda.commons.io.textfile;

/**
 * @author f.ricci
 *
 */
public enum FileStatus {
	NOT_ALLOCATED("NA","Not allocated to a specific file path."),	
//	ASSIGNED("AS","This file was assigned to a specific file path."),	
	ALREADY_ALLOCATED("AA","Already allocated."),	
	IO_EXCEPTION("IO","Last operation results in IOException."),	
	GENERAL_EXCEPTION("GE","Last operation results in a general Exception."),	
	NO_FURTHER_INFO("00","No further information."),
	RECORD_OVERFLOW("04","The length of the record just read or write did not conform to the fixed file attributes for the file."),
	EOF("10","A sequential READ was attempted after end of file was reached."),
	ALREADY_OPENED("41","An OPEN statement was attempted for a file in the open mode."),
	NO_OPENED("42","A CLOSE was attempted for a file not in the open mode."),
	NO_NEXT_RECORD("46","A sequential READ was attempted but no valid next record had been established."),
	NO_INPUT_MODE("47","A READ was attempted for a file not open in the input or I-O mode."),
	NO_OUTPUT_MODE("48","A WRITE was attempted on a file not open in the I-O, output, or extend mode.")

	;
	
	private String status;
	private String meaning;
	
	public String getStatus() {
		return status;
	}
	
	public String getMeaning() {
		return meaning;
	}	
	
	FileStatus(String status, String meaning) {
		this.status=status;
		this.meaning=meaning;
	}
	
	public String toString() {
		return status + "=" + meaning;
	}
}
