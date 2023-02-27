package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class ModuloIntegrazionePagamentiNodo extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private String chiaveTransazione;
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

    public ModuloIntegrazionePagamentiNodo() { 
    }

    public ModuloIntegrazionePagamentiNodo(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	Calendar cal = Calendar.getInstance();
    	chiaveTransazione = data.getString("MIN_KTRAKTRA");
    	idPortale = data.getString("MIN_KMIPPORT");
    	numeroOperazione = data.getString("MIN_KMIPOPER");
    	xmlPaymentRequest = data.getString("MIN_CMIPPREQ");
    	xmlPaymentData = data.getString("MIN_CMIPPDAT");
    	statoPagamentoCUP = data.getString("MIN_CMIPSTAT");
    	ultimoEsitoNotifica = data.getString("MIN_CMIPUESI");
    	codicePortaleEsterno = data.getString("MIN_CMIPPORT");
    	parametriOpzionali1 = data.getString("MIN_DMIPOPT1");
    	parametriOpzionali2 = data.getString("MIN_DMIPOPT2");
    	cal.setTimeInMillis(data.getTimestamp("MIN_GMIPGAGG").getTime());
    	dataUltimoAggiornamento = cal;
    	codiceSocieta = data.getString("MIN_CSOCCSOC");
    	codiceUtente = data.getString("MIN_CUTECUTE");
    	chiaveEnte = data.getString("MIN_KANEKENT");
    	commitNotifica = data.getString("MIN_FMIPNOTI");
    }

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public String getChiaveTransazione() {
		return chiaveTransazione;
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
    	arg.setString(1, this.chiaveTransazione);
		arg.setString(2, this.idPortale != null ? this.idPortale : "");
		arg.setString(3, this.numeroOperazione != null ? this.numeroOperazione : "");
		arg.setString(4, this.xmlPaymentRequest != null ? this.xmlPaymentRequest : "");  
		arg.setString(5, this.xmlPaymentData != null ? this.xmlPaymentData : "");
		arg.setString(6, this.statoPagamentoCUP != null ? this.statoPagamentoCUP : "");
		arg.setString(7, this.ultimoEsitoNotifica != null ? this.ultimoEsitoNotifica : "");
		arg.setString(8, this.codicePortaleEsterno != null ? this.codicePortaleEsterno : "");
		arg.setString(9, this.parametriOpzionali1 != null ? this.parametriOpzionali1 : "");
		arg.setString(10, this.parametriOpzionali2 != null ? this.parametriOpzionali2 : "");
		arg.setString(11, this.codiceSocieta != null ? this.codiceSocieta : "");
		arg.setString(12, this.codiceUtente != null ? this.codiceUtente : "");
		arg.setString(13, this.chiaveEnte != null ? this.chiaveEnte : "");
		arg.setString(14, this.commitNotifica != null ? this.commitNotifica : "");
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTransazione);
		arg.setString(2, this.idPortale);
		arg.setString(3, this.numeroOperazione);
		arg.setString(4, this.xmlPaymentRequest);  
		arg.setString(5, this.xmlPaymentData); 
		arg.setString(6, this.statoPagamentoCUP);
		arg.setString(7, this.ultimoEsitoNotifica);
		arg.setString(8, this.codicePortaleEsterno);
		arg.setString(9, this.parametriOpzionali1);
		arg.setString(10, this.parametriOpzionali2);
		arg.setString(11, this.codiceSocieta);
		arg.setString(12, this.codiceUtente);
		arg.setString(13, this.chiaveEnte);
		arg.setString(14, this.commitNotifica);
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	

	
}