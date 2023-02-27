package com.seda.payer.core.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Aea {

	private String codiceCausale;
	private String esitoCausale;
	private String descrizioneCausale;
	
	public Aea(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	this.setCodiceCausale(data.getString("AEA_CAEACODE"));
    	this.setEsitoCausale(data.getString("AEA_CAEAESIT"));
    	this.setDescrizioneCausale(data.getString("AEA_DAEADESC"));
    }

	public void setCodiceCausale(String codiceCausale) {
		this.codiceCausale = codiceCausale;
	}

	public String getCodiceCausale() {
		return codiceCausale;
	}

	public void setEsitoCausale(String esitoCausale) {
		this.esitoCausale = esitoCausale;
	}

	public String getEsitoCausale() {
		return esitoCausale;
	}

	public void setDescrizioneCausale(String descrizioneCausale) {
		this.descrizioneCausale = descrizioneCausale;
	}

	public String getDescrizioneCausale() {
		return descrizioneCausale;
	}
}
