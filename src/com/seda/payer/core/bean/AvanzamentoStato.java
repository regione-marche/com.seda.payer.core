package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.seda.data.spi.PageInfo;
import java.util.ArrayList;

/**
 * 
 * @author ddiemdio
 *
 */

@SuppressWarnings("unchecked")
public class AvanzamentoStato implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private java.lang.String codiceSocieta;
    private String codiceBelfiore;
	private java.lang.String codiceUtente;

    private String dataRiversamentoDa;
    private String dataRiversamentoA;
	
	
    
    private java.lang.String enteBeneficiario;
    private java.lang.String tipoRiversamento;
    private java.lang.String tipoRendicontazione;    
    private java.lang.String statoRiversamento;

    private java.lang.String operatore;
    private java.lang.String flagTipoRicerca;
    private int flagAVStato;

    private PageInfo pageInfo;
    private String order;

    private String emailUser;
    
    //output
    private int code;
    private String message;
    private ArrayList log;

    private int rivAnalizzati;
    private int rivAvanzati;
    private int rivNAvanzati;
	
    private int rivPrenotati;
	private BigDecimal rivTotPrenotato;
     
    public AvanzamentoStato() { 
    	codiceSocieta="";
        codiceBelfiore="";
    	codiceUtente="";

        dataRiversamentoDa="";
        dataRiversamentoA="";
    	
    	
        
        enteBeneficiario="";
        tipoRiversamento="";
        tipoRendicontazione="";    
        statoRiversamento="";

        
        operatore="";
        flagTipoRicerca="";
        flagAVStato=0;
        order="";
        
        emailUser = "";
        
        log = new ArrayList();
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


	public PageInfo getPageInfo() {
		return pageInfo;
	}


	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}


	public java.lang.String getOperatore() {
		return operatore;
	}

	public void setOperatore(java.lang.String operatore) {
		this.operatore = operatore;
	}

	public java.lang.String getFlagTipoRicerca() {
		return flagTipoRicerca;
	}

	public void setFlagTipoRicerca(java.lang.String flagTipoRicerca) {
		this.flagTipoRicerca = flagTipoRicerca;
	}

	public int getFlagAVStato() {
		return flagAVStato;
	}

	public void setFlagAVStato(int flagAVStato) {
		this.flagAVStato = flagAVStato;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public ArrayList getLog() {
		return log;
	}

	public void setLog(ArrayList log) {
		this.log = log;
	}

	public int getRivAnalizzati() {
		return rivAnalizzati;
	}

	public void setRivAnalizzati(int rivAnalizzati) {
		this.rivAnalizzati = rivAnalizzati;
	}

	public int getRivAvanzati() {
		return rivAvanzati;
	}

	public void setRivAvanzati(int rivAvanzati) {
		this.rivAvanzati = rivAvanzati;
	}

	public int getRivNAvanzati() {
		return rivNAvanzati;
	}

	public void setRivNAvanzati(int rivNAvanzati) {
		this.rivNAvanzati = rivNAvanzati;
	}

	public int getRivPrenotati() {
		return rivPrenotati;
	}

	public void setRivPrenotati(int rivPrenotati) {
		this.rivPrenotati = rivPrenotati;
	}

	public BigDecimal getRivTotPrenotato() {
		return rivTotPrenotato;
	}

	public void setRivTotPrenotato(BigDecimal rivTotPrenotato) {
		this.rivTotPrenotato = rivTotPrenotato;
	}

	
}