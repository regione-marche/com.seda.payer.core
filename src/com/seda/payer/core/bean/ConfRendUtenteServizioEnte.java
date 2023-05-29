package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

@SuppressWarnings("serial")
public class ConfRendUtenteServizioEnte extends Lifecycle implements Serializable {
/*
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_CSOCCSOC" IS 'CODICE SOCIETA'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_CUTECUTE" IS 'CODICE UTENTE'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_KANEKENT" IS 'CHIAVE ENTE'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_CTSECTSE" IS 'TIPOLOGIA SERVIZIO'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_EREEEDES" IS 'EMAIL DESTINATARIO'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_EREEECCN" IS 'EMAIL CONOSCENZA NASCOSTA'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_EREEEMIT" IS 'EMAIL MITTENTE'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_DREEDMIT" IS 'DESCRIZIONE MITTENTE'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_FREEMAIL" IS 'FLAG ABILITAZIONE INVIO EMAIL'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_DREESFTP" IS 'SERVER FTP'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_DREEUFTP" IS 'UTENTE FTP'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_DREEPFTP" IS 'PASSWORD FTP'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_DREERDIR" IS 'DIRECTORY REMOTA SERVER FTP'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_FREEAFTP" IS 'FLAG ABILITAZIONE INVIO FTP'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_NREEMAXB" IS 'DIMENSIONE MASSIMA DELL''ALLEGATO EMAIL ESPRESSO IN KB.'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_GREEGAGG" IS 'DATA ULTIMO AGGIORNAMENTO'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_CREECOPE" IS 'OPERTORE ULTIMO AGGIORNAMENTO'! 
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_FREETREN" IS 'FLAG TIPO RENDICONTAZIONE (''Y''= SEDA, ''N''= STANDARD)'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_FREECARI" IS 'FLAG CARICO'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_CREEENTE" IS 'CODICE ENTE'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_CREEIMPS" IS 'CODICE IMPOSTA SERVIZIO'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_FREECONT" IS 'FLAG NUMERO DOCUMENTO = CODICE CONTRIBUENTE'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_CREECTRB" IS 'CODICE TRIBUTO'!
COMMENT ON COLUMN "SE000SV"."PYREETB"."REE_TRAQTRCN" IS 'TRACCIATO SEDA 400'!
 */
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
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
	//inizio LP PG200060
	private String passwordZip;
	private String flagTracciatoComandiPolizia;
	private String tracciatoQuattrocento = "";
	
	public ConfRendUtenteServizioEnte(String codiceSocieta,
			String codiceUtente, String chiaveEnte, String tipologiaServizio,
			String emailDestinatario, String emailConoscenzaNascosta,
			String emailMittente, String descrizioneMittente,
			String flagAbilitazioneInvioEmail, String serverFtp,
			String utenteFtp, String passwordFtp, String dirRemotaServerFtp,
			String flagAbilitazioneInvioFtp, int maxAttachSizeKb,
			Calendar dataUltimoAggiornamento,
			String operatoreUltimoAggiornamento,
			String flagTipoRendicontazione, String passwordZip,String tracciatoQuattrocento) {
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnte = chiaveEnte;
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
		this.formatoFileRend = "";
		this.flagAbilitazioneInvioWebService = "";
		this.urlWebServiceEnte = "";
		this.utenteWebServiceEnte = "";
		this.passwordWebServiceEnte = "";
		this.passwordZip = passwordZip;
		this.flagTracciatoComandiPolizia = "";
		this.tracciatoQuattrocento = tracciatoQuattrocento;
	}
	//fine LP PG200060

