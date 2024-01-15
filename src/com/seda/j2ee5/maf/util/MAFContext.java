/**
 * 
 */
package com.seda.j2ee5.maf.util;

import com.seda.commons.security.TokenGenerator;

/**
 * @author Seda Lab
 *
 */
public class MAFContext {
	
	public static final String CONTEXT_maf_actions = "maf_actions";
	public static final String DEFAULT_maf_actions = "maf-actions.xml";
	
	public static final String CONTEXT_maf_menu = "maf_menu";
	public static final String DEFAULT_maf_menu = "maf-menu.xml"; 	
	
	public static final String CONTEXT_maf_templates = "maf_templates";
	public static final String DEFAULT_maf_templates = "maf-templates.xml";	
	
	public static final String CONTEXT_maf_security = "maf_security";
	public static final String DEFAULT_maf_security = "maf-security.xml";	
	
	public static final String CONTEXT_maf_exceptions = "maf_exceptions";
	public static final String DEFAULT_maf_exceptions = "maf-exceptions.xml";

	public static final String CONTEXT_MESSAGE="message";
	public static final String DEFAULT_MESSAGE="message";
	
	public final static String CONTEXT_ENCODE_DELETE = "encode_delete";
	public static final boolean DEFAULT_ENCODE_DELETE = false;
	
	public final static String CONTEXT_ENCODE_PARAMETER = "encode_parameter";
	public final static boolean DEFAULT_ENCODE_PARAMETER = true;
	
	public final static String CONTEXT_CHAR_ENCODING = "encode_parameter";
	public final static String DEFAULT_CHAR_ENCODING = "ASCII";
	
	public static final String CONTEXT_GUEST = "guest";
	public static final String DEFAULT_GUEST = "GUEST";
	
	public static final String CONTEXT_ANONYMOUS = "anonymous";
	public static final String DEFAULT_ANONYMOUS = "ANONYMOUS";	

	public static final String VALIDATION_CONTEXT="validation";
	public static final boolean VALIDATION_DEFAULT=false;
	
	public static final String VALIDATION_KEY_CONTEXT="validationKey";
	public static final String VALIDATION_KEY_DEFAULT="_validation";
	
	public static final String VALIDATION_FORM_CONTEXT="validationFormName";
	public static final String VALIDATION_FORM_DEFAULT="formName";
	
	public static final String VALIDATION_MESSAGE_CONTEXT = "validationMessage";
	public static final String VALIDATION_MESSAGE_DEFAULT = "message";
	
	public static final String VALIDATION_BASE64_CONTEXT="validationBase64";
	public static final boolean VALIDATION_BASE64_DEFAULT=false;
	
	public static final String VALIDATION_BACKUP_ATTRIBUTE="validationBackup";
	
	public static final String CSRFTOKEN_DEFENDER_CONTEXT = "csrfDefender";
	public static final boolean CSRFTOKEN_DEFENDER_DEFAULT = false;
	
	public static final String CSRFTOKEN_DENY_CONTEXT = "csrfDeny";
	public static final boolean CSRFTOKEN_DENY_DEFAULT = true;

	public static final String CSRFTOKEN_IGNORE_ROOT_CONTEXT = "csrfIgnoreRoot";
	public static final boolean CSRFTOKEN_IGNORE_ROOT_DEFAULT = false;
	
	public static final String CSRFTOKEN_IGNORE_ROOT_REGENERATE_CONTEXT = "csrfIgnoreRootRegenerate";
	public static final boolean CSRFTOKEN_IGNORE_ROOT_REGENERATE_DEFAULT = false;	
	
	public static final String CSRFTOKEN_NAME_CONTEXT = "csrfTokenName";
	public static final String CSRFTOKEN_NAME_DEFAULT = "csrfToken";
	
	public static final String CSRFTOKEN_LEN_CONTEXT = "csrfTokenLength";
	public static final int CSRFTOKEN_LEN_DEFAULT = 32;

	public static final String CSRFTOKEN_PROVIDER_CONTEXT = "csfrTokenProvider";
	public static final String CSRFTOKEN_PROVIDER_DEFAULT = TokenGenerator.PROVIDER_INTERNAL;
	
	public static final String SESSIONFIXATION_DESTROY_CONTEXT = "sfixDestroy";
	public static final boolean SESSIONFIXATION_DESTROY_DEFAULT = true;


}


