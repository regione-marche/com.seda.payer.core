package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class Company extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String companyCode;
	private java.lang.String companyDescription;
	private java.util.Calendar dataUltimoAggiornamento;
    private java.lang.String operatorCode;

    public Company() { }
    
    public Company(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        companyCode = data.getString("SOC_CSOCCSOC");
        companyDescription = data.getString("SOC_DSOCDSOC");
        dataUltimoAggiornamento = Calendar.getInstance();
        operatorCode = data.getString("SOC_CSOCCOPE");
    }

	public java.util.Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(java.util.Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public java.lang.String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(java.lang.String companyCode) {
		this.companyCode = companyCode;
	}

	public java.lang.String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(java.lang.String companyDescription) {
		this.companyDescription = companyDescription;
	}
	public java.lang.String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(java.lang.String operatorCode) {
		this.operatorCode = operatorCode;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.companyCode);
		arg.setString(2, this.companyDescription);
		//arg.getDate(3, this.dataUltimoAggiornamento);
		arg.setString(3, this.operatorCode);
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