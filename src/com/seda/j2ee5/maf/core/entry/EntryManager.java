package com.seda.j2ee5.maf.core.entry;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.ThreadContext;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.captcha.CaptchaManager;
import com.seda.j2ee5.maf.components.defender.bfl.BFLContext;
import com.seda.j2ee5.maf.components.defender.bfl.BFLLockedException;
import com.seda.j2ee5.maf.components.defender.csrf.CSRFDefender;
import com.seda.j2ee5.maf.core.action.ActionData;
import com.seda.j2ee5.maf.core.action.ActionManager;
import com.seda.j2ee5.maf.core.action.ComplexActionTable;
import com.seda.j2ee5.maf.core.application.ApplicationBinderFactory;
import com.seda.j2ee5.maf.core.application.ApplicationsData;
import com.seda.j2ee5.maf.core.menu.MenuData;
import com.seda.j2ee5.maf.core.menu.MenuDataMap;
import com.seda.j2ee5.maf.core.menu.MenuItemData;
import com.seda.j2ee5.maf.core.screen.ScreenWriter;
import com.seda.j2ee5.maf.core.security.RealmManager;
import com.seda.j2ee5.maf.core.security.RealmProfilesManager;
import com.seda.j2ee5.maf.core.security.RealmRolesManager;
import com.seda.j2ee5.maf.core.security.SecurityData;
import com.seda.j2ee5.maf.core.security.SecuritySSO;
import com.seda.j2ee5.maf.core.security.SecuritySignOn;
import com.seda.j2ee5.maf.core.security.SignOnException;
import com.seda.j2ee5.maf.core.security.SignOnKeys;
import com.seda.j2ee5.maf.core.security.SignOnRules;
import com.seda.j2ee5.maf.core.security.UserBeanSupport;
import com.seda.j2ee5.maf.template.TemplateMap;
import com.seda.j2ee5.maf.template.TemplateRuntimeSupport;
import com.seda.j2ee5.maf.template.TemplateScreen;
import com.seda.j2ee5.maf.template.TemplateScreenMap;
import com.seda.j2ee5.maf.util.MAFAttributes;
import com.seda.j2ee5.maf.util.MAFContext;
import com.seda.j2ee5.maf.util.MAFCookies;
import com.seda.j2ee5.maf.util.MAFLogger;
import com.seda.j2ee5.maf.util.MAFRequest;
import com.seda.j2ee5.maf.util.MAFUtil;
import com.seda.j2ee5.maf.util.URLUtil;

/**
 * Servlet Filter implementation class EntryManager
 */
public class EntryManager implements Filter {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(EntryManager.class);
	
	private FilterConfig config = null;
	private ServletContext context = null;	
	
	private HashMap<String, String> subcontextMap;
	private HashMap<String, String> welcomeMap;
	private HashMap<String, Boolean> activeMap;	
	
	private SecurityData securityData=null;
	private SignOnRules signOnRules=null;
	private String guestProfile;
	private String anonymousRole;
	private String message;
	
	private HashMap<String, Boolean> protectedMap;
	private HashMap<String, Boolean> rememberProtectedURLMap;
	private HashMap<String, RealmManager> realmManagers;
    private HashMap<String, ActionManager> actionManagers;
    private HashMap<String, CaptchaManager> captchaManagerMap;
    private HashMap<String, TemplateMap> templateMapCollection;
	private RealmProfilesManager realmProfilesManager;	
	private RealmRolesManager realmRolesManager;	
	private SecuritySignOn signOn=null;
	private SecuritySSO sso=null;
//	private HashMap<String, String> binderClasses;
	private MenuDataMap mainMenuDataMap;
	private HashMap<String, MenuData> applicationsMenu;
	private HashMap<String, ComplexActionTable> complexActionTableMap;
	
	private BFLContext bflContext = BFLContext.getInstance();
	private String loginPage=null;
	private String securityErrorPage=null;
	private String afterLogin=null;
	
    private String defaultApplication;
    private String defaultSubContext;
    private String defaultIgnoredExt="";
    private String defaultIgnoredSubcontext="";
    
    private boolean dormant;
    
    private String getWelcomeURL(String contextPath,String subcontext, String welcome) {
    	return URLUtil.urlRedirect(contextPath,URLUtil.setLastSlash(subcontext)+welcome);
    }
    
    private String getDefaultSubContextURL(String contextPath) {
    	return URLUtil.urlRedirect(contextPath,defaultSubContext);
    }
    
    private String getLoginPageURL(String contextPath) {
    	return URLUtil.urlRedirect(contextPath, loginPage);
    }
	private String getSecurityErrorPageURL(String contextPath) {
		return URLUtil.urlRedirect(contextPath, securityErrorPage);
	}
	private String getAfterLoginURL(String contextPath) {
		return URLUtil.urlRedirect(contextPath, afterLogin);		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// ************** Manca il controllo che i redirect alla login alla security siano disponibili *******************
		// Initialization
		HttpServletRequest hreq = (HttpServletRequest)request;
		HttpServletResponse hres = (HttpServletResponse)response;
		HttpSession session = hreq.getSession();
		// check for dormant state
		if (dormant) {
			String message = MAFLogger.getMessage("dormant_state");
        	error(message);
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message + ": EntryManager", hres);			
			return;
		}		
        // Get everything after the context root ignoring all after the last slash
		MAFRequest mafRequest = new MAFRequest(hreq);
        
