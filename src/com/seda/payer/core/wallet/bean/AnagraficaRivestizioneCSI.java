package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class AnagraficaRivestizioneCSI extends ModelAttributes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codiceFiscale;
	private String codiceAttivazione;
	private String denominazione;
	
	public AnagraficaRivestizioneCSI(){
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceAttivazione() {
		return codiceAttivazione;
	}

	public void setCodiceAttivazione(String codcieAttivazione) {
		this.codiceAttivazione = codcieAttivazione;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	@Override
	public String toString() {
		return "AnagraficaRivestizioneCSI [codiceAttivazione="
				+ codiceAttivazione + ", codiceFiscale=" + codiceFiscale
				+ ", denominazione=" + denominazione + "]";
	}	
}
