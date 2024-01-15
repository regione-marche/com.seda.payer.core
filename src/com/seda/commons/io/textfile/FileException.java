/**
 * 
 */
package com.seda.commons.io.textfile;

import java.io.IOException;

/**
 * @author f.ricci
 *
 */
public class FileException extends RuntimeException {

	private FileStatus status;
	
	public FileStatus getFileStatus() {
		return status==null?FileStatus.NO_FURTHER_INFO:status;
	}
	
	public FileException(String message) {
		super(message);
	}
	
	public FileException(String message, IOException x) {
		super(message, x);
		status=FileStatus.IO_EXCEPTION;
	}	
	public FileException(String message, Exception x) {
		super(message, x);
		status=FileStatus.GENERAL_EXCEPTION;
	}	
	
	public FileException(String message, FileStatus status) {
		super(message);
		this.status=status;
	}
	public FileException(FileStatus status) {
		super(status.getMeaning());
		this.status=status;
	}	
}
