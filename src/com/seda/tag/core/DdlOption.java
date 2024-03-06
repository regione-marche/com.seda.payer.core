package com.seda.tag.core;

import java.io.Serializable;

public class DdlOption implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    public String sText;
    public String sValue;
     
	public String getSText() {
		return sText;
	}
	public void setSText(String text) {
		sText = text;
	}
	public String getSValue() {
		return sValue;
	}
	public void setSValue(String value) {
		sValue = value;
	}
	public DdlOption() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DdlOption(String sValue, String sText) {
		super();
		this.sText = sText;
		this.sValue = sValue;
	}
	@Override
	public String toString() {
		return "DdlOption [sValue=" + sValue + ", sText=" + sText + "]";
	}
	
}
