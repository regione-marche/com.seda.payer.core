package com.seda.payer.core.riconciliazionemt.bean;

import com.seda.data.spi.PageInfo;

public class GiornaleDiCassaPageList extends PageInfo {
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String giornaleDiCassaListXml;
	String giornaleDiCassaListXmlRiep;
	String retCode;
	String message;
	public GiornaleDiCassaPageList() {
		
	}
	public GiornaleDiCassaPageList(PageInfo pageInfo, String retCode,String message, String giornaleDiCassaListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.giornaleDiCassaListXml = giornaleDiCassaListXml;
	}
	public GiornaleDiCassaPageList(PageInfo pageInfo, String retCode,String message, String[] giornaleDiCassaListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.giornaleDiCassaListXml = giornaleDiCassaListXml[0];
		this.giornaleDiCassaListXmlRiep = giornaleDiCassaListXml[1];
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
	public String getGiornaleDiCassaListXml() {
		return giornaleDiCassaListXml;
	}
	public String getGiornaleDiCassaListXmlRiep() {
		return giornaleDiCassaListXmlRiep;
	}
	public void setGiornaleDiCassaListXmlXml(String giornaleDiCassaListXml) {
		this.giornaleDiCassaListXml = giornaleDiCassaListXml;
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
