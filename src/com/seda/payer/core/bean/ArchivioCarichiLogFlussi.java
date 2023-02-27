package com.seda.payer.core.bean;

import java.sql.Timestamp;
import java.util.Date;

public class ArchivioCarichiLogFlussi {
	//PYELGTB
	
	private Long progressivoFlusso;
	private String codiceUtente;
	private Date dataCreazioneFlusso;
	private Integer progressivoFlussoInGiornata;
	private String proceduraGestione;
	private String tipoServizio;
	private String identificativoFlusso;
	private Timestamp dataInizioElabFlusso;
	private Timestamp dataFineElabFlusso;
	private String flagElaborazioneFlusso;
	private String flagElaborazioneStampaAvviso;

    public ArchivioCarichiLogFlussi() {
    }

	public ArchivioCarichiLogFlussi(Long progressivoFlusso,
			String codiceUtente, Date dataCreazioneFlusso,
			Integer progressivoFlussoInGiornata, String proceduraGestione,
			String tipoServizio, String identificativoFlusso,
			Timestamp dataInizioElabFlusso, Timestamp dataFineElabFlusso,
			String flagElaborazioneFlusso, String flagElaborazioneStampaAvviso) {
		super();
		this.progressivoFlusso = progressivoFlusso;
		this.codiceUtente = codiceUtente;
		this.dataCreazioneFlusso = dataCreazioneFlusso;
		this.progressivoFlussoInGiornata = progressivoFlussoInGiornata;
		this.proceduraGestione = proceduraGestione;
		this.tipoServizio = tipoServizio;
		this.identificativoFlusso = identificativoFlusso;
		this.dataInizioElabFlusso = dataInizioElabFlusso;
		this.dataFineElabFlusso = dataFineElabFlusso;
		this.flagElaborazioneFlusso = flagElaborazioneFlusso;
		this.flagElaborazioneStampaAvviso = flagElaborazioneStampaAvviso;
	}

	public Long getProgressivoFlusso() {
		return progressivoFlusso;
	}

	public void setProgressivoFlusso(Long progressivoFlusso) {
		this.progressivoFlusso = progressivoFlusso;
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

	public Integer getProgressivoFlussoInGiornata() {
		return progressivoFlussoInGiornata;
	}

	public void setProgressivoFlussoInGiornata(Integer progressivoFlussoInGiornata) {
		this.progressivoFlussoInGiornata = progressivoFlussoInGiornata;
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

	public String getIdentificativoFlusso() {
		return identificativoFlusso;
	}

	public void setIdentificativoFlusso(String identificativoFlusso) {
		this.identificativoFlusso = identificativoFlusso;
	}

	public Timestamp getDataInizioElabFlusso() {
		return dataInizioElabFlusso;
	}

	public void setDataInizioElabFlusso(Timestamp dataInizioElabFlusso) {
		this.dataInizioElabFlusso = dataInizioElabFlusso;
	}

	public Timestamp getDataFineElabFlusso() {
		return dataFineElabFlusso;
	}

	public void setDataFineElabFlusso(Timestamp dataFineElabFlusso) {
		this.dataFineElabFlusso = dataFineElabFlusso;
	}

	public String getFlagElaborazioneFlusso() {
		return flagElaborazioneFlusso;
	}

	public void setFlagElaborazioneFlusso(String flagElaborazioneFlusso) {
		this.flagElaborazioneFlusso = flagElaborazioneFlusso;
	}

	public String getFlagElaborazioneStampaAvviso() {
		return flagElaborazioneStampaAvviso;
	}

	public void setFlagElaborazioneStampaAvviso(String flagElaborazioneStampaAvviso) {
		this.flagElaborazioneStampaAvviso = flagElaborazioneStampaAvviso;
	}
    
}
