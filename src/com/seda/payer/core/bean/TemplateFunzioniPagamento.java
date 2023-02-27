package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class TemplateFunzioniPagamento extends Lifecycle implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codTipologiaServizio;
	private String nomeCampo;
	private String flagObbligatorio;
	private String testoLabel;
	//inizio PG130040 PR
	private String flagTipoValore; 		//se numerico o alfanumerico
	private String flagLunghezza;  		//se fissa o variabile
	private int lunghezza; 		   		//lunghezza
	private String flagDescrAlt;   		//se default o descrizione alternativa
	private String descrAlternativa; 	//descrizione alternativa
	//fine PG130040 PR
	
	
	public TemplateFunzioniPagamento(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	setCodTipologiaServizio(data.getString("CTS_CTSECTSE"));
    	setNomeCampo(data.getString("CTS_KCTSNAME"));
    	setFlagObbligatorio(data.getString("CTS_FCTSOBBL"));
    	setTestoLabel(data.getString("CTS_DCTSLABE"));
    	//inizio PG130040 PR
    	setFlagTipoValore(data.getString("CTS_FCTSTIPO"));
    	setFlaglunghezza(data.getString("CTS_FCTSLUNG"));
    	setLunghezza(data.getInt("CTS_NCTSLUNG"));
    	setFlagDescrAlt(data.getString("CTS_FCTSDALT"));
    	setDescrAlternativa(data.getString("CTS_DCTSDALT"));
    	//fine PG130040 PR
    }
	
	public void setCodTipologiaServizio(String codTipologiaServizio) {
		this.codTipologiaServizio = codTipologiaServizio;
	}

	public String getCodTipologiaServizio() {
		return codTipologiaServizio;
	}

	public void setNomeCampo(String nomeCampo) {
		this.nomeCampo = nomeCampo;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public void setFlagObbligatorio(String flagObbligatorio) {
		this.flagObbligatorio = flagObbligatorio;
	}

	public String getFlagObbligatorio() {
		return flagObbligatorio;
	}

	public void setTestoLabel(String testoLabel) {
		this.testoLabel = testoLabel;
	}

	public String getTestoLabel() {
		return testoLabel;
	}
	
	//inizio PG130040 PR
	public void setFlagTipoValore(String flagTipoValore) {
		this.flagTipoValore = flagTipoValore;
	}

	public String getFlagTipoValore() {
		return flagTipoValore;
	}
	public void setFlaglunghezza(String flagLunghezza) {
		this.flagLunghezza = flagLunghezza;
	}

	public String getFlagLunghezza() {
		return flagLunghezza;
	}
	public void setLunghezza(int lunghezza) {
		this.lunghezza = lunghezza;
	}

	public int getLunghezza() {
		return lunghezza;
	}
	
	public void setFlagDescrAlt(String flagDescrAlt) {
		this.flagDescrAlt = flagDescrAlt;
	}

	public String getFlagDescrAlt() {
		return flagDescrAlt;
	}
	
	public void setDescrAlternativa(String descrAlternativa) {
		this.descrAlternativa = descrAlternativa;
	}

	public String getDescrAlternativa() {
		return descrAlternativa;
	}
	
	//fine   PG130040 PR
	
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


}
