package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class PyUser implements Serializable{
	//USR_KUSRKUSR BIGINT 'CHIAVE UTENTE CALCOLATA'
	private Long chiaveUtente = null;
	//USR_CUSRUSER VARCHAR(50) 'USERNAME PER AUTENTICAZIONE'!
	private String userName = null;
	//USR_CPRFPROF VARCHAR(10) 'PROFILO UTENTE'!
	private String userProfile = null;
	//USR_FUSRFATT CHAR(1) 'FLAG ATTIVAZIONE'!
	private String flagAttivazione = null;
	//USR_CSOCCSOC CHAR(5) 'ATTRIBUTO CODICE SOCIETA'!
	private String codiceSocieta = null;
	//USR_CUTECUTE CHAR(5) 'ATTRIBUTO CODICE UTENTE'!
	private String codiceUtente = null;
	//USR_KANEKENT_CON CHAR(10) 'ATTRIBUTO CHIAVE ENTE/CONSORZIO'!
	private String chiaveEnteConsorzio = null;
	//USR_KANEKENT_ENT CHAR(10) 'ATTRIBUTO CHIAVE ENTE CONSORZIATO'!
	private String chiaveEnteConsorziato = null;
	//USR_FUSRRDWN CHAR(1) 'FLAG ABILITAZIONE DOWNLOAD HTTP FILE RENDICONTAZIONE (Y/N)'!
	private String downloadFlussiRendicontazione = null;
	//USR_FUSRRFTP CHAR(1) 'FLAG ABILITAZIONE INVIO FTP FILE RENDICONTAZIONE (Y/N)'!
	private String invioFlussiRendicontazioneViaFtp = null;
	//USR_FUSRRMAI CHAR(1) 'FLAG ABILITAZIONE INVIO MAIL FILE RENDICONTAZIONE (Y/N)'!
	private String invioFlussiRendicontazioneViaEmail = null;
	//USR_FUSRTROK CHAR(1) 'FLAG ABILITAZIONE AZIONI PER TRANSAZIONI COMPLETATE CON SUCCESSO'!
	private String azioniPerTransazioniOK = null;
	//USR_FUSRTRKO CHAR(1) 'FLAG ABILITAZIONE AZIONI PER TRANSAZIONI SOSPESE/FALLITE'!
	private String azioniPerTransazioniKO= null;
	//USR_FUSRRICO CHAR(1) 'FLAG ABILITAZIONE AZIONI (QUADRATURA MAN. E CHIUSURA), DOWNLOAD FLUSSO CBI E SEZIONE RICONCILIAZIONE MANUALE.'!
	private String azioniPerRiconciliazioneManuale = null;
	//USR_FUSRAECM CHAR(1) 'FLAG ATTIVAZIONE ESTRATTO CONTO MANAGER (Y/N)'!
	private String attivazioneEstrattoContoManager = null;
	//USR_FUSRRIVE CHAR(1) 'FLAG ABILITAZIONE PROFILO RIVERSAMENTO ( Y = COMPLETO, N = RIVERSAMENTI ESEGUITI ED IN VISUALIZZAZIONE ).'!
	private String abilitazioneProfiloRiversamento = null;

//dom
	//USR_FUSRAECM CHAR(1) 'FLAG ABILITAZIONE INVIO MAIL MOD 21 CONTO DI GESTIONE (Y/N)'!
	private String mailContoGestione = null;

	//USR_FUSRAMTP CHAR(1) 'FLAG ABILITAZIONE PER L'ASSOCIAZIONE PROVVISORIA DI FLUSSI/TRANSAZIONI A MOVIMENTI DI CASSA IN RICONCILIAZIONE MOV TESORERIA'
	private String associazioniProvvisorieRiconciliazionemt = null;
	//USR_FUSRAMTD CHAR(1) 'FLAG ABILITAZIONE PER L'ASSOCIAZIONE DEFINITIVA DI FLUSSI/TRANSAZIONI A MOVIMENTI DI CASSA IN RICONCILIAZIONE MOV TESORERIA'
	private String associazioniDefinitiveRiconciliazionemt = null;
	//USR_FUSRMUTE CHAR(1) 'FLAG ABILITAZIONE GESTIONE MULTI UTENTE PER PROFILI APPLICATIVI DI TIPO "AMEN" ( Y = MULTI UTENTE, N = SINGOLO UTENTE ).'!
	private String abilitazioneMultiUtente = null;
	//USR_GUSRGAGG TIMESTAMP 'DATA ULTIMO AGGIORNAMENTO'!
	private Timestamp dataUltimoAggiornamento = null;
	//USR_CUSRCOPE VARCHAR(50) 'OPERATORE ULTIMO AGGIORNAMENTO'!
	private String operatoreUltimoAggiornamento = null;
	
	
	//descrizioni
	private String descrSocieta;
	private String descrUtente;
	private String descrEnte;
	
	//USR_CUSRLTSE VARCHAR(200) 'LISTA TIPOLOGIE SERVIZIO ABILITATE PER USER AMEN'!
	private String listaTipologieServizio = null;
	
	//EP160510_001 GG 03112016 - inizio
	//USR_CUSRCENP CHAR(5) 'CODICE ENTE DI PERTINENZA EC MANAGER'
	private String entePertinenza = null;
	//EP160510_001 GG 03112016 - fine
	
	
	//RE180181_001 SB - inizio
	//USR_CUSRCGRU VARCHAR(3) 'CODICE GRUPPO AGENZIA'
	private String gruppoAgenzia = null;
	
	//USR_CUSRMAIL VARCHAR(100) 'INDIRIZZO MAIL'
	private String mail = null;
	
	//USR_CUSRmpec VARCHAR(100) 'INDIRIZZO MAIL PEC'
	private String mailPec = null;
	
	//USR_CUSRMPIN VARCHAR(8) 'PINCODE MAIL'
	private String pinCodeMail = null;
	
	//USR_CUSRPPIN VARCHAR(8) 'PINCODE MAIL PEC'
	private String pinCodePec = null;
	
	//USR_FUSRVMPN CHAR(1) 'FLAG VALIDAZIONE PIN CODE MAIL ("Y"=PIN CODE VALIDATO, "N"=PIN CODE NON VALIDATO)'
	private String flagValidazioneMail = null;
	
	//USR_FUSRVPPN CHAR(1) 'FLAG PER CONFERMARE LA VALIDAZIONE DELLA EMAIL PEC'
	private String flagValidazionePec = null;
	
	//RE180181_001 SB - fine
	//USR_FUSRRMAI CHAR(1) 'FLAG ABILITAZIONE INVIO MAIL FILE RENDICONTAZIONE (Y/N)'!
	private String invioFlussiRendicontazioneViaWs = null;

	// USR_FUSRPRFT CHAR(1) FLAG ABILITAZIONE DATI FATTURAZIONE (Y/N)'
	private String flagPrenotazioneFatturazione = null;

	public PyUser() {
		super();
	}
	
	//EP160510_001 GG 03112016 - Introdotto entePertinenza
	public PyUser(Long chiaveUtente, String userName, String userProfile, String flagAttivazione,
			String codiceSocieta, String codiceUtente,
			String chiaveEnteConsorzio, String chiaveEnteConsorziato,
			String downloadFlussiRendicontazione,
			String invioFlussiRendicontazioneViaFtp,
			String invioFlussiRendicontazioneViaEmail,
			String azioniPerTransazioniOK, String azioniPerTransazioniKO,
			String azioniPerRiconciliazioneManuale,
			String attivazioneEstrattoContoManager,
			String abilitazioneProfiloRiversamento,
			String associazioniProvvisorieRiconciliazionemt,
			String associazioniDefinitiveRiconciliazionemt,
			String abilitazioneMultiUtente,
			Timestamp dataUltimoAggiornamento, String operatoreUltimoAggiornamento,
			String listaTipologieServizio,
			String mailContoGestione, String entePertinenza,
			String gruppoAgenzia,   //RE180181_001 SB - inizio
			String mail, 
			String mailPec,
			String pinCodeMail, 
			String pinCodePec,
			String flagValidazioneMail,
			String flagValidazionePec,   //RE180181_001 SB - fine
			String invioFlussiRendicontazioneViaWs,
			String flagPrenotazioneFatturazione // SR PGNTCORE-23
			) 
	{
		super();
		this.chiaveUtente = chiaveUtente;
		this.userName = userName;
		this.userProfile = userProfile;
		this.flagAttivazione = flagAttivazione;
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnteConsorzio = chiaveEnteConsorzio;
		this.chiaveEnteConsorziato = chiaveEnteConsorziato;
		this.downloadFlussiRendicontazione = downloadFlussiRendicontazione;
		this.invioFlussiRendicontazioneViaFtp = invioFlussiRendicontazioneViaFtp;
		this.invioFlussiRendicontazioneViaEmail = invioFlussiRendicontazioneViaEmail;
		this.azioniPerTransazioniOK = azioniPerTransazioniOK;
		this.azioniPerTransazioniKO = azioniPerTransazioniKO;
		this.azioniPerRiconciliazioneManuale = azioniPerRiconciliazioneManuale;
		this.attivazioneEstrattoContoManager = attivazioneEstrattoContoManager;
		this.abilitazioneProfiloRiversamento = abilitazioneProfiloRiversamento;
		this.abilitazioneMultiUtente = abilitazioneMultiUtente;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento; 
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
		this.listaTipologieServizio = listaTipologieServizio;
		this.mailContoGestione = mailContoGestione;
		this.entePertinenza = entePertinenza; 	//EP160510_001 GG 03112016
		this.associazioniProvvisorieRiconciliazionemt = associazioniProvvisorieRiconciliazionemt;
		this.associazioniDefinitiveRiconciliazionemt = associazioniDefinitiveRiconciliazionemt;
		//RE180181_001 SB - inizio
		this.gruppoAgenzia = gruppoAgenzia;    
		this.mail = mail;
		this.mailPec = mailPec;
		this.pinCodeMail = pinCodeMail;
		this.pinCodePec = pinCodePec;
		this.flagValidazioneMail = flagValidazioneMail;
		this.flagValidazionePec = flagValidazionePec;
		//RE180181_001 SB - fine
		this.invioFlussiRendicontazioneViaWs = invioFlussiRendicontazioneViaWs;
		this.flagPrenotazioneFatturazione = flagPrenotazioneFatturazione; // SR PGNTCORE-23
 	}

	public PyUser(Long chiaveUtente, String userName, String userProfile, String flagAttivazione,
				  String codiceSocieta, String codiceUtente,
				  String chiaveEnteConsorzio, String chiaveEnteConsorziato,
				  String downloadFlussiRendicontazione,
				  String invioFlussiRendicontazioneViaFtp,
				  String invioFlussiRendicontazioneViaEmail,
				  String azioniPerTransazioniOK, String azioniPerTransazioniKO,
				  String azioniPerRiconciliazioneManuale,
				  String attivazioneEstrattoContoManager,
				  String abilitazioneProfiloRiversamento,
				  String associazioniProvvisorieRiconciliazionemt,
				  String associazioniDefinitiveRiconciliazionemt,
				  String abilitazioneMultiUtente,
				  Timestamp dataUltimoAggiornamento, String operatoreUltimoAggiornamento,
				  String listaTipologieServizio,
				  String mailContoGestione, String entePertinenza,
				  String gruppoAgenzia,   //RE180181_001 SB - inizio
				  String mail,
				  String mailPec,
				  String pinCodeMail,
				  String pinCodePec,
				  String flagValidazioneMail,
				  String flagValidazionePec,   //RE180181_001 SB - fine
				  String invioFlussiRendicontazioneViaWs
	)
	{
		super();
		this.chiaveUtente = chiaveUtente;
		this.userName = userName;
		this.userProfile = userProfile;
		this.flagAttivazione = flagAttivazione;
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnteConsorzio = chiaveEnteConsorzio;
		this.chiaveEnteConsorziato = chiaveEnteConsorziato;
		this.downloadFlussiRendicontazione = downloadFlussiRendicontazione;
		this.invioFlussiRendicontazioneViaFtp = invioFlussiRendicontazioneViaFtp;
		this.invioFlussiRendicontazioneViaEmail = invioFlussiRendicontazioneViaEmail;
		this.azioniPerTransazioniOK = azioniPerTransazioniOK;
		this.azioniPerTransazioniKO = azioniPerTransazioniKO;
		this.azioniPerRiconciliazioneManuale = azioniPerRiconciliazioneManuale;
		this.attivazioneEstrattoContoManager = attivazioneEstrattoContoManager;
		this.abilitazioneProfiloRiversamento = abilitazioneProfiloRiversamento;
		this.abilitazioneMultiUtente = abilitazioneMultiUtente;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
		this.listaTipologieServizio = listaTipologieServizio;
		this.mailContoGestione = mailContoGestione;
		this.entePertinenza = entePertinenza; 	//EP160510_001 GG 03112016
		this.associazioniProvvisorieRiconciliazionemt = associazioniProvvisorieRiconciliazionemt;
		this.associazioniDefinitiveRiconciliazionemt = associazioniDefinitiveRiconciliazionemt;
		//RE180181_001 SB - inizio
		this.gruppoAgenzia = gruppoAgenzia;
		this.mail = mail;
		this.mailPec = mailPec;
		this.pinCodeMail = pinCodeMail;
		this.pinCodePec = pinCodePec;
		this.flagValidazioneMail = flagValidazioneMail;
		this.flagValidazionePec = flagValidazionePec;
		//RE180181_001 SB - fine
		this.invioFlussiRendicontazioneViaWs = invioFlussiRendicontazioneViaWs;
	}

	public static PyUser getBean(ResultSet data, boolean bGetDescr) throws SQLException 
	{
		if (data == null) return null;
		PyUser bean = null;
		
		bean = new PyUser();
		bean.chiaveUtente = data.getLong("USR_KUSRKUSR");
		bean.userName = data.getString("USR_CUSRUSER");
		bean.userProfile = data.getString("USR_CPRFPROF");
		bean.flagAttivazione = data.getString("USR_FUSRFATT");
		bean.codiceSocieta = data.getString("USR_CSOCCSOC");
		bean.codiceUtente = data.getString("USR_CUTECUTE");
		bean.chiaveEnteConsorzio = data.getString("USR_KANEKENT_CON");
		bean.chiaveEnteConsorziato = data.getString("USR_KANEKENT_ENT");
		bean.downloadFlussiRendicontazione = data.getString("USR_FUSRRDWN");
		bean.invioFlussiRendicontazioneViaFtp = data.getString("USR_FUSRRFTP");
		bean.invioFlussiRendicontazioneViaEmail = data.getString("USR_FUSRRMAI");
		bean.azioniPerTransazioniOK = data.getString("USR_FUSRTROK");
		bean.azioniPerTransazioniKO= data.getString("USR_FUSRTRKO");
		bean.azioniPerRiconciliazioneManuale = data.getString("USR_FUSRRICO");
		bean.attivazioneEstrattoContoManager = data.getString("USR_FUSRAECM");
		bean.abilitazioneProfiloRiversamento = data.getString("USR_FUSRRIVE");
		bean.abilitazioneMultiUtente = data.getString("USR_FUSRMUTE");
		bean.dataUltimoAggiornamento = data.getTimestamp("USR_GUSRGAGG");
		bean.operatoreUltimoAggiornamento = data.getString("USR_CUSRCOPE");
		bean.listaTipologieServizio = data.getString("USR_CUSRLTSE");
        bean.associazioniProvvisorieRiconciliazionemt = data.getString("USR_FUSRAMTP");
        bean.associazioniDefinitiveRiconciliazionemt = data.getString("USR_FUSRAMTD");
		bean.mailContoGestione = data.getString("USR_FUSRCMAI");
		
		if (bGetDescr)
		{
			//i seguenti valori non vengono restituiti da tutte le stored
			try {
				bean.descrSocieta = data.getString("SOC_DSOCDSOC");
				bean.descrUtente = data.getString("UTE_DUTEDUTE");
				bean.descrEnte = data.getString("ANE_DANEDENT");
			} catch (Exception e) {
				bean.descrSocieta = "";
				bean.descrUtente = "";
				bean.descrEnte = "";
			}
		}
		bean.entePertinenza = data.getString("USR_CUSRCENP");	//EP160510_001 GG 03112016
		//RE180181_001 SB - inizio
		bean.gruppoAgenzia = data.getString("USR_CUSRCGRU");	
		bean.mail = data.getString("USR_CUSRMAIL");
		bean.mailPec = data.getString("USR_CUSRMPEC");
		bean.pinCodeMail = data.getString("USR_CUSRMPIN");
		bean.pinCodePec = data.getString("USR_CUSRPPIN");
		bean.flagValidazioneMail = data.getString("USR_FUSRVMPN");
		bean.flagValidazionePec = data.getString("USR_FUSRVPPN");
		//RE180181_001 SB - fine
		bean.invioFlussiRendicontazioneViaWs = data.getString("USR_FUSRRWS");
		bean.flagPrenotazioneFatturazione = data.getString("USR_FUSRPRFT");
		return bean;
	}
	
	public void setChiaveUtente(Long chiaveUtente) {
		this.chiaveUtente = chiaveUtente;
	}
	public Long getChiaveUtente() {
		return chiaveUtente;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	public String getFlagAttivazione() {
		return flagAttivazione;
	}
	public void setFlagAttivazione(String flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
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
	public String getChiaveEnteConsorzio() {
		return chiaveEnteConsorzio;
	}
	public void setChiaveEnteConsorzio(String chiaveEnteConsorzio) {
		this.chiaveEnteConsorzio = chiaveEnteConsorzio;
	}
	public String getChiaveEnteConsorziato() {
		return chiaveEnteConsorziato;
	}
	public void setChiaveEnteConsorziato(String chiaveEnteConsorziato) {
		this.chiaveEnteConsorziato = chiaveEnteConsorziato;
	}
	public String getDownloadFlussiRendicontazione() {
		return downloadFlussiRendicontazione;
	}
	public void setDownloadFlussiRendicontazione(
			String downloadFlussiRendicontazione) {
		this.downloadFlussiRendicontazione = downloadFlussiRendicontazione;
	}
	public String getInvioFlussiRendicontazioneViaFtp() {
		return invioFlussiRendicontazioneViaFtp;
	}
	public void setInvioFlussiRendicontazioneViaFtp(
			String invioFlussiRendicontazioneViaFtp) {
		this.invioFlussiRendicontazioneViaFtp = invioFlussiRendicontazioneViaFtp;
	}
	public String getInvioFlussiRendicontazioneViaEmail() {
		return invioFlussiRendicontazioneViaEmail;
	}
	public void setInvioFlussiRendicontazioneViaEmail(
			String invioFlussiRendicontazioneViaEmail) {
		this.invioFlussiRendicontazioneViaEmail = invioFlussiRendicontazioneViaEmail;
	}
	public String getAzioniPerTransazioniOK() {
		return azioniPerTransazioniOK;
	}
	public void setAzioniPerTransazioniOK(String azioniPerTransazioniOK) {
		this.azioniPerTransazioniOK = azioniPerTransazioniOK;
	}
	public String getAzioniPerTransazioniKO() {
		return azioniPerTransazioniKO;
	}
	public void setAzioniPerTransazioniKO(String azioniPerTransazioniKO) {
		this.azioniPerTransazioniKO = azioniPerTransazioniKO;
	}
	public String getAzioniPerRiconciliazioneManuale() {
		return azioniPerRiconciliazioneManuale;
	}
	public void setAzioniPerRiconciliazioneManuale(
			String azioniPerRiconciliazioneManuale) {
		this.azioniPerRiconciliazioneManuale = azioniPerRiconciliazioneManuale;
	}
	public String getAttivazioneEstrattoContoManager() {
		return attivazioneEstrattoContoManager;
	}
	public void setAttivazioneEstrattoContoManager(
			String attivazioneEstrattoContoManager) {
		this.attivazioneEstrattoContoManager = attivazioneEstrattoContoManager;
	}
	public String getAbilitazioneProfiloRiversamento() {
		return abilitazioneProfiloRiversamento;
	}
	public void setAbilitazioneProfiloRiversamento(
			String abilitazioneProfiloRiversamento) {
		this.abilitazioneProfiloRiversamento = abilitazioneProfiloRiversamento;
	}
	public String getAbilitazioneMultiUtente() {
		return abilitazioneMultiUtente;
	}
	public void setAbilitazioneMultiUtente(String abilitazioneMultiUtente) {
		this.abilitazioneMultiUtente = abilitazioneMultiUtente;
	}
	public Timestamp getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}


	public void setDataUltimoAggiornamento(Timestamp dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}


	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}
	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}


	public void setDescrSocieta(String descrSocieta) {
		this.descrSocieta = descrSocieta;
	}


	public String getDescrSocieta() {
		return descrSocieta;
	}


	public void setDescrUtente(String descrUtente) {
		this.descrUtente = descrUtente;
	}


	public String getDescrUtente() {
		return descrUtente;
	}


	public void setDescrEnte(String descrEnte) {
		this.descrEnte = descrEnte;
	}


	public String getDescrEnte() {
		return descrEnte;
	}


	public void setListaTipologieServizio(String listaTipologieServizio) {
		this.listaTipologieServizio = listaTipologieServizio;
	}


	public String getListaTipologieServizio() {
		return listaTipologieServizio;
	}


	public String getMailContoGestione() {
		return mailContoGestione;
	}


	public void setMailContoGestione(String mailContoGestione) {
		this.mailContoGestione = mailContoGestione;
	}
	
	//EP160510_001 GG 03112016 - inizio
	public String getEntePertinenza() {
		return entePertinenza;
	}

	public void setEntePertinenza(String entePertinenza) {
		this.entePertinenza = entePertinenza;
	}
	//EP160510_001 GG 03112016 - fine
	
	//RE180181_001 SB - inizio
	public String getGruppoAgenzia() {
		return gruppoAgenzia;
	}

	public void setGruppoAgenzia(String gruppoAgenzia) {
		this.gruppoAgenzia = gruppoAgenzia;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMailPec() {
		return mailPec;
	}

	public void setMailPec(String mailPec) {
		this.mailPec = mailPec;
	}
	
	public String getPinCodeMail() {
		return pinCodeMail;
	}

	public void setPinCodeMail(String pinCodeMail) {
		this.pinCodeMail = pinCodeMail;
	}
	
	public String getPinCodePec() {
		return pinCodePec;
	}

	public void setPinCodePec (String pinCodePec) {
		this.pinCodePec = pinCodePec;
	}
	
	public String getFlagValidazioneMail() {
		return flagValidazioneMail;
	}

	public void setFlagValidazioneMail(String flagValidazioneMail) {
		this.flagValidazioneMail = flagValidazioneMail;
	}
	
	public String getFlagValidazionePec() {
		return flagValidazionePec;
	}

	public void setFlagValidazionePec(String flagValidazionePec) {
		this.flagValidazionePec = flagValidazionePec;
	}
	//RE180181_001 SB - fine

	public String getAssociazioniProvvisorieRiconciliazionemt() {
		return associazioniProvvisorieRiconciliazionemt;
	}

	public void setAssociazioniProvvisorieRiconciliazionemt(
			String associazioniProvvisorieRiconciliazionemt) {
		this.associazioniProvvisorieRiconciliazionemt = associazioniProvvisorieRiconciliazionemt;
	}

	public String getAssociazioniDefinitiveRiconciliazionemt() {
		return associazioniDefinitiveRiconciliazionemt;
	}

	public void setAssociazioniDefinitiveRiconciliazionemt(
			String associazioniDefinitiveRiconciliazionemt) {
		this.associazioniDefinitiveRiconciliazionemt = associazioniDefinitiveRiconciliazionemt;
	}

	public String getInvioFlussiRendicontazioneViaWs() {
		return invioFlussiRendicontazioneViaWs;
	}

	public void setInvioFlussiRendicontazioneViaWs(String invioFlussiRendicontazioneViaWs) {
		this.invioFlussiRendicontazioneViaWs = invioFlussiRendicontazioneViaWs;
	}

	public String getFlagPrenotazioneFatturazione() {
		return flagPrenotazioneFatturazione;
	}
	public void setFlagPrenotazioneFatturazione(String flagPrenotazioneFatturazione) {
		this.flagPrenotazioneFatturazione = flagPrenotazioneFatturazione;
	}
}