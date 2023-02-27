package com.seda.payer.core.bean;

import java.io.Serializable;


/**
 * 
 * @author ddiemdio
 *
 */


public class EntrateDettaglioAnagrafica implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String progressivoFlusso;

    private String codiceUtente;

    private String dataCreazioneFlusso;

    private String tipoServizio;

    private String codiceEnte;

    private String tipoUfficio;

    private String codiceUfficio;

    private String impostaServizio;

    private String codiceFiscale;

    private String codiceTomb;
    
    //output
    
    private String listXml;		 

//    private AnagraficaDettaglioBean dettaglio;

    public EntrateDettaglioAnagrafica()
    {
        this.progressivoFlusso = "";
        this.codiceUtente = "";
        this.dataCreazioneFlusso = "";
        this.tipoServizio = "";
        this.codiceEnte = "";
        this.tipoUfficio = "";
        this.codiceUfficio = "";
        this.impostaServizio = "";
        this.codiceFiscale = "";
        this.codiceTomb = "";
    }

    public EntrateDettaglioAnagrafica(
            String progressivoFlusso,
            String codiceUtente,
            String dataCreazioneFlusso,
            String tipoServizio,
            String codiceEnte,
            String tipoUfficio,
            String codiceUfficio,
            String impostaServizio,
            String codiceFiscale,
            String codiceTomb) {
            this.progressivoFlusso = progressivoFlusso;
            this.codiceUtente = codiceUtente;
            this.dataCreazioneFlusso = dataCreazioneFlusso;
            this.tipoServizio = tipoServizio;
            this.codiceEnte = codiceEnte;
            this.tipoUfficio = tipoUfficio;
            this.codiceUfficio = codiceUfficio;
            this.impostaServizio = impostaServizio;
            this.codiceFiscale = codiceFiscale;
            this.codiceTomb = codiceTomb;
     }

	public String getProgressivoFlusso() {
		return progressivoFlusso;
	}

	public void setProgressivoFlusso(String progressivoFlusso) {
		this.progressivoFlusso = progressivoFlusso;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getDataCreazioneFlusso() {
		return dataCreazioneFlusso;
	}

	public void setDataCreazioneFlusso(String dataCreazioneFlusso) {
		this.dataCreazioneFlusso = dataCreazioneFlusso;
	}

	public String getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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