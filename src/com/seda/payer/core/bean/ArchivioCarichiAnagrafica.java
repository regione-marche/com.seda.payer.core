package com.seda.payer.core.bean;

import java.util.Date;

public class ArchivioCarichiAnagrafica {
	//PYEH8TB
	
	private Long progressivoFlusso;
	private String tipoRecord;
	private String codiceUtente;
	private Date dataCreazioneFlusso;
	private String tipoServizio;
	private String codiceEnte;
	private String tipoUfficio;
	private String codiceUfficio;
	private String impostaServizio;
	private String codiceFiscale;
	private String denominazione;
	private String tipoAnagrafica;
	private String codiceBelfioreComuneNascita;
	private Date dataNascita;
	private String statusAnagrafica;
	private String indirizzoFiscale;
	private String codiceBelfioreComuneFiscale;
	private String tombstoned;
    private String email;
    private String emailPec;

    public ArchivioCarichiAnagrafica() {
    }

	public ArchivioCarichiAnagrafica(Long progressivoFlusso, String tipoRecord,
			String codiceUtente, Date dataCreazioneFlusso,
			String tipoServizio, String codiceEnte, String tipoUfficio,
			String codiceUfficio, String impostaServizio, String codiceFiscale,
			String denominazione, String tipoAnagrafica,
			String codiceBelfioreComuneNascita, Date dataNascita,
			String statusAnagrafica, String indirizzoFiscale,
			String codiceBelfioreComuneFiscale, String tombstoned,
			String email, String emailPec) {
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
		this.codiceFiscale = codiceFiscale;
		this.denominazione = denominazione;
		this.tipoAnagrafica = tipoAnagrafica;
		this.codiceBelfioreComuneNascita = codiceBelfioreComuneNascita;
		this.dataNascita = dataNascita;
		this.statusAnagrafica = statusAnagrafica;
		this.indirizzoFiscale = indirizzoFiscale;
		this.codiceBelfioreComuneFiscale = codiceBelfioreComuneFiscale;
		this.tombstoned = tombstoned;
		this.email = email;
		this.emailPec = emailPec;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getTipoAnagrafica() {
		return tipoAnagrafica;
	}

	public void setTipoAnagrafica(String tipoAnagrafica) {
		this.tipoAnagrafica = tipoAnagrafica;
	}

	public String getCodiceBelfioreComuneNascita() {
		return codiceBelfioreComuneNascita;
	}

	public void setCodiceBelfioreComuneNascita(String codiceBelfioreComuneNascita) {
		this.codiceBelfioreComuneNascita = codiceBelfioreComuneNascita;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getStatusAnagrafica() {
		return statusAnagrafica;
	}

	public void setStatusAnagrafica(String statusAnagrafica) {
		this.statusAnagrafica = statusAnagrafica;
	}

	public String getIndirizzoFiscale() {
		return indirizzoFiscale;
	}

	public void setIndirizzoFiscale(String indirizzoFiscale) {
		this.indirizzoFiscale = indirizzoFiscale;
	}

	public String getCodiceBelfioreComuneFiscale() {
		return codiceBelfioreComuneFiscale;
	}

	public void setCodiceBelfioreComuneFiscale(String codiceBelfioreComuneFiscale) {
		this.codiceBelfioreComuneFiscale = codiceBelfioreComuneFiscale;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailPec() {
		return emailPec;
	}

	public void setEmailPec(String emailPec) {
		this.emailPec = emailPec;
	}

}
