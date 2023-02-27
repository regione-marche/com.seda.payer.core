package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ArchivioCarichiScadenza {
	//PYEH2TB
	
	private Long progressivoFlusso;
	private String tipoRecord;
	private String codiceUtente;
	private Date dataCreazioneFlusso;
	private String tipoServizio;
	private String codiceEnte;
	private String tipoUfficio;
	private String codiceUfficio;
	private String impostaServizio;
	private String numeroDocumento;
	private BigDecimal impDovutoTributiCompresiInteressiMR;
	private BigDecimal impPagatoTributi;
	private Date dataScadenzaRata;
	private Integer numeroRata;
	private String numeroBollettino;
	private BigDecimal impBollettinoRata;
	private BigDecimal impMora;
	private BigDecimal impAggioCoattivoDovutoResiduoEntroScadenza;
	private BigDecimal impAggioCoattivoOrigEntroScadenza;
	private BigDecimal impAggioCoattivoDovutoResiduoOltreScadenza;
	private BigDecimal impAggioCoattivoPagato;
	private BigDecimal impDovutoDirittiNotifica;
	private BigDecimal impPagatoDirittiNotifica;
    private String tombstoned;
    private String identificativoUnivocoVersamento;

    public ArchivioCarichiScadenza() {
    }

	public ArchivioCarichiScadenza(Long progressivoFlusso, String tipoRecord,
			String codiceUtente, Date dataCreazioneFlusso, String tipoServizio,
			String codiceEnte, String tipoUfficio, String codiceUfficio,
			String impostaServizio, String numeroDocumento,
			BigDecimal impDovutoTributiCompresiInteressiMR,
			BigDecimal impPagatoTributi, Date dataScadenzaRata,
			Integer numeroRata, String numeroBollettino,
			BigDecimal impBollettinoRata, BigDecimal impMora,
			BigDecimal impAggioCoattivoDovutoResiduoEntroScadenza,
			BigDecimal impAggioCoattivoOrigEntroScadenza,
			BigDecimal impAggioCoattivoDovutoResiduoOltreScadenza,
			BigDecimal impAggioCoattivoPagato,
			BigDecimal impDovutoDirittiNotifica,
			BigDecimal impPagatoDirittiNotifica, String tombstoned,
			String identificativoUnivocoVersamento) {
		super();
		this.progressivoFlusso = progressivoFlusso;
		this.tipoRecord = tipoRecord;
		this.codiceUtente = codiceUtente;
		this.dataCreazioneFlusso = dataCreazioneFlusso;
		this.tipoServizio = tipoServizio;
		this.codiceEnte = codiceEnte;
		this.tipoUfficio = tipoUfficio;
		this.codiceUfficio = codiceUfficio;
		this.impostaServizio = impostaServizio;
		this.numeroDocumento = numeroDocumento;
		this.impDovutoTributiCompresiInteressiMR = impDovutoTributiCompresiInteressiMR;
		this.impPagatoTributi = impPagatoTributi;
		this.dataScadenzaRata = dataScadenzaRata;
		this.numeroRata = numeroRata;
		this.numeroBollettino = numeroBollettino;
		this.impBollettinoRata = impBollettinoRata;
		this.impMora = impMora;
		this.impAggioCoattivoDovutoResiduoEntroScadenza = impAggioCoattivoDovutoResiduoEntroScadenza;
		this.impAggioCoattivoOrigEntroScadenza = impAggioCoattivoOrigEntroScadenza;
		this.impAggioCoattivoDovutoResiduoOltreScadenza = impAggioCoattivoDovutoResiduoOltreScadenza;
		this.impAggioCoattivoPagato = impAggioCoattivoPagato;
		this.impDovutoDirittiNotifica = impDovutoDirittiNotifica;
		this.impPagatoDirittiNotifica = impPagatoDirittiNotifica;
		this.tombstoned = tombstoned;
		this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
	}

	public Long getProgressivoFlusso() {
		return progressivoFlusso;
	}

	public void setProgressivoFlusso(Long progressivoFlusso) {
		this.progressivoFlusso = progressivoFlusso;
	}

	public String getTipoRecord() {
		return tipoRecord;
	}

	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public Date getDataCreazioneFlusso() {
		return dataCreazioneFlusso;
	}

	public void setDataCreazioneFlusso(Date dataCreazioneFlusso) {
		this.dataCreazioneFlusso = dataCreazioneFlusso;
	}

	public String getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public BigDecimal getImpDovutoTributiCompresiInteressiMR() {
		return impDovutoTributiCompresiInteressiMR;
	}

	public void setImpDovutoTributiCompresiInteressiMR(
			BigDecimal impDovutoTributiCompresiInteressiMR) {
		this.impDovutoTributiCompresiInteressiMR = impDovutoTributiCompresiInteressiMR;
	}

	public BigDecimal getImpPagatoTributi() {
		return impPagatoTributi;
	}

	public void setImpPagatoTributi(BigDecimal impPagatoTributi) {
		this.impPagatoTributi = impPagatoTributi;
	}

	public Date getDataScadenzaRata() {
		return dataScadenzaRata;
	}

	public void setDataScadenzaRata(Date dataScadenzaRata) {
		this.dataScadenzaRata = dataScadenzaRata;
	}

	public Integer getNumeroRata() {
		return numeroRata;
	}

	public void setNumeroRata(Integer numeroRata) {
		this.numeroRata = numeroRata;
	}

	public String getNumeroBollettino() {
		return numeroBollettino;
	}

	public void setNumeroBollettino(String numeroBollettino) {
		this.numeroBollettino = numeroBollettino;
	}

	public BigDecimal getImpBollettinoRata() {
		return impBollettinoRata;
	}

	public void setImpBollettinoRata(BigDecimal impBollettinoRata) {
		this.impBollettinoRata = impBollettinoRata;
	}

	public BigDecimal getImpMora() {
		return impMora;
	}

	public void setImpMora(BigDecimal impMora) {
		this.impMora = impMora;
	}

	public BigDecimal getImpAggioCoattivoDovutoResiduoEntroScadenza() {
		return impAggioCoattivoDovutoResiduoEntroScadenza;
	}

	public void setImpAggioCoattivoDovutoResiduoEntroScadenza(
			BigDecimal impAggioCoattivoDovutoResiduoEntroScadenza) {
		this.impAggioCoattivoDovutoResiduoEntroScadenza = impAggioCoattivoDovutoResiduoEntroScadenza;
	}

	public BigDecimal getImpAggioCoattivoOrigEntroScadenza() {
		return impAggioCoattivoOrigEntroScadenza;
	}

	public void setImpAggioCoattivoOrigEntroScadenza(
			BigDecimal impAggioCoattivoOrigEntroScadenza) {
		this.impAggioCoattivoOrigEntroScadenza = impAggioCoattivoOrigEntroScadenza;
	}

	public BigDecimal getImpAggioCoattivoDovutoResiduoOltreScadenza() {
		return impAggioCoattivoDovutoResiduoOltreScadenza;
	}

	public void setImpAggioCoattivoDovutoResiduoOltreScadenza(
			BigDecimal impAggioCoattivoDovutoResiduoOltreScadenza) {
		this.impAggioCoattivoDovutoResiduoOltreScadenza = impAggioCoattivoDovutoResiduoOltreScadenza;
	}

	public BigDecimal getImpAggioCoattivoPagato() {
		return impAggioCoattivoPagato;
	}

	public void setImpAggioCoattivoPagato(BigDecimal impAggioCoattivoPagato) {
		this.impAggioCoattivoPagato = impAggioCoattivoPagato;
	}

	public BigDecimal getImpDovutoDirittiNotifica() {
		return impDovutoDirittiNotifica;
	}

	public void setImpDovutoDirittiNotifica(BigDecimal impDovutoDirittiNotifica) {
		this.impDovutoDirittiNotifica = impDovutoDirittiNotifica;
	}

	public BigDecimal getImpPagatoDirittiNotifica() {
		return impPagatoDirittiNotifica;
	}

	public void setImpPagatoDirittiNotifica(BigDecimal impPagatoDirittiNotifica) {
		this.impPagatoDirittiNotifica = impPagatoDirittiNotifica;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}

	public String getIdentificativoUnivocoVersamento() {
		return identificativoUnivocoVersamento;
	}

	public void setIdentificativoUnivocoVersamento(
			String identificativoUnivocoVersamento) {
		this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
	}
	
}
