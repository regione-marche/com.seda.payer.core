package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;

public class Log1 extends Lifecycle implements Serializable {


	private static final long serialVersionUID = 1L;
	
    private BigInteger chiaveLog;
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

    public Log1() { 
    }

    /*
    LG1_KLG1PKEY BIGINT(31) NOT NULL AUTO_INCREMENT COMMENT 'PROGRESSIVO LOG DELLA CHIAMATA',
    LG1_DLG1TIPO VARCHAR(50) NOT NULL COMMENT 'TIPOCHIAMATA: RecuperaDatiBolletitno o ListaDoc o ListaScadenze',
    LG1_CLG1CUTE VARCHAR(5) NOT NULL COMMENT 'CUTECUTE',
    LG1_CLG1CODS VARCHAR(5) NULL COMMENT 'CODICE SOCIETÀ',
    LG1_CLG1ENTE VARCHAR(5) NULL COMMENT 'CODICE ENTE',
    LG1_DLG1BOLL VARCHAR(30) NULL COMMENT 'BOLLETTINO',
    LG1_DLG1CFIS VARCHAR(16) NULL COMMENT 'CODICE FISCALE',
    LG1_CLG1IDDM VARCHAR(11) NULL COMMENT 'IDDOMINIO',
    LG1_GLG1DTIN DATETIME NOT NULL COMMENT 'DATA INIZIO CHIAMATA',
    LG1_GLG1DTFI DATETIME NOT NULL COMMENT 'DATA FINE CHIAMATA',
    LG1_DLG1XMLI TEXT COMMENT 'XML REQUEST',
    LG1_DLG1XMLO TEXT COMMENT 'XML RESPONSE',
    LG1_CLG1ESIT VARCHAR(2) NOT NULL DEFAULT '' COMMENT 'ESITO',
    LG1_CLG1MESS VARCHAR(500) NOT NULL DEFAULT '' COMMENT 'MESSAGGIO ERRORE',
    LG1_GLG1GAGG TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'DATA INSERIMENTO',
    LG1_CLG1COPE VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'OPERATORE INSERIMENTO',
   */

    public Log1(ResultSet data) throws SQLException {
    	if (data == null)
    		return;
    	chiaveLog = BigInteger.valueOf(data.getInt("LG1_KLG1PKEY"));
    	tipoChiamata = data.getString("LG1_DLG1TIPO");
    	codiceUtente = data.getString("LG1_CLG1CUTE");
    	codiceSocieta = data.getString("LG1_CLG1CODS");
        codiceEnte = data.getString("LG1_CLG1ENTE");
    	bollettino = data.getString("LG1_DLG1BOLL");
    	codiceFiscale = data.getString("LG1_DLG1CFIS");
    	idDominio = data.getString("LG1_CLG1IDDM");
		dataInizioChiamata = data.getTimestamp("LG1_GLG1DTIN");
		dataFineChiamata = data.getTimestamp("LG1_GLG1DTFI");
		xmlRequest = data.getString("LG1_DLG1XMLI");
		xmlResponse = data.getString("LG1_DLG1XMLO");
		esito = data.getString("LG1_CLG1ESIT");
		messaggioErrore = data.getString("LG1_CLG1MESS");
		dataInserimento = data.getTimestamp("LG1_GLG1GAGG");
		operatoreInserimento = data.getString("LG1_CLG1COPE");
    }

	public BigInteger getChiaveLog() {
		return chiaveLog;
	}

	public void setChiaveLog(BigInteger chiaveLog) {
		this.chiaveLog = chiaveLog;
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
	
	public String toString() {
		return "Log1 ["
				+ "chiaveLog=" + chiaveLog
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
				+ ", dataInserimento=" + dataInserimento
				+ ", operatoreInserimento="	+ operatoreInserimento + "]";
	}
   
	@Override
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