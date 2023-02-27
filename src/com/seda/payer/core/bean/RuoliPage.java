package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.seda.data.spi.PageInfo;



public class RuoliPage implements Serializable {

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
        
    private String listXml;		 
    private PageInfo pageInfo;
    
    private BigDecimal totcarico;
    private BigDecimal totdimcarico;
    private BigDecimal totriscosso;
    private BigDecimal totrimborso;
    private BigDecimal totresiduo;

	public RuoliPage() { 
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