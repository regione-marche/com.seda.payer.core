/**
 * 
 */
package com.seda.commons.zip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Seda Lab
 *
 */
public class Ziper {
	
	private int level = Deflater.DEFAULT_COMPRESSION; 
	
	private Streamer streamer;
	private SortedSet<String> filesMade;
	
	private String source;
	private String destination;
	
	private Streamer getStreamer() {
		if (streamer==null) {
			streamer=new Streamer();
		}
		return this.streamer;		
	}
	
	public SortedSet<String> getFilesMade() {
		return filesMade;
	}	
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void reset() {
		setLevel(Deflater.DEFAULT_COMPRESSION);
		setSource(null);
		setDestination(null);
	}	
	
	public void compress() throws SecurityException, FileNotFoundException, IOException {
		compress(new ZipOutputStream(new FileOutputStream(getDestination())));
	}
	
	public void compress(ZipOutputStream zipOutputStream) throws SecurityException, IOException {
		preProcessing();
		zipOutputStream.setLevel(getLevel());
		try {
			File f = new File(getSource());
			if (f.isDirectory()) {
				addFolderToZip(null, getSource(), zipOutputStream);				
			} else {
				addFileToZip(null, getSource(), zipOutputStream);
			}

		} finally {
			if (zipOutputStream!=null) {
				zipOutputStream.close();
			}
		}
	}
	
	private void preProcessing() throws IOException {
        filesMade = new TreeSet<String>();		
	}	
	
	public void addFolderToZip(String path, String srcFolder, 
			ZipOutputStream zipOutputStream) throws IOException {
		File f = new File(srcFolder);
		for (String name : f.list()) {
			if (path==null ||
					path.equals("")) {
				addFileToZip(f.getName(), srcFolder + "/" + name , zipOutputStream);
			} else {
				addFileToZip(path + "/" + f.getName(), srcFolder + "/" + name, zipOutputStream);				
			}
		}
	}
	
	public void addFileToZip(String path, String srcFile, 
			ZipOutputStream zipOutputStream) throws IOException {
		 File f = new File(srcFile);
		 if (f.isDirectory()) {
			 addFolderToZip(path, srcFile, zipOutputStream);
		 } else {
			 zipOutputStream.putNextEntry(new ZipEntry((path==null?"":path+ "/") + f.getName()));
			 getStreamer().write(srcFile, zipOutputStream);
			 getFilesMade().add(srcFile);
		 }		
	}
	
	
}
