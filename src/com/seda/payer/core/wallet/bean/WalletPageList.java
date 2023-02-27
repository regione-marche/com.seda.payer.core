package com.seda.payer.core.wallet.bean;

import com.seda.data.spi.PageInfo;

public class WalletPageList extends PageInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String walletListXml;
	String walletListXmlRiep;
	String retCode;
	String message;
	public WalletPageList() {
		
	}
	public WalletPageList(PageInfo pageInfo, String retCode,String message, String walletListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.walletListXml = walletListXml;
	}
	public WalletPageList(PageInfo pageInfo, String retCode,String message, String[] walletListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.walletListXml = walletListXml[0];
		this.walletListXmlRiep = walletListXml[1];
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
	public String getWalletListXmlRiep() {
		return walletListXmlRiep;
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
