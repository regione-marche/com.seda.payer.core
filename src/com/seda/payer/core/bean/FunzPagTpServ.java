package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class FunzPagTpServ extends Lifecycle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String labelFormPagamento;
	private java.lang.String nomeForm;
	private java.lang.String immissioneVal;
	private java.lang.String codiceOperatore;
	private ConfigUtenteTipoServizio tipServizio;
	//inizio PG130040 CG
	private String flagTipoValore; 		//se numerico o alfanumerico
	private String flagLunghezza;  		//se fissa o variabile
	private int lunghezza; 		   		//lunghezza
	private String flagDescrAlt;   		//se default o descrizione alternativa
	private String descrAlternativa; 	//descrizione alternativa
	//fine PG130040 CG
	

	public FunzPagTpServ() {
		this.tipServizio = new ConfigUtenteTipoServizio(); 
	}

	public FunzPagTpServ(ResultSet data) throws SQLException {
	    if (data == null)
	    	return;
	    labelFormPagamento = data.getString("CTS_DCTSLABE");
	    nomeForm = data.getString("CTS_KCTSNAME");
	    immissioneVal = data.getString("CTS_FCTSOBBL");
	    codiceOperatore = data.getString("CTS_CCTSCOPE");
	    tipServizio = new ConfigUtenteTipoServizio();{
	    	tipServizio.getTipoServizio().getCompany().setCompanyCode(data.getString("CTS_CSOCCSOC"));
	    	tipServizio.getUser().setUserCode(data.getString("CTS_CUTECUTE"));
	    	tipServizio.getTipoServizio().setCodiceTipologiaServizio(data.getString("CTS_CTSECTSE"));
	    flagTipoValore = data.getString("CTS_FCTSTIPO");
	    flagLunghezza = data.getString("CTS_FCTSLUNG");
	    lunghezza = data.getInt("CTS_NCTSLUNG");
	    flagDescrAlt = data.getString("CTS_FCTSDALT");
	    descrAlternativa = data.getString("CTS_DCTSDALT");
	    }
	}
	
	
	
	public String getFlagTipoValore() {
		return flagTipoValore;
	}

	public void setFlagTipoValore(String flagTipoValore) {
		this.flagTipoValore = flagTipoValore;
	}

	public String getFlagLunghezza() {
		return flagLunghezza;
	}

	public void setFlagLunghezza(String flagLunghezza) {
		this.flagLunghezza = flagLunghezza;
	}

	public int getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(int lunghezza) {
		this.lunghezza = lunghezza;
	}

	public String getFlagDescrAlt() {
		return flagDescrAlt;
	}

	public void setFlagDescrAlt(String flagDescrAlt) {
		this.flagDescrAlt = flagDescrAlt;
	}

	public String getDescrAlternativa() {
		return descrAlternativa;
	}

	public void setDescrAlternativa(String descrAlternativa) {
		this.descrAlternativa = descrAlternativa;
	}

	
	public java.lang.String getLabelFormPagamento() {
		return labelFormPagamento;
	}

	public void setLabelFormPagamento(java.lang.String labelFormPagamento) {
		this.labelFormPagamento = labelFormPagamento;
	}

	public java.lang.String getNomeForm() {
		return nomeForm;
	}

	public void setNomeForm(java.lang.String nomeForm) {
		this.nomeForm = nomeForm;
	}

	public java.lang.String getImmissioneVal() {
		return immissioneVal;
	}

	public void setImmissioneVal(java.lang.String immissioneVal) {
		this.immissioneVal = immissioneVal;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public ConfigUtenteTipoServizio getTipServizio() {
		return tipServizio;
	}

	public void setTipServizio(ConfigUtenteTipoServizio tipServizio) {
		this.tipServizio = tipServizio;
	}

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getTipServizio().getTipoServizio().getCompany().getCompanyCode());
		arg.setString(2, this.getTipServizio().getUser().getUserCode());
		arg.setString(3, this.getTipServizio().getTipoServizio().getCodiceTipologiaServizio());
		arg.setString(4, this.getNomeForm());
		arg.setString(5, this.getLabelFormPagamento());
		arg.setString(6, this.getImmissioneVal());
		arg.setString(7, this.getCodiceOperatore());
		arg.setString(8, this.getFlagTipoValore());
		arg.setString(9, this.getFlagLunghezza());
		arg.setInt(10, this.getLunghezza());
		arg.setString(11, this.getFlagDescrAlt());
		arg.setString(12, this.getDescrAlternativa());
		
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getTipServizio().getTipoServizio().getCompany().getCompanyCode());
		arg.setString(2, this.getTipServizio().getUser().getUserCode());
		arg.setString(3, this.getTipServizio().getTipoServizio().getCodiceTipologiaServizio());
		arg.setString(4, this.getNomeForm());
		arg.setString(5, this.getLabelFormPagamento());
		arg.setString(6, this.getImmissioneVal());
		arg.setString(7, this.getCodiceOperatore());
		arg.setString(8, this.getFlagTipoValore());
		arg.setString(9, this.getFlagLunghezza());
		arg.setInt(10, this.getLunghezza());
		arg.setString(11, this.getFlagDescrAlt());
		arg.setString(12, this.getDescrAlternativa());
		
	}

}
