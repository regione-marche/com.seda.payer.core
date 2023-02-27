package com.seda.payer.core.bean;

public enum StatoCarrelloVirtuale {
	
	OPEN("O"),
	CLOSED("C"),
	PENDING("P");

	private String code;

	private StatoCarrelloVirtuale(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
