package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class TotemTipologiaImposta extends Lifecycle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String codiceEnte;
	private String impostaServizio;
	private String tipologiaImposta;
	private Calendar dataUltimoAggiornamento;
	private String opertoreUltimoAggiornamento;
	

	public TotemTipologiaImposta(String codiceEnte, String impostaServizio, String tipologiaImposta, Calendar dataUltimoAggiornamento, String opertoreUltimoAggiornamento) {
		super();
		this.codiceEnte = codiceEnte;
		this.impostaServizio = impostaServizio;
		this.tipologiaImposta = tipologiaImposta;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
	}

	public TotemTipologiaImposta() {}

	public TotemTipologiaImposta(ResultSet data)  throws SQLException {
    	if (data == null)
    		return;

    	codiceEnte = data.getString("TIT_CTITCENT");
    	impostaServizio = data.getString("TIT_CTITCISE");
    	tipologiaImposta = data.getString("TIT_CTITTPIM");
		dataUltimoAggiornamento = Calendar.getInstance();
		opertoreUltimoAggiornamento = data.getString("TIT_CTITCOPE");
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	public String getTipologiaImposta() {
		return tipologiaImposta;
	}

	public void setTipologiaImposta(String tipologiaImposta) {
		this.tipologiaImposta = tipologiaImposta;
	}

	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOpertoreUltimoAggiornamento() {
		return opertoreUltimoAggiornamento;
	}

	public void setOpertoreUltimoAggiornamento(String opertoreUltimoAggiornamento) {
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
		this.setStringArgs(arg);
	}


	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		this.setStringArgs(arg);
	}
	
	private void setStringArgs(CallableStatement arg) throws SQLException {
		arg.setString(1, codiceEnte);
		arg.setString(2, impostaServizio);
		arg.setString(3, tipologiaImposta);
		arg.setString(4, opertoreUltimoAggiornamento);
	}

}
