package com.seda.payer.core.riconciliazionemt.bean;

import java.math.BigDecimal;
import java.util.Calendar;

public class Flusso {
	private long id;
	private long idMdc;
	private String flusso;
	private String codiceMittente;
	private String mittente;
	private BigDecimal importo;
	private Calendar dataFlusso;
	
    private BigDecimal importoDa;
    private BigDecimal importoA;
    
    public Flusso() {
    	
    }
    
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFlusso() {
		return flusso;
	}
	public void setFlusso(String flusso) {
		this.flusso = flusso;
	}
	public String getCodiceMittente() {
		return codiceMittente;
	}
	public void setCodiceMittente(String codiceMittente) {
		this.codiceMittente = codiceMittente;
	}
	public String getMittente() {
		return mittente;
	}
	public void setMittente(String mittente) {
		this.mittente = mittente;
	}
	public BigDecimal getImporto() {
		return importo;
	}
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	public Calendar getDataFlusso() {
		return dataFlusso;
	}
	public void setDataFlusso(Calendar dataFlusso) {
		this.dataFlusso = dataFlusso;
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
	public Calendar getDataFlussoDa() {
		return dataFlussoDa;
	}
	public void setDataFlussoDa(Calendar dataFlussoDa) {
		this.dataFlussoDa = dataFlussoDa;
	}
	public Calendar getDataFlussoA() {
		return dataFlussoA;
	}
	public void setDataFlussoA(Calendar dataFlussoA) {
		this.dataFlussoA = dataFlussoA;
	}
	public void setIdMdc(long idMdc) {
		this.idMdc = idMdc;
	}

	public long getIdMdc() {
		return idMdc;
	}
	private Calendar dataFlussoDa;
    private Calendar dataFlussoA;
}
