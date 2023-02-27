package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class TxmanagerUser  extends Lifecycle  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * PYPRFTB.PRF_CPRFPROF  
	 */
	private String codiceProfilo;
	/*
	 * PYPRFTB.PRF_NPRFLIVE 
	 */
	private int livelloProfilo;
	/*
	 * PYUSRTB.USR_CSOCCSOC
	 */
	private String codiceSocieta;
	/*
	 * PYUSRTB.USR_CUTECUTE
	 */
	private String codiceUtente;
	/*
	 * PYUSRTB.USR_KANEKENT_CON
	 */
	private String chiaveEnteConsorzio;
	/*
	 * PYUSRTB.USR_KANEKENT_ENT 
	 */
	private String chiaveEnteConsorziato;
	
	public TxmanagerUser() {}
	
	public TxmanagerUser(ResultSet data) throws SQLException
	{
		if(data == null) return;
		codiceProfilo = data.getString("PRF_CPRFPROF");
		livelloProfilo = data.getInt("PRF_NPRFLIVE");
		codiceSocieta = data.getString("USR_CSOCCSOC");
		codiceUtente = data.getString("USR_CUTECUTE");
		chiaveEnteConsorzio = data.getString("USR_KANEKENT_CON");
		chiaveEnteConsorziato = data.getString("USR_KANEKENT_ENT");
		
	}

	public String getCodiceProfilo() {
		return codiceProfilo;
	}

	public int getLivelloProfilo() {
		return livelloProfilo;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public String getChiaveEnteConsorzio() {
		return chiaveEnteConsorzio;
	}

	public String getChiaveEnteConsorziato() {
		return chiaveEnteConsorziato;
	}

	public void setCodiceProfilo(String codiceProfilo) {
		this.codiceProfilo = codiceProfilo;
	}

	public void setLivelloProfilo(int livelloProfilo) {
		this.livelloProfilo = livelloProfilo;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public void setChiaveEnteConsorzio(String chiaveEnteConsorzio) {
		this.chiaveEnteConsorzio = chiaveEnteConsorzio;
	}

	public void setChiaveEnteConsorziato(String chiaveEnteConsorziato) {
		this.chiaveEnteConsorziato = chiaveEnteConsorziato;
	}

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
