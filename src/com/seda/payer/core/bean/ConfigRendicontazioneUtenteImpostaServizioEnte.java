package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class ConfigRendicontazioneUtenteImpostaServizioEnte extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String serverFTP;
    private java.lang.String utenteFTP;
    private java.lang.String passwordFTP;
    private java.lang.String fileFTP;
    private java.lang.String flagAttivazione;
    private java.lang.String codiceOperatore;
    private java.lang.String emailFrom;
    private java.lang.String emailCcn;
    private java.lang.String emailTo;
    private java.lang.String descrMittente;
    private java.lang.String attachFlag;
    private Ente ente;
    private ImpostaServizio impostaServizio;

    
    public ConfigRendicontazioneUtenteImpostaServizioEnte() { 
    	ente = new Ente();
    	impostaServizio = new ImpostaServizio();
    }

    public ConfigRendicontazioneUtenteImpostaServizioEnte(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	serverFTP = data.getString("REE_DREESFTP");
    	utenteFTP = data.getString("REE_DREEUFTP");
    	passwordFTP = data.getString("REE_DREEPFTP");
    	fileFTP = data.getString("REE_DREEFILE");
        flagAttivazione = data.getString("REE_FREEFATT");
        codiceOperatore = data.getString("REE_CREECOPE");
        emailFrom = data.getString("REE_EREEEMIT");
        emailCcn = data.getString("REE_EREEECCN");
        emailTo = data.getString("REE_EREEEDES");
        attachFlag = data.getString("REE_FREEFALL");
        descrMittente = data.getString("REE_DREEDMIT");
        ente = new Ente(); {
        	ente.getUser().getCompany().setCompanyCode(data.getString("REE_CSOCCSOC"));
        	ente.getUser().setUserCode(data.getString("REE_CUTECUTE"));
        	ente.getAnagEnte().setChiaveEnte(data.getString("REE_KANEKENT"));	
        }
        impostaServizio = new ImpostaServizio(); {
        	impostaServizio.getTipologiaServizio().setCodiceTipologiaServizio(data.getString("REE_CTSECTSE"));
        	impostaServizio.setCodiceImpostaServizio(data.getString("REE_CISECISE"));
        }   
    }

	public java.lang.String getServerFTP() {
		return serverFTP;
	}

	public void setServerFTP(java.lang.String serverFTP) {
		this.serverFTP = serverFTP;
	}

	public java.lang.String getUtenteFTP() {
		return utenteFTP;
	}

	public void setUtenteFTP(java.lang.String utenteFTP) {
		this.utenteFTP = utenteFTP;
	}

	public java.lang.String getPasswordFTP() {
		return passwordFTP;
	}

	public void setPasswordFTP(java.lang.String passwordFTP) {
		this.passwordFTP = passwordFTP;
	}

	public java.lang.String getFileFTP() {
		return fileFTP;
	}

	public void setFileFTP(java.lang.String fileFTP) {
		this.fileFTP = fileFTP;
	}

	public java.lang.String getFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(java.lang.String flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public ImpostaServizio getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(ImpostaServizio impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	public java.lang.String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(java.lang.String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public java.lang.String getEmailCcn() {
		return emailCcn;
	}

	public void setEmailCcn(java.lang.String emailCcn) {
		this.emailCcn = emailCcn;
	}

	public java.lang.String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(java.lang.String emailTo) {
		this.emailTo = emailTo;
	}

	public java.lang.String getDescrMittente() {
		return descrMittente;
	}

	public void setDescrMittente(java.lang.String descrMittente) {
		this.descrMittente = descrMittente;
	}

	public java.lang.String getAttachFlag() {
		return attachFlag;
	}

	public void setAttachFlag(java.lang.String attachFlag) {
		this.attachFlag = attachFlag;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.ente.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.ente.getUser().getUserCode());
		arg.setString(3, this.ente.getAnagEnte().getChiaveEnte());		
		arg.setString(4, this.impostaServizio.getTipologiaServizio().getCodiceTipologiaServizio());
		arg.setString(5, this.impostaServizio.getCodiceImpostaServizio());
		arg.setString(6, this.getEmailTo());
		arg.setString(7, this.getEmailCcn());
		arg.setString(8, this.getEmailFrom());	
		arg.setString(9, this.getDescrMittente());
		arg.setString(10, this.getAttachFlag());
		arg.setString(11, this.getServerFTP());
		arg.setString(12, this.getUtenteFTP());
		arg.setString(13, this.getPasswordFTP());
		arg.setString(14, this.getFileFTP());
		arg.setString(15, this.getFlagAttivazione());
		arg.setString(16, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}