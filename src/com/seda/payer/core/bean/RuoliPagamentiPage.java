package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.seda.data.spi.PageInfo;



public class RuoliPagamentiPage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private String codiceSocieta;
    private String codiceUtente;
	private String codiceEnte;
	private String codiceUfficio;
	private String tipoUfficio;
    private String numeroRuolo;
    private String annoRuolo;
    private String codiceFiscale;
    private String progrPartita;
    private String dataRegPagDa;
    private String dataRegPagA;
    private String dataEffPagDa;
    private String dataEffPagA;
    private String progFlussoRuolo;
    private String progrConcessione;
    
    private String listXml;		 
    private PageInfo pageInfo;
    
    private BigDecimal totimporto;
    private BigDecimal totmora;

	public RuoliPagamentiPage() { 
    	codiceSocieta="";
    	codiceEnte="";
    	codiceUtente="";
        progrPartita = "";
        //chiave Ruolo
        progFlussoRuolo = "";
        progrConcessione = "";
        numeroRuolo = "";
        annoRuolo = "";
        codiceUfficio = "";
    	tipoUfficio = "";
    	codiceFiscale = "";
    	dataRegPagA = "";
    	dataRegPagDa = "";
    	dataEffPagDa = "";
    	dataEffPagA = "";
        totimporto = new BigDecimal(0);
        totmora = new BigDecimal(0);
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

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
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

	public String getDataRegPagDa() {
		return dataRegPagDa;
	}

	public void setDataRegPagDa(String dataRegPagDa) {
		this.dataRegPagDa = dataRegPagDa;
	}

	public String getDataRegPagA() {
		return dataRegPagA;
	}

	public void setDataRegPagA(String dataRegPagA) {
		this.dataRegPagA = dataRegPagA;
	}

	public String getDataEffPagDa() {
		return dataEffPagDa;
	}

	public void setDataEffPagDa(String dataEffPagDa) {
		this.dataEffPagDa = dataEffPagDa;
	}

	public String getDataEffPagA() {
		return dataEffPagA;
	}

	public void setDataEffPagA(String dataEffPagA) {
		this.dataEffPagA = dataEffPagA;
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

	public BigDecimal getTotimporto() {
		return totimporto;
	}

	public void setTotimporto(BigDecimal totimporto) {
		this.totimporto = totimporto;
	}

	public BigDecimal getTotmora() {
		return totmora;
	}

	public void setTotmora(BigDecimal totmora) {
		this.totmora = totmora;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}