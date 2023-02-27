package com.seda.payer.core.riconciliazionemt.bean;

import com.seda.data.spi.PageInfo;

public class MovimentoDiCassaPageList {
	private static final long serialVersionUID = 1L;
	PageInfo pageInfo = new PageInfo();
	String movimentoDiCassaListXml;
	String movimentoDiCassaListXmlRiep;
	String retCode;
	String message;
	public MovimentoDiCassaPageList() {
		
	}
	public MovimentoDiCassaPageList(PageInfo pageInfo, String retCode,String message, String movimentoDiCassaListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.movimentoDiCassaListXml = movimentoDiCassaListXml;
	}
	public MovimentoDiCassaPageList(PageInfo pageInfo, String retCode,String message, String[] movimentoDiCassaListXml) {
		this.pageInfo = pageInfo;
		this.retCode = retCode;
		this.message = message;
		this.movimentoDiCassaListXml = movimentoDiCassaListXml[0];
		this.movimentoDiCassaListXmlRiep = movimentoDiCassaListXml[1];
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
	public String getMovimentoDiCassaListXml() {
		return movimentoDiCassaListXml;
	}
	public String getMovimentoDiCassaListXmlRiep() {
		return movimentoDiCassaListXmlRiep;
	}
	public void setMovimentoDiCassaListXml(String movimentoDiCassaListXml) {
		this.movimentoDiCassaListXml = movimentoDiCassaListXml;
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