        String currentURL = mafRequest.getCurrentURL();
        String currentContext = mafRequest.getCurrentContext();
        String realContext = mafRequest.getRealContext();
        String afterContext = mafRequest.getAfterContext();
        String subcontext = mafRequest.getSubcontext();
        String targetURL = mafRequest.getTargetURL();
        String extension = mafRequest.getExtension();
        // check for a matching context
        if (!currentContext.equals(realContext)) {
        	String message = MAFLogger.getMessage("entry_manager_mismatch_context", realContext, currentContext);
        	error(message);
        	// send the error to the screen
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, hres);
			return;
        }
    	// pass the request along the filter chain, if this is a not filtered request        
        if (extension!=null && defaultIgnoredExt.contains(extension)) {
    		chain.doFilter(request, response);
    		return;
        }
        String subContextCheck=null;
        if (mafRequest.getSubcontext()==null || mafRequest.getSubcontext().trim().length()==0) {
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

        // define redirect security page
		String securityErrorPageURL = getSecurityErrorPageURL(hreq.getContextPath());
		String loginPageURL = getLoginPageURL(hreq.getContextPath());
        // get the user bean from the session
    	UserBeanSupport beanSupport = (UserBeanSupport) session.getAttribute(SignOnKeys.USER_BEAN);
		// Check for a sign on or single sign on. Note: the sub context is not required for all type of sign on.
        if (securityData.isEnabled()) {
        	if (targetURL != null) {
	        	if (targetURL.equals(SignOnKeys.FORM_SIGNON_URL)) {
		            validateSignOn(hreq, hres, chain);
		            // jump out of this method
		            return;	        		
	        	}
	        	if (targetURL.equals(SignOnKeys.FORM_SSO_URL)) {
		            validateSSO(hreq, hres, chain);
		            // jump out of this method
		            return;	        		
	        	}
	        }        	
        }
        // if nothing is specified after context, go to the default sub context or login page
        if (afterContext==null || afterContext.equals("/")) {
        	// hreq.removeAttribute(MAFAttributes.CURRENT_APPLICATION);
        	info(MAFLogger.getMessage("entry_manager_empty_after",currentURL));
        	String encodedURL;
        	if (securityData.isEnabled() && isProtected(defaultApplication)) {
//        		encodedURL = hres.encodeURL(loginPageURL);
        		if (loginPage==null || 
        				loginPage.trim().length()==0 || 
        				(loginPage.trim().length()==1 && loginPage.trim().startsWith("/"))) {
        			// send the error to the screen
                	String message = "The required application or page can not be directly started or is protected and you are not logged in";;
                	error(message);        			
        			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, hres);
		            return;        			
        		}
        		encodedURL = loginPageURL;
        	} else {
        		String defaultSubContextURL = getDefaultSubContextURL(hreq.getContextPath());
//        		encodedURL = hres.encodeURL(defaultSubContextURL);
        		encodedURL = defaultSubContextURL;
        	}
        	CSRFDefender.setRedirect(request); // tell to the csrfdefender that this is a redirect
        	hres.sendRedirect(encodedURL);
        	return;
        }
        // Analyzing the sub context and the target url for welcome action and valid configuration
        String application=null;
        if (subcontext!=null && subcontext.startsWith("/")) {
        	application = getApplicationName(subcontext);
            if (application==null) {
            	// hreq.removeAttribute(MAFAttributes.CURRENT_APPLICATION);
            	String message = MAFLogger.getMessage("entry_manager_application_not_found", subcontext);
            	error(message);
            	// send the error to the screen
				ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, hres);
        		return; 
            } else {
            	hreq.setAttribute(MAFAttributes.CURRENT_APPLICATION,application);
            	// check for a not null target url, in this case redirect to the welcome of this sub context
            	if (targetURL==null || targetURL.length()==0) {
                	CSRFDefender.setRedirect(request); // tell to the csrfdefender that this is a redirect
		            
            		String welcome = getWelcome(application);
            		String welcomeURL = getWelcomeURL(hreq.getContextPath(), subcontext, welcome);
//            		String encodedURL = hres.encodeURL(welcomeURL);
            		String encodedURL = welcomeURL;            		
            		hres.sendRedirect(encodedURL);
            		return;
            	}
            }
        } else {
        	// not valid subcontext
        	// hreq.removeAttribute(MAFAttributes.CURRENT_APPLICATION);
        	String message = MAFLogger.getMessage("entry_manager_invalid_subcontext", subcontext);
        	error(message);
        	// skip processing
        	// send the error to the screen
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, hres);
    		return;         	
        }
        // verify if this requests application is in an active state
        if (!isActive(application)) {
        	// hreq.removeAttribute(MAFAttributes.CURRENT_APPLICATION);
        	String message = MAFLogger.getMessage("entry_manager_application_not_active", application);
        	error(message);
        	// send the error to the screen        	
        	ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), message, hres);
        	return;
        }
        // Check for captcha request
        CaptchaManager captchaManager = captchaManagerMap.get(application);
        if (captchaManager!=null && captchaManager.isCaptchaURL(targetURL)) {
        	// tell to the csrfdefender that this is a redirect
    		CSRFDefender.setWriter(mafRequest.getHttpServletRequest()); 
//Andrea Nella versione 1.2 avevamo   getMAFHttpRequest()    		
        	// send captcha
    		captchaManager.sendCaptcha(mafRequest.getHttpServletRequest(), hres);
//Andrea Nella versione 1.2 avevamo   getMAFHttpRequest()
        	// enforce response
    		CSRFDefender.enforceResponse(mafRequest.getHttpServletRequest());
//Andrea Nella versione 1.2 avevamo   getMAFHttpRequest()
        	return;
        }
        // Get the complex action table map and verify for a new action id
        ComplexActionTable complexActionTable = complexActionTableMap.get(application);
        String complexAction = complexActionTable.get(targetURL);
        boolean modifiedAction = !complexAction.equals(targetURL);
        // Check for profile and role
    	String beanProfile = null;    	
    	String beanRole = null;
    	if (beanSupport==null) {
    		// no user bean found in session, set the default guest profile and anonymous roles
    		beanProfile=guestProfile;
    		beanRole=anonymousRole;
    	} else {
    		beanProfile = beanSupport.getProfile();
    		if (beanProfile==null || beanProfile.trim().length()==0) {
        		// if no profile for this installation, set the guest profile
    			beanProfile=guestProfile;
    		}
    		beanRole= beanSupport.getRole(application);
    		if (beanRole==null || beanRole.trim().length()==0) {
        		// if no role for this application, set the anonymous roles
    			beanRole=anonymousRole;
    		}
    	}
    	// save the main menu related to the user profile
    	ArrayList<MenuItemData> menu = null;
    	MenuData mainMenu = TemplateRuntimeSupport.resolveMenuApplicationData(mainMenuDataMap, hreq, application);
    	if (mainMenu!=null) {
    		menu = mainMenu.getMenu(beanProfile);
    		MenuData.selectByApplication(application, menu);
    		session.setAttribute(SignOnKeys.MAIN_MENU, menu);    		
    	}

		// --
		ArrayList<MenuItemData> applicationMenu = applicationsMenu.get(application).getMenu(beanRole);
		// try to find a menuaction attribute modifier		
		String menuKey=(String)request.getAttribute("menuaction");
		// or, try to find it in the parameter	
		if (menuKey==null) {
			menuKey=(String)request.getParameter("menuaction");
		}
		// if menuaction modifier is not present
		if (menuKey==null) {
			// check for a action request 
			ActionData actionData = actionManagers.get(application).getActionData(complexAction);
			if (actionData!=null) {
				menuKey = actionData.getMenuActionUrl();
			} else {
				// or may be a screen
				String screenName = URLUtil.getUrlKey(complexAction);
				// get the TemplateMap for the current application
		        TemplateMap templateMap = templateMapCollection.get(application);
		        if (templateMap!=null) {
		        	TemplateScreenMap templateScreenMap = TemplateRuntimeSupport.resolveTemplateScreenMap(templateMap, hreq, application);
		        	if (templateScreenMap!=null) {
			        	TemplateScreen screen = templateScreenMap.getScreen(screenName);
						if (screen!=null)
							menuKey = screen.getMenuActionUrl();	
		        	}
		        }
			}			
		}
		if (menuKey!=null) {
			MenuData.selectByAction(menuKey, applicationMenu);
			hreq.getSession().setAttribute(SignOnKeys.APPLICATION_MENU, applicationMenu);			
		} else {
			// not action neither screen o parameter modifier was found
			hreq.getSession().removeAttribute(SignOnKeys.APPLICATION_MENU);			
		}
        // Check for enabled security
        if (securityData.isDisabled()) {
        	// for a complex url, forward to the right action instead of chain
        	if (modifiedAction) {
        		//forward to the right action
        		context.getRequestDispatcher("/"+complexAction).forward(request, response);  
        	} else {
            	// pass the request along the filter chain
        		chain.doFilter(request, response);        		
        	}
    		return;
        }
        //*** Security is enabled ***//
    	// check if the current url matches the login page. Note: this skip the security checking because
        // the login page is, per convention, not protected
        if (currentURL.equals(loginPageURL)) {
			chain.doFilter(request,response);
			return;        			
		}
        // check if the current url matches the security error page. Note: this skip the security checking because
        // the security error page is, per convention, not protected
        if (currentURL.equals(securityErrorPageURL)) {
			chain.doFilter(request,response);
			return;        			
		}        
        // check if the user is signed on
        boolean signedOn = false;
        if (session.getAttribute(SignOnKeys.SIGNED_ON_USER) != null) {
            signedOn =((Boolean)session.getAttribute(SignOnKeys.SIGNED_ON_USER)).booleanValue();
        } else {
            session.setAttribute(SignOnKeys.SIGNED_ON_USER, new Boolean(false));
        }
        // if the request in not protected
        if (!isProtected(application)) {
        	if (modifiedAction) {
        		//forward to the right action
        		context.getRequestDispatcher("/"+complexAction).forward(request, response);  
        	} else {
	        	// pass the request along the filter chain
	    		chain.doFilter(request, response);
        	}
    		return;        	
        }
        // jump to the login page if not signed on
        if (!signedOn) {
        	// put the original url in the session so others can access
        	String originalContext = URLUtil.urlRedirect(hreq.getContextPath(),subcontext);
        	String originalURL = URLUtil.urlRedirect(originalContext,targetURL);
        	rememberURL(session,application,originalURL);
        	String message=MessageFormat.format(signOnRules.getNotSignedonMessage(), application);
        	String loginPage = URLUtil.addParameter(loginPageURL, this.message, message);
//        	String encodedURL = hres.encodeURL(loginPage);
        	String encodedURL = loginPage;        	
        	MAFUtil.regenerateSession(hreq); // to prevent session fixation attack        	
    		hres.sendRedirect(encodedURL);
        	CSRFDefender.setRedirect(request); // tell to the csrfdefender that this is a redirect
    		// Log this attack
        	String remoteIP = hreq.getRemoteAddr(); // get user identity
    		error(MAFLogger.getMessage("entry_manager_not_signed_on_detail", remoteIP, application));
        	// Jump out of the filter and go to the login page
        	return;        	
        }	
        // check the security for this user bean
    	if (beanSupport!=null) {
    		// check for the same session id
    		//String currentSession = session.getId();
    		String currentSession;
    		String userBeanSession;
    		
    		// jboss clustered session id length and format (h8X8W-7KGpDvp57kMpwyZQ__.node1)
    		if (session.getId().trim().length()==30 && session.getId().indexOf("__.") != -1) {
    			currentSession=session.getId().substring(0, session.getId().indexOf("__."));
    			userBeanSession=beanSupport.session().substring(0, beanSupport.session().indexOf("__."));
    		} else {
    			currentSession=session.getId();
        		userBeanSession=beanSupport.session();
    		}
    		
    		if (!currentSession.equals(userBeanSession)) {
    			String message = MAFLogger.getMessage("entry_manager_mismatch_session");
        		String encodedURL = URLUtil.addParameter(securityErrorPageURL, this.message, message);
            	MAFUtil.regenerateSession(hreq); // to prevent session fixation attack        		
        		hres.sendRedirect(encodedURL);
            	CSRFDefender.setRedirect(request); // tell to the csrfdefender that this is a redirect
    			// Log this attack
            	String remoteIP = hreq.getRemoteAddr(); // get user identity
        		error(MAFLogger.getMessage("entry_manager_mismatch_session_detail", remoteIP, 
        				beanSupport.name(),beanSupport.session(),session.getId()));
	        	return;    			
    		}
    		
    		// check for protected application before chain
    		RealmProfilesManager profilesManager = getRealmProfilesManager();
    		if (profilesManager!=null) {
    			if (!profilesManager.checkPassThrough(application, beanProfile)) {
    				// put the original url in the session so others can access
        			String originalContext = URLUtil.urlRedirect(hreq.getContextPath(),subcontext);
                	String originalURL = URLUtil.urlRedirect(originalContext,targetURL);
                	rememberURL(session,application,originalURL);
                	// redirect to security error page
        			String message = MAFLogger.getMessage("entry_manager_profile_error");
            		String encodedURL = URLUtil.addParameter(securityErrorPageURL, this.message, message);
                	MAFUtil.regenerateSession(hreq); // to prevent session fixation attack            		
            		hres.sendRedirect(encodedURL);
                	CSRFDefender.setRedirect(request); // tell to the csrfdefender that this is a redirect
            		// Log this attack
                	String remoteIP = hreq.getRemoteAddr(); // get user identity
            		error(MAFLogger.getMessage("entry_manager_profile_error_detail", remoteIP, 
            				beanSupport.name(),beanProfile,application));
    	        	return;
    			}
    		}
    		// check for protected action before chain
    		RealmRolesManager rolesManager = getRealmRolesManager();
    		if (rolesManager!=null) {
    			if (!rolesManager.checkPassThrough(application, complexAction, beanRole)) {
    				// put the original url in the session so others can access
        			String originalContext = URLUtil.urlRedirect(hreq.getContextPath(),subcontext);
                	String originalURL = URLUtil.urlRedirect(originalContext,targetURL);
                	rememberURL(session,application,originalURL);
                	// redirect to security error page
        			String message = MAFLogger.getMessage("entry_manager_role_error");
            		String encodedURL = URLUtil.addParameter(securityErrorPageURL, this.message, message);
                	MAFUtil.regenerateSession(hreq); // to prevent session fixation attack            		
            		hres.sendRedirect(encodedURL); 
                	CSRFDefender.setRedirect(request); // tell to the csrfdefender that this is a redirect
            		// Log this attack
                	String remoteIP = hreq.getRemoteAddr(); // get user identity
            		error(MAFLogger.getMessage("entry_manager_role_error_detail", remoteIP, 
            				beanSupport.name(),beanRole, targetURL, application));
    	        	return;
    			}
    		}
    		// check for protected resource, more granularity
        	// get the securityRealm for the application
        	RealmManager realmManager = getRealmManager(application);
        	//check for protected resources before chain
        	if (realmManager!=null) {
        		if (realmManager.checkPassThrough(complexAction, beanRole)) {
                	if (modifiedAction) {
                		//forward to the right action
                		context.getRequestDispatcher("/"+complexAction).forward(request, response);  
                	} else {
	        			// pass the request along the filter chain because it is all in security
			            chain.doFilter(request,response);
                	}
        		} else {
        			// put the original url in the session so others can access
        			String originalContext = URLUtil.urlRedirect(hreq.getContextPath(),subcontext);
                	String originalURL = URLUtil.urlRedirect(originalContext,targetURL);
                	rememberURL(session,application,originalURL);
                	// redirect to security error page
        			String message = MAFLogger.getMessage("entry_manager_role_error");
            		String encodedURL = URLUtil.addParameter(securityErrorPageURL, this.message, message);
                	MAFUtil.regenerateSession(hreq); // to prevent session fixation attack            		
            		hres.sendRedirect(encodedURL);
                	CSRFDefender.setRedirect(request); // tell to the csrfdefender that this is a redirect
            		// Log this attack
                	String remoteIP = hreq.getRemoteAddr(); // get user identity
            		error(MAFLogger.getMessage("entry_manager_role_error_detail", remoteIP, 
            				beanSupport.name(),beanRole, targetURL, application));
    	        	return;
        		}
        	} else {
            	if (modifiedAction) {
            		//forward to the right action
            		context.getRequestDispatcher("/"+complexAction).forward(request, response);  
            	} else {
    	        	// pass the request along the filter chain because the realm manager is null
            		chain.doFilter(request,response);
            	}
        	}  
    	} else {
    		ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), MAFLogger.getMessage("entry_manager_userbean_missing"), hres);
    		return;
    	}
	}

	private void rememberURL(HttpSession session, String application, String originalURL) {
    	if (isRememberProtectedURL(application)) {
        	session.setAttribute(SignOnKeys.ORIGINAL_URL,  originalURL);
    	} else {
    		session.removeAttribute(SignOnKeys.ORIGINAL_URL);
    	}
	}

	public  void validateSignOn(HttpServletRequest hreq, HttpServletResponse  hres, 
			FilterChain chain) throws IOException, ServletException {
		
		if (signOn==null) {
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), MAFLogger.getMessage("entry_manager_signon_missing"), hres);
		} else {
			// get the user name
			String userName = hreq.getParameter(SignOnKeys.FORM_USER_NAME);
			// get the password
			String password = hreq.getParameter(SignOnKeys.FORM_PASSWORD);
			// check for login rule set
			boolean usrOk=(userName==null?false:signOnRules.usrMatches(userName));
			boolean pswOk=(password==null?false:signOnRules.pswMatches(password));
			if (!usrOk || !pswOk) {
				//suspect xss attack
				fatal(MAFLogger.getMessage("entry_manager_signon_usr_psw_xss", 
						userName, hreq.getRemoteAddr(), pswOk?"*":password));
				// reject authentication
				rejectAuthentication(signOnRules.getInvalidMessage(),hreq,hres);
				return;
			}

			// check if the user wants userName set in cookie
			String rememberUserName = hreq.getParameter(SignOnKeys.REMEMBER_USERNAME);
			try {
				if (rememberUserName != null) {
					if (rememberUserName.equalsIgnoreCase("on")) {
						// set a cookie with the user name in
						Cookie userNameCookie = new Cookie(SignOnKeys.COOKIE_NAME, userName);
						// set cookie to last for one month
						userNameCookie.setMaxAge(2678400);
						// add the cookie
						hres.addCookie(userNameCookie);						
					} else {
						// xss attack!!!!
						MAFUtil.deleteCookie(hreq, hres, SignOnKeys.COOKIE_NAME);
						error(MAFLogger.getMessage("entry_manager_signon_cookies_xss", 
								userName, hreq.getRemoteAddr(),rememberUserName));
						
						String loginPage = getLoginPageURL(hreq.getContextPath());
//			        	String encodedURL = hres.encodeURL(loginPage);
			        	String encodedURL = loginPage;        	
			        	MAFUtil.regenerateSession(hreq); // to prevent session fixation attack        	
			    		hres.sendRedirect(encodedURL);
					}
				} else {
					MAFUtil.deleteCookie(hreq, hres, SignOnKeys.COOKIE_NAME);
				}				
			} catch (Exception x) {
				error(MAFLogger.getMessage("entry_manager_signon_cookies_error", 
						SignOnKeys.COOKIE_NAME, x.getMessage()), x);
			}

			String bfDetected=null;
			try {
				try {
					bflContext.doAttempt(userName, hreq.getRemoteAddr());
				} catch (BFLLockedException e) {
					fatal(MAFLogger.getMessage("entry_manager_signon_bf", 
							userName, hreq.getRemoteAddr(),e.getMessage()));
					bfDetected=signOnRules.getLockedMessage();						
				}
				synchronized (signOn) {
					MAFCookies.applyCookiesSupport(signOn, hreq, hres);
					UserBeanSupport userBean = signOn.authenticate(userName, password, hreq);
					if (bfDetected!=null) {
						// in case of sign on exception reject the authentication				
						rejectAuthentication(bfDetected,hreq,hres);					
					} else {
						bflContext.remove(userName); // remove from brute force buffer manager for successfully login
						confirmAuthentication(userBean,hreq,hres);
					}					
				}
			} catch (SignOnException x) {
				String rejectMessage=x.getMessage(); // get the message from the sign on exception
				switch (x.getState()) {
				case SignOnException.STATE_NOT_FOUND:
					// in case of a not existing account					
					error(MAFLogger.getMessage("entry_manager_signon_state_not_found", 
							userName, hreq.getRemoteAddr()));
					// if this not registered account fired a brute force attack
					if (bfDetected!=null) {
						bflContext.remove(userName); // remove from brute force buffer manager for not_found
					}
					if (rejectMessage==null) {
						fatal(MAFLogger.getMessage("entry_manager_signon_bf2", 
								userName, hreq.getRemoteAddr()));
						rejectMessage=bfDetected!=null?bfDetected:signOnRules.getNotfoundMessage();
					}				
				case SignOnException.STATE_ERROR:
					error(MAFLogger.getMessage("entry_manager_signon_state_error", 
							userName, hreq.getRemoteAddr(), rejectMessage));
					// in case of a general failure
					break;
				case SignOnException.STATE_NOT_AUTHENTICATED:
				default:
					// in case of a bad user name or password					
					error(MAFLogger.getMessage("entry_manager_signon_state_not_authenticated", 
							userName, hreq.getRemoteAddr()));
					break;
				}
				// in case of sign on exception reject the authentication				
				rejectAuthentication(rejectMessage,hreq,hres);
			} catch (Exception x) {
				// send the error to the screen
				MAFUtil.regenerateSession(hreq); // to prevent session fixation attack
				ScreenWriter.writeError(x, MAFLogger.getMessage("apologies"), MAFLogger.getMessage("entry_manager_signon_process_error",signOn.getClass().getName()), hres);
			} 
		}
	}	
	
	public  void validateSSO(HttpServletRequest hreq, HttpServletResponse  hres, 
			FilterChain chain) throws IOException, ServletException {

		synchronized (sso) {
			if (sso==null) {
				// send an apologies message
				hres.setContentType("text/html;charset="+hreq.getCharacterEncoding());
				ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), MAFLogger.getMessage("entry_manager_sso_missing"), hres);
			} else {
				try {
					MAFCookies.applyCookiesSupport(sso, hreq, hres);
					UserBeanSupport userBean = sso.authenticate(hreq);
					confirmAuthentication(userBean,hreq,hres);
				} catch (SignOnException x) {
					// in case of sign on exception reject the authentication
					rejectAuthentication(x.getMessage(),hreq,hres);
				} catch (Exception x) {
					// send the error to the screen
					MAFUtil.regenerateSession(hreq); // to prevent session fixation attack
					ScreenWriter.writeError(x, MAFLogger.getMessage("apologies"),MAFLogger.getMessage("entry_manager_sso_process_error",sso.getClass().getName()), hres);
				} 
			}			
		}
	}
	
	private void confirmAuthentication(UserBeanSupport userBean, HttpServletRequest hreq, HttpServletResponse hres) throws IOException, ServletException {
		if (userBean!=null) {
			// Always generate a new session to which the user will log in if successfully authenticated. 
			// Prevent user ability to manipulate session ID. 
			// Do not accept session IDs provided by the user's browser at login 
        	MAFUtil.regenerateSession(hreq); // to prevent session fixation attack		
			// save the session id of the user bean after login
			userBean.setSession(hreq.getSession().getId());
			// save the remote ip of the user bean after login
			userBean.setRemoteIp(hreq.getRemoteAddr());			
			// remove the sign on user key before putting it back in
			if (hreq.getSession().getAttribute(SignOnKeys.SIGNED_ON_USER) != null) {
				hreq.getSession().removeAttribute(SignOnKeys.SIGNED_ON_USER);
			}
			info(MAFLogger.getMessage("entry_manager_auth_session",userBean.session(), userBean.name(), userBean.remoteIp()));
			// place a true boolean in the session
			hreq.getSession().setAttribute(SignOnKeys.SIGNED_ON_USER, new Boolean(true));
			// bind each application the user bean
			bindApplications(hreq,userBean);
			// store the new user bean in the session
			if (hreq.getSession().getAttribute(SignOnKeys.USER_BEAN) != null) {
				hreq.getSession().removeAttribute(SignOnKeys.USER_BEAN);
			}
			hreq.getSession().setAttribute(SignOnKeys.USER_BEAN, userBean);
			// redirect to the original destination
			String targetURL = (String)hreq.getSession().getAttribute(SignOnKeys.ORIGINAL_URL);
			// if no original destination was found
			if (targetURL==null) {
				//use base security information for after successfully login
				targetURL = getAfterLoginURL(hreq.getContextPath());
			}
//			String encodedURL = hres.encodeURL(targetURL);
			String encodedURL = targetURL;
    		hres.sendRedirect(encodedURL);
        	CSRFDefender.setRedirect(hreq); // tell to the csrfdefender that this is a redirect
		} else {
			// in case of sign on exception reject the authentication
			rejectAuthentication(MAFLogger.getMessage("entry_manager_userbean_missing"),hreq,hres);	
		}		
	}
	
	private void rejectAuthentication(String message, HttpServletRequest hreq, HttpServletResponse hres) throws IOException, ServletException {
		String securityErrorPageURL = getSecurityErrorPageURL(hreq.getContextPath());
		if (securityErrorPageURL!=null) {
			//String encodedURL = URLUtil.addParameter(securityErrorPageURL, this.message, message);;
        	MAFUtil.regenerateSession(hreq); // to prevent session fixation attack			
        	HttpSession session = hreq.getSession();
        	session.setAttribute("message", message);
    		hres.sendRedirect(securityErrorPageURL);
        	CSRFDefender.setRedirect(hreq); // tell to the csrfdefender that this is a redirect
		} else {
			// send the error to the screen
			ScreenWriter.writeError(null, MAFLogger.getMessage("apologies"), MAFLogger.getMessage("entry_manager_security_page"), hres);
		}
	}
	
	private void bindApplications(HttpServletRequest hreq, UserBeanSupport userBean) {
		ApplicationBinderFactory.instance().getApplicationBinder().bind(hreq, userBean);
		
//		Iterator<String> iterator = binderClasses.keySet().iterator(); 
//        // load application if required
//        while (iterator.hasNext (  )  )  {
//        	String applicationName = iterator.next();
//        	String className = binderClasses.get(applicationName);
//        	if (className!=null) {
//        		try {
//        			UserBeanApplicationBinder binderClass = loadBinderClass(className);
//        			if (binderClass!=null) {
//        				binderClass.init(hreq);
//        				String role = binderClass.bind(userBean);
//        				binderClass.end();
//        				userBean.setRole(applicationName, role==null?"":role);
//        			} else {
//        				logger.debug(MAFLogger.getMessage("entry_manager_binder_missing",applicationName));
//        			}
//
//        		} catch (Exception x) {
//        			error(MAFLogger.getMessage("entry_manager_binder_error",className,applicationName),x);        			
//        		}
//        	}
//        }   
	}	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		config = fConfig;
		context = config.getServletContext();
		ThreadContext.put(MAFLogger.MDC_CTX, context.getServletContextName());		
