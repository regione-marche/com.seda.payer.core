package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class FattureRep extends ModelAttributes implements Serializable{

	/**
	 *    tipo record 50 tabella PYFTRTB
	 */
	private static final long serialVersionUID = 1L;

	private String tipoServizioSpecifico;//corrisponde a FTR_CSRVCODI
	private String idWallet;
	private String numeroProgressivoDisposizione;
	private String codiceAnagraficaFiglio;
	private String codiceFiscaleFiglio;
	private String numeroFattura;
	private Calendar periodoCompetenza;
	private String krepkrep;
	private String codiceServizio;
	
	 
	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public String getKrepkrep() {
		return krepkrep;
	}

	public void setKrepkrep(String krepkrep) {
		this.krepkrep = krepkrep;
	}

	public FattureRep(){
	}
	
public FattureRep(
		String idWallet,
		String numeroProgressivoDisposizione,
		String tipoServizioSpecifico,
		String codiceAnagraficaFiglio,
		String numeroFattura,
		Calendar periodoCompetenza
	){
		this.tipoServizioSpecifico = tipoServizioSpecifico;
		this.idWallet = idWallet;
		this.codiceAnagraficaFiglio = codiceAnagraficaFiglio;
		this.numeroFattura = numeroFattura;
		this.periodoCompetenza = periodoCompetenza;
		this.numeroProgressivoDisposizione = numeroProgressivoDisposizione;
		
	}

public String getTipoServizioSpecifico() {
	return tipoServizioSpecifico;
}

public void setTipoServizioSpecifico(String tipoServizioSpecifico) {
	this.tipoServizioSpecifico = tipoServizioSpecifico;
}

public String getIdWallet() {
	return idWallet;
}

public void setIdWallet(String idWallet) {
	this.idWallet = idWallet;
}

public String getCodiceAnagraficaFiglio() {
	return codiceAnagraficaFiglio;
}

public void setCodiceAnagraficaFiglio(String codiceAnagraficaFiglio) {
	this.codiceAnagraficaFiglio = codiceAnagraficaFiglio;
}

public String getNumeroFattura() {
	return numeroFattura;
}

public void setNumeroFattura(String numeroFattura) {
	this.numeroFattura = numeroFattura;
}

public Calendar getPeriodoCompetenza() {
	return periodoCompetenza;
}

public void setPeriodoCompetenza(java.util.Calendar periodoCompetenza) {
	this.periodoCompetenza = periodoCompetenza;
}

public String getNumeroProgressivoDisposizione() {
	return numeroProgressivoDisposizione;
}

public void setNumeroProgressivoDisposizione(
		String numeroProgressivoDisposizione) {
	this.numeroProgressivoDisposizione = numeroProgressivoDisposizione;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public String getCodiceFiscaleFiglio() {
	return codiceFiscaleFiglio;
}

public void setCodiceFiscaleFiglio(String codiceFiscaleFiglio) {
	this.codiceFiscaleFiglio = codiceFiscaleFiglio;
}

@Override
public String toString() {
	return "FattureRep [codiceAnagraficaFiglio=" + codiceAnagraficaFiglio
			+ ", codiceFiscaleFiglio=" + codiceFiscaleFiglio + ", idWallet="
			+ idWallet + ", numeroFattura=" + numeroFattura
			+ ", numeroProgressivoDisposizione="
			+ numeroProgressivoDisposizione + ", periodoCompetenza="
			+ periodoCompetenza + ", tipoServizioSpecifico="
			+ tipoServizioSpecifico + "]";
}



}
