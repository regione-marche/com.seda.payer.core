/**
 * 
 */
package com.seda.j2ee5.maf.template;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.seda.j2ee5.maf.core.menu.MenuData;
import com.seda.j2ee5.maf.core.menu.MenuDataMap;
import com.seda.j2ee5.maf.core.security.SignOnKeys;
import com.seda.j2ee5.maf.core.security.UserBeanSupport;
import com.seda.j2ee5.maf.util.MAFAttributes;

/**
 * @author f.ricci
 *
 */
public class TemplateRuntimeSupport {

	
	public static TemplateScreenMap resolveTemplateScreenMap(TemplateMap templateMap, HttpServletRequest request, String application) {
		TemplateScreenMap templateScreenMap=null;
		String templateScreenMapName = resolveTemplateName(request, application);
		// get the screen information for the corresponding language
		if (templateScreenMapName==null)
			templateScreenMap=templateMap.getDefaultTemplateScreenMap();
		else {
			templateScreenMap=templateMap.getTemplateScreenMap(templateScreenMapName);
		}
		return templateScreenMap;
	}
	
	public static MenuData resolveMenuApplicationData(MenuDataMap menuDataMap, HttpServletRequest request, String application) {
		MenuData menuData = null;
		String templateScreenMapName = resolveTemplateName(request, application);
		// get the screen information for the corresponding language
		if (menuDataMap!=null) {
			if (templateScreenMapName==null)
				menuData=menuDataMap.getDefaultMenuDataMap();
			else {
				menuData=menuDataMap.getMenuData(templateScreenMapName);
			}					
		}
		return menuData;
	}
	
	public static String resolveTemplateName(HttpServletRequest request, String application) {
		// try to find the template name modifier in the request
		String templateScreenMapName = (String)request.getAttribute(MAFAttributes.CURRENT_APPLICATION_TEMPLATE);
		if (templateScreenMapName==null) {
			// try ti find it in the user bean
			HttpSession session = request.getSession(false);
			if (session!=null) {
				UserBeanSupport userBeanSupport = (UserBeanSupport)session.getAttribute(SignOnKeys.USER_BEAN);
				if (userBeanSupport!=null) {
					templateScreenMapName=userBeanSupport.getTemplate(application);
				}
			}
		}
		return templateScreenMapName;
	}
	
}
