package com.seda.payer.commons.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtility {
	
	public FTPUtility() {}

	public boolean sendFTP(String ftpServer, String ftpUser, String ftpPassword,
			 String ftpFolder, String outFilename,String szRemoteFileName) throws IOException{
			
		boolean sendFtpOk = true;
		
		/*RECUPERO CONFIGURAZIONE PROXYY SE PRESENTE*/
		/*
		String proxyHost = System.getProperty("ftp.proxyHost");
		Integer proxyPort = 80;
		try { 
			proxyPort= Integer.parseInt(System.getProperty("ftp.proxyPort","80"));
		} catch (Exception e) { 
			logger.error("sendFTP - Invalid proxy port. Use default port 80"); 
		}
		
		String proxyUser = System.getProperty("ftp.proxyUser");
		String proxyPassword = System.getProperty("ftp.proxyPassword");
		*/
		
		FTPClient ftpClient;
		
		/*
		 * Al momento questa parte viene asteriscata in attesa di completare i test in quanto su Jboss non 
		 * permette poi l'invio del flusso con FTP 
		if (proxyHost != null) {
			UtilityBatch.writeLog("Invio FTP utilizzando il seguente proxy: '" + proxyHost+":"+proxyPort.toString()+"'");
			UtilityBatch.writeLog("Invio FTP utilizzando proxy con user: " + proxyUser+" e password: "+proxyPassword);
			ftpClient=new FTPHTTPClient(proxyHost, proxyPort, proxyUser, proxyPassword);
		} else {
			ftpClient=new FTPClient();
		}
		*/
		ftpClient=new FTPClient();
		LogUtility.writeLog("Connessione al server FTP: '"+ftpServer+"'");
		ftpClient.connect(ftpServer);
		LogUtility.writeLog("Connessione eseguita");
		ftpClient.enterLocalPassiveMode();		
		
		LogUtility.writeLog("Login FTP utilizzando user: '"+ftpUser+"' e password: '"+ftpPassword+"'");
		if (ftpClient.login(ftpUser,ftpPassword))
		{
			LogUtility.writeLog("Login eseguito con successo");
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			
			LogUtility.writeLog("Imposto la directory : '"+ftpFolder+"'");
			ftpClient.cwd(ftpFolder);
			
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
			{
				File file = new File(outFilename);
				FileInputStream fis = new FileInputStream(file);
				//ftpClient.pasv();
				if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode()))
				{
					LogUtility.writeLog("Invio FTP del file " + outFilename);
					if(!ftpClient.storeFile(szRemoteFileName, fis))
					{
						sendFtpOk = false;
						LogUtility.writeLog("Errore nell'invio FTP del file: " + outFilename + " - ReplyCode = " + ftpClient.getReplyCode());
					}
				}
				else
					sendFtpOk = false;
				fis.close();
			}
			else
				sendFtpOk = false;
		}
		else
			sendFtpOk = false;
		
		LogUtility.writeLog("Eseguo il logout");
		ftpClient.logout();
		ftpClient=null;
		
		return sendFtpOk;
	}

}
