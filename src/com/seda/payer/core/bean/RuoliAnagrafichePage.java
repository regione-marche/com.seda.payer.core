package com.seda.payer.core.bean;

import java.io.Serializable;

import com.seda.data.spi.PageInfo;



public class RuoliAnagrafichePage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String codiceSocieta;

    private String codiceUtente;

    private String codiceEnte;

    private String codiceFiscale;

    private String denominazione;

    private String tipoRic;

    //output
    
    private String listXml;		 
    private PageInfo pageInfo;


    
    public RuoliAnagrafichePage() { 

        this.codiceSocieta = "";
        this.codiceUtente = "";
        this.codiceEnte = "";
        this.codiceFiscale = "";
        this.denominazione = "";
        this.tipoRic = "";
            
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getTipoRic() {
		return tipoRic;
	}

	public void setTipoRic(String tipoRic) {
		this.tipoRic = tipoRic;
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



}