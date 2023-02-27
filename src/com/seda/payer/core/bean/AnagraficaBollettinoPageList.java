package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class AnagraficaBollettinoPageList extends PageInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String anagraficabollettinoListXml;
	String retCode;
	String message;
	public AnagraficaBollettinoPageList() {
		
	}
	public AnagraficaBollettinoPageList(PageInfo pageInfo, String retCode,String message, String anaBollettinoListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.anagraficabollettinoListXml = anaBollettinoListXml;
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
	public String getAnagraficaBollettinoListXml() {
		return anagraficabollettinoListXml;
	}
	public void setAnagraficaBollettinoListXml(String anagraficabollettinoListXml) {
		this.anagraficabollettinoListXml = anagraficabollettinoListXml;
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
