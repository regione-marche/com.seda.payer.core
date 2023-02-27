package com.seda.payer.core.mercato.bean;

import java.io.Serializable;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazioneAnagAutorizzazione extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Tabella PYAUTTB
	private String codiceKeyAnagAutor;				//  "AUT_KAUTKAUT" VARCHAR64					
	private String codiceSocieta;        			//	"AUT_CSOCCSOC" CHAR(5)
	private String cuteCute;						//	"AUT_CUTECUTE" CHAR(5)
	private String chiaveEnte;						//	"AUT_KANEKENT" CHAR(10)
	private String codiceAnagAutorizzazione;  		//	"AUT_CAUTNMAU" VARCHAR(30)
	private String codiceFiscAnagAutorizzazione;	//	"AUT_CAUTCFPI" VARCHAR(20)
	private String nominativoAnagAutorizzazione;	//	"AUT_CAUTNOMN" VARCHAR(90)
	private Calendar dataInizioValidita;			//  "AUT_GAUTDTIN" TIMESTAMP
	private Calendar dataFineValidita;				//  "AUT_GAUTDTFN" TIMESTAMP
	

	public ConfigurazioneAnagAutorizzazione(){
	}

	public ConfigurazioneAnagAutorizzazione(
			 String codiceKeyAnagAutor,
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,						
			 String codiceAnagAutorizzazione,
			 String codiceFiscAnagAutorizzazione,
			 String nominativoAnagAutorizzazione,
			 Calendar dataInizioValidita,
			 Calendar dataFineValidita)
	{
		 this.codiceKeyAnagAutor = codiceKeyAnagAutor;
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;			
		 this.codiceAnagAutorizzazione = codiceAnagAutorizzazione;
		 this.codiceFiscAnagAutorizzazione = codiceFiscAnagAutorizzazione;
		 this.nominativoAnagAutorizzazione = nominativoAnagAutorizzazione;
		 this.dataInizioValidita = dataInizioValidita;
		 this.dataFineValidita = dataFineValidita;
	}


	@Override
	public String toString() {
		return "ConfigurazioneAreaMercantile [codiceKeyAnagAutor=" + codiceKeyAnagAutor
				+ ",chiaveEnte=" + chiaveEnte
				+ ", codiceSocieta="
				+ codiceSocieta + ", cuteCute=" + cuteCute 
				+ ", codiceAnagAutorizzazione=" + codiceAnagAutorizzazione  
				+ ", codiceFiscAnagAutorizzazione=" + codiceFiscAnagAutorizzazione
				+ ", nominativoAnagAutorizzazione=" + nominativoAnagAutorizzazione
				+ ", dataInizioValidita=" + dataInizioValidita
				+ ", dataFineValidita=" + dataFineValidita
				+ "]";
	}

	public String getCodiceKeyAnagAutor() {
		return codiceKeyAnagAutor;
	}
	
	public void setCodiceKeyAnagAutor(String codiceKeyAnagAutor) {
		this.codiceKeyAnagAutor = codiceKeyAnagAutor;
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

	public String getCodiceAnagAutorizzazione() {
		return codiceAnagAutorizzazione;
	}

	public void setCodiceAnagAutorizzazione(String codiceAnagAutorizzazione) {
		this.codiceAnagAutorizzazione = codiceAnagAutorizzazione;
	}

	public String getCodiceFiscAnagAutorizzazione() {
		return codiceFiscAnagAutorizzazione;
	}

	public void setCodiceFiscAnagAutorizzazione(String codiceFiscAnagAutorizzazione) {
		this.codiceFiscAnagAutorizzazione = codiceFiscAnagAutorizzazione;
	}
	
	public String getNominativoAnagAutorizzazione() {
		return nominativoAnagAutorizzazione;
	}

	public void setNominativoAnagAutorizzazione(String nominativoAnagAutorizzazione) {
		this.nominativoAnagAutorizzazione = nominativoAnagAutorizzazione;
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

}
