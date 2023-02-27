package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.seda.data.dao.ModelAttributes;
import com.seda.data.dao.ModelObjectFactory;

public class Rep extends ModelAttributes  implements Serializable{
	
	
	/**
	 *    record 14 Tabella PYREPTB
	 */
	private static final long serialVersionUID = 1L;
	
	private String idWallet;
	private String codiceServizio;
	private String numeroProgressivoDisposizione;
	private String tipoDisposizione; //Tipo Disposizione (C = carico, D = Discarico)
	private String codiceEnteCreditore;
	private String codiceFiscaleGenitore;
	private String codiceAnagraficaFiglio;
	private String codiceAnagraficaGenitore;
	private BigDecimal importoCarico;
	private BigDecimal importoPagato;
	private BigDecimal importoRendicontato;
	private String note;
	private BigDecimal importoTariffa;
	private String  tipoTariffa;
	private String krepkrep;
	private int progressivo;
	private String krepkrepDis;



	public String getCodiceAnagraficaGenitore() {
		return codiceAnagraficaGenitore;
	}

	public void setCodiceAnagraficaGenitore(String codiceAnagraficaGenitore) {
		this.codiceAnagraficaGenitore = codiceAnagraficaGenitore;
	}
	
	public String getKrepkrep() {
		return krepkrep;
	}

	public void setKrepkrep(String krepkrep) {
		this.krepkrep = krepkrep;
	}

	public Rep(){
		
	}
	
	public Rep(
			String identificativoRecord,
			String idWallet,
			String codiceServizio,
			String numeroProgressivoDisposizione,
			String tipoDisposizione,
			String codiceEnteCreditore,
			String codiceFiscaleGenitore,
			String codiceAnagraficaFiglio,
			BigDecimal importoCarico,
			BigDecimal importoPagato,
			BigDecimal importoRendicontato,
			String note
		){
		this.idWallet = idWallet;
		this.codiceServizio = codiceServizio;
		this.numeroProgressivoDisposizione = numeroProgressivoDisposizione;
		this.tipoDisposizione = tipoDisposizione;
		this.codiceEnteCreditore = codiceEnteCreditore;
		this.codiceFiscaleGenitore = codiceFiscaleGenitore;
		this.codiceAnagraficaFiglio = codiceAnagraficaFiglio;
		this.importoCarico = importoCarico;
		this.importoPagato = importoPagato;
		this.importoRendicontato = importoRendicontato;
		this.note = note;
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

	public String getTipoDisposizione() {
		return tipoDisposizione;
	}

	public void setTipoDisposizione(String tipoDisposizione) {
		this.tipoDisposizione = tipoDisposizione;
	}

	public String getCodiceEnteCreditore() {
		return codiceEnteCreditore;
	}

	public void setCodiceEnteCreditore(String codiceEnteCreditore) {
		this.codiceEnteCreditore = codiceEnteCreditore;
	}

	public String getCodiceFiscaleGenitore() {
		return codiceFiscaleGenitore;
	}

	public void setCodiceFiscaleGenitore(String codiceFiscaleGenitore) {
		this.codiceFiscaleGenitore = codiceFiscaleGenitore;
	} 

	public String getCodiceAnagraficaFiglio() {
		return codiceAnagraficaFiglio;
	}

	public void setCodiceAnagraficaFiglio(String codiceAnagraficaFiglio) {
		this.codiceAnagraficaFiglio = codiceAnagraficaFiglio;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public BigDecimal getImportoTariffa() {
		return importoTariffa;
	}

	public void setImportoTariffa(BigDecimal importoTariffa) {
		this.importoTariffa = importoTariffa;
	}

	public String getTipoTariffa() {
		return tipoTariffa;
	}

	public void setTipoTariffa(String tipoTariffa) {
		this.tipoTariffa = tipoTariffa;
	}
	
	public int getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(int progressivo) {
		this.progressivo = progressivo;
	}

	public String getKrepkrepDis() {
		return krepkrepDis;
	}

	public void setKrepkrepDis(String krepkrepDis) {
		this.krepkrepDis = krepkrepDis;
	}

	@Override
	public String toString() {
		return "Rep [codiceAnagraficaFiglio=" + codiceAnagraficaFiglio
				+ ", codiceAnagraficaGenitore=" + codiceAnagraficaGenitore
				+ ", codiceEnteCreditore=" + codiceEnteCreditore
				+ ", codiceFiscaleGenitore=" + codiceFiscaleGenitore
				+ ", codiceServizio=" + codiceServizio + ", idWallet="
				+ idWallet + ", importoCarico=" + importoCarico
				+ ", importoPagato=" + importoPagato + ", importoRendicontato="
				+ importoRendicontato + ", importoTariffa=" + importoTariffa
				+ ", krepkrep=" + krepkrep + ", krepkrepDis=" + krepkrepDis
				+ ", note=" + note + ", numeroProgressivoDisposizione="
				+ numeroProgressivoDisposizione + ", progressivo="
				+ progressivo + ", tipoDisposizione=" + tipoDisposizione
				+ ", tipoTariffa=" + tipoTariffa + "]";
	}

	
	
}
