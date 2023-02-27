package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.seda.data.dao.ModelAttributes;

public class FattureComunicazioniREP extends ModelAttributes implements Serializable {

	/**
	 *    tipo record 70 / 71 multiplo tabella  PYICRTB
	 */
	private static final long serialVersionUID = 1L;
	
	private String identificativoRecord;
	private String idWallet;
	private String numeroProgressivoRicorrenza;
	private String tipoServizioSpecifico;
	//private String importoFatturaTotale = "";
	private BigDecimal importoFatturaTotale = new BigDecimal(0);
	private String descrizioneVoceOperazione = "";
	private String segnoImportoVoce = "";
	private String importoVoce = "";
	private String nota = "";
	private String numeroProgressivoDisposizione;
	private String krepkrep="";
	private String kicrkicr="";
	
	public String getKrepkrep() {
		return krepkrep;
	}

	public void setKrepkrep(String krepkrep) {
		this.krepkrep = krepkrep;
	}

	public String getKicrkicr() {
		return kicrkicr;
	}

	public void setKicrkicr(String kicrkicr) {
		this.kicrkicr = kicrkicr;
	}

	public FattureComunicazioniREP(){
		
	}
	
	public FattureComunicazioniREP(
			String identificativoRecord,
			String idWallet,
			String numeroProgressivoRicorrenza,
			BigDecimal importoFatturaTotale,
			String descrizioneVoceOperazione,
			String segnoImportoVoce,
			String importoVoce,
			String nota,
			String numeroProgressivoDisposizione
		){
		this.identificativoRecord = identificativoRecord;
		this.idWallet = idWallet;
		this.numeroProgressivoRicorrenza = numeroProgressivoRicorrenza;
		this.importoFatturaTotale = importoFatturaTotale;
		this.descrizioneVoceOperazione = descrizioneVoceOperazione;
		this.segnoImportoVoce = segnoImportoVoce;
		this.importoVoce = importoVoce;
		this.nota = nota;
		this.numeroProgressivoDisposizione = numeroProgressivoDisposizione;
		
	}

	public String getIdentificativoRecord() {
		return identificativoRecord;
	}

	public void setIdentificativoRecord(String identificativoRecord) {
		this.identificativoRecord = identificativoRecord;
	}

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}

	public String getNumeroProgressivoRicorrenza() {
		return numeroProgressivoRicorrenza;
	}

	public void setNumeroProgressivoRicorrenza(String numeroProgressivoRicorrenza) {
		this.numeroProgressivoRicorrenza = numeroProgressivoRicorrenza;
	}

	public BigDecimal getImportoFatturaTotale() {
		return importoFatturaTotale;
	}

	public void setImportoFatturaTotale(BigDecimal importoFatturaTotale) {
		this.importoFatturaTotale = importoFatturaTotale;
	}

	public String getDescrizioneVoceOperazione() {
		return descrizioneVoceOperazione;
	}

	public void setDescrizioneVoceOperazione(String descrizioneVoceOperazione) {
		this.descrizioneVoceOperazione = descrizioneVoceOperazione;
	}

	public String getSegnoImportoVoce() {
		return segnoImportoVoce;
	}

	public void setSegnoImportoVoce(String segnoImportoVoce) {
		this.segnoImportoVoce = segnoImportoVoce;
	}

	public String getImportoVoce() {
		return importoVoce;
	}

	public void setImportoVoce(String importoVoce) {
		this.importoVoce = importoVoce;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getNumeroProgressivoDisposizione() {
		return numeroProgressivoDisposizione;
	}

	public void setNumeroProgressivoDisposizione(
			String numeroProgressivoDisposizione) {
		this.numeroProgressivoDisposizione = numeroProgressivoDisposizione;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTipoServizioSpecifico() {
		return tipoServizioSpecifico;
	}

	public void setTipoServizioSpecifico(String tipoServizioSpecifico) {
		this.tipoServizioSpecifico = tipoServizioSpecifico;
	}

	@Override
	public String toString() {
		return "FattureComunicazioniRep [descrizioneVoceOperazione="
				+ descrizioneVoceOperazione + ", idWallet=" + idWallet
				+ ", identificativoRecord=" + identificativoRecord
				+ ", importoFatturaTotale=" + importoFatturaTotale
				+ ", importoVoce=" + importoVoce + ", nota=" + nota
				+ ", numeroProgressivoDisposizione="
				+ numeroProgressivoDisposizione
				+ ", numeroProgressivoRicorrenza="
				+ numeroProgressivoRicorrenza + ", segnoImportoVoce="
				+ segnoImportoVoce + "]";
	}
	
	
	
}
