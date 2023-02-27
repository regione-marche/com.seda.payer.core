package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;


public class AnagraficaFiglioMense extends ModelAttributes implements Serializable{

	private static final long serialVersionUID = 1L;
	//	Tabella PYAFMTB
	private String idWallet; 					//	"AFM_KBRSKBRS" VARCHAR(18)
	private String codiceServizio;				//	"AFM_CSRVCODI" CHAR(2)
	private String codiceAnagraficaFiglio;		//	"AFM_CAFMCANA" VARCHAR(10)
	private String codiceFiscaleFiglio;			//	"AFM_CAFMCFIS" VARCHAR(16)
	private String codiceAnagraficaGenitore;	//	"AFM_CANGAGEN" VARCHAR(10)
	private String codiceFiscaleGenitore;		//	"AFM_CANGCFIS" VARCHAR(16)
	private String denominazioneFiglio;			//	"AFM_DAFMDENO" VARCHAR(61)
	private String codiceScuola;				//	"AFM_CSCUSCOD" VARCHAR(6)
	private Calendar dataValidita;				//	"AFM_GAFMGDTV" TIMESTAMP
	private BigDecimal importoTariffa;			//	"AFM_IAFMIMPT" DECIMAL(10 , 0)
	private BigDecimal importoIsee;				//	"AFM_IAFMISEE" DECIMAL(10 , 0)
	private String tipologiaTariffa;			//	"AFM_TAFMTTAR" VARCHAR(1)	
	private String classeSezione;				//	"AFM_CAFMCLSZ" VARCHAR(2)
	private String flagAttivazione;				//	"AFM_FAFMFLAT" CHAR(1)
	private Calendar  dataCaricamento;			//	"AFM_GAFMGCAR" TIMESTAMP
	private BigDecimal importoCaricamento;
	private BigDecimal importoPagato;
	private BigDecimal importoRendicontato;
	private Calendar dataNascitaFiglio;
	private String note;
	private String flagSospensione;				//	"AFM_FAFMFSOS" CHAR(1)		//PG150310_001 GG	
	
	public AnagraficaFiglioMense(){
	};
	
	public AnagraficaFiglioMense (
			String idWallet,
			String codiceServizio,
			String codiceAnagraficaFiglio,
			String codiceFiscaleFiglio,
			String codiceAnagraficaGenitore,
			String codiceFiscaleGenitore,
			String denominazioneFiglio,
			String codiceScuola,
			Calendar dataValidita,
			String tipologiaTariffa,
			String classeSezione,
			BigDecimal importoTariffa,
			BigDecimal importoIsee,
			String flagAttivazione,
			Calendar dataCaricamento,
			BigDecimal importoCaricamento,
			BigDecimal importoPagato,
			BigDecimal importoRendicontato,
			String note,
			String flagSospensione)				//PG150310_001 GG
	{
		this.idWallet=idWallet;
		this.codiceServizio=codiceServizio;
		this.codiceAnagraficaFiglio=codiceAnagraficaFiglio;
		this.codiceFiscaleFiglio=codiceFiscaleFiglio;
		this.codiceAnagraficaGenitore=codiceAnagraficaGenitore;
		this.codiceFiscaleGenitore=codiceFiscaleGenitore;
		this.denominazioneFiglio=denominazioneFiglio;
		this.codiceScuola=codiceScuola;
		this.dataValidita=dataValidita;
		this.tipologiaTariffa=tipologiaTariffa;
		this.classeSezione=classeSezione;
		this.importoTariffa=importoTariffa;
		this.importoIsee=importoIsee;
		this.flagAttivazione=flagAttivazione;
		this.dataCaricamento=dataCaricamento;
		this.importoCaricamento=importoCaricamento;
		this.importoPagato=importoPagato;
		this.importoRendicontato=importoRendicontato;
		this.note=note;
		this.flagSospensione=flagSospensione;		//PG150310_001 GG
	}

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}

	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public String getCodiceAnagraficaFiglio() {
		return codiceAnagraficaFiglio;
	}

	public void setCodiceAnagraficaFiglio(String codiceAnagraficaFiglio) {
		this.codiceAnagraficaFiglio = codiceAnagraficaFiglio;
	}

	public String getCodiceFiscaleFiglio() {
		return codiceFiscaleFiglio;
	}

	public void setCodiceFiscaleFiglio(String codiceFiscaleFiglio) {
		this.codiceFiscaleFiglio = codiceFiscaleFiglio;
	}

	public String getCodiceAnagraficaGenitore() {
		return codiceAnagraficaGenitore;
	}

	public void setCodiceAnagraficaGenitore(String codiceAnagraficaGenitore) {
		this.codiceAnagraficaGenitore = codiceAnagraficaGenitore;
	}

	public String getCodiceFiscaleGenitore() {
		return codiceFiscaleGenitore;
	}

	public void setCodiceFiscaleGenitore(String codiceFiscaleGenitore) {
		this.codiceFiscaleGenitore = codiceFiscaleGenitore;
	}

	public String getDenominazioneFiglio() {
		return denominazioneFiglio;
	}

	public void setDenominazioneFiglio(String denominazioneFiglio) {
		this.denominazioneFiglio = denominazioneFiglio;
	}

	public String getCodiceScuola() {
		return codiceScuola;
	}

	public void setCodiceScuola(String codiceScuola) {
		this.codiceScuola = codiceScuola;
	}

	public Calendar getDataValidita() {
		return dataValidita;
	}

	public void setDataValidita(Calendar dataValidita) {
		this.dataValidita = dataValidita;
	}

	public String getTipologiaTariffa() {
		return tipologiaTariffa;
	}

	public void setTipologiaTariffa(String tipologiaTariffa) {
		this.tipologiaTariffa = tipologiaTariffa;
	}

	public String getClasseSezione() {
		return classeSezione;
	}

	public void setClasseSezione(String classeSezione) {
		this.classeSezione = classeSezione;
	}

	public BigDecimal getImportoTariffa() {
		return importoTariffa;
	}

	public void setImportoTariffa(BigDecimal importoTariffa) {
		this.importoTariffa = importoTariffa;
	}

	public BigDecimal getImportoIsee() {
		return importoIsee;
	}

	public void setImportoIsee(BigDecimal importoIsee) {
		this.importoIsee = importoIsee;
	}

	public String getFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(String flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public Calendar getDataCaricamento() {
		return dataCaricamento;
	}

	public void setDataCaricamento(Calendar dataCaricamento) {
		this.dataCaricamento = dataCaricamento;
	}

	public BigDecimal getImportoCaricamento() {
		return importoCaricamento;
	}

	public void setImportoCaricamento(BigDecimal importoCaricamento) {
		this.importoCaricamento = importoCaricamento;
	}

	public BigDecimal getImportoPagato() {
		return importoPagato;
	}

	public void setImportoPagato(BigDecimal importoPagato) {
		this.importoPagato = importoPagato;
	}

	public BigDecimal getImportoRendicontato() {
		return importoRendicontato;
	}

	public void setImportoRendicontato(BigDecimal importoRendicontato) {
		this.importoRendicontato = importoRendicontato;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Calendar getDataNascitaFiglio() {
		return dataNascitaFiglio;
	}

	public void setDataNascitaFiglio(Calendar dataNascitaFiglio) {
		this.dataNascitaFiglio = dataNascitaFiglio;
	}
	
	//PG150310_001 GG - inizio
	public String getFlagSospensione() {
		return flagSospensione;
	}

	public void setFlagSospensione(String flagSospensione) {
		this.flagSospensione = flagSospensione;
	}
	//PG150310_001 GG - fine
		
}
