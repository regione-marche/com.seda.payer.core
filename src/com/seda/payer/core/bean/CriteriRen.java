package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

/**
 * Questo bean fa il mapping delle tabelle PYREETB/PYRESTB che 
 * contengono le informazioni di configurazione per la rendicontazione
 * agli enti.  
 * @author f.vadicamo
 *
 */

public class CriteriRen  extends Lifecycle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private java.lang.String codiceSocieta;
	private java.lang.String codiceUtente;
	private java.lang.String chiaveEnte;
	private java.lang.String tipologiaServizio;
	private java.lang.String emailDestinatario;
	private java.lang.String emailConoscenzaNascosta;
	private java.lang.String emailMittente;
	private java.lang.String descrizioneMittente;
	private java.lang.String flagAbilitazioneInvioEmail;
	private java.lang.String serverFtp;
	private java.lang.String utenteFtp;
	private java.lang.String passwordFtp;
	private java.lang.String directoryRemotaServerFtp;
	private java.lang.String flagAbilitazioneInvioFtp;
	private int maxSizeEmailAttachInKb;
	private java.util.Date dataUltimoAggiornamento;
	private java.lang.String opertoreUltimoAggiornamento; 
	private java.lang.String tipologiaRendicontazione;  //PG110260
	private java.lang.String flagCarico;
	private java.lang.String ente;
	private java.lang.String impServizio;
	private java.lang.String flagCodContrib;
	private java.lang.String codTributo; 	//PG180260 GG 07122018
	private java.lang.String abilitazioneInvioWs;
	private java.lang.String urlWs;
	private java.lang.String utenteWs;
	private java.lang.String passwordWs;
	private java.lang.String rendQuattrocento;

	public CriteriRen() {}
	
	public CriteriRen(
			 String codiceSocieta,
			 String codiceUtente,
			 String chiaveEnte,
			 String tipologiaServizio,
			 String emailDestinatario,
			 String emailConoscenzaNascosta,
			 String emailMittente,
			 String descrizioneMittente,
			 String flagAbilitazioneInvioEmail,
			 String serverFtp,
			 String utenteFtp,
			 String passwordFtp,
			 String directoryRemotaServerFtp,
			 String flagAbilitazioneInvioFtp,
			 int maxSizeEmailAttachInKb,
			 java.util.Date dataUltimoAggiornamento,
			 String opertoreUltimoAggiornamento,
			 String tipologiaRendicontazione,
			 String flagCarico,
			 String ente,
			 String impServizio,
			 String flagCodContrib,
			 String codTributo,		//PG180260 GG 07122018
			 String abilitazioneWs,
			 String urlWs,
			 String utenteWs,
			 String passwordWs,
			 String rendQuattrocento
	)
	{
		this.codiceSocieta=codiceSocieta;
		this.codiceUtente=codiceUtente;
		this.chiaveEnte=chiaveEnte;
		this.tipologiaServizio=tipologiaServizio;
		this.emailDestinatario=emailDestinatario;
		this.emailConoscenzaNascosta=emailConoscenzaNascosta;
		this.emailMittente=emailMittente;
		this.descrizioneMittente=descrizioneMittente;
		this.flagAbilitazioneInvioEmail=flagAbilitazioneInvioEmail;
		this.serverFtp=serverFtp;
		this.utenteFtp=utenteFtp;
		this.passwordFtp=passwordFtp;
		this.directoryRemotaServerFtp=directoryRemotaServerFtp;
		this.flagAbilitazioneInvioFtp=flagAbilitazioneInvioFtp;
		this.maxSizeEmailAttachInKb=maxSizeEmailAttachInKb;
		this.dataUltimoAggiornamento=dataUltimoAggiornamento;
		this.opertoreUltimoAggiornamento=opertoreUltimoAggiornamento;
		this.tipologiaRendicontazione=tipologiaRendicontazione; //PG110260
		this.flagCarico=flagCarico; 
		this.ente=ente;
		this.impServizio=impServizio;
		this.flagCodContrib=flagCodContrib;
		this.codTributo=codTributo;		//PG180260 GG 07122018
		this.abilitazioneInvioWs = abilitazioneWs;
		this.urlWs = urlWs;
		this.utenteWs = utenteWs;
		this.passwordWs = passwordWs;
		this.rendQuattrocento = rendQuattrocento;
	}

	public java.lang.String getrendQuattrocento() {
		return rendQuattrocento;
	}

	public void setrendQuattrocento(java.lang.String rendQuattrocento) {
		this.rendQuattrocento = rendQuattrocento;
	}

	public java.lang.String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(java.lang.String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public java.lang.String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(java.lang.String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public java.lang.String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(java.lang.String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public java.lang.String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(java.lang.String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public java.lang.String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(java.lang.String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public java.lang.String getEmailConoscenzaNascosta() {
		return emailConoscenzaNascosta;
	}

	public void setEmailConoscenzaNascosta(java.lang.String emailConoscenzaNascosta) {
		this.emailConoscenzaNascosta = emailConoscenzaNascosta;
	}

	public java.lang.String getEmailMittente() {
		return emailMittente;
	}

	public void setEmailMittente(java.lang.String emailMittente) {
		this.emailMittente = emailMittente;
	}

	public java.lang.String getDescrizioneMittente() {
		return descrizioneMittente;
	}

	public void setDescrizioneMittente(java.lang.String descrizioneMittente) {
		this.descrizioneMittente = descrizioneMittente;
	}

	public java.lang.String getFlagAbilitazioneInvioEmail() {
		return flagAbilitazioneInvioEmail;
	}

	public void setFlagAbilitazioneInvioEmail(
			java.lang.String flagAbilitazioneInvioEmail) {
		this.flagAbilitazioneInvioEmail = flagAbilitazioneInvioEmail;
	}

	public java.lang.String getServerFtp() {
		return serverFtp;
	}

	public void setServerFtp(java.lang.String serverFtp) {
		this.serverFtp = serverFtp;
	}

	public java.lang.String getUtenteFtp() {
		return utenteFtp;
	}

	public void setUtenteFtp(java.lang.String utenteFtp) {
		this.utenteFtp = utenteFtp;
	}

	public java.lang.String getPasswordFtp() {
		return passwordFtp;
	}

	public void setPasswordFtp(java.lang.String passwordFtp) {
		this.passwordFtp = passwordFtp;
	}

	public java.lang.String getDirectoryRemotaServerFtp() {
		return directoryRemotaServerFtp;
	}

	public void setDirectoryRemotaServerFtp(
			java.lang.String directoryRemotaServerFtp) {
		this.directoryRemotaServerFtp = directoryRemotaServerFtp;
	}

	public java.lang.String getFlagAbilitazioneInvioFtp() {
		return flagAbilitazioneInvioFtp;
	}

	public void setFlagAbilitazioneInvioFtp(
			java.lang.String flagAbilitazioneInvioFtp) {
		this.flagAbilitazioneInvioFtp = flagAbilitazioneInvioFtp;
	}

	public int getMaxSizeEmailAttachInKb() {
		return maxSizeEmailAttachInKb;
	}

	public void setMaxSizeEmailAttachInKb(int maxSizeEmailAttachInKb) {
		this.maxSizeEmailAttachInKb = maxSizeEmailAttachInKb;
	}

	public java.util.Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(java.util.Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public java.lang.String getOpertoreUltimoAggiornamento() {
		return opertoreUltimoAggiornamento;
	}

	public void setOpertoreUltimoAggiornamento(
			java.lang.String opertoreUltimoAggiornamento) {
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
	}

	
	//PG110260
	public java.lang.String getTipologiaRendicontazione() {
		return tipologiaRendicontazione;
	}
	//PG110260
	public void setTipologiaRendicontazione(
			java.lang.String tipologiaRendicontazione) {
		this.tipologiaRendicontazione = tipologiaRendicontazione;
	}
	
	public java.lang.String getFlagCarico() {
		return flagCarico;
	}

	public void setFlagCarico(java.lang.String flagCarico) {
		this.flagCarico = flagCarico;
	}

	public java.lang.String getEnte() {
		return ente;
	}

	public void setEnte(java.lang.String ente) {
		this.ente = ente;
	}

	public java.lang.String getImpServizio() {
		return impServizio;
	}

	public void setImpServizio(java.lang.String impServizio) {
		this.impServizio = impServizio;
	}

	public java.lang.String getFlagCodContrib() {
		return flagCodContrib;
	}

	public void setFlagCodContrib(java.lang.String flagCodContrib) {
		this.flagCodContrib = flagCodContrib;
	}
	
	//PG180260 GG 07122018 - inizio
	public java.lang.String getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(java.lang.String codTributo) {
		this.codTributo = codTributo;
	}
	//PG180260 GG 07122018 - fine
	
	public java.lang.String getAbilitazioneInvioWs() {
		return abilitazioneInvioWs;
	}

	public void setAbilitazioneInvioWs(java.lang.String abilitazioneInvioWs) {
		this.abilitazioneInvioWs = abilitazioneInvioWs;
	}

	public java.lang.String getUrlWs() {
		return urlWs;
	}

	public void setUrlWs(java.lang.String urlWs) {
		this.urlWs = urlWs;
	}

	public java.lang.String getUtenteWs() {
		return utenteWs;
	}

	public void setUtenteWs(java.lang.String utenteWs) {
		this.utenteWs = utenteWs;
	}

	public java.lang.String getPasswordWs() {
		return passwordWs;
	}

	public void setPasswordWs(java.lang.String passwordWs) {
		this.passwordWs = passwordWs;
	}	
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	

}
