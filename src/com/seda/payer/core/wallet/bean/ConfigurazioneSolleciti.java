package com.seda.payer.core.wallet.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ConfigurazioneSolleciti extends ModelAttributes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	// Tabella PYCSLTB
	private String codiceSocieta;        		//	"CSL_CSOCCSOC" VARCHAR(5)
	private String cuteCute;					//	"CSL_CUTECUTE" VARCHAR(5)
	private String chiaveEnte;					//	"CSL_KANEKENT" CHAR(10)
	private Calendar dataValidita;  			//	"CSL_GCSLGVAL" DATE
	private String smsTipoCortesiaSollecito;	//	"CSL_TCSLTIPO" CHAR(1)			sms_cortesia - sms_sollecito - sollecito_cartaceo	
	private BigDecimal importoResiduo;			//	"CSL_ICSLIRES" DECIMAL(10 , 2)  >0 se sms_cortesia ;  <0 se sms_sollecito o sollecito_cartaceo
	private Integer intervalloGiorniTraInviiSuccessivi;	//	"CSL_NCSLNGIO" INTEGER	solo con sms_cortesia - sms_sollecito
	private String tipoSchedulazione;			//	"CSL_TCSLTSCH" CHAR(1)			solo con sms_cortesia - sms_sollecito
	private Integer meseInizioInvioSMS;			//  "CSL_NCSLMPER_A"  INTEGER		solo con sms_cortesia - sms_sollecito
	private Integer meseFineInvioSMS;			//  "CSL_NCSLMPER_DA"  INTEGER		solo con sms_cortesia - sms_sollecito
	private String invioEmail;					//	"CSL_FCSLMAIL" CHAR(1)			solo con sms_cortesia - sms_sollecito
	private String codiceOnereSollecitoCartaceo;//	"CSL_CCSLCONE" CHAR(3)			solo sollecito_cartaceo
	private String descrOnereSollecitoCartaceo;	//	"CSL_DCSLDESC" VARCHAR(100)		solo sollecito_cartaceo
	private BigDecimal importoOnere;			//	"CSL_ICSLIONE" DECIMAL(15,2)	solo sollecito_cartaceo
	private Integer giorniRivestizioneAnagrafica;//	"CSL_NCSLNING" INTEGER			solo sollecito_cartaceo			
	private String contoCorrentePostale;		//	"CSL_CCSLCCCN" CHAR(12)			solo sollecito_cartaceo
	private String descrContoCorrentePostale;	//	"CSL_DCSLDCCN" VARCHAR(100)		solo sollecito_cartaceo
	private Integer intervalloGiorniInvioSollecito;		//	"CSL_NCSLNGSL" INTEGER	solo con sms_cortesia - sms_sollecito
	private String operatore;
	private String flagAttivazione;				// "CSL_CCSLCATT" char(1) defalut Y
	private String tribXEvoluzione;				// "CSL_CCSLTRIB" char(4) 
	
	public ConfigurazioneSolleciti(){		
	}
	
	public ConfigurazioneSolleciti(
			 String codiceSocieta,        		
			 String cuteCute,					
			 String chiaveEnte,					
			 Calendar dataValidita,	
			 String smsTipoCortesiaSollecito,	
			 BigDecimal importoResiduo,			
			 Integer intervalloGiorniTraInviiSuccessivi,	
			 String tipoSchedulazione,
			 Integer meseInizioInvioSMS,
			 Integer meseFineInvioSMS,
			 String invioEmail,
			 String codiceOnereSollecitoCartaceo,
			 String descrOnereSollecitoCartaceo,
			 BigDecimal importoOnere,
			 Integer giorniRivestizioneAnagrafica,
			 String contoCorrentePostale,
			 String descrContoCorrentePostale,
			 String operatore,
			 Integer intervalloGiorniInvioSollecito,
			 String flagAttivazione, 
			 String tribXEvoluzione) 
	{
		 this.codiceSocieta = codiceSocieta;         		
		 this.cuteCute = cuteCute;					
		 this.chiaveEnte = chiaveEnte;			
		 this.dataValidita = dataValidita;
		 this.smsTipoCortesiaSollecito = smsTipoCortesiaSollecito;
		 this.importoResiduo = importoResiduo;	
		 this.intervalloGiorniTraInviiSuccessivi =intervalloGiorniTraInviiSuccessivi;	
		 this.tipoSchedulazione=tipoSchedulazione;
		 this.meseInizioInvioSMS = meseInizioInvioSMS;
		 this.meseFineInvioSMS = meseFineInvioSMS;
		 this.invioEmail = invioEmail;
		 this.codiceOnereSollecitoCartaceo = codiceOnereSollecitoCartaceo;
		 this.descrOnereSollecitoCartaceo = descrOnereSollecitoCartaceo;
		 this.importoOnere = importoOnere;
		 this.giorniRivestizioneAnagrafica = giorniRivestizioneAnagrafica;
		 this.contoCorrentePostale = contoCorrentePostale;
		 this.descrContoCorrentePostale = descrContoCorrentePostale;		
		 this.operatore = operatore;
		 this.intervalloGiorniInvioSollecito = intervalloGiorniInvioSollecito;
		 this.flagAttivazione = flagAttivazione;
		 this.tribXEvoluzione = tribXEvoluzione;
	}
			
	
	public String getFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(String flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public Integer getIntervalloGiorniInvioSollecito() {
		return intervalloGiorniInvioSollecito;
	}

	public void setIntervalloGiorniInvioSollecito(
			Integer intervalloGiorniInvioSollecito) {
		this.intervalloGiorniInvioSollecito = intervalloGiorniInvioSollecito;
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
	public String getSmsTipoCortesiaSollecito() {
		return smsTipoCortesiaSollecito;
	}
	public void setSmsTipoCortesiaSollecito(String smsTipoCortesiaSollecito) {
		this.smsTipoCortesiaSollecito = smsTipoCortesiaSollecito;
	}
	public BigDecimal getImportoResiduo() {
		return importoResiduo;
	}
	public void setImportoResiduo(BigDecimal importoResiduo) {
		this.importoResiduo = importoResiduo;
	}
	public Integer getIntervalloGiorniTraInviiSuccessivi() {
		return intervalloGiorniTraInviiSuccessivi;
	}
	public void setIntervalloGiorniTraInviiSuccessivi(
			Integer intervalloGiorniTraInviiSuccessivi) {
		this.intervalloGiorniTraInviiSuccessivi = intervalloGiorniTraInviiSuccessivi;
	}
	public String getTipoSchedulazione() {
		return tipoSchedulazione;
	}
	public void setTipoSchedulazione(String tipoSchedulazione) {
		this.tipoSchedulazione = tipoSchedulazione;
	}
	public String getInvioEmail() {
		return invioEmail;
	}
	public void setInvioEmail(String invioEmail) {
		this.invioEmail = invioEmail;
	}
	public String getCodiceOnereSollecitoCartaceo() {
		return codiceOnereSollecitoCartaceo;
	}
	public void setCodiceOnereSollecitoCartaceo(String codiceOnereSollecitoCartaceo) {
		this.codiceOnereSollecitoCartaceo = codiceOnereSollecitoCartaceo;
	}
	public String getDescrOnereSollecitoCartaceo() {
		return descrOnereSollecitoCartaceo;
	}
	public void setDescrOnereSollecitoCartaceo(String descrOnereSollecitoCartaceo) {
		this.descrOnereSollecitoCartaceo = descrOnereSollecitoCartaceo;
	}
	public BigDecimal getImportoOnere() {
		return importoOnere;
	}
	public void setImportoOnere(BigDecimal importoOnere) {
		this.importoOnere = importoOnere;
	}
	public Integer getGiorniRivestizioneAnagrafica() {
		return giorniRivestizioneAnagrafica;
	}
	public void setGiorniRivestizioneAnagrafica(Integer giorniRivestizioneAnagrafica) {
		this.giorniRivestizioneAnagrafica = giorniRivestizioneAnagrafica;
	}
	public String getContoCorrentePostale() {
		return contoCorrentePostale;
	}
	public void setContoCorrentePostale(String contoCorrentePostale) {
		this.contoCorrentePostale = contoCorrentePostale;
	}
	public String getDescrContoCorrentePostale() {
		return descrContoCorrentePostale;
	}
	public void setDescrContoCorrentePostale(String descrContoCorrentePostale) {
		this.descrContoCorrentePostale = descrContoCorrentePostale;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getMeseInizioInvioSMS() {
		return meseInizioInvioSMS;
	}

	public void setMeseInizioInvioSMS(Integer meseInizioInvioSMS) {
		this.meseInizioInvioSMS = meseInizioInvioSMS;
	}

	public Integer getMeseFineInvioSMS() {
		return meseFineInvioSMS;
	}

	public void setMeseFineInvioSMS(Integer meseFineInvioSMS) {
		this.meseFineInvioSMS = meseFineInvioSMS;
	}

	public String getTribXEvoluzione() {
		return tribXEvoluzione;
	}

	public void setTribXEvoluzione(String tribXEvoluzione) {
		this.tribXEvoluzione = tribXEvoluzione;
	}

	@Override
	public String toString() {
		return "ConfigurazioneSolleciti [chiaveEnte=" + chiaveEnte
				+ ", codiceOnereSollecitoCartaceo="
				+ codiceOnereSollecitoCartaceo + ", codiceSocieta="
				+ codiceSocieta + ", contoCorrentePostale="
				+ contoCorrentePostale + ", cuteCute=" + cuteCute
				+ ", dataValidita=" + dataValidita
				+ ", descrContoCorrentePostale=" + descrContoCorrentePostale
				+ ", descrOnereSollecitoCartaceo="
				+ descrOnereSollecitoCartaceo + ", flagAttivazione="
				+ flagAttivazione + ", giorniRivestizioneAnagrafica="
				+ giorniRivestizioneAnagrafica + ", importoOnere="
				+ importoOnere + ", importoResiduo=" + importoResiduo
				+ ", intervalloGiorniInvioSollecito="
				+ intervalloGiorniInvioSollecito
				+ ", intervalloGiorniTraInviiSuccessivi="
				+ intervalloGiorniTraInviiSuccessivi + ", invioEmail="
				+ invioEmail + ", meseFineInvioSMS=" + meseFineInvioSMS
				+ ", meseInizioInvioSMS=" + meseInizioInvioSMS + ", operatore="
				+ operatore + ", smsTipoCortesiaSollecito="
				+ smsTipoCortesiaSollecito + ", tipoSchedulazione="
				+ tipoSchedulazione + ", tribXEvoluzione=" + tribXEvoluzione
				+ "]";
	}
	
}
