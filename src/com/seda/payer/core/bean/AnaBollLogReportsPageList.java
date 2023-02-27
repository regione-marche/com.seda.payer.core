package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class AnaBollLogReportsPageList extends PageInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String anaBollLogReportsListXml;
	String retCode;
	String message;
	
	public AnaBollLogReportsPageList() {
		
	}
	public AnaBollLogReportsPageList(PageInfo pageInfo, String retCode,String message, String anaBollLogReportsListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.anaBollLogReportsListXml = anaBollLogReportsListXml;
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
	public String getAnaBollLogReportsListXml() {
		return anaBollLogReportsListXml;
	}
	public void setAnaBollLogReportsListXml(String anaBollLogReportsListXml) {
		this.anaBollLogReportsListXml = anaBollLogReportsListXml;
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
