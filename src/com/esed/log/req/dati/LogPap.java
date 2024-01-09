package com.esed.log.req.dati;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LogPap implements Serializable
{
	private static final long serialVersionUID = 1L;
	private BigInteger idLog;
	private String codiceUtente;
	private String idDominio;
	private String iuv;
	private Timestamp dataInizioChiamata;
	private Timestamp dataFineChiamata;
	private String xmlRequest;
	private String xmlResponse;
	private String esito;
	private String errore;
	private Timestamp dataInserimento;
	private String operazione;
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
	
	
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	
	
	public String getCodiceUtente() {
		return codiceUtente;
	}
	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
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
	
	public String getErrore() {
		return errore;
	}
	public void setErrore(String errore) {
		this.errore = errore;
	}
	
	
	public Timestamp getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Timestamp dataInserimento) {
		this.dataInserimento = dataInserimento;
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

	public LogPap() {
		super();
	}

	public String toString() {
		return "LogPap ["
				+ "idLog=" + idLog
				+ ", codiceUtente=" + codiceUtente
				+ ", idDominio=" + idDominio
				+ ", iuv=" + iuv
				+ ", dataInizioChiamata=" + dataInizioChiamata
				+ ", dataFineChiamata=" + dataFineChiamata
				+ ", xmlRequest=" + xmlRequest
				+ ", xmlResponse=" + xmlResponse
				+ ", esito=" + esito
				+ ", errore=" + errore
				+ ", operazione=" + operazione
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
	public String getOperazione() {
		return operazione;
	}
	public void setOperazione(String operazione) {
		this.operazione = operazione;
	}
	
	
}