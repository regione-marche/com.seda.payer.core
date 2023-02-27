package com.seda.payer.core.mercato.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class GestioneTariffeResponse extends ModelAttributes implements Serializable{
	private boolean esito;
	private String messaggio;
	public boolean isEsito() {
		return esito;
	}
	public void setEsito(boolean esito) {
		this.esito = esito;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	@Override
	public String toString() {
		return "GestioneTariffeResponse [esito=" + esito
				+ ", messaggio=" + messaggio + "]";
	}	

}
