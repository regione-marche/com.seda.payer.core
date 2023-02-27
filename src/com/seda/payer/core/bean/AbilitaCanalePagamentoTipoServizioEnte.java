package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class AbilitaCanalePagamentoTipoServizioEnte extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String flagAttivazione;
    private java.lang.String codiceOperatore;
    private Ente ente;
    private TipologiaServizio tipoServizio;
    private CanalePagamento canale;

    
    public AbilitaCanalePagamentoTipoServizioEnte() { 
    	ente = new Ente();
    	tipoServizio = new TipologiaServizio();
    	canale = new CanalePagamento();
    	
    }

    public AbilitaCanalePagamentoTipoServizioEnte(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        flagAttivazione = data.getString("CES_FCESFATT");
        codiceOperatore = data.getString("CES_CCESCOPE");
        ente = new Ente(); {
        	ente.getUser().getCompany().setCompanyCode(data.getString("CES_CSOCCSOC"));
        	ente.getUser().setUserCode(data.getString("CES_CUTECUTE"));
        	ente.getAnagEnte().setChiaveEnte(data.getString("CES_KANEKENT")); 	
        }
        tipoServizio = new TipologiaServizio(); {
        	tipoServizio.setCodiceTipologiaServizio(data.getString("CES_CTSECTSE"));
        }
        canale = new CanalePagamento(); {
        	canale.setChiaveCanalePagamento(data.getString("CES_KCANKCAN"));
        }
    }

    
	public java.lang.String getFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(java.lang.String flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
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

	public CanalePagamento getCanale() {
		return canale;
	}

	public void setCanale(CanalePagamento canale) {
		this.canale = canale;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.ente.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.ente.getUser().getUserCode());
		arg.setString(3, this.ente.getAnagEnte().getChiaveEnte());	
		arg.setString(4, this.canale.getChiaveCanalePagamento());
		arg.setString(5, this.tipoServizio.getCodiceTipologiaServizio());
		arg.setString(6, this.flagAttivazione);
		arg.setString(7, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
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