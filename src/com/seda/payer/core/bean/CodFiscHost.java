package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class CodFiscHost implements Serializable {

	/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
	private String retCode;
	private String message;
	private String codFisc;

	//PG160170_001 GG 02022017 Modificato a seguito introduzione progressivo coobbligato in input
	public CodFiscHost(CallableStatement cs) throws SQLException {
		if (cs == null)
			return;
		
		try{
			this.setCodFisc(cs.getString(9));
			this.setRetCode(cs.getString(10));
			this.setMessage(cs.getString(11));
		}
		catch(Exception Ex){}
		return;
	}

	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
	}

	public String getCodFisc() {
		return codFisc;
	}
	
	
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
