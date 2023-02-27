package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class ValidazioneRange extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codTipologiaServizio;
	private String tipoControllo;
	private String inizioRangeDa;
	private String fineRangeA;
	private String inizioRangePer;
	private String flagCIN;
	
	 public ValidazioneRange(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	setCodTipologiaServizio(data.getString("CES_CTSECTSE"));
    	setTipoControllo(data.getString("TIPO_CONTROLLO"));
    	setInizioRangeDa(data.getString("INIZIO_RANGE_DA"));
    	setFineRangeA(data.getString("FINE_RANGE_A"));
    	setInizioRangePer(data.getString("INIZIO_RANGE_PER"));
    	setFlagCIN(data.getString("FLAG_CIN"));
    }
	 
	public void setTipoControllo(String tipoControllo) {
		this.tipoControllo = tipoControllo;
	}

	public String getTipoControllo() {
		return tipoControllo;
	}

	public void setInizioRangeDa(String inizioRangeDa) {
		this.inizioRangeDa = inizioRangeDa;
	}

	public String getInizioRangeDa() {
		return inizioRangeDa;
	}

	public void setFineRangeA(String fineRangeA) {
		this.fineRangeA = fineRangeA;
	}

	public String getFineRangeA() {
		return fineRangeA;
	}

	public void setInizioRangePer(String inizioRangePer) {
		this.inizioRangePer = inizioRangePer;
	}

	public String getInizioRangePer() {
		return inizioRangePer;
	}

	public void setFlagCIN(String flagCIN) {
		this.flagCIN = flagCIN;
	}

	public String getFlagCIN() {
		return flagCIN;
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

	public void setCodTipologiaServizio(String codTipologiaServizio) {
		this.codTipologiaServizio = codTipologiaServizio;
	}

	public String getCodTipologiaServizio() {
		return codTipologiaServizio;
	}
	
}
