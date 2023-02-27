package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//import com.seda.payer.core.bean.Riversamento;



public class EntrateDocumentoDettaglio implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String impostaServizio;
    private String ufficioImpositore;
    private String annoEmissione;
    private String numeroEmissione;
    private String tipologiaServizio;
    private String docRendicontato;
    private String codiceFiscale;
    private String numeroDocumento;
    private String numeroRate;
    private Date scadenzaPrimaRata;
    private String sospensione;
    private Date dataNotifica;
    private String procEsecutive;
    private String numeroDocumentoColl;
    
    private EntrateDocumentiPage documentiTot;
    
    private BigDecimal intMora;
    private BigDecimal totEccedenza;
    
    private String descSocieta;
    private String descUtente;
    private String descEnte;
    
    private String numeroBollettino;
    private String numeroIUV;
    
    //emissione
    private String numDocumentiEmis;
    private String numDocumentiRendEmis;


	public EntrateDocumentoDettaglio() { 
	    impostaServizio = "";
	    ufficioImpositore = "";
	    annoEmissione= "";
	    numeroEmissione ="";
	    tipologiaServizio = "";
	    docRendicontato = "";
	    codiceFiscale = "";
	    numeroDocumento = "";
	    numeroRate = "";
	    sospensione = "";
	    procEsecutive = "";
	    numeroDocumentoColl = "";
	    descSocieta = "";
	    descUtente = "";
	    descEnte = "";
	    
	    numeroBollettino = "";
	    numeroIUV = "";
	    
	    
	    numDocumentiEmis="";
	    numDocumentiRendEmis="";
	    
	    
	    intMora = new BigDecimal(0);
	    totEccedenza = new BigDecimal(0);        
    }

	
	public String getNumeroBollettino() {
		return numeroBollettino;
	}
	
	public void setNumeroBollettino(String numeroBollettino) {
		this.numeroBollettino = numeroBollettino;
	}
	
	public String getNumeroIUV() {
		return numeroIUV;
	}
	
	public void setNumeroIUV(String numeroIUV) {
		this.numeroIUV = numeroIUV;
	}
		


	public String getNumDocumentiEmis() {
		return numDocumentiEmis;
	}




	public void setNumDocumentiEmis(String numDocumentiEmis) {
		this.numDocumentiEmis = numDocumentiEmis;
	}




	public String getNumDocumentiRendEmis() {
		return numDocumentiRendEmis;
	}




	public void setNumDocumentiRendEmis(String numDocumentiRendEmis) {
		this.numDocumentiRendEmis = numDocumentiRendEmis;
	}




	public String getDescSocieta() {
		return descSocieta;
	}




	public void setDescSocieta(String descSocieta) {
		this.descSocieta = descSocieta;
	}




	public String getDescUtente() {
		return descUtente;
	}




	public void setDescUtente(String descUtente) {
		this.descUtente = descUtente;
	}




	public String getDescEnte() {
		return descEnte;
	}




	public void setDescEnte(String descEnte) {
		this.descEnte = descEnte;
	}




	public String getImpostaServizio() {
		return impostaServizio;
	}




	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}




	public String getUfficioImpositore() {
		return ufficioImpositore;
	}




	public void setUfficioImpositore(String ufficioImpositore) {
		this.ufficioImpositore = ufficioImpositore;
	}




	public String getAnnoEmissione() {
		return annoEmissione;
	}




	public void setAnnoEmissione(String annoEmissione) {
		this.annoEmissione = annoEmissione;
	}




	public String getNumeroEmissione() {
		return numeroEmissione;
	}




	public void setNumeroEmissione(String numeroEmissione) {
		this.numeroEmissione = numeroEmissione;
	}




	public String getTipologiaServizio() {
		return tipologiaServizio;
	}




	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}




	public String getDocRendicontato() {
		return docRendicontato;
	}




	public void setDocRendicontato(String docRendicontato) {
		this.docRendicontato = docRendicontato;
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




	public String getNumeroRate() {
		return numeroRate;
	}




	public void setNumeroRate(String numeroRate) {
		this.numeroRate = numeroRate;
	}




	public Date getScadenzaPrimaRata() {
		return scadenzaPrimaRata;
	}




	public void setScadenzaPrimaRata(Date scadenzaPrimaRata) {
		this.scadenzaPrimaRata = scadenzaPrimaRata;
	}




	public String getSospensione() {
		return sospensione;
	}




	public void setSospensione(String sospensione) {
		this.sospensione = sospensione;
	}




	public Date getDataNotifica() {
		return dataNotifica;
	}




	public void setDataNotifica(Date dataNotifica) {
		this.dataNotifica = dataNotifica;
	}




	public String getProcEsecutive() {
		return procEsecutive;
	}




	public void setProcEsecutive(String procEsecutive) {
		this.procEsecutive = procEsecutive;
	}




	public String getNumeroDocumentoColl() {
		return numeroDocumentoColl;
	}




	public void setNumeroDocumentoColl(String numeroDocumentoColl) {
		this.numeroDocumentoColl = numeroDocumentoColl;
	}




	public EntrateDocumentiPage getDocumentiTot() {
		return documentiTot;
	}




	public void setDocumentiTot(EntrateDocumentiPage documentiTot) {
		this.documentiTot = documentiTot;
	}


	public BigDecimal getIntMora() {
		return intMora;
	}




	public void setIntMora(BigDecimal intMora) {
		this.intMora = intMora;
	}




	public BigDecimal getTotEccedenza() {
		return totEccedenza;
	}




	public void setTotEccedenza(BigDecimal totEccedenza) {
		this.totEccedenza = totEccedenza;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    


}