package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class FlussoDettagliPageList extends PageInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1269426455243316161L;

	private String flussoDettagliListXml;

	public String getFlussoDettagliListXml() {
		return flussoDettagliListXml;
	}

	public void setFlussoDettagliListXml(String flussoDettagliListXml) {
		this.flussoDettagliListXml = flussoDettagliListXml;
	}
	
}
