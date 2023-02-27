package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ufficio implements Serializable{
	
private static final long serialVersionUID = 1L;

	private String idUfficio;
	private String codiceUfficio;
	private String descrizioneIT;
	private String descrizioneDE;
	
	public Ufficio() {}

	public Ufficio(ResultSet data) throws SQLException {
    	if (data == null)
    		return;
    	setIdUfficio(data.getString("PAB_CPABIDUO"));
    	setCodiceUfficio(data.getString("PAB_CPABDDDL"));
    	setDescrizioneIT(data.getString("PAB_CPABDSIT"));
    	setDescrizioneDE(data.getString("PAB_CPABDSDE"));
    }

	public Ufficio(String idUfficio, String codiceUfficio, String descrizioneIT, String descrizioneDE) {
		super();
		this.idUfficio = idUfficio;
		this.codiceUfficio = codiceUfficio;
		this.descrizioneIT = descrizioneIT;
		this.descrizioneDE = descrizioneDE;
	}

	public String getIdUfficio() {
		return idUfficio;
	}

	public void setIdUfficio(String idUfficio) {
		this.idUfficio = idUfficio;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}
	
	public String getDescrizioneIT() {
		return descrizioneIT;
	}
	
	public void setDescrizioneIT(String descrizioneIT) {
		this.descrizioneIT = descrizioneIT;
	}

	public String getDescrizioneDE() {
		return descrizioneDE;
	}
	
	public void setDescrizioneDE(String descrizioneDE) {
		this.descrizioneDE = descrizioneDE;
	}
}
