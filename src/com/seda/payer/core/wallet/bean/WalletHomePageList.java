package com.seda.payer.core.wallet.bean;

import com.seda.data.spi.PageInfo;

public class WalletHomePageList extends PageInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String walletListXml;
	String importoCarico;
	String importoPagato;
	String importoDaPagare;
	String retCode;
	String message;
	
	
	
	
	
	public String getImportoCarico() {
		return importoCarico;
	}
	public void setImportoCarico(String importoCarico) {
		this.importoCarico = importoCarico;
	}
	public String getImportoPagato() {
		return importoPagato;
	}
	public void setImportoPagato(String importoPagato) {
		this.importoPagato = importoPagato;
	}
	public String getImportoDaPagare() {
		return importoDaPagare;
	}
	public void setImportoDaPagare(String importoDaPagare) {
		this.importoDaPagare = importoDaPagare;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public WalletHomePageList() {
		
	}
	public WalletHomePageList(PageInfo pageInfo, String retCode,String message, String walletListXml,String importoCarico,String importoPagato,String importoDaPagare) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.walletListXml = walletListXml;
		this.importoCarico=importoCarico;
		this.importoDaPagare=importoDaPagare;
		this.importoPagato=importoPagato;
	}
	public PageInfo getPageInfo() {
		if (pageInfo == null) {
			return null;
		}
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public String getWalletListXml() {
		return walletListXml;
	}
	public void setWalletListXml(String walletListXml) {
		this.walletListXml = walletListXml;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

