/**
 * 
 */
package com.seda.j2ee5.maf.template;

import java.io.Serializable;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;

/**
 * @author f.ricci
 *
 */
public class TemplateMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, TemplateScreenMap> templateMap;
	private String applid;

	private static final LoggerWrapper logger =  CustomLoggerManager.get(TemplateMap.class);
	
	public TemplateMap(String applid, HashMap<String, TemplateScreenMap> templateScreenMap) {
		super();
		this.templateMap = templateScreenMap;
		this.applid=applid;
	}
	
	public TemplateScreenMap getTemplateScreenMap(String name) {
		if (templateMap.containsKey(name))
			return templateMap.get(name);
		logger.warn("TemplateScreenMap '"+name+"' not found. Default will be used.");
		return getDefaultTemplateScreenMap();
	}
	
	public TemplateScreenMap getDefaultTemplateScreenMap() {
		Enumeration<String> keyEnum = Collections.enumeration(templateMap.keySet());
		while (keyEnum.hasMoreElements()) {
			String key = keyEnum.nextElement();
			if (templateMap.get(key).isDefaultTemplateScreenMap())
				return templateMap.get(key);
		}
		logger.warn("TemplateScreenMap default not found. null set.");		
		return null;
	}

	@Override
	public String toString() {
		return "TemplateMap [applid=" + applid + ", templateMap="
				+ templateMap + "]";
	}
	
	
}
