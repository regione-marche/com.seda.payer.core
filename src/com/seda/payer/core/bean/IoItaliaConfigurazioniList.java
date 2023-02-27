package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class IoItaliaConfigurazioniList extends PageInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4246868749032868984L;

	private String configurazioniListXml;

	public String getConfigurazioniListXml() {
		return configurazioniListXml;
	}

	public void setConfigurazioniListXml(String configurazioniListXml) {
		this.configurazioniListXml = configurazioniListXml;
	}
	
}
