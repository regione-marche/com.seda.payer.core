package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import javax.xml.bind.annotation.XmlRootElement;
import com.seda.payer.commons.bean.Lifecycle;

@XmlRootElement
public class LogRequest extends Lifecycle implements Serializable
{
	private static final long serialVersionUID = 1L;
	//parametri da classe request
	private BigInteger idRequest;
	private String indirizzoIP;
	private String sessionID;
	private String tipoRequest;
	private String applicativo;
	private String action;
	private String queryString;
	private String param;
	private String error;
	private String request;
	private Timestamp dataRequest;
	private String operatoreRequest;
	//parametri da applicazione
	private String template;
	private String dbSchemaCodSocieta;
	private String sezioneApplicativa;
	private String canalePagamento;
	private String userName;
	private String userProfile;
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
	private String descrizioneEnte;
	private String siglaProvinciaEnte;
	//eventuali parametri presenti sulla request
	private String codiceFiscale;
	private String numeroDocumento;
	private String numeroBollettino;
	private String belfioreRequest;
	private String comuneRequest;
	private String siglaProvinciaRequest;
	private String chiaveTransazione;
	private String numeroIUV;
	//parametri per la ricerca
	private String dataInizio_da;
	private String dataFine_a;
	//per selezionare la tabella su cui lavorare
	private String tagSuffissoTabella;

	public BigInteger getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(BigInteger idRequest) {
		this.idRequest = idRequest;
	}

	public String getIndirizzoIP() {
		return indirizzoIP;
	}

	public void setIndirizzoIP(String indirizzoIP) {
		this.indirizzoIP = indirizzoIP;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getTipoRequest() {
		return tipoRequest;
	}

	public void setTipoRequest(String tipoRequest) {
		this.tipoRequest = tipoRequest;
	}

	public String getApplicativo() {
		return applicativo;
	}

	public void setApplicativo(String applicativo) {
		this.applicativo = applicativo;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}


	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Timestamp getDataRequest() {
		return dataRequest;
	}

	public void setDataRequest(Timestamp dataRequest) {
		this.dataRequest = dataRequest;
	}

	public String getOperatoreRequest() {
		return operatoreRequest;
	}

	public void setOperatoreRequest(String operatoreRequest) {
		this.operatoreRequest = operatoreRequest;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getDbSchemaCodSocieta() {
		return dbSchemaCodSocieta;
	}

	public void setDbSchemaCodSocieta(String dbSchemaCodSocieta) {
		this.dbSchemaCodSocieta = dbSchemaCodSocieta;
	}

	public String getSezioneApplicativa() {
		return sezioneApplicativa;
	}

	public void setSezioneApplicativa(String sezioneApplicativa) {
		this.sezioneApplicativa = sezioneApplicativa;
	}

	public String getCanalePagamento() {
		return canalePagamento;
	}

	public void setCanalePagamento(String canalePagamento) {
		this.canalePagamento = canalePagamento;
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

	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	public void setDescrizioneEnte(String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

	public String getSiglaProvinciaEnte() {
		return siglaProvinciaEnte;
	}

	public void setSiglaProvinciaEnte(String siglaProvinciaEnte) {
		this.siglaProvinciaEnte = siglaProvinciaEnte;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroBollettino() {
		return numeroBollettino;
	}

	public void setNumeroBollettino(String numeroBollettino) {
		this.numeroBollettino = numeroBollettino;
	}

	public String getBelfioreRequest() {
		return belfioreRequest;
	}

	public void setBelfioreRequest(String belfioreRequest) {
		this.belfioreRequest = belfioreRequest;
	}

	public String getComuneRequest() {
		return comuneRequest;
	}

	public void setComuneRequest(String comuneRequest) {
		this.comuneRequest = comuneRequest;
	}

	public String getSiglaProvinciaRequest() {
		return siglaProvinciaRequest;
	}

	public void setSiglaProvinciaRequest(String siglaProvinciaRequest) {
		this.siglaProvinciaRequest = siglaProvinciaRequest;
	}

	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public String getNumeroIUV() {
		return numeroIUV;
	}

	public void setNumeroIUV(String numeroIUV) {
		this.numeroIUV = numeroIUV;
	}

	public String getDataInizio_da() {
		return dataInizio_da;
	}

	public void setDataInizio_da(String dataInizio_da) {
		this.dataInizio_da = dataInizio_da;
	}

	public String getDataFine_a() {
		return dataFine_a;
	}

	public void setDataFine_a(String dataFine_a) {
		this.dataFine_a = dataFine_a;
	}

	public String getTagSuffissoTabella() {
		return tagSuffissoTabella;
	}

	public void setTagSuffissoTabella(String tagSuffissoTabella) {
		this.tagSuffissoTabella = tagSuffissoTabella;
	}

	public LogRequest() {
		super();
	}

	/*
	  REQ_IKEYIREQ BIGINT(31) NOT NULL AUTO_INCREMENT COMMENT 'ID REQUEST',
	  REQ_CREQDIIP VARCHAR(50) DEFAULT '' COMMENT 'INDIRIZZO IP',
	  REQ_CREQDSID VARCHAR(40) DEFAULT '' COMMENT 'SESSION ID',
	  REQ_CREQCTIP VARCHAR(4) DEFAULT '' COMMENT 'TIPO REQUEST',
	  REQ_CREQCCHI VARCHAR(50) DEFAULT '' COMMENT 'APPLICATIVO',
	  REQ_CREQCACT VARCHAR(250) DEFAULT '' COMMENT 'ACTION',
	  REQ_CREQDQUE TEXT COMMENT 'QUERY STRING',
	  REQ_CREQDPAR TEXT COMMENT 'PARAM',
	  REQ_CREQDREQ TEXT COMMENT 'REQUEST',
	  REQ_GREQGREQ TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DATA REQUEST',
	  REQ_CREQDOPE VARCHAR(50) DEFAULT '' COMMENT 'OPERATORE REQUEST',
	  REQ_CREQDTPL VARCHAR(50) DEFAULT '' COMMENT 'TEMPLATE',
	  REQ_CREQDDBC VARCHAR(5) DEFAULT '' COMMENT 'DBSCHEMACODSOCIETA',
	  REQ_CREQDSEZ VARCHAR(50) DEFAULT '' COMMENT 'SEZIONE APPLICATIVA',
	  REQ_CREQCCAN VARCHAR(3) DEFAULT '' COMMENT 'CHIAVE CANALE PAGAMENTO',
	  REQ_CREQDUNM VARCHAR(100) DEFAULT '' COMMENT 'USER NAME',
	  REQ_CREQDUPR VARCHAR(10) DEFAULT '' COMMENT 'USER PROFILE',
	  REQ_CREQCSOC VARCHAR(5) DEFAULT '' COMMENT 'CODICE SOCIETA',
	  REQ_CREQCUSR VARCHAR(6) DEFAULT '' COMMENT 'CODICE UTENTE',
	  REQ_CREQKANE VARCHAR(10) DEFAULT '' COMMENT 'CHIAVE ENTE',
	  REQ_CREQDENT VARCHAR(256) DEFAULT '' COMMENT 'DESCRIZIONE ENTE',
	  REQ_CREQDSPR VARCHAR(2) DEFAULT '' COMMENT 'SIGLA PROVINCIA ENTE',
	  REQ_CREQRCFS VARCHAR(16) DEFAULT '' COMMENT 'CODICE FISCALE REQUEST',
	  REQ_CREQRDOC VARCHAR(50) DEFAULT '' COMMENT 'NUMERO DOCUMENTO REQUEST',
	  REQ_CREQRBOL VARCHAR(50) DEFAULT '' COMMENT 'NUMERO BOLLETTINO REQUEST',
	  REQ_CREQRBLF VARCHAR(4) DEFAULT '' COMMENT 'COMUNE REQUEST BELFIORE',
	  REQ_CREQRCOM VARCHAR(100) DEFAULT '' COMMENT 'COMUNE REQUEST',
	  REQ_CREQRPRV VARCHAR(2) DEFAULT '' COMMENT 'PROVINCIA REQUEST',
	  REQ_CREQRTRA VARCHAR(64) DEFAULT '' COMMENT 'TRANSAZIONE  REQUEST',
	  REQ_CREQRIUV VARCHAR(35) DEFAULT '' COMMENT 'IUV REQUEST',
	*/

	/**
	 * 
	 * @param data
	 * @throws SQLException
	 */
	public LogRequest(ResultSet data) throws SQLException {
		if (data == null)
			return;
		this.idRequest = BigInteger.valueOf(data.getInt("REQ_IKEYIREQ"));
		this.indirizzoIP = data.getString("REQ_CREQDIIP");
		this.sessionID =  data.getString("REQ_CREQDSID");
		this.tipoRequest = data.getString("REQ_CREQCTIP");
		this.applicativo = data.getString("REQ_CREQCCHI");
		this.action = data.getString("REQ_CREQCACT");
		this.queryString = data.getString("REQ_CREQDQUE");
		this.error = data.getString("REQ_CREQCERR");
		this.param = data.getString("REQ_CREQDPAR");
		this.request = data.getString("REQ_CREQDREQ");
		this.dataRequest = data.getTimestamp("REQ_GREQGREQ");
		this.operatoreRequest = data.getString("REQ_CREQDOPE");
		this.template = data.getString("REQ_CREQDTPL");
		this.dbSchemaCodSocieta = data.getString("REQ_CREQDDBC");
		this.sezioneApplicativa = data.getString("REQ_CREQDSEZ");
		this.canalePagamento = data.getString("REQ_CREQCCAN");
		this.userName = data.getString("REQ_CREQDUNM");
		this.userProfile = data.getString("REQ_CREQDUPR");
		this.codiceSocieta = data.getString("REQ_CREQCSOC");
		this.codiceUtente = data.getString("REQ_CREQCUSR");
		this.chiaveEnte = data.getString("REQ_CREQKANE");
		this.descrizioneEnte = data.getString("REQ_CREQDENT");
		this.siglaProvinciaEnte = data.getString("REQ_CREQDSPR");
		this.codiceFiscale = data.getString("REQ_CREQRCFS");
		this.numeroDocumento = data.getString("REQ_CREQRDOC");
		this.numeroBollettino = data.getString("REQ_CREQRBOL");
		this.belfioreRequest = data.getString("REQ_CREQRBLF");
		this.comuneRequest = data.getString("REQ_CREQRCOM");
		this.siglaProvinciaRequest = data.getString("REQ_CREQRPRV");
		this.chiaveTransazione = data.getString("REQ_CREQRTRA");
		this.numeroIUV = data.getString("REQ_CREQRIUV");
		try {
			this.tagSuffissoTabella = data.getString("REQ_TGSUFTAB");
		} catch (Exception e) {
			this.tagSuffissoTabella = "";
		}
	}

	public String toString() {
		return "LogRequest ["
				+ "idRequest=" + idRequest
				+ ", indirizzoIP=" + indirizzoIP
				+ ", sessionID=" + sessionID
				+ ", tipoRequest=" + tipoRequest
				+ ", applicativo=" + applicativo
				+ ", action=" + action
				+ ", queryString=" + queryString
				+ ", error=" + error
				+ ", param=" + param
				+ ", request=" + request
				+ ", dataRequest=" + dataRequest
				+ ", operatoreRequest="	+ operatoreRequest
				+ ", template="	+ template
				+ ", dbSchemaCodSocieta=" + dbSchemaCodSocieta
				+ ", sezioneApplicativa=" + sezioneApplicativa
				+ ", canalePagamento=" + canalePagamento
				+ ", userName="	+ userName
				+ ", userProfile=" + userProfile
				+ ", codiceSocieta=" + codiceSocieta
				+ ", codiceUtente=" + codiceUtente
				+ ", chiaveEnte=" + chiaveEnte
				+ ", descrizioneEnte=" + descrizioneEnte
				+ ", siglaProvinciaEnte=" + siglaProvinciaEnte
				+ ", codiceFiscale=" + codiceFiscale
				+ ", numeroDocumento=" + numeroDocumento
				+ ", numeroBollettino=" + numeroBollettino
				+ ", belfioreRequest=" + belfioreRequest
				+ ", comuneRequest=" + comuneRequest
				+ ", siglaProvinciaRequest=" + siglaProvinciaRequest
				+ ", chiaveTransazione=" + chiaveTransazione
				+ ", numeroIUV=" + numeroIUV
				+ ", tagSuffissoTabella=" + tagSuffissoTabella
				+ "]";
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onSave(java.sql.CallableStatement)
	 */
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.indirizzoIP);
		arg.setString(2, this.sessionID);
		arg.setString(3, this.tipoRequest);	
		arg.setString(4, this.applicativo);	
		arg.setString(5, this.action);	
		arg.setString(6, this.queryString);	
		arg.setString(7, this.param);	
		arg.setString(8, this.request);	
		arg.setString(9, this.operatoreRequest);
		arg.setString(10, this.template);
		arg.setString(11, this.dbSchemaCodSocieta);
		arg.setString(12, this.sezioneApplicativa);
		arg.setString(13, this.canalePagamento);
		arg.setString(14, this.userName);
		arg.setString(15, this.userProfile);
		arg.setString(16, this.codiceSocieta);
		arg.setString(17, this.codiceUtente);
		arg.setString(18, this.chiaveEnte);
		arg.setString(19, this.descrizioneEnte);
		arg.setString(20, this.siglaProvinciaEnte);
		arg.setString(21, this.codiceFiscale);	
		arg.setString(22, this.numeroDocumento);
		arg.setString(23, this.numeroBollettino);	
		arg.setString(24, this.belfioreRequest);	
		arg.setString(25, this.comuneRequest);	
		arg.setString(26, this.siglaProvinciaRequest);	
		arg.setString(27, this.chiaveTransazione);	
		arg.setString(28, this.numeroIUV);	
		arg.registerOutParameter(29, Types.INTEGER);
		arg.registerOutParameter(30, Types.BIGINT);
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onUpdate(java.sql.CallableStatement)
	 */
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setInt(1, this.idRequest.intValue());	
		arg.setString(2, this.indirizzoIP);
		arg.setString(3, this.sessionID);
		arg.setString(4, this.tipoRequest);	
		arg.setString(5, this.applicativo);	
		arg.setString(6, this.action);	
		arg.setString(7, this.queryString);	
		arg.setString(8, this.param);	
		arg.setString(9, this.request);	
		arg.setString(10, this.operatoreRequest);
		arg.setString(11, this.template);
		arg.setString(12, this.dbSchemaCodSocieta);
		arg.setString(13, this.sezioneApplicativa);
		arg.setString(14, this.canalePagamento);
		arg.setString(15, this.userName);
		arg.setString(16, this.userProfile);
		arg.setString(17, this.codiceSocieta);
		arg.setString(18, this.codiceUtente);
		arg.setString(19, this.chiaveEnte);
		arg.setString(20, this.descrizioneEnte);
		arg.setString(21, this.siglaProvinciaEnte);
		arg.setString(22, this.codiceFiscale);	
		arg.setString(23, this.numeroDocumento);
		arg.setString(24, this.numeroBollettino);	
		arg.setString(25, this.belfioreRequest);	
		arg.setString(26, this.comuneRequest);	
		arg.setString(27, this.siglaProvinciaRequest);	
		arg.setString(28, this.chiaveTransazione);	
		arg.setString(29, this.numeroIUV);	
		arg.registerOutParameter(30, Types.INTEGER);
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onDelete(java.sql.CallableStatement)
	 */
	public void onDelete(CallableStatement arg) throws SQLException {
		if (this.idRequest != null)
			arg.setInt(1, this.idRequest.intValue());
		else
			throw new SQLException("id request is null");
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement)
	 */
	public void onLoad(CallableStatement arg) throws SQLException {
		if (this.idRequest != null) {
			arg.setInt(1, this.idRequest.intValue());
			if(this.tagSuffissoTabella == null) {
				arg.setNull(2, Types.VARCHAR);
			} else {
				arg.setString(2, this.tagSuffissoTabella);
			}
		} else
			throw new SQLException("id request is null");
	}

	/* <code></code>
	 * @see com.seda.payer.commons.bean.Lifecycle#onLoad(java.sql.CallableStatement, int, int, java.lang.String)
	 */
	public void onLoad(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException {
		arg.setInt(1, pageNumber);
		arg.setInt(2, rowsPerPage);
		arg.setString(3, order);
		arg.setInt(4, (this.idRequest != null ? this.idRequest.intValue() : 0));
		arg.setString(5, (this.indirizzoIP != null ? this.indirizzoIP : ""));
		arg.setString(6, (this.sessionID != null ? this.sessionID : ""));
		arg.setString(7, (this.tipoRequest != null ? this.tipoRequest : ""));	
		arg.setString(8, (this.applicativo != null ? this.applicativo : ""));
		arg.setString(9, (this.action != null ? this.action : ""));
		arg.setString(10, (this.queryString != null ? this.queryString : ""));
		arg.setString(11, (this.request != null ? this.request : ""));
		arg.setString(12, (this.dataInizio_da != null ? this.dataInizio_da : ""));
		arg.setString(13, (this.dataFine_a != null ? this.dataFine_a : ""));
		arg.setString(14, (this.operatoreRequest != null ? this.operatoreRequest : ""));
		arg.setString(15, (this.template != null ? this.template : ""));
		arg.setString(16, (this.dbSchemaCodSocieta != null ? this.dbSchemaCodSocieta : ""));
		arg.setString(17, (this.sezioneApplicativa != null ? this.sezioneApplicativa : ""));
		arg.setString(18, (this.canalePagamento != null ? this.canalePagamento : ""));
		arg.setString(19, (this.userName != null ? this.userName : ""));
		arg.setString(20, (this.userProfile != null ? this.userProfile : ""));
		arg.setString(21, (this.codiceSocieta != null ? this.codiceSocieta : ""));
		arg.setString(22, (this.codiceUtente != null ? this.codiceUtente : ""));
		arg.setString(23, (this.chiaveEnte != null ? this.chiaveEnte : ""));
		arg.setString(24, (this.descrizioneEnte != null ? this.descrizioneEnte : ""));
		arg.setString(25, (this.siglaProvinciaEnte != null ? this.siglaProvinciaEnte : ""));
		arg.setString(26, (this.codiceFiscale != null ? this.codiceFiscale : ""));
		arg.setString(27, (this.numeroDocumento != null ? this.numeroDocumento : ""));
		arg.setString(28, (this.numeroBollettino != null ? this.numeroBollettino : ""));
		arg.setString(29, (this.belfioreRequest != null ? this.belfioreRequest : ""));
		arg.setString(30, (this.comuneRequest != null ? this.comuneRequest : ""));
		arg.setString(31, (this.siglaProvinciaRequest != null ? this.siglaProvinciaRequest : ""));
		arg.setString(32, (this.chiaveTransazione != null ? this.chiaveTransazione : ""));
		arg.setString(33, (this.numeroIUV != null ? this.numeroIUV : ""));
		arg.setString(34, (this.error != null ? this.error : ""));
		/**/
		/* we register out_select */
		arg.registerOutParameter(35, Types.VARCHAR);
		/* we register row start */
		arg.registerOutParameter(36, Types.INTEGER);
		/* we register row end */
		arg.registerOutParameter(37, Types.INTEGER);
		/* we register total rows */
		arg.registerOutParameter(38, Types.INTEGER);
		/* we register total pages */
		arg.registerOutParameter(39, Types.INTEGER);
	}
}