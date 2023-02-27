package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class EcNotifichePageList extends PageInfo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String ecnotificheListXml;
	String retCode;
	String message;
	public EcNotifichePageList() {
		
	}
	public EcNotifichePageList(PageInfo pageInfo, String retCode,String message, String ecnotificheListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.ecnotificheListXml = ecnotificheListXml;
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
	public String getEcnotificheListXml() {
		return ecnotificheListXml;
	}
	public void setEcnotificheListXml(String ecnotificheListXml) {
		this.ecnotificheListXml = ecnotificheListXml;
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
