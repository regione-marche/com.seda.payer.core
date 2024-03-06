package com.seda.j2ee5.maf.core.application;

import java.net.URL;
import java.text.MessageFormat;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.ThreadContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;
/**
 * Application Lifecycle Listener implementation class ApplicationLoader
 *
 */
public class ApplicationLoader implements ServletContextListener {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(ApplicationLoader.class);
	
	public static final String INIT_maf_applications = "maf_applications";
	public static final String DEFAULT_maf_applications = "/WEB-INF/maf-applications.xml"; 
	
	public static final String WEB_XML = "/WEB-INF/web.xml";	
	
	private WebData webData;
	private ApplicationsData applications;
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
    	ServletContext context = event.getServletContext();
    	ThreadContext.put(MAFLogger.MDC_CTX, context.getServletContextName());
    	System.out.println();
    	logger.info(MAFLogger.getMessage("application_loader_starts"));
        String path;
        String initPath = context.getInitParameter(INIT_maf_applications);
        if (initPath!=null && initPath.trim().length()>=0) {
        	path=initPath;
        } else {
        	path=DEFAULT_maf_applications;
        }
    	logger.debug(MAFLogger.getMessage("application_loader_entry", path));
    	
    	URL webXmlURL = null;
        try {
        	webXmlURL = context.getResource(WEB_XML);
        } catch (java.net.MalformedURLException ex) {
        	logger.error(MAFLogger.getMessage("malformed_url", WEB_XML),ex);
        }
    	
        if (webXmlURL != null) {
        	webData = WebXmlDao.loadWebData(webXmlURL);        	
            if (webData==null) {
            	logger.error(MAFLogger.getMessage("application_loader_confirm_web", WEB_XML));
            } 
            context.setAttribute(MAFAttributes.WEB, webData);
        } else {
        	logger.error(MAFLogger.getMessage("url_not_found", WEB_XML));
        }	         
                
        logger.info(MAFLogger.getMessage("application_loader_action_fire", webData.getControllerServlet(),webData.getControllerURL()));
        logger.info(MAFLogger.getMessage("application_loader_template_fire", webData.getTemplateServlet(),webData.getTemplateURL()));        
        
    	URL applicationsResourceURL = null;
        try {
        	applicationsResourceURL = context.getResource(path);
        } catch (java.net.MalformedURLException ex) {
        	logger.error(MAFLogger.getMessage("malformed_url", path),ex);            
        }
        
        if (applicationsResourceURL != null) {
            applications = ApplicationsXmlDao.loadApplications(applicationsResourceURL, context);        	
            if (applications==null) {
            	logger.error(MAFLogger.getMessage("application_loader_confirm_application", path));            	
            } else {
            	// initialize application 
            	applicationInitialize(context);
            	// start application
            	applicationStarter(context);
            }
            context.setAttribute(MAFAttributes.APPLICATIONS, applications);
        } else {
        	logger.error(MAFLogger.getMessage("url_not_found", path));
        }
    	logger.info(MAFLogger.getMessage("application_loader_ends"));
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event) {
    	if (applications!=null) {
        	// initialize application 
        	applicationDestroy(event.getServletContext());
        }
    	ThreadContext.remove(MAFLogger.MDC_CTX);    	
    	applications=null;
    }

	/**
     * This method loop through the application and call the start method of the starter class
     */    
    private void applicationStarter(ServletContext context) {
        // loops through the application
        Iterator<String> iterator = applications.getApplicationsIterator(); 
        // load application if required
        while (iterator.hasNext (  )  )  {  
        	ApplicationData application = applications.getApplication(iterator.next());
        	String applicationName = application.getName();
        	if (application.startupActivation()) {
            	if (application.hasStarter()) {
            		ApplicationStarter starter = loadStarterClass(application);
            		if (starter != null) {
            			try {
            				starter.start(context);
            				application.setActive(true);
            				logger.info(MAFLogger.getMessage("application_loader_started", applicationName));
            			} catch (ApplicationStarterException e) {
            				application.setActive(false);
            				logger.error(MAFLogger.getMessage("application_loader_started_error", applicationName), e);
            			}
            		}
            	} else {
    				application.setActive(true);
    				logger.info(MAFLogger.getMessage("application_loader_started", applicationName));				
            	}        		
        	} else {
				logger.info(MessageFormat.format("Application '{0}' force disabled", applicationName));				
        	}       
        }
    }
    
    /**
     * This method load the necessary ApplicationStarter class necessary to process the start method
     */
    private ApplicationStarter loadStarterClass(ApplicationData application) {
        ApplicationStarter starter = null;
        String starterClass = null;
        if (application != null) {
        	starterClass = application.getStarterClass();
            if (application.startupActivation()) {
                try {
                	starter = (ApplicationStarter)Class.forName(starterClass).newInstance();
//                	starter = (ApplicationStarter)getClass().getClassLoader().loadClass(starterClass).newInstance();
                } catch (Exception ex) {
                	logger.error(MAFLogger.getMessage("application_loader_loading_error", starterClass,application), ex);
                }
            }
        }
        return starter;
    }
    
    
	private void applicationInitialize(ServletContext context) {
		
	}
	
    private void applicationDestroy(ServletContext servletContext) {
		
	}
    
}
