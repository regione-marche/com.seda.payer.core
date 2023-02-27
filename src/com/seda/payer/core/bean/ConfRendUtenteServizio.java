package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

@SuppressWarnings("serial")
public class ConfRendUtenteServizio extends Lifecycle implements Serializable {
/*
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_CSOCCSOC" IS 'CODICE SOCIETA'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_CUTECUTE" IS 'CODICE UTENTE'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_CTSECTSE" IS 'TIPOLOGIA SERVIZIO'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_ERESEDES" IS 'EMAIL DESTINATARIO'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_ERESECCN" IS 'EMAIL CONOSCENZA NASCOSTA'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_ERESEMIT" IS 'EMAIL MITTENTE'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_DRESDMIT" IS 'DESCRIZIONE MITTENTE'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_FRESMAIL" IS 'FLAG ABILITAZIONE INVIO EMAIL'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_DRESSFTP" IS 'SERVER FTP'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_DRESUFTP" IS 'UTENTE FTP'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_DRESPFTP" IS 'PASSWORD FTP'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_DRESRDIR" IS 'DIRECTORY REMOTA SERVER FTP'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_FRESAFTP" IS 'FLAG ABILITAZIONE INVIO FTP'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_NRESMAXB" IS 'DIMENSIONE MASSIMA DELL''ALLEGATO EMAIL ESPRESSO IN KB.'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_GRESGAGG" IS 'DATA ULTIMO AGGIORNAMENTO'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_CRESCOPE" IS 'OPERTORE ULTIMO AGGIORNAMENTO'! 
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_FREETREN" IS 'FLAG TIPO RENDICONTAZIONE (''Y''= SEDA, ''N''= STANDARD)'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_FRESCARI" IS 'FLAG CARICO'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_CRESENTE" IS 'CODICE ENTE'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_CRESIMPS" IS 'CODICE IMPOSTA SERVIZIO'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_FRESCONT" IS 'FLAG NUMERO DOCUMENTO = CODICE CONTRIBUENTE'!
COMMENT ON COLUMN "SE000SV"."PYRESTB"."RES_CRESCTRB" IS 'CODICE TRIBUTO'!
 */
	private String codiceSocieta;
	private String codiceUtente;
	private String tipologiaServizio;
	private String emailDestinatario;
	private String emailConoscenzaNascosta;
	private String emailMittente;
	private String descrizioneMittente;
	private String flagAbilitazioneInvioEmail;
	private String serverFtp;
	private String utenteFtp;
	private String passwordFtp;
	private String dirRemotaServerFtp;
	private String flagAbilitazioneInvioFtp;
	private int maxAttachSizeKb;
	private Calendar dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	private String flagTipoRendicontazione; //PG110260
	private String flagCarico;
	private String ente;
	private String impServizio;
	private String flagCodContrib;
	private String codTributo;	//PG180260_001 GG 04122018
    //PG180010 inizio
	private String formatoFileRend;
    private String flagAbilitazioneInvioWebService;
    private String urlWebServiceEnte;
    private String utenteWebServiceEnte;
    private String passwordWebServiceEnte;
    //PG180010 fine
    private String flagTracciatoComandiPolizia;	//PG200280

    //inizio LP PG200060
	public ConfRendUtenteServizio(String codiceSocieta,
			String codiceUtente, String tipologiaServizio,
			String emailDestinatario, String emailConoscenzaNascosta,
			String emailMittente, String descrizioneMittente,
			String flagAbilitazioneInvioEmail, String serverFtp,
			String utenteFtp, String passwordFtp, String dirRemotaServerFtp,
			String flagAbilitazioneInvioFtp, int maxAttachSizeKb,
			Calendar dataUltimoAggiornamento,
			String operatoreUltimoAggiornamento, String flagTipoRendicontazione) {
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.tipologiaServizio = tipologiaServizio;
		this.emailDestinatario = emailDestinatario;
		this.emailConoscenzaNascosta = emailConoscenzaNascosta;
		this.emailMittente = emailMittente;
		this.descrizioneMittente = descrizioneMittente;
		this.flagAbilitazioneInvioEmail = flagAbilitazioneInvioEmail;
		this.serverFtp = serverFtp;
		this.utenteFtp = utenteFtp;
		this.passwordFtp = passwordFtp;
		this.dirRemotaServerFtp = dirRemotaServerFtp;
		this.flagAbilitazioneInvioFtp = flagAbilitazioneInvioFtp;
		this.maxAttachSizeKb = maxAttachSizeKb;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
		this.flagTipoRendicontazione = flagTipoRendicontazione; //PG110260
		this.flagCarico = ""; 
		this.ente = ""; 
		this.impServizio = ""; 
		this.flagCodContrib = "";
		this.codTributo = ""; 
		this.formatoFileRend = "TXT";
		this.flagAbilitazioneInvioWebService = "";
		this.urlWebServiceEnte = "";
		this.utenteWebServiceEnte = "";
		this.passwordWebServiceEnte = "";
		this.flagTracciatoComandiPolizia = ""; 
	}
    //fine LP PG200060
    