    public ConfRendUtenteServizioEnte(String codiceSocieta,
			String codiceUtente, String chiaveEnte, String tipologiaServizio,
			String emailDestinatario, String emailConoscenzaNascosta,
			String emailMittente, String descrizioneMittente,
			String flagAbilitazioneInvioEmail, String serverFtp,
			String utenteFtp, String passwordFtp, String dirRemotaServerFtp,
			String flagAbilitazioneInvioFtp, int maxAttachSizeKb,
			Calendar dataUltimoAggiornamento,
			String operatoreUltimoAggiornamento,
			String flagTipoRendicontazione,	String flagCarico,
			String ente,String impServizio,String flagCodContrib
			,String codTributo	//PG180260 GG 04122018
			,String formatoFileRend, String flagAbilitazioneInvioWebService,//PG180010 
			String urlWebServiceEnte, String utenteWebServiceEnte, String passwordWebServiceEnte //PG180010 
		,String tracciatoQuattrocento) {
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnte = chiaveEnte;
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
		this.codTributo = codTributo;		//PG180260 GG 04122018
	    //PG180010 inizio
		this.formatoFileRend = formatoFileRend;
		this.flagAbilitazioneInvioWebService = flagAbilitazioneInvioWebService;
		this.urlWebServiceEnte = urlWebServiceEnte;
		this.utenteWebServiceEnte = utenteWebServiceEnte;
		this.passwordWebServiceEnte = passwordWebServiceEnte;
	    //PG180010 fine
		//inizio LP PG200060
		this.passwordZip = "";
		//fine LP PG200060
		this.flagTracciatoComandiPolizia = "";
		this.tracciatoQuattrocento = tracciatoQuattrocento;
	}
    
    //PG200280 GG - inizio
    public ConfRendUtenteServizioEnte(String codiceSocieta,
			String codiceUtente, String chiaveEnte, String tipologiaServizio,
			String emailDestinatario, String emailConoscenzaNascosta,
			String emailMittente, String descrizioneMittente,
			String flagAbilitazioneInvioEmail, String serverFtp,
			String utenteFtp, String passwordFtp, String dirRemotaServerFtp,
			String flagAbilitazioneInvioFtp, int maxAttachSizeKb,
			Calendar dataUltimoAggiornamento,
			String operatoreUltimoAggiornamento,
			String flagTipoRendicontazione,	String flagCarico,
			String ente,String impServizio,String flagCodContrib
			,String codTributo	//PG180260 GG 04122018
			,String formatoFileRend, String flagAbilitazioneInvioWebService,//PG180010 
			String urlWebServiceEnte, String utenteWebServiceEnte, String passwordWebServiceEnte//PG180010 
			, String flagTracciatoComandiPolizia,String tracciatoQuattrocento	//PG200280
		) {
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnte = chiaveEnte;
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
		this.codTributo = codTributo;		//PG180260 GG 04122018
	    //PG180010 inizio
		this.formatoFileRend = formatoFileRend;
		this.flagAbilitazioneInvioWebService = flagAbilitazioneInvioWebService;
		this.urlWebServiceEnte = urlWebServiceEnte;
		this.utenteWebServiceEnte = utenteWebServiceEnte;
		this.passwordWebServiceEnte = passwordWebServiceEnte;
	    //PG180010 fine
		//inizio LP PG200060
		this.passwordZip = "";
		//fine LP PG200060
		this.flagTracciatoComandiPolizia = flagTracciatoComandiPolizia;
		this.tracciatoQuattrocento = tracciatoQuattrocento;
	}
    //PG200280 GG - fine

	public ConfRendUtenteServizioEnte() {
	}

	public ConfRendUtenteServizioEnte(ResultSet data) throws SQLException
	{
		if (data == null) return;
		codiceSocieta= data.getString("REE_CSOCCSOC");
		codiceUtente= data.getString("REE_CUTECUTE");
		chiaveEnte= data.getString("REE_KANEKENT");
		tipologiaServizio= data.getString("REE_CTSECTSE");
		emailDestinatario= data.getString("REE_EREEEDES");
		emailConoscenzaNascosta= data.getString("REE_EREEECCN");
		emailMittente= data.getString("REE_EREEEMIT");
		descrizioneMittente= data.getString("REE_DREEDMIT");
		flagAbilitazioneInvioEmail= data.getString("REE_FREEMAIL");
		serverFtp= data.getString("REE_DREESFTP");
		utenteFtp= data.getString("REE_DREEUFTP");
		passwordFtp= data.getString("REE_DREEPFTP");
		dirRemotaServerFtp= data.getString("REE_DREERDIR");
		flagAbilitazioneInvioFtp= data.getString("REE_FREEAFTP");
		maxAttachSizeKb= data.getInt("REE_NREEMAXB");
		dataUltimoAggiornamento= cal(data.getTimestamp("REE_GREEGAGG"));
		operatoreUltimoAggiornamento= data.getString("REE_CREECOPE");
		flagTipoRendicontazione = data.getString("REE_FREETREN"); //PG110260
		flagCarico= data.getString("REE_FREECARI"); 
		ente= (data.getString("REE_CREEENTE")).trim();
		impServizio= (data.getString("REE_CREEIMPS")).trim();
		flagCodContrib= data.getString("REE_FREECONT");
		codTributo= data.getString("REE_CREECTRB");		//PG180260 GG 04122018 
	    //PG180010 inizio
		formatoFileRend = data.getString("REE_CREEFFRE");
		flagAbilitazioneInvioWebService = data.getString("REE_CREEAWBS");
		urlWebServiceEnte = data.getString("REE_CREEUWBS");
		utenteWebServiceEnte = data.getString("REE_CREEUTWS");
		passwordWebServiceEnte = data.getString("REE_CREEPWWS");
		//inizio LP PG200060
		passwordZip = data.getString("REE_DREEPZIP");
		//fine LP PG200060
 		flagTracciatoComandiPolizia= data.getString("REE_FREETRCP");	//PG200280 GG
 		/*
 		if(data.getString("REE_TRAQTRCN") == null || data.getString("REE_TRAQTRCN").equals("")) {
 			tracciatoQuattrocento = "N";
 		}
 		else {
 			tracciatoQuattrocento = "";
 		}
 		*/
 		
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
		arg.setString(3, this.chiaveEnte);
		arg.setString(4, this.tipologiaServizio);
		arg.setString(5, this.emailDestinatario);
		arg.setString(6, this.emailConoscenzaNascosta);
		arg.setString(7, this.emailMittente);
		arg.setString(8, this.descrizioneMittente);
		arg.setString(9, this.flagAbilitazioneInvioEmail);
		arg.setString(10, this.serverFtp);
		arg.setString(11, this.utenteFtp);
		arg.setString(12, this.passwordFtp);
		arg.setString(13, this.dirRemotaServerFtp);
		arg.setString(14, this.flagAbilitazioneInvioFtp);
		arg.setInt(15, this.maxAttachSizeKb);
		arg.setString(16, this.operatoreUltimoAggiornamento);
		arg.setString(17, this.flagTipoRendicontazione); //PG110260
		//inizio LP PG200060
		/*
		arg.setString(18, this.flagCarico); 
		arg.setString(19, this.ente); 
		arg.setString(20, this.impServizio); 
		arg.setString(21, this.flagCodContrib);
		arg.setString(22, this.codTributo);		//PG180260 GG 04122018
		//PG180010 inizio
		arg.setString(23, this.formatoFileRend);
		arg.setString(24, this.flagAbilitazioneInvioWebService);
		arg.setString(25, this.urlWebServiceEnte);
		arg.setString(26, this.utenteWebServiceEnte);
		arg.setString(27, this.passwordWebServiceEnte);
		//PG180010 fine
		*/
		arg.setString(18, this.flagCarico != null ? this.flagCarico : ""); 
		arg.setString(19, this.ente != null ? this.ente : ""); 
		arg.setString(20, this.impServizio != null ? this.impServizio : "");
		arg.setString(21, this.flagCodContrib != null ? this.flagCodContrib : "");
		arg.setString(22, this.codTributo != null ? this.codTributo : "");
		arg.setString(23, this.formatoFileRend != null ? this.formatoFileRend : "TXT");
		arg.setString(24, this.flagAbilitazioneInvioWebService != null ? this.flagAbilitazioneInvioWebService : "");
		arg.setString(25, this.urlWebServiceEnte != null ? this.urlWebServiceEnte : "");
		arg.setString(26, this.utenteWebServiceEnte != null ? this.utenteWebServiceEnte : "");
		arg.setString(27, this.passwordWebServiceEnte != null ? this.passwordWebServiceEnte : "");
		arg.setString(28, this.passwordZip); 
		//fine LP PG200060
		arg.setString(29, this.flagTracciatoComandiPolizia != null ? this.flagTracciatoComandiPolizia : ""); 	//PG200280
		//arg.setString(30,this.tracciatoQuattrocento != null ? this.tracciatoQuattrocento : "");
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.chiaveEnte);
		arg.setString(4, this.tipologiaServizio);
		arg.setString(5, this.emailDestinatario);
		arg.setString(6, this.emailConoscenzaNascosta);
		arg.setString(7, this.emailMittente);
		arg.setString(8, this.descrizioneMittente);
		arg.setString(9, this.flagAbilitazioneInvioEmail);
		arg.setString(10, this.serverFtp);
		arg.setString(11, this.utenteFtp);
		arg.setString(12, this.passwordFtp);
		arg.setString(13, this.dirRemotaServerFtp);
		arg.setString(14, this.flagAbilitazioneInvioFtp);
		arg.setInt(15, this.maxAttachSizeKb);
		arg.setString(16, this.operatoreUltimoAggiornamento);
		arg.setString(17, this.flagTipoRendicontazione); //PG110260
		//inizio LP PG200060
		/*
		arg.setString(18, this.flagCarico); 
		arg.setString(19, this.ente); 
		arg.setString(20, this.impServizio); 
		arg.setString(21, this.flagCodContrib);
		arg.setString(22, this.codTributo);		//PG180260 GG 04122018
		//PG180010 inizio
		arg.setString(23, this.formatoFileRend);
		arg.setString(24, this.flagAbilitazioneInvioWebService);
		arg.setString(25, this.urlWebServiceEnte);
		arg.setString(26, this.utenteWebServiceEnte);
		arg.setString(27, this.passwordWebServiceEnte);
		//PG180010 fine
		*/
		arg.setString(18, this.flagCarico != null ? this.flagCarico : ""); 
		arg.setString(19, this.ente != null ? this.ente : ""); 
		arg.setString(20, this.impServizio != null ? this.impServizio : "");
		arg.setString(21, this.flagCodContrib != null ? this.flagCodContrib : "");
		arg.setString(22, this.codTributo != null ? this.codTributo : "");
		arg.setString(23, this.formatoFileRend != null ? this.formatoFileRend : "TXT");
		arg.setString(24, this.flagAbilitazioneInvioWebService != null ? this.flagAbilitazioneInvioWebService : "");
		arg.setString(25, this.urlWebServiceEnte != null ? this.urlWebServiceEnte : "");
		arg.setString(26, this.utenteWebServiceEnte != null ? this.utenteWebServiceEnte : "");
		arg.setString(27, this.passwordWebServiceEnte != null ? this.passwordWebServiceEnte : "");
		arg.setString(28, this.passwordZip); 
		//fine LP PG200060
		arg.setString(29, this.flagTracciatoComandiPolizia != null ? this.flagTracciatoComandiPolizia : "");
		//arg.setString(30,this.tracciatoQuattrocento != null ? this.tracciatoQuattrocento : "");
	}

	
	public String gettracciatoQuattrocento() {
		return this.tracciatoQuattrocento;
	}

	public void settracciatoQuattrocento(String tracciatoQuattrocento) {
		this.tracciatoQuattrocento = tracciatoQuattrocento;
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

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
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

	//inizio LP PG200060
	public String getPasswordZip() {
		return passwordZip;
	}

	public void setPasswordZip(String passwordZip) {
		this.passwordZip = passwordZip;
	}
	//fine LP PG200060
	
	//PG200280 GG - inizio
	public String getFlagTracciatoComandiPolizia() {
		return flagTracciatoComandiPolizia;
	}

	public void setFlagTracciatoComandiPolizia(String flagTracciatoComandiPolizia) {
		this.flagTracciatoComandiPolizia = flagTracciatoComandiPolizia;
	}
	//PG200280 GG fine

	private Calendar cal(Timestamp t)
    {
    	if(t == null) return null;
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(t.getTime() + (t.getNanos() / 1000000));
		return c;
    }

}
