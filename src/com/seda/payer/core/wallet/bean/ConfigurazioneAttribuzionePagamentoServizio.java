package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazioneAttribuzionePagamentoServizio extends ModelAttributes implements Serializable{
	// Tabella PYPASTB
	private String codiceSocieta;        		//	"PAS_CSOCCSOC" VARCHAR(5)
	private String cuteCute;					//	"PAS_CUTECUTE" VARCHAR(5)
	private String chiaveEnte;					//	"PAS_KANEKENT" CHAR(10)
	private String codiceServizio;  			//	"PAS_CSRVCODI" CHAR(2)
	private String codiceTributo;				//	"PAS_CTRBCODI" CHAR(1)
	private String codiceTributo2;				//	"PAS_CTRBCOD2" CHAR(1)
	private String codiceTributo3;				//	"PAS_CTRBCOD3" CHAR(1)
	private String descrizioneTributo;
	private Integer priorita;					//	"PAS_CPASCPRI" INTEGER
	private String operatore;

	public ConfigurazioneAttribuzionePagamentoServizio(){
	}

	public ConfigurazioneAttribuzionePagamentoServizio(
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,					
			 String codiceServizio,
			 String codiceTributo,
			 String descrizioneTributo,
			 Integer priorita,
			 String operatore,
			 String codiceTributo2,
			 String codiceTributo3
			 ) 
	{
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;			
		 this.codiceServizio = codiceServizio;
		 this.codiceTributo = codiceTributo;
		 this.descrizioneTributo = descrizioneTributo;
		 this.priorita = priorita;	
		 this.operatore = operatore;
		 this.codiceTributo2 = codiceTributo2;
		 this.codiceTributo3 = codiceTributo3;
	}

	
	

	@Override
	public String toString() {
		return "ConfigurazioneAttribuzionePagamentoServizio [chiaveEnte="
				+ chiaveEnte + ", codiceServizio=" + codiceServizio
				+ ", codiceSocieta=" + codiceSocieta + ", codiceTributo="
				+ codiceTributo + ", codiceTributo2=" + codiceTributo2
				+ ", codiceTributo3=" + codiceTributo3 + ", cuteCute="
				+ cuteCute + ", descrizioneTributo=" + descrizioneTributo
				+ ", operatore=" + operatore + ", priorita=" + priorita + "]";
	}

	public String getCodiceTributo2() {
		return codiceTributo2;
	}

	public void setCodiceTributo2(String codiceTributo2) {
		this.codiceTributo2 = codiceTributo2;
	}

	public String getCodiceTributo3() {
		return codiceTributo3;
	}

	public void setCodiceTributo3(String codiceTributo3) {
		this.codiceTributo3 = codiceTributo3;
	}

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
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

	public String getCodiceServizio() {
		return codiceServizio;
	}

	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}

	public Integer getPriorita() {
		return priorita;
	}

	public void setPriorita(Integer priorita) {
		this.priorita = priorita;
	}

	public String getCodiceTributo() {
		return codiceTributo;
	}

	public void setCodiceTributo(String codiceTributo) {
		this.codiceTributo = codiceTributo;
	}

	public String getDescrizioneTributo() {
		return descrizioneTributo;
	}

	public void setDescrizioneTributo(String descrizioneTributo) {
		this.descrizioneTributo = descrizioneTributo;
	}


}
