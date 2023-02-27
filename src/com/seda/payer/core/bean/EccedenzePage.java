package com.seda.payer.core.bean;

import java.io.Serializable;

//import com.seda.payer.core.bean.Riversamento;
import com.seda.data.spi.PageInfo;

/**
 * 
 * @author ddiemdio
 *
 */


public class EccedenzePage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private String codiceSocieta;
    private String siglaProvincia;
	private String codiceUtente;
    private String codiceEnte;
    private String codiceFiscale;
    private String nomeDoc;
    private String dataRicezioneDa;
    private String dataRicezioneA;
    private String esitoRimborso;
    private String strumento;
    private String dataOrdineDa;
    private String dataOrdineA;
        
    //output
    
    private String listXml;		 
    private PageInfo pageInfo;

    private java.math.BigDecimal totImportoRimborsiPos;
    private java.math.BigDecimal totImportoRimborsiNeg;
    private java.math.BigDecimal totImportoAssegniPos;
    private java.math.BigDecimal totImportoAssegniNeg;
    private java.math.BigDecimal totImportoBonificiPos;
    private java.math.BigDecimal totImportoBonificiNeg;

    
    public EccedenzePage() { 
    	codiceSocieta="";
        siglaProvincia="";
    	codiceUtente="";

        codiceEnte="";
        codiceFiscale="";
        nomeDoc="";
        dataRicezioneDa="";
        dataRicezioneA="";
        esitoRimborso="";
        strumento="";
        dataOrdineDa="";
        dataOrdineA="";
            
    }


	public String getCodiceSocieta() {
		return codiceSocieta;
	}


	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}


	public String getSiglaProvincia() {
		return siglaProvincia;
	}


	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
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


	public String getNomeDoc() {
		return nomeDoc;
	}


	public void setNomeDoc(String nomeDoc) {
		this.nomeDoc = nomeDoc;
	}


	public String getDataRicezioneDa() {
		return dataRicezioneDa;
	}


	public void setDataRicezioneDa(String dataRicezioneDa) {
		this.dataRicezioneDa = dataRicezioneDa;
	}


	public String getDataRicezioneA() {
		return dataRicezioneA;
	}


	public void setDataRicezioneA(String dataRicezioneA) {
		this.dataRicezioneA = dataRicezioneA;
	}


	public String getEsitoRimborso() {
		return esitoRimborso;
	}


	public void setEsitoRimborso(String esitoRimborso) {
		this.esitoRimborso = esitoRimborso;
	}


	public String getStrumento() {
		return strumento;
	}


	public void setStrumento(String strumento) {
		this.strumento = strumento;
	}


	public String getDataOrdineDa() {
		return dataOrdineDa;
	}


	public void setDataOrdineDa(String dataOrdineDa) {
		this.dataOrdineDa = dataOrdineDa;
	}


	public String getDataOrdineA() {
		return dataOrdineA;
	}


	public void setDataOrdineA(String dataOrdineA) {
		this.dataOrdineA = dataOrdineA;
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


	public java.math.BigDecimal getTotImportoRimborsiPos() {
		return totImportoRimborsiPos;
	}


	public void setTotImportoRimborsiPos(java.math.BigDecimal totImportoRimborsiPos) {
		this.totImportoRimborsiPos = totImportoRimborsiPos;
	}


	public java.math.BigDecimal getTotImportoRimborsiNeg() {
		return totImportoRimborsiNeg;
	}


	public void setTotImportoRimborsiNeg(java.math.BigDecimal totImportoRimborsiNeg) {
		this.totImportoRimborsiNeg = totImportoRimborsiNeg;
	}


	public java.math.BigDecimal getTotImportoAssegniPos() {
		return totImportoAssegniPos;
	}


	public void setTotImportoAssegniPos(java.math.BigDecimal totImportoAssegniPos) {
		this.totImportoAssegniPos = totImportoAssegniPos;
	}


	public java.math.BigDecimal getTotImportoAssegniNeg() {
		return totImportoAssegniNeg;
	}


	public void setTotImportoAssegniNeg(java.math.BigDecimal totImportoAssegniNeg) {
		this.totImportoAssegniNeg = totImportoAssegniNeg;
	}


	public java.math.BigDecimal getTotImportoBonificiPos() {
		return totImportoBonificiPos;
	}


	public void setTotImportoBonificiPos(java.math.BigDecimal totImportoBonificiPos) {
		this.totImportoBonificiPos = totImportoBonificiPos;
	}


	public java.math.BigDecimal getTotImportoBonificiNeg() {
		return totImportoBonificiNeg;
	}


	public void setTotImportoBonificiNeg(java.math.BigDecimal totImportoBonificiNeg) {
		this.totImportoBonificiNeg = totImportoBonificiNeg;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    


}