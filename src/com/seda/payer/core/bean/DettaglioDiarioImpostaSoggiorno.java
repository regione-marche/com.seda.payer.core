package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class DettaglioDiarioImpostaSoggiorno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chiaveDettaglioCompilazione;
	private String chiaveTestataCompilazione;
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

	public DettaglioDiarioImpostaSoggiorno() {}
	
	public DettaglioDiarioImpostaSoggiorno(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setChiaveDettaglioCompilazione(data.getString("SDD_KSDDKSDD"));
    	setChiaveTestataCompilazione(data.getString("SDD_KSDTKSDT"));
    	setChiaveTipologiaSoggetto(data.getString("SDD_KSSGKSSG"));
    	setNumeroGiorniPermanenzaTotale(data.getInt("SDD_NSDDGPER"));
    	setNumeroGiorniPermanenzaPagamento(data.getInt("SDD_NSDDGPAG"));
    	setNumeroPersone(data.getInt("SDD_NSDDNUTE"));
    	setImportoDaPagare(data.getBigDecimal("SDD_ISDDIIMP"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTimestamp("SDD_GSDDGAGG").getTime());
        setDataUltimoAggiornamento(cal);
        setOperatoreUltimoAggiornamento(data.getString("SDD_CSDDCOPE"));
    	//inizio LP PG1800XX_016
        setFlagEsenzione(data.getString("SSG_FSSGFESE"));
        setChiaveFasciaTariffa(data.getInt("SDD_KSFIKSFI"));
        setImportoFasciaTariffa(data.getBigDecimal("SDD_ISFIITAR"));
        setChiaveTariffa(data.getString("SDD_KSTFKSTF"));
    	//fine LP PG1800XX_016
    }
	
	public DettaglioDiarioImpostaSoggiorno(ResultSet data, boolean bReduced) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setChiaveTipologiaSoggetto(data.getString("SDD_KSSGKSSG"));
    	setNumeroGiorniPermanenzaTotale(data.getInt("SDD_NSDDGPER"));
    	setNumeroGiorniPermanenzaPagamento(data.getInt("SDD_NSDDGPAG"));
    	setNumeroPersone(data.getInt("SDD_NSDDNUTE"));
    	setImportoDaPagare(data.getBigDecimal("SDD_ISDDIIMP"));
    	//inizio LP PG1800XX_016
        setFlagEsenzione(data.getString("SSG_FSSGFESE"));
        setChiaveFasciaTariffa(data.getInt("SDD_KSFIKSFI"));
        setImportoFasciaTariffa(data.getBigDecimal("SDD_ISFIITAR"));
        setChiaveTariffa(data.getString("SDD_KSTFKSTF"));
    	//fine LP PG1800XX_016
    }
	
	public String getChiaveDettaglioCompilazione() {
		return chiaveDettaglioCompilazione;
	}
	public void setChiaveDettaglioCompilazione(String chiaveDettaglioCompilazione) {
		this.chiaveDettaglioCompilazione = chiaveDettaglioCompilazione;
	}
	public String getChiaveTestataCompilazione() {
		return chiaveTestataCompilazione;
	}
	public void setChiaveTestataCompilazione(String chiaveTestataCompilazione) {
		this.chiaveTestataCompilazione = chiaveTestataCompilazione;
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
}