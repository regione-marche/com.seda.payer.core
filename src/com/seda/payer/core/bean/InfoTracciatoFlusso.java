package com.seda.payer.core.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoTracciatoFlusso {
	
	private String flussoXML;
	private String nomeFlusso;
	private long chiave;
	
	public InfoTracciatoFlusso(ResultSet rs) throws SQLException {
		if(rs==null) return;
		nomeFlusso = rs.getString("QUN_DQUNSUPP");
		chiave = rs.getLong("QUN_PQUNPKEY");
	}
	
	public String getFlussoXML() {
		return flussoXML;
	}
	public void setFlussoXML(String flussoXML) {
		this.flussoXML = flussoXML;
	}
	public String getNomeFlusso() {
		return nomeFlusso;
	}
	public void setNomeFlusso(String nomeFlusso) {
		this.nomeFlusso = nomeFlusso;
	}
	public long getChiave() {
		return chiave;
	}
	public void setChiave(long chiave) {
		this.chiave = chiave;
	}


}
