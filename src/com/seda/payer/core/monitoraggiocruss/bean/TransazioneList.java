package com.seda.payer.core.monitoraggiocruss.bean;

public class TransazioneList {
	private static final long serialVersionUID = 1L;
	
	String transazioneListXml;
	String transazioneListXmlRiep;
	
	public TransazioneList(){
		
	}
	public TransazioneList(String transazioneListXml){
		this.transazioneListXml = transazioneListXml;
		
	}
	public TransazioneList(String[] transazioneListXml){
		this.transazioneListXml = transazioneListXml[0];
		this.transazioneListXmlRiep = transazioneListXml[1];
	}
	
	public String getTransazioneListXml() {
		return transazioneListXml;
	}
	public String transazioneListXmlRiep() {
		return transazioneListXmlRiep;
	}
}
