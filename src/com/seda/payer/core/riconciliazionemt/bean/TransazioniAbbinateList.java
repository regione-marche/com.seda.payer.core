package com.seda.payer.core.riconciliazionemt.bean;

public class TransazioniAbbinateList {
	private static final long serialVersionUID = 1L;
	
	String transazioniAbbinateListXml;
	String transazioniAbbinateListXmlRiep;
	
	public TransazioniAbbinateList(){
		
	}
	public TransazioniAbbinateList(String transazioniAbbinateListXml){
		this.transazioniAbbinateListXml = transazioniAbbinateListXml;
		
	}
	public TransazioniAbbinateList(String[] transazioniAbbinateListXml){
		this.transazioniAbbinateListXml = transazioniAbbinateListXml[0];
		this.transazioniAbbinateListXmlRiep = transazioniAbbinateListXml[1];
	}
	
	public String getTransazioniAbbinateListXml() {
		return transazioniAbbinateListXml;
	}
	public String getTransazioniAbbinateListXmlRiep() {
		return transazioniAbbinateListXmlRiep;
	}
}
