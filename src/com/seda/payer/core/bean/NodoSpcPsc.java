package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NodoSpcPsc implements Serializable {

	/**
	 * PSP disponibile per NodoSPC
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String idCanale;
	private String idPsp;
	private String idIntermediario;
	private String tipoVersamento;
	private int priorita;
	private String disponibilitaServizio;
	private String descrizioneServizio;
	private String condizioniEconomiche;
	private String url;
	

	public String getIdCanale() {
		return idCanale;
	}

	public void setIdCanale(String idCanale) {
		this.idCanale = idCanale;
	}

	public String getIdPsp() {
		return idPsp;
	}

	public void setIdPsp(String idPsp) {
		this.idPsp = idPsp;
	}

	public String getIdIntermediario() {
		return idIntermediario;
	}

	public void setIdIntermediario(String idIntermediario) {
		this.idIntermediario = idIntermediario;
	}

	public String getTipoVersamento() {
		return tipoVersamento;
	}

	public void setTipoVersamento(String tipoVersamento) {
		this.tipoVersamento = tipoVersamento;
	}

	public int getPriorita() {
		return priorita;
	}

	public void setPriorita(int priorita) {
		this.priorita = priorita;
	}

	public String getDisponibilitaServizio() {
		return disponibilitaServizio;
	}

	public void setDisponibilitaServizio(String disponibilitaServizio) {
		this.disponibilitaServizio = disponibilitaServizio;
	}

	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}

	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}

	public String getCondizioniEconomiche() {
		return condizioniEconomiche;
	}

	public void setCondizioniEconomiche(String condizioniEconomiche) {
		this.condizioniEconomiche = condizioniEconomiche;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public NodoSpcPsc(ResultSet data) throws SQLException 
	{
    	if (data == null)
    		return;
    	setIdCanale(data.getString("PSC_KPSCKEY"));
       	setIdPsp(data.getString("PSC_KPSPKEY"));
       	setIdIntermediario(data.getString("PSC_CPSPIDI"));
    	setTipoVersamento(data.getString("PSC_CPSCTIP"));
    	setPriorita(data.getInt("PSC_IPSCPRI"));
    	setDisponibilitaServizio(data.getString("PSC_CPSCDIS"));
    	setDescrizioneServizio(data.getString("PSC_CPSCDES"));
    	setCondizioniEconomiche(data.getString("PSC_CPSCCON"));
    	setUrl(data.getString("PSC_CPSCURL"));
	}
	
	public NodoSpcPsc()  
	{
    	
	}
	



}
