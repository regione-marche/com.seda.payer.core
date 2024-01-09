package com.seda.j2ee5.maf.core.menu;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import com.seda.commons.logger.CustomLoggerManager;
import com.seda.commons.logger.LoggerWrapper;

public class MenuDataMap {

	private static final LoggerWrapper logger =  CustomLoggerManager.get(MenuDataMap.class);
	
	private HashMap<String, MenuData> menuMap;	
	
	public MenuDataMap() {
		super();
	}	
	
	public MenuDataMap(HashMap<String, MenuData> menuMap) {
		super();
		this.menuMap = menuMap;
	}

	public void addMenuData(String template, MenuData menuData) {
		if (menuMap==null)
			menuMap = new HashMap<String, MenuData>();
		menuMap.put(template, menuData);
	}
	
	public MenuData getMenuData(String template) {
		
		if (menuMap!=null){
			if (menuMap.containsKey(template))
				return menuMap.get(template);
		}
		
		//logger.debug("MenuData for template '"+template+"' not found. Default will be used.");
		return getDefaultMenuDataMap();
	}
	
	public MenuData getDefaultMenuDataMap() {
		if (menuMap!=null){			
			Enumeration<String> keyEnum = Collections.enumeration(menuMap.keySet());
		
			while (keyEnum.hasMoreElements()) {
				String key = keyEnum.nextElement();
				if (menuMap.get(key).isDefaultMenuData())
					return menuMap.get(key);
			}
		}
		
		logger.debug("MenuData default not found. null set.");		
		return null;
	}
	
}
