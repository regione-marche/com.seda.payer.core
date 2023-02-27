package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class ModuloIntegrazionePagamentiPaymentStatus extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private String chiaveTransazione;
    private int gruppoTentativiNotifica;
    private int numeroTentativoNotifica;
    private String modalitaNotifica;
    private String esitoTransazione;
    private String idPortale;
    private String numeroOperazione;
    private String xmlPaymentStatus;
    private String xmlPaymentData;
    private String xmlCommitMessage;
    private String esitoNotifica;
    private String S2SResponseHtmlStatusCode;
    private String S2SResponseMessage;
    private String parametriOpzionali1;
    private String parametriOpzionali2;
    private Calendar dataUltimoAggiornamento;
    private String operatoreUltimoAggiornamento;
    //da MIP
    private String xmlPaymentRequestMIP;
    private String codicePortaleEsternoMIP;
    private String parametriOpzionali1MIP;
    //da ANE
    private String descrizioneEnte;
    
    
    public ModuloIntegrazionePagamentiPaymentStatus() { 
    }

    public ModuloIntegrazionePagamentiPaymentStatus(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	Calendar cal = Calendar.getInstance();
    	
    	chiaveTransazione = data.getString("MPS_KTRAKTRA");
    	gruppoTentativiNotifica = data.getInt("MPS_NMPSGRUP");
        numeroTentativoNotifica = data.getInt("MPS_NMPSCORR");
        modalitaNotifica = data.getString("MPS_CMPSMODA");
    	esitoTransazione = data.getString("MPS_FMPSFESI");
    	idPortale = data.getString("MPS_KMPSPORT");
    	numeroOperazione = data.getString("MPS_KMPSOPER");
    	xmlPaymentStatus = data.getString("MPS_CMPSPSTS");
    	xmlPaymentData = data.getString("MPS_CMPSPDAT");
    	xmlCommitMessage = data.getString("MPS_CMPSCMSG");
        esitoNotifica = data.getString("MPS_CMPSESIT");
        S2SResponseHtmlStatusCode = data.getString("MPS_CMPSCODE");
        S2SResponseMessage = data.getString("MPS_DMPSRESP");
        parametriOpzionali1 = data.getString("MPS_DMPSOPT1");
        parametriOpzionali2 = data.getString("MPS_DMPSOPT2");
        cal.setTimeInMillis(data.getTimestamp("MPS_GMPSGAGG").getTime());
        dataUltimoAggiornamento = cal;
        operatoreUltimoAggiornamento = data.getString("MPS_CMPSCOPE");
    }
    
    public ModuloIntegrazionePagamentiPaymentStatus(ResultSet data, boolean bBatch, boolean bUpdate) throws SQLException 
    {
    	this(data);
    	
    	if (bBatch)
    	{
    		//parametri recuperati solo per la stored PYMPSSP_BATCH
        	xmlPaymentRequestMIP = data.getString("MIP_CMIPPREQ");
        	codicePortaleEsternoMIP = data.getString("MIP_CMIPPORT");
        	parametriOpzionali1MIP = data.getString("MIP_DMIPOPT1");
    	}
    	if (bUpdate)
    	{
    		//parametro recuperato solo per la stored PYMPSSP_UPD
        	descrizioneEnte = data.getString("ANE_DANEDENT");
    	}
    }

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	/**
	 * @param gruppoTentativiNotifica the gruppoTentativiNotifica to set
	 */
	public void setGruppoTentativiNotifica(int gruppoTentativiNotifica) {
		this.gruppoTentativiNotifica = gruppoTentativiNotifica;
	}

	/**
	 * @return the gruppoTentativiNotifica
	 */
	public int getGruppoTentativiNotifica() {
		return gruppoTentativiNotifica;
	}

	/**
	 * @param numeroTentativoNotifica the numeroTentativoNotifica to set
	 */
	public void setNumeroTentativoNotifica(int numeroTentativoNotifica) {
		this.numeroTentativoNotifica = numeroTentativoNotifica;
	}

	/**
	 * @return the numeroTentativoNotifica
	 */
	public int getNumeroTentativoNotifica() {
		return numeroTentativoNotifica;
	}

	/**
	 * @param modalitaNotifica the modalitaNotifica to set
	 */
	public void setModalitaNotifica(String modalitaNotifica) {
		this.modalitaNotifica = modalitaNotifica;
	}

	/**
	 * @return the modalitaNotifica
	 */
	public String getModalitaNotifica() {
		return modalitaNotifica;
	}

	public void setEsitoTransazione(String esitoTransazione) {
		this.esitoTransazione = esitoTransazione;
	}

	public String getEsitoTransazione() {
		return esitoTransazione;
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

	public void setXmlPaymentStatus(String xmlPaymentStatus) {
		this.xmlPaymentStatus = xmlPaymentStatus;
	}

	public String getXmlPaymentStatus() {
		return xmlPaymentStatus;
	}

	public void setXmlPaymentData(String xmlPaymentData) {
		this.xmlPaymentData = xmlPaymentData;
	}

	public String getXmlPaymentData() {
		return xmlPaymentData;
	}
	
	/**
	 * @param xmlCommitMessage the xmlCommitMessage to set
	 */
	public void setXmlCommitMessage(String xmlCommitMessage) {
		this.xmlCommitMessage = xmlCommitMessage;
	}

	/**
	 * @return the xmlCommitMessage
	 */
	public String getXmlCommitMessage() {
		return xmlCommitMessage;
	}

	/**
	 * @param esitoNotifica the esitoNotifica to set
	 */
	public void setEsitoNotifica(String esitoNotifica) {
		this.esitoNotifica = esitoNotifica;
	}

	/**
	 * @return the esitoNotifica
	 */
	public String getEsitoNotifica() {
		return esitoNotifica;
	}

	/**
	 * @param s2SResponseHtmlStatusCode the s2SResponseHtmlStatusCode to set
	 */
	public void setS2SResponseHtmlStatusCode(String s2SResponseHtmlStatusCode) {
		S2SResponseHtmlStatusCode = s2SResponseHtmlStatusCode;
	}

	/**
	 * @return the s2SResponseHtmlStatusCode
	 */
	public String getS2SResponseHtmlStatusCode() {
		return S2SResponseHtmlStatusCode;
	}

	/**
	 * @param s2SResponseMessage the s2SResponseMessage to set
	 */
	public void setS2SResponseMessage(String s2SResponseMessage) {
		S2SResponseMessage = s2SResponseMessage;
	}

	/**
	 * @return the s2SResponseMessage
	 */
	public String getS2SResponseMessage() {
		return S2SResponseMessage;
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

	/**
	 * @param operatoreUltimoAggiornamento the operatoreUltimoAggiornamento to set
	 */
	public void setOperatoreUltimoAggiornamento(
			String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}

	/**
	 * @return the operatoreUltimoAggiornamento
	 */
	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	/**
	 * @param xmlPaymentrequestMIP the xmlPaymentrequestMIP to set
	 */
	public void setXmlPaymentRequestMIP(String xmlPaymentRequestMIP) {
		this.xmlPaymentRequestMIP = xmlPaymentRequestMIP;
	}

	/**
	 * @return the xmlPaymentrequestMIP
	 */
	public String getXmlPaymentRequestMIP() {
		return xmlPaymentRequestMIP;
	}

	public void setCodicePortaleEsternoMIP(String codicePortaleEsternoMIP) {
		this.codicePortaleEsternoMIP = codicePortaleEsternoMIP;
	}

	public String getCodicePortaleEsternoMIP() {
		return codicePortaleEsternoMIP;
	}

	public void setParametriOpzionali1MIP(String parametriOpzionali1MIP) {
		this.parametriOpzionali1MIP = parametriOpzionali1MIP;
	}

	public String getParametriOpzionali1MIP() {
		return parametriOpzionali1MIP;
	}

	public void setDescrizioneEnte(String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
    	arg.setString(1, this.chiaveTransazione);
    	arg.setInt(2, this.gruppoTentativiNotifica);
    	arg.setInt(3, this.numeroTentativoNotifica);
    	arg.setString(4, this.modalitaNotifica != null ? modalitaNotifica : "");
    	arg.setString(5, this.esitoTransazione != null ? esitoTransazione : "");
		arg.setString(6, this.idPortale != null ? idPortale : "");
		arg.setString(7, this.numeroOperazione != null ? numeroOperazione : "");
		arg.setString(8, this.xmlPaymentStatus != null ? xmlPaymentStatus : "");  
		arg.setString(9, this.xmlPaymentData != null ? xmlPaymentData : "");  
		arg.setString(10, this.xmlCommitMessage != null ? xmlCommitMessage : "");
		arg.setString(11, this.esitoNotifica != null ? esitoNotifica : "");
		arg.setString(12, this.S2SResponseHtmlStatusCode != null ? S2SResponseHtmlStatusCode : "");
		arg.setString(13, this.S2SResponseMessage != null ? S2SResponseMessage : "");
		arg.setString(14, this.parametriOpzionali1 != null ? parametriOpzionali1 : "");
		arg.setString(15, this.parametriOpzionali2 != null ? parametriOpzionali2 : "");
		arg.setString(16, this.operatoreUltimoAggiornamento != null ? operatoreUltimoAggiornamento : "");
		
		arg.registerOutParameter(17, Types.INTEGER); //gruppo tentativi
		arg.registerOutParameter(18, Types.INTEGER); //numero tentativo
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTransazione);
    	arg.setInt(2, this.gruppoTentativiNotifica);
    	arg.setInt(3, this.numeroTentativoNotifica);
    	arg.setString(4, this.modalitaNotifica);
    	arg.setString(5, this.esitoTransazione);
		arg.setString(6, this.idPortale);
		arg.setString(7, this.numeroOperazione);
		arg.setString(8, this.xmlPaymentStatus);  
		arg.setString(9, this.xmlPaymentData);  
		arg.setString(10, this.xmlCommitMessage);
		arg.setString(11, this.esitoNotifica);
		arg.setString(12, this.S2SResponseHtmlStatusCode);
		arg.setString(13, this.S2SResponseMessage);
		arg.setString(14, this.parametriOpzionali1);
		arg.setString(15, this.parametriOpzionali2);
		arg.setString(16, this.operatoreUltimoAggiornamento);
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}