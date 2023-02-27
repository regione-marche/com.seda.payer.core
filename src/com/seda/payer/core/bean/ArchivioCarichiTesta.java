package com.seda.payer.core.bean;

import java.util.Date;

public class ArchivioCarichiTesta {
	//PYEH0TB
	
	private Long progressivoFlusso;
	private String tipoRecord;
	private String codiceUtente;
	private Date dataCreazioneFlusso;
	private String proceduraGestione;
	private String tipoServizio;
	private String tombstoned;
	private String flagStampaAvviso;
	private String flagGenerazioneIUV;
	private String idDominio;
	private String auxDigit;
	private String applicationCode;
	private String segregationCode;
	
    public ArchivioCarichiTesta() {
    }

	public ArchivioCarichiTesta(Long progressivoFlusso, String tipoRecord,
			String codiceUtente, Date dataCreazioneFlusso,
			String proceduraGestione, String tipoServizio, String tombstoned,
			String flagStampaAvviso, String flagGenerazioneIUV,
			String idDominio, String auxDigit, String applicationCode,
			String segregationCode) {
		super();
		this.progressivoFlusso = progressivoFlusso;
		this.tipoRecord = tipoRecord;
		this.codiceUtente = codiceUtente;
		this.dataCreazioneFlusso = dataCreazioneFlusso;
		this.proceduraGestione = proceduraGestione;
		this.tipoServizio = tipoServizio;
		this.tombstoned = tombstoned;
		this.flagStampaAvviso = flagStampaAvviso;
		this.flagGenerazioneIUV = flagGenerazioneIUV;
		this.idDominio = idDominio;
		this.auxDigit = auxDigit;
		this.applicationCode = applicationCode;
		this.segregationCode = segregationCode;
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

	public String getProceduraGestione() {
		return proceduraGestione;
	}

	public void setProceduraGestione(String proceduraGestione) {
		this.proceduraGestione = proceduraGestione;
	}

	public String getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}

	public String getFlagStampaAvviso() {
		return flagStampaAvviso;
	}

	public void setFlagStampaAvviso(String flagStampaAvviso) {
		this.flagStampaAvviso = flagStampaAvviso;
	}

	public String getFlagGenerazioneIUV() {
		return flagGenerazioneIUV;
	}

	public void setFlagGenerazioneIUV(String flagGenerazioneIUV) {
		this.flagGenerazioneIUV = flagGenerazioneIUV;
	}

	public String getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}

	public String getAuxDigit() {
		return auxDigit;
	}

	public void setAuxDigit(String auxDigit) {
		this.auxDigit = auxDigit;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getSegregationCode() {
		return segregationCode;
	}

	public void setSegregationCode(String segregationCode) {
		this.segregationCode = segregationCode;
	}

}
