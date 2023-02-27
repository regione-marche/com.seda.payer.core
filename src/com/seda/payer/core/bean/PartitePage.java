package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.seda.data.spi.PageInfo;



public class PartitePage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private String codiceSocieta;
    private String codiceUtente;
	private String codiceEnte;
    private String tipoUfficio;
    private String codiceUfficio;
    private String numeroRuolo;
    private String annoRuolo;
    private String dataConsegneDa;
    private String dataConsegneA;
    private String codiceFiscale;
    private String progrPartita;
    private String statoPartita;
    private String flagCartellazione;
    private String codiceCartella;
    //chiave Ruolo
    private String progFlussoRuolo;
    private String progrConcessione;
    private String codiceTombRuolo;
    
    private String listXml;		 
    private PageInfo pageInfo;
    
    private BigDecimal totcarico;
    private BigDecimal totdimcarico;
    private BigDecimal totriscosso;
    private BigDecimal totrimborso;
    private BigDecimal totresiduo;

	public PartitePage() { 
    	codiceSocieta="";
    	codiceEnte="";
    	codiceUtente="";

        tipoUfficio="";
        codiceUfficio="";
        totcarico = new BigDecimal(0);
        totdimcarico = new BigDecimal(0);
        totriscosso= new BigDecimal(0);
        totrimborso = new BigDecimal(0);
        totresiduo =new BigDecimal(0);
        dataConsegneDa = "";
        dataConsegneA = "";
        
        codiceFiscale = "";
        progrPartita = "";
        statoPartita = "";
        flagCartellazione = "";
        codiceCartella = "";
        //chiave Ruolo
        progFlussoRuolo = "";
        progrConcessione = "";
        codiceTombRuolo = "";

    }

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getProgrPartita() {
		return progrPartita;
	}

	public void setProgrPartita(String progrPartita) {
		this.progrPartita = progrPartita;
	}

	public String getStatoPartita() {
		return statoPartita;
	}

	public void setStatoPartita(String statoPartita) {
		this.statoPartita = statoPartita;
	}

	public String getFlagCartellazione() {
		return flagCartellazione;
	}

	public void setFlagCartellazione(String flagCartellazione) {
		this.flagCartellazione = flagCartellazione;
	}

	public String getCodiceCartella() {
		return codiceCartella;
	}

	public void setCodiceCartella(String codiceCartella) {
		this.codiceCartella = codiceCartella;
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

	public String getCodiceTombRuolo() {
		return codiceTombRuolo;
	}

	public void setCodiceTombRuolo(String codiceTombRuolo) {
		this.codiceTombRuolo = codiceTombRuolo;
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


	public String getDataConsegneDa() {
		return dataConsegneDa;
	}

	public void setDataConsegneDa(String dataConsegneDa) {
		this.dataConsegneDa = dataConsegneDa;
	}

	public String getDataConsegneA() {
		return dataConsegneA;
	}

	public void setDataConsegneA(String dataConsegneA) {
		this.dataConsegneA = dataConsegneA;
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