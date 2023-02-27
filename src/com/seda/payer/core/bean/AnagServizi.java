package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class AnagServizi extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String codiceAnagServizi;
	private java.lang.String descrizioneAnagServizi; 
    private java.lang.String operatorCode;

    public AnagServizi() { }
    
    public AnagServizi(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        codiceAnagServizi = data.getString("SER_CSERCSER");
        descrizioneAnagServizi = data.getString("SER_DSERDSER");
        operatorCode = data.getString("SER_CSERCOPE");
    }

	public java.lang.String getCodiceAnagServizi() {
		return codiceAnagServizi;
	}

	public void setCodiceAnagServizi(java.lang.String codiceAnagServizi) {
		this.codiceAnagServizi = codiceAnagServizi;
	}

	public java.lang.String getDescrizioneAnagServizi() {
		return descrizioneAnagServizi;
	}

	public void setDescrizioneAnagServizi(java.lang.String descrizioneAnagServizi) {
		this.descrizioneAnagServizi = descrizioneAnagServizi;
	}
	
	public java.lang.String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(java.lang.String operatorCode) {
		this.operatorCode = operatorCode;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceAnagServizi);
		arg.setString(2, this.descrizioneAnagServizi);
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