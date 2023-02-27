package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class CostiNotifica extends Lifecycle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.math.BigDecimal costoSms;
	private java.math.BigDecimal costoPostaOrdinaria;
	private java.lang.String codiceOperatore;
	private User user;

	
	
	public CostiNotifica() {
		user = new User();
	}
	
	public CostiNotifica(ResultSet data) throws SQLException {
	    if (data == null)
	    	return;
	    costoSms = data.getBigDecimal("CSN_ICSNCSMS");
	    costoPostaOrdinaria = data.getBigDecimal("CSN_ICSNPOST");
	    codiceOperatore = data.getString("CSN_GCSNGAGG");
	    user = new User();{
	    	user.getCompany().setCompanyCode(data.getString("CSN_CSOCCSOC"));
	    	user.setUserCode(data.getString("CSN_CUTECUTE"));
	    }
	}
	
	public java.math.BigDecimal getCostoSms() {
		return costoSms;
	}

	public void setCostoSms(java.math.BigDecimal costoSms) {
		this.costoSms = costoSms;
	}

	public java.math.BigDecimal getCostoPostaOrdinaria() {
		return costoPostaOrdinaria;
	}

	public void setCostoPostaOrdinaria(java.math.BigDecimal costoPostaOrdinaria) {
		this.costoPostaOrdinaria = costoPostaOrdinaria;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		arg.setString(1, this.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.getUser().getUserCode());
		arg.setBigDecimal(3, this.getCostoPostaOrdinaria());
		arg.setBigDecimal(4, this.getCostoSms());
		arg.setString(5, this.getCodiceOperatore());	
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.getUser().getUserCode());
		arg.setBigDecimal(3, this.getCostoPostaOrdinaria());
		arg.setBigDecimal(4, this.getCostoSms());
		arg.setString(5, this.getCodiceOperatore());
		
	}
	

}
