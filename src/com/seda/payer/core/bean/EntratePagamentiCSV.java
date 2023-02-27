package com.seda.payer.core.bean;

import java.io.Serializable;


/**
 * 
 * @author ddiemdio
 *
 */


public class EntratePagamentiCSV implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String codiceSocieta;

    private String codiceUtente;

    private String codiceEnte;

    private String impostaServizio;

    private String codiceFiscale;

    private String modalita;

    private String tipologia;

    private String tipoMovimento;

    private String tipoUfficio;

    private String codiceUfficio;

    private String annoEmissione;

    private String numeroEmissione;

    private String dataPagamentoDa;

    private String dataPagamentoA;

    private String tipologiaServizio;

    private String numeroDocumento;

    private String progRiscossione;

    private String codiceTomb;
        
    //output
    
    private String file;		 


    
    public EntratePagamentiCSV() { 

        codiceSocieta = "";

        codiceUtente = "";

        codiceEnte = "";

        impostaServizio = "";

        codiceFiscale = "";

        modalita = "";

        tipologia = "";

        tipoMovimento = "";

        tipoUfficio = "";

        codiceUfficio = "";

        annoEmissione = "";

        numeroEmissione = "";

        dataPagamentoDa = "";

        dataPagamentoA = "";

        tipologiaServizio = "";

        numeroDocumento = "";

        progRiscossione = "";

        codiceTomb = "";
            
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



	public String getCodiceEnte() {
		return codiceEnte;
	}



	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}



	public String getImpostaServizio() {
		return impostaServizio;
	}



	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}



	public String getCodiceFiscale() {
		return codiceFiscale;
	}



	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}



	public String getModalita() {
		return modalita;
	}



	public void setModalita(String modalita) {
		this.modalita = modalita;
	}



	public String getTipologia() {
		return tipologia;
	}



	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}



	public String getTipoMovimento() {
		return tipoMovimento;
	}



	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}



	public String getTipoUfficio() {
		return tipoUfficio;
	}



	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}



	public String getCodiceUfficio() {
		return codiceUfficio;
	}



	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
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



	public String getDataPagamentoDa() {
		return dataPagamentoDa;
	}



	public void setDataPagamentoDa(String dataPagamentoDa) {
		this.dataPagamentoDa = dataPagamentoDa;
	}



	public String getDataPagamentoA() {
		return dataPagamentoA;
	}



	public void setDataPagamentoA(String dataPagamentoA) {
		this.dataPagamentoA = dataPagamentoA;
	}



	public String getTipologiaServizio() {
		return tipologiaServizio;
	}



	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}



	public String getNumeroDocumento() {
		return numeroDocumento;
	}



	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}



	public String getProgRiscossione() {
		return progRiscossione;
	}



	public void setProgRiscossione(String progRiscossione) {
		this.progRiscossione = progRiscossione;
	}



	public String getCodiceTomb() {
		return codiceTomb;
	}



	public void setCodiceTomb(String codiceTomb) {
		this.codiceTomb = codiceTomb;
	}



	public String getFile() {
		return file;
	}



	public void setFile(String file) {
		this.file = file;
	}





}