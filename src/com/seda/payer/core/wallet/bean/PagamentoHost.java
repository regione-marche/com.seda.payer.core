/**
 * PagamentoHost.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class PagamentoHost extends ModelAttributes implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private String codiceUtente;

    private String codiceEnte;

    private String numBorsellino;

    private String dataScadenzaRata;

    private BigDecimal importo;

    private String canale;

    private String flagAcquisizione;

    public PagamentoHost() {
    }
    
    public PagamentoHost(ResultSet data) throws SQLException, ParseException {
		if (data == null)
			return;

		setCodiceUtente(data.getString(1));
		setCodiceEnte(data.getString(2));
		setNumBorsellino(data.getString(3));
		String dataPag = data.getString(4);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal  = Calendar.getInstance();
		cal.setTime(df.parse(dataPag));
		setDataScadenzaRata(dataPag);
		setImporto(data.getBigDecimal(5));
		setCanale(data.getString(6)); 
		setFlagAcquisizione("Y"); //sempre acquisito
	}

    public PagamentoHost(
           java.lang.String codiceUtente,
           java.lang.String codiceEnte,
           java.lang.String numBorsellino,
           java.lang.String dataScadenzaRata,
           java.math.BigDecimal importo,
           java.lang.String canale,
           java.lang.String flagAcquisizione) {
           this.codiceUtente = codiceUtente;
           this.codiceEnte = codiceEnte;
           this.numBorsellino = numBorsellino;
           this.dataScadenzaRata = dataScadenzaRata;
           this.importo = importo;
           this.canale = canale;
           this.flagAcquisizione = flagAcquisizione;
    }


    /**
     * Gets the codiceUtente value for this PagamentoHost.
     * 
     * @return codiceUtente
     */
    public java.lang.String getCodiceUtente() {
        return codiceUtente;
    }


    /**
     * Sets the codiceUtente value for this PagamentoHost.
     * 
     * @param codiceUtente
     */
    public void setCodiceUtente(java.lang.String codiceUtente) {
        this.codiceUtente = codiceUtente;
    }


    /**
     * Gets the codiceEnte value for this PagamentoHost.
     * 
     * @return codiceEnte
     */
    public java.lang.String getCodiceEnte() {
        return codiceEnte;
    }


    /**
     * Sets the codiceEnte value for this PagamentoHost.
     * 
     * @param codiceEnte
     */
    public void setCodiceEnte(java.lang.String codiceEnte) {
        this.codiceEnte = codiceEnte;
    }


    /**
     * Gets the numBorsellino value for this PagamentoHost.
     * 
     * @return numBorsellino
     */
    public java.lang.String getNumBorsellino() {
        return numBorsellino;
    }


    /**
     * Sets the numBorsellino value for this PagamentoHost.
     * 
     * @param numBorsellino
     */
    public void setNumBorsellino(java.lang.String numBorsellino) {
        this.numBorsellino = numBorsellino;
    }


    /**
     * Gets the dataScadenzaRata value for this PagamentoHost.
     * 
     * @return dataScadenzaRata
     */
    public java.lang.String getDataScadenzaRata() {
        return dataScadenzaRata;
    }


    /**
     * Sets the dataScadenzaRata value for this PagamentoHost.
     * 
     * @param dataScadenzaRata
     */
    public void setDataScadenzaRata(java.lang.String dataScadenzaRata) {
        this.dataScadenzaRata = dataScadenzaRata;
    }


    /**
     * Gets the importo value for this PagamentoHost.
     * 
     * @return importo
     */
    public java.math.BigDecimal getImporto() {
        return importo;
    }


    /**
     * Sets the importo value for this PagamentoHost.
     * 
     * @param importo
     */
    public void setImporto(java.math.BigDecimal importo) {
        this.importo = importo;
    }


    /**
     * Gets the canale value for this PagamentoHost.
     * 
     * @return canale
     */
    public java.lang.String getCanale() {
        return canale;
    }


    /**
     * Sets the canale value for this PagamentoHost.
     * 
     * @param canale
     */
    public void setCanale(java.lang.String canale) {
        this.canale = canale;
    }


    /**
     * Gets the flagAcquisizione value for this PagamentoHost.
     * 
     * @return flagAcquisizione
     */
    public java.lang.String getFlagAcquisizione() {
        return flagAcquisizione;
    }


    /**
     * Sets the flagAcquisizione value for this PagamentoHost.
     * 
     * @param flagAcquisizione
     */
    public void setFlagAcquisizione(java.lang.String flagAcquisizione) {
        this.flagAcquisizione = flagAcquisizione;
    }

}
