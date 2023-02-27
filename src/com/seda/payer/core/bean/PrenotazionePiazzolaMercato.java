package com.seda.payer.core.bean;

import java.util.Date;

public class PrenotazionePiazzolaMercato {
	private java.lang.String chiavePrenotazione;
	
    private java.lang.String codiceAutorizzazione;

    private java.lang.String nominativoAutorizzazione;
    
    private String codiceFiscale;
    
    private String codiceBorsellino;

    private java.lang.String codiceMercato;

    private java.lang.String descrizioneMercato;

    private java.lang.String codicePiazzola;

    private java.lang.String descrizionePiazzola;

    private java.lang.String tipologiaBanco;

    private java.lang.String periodoGiornaliero;

    private int giornoSettimana;

    private java.util.Date data;
    
    private java.util.Date dataPagamento;

    private java.lang.Float importoPagato;

    private float importoDovuto;

    private java.lang.String puntoN;

    private java.lang.String puntoS;

    private java.lang.String puntoE;

    private java.lang.String puntoO;

	public PrenotazionePiazzolaMercato() {
	}


	public PrenotazionePiazzolaMercato(String chiavePrenotazione,
			String codiceAutorizzazione, String nominativoAutorizzazione,
			String codiceFiscale, String codiceBorsellino,
			String codiceMercato, String descrizioneMercato,
			String codicePiazzola, String descrizionePiazzola,
			String tipologiaBanco, String periodoGiornaliero,
			int giornoSettimana, Date data, Date dataPagamento,
			Float importoPagato, float importoDovuto, String puntoN,
			String puntoS, String puntoE, String puntoO) {
		super();
		this.chiavePrenotazione = chiavePrenotazione;
		this.codiceAutorizzazione = codiceAutorizzazione;
		this.nominativoAutorizzazione = nominativoAutorizzazione;
		this.codiceFiscale = codiceFiscale;
		this.codiceBorsellino = codiceBorsellino;
		this.codiceMercato = codiceMercato;
		this.descrizioneMercato = descrizioneMercato;
		this.codicePiazzola = codicePiazzola;
		this.descrizionePiazzola = descrizionePiazzola;
		this.tipologiaBanco = tipologiaBanco;
		this.periodoGiornaliero = periodoGiornaliero;
		this.giornoSettimana = giornoSettimana;
		this.data = data;
		this.dataPagamento = dataPagamento;
		this.importoPagato = importoPagato;
		this.importoDovuto = importoDovuto;
		this.puntoN = puntoN;
		this.puntoS = puntoS;
		this.puntoE = puntoE;
		this.puntoO = puntoO;
	}


	public java.lang.String getChiavePrenotazione() {
		return chiavePrenotazione;
	}

	public void setChiavePrenotazione(java.lang.String chiavePrenotazione) {
		this.chiavePrenotazione = chiavePrenotazione;
	}

	public java.lang.String getCodiceAutorizzazione() {
		return codiceAutorizzazione;
	}

	public void setCodiceAutorizzazione(java.lang.String codiceAutorizzazione) {
		this.codiceAutorizzazione = codiceAutorizzazione;
	}

	public java.lang.String getNominativoAutorizzazione() {
		return nominativoAutorizzazione;
	}

	public void setNominativoAutorizzazione(
			java.lang.String nominativoAutorizzazione) {
		this.nominativoAutorizzazione = nominativoAutorizzazione;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceBorsellino() {
		return codiceBorsellino;
	}

	public void setCodiceBorsellino(String codiceBorsellino) {
		this.codiceBorsellino = codiceBorsellino;
	}

	public java.lang.String getCodiceMercato() {
		return codiceMercato;
	}

	public void setCodiceMercato(java.lang.String codiceMercato) {
		this.codiceMercato = codiceMercato;
	}

	public java.lang.String getDescrizioneMercato() {
		return descrizioneMercato;
	}

	public void setDescrizioneMercato(java.lang.String descrizioneMercato) {
		this.descrizioneMercato = descrizioneMercato;
	}

	public java.lang.String getCodicePiazzola() {
		return codicePiazzola;
	}

	public void setCodicePiazzola(java.lang.String codicePiazzola) {
		this.codicePiazzola = codicePiazzola;
	}

	public java.lang.String getDescrizionePiazzola() {
		return descrizionePiazzola;
	}

	public void setDescrizionePiazzola(java.lang.String descrizionePiazzola) {
		this.descrizionePiazzola = descrizionePiazzola;
	}

	public java.lang.String getTipologiaBanco() {
		return tipologiaBanco;
	}

	public void setTipologiaBanco(java.lang.String tipologiaBanco) {
		this.tipologiaBanco = tipologiaBanco;
	}

	public java.lang.String getPeriodoGiornaliero() {
		return periodoGiornaliero;
	}

	public void setPeriodoGiornaliero(java.lang.String periodoGiornaliero) {
		this.periodoGiornaliero = periodoGiornaliero;
	}

	public int getGiornoSettimana() {
		return giornoSettimana;
	}

	public void setGiornoSettimana(int giornoSettimana) {
		this.giornoSettimana = giornoSettimana;
	}

	public java.util.Date getData() {
		return data;
	}

	public void setData(java.util.Date data) {
		this.data = data;
	}

	public java.util.Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(java.util.Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public java.lang.Float getImportoPagato() {
		return importoPagato;
	}

	public void setImportoPagato(java.lang.Float importoPagato) {
		this.importoPagato = importoPagato;
	}

	public float getImportoDovuto() {
		return importoDovuto;
	}

	public void setImportoDovuto(float importoDovuto) {
		this.importoDovuto = importoDovuto;
	}

	public java.lang.String getPuntoN() {
		return puntoN;
	}

	public void setPuntoN(java.lang.String puntoN) {
		this.puntoN = puntoN;
	}

	public java.lang.String getPuntoS() {
		return puntoS;
	}

	public void setPuntoS(java.lang.String puntoS) {
		this.puntoS = puntoS;
	}

	public java.lang.String getPuntoE() {
		return puntoE;
	}

	public void setPuntoE(java.lang.String puntoE) {
		this.puntoE = puntoE;
	}

	public java.lang.String getPuntoO() {
		return puntoO;
	}

	public void setPuntoO(java.lang.String puntoO) {
		this.puntoO = puntoO;
	}

	

}
