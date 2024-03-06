package com.seda.payer.commons.utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * <p>Title:		 FileUtility</p>
 * <p>Description:	 </p>
 * <p>Copyright:	 Copyright (c) 01-gen-2000</p>
 * <p>Creation date: 20-ott-2010</p>
 * <p>Company:		 SEDA</p>
 * @author Marco Montisano
 */
public class FileUtility implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SEPARATOR = "/";
	public static FileOrder ORDER_ASC = new FileOrder("ASC");
	public static FileOrder ORDER_DESC = new FileOrder("DESC");
	public static class FileOrder implements Serializable {
		private static final long serialVersionUID = 1L;
		private String order;
		public FileOrder(String order) {
			this.order = order;
		}
		public String getOrder() { return order; }
		public void setOrder(String order) { this.order = order; }
	}
	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String getContent(String path) throws IOException {
		//inizio LP PG21XX04 Leak
		//BufferedInputStream bis;
		BufferedInputStream bis = null;
		//fine LP PG21XX04 Leak
        StringBuffer sb = new StringBuffer();
        try {
            bis = new BufferedInputStream(new FileInputStream(path));
            int c;
            while ((c = bis.read()) != -1)
            	sb.append((char) c);

            bis.close();
    		//inizio LP PG21XX04 Leak
            bis = null;
    		//fine LP PG21XX04 Leak
        } catch (Exception e) {
        	e.printStackTrace();
        }
		//inizio LP PG21XX04 Leak
	    finally {
	    	try {
	    		if(bis != null) {
	    			bis.close();
	    		}
	    	} catch (IOException e) {
	    		e.printStackTrace();
			}
	    }
		//fine LP PG21XX04 Leak
        return sb.toString();
	}
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBitesFromFile(String fileName) throws IOException {
		InputStream in = new FileInputStream(fileName);
		byte[] buf = new byte[in.available()];
		int len;
		while ((len = in.read(buf)) > 0) {
		}
		System.out.println(len);
		in.close();
		return buf;
	}
	/**
	 * @param data
	 * @param dir
	 * @param name
	 * @throws IOException
	 */
	public static void wr(byte[] data, String dir, String name) throws IOException {
		int byteToReading = 0;
		byte[] byteToRead = new byte[1*1024];
		ByteArrayInputStream in = new ByteArrayInputStream(data); 
		FileOutputStream out = new FileOutputStream(dir + SEPARATOR + name, false); 
		while ((byteToReading = in.read(byteToRead)) != -1) {
			byte[] byteToWrite = new byte[byteToReading];
			System.arraycopy(byteToRead, 0, byteToWrite, 0, byteToReading);
			out.write(byteToWrite);
			out.flush();
		}
		out.close();
		in.close();
	}
	/**
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void copy(InputStream in, OutputStream out) throws IOException {
		int byteToReading = 0;
		byte[] byteToRead = new byte[1*1024];
		while ((byteToReading = in.read(byteToRead)) != -1) {
			byte[] byteToWrite = new byte[byteToReading];
			System.arraycopy(byteToRead, 0, byteToWrite, 0, byteToReading);
			out.write(byteToWrite);
			out.flush();
		}
	}
	/**
	 * @param dir
	 * @param filenameFilter
	 * @param ricorsive
	 */
	public static void rm(String dir, String filenameFilter, boolean ricorsive) {
		File fileDir = new File(dir);
		final String ff = filenameFilter;
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.startsWith(ff) || name.endsWith(ff) || name.contains(name);
			}
		};
		if (ricorsive) {
			String[] fileList = fileDir.list(filter);
	    	if (fileList != null) {
	    		for (int i = 0; i < fileList.length; i++) {
	    			if ((new java.io.File(dir + fileList[i])).isFile()) {
	    				new File(dir + fileList[i]).delete();
	    			} else if ((new java.io.File(dir + fileList[i])).isDirectory()) {
	    				rm(dir + fileList[i] + "/", filenameFilter, ricorsive);
	    				new java.io.File(dir + fileList[i] + "/").delete();
	    			}
	    		}
	    	}
    	}
    	fileDir.delete();
	}
	/**
	 * @param f
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public static boolean move(File f, String dir) throws Exception {
		return new File(f.getAbsolutePath()).renameTo(new File(dir + f.getName()));
	}
	/**
	 * @param dir
	 * @throws Exception
	 */
	public static void mkdir(String dir) throws Exception {
		if (!new File(dir).isDirectory())
			new File(dir).mkdir();
	}
	/**
	 * @param dir
	 * @param pattern
	 * @param order
	 * @return
	 */
	public static TreeSet find(String dir, final Pattern pattern, final FileOrder order) {
		File fileDir = new File(dir);
    	if (fileDir == null)
    		return null;

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File file, String name) {
				Matcher matcher = pattern.matcher(name);
				return matcher.find();
			}
		};
		String[] fileList = fileDir.list(filter);
    	if (fileList != null) {
    		TreeSet treeSet = new TreeSet(new Comparator() {
				public int compare(Object arg0, Object arg1) {
					if (order.getOrder().compareTo(ORDER_ASC.getOrder()) == 0)
						return 1;

					return new Timestamp(((File) arg1).lastModified()).compareTo(new Timestamp(((File) arg0).lastModified()));
				}
    		});
    		for (int i = 0; i < fileList.length; i++) {
    			if ((new java.io.File(dir + fileList[i])).isFile()) {
    				File nextFile = new java.io.File(dir + fileList[i]);
    				treeSet.add(nextFile);
    			}
    		}
    		return treeSet;
    	}
    	return null;
	}
}