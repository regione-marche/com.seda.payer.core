/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.seda.commons.string.Convert;
import com.seda.j2ee5.maf.defender.http.HttpDefenseRequest;

/**
 * Gives the ability to read the content of a buffered view state. To buffer a view state use the {@link ViewStateTag} in a jsp page.
 * 
 * @author Seda Lab
 *
 */
public class ViewStateManager {

	public static final String SUFFIX_ATTRIBUTES="_attributes_";
	public static final String SUFFIX_PARAMETERSVALUES="_parametersValues";
	public static final String SUFFIX_PARAMETERS="_parameters";
	public static final String SUFFIX_USERPARAMETERS="_user_parameters";
	
	private HttpSession session;	
	private HttpServletRequest request;
	private HttpDefenseRequest defenseRequest;
	
	@SuppressWarnings("unchecked")
	private HashMap attributes;
	private HashMap<String, String[]> parametersValues;
	private HashMap<String, String> parameters;	
	private HashMap<String, String> userParameters;
	
	@SuppressWarnings("unchecked")
	public HashMap getAttributes(){return this.attributes;}
	public HashMap<String, String[]> getParametersValues() {return this.parametersValues;}
	public HashMap<String, String> getParameters() {return this.parameters;}	
	public HashMap<String, String> getUserParameters() {return this.userParameters;} 
	