//		contextPath = context.getContextPath();
		// get MAF message attribute name		
		this.message=MAFContext.DEFAULT_MESSAGE;
		String initMessage = context.getInitParameter(MAFContext.CONTEXT_MESSAGE);
		if (initMessage!=null)
			this.message=initMessage;
		// get context parameter
		String guestProfile = context.getInitParameter(MAFContext.CONTEXT_GUEST);
		if (guestProfile==null || guestProfile.trim().length()==0) {
			this.guestProfile=MAFContext.DEFAULT_GUEST;
		} else {
			this.guestProfile=guestProfile.trim();
		}
		context.setAttribute(MAFAttributes.GUEST, guestProfile);
		info(MAFLogger.getMessage("entry_manager_profile_default", this.guestProfile));
		
		String anonymousRole = context.getInitParameter(MAFContext.CONTEXT_ANONYMOUS);
		if (anonymousRole==null || anonymousRole.trim().length()==0) {
			this.anonymousRole=MAFContext.DEFAULT_ANONYMOUS;
		} else {
			this.anonymousRole=anonymousRole.trim();
		}
		context.setAttribute(MAFAttributes.ANONYMOUS, anonymousRole);
		info(MAFLogger.getMessage("entry_manager_role_default", this.anonymousRole));		
		// get application data from this installazione context
		ApplicationsData applicationsData = (ApplicationsData)context.getAttribute(MAFAttributes.APPLICATIONS);
		// if no applications was found, we are in a stopped state 
        // Warning: the applications must be instantiated from the application loader at the context created event        
        if (applicationsData==null) {
        	error(MAFLogger.getMessage("application_data_missing"));
        	dormant=true;
        } else {
        	dormant=false;        	
        	// Application management
            subcontextMap = applicationsData.getSubcontextMap();        	
            activeMap = applicationsData.getActivationMap();            
            welcomeMap = applicationsData.getWelcomeMap();
        	// Application management - default            
            defaultApplication=applicationsData.getDefaultApplication();
            defaultSubContext=applicationsData.getApplication(defaultApplication).getSubcontext();
//            defaultSubContextURL=URLUtil.urlRedirect(contextPath,defaultSubContext);
        	// Security management            
        	defaultIgnoredExt=applicationsData.getDefaultIgnoredExt();
            defaultIgnoredSubcontext=applicationsData.getDefaultIgnoredSubcontext();        	
        	protectedMap=applicationsData.getProtectedApplicationsMap();
        	rememberProtectedURLMap=applicationsData.getRememberProtectedURLMap();
        	realmProfilesManager=applicationsData.getRealmProfilesManager();
        	realmRolesManager=applicationsData.getRealmRolesManager();        	
        	realmManagers=applicationsData.getRealmManagers();
        	securityData=applicationsData.getSecurityData();
        	mainMenuDataMap=applicationsData.getMenuDataMap();
        	applicationsMenu=applicationsData.getApplicationsMenu();
        	complexActionTableMap=applicationsData.getComplexActionTableMap();
//        	binderClasses=applicationsData.getBinderClasses();
        	actionManagers=applicationsData.getActionManagers();
        	captchaManagerMap=applicationsData.getCaptchaManagers();
        	templateMapCollection=applicationsData.getTemplateMapping();
        	
        	logger.debug("subcontextMap " + subcontextMap + "");
        	logger.debug("activeMap " +  activeMap + "");
        	logger.debug("rememberProtectedURLMap " + rememberProtectedURLMap);
        	logger.debug("welcomeMap " +  welcomeMap + "");
        	logger.debug("actionManagers " +  actionManagers + "");
        	logger.debug("captchaManagers " +  captchaManagerMap + "");
        	logger.debug("templateMapCollection " +  templateMapCollection + "");
        	logger.debug("defaultApplication " + defaultApplication + "");
        	logger.debug("defaultSubContext " + defaultSubContext + "");
        	logger.debug("defaultIgnoredExt " + defaultIgnoredExt + "");
        	logger.debug("protectedMap " + protectedMap + "");
        	logger.debug("realmProfilesManager " +  realmProfilesManager + "");
        	logger.debug("realmRolesManager " +  realmRolesManager + "");
        	logger.debug("realmManagers " +  realmManagers + "");
        	logger.debug("securityData " + securityData + "");
        	logger.debug("mainMenuDataMap " + mainMenuDataMap + "");
        	logger.debug("applicationsMenu " +  applicationsMenu + "");        	
        	logger.debug("complexActionTableMap " +  complexActionTableMap + "");
//        	logger.debug("binderClasses " +  binderClasses + ""); 
        }
        
        if (securityData!=null) {
        	loginPage=securityData.getLoginPage();
        	securityErrorPage=securityData.getSecurityErrorPage();
        	if (securityData.getAfterLogin()==null) {
        		afterLogin=defaultSubContext;
        	} else {
            	afterLogin=securityData.getAfterLogin();        		
        	}
        	logger.debug("loginPage " + loginPage + "");
        	logger.debug("securityErrorPage " +  securityErrorPage + "");        	
        	logger.debug("afterLogin " +  afterLogin + "");        	        	
            if (securityData.getSignOnClass()!=null) {
            	signOn=loadSignOnClass(securityData.getSignOnClass());
            	if (signOn!=null) {
            		try {
						signOn.init(context);
					} catch (SignOnException e) {
						error(MAFLogger.getMessage("entry_manager_signon_init_error", securityData.getSignOnClass()),e);
						signOn=null;
					}
            	}
            }
            if (securityData.getSsoClass()!=null) {
            	sso=loadSSOClass(securityData.getSsoClass());
            	if (sso!=null) {
            		try {
            			sso.init(context);
					} catch (SignOnException e) {
						error(MAFLogger.getMessage("entry_manager_sso_init_error", securityData.getSsoClass()),e);
						sso=null;
					}
            	}            	
            }
            signOnRules=securityData.getSignOnRules();
        }
		// Brute Force login
        bflContext.initialize(signOnRules);
	}

	private String getApplicationName(String subcontext) {
    	String applicationName=null;
		if (subcontextMap!=null)
			applicationName = subcontextMap.get(subcontext);
		return applicationName;
	}
	
	private String getWelcome(String applicationName) {
    	String welcome=null;
		if (welcomeMap!=null)
			welcome = welcomeMap.get(applicationName);
		return welcome;
	}
	
	private boolean isActive(String applicationName) {
    	Boolean active=false;
		if (applicationName!=null)
			active = activeMap.get(applicationName);
		return active;
	} 		
	
	private boolean isRememberProtectedURL(String applicationName) {
    	Boolean remember=false;
		if (applicationName!=null)
			remember = rememberProtectedURLMap.get(applicationName);
		return remember;
	} 		
	
	private RealmProfilesManager getRealmProfilesManager() {
		return realmProfilesManager;
	}
	
	private RealmRolesManager getRealmRolesManager() {
		return realmRolesManager;
	}
	
	private RealmManager getRealmManager(String applicationName) {
		RealmManager realmManager=null;
		if (realmManagers!=null) {
			realmManager = realmManagers.get(applicationName);
		}	
		return realmManager;
	}	
	
	private boolean isProtected(String applicationName) {
    	boolean isProtected=false;
		if (protectedMap!=null) {
			isProtected = protectedMap.get(applicationName);
		}	
		return isProtected;
	}	
	
	 /**
     * This method load the necessary SignOn class necessary to process the login request
     */
    private SecuritySignOn loadSignOnClass(String className) {
    	SecuritySignOn signOnHandler = null;
    	try {
    		signOnHandler = (SecuritySignOn)getClass().getClassLoader().loadClass(className).newInstance();
    	} catch (Exception ex) {
    		dormant=true;
    		error(MAFLogger.getMessage("entry_manager_signon_load_error", className),ex);
    	}
    	return signOnHandler;
    }
    
	 /**
     * This method load the necessary Single SignOn class necessary to validate the entry request
     */
    private SecuritySSO loadSSOClass(String className) {
    	SecuritySSO ssoHandler = null;
    	try {
    		ssoHandler = (SecuritySSO)getClass().getClassLoader().loadClass(className).newInstance();
    	} catch (Exception ex) {
    		dormant=true;
    		error(MAFLogger.getMessage("entry_manager_sso_load_error", className),ex);    		
    	}
    	return ssoHandler;
    }     
	
    /**
     * This method load the necessary Application Binder class necessary to enrich the user bean
     */
