package com.seda.payer.core.mercato.bean;

import java.io.Serializable;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazioneAreaMercantile extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Tabella PYMRCTB
	private String codiceSocieta;        		//	"MRC_CSOCCSOC" CHAR(5)
	private String cuteCute;					//	"MRC_CUTECUTE" CHAR(5)
	private String chiaveEnte;					//	"MRC_KANEKENT" CHAR(10)
	private String codiceKeyAreaMercantile;		//  "MRC_KMRCKMRC" VARCHAR(10)
	private String codiceAreaMercantile;  		//	"MRC_CMRCCTIP" VARCHAR(10)
	private String descrizioneAreaMercantile;  	//	"MRC_CMRCDSAM" VARCHAR(70)
	private Calendar dataInizioValidita;		//  "MRC_GMRCDTIN" TIMESTAMP
	private Calendar dataFineValidita;			//  "MRC_GMRCDTFN" TIMESTAMP
	

	public ConfigurazioneAreaMercantile(){
	}

	public ConfigurazioneAreaMercantile(
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,
			 String codiceKeyAreaMercantile,
			 String codiceAreaMercantile,
			 String descrizioneAreaMercantile,
			 Calendar dataInizioValidita,
			 Calendar dataFineValidita)
	{
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;
		 this.codiceKeyAreaMercantile = codiceKeyAreaMercantile;
		 this.codiceAreaMercantile = codiceAreaMercantile;
		 this.descrizioneAreaMercantile = descrizioneAreaMercantile;
		 this.dataInizioValidita = dataInizioValidita;
		 this.dataFineValidita = dataFineValidita;
	}


	@Override
	public String toString() {
		return "ConfigurazioneAreaMercantile [chiaveEnte=" + chiaveEnte
				+ ", codiceSocieta="
				+ codiceSocieta + ", cuteCute=" + cuteCute
				+ ", codiceKeyAreaMercantile=" + codiceKeyAreaMercantile
				+ ", codiceAreaMercantile=" + codiceAreaMercantile  
				+ ", descrizioneAreaMercantile=" + descrizioneAreaMercantile
				+ ", dataInizioValidita=" + dataInizioValidita
				+ ", dataFineValidita=" + dataFineValidita
				+ "]";
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
	
	public String getCodiceAreaMercantile() {
		return codiceAreaMercantile;
	}

	public void setCodiceAreaMercantile(String codiceAreaMercantile) {
		this.codiceAreaMercantile = codiceAreaMercantile;
	}

	public String getDescrizioneAreaMercantile() {
		return descrizioneAreaMercantile;
	}

	public void setDescrizioneAreaMercantile(String descrizioneAreaMercantile) {
		this.descrizioneAreaMercantile = descrizioneAreaMercantile;
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
