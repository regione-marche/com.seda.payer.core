/**
 * 
 */
package com.seda.j2ee5.maf.core.application;

import java.util.HashMap;

import com.seda.j2ee5.maf.core.controller.ControllerManager;
import com.seda.j2ee5.maf.template.TemplateManager;
import com.seda.j2ee5.maf.util.URLUtil;
/**
 * 
 * @author Seda Lab
 *
 */
public class WebData {
	
	public HashMap<String, WebServlet> servletMap = new HashMap<String, WebServlet>(); 
	
	public final static String CONTROLLER_CLASS = ControllerManager.class.getName(); 
	public final static String TEMPLATE_CLASS = TemplateManager.class.getName();	
	
	private String controllerServlet;
	private String controllerURL;
	
	private String templateServlet;
	private String templateURL;
	
	public String getControllerServlet() {
		return controllerServlet;
	}
	public String getControllerURL() {
		return controllerURL;
	}
	public String getTemplateServlet() {
		return templateServlet;
	}
	public String getTemplateURL() {
		return templateURL;
	}

	public String getTemplateExtention() {
		return URLUtil.getUrlExtension(templateURL);
	}	
	
	public WebData(String controllerServlet, String controllerURL, 
			String templateServlet, String templateURL, HashMap<String, WebServlet> servletMap) {
		super();
		this.controllerServlet = controllerServlet;
		this.controllerURL = controllerURL;
		this.templateServlet = templateServlet;
		this.templateURL = templateURL;
		this.servletMap=servletMap;
	}
	@Override
	public String toString() {
		return "WebData [servletMap=" + servletMap + ", controllerServlet="
				+ controllerServlet + ", controllerURL=" + controllerURL
				+ ", templateServlet=" + templateServlet + ", templateURL="
				+ templateURL + "]";
	}

}
