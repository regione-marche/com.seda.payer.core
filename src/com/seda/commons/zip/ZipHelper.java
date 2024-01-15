/**
 * 
 */
package com.seda.commons.zip;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.seda.commons.zip.UnZiper.mode;
/**
 * 
 * ZipHelper provides functions to compress and decompress folders and files using ZIP algorithms
 * 
 * @author Seda Lab
 *
 */
public class ZipHelper {

	private Ziper ziper;
	private UnZiper unZiper;
	/**
	 * Returns the instance of the {@link Ziper} class used to compress files and folders
	 * 
	 * @return <code>Ziper</code> the Ziper object used by this helper
	 */
	public Ziper getZiper() {
		if (ziper==null) 
			ziper = new Ziper();
		return ziper;
	}

	/**
	 * 	Returns the instance of the {@link UnZiper} class used to decompress zip file
	 * 
	 * @return <code>UnZiper</code> the UnZiper object used by this helper
	 */
	public UnZiper getUnZiper() {
		if (unZiper==null) 
			unZiper = new UnZiper();		
		return unZiper;
	}

	// =======================================================================
	// UnZip Section
	// =======================================================================
	/**
	 * Extracts files from the <code>name</code> ZIP file in to the user working directory as output folder
	 * 
	 * @param name the system-dependent file name
	 * @throws IOException if an I/O error has occurred
	 */
	public void unZip(String name) throws IOException {
		getUnZiper().reset();
		getUnZiper().setDestination(null);
		doUnZip(name);
	}
	/**
	 * Extracts files from the <code>name</code> ZIP file in to the <code>destination</code> folder
	 * 
	 * @param name the system-dependent file name
	 * @param destination the destination folder
	 * @throws IOException if an I/O error has occurred
	 */
	public void unZip(String name, String destination) throws IOException {
		getUnZiper().reset();		
		getUnZiper().setDestination(destination);
		doUnZip(name);
	}
	
	/**
	 * Extracts files from the <code>name</code> ZIP file in to the <code>destination</code> folder
	 * 
	 * @param name the system-dependent file name
	 * @param destination the destination folder
	 * @param action {@link UnZiper.mode} action
	 * @throws IOException if an I/O error has occurred
	 */
	public void unZip(String name, String destination, mode action) throws IOException {
		getUnZiper().reset();		
		getUnZiper().setDestination(destination);
		getUnZiper().setAction(action);
		doUnZip(name);
	}	
	
	/**
	 * 
	 * Return a <code>SortedSet</code> of String containing the list of the decompressed files
	 * 
	 * @return <code>SortedSet</code> of String path 
	 */
	public SortedSet<String> getUnzipFilesMade() {
		return getUnZiper().getFilesMade();
	}
	
	/**
	 * Extracts files from a ZIP archive
	 * @param name the system-dependent file name
	 * @throws IOException if an I/O error has occurred
	 */
	private void doUnZip(String name) throws IOException {
		getUnZiper().deCompress(getZipInputStream(name));		
	}
	// =======================================================================
	// Zip Section
	// =======================================================================	
	/**
	 * Compresses all file from the <code>source</code> file or folder in to the <code>destination</code> ZIP file
	 * @param source the source file or folder
	 * @param destination the output zip file
	 * @throws IOException if an I/O error has occurred
	 */
	public void zip(String source, String destination) throws IOException {
		getZiper().reset();
		getZiper().setSource(source);		
		getZiper().setDestination(destination);
		doZip(destination);
	}
	/**
	 * Compresses all file from the <code>source</code> file or folder in to the <code>destination</code> ZIP file 
	 * See also {@link java.util.zip.Deflater} constants for the right compression level. <code>Deflater.DEFAULT_COMPRESSION</code> is the default
	 * 
	 * @param source the source file or folder
	 * @param destination the output zip file
	 * @param level compression level for subsequent entries which are DEFLATED. The compression level (0-9)
	 * @throws IOException if an I/O error has occurred
	 */
	public void zip(String source, String destination, int level) throws IOException {
		getZiper().reset();
		getZiper().setLevel(level);
		getZiper().setSource(source);		
		getZiper().setDestination(destination);
		doZip(destination);
	}	

	/**
	 * Return a <code>SortedSet</code> of String containing the list of the compressed files
	 * @return <code>SortedSet</code> of String path
	 */
	public SortedSet<String> getZipFilesMade() {
		return getZiper().getFilesMade();
	}
	
	/**
	 * Compresses files in to a ZIP archive
	 * @param destination the output zip file
	 * @throws IOException if an I/O error has occurred
	 */
	private void doZip(String destination) throws IOException {
		getZiper().compress(getZipOutputStream(destination));		
	}	
	/**
	 * Creates a new ZIP input stream
	 * 
	 * @param name the system-dependent file name
	 * @return an input stream filter for reading files in the ZIP file format. Includes support for both compressed and uncompressed entries
	 * @throws FileNotFoundException if the file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading
	 * @throws SecurityException if a security manager exists and its checkRead method denies read access to the file
	 */
	public ZipInputStream getZipInputStream(String name) throws FileNotFoundException, SecurityException {
		return new ZipInputStream(new FileInputStream(name));
	}
	/**
	 * Creates a new ZIP output stream
	 * 
	 * @param name the system-dependent filename
	 * @return the actual output stream
	 * @throws FileNotFoundException if the file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason
	 * @throws SecurityException if a security manager exists and its checkWrite method denies write access to the file
	 */
	public ZipOutputStream getZipOutputStream(String name) throws FileNotFoundException, SecurityException {
		return new ZipOutputStream(new FileOutputStream(name));
	}
	/**
	 * Returns an enumeration of the ZIP file entries
	 * 
	 * @param name the name of the zip file
	 * @return an enumeration of the ZIP file entries
	 * @throws IOException if a ZIP format error has occurred
	 * @throws ZipException if an I/O error has occurred
	 * @throws SecurityException if a security manager exists and its checkRead method denies read access to the file 
	 * @see java.util.zip.ZipFile#entries()
	 */
	public Enumeration<? extends ZipEntry> getEntries(String name) throws ZipException, IOException, SecurityException {
		ZipFile z;
		try {
			z=new ZipFile(name);
			return z.entries();
		} finally {
			z=null;
		}
	}	
	/**
	 * Return a complete list of the element contained in a zip file
	 * 
	 * @param name the system-dependent filename
	 * @return <code>SortedSet</code> a natural list of the element in the zip file
	 * @throws IOException if an I/O error has occurred
	 */
	public SortedSet<String> getFileList(String name) throws IOException {
		UnZiper unzip = new UnZiper();
		unzip.setAction(UnZiper.mode.LIST);		
		try {
			unzip.deCompress(getZipInputStream(name));
			return unzip.getFilesMade();
		} finally {
			unzip=null;
		}
	}
	
	//=====================================================
	public void finalize() {
		destroy();
	}
	/**
	 * Destroys all internal resource
	 */
	private void destroy() {
		this.ziper=null;
		this.unZiper=null;
	}
}
