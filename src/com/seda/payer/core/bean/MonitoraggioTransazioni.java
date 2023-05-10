package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;
/**
 */
public class MonitoraggioTransazioni extends Lifecycle implements Serializable { 

	private static final long serialVersionUID = 1L;

    private java.lang.String codiceSocieta;
    private java.lang.String codiceUtente;
    private java.lang.String codiceEnte;
    private java.lang.String tipoUfficio;
    private java.lang.String codiceUfficio;
    private java.lang.String numeroDocumento;
    private java.lang.String numeroBollettinoPagoPA;
    
    
	public java.lang.String getCodiceSocieta() {
		return codiceSocieta;
	}
	public void setCodiceSocieta(java.lang.String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}
	public java.lang.String getCodiceUtente() {
		return codiceUtente;
	}
	public void setCodiceUtente(java.lang.String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}
	public java.lang.String getCodiceEnte() {
		return codiceEnte;
	}
	public void setCodiceEnte(java.lang.String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}
	public java.lang.String getTipoUfficio() {
		return tipoUfficio;
	}
	public void setTipoUfficio(java.lang.String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}
	public java.lang.String getCodiceUfficio() {
		return codiceUfficio;
	}
	public void setCodiceUfficio(java.lang.String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	public java.lang.String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(java.lang.String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public java.lang.String getNumeroBollettinoPagoPA() {
		return numeroBollettinoPagoPA;
	}
	public void setNumeroBollettinoPagoPA(java.lang.String numeroBollettinoPagoPA) {
		this.numeroBollettinoPagoPA = numeroBollettinoPagoPA;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}


}
