package com.seda.payer.core.mercato.bean;

import com.seda.data.spi.PageInfo;

public class MercatoPageList extends PageInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String mercatoListXml;
	String mercatoListXmlRiep;
	String retCode;
	String message;
	public MercatoPageList() {
		
	}
	public MercatoPageList(PageInfo pageInfo, String retCode,String message, String mercatoListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.mercatoListXml = mercatoListXml;
	}
	public MercatoPageList(PageInfo pageInfo, String retCode,String message, String[] mercatoListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.mercatoListXml = mercatoListXml[0];
		this.mercatoListXmlRiep = mercatoListXml[1];
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
	public String getMercatoListXml() {
		return mercatoListXml;
	}
	public String getMercatoListXmlRiep() {
		return mercatoListXmlRiep;
	}
	public void setMercatoListXml(String mercatoListXml) {
		this.mercatoListXml = mercatoListXml;
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
