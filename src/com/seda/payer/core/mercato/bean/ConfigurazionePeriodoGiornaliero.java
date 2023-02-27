package com.seda.payer.core.mercato.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazionePeriodoGiornaliero extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Tabella PYPEGTB
	private String codiceKeyPeriodoGiornaliero;		//  "PEG_KPEGKPEG" VARCHAR(10)
	private String codiceSocieta;        			//	"PEG_CSOCCSOC" CHAR(5)
	private String cuteCute;						//	"PEG_CUTECUTE" CHAR(5)
	private String chiaveEnte;						//	"PEG_KANEKENT" CHAR(10)
	private String codicePeriodoGiornaliero;		//	"PEG_CPEGCTIP" VARCHAR(10)
	private String descrizionePeriodoGiornaliero;	//	"PEG_CPEGDSPE" VARCHAR(70)
	

	public ConfigurazionePeriodoGiornaliero(){
	}

	public ConfigurazionePeriodoGiornaliero(
			 String codiceKeyPeriodoGiornaliero,
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,						
			 String codicePeriodoGiornaliero,
			 String descrizionePeriodoGiornaliero)
	{
		 this.codiceKeyPeriodoGiornaliero = codiceKeyPeriodoGiornaliero;
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;			
		 this.codicePeriodoGiornaliero = codicePeriodoGiornaliero;
		 this.descrizionePeriodoGiornaliero = descrizionePeriodoGiornaliero;
	}


	@Override
	public String toString() {
		return "ConfigurazionePeriodoGiornaliero [chiaveEnte=" + chiaveEnte
				+ ", codiceSocieta="
				+ codiceSocieta + ", cuteCute=" + cuteCute 
				+ ", codicePeriodoGiornaliero=" + codicePeriodoGiornaliero  
				+ ", descrizionePeriodoGiornaliero=" + descrizionePeriodoGiornaliero
				+ "]";
	}

	public String getCodiceKeyPeriodoGiornaliero() {
		return codiceKeyPeriodoGiornaliero;
	}
	
	public void setCodiceKeyPeriodoGiornaliero(String codiceKeyPeriodoGiornaliero) {
		this.codiceKeyPeriodoGiornaliero = codiceKeyPeriodoGiornaliero;
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

	public String getCodicePeriodoGiornaliero() {
		return codicePeriodoGiornaliero;
	}

	public void setCodicePeriodoGiornaliero(String codicePeriodoGiornaliero) {
		this.codicePeriodoGiornaliero = codicePeriodoGiornaliero;
	}

	public String getDescrizionePeriodoGiornaliero() {
		return descrizionePeriodoGiornaliero;
	}

	public void setDescrizionePeriodoGiornaliero(String descrizionePeriodoGiornaliero) {
		this.descrizionePeriodoGiornaliero = descrizionePeriodoGiornaliero;
	}

}
