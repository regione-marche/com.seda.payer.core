package com.seda.j2ee5.maf.template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.commons.string.Convert;
import com.seda.j2ee5.maf.core.application.ApplicationsData;
import com.seda.j2ee5.maf.core.screen.ScreenWriter;
import com.seda.j2ee5.maf.taglibs.IfTag;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.URLUtil;

/**
 * Servlet implementation class TemplateManager
 */
public class TemplateManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(TemplateManager.class);
	
	public static final String DEFAULT_SCREEN_SFX=".screen";	
	
    private ServletContext context;
    
    private HashMap<String, TemplateMap> templateMapCollection;
   
    private boolean dormant;        
    
    protected void setDormant(boolean dormant) {this.dormant=dormant;}
    
    protected void setContext(ServletContext context) {
    	
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TemplateManager() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
		ThreadContext.put(MAFLogger.MDC_CTX, config.getServletContext().getServletContextName());        
        // store the general application servlet context        
        this.context = config.getServletContext();
        //
        ApplicationsData applicationsData = (ApplicationsData) context.getAttribute(MAFAttributes.APPLICATIONS);
        // if no applications was found, we are in a stopped state 
        // Warning: the applications must be instantiated from the application loader at the context created event        
        if (applicationsData==null) {
        	logger.error(MAFLogger.getMessage("application_data_missing"));        	
        	dormant=true;
        } else {        	
            this.templateMapCollection=applicationsData.getTemplateMapping();
        	dormant=false;            
        }
    }    
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected final void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		// Remove last current Url		
		if (request.getSession().getAttribute(MAFAttributes.CURRENT_URL) != null) {
			request.getSession().removeAttribute(MAFAttributes.CURRENT_URL);
		}		
		// The language when specified as a parameter overrides the language set in the session
		Locale locale = null;		
		String languageParam = request.getParameter(MAFAttributes.LOCALE);
		if (languageParam != null) {
			locale = Convert.I18n.stringToLocale(languageParam);
		} else if (request.getSession().getAttribute(MAFAttributes.LOCALE) != null) {
			locale = (Locale)request.getSession().getAttribute(MAFAttributes.LOCALE);
		}
		if (locale !=null) {
			// response.setLocale(locale); ??????????? what can i do with this locale?
			// pay attention, the response.setLocale change the response Header
		}
		// Cache-Control to no- cache will keep browsers from locally caching responses for cases 
		// in which duplicate requests for the same URL (including URL parameters) may return different responses
		// Set to expire far in the past.
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
		  // Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		  // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		  // Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// call super method
		super.service(request, response);					
	}    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);		
	}

	public void process(HttpServletRequest request, HttpServletResponse  response) throws IOException, ServletException {
		
		if (dormant) {
        	String message = MAFLogger.getMessage("dormant_state");
        	logger.error(message);
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, response);			
			return;
		}		

        String application = (String)request.getAttribute(MAFAttributes.CURRENT_APPLICATION);
        if (application==null) {
        	// if no current application was found in the request
        	// skip the template process
        	String message = MAFLogger.getMessage("application_missing");
        	logger.error(message);
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, response);        	
			return;        	
        }
        // get the TemplateMap for the current application
        TemplateMap templateMap = templateMapCollection.get(application);
        if (templateMap==null) {
        	String message = "TemplateMap not found for application '"+application+"'";
        	logger.error(message);
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, response);        	
			return;        	
        }
               
		String selectedUrl = request.getRequestURI();
		// get the screen name		
		String screenName = URLUtil.getUrlKey(selectedUrl);		
		// check if request is for the previous screen
		if (screenName.equalsIgnoreCase(MAFAttributes.PREVIOUS)) {
			// get previous application
			application = (String) request.getSession().getAttribute(MAFAttributes.PREVIOUS_SCREEN_APPLICATION);
			// get the previous screen name			
			String longScreenName = (String)request.getSession().getAttribute(MAFAttributes.PREVIOUS_SCREEN);			
			screenName = URLUtil.getUrlKey(longScreenName); 			
		} else {
			String extension = URLUtil.getUrlExtension(selectedUrl);
			request.getSession().setAttribute(MAFAttributes.PREVIOUS_SCREEN, screenName + extension);
			request.getSession().setAttribute(MAFAttributes.PREVIOUS_SCREEN_APPLICATION, application);			
		}
		// get the screen information for the corresponding language
		TemplateScreenMap templateScreenMap=TemplateRuntimeSupport.resolveTemplateScreenMap(templateMap, request, application);
	
		if (templateScreenMap==null) {
			String message = MAFLogger.getMessage("template_manager_templatescreens_not_found",application);
        	logger.error(message);
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, response);
		}
		
		if (screenName != null){
			// gets the resource bundle name for i18n direct include
			String resourceBundle = templateScreenMap.getResourceBundle();
			request.setAttribute(MAFAttributes.RESOURCE_BUNDLE, resourceBundle);
			TemplateScreen screen = templateScreenMap.getScreen(screenName);
			if (screen != null) {
				request.setAttribute(MAFAttributes.CURRENT_SCREEN, screen);
				String templateName = templateScreenMap.getTemplate(screenName);
				if (templateName != null) {
					insertTemplate(request, response, templateName);						
				}
			} else {
				String message = MAFLogger.getMessage("template_manager_definition_screen_not_found",application,screenName);
				logger.error(message);
				ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, response);					
			}				
		}			
	}
	
    private void insertTemplate(HttpServletRequest request, HttpServletResponse response, 
    		String templateName) throws IOException, ServletException {    	
		
    	try {
			context.getRequestDispatcher(templateName).forward(request, response);
		} catch (Throwable t) {
			// inserire la gestione del redirect allo screen di errore
			// come fatto nel controller usando lo screen manager e le exceptions
		} finally {
			
		}
    }
	/**
	 * This method tries to wrap the request dispatcher call with-in
	 * a transaction for making the local EJB access efficient. However,
	 * it is not a fatal error, if such a transaction can not be started
	 * for some reason, so it handles them gracefully.
	 */
//        private void insertTemplate(HttpServletRequest request, HttpServletResponse response, 
//        		String templateName) throws IOException, ServletException {    	
//    	boolean tx_started = false;
//		UserTransaction ut = null;
//		
//		try {
//			// Lookup the UserTransaction object
//			InitialContext ic = new InitialContext();
//			ut = (UserTransaction) ic.lookup("java:comp/UserTransaction");
//			ut.begin();         // start the transaction.
//			tx_started = true;
//		} catch (NamingException x) {
//			// it should not have happened, but it is a recoverable error.
//			// Just don't start the transaction.
//			logger.error("",x);
//		} catch (NotSupportedException x) {
//			// Again this is a recoverable error.
//			logger.error("",x);
//		} catch (SystemException x) {
//			// Again this is a recoverable error.
//			logger.error("",x);
//		}
//		
//		try {
//			context.getRequestDispatcher(templateName).forward(request, response);
//		} finally {
//			// commit the transaction if it was started earlier successfully.
//			try {
//				if (tx_started && ut != null) {
//					ut.commit();
//				}
//			} catch (IllegalStateException x) {
//				logger.error("",x);
//			} catch (RollbackException x) {
//				logger.error("",x);
//			} catch (HeuristicMixedException x) {
//				logger.error("",x);
//			} catch (HeuristicRollbackException x) {
//				logger.error("",x);
//			} catch (SystemException x) {
//				logger.error("",x);
//			}
//		}
//	}	
//    
}
