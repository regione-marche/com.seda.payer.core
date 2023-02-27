package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class PresenzeGiornaliere extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cuteCute;
	private String codiceEnte;
	private String identificativoRecord;
	private String idWallet;
	private String codiceServizio;
	private String codiceAnagraficaGenitore;
	private String codiceAnagraficaFiglio;
	private String annoScolastico;
	private String annoTributo;
	private String codiceTributo;
	private BigDecimal importoTributo;
	private String causale;
	private String descrizioneServizio;
	private String chiaveMovimento;
	private String codiceScuola;
	private Calendar presenzaScuola;
	private Date dataCaricamento;
	private BigDecimal importoPagato;
	private BigDecimal importoRendicontato;
	private BigDecimal importoTariffa;
	private String  tipoTariffa;
	public PresenzeGiornaliere(){
		
	}
	
	public PresenzeGiornaliere(
			String identificativoRecord,
			String idWallet,
			String codiceServizio,
			String codiceSocieta,
			String cuteCute,
			String codiceEntePagonet,
			String codiceAnagraficaGenitore,
			String codiceAnagraficaFiglio,
			String annoTributo,
			String codiceTributo,
			BigDecimal importoTributo,
			String causale,
			String descrizioneServizio,
			String chiaveMovimento,
			String codiceScuola,
			Calendar presenzaScuola,
			Date dataCaricamento,
			BigDecimal importoPagato,
			BigDecimal importoRendicontato,
			String annoScolastico
	
		){
		this.identificativoRecord = identificativoRecord;
		this.codiceAnagraficaGenitore = codiceAnagraficaGenitore;
		this.codiceAnagraficaFiglio = codiceAnagraficaFiglio;
		this.annoScolastico=annoScolastico;
		this.annoTributo = annoTributo;
		this.codiceTributo = codiceTributo;
		this.importoTributo = importoTributo;
		this.causale = causale;
		this.descrizioneServizio = descrizioneServizio;
		this.chiaveMovimento = chiaveMovimento;
		this.codiceScuola = codiceScuola;
		this.presenzaScuola = presenzaScuola;
		this.dataCaricamento = dataCaricamento;
		this.importoPagato = importoPagato;
		this.importoRendicontato = importoRendicontato;
		this.idWallet = idWallet;
		this.codiceServizio = codiceServizio;
		
		
		
		
	}

	public String getIdentificativoRecord() {
		return identificativoRecord;
	}

	public void setIdentificativoRecord(String identificativoRecord) {
		this.identificativoRecord = identificativoRecord;
	}
	
	public String getCodiceAnagraficaGenitore() {
		return codiceAnagraficaGenitore;
	}

	public void setCodiceAnagraficaGenitore(String codiceAnagraficaGenitore) {
		this.codiceAnagraficaGenitore = codiceAnagraficaGenitore;
	}

	public String getCodiceAnagraficaFiglio() {
		return codiceAnagraficaFiglio;
	}

	public void setCodiceAnagraficaFiglio(String codiceAnagraficaFiglio) {
		this.codiceAnagraficaFiglio = codiceAnagraficaFiglio;
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

	public BigDecimal getImportoTributo() {
		return importoTributo;
	}

	public void setImportoTributo(BigDecimal importoTributo) {
		this.importoTributo = importoTributo;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}

	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}

	public String getChiaveMovimento() {
		return chiaveMovimento;
	}

	public void setChiaveMovimento(String chiaveMovimento) {
		this.chiaveMovimento = chiaveMovimento;
	}

	public String getCodiceScuola() {
		return codiceScuola;
	}

	public void setCodiceScuola(String codiceScuola) {
		this.codiceScuola = codiceScuola;
	}

	public Calendar getPresenzaScuola() {
		return presenzaScuola;
	}

	public void setPresenzaScuola(Calendar presenzaScuola) {
		this.presenzaScuola = presenzaScuola;
	}

	public Date getDataCaricamento() {
		return dataCaricamento;
	}

	public void setDataCaricamento(Date dataCaricamento) {
		this.dataCaricamento = dataCaricamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
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
	
	public String getAnnoScolastico() {
		return annoScolastico;
	}

	public void setAnnoScolastico(String annoScolastico) {
		this.annoScolastico = annoScolastico;
	}

	public String getCuteCute() {
		return cuteCute;
	}

	public void setCuteCute(String cuteCute) {
		this.cuteCute = cuteCute;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	@Override
	public String toString() {
		return "PresenteGiornaliere [annoTributo=" + annoTributo + ", causale="
				+ causale + ", chiaveMovimento=" + chiaveMovimento
				+ ", codiceAnagraficaFiglio=" + codiceAnagraficaFiglio
				+ ", codiceAnagraficaGenitore=" + codiceAnagraficaGenitore
				+ ", codiceScuola=" + codiceScuola + ", codiceServizio="
				+ codiceServizio + ", codiceTributo=" + codiceTributo
				+ ", dataCaricamento=" + dataCaricamento
				+ ", descrizioneServizio=" + descrizioneServizio
				+ ", idWallet=" + idWallet + ", identificativoRecord="
				+ identificativoRecord + ", importoPagato=" + importoPagato
				+ ", importoRendicontato=" + importoRendicontato
				+ ", annoScolastico=" + annoScolastico
				+ ", importoTributo=" + importoTributo + ", presenzaScuola="
				+ presenzaScuola + "]";
	}

	
	

}