	public ViewStateManager(HttpServletRequest request, String viewstateName) {
		this.request=request;
		if (request instanceof HttpDefenseRequest) {
			defenseRequest = (HttpDefenseRequest)request;
		}
		this.session=request.getSession(false);
		try {
			this.attributes = loadAttributes(viewstateName);
			this.parametersValues = loadParametersValues(viewstateName);
			this.parameters=loadParameters(viewstateName);
			this.userParameters = loadUserParameters(viewstateName);			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void clean(HttpServletRequest request, String viewstateName) {
		HttpSession session = request.getSession(false);
		if (session!=null) {
			String prefixAttribute = viewstateName+SUFFIX_ATTRIBUTES;
			String keyParametersValues = viewstateName+SUFFIX_PARAMETERSVALUES;
			String keyParameters = viewstateName+SUFFIX_PARAMETERS;
			String keyUserParameters = viewstateName+SUFFIX_USERPARAMETERS;
			
			Enumeration sessionNames = session.getAttributeNames();
			ArrayList<String> sessionNameList = new ArrayList<String>();
			while (sessionNames.hasMoreElements()) {
				sessionNameList.add((String) sessionNames.nextElement());
			}
			for (String sessionName : sessionNameList) {
				if (sessionName.startsWith(prefixAttribute)) 
					session.removeAttribute(sessionName);	
			}
			
			session.removeAttribute(keyParametersValues);
			session.removeAttribute(keyParameters);
			session.removeAttribute(keyUserParameters);
		}
	}
	
	public String getUserParameter(String key) {
		String userParameter=null;
		if (this.userParameters!=null) {
			userParameter = getUserParameters().get(key);
		}
		return userParameter;
	}		
	
	public Object getAttribute(String key) {
		Object attribute=null;
		if (this.attributes!=null) {
			attribute = getAttributes().get(key);
		}
		return attribute;
	}
	
	public String[] getParameterValues(String key) {
		String[] parameterValues=null;
		if (this.parametersValues!=null) {
			parameterValues = getParametersValues().get(key);
		}
		return parameterValues;
	}
	
	public String getParameter(String key) {
		String parameter=null;
		if (this.parameters!=null) {
			parameter = getParameters().get(key);
		}
		return parameter;
	}		

	@SuppressWarnings("unchecked")
	private HashMap loadAttributes(String cacheName) throws IOException, ClassNotFoundException  {
		String prefixAttribute = cacheName+SUFFIX_ATTRIBUTES;
		int beginIndex = prefixAttribute.length();
		// put the request attributes into a map
		HashMap attributes = new HashMap();
		if (session!=null) {
			Enumeration enumeration = session.getAttributeNames();
			while (enumeration.hasMoreElements()) {
				String key = (String)enumeration.nextElement();
				if (key.startsWith(prefixAttribute)) {
					String value = (String)session.getAttribute(key);
					attributes.put(key.substring(beginIndex), Convert.stringBase64ToObject(value));	
				}
			}			
		}
		return attributes;
	}

	@SuppressWarnings("unchecked")
	private HashMap<String, String[]> loadParametersValues(String cacheName) throws IOException, ClassNotFoundException {
		HashMap<String, String[]> parametersValues = null;
		String keyId = cacheName+SUFFIX_PARAMETERSVALUES;
		if (session!=null) {
			String parametersString = (String) session.getAttribute(keyId);
			if (parametersString!=null) {
				parametersValues = (HashMap<String, String[]>)Convert.stringBase64ToObject(parametersString);
			} else {
				parametersValues = new HashMap<String, String[]>();
			}			
		}
		return parametersValues==null?new HashMap<String, String[]>():parametersValues;
	}
	
	@SuppressWarnings("unchecked")
	private HashMap<String, String> loadParameters(String cacheName) throws IOException, ClassNotFoundException {
		HashMap<String, String> parameters = null;
		String keyId = cacheName+SUFFIX_PARAMETERS;		
		if (session!=null) {
			String parametersString = (String) session.getAttribute(keyId);			
			if (parametersString!=null) {
				parameters = (HashMap<String, String>)Convert.stringBase64ToObject(parametersString);
			} else {
				parameters = new HashMap<String, String>();
			}			
		}
		return parameters==null?new HashMap<String, String>():parameters;
	}	
	
	@SuppressWarnings("unchecked")
	private HashMap<String, String> loadUserParameters(String cacheName) throws IOException, ClassNotFoundException {
		HashMap<String, String> userParameters = null;
		String keyId = cacheName+SUFFIX_USERPARAMETERS;		
		if (session!=null) {
			String userParametersString = (String) session.getAttribute(keyId);
			if (userParametersString!=null) {
				userParameters = (HashMap<String, String>)Convert.stringBase64ToObject(userParametersString);
			} else {
				userParameters = new HashMap<String, String>();
			}			
		}
		return userParameters==null?new HashMap<String, String>():userParameters;
	}
	
	public void replyAttributes(String... ignoredStrings) throws IOException, ClassNotFoundException {
		replyAttributes(false, ignoredStrings);
	}	
	
	@SuppressWarnings("unchecked")	
	public void replyAttributes(boolean replace, String... ignoredStrings) throws IOException, ClassNotFoundException {
		String ignoredBuffer="";
		if (ignoredStrings!=null && ignoredStrings.length>0)
			ignoredBuffer=Convert.arrayToString(ignoredStrings);
		if (replace) {
			ArrayList<String> attributeNameList = new ArrayList<String>();
			Enumeration attrKeys = request.getAttributeNames();
			while (attrKeys.hasMoreElements()) {
				attributeNameList.add((String) attrKeys.nextElement());
			}
			for (String attributeName : attributeNameList) {
				request.removeAttribute(attributeName);
			}
//			while (attrKeys.hasMoreElements()) {
//				String attrKey = (String) attrKeys.nextElement();
//				request.removeAttribute(attrKey);
//			}
		}
		// put in the previous request attributes
		HashMap viewstateAttributes = getAttributes();
		Iterator<String> it = viewstateAttributes.keySet().iterator();
		// put the request attributes stored in the session in the request
		while (it.hasNext()) {
			String key = (String)it.next();
			if (ignoredBuffer.contains(key)) {
				continue;
			}
			Object value = viewstateAttributes.get(key);
			request.setAttribute(key,value);				
		}
	}		
	
	public void replyParameters(String... ignoredStrings)  {
		replyParameters(false, ignoredStrings);
	}
	
	public void replyParameters(boolean replace, String... ignoredStrings)  {
		if (defenseRequest==null) return;
		String ignoredBuffer="";
		if (ignoredStrings!=null && ignoredStrings.length>0)
			ignoredBuffer=Convert.arrayToString(ignoredStrings);
		if (replace) {
			String[] paramNames = defenseRequest.getParameterNamesArray();
			for (int i = 0,j=paramNames.length; i < j; i++) {
				defenseRequest.removeParameter(paramNames[i]);
			}
		}
		// put in the previous request attributes
		HashMap<String, String[]> viewstateParametersValues = getParametersValues();
		Iterator<String> it = viewstateParametersValues.keySet().iterator();
		// put the request attributes stored in the session in the request
		while (it.hasNext()) {
			String key = (String)it.next();
			if (ignoredBuffer.contains(key)) {
				continue;
			}			
			String[] value = (String[])viewstateParametersValues.get(key);
			defenseRequest.setParameters(key,value);
		}
	}	
	
	@SuppressWarnings("unchecked")
	public void replyUserParameters()  {
		// put in the previous request attributes
		HashMap viewstateUserParameters = getUserParameters();
		Iterator<String> it = viewstateUserParameters.keySet().iterator();
		// put the request attributes stored in the session in the request
		while (it.hasNext()) {
			String key = (String)it.next();
			String value = (String)viewstateUserParameters.get(key);
			request.setAttribute(key,value);
		
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, ViewStatePair> compareAttributes(String... ignoredKeys) {
		String ignoredBuffer="";
		if (ignoredKeys!=null && ignoredKeys.length>0)
			ignoredBuffer=Convert.arrayToString(ignoredKeys);
		
		Map<String, ViewStatePair> differents = new HashMap<String, ViewStatePair>();
		Enumeration attrKeys = request.getAttributeNames();
		while (attrKeys.hasMoreElements()) {
			Object attrObject = attrKeys.nextElement();
			if (attrObject instanceof java.lang.String) {
				String attrKey = (String) attrObject;
				if (ignoredBuffer.contains(attrKey)) continue;				
				Object vObject = getAttribute(attrKey);
				Object obj = request.getAttribute(attrKey);
				if (!vObject.equals(obj))
					differents.put(attrKey, new ViewStatePair(vObject, obj));
			}
		}
		return differents;
	}
	
	public class ViewStatePair {
		private Object oldValue;
		private Object newValue;
		public Object getOldValue() {
			return oldValue;
		}
		public Object getNewValue() {
			return newValue;
		}
		public ViewStatePair(Object oldValue, Object newValue) {
			this.oldValue = oldValue;
			this.newValue = newValue;
		}
	}
}
