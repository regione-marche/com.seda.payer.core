package com.seda.payer.commons.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
/**
 * <p>ClassName:	 GZiper</p>
 * <p>Copyright:	 Copyright (c) 01-gen-2000</p>
 * <p>Creation date: 2-nov-2010</p>
 * <p>Company:		 SEDA</p>
 * @author Marco.Montisano
 */
public class GZiper implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * @param zipEntry
	 * @param zipName
	 * @throws Exception
	 */
	public static void zip(String zipEntry, String zipName) throws Exception {
		ZipOutputStream out = null;
		FileInputStream fi = null;
		BufferedInputStream origin = null;
		try {
			int byteToRead = 2048;
			FileOutputStream dest = new FileOutputStream(zipName);
			out = new ZipOutputStream(new BufferedOutputStream(dest));
			byte data[] = new byte[byteToRead];
			File f = new File(zipEntry);
			fi = new FileInputStream(f);
			origin = new BufferedInputStream(fi, byteToRead);
			ZipEntry entry = new ZipEntry(f.getName());
			out.putNextEntry(entry);
			int byteToWrite;
			while((byteToWrite = origin.read(data, 0, byteToRead)) != -1) {
				out.write(data, 0, byteToWrite);
				out.flush();
			}
		} finally {
			if (fi != null) fi.close();
			if (out != null) out.close();
			if (origin != null) origin.close();
		}
	}
	/**
	 * @param zipName
	 * @param destDir
	 * @throws Exception
	 */
	public static void unzip(String zipName, String destDir) throws Exception {
		BufferedOutputStream dest = null;
		BufferedInputStream is = null;
		try {
			int byteToRead = 2048;
			ZipEntry entry;
			ZipFile zipfile = new ZipFile(zipName);
			Enumeration e = zipfile.entries();
			while(e.hasMoreElements()) {
				entry = (ZipEntry) e.nextElement();
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int byteToWrite;
				byte data[] = new byte[byteToRead];
				String folder = "";
				if (!StringUtility.isEmpty(destDir)) {
					if (!new File(destDir).isDirectory())
						new File(destDir).mkdir();

					folder = destDir + File.separator;
				}
				FileOutputStream fos = new FileOutputStream(folder + entry.getName());
				dest = new BufferedOutputStream(fos, byteToRead);
				while ((byteToWrite = is.read(data, 0, byteToRead)) != -1) {
					dest.write(data, 0, byteToWrite);
					dest.flush();
				}
				dest.flush();
				dest.close();
				is.close();
			}
			zipfile.close();
		} finally {
			if (dest != null) dest.close();
			if (is != null) is.close();
		}
	}

	public static void zipFiles(String[] files, String zipName, String zipOutDir) throws Exception{
		ZipOutputStream out = null;
		FileInputStream fi = null;
		BufferedInputStream origin = null;
		try {
			int byteToRead = 2048;
			FileOutputStream dest = new FileOutputStream(zipOutDir + zipName);
			out = new ZipOutputStream(new BufferedOutputStream(dest));
			for(int i = 0; i < files.length; i++) {
				byte data[] = new byte[byteToRead];
				File f = new File(files[i]);
				fi = new FileInputStream(f);
				origin = new BufferedInputStream(fi, byteToRead);
				ZipEntry entry = new ZipEntry(f.getName());
				out.putNextEntry(entry);
				int byteToWrite;
				while((byteToWrite = origin.read(data, 0, byteToRead)) != -1) {
					out.write(data, 0, byteToWrite);
					out.flush();
				}
			}
		} finally {
			if (fi != null) fi.close();
			if (out != null) out.close();
			if (origin != null) origin.close();
		}
	}
}