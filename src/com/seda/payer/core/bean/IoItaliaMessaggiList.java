package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class IoItaliaMessaggiList extends PageInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2654492224746612889L;

	private String messaggiListXml;

	public String getMessaggiListXml() {
		return messaggiListXml;
	}

	public void setMessaggiListXml(String messaggiListXml) {
		this.messaggiListXml = messaggiListXml;
	}
	
}
