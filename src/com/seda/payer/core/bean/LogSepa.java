package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlRootElement;

import com.seda.payer.commons.bean.Lifecycle;

@XmlRootElement
public class LogSepa extends Lifecycle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codiceFiscale;
	private String iban;
	private Calendar dataAggiornamento;
	private String operatore;
	private String esito;
	
	
	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}



	public String getIban() {
		return iban;
	}



	public Calendar getDataAggiornamento() {
		return dataAggiornamento;
	}



	public String getOperatore() {
		return operatore;
	}



	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}



	public void setIban(String iban) {
		this.iban = iban;
	}



	public void setDataAggiornamento(Calendar dataAggiornamento) {
		this.dataAggiornamento = dataAggiornamento;
	}



	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}



	public String getEsito() {
		return esito;
	}



	public void setEsito(String esito) {
		this.esito = esito;
	}



	public LogSepa() {
		super();
	}
	
	/**
	 * 
	 * @param data
	 * @throws SQLException
	 */
	public LogSepa(ResultSet data) throws SQLException {
		if (data == null)
			return;
		this.codiceFiscale = data.getString("SEL_CSELCFIS");
		this.iban = data.getString("SEL_CSELIBAN");
		this.dataAggiornamento =  sqlDateToCalendar(data.getDate("SEL_GSELGAGG").toString());
		this.operatore = data.getString("SEL_CSELCOPE");
		this.esito = data.getString("SEL_FSELFESI");
	}
	
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceFiscale);
		arg.setString(2, this.iban);
		arg.setString(3, this.operatore);	
		arg.setString(4, esito);
		arg.registerOutParameter(5, Types.BIGINT);
	}
	
	public void onDelete(CallableStatement arg) throws SQLException {
	}
	
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceFiscale);	
		arg.setString(2, this.iban);
		arg.setString(3, this.operatore);
		arg.setString(4, esito);
		arg.registerOutParameter(5, Types.INTEGER);
	}


	public void onLoad(CallableStatement arg) throws SQLException {
		if (this.codiceFiscale != null && this.iban != null) {
			arg.setString(1, this.codiceFiscale);
			arg.setString(2, this.iban);
		} else
			throw new SQLException("codice fiscale or iban is null");
	}
	
	private Calendar sqlDateToCalendar(String sqlDateToString) {
		Date date = Date.valueOf(sqlDateToString);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal;
	}
}
