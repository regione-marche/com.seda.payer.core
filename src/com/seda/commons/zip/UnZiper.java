/**
 * 
 */
package com.seda.commons.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.seda.commons.string.Concat;

/**
 * @author Seda Lab
 *
 */
public class UnZiper {
	/**
	 * Provides enumeration for decompression mode
	 * 
	 */
	public enum mode {
		/**
		 * Lists all zip entries in the archive and build a {@link SortedSet} of entries 
		 */
		LIST,
		/**
		 * Extracts all zip entries in the destination folder and build a list of entries.
		 * @see mode#LIST
		 */
		EXTRACT
		;
	}
	
	private Streamer streamer;
	private HashMap<String, String> directoryMade = new HashMap<String,String>();
	private SortedSet<String> filesMade;	
	private mode action = mode.EXTRACT;

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
	public mode getAction() {
		return action;
	}
	public void setAction(mode action) {
		this.action = action;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		if (destination==null) {
			setDestination(System.getProperty("user.dir"));
		}
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void reset() {
		setSource(null);
		setDestination(null);
		setAction(mode.EXTRACT);
	}
	
	
	public void deCompress() throws IOException {
		deCompress(new ZipInputStream(new FileInputStream(getSource())));
	}
	
	public void deCompress(ZipInputStream zipInputStream) throws IOException {
		preProcessing();
		ZipEntry zipEntry=null;
		try {
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				extract(zipInputStream, zipEntry);
			}					
		} finally {
			if (zipInputStream!=null) {
				zipInputStream.close();
			}
		}
	}
	
	private void preProcessing() throws IOException {
        filesMade = new TreeSet<String>();		
		File destDir = new File(getDestination());
		try {
			if (destDir.exists() && destDir.isFile())
				throw new IOException("Destination folder is not a folder");
			if (destDir.exists()) 
				return;
			destDir.mkdirs();
		} finally {
			if (destDir!=null) {
				destDir=null;
			}
		}
	}
	
	private void extract(ZipInputStream zipInputStream, ZipEntry zipEntry) throws IOException {
		String zipName = zipEntry.getName();
		switch (getAction()) {
		case EXTRACT:
			if (zipEntry.isDirectory()) {
				new File(destination, zipName).mkdirs();
			} else {
				verifyPath(zipName);
				getStreamer().write(zipInputStream, new File(getDestination(),zipName));
			}
		case LIST:
			filesMade.add(zipName);
			break;
		}
	}
	
	private void verifyPath(String zipName) {
		File zipFile = new File(zipName);
		String parentDir = zipFile.getParent();
		if (!directoryMade.containsKey(parentDir)) {
			File destinationPath = new File(Concat.buildPath(getDestination(), parentDir));
			destinationPath.mkdirs();
			directoryMade.put(parentDir, "created");
			destinationPath=null;
		}
	}
	
}
