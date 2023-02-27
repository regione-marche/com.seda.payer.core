package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;


import com.seda.data.dao.ModelAttributes;

public class Solleciti extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Tabella PYSOLTB
	private String idWallet;				//  SOL_KBRSKBRS
	private String tipoSollecito;			//  SOL_CSOLTIPO
	private Calendar dataSollecito;				//  SOL_GSOLDATA
	private String nomeFile;				//  SOL_DSOLFILE
	private String codiceOnere;				//	SOL_CSOLCONE
	private String descrizioneOnere;		//	SOL_DSOLDESC
	private BigDecimal importoCarico;		//	SOL_ISOLIMPO
	private BigDecimal importoPagato;		//	SOL_ISOLIPAG
	private BigDecimal importoRendicontato;	//	SOL_ISOLIREN
	private String codiceServizio;			//	SOL_CSRVCODI
	
	

	public Solleciti(){
		
	}
	
	public Solleciti(
			String idWallet,
			String tipoSollecito,
			Calendar dataSollecito,
			String nomeFile,
			String codiceOnere,
			String descrizioneOnere,
			BigDecimal importoCarico,
			BigDecimal importoPagato,
			BigDecimal importoRendicontato,
			String codiceServizio
			
		){
		this.idWallet = idWallet;
		this.tipoSollecito = tipoSollecito;
		this.dataSollecito = dataSollecito;
		this.nomeFile = nomeFile;
		this.importoCarico = importoCarico;
		this.importoPagato = importoPagato;
		this.importoRendicontato = importoRendicontato;
		this.codiceOnere = codiceOnere;
		this.descrizioneOnere = descrizioneOnere;
		this.codiceServizio=codiceServizio;
		}

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}

	public String getTipoSollecito() {
		return tipoSollecito;
	}

	public void setTipoSollecito(String tipoSollecito) {
		this.tipoSollecito = tipoSollecito;
	}

	public Calendar getDataSollecito() {
		return dataSollecito;
	}

	public void setDataSollecito(Calendar dataSollecito) {
		this.dataSollecito = dataSollecito;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getImportoCarico() {
		return importoCarico;
	}

	public void setImportoCarico(BigDecimal importoCarico) {
		this.importoCarico = importoCarico;
	}

	public BigDecimal getImportoPagato() {
		return importoPagato;
	}

	public void setImportoPagato(BigDecimal importoPagato) {
		this.importoPagato = importoPagato;
	}

	public BigDecimal getImportoRendicontato() {
		return importoRendicontato;
	}

	public void setImportoRendicontato(BigDecimal importoRendicontato) {
		this.importoRendicontato = importoRendicontato;
	}

	public String getCodiceOnere() {
		return codiceOnere;
	}

	public void setCodiceOnere(String codiceOnere) {
		this.codiceOnere = codiceOnere;
	}

	public String getDescrizioneOnere() {
		return descrizioneOnere;
	}

	public void setDescrizioneOnere(String descrizioneOnere) {
		this.descrizioneOnere = descrizioneOnere;
	}
	
	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
	@Override
	public String toString() {
		return "Solleciti [codiceOnere=" + codiceOnere + ", dataSollecito="
				+ dataSollecito + ", descrizioneOnere=" + descrizioneOnere
				+ ", idWallet=" + idWallet + ", importoCarico=" + importoCarico
				+ ", importoPagato=" + importoPagato + ", importoRendicontato="
				+ importoRendicontato + ", nomeFile=" + nomeFile
				+ ", tipoSollecito=" + tipoSollecito 
				+ ", codiceServizio =" + codiceServizio + "]";
	}

}
