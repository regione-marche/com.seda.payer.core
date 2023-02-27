package com.seda.payer.core.mercato.bean;

import java.io.Serializable;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazionePiazzola extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Tabella PYPZLTB
	private String codiceKeyPiazzola;			//  "PZL_KPZLKPZL" VARCHAR(64)
	private String codiceSocieta;        		//	"PZL_CSOCCSOC" CHAR(5)
	private String cuteCute;					//	"PZL_CUTECUTE" CHAR(5)
	private String chiaveEnte;					//	"PZL_KANEKENT" CHAR(10)
	private String codiceKeyAreaMercantile;		//  "PZL_CPZLKMRC" VARCHAR(10)
	private String codicePiazzola;		  		//	"PZL_CPZLCTIP" VARCHAR(10)
	private String descrizionePiazzola;  		//	"PZL_CMRCDSAM" VARCHAR(70)
	private Double coordLatAng1;				//	"PZL_DPZLA1LT" DECIMAL(10,7),
	private Double coordLonAng1;				//  "PZL_DPZLA1LG" DECIMAL(10,7),
	private Double coordLatAng2;				//	"PZL_DPZLA2LT" DECIMAL(10,7),
	private Double coordLonAng2;				//  "PZL_DPZLA2LG" DECIMAL(10,7),
	private Double coordLatAng3;				//	"PZL_DPZLA3LT" DECIMAL(10,7),
	private Double coordLonAng3;				//  "PZL_DPZLA3LG" DECIMAL(10,7),
	private Double coordLatAng4;				//	"PZL_DPZLA4LT" DECIMAL(10,7),
	private Double coordLonAng4;				//  "PZL_DPZLA4LG" DECIMAL(10,7),
	private Calendar dataInizioValidita;		//  "PZL_GMRCDTIN" TIMESTAMP
	private Calendar dataFineValidita;			//  "PZL_GMRCDTFN" TIMESTAMP
	private Calendar dataAInizioValidita;
	private Calendar dataAFineValidita;
	private String descrizioneAreaMerc;
	

	public ConfigurazionePiazzola(){
	}

	public ConfigurazionePiazzola(
			 String codiceKeyPiazzola,
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,			
			 String codiceKeyAreaMercantile,
			 String codicePiazzola,
			 String descrizionePiazzola,
			 Double coordLatAng1,
			 Double coordLonAng1,
			 Double coordLatAng2,
			 Double coordLonAng2,
			 Double coordLatAng3,
			 Double coordLonAng3,
			 Double coordLatAng4,
			 Double coordLonAng4,
			 Calendar dataInizioValidita,
			 Calendar dataFineValidita,
			 Calendar dataAInizioValidita,
			 Calendar dataAFineValidita,
			 String descrizioneAreaMerc)
	{
		 this.codiceKeyPiazzola = codiceKeyPiazzola;
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;
		 this.codiceKeyAreaMercantile = codiceKeyAreaMercantile;
		 this.codicePiazzola = codicePiazzola;
		 this.descrizionePiazzola = descrizionePiazzola;
		 this.coordLatAng1 = coordLatAng1;
		 this.coordLonAng1 = coordLonAng1;
		 this.coordLatAng2 = coordLatAng2;
		 this.coordLonAng2 = coordLonAng2;
		 this.coordLatAng3 = coordLatAng3;
		 this.coordLonAng3 = coordLonAng3;
		 this.coordLatAng4 = coordLatAng4;
		 this.coordLonAng4 = coordLonAng4;
		 this.dataInizioValidita = dataInizioValidita;
		 this.dataFineValidita = dataFineValidita;
		 this.dataAInizioValidita = dataAInizioValidita;
		 this.dataAFineValidita = dataAFineValidita;
		 this.descrizioneAreaMerc = descrizioneAreaMerc;
	}


	@Override
	public String toString() {
		return "ConfigurazionePiazzola [chiavePiazzola=" + codiceKeyPiazzola 
		        + "chiaveEnte=" + chiaveEnte
				+ ", codiceSocieta=" + codiceSocieta 
				+ ", cuteCute=" + cuteCute 
				+ ", codiceKeyAreaMercantile=" + codiceKeyAreaMercantile
				+ ", codicePiazzola=" + codicePiazzola  
				+ ", descrizionePiazzola=" + descrizionePiazzola
				+ ", coordLatAng1=" + coordLatAng1
				+ ", coordLonAng1=" + coordLonAng1
				+ ", coordLatAng2=" + coordLatAng2
				+ ", coordLonAng2=" + coordLonAng2
				+ ", coordLatAng3=" + coordLatAng3
				+ ", coordLonAng3=" + coordLonAng3
				+ ", coordLatAng4=" + coordLatAng4
				+ ", coordLonAng4=" + coordLonAng4
				+ ", dataInizioValidita=" + dataInizioValidita
				+ ", dataFineValidita=" + dataFineValidita
				+ "]";
	}

	public String getCodiceKeyPiazzola() {
		return codiceKeyPiazzola;
	}

	public void setCodiceKeyPiazzola(String codiceKeyPiazzola) {
		this.codiceKeyPiazzola = codiceKeyPiazzola;
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

	public String getCodicePiazzola() {
		return codicePiazzola;
	}

	public void setCodicePiazzola(String codicePiazzola) {
		this.codicePiazzola = codicePiazzola;
	}
	public String getCodiceKeyAreaMercantile() {
		return codiceKeyAreaMercantile;
	}
	
	public void setCodiceKeyAreaMercantile(String codiceKeyAreaMercantile) {
		this.codiceKeyAreaMercantile = codiceKeyAreaMercantile;
	}

	public String getDescrizionePiazzola() {
		return descrizionePiazzola;
	}

	public void setDescrizionePiazzola(String descrizionePiazzola) {
		this.descrizionePiazzola = descrizionePiazzola;
	}
	
	public Double getCoordLatAng1(){
		return coordLatAng1;
	}
	
	public void setCoordLatAng1(Double coordLatAng1) {
		this.coordLatAng1 = coordLatAng1;
	}
	
	public Double getCoordLonAng1(){
		return coordLonAng1;
	}
	
	public void setCoordLonAng1(Double coordLonAng1) {
		this.coordLonAng1 = coordLonAng1;
	}	
	
	public Double getCoordLatAng2(){
		return coordLatAng2;
	}
	
	public void setCoordLatAng2(Double coordLatAng2) {
		this.coordLatAng2 = coordLatAng2;
	}
	
	public Double getCoordLonAng2(){
		return coordLonAng2;
	}
	
	public void setCoordLonAng2(Double coordLonAng2) {
		this.coordLonAng2 = coordLonAng2;
	}	

	public Double getCoordLatAng3(){
		return coordLatAng3;
	}
	
	public void setCoordLatAng3(Double coordLatAng3) {
		this.coordLatAng3 = coordLatAng3;
	}
	
	public Double getCoordLonAng3(){
		return coordLonAng3;
	}
	
	public void setCoordLonAng3(Double coordLonAng3) {
		this.coordLonAng3 = coordLonAng3;
	}	

	public Double getCoordLatAng4(){
		return coordLatAng4;
	}
	
	public void setCoordLatAng4(Double coordLatAng4) {
		this.coordLatAng4 = coordLatAng4;
	}
	
	public Double getCoordLonAng4(){
		return coordLonAng4;
	}
	
	public void setCoordLonAng4(Double coordLonAng4) {
		this.coordLonAng4 = coordLonAng4;
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
	
	public String getDescrizioneAreaMerc() {
		return this.descrizioneAreaMerc;
	}
	
	public void setDescrizioneAreaMerc(String descrizioneAreaMerc) {
		this.descrizioneAreaMerc = descrizioneAreaMerc;
	}
	
}
