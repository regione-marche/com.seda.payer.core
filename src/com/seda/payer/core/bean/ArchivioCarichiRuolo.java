package com.seda.payer.core.bean;

import java.util.Date;

public class ArchivioCarichiRuolo {
//PYEH6TB
	
	private Long progressivoFlusso;
	private String tipoRecord;
	private String codiceUtente;
	private Date dataCreazioneFlusso;
	private String tipoServizio;
	private String codiceEnte;
	private String tipoUfficio;
	private String codiceUfficio;
	private String impostaServizio;
	private String descImpostaServizio;
	private String codTipologiaServizio;
	private String descTipologiaServizio;
    private String tombstoned;

    public ArchivioCarichiRuolo() {
    }

	public ArchivioCarichiRuolo(Long progressivoFlusso, String tipoRecord,
			String codiceUtente, Date dataCreazioneFlusso,
			String tipoServizio, String codiceEnte, String tipoUfficio,
			String codiceUfficio, String impostaServizio,
			String descImpostaServizio, String codTipologiaServizio,
			String descTipologiaServizio, String tombstoned) {
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
		this.descImpostaServizio = descImpostaServizio;
		this.codTipologiaServizio = codTipologiaServizio;
		this.descTipologiaServizio = descTipologiaServizio;
		this.tombstoned = tombstoned;
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

	public String getDescImpostaServizio() {
		return descImpostaServizio;
	}

	public void setDescImpostaServizio(String descImpostaServizio) {
		this.descImpostaServizio = descImpostaServizio;
	}

	public String getCodTipologiaServizio() {
		return codTipologiaServizio;
	}

	public void setCodTipologiaServizio(String codTipologiaServizio) {
		this.codTipologiaServizio = codTipologiaServizio;
	}

	public String getDescTipologiaServizio() {
		return descTipologiaServizio;
	}

	public void setDescTipologiaServizio(String descTipologiaServizio) {
		this.descTipologiaServizio = descTipologiaServizio;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}
    
}
