package com.seda.payer.core.bean;

import java.io.Serializable;

//import com.seda.payer.core.bean.Riversamento;
import com.seda.data.spi.PageInfo;


public class RuoliPagamentiCSV implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String codiceSocieta;

    private String codiceUtente;

    private String codiceEnte;

    private String tipoUfficio;

    private String codiceUfficio;

    private String codiceFiscale;

    private String annoRuolo;

    private String numeroRuolo;

    private String dataRegistrazioneDa;

    private String dataRegistrazioneA;

    private String dataEffettivaDa;

    private String dataEffettivaA;
    
    private String progrPartita;
        
    //output
    
    private String file;		 


    
    public RuoliPagamentiCSV() { 

        codiceSocieta = "";

        codiceUtente = "";

        codiceEnte = "";

        codiceFiscale = "";

        tipoUfficio = "";

        codiceUfficio = "";

        annoRuolo = "";

        numeroRuolo = "";

        dataRegistrazioneDa = "";

        dataRegistrazioneA = "";

        dataEffettivaDa = "";

        dataEffettivaA = "";
        
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

	public String getAnnoRuolo() {
		return annoRuolo;
	}



	public void setAnnoRuolo(String annoRuolo) {
		this.annoRuolo = annoRuolo;
	}



	public String getNumeroRuolo() {
		return numeroRuolo;
	}



	public void setNumeroRuolo(String numeroRuolo) {
		this.numeroRuolo = numeroRuolo;
	}



	public String getDataRegistrazioneDa() {
		return dataRegistrazioneDa;
	}



	public void setDataRegistrazioneDa(String dataRegistrazioneDa) {
		this.dataRegistrazioneDa = dataRegistrazioneDa;
	}



	public String getDataRegistrazioneA() {
		return dataRegistrazioneA;
	}



	public void setDataRegistrazioneA(String dataRegistrazioneA) {
		this.dataRegistrazioneA = dataRegistrazioneA;
	}



	public String getDataEffettivaDa() {
		return dataEffettivaDa;
	}



	public void setDataEffettivaDa(String dataEffettivaDa) {
		this.dataEffettivaDa = dataEffettivaDa;
	}



	public String getDataEffettivaA() {
		return dataEffettivaA;
	}



	public void setDataEffettivaA(String dataEffettivaA) {
		this.dataEffettivaA = dataEffettivaA;
	}



	public String getProgrPartita() {
		return progrPartita;
	}



	public void setProgrPartita(String progrPartita) {
		this.progrPartita = progrPartita;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getFile() {
		return file;
	}



	public void setFile(String file) {
		this.file = file;
	}





}