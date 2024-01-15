/**
 * 
 */
package com.seda.commons.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Seda Lab
 *
 */
public class Streamer {
	// Questa classe usa la bufferizzazione fissata nella classe Streamer.
	// Una evoluzione sarebbe quella di basarsi su delle statistiche deterministiche
	// in modo che, se il file supera una certa dimensione:
	// si fa una esecuzione in LIST della classe,
	// si calcola il numero di file, cartelle, e dimesioni
	// si calcola il giusto buffer per riuscire più velocemente ad eseguire l'unzip
    private int buffer=32768;
    
	public int getBuffer() {
		return buffer;
	}
	public void setBuffer(int buffer) {
		this.buffer = buffer;
	}

	public int write(InputStream input, OutputStream output) throws IOException {
	    int count, bytes=0;		
	    byte data[] = new byte[getBuffer()];
	    while ((count = input.read(data, 0, getBuffer())) != -1) {
	    	bytes+=count;
	    	output.write(data, 0, count);
	    }
	    return bytes;
	}

	public int write(InputStream input, File outputFile) throws IOException {
		OutputStream outputStream = null;
		outputStream = new FileOutputStream(outputFile);
		try {
			return write(input, outputStream);
		} finally {
			if (outputStream!=null) {
				outputStream.close();
			}
		}
	}	
	
	public int write(File inputFile, OutputStream output) throws IOException {
		InputStream inputStream = null;
		inputStream = new FileInputStream(inputFile);
		try {
			return write(inputStream, output);
		} finally {
			if (inputStream!=null) {
				inputStream.close();
			}
		}
	}		
	
	public int write(InputStream input, String output) throws IOException {
		return write(input, new File(output));
	}	
	
	public int write(String input, OutputStream output) throws IOException {
		return write(new File(input), output);
	}
}
