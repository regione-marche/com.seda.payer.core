package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigurazioneModello3 implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	//PG180080 TODO Da definire tutte le propriet' in base ai campi che sono stati definiti per la tabella su DB
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
	private String codiceIdentificativoDominio;
	private String auxDigit;
	private String codiceSegregazione;
	private String tipologiaServizio;
	private String urlIntegrazione;
	private String codiceOperatore;
	private Calendar dataAggiornamento; 
	private String carattereDiServizio; //SVILUPPO_002_SB
	private String descrEnte; //PG22XX09_YL5
	
public ConfigurazioneModello3() {}

	public static Calendar getCalendarFromDate(java.util.Date date)
	{
		if (date == null) return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public ConfigurazioneModello3(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setCodiceSocieta(data.getString("MDT_CSOCCSOC"));
    	setCodiceUtente(data.getString("MDT_CUTECUTE"));
    	setChiaveEnte(data.getString("MDT_KANEKENT"));
    	setCodiceIdentificativoDominio(data.getString("MDT_CMDTIDDO"));
    	setAuxDigit(data.getString("MDT_CMDTAUXD"));
    	setCodiceSegregazione(data.getString("MDT_CMDTCDSE"));
    	setCarattereDiServizio(data.getString("MDT_CMDTCSER")); //SVILUPPO_002_SB
    	setTipologiaServizio(data.getString("MDT_GMDTTPSE"));
    	setUrlIntegrazione(data.getString("MDT_GMDTUINT"));
    	setCodiceOperatore(data.getString("MDT_CMDTCOPE"));
    	setDataAggiornamento(getCalendarFromDate(data.getDate("MDT_GMDTDAGG")));
    	try {
    		setDescrEnte(data.getString("ANE_DANEDENT")); //PG22XX09_YL5
    	} catch (Exception ex) {
    		//il resultset proviene dalla sel e non dalla lst per cui l'informazione non è presente
    	}
    	
    }
	
	
	

	public ConfigurazioneModello3(String codiceSocieta, String codiceUtente,
			String chiaveEnte, String codiceIdentificativoDominio,
			String auxDigit, String codiceSegregazione,
			String tipologiaServizio, String urlIntegrazione,
			String codiceOperatore, Calendar dataAggiornamento, String carattereDiServizio) {
		super();
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnte = chiaveEnte;
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
		this.auxDigit = auxDigit;
		this.codiceSegregazione = codiceSegregazione;
		this.tipologiaServizio = tipologiaServizio;
		this.urlIntegrazione = urlIntegrazione;
		this.codiceOperatore = codiceOperatore;
		this.dataAggiornamento = dataAggiornamento;
		this.carattereDiServizio=carattereDiServizio; //SVILUPPO_002_SB
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

	public String getCodiceIdentificativoDominio() {
		return codiceIdentificativoDominio;
	}

	public void setCodiceIdentificativoDominio(String codiceIdentificativoDominio) {
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
	}

	public String getAuxDigit() {
		return auxDigit;
	}

	public void setAuxDigit(String auxDigit) {
		this.auxDigit = auxDigit;
	}

	public String getCodiceSegregazione() {
		return codiceSegregazione;
	}
	//SVILUPPO_002_SB
	public String getCarattereDiServizio() {
		return carattereDiServizio;
	}

	public void setCodiceSegregazione(String codiceSegregazione) {
		this.codiceSegregazione = codiceSegregazione;
	}
	//SVILUPPO_002_SB
	public void setCarattereDiServizio(String carattereDiServizio) {
		this.carattereDiServizio = carattereDiServizio;
	}

	public String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public String getUrlIntegrazione() {
		return urlIntegrazione;
	}

	public void setUrlIntegrazione(String urlIntegrazione) {
		this.urlIntegrazione = urlIntegrazione;
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
	//INI PG22XX09_YL5
	/**
	 * @return the descrEnte
	 */
	public String getDescrEnte() {
		return descrEnte;
	}

	/**
	 * @param descrEnte the descrEnte to set
	 */
	public void setDescrEnte(String descrEnte) {
		this.descrEnte = descrEnte;
	}
	// FINE PG22XX09_YL5


		
	
	

}
