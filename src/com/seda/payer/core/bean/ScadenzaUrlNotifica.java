package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class ScadenzaUrlNotifica implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String codiceSocieta;
	private String codiceUtente;
	private String codiceEnte;
	private String codiceFiscale;
	private String numeroDocumento;
	private String urlDownload;
	private String chiaveNotifica;
	private Calendar dataScadenza; 
	private Calendar dataAggiornamento;
	
	public ScadenzaUrlNotifica() {}

	public static Calendar getCalendarFromDate(java.util.Date date)
	{
		if (date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public ScadenzaUrlNotifica(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;

    	setCodiceSocieta(data.getString("UNP_CSOCCSOC"));
    	setCodiceUtente(data.getString("UNP_CUTECUTE"));
    	setCodiceEnte(data.getString("UNP_CANECENT"));
    	setCodiceFiscale(data.getString("UNP_CUNPCFIS"));
    	setNumeroDocumento(data.getString("UNP_KUNPNDOC"));
    	setUrlDownload(data.getString("UNP_DUNPURLD"));
    	setChiaveNotifica(data.getString("UNP_CUNPCNOT"));
    	setDataScadenza(getCalendarFromDate(data.getDate("UNP_GUNPSCAD")));
    	setDataAggiornamento(getCalendarFromDate(data.getDate("UNP_GUNPGAGG")));
    }
	
	public ScadenzaUrlNotifica(String codiceUtente, String codiceEnte, String codiceSocieta, 
			String codiceFiscale, String numeroDocumento, String urlDownload, String chiaveNotifica,  Calendar dataScadenza, Calendar dataAggiornamento) {
		super();
		this.codiceUtente = codiceUtente;
		this.codiceSocieta = codiceSocieta;
		this.codiceEnte = codiceEnte;
		this.codiceFiscale = codiceFiscale;
		this.numeroDocumento = numeroDocumento;
		this.urlDownload = urlDownload;
		this.chiaveNotifica = chiaveNotifica;
		this.dataScadenza = dataScadenza;
		this.dataAggiornamento = dataAggiornamento;
	}


	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public String getUrlDownload() {
		return urlDownload;
	}

	public String getChiaveNotifica() {
		return chiaveNotifica;
	}

	public Calendar getDataScadenza() {
		return dataScadenza;
	}

	public Calendar getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
	}

	public void setChiaveNotifica(String chiaveNotifica) {
		this.chiaveNotifica = chiaveNotifica;
	}

	public void setDataScadenza(Calendar dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public void setDataAggiornamento(Calendar dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
