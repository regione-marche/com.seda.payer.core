package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class InfoEnte implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codSocieta;
	private String codUtente;
	private String codEnte;
	private String chiaveEnte;

	
	
    public InfoEnte(String codSocieta, String codUtente, String codEnte) {
		super();
		this.codSocieta = codSocieta;
		this.codUtente = codUtente;
		this.codEnte = codEnte;
	}

	public InfoEnte(ResultSet data) throws SQLException 
    {
    	if (data == null)
    		return;

        codSocieta = data.getString("ENT_CSOCCSOC");
        codUtente = data.getString("ENT_CUTECUTE");
        codEnte = data.getString("ENT_CANECENT");
    }
    
    
        
	
	public String getCodSocieta() {
		return codSocieta;
	}
	public void setCodSocieta(String codSocieta) {
		this.codSocieta = codSocieta;
	}
	public String getCodUtente() {
		return codUtente;
	}
	public void setCodUtente(String codUtente) {
		this.codUtente = codUtente;
	}
	public String getCodEnte() {
		return codEnte;
	}
	public void setCodEnte(String codEnte) {
		this.codEnte = codEnte;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	
	
}
