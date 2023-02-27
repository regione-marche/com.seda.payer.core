package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;


public class LogAnagraficaFiglioMense extends ModelAttributes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	//private String identificativoRecord;		//  "LFM_KLFMKLFM" Key
	private String idWallet;					//	"LFM_KBRSKBRS" VARCHAR(18)
	private String codiceServizio;				//	"LFM_CSRVCODI" CHAR(2)
	private String codiceAnagraficaFiglio;		//	"LFM_CAFMCANA" VARCHAR(10)
	private String codiceFiscaleFiglio;			//	"LFM_CAFMCFIS" VARCHAR(16)
	private String codiceAnagraficaGenitore;	//	"LFM_CANGAGEN" VARCHAR(10)
	private String codiceFiscaleGenitore;		//	"LFM_CANGCFIS" VARCHAR(16)
	private String denominazioneFiglio;			//	"LFM_DAFMDENO" VARCHAR(61)
	private String codiceScuola;				//	"LFM_CSCUSCOD" VARCHAR(6)
	private Calendar dataValidita;				//	"LFM_GAFMGDTV" TIMESTAMP
	private BigDecimal importoTariffa;			//	"LFM_IAFMIMPT" DECIMAL(10 , 0)
	private BigDecimal importoIsee;				//	"LFM_IAFMISEE" DECIMAL(10 , 0)
	private String tipologiaTariffa;			//	"LFM_TAFMTTAR" VARCHAR(1)	
	private String classeSezione;				//	"LFM_CAFMCLSZ" VARCHAR(2)
	private Boolean flagAttivazione;			//	"LFM_FAFMFLAT" CHAR(1)
	private Calendar dataCaricamento;			//	"LFM_GAFMGCAR" TIMESTAMP
	private String flagSospensione;				//	"LFM_FAFMFSOS" CHAR(1)		//PG150310_001 GG
	
	
	public LogAnagraficaFiglioMense(){
	};
	
	public LogAnagraficaFiglioMense (
			
			// String identificativoRecord,
			 String idWallet,
			 String codiceServizio,
			 String codiceAnagraficaFiglio,
			 String codiceFiscaleFiglio,
			 String codiceAnagraficaGenitore,
			 String codiceFiscaleGenitore,
			 String denominazioneFiglio,
			 String codiceScuola,				//	"LFM_CSCUSCOD" VARCHAR(6)
			 Calendar dataValidita,				//	"LFM_GAFMGDTV" TIMESTAMP
			 BigDecimal importoTariffa,			//	"LFM_IAFMIMPT" DECIMAL(10 , 0)
			 BigDecimal importoIsee,			//	"LFM_IAFMISEE" DECIMAL(10 , 0)
			 String tipologiaTariffa,			//	"LFM_TAFMTTAR" VARCHAR(1)	
			 String classeSezione,				//	"LFM_CAFMCLSZ" VARCHAR(2)
			 Boolean flagAttivazione,			//	"LFM_FAFMFLAT" CHAR(1)
			 Calendar dataCaricamento,
			 String flagSospensione)
	{
		//this.identificativoRecord=identificativoRecord;
		this.idWallet=idWallet;
		this.codiceServizio=codiceServizio;
		this.codiceAnagraficaFiglio=codiceAnagraficaFiglio;
		this.codiceFiscaleFiglio=codiceFiscaleFiglio;
		this.codiceAnagraficaGenitore=codiceAnagraficaGenitore;
		this.codiceFiscaleGenitore=codiceFiscaleGenitore;
		this.denominazioneFiglio=denominazioneFiglio;
		this.codiceScuola=codiceScuola;
		this.dataValidita=dataValidita;		
		this.importoTariffa=importoTariffa;
		this.importoIsee=importoIsee;
		this.tipologiaTariffa=tipologiaTariffa;
		this.classeSezione=classeSezione;
		this.flagAttivazione=flagAttivazione;
		this.dataCaricamento=dataCaricamento;
		this.flagSospensione=flagSospensione;
	}

//	public String getIdentificativoRecord() {
//		return identificativoRecord;
//	}
//
//	public void setIdentificativoRecord(String identificativoRecord) {
//		this.identificativoRecord = identificativoRecord;
//	}

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

	public Boolean getFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(Boolean flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public Calendar getDataCaricamento() {
		return dataCaricamento;
	}

	public void setDataCaricamento(Calendar dataCaricamento) {
		this.dataCaricamento = dataCaricamento;
	}

	//PG150310_001 GG - inizio
	public String getFlagSospensione() {
		return flagSospensione;
	}

	public void setFlagSospensione(String flagSospensione) {
		this.flagSospensione = flagSospensione;
	}
	//PG150310_001 GG - fine

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//PG150310_001 GG - introduzione flagSospensione
	@Override
	public String toString() {
		return "LogAnagraficaFiglioMense [classeSezione=" + classeSezione
				+ ", codiceAnagraficaFiglio=" + codiceAnagraficaFiglio
				+ ", codiceAnagraficaGenitore=" + codiceAnagraficaGenitore
				+ ", codiceFiscaleFiglio=" + codiceFiscaleFiglio
				+ ", codiceFiscaleGenitore=" + codiceFiscaleGenitore
				+ ", codiceScuola=" + codiceScuola  
				+ ", codiceServizio="	+ codiceServizio  
				+ ", dataCaricamento=" + dataCaricamento 
				+ ", dataValidita=" + dataValidita 
				+ ", denominazioneFiglio="+ denominazioneFiglio 
				+ ", flagAttivazione=" + flagAttivazione
				+ ", flagSospensione=" + flagSospensione
				+ ", idWallet=" + idWallet 
				+ ", importoIsee=" + importoIsee 
				+ ", importoTariffa=" + importoTariffa
				+ ", tipologiaTariffa=" + tipologiaTariffa + "]";
	}	
}
