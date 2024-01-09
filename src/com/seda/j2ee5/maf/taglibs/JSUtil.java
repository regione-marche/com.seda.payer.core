/**
 * 
 */
package com.seda.j2ee5.maf.taglibs;

/**
 * @author Seda Lab
 *
 */
public class JSUtil {

	public enum Event {
		onchange,
		onclick;
	}
	
	public static String getSubmitFunctionScript(String functionName, String action, String formName) {
		// define javascript function to change at on click event the wrapper form action
		StringBuffer javaScript = new StringBuffer();
        javaScript.append("<script language=\"JavaScript\">\n");
        javaScript.append("function " + functionName + "() {\n");
        javaScript.append("    document."+formName+".action=\"" + action + "\";\n");
        javaScript.append("    " + getSubmitFormCode(formName) + "\n");                
        javaScript.append("    return true;\n");
        javaScript.append("}\n");
        javaScript.append("</script>\n"); 
		return javaScript.toString();		
	}
	
	public static String getSubmitValidationFunctionScript(String functionName, String action, String formName) {
		// define javascript function to change at on click event the wrapper form action
		StringBuffer javaScript = new StringBuffer();
        javaScript.append("<script language=\"JavaScript\">\n");
        javaScript.append("function " + functionName + "() {\n");
        javaScript.append("    var validation = get_"+formName+"_validation()||\"\";\n");
        javaScript.append("    var action = \""+action+"\";\n");
        javaScript.append("    if (validation.length>0) {\n");        
        javaScript.append("       if (action.indexOf(\"?\")==-1) {\n");
        javaScript.append("          action+=\"?\"+validation;\n");            		
        javaScript.append("       } else {\n");
        javaScript.append("          action+=\"&\"+validation;\n");
        javaScript.append("       }\n");    	
        javaScript.append("    }\n");        
        javaScript.append("    document."+formName+".action=action;\n");
        javaScript.append("    " + getSubmitFormCode(formName) + "\n");                
        javaScript.append("    return true;\n");
        javaScript.append("}\n");
        javaScript.append("</script>\n"); 
		return javaScript.toString();		
	}	
	
	public static String getFunctionGetScript(String functionName, String constant) {
		// define javascript function to change at on click event the wrapper form action
		StringBuffer javaScript = new StringBuffer();
        javaScript.append("<script language=\"JavaScript\">\n");
        javaScript.append("function " + functionName + "() {\n");
        javaScript.append("    return \"" + constant + "\";\n");
        javaScript.append("}\n");
        javaScript.append("</script>\n"); 
		return javaScript.toString();		
	}
	
	public static String getSubmitFormCode(String formName) {
		return formName==null?"this.form.submit();":"document."+formName+".submit();";		
	}

	public static String getFunctionName(String event, String formTagName, String tagName) {
		StringBuffer functionName = new StringBuffer();
		functionName.append(event==null?"function":event);
		functionName.append(formTagName==null?"":"_"+formTagName.replace(" ", "_"));
		functionName.append(tagName==null?"":"_"+tagName.replace(" ", "_"));		
		return functionName.toString();
	}

	
}
