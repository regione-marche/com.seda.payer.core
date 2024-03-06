/**
 * 
 */
package com.seda.commons.io.textfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * @author f.ricci
 *
 */
public class ReaderSupport {
	private BufferedReader reader;
	private FileReader fileReader;
	
	public ReaderSupport(File file) throws IOException {
		fileReader=new FileReader(file);
		reader=new BufferedReader(fileReader);
	}
	
	public String read() throws IOException {
		if (reader!=null) {
			return reader.readLine();
		} 
		return null;
	}		
	
	public void close()  {
		if (reader!=null) {		
			try {
				fileReader.close();
				reader.close();
			} catch (IOException x) {
				reader=null;
			}
		}
	}
}
