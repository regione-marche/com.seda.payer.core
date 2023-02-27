package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigEstrattoConto extends Lifecycle implements Serializable{
	
	private static final long serialVersionUID = 1L;
    private String urlIntegrazione;
    private String urlCooperazione;
    private String tipoIntegrazione;
    private String codiceOperatore;
    private String codiceUtenteSeda;
    private Ente ente;
    //inizio LP PG200060
    private String cupFlag;
    //fine LP PG200060
    
    
	public ConfigEstrattoConto() {
		ente = new Ente();
	}
	
	 public ConfigEstrattoConto(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;

	    	urlIntegrazione = data.getString("CEC_DCECSRVC");
	    	urlCooperazione = data.getString("CEC_DCECCOOP");
	    	tipoIntegrazione = data.getString("CEC_TCECINTE");
	    	codiceUtenteSeda = data.getString("CEC_CCECSEDA");
	    	codiceOperatore = data.getString("CEC_CCECCOPE");
	        //inizio LP PG200060
    		cupFlag = data.getString("CEC_FCECFCUP");
	        //fine LP PG200060
	    	ente = new Ente();
    		ente.getUser().getCompany().setCompanyCode(data.getString("CEC_CSOCCSOC"));
    		ente.getUser().setUserCode(data.getString("CEC_CUTECUTE"));
    		ente.getAnagEnte().setChiaveEnte(data.getString("CEC_KANEKENT"));
	 }

	
	
	public String getUrlIntegrazione() {
		return urlIntegrazione;
	}

	public void setUrlIntegrazione(String urlIntegrazione) {
		this.urlIntegrazione = urlIntegrazione;
	}

	public String getUrlCooperazione() {
		return urlCooperazione;
	}

	public void setUrlCooperazione(String urlCooperazione) {
		this.urlCooperazione = urlCooperazione;
	}

	public String getTipoIntegrazione() {
		return tipoIntegrazione;
	}

	public void setTipoIntegrazione(String tipoIntegrazione) {
		this.tipoIntegrazione = tipoIntegrazione;
	}

	public String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	public void setCodiceUtenteSeda(String codiceUtenteSeda) {
		this.codiceUtenteSeda = codiceUtenteSeda;
	}

	public String getCodiceUtenteSeda() {
		return codiceUtenteSeda;
	}

	public Ente getEnte() {
		return ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}
	
    //inizio LP PG200060
	public void setCupFlag(String cupFlag) {
		this.cupFlag = cupFlag;
	}
	
	public String getCupFlag() {
		return cupFlag;
	}
    //fine LP PG200060
	

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getEnte().getUser().getCompany().getCompanyCode());
		arg.setString(2, this.getEnte().getUser().getUserCode());
		arg.setString(3, this.getEnte().getAnagEnte().getChiaveEnte());
		arg.setString(4, this.getTipoIntegrazione());
		arg.setString(5, this.getUrlIntegrazione());
		arg.setString(6, this.getUrlCooperazione());
		arg.setString(7, this.getCodiceOperatore());
		arg.setString(8, this.getCodiceUtenteSeda());
	    //inizio LP PG200060
		arg.setString(9, (this.getCupFlag() != null ? this.getCupFlag() : "N"));
	    //fine LP PG200060
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getEnte().getUser().getCompany().getCompanyCode());
		arg.setString(2, this.getEnte().getUser().getUserCode());
		arg.setString(3, this.getEnte().getAnagEnte().getChiaveEnte());
		arg.setString(4, this.getTipoIntegrazione());
		arg.setString(5, this.getUrlIntegrazione());
		arg.setString(6, this.getUrlCooperazione());
		arg.setString(7, this.getCodiceOperatore());
		arg.setString(8, this.getCodiceUtenteSeda());
	    //inizio LP PG200060
		arg.setString(9, (this.getCupFlag() != null ? this.getCupFlag() : "N"));
	    //fine LP PG200060
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
    
   

}
