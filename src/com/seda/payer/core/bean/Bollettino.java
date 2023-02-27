package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;


public class Bollettino extends Lifecycle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private java.lang.String tipoBollettino;
	private java.lang.String desTipoBollettino;
	private java.lang.String modCompBollettino;
	private java.lang.String desCompBollettino;
	private java.lang.String tipoFlusso;
	private java.util.Calendar dataUltimoAggiornamento;
	private java.lang.String opertoreUltimoAggiornamento;
	
	
	public Bollettino(String tipoBollettino, String desTipoBollettino, String modCompBollettino,
			String desCompBollettino, String tipoFlusso,
			java.util.Calendar dataUltimoAggiornamento, String opertoreUltimoAggiornamento) {
		this.tipoBollettino = tipoBollettino;
		this.desTipoBollettino = desTipoBollettino;
		this.modCompBollettino = modCompBollettino;
		this.desCompBollettino = desCompBollettino;
		this.tipoFlusso = tipoFlusso;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
	}


	public Bollettino() {
	
	}

	public Bollettino(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        tipoBollettino = data.getString("BOL_TBOLTBOL");
		desTipoBollettino = data.getString("BOL_DBOLDBOL");
		modCompBollettino = data.getString("BOL_TBOLMODA");
		desCompBollettino = data.getString("BOL_DBOLMODA");
		tipoFlusso = data.getString("BOL_TBOLTFLU");
		dataUltimoAggiornamento = Calendar.getInstance();;
		opertoreUltimoAggiornamento = data.getString("BOL_CBOLCOPE");
    }

	public java.lang.String getTipoBollettino() {
		return tipoBollettino;
	}


	public void setTipoBolletino(java.lang.String tipoBollettino) {
		this.tipoBollettino = tipoBollettino;
	}


	public java.lang.String getDesTipoBollettino() {
		return desTipoBollettino;
	}


	public void setDesTipoBollettino(java.lang.String desTipoBollettino) {
		this.desTipoBollettino = desTipoBollettino;
	}


	public java.lang.String getModCompBollettino() {
		return modCompBollettino;
	}


	public void setModCompBollettino(java.lang.String modCompBollettino) {
		this.modCompBollettino = modCompBollettino;
	}


	public java.lang.String getDesCompBollettino() {
		return desCompBollettino;
	}


	public void setDesCompBollettino(java.lang.String desCompBollettino) {
		this.desCompBollettino = desCompBollettino;
	}


	public java.lang.String getTipoFlusso() {
		return tipoFlusso;
	}


	public void setTipoFlusso(java.lang.String tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}


	public java.util.Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}


	public void setDataUltimoAggiornamento(java.util.Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}


	public java.lang.String getOpertoreUltimoAggiornamento() {
		return opertoreUltimoAggiornamento;
	}


	public void setOpertoreUltimoAggiornamento(
			java.lang.String opertoreUltimoAggiornamento) {
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
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
		arg.setString(1, this.tipoBollettino);
		arg.setString(2, this.desTipoBollettino);
		arg.setString(3, this.modCompBollettino);
		arg.setString(4, this.desCompBollettino);
		arg.setString(5, this.tipoFlusso);
		arg.setString(6, this.opertoreUltimoAggiornamento);
		
	}


	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.tipoBollettino);
		arg.setString(2, this.desTipoBollettino);
		arg.setString(3, this.modCompBollettino);
		arg.setString(4, this.desCompBollettino);
		arg.setString(5, this.tipoFlusso);
		arg.setString(6, this.opertoreUltimoAggiornamento);
			
	}
	
	
	
}
