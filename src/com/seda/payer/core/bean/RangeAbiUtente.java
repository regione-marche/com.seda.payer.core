package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class RangeAbiUtente extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String chiaveRange;
	private java.lang.String inizioRangeDa;
    private java.lang.String inizioRangePer;
	private java.lang.String fineRangeA;
    private java.lang.String codiceOperatore;
    private User user;

    
    public RangeAbiUtente() { 
    	user = new User();
    }

    public RangeAbiUtente(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        chiaveRange = data.getString("RNU_KRNUKRNU");
        inizioRangeDa = data.getString("RNU_NRNUNROD");
        inizioRangePer = data.getString("RNU_NRNUNROI");
        fineRangeA = data.getString("RNU_NRNUNROA");
        codiceOperatore = data.getString("RNU_CRNUCOPE");
        user = new User(); {
        	user.getCompany().setCompanyCode(data.getString("RNU_CSOCCSOC"));
        	user.setUserCode(data.getString("RNU_CUTECUTE"));
        }
    }

    
	public java.lang.String getChiaveRange() {
		return chiaveRange;
	}

	public void setChiaveRange(java.lang.String chiaveRange) {
		this.chiaveRange = chiaveRange;
	}

	public java.lang.String getInizioRangeDa() {
		return inizioRangeDa;
	}

	public void setInizioRangeDa(java.lang.String inizioRangeDa) {
		this.inizioRangeDa = inizioRangeDa;
	}

	public java.lang.String getInizioRangePer() {
		return inizioRangePer;
	}

	public void setInizioRangePer(java.lang.String inizioRangePer) {
		this.inizioRangePer = inizioRangePer;
	}

	public java.lang.String getFineRangeA() {
		return fineRangeA;
	}

	public void setFineRangeA(java.lang.String fineRangeA) {
		this.fineRangeA = fineRangeA;
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
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.user.getCompany().getCompanyCode());
		arg.setString(2, this.user.getUserCode());
		arg.setString(3, this.inizioRangeDa);
		arg.setString(4, this.fineRangeA);
		arg.setString(5, this.inizioRangePer);
		arg.setString(6, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveRange);
		arg.setString(2, this.user.getCompany().getCompanyCode());
		arg.setString(3, this.user.getUserCode());
		arg.setString(4, this.inizioRangeDa);
		arg.setString(5, this.fineRangeA);
		arg.setString(6, this.inizioRangePer);
		arg.setString(7, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}
