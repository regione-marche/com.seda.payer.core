package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class RangeAbiUtenteTipoServizioEnte extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private RangeAbiUtente range;
    private Ente ente;
    private TipologiaServizio tipoServizio;
    private java.lang.String flagCin;
    private java.lang.String tipoRange;

    
    public RangeAbiUtenteTipoServizioEnte() { 
    	range = new RangeAbiUtente();
    	ente = new Ente();
    	tipoServizio = new TipologiaServizio();
    }

    public RangeAbiUtenteTipoServizioEnte(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        range = new RangeAbiUtente(); {
        	range.setChiaveRange(data.getString("RNE_KRNEKRNE"));
        	range.getUser().getCompany().setCompanyCode(data.getString("RNE_CSOCCSOC"));
        	range.getUser().setUserCode(data.getString("RNE_CUTECUTE"));
        	range.setInizioRangeDa(data.getString("RNE_NRNENROD"));
        	range.setFineRangeA(data.getString("RNE_NRNENROA"));
        	range.setInizioRangePer(data.getString("RNE_NRNENROI"));
        	range.setCodiceOperatore(data.getString("RNE_CRNECOPE"));
        }
    	
        ente = new Ente(); {
        	ente.getAnagEnte().setChiaveEnte(data.getString("RNE_KANEKENT"));	
        }
        tipoServizio = new TipologiaServizio(); {
        	tipoServizio.setCodiceTipologiaServizio(data.getString("RNE_CTSECTSE"));
        } 
       flagCin = data.getString("RNE_FRNEFCIN");
       tipoRange = data.getString("RNE_FRNETIPO");
    }
    
    
	public java.lang.String getFlagCin() {
		return flagCin;
	}

	public void setFlagCin(java.lang.String flagCin) {
		this.flagCin = flagCin;
	}

	public java.lang.String getTipoRange() {
		return tipoRange;
	}

	public void setTipoRange(java.lang.String tipoRange) {
		this.tipoRange = tipoRange;
	}

	public RangeAbiUtente getRange() {
		return range;
	}

	public void setRange(RangeAbiUtente range) {
		this.range = range;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
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
		arg.setString(3, this.ente.getAnagEnte().getChiaveEnte());		
		arg.setString(4, this.tipoServizio.getCodiceTipologiaServizio());
		arg.setString(5, this.getTipoRange());
		arg.setString(6, this.range.getInizioRangeDa());
		arg.setString(7, this.range.getFineRangeA());
		arg.setString(8, this.range.getInizioRangePer());
		arg.setString(9, this.getFlagCin());
		arg.setString(10, this.range.getCodiceOperatore());  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.range.getChiaveRange());
		arg.setString(2, this.range.getUser().getCompany().getCompanyCode());
		arg.setString(3, this.range.getUser().getUserCode());
		arg.setString(4, this.ente.getAnagEnte().getChiaveEnte());		
		arg.setString(5, this.tipoServizio.getCodiceTipologiaServizio());
		arg.setString(6, this.getTipoRange());
		arg.setString(7, this.range.getInizioRangeDa());
		arg.setString(8, this.range.getFineRangeA());
		arg.setString(9, this.range.getInizioRangePer());
		arg.setString(10, this.getFlagCin());
		arg.setString(11, this.range.getCodiceOperatore());  // (OPERATORE ULTIMO AGGIORNAMENTO)

	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}
