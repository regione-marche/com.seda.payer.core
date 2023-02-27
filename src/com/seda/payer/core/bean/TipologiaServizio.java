package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class TipologiaServizio extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String codiceTipologiaServizio;
    private java.lang.String descrizioneTipologiaServizio;
    private java.lang.String codiceOperatore;
    private Company company;

    public TipologiaServizio() { 
    	company = new Company();
    }

    public TipologiaServizio(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	codiceTipologiaServizio = data.getString("TSE_CTSECTSE");
    	descrizioneTipologiaServizio = data.getString("TSE_DTSEDTSE");
        codiceOperatore = data.getString("TSE_CTSECOPE");
        company = new Company(); {
        	company.setCompanyCode(data.getString("TSE_CSOCCSOC"));
        }    
    }
    
	public java.lang.String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}

	public void setCodiceTipologiaServizio(java.lang.String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}

	public java.lang.String getDescrizioneTipologiaServizio() {
		return descrizioneTipologiaServizio;
	}

	public void setDescrizioneTipologiaServizio(
			java.lang.String descrizioneTipologiaServizio) {
		this.descrizioneTipologiaServizio = descrizioneTipologiaServizio;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.company.getCompanyCode());
		arg.setString(2, this.codiceTipologiaServizio);
		arg.setString(3, this.descrizioneTipologiaServizio);
		arg.setString(4, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
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
