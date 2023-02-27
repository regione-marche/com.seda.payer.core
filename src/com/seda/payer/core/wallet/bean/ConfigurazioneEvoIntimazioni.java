package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazioneEvoIntimazioni extends ModelAttributes implements Serializable{

	private static final long serialVersionUID = 1L;
	// Tabella PYSLITB
	private String codiceSocieta;        		//	"SLI_CSOCCSOC" VARCHAR(5)
	private String cuteCute;					//	"SLI_CUTECUTE" VARCHAR(5)
	private String chiaveEnte;					//	"SLI_KANEKENT" CHAR(10)
	private Calendar dataValidita;  			//	"SLI_GSLIGVAL" DATE
	private BigDecimal importoResiduo;			//	"SLI_ISLIIRES" DECIMAL(15 , 2)
	private String smsSollecito;				//	"SLI_FSLIFSMS" CHAR(1)
	private String operatore;				    //	"SLI_CSLICOPE" VARCHAR(50)
	private String flagSollecitoCartaceo;		//	"SLI_FSLIFSLC" CHAR(1)
	private Integer intervalloGiorniEvoluzione;	//	"SLI_NSLINGEV" INTEGER
	private String flagAttivazione; 		    //	"SLI_CSLICATT CHAR(1)
	private String codiceEnteEvoluzione; 		//	"SLI_CSLICATT CHAR(1)
	private String impostaServizioEvoluzione; 	//	"SLI_CSLIISRV VARCHAR(2)
	private String tipoServizio;          		//	"SLI_CSRVCODI CHAR(2)
	
	public ConfigurazioneEvoIntimazioni()
	{};
	
	public ConfigurazioneEvoIntimazioni(
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,					
			 Calendar dataValidita,	
			 String smsSollecito,	
			 BigDecimal importoResiduo,
			 String operatore,
			 String flagSollecitoCartaceo,
			 Integer intervalloGiorniEvoluzione,
			 String flagAttivazione,
			 String codiceEnteEvoluzione,
			 String impostaServizioEvoluzione,
			 String tipoServizio
			 ) 
	{
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;			
		 this.dataValidita = dataValidita;
		 this.smsSollecito = smsSollecito;
		 this.importoResiduo = importoResiduo;	
		 this.operatore = operatore;
		 this.flagSollecitoCartaceo = flagSollecitoCartaceo;
		 this.intervalloGiorniEvoluzione = intervalloGiorniEvoluzione;
		 this.flagAttivazione = flagAttivazione;
		 this.codiceEnteEvoluzione = codiceEnteEvoluzione;
		 this.impostaServizioEvoluzione = impostaServizioEvoluzione;
		 this.tipoServizio = tipoServizio;
		 
	}

	
	

	public String getFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(String flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public String getCodiceEnteEvoluzione() {
		return codiceEnteEvoluzione;
	}

	public void setCodiceEnteEvoluzione(String codiceEnteEvoluzione) {
		this.codiceEnteEvoluzione = codiceEnteEvoluzione;
	}

	public String getImpostaServizioEvoluzione() {
		return impostaServizioEvoluzione;
	}

	public void setImpostaServizioEvoluzione(String impostaServizioEvoluzione) {
		this.impostaServizioEvoluzione = impostaServizioEvoluzione;
	}

	public String getFlagSollecitoCartaceo() {
		return flagSollecitoCartaceo;
	}

	public void setFlagSollecitoCartaceo(String flagSollecitoCartaceo) {
		this.flagSollecitoCartaceo = flagSollecitoCartaceo;
	}

	public Integer getIntervalloGiorniEvoluzione() {
		return intervalloGiorniEvoluzione;
	}

	public void setIntervalloGiorniEvoluzione(Integer intervalloGiorniEvoluzione) {
		this.intervalloGiorniEvoluzione = intervalloGiorniEvoluzione;
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

	public Calendar getDataValidita() {
		return dataValidita;
	}

	public void setDataValidita(Calendar dataValidita) {
		this.dataValidita = dataValidita;
	}

	public BigDecimal getImportoResiduo() {
		return importoResiduo;
	}

	public void setImportoResiduo(BigDecimal importoResiduo) {
		this.importoResiduo = importoResiduo;
	}

	public String getSmsSollecito() {
		return smsSollecito;
	}

	public void setSmsSollecito(String smsSollecito) {
		this.smsSollecito = smsSollecito;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	@Override
	public String toString() {
		return "ConfigurazioneEvoIntimazioni [chiaveEnte=" + chiaveEnte
				+ ", codiceEnteEvoluzione=" + codiceEnteEvoluzione
				+ ", codiceSocieta=" + codiceSocieta + ", cuteCute=" + cuteCute
				+ ", dataValidita=" + dataValidita + ", flagAttivazione="
				+ flagAttivazione + ", flagSollecitoCartaceo="
				+ flagSollecitoCartaceo + ", importoResiduo=" + importoResiduo
				+ ", impostaServizioEvoluzione=" + impostaServizioEvoluzione
				+ ", intervalloGiorniEvoluzione=" + intervalloGiorniEvoluzione
				+ ", operatore=" + operatore + ", smsSollecito=" + smsSollecito
				+ ", tipoServizio=" + tipoServizio + "]";
	}

	
	
}
