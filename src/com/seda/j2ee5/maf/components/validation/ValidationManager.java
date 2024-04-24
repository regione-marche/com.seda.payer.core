package com.seda.j2ee5.maf.components.validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.ThreadContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.string.Convert;
import com.seda.commons.validator.Validation;
import com.seda.commons.validator.ValidationException;
import com.seda.commons.validator.ValidationMessage;
import com.seda.j2ee5.maf.components.encoding.EncodingContext;
import com.seda.j2ee5.maf.components.encoding.EncodingManager;
import com.seda.j2ee5.maf.core.application.ApplicationsData;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.MAFRequest;
import com.seda.j2ee5.maf.util.MAFUtil;
import com.seda.payer.commons.utility.StringUtility;
/**
 * Servlet Filter implementation class ValidatorManager
 */
public class ValidationManager implements Filter {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ValidationManager.class);
	
	private String defaultIgnoredExt="";
	private String defaultIgnoredSubcontext="";
	private boolean encoding=false;
	private boolean encodingDelete = false;
	
    /**
     * Default constructor. 
     */
    public ValidationManager() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		MAFRequest mafRequest = new MAFRequest((HttpServletRequest)request);
    	// pass the request along the filter chain, if this is a not filtered request
        if (mafRequest.getExtension()!=null && defaultIgnoredExt.contains(mafRequest.getExtension())) {
    		chain.doFilter(request, response);
    		return;
        }
        String subContextCheck=null;
        if (mafRequest.getSubcontext()==null || mafRequest.getSubcontext().trim().length()==0) {
        	subContextCheck=mafRequest.getHttpServletRequest().getServletPath();
        } else {
        	subContextCheck=mafRequest.getSubcontext();
        }

        if (subContextCheck!=null && subContextCheck.trim().length()>0 && subContextCheck.endsWith("//")) {
			((HttpServletResponse)response).sendRedirect(
				Optional.of(request)
					.map(r -> (HttpServletRequest)r)
					.map(r -> r.getRequestURI().toString())
					.map(url -> StringUtility.removeAllEndChar(url, '/'))
					.get()
			);
		}

        if (subContextCheck!=null && subContextCheck.trim().length()>0 && !subContextCheck.trim().equals("/")) {
        	if (defaultIgnoredSubcontext.contains(subContextCheck)) {
        		chain.doFilter(request, response);
        		return;
        	}
        }

        // enforce validation
        boolean rejected=false;
		if (ValidationContext.getInstance().isActive() && 
				mafRequest.getHttpSession()!=null) {
	        String contentType = request.getContentType();
			if (contentType!=null && contentType.equalsIgnoreCase(EncodingManager.URLENCODED)) {
				rejected=enforceValidation(mafRequest);
			} 
		} 
		// clean all previous validation forms with a backup
		ValidationUtil.cleanValidation(mafRequest, true);
		// reject the request
		if (rejected) {
			MAFUtil.rejectRequest(mafRequest.getHttpServletRequest(), (HttpServletResponse)response, true, true);
//			ValidationErrorMap errorMap = (ValidationErrorMap)request.getAttribute(ValidationContext.getInstance().getValidationMessage());
		} else {
			//Pass the request to its destination, if everything
			//is okay or was nothing to do		
			chain.doFilter(request,response);			
		}
	}

	private boolean enforceValidation(MAFRequest mafRequest) throws IOException, ServletException {
		boolean rejected = false;

		String formName; 
		if (encoding && encodingDelete) 
			formName = (String)mafRequest.getAttribute(ValidationContext.getInstance().getValidationFormName());
		else 
			formName = mafRequest.getParameter(ValidationContext.getInstance().getValidationFormName());

		if (formName!=null) {
			rejected = enforceValidationForm(formName, mafRequest);
		} else {
			rejected=true;			
			String msg = String.format("Target url %s has a null '%s' parameter value. Validation skipped.",mafRequest.getTargetURL(), ValidationContext.getInstance().getValidationFormName());
			ValidationMessage message = new ValidationMessage("", "Internal", msg);
			mafRequest.getHttpServletRequest().setAttribute(ValidationContext.getInstance().getValidationMessage(), message);
			logger.error(msg);
		}
		return rejected;
	}

	private boolean enforceValidationForm(String formName, MAFRequest mafRequest) {
		HttpServletRequest request = mafRequest.getHttpServletRequest();
		HttpSession session = mafRequest.getHttpSession();
		
		String formKey = ValidationContext.getInstance().getValidationKey();
		ValidationForm validationForm = (ValidationForm)session.getAttribute(formName.concat(formKey));

		Map<String, String> validatorMap;
		
		if (encoding && encodingDelete) validatorMap = getValidatorMap(mafRequest.getAttributeMap());
		else  validatorMap = getValidatorMap(request.getParameterMap());
		
		String validationMessage = ValidationContext.getInstance().getValidationMessage();
		boolean rejected=false;
		// If the validationForm object is not presents, the source form doesn't have the validation=true attribute
		if (validationForm!=null) {
			// loop through the validation submits to check for an active enforce operation
			for(ValidationSubmit validationSubmit: validationForm.getValidationSubmits()) {
				String name = validationSubmit.getName();
				// try to find the name in the request
				String value = validatorMap.get(name);
				// try to match the submit value with the request (unnecessary?)
				if (value!=null && value.equals(validationSubmit.getValue())) {
					// for a matching value, checks the boolean validate field
					if (validationSubmit.isValidate()) {
						//validate the request form
						Validation validation = new Validation();
						validation.setLocale((Locale)mafRequest.getHttpSession().getAttribute(MAFAttributes.LOCALE));	//04082016 GG
						validation.setUserMessages(validationForm.getUserMessages());
						try {
							ArrayList<ValidationMessage> messages = validation.validateBuffer(validationForm.getValidationBuffer(), validatorMap);
							if (messages.size()>0) {
								rejected=true;
								request.setAttribute(validationMessage, new ValidationErrorMap(formName, messages));
							}
						} catch (ValidationException e) {
							String msg = MAFLogger.getMessage("generic_exception") + " " + e.getMessage();							
							logger.error(msg,e);
							ValidationMessage message = new ValidationMessage("", "Internal", msg);
							request.setAttribute(validationMessage, new ValidationErrorMap(formName, message));
							rejected=true;							
						}
						break;
					}
				}
			}
		} 
		validatorMap=null;
		return rejected;
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> getValidatorMap(Map parametersMap) {
		Map<String, String> validatorMap = new HashMap<String, String>();
		Iterator<String> it = parametersMap.keySet().iterator();
		while (it.hasNext()) {
			String key = (String)it.next();
			String value = null;
			Object o = parametersMap.get(key);
			if (o instanceof String) {
				value = (String)o;
			} else if (o.getClass().isArray()) {
				String[] values = (String[])o;
				value = Convert.arrayToString(values);
			}
			if (value!=null)
				validatorMap.put(key, value);
		}
		return validatorMap;
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		ThreadContext.put(MAFLogger.MDC_CTX, fConfig.getServletContext().getServletContextName());		
		// Get maf application context configuration
		configureApplication(fConfig);
		// configure ValidationContext
		ValidationContext.getInstance().initialize(fConfig.getServletContext());
		encoding=EncodingContext.getInstance().isEncodeParameter();
		encodingDelete=EncodingContext.getInstance().isEncodeDelete();
	}
	
	private void configureApplication(FilterConfig fConfig) {
		ServletContext context = fConfig.getServletContext();
		// get application data from this installazione context
		ApplicationsData applicationsData = (ApplicationsData)context.getAttribute(MAFAttributes.APPLICATIONS);
		if (applicationsData!=null) {
			defaultIgnoredExt=applicationsData.getDefaultIgnoredExt();
			defaultIgnoredSubcontext=applicationsData.getDefaultIgnoredSubcontext();
		}
	}
}
