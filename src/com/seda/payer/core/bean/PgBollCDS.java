package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class PgBollCDS extends Lifecycle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String retCode;
	private String message;

	public PgBollCDS(CallableStatement cs) throws SQLException {
		if (cs == null)
			return;
		
		try{
			this.setRetCode(cs.getString(3));
			this.setMessage(cs.getString(4));
		}
		catch(Exception Ex){}
		return;
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
	
	
	@Override
	public void onDelete(CallableStatement arg0) throws SQLException {		
	}

	@Override
	public void onLoad(CallableStatement arg0) throws SQLException {	
	}

	@Override
	public void onSave(CallableStatement arg0) throws SQLException {
	}

	@Override
	public void onUpdate(CallableStatement arg0) throws SQLException {	
	}

	

}
