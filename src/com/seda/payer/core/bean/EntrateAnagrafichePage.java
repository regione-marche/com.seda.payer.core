package com.seda.payer.core.bean;

import java.io.Serializable;

//import com.seda.payer.core.bean.Riversamento;
import com.seda.data.spi.PageInfo;

/**
 * 
 * @author ddiemdio
 *
 */


public class EntrateAnagrafichePage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String codiceSocieta;

    private String codiceUtente;

    private String codiceEnte;

    private String impostaServizio;

    private String codiceFiscale;

    private String denominazione;

    private String tipoRic;

    private String codiceTomb;
    
    private Paginazione paginazione;
    //output
    
    private String listXml;		 
    private PageInfo pageInfo;


    
    public EntrateAnagrafichePage() { 

        this.codiceSocieta = "";
        this.codiceUtente = "";
        this.codiceEnte = "";
        this.impostaServizio = "";
        this.codiceFiscale = "";
        this.denominazione = "";
        this.tipoRic = "";
        this.codiceTomb = "";
        this.paginazione = new Paginazione(0,0,"");
            
    }

    public EntrateAnagrafichePage(
            String codiceSocieta,
            String codiceUtente,
            String codiceEnte,
            String impostaServizio,
            String codiceFiscale,
            String denominazione,
            String tipoRic,
            String codiceTomb,
            Paginazione paginazione) 
    {
            this.codiceSocieta = codiceSocieta;
            this.codiceUtente = codiceUtente;
            this.codiceEnte = codiceEnte;
            this.impostaServizio = impostaServizio;
            this.codiceFiscale = codiceFiscale;
            this.denominazione = denominazione;
            this.tipoRic = tipoRic;
            this.codiceTomb = codiceTomb;
            this.paginazione = paginazione;
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

	public String getCodiceTomb() {
		return codiceTomb;
	}

	public void setCodiceTomb(String codiceTomb) {
		this.codiceTomb = codiceTomb;
	}

	public Paginazione getPaginazione() {
		return paginazione;
	}

	public void setPaginazione(Paginazione paginazione) {
		this.paginazione = paginazione;
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