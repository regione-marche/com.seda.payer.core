package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class TipologiaServizioAPPIO extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String codiceTipologiaServizio;
    private java.lang.String descrizioneTipologiaServizio;
    private java.lang.String codiceOperatore;


    public TipologiaServizioAPPIO() { 
    	
    }
    
    public TipologiaServizioAPPIO(ResultSet data) throws SQLException {
    	if (data == null)
    		return;
    	
    	codiceTipologiaServizio = data.getString("TIO_CTIOCTIO");
		descrizioneTipologiaServizio = data.getString("TIO_DTIODTIO");
		codiceOperatore = data.getString("TIO_CTIOCOPE");
          
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


	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceTipologiaServizio);
		arg.setString(2, this.descrizioneTipologiaServizio);
		arg.setString(3, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
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
