package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class Servizio extends ModelAttributes implements Serializable {

	/**
	 * 
	 */
	// Tabella PYSRVTB
	private static final long serialVersionUID = 1L;
	private String codiceServizio;           // SRV_CSRVCODI
	private String descrizioneServizio;		 // SRV_DSRVDESC
	
	public Servizio(){
	}
	
	public Servizio(
		String codiceServizio,
		String descrizioneServizio
		){
		this.codiceServizio = codiceServizio;
		this.descrizioneServizio = descrizioneServizio;
		
	}

	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}

	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Servizio [codiceServizio=" + codiceServizio
				+ ", descrizioneServizio=" + descrizioneServizio + "]";
	}
	
}
