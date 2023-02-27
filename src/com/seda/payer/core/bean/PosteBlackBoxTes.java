package com.seda.payer.core.bean;

import java.io.Serializable;
import java.util.Calendar;

public class PosteBlackBoxTes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3299485946211795042L;

	private String tipoFlusso;
	private String codiceIdDominio;
	private Calendar timestampFlusso;
	private String nomeFile;
	
	
	
	public PosteBlackBoxTes() {
	}
	
	public PosteBlackBoxTes(String tipoFlusso, String codiceIdDominio,
			Calendar timestampFlusso, String nomeFile) {
		this.tipoFlusso = tipoFlusso;
		this.codiceIdDominio = codiceIdDominio;
		this.timestampFlusso = timestampFlusso;
		this.nomeFile = nomeFile;
	}
	public String getTipoFlusso() {
		return tipoFlusso;
	}
	public void setTipoFlusso(String tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}
	public String getCodiceIdDominio() {
		return codiceIdDominio;
	}
	public void setCodiceIdDominio(String codiceIdDominio) {
		this.codiceIdDominio = codiceIdDominio;
	}
	public Calendar getTimestampFlusso() {
		return timestampFlusso;
	}
	public void setTimestampFlusso(Calendar timestampFlusso) {
		this.timestampFlusso = timestampFlusso;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	
	
}
