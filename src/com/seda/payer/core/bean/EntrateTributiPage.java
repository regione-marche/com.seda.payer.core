package com.seda.payer.core.bean;

import java.io.Serializable;

import com.seda.data.spi.PageInfo;



public class EntrateTributiPage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private String codiceSocieta;
    private String codiceUtente;
	private String codiceEnte;
    private String tipoUfficio;
    private String codiceUfficio;
	private String impostaServizio;
    private String numeroDocumento;
    
    private String listXml;		 
    private PageInfo pageInfo;




	public EntrateTributiPage() { 
    	codiceSocieta="";
    	codiceEnte="";
    	codiceUtente="";

        impostaServizio="";
        tipoUfficio="";
        codiceUfficio="";
        numeroDocumento="";
         
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




	public String getImpostaServizio() {
		return impostaServizio;
	}




	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}




	public String getNumeroDocumento() {
		return numeroDocumento;
	}




	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
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