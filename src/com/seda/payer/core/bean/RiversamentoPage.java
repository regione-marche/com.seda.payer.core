package com.seda.payer.core.bean;

import java.io.Serializable;

//import com.seda.payer.core.bean.Riversamento;
import com.seda.data.spi.PageInfo;
//import com.seda.payer.riversamento.facade.dto.Riversamento;


/**
 * 
 * @author ddiemdio
 *
 */


public class RiversamentoPage implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private java.lang.String codiceSocieta;
    private String codiceBelfiore;
	private java.lang.String codiceUtente;

//    private java.sql.Date dataRiversamento;

//    private java.sql.Date dataRiversamentoDa;
//    private java.sql.Date dataRiversamentoA;
    private String dataRiversamentoDa;
    private String dataRiversamentoA;
	
	
    
    private java.lang.String enteBeneficiario;
    private java.lang.String tipoRiversamento;
    private java.lang.String tipoRendicontazione;    
    private java.lang.String statoRiversamento;

    
    //output
    
    private String listXml;		 
    private PageInfo pageInfo;

    private java.math.BigDecimal totImportoContribuenti;
    private java.math.BigDecimal totImportoCittadino;
    private java.math.BigDecimal totCommissioniGateway;
    private java.math.BigDecimal totSpeseNotifica;
    private java.math.BigDecimal totCommissioneGestore;
    private java.math.BigDecimal totImportoRiversare;
    private java.math.BigDecimal totImportoRecuperare;

    private java.lang.Integer numeroRivFuturi;
    
    public RiversamentoPage() { 
    	codiceSocieta="";
        codiceBelfiore="";
    	codiceUtente="";

        dataRiversamentoDa="";
        dataRiversamentoA="";
    	
    	
        
        enteBeneficiario="";
        tipoRiversamento="";
        tipoRendicontazione="";    
        statoRiversamento="";
        
        numeroRivFuturi = 0;
    }
    
	public java.lang.String getCodiceSocieta() {
		return codiceSocieta;
	}


	public void setCodiceSocieta(java.lang.String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}


	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}


	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}


	public java.lang.String getCodiceUtente() {
		return codiceUtente;
	}


	public void setCodiceUtente(java.lang.String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}


	public String getDataRiversamentoDa() {
		return dataRiversamentoDa;
	}


	public void setDataRiversamentoDa(String dataRiversamentoDa) {
		this.dataRiversamentoDa = dataRiversamentoDa;
	}


	public String getDataRiversamentoA() {
		return dataRiversamentoA;
	}


	public void setDataRiversamentoA(String dataRiversamentoA) {
		this.dataRiversamentoA = dataRiversamentoA;
	}


	public java.lang.String getEnteBeneficiario() {
		return enteBeneficiario;
	}


	public void setEnteBeneficiario(java.lang.String enteBeneficiario) {
		this.enteBeneficiario = enteBeneficiario;
	}


	public java.lang.String getTipoRiversamento() {
		return tipoRiversamento;
	}


	public void setTipoRiversamento(java.lang.String tipoRiversamento) {
		this.tipoRiversamento = tipoRiversamento;
	}


	public java.lang.String getTipoRendicontazione() {
		return tipoRendicontazione;
	}


	public void setTipoRendicontazione(java.lang.String tipoRendicontazione) {
		this.tipoRendicontazione = tipoRendicontazione;
	}


	public java.lang.String getStatoRiversamento() {
		return statoRiversamento;
	}


	public void setStatoRiversamento(java.lang.String statoRiversamento) {
		this.statoRiversamento = statoRiversamento;
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


	public java.math.BigDecimal getTotImportoContribuenti() {
		return totImportoContribuenti;
	}


	public void setTotImportoContribuenti(
			java.math.BigDecimal totImportoContribuenti) {
		this.totImportoContribuenti = totImportoContribuenti;
	}


	public java.math.BigDecimal getTotImportoCittadino() {
		return totImportoCittadino;
	}


	public void setTotImportoCittadino(java.math.BigDecimal totImportoCittadino) {
		this.totImportoCittadino = totImportoCittadino;
	}


	public java.math.BigDecimal getTotCommissioniGateway() {
		return totCommissioniGateway;
	}


	public void setTotCommissioniGateway(java.math.BigDecimal totCommissioniGateway) {
		this.totCommissioniGateway = totCommissioniGateway;
	}


	public java.math.BigDecimal getTotSpeseNotifica() {
		return totSpeseNotifica;
	}


	public void setTotSpeseNotifica(java.math.BigDecimal totSpeseNotifica) {
		this.totSpeseNotifica = totSpeseNotifica;
	}


	public java.math.BigDecimal getTotCommissioneGestore() {
		return totCommissioneGestore;
	}


	public void setTotCommissioneGestore(java.math.BigDecimal totCommissioneGestore) {
		this.totCommissioneGestore = totCommissioneGestore;
	}


	public java.math.BigDecimal getTotImportoRiversare() {
		return totImportoRiversare;
	}


	public void setTotImportoRiversare(java.math.BigDecimal totImportoRiversare) {
		this.totImportoRiversare = totImportoRiversare;
	}

	public java.math.BigDecimal getTotImportoRecuperare() {
		return totImportoRecuperare;
	}

	public void setTotImportoRecuperare(java.math.BigDecimal totImportoRecuperare) {
		this.totImportoRecuperare = totImportoRecuperare;
	}

	public java.lang.Integer getNumeroRivFuturi() {
		return numeroRivFuturi;
	}

	public void setNumeroRivFuturi(java.lang.Integer numeroRivFuturi) {
		this.numeroRivFuturi = numeroRivFuturi;
	}


}