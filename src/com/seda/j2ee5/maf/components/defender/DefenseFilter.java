package com.seda.j2ee5.maf.components.defender;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.captcha.CaptchaManager;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFConfig;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFContext;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFDefender;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFException;
import com.seda.j2ee5.maf.core.action.ActionData;
import com.seda.j2ee5.maf.core.action.ActionManager;
import com.seda.j2ee5.maf.core.action.ComplexActionTable;
import com.seda.j2ee5.maf.core.application.ApplicationsData;
//import com.seda.j2ee5.maf.core.application.WebData;
import com.seda.j2ee5.maf.core.screen.ScreenWriter;
import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;
import com.seda.j2ee5.maf.defender.repository.GlobalRuleSet;
import com.seda.j2ee5.maf.defender.repository.RuleSet;
import com.seda.j2ee5.maf.defender.violation.FatalViolationException;
//import com.seda.j2ee5.maf.taglibs.ViewStateManager;
//import com.seda.j2ee5.maf.template.TemplateManager;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.MAFRequest;
import com.seda.j2ee5.maf.util.MAFUtil;
//import com.seda.j2ee5.maf.util.URLUtil;

/**
 * Servlet Filter implementation class DefenseFilter
 */
public class DefenseFilter implements Filter {

	private String contextName; 
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(DefenseFilter.class);
	
	private String defaultIgnoredExt="";
	private String defaultIgnoredSubcontext="";	
//	private String screenSuffix=TemplateManager.DEFAULT_SCREEN_SFX;
	// Application defense configuration
	private HashMap<String, ComplexActionTable> complexActionTableMap;	
	private Map<String, ActionManager> actionManagerMap;  
	private Map<String, GlobalRuleSet> globalRuleSetMap;
	// CSRF defender
	boolean csrfDenied = false;
	// this filter configuration
	private FilterConfig config;
    /**
     * Default constructor. 
     */
    public DefenseFilter() {
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
		// if (!CSRFConfig.isActive(request..get..getServletContext())) {
		if (true) {
    		chain.doFilter(request, response);
			return;
		} //incompatibile con la javaee-api
		
	    // Get everything after the context root ignoring all after the last slash
		MAFRequest mafRequest = new MAFRequest((HttpServletRequest)request);
    	// pass the request along the filter chain, if this is a not filtered request
		// FIX ME: anche sulle immagini e sui css e su tutto quello che e' href= o src=
		//         va fissato il token per csrf
        if (mafRequest.getExtension()!=null && defaultIgnoredExt.contains(mafRequest.getExtension())) {
    		chain.doFilter(request, response);
    		return;
        }
        String subContextCheck=null;
        if (mafRequest.getSubcontext()==null || mafRequest.getSubcontext().trim().length()==0 ) {
        	subContextCheck=mafRequest.getHttpServletRequest().getServletPath();
        } else {
        	subContextCheck=mafRequest.getSubcontext();
        }

        if (subContextCheck!=null && subContextCheck.trim().length()>0 && !subContextCheck.trim().equals("/")) {
        	if (defaultIgnoredSubcontext.contains(subContextCheck)) {
        		chain.doFilter(request, response);
        		return;
        	}
        }
        
		HttpDefenseRequest defenseRequest;
		if (request instanceof HttpDefenseRequest)
			defenseRequest = (HttpDefenseRequest) request;
		else
			defenseRequest = new HttpDefenseRequest((HttpServletRequest)request);

		String subcontext = mafRequest.getSubcontext();
		String action = mafRequest.getTargetURL();
		// if null context or there is not an action, wait for a valid uri request
		// this will be result in a send redirect in the entry manager
		if (subcontext==null || action==null || action.trim().length()==0) {
			chain.doFilter(defenseRequest, response);
			// remember to enforce response to prevent unexpected error
			CSRFDefender.enforceResponse(defenseRequest);
			return;        
		}
		
		boolean enforceCsrf = isEnforceCsrf(subcontext, action);
		// detect CSRF attacks
		try {
			CSRFDefender.enforceRequest(defenseRequest, enforceCsrf);
		} catch (CSRFException x) {
			fatal(x.getMessage());
			if (response instanceof HttpServletResponse ) {
				if (csrfDenied) {
					ScreenWriter.sendError(response, 404, "Not found");					
				} else {
					try  {
						((HttpServletResponse)response).setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
						MAFUtil.rejectRequest((HttpServletRequest)defenseRequest, (HttpServletResponse)response, true, false);
					} catch (Throwable t) {
						fatal("Reject request error: " + t.getMessage());
						ScreenWriter.sendError(response, 404, "Not found");
					}
				}
			}
			return;
		}
		// detect general attacks on parameters, (cookies and headers coming soon)
		try {
			enforceRequest(mafRequest,defenseRequest);
		} catch (FatalViolationException x) {
			fatal(x.getViolation().toString());
			if (response instanceof HttpServletResponse ) {
				ScreenWriter.sendError(response, 404, "Not found");
			}
			return;
		}
		// chain along the filter
		chain.doFilter(defenseRequest, response);
		// CSRF generator if the token is null for session destroy action
		CSRFDefender.enforceResponse(defenseRequest);	
		
	}
	private void enforceRequest(MAFRequest mafRequest, HttpDefenseRequest request
			) throws FatalViolationException {
		
		String subcontext = mafRequest.getSubcontext();
		String action = mafRequest.getTargetURL();
		// if null context, wait for a valid uri request
		if (subcontext==null || action==null)
			return;
 
		// for a complex action
		if (complexActionTableMap.containsKey(subcontext)) {
			if (complexActionTableMap.get(subcontext).contains(action))
				action=complexActionTableMap.get(subcontext).get(action);			
		}
		
		GlobalRuleSet globalRuleSet;
		RuleSet ruleSet;
		Defender defender;
		try {
			globalRuleSet = getGlobalRuleSet(subcontext);
			ruleSet = getRuleSet(subcontext, action);
			// if no configuration was made
			if (globalRuleSet==null || ruleSet==null)
				return;
			defender = new Defender(globalRuleSet, ruleSet);
			defender.enforce(subcontext, action, request);			
		} finally {
			globalRuleSet=null;
			ruleSet=null;
			defender=null;
		}
	}


