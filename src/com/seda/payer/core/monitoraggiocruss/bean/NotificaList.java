package com.seda.payer.core.monitoraggiocruss.bean;

public class NotificaList {
	private static final long serialVersionUID = 1L;
	
	String notificaListXml;
	String notificaListXmlRiep;
	
	public NotificaList(){
		
	}
	public NotificaList(String notificaListXml){
		this.notificaListXml = notificaListXml;
		
	}
	public NotificaList(String[] notificaListXml){
		this.notificaListXml = notificaListXml[0];
		this.notificaListXmlRiep = notificaListXml[1];
	}
	
	public String getNotificaListXml() {
		return notificaListXml;
	}
	public String notificaListXmlRiep() {
		return notificaListXmlRiep;
	}
}
