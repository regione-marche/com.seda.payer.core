package com.seda.payer.core.mercato.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazioneTipologiaBanco extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Tabella PYTPBTB
	private String codiceKeyTipologiaBanco;		//  "TPB_KTPBKTPB" VARCHAR(10)
	private String codiceSocieta;        		//	"TPB_CSOCCSOC" CHAR(5)
	private String cuteCute;					//	"TPB_CUTECUTE" CHAR(5)
	private String chiaveEnte;					//	"TPB_KANEKENT" CHAR(10)
	private String codiceTipologiaBanco;  		//	"TPB_CTPBCTIP" VARCHAR(10)
	private String descrizioneTipologiaBanco;  	//	"TPB_CTPBDSTB" VARCHAR(70)
	

	public ConfigurazioneTipologiaBanco(){
	}

	public ConfigurazioneTipologiaBanco(
			 String codiceKeyTipologiaBanco,
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,						
			 String codiceTipologiaBanco,
			 String descrizioneTipologiaBanco)
	{
		 this.codiceKeyTipologiaBanco = codiceKeyTipologiaBanco;
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;			
		 this.codiceTipologiaBanco = codiceTipologiaBanco;
		 this.descrizioneTipologiaBanco = descrizioneTipologiaBanco;
	}


	@Override
	public String toString() {
		return "ConfigurazioneTipologiaBanco [chiaveEnte=" + chiaveEnte
				+ ", codiceSocieta="
				+ codiceSocieta + ", cuteCute=" + cuteCute 
				+ ", codiceTipologiaBanco=" + codiceTipologiaBanco  
				+ ", descrizioneTipologiaBanco=" + descrizioneTipologiaBanco
				+ "]";
	}

	public String getCodiceKeyTipologiaBanco(){
		return codiceKeyTipologiaBanco;
	}
	
	public void setCodiceKeyTipologiaBanco(String codiceKeyTipologiaBanco){
		this.codiceKeyTipologiaBanco = codiceKeyTipologiaBanco;
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

	public String getCodiceTipologiaBanco() {
		return codiceTipologiaBanco;
	}

	public void setCodiceTipologiaBanco(String codiceTipologiaBanco) {
		this.codiceTipologiaBanco = codiceTipologiaBanco;
	}

	public String getDescrizioneTipologiaBanco() {
		return descrizioneTipologiaBanco;
	}

	public void setDescrizioneTipologiaBanco(String descrizioneTipologiaBanco) {
		this.descrizioneTipologiaBanco = descrizioneTipologiaBanco;
	}

}
