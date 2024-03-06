/**
 * 
 */
package com.seda.commons.zip;

import java.io.IOException;

/**
 * @author dbadm
 *
 */
public class ZipHelperTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ZipHelperTest(args);
	}

	public ZipHelperTest(String[] args) {
		ZipHelper zipHelper = new ZipHelper();
		String mode=args[0];
		String input=args[1];
		String output=args[2];
		try {
			if (mode!=null) {
				if (mode.equals("-z")) {
					zipHelper.zip(input,output);
					zipHelper.getZipFilesMade().toString();			
				} else if (mode.equals("-u")) {
					zipHelper.unZip(input,output);
					zipHelper.getUnzipFilesMade().toString();			
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			zipHelper=null;
		}
	}	
}
