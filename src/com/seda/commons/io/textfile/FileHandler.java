/**
 * 
 */
package com.seda.commons.io.textfile;

import java.io.File;
import java.io.IOException;

import com.seda.commons.string.Pad;

/**
 * @author f.ricci
 *
 */
public class FileHandler {

	private String filePath;
	private File file;
	private ReaderSupport reader = null;
	private WriterSupport writer = null;
	private boolean allocated=false;
	private FileStatus fileStatus = FileStatus.NO_FURTHER_INFO;
	private OpenStatus openStatus = OpenStatus.CLOSED;	
	private int fixed=-1;
	
	public OpenStatus getOpenStatus() {
		return openStatus;
	}
	public FileStatus getFileStatus() {
		return fileStatus;
	}
	public String getFilePath() {
		return filePath;
	}		
	public void setFixed(int fixlen) {
		if (fixlen<=0) {
			throw new FileException("You can not fix a negative or zero record length value.");
		}
		if (allocated && openStatus.equals(OpenStatus.CLOSED)) {
			fixed=fixlen;			
		} else {
			throw new FileException("You can not fix a file not allocated or not in closed state.");
		}
	}
	
	public FileHandler (){}
	
	public FileHandler (String filePath) {
		allocate(filePath);
	}
	
	public void allocate(String filePath) {
		if (!allocated) {
			file = new File(filePath);
			allocated=true;
		} else {
			throw new FileException("Already allocated to " + filePath);
		}
	}
	
	public void openOuput() {
		checkOpen();
		try {
			writer = new WriterSupport(file);
		} catch (IOException e) {
			fileStatus=FileStatus.IO_EXCEPTION;
			throw new FileException(e.getMessage(), e);
		}
		openStatus=OpenStatus.OUTPUT;
	}
	
	public void openInput() {
		checkOpen();		
		try {
			reader = new ReaderSupport(file);
		} catch (IOException e) {
			fileStatus=FileStatus.IO_EXCEPTION;
			throw new FileException(e.getMessage(), e);
		}
		openStatus=OpenStatus.INPUT;
	}

	public void write(String line) {
		if (openStatus.equals(OpenStatus.OUTPUT)) {
			if (line!=null) {
				try {
					checkFixed(line);
					writer.write(line);
				} catch (IOException e) {
					close();
					throw new FileException(e.getMessage(), e);
				}
			} else {
				fileStatus=FileStatus.RECORD_OVERFLOW;
			}
		} else {
			fileStatus=FileStatus.NO_OUTPUT_MODE;
		}
	}
	
	public String read() {
		String line=null;
		if (openStatus.equals(OpenStatus.INPUT)) {
			try {
				line=reader.read();
				if (line!=null) {
					checkFixed(line);
				} else {
					if (fileStatus.equals(FileStatus.NO_FURTHER_INFO))
						fileStatus=FileStatus.EOF;
					else if (fileStatus.equals(FileStatus.EOF)) {
						fileStatus=FileStatus.NO_NEXT_RECORD;
					}
				}
			} catch (IOException e) {
				close();				
				throw new FileException(e.getMessage(), e);
			}
		} else {
			fileStatus=FileStatus.NO_INPUT_MODE;
		}
		return line;
	}	
	
	public void close() {
		switch (openStatus) {
		case CLOSED:
			fileStatus = FileStatus.NO_OPENED;
			break;
		case OUTPUT:
			writer.close();
			openStatus=OpenStatus.CLOSED;
			fileStatus = FileStatus.NO_FURTHER_INFO;
			break;
		case INPUT:
			reader.close();
			openStatus=OpenStatus.CLOSED;
			fileStatus = FileStatus.NO_FURTHER_INFO;
			break;			
		}
	}
	
	
	private void checkOpen() {
		if (!allocated) {
			throw new FileException("File not allocated.");
		}
		if (!openStatus.equals(OpenStatus.CLOSED)) {
			fileStatus=FileStatus.ALREADY_OPENED;
			throw new FileException(fileStatus);
		}		
	}
	
	private void checkFixed(String line) {
		if (fixed>0) {
			if (line.length()<fixed) {
				fileStatus=FileStatus.RECORD_OVERFLOW;
				line=Pad.left(line, fixed, ' ');							
			} else if (line.length()>fixed) {
				fileStatus=FileStatus.RECORD_OVERFLOW;
				line=line.substring(fixed);							
			}					
		}
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (writer!=null) writer.close();
		if (reader!=null) reader.close();
	}

}