    public ConfRendUtenteServizio(String codiceSocieta,
			String codiceUtente, String tipologiaServizio,
			String emailDestinatario, String emailConoscenzaNascosta,
			String emailMittente, String descrizioneMittente,
			String flagAbilitazioneInvioEmail, String serverFtp,
			String utenteFtp, String passwordFtp, String dirRemotaServerFtp,
			String flagAbilitazioneInvioFtp, int maxAttachSizeKb,
			Calendar dataUltimoAggiornamento,
			String operatoreUltimoAggiornamento, String flagTipoRendicontazione,	
			String flagCarico, String ente,
			String impServizio,String flagCodContrib
			,String codTributo	//PG180260_001 GG 04122018
			,String formatoFileRend, String flagAbilitazioneInvioWebService,//PG180010 
			String urlWebServiceEnte, String utenteWebServiceEnte, String passwordWebServiceEnte//PG180010 
			) {
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.tipologiaServizio = tipologiaServizio;
		this.emailDestinatario = emailDestinatario;
		this.emailConoscenzaNascosta = emailConoscenzaNascosta;
		this.emailMittente = emailMittente;
		this.descrizioneMittente = descrizioneMittente;
		this.flagAbilitazioneInvioEmail = flagAbilitazioneInvioEmail;
		this.serverFtp = serverFtp;
		this.utenteFtp = utenteFtp;
		this.passwordFtp = passwordFtp;
		this.dirRemotaServerFtp = dirRemotaServerFtp;
		this.flagAbilitazioneInvioFtp = flagAbilitazioneInvioFtp;
		this.maxAttachSizeKb = maxAttachSizeKb;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
		this.flagTipoRendicontazione = flagTipoRendicontazione; //PG110260
		this.flagCarico= flagCarico; 
		this.ente= ente; 
		this.impServizio= impServizio; 
		this.flagCodContrib= flagCodContrib;
		this.codTributo= codTributo;	//PG180260_001 GG 04122018 
	    //PG180010 inizio
		this.formatoFileRend = formatoFileRend;
		this.flagAbilitazioneInvioWebService = flagAbilitazioneInvioWebService;
		this.urlWebServiceEnte = urlWebServiceEnte;
		this.utenteWebServiceEnte = utenteWebServiceEnte;
		this.passwordWebServiceEnte = passwordWebServiceEnte;
	    //PG180010 fine
		this.flagTracciatoComandiPolizia = "";
	}
    
    //PG200280 GG - inizio
    public ConfRendUtenteServizio(String codiceSocieta,
			String codiceUtente, String tipologiaServizio,
			String emailDestinatario, String emailConoscenzaNascosta,
			String emailMittente, String descrizioneMittente,
			String flagAbilitazioneInvioEmail, String serverFtp,
			String utenteFtp, String passwordFtp, String dirRemotaServerFtp,
			String flagAbilitazioneInvioFtp, int maxAttachSizeKb,
			Calendar dataUltimoAggiornamento,
			String operatoreUltimoAggiornamento, String flagTipoRendicontazione,	
			String flagCarico, String ente,
			String impServizio,String flagCodContrib
			,String codTributo	//PG180260_001 GG 04122018
			,String formatoFileRend, String flagAbilitazioneInvioWebService,//PG180010 
			String urlWebServiceEnte, String utenteWebServiceEnte, String passwordWebServiceEnte//PG180010 
			, String flagTracciatoComandiPolizia	//PG200280
			) {
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.tipologiaServizio = tipologiaServizio;
		this.emailDestinatario = emailDestinatario;
		this.emailConoscenzaNascosta = emailConoscenzaNascosta;
		this.emailMittente = emailMittente;
		this.descrizioneMittente = descrizioneMittente;
		this.flagAbilitazioneInvioEmail = flagAbilitazioneInvioEmail;
		this.serverFtp = serverFtp;
		this.utenteFtp = utenteFtp;
		this.passwordFtp = passwordFtp;
		this.dirRemotaServerFtp = dirRemotaServerFtp;
		this.flagAbilitazioneInvioFtp = flagAbilitazioneInvioFtp;
		this.maxAttachSizeKb = maxAttachSizeKb;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
		this.flagTipoRendicontazione = flagTipoRendicontazione; //PG110260
		this.flagCarico= flagCarico; 
		this.ente= ente; 
		this.impServizio= impServizio; 
		this.flagCodContrib= flagCodContrib;
		this.codTributo= codTributo;	//PG180260_001 GG 04122018 
	    //PG180010 inizio
		this.formatoFileRend = formatoFileRend;
		this.flagAbilitazioneInvioWebService = flagAbilitazioneInvioWebService;
		this.urlWebServiceEnte = urlWebServiceEnte;
		this.utenteWebServiceEnte = utenteWebServiceEnte;
		this.passwordWebServiceEnte = passwordWebServiceEnte;
	    //PG180010 fine
		this.flagTracciatoComandiPolizia = flagTracciatoComandiPolizia;	//PG200280
	}
    //PG200280 GG - fine

