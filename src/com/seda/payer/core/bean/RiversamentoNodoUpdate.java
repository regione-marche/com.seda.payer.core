/**
 * 
 */
package com.seda.payer.core.bean;

import java.io.Serializable;

/**
 * @author pgasparroni
 *
 */
public class RiversamentoNodoUpdate implements Serializable {

	private static final long serialVersionUID = -1253780862394122905L;
//	input data
	private String codiceSocieta;
	private String codiceUtente;
	private String dataRiversamento;
	private String enteBeneficiario;
	private String tipoRiversamento;
	private String tipoRendicontazione;
	private String statoRiversamento;
	private String flagCBI;
	private String operatore;
	private String nota;

	//	output data
	private String retCode;
	private String retMessage;
	private String message;
	
	private int numRows;

	/**
	 * 
	 */
	public RiversamentoNodoUpdate() {
		codiceSocieta = "";
		codiceUtente = "";
		dataRiversamento = "";
		enteBeneficiario = "";
		tipoRiversamento = "";
		tipoRendicontazione = "";
		statoRiversamento = "";
		flagCBI = "";
		operatore = "";
		nota = "";
		
		retCode = "";
		retMessage  = "";
		message = "";
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
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

	public String getDataRiversamento() {
		return dataRiversamento;
	}

	public void setDataRiversamento(String dataRiversamento) {
		this.dataRiversamento = dataRiversamento;
	}

	public String getEnteBeneficiario() {
		return enteBeneficiario;
	}

	public void setEnteBeneficiario(String enteBeneficiario) {
		this.enteBeneficiario = enteBeneficiario;
	}

	public String getTipoRiversamento() {
		return tipoRiversamento;
	}

	public void setTipoRiversamento(String tipoRiversamento) {
		this.tipoRiversamento = tipoRiversamento;
	}

	public String getTipoRendicontazione() {
		return tipoRendicontazione;
	}

	public void setTipoRendicontazione(String tipoRendicontazione) {
		this.tipoRendicontazione = tipoRendicontazione;
	}

	public String getStatoRiversamento() {
		return statoRiversamento;
	}

	public void setStatoRiversamento(String statoRiversamento) {
		this.statoRiversamento = statoRiversamento;
	}

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMessage() {
		return retMessage;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFlagCBI() {
		return flagCBI;
	}

	public void setFlagCBI(String flagCBI) {
		this.flagCBI = flagCBI;
	}
	
	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	
}
