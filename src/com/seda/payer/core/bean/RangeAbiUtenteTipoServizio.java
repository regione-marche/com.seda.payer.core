package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class RangeAbiUtenteTipoServizio extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private RangeAbiUtente range;
    private TipologiaServizio tipoServizio;
    private java.lang.String flagCin;
    private java.lang.String tipoRange;

    
    public RangeAbiUtenteTipoServizio() { 
    	range = new RangeAbiUtente();
    	tipoServizio = new TipologiaServizio();
    }

    public RangeAbiUtenteTipoServizio(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        range = new RangeAbiUtente(); {
        	range.getUser().getCompany().setCompanyCode(data.getString("RNS_CSOCCSOC"));
        	range.getUser().setUserCode(data.getString("RNS_CUTECUTE"));
        	range.setChiaveRange(data.getString("RNS_KRNSKRNS"));
        	range.setInizioRangeDa(data.getString("RNS_NRNSNROD"));
        	range.setInizioRangePer(data.getString("RNS_NRNSNROI"));
        	range.setFineRangeA(data.getString("RNS_NRNSNROA"));
        	range.setCodiceOperatore(data.getString("RNS_CRNSCOPE"));
        }
        
        tipoServizio = new TipologiaServizio(); {
        	tipoServizio.getCompany().setCompanyCode(data.getString("RNS_CSOCCSOC"));
        	tipoServizio.setCodiceTipologiaServizio(data.getString("RNS_CTSECTSE"));
        } 
        flagCin=data.getString("RNS_FRNSFCIN");
        tipoRange= data.getString("RNS_FRNSTIPO");
    }

    
	public java.lang.String getTipoRange() {
		return tipoRange;
	}

	public void setTipoRange(java.lang.String tipoRange) {
		this.tipoRange = tipoRange;
	}

	public java.lang.String getFlagCin() {
		return flagCin;
	}

	public void setFlagCin(java.lang.String flagCin) {
		this.flagCin = flagCin;
	}

	public RangeAbiUtente getRange() {
		return range;
	}

	public void setRange(RangeAbiUtente range) {
		this.range = range;
	}

	public TipologiaServizio getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(TipologiaServizio tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.range.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.range.getUser().getUserCode());
		arg.setString(3, this.getFlagCin());
		arg.setString(4, this.getTipoRange());
		arg.setString(5, this.tipoServizio.getCodiceTipologiaServizio());
		arg.setString(6, this.range.getInizioRangeDa());
		arg.setString(7, this.range.getInizioRangePer());
		arg.setString(8, this.range.getFineRangeA());
		arg.setString(9, this.range.getCodiceOperatore());  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.range.getChiaveRange());
		arg.setString(2, this.range.getUser().getCompany().getCompanyCode());
		arg.setString(3, this.range.getUser().getUserCode());
		arg.setString(4, this.getFlagCin());
		arg.setString(5, this.getTipoRange());
		arg.setString(6, this.tipoServizio.getCodiceTipologiaServizio());
		arg.setString(7, this.range.getInizioRangeDa());
		arg.setString(8, this.range.getInizioRangePer());
		arg.setString(9, this.range.getFineRangeA());
		arg.setString(10, this.range.getCodiceOperatore());  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}