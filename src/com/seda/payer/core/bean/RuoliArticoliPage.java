package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.seda.data.spi.PageInfo;

// da ArticoliPage a EntrateArticoliPage

public class RuoliArticoliPage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private String codiceSocieta;
    private String codiceUtente;
	private String codiceEnte;
    private String numeroRuolo;
    private String annoRuolo;
    private String progrPartita;
    private String progFlussoRuolo;
    private String progrConcessione;
    
    private String listXml;		 
    private PageInfo pageInfo;
    
    private BigDecimal totcarico;
    private BigDecimal totdimcarico;
    private BigDecimal totriscosso;
    private BigDecimal totrimborso;
    private BigDecimal totmora;
    private BigDecimal totresiduo;

	public RuoliArticoliPage() { 
    	codiceSocieta="";
    	codiceEnte="";
    	codiceUtente="";
        progrPartita = "";
        //chiave Ruolo
        progFlussoRuolo = "";
        progrConcessione = "";
        numeroRuolo = "";
        annoRuolo = "";

        totcarico = new BigDecimal(0);
        totdimcarico = new BigDecimal(0);
        totriscosso= new BigDecimal(0);
        totrimborso = new BigDecimal(0);
        totmora = new BigDecimal(0);
        totresiduo =new BigDecimal(0);
    }


	public BigDecimal getTotmora() {
		return totmora;
	}


	public void setTotmora(BigDecimal totmora) {
		this.totmora = totmora;
	}


	public String getProgrPartita() {
		return progrPartita;
	}

	public void setProgrPartita(String progrPartita) {
		this.progrPartita = progrPartita;
	}

	public String getProgFlussoRuolo() {
		return progFlussoRuolo;
	}

	public void setProgFlussoRuolo(String progFlussoRuolo) {
		this.progFlussoRuolo = progFlussoRuolo;
	}

	public String getProgrConcessione() {
		return progrConcessione;
	}

	public void setProgrConcessione(String progrConcessione) {
		this.progrConcessione = progrConcessione;
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

	public String getNumeroRuolo() {
		return numeroRuolo;
	}

	public void setNumeroRuolo(String numeroRuolo) {
		this.numeroRuolo = numeroRuolo;
	}

	public String getAnnoRuolo() {
		return annoRuolo;
	}

	public void setAnnoRuolo(String annoRuolo) {
		this.annoRuolo = annoRuolo;
	}

	public String getListXml() {
		return listXml;
	}

	public void setListXml(String listXml) {
		this.listXml = listXml;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public BigDecimal getTotcarico() {
		return totcarico;
	}

	public void setTotcarico(BigDecimal totcarico) {
		this.totcarico = totcarico;
	}

	public BigDecimal getTotdimcarico() {
		return totdimcarico;
	}

	public void setTotdimcarico(BigDecimal totdimcarico) {
		this.totdimcarico = totdimcarico;
	}

	public BigDecimal getTotriscosso() {
		return totriscosso;
	}

	public void setTotriscosso(BigDecimal totriscosso) {
		this.totriscosso = totriscosso;
	}

	public BigDecimal getTotrimborso() {
		return totrimborso;
	}

	public void setTotrimborso(BigDecimal totrimborso) {
		this.totrimborso = totrimborso;
	}

	public BigDecimal getTotresiduo() {
		return totresiduo;
	}

	public void setTotresiduo(BigDecimal totresiduo) {
		this.totresiduo = totresiduo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}