package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class EnteConsorziato extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private Ente ente;
    private AnagEnte anagEnte;
    private java.lang.String codiceOperatore;

    public EnteConsorziato() { 
    	ente = new Ente();
    	anagEnte = new AnagEnte();
    }

    public EnteConsorziato(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        ente = new Ente(); {
        	ente.getUser().getCompany().setCompanyCode(data.getString("ENC_CSOCCSOC"));
        	ente.getUser().setUserCode(data.getString("ENC_CUTECUTE"));
        	ente.getAnagEnte().setChiaveEnte(data.getString("ENC_KANEKENT_CON"));
        }    
        anagEnte = new AnagEnte(); {
        	anagEnte.setChiaveEnte(data.getString("ENC_KANEKENT_ENT"));
        } 
        codiceOperatore = data.getString("ENC_CENCCOPE");
    }

  

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}

	public AnagEnte getAnagEnte() {
		return anagEnte;
	}

	public void setAnagEnte(AnagEnte anagEnte) {
		this.anagEnte = anagEnte;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.ente.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.ente.getUser().getUserCode());
		arg.setString(3, this.ente.getAnagEnte().getChiaveEnte());
		arg.setString(4, this.anagEnte.getChiaveEnte());
		arg.setString(5, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
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
