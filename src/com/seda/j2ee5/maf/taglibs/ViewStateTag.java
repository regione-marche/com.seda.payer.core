/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.string.Convert;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;

/**
 * 
 * This tag caches the view state in the user session and provides a couple of object
 * with the current page parameters and the page request attributes by encoding them 
 * using Base 64 Encoded Strings. To read the content of the view state use {@link ViewStateManager} 
 * class.
 *
 * @author Seda Lab
 *
 */
public class ViewStateTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(ViewStateTag.class);
	
    private String name;
    private boolean encodeAttributes = false;
    private boolean encodeParameters = false;
    
    @SuppressWarnings("unchecked")
	private Class serializable = null;
    private HashMap<String, String> userParameters = null;    

    @Deprecated
    public void setId(String id) {
        this.name = id;
    }        
    
    public void setName(String name) {
        this.name = name;
    }    
    
    public void setEncodeAttributes(boolean encodeAttributes) {
        this.encodeAttributes = encodeAttributes;
    }
    
    public void setEncodeParameters(boolean encodeParameters) {
        this.encodeParameters = encodeParameters;
    }    

    private String getUserParametersId() {
    	return name+"_user_parameters";
    }    
    private String getParametersId() {
    	return name+"_parameters";
    }
    private String getParametersValuesId() {
    	return name+"_parametersValues";
    }    
    private String getAttributeId(String key) {
    	return name+"_attributes_"+key;
    }    
   
    @SuppressWarnings({ "static-access", "unchecked" })
	private Class getSerializable() {
    	if (serializable == null) {
        	try {
        		serializable = getClass().forName("java.io.Serializable");
            } catch (java.lang.ClassNotFoundException cnf) {
            	logger.error(MAFLogger.getMessage("generic_exception"), cnf);
            }
        }
    	return serializable;
    }
    
    public int doStartTag() throws JspTagException {
    	userParameters=null;
        return EVAL_BODY_BUFFERED;
    }    
	
    @SuppressWarnings({ "unchecked" })
	public int doEndTag() throws JspTagException {
        HttpServletRequest request = 
            ((HttpServletRequest) pageContext.getRequest());
        HttpSession session = request.getSession();
        // store the last view state id created
        removeFromSession(session, MAFAttributes.VIEWSTATE_ID);
        session.setAttribute(MAFAttributes.VIEWSTATE_ID, name);
        // cache any parameters that may have been added via sub tags, 
        // or remove it from the session
		try {
			removeFromSession(session,getUserParametersId());
	        if (userParameters != null) {
	        	String userParametersMap;
	        	userParametersMap = Convert.serializableToStringBase64(userParameters);
	        	session.setAttribute(getUserParametersId(), userParametersMap);
	        }
        } catch (IOException e) {
        	logger.error(MAFLogger.getMessage("generic_exception"), e);
			throw new JspTagException(e.getMessage());
		}
        // check the request for previous parameters, or remove it from the session
        if (encodeParameters) {
    		try {
    			removeFromSession(session,getParametersId());    			
    			HashMap<String, String[]> parametersValues = new HashMap<String, String[]>(request.getParameterMap().size());
    			HashMap<String, String> parameters = new HashMap<String, String>(request.getParameterMap().size());    			
    			Enumeration paramKeys = request.getParameterNames();
    			while (paramKeys.hasMoreElements()) {
					String paramKey = (String) paramKeys.nextElement();
					parametersValues.put(paramKey, request.getParameterValues(paramKey));
					parameters.put(paramKey, request.getParameter(paramKey));
				}
    			String parametersValuesMap = Convert.serializableToStringBase64(parametersValues);
    			session.setAttribute(getParametersValuesId(), parametersValuesMap);
               	String parametersMap = Convert.serializableToStringBase64(parameters);
   	        	session.setAttribute(getParametersId(), parametersMap);            	
            } catch (IOException e) {
            	logger.error(MAFLogger.getMessage("generic_exception"), e);
    			throw new JspTagException(e.getMessage());
    		}
        }        
        //  Serialize the request attributes (only serializable objects are going
        //  to be processed) or remove it from the session.
        if (encodeAttributes) {
        	String removerPrefix = getAttributeId("");
        	removeAllFromSession(session, removerPrefix);        	
        	// put the request attributes into attributes
        	Enumeration enumeration = request.getAttributeNames();
        	while (enumeration.hasMoreElements()) {
        		String key = (String)enumeration.nextElement();
        		// don't serialize javax items        	
        		//if (!key.startsWith("javax.servlet")) {
                if (!key.startsWith("javax")) {
                    Object value = request.getAttribute(key);
                    if (value!=null && !value.getClass().getName().startsWith("javax.net")) {
//                    	logger.info("la chiave � '" + key + "' e il nome della classe � '" + value.getClass().getName() + "' = " + value.getClass().getName().startsWith("javax.net"));
                    	// check if serializable
                        if (getSerializable().isAssignableFrom(value.getClass())) {
                            try {
                        		String keyId = getAttributeId(key);                        	
                            	String attribute;
                            	attribute=Convert.serializableToStringBase64((Serializable) value);
                    			removeFromSession(session,keyId);
                    			session.setAttribute(keyId, attribute);
                            } catch (java.io.IOException e) {
                            	logger.error(MAFLogger.getMessage("generic_exception"), e);
                    			throw new JspTagException(e.getMessage());
                            }
                        } else {
                        	logger.error(MAFLogger.getMessage("viewstate_tag_not_serializable", key, value.getClass().getName()));
                        }
                    }
                }
           } 
       }// end get attributes        
       recycle(); 
       return EVAL_PAGE;        
    }
    
    private void recycle() {
    	name=null;
        encodeAttributes = false;
        encodeParameters = false;
	}

	@SuppressWarnings("unchecked")
	private void removeAllFromSession(HttpSession session, String removerPrefix) {
		// put the request attributes into attributes
    	Enumeration enumeration = session.getAttributeNames();
    	while (enumeration.hasMoreElements()) {
    		String key = (String)enumeration.nextElement();
    		// remove previous viewstate attributes        	
    		if (key.startsWith(removerPrefix))
    			session.removeAttribute(key);
       } 		
	}

	public void putParameter(String key, String value) {
        if (userParameters == null) userParameters = new HashMap<String, String>();
        userParameters.put(key,value);
    }

    private void removeFromSession(HttpSession session, String key) {
    	if (session.getAttribute(key)!=null) {
			session.removeAttribute(key);
		}    	
    }
}
