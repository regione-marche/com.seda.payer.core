package com.seda.commons.string;

/**
 * @author SEDA Lab
 */
public class Concat {
	/**
	 * Finds the right separator for every operating system.
	 */
	private final static String filesep = System.getProperty("file.separator");	
	/**
	 * Returns the file path using the right separator for every operating system.
	 * @param filePath - It's the file path.
	 * @param fileName - It's the file name.
	 * @return <code>String</code> - It concatenates file path + file name.
	 */
	public final static String buildPath(String filePath, String fileName) {
		return buildPath(filePath, fileName,(filePath.startsWith("\"") && filePath.endsWith("\"")));
	}

	public final static String buildPath(String filePath, String fileName, boolean quote) {
		if (filePath.startsWith("\"") && filePath.endsWith("\"")) {
			filePath=filePath.substring(1,filePath.length()-1);			
		}
		
		if (filePath.endsWith(Concat.filesep)) {
			return filePath + fileName;
		} 
		return (quote?"\"":"")+filePath + Concat.filesep + fileName+(quote?"\"":"");
	}
}
