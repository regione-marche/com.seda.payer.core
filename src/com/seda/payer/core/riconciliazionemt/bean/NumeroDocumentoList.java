package com.seda.payer.core.riconciliazionemt.bean;

public class NumeroDocumentoList {
	private static final long serialVersionUID = 1L;
	
	String numeroDocumentoListXml;
	String numeroDocumentoListXmlRiep;
	
	public NumeroDocumentoList(){
		
	}
	public NumeroDocumentoList(String numeroDocumentoListXml){
		this.numeroDocumentoListXml = numeroDocumentoListXml;
		
	}
	public NumeroDocumentoList(String[] numeroDocumentoListXml){
		this.numeroDocumentoListXml = numeroDocumentoListXml[0];
		this.numeroDocumentoListXmlRiep = numeroDocumentoListXml[1];
	}
	
	public String getNumeroDocumentoListXml() {
		return numeroDocumentoListXml;
	}
	public String getNumeroDocumentoListXmlRiep() {
		return numeroDocumentoListXmlRiep;
	}
}
