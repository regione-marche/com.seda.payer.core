package com.seda.payer.core.mercato.bean;

import java.io.Serializable;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazioneTariffe extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Tabella PYTAMTB
	private String codiceKeyTariffa;			//  "TAM_KTAMKTAM" VARCHAR(64)
	private String codiceSocieta;        		//	"TAM_CSOCCSOC" CHAR(5)
	private String cuteCute;					//	"TAM_CUTECUTE" CHAR(5)
	private String chiaveEnte;					//	"TAM_KANEKENT" CHAR(10)
	private String codiceKeyAreaMercantile;		//  "TAM_CTAMKMRC" VARCHAR(10)
	private String codiceKeyPiazzola;		  	//	"TAM_CTAMKPZL" VARCHAR(64)
	private Integer codiceGiornoSettimana;		//  "TAM_ITAMGSEM" INTEGER
	private String codiceKeyAutorizzazione;		//  "TAM_CTAMKAUT" VARCHAR(10)
	private String codiceKeyTipologiaBanco;     //  "TAM_CTAMKTPB" VARCHAR(10)
	private String codiceKeyPeriodoGiornal;     //  "TAM_CTAMKPEG" VARCHAR(10)
	private String codiceKeyCompenso;           //  "TAM_CTAMKCOM" VARCHAR(10)
	private Double tariffaTari;					//	"TAM_DTAMTRTA" DECIMAL(9,5),
	private Double tariffaCosap;				//  "TAM_DTAMTRCO" DECIMAL(9,5),
	private Calendar dataInizioValidita;		//  "TAM_GTAMDTIN" TIMESTAMP
	private Calendar dataFineValidita;			//  "TAM_GTAMDTFN" TIMESTAMP
	private Calendar dataAInizioValidita;
	private Calendar dataAFineValidita;
	private String descrAreaMercato;			
	private String descrPiazzola;         
	private String descrTipologiaBanco;
	private String descrPeriodoGiorn;
	

	public ConfigurazioneTariffe(){
	}

	public ConfigurazioneTariffe(
			 String codiceKeyTariffa,
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,			
			 String codiceKeyAreaMercantile,
			 String codiceKeyPiazzola,
			 Integer codiceGiornoSettimana,
			 String codiceKeyAutorizzazione,
			 String codiceKeyTipologiaBanco,
			 String codiceKeyPeriodoGiornal,
			 String codiceKeyCompenso,
			 Double tariffaTari,
			 Double tariffaCosap,
			 Calendar dataInizioValidita,
			 Calendar dataFineValidita,
			 Calendar dataAInizioValidita,
			 Calendar dataAFineValidita,
			 String descrAreaMercato,
			 String descrPiazzola,
			 String descrTipologiaBanco,
			 String descrPeriodoGiorn)
	{
		 this.codiceKeyTariffa = codiceKeyTariffa;
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;
		 this.codiceKeyAreaMercantile = codiceKeyAreaMercantile;
		 this.codiceKeyPiazzola = codiceKeyPiazzola;
		 this.codiceGiornoSettimana = codiceGiornoSettimana;
		 this.codiceKeyAutorizzazione = codiceKeyAutorizzazione;
		 this.codiceKeyTipologiaBanco = codiceKeyTipologiaBanco;
		 this.codiceKeyPeriodoGiornal = codiceKeyPeriodoGiornal;
		 this.codiceKeyCompenso = codiceKeyCompenso;
		 this.tariffaTari = tariffaTari;
		 this.tariffaCosap = tariffaCosap;
		 this.dataInizioValidita = dataInizioValidita;
		 this.dataFineValidita = dataFineValidita;
		 this.dataAInizioValidita = dataAInizioValidita;
		 this.dataAFineValidita = dataAFineValidita;
		 this.descrAreaMercato = descrAreaMercato;
		 this.descrPiazzola = descrPiazzola;
		 this.descrTipologiaBanco = descrTipologiaBanco;
		 this.descrPeriodoGiorn = descrPeriodoGiorn;
	}


	@Override
	public String toString() {
		return "ConfigurazioneTariffe [chiaveTariffa=" + codiceKeyTariffa 
		        + "chiaveEnte=" + chiaveEnte
				+ ", codiceSocieta=" + codiceSocieta 
				+ ", cuteCute=" + cuteCute 
				+ ", codiceKeyAreaMercantile=" + codiceKeyAreaMercantile
				+ ", codiceKeyPiazzola=" + codiceKeyPiazzola  
				+ ", codiceGiornoSettimana=" + codiceGiornoSettimana
				+ ", codiceKeyAutorizzazione=" + codiceKeyAutorizzazione
				+ ", codiceKeyTipologiaBanco=" + codiceKeyTipologiaBanco
				+ ", codiceKeyPeriodoGiornal=" + codiceKeyPeriodoGiornal
				+ ", codiceKeyCompenso=" + codiceKeyCompenso
				+ ", tariffaTari=" + tariffaTari
				+ ", tariffaCosip=" + tariffaCosap
				+ ", dataInizioValidita=" + dataInizioValidita
				+ ", dataFineValidita=" + dataFineValidita
				+ "]";
	}

	public String getCodiceKeyTariffa() {
		return codiceKeyTariffa;
	}
	
	public void setCodiceKeyTariffa(String codiceKeyTariffa) {
		this.codiceKeyTariffa = codiceKeyTariffa;
	}
	
	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCuteCute() {
		return cuteCute;
	}

	public void setCuteCute(String cuteCute) {
		this.cuteCute = cuteCute;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public String getCodiceKeyAreaMercantile() {
		return codiceKeyAreaMercantile;
	}
	
	public void setCodiceKeyAreaMercantile(String codiceKeyAreaMercantile) {
		this.codiceKeyAreaMercantile = codiceKeyAreaMercantile;
	}

	public String getCodiceKeyPiazzola() {
		return codiceKeyPiazzola;
	}

	public void setCodiceKeyPiazzola(String codiceKeyPiazzola) {
		this.codiceKeyPiazzola = codiceKeyPiazzola;
	}
	
	public Integer getCodiceGiornoSettimana() {
		return codiceGiornoSettimana;
	}

	public void setCodiceGiornoSettimana(int codiceGiornoSettimana) {
		this.codiceGiornoSettimana = codiceGiornoSettimana;
	}
	
	public String getCodiceKeyAutorizzazione() {
		return codiceKeyAutorizzazione;
	}
	
	public void setCodiceKeyAutorizzazione(String codiceKeyAutorizzazione){
		this.codiceKeyAutorizzazione = codiceKeyAutorizzazione;
	}
	
	public String getCodiceKeyTipologiaBanco() {
		return codiceKeyTipologiaBanco;
	}
	
	public void setCodiceKeyTipologiaBanco(String codiceKeyTipologiaBanco) {
		this.codiceKeyTipologiaBanco = codiceKeyTipologiaBanco;
	}
	
	public String getCodiceKeyPeriodoGiornal() {
		return codiceKeyPeriodoGiornal;
	}
	
	public void setCodiceKeyPeriodoGiornal(String codiceKeyPeriodoGiornal){
		this.codiceKeyPeriodoGiornal = codiceKeyPeriodoGiornal;
	}

	public String getCodiceKeyCompenso() {
		return codiceKeyCompenso;
	}
	
	public void setCodiceKeyCompenso(String codiceKeyCompenso){
		this.codiceKeyCompenso = codiceKeyCompenso;
	}

	public Double getTariffaTari(){
		return tariffaTari;
	}
	
	public void setTariffaTari(Double tariffaTari) {
		this.tariffaTari = tariffaTari;
	}
	
	public Double getTariffaCosap(){
		return tariffaCosap;
	}
	
	public void setTariffaCosap(Double tariffaCosap) {
		this.tariffaCosap = tariffaCosap;
	}	
		
	public Calendar getDataInizioValidita() {
		return dataInizioValidita;
	}
	
	public void setDataInizioValidita(Calendar dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Calendar getDataFineValidita() {
		return dataFineValidita;
	}
	
	public void setDataFineValidita(Calendar dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	
	public Calendar getDataAInizioValidita() {
		return dataAInizioValidita;
	}
	
	public void setDataAInizioValidita(Calendar dataAInizioValidita) {
		this.dataAInizioValidita = dataAInizioValidita;
	}

	public Calendar getDataAFineValidita() {
		return dataAFineValidita;
	}
	
	public void setDataAFineValidita(Calendar dataAFineValidita) {
		this.dataAFineValidita = dataAFineValidita;
	}
	
	public String getDescrAreaMercato() {
		return descrAreaMercato;
	}
	
	public void setDescrAreaMercato(String descrAreaMercato){
		this.descrAreaMercato = descrAreaMercato;
	}	
	
	public String getDescrPiazzola() {
		return descrPiazzola;
	}
	
	public void setDescrPiazzola(String descrPiazzola){
		this.descrPiazzola = descrPiazzola;
	}		

	public String getDescrTipologiaBanco() {
		return descrTipologiaBanco;
	}
	
	public void setDescrTipologiaBanco(String descrTipologiaBanco){
		this.descrTipologiaBanco = descrTipologiaBanco;
	}	

	public String getDescrPeriodoGiorn() {
		return descrPeriodoGiorn;
	}
	
	public void setDescrPeriodoGiorn(String descrPeriodoGiorn){
		this.descrPeriodoGiorn = descrPeriodoGiorn;
	}	
	
	
// Fine della Classe	
}
