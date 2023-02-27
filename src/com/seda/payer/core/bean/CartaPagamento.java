package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class CartaPagamento extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String codiceCartaPagamento;
    private java.lang.String descrizioneCartaPagamento;
 
    public CartaPagamento() { 
    }

    public CartaPagamento(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        codiceCartaPagamento = data.getString("CAR_CCARCCAR");
        descrizioneCartaPagamento = data.getString("CAR_DCARDCAR");
    }

    
	public java.lang.String getCodiceCartaPagamento() {
		return codiceCartaPagamento;
	}

	public void setCodiceCartaPagamento(java.lang.String codiceCartaPagamento) {
		this.codiceCartaPagamento = codiceCartaPagamento;
	}

	public java.lang.String getDescrizioneCartaPagamento() {
		return descrizioneCartaPagamento;
	}

	public void setDescrizioneCartaPagamento(
			java.lang.String descrizioneCartaPagamento) {
		this.descrizioneCartaPagamento = descrizioneCartaPagamento;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getCodiceCartaPagamento());
		arg.setString(2, this.getDescrizioneCartaPagamento());
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