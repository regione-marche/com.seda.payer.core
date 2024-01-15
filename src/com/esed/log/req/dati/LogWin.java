package com.esed.log.req.dati;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogWin implements Serializable
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
	//parametri per la ricerca
	private int rowsPerPage;
	private int pageNumber;
	private String order;	
	//per selezionare la tabella su cui lavorare
	private String tagSuffissoTabella;

	private String template;
	private String dbSchemaCodSocieta;
	
	
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


	
	
	public boolean setCodiceUtenteCheckEmpty(String codiceUtente) {
		if(codiceUtente != null && codiceUtente.trim().length() == 5) {
			if(this.codiceUtente == null || this.codiceUtente.trim().length() == 0) {
				this.codiceUtente = codiceUtente.trim();
				return true;
			}
		}
		return false;
	}
	public boolean setCodiceSocietaCheckEmpty(String codiceSocieta) {
		if(codiceSocieta != null && codiceSocieta.trim().length() == 5) {
			if(this.codiceSocieta == null || this.codiceSocieta.trim().length() == 0) {
				this.codiceSocieta = codiceSocieta.trim();
				return true;
			}
		}
		return false;
	}
	public boolean setCodiceEnteCheckEmpty(String codiceEnte) {
		if(codiceEnte != null && codiceEnte.trim().length() == 5) {
			if(this.codiceEnte == null || this.codiceEnte.trim().length() == 0) {
				this.codiceEnte = codiceEnte.trim();
				return true;
			}
		}
		return false;
	}
	public boolean setCodiceFiscaleCheckEmpty(String codiceFiscale) {
		if(codiceFiscale != null && codiceFiscale.trim().length() > 0) {
			if(this.codiceFiscale == null || this.codiceFiscale.trim().length() == 0) {
				this.codiceFiscale = codiceFiscale.trim();
				return true;
			}
		}
		return false;
	}
	
	public boolean setIdDominioCheckEmpty(String idDominio) {
		if(idDominio != null && idDominio.trim().length() > 0) {
		if(this.idDominio == null || this.idDominio.trim().length() == 0) {
			this.idDominio = idDominio.trim();
			return true;
		}
	}
	return false;
	}



	
	
	


	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	
	public String getTagSuffissoTabella() {
		return tagSuffissoTabella;
	}

	public void setTagSuffissoTabella(String tagSuffissoTabella) {
		this.tagSuffissoTabella = tagSuffissoTabella;
	}

	public LogWin() {
		super();
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

	
}