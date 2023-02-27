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
public class LogWin extends Lifecycle implements Serializable
{
	private static final long serialVersionUID = 1L;
	private BigInteger idLog;
	private String tipoChiamata;
	private String codiceUtente;
	private String codiceSocieta;
	private String codiceEnte;
	private String bollettino;
	private String codiceFiscale;
	private String idDominio;
	private Timestamp dataInizioChiamata;
	private Timestamp dataFineChiamata;
	private String xmlRequest;
	private String xmlResponse;
	private String esito;
	private String messaggioErrore;
	private Timestamp dataInserimento;
	private String operatoreInserimento;
	//per selezionare la tabella su cui lavorare
	private String tagSuffissoTabella;

	private String dbSchemaCodSocieta;
	

	
	public LogWin() {
		super();
	}
	
	
	public BigInteger getIdLog() {
		return idLog;
	}
	public void setIdLog(BigInteger idLog) {
		this.idLog = idLog;
	}
	
	
	public String getTipoChiamata() {
		return tipoChiamata;
	}
	public void setTipoChiamata(String tipoChiamata) {
		this.tipoChiamata = tipoChiamata;
	}
	
	
	public String getCodiceUtente() {
		return codiceUtente;
	}
	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}
	
	
	public String getCodiceSocieta() {
		return codiceSocieta;
	}
	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	
	public String getCodiceEnte() {
		return codiceEnte;
	}
	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}
	
	
	public String getBollettino() {
		return bollettino;
	}
	public void setBollettino(String bollettino) {
		this.bollettino = bollettino;
	}

	
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
	
	public String getIdDominio() {
		return idDominio;
	}
	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}
	
	
	public Timestamp getDataInizioChiamata() {
		return dataInizioChiamata;
	}
	public void setDataInizioChiamata(Timestamp dataInizioChiamata) {
		this.dataInizioChiamata = dataInizioChiamata;
	}
	

	public Timestamp getDataFineChiamata() {
		return dataFineChiamata;
	}
	public void setDataFineChiamata(Timestamp dataFineChiamata) {
		this.dataFineChiamata = dataFineChiamata;
	}

	
	public String getXmlRequest() {
		return xmlRequest;
	}
	public void setXmlRequest(String xmlRequest) {
		this.xmlRequest = xmlRequest;
	}

	
	public String getXmlResponse() {
		return xmlResponse;
	}
	public void setXmlResponse(String xmlResponse) {
		this.xmlResponse = xmlResponse;
	}
	
	
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	
	public String getMessaggioErrore() {
		return messaggioErrore;
	}
	public void setMessaggioErrore(String messaggioErrore) {
		this.messaggioErrore = messaggioErrore;
	}
	
	
	public Timestamp getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Timestamp dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	
	
	public String getOperatoreInserimento() {
		return operatoreInserimento;
	}
	public void setOperatoreInserimento(String operatoreInserimento) {
		this.operatoreInserimento = operatoreInserimento;
	}
	
	public String getTagSuffissoTabella() {
		return tagSuffissoTabella;
	}

	public void setTagSuffissoTabella(String tagSuffissoTabella) {
		this.tagSuffissoTabella = tagSuffissoTabella;
	}

	public String getDbSchemaCodSocieta() {
		return dbSchemaCodSocieta;
	}
	public void setDbSchemaCodSocieta(String dbSchemaCodSocieta) {
		this.dbSchemaCodSocieta = dbSchemaCodSocieta;
	}
	
//    WIN_KWINPKEY  BIGINT NOT NULL COMMENT 'PROGRESSIVO_LOG_DELLA_CHIAMATA', 
//    WIN_DWINTIPO  VARCHAR(50) NOT NULL COMMENT 'TIPOCHIAMATA_RecuperaDatiBollettino_ListaDoc_ListaScadenze',
//    WIN_CWINCUTE  VARCHAR(5) NOT NULL COMMENT 'CUTECUTE',
//    WIN_CWINCODS  VARCHAR(5) COMMENT 'CODICE_SOCIETA', 
//    WIN_CWINENTE  VARCHAR(5) COMMENT 'CODICE_ENTE', 
//    WIN_DWINBOLL  VARCHAR(30) COMMENT 'BOLLETTINO', 
//    WIN_DWINCFIS  VARCHAR(16) COMMENT 'CODICE_FISCALE', 
//    WIN_CWINIDDM  VARCHAR(16) COMMENT 'IDDOMINIO', 
//    WIN_GWINDTIN  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DATA_INIZIO_CHIAMATA',
//    WIN_GWINDTFI  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'DATA_FINE_CHIAMATA', 
//    WIN_DWINXMLI  TEXT COMMENT 'XML_REQUEST', 
//    WIN_DWINXMLO  TEXT COMMENT 'XML_RESPONSE',
//    WIN_CWINESIT  VARCHAR(2) DEFAULT '' COMMENT 'ESITO', 
//    WIN_CWINMESS  VARCHAR(500) NOT NULL DEFAULT '' COMMENT 'MESSAGGIO_ERRORE', 
//    WIN_GWINGAGG  TIMESTAMP DEFAULT CURRENT_TIMESTAMP  COMMENT 'DATA_INSERIMENTO',
//    WIN_CWINCOPE  VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'OPERATORE_INSERIMENTO',

	
	public LogWin(ResultSet data) throws SQLException {
		if (data == null)
			return;
		this.idLog = BigInteger.valueOf(data.getInt("WIN_KWINPKEY"));
		this.tipoChiamata = data.getString("WIN_DWINTIPO");
		this.codiceUtente =  data.getString("WIN_CWINCUTE");
		this.codiceSocieta = data.getString("WIN_CWINCODS");
		this.codiceEnte = data.getString("WIN_CWINENTE");
		this.bollettino = data.getString("WIN_DWINBOLL");
		this.codiceFiscale = data.getString("WIN_DWINCFIS");
		this.idDominio = data.getString("WIN_CWINIDDM");
		this.dataInizioChiamata = data.getTimestamp("WIN_GWINDTIN");
		this.dataFineChiamata = data.getTimestamp("WIN_GWINDTFI");
		this.xmlRequest = data.getString("WIN_DWINXMLI");
		this.xmlResponse = data.getString("WIN_DWINXMLO");
		this.esito = data.getString("WIN_CWINESIT");
		this.messaggioErrore = data.getString("WIN_CWINMESS");
		this.dataInserimento = data.getTimestamp("WIN_GWINGAGG");
		this.operatoreInserimento = data.getString("WIN_CWINCOPE");
		
		try {
			this.tagSuffissoTabella = data.getString("WIN_TGSUFTAB");
		} catch (Exception e) {
			this.tagSuffissoTabella = "";
		}
	}

	public String toString() {
		return "LogWin ["
				+ "idLog=" + idLog
				+ ", tipoChiamata=" + tipoChiamata
				+ ", codiceUtente=" + codiceUtente
				+ ", codiceSocieta=" + codiceSocieta
				+ ", codiceEnte=" + codiceEnte
				+ ", bollettino=" + bollettino
				+ ", codiceFiscale=" + codiceFiscale
				+ ", idDominio=" + idDominio
				+ ", dataInizioChiamata=" + dataInizioChiamata
				+ ", dataFineChiamata=" + dataFineChiamata
				+ ", xmlRequest=" + xmlRequest
				+ ", xmlResponse=" + xmlResponse
				+ ", esito=" + esito
				+ ", messaggioErrore=" + messaggioErrore
				+ ", tagSuffissoTabella=" + tagSuffissoTabella
				+ "]";
	}




	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.tipoChiamata);
		arg.setString(2, this.codiceUtente);	
		arg.setString(3, this.codiceSocieta);	
		arg.setString(4, this.codiceEnte);	
		arg.setString(5, this.bollettino);	
		arg.setString(6, this.codiceFiscale);	
		arg.setString(7, this.idDominio);	
		arg.setTimestamp(8, this.dataInizioChiamata);
		arg.setTimestamp(9, this.dataFineChiamata);
		arg.setString(10, this.xmlRequest);
		arg.setString(11, this.xmlResponse);
		arg.setString(12, this.esito);
		arg.setString(13, this.messaggioErrore);
		arg.setString(14, this.operatoreInserimento);
		arg.registerOutParameter(15, Types.INTEGER);
		arg.registerOutParameter(16, Types.BIGINT);
	}

	
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setInt(1, this.idLog.intValue());	
		arg.setString(2, this.tipoChiamata);
		arg.setString(3, this.codiceUtente);	
		arg.setString(4, this.codiceSocieta);	
		arg.setString(5, this.codiceEnte);	
		arg.setString(6, this.bollettino);	
		arg.setString(7, this.codiceFiscale);	
		arg.setString(8, this.idDominio);	
		arg.setTimestamp(9, this.dataInizioChiamata);
		arg.setTimestamp(10, this.dataFineChiamata);
		arg.setString(11, this.xmlRequest);
		arg.setString(12, this.xmlResponse);
		arg.setString(13, this.esito);
		arg.setString(14, this.messaggioErrore);
		arg.setTimestamp(15, this.dataInserimento);
		arg.registerOutParameter(16, Types.INTEGER);
	}

	
	public void onDelete(CallableStatement arg) throws SQLException {
		if (this.idLog != null)
			arg.setInt(1, this.idLog.intValue());
		else
			throw new SQLException("id request is null");
	}

	
	public void onLoad(CallableStatement arg) throws SQLException {
		if (this.idLog != null) {
			arg.setInt(1, this.idLog.intValue());
			if(this.tagSuffissoTabella == null) {
				arg.setNull(2, Types.VARCHAR);
			} else {
				arg.setString(2, this.tagSuffissoTabella);
			}
		} else
			throw new SQLException("id request is null");
	}

	
	public void onLoad(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException {
		arg.setInt(1, pageNumber);
		arg.setInt(2, rowsPerPage);
		arg.setString(3, order);
		
		arg.setString(4, (this.tipoChiamata != null ? this.tipoChiamata : ""));
		arg.setString(5, (this.codiceUtente != null ? this.codiceUtente : ""));
		arg.setString(6, (this.codiceSocieta != null ? this.codiceSocieta : ""));	
		arg.setString(7, (this.codiceEnte != null ? this.codiceEnte : ""));
		arg.setString(8, (this.bollettino != null ? this.bollettino : ""));
		arg.setString(9, (this.codiceFiscale != null ? this.codiceFiscale : ""));
		arg.setString(10, (this.idDominio != null ? this.idDominio : ""));
		arg.setTimestamp(11, (this.dataInizioChiamata != null ? this.dataInizioChiamata : null));
		arg.setTimestamp(12, (this.dataFineChiamata != null ? this.dataFineChiamata : null));
		arg.setString(13, (this.xmlRequest != null ? this.xmlRequest : ""));
		arg.setString(14, (this.xmlResponse != null ? this.xmlResponse : ""));
		arg.setString(15, (this.esito != null ? this.esito : ""));
		arg.setString(16, (this.messaggioErrore != null ? this.messaggioErrore : ""));
		arg.setTimestamp(17, (this.dataInserimento != null ? this.dataInserimento : null ));
		arg.setString(18, (this.operatoreInserimento != null ? this.operatoreInserimento : ""));
		/**/
		/* we register out_select */
		arg.registerOutParameter(19, Types.VARCHAR);
		/* we register row start */
		arg.registerOutParameter(20, Types.INTEGER);
		/* we register row end */
		arg.registerOutParameter(21, Types.INTEGER);
		/* we register total rows */
		arg.registerOutParameter(22, Types.INTEGER);
		/* we register total pages */
		arg.registerOutParameter(23, Types.INTEGER);
	}
}