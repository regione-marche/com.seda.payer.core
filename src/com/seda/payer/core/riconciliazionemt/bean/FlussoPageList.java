package com.seda.payer.core.riconciliazionemt.bean;

import com.seda.data.spi.PageInfo;

public class FlussoPageList {
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String flussoListXml;
	String flussoListXmlRiep;
	String retCode;
	String message;
	public FlussoPageList() {
		
	}
	public FlussoPageList(PageInfo pageInfo, String retCode,String message, String flussoListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.flussoListXml = flussoListXml;
	}
	public FlussoPageList(PageInfo pageInfo, String retCode,String message, String[] flussoListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.flussoListXml = flussoListXml[0];
		this.flussoListXmlRiep = flussoListXml[1];
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
	public String getFlussoListXml() {
		return flussoListXml;
	}
	public String getFlussoListXmlRiep() {
		return flussoListXmlRiep;
	}
	public void setFlussoListXml(String flussoListXml) {
		this.flussoListXml = flussoListXml;
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
