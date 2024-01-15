/**
 * 
 */
package com.seda.j2ee5.maf.core.screen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.MAFRequest;
import com.seda.j2ee5.maf.util.MAFUtil;

/**
 * @author Seda Lab
 *
 */
public class ScreenWriter {

	public static final String REQUEST = "request";
	public static final String SESSION = "session";
	public static final String APPLICATION = "application";
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(ScreenWriter.class);
	
	private ScreenWriterData writerData;
	private String applid;
	
	private Object getFromScope(HttpServletRequest request, String id) {
		Object output=null;
		String scope=writerData.getScope();

		if (REQUEST.equalsIgnoreCase(scope) || scope==null) {output=getFromRequest(request,id);
    	} else if (SESSION.equalsIgnoreCase(scope)) {output=getFromSession(request,id);    		
    	} else if (APPLICATION.equalsIgnoreCase(scope)) {output=getFromApplication(request,id);    		
    	} else {
    		logger.error(MAFLogger.getMessage("screen_writer_scope_not_valid", applid, writerData.getScope(), id));
    	}
		
		return output;
	}
	
	private Object getFromRequest(HttpServletRequest request, String id) {
		return request.getAttribute(id);
	}
	private Object getFromSession(HttpServletRequest request, String id) {
		HttpSession httpSession = request.getSession(false);
		if (httpSession==null) {
			return null;
		}
		return httpSession.getAttribute(id);
	}
	private Object getFromApplication(HttpServletRequest request, String id) {
		HttpSession httpSession = request.getSession(false);
		if (httpSession==null) {
			return null;
		}		
		return httpSession.getServletContext().getAttribute(id);
	}		
	
	private void writeString(HttpServletResponse response, String input, String attach) throws IOException {
		if (attach!=null)
			response.addHeader("Content-Disposition","attachment; filename="+attach);
		response.setContentLength(input.length());
		PrintWriter printWriter = response.getWriter();
		printWriter.print(input);
		printWriter.close();
	}
	
	private void writeBinary(HttpServletResponse response, byte[] bytesToWrite, String attach) throws IOException {
		ServletOutputStream outputStream = response.getOutputStream( );
		try {
			if (attach!=null)
				response.addHeader("Content-Disposition","attachment; filename="+attach);
			response.setContentLength( bytesToWrite.length);
			outputStream.write(bytesToWrite);
			outputStream.flush();
		} finally {
			try {
				if (outputStream != null) outputStream.close( );
			} catch (IOException ignore) { }
			outputStream=null;
			bytesToWrite=null;
		}	

	}	
	
	public void init(ScreenWriterData writerData, String applid) throws java.io.IOException, javax.servlet.ServletException {
		this.writerData=writerData;
		this.applid=applid;
	}

	public void end(Throwable t) {
		this.writerData=null;
	}	
	
	public void write(HttpServletRequest request, HttpServletResponse response)
    	throws java.io.IOException, javax.servlet.ServletException {
		
		if (writerData==null) {
			MAFRequest mafRequest = new MAFRequest(request);
			response.setContentType("application/txt");
			writeString(response, String.format("[%s] Target url \"%s\" doesn't have a valid writer data", applid, mafRequest.getTargetURL()), 
					"error.txt");
			return;			
		}
 
		Object scopeObjectBuffer = getFromScope(request,writerData.getId());
		if (scopeObjectBuffer==null) {
			logger.error(MAFLogger.getMessage("screen_writer_id_not_found", applid, writerData.getId(), writerData.getScope()));
			return;
    	}
		Object attachObject =null;
		String attachName =null;
		if (writerData.getAttachId()!=null && !writerData.getAttachId().trim().equals("")) {
			attachObject = getFromScope(request,writerData.getAttachId());
			if (attachObject instanceof String) {
				attachName=String.valueOf(attachObject);
			} else {
				logger.error(String.format("[%s] Attach '%s' is not a java.lang.String objegt: %s", applid, writerData.getAttachId(), attachObject!=null?attachObject.getClass().getName():"null"));
				return;
			}
		}
		
		byte[] bytesToWrite = resolveBytes(request,scopeObjectBuffer);
		
		if (bytesToWrite!=null) {
			if (writerData.getContentType()!=null) {
				response.setContentType(writerData.getContentType());
			}
			writeBinary(response,bytesToWrite,attachName);
		} else {
			logger.error(String.format("[%s] Unable to resolve attachment. Please see your writer configuration: %s",applid,writerData.toString()));			
		}
		scopeObjectBuffer=null;
		bytesToWrite=null;
	}
	
	
	private byte[] resolveBytes(HttpServletRequest request, Object scopeObject) {
		byte[] bytesToWrite=null;
		if (writerData.isInScope()) {
			  if (scopeObject instanceof String) {
				  bytesToWrite = String.valueOf(scopeObject).getBytes();
			  } else if (scopeObject instanceof byte[]) {
				  bytesToWrite = (byte[]) scopeObject;
			  }
		} else if (scopeObject instanceof String) {
			bytesToWrite=getBytesFromFile(new File((String)scopeObject));
		}
		return bytesToWrite;
	}

	private byte[] getBytesFromFile(File file) {
		InputStream is = null;
    	byte[] bytes = null;
    	try {
    		is = new FileInputStream(file);
        	//Get the file length
        	long length = file.length();
        	//Create the byte array
        	bytes = new byte[(int)length];
        	//Read the file
        	int offset = 0;
        	int numRead = 0;
        	while(offset<bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset))>=0){
        		offset+=numRead;
        	}
        	//Read all bytes?
        	if (offset<bytes.length)
        		throw new IOException("Could not completely read file " + file.getName());
    	} catch (IOException x) {
    		logger.error(String.format("[%s] Error reading file input stream %s",applid,file.getName()));
    	} finally {
    		if (is!=null) {
    			try {is.close();} catch (IOException x) {}
    		}
    		is=null;
    	}
    	return bytes;
	}

	public static void writeError(Throwable t, String userTitle, String userMessage, ServletResponse response) {
		writeError(t, userTitle, userMessage, response, HttpServletResponse.SC_BAD_REQUEST);
	}	
	
	public static void writeError(Throwable t, String userTitle, String userMessage, ServletResponse response, Integer status) {

		HttpServletResponse hres = (HttpServletResponse) response;
		if (status==null) status= HttpServletResponse.SC_BAD_REQUEST;
		hres.setStatus(status);
		String stackTrace = MAFUtil.getStackTrace(t);                    
		try {                
			hres.setContentType("text/html");
			PrintStream ps = new PrintStream(hres.getOutputStream());
			PrintWriter pw = new PrintWriter(ps);
			pw.print("<html>\n<head>\n<title>"+ userTitle +"</title>\n</head>\n<body>\n"); //NOI18N
			pw.print("<h1>" + userTitle + "</h1>\n");
			pw.print("<pre>\n");
			pw.print("<h3>" + userMessage + "</h3>\n");
			if(stackTrace != null && !stackTrace.equals("")) {
				pw.print(stackTrace);	
			}
			pw.print("</pre></body>\n</html>"); //NOI18N
			MAFUtil.closeIgnoringException(pw);
			MAFUtil.closeIgnoringException(ps);
			MAFUtil.closeIgnoringException(hres.getOutputStream());
		} catch(IOException ex){ 
			logger.error("Exception during send error", ex);
		}

	}
	
	public static void sendError(ServletResponse response, int err, String message) throws IOException {
		HttpServletResponse hres = (HttpServletResponse) response;
		hres.sendError(err, message);
	}
	
}
