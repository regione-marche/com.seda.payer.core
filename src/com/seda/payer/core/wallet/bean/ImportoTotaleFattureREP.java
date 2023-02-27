package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.seda.data.dao.ModelAttributes;

public class ImportoTotaleFattureREP extends ModelAttributes implements Serializable {

	/**
	 *    tipo record 60 / 61 multiplo tabella  PYIFTTB
	 */
	private static final long serialVersionUID = 1L;
	private String identificativoRecord; 
	private String idWallet;
	private String numeroProgressivoDisposizione;
	private String tipoServizioSpecifico;
	private String numeroProgressivoRicorrenza;
	private BigDecimal ImportoTotaleFattura = new  BigDecimal(0);
	private String annoTributo = "";
	private String codiceTributo = "";
	private String descrizioneVoceOperazione = "";
	private String segno = "";
	private String importoVoce = "";
	private String krepkrep="";
	private String kiftkift="";
	private String codiceServizio ="";

	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public String getKrepkrep() {
		return krepkrep;
	}

	public void setKrepkrep(String krepkrep) {
		this.krepkrep = krepkrep;
	}

	public String getKiftkift() {
		return kiftkift;
	}

	public void setKiftkift(String kiftkift) {
		this.kiftkift = kiftkift;
	}

	public ImportoTotaleFattureREP(){
		
	}
	
	public ImportoTotaleFattureREP(
			String identificativoRecord,
			String idWallet,
			String numeroProgressivoDisposizione,
			String tipoServizioSpecifico,
			String numeroProgressivoRicorrenza,
			BigDecimal ImportoTotaleFattura,
			String annoTributo,
			String codiceTributo,
			String descrizioneVoceOperazione,
			String segno,
			String importoVoce	
		){
		this.identificativoRecord = identificativoRecord;
		this.idWallet = idWallet;
		this.numeroProgressivoDisposizione = numeroProgressivoDisposizione;
		this.tipoServizioSpecifico = tipoServizioSpecifico;
		this.numeroProgressivoRicorrenza = numeroProgressivoRicorrenza;
		this.ImportoTotaleFattura = ImportoTotaleFattura;
		this.annoTributo = annoTributo;
		this.codiceTributo = codiceTributo;
		this.descrizioneVoceOperazione = descrizioneVoceOperazione;
		this.segno = segno;
		this.importoVoce = importoVoce;
		
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

public String getNumeroProgressivoDisposizione() {
	return numeroProgressivoDisposizione;
}

public void setNumeroProgressivoDisposizione(
		String numeroProgressivoDisposizione) {
	this.numeroProgressivoDisposizione = numeroProgressivoDisposizione;
}

public String getTipoServizioSpecifico() {
	return tipoServizioSpecifico;
}

public void setTipoServizioSpecifico(String tipoServizioSpecifico) {
	this.tipoServizioSpecifico = tipoServizioSpecifico;
}

public String getNumeroProgressivoRicorrenza() {
	return numeroProgressivoRicorrenza;
}

public void setNumeroProgressivoRicorrenza(String numeroProgressivoRicorrenza) {
	this.numeroProgressivoRicorrenza = numeroProgressivoRicorrenza;
}

public BigDecimal getImportoTotaleFattura() {
	return ImportoTotaleFattura;
}

public void setImportoTotaleFattura(BigDecimal importoTotaleFattura) {
	ImportoTotaleFattura = importoTotaleFattura;
}

public String getAnnoTributo() {
	return annoTributo;
}

public void setAnnoTributo(String annoTributo) {
	this.annoTributo = annoTributo;
}

public String getCodiceTributo() {
	return codiceTributo;
}

public void setCodiceTributo(String codiceTributo) {
	this.codiceTributo = codiceTributo;
}

public String getDescrizioneVoceOperazione() {
	return descrizioneVoceOperazione;
}

public void setDescrizioneVoceOperazione(String descrizioneVoceOperazione) {
	this.descrizioneVoceOperazione = descrizioneVoceOperazione;
}

public String getSegno() {
	return segno;
}

public void setSegno(String segno) {
	this.segno = segno;
}

public String getImportoVoce() {
	return importoVoce;
}

public void setImportoVoce(String importoVoce) {
	this.importoVoce = importoVoce;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

@Override
public String toString() {
	return "ImportoTotaleFattureREP [ImportoTotaleFattura="
			+ ImportoTotaleFattura + ", annoTributo=" + annoTributo
			+ ", codiceTributo=" + codiceTributo
			+ ", descrizioneVoceOperazione=" + descrizioneVoceOperazione
			+ ", idWallet=" + idWallet + ", identificativoRecord="
			+ identificativoRecord + ", importoVoce=" + importoVoce
			+ ", numeroProgressivoDisposizione="
			+ numeroProgressivoDisposizione + ", numeroProgressivoRicorrenza="
			+ numeroProgressivoRicorrenza + ", segno=" + segno
			+ ", tipoServizioSpecifico=" + tipoServizioSpecifico + "]";
}


}
