package com.seda.payer.core.bean;

import java.io.Serializable;
//import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;

//import com.seda.data.spi.PageInfo;
import com.seda.payer.commons.bean.Lifecycle;


/*
- RAN_KRANKFLU, impostato con il valore del parametro progressivoFlusso
- RAN_CSOCCSOC, impostato con il valore del parametro codiceSocieta
- RAN_CUTECUTE, impostato con il valore del parametro codiceUtente
- RAN_KANEKENT, impostato con il valore del parametro chiaveEnte
- RAN_CRANCAGE, impostato con il valore del campo Ag.Ris. del flusso Partite/Articoli
- RAN_CRANCFIS, impostato con il valore del campo Codice Fiscale del flusso Partite/Articoli
- RAN_DRANDENO, impostato con il valore del campo Denominazione del flusso Partite/
Articoli
- RAN_DRANDOMI, impostato con il valore del campo Localita del flusso Partite/Articoli
- RAN_DRANDIND, impostato con il valore del campo Indirizzo del flusso Partite/Articoli
- RAN_CRANTOMB, impostato con il valore "N"

 */



//public class RuoliDettaglioAnagrafica implements Serializable {
	public class AnagraficaPartitaRuolo extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String listXml;		 

    
    protected String progrFlusso;
	protected long progrFlussoL;			//RAN_KRANKFLU	- agg
	protected String concessione;
	protected String codiceSocieta;		//RAN_CSOCCSOC
	protected String codiceUtente;		//RAN_CUTECUTE
	protected String codiceEnte;
	protected String chiaveEnte;			//RAN_KANEKENT	-agg
    protected String agRiscossione;       //RAN_CRANCAGE
	protected String codiceFiscale;		//RAN_CRANCFIS
	protected String denominazione;		//RAN_DRANDENO
	protected String localita;			//RAN_DRANDOMI  - agg
	protected String indirizzo;			//RAN_DRANDIND  - agg
	protected String codiceTomb;			//RAN_CRANTOMB
	

//	public RuoliDettaglioAnagrafica() { 
		public AnagraficaPartitaRuolo() { 
    	progrFlusso = "";
    	concessione = "";
    	codiceTomb = "";
    	codiceSocieta ="";
    	codiceUtente = "";
    	codiceEnte = "";
        codiceFiscale = "";

    }


		public void onSave(CallableStatement arg) throws SQLException {

			arg.setLong(1, progrFlussoL);			//RAN_KRANKFLU	- agg
			arg.setString(2, codiceSocieta);		//RAN_CSOCCSOC
			arg.setString(3, codiceUtente);		//RAN_CUTECUTE
			arg.setString(4, chiaveEnte);		//RAN_KANEKENT	
		    arg.setString(5, agRiscossione);       //RAN_CRANCAGE
			arg.setString(6, codiceFiscale);		//RAN_CRANCFIS
			arg.setString(7, denominazione);		//RAN_DRANDENO
			arg.setString(8, localita);			//RAN_DRANDOMI  - agg
			arg.setString(9, indirizzo);			//RAN_DRANDIND  - agg
			arg.setString(10, codiceTomb);			//RAN_CRANTOMB
		
		}
		
		public void onUpdate(CallableStatement arg) throws SQLException {
		}
		
		public void onLoad(CallableStatement arg) throws SQLException {

		}
		
		public void onDelete(CallableStatement arg) throws SQLException {

		}	
		
//GRAZ		
		
	public String getListXml() {
		return listXml;
	}

	public void setListXml(String listXml) {
		this.listXml = listXml;
	}

	public String getProgrFlusso() {
		return progrFlusso;
	}

	public void setProgrFlusso(String progrFlusso) {
		this.progrFlusso = progrFlusso;
	}

	public String getConcessione() {
		return concessione;
	}

	public void setConcessione(String concessione) {
		this.concessione = concessione;
	}

	public String getCodiceTomb() {
		return codiceTomb;
	}

	public void setCodiceTomb(String codiceTomb) {
		this.codiceTomb = codiceTomb;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}


	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public long getProgrFlussoL() {
		return progrFlussoL;
	}


	public void setProgrFlussoL(long progrFlussoL) {
		this.progrFlussoL = progrFlussoL;
	}


	public String getAgRiscossione() {
		return agRiscossione;
	}


	public void setAgRiscossione(String agRiscossione) {
		this.agRiscossione = agRiscossione;
	}


	public String getDenominazione() {
		return denominazione;
	}


	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}


	public String getLocalita() {
		return localita;
	}


	public void setLocalita(String localita) {
		this.localita = localita;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public String getChiaveEnte() {
		return chiaveEnte;
	}


	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	

}