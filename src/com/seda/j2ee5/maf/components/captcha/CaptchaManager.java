/**
 * 
 */
package com.seda.j2ee5.maf.components.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;
import com.seda.j2ee5.maf.components.encoding.EncodingContext;
import com.seda.j2ee5.maf.components.validation.ValidationUtil;
import com.seda.j2ee5.maf.util.MAFRequest;

/**
 * @author f.ricci
 *
 */
public class CaptchaManager {
	
	private static final LoggerWrapper logger =  CustomLoggerManager.get(CaptchaManager.class);
	
    public static final String CAPTCHA_FIELD = "j_captcha_response";
    public static final String CAPTCHA_LENGTH = "j_captcha_length";    
    public static final String CAPTCHA_WIDTH = "j_captcha_width";
    public static final String CAPTCHA_HEIGHT = "j_captcha_height";
	
	public static final String CAPTCHA_KEY = "captcha_key";
    public static final String CAPTCHA_STRING = "captcha_string";	
    
    public static final String CAPTCHA_CLASS = BaseCaptcha.class.getName();    
    public static final String CAPTCHA_URL = "captcha.do";

	private String captchaApplid=null;
	private String captchaClass = CAPTCHA_CLASS;
	private String captchaURL = CAPTCHA_URL;
	
	private BaseCaptcha delegate=null;
	private boolean configured=false;
	
	private EncodingContext encodingContext = EncodingContext.getInstance();	
	
	public Boolean isCaptchaURL(String targetURL) {
		return captchaURL.equals(targetURL);
	}
	
	public void sendCaptcha(HttpServletRequest request, HttpServletResponse response) {
		if (!configured) {
			logger.error("["+captchaApplid+"] CaptchaManager is not configured. See previous errors.");			
			return;			
		}
		// get captcha modifier
		int captchaLength = getIntParameter(request, CAPTCHA_LENGTH,5);
		int captchaWidth = getIntParameter(request, CAPTCHA_WIDTH,BaseCaptcha.WIDTH);
		int captchaHeight = getIntParameter(request, CAPTCHA_HEIGHT,BaseCaptcha.HEIGHT);

		// just in case... not really necessary to store the session id here
        HttpSession session = request.getSession();
        session.setAttribute(CAPTCHA_KEY, session.getId());
        String cstring = delegate.generateCaptchaString(captchaLength);
        session.setAttribute(CAPTCHA_STRING, cstring);
        
        ValidationUtil.restoreValidation(new MAFRequest(request));
        
        BufferedImage bimg = delegate.getCaptchaImage(cstring,captchaWidth,captchaHeight);
        
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        OutputStream out=null;
		try {
			out = response.getOutputStream();
	        ImageIO.write(bimg, "jpeg", out);			
		} catch (IOException e) {
			logger.error("["+captchaApplid+"] Exception during send captcha " +e.getMessage(), e);
		} finally {
			if (out!=null) {
				try {
			        out.flush();
			        out.close();					
				} catch (IOException e) {}
			}
		}
			
	}
	
	 /**
     * @return boolean true if captcha is correct
     */
    public Boolean isCaptchaCorrect(HttpServletRequest request) {
        Boolean validResponse = Boolean.FALSE;    	
		if (!configured) {
			logger.error("["+captchaApplid+"] CaptchaManager is not configured. See previous errors.");			
			return validResponse;			
		}    	
        String captchaString = getSafeParameter(request, CAPTCHA_FIELD);
        if (captchaString == null) {
            return validResponse;
        }
        HttpSession session = request.getSession();
        String storedString = (String) session.getAttribute(CAPTCHA_STRING);
        if (storedString != null && storedString.equals(captchaString)) {
            validResponse = Boolean.TRUE;
        }
        return validResponse;
    }	
	
	protected int getIntParameter(HttpServletRequest request, String paramName, int def) {
		String parameter=getSafeParameter(request, paramName);
		if (parameter==null)
			return def;
		
		int value=def;
		
		try {
			value=Integer.parseInt(parameter);
		} catch (Exception x) {
			logger.warn("["+captchaApplid+"] Encoded attribute '"+paramName+"' is not a valid integer: " +parameter + ", will be set to default " + def);			
			value=def;
		}
	
		return value;

	}
	
	protected String getSafeParameter(HttpServletRequest request, String paramName) {
		String parameter=null;
		if (encodingContext.isEncodeParameter()) {
			Object object = request.getAttribute(paramName);
			if (object!=null) {
				if (object instanceof String) {
					parameter = (String)object;				
				} else {
					logger.warn("["+captchaApplid+"] Encoded attribute '"+paramName+"' is not a java.lang.String class: " +object.getClass().getName() + ", toString=" +object);
				}				
			} 
		} else {
			parameter = (String)request.getParameter(paramName);	
		}
		return parameter;
	}
	
	public CaptchaManager(String captchaApplid, String captchaUrl, String captchaClass) {
		this.captchaApplid=captchaApplid;
		
		if (captchaUrl!=null && captchaUrl.trim().length()>0)
			this.captchaURL=captchaUrl;
		
		if (captchaClass!=null && captchaClass.trim().length()>0) {
			this.captchaClass=captchaClass;
		}
		
		buildDelegate();
	}

	protected void buildDelegate() {
		Class<?> c=null;
		try {
			c = Class.forName(captchaClass);
		} catch (ClassNotFoundException e) {
			logger.error("[%] Unable to load captcha class " + captchaClass);
			return;
		}
		
		try {
			delegate = (BaseCaptcha)c.newInstance();
		} catch (Exception e) {
			logger.error("[%] Unable to create an instance of " + captchaClass);
			return;
		}
		
		configured=true;
	}

	public static final void applyCaptchaManager(Object object,
			CaptchaManager captchaManager) {

		if (object==null)
			return;
		
		Class<?> currentClass = object.getClass();
		while (currentClass != null) {
			// we also need to look for interface methods - 
			// because the class may be abstract
			Field[] arrFields = currentClass.getDeclaredFields();
			for (int i = 0; i < arrFields.length; i++) {
				if (arrFields[i].getName().equals("captchaManager")) {
					if (!arrFields[i].isAccessible()) arrFields[i].setAccessible(true);
					try {
						arrFields[i].set(object, captchaManager);
					} catch (Exception e) {
						logger.error("Unable to set field 'captchaManager' in object " + object.getClass().getName(),e);
					} 
					return;
				}
			}
			currentClass = currentClass.getSuperclass();
		}		
	}	
}
