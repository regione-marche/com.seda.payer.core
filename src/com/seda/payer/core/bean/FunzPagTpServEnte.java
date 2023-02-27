package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class FunzPagTpServEnte extends Lifecycle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String labelFormFunPagamento;
	private java.lang.String immissioneVal;
	private java.lang.String nomeForm;
	private java.lang.String codiceOperatore;
	private ConfigUtenteTipoServizioEnte tipServizio;
	//inizio PG130040 CG
	private String flagTipoValore; 		//se numerico o alfanumerico
	private String flagLunghezza;  		//se fissa o variabile
	private int lunghezza; 		   		//lunghezza
	private String flagDescrAlt;   		//se default o descrizione alternativa
	private String descrAlternativa; 	//descrizione alternativa
	//fine PG130040 CG
	
	public FunzPagTpServEnte() {
		this.tipServizio = new ConfigUtenteTipoServizioEnte();
	}

	public FunzPagTpServEnte(ResultSet data) throws SQLException {
	    if (data == null)
	    	return;
	    labelFormFunPagamento = data.getString("CTE_DCTELABE");
	    nomeForm = data.getString("CTE_KCTENAME");
	    immissioneVal = data.getString("CTE_FCTEOBBL");
	    codiceOperatore = data.getString("CTE_CCTECOPE");
	    tipServizio = new ConfigUtenteTipoServizioEnte();{
	    	tipServizio.getTipoServizio().getCompany().setCompanyCode(data.getString("CTE_CSOCCSOC"));
	    	tipServizio.getEnte().getUser().setUserCode(data.getString("CTE_CUTECUTE"));
	    	tipServizio.getEnte().getAnagEnte().setChiaveEnte(data.getString("CTE_KANEKENT"));
	    	tipServizio.getTipoServizio().setCodiceTipologiaServizio(data.getString("CTE_CTSECTSE"));
	    flagTipoValore = data.getString("CTE_FCTETIPO");
	 	flagLunghezza = data.getString("CTE_FCTELUNG");
	 	lunghezza = data.getInt("CTE_NCTELUNG");
	 	flagDescrAlt = data.getString("CTE_FCTEDALT");
	 	descrAlternativa = data.getString("CTE_DCTEDALT");
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


	public java.lang.String getLabelFormFunPagamento() {
		return labelFormFunPagamento;
	}

	public void setLabelFormFunPagamento(java.lang.String labelFormFunPagamento) {
		this.labelFormFunPagamento = labelFormFunPagamento;
	}

	public ConfigUtenteTipoServizioEnte getTipServizio() {
		return tipServizio;
	}

	public void setTipServizio(ConfigUtenteTipoServizioEnte tipServizio) {
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
		arg.setString(2, this.getTipServizio().getEnte().getUser().getUserCode());
		arg.setString(3, this.getTipServizio().getEnte().getAnagEnte().getChiaveEnte());
		arg.setString(4, this.getTipServizio().getTipoServizio().getCodiceTipologiaServizio());
		arg.setString(5, this.getNomeForm());
		arg.setString(6, this.getLabelFormFunPagamento());
		arg.setString(7, this.getImmissioneVal());
		arg.setString(8, this.getCodiceOperatore());
		arg.setString(9, this.getFlagTipoValore());
		arg.setString(10, this.getFlagLunghezza());
		arg.setInt(11, this.getLunghezza());
		arg.setString(12, this.getFlagDescrAlt());
		arg.setString(13, this.getDescrAlternativa());
		
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getTipServizio().getTipoServizio().getCompany().getCompanyCode());
		arg.setString(2, this.getTipServizio().getEnte().getUser().getUserCode());
		arg.setString(3, this.getTipServizio().getEnte().getAnagEnte().getChiaveEnte());
		arg.setString(4, this.getTipServizio().getTipoServizio().getCodiceTipologiaServizio());
		arg.setString(5, this.getNomeForm());
		arg.setString(6, this.getLabelFormFunPagamento());
		arg.setString(7, this.getImmissioneVal());
		arg.setString(8, this.getCodiceOperatore());
		arg.setString(9, this.getFlagTipoValore());
		arg.setString(10, this.getFlagLunghezza());
		arg.setInt(11, this.getLunghezza());
		arg.setString(12, this.getFlagDescrAlt());
		arg.setString(13, this.getDescrAlternativa());
		
	}

}