	public ConfRendUtenteServizio() {
	}

	public ConfRendUtenteServizio(ResultSet data) throws SQLException
	{
		if (data == null) return;
		codiceSocieta= data.getString("RES_CSOCCSOC");
		codiceUtente= data.getString("RES_CUTECUTE");
		tipologiaServizio= data.getString("RES_CTSECTSE");
		emailDestinatario= data.getString("RES_ERESEDES");
		emailConoscenzaNascosta= data.getString("RES_ERESECCN");
		emailMittente= data.getString("RES_ERESEMIT");
		descrizioneMittente= data.getString("RES_DRESDMIT");
		flagAbilitazioneInvioEmail= data.getString("RES_FRESMAIL");
		serverFtp= data.getString("RES_DRESSFTP");
		utenteFtp= data.getString("RES_DRESUFTP");
		passwordFtp= data.getString("RES_DRESPFTP");
		dirRemotaServerFtp= data.getString("RES_DRESRDIR");
		flagAbilitazioneInvioFtp= data.getString("RES_FRESAFTP");
		maxAttachSizeKb= data.getInt("RES_NRESMAXB");
		dataUltimoAggiornamento= cal(data.getTimestamp("RES_GRESGAGG"));
		operatoreUltimoAggiornamento= data.getString("RES_CRESCOPE");
		flagTipoRendicontazione = data.getString("RES_FRESTREN"); //PG110260
		flagCarico= data.getString("RES_FRESCARI"); 
		ente= (data.getString("RES_CRESENTE")).trim();
		impServizio= (data.getString("RES_CRESIMPS")).trim();
		flagCodContrib= data.getString("RES_FRESCONT");
		codTributo= data.getString("RES_CRESCTRB");		//PG180260 GG 04122018 
		formatoFileRend = data.getString("RES_CRESFFRE");
		flagAbilitazioneInvioWebService = data.getString("RES_CRESAWBS");
		urlWebServiceEnte = data.getString("RES_CRESUWBS");
		utenteWebServiceEnte = data.getString("RES_CRESUTWS");
		passwordWebServiceEnte = data.getString("RES_CRESPWWS");
		flagTracciatoComandiPolizia = data.getString("RES_FRESTRCP");	//PG200280
	}

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.tipologiaServizio);
		arg.setString(4, this.emailDestinatario);
		arg.setString(5, this.emailConoscenzaNascosta);
		arg.setString(6, this.emailMittente);
		arg.setString(7, this.descrizioneMittente);
		arg.setString(8, this.flagAbilitazioneInvioEmail);
		arg.setString(9, this.serverFtp);
		arg.setString(10, this.utenteFtp);
		arg.setString(11, this.passwordFtp);
		arg.setString(12, this.dirRemotaServerFtp);
		arg.setString(13, this.flagAbilitazioneInvioFtp);
		arg.setInt(14, this.maxAttachSizeKb);
		arg.setString(15, this.operatoreUltimoAggiornamento);
		arg.setString(16, this.flagTipoRendicontazione); //PG110260
		//inizio LP PG200060
		/*
		arg.setString(17, this.flagCarico); 
		arg.setString(18, this.ente); 
		arg.setString(19, this.impServizio); 
		arg.setString(20, this.flagCodContrib);
		arg.setString(21, this.codTributo);		//PG180260 GG 04122018
		//PG180010 inizio
		arg.setString(22, this.formatoFileRend);
		arg.setString(23, this.flagAbilitazioneInvioWebService);
		arg.setString(24, this.urlWebServiceEnte);
		arg.setString(25, this.utenteWebServiceEnte);
		arg.setString(26, this.passwordWebServiceEnte);
		//PG180010 fine
		*/
		arg.setString(17, this.flagCarico != null ? this.flagCarico : ""); 
		arg.setString(18, this.ente != null ? this.ente : ""); 
		arg.setString(19, this.impServizio != null ? this.impServizio : "");
		arg.setString(20, this.flagCodContrib != null ? this.flagCodContrib : "");
		arg.setString(21, this.codTributo != null ? this.codTributo : "");
		arg.setString(22, this.formatoFileRend != null ? this.formatoFileRend : "TXT");
		arg.setString(23, this.flagAbilitazioneInvioWebService != null ? this.flagAbilitazioneInvioWebService : "");
		arg.setString(24, this.urlWebServiceEnte != null ? this.urlWebServiceEnte : "");
		arg.setString(25, this.utenteWebServiceEnte != null ? this.utenteWebServiceEnte : "");
		arg.setString(26, this.passwordWebServiceEnte != null ? this.passwordWebServiceEnte : "");
		//fine LP PG200060
		arg.setString(27, this.flagTracciatoComandiPolizia != null ? this.flagTracciatoComandiPolizia : ""); 	//PG200280
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.tipologiaServizio);
		arg.setString(4, this.emailDestinatario);
		arg.setString(5, this.emailConoscenzaNascosta);
		arg.setString(6, this.emailMittente);
		arg.setString(7, this.descrizioneMittente);
		arg.setString(8, this.flagAbilitazioneInvioEmail);
		arg.setString(9, this.serverFtp);
		arg.setString(10, this.utenteFtp);
		arg.setString(11, this.passwordFtp);
		arg.setString(12, this.dirRemotaServerFtp);
		arg.setString(13, this.flagAbilitazioneInvioFtp);
		arg.setInt(14, this.maxAttachSizeKb);
		arg.setString(15, this.operatoreUltimoAggiornamento);
		arg.setString(16, this.flagTipoRendicontazione); //PG110260
		//inizio LP PG200060
		/*
		arg.setString(17, this.flagCarico); 
		arg.setString(18, this.ente); 
		arg.setString(19, this.impServizio); 
		arg.setString(20, this.flagCodContrib);
		arg.setString(21, this.codTributo);		//PG180260 GG 04122018
		//PG180010 inizio
		arg.setString(22, this.formatoFileRend);
		arg.setString(23, this.flagAbilitazioneInvioWebService);
		arg.setString(24, this.urlWebServiceEnte);
		arg.setString(25, this.utenteWebServiceEnte);
		arg.setString(26, this.passwordWebServiceEnte);
		//PG180010 fine
		*/
		arg.setString(17, this.flagCarico != null ? this.flagCarico : ""); 
		arg.setString(18, this.ente != null ? this.ente : ""); 
		arg.setString(19, this.impServizio != null ? this.impServizio : "");
		arg.setString(20, this.flagCodContrib != null ? this.flagCodContrib : "");
		arg.setString(21, this.codTributo != null ? this.codTributo : "");
		arg.setString(22, this.formatoFileRend != null ? this.formatoFileRend : "TXT");
		arg.setString(23, this.flagAbilitazioneInvioWebService != null ? this.flagAbilitazioneInvioWebService : "");
		arg.setString(24, this.urlWebServiceEnte != null ? this.urlWebServiceEnte : "");
		arg.setString(25, this.utenteWebServiceEnte != null ? this.utenteWebServiceEnte : "");
		arg.setString(26, this.passwordWebServiceEnte != null ? this.passwordWebServiceEnte : "");
		//fine LP PG200060
		arg.setString(27, this.flagTracciatoComandiPolizia != null ? this.flagTracciatoComandiPolizia : "");
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

	public String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public String getEmailConoscenzaNascosta() {
		return emailConoscenzaNascosta;
	}

	public void setEmailConoscenzaNascosta(String emailConoscenzaNascosta) {
		this.emailConoscenzaNascosta = emailConoscenzaNascosta;
	}

	public String getEmailMittente() {
		return emailMittente;
	}

	public void setEmailMittente(String emailMittente) {
		this.emailMittente = emailMittente;
	}

	public String getDescrizioneMittente() {
		return descrizioneMittente;
	}

	public void setDescrizioneMittente(String descrizioneMittente) {
		this.descrizioneMittente = descrizioneMittente;
	}

	public String getFlagAbilitazioneInvioEmail() {
		return flagAbilitazioneInvioEmail;
	}

	public void setFlagAbilitazioneInvioEmail(String flagAbilitazioneInvioEmail) {
		this.flagAbilitazioneInvioEmail = flagAbilitazioneInvioEmail;
	}

	public String getServerFtp() {
		return serverFtp;
	}

	public void setServerFtp(String serverFtp) {
		this.serverFtp = serverFtp;
	}

	public String getUtenteFtp() {
		return utenteFtp;
	}

	public void setUtenteFtp(String utenteFtp) {
		this.utenteFtp = utenteFtp;
	}

	public String getPasswordFtp() {
		return passwordFtp;
	}

	public void setPasswordFtp(String passwordFtp) {
		this.passwordFtp = passwordFtp;
	}

	public String getDirRemotaServerFtp() {
		return dirRemotaServerFtp;
	}

	public void setDirRemotaServerFtp(String dirRemotaServerFtp) {
		this.dirRemotaServerFtp = dirRemotaServerFtp;
	}

	public String getFlagAbilitazioneInvioFtp() {
		return flagAbilitazioneInvioFtp;
	}

	public void setFlagAbilitazioneInvioFtp(String flagAbilitazioneInvioFtp) {
		this.flagAbilitazioneInvioFtp = flagAbilitazioneInvioFtp;
	}

	public int getMaxAttachSizeKb() {
		return maxAttachSizeKb;
	}

	public void setMaxAttachSizeKb(int maxAttachSizeKb) {
		this.maxAttachSizeKb = maxAttachSizeKb;
	}

	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}
	
	public String getFlagTipoRendicontazione() { //PG110260
		return flagTipoRendicontazione;
	}

	public void setFlagTipoRendicontazione(String flagTipoRendicontazione) { //PG110260
		this.flagTipoRendicontazione = flagTipoRendicontazione;
	}
	
	public String getFlagCarico() {
		return flagCarico;
	}

	public void setFlagCarico(String flagCarico) {
		this.flagCarico = flagCarico;
	}

	public String getEnte() {
		return ente;
	}

	public void setEnte(String ente) {
		this.ente = ente;
	}

	public String getImpServizio() {
		return impServizio;
	}

	public void setImpServizio(String impServizio) {
		this.impServizio = impServizio;
	}

	public String getFlagCodContrib() {
		return flagCodContrib;
	}

	public void setFlagCodContrib(String flagCodContrib) {
		this.flagCodContrib = flagCodContrib;
	}
	
    public String getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(String codTributo) {
		this.codTributo = codTributo;
	}
	//PG180010 - inizio
	public String getFormatoFileRend() {
		return formatoFileRend;
	}

	public void setFormatoFileRend(String formatoFileRend) {
		this.formatoFileRend = formatoFileRend;
	}

	public String getFlagAbilitazioneInvioWebService() {
		return flagAbilitazioneInvioWebService;
	}

	public void setFlagAbilitazioneInvioWebService(
			String flagAbilitazioneInvioWebService) {
		this.flagAbilitazioneInvioWebService = flagAbilitazioneInvioWebService;
	}

	public String getUrlWebServiceEnte() {
		return urlWebServiceEnte;
	}

	public void setUrlWebServiceEnte(String urlWebServiceEnte) {
		this.urlWebServiceEnte = urlWebServiceEnte;
	}

	public String getUtenteWebServiceEnte() {
		return utenteWebServiceEnte;
	}

	public void setUtenteWebServiceEnte(String utenteWebServiceEnte) {
		this.utenteWebServiceEnte = utenteWebServiceEnte;
	}

	public String getPasswordWebServiceEnte() {
		return passwordWebServiceEnte;
	}

	public void setPasswordWebServiceEnte(String passwordWebServiceEnte) {
		this.passwordWebServiceEnte = passwordWebServiceEnte;
	}
	//PG180010 - fine
	
	//PG200280 GG - inzio
	public String getFlagTracciatoComandiPolizia() {
		return flagTracciatoComandiPolizia;
	}

	public void setFlagTracciatoComandiPolizia(String flagTracciatoComandiPolizia) {
		this.flagTracciatoComandiPolizia = flagTracciatoComandiPolizia;
	}
	//PG200280 GG - fine
	
	private Calendar cal(Timestamp t)
    {
    	if(t == null) return null;
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(t.getTime() + (t.getNanos() / 1000000));
		return c;
    }
}
