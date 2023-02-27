package com.seda.payer.core.riconciliazionemt.bean;

public class MittenteList {
	private static final long serialVersionUID = 1L;
	
	String mittenteListXml;
	String mittenteListXmlRiep;
	
	public MittenteList(){
		
	}
	public MittenteList(String mittenteListXml){
		this.mittenteListXml = mittenteListXml;
		
	}
	public MittenteList(String[] mittenteListXml){
		this.mittenteListXml = mittenteListXml[0];
		this.mittenteListXmlRiep = mittenteListXml[1];
	}
	
	public String getMittenteListXml() {
		return mittenteListXml;
	}
	public String getMittenteListXmlRiep() {
		return mittenteListXmlRiep;
	}
}
