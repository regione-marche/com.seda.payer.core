package com.seda.payer.core.bean;

import java.util.Date;

public class ArchivioCarichiCoda {
	//PYEH9TB
	
	private Long progressivoFlusso;
	private String tipoRecord;
	private String codiceUtente;
	private Date dataCreazioneFlusso;
	private String tipoServizio;
	private Integer numeroRecordFlusso;
	private String tombstoned;
	
    public ArchivioCarichiCoda() {
    }

	public ArchivioCarichiCoda(Long progressivoFlusso, String tipoRecord,
			String codiceUtente, Date dataCreazioneFlusso, String tipoServizio,
			Integer numeroRecordFlusso, String tombstoned) {
		super();
		this.progressivoFlusso = progressivoFlusso;
		this.tipoRecord = tipoRecord;
		this.codiceUtente = codiceUtente;
		this.dataCreazioneFlusso = dataCreazioneFlusso;
		this.tipoServizio = tipoServizio;
		this.numeroRecordFlusso = numeroRecordFlusso;
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

	public Integer getNumeroRecordFlusso() {
		return numeroRecordFlusso;
	}

	public void setNumeroRecordFlusso(Integer numeroRecordFlusso) {
		this.numeroRecordFlusso = numeroRecordFlusso;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}
}
