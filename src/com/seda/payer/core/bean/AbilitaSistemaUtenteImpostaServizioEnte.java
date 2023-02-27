package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class AbilitaSistemaUtenteImpostaServizioEnte extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String urlSistemaEsterno;
    private java.lang.String flagAttivazione;
    private java.lang.String codiceOperatore;
    private Ente ente;
    private ImpostaServizio impostaServizio;

    
    public AbilitaSistemaUtenteImpostaServizioEnte() { 
    	ente = new Ente();
    	impostaServizio = new ImpostaServizio();
    }

    public AbilitaSistemaUtenteImpostaServizioEnte(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        urlSistemaEsterno = data.getString("EXT_CEXTCURL");
        flagAttivazione = data.getString("EXT_FEXTFATT");
        codiceOperatore = data.getString("EXT_CEXTCOPE");
        ente = new Ente(); {
        	ente.getUser().getCompany().setCompanyCode(data.getString("EXT_CSOCCSOC"));
        	ente.getUser().setUserCode(data.getString("EXT_CUTECUTE"));
        	ente.getAnagEnte().setChiaveEnte(data.getString("EXT_KANEKENT"));  	
        }
        impostaServizio = new ImpostaServizio(); {
        	impostaServizio.getTipologiaServizio().setCodiceTipologiaServizio(data.getString("EXT_CTSECTSE"));
        	impostaServizio.setCodiceImpostaServizio(data.getString("EXT_CISECISE"));
        }   
    }


	public java.lang.String getUrlSistemaEsterno() {
		return urlSistemaEsterno;
	}

	public void setUrlSistemaEsterno(java.lang.String urlSistemaEsterno) {
		this.urlSistemaEsterno = urlSistemaEsterno;
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

	public ImpostaServizio getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(ImpostaServizio impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.ente.getUser().getCompany().getCompanyCode());
		arg.setString(2, this.ente.getUser().getUserCode());
		arg.setString(3, this.ente.getAnagEnte().getChiaveEnte());		
		arg.setString(4, this.impostaServizio.getTipologiaServizio().getCodiceTipologiaServizio());
		arg.setString(5, this.impostaServizio.getCodiceImpostaServizio());
		arg.setString(6, this.getUrlSistemaEsterno());
		arg.setString(7, this.getFlagAttivazione());
		arg.setString(8, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
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