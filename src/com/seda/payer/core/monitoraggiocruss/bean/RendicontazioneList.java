package com.seda.payer.core.monitoraggiocruss.bean;

public class RendicontazioneList {
	private static final long serialVersionUID = 1L;
	
	String rendicontazioneListXml;
	String rendicontazioneListXmlRiep;
	
	public RendicontazioneList(){
		
	}
	public RendicontazioneList(String rendicontazioneListXml){
		this.rendicontazioneListXml = rendicontazioneListXml;
		
	}
	public RendicontazioneList(String[] rendicontazioneListXml){
		this.rendicontazioneListXml = rendicontazioneListXml[0];
		this.rendicontazioneListXmlRiep = rendicontazioneListXml[1];
	}
	
	public String getRendicontazioneListXml() {
		return rendicontazioneListXml;
	}
	public String rendicontazioneListXmlRiep() {
		return rendicontazioneListXmlRiep;
	}
}
