/**
 * 
 */
package com.seda.j2ee5.maf.core.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Seda Lab
 *
 */
public class MenuData implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean defaultMenuData=false;
	
	private HashMap<String, ArrayList<MenuItemData>> menuMap;

	public boolean isDefaultMenuData() {
		return defaultMenuData;
	}
	
	public void setDefaultMenuData(boolean defaultMenuData) {
		this.defaultMenuData=defaultMenuData;
	}
	
	public MenuData() {
		menuMap = new HashMap<String, ArrayList<MenuItemData>>();
	}
	/**
	 * 
	 * @param key the profile or role name
	 * @param value menu 
	 */
	public void putMenu(String key, ArrayList<MenuItemData> value) {
		if (menuMap.containsKey(key)) {
			menuMap.remove(key);
        }
		menuMap.put(key, value);		
	}
	/**
	 * @return all information about menu and roles or profiles
	 */
	public Map<String, ArrayList<MenuItemData>> getMenuMap() {
		return menuMap;
	}
	/**
	 * 
	 * @param key the profile or role name
	 * @return menu
	 */
	public ArrayList<MenuItemData> getMenu(String key) {
		return menuMap.get(key);
	}
	/**
	 * 
	 * @param key profile or role name
	 * @param value menu item
	 */
	public void putMenuItem(String key, MenuItemData value) {
		ArrayList<MenuItemData> menuItemList = menuMap.get(key);
		if (menuItemList==null) {
			menuItemList=new ArrayList<MenuItemData>();
			menuMap.put(key, menuItemList);
		}

		menuItemList.add(value);
	}

	public static void selectByApplication(String applid, ArrayList<MenuItemData> menuItemList) {
		if (menuItemList!=null && applid!=null) {
			for (MenuItemData menuItemData : menuItemList) {
				if (menuItemData.isEnabled() && applid.equals(menuItemData.getApplid())) {
					menuItemData.setSelected(true);
				} else {
					menuItemData.setSelected(false);
				}
			}
		}
	}
	
	public static void selectByAction(String action, ArrayList<MenuItemData> menuItemList) {
		if (menuItemList!=null && action!=null) {
			for (MenuItemData menuItemData : menuItemList) {
				if (menuItemData.isEnabled() && action.equals(menuItemData.getAction())) {
					menuItemData.setSelected(true);
				} else {
					menuItemData.setSelected(false);
				}
			}
		}
	}	
	
	
	@Override
	public String toString() {
		return "MenuData [menuMap=" + menuMap + "]";
	}
}
