package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigBancaDati extends Lifecycle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long	 idBancaDati;
	private String	 companyCode;
	private String	 cuteCute;
	private String	 name;
    private String	 url;
    private String 	 username;
    private String	 password;
    private String	 operatorCode;
    private Calendar updateDate;
    
    //PG22XX12_SB3 - inizio
    private String tipoIntegrazione;
    //PG22XX12_SB3 - fine
    
	public ConfigBancaDati() {
//		ente = new Ente();
	}
	public static Calendar getCalendarFromDate(java.util.Date date)
    {
          if (date == null) return null;
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          return cal;
    }
	
	 public ConfigBancaDati(ResultSet data) throws SQLException {
	    	if (data == null)
	    		return;

	    	idBancaDati =	data.getLong("CBD_KCBDKCBD");
    		companyCode =	data.getString("CBD_CSOCCSOC");
    		cuteCute 	=	data.getString("CBD_CUTECUTE");
	    	name		=	data.getString("CBD_DCBDNMBD"); 
	    	url			=	data.getString("CBD_DCBDCURL");
	    	username	=	data.getString("CBD_CCBDUSER");
	    	password	=	data.getString("CBD_CCBDPASS");
	    	operatorCode = data.getString("CBD_DCBDCOPE");
	    	updateDate	= getCalendarFromDate(data.getDate("CBD_GCBDDAGG"));
	    	tipoIntegrazione = data.getString("CBD_FCBDFINT");
	 }


	public long getIdBancaDati() {
		return idBancaDati;
	}

	public void setIdBancaDati(long idBancaDati) {
		this.idBancaDati = idBancaDati;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCuteCute() {
		return cuteCute;
	}

	public void setCuteCute(String cuteCute) {
		this.cuteCute = cuteCute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOperatorCode () {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Calendar getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Calendar updateDate) {
		this.updateDate = updateDate;
	}

	public String getTipoIntegrazione() {
		return tipoIntegrazione;
	}
	public void setTipoIntegrazione(String tipoIntegrazione) {
		this.tipoIntegrazione = tipoIntegrazione;
	}
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getCompanyCode());
		arg.setString(2, this.getCuteCute());
		arg.setString(3, this.getName());
		arg.setString(4, this.getUrl());
		arg.setString(5, this.getUsername());
		arg.setString(6, this.getPassword());
		arg.setString(7, this.getOperatorCode());
		arg.setString(8, this.getTipoIntegrazione()==null ? "I" : this.getTipoIntegrazione());
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setLong(1, this.getIdBancaDati());
		arg.setString(2, this.getCompanyCode());
		arg.setString(3, this.getCuteCute());
		arg.setString(4, this.getName());
		arg.setString(5, this.getUrl());
		arg.setString(6, this.getUsername());
		arg.setString(7, this.getPassword());
		arg.setString(8, this.getOperatorCode());
		arg.setString(9, this.getTipoIntegrazione()==null ? "I" : this.getTipoIntegrazione());
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
    
   

}
