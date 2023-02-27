package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ArchivioCarichiMovimento {
	//PYEH3TB
	
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
    private Integer progressivoPagamento;
    private String tipoMovimento;
    private Date dataMovimento;
    private String causaleMovimento;
    private String segno;
    private BigDecimal impPagatoSuTributoCompresoIntMR;
    private BigDecimal impPagatoDirittiNotifica;
    private BigDecimal impPagatoAggioCoattivo;
    private BigDecimal impPagatoAltreSpese;
    private BigDecimal impPagatoIntMora;
    private Integer numeroRate;
    private Integer primaRata; 
    private String canalePagamento;
    private String tipoPagamento;
    private String tombstoned;
  
    public ArchivioCarichiMovimento() {
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

	public Integer getProgressivoPagamento() {
		return progressivoPagamento;
	}

	public void setProgressivoPagamento(Integer progressivoPagamento) {
		this.progressivoPagamento = progressivoPagamento;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public String getCausaleMovimento() {
		return causaleMovimento;
	}

	public void setCausaleMovimento(String causaleMovimento) {
		this.causaleMovimento = causaleMovimento;
	}

	public String getSegno() {
		return segno;
	}

	public void setSegno(String segno) {
		this.segno = segno;
	}

	public BigDecimal getImpPagatoSuTributoCompresoIntMR() {
		return impPagatoSuTributoCompresoIntMR;
	}

	public void setImpPagatoSuTributoCompresoIntMR(
			BigDecimal impPagatoSuTributoCompresoIntMR) {
		this.impPagatoSuTributoCompresoIntMR = impPagatoSuTributoCompresoIntMR;
	}

	public BigDecimal getImpPagatoDirittiNotifica() {
		return impPagatoDirittiNotifica;
	}

	public void setImpPagatoDirittiNotifica(BigDecimal impPagatoDirittiNotifica) {
		this.impPagatoDirittiNotifica = impPagatoDirittiNotifica;
	}

	public BigDecimal getImpPagatoAggioCoattivo() {
		return impPagatoAggioCoattivo;
	}

	public void setImpPagatoAggioCoattivo(BigDecimal impPagatoAggioCoattivo) {
		this.impPagatoAggioCoattivo = impPagatoAggioCoattivo;
	}

	public BigDecimal getImpPagatoAltreSpese() {
		return impPagatoAltreSpese;
	}

	public void setImpPagatoAltreSpese(BigDecimal impPagatoAltreSpese) {
		this.impPagatoAltreSpese = impPagatoAltreSpese;
	}

	public BigDecimal getImpPagatoIntMora() {
		return impPagatoIntMora;
	}

	public void setImpPagatoIntMora(BigDecimal impPagatoIntMora) {
		this.impPagatoIntMora = impPagatoIntMora;
	}

	public Integer getNumeroRate() {
		return numeroRate;
	}

	public void setNumeroRate(Integer numeroRate) {
		this.numeroRate = numeroRate;
	}

	public Integer getPrimaRata() {
		return primaRata;
	}

	public void setPrimaRata(Integer primaRata) {
		this.primaRata = primaRata;
	}

	public String getCanalePagamento() {
		return canalePagamento;
	}

	public void setCanalePagamento(String canalePagamento) {
		this.canalePagamento = canalePagamento;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}

}
