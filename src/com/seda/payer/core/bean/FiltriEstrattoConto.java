package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class FiltriEstrattoConto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String chiaveFiltroEstrattoConto;
	private String codiceFiscale;
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
	private String codiceImpostaServizio;
	private String numeroEmissione;
	private String numeroDocumento;
	private Calendar dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento; 
	private String descrizioneSocUteEnt;
    
	public FiltriEstrattoConto() {
	}
	
	 public FiltriEstrattoConto(ResultSet data) throws SQLException 
	 {
    	if (data == null)
    		return;
    	setChiaveFiltroEstrattoConto(data.getString("FEC_KFECKFEC"));
    	setCodiceFiscale(data.getString("FEC_CFECCFIS"));
//    	setCodiceSocieta(data.getString("FEC_CSOCCSOC"));
//    	setCodiceUtente(data.getString("FEC_CUTECUTE"));
//    	setChiaveEnte(data.getString("FEC_KANEKENT"));
    	//Giulia
    	setChiaveEnte(data.getString("FEC_CFECCENT"));
    	setCodiceImpostaServizio(data.getString("FEC_CFECIMSE"));
    	setNumeroEmissione(data.getString("FEC_CFECEMIS"));
    	setNumeroDocumento(data.getString("FEC_CFECNDOC"));
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(data.getTimestamp("FEC_CFECGAGG").getTime());
    	setDataUltimoAggiornamento(cal);
    	setOperatoreUltimoAggiornamento(data.getString("FEC_CFECCOPE"));
    	//Giulia
    	//setDescrizioneSocUteEnt(data.getString("ANE_DANEDENT"));
	 }
	 

	public String getChiaveFiltroEstrattoConto() {
		return chiaveFiltroEstrattoConto;
	}

	public void setChiaveFiltroEstrattoConto(String chiaveFiltroEstrattoConto) {
		this.chiaveFiltroEstrattoConto = chiaveFiltroEstrattoConto;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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

	public String getCodiceImpostaServizio() {
		return codiceImpostaServizio;
	}

	public void setCodiceImpostaServizio(String codiceImpostaServizio) {
		this.codiceImpostaServizio = codiceImpostaServizio;
	}

	public String getNumeroEmissione() {
		return numeroEmissione;
	}

	public void setNumeroEmissione(String numeroEmissione) {
		this.numeroEmissione = numeroEmissione;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}

	public void setDescrizioneSocUteEnt(String descrizioneSocUteEnt) {
		this.descrizioneSocUteEnt = descrizioneSocUteEnt;
	}

	public String getDescrizioneSocUteEnt() {
		return descrizioneSocUteEnt;
	}

}
