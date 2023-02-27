package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class AbilitaSistemaUtenteImpostaServizio extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String urlSistemaEsterno;
    private java.lang.String flagAttivazione;
    private java.lang.String codiceOperatore;
    private User user;
    private ImpostaServizio impostaServizio;

    
    public AbilitaSistemaUtenteImpostaServizio() { 
    	user = new User();
    	impostaServizio = new ImpostaServizio();
    }

    public AbilitaSistemaUtenteImpostaServizio(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        urlSistemaEsterno = data.getString("EXI_CEXICURL");
        flagAttivazione = data.getString("EXI_FEXIFATT");
        codiceOperatore = data.getString("EXI_CEXICOPE");
        user = new User(); {
        	user.getCompany().setCompanyCode(data.getString("EXI_CSOCCSOC"));
        	user.setUserCode(data.getString("EXI_CUTECUTE"));
        }
        impostaServizio = new ImpostaServizio(); {
        	impostaServizio.getTipologiaServizio().setCodiceTipologiaServizio(data.getString("EXI_CTSECTSE"));
        	impostaServizio.setCodiceImpostaServizio(data.getString("EXI_CISECISE"));
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ImpostaServizio getImpostaServizio() {
		return impostaServizio;
	}

	public void setImposta(ImpostaServizio impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.user.getCompany().getCompanyCode());
		arg.setString(2, this.user.getUserCode());
		arg.setString(3, this.impostaServizio.getTipologiaServizio().getCodiceTipologiaServizio());
		arg.setString(4, this.impostaServizio.getCodiceImpostaServizio());
		arg.setString(5, this.getUrlSistemaEsterno());
		arg.setString(6, this.getFlagAttivazione());
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