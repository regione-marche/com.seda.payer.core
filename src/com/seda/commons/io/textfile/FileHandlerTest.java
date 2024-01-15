/**
 * 
 */
package com.seda.commons.io.textfile;

/**
 * @author f.ricci
 *
 */
public class FileHandlerTest {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long ini = System.currentTimeMillis();
		new FileHandlerTest(args);
		System.out.println("Fine " + (System.currentTimeMillis()-ini));
	}

	public FileHandlerTest(String[] args) {
		if (args.length!=1) {
			throw new RuntimeException("Specificare un percorso di generazione del file di prova");
		}
		FileHandler fileHandler = new FileHandler();
		fileHandler.allocate(args[0]);
		fileHandler.openOuput();
		if (FileStatus.NO_FURTHER_INFO.equals(fileHandler.getFileStatus())) {
			for (int i = 0; i < 100; i++) {
				fileHandler.write("record di esempio numero " + i);
			}			
		} else {
			throw new RuntimeException("Errore nell'apertura in output del file, file status " + fileHandler.getFileStatus());
		}
		fileHandler.close();

		fileHandler.openInput();
		for (String line = fileHandler.read(); line != null; line = fileHandler.read()) {
			   System.out.println(line);
		}
		fileHandler.close();
	}
}
