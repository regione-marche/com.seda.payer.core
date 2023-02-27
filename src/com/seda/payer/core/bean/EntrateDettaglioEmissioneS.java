package com.seda.payer.core.bean;

import java.io.Serializable;


/**
 * 
 * @author ddiemdio
 *
 */


public class EntrateDettaglioEmissioneS implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String codiceUtente;

    private String codiceEnte;

    private String tipoUfficio;

    private String codiceUfficio;

    private String impostaServizio;

    private String annoEmissione;

    private String numeroEmissione;

    private String codiceTomb;
    
    //output
    
    private String listXml;		 


    public EntrateDettaglioEmissioneS()
    {
    	this.codiceUtente = "";
        this.codiceEnte = "";
        this.tipoUfficio = "";
        this.codiceUfficio = "";
        this.impostaServizio = "";
        this.annoEmissione="";
        this.numeroEmissione="";
        this.codiceTomb = "";
    }

    public EntrateDettaglioEmissioneS(
            String codiceUtente,
            String codiceEnte,
            String tipoUfficio,
            String codiceUfficio,
            String impostaServizio,
            String annoEmissione,
            String numeroEmissione,
            String codiceTomb) {
            this.codiceUtente = codiceUtente;
            this.codiceEnte = codiceEnte;
            this.tipoUfficio = tipoUfficio;
            this.codiceUfficio = codiceUfficio;
            this.impostaServizio = impostaServizio;
            this.annoEmissione = annoEmissione;
            this.numeroEmissione = numeroEmissione;
            this.codiceTomb = codiceTomb;
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

	public String getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
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

	public String getCodiceTomb() {
		return codiceTomb;
	}

	public void setCodiceTomb(String codiceTomb) {
		this.codiceTomb = codiceTomb;
	}

	public String getListXml() {
		return listXml;
	}

	public void setListXml(String listXml) {
		this.listXml = listXml;
	}





}