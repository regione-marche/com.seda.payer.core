/**
 * 
 */
package com.seda.j2ee5.maf.core.security;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Seda Lab
 *
 */
public class ProtectedResource implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name = null;
    private String urlPattern = null;
    private ArrayList<String> roles = null;

    public ProtectedResource (String name, String urlPattern, ArrayList<String> roles) {
        this.name = name;
        this.urlPattern = urlPattern;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public String getURLPattern() {
        return urlPattern;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public String toString() {
        return "ProtectedResource [ name=" + name + ", urlPattern=" + urlPattern + ", roles=" + roles + "]";
    }	
	
	
}
