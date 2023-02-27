package com.seda.payer.core.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InvalidateLog implements Serializable{

	private String retCode = null;
	private String retMessage = null;
	
	public InvalidateLog() { }

	public String getRetCode() {
		return retCode;
	}

	public String getRetMessage() {
		return retMessage;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}
}
