package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class TxUserAddress    extends Lifecycle  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * TRA_DTRAINDI	VARCHAR(256) INDIRIZZO CONTRIBUENTE PER INVIO POSTA ORDINARIA
	 */
	private String indirizzoInvioPosta;
	/*
	 * TRA_CTRACCAP CHAR(5) CAP CONTRIBUENTE PER INVIO POSTA ORDINARIA
	 */
	private String capInvioPosta;
	/*
	 * TRA_DTRACITT	VARCHAR(50)	CITTA CONTRIBUENTE PER INVIO POSTA ORDINARIA
	 */
	private String cittaInvioPosta;	
	/*
	 * TRA_CTRAPROV	CHAR(2)	PROVINCIA CONTRIBUENTE PER INVIO POSTA ORDINARIA
	 */
	private String provinciaInvioPosta;
	
	public TxUserAddress(){}
	
	public TxUserAddress(ResultSet data) throws SQLException
	{
		if(data == null) return;
		indirizzoInvioPosta = data.getString("TRA_DTRAINDI");
		capInvioPosta = data.getString("TRA_CTRACCAP");
		cittaInvioPosta = data.getString("TRA_DTRACITT");
		provinciaInvioPosta = data.getString("TRA_CTRAPROV");
	}

	public String getIndirizzoInvioPosta() {
		return indirizzoInvioPosta;
	}

	public String getCapInvioPosta() {
		return capInvioPosta;
	}

	public String getCittaInvioPosta() {
		return cittaInvioPosta;
	}

	public String getProvinciaInvioPosta() {
		return provinciaInvioPosta;
	}

	public void setIndirizzoInvioPosta(String indirizzoInvioPosta) {
		this.indirizzoInvioPosta = indirizzoInvioPosta;
	}

	public void setCapInvioPosta(String capInvioPosta) {
		this.capInvioPosta = capInvioPosta;
	}

	public void setCittaInvioPosta(String cittaInvioPosta) {
		this.cittaInvioPosta = cittaInvioPosta;
	}

	public void setProvinciaInvioPosta(String provinciaInvioPosta) {
		this.provinciaInvioPosta = provinciaInvioPosta;
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

}
