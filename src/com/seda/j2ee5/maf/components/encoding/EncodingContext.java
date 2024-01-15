/**
 * 
 */
package com.seda.j2ee5.maf.components.encoding;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import org.apache.logging.log4j.ThreadContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFContext;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * @author f.ricci
 *
 */
public class EncodingContext {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(EncodingContext.class);

	private String characterEncoding="ASCII";
	private boolean encodeParameter=MAFContext.DEFAULT_ENCODE_PARAMETER;
	private boolean encodeDelete=MAFContext.DEFAULT_ENCODE_DELETE;
	
	
	public String getCharacterEncoding() {
		return characterEncoding;
	}
	
	public boolean isEncodeParameter() {
		return encodeParameter;
	}
	
	public boolean isEncodeDelete() {
		return encodeDelete;
	}	

	private static EncodingContext me;
	
	static {
    	try {
    		me = new EncodingContext();
    	} catch(Exception se) {
    		logger.error(MAFLogger.getMessage("generic_exception"), se);
    	}
    }
	
	private EncodingContext() {
	}

	public static EncodingContext getInstance() {
		return me;
	}
	
	public void initialize(FilterConfig config) {
		initialize(config.getServletContext());
	}
	
	public void initialize(ServletContext context) {
		ThreadContext.put(MAFLogger.MDC_CTX, context.getServletContextName());
		characterEncoding=MAFContext.DEFAULT_CHAR_ENCODING;
		
		String encodingString = context.getInitParameter("encoding"); 
		if (encodingString!=null && encodingString.trim().length()>0)
			characterEncoding = encodingString; 
		
		logger.info(MAFLogger.getMessage("encoding_manager_request_encode_to",characterEncoding));
		encodeParameter=MAFContext.DEFAULT_ENCODE_PARAMETER;
		String encodeParameterString = context.getInitParameter(MAFContext.CONTEXT_ENCODE_PARAMETER);
		if (encodeParameterString!=null)
			encodeParameter = Boolean.parseBoolean(encodeParameterString);
		
		if (encodeParameter)
			logger.info(MAFLogger.getMessage("encode_parameter_manager_active"));
		else 
			logger.info(MAFLogger.getMessage("encode_parameter_manager_not_active"));
		
		encodeParameter=MAFContext.DEFAULT_ENCODE_PARAMETER;
		
		String encodeDeleteString = context.getInitParameter(MAFContext.CONTEXT_ENCODE_DELETE);
		if (encodeDeleteString!=null)
			encodeDelete = Boolean.parseBoolean(encodeDeleteString);
		
		if (encodeParameter) {
			if (encodeDelete) 
				logger.info("Encode manager will move the paramaters to the request attribute");
			else
				logger.info("Encode manager will copy the paramaters to the request attribute");
		}
	}
}
