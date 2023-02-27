package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class EsistenzaPagamento extends Lifecycle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String statoTransazione = "-1";
	private String idTransazione = "";
	private java.util.Calendar dataPagamento = Calendar.getInstance();
	private BigDecimal importoBollettino = BigDecimal.ZERO;
	private String tipoControllo = "";
	
	public EsistenzaPagamento() {
	}

	
	
	@Override
	public void onDelete(CallableStatement arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(CallableStatement arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSave(CallableStatement arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(CallableStatement arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}



	public void setStatoTransazione(String statoTransazione) {
		this.statoTransazione = statoTransazione;
	}



	public String getStatoTransazione() {
		return statoTransazione;
	}



	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}



	public String getIdTransazione() {
		return idTransazione;
	}



	public void setDataPagamento(java.util.Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}



	public java.util.Calendar getDataPagamento() {
		return dataPagamento;
	}



	public void setImportoBollettino(java.math.BigDecimal importoBollettino) {
		this.importoBollettino = importoBollettino;
	}



	public java.math.BigDecimal getImportoBollettino() {
		return importoBollettino;
	}



	public void setTipoControllo(String tipoControllo) {
		this.tipoControllo = tipoControllo;
	}



	public String getTipoControllo() {
		return tipoControllo;
	}






}
