/**
 * 
 */
package com.seda.commons.io.textfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author f.ricci
 *
 */
public class WriterSupport {

	private BufferedWriter writer;
	private FileWriter fileWriter;
	
	public WriterSupport(File file) throws IOException {
		fileWriter=new FileWriter(file);
		writer=new BufferedWriter(fileWriter);
	}
	
	public void write(String line) throws IOException {
		if (writer!=null) {
			writer.write(line);
			writer.newLine();  			
		}
	}		
	
	public void close()  {
		if (writer!=null) {		
			try {
				writer.close();				
				fileWriter.close();
			} catch (IOException x) {
				writer=null;
			}
		}
	}
	
}