	private GlobalRuleSet getGlobalRuleSet(String subcontext) {
		if (globalRuleSetMap!=null)
			return globalRuleSetMap.get(subcontext);
		return null;
	}	

	
	private boolean isEnforceCsrf(String subcontext, String url) {
		boolean enforceCsrf = true;
		ActionData actionData = getActionData(subcontext, url);
		if (actionData!=null) {
			enforceCsrf = actionData.isEnforceCsrf();
		}
		return enforceCsrf;
	}	
	
	private RuleSet getRuleSet(String subcontext, String url) {
		RuleSet ruleSet = null;
		ActionManager actionManager = getActionManager(subcontext);
		if (actionManager!=null) {
			ruleSet = actionManager.getRuleSet(url);
		}
		return ruleSet;
	}

	private ActionData getActionData(String subcontext, String url) {
		ActionData actionData = null;
		ActionManager actionManager = actionManagerMap.get(subcontext);
		if (actionManager!=null) {
			actionData = actionManager.getActionData(url);
		}
		return actionData;
	}		
	
	private ActionManager getActionManager(String subcontext) {
		ActionManager actionManager = null;
		if (actionManagerMap!=null) {
			actionManager = actionManagerMap.get(subcontext);
		}
		return actionManager;
	}	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.contextName = fConfig.getServletContext().getServletContextName();		
		this.config=fConfig;
		// Get maf application context configuration
		configureApplication(fConfig);
		// CSRF Defender configuration
		CSRFContext.getInstance().initialize(config.getServletContext());
		csrfDenied = CSRFContext.getInstance().isDenied();
	}

	private void configureApplication(FilterConfig fConfig) {
		ServletContext context = fConfig.getServletContext();
		// get application data from context
		ApplicationsData applicationsData = (ApplicationsData)context.getAttribute(MAFAttributes.APPLICATIONS);
		if (applicationsData!=null) {
			defaultIgnoredExt=applicationsData.getDefaultIgnoredExt();
			defaultIgnoredSubcontext=applicationsData.getDefaultIgnoredSubcontext();
			globalRuleSetMap=applicationsData.getGlobalRuleSetMap();
			actionManagerMap=applicationsData.getActionManagerMap();
        	complexActionTableMap=applicationsData.getComplexActionTableMap(false);
		}
	}

//	private void info(String message) {
//		ThreadContext.put(MAFLogger.MDC_CTX, contextName);
//		logger.info(message);
//	}
//	private void warn(String message) {
//		ThreadContext.put(MAFLogger.MDC_CTX, contextName);
//		logger.warn(message);
//	}
//	private void error(String message) {
//		ThreadContext.put(MAFLogger.MDC_CTX, contextName);
//		logger.error(message);
//	}
	private void fatal(String message) {
		ThreadContext.put(MAFLogger.MDC_CTX, contextName);
		logger.error(message);
	}	
}
