package com.seda.payer.core.bean;

public class Mercato {
	
	private String codiceMercato;
	private String descrizioneMercato;
	
	public Mercato() {
	}
	
	public Mercato(String codiceMercato, String descrizioneMercato) {
		super();
		this.codiceMercato = codiceMercato;
		this.descrizioneMercato = descrizioneMercato;
	}

	public String getCodiceMercato() {
		return codiceMercato;
	}

	public void setCodiceMercato(String codiceMercato) {
		this.codiceMercato = codiceMercato;
	}

	public String getDescrizioneMercato() {
		return descrizioneMercato;
	}

	public void setDescrizioneMercato(String descrizioneMercato) {
		this.descrizioneMercato = descrizioneMercato;
	}

}