//    private UserBeanApplicationBinder loadBinderClass(String className) {
//    	UserBeanApplicationBinder applicationBinderHandler = null;
//    	try {
//    		applicationBinderHandler = (UserBeanApplicationBinder)getClass().getClassLoader().loadClass(className).newInstance();
//    	} catch (Exception ex) {
//    		dormant=true;
//    		error(MAFLogger.getMessage("entry_manager_binder_load_error", className),ex);    		
//    	}
//    	return applicationBinderHandler;
//    }  

    
    private void error(String m) {
    	ThreadContext.put(MAFLogger.MDC_CTX, context.getServletContextName());
    	logger.error(m);
    }
    
    private void error(String m, Throwable t) {
    	ThreadContext.put(MAFLogger.MDC_CTX, context.getServletContextName());
    	logger.error(m,t);
    }
    
    private void info(String m) {
    	ThreadContext.put(MAFLogger.MDC_CTX, context.getServletContextName());
    	logger.info(m);
    }
//    private void warn(String m) {
//    	ThreadContext.put(MAFLogger.MDC_CTX, context.getServletContextName());
//    	logger.warn(m);
//    }
    private void fatal(String m) {
    	ThreadContext.put(MAFLogger.MDC_CTX, context.getServletContextName());
    	logger.error(m);
    }
    
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.config=null;
		if (signOn!=null) {
			this.signOn.term();		
		}
		if (sso!=null) {
			this.sso.term();		
		}			
	}
	
}
