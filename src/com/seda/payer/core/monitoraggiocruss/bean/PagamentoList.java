package com.seda.payer.core.monitoraggiocruss.bean;

public class PagamentoList {
	private static final long serialVersionUID = 1L;
	
	String pagamentoListXml;
	String pagamentoListXmlRiep;
	
	public PagamentoList(){
		
	}
	public PagamentoList(String pagamentoListXml){
		this.pagamentoListXml = pagamentoListXml;
		
	}
	public PagamentoList(String[] pagamentoListXml){
		this.pagamentoListXml = pagamentoListXml[0];
		this.pagamentoListXmlRiep = pagamentoListXml[1];
	}
	
	public String getPagamentoListXml() {
		return pagamentoListXml;
	}
	public String pagamentoListXmlRiep() {
		return pagamentoListXmlRiep;
	}
}
