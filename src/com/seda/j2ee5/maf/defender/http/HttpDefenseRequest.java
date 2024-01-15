/**
 * 
 */
package com.seda.j2ee5.maf.defender.http;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.seda.commons.string.Convert;

/**
 * @author Seda Lab
 *
 */
public class HttpDefenseRequest extends HttpServletRequestWrapper {

	private Map<String, String> parameters = new HashMap<String, String>();
	private Map<String, String[]> parametersValues = new HashMap<String, String[]>();
	HashMap<String, Object> encodedParameters = new HashMap<String, Object>();	

	public static final String _255 = "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";  
	public static final Pattern pattern = Pattern.compile("^(?:" + _255 + "\\.){3}" + _255 + "$");	

	private String remoteAddr;

	@SuppressWarnings("unchecked")
	public HttpDefenseRequest(HttpServletRequest request) {
		super(request);

		Enumeration e = request.getParameterNames();

		while(e.hasMoreElements()) {
			String name = (String)e.nextElement();
			String value = request.getParameter(name);
			String[] values = request.getParameterValues(name);

			parameters.put(name, value);
			parametersValues.put(name, values);
		}
		remoteAddr=getIpFromRequest(request);
	}

	@Override
	public String getRemoteAddr() {
		return remoteAddr;
	}
	@Override	
	public String getParameter(String name) {
		return parameters.get(name);
	}
	@Override
	public String[] getParameterValues(String name) {
		String[] values = null;
		if (parametersValues.containsKey(name))
			values = parametersValues.get(name);
		return values;
	}	

	public void setParameter(String name, String value) {
		parameters.put(name, value);
		parametersValues.put(name, new String[]{value});
	}

	public void setParameters(String name, String[] values) {
		parametersValues.put(name, values);		
		parameters.put(name, Convert.arrayToString(values));

	}	

	public void putAllParameters(HashMap<String, String> parameters) {
		if (parameters!=null && parameters.size()>0) {
			Enumeration<String> paramKeys = Collections.enumeration(parameters.keySet());
			while (paramKeys.hasMoreElements()) {
				String key = (String) paramKeys.nextElement();
				String param = parameters.get(key);
				setParameter(key, param);
			}
		}
	}

	public void removeParameter(String name) {
		parameters.remove(name);
		parametersValues.remove(name);
	}

	public void clearParameters() {
		parameters = new HashMap<String, String>();
		parametersValues = new HashMap<String, String[]>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map getParameterMap() {
		return parameters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration getParameterNames() {
		return Collections.enumeration(parameters.keySet());
	}
	public String[] getParameterNamesArray() {
		return parameters.keySet().toArray(new String[parameters.keySet().size()]);
	}

	public void setEncodedParameters(HashMap<String, Object> encodedParameters) {
		this.encodedParameters=encodedParameters;
	}	

	public HashMap<String, Object> getEncodedParameters() {
		return encodedParameters;
	}	

	public String longToIpV4(long longIp) {    
		int octet3 = (int) ((longIp >> 24) % 256);    
		int octet2 = (int) ((longIp >> 16) % 256);    
		int octet1 = (int) ((longIp >> 8) % 256);    
		int octet0 = (int) ((longIp) % 256);    
		return octet3 + "." + octet2 + "." + octet1 + "." + octet0;  
	}  

	public long ipV4ToLong(String ip) {    
		String[] octets = ip.split("\\.");    
		return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16) +        
		(Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);  
	}  
	public boolean isIPv4Private(String ip) {    
		long longIp = ipV4ToLong(ip);    
		return (longIp >= ipV4ToLong("10.0.0.0") && longIp <= ipV4ToLong("10.255.255.255")) ||        
		(longIp >= ipV4ToLong("172.16.0.0") && longIp <= ipV4ToLong("172.31.255.255")) ||        
		longIp >= ipV4ToLong("192.168.0.0") && longIp <= ipV4ToLong("192.168.255.255");  
	}  
	public boolean isIPv4Valid(String ip) {    
		return pattern.matcher(ip).matches();  
	}  

	public String getIpFromRequest(HttpServletRequest request) {    
		boolean found = false;
		HttpServletRequest hreq = (HttpServletRequest)request;
		//inizio debug
		String ip =null;
		Enumeration headerKeys = hreq.getHeaderNames();
		while (headerKeys.hasMoreElements()) {
			String key = (String) headerKeys.nextElement();
			if (key.startsWith("x-forward")) {
				ip=hreq.getHeader(key).toString();				
				break;
			}
		}
//		String ip = request.getHeader("x-forwarded-for");
//		if (ip==null) request.getHeader("x-forward");
		//inizio debug		
		if (ip != null) {
//			System.out.println("* debug ip from x-forwarded-for "+ip);
			StringTokenizer tokenizer = new StringTokenizer(ip, ",");
//			System.out.println("* debug tokenizer.toString() "+tokenizer);
			while (tokenizer.hasMoreTokens()) {        
				ip = tokenizer.nextToken().trim();
//				System.out.println("* debug next ip "+ip);				
				if (isIPv4Valid(ip) && !isIPv4Private(ip)) {          
					found = true;          
					break;        
				}      
			}    
		}    
		if (!found) {      
			ip = request.getRemoteAddr();    
		}    
		return ip;  
	}	

}
