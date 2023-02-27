package com.seda.payer.core.riconciliazionemt.bean;

import java.math.BigDecimal;
import java.util.Calendar;

public class Transazione {
	private String id;
	private long idMdc;
	private String iur;
	private String iuv;
	private BigDecimal importo;
	private Calendar dataTransazione;

	private BigDecimal importoDa;
    private BigDecimal importoA;
    private Calendar dataTransazioneDa;
    private Calendar dataTransazioneA;
    
    public Transazione() {
    	
    }
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIur() {
		return iur;
	}
	public void setIur(String iur) {
		this.iur = iur;
	}
	public String getIuv() {
		return iuv;
	}
	public void setIuv(String iuv) {
		this.iuv = iuv;
	}
	public BigDecimal getImporto() {
		return importo;
	}
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	public BigDecimal getImportoDa() {
		return importoDa;
	}
	public void setImportoDa(BigDecimal importoDa) {
		this.importoDa = importoDa;
	}
	public BigDecimal getImportoA() {
		return importoA;
	}
	public void setImportoA(BigDecimal importoA) {
		this.importoA = importoA;
	}
	public Calendar getDataTransazioneDa() {
		return dataTransazioneDa;
	}
	public void setDataTransazioneDa(Calendar dataTransazioneDa) {
		this.dataTransazioneDa = dataTransazioneDa;
	}
    public Calendar getDataTransazione() {
		return dataTransazione;
	}
	public void setDataTransazione(Calendar dataTransazione) {
		this.dataTransazione = dataTransazione;
	}
	public Calendar getDataTransazioneA() {
		return dataTransazioneA;
	}
	public void setDataTransazioneA(Calendar dataTransazioneA) {
		this.dataTransazioneA = dataTransazioneA;
	}

	public void setIdMdc(long idMdc) {
		this.idMdc = idMdc;
	}

	public long getIdMdc() {
		return idMdc;
	}
}
