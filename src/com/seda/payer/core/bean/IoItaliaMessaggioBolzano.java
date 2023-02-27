package com.seda.payer.core.bean;

public class IoItaliaMessaggioBolzano extends IoItaliaMessaggio {

	private String wsKey1;
	private String wsKey2;
	
	public IoItaliaMessaggioBolzano() {
		super();
	}

	public String getWsKey1() {
		return wsKey1;
	}

	public void setWsKey1(String wsKey1) {
		this.wsKey1 = wsKey1;
	}

	public String getWsKey2() {
		return wsKey2;
	}

	public void setWsKey2(String wsKey2) {
		this.wsKey2 = wsKey2;
	}
	
}
