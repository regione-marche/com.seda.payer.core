package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class RangeAbiUtenteEnte extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private RangeAbiUtente range;
    private Ente ente;

    
    public RangeAbiUtenteEnte() { 
    	range = new RangeAbiUtente();
    	ente = new Ente();
    }

    public RangeAbiUtenteEnte(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        range = new RangeAbiUtente(); {
        	range.getUser().getCompany().setCompanyCode(data.getString("RNC_CSOCCSOC"));
        	range.getUser().setUserCode(data.getString("RNC_CUTECUTE"));
        	range.setChiaveRange(data.getString("RNC_KRNCKRNC"));
        	range.setInizioRangeDa(data.getString("RNC_NRNCNROD"));
        	range.setInizioRangePer(data.getString("RNC_NRNCNROI"));
        	range.setFineRangeA(data.getString("RNC_NRNCNROA"));
        	range.setCodiceOperatore(data.getString("RNC_CRNCCOPE"));
        }
    	
        ente = new Ente(); {
        	ente.getAnagEnte().setChiaveEnte(data.getString("RNC_KANEKENT"));	
        }
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


	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.range.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.range.getUser().getUserCode());
		arg.setString(3, this.ente.getAnagEnte().getChiaveEnte());		
		arg.setString(4, this.range.getInizioRangeDa());
		arg.setString(5, this.range.getFineRangeA());
		arg.setString(6, this.range.getInizioRangePer());
		arg.setString(7, this.range.getCodiceOperatore());  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.range.getChiaveRange());
		arg.setString(2, this.range.getUser().getCompany().getCompanyCode());
		arg.setString(3, this.range.getUser().getUserCode());
		arg.setString(4, this.ente.getAnagEnte().getChiaveEnte());		
		arg.setString(5, this.range.getInizioRangeDa());
		arg.setString(6, this.range.getFineRangeA());
		arg.setString(7, this.range.getInizioRangePer());
		arg.setString(8, this.range.getCodiceOperatore());  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}
