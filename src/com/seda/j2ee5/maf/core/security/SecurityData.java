/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import java.io.Serializable;

/**
 * @author Seda Lab
 *
 */
public class SecurityData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean enabled=false;
    private String loginPage= null;
    private String securityErrorPage=null;
    private String signOnClass=null;
    private String ssoClass=null;
    private String afterLogin=null;
    private SignOnRules signOnRules=null;
    
	public boolean isEnabled() {
		return enabled;
	}
	public boolean isDisabled() {
		return !enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getLoginPage() {
		return loginPage;
	}
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	public String getSecurityErrorPage() {
		return securityErrorPage;
	}
	public void setSecurityErrorPage(String securityErrorPage) {
		this.securityErrorPage = securityErrorPage;
	}
	public String getSignOnClass() {
		return signOnClass;
	}
	public void setSignOnClass(String signOnClass) {
		this.signOnClass = signOnClass;
	}
	public String getSsoClass() {
		return ssoClass;
	}
	public void setSsoClass(String ssoClass) {
		this.ssoClass = ssoClass;
	}

	public String getAfterLogin() {
		return afterLogin;
	}
	public void setAfterLogin(String afterLogin) {
		this.afterLogin = afterLogin;
	}
	
	public SignOnRules getSignOnRules() {
		return signOnRules;
	}
	public void setSignOnRules(SignOnRules signOnRules) {
		this.signOnRules = signOnRules;
	}
	@Override
	public String toString() {
		return "SecurityData [afterLogin=" + afterLogin + ", enabled="
				+ enabled + ", loginPage=" + loginPage + ", securityErrorPage="
				+ securityErrorPage + ", signOnClass=" + signOnClass
				+ ", ssoClass=" + ssoClass + ", signOnRules=" + signOnRules 
				+ "]";
	}
	
}
