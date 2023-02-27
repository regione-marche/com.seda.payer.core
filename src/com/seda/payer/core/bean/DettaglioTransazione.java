package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;
/**
 */
public class DettaglioTransazione implements Serializable { 

	private static final long serialVersionUID = 1L;

    private java.lang.String codiceTransazione;
    private java.util.Calendar dataTransazione;
    private java.util.Calendar dataPagamento;
    private java.lang.String codiceAutorizzazioneBanca;
    private java.lang.String codiceIdentificativoBancaIUR;
    private java.lang.String codiceIUV;
    private java.math.BigDecimal importoTotaleTransazione;
    private java.lang.String tipologiaBollettino;
    private java.lang.String tipologiaServizio;
    private java.lang.String numeroBollettinoPagoPA;
    private java.math.BigDecimal importoBollettino;
    private java.lang.String numeroContoCorrente;
    private java.lang.String intestatarioContoCorrente;
    private java.lang.String numeroDocumento;
    private java.lang.String codiceFiscale;
    private java.lang.String denominazione;
    private java.lang.String flagRendicontazione;
    private java.lang.String flagRiconciliazione;

    
    public java.lang.String getCodiceTransazione() {
		return codiceTransazione;
	}

	public void setCodiceTransazione(java.lang.String codiceTransazione) {
		this.codiceTransazione = codiceTransazione;
	}

	public java.util.Calendar getDataTransazione() {
		return dataTransazione;
	}

	public void setDataTransazione(java.util.Calendar dataTransazione) {
		this.dataTransazione = dataTransazione;
	}

	public java.util.Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(java.util.Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public java.lang.String getCodiceAutorizzazioneBanca() {
		return codiceAutorizzazioneBanca;
	}

	public void setCodiceAutorizzazioneBanca(
			java.lang.String codiceAutorizzazioneBanca) {
		this.codiceAutorizzazioneBanca = codiceAutorizzazioneBanca;
	}

	public java.lang.String getCodiceIdentificativoBancaIUR() {
		return codiceIdentificativoBancaIUR;
	}

	public void setCodiceIdentificativoBancaIUR(
			java.lang.String codiceIdentificativoBancaIUR) {
		this.codiceIdentificativoBancaIUR = codiceIdentificativoBancaIUR;
	}

	public java.lang.String getCodiceIUV() {
		return codiceIUV;
	}

	public void setCodiceIUV(java.lang.String codiceIUV) {
		this.codiceIUV = codiceIUV;
	}

	public java.math.BigDecimal getImportoTotaleTransazione() {
		return importoTotaleTransazione;
	}

	public void setImportoTotaleTransazione(
			java.math.BigDecimal importoTotaleTransazione) {
		this.importoTotaleTransazione = importoTotaleTransazione;
	}

	public java.lang.String getTipologiaBollettino() {
		return tipologiaBollettino;
	}

	public void setTipologiaBollettino(java.lang.String tipologiaBollettino) {
		this.tipologiaBollettino = tipologiaBollettino;
	}

	public java.lang.String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(java.lang.String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public java.lang.String getNumeroBollettinoPagoPA() {
		return numeroBollettinoPagoPA;
	}

	public void setNumeroBollettinoPagoPA(java.lang.String numeroBollettinoPagoPA) {
		this.numeroBollettinoPagoPA = numeroBollettinoPagoPA;
	}

	public java.math.BigDecimal getImportoBollettino() {
		return importoBollettino;
	}

	public void setImportoBollettino(java.math.BigDecimal importoBollettino) {
		this.importoBollettino = importoBollettino;
	}

	public java.lang.String getNumeroContoCorrente() {
		return numeroContoCorrente;
	}

	public void setNumeroContoCorrente(java.lang.String numeroContoCorrente) {
		this.numeroContoCorrente = numeroContoCorrente;
	}

	public java.lang.String getIntestatarioContoCorrente() {
		return intestatarioContoCorrente;
	}

	public void setIntestatarioContoCorrente(
			java.lang.String intestatarioContoCorrente) {
		this.intestatarioContoCorrente = intestatarioContoCorrente;
	}

	public java.lang.String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(java.lang.String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public java.lang.String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(java.lang.String denominazione) {
		this.denominazione = denominazione;
	}

	public java.lang.String getFlagRendicontazione() {
		return flagRendicontazione;
	}

	public void setFlagRendicontazione(java.lang.String flagRendicontazione) {
		this.flagRendicontazione = flagRendicontazione;
	}

	public java.lang.String getFlagRiconciliazione() {
		return flagRiconciliazione;
	}

	public void setFlagRiconciliazione(java.lang.String flagRiconciliazione) {
		this.flagRiconciliazione = flagRiconciliazione;
	}

	public java.lang.String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(java.lang.String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

}
