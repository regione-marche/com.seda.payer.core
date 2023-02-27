package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//import com.seda.payer.core.bean.Riversamento;
import com.seda.data.spi.PageInfo;



public class EntrateDocumentiPage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private String codiceSocieta;
    private String codiceUtente;
	private String codiceEnte;
    private String impostaServizio;
    private String codiceFiscale;
    private String annoEmissione;
    private String numeroEmissione;
    private String tipoUfficio;
    private String codiceUfficio;
    private String numeroDocumento;
    private String tipologiaServizio;
    private String statoDocumento;
    private String statoSospensione;
    private String statoProcedure;

    private String numeroBollettino;
    private String numeroIUV;
    
        
    //output
	/*OUT O_TOT_CAR DECIMAL(15 , 2),
	OUT O_TOT_REND DECIMAL(15 , 2),
	OUT O_TOT_DIM_CAR DECIMAL(15 , 2),
	OUT O_TOT_RISC DECIMAL(15 , 2),
	OUT O_TOT_RIMB DECIMAL(15 , 2),
	OUT O_TOT_CARICO_SX DECIMAL(15 , 2),
	OUT O_TOT_RESIDUO DECIMAL(15 , 2)*/

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


	private String listXml;		 
    private PageInfo pageInfo;
    
    private BigDecimal totcarico;
    private BigDecimal totrendicontato;
    private BigDecimal totdimcarico;
    private BigDecimal totriscosso;
    private BigDecimal totrimborso;
    private BigDecimal totfincarico;
    private BigDecimal totresiduo;
    private Date dataUltimoAgg;
    
    private BigDecimal percRiscossoCarico;
    private BigDecimal percRendicontatoCarico;
    private BigDecimal percRimborsoCarico;
    private BigDecimal percSgravatoCarico;
    private BigDecimal percResiduoCarico;
    private BigDecimal percResScadutoCarico;
    
    private BigDecimal totResScaduto;
    
    private String progrFlusso;
    private String tipoServizio;
	private String codiceTomb;
	private String chiaveTipoServ;
	private String chiaveCodiceEnte;
	
	//emissioni
    private BigDecimal totcaricoRendicontato;
    private BigDecimal totdimcaricoRendicontato;
    private BigDecimal totriscossoRendicontato;
    private BigDecimal totrimborsoRendicontato;

	public String getChiaveCodiceEnte() {
		return chiaveCodiceEnte;
	}


	public void setChiaveCodiceEnte(String chiaveCodiceEnte) {
		this.chiaveCodiceEnte = chiaveCodiceEnte;
	}


	public String getChiaveTipoServ() {
		return chiaveTipoServ;
	}


	public void setChiaveTipoServ(String chiaveTipoServ) {
		this.chiaveTipoServ = chiaveTipoServ;
	}


	public String getProgrFlusso() {
		return progrFlusso;
	}


	public void setProgrFlusso(String progrFlusso) {
		this.progrFlusso = progrFlusso;
	}


	public String getTipoServizio() {
		return tipoServizio;
	}


	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}


	public String getCodiceTomb() {
		return codiceTomb;
	}


	public void setCodiceTomb(String codiceTomb) {
		this.codiceTomb = codiceTomb;
	}


	public BigDecimal getTotResScaduto() {
		return totResScaduto;
	}


	public void setTotResScaduto(BigDecimal totResScaduto) {
		this.totResScaduto = totResScaduto;
	}


	public BigDecimal getPercRiscossoCarico() {
		return percRiscossoCarico;
	}


	public void setPercRiscossoCarico(BigDecimal percRiscossoCarico) {
		this.percRiscossoCarico = percRiscossoCarico;
	}


	public BigDecimal getPercRendicontatoCarico() {
		return percRendicontatoCarico;
	}


	public void setPercRendicontatoCarico(BigDecimal percRendicontatoCarico) {
		this.percRendicontatoCarico = percRendicontatoCarico;
	}


	public BigDecimal getPercRimborsoCarico() {
		return percRimborsoCarico;
	}


	public void setPercRimborsoCarico(BigDecimal percRimborsoCarico) {
		this.percRimborsoCarico = percRimborsoCarico;
	}


	public BigDecimal getPercSgravatoCarico() {
		return percSgravatoCarico;
	}


	public void setPercSgravatoCarico(BigDecimal percSgravatoCarico) {
		this.percSgravatoCarico = percSgravatoCarico;
	}


	public BigDecimal getPercResiduoCarico() {
		return percResiduoCarico;
	}


	public BigDecimal getTotcaricoRendicontato() {
		return totcaricoRendicontato;
	}


	public void setTotcaricoRendicontato(BigDecimal totcaricoRendicontato) {
		this.totcaricoRendicontato = totcaricoRendicontato;
	}


	public BigDecimal getTotdimcaricoRendicontato() {
		return totdimcaricoRendicontato;
	}


	public void setTotdimcaricoRendicontato(BigDecimal totdimcaricoRendicontato) {
		this.totdimcaricoRendicontato = totdimcaricoRendicontato;
	}


	public BigDecimal getTotriscossoRendicontato() {
		return totriscossoRendicontato;
	}


	public void setTotriscossoRendicontato(BigDecimal totriscossoRendicontato) {
		this.totriscossoRendicontato = totriscossoRendicontato;
	}


	public BigDecimal getTotrimborsoRendicontato() {
		return totrimborsoRendicontato;
	}


	public void setTotrimborsoRendicontato(BigDecimal totrimborsoRendicontato) {
		this.totrimborsoRendicontato = totrimborsoRendicontato;
	}


	public void setPercResiduoCarico(BigDecimal percResiduoCarico) {
		this.percResiduoCarico = percResiduoCarico;
	}


	public EntrateDocumentiPage() { 
    	codiceSocieta="";
    	codiceEnte="";
    	codiceUtente="";

        impostaServizio="";
        codiceFiscale="";
        annoEmissione="";
        numeroEmissione="";
        tipoUfficio="";
        codiceUfficio="";
        numeroDocumento="";
        tipologiaServizio="";
        statoDocumento="";
        statoSospensione = "";
        statoProcedure = "";
        numeroBollettino = "";
        numeroIUV = "";
        totcarico = new BigDecimal(0);
        totrendicontato = new BigDecimal(0);
        totdimcarico = new BigDecimal(0);
        totriscosso= new BigDecimal(0);
        totrimborso = new BigDecimal(0);
        totfincarico = new BigDecimal(0);
        totresiduo =new BigDecimal(0);
        //dataUltimoAgg;

        percRiscossoCarico = new BigDecimal(0);
        percRendicontatoCarico = new BigDecimal(0);
        percRimborsoCarico = new BigDecimal(0);
        percSgravatoCarico = new BigDecimal(0);
        percResiduoCarico = new BigDecimal(0);
        percResScadutoCarico = new BigDecimal(0);
        
        totResScaduto = new BigDecimal(0);
        progrFlusso = "";
        tipoServizio = "";
    	codiceTomb = "";
    	chiaveTipoServ = "";
    	chiaveCodiceEnte = "";
    	
        totcaricoRendicontato = new BigDecimal(0);
        totdimcaricoRendicontato= new BigDecimal(0);
        totriscossoRendicontato= new BigDecimal(0);
        totrimborsoRendicontato= new BigDecimal(0);
        
    }


	public BigDecimal getPercResScadutoCarico() {
		return percResScadutoCarico;
	}


	public void setPercResScadutoCarico(BigDecimal percResScadutoCarico) {
		this.percResScadutoCarico = percResScadutoCarico;
	}


	public BigDecimal getTotcarico() {
		return totcarico;
	}



	public void setTotcarico(BigDecimal totcarico) {
		this.totcarico = totcarico;
	}



	public BigDecimal getTotrendicontato() {
		return totrendicontato;
	}



	public void setTotrendicontato(BigDecimal totrendicontato) {
		this.totrendicontato = totrendicontato;
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



	public BigDecimal getTotfincarico() {
		return totfincarico;
	}



	public void setTotfincarico(BigDecimal totfincarico) {
		this.totfincarico = totfincarico;
	}



	public BigDecimal getTotresiduo() {
		return totresiduo;
	}



	public void setTotresiduo(BigDecimal totresiduo) {
		this.totresiduo = totresiduo;
	}



	public Date getDataUltimoAgg() {
		return dataUltimoAgg;
	}



	public void setDataUltimoAgg(Date dataUltimoAgg) {
		this.dataUltimoAgg = dataUltimoAgg;
	}

    
	
	public String getImpostaServizio() {
		return impostaServizio == null?"":impostaServizio;
	}



	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}



	public String getAnnoEmissione() {
		return annoEmissione== null?"":annoEmissione;
	}



	public void setAnnoEmissione(String annoEmissione) {
		this.annoEmissione = annoEmissione;
	}



	public String getNumeroEmissione() {
		return numeroEmissione== null?"":numeroEmissione;
	}



	public void setNumeroEmissione(String numeroEmissione) {
		this.numeroEmissione = numeroEmissione;
	}



	public String getTipoUfficio() {
		return tipoUfficio== null?"":tipoUfficio;
	}



	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}



	public String getCodiceUfficio() {
		return codiceUfficio== null?"":codiceUfficio;
	}



	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}



	public String getNumeroDocumento() {
		return numeroDocumento== null?"":numeroDocumento;
	}



	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}



	public String getTipologiaServizio() {
		return tipologiaServizio== null?"":tipologiaServizio;
	}



	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}



	public String getStatoDocumento() {
		return statoDocumento== null?"":statoDocumento;
	}



	public void setStatoDocumento(String statoDocumento) {
		this.statoDocumento = statoDocumento;
	}



	public String getStatoSospensione() {
		return statoSospensione== null?"":statoSospensione;
	}



	public void setStatoSospensione(String statoSospensione) {
		this.statoSospensione = statoSospensione;
	}



	public String getStatoProcedure() {
		return statoProcedure== null?"":statoProcedure;
	}



	public void setStatoProcedure(String statoProcedure) {
		this.statoProcedure = statoProcedure;
	}



	public String getCodiceSocieta() {
		return codiceSocieta== null?"":codiceSocieta;
	}


	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}


	public String getCodiceUtente() {
		return codiceUtente== null?"":codiceUtente;
	}


	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}


	public String getCodiceEnte() {
		return codiceEnte== null?"":codiceEnte;
	}


	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}


	public String getCodiceFiscale() {
		return codiceFiscale== null?"":codiceFiscale;
	}


	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    


}