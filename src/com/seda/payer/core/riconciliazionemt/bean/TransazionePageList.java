package com.seda.payer.core.riconciliazionemt.bean;

import com.seda.data.spi.PageInfo;

public class TransazionePageList {
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String transazioneListXml;
	String transazioneListXmlRiep;
	String retCode;
	String message;
	public TransazionePageList() {
		
	}
	public TransazionePageList(PageInfo pageInfo, String retCode,String message, String transazioneListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.transazioneListXml = transazioneListXml;
	}
	public TransazionePageList(PageInfo pageInfo, String retCode,String message, String[] transazioneListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.transazioneListXml = transazioneListXml[0];
		this.transazioneListXmlRiep = transazioneListXml[1];
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
	public String getTransazioneListXml() {
		return transazioneListXml;
	}
	public String getTransazioneListXmlRiep() {
		return transazioneListXmlRiep;
	}
	public void setTransazioneListXml(String transazioneListXml) {
		this.transazioneListXml = transazioneListXml;
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
