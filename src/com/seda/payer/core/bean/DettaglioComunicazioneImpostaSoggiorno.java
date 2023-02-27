package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class DettaglioComunicazioneImpostaSoggiorno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chiaveDettaglioComunicazione;
	private String chiaveTestataComunicazione;
	private String chiaveTipologiaSoggetto;
	//inizio LP PG1800XX_016
	private String flagEsenzione;
	private int chiaveFasciaTariffa;
	private BigDecimal importoFasciaTariffa;
	//fine LP PG1800XX_016
	private int numeroGiorniPermanenzaTotale;
	private int numeroGiorniPermanenzaPagamento;
	private int numeroPersone;
	private BigDecimal importoDaPagare;
	private Calendar dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	private String chiaveTariffa;
	private String descrizioneTipoSoggetto;
	
	

	public DettaglioComunicazioneImpostaSoggiorno() {}
	
	public DettaglioComunicazioneImpostaSoggiorno(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	

    	setChiaveDettaglioComunicazione(data.getString("SCD_KSCDKSCD"));
    	setChiaveTestataComunicazione(data.getString("SCD_KSCTKSCT"));
    	setChiaveTipologiaSoggetto(data.getString("SCD_KSSGKSSG"));
    	setNumeroGiorniPermanenzaTotale(data.getInt("SCD_NSCDGPER"));
    	setNumeroGiorniPermanenzaPagamento(data.getInt("SCD_NSCDGPAG"));
    	setNumeroPersone(data.getInt("SCD_NSCDNUTE"));
    	setImportoDaPagare(data.getBigDecimal("SCD_ISCDIIMP"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTimestamp("SCD_GSCDGAGG").getTime());
        setDataUltimoAggiornamento(cal);
        setOperatoreUltimoAggiornamento(data.getString("SCD_CSCDCOPE"));
    	//inizio LP PG1800XX_016
        setFlagEsenzione(data.getString("SSG_FSSGFESE"));
        setChiaveFasciaTariffa(data.getInt("SCD_KSFIKSFI"));
        setImportoFasciaTariffa(data.getBigDecimal("SCD_ISFIITAR"));
    	//fine LP PG1800XX_016
        setChiaveTariffa(data.getString("SCD_KSTFKSTF"));
        setDescrizioneTipoSoggetto(data.getString("SSG_DSSGDSSG"));
    }
	
	public String getChiaveDettaglioComunicazione() {
		return chiaveDettaglioComunicazione;
	}
	public void setChiaveDettaglioComunicazione(String chiaveDettaglioComunicazione) {
		this.chiaveDettaglioComunicazione = chiaveDettaglioComunicazione;
	}
	public String getChiaveTestataComunicazione() {
		return chiaveTestataComunicazione;
	}
	public void setChiaveTestataComunicazione(String chiaveTestataComunicazione) {
		this.chiaveTestataComunicazione = chiaveTestataComunicazione;
	}
	public String getChiaveTipologiaSoggetto() {
		return chiaveTipologiaSoggetto;
	}
	public void setChiaveTipologiaSoggetto(String chiaveTipologiaSoggetto) {
		this.chiaveTipologiaSoggetto = chiaveTipologiaSoggetto;
	}
	public int getNumeroGiorniPermanenzaTotale() {
		return numeroGiorniPermanenzaTotale;
	}
	public void setNumeroGiorniPermanenzaTotale(int numeroGiorniPermanenzaTotale) {
		this.numeroGiorniPermanenzaTotale = numeroGiorniPermanenzaTotale;
	}
	public int getNumeroGiorniPermanenzaPagamento() {
		return numeroGiorniPermanenzaPagamento;
	}
	public void setNumeroGiorniPermanenzaPagamento(
			int numeroGiorniPermanenzaPagamento) {
		this.numeroGiorniPermanenzaPagamento = numeroGiorniPermanenzaPagamento;
	}
	public int getNumeroPersone() {
		return numeroPersone;
	}
	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}
	public BigDecimal getImportoDaPagare() {
		return importoDaPagare;
	}
	public void setImportoDaPagare(BigDecimal importoDaPagare) {
		this.importoDaPagare = importoDaPagare;
	}
	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}
	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}
	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}
	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}
	//inizio LP PG1800XX_016
	public void setFlagEsenzione(String flagEsenzione) {
		this.flagEsenzione = flagEsenzione;
	}
	public String getFlagEsenzione() {
		return flagEsenzione;
	}
	public int getChiaveFasciaTariffa() {
		return chiaveFasciaTariffa;
	}
	public void setChiaveFasciaTariffa(int chiaveFasciaTariffa) {
		this.chiaveFasciaTariffa = chiaveFasciaTariffa;
	}
	public void setImportoFasciaTariffa(BigDecimal importoFasciaTariffa) {
		this.importoFasciaTariffa = importoFasciaTariffa;
	}
	public BigDecimal getImportoFasciaTariffa() {
		return importoFasciaTariffa;
	}
	//fine LP PG1800XX_016

	public String getChiaveTariffa() {
		return chiaveTariffa;
	}

	public void setChiaveTariffa(String chiaveTariffa) {
		this.chiaveTariffa = chiaveTariffa;
	}
	public String getDescrizioneTipoSoggetto() {
		return descrizioneTipoSoggetto;
	}

	public void setDescrizioneTipoSoggetto(String descrizioneTipoSoggetto) {
		this.descrizioneTipoSoggetto = descrizioneTipoSoggetto;
	}
}