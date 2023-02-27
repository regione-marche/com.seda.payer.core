package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class CanalePagamento extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String chiaveCanalePagamento;
    private java.lang.String descrizioneCanalePagamento;
    private java.lang.String codiceOperatore;

    public CanalePagamento() { 
    }

    public CanalePagamento(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	chiaveCanalePagamento = data.getString("CAN_KCANKCAN");
        descrizioneCanalePagamento = data.getString("CAN_DCANDCAN");
        codiceOperatore = data.getString("CAN_CCANCOPE");
    }

	public java.lang.String getChiaveCanalePagamento() {
		return chiaveCanalePagamento;
	}

	public void setChiaveCanalePagamento(java.lang.String chiaveCanalePagamento) {
		this.chiaveCanalePagamento = chiaveCanalePagamento;
	}

	public java.lang.String getDescrizioneCanalePagamento() {
		return descrizioneCanalePagamento;
	}

	public void setDescrizioneCanalePagamento(
			java.lang.String descrizioneCanalePagamento) {
		this.descrizioneCanalePagamento = descrizioneCanalePagamento;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveCanalePagamento);
		arg.setString(2, this.descrizioneCanalePagamento);
		arg.setString(3, this.codiceOperatore);  // !! (OPERTAORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}