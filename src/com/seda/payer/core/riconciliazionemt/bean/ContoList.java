package com.seda.payer.core.riconciliazionemt.bean;

public class ContoList {

	private static final long serialVersionUID = 1L;
	
	String contoListXml;
	String contoListXmlRiep;
	
	public ContoList(){
		
	}
	public ContoList(String contoListXml){
		this.contoListXml = contoListXml;
		
	}
	public ContoList(String[] contoListXml){
		this.contoListXml = contoListXml[0];
		this.contoListXmlRiep = contoListXml[1];
	}
	
	public String getContoListXml() {
		return contoListXml;
	}
	public String getContoListXmlRiep() {
		return contoListXmlRiep;
	}
}