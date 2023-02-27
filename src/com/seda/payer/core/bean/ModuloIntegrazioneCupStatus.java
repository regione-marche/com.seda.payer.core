package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class ModuloIntegrazioneCupStatus extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private String chiaveTransazione;
    private String codiceFiscale;
    private String codicePagamento;
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
    //da MIC
    private String xmlPaymentRequestMIC;
    private String codicePortaleEsternoMIC;
    private String parametriOpzionali1MIC;
    //da ANE
    private String descrizioneEnte;
    
    
    public ModuloIntegrazioneCupStatus() { 
    }

    public ModuloIntegrazioneCupStatus(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	Calendar cal = Calendar.getInstance();
    	
    	chiaveTransazione = data.getString("MCS_KTRAKTRA");
    	codiceFiscale = data.getString("MCS_CCUPCFIS");
    	codicePagamento = data.getString("MCS_CCUPCPAG");
    	gruppoTentativiNotifica = data.getInt("MCS_NMCSGRUP");
        numeroTentativoNotifica = data.getInt("MCS_NMCSCORR");
        modalitaNotifica = data.getString("MCS_CMCSMODA");
    	esitoTransazione = data.getString("MCS_FMCSFESI");
    	idPortale = data.getString("MCS_KMCSPORT");
    	numeroOperazione = data.getString("MCS_KMCSOPER");
    	xmlPaymentStatus = data.getString("MCS_CMCSPSTS");
    	xmlPaymentData = data.getString("MCS_CMCSPDAT");
    	xmlCommitMessage = data.getString("MCS_CMCSCMSG");
        esitoNotifica = data.getString("MCS_CMCSESIT");
        S2SResponseHtmlStatusCode = data.getString("MCS_CMCSCODE");
        S2SResponseMessage = data.getString("MCS_DMCSRESP");
        parametriOpzionali1 = data.getString("MCS_DMCSOPT1");
        parametriOpzionali2 = data.getString("MCS_DMCSOPT2");
        cal.setTimeInMillis(data.getTimestamp("MCS_GMCSGAGG").getTime());
        dataUltimoAggiornamento = cal;
        operatoreUltimoAggiornamento = data.getString("MCS_CMCSCOPE");
    }
    
    public ModuloIntegrazioneCupStatus(ResultSet data, boolean bBatch, boolean bUpdate) throws SQLException 
    {
    	this(data);
    	
    	if (bBatch)
    	{
    		//parametri recuperati solo per la stored PYMCSSP_BATCH
        	xmlPaymentRequestMIC = data.getString("MIC_CMICPREQ");
        	codicePortaleEsternoMIC = data.getString("MIC_CMICPORT");
        	parametriOpzionali1MIC = data.getString("MIC_DMICOPT1");
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
	 * @param xmlPaymentrequestMIC the xmlPaymentrequestMIC to set
	 */
	public void setXmlPaymentRequestMIC(String xmlPaymentRequestMIC) {
		this.xmlPaymentRequestMIC = xmlPaymentRequestMIC;
	}

	/**
	 * @return the xmlPaymentrequestMIC
	 */
	public String getXmlPaymentRequestMIC() {
		return xmlPaymentRequestMIC;
	}

	public void setCodicePortaleEsternoMIC(String codicePortaleEsternoMIC) {
		this.codicePortaleEsternoMIC = codicePortaleEsternoMIC;
	}

	public String getCodicePortaleEsternoMIC() {
		return codicePortaleEsternoMIC;
	}

	public void setParametriOpzionali1MIC(String parametriOpzionali1MIC) {
		this.parametriOpzionali1MIC = parametriOpzionali1MIC;
	}

	public String getParametriOpzionali1MIC() {
		return parametriOpzionali1MIC;
	}

	public void setDescrizioneEnte(String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		int ix=1;
    	arg.setString(ix++, this.chiaveTransazione);
    	arg.setString(ix++, this.codiceFiscale);
    	arg.setString(ix++, this.codicePagamento);

    	arg.setInt(ix++, this.gruppoTentativiNotifica);
    	arg.setInt(ix++, this.numeroTentativoNotifica);
    	arg.setString(ix++, this.modalitaNotifica != null ? modalitaNotifica : "");
    	arg.setString(ix++, this.esitoTransazione != null ? esitoTransazione : "");
		arg.setString(ix++, this.idPortale != null ? idPortale : "");
		arg.setString(ix++, this.numeroOperazione != null ? numeroOperazione : "");
		arg.setString(ix++, this.xmlPaymentStatus != null ? xmlPaymentStatus : "");  
		arg.setString(ix++, this.xmlPaymentData != null ? xmlPaymentData : "");  
		arg.setString(ix++, this.xmlCommitMessage != null ? xmlCommitMessage : "");
		arg.setString(ix++, this.esitoNotifica != null ? esitoNotifica : "");
		arg.setString(ix++, this.S2SResponseHtmlStatusCode != null ? S2SResponseHtmlStatusCode : "");
		arg.setString(ix++, this.S2SResponseMessage != null ? S2SResponseMessage : "");
		arg.setString(ix++, this.parametriOpzionali1 != null ? parametriOpzionali1 : "");
		arg.setString(ix++, this.parametriOpzionali2 != null ? parametriOpzionali2 : "");
		arg.setString(ix++, this.operatoreUltimoAggiornamento != null ? operatoreUltimoAggiornamento : "");
		
		arg.registerOutParameter(19, Types.INTEGER); //gruppo tentativi
		arg.registerOutParameter(20, Types.INTEGER); //numero tentativo
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		int ix=1;
		arg.setString(ix++, this.chiaveTransazione);
    	arg.setString(ix++, this.codiceFiscale);
    	arg.setString(ix++, this.codicePagamento);

    	arg.setInt(ix++, this.gruppoTentativiNotifica);
    	arg.setInt(ix++, this.numeroTentativoNotifica);
    	arg.setString(ix++, this.modalitaNotifica);
    	arg.setString(ix++, this.esitoTransazione);
		arg.setString(ix++, this.idPortale);
		arg.setString(ix++, this.numeroOperazione);
		arg.setString(ix++, this.xmlPaymentStatus);  
		arg.setString(ix++, this.xmlPaymentData);  
		arg.setString(ix++, this.xmlCommitMessage);
		arg.setString(ix++, this.esitoNotifica);
		arg.setString(ix++, this.S2SResponseHtmlStatusCode);
		arg.setString(ix++, this.S2SResponseMessage);
		arg.setString(ix++, this.parametriOpzionali1);
		arg.setString(ix++, this.parametriOpzionali2);
		arg.setString(ix++, this.operatoreUltimoAggiornamento);
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}