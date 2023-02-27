package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class ImpostaServizio extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private java.lang.String codiceImpostaServizio;
    private java.lang.String descrizioneImpostaServizio;
    private java.lang.String codiceOperatore;
    private TipologiaServizio tipoServizio;
    private java.lang.String flagPagoPA;

    public ImpostaServizio() { 
    	tipoServizio = new TipologiaServizio();
    }

    public ImpostaServizio(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

        codiceImpostaServizio = data.getString("ISE_CISECISE");
        descrizioneImpostaServizio = data.getString("ISE_DISEDISE");
        codiceOperatore = data.getString("ISE_CISECOPE");
        tipoServizio = new TipologiaServizio(); {
        	tipoServizio.getCompany().setCompanyCode(data.getString("ISE_CSOCCSOC"));
        	tipoServizio.setCodiceTipologiaServizio(data.getString("ISE_CTSECTSE"));
        }
        flagPagoPA = data.getString("ISE_FISEFPPA");
    }

    
	public java.lang.String getCodiceImpostaServizio() {
		return codiceImpostaServizio;
	}

	public void setCodiceImpostaServizio(java.lang.String codiceImpostaServizio) {
		this.codiceImpostaServizio = codiceImpostaServizio;
	}

	public java.lang.String getDescrizioneImpostaServizio() {
		return descrizioneImpostaServizio;
	}

	public void setDescrizioneImpostaServizio(
			java.lang.String descrizioneImpostaServizio) {
		this.descrizioneImpostaServizio = descrizioneImpostaServizio;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public TipologiaServizio getTipologiaServizio() {
		return tipoServizio;
	}

	public void setTipologiaServizio(TipologiaServizio tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public java.lang.String getFlagPagoPA() {
		return flagPagoPA;
	}

	public void setFlagPagoPA(java.lang.String flagPagoPA) {
		this.flagPagoPA = flagPagoPA;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.tipoServizio.getCompany().getCompanyCode());
		arg.setString(2, this.tipoServizio.getCodiceTipologiaServizio());
		arg.setString(3, this.codiceImpostaServizio);
		arg.setString(4, this.descrizioneImpostaServizio);
		//arg.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		arg.setString(5, this.codiceOperatore);  // !! (OPERATORE ULTIMO AGGIORNAMENTO)
		//inizio LP PG200060
		//arg.setString(6, this.getFlagPagoPA());
		arg.setString(6, (this.flagPagoPA != null ? flagPagoPA : ""));
		//fine LP PG200060
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
