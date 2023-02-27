package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


public class AgendaScadenzePossibili implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numDocumento;
	private Calendar dataScadenza;
	private String enteChiave;
	private String enteDescrizione;
	private BigDecimal importoBollettino;
	private String numeroBollettino;
	private String tipologiaServizio;
	private String numeroRata;
	
	 
    public AgendaScadenzePossibili() { 
    }

    public AgendaScadenzePossibili(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	this.setNumDocumento((data.getString("NUM_DOC")));
    	// ritorna la stringa ISO dd/MM/YYYY
    	this.setDataScadenza((AgendaEvento.getCalendarFromDate(data.getDate("DATA_SCADENZA"))));	
    	this.setEnteChiave((data.getString("CHIAVE_ENTE")));
    	this.setEnteDescrizione((data.getString("DESC_ENTE")));
    	this.setImportoBollettino((data.getBigDecimal("IMPORTO_BOLL")));
    	this.setNumeroBollettino((data.getString("NUM_BOLL")));
    	this.setTipologiaServizio((data.getString("TIPOLOGIA_SERV")));
    	this.setNumeroRata((data.getString("NUM_RATA")));    	
    	
    }	
	
	/**
	 * Converte il NULL in stringa vuota
	 * @param sTemp
	 * @return
	 */
	private String ConvertNull(String sTemp)
	{
		return sTemp != null ? sTemp : "";
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setDataScadenza(Calendar dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public Calendar getDataScadenza() {
		return dataScadenza;
	}

	public void setEnteChiave(String enteChiave) {
		this.enteChiave = enteChiave;
	}

	public String getEnteChiave() {
		return enteChiave;
	}

	public void setEnteDescrizione(String enteDescrizione) {
		this.enteDescrizione = enteDescrizione;
	}

	public String getEnteDescrizione() {
		return enteDescrizione;
	}

	public void setImportoBollettino(BigDecimal importoBollettino) {
		this.importoBollettino = importoBollettino;
	}

	public BigDecimal getImportoBollettino() {
		return importoBollettino;
	}

	public void setNumeroBollettino(String numeroBollettino) {
		this.numeroBollettino = numeroBollettino;
	}

	public String getNumeroBollettino() {
		return numeroBollettino;
	}

	public void setNumeroRata(String numeroRata) {
		this.numeroRata = numeroRata;
	}

	public String getNumeroRata() {
		return numeroRata;
	}

	public void setTipologiaServizio(String tipoServizio) {
		this.tipologiaServizio = tipoServizio;
	}

	public String getTipologiaServizio() {
		return tipologiaServizio;
	}


}