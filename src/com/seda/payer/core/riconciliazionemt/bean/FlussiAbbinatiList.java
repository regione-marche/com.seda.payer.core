package com.seda.payer.core.riconciliazionemt.bean;

public class FlussiAbbinatiList {
	private static final long serialVersionUID = 1L;
	
	String flussiAbbinatiListXml;
	String flussiAbbinatiListXmlRiep;
	
	public FlussiAbbinatiList(){
		
	}
	public FlussiAbbinatiList(String flussiAbbinatiListXml){
		this.flussiAbbinatiListXml = flussiAbbinatiListXml;
		
	}
	public FlussiAbbinatiList(String[] flussiAbbinatiListXml){
		this.flussiAbbinatiListXml = flussiAbbinatiListXml[0];
		this.flussiAbbinatiListXmlRiep = flussiAbbinatiListXml[1];
	}
	
	public String getFlussiAbbinatiListXml() {
		return flussiAbbinatiListXml;
	}
	public String getFlussiAbbinatiListXmlRiep() {
		return flussiAbbinatiListXmlRiep;
	}
}
