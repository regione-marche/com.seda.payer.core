package com.seda.payer.core.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoTracciato {
	
	private String xmlRT;
	private String xmlRPT;
	private long chiave;
	private String transazione;
	
	public InfoTracciato() {
	}
	
	public InfoTracciato(ResultSet rs) throws SQLException {
		if(rs==null) return;
		xmlRT = rs.getString("RPT_CRPTRT");
		xmlRPT = rs.getString("RPT_CRPTRPT");
		chiave = rs.getLong("RPT_PRPTPKEY");
		transazione = rs.getString("RPT_KTRAKTRA");
	}
	
	public String getXmlRT() {
		return xmlRT;
	}
	public void setXmlRT(String xmlRT) {
		this.xmlRT = xmlRT;
	}
	public String getXmlRPT() {
		return xmlRPT;
	}
	public void setXmlRPT(String xmlRPT) {
		this.xmlRPT = xmlRPT;
	}
	public long getChiave() {
		return chiave;
	}
	public void setChiave(long chiave) {
		this.chiave = chiave;
	}

	public String getTransazione() {
		return transazione;
	}

	public void setTransazione(String transazione) {
		this.transazione = transazione;
	}
}
