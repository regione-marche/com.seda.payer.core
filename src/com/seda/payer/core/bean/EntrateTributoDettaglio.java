package com.seda.payer.core.bean;

import java.io.Serializable;



public class EntrateTributoDettaglio implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private String codiceSocieta;
    private String codiceUtente;
	private String codiceEnte;
    private String tipoUfficio;
    private String codiceUfficio;
	private String impostaServizio;
    private String numeroDocumento;
    private String progrFlusso;
    private String progrServ;
    private String progrTrib;
    private String annoTrib;
    private String codTrib;
    private String codTomb;
    
	private String listXml;		 

    
    
	public EntrateTributoDettaglio() { 
    	codiceSocieta="";
    	codiceEnte="";
    	codiceUtente="";

        impostaServizio="";
        tipoUfficio="";
        codiceUfficio="";
        numeroDocumento="";
        
        progrFlusso="";
        progrServ="";
        progrTrib="";
        annoTrib="";
        codTrib="";
        codTomb="";
         
    }
	
	public String getCodTomb() {
		return codTomb;
	}



	public void setCodTomb(String codTomb) {
		this.codTomb = codTomb;
	}

    
    
    public String getProgrFlusso() {
		return progrFlusso;
	}




	public void setProgrFlusso(String progrFlusso) {
		this.progrFlusso = progrFlusso;
	}




	public String getProgrServ() {
		return progrServ;
	}




	public void setProgrServ(String progrServ) {
		this.progrServ = progrServ;
	}




	public String getProgrTrib() {
		return progrTrib;
	}




	public void setProgrTrib(String progrTrib) {
		this.progrTrib = progrTrib;
	}




	public String getAnnoTrib() {
		return annoTrib;
	}




	public void setAnnoTrib(String annoTrib) {
		this.annoTrib = annoTrib;
	}




	public String getCodTrib() {
		return codTrib;
	}




	public void setCodTrib(String codTrib) {
		this.codTrib = codTrib;
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




	public static long getSerialversionuid() {
		return serialVersionUID;
	}


 

}