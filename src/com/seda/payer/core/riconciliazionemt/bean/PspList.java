package com.seda.payer.core.riconciliazionemt.bean;

public class PspList {
	private static final long serialVersionUID = 1L;
	
	String pspListXml;
	String pspListXmlRiep;
	
	public PspList(){
		
	}
	public PspList(String pspListXml){
		this.pspListXml = pspListXml;
		
	}
	public PspList(String[] pspListXml){
		this.pspListXml = pspListXml[0];
		this.pspListXmlRiep = pspListXml[1];
	}
	
	public String getPspListXml() {
		return pspListXml;
	}
	public String getPspListXmlRiep() {
		return pspListXmlRiep;
	}
}
