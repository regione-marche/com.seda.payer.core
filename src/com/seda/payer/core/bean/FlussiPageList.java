package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class FlussiPageList extends PageInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4879740573640596079L;

	private String flussiListXml;
	
	public String getFlussiListXml() {
		return flussiListXml;
	}
	public void setFlussiListXml(String flussiListXml) {
		this.flussiListXml = flussiListXml;
	}
}
