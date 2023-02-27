package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazioneRaccordoPagonet extends ModelAttributes implements Serializable{

	// Tabella PYRCPTB
	 
	private String codiceSocieta;        		//	"RCP_CSOCCSOC" VARCHAR(5)
	private String cuteCute;					//	"RCP_CUTECUTE" VARCHAR(5)
	private String chiaveEnte;					//	"RCP_KANEKENT" CHAR(10)
	private String tipologiaServizio;			//	"RCP_CRCPTSER" CHAR(3)
	private String operatore;					//	"RCP_CRCPCOPE" VARCHAR(50)
	private String descrAutBollettini;					//	"RCP_DRCPDAUT" VARCHAR(256)
	private String descrAutBollettini_2;					//	"RCP_DRCPDAUT_2" VARCHAR(256)
	

	public ConfigurazioneRaccordoPagonet()
	{};
	
	public ConfigurazioneRaccordoPagonet(
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,					
			 String tipologiaServizio,	
			 String operatore,
			 String descrAutBollettini,
			 String descrAutBollettini_2
			 ) 
	{
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;			
		 this.tipologiaServizio = tipologiaServizio;
		 this.operatore = operatore;
		 this.descrAutBollettini = descrAutBollettini;
		 this.descrAutBollettini_2 =descrAutBollettini_2; 
	}

	

	
	@Override
	public String toString() {
		return "ConfigurazioneRaccordoPagonet [chiaveEnte=" + chiaveEnte
				+ ", codiceSocieta=" + codiceSocieta + ", cuteCute=" + cuteCute
				+ ", descrAutBollettini=" + descrAutBollettini
				+ ", descrAutBollettini_2=" + descrAutBollettini_2
				+ ", operatore=" + operatore + ", tipologiaServizio="
				+ tipologiaServizio + "]";
	}

	public String getDescrAutBollettini_2() {
		return descrAutBollettini_2;
	}

	public void setDescrAutBollettini_2(String descrAutBollettini_2) {
		this.descrAutBollettini_2 = descrAutBollettini_2;
	}

	public String getDescrAutBollettini() {
		return descrAutBollettini;
	}

	public void setDescrAutBollettini(String descrAutBollettini) {
		this.descrAutBollettini = descrAutBollettini;
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

	public String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}
}
