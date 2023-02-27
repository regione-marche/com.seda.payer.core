package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PgBollIVDocumento implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String codiceUtente; 
	private String codiceEnte;
	private String impostaServizio;
	private String numeroDocumento;
	private String numeroRata;
	private String dataScadenzaRata; //formato AAAA-MM-GG
	private BigDecimal importoRav;
	private String flagOneri;
	private String dataRicalcoloInteressi; //formato AAAA-MM-GG
	private String flagMora;
	private BigDecimal importoMoraAttualizzato;
	private String flagSpese;
	private BigDecimal importoSpeseAttualizzato;
	private String dataRicalcolo; //formato AAAA-MM-GG
	private String flagVariazioneImporto;
	private String flagDocumentoAccertamenti;
	private String flagSanzioneRidotta;
	
	public PgBollIVDocumento(ResultSet data) throws SQLException {
		if (data == null)
			return;
		
		setCodiceUtente(data.getString(1));
		setCodiceEnte(data.getString(2));
		setImpostaServizio(data.getString(3));
		setNumeroDocumento(data.getString(4));
		setNumeroRata(data.getString(5));
		setDataScadenzaRata(data.getString(6)); //formato AAAA-MM-GG
		setImportoRav(data.getBigDecimal(7));
		setFlagOneri(data.getString(8));
		setDataRicalcoloInteressi(data.getString(9)); //formato AAAA-MM-GG
		setFlagMora(data.getString(10));
		setImportoMoraAttualizzato(data.getBigDecimal(11));
		setFlagSpese(data.getString(12));
		setImportoSpeseAttualizzato(data.getBigDecimal(13));
		setDataRicalcolo(data.getString(14)); //formato AAAA-MM-GG
		setFlagVariazioneImporto(data.getString(15));
		setFlagDocumentoAccertamenti(data.getString(16));
		setFlagSanzioneRidotta(data.getString(17));
	}
	
	public String getCodiceUtente() {
		return codiceUtente;
	}
	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}
	public String getCodiceEnte() {
		return codiceEnte;
	}
	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}
	public String getImpostaServizio() {
		return impostaServizio;
	}
	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNumeroRata() {
		return numeroRata;
	}
	public void setNumeroRata(String numeroRata) {
		this.numeroRata = numeroRata;
	}
	public String getDataScadenzaRata() {
		return dataScadenzaRata;
	}
	public void setDataScadenzaRata(String dataScadenzaRata) {
		this.dataScadenzaRata = dataScadenzaRata;
	}
	public BigDecimal getImportoRav() {
		return importoRav;
	}
	public void setImportoRav(BigDecimal importoRav) {
		this.importoRav = importoRav;
	}
	public String getFlagOneri() {
		return flagOneri;
	}
	public void setFlagOneri(String flagOneri) {
		this.flagOneri = flagOneri;
	}
	public String getDataRicalcoloInteressi() {
		return dataRicalcoloInteressi;
	}
	public void setDataRicalcoloInteressi(String dataRicalcoloInteressi) {
		this.dataRicalcoloInteressi = dataRicalcoloInteressi;
	}
	public String getFlagMora() {
		return flagMora;
	}
	public void setFlagMora(String flagMora) {
		this.flagMora = flagMora;
	}
	public BigDecimal getImportoMoraAttualizzato() {
		return importoMoraAttualizzato;
	}
	public void setImportoMoraAttualizzato(BigDecimal importoMoraAttualizzato) {
		this.importoMoraAttualizzato = importoMoraAttualizzato;
	}
	public String getFlagSpese() {
		return flagSpese;
	}
	public void setFlagSpese(String flagSpese) {
		this.flagSpese = flagSpese;
	}
	public BigDecimal getImportoSpeseAttualizzato() {
		return importoSpeseAttualizzato;
	}
	public void setImportoSpeseAttualizzato(BigDecimal importoSpeseAttualizzato) {
		this.importoSpeseAttualizzato = importoSpeseAttualizzato;
	}
	public String getDataRicalcolo() {
		return dataRicalcolo;
	}
	public void setDataRicalcolo(String dataRicalcolo) {
		this.dataRicalcolo = dataRicalcolo;
	}
	public String getFlagVariazioneImporto() {
		return flagVariazioneImporto;
	}
	public void setFlagVariazioneImporto(String flagVariazioneImporto) {
		this.flagVariazioneImporto = flagVariazioneImporto;
	}
	public String getFlagDocumentoAccertamenti() {
		return flagDocumentoAccertamenti;
	}
	public void setFlagDocumentoAccertamenti(String flagDocumentoAccertamenti) {
		this.flagDocumentoAccertamenti = flagDocumentoAccertamenti;
	}
	public String getFlagSanzioneRidotta() {
		return flagSanzioneRidotta;
	}
	public void setFlagSanzioneRidotta(String flagSanzioneRidotta) {
		this.flagSanzioneRidotta = flagSanzioneRidotta;
	}
	

}
