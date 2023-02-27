package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class ConfigurazioneEnteISNotifica implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
	private String codiceImpostaServizio;
	private String flagNotificaAllegato;
	private String codiceOperatore;
	private Calendar dataAggiornamento; 
	
	public ConfigurazioneEnteISNotifica() {}

	public static Calendar getCalendarFromDate(java.util.Date date)
	{
		if (date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public ConfigurazioneEnteISNotifica(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;

    	try {
    		setCodiceSocieta(data.getString("ENT_CSOCCSOC"));
    	} catch (Exception e) {
			//La colonna ENT_CSOCCSOC viene caricata solo dalle PYENOSP_SEL e PYENOSP_SEL2
		}
    	setCodiceUtente(data.getString("ENO_CUTECUTE"));
    	setChiaveEnte(data.getString("ENO_KANEKENT"));
    	setCodiceImpostaServizio(data.getString("ENO_CISECENO"));
    	setFlagNotificaAllegato(data.getString("ENO_FENOFNOT"));
    	setCodiceOperatore(data.getString("ENO_CMDTCOPE"));
    	setDataAggiornamento(getCalendarFromDate(data.getDate("ENO_GMDTDAGG")));
    }
	
	public ConfigurazioneEnteISNotifica(String codiceUtente, String chiaveEnte, String codiceImpostaServizio, 
			String flagNotificaAllegato, String codiceOperatore, Calendar dataAggiornamento) {
		super();
		this.codiceUtente = codiceUtente;
		this.chiaveEnte = chiaveEnte;
		this.codiceImpostaServizio = codiceImpostaServizio;
		this.flagNotificaAllegato = flagNotificaAllegato;
		this.codiceOperatore = codiceOperatore;
		this.dataAggiornamento = dataAggiornamento;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}
	
	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public void setCodiceImpostaServizio(String codiceImpostaServizio) {
		this.codiceImpostaServizio = codiceImpostaServizio;
	}

	public String getCodiceImpostaServizio() {
		return codiceImpostaServizio;
	}

	public void setFlagNotificaAllegato(String flagNotificaAllegato) {
		this.flagNotificaAllegato = flagNotificaAllegato;
	}

	public String getFlagNotificaAllegato() {
		return flagNotificaAllegato;
	}

	public String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public Calendar getDataAggiornamento() {
		return dataAggiornamento;
	}

	public void setDataAggiornamento(Calendar dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
