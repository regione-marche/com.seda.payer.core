package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazionePagamentoServizio extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Tabella PYPPSTB
	private String codiceSocieta;        		//	"PPS_CSOCCSOC" VARCHAR(5)
	private String cuteCute;					//	"PPS_CUTECUTE" VARCHAR(5)
	private String chiaveEnte;					//	"PPS_KANEKENT" CHAR(10)
	private String codiceServizio;  			//	"PPS_CSRVCODI" CHAR(2)
	private Integer priorita;					//	"PPS_CPPSCPRI" INTEGER
	private String enteServizio;  				//	"PPS_CENTNT" VARCHAR(6)
	private String impostaServizio;  				//	"PPS_CISVCISV" VARCHAR(6)
	
	private String operatore;

	public ConfigurazionePagamentoServizio(){
	}

	public ConfigurazionePagamentoServizio(
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,					
			 String codiceServizio,	
			 Integer priorita,
			 String operatore,
			 String enteServizio,
			 String impostaServizio) 
	{
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;			
		 this.codiceServizio = codiceServizio;
		 this.priorita = priorita;	
		 this.operatore = operatore;
		 this.enteServizio = enteServizio;
		 this.impostaServizio = impostaServizio;
	}


	@Override
	public String toString() {
		return "ConfigurazionePagamentoServizio [chiaveEnte=" + chiaveEnte
				+ ", codiceServizio=" + codiceServizio + ", codiceSocieta="
				+ codiceSocieta + ", cuteCute=" + cuteCute + ", operatore="
				+ operatore + ", priorita=" + priorita + "]";
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

	public String getEnteServizio() {
		return enteServizio;
	}

	public void setEnteServizio(String enteServizio) {
		this.enteServizio = enteServizio;
	}

	public String getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

}
