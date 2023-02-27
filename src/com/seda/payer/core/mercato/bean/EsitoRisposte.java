package com.seda.payer.core.mercato.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class EsitoRisposte extends ModelAttributes implements Serializable{
	private String codiceMessaggio;
	private String descrizioneMessaggio;
	public String getCodiceMessaggio() {
		return codiceMessaggio;
	}
	public void setCodiceMessaggio(String codiceMessaggio) {
		this.codiceMessaggio = codiceMessaggio;
	}
	public String getDescrizioneMessaggio() {
		return descrizioneMessaggio;
	}
	public void setDescrizioneMessaggio(String descrizioneMessaggio) {
		this.descrizioneMessaggio = descrizioneMessaggio;
	}
	@Override
	public String toString() {
		return "EsitoRisposte [codiceMessaggio=" + codiceMessaggio
				+ ", descrizioneMessaggio=" + descrizioneMessaggio + "]";
	}
}
