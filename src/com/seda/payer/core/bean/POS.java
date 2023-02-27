package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class POS implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chiaveTransazione;
	private String terminalID;
	private String acquirerID;
	private String authorizationCode;
	private String operationNumber;
	private String informazioniAll;
	private Calendar dataAggiornamento;
	private String operatoreAggiornamento;
	
	public String getChiaveTransazione() {
		return chiaveTransazione;
	}
	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}
	public String getTerminalID() {
		return terminalID;
	}
	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}
	public String getAcquirerID() {
		return acquirerID;
	}
	public void setAcquirerID(String acquirerID) {
		this.acquirerID = acquirerID;
	}
	public String getAuthorizationCode() {
		return authorizationCode;
	}
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}
	public String getOperationNumber() {
		return operationNumber;
	}
	public void setOperationNumber(String operationNumber) {
		this.operationNumber = operationNumber;
	}
	public String getInformazioniAll() {
		return informazioniAll;
	}
	public void setInformazioniAll(String informazioniAll) {
		this.informazioniAll = informazioniAll;
	}
	public Calendar getDataAggiornamento() {
		return dataAggiornamento;
	}
	public void setDataAggiornamento(Calendar dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}
	public String getOperatoreAggiornamento() {
		return operatoreAggiornamento;
	}
	public void setOperatoreAggiornamento(String operatoreAggiornamento) {
		this.operatoreAggiornamento = operatoreAggiornamento;
	}
	
	//PG200150
	public static POS getBean(ResultSet data) throws SQLException {
		POS bean = new POS(); {
			bean.chiaveTransazione = data.getString("POS_KTRAKTRA");
			bean.terminalID = data.getString("POS_CPOSTEID");
			bean.acquirerID = data.getString("POS_CPOSAQID");
			bean.authorizationCode = data.getString("POS_CPOSAUTH");
			bean.operationNumber = data.getString("POS_CPOSOPER");
			bean.informazioniAll = data.getString("POS_CPOSDALL");
			bean.dataAggiornamento = getCalendarFromTimestamp(data.getTimestamp("POS_GPOSGAGG"));
			bean.operatoreAggiornamento = data.getString("POS_CPOSCOPE");
		}
		return bean;
	}
	//PG200150
	public static Calendar getCalendarFromTimestamp(java.sql.Timestamp date){
		if (date == null) return null;
		long millis = date.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal;
	}
	

}
