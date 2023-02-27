package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class ServizioTipoScuola  extends ModelAttributes implements Serializable {

	/**
	 * 
	 */
	// Tabella PYSSCTB
	private static final long serialVersionUID = 1L;
	private String codiceServizio;      //  SSC_CSRVCODI
	private String codiceScuola;		//  SSC_TSCUTIPO
	
	public ServizioTipoScuola(){
		
	}
	
	public ServizioTipoScuola(
			String codiceServizio,
			String codiceScuola){
		this.codiceServizio = codiceServizio;
		this.codiceScuola = codiceScuola;
	}

	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public String getCodiceScuola() {
		return codiceScuola;
	}

	public void setCodiceScuola(String codiceScuola) {
		this.codiceScuola = codiceScuola;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ServizioTipoScuola [codiceScuola=" + codiceScuola
				+ ", codiceServizio=" + codiceServizio + "]";
	}
	
	
}
