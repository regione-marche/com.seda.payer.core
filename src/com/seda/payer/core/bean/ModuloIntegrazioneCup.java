package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class ModuloIntegrazioneCup extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private String chiaveTransazione;
    private String codiceFiscale;
    private String codicePagamento;
    private String idPortale;
    private String statoPagamentoCUP;
    private String numeroOperazione;
    private String xmlPaymentRequest;
    private String xmlPaymentData;
    private String ultimoEsitoNotifica;
    private String codicePortaleEsterno;
    private String parametriOpzionali1;
    private String parametriOpzionali2;
    private Calendar dataUltimoAggiornamento;
    private String codiceSocieta;
    private String codiceUtente;
    private String chiaveEnte;
    private String commitNotifica;

    public ModuloIntegrazioneCup() { 
    }

    public ModuloIntegrazioneCup(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	Calendar cal = Calendar.getInstance();
    	chiaveTransazione = data.getString("MIC_KTRAKTRA");
    	codiceFiscale = data.getString("MIC_CCUPCFIS");
    	codicePagamento = data.getString("MIC_CCUPCPAG");
    	idPortale = data.getString("MIC_KMICPORT");
    	numeroOperazione = data.getString("MIC_KMICOPER");
    	xmlPaymentRequest = data.getString("MIC_CMICPREQ");
    	xmlPaymentData = data.getString("MIC_CMICPDAT");
    	statoPagamentoCUP = data.getString("MIC_CMICSTAT");
    	ultimoEsitoNotifica = data.getString("MIC_CMICUESI");
    	codicePortaleEsterno = data.getString("MIC_CMICPORT");
    	parametriOpzionali1 = data.getString("MIC_DMICOPT1");
    	parametriOpzionali2 = data.getString("MIC_DMICOPT2");
    	cal.setTimeInMillis(data.getTimestamp("MIC_GMICGAGG").getTime());
    	dataUltimoAggiornamento = cal;
    	codiceSocieta = data.getString("MIC_CSOCCSOC");
    	codiceUtente = data.getString("MIC_CUTECUTE");
    	chiaveEnte = data.getString("MIC_KANEKENT");
    	commitNotifica = data.getString("MIC_FMICNOTI");
    }

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodicePagamento() {
		return codicePagamento;
	}

	public void setCodicePagamento(String codicePagamento) {
		this.codicePagamento = codicePagamento;
	}

	public void setIdPortale(String idPortale) {
		this.idPortale = idPortale;
	}

	public String getIdPortale() {
		return idPortale;
	}

	public void setNumeroOperazione(String numeroOperazione) {
		this.numeroOperazione = numeroOperazione;
	}

	public String getNumeroOperazione() {
		return numeroOperazione;
	}

	public void setXmlPaymentRequest(String xmlPaymentRequest) {
		this.xmlPaymentRequest = xmlPaymentRequest;
	}

	public String getXmlPaymentRequest() {
		return xmlPaymentRequest;
	}

	public void setXmlPaymentData(String xmlPaymentData) {
		this.xmlPaymentData = xmlPaymentData;
	}

	public String getXmlPaymentData() {
		return xmlPaymentData;
	}

	public void setStatoPagamentoCUP(String statoPagamentoCUP) {
		this.statoPagamentoCUP = statoPagamentoCUP;
	}

	public String getStatoPagamentoCUP() {
		return statoPagamentoCUP;
	}
	
	/**
	 * @param ultimoEsitoNotifica the ultimoEsitoNotifica to set
	 */
	public void setUltimoEsitoNotifica(String ultimoEsitoNotifica) {
		this.ultimoEsitoNotifica = ultimoEsitoNotifica;
	}

	/**
	 * @return the ultimoEsitoNotifica
	 */
	public String getUltimoEsitoNotifica() {
		return ultimoEsitoNotifica;
	}

	/**
	 * @param codicePortaleEsterno the codicePortaleEsterno to set
	 */
	public void setCodicePortaleEsterno(String codicePortaleEsterno) {
		this.codicePortaleEsterno = codicePortaleEsterno;
	}

	/**
	 * @return the codicePortaleEsterno
	 */
	public String getCodicePortaleEsterno() {
		return codicePortaleEsterno;
	}

	/**
	 * @param parametriOpzionali1 the parametriOpzionali1 to set
	 */
	public void setParametriOpzionali1(String parametriOpzionali1) {
		this.parametriOpzionali1 = parametriOpzionali1;
	}

	/**
	 * @return the parametriOpzionali1
	 */
	public String getParametriOpzionali1() {
		return parametriOpzionali1;
	}

	/**
	 * @param parametriOpzionali2 the parametriOpzionali2 to set
	 */
	public void setParametriOpzionali2(String parametriOpzionali2) {
		this.parametriOpzionali2 = parametriOpzionali2;
	}

	/**
	 * @return the parametriOpzionali2
	 */
	public String getParametriOpzionali2() {
		return parametriOpzionali2;
	}

	/**
	 * @param dataUltimoAggiornamento the dataUltimoAggiornamento to set
	 */
	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	/**
	 * @return the dataUltimoAggiornamento
	 */
	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setCommitNotifica(String commitNotifica) {
		this.commitNotifica = commitNotifica;
	}

	public String getCommitNotifica() {
		return commitNotifica;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		int ix=1;
    	arg.setString(ix++, this.chiaveTransazione);
    	arg.setString(ix++, this.codiceFiscale);
    	arg.setString(ix++, this.codicePagamento);
    	
		arg.setString(ix++, this.idPortale != null ? this.idPortale : "");
		arg.setString(ix++, this.numeroOperazione != null ? this.numeroOperazione : "");
		arg.setString(ix++, this.xmlPaymentRequest != null ? this.xmlPaymentRequest : "");  
		arg.setString(ix++, this.xmlPaymentData != null ? this.xmlPaymentData : "");
		arg.setString(ix++, this.statoPagamentoCUP != null ? this.statoPagamentoCUP : "");
		arg.setString(ix++, this.ultimoEsitoNotifica != null ? this.ultimoEsitoNotifica : "");
		arg.setString(ix++, this.codicePortaleEsterno != null ? this.codicePortaleEsterno : "");
		arg.setString(ix++, this.parametriOpzionali1 != null ? this.parametriOpzionali1 : "");
		arg.setString(ix++, this.parametriOpzionali2 != null ? this.parametriOpzionali2 : "");
		arg.setString(ix++, this.codiceSocieta != null ? this.codiceSocieta : "");
		arg.setString(ix++, this.codiceUtente != null ? this.codiceUtente : "");
		arg.setString(ix++, this.chiaveEnte != null ? this.chiaveEnte : "");
		arg.setString(ix++, this.commitNotifica != null ? this.commitNotifica : "");
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		int ix=1;
		arg.setString(ix++, this.chiaveTransazione);
		arg.setString(ix++, this.codiceFiscale);
		arg.setString(ix++, this.codicePagamento);
		arg.setString(ix++, this.idPortale);
		arg.setString(ix++, this.numeroOperazione);
		arg.setString(ix++, this.xmlPaymentRequest);  
		arg.setString(ix++, this.xmlPaymentData); 
		arg.setString(ix++, this.statoPagamentoCUP);
		arg.setString(ix++, this.ultimoEsitoNotifica);
		arg.setString(ix++, this.codicePortaleEsterno);
		arg.setString(ix++, this.parametriOpzionali1);
		arg.setString(ix++, this.parametriOpzionali2);
		arg.setString(ix++, this.codiceSocieta);
		arg.setString(ix++, this.codiceUtente);
		arg.setString(ix++, this.chiaveEnte);
		arg.setString(ix++, this.commitNotifica);
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	

	
}