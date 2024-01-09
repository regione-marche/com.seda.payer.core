package com.seda.payer.commons.bean;

import java.io.Serializable;

public class TypeRequest implements Serializable {

	public static final TypeRequest ADD_SCOPE = new TypeRequest("1");
	public static final TypeRequest EDIT_SCOPE = new TypeRequest("2");
	public static final TypeRequest DEL_SCOPE = new TypeRequest("3");
	public static final TypeRequest VIEW_SCOPE = new TypeRequest("4");
	
	private static final long serialVersionUID = 1L;
	private String scope;

	public TypeRequest() {
	}
	
	public TypeRequest(String scope) {
		this.scope = scope;
	}

	public String scope() {
		return scope;
	} 

	public String getAddScope() {
		return ADD_SCOPE.scope();
	}

	public String getEditScope() {
		return EDIT_SCOPE.scope();
	}

	public String getDelScope() {
		return DEL_SCOPE.scope();
	}

	public String getViewScope() {
		return VIEW_SCOPE.scope();
	}
}
