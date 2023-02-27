package com.seda.payer.core.mercato.bean;

import java.io.Serializable;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazionePrenotazioni extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Tabella PYPRNTB
	private String codiceKeyPrenotazione;		//  "PRN_KPRNKPRN" VARCHAR(64)
	private String codiceSocieta;        		//	"PRN_CSOCCSOC" CHAR(5)
	private String cuteCute;					//	"PRN_CUTECUTE" CHAR(5)
	private String chiaveEnte;					//	"PRN_KANEKENT" CHAR(10)
	private String codiceKeyTariffa;			//  "PRN_CPRNKTAM" VARCHAR(64)
	private Calendar dataPrenotazione;			//  "PRN_GPRNDATA" TIMESTAMP
	private Double importoPagato;				//  "PRN_DPRNIMPO" DECIMAL(9,5)
	private Calendar dataPrenotazioneAl;	


	public ConfigurazionePrenotazioni(){
	}

	public ConfigurazionePrenotazioni(
			 String codiceKeyPrenotazione,
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,			
			 String codiceKeyTariffa,
			 Calendar dataPrenotazione,
			 Double importoPagato,
			 Calendar dataPrenotazioneAl
			 )
	{
		 this.codiceKeyPrenotazione = codiceKeyPrenotazione;
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;
		 this.codiceKeyTariffa = codiceKeyTariffa;
		 this.dataPrenotazione = dataPrenotazione;
		 this.importoPagato = importoPagato;
		 this.dataPrenotazioneAl = dataPrenotazioneAl;
	}


	@Override
	public String toString() {
		return "Prenotazionie [chiavePrenotazione=" + codiceKeyPrenotazione 
		        + "chiaveEnte=" + chiaveEnte
				+ ", codiceSocieta=" + codiceSocieta 
				+ ", cuteCute=" + cuteCute 
				+ ", codiceKeyTariffa=" + codiceKeyTariffa
				+ ", dataPrenotazione=" + dataPrenotazione  
				+ ", importoPagato=" + importoPagato
				+ "]";
	}

	public String getCodiceKeyPrenotazione() {
		return codiceKeyPrenotazione;
	}
	
	public void setCodiceKeyPrenotazione(String codiceKeyPrenotazione) {
		this.codiceKeyPrenotazione = codiceKeyPrenotazione;
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

	public String getCodiceKeyTariffa() {
		return codiceKeyTariffa;
	}
	
	public void setCodiceKeyTariffa(String codiceKeyTariffa) {
		this.codiceKeyTariffa = codiceKeyTariffa;
	}
	
	public Calendar getDataPrenotazione() {
		return dataPrenotazione;
	}
	
	public void setDataPrenotazione(Calendar dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public Double getImportoPagato(){
		return importoPagato;
	}
	
	public void setImportoPagato(Double importoPagato) {
		this.importoPagato = importoPagato;
	}
	
	public Calendar getDataPrenotazioneAl() {
		return dataPrenotazioneAl;
	}
	
	public void setDataPrenotazioneAl(Calendar dataPrenotazioneAl) {
		this.dataPrenotazioneAl = dataPrenotazioneAl;
	}
	
// Fine della Classe	
}
