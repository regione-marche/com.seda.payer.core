package com.seda.payer.commons.geos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class WSRest_GEOS {
	
	public static boolean callWSRest_GEOS(Flusso flusso, String targetPdf, String urlGeos)
	{
		String xmlStringForGeos = "";
		//inizio LP PG21XX04 Leak
        java.io.OutputStream os = null;
		//fine LP PG21XX04 Leak
	    try {
	    	System.out.println("urlGeos: "+urlGeos);
	    	URL url = new URL(urlGeos);

	    	JAXBContext xmlGeos = JAXBContext.newInstance(Flusso.class);
	    	Marshaller m = xmlGeos.createMarshaller();
	    	m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
	    	m.setProperty("jaxb.encoding", "US-ASCII");
	    	StringWriter sw = new StringWriter();
	    	m.marshal(flusso,sw);
	    	xmlStringForGeos = sw.toString();

	    	xmlStringForGeos = xmlStringForGeos.replace("<Documentdata>", "<Documenti>" + System.getProperty("line.separator") + "<Documentdata>");
	    	xmlStringForGeos = xmlStringForGeos.replace("<DatiAnagrafici>", "<DatiSpecifici>" + System.getProperty("line.separator") + "<DatiAnagrafici>");
	    	xmlStringForGeos = xmlStringForGeos.replace("</Documentdata>", "</DatiSpecifici>" + System.getProperty("line.separator") + "</Documentdata>" + System.getProperty("line.separator") + "</Documenti>");

	    	System.out.println("GEOS: "+xmlStringForGeos);

	    	byte[] xmlData = xmlStringForGeos.getBytes();
	
	        HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
	
	        urlCon.setDoOutput(true);
	        urlCon.setDoInput(true);
	        urlCon.setUseCaches(false);
	
	        urlCon.setRequestMethod("POST");
	        urlCon.setRequestProperty("Content-Type", "application/xml");
	        urlCon.setRequestProperty("Content-length", String.valueOf(xmlData.length));
	
			//inizio LP PG21XX04 Leak
	        //java.io.OutputStream os = urlCon.getOutputStream();
	        os = urlCon.getOutputStream();
			//fine LP PG21XX04 Leak
	        os.write(xmlData);
	        os.flush();
	        
	        InputStream stream = null;
			if (urlCon.getResponseCode() != HttpURLConnection.HTTP_OK){
	        	stream  = urlCon.getErrorStream();
	        } else {
	        	stream = urlCon.getInputStream();
	        }
						
			File targetFile = new File(targetPdf);
			//Inserita cancellazione per evitare duplicazione di file nella directory qualora serva la creazione fisica del file
			if (targetFile.exists())
				targetFile.delete();
			//
		    java.io.OutputStream outStream = new FileOutputStream(targetFile);

//    	    byte[] buf = new byte[4096];
//    	    int len = -1;
//    	    while ((len = stream.read(buf)) != -1) {
//    	        outStream.write(buf, 0, len);
//    	    }
		    
		    int bytesRead = -1;
            int BUFFER_SIZE = 4096;
            System.out.println("BUFFER_SIZE");
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = stream.read(buffer)) != -1) {
            	outStream.write(buffer, 0, bytesRead);
            }
    	    outStream.flush();
    	    outStream.close();
    	    
    	    stream.close();
    	    urlCon.disconnect();
	
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
		//inizio LP PG21XX04 Leak
	    finally {
	    	try {
	    		if(os != null) {
	    			os.close();
	    		}
	    	} catch (IOException e) {
	    		e.printStackTrace();
			}
	    }
		//fine LP PG21XX04 Leak
		return true;		
	}
	
	 private static String convertInputStreamToString(InputStream is) throws IOException {

	        ByteArrayOutputStream result = new ByteArrayOutputStream();
	        byte[] buffer = new byte[8192];
	        int length;
	        while ((length = is.read(buffer)) != -1) {
	            result.write(buffer, 0, length);
	        }

	        // Java 1.1
	        return result.toString(StandardCharsets.UTF_8.name());

	        // Java 10
	        // return result.toString(StandardCharsets.UTF_8);

	    }
}