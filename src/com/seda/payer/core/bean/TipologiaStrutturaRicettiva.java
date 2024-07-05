package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class TipologiaStrutturaRicettiva extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chiaveTipologiaStruttura;
	private String descrizioneTipologiaStruttura;
	private Calendar dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;

	public TipologiaStrutturaRicettiva() {}
	
	public TipologiaStrutturaRicettiva(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setChiaveTipologiaStruttura(data.getString("SSR_CSSRCSSR"));
    	setDescrizioneTipologiaStruttura(data.getString("SSR_DSSRDSSR"));
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(data.getTimestamp("SSR_GSSRGAGG").getTime());
    	setDataUltimoAggiornamento(cal);
    	setOperatoreUltimoAggiornamento(data.getString("SSR_CSSRCOPE"));
    }

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTipologiaStruttura);
		arg.setString(2, this.descrizioneTipologiaStruttura);
		arg.setString(3, this.operatoreUltimoAggiornamento);
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTipologiaStruttura);
		arg.setString(2, this.descrizioneTipologiaStruttura);
		arg.setString(3, this.operatoreUltimoAggiornamento);
	}

	public String getChiaveTipologiaStruttura() {
		return chiaveTipologiaStruttura;
	}

	public void setChiaveTipologiaStruttura(String chiaveTipologiaStruttura) {
		this.chiaveTipologiaStruttura = chiaveTipologiaStruttura;
	}

	public String getDescrizioneTipologiaStruttura() {
		return descrizioneTipologiaStruttura;
	}

	public void setDescrizioneTipologiaStruttura(
			String descrizioneTipologiaStruttura) {
		this.descrizioneTipologiaStruttura = descrizioneTipologiaStruttura;
	}

	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}
}
