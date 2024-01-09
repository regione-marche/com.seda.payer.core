/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

/**
 * @author Seda Lab
 *
 */
public class SignOnKeys {
	
	// these static strings define where to put/get things
    public static final String FORM_SIGNON_URL = "j_signon_do";
    public static final String FORM_SSO_URL = "j_sso_do";
    public static final String FORM_USER_NAME = "j_username";
    public static final String FORM_PASSWORD = "j_password";
    public static final String REMEMBER_USERNAME = "j_remember_username";
    public static final String USER_NAME = "j_signon_username";
    public static final String SIGNED_ON_USER  = "j_signon";
    public static final String ORIGINAL_URL = "j_signon_original_url";
    public static final String CREATE_USER_URL = "j_create_user";
    public static final String USER_BEAN = "j_user_bean";
    
    public static final String SESSION_ID = "j_session";
    
    public static final String COOKIE_NAME = "maf_signon";	
	
    public static final String MAIN_MENU = "j_menu";
	public static final String MAIN_MENU_TEXT = "j_menu_text";    
    public static final String APPLICATION_MENU = "j_application_menu";

    // to prevent instantiation
    private SignOnKeys (){}
}
