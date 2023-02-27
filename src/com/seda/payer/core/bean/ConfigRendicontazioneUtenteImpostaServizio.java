package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigRendicontazioneUtenteImpostaServizio extends Lifecycle implements Serializable {

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
    
    private User user;
    private ImpostaServizio impostaServizio;

    
    public ConfigRendicontazioneUtenteImpostaServizio() { 
    	user = new User();
    	impostaServizio = new ImpostaServizio();
    }

    public ConfigRendicontazioneUtenteImpostaServizio(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	serverFTP = data.getString("RES_DRESSFTP");
    	utenteFTP = data.getString("RES_DRESUFTP");
    	passwordFTP = data.getString("RES_DRESPFTP");
    	fileFTP = data.getString("RES_DRESFILE");
        flagAttivazione = data.getString("RES_FRESFATT");
        codiceOperatore = data.getString("RES_CRESCOPE");
        emailFrom = data.getString("RES_ERESEMIT");
        emailCcn = data.getString("RES_ERESECCN");
        emailTo = data.getString("RES_ERESEDES");
        attachFlag = data.getString("RES_FRESFALL");
        descrMittente = data.getString("RES_DRESDMIT");
        user = new User(); {
        	user.getCompany().setCompanyCode(data.getString("RES_CSOCCSOC"));
        	user.setUserCode(data.getString("RES_CUTECUTE"));
        }
        impostaServizio = new ImpostaServizio(); {
        	impostaServizio.getTipologiaServizio().setCodiceTipologiaServizio(data.getString("RES_CTSECTSE"));
        	impostaServizio.setCodiceImpostaServizio(data.getString("RES_CISECISE"));
        }   
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ImpostaServizio getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(ImpostaServizio impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.user.getCompany().getCompanyCode());
		arg.setString(2, this.user.getUserCode());
		arg.setString(3, this.impostaServizio.getTipologiaServizio().getCodiceTipologiaServizio());
		arg.setString(4, this.impostaServizio.getCodiceImpostaServizio());
		arg.setString(5, this.getEmailTo());
		arg.setString(6, this.getEmailCcn());
		arg.setString(7, this.getEmailFrom());
		arg.setString(8, this.getDescrMittente()); 
		arg.setString(9, this.getAttachFlag());
		arg.setString(10, this.getServerFTP());
		arg.setString(11, this.getUtenteFTP());
		arg.setString(12, this.getPasswordFTP());
		arg.setString(13, this.getFileFTP());
		arg.setString(14, this.getFlagAttivazione());
		arg.setTimestamp(15, new Timestamp(System.currentTimeMillis()));
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