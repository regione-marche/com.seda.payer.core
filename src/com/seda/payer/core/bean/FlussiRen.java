package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FlussiRen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private java.lang.String chiaveRendicontazione;

	private java.lang.String tipologiaFlusso;
	
	private java.lang.String codiceSocieta;
	
	private java.lang.String codiceUtente;
	
	private java.lang.String chiaveEnte;
	
	private java.util.Date dataCreazioneFlusso;
	
	private int progressivoFlusso;

	private java.lang.String codiceTiplogiaServizio;
	
	private java.lang.String codiceImpostaServizio;
	
	private java.lang.String chiaveGateway;
	
	private java.lang.String numeroContoCorrentePostale;
	
	private java.lang.String nomeFile;
	
	private java.lang.String flagInvioMail;

	private java.lang.String flagInvioFtp;

	//inizio LP PG21XX04 
	private java.lang.String flagInvioWS;
	//fine LP PG21XX04 
	
	private java.util.Date dataUltimoAggiornamento;
	
	private java.lang.String operatoreUltimoAggiornamento;
	
	private java.lang.String idPSP;
	
	private java.lang.String chiaveFlussoContabilita; //PG200150

	public FlussiRen() {
	}


	public FlussiRen(String chiaveRendicontazione, String tipologiaFlusso,
			String codiceSocieta, String codiceUtente, String chiaveEnte,
			Date dataCreazioneFlusso, int progressivoFlusso,
			String codiceTiplogiaServizio, String codiceImpostaServizio,
			String chiaveGateway, String numeroContoCorrentePostale,
			String nomeFile, String flagInvioMail, String flagInvioFtp,
			Date dataUltimoAggiornamento, String operatoreUltimoAggiornamento, String idPSP) {
		this.chiaveRendicontazione = chiaveRendicontazione;
		this.tipologiaFlusso = tipologiaFlusso;
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnte = chiaveEnte;
		this.dataCreazioneFlusso = dataCreazioneFlusso;
		this.progressivoFlusso = progressivoFlusso;
		this.codiceTiplogiaServizio = codiceTiplogiaServizio;
		this.codiceImpostaServizio = codiceImpostaServizio;
		this.chiaveGateway = chiaveGateway;
		this.numeroContoCorrentePostale = numeroContoCorrentePostale;
		this.nomeFile = nomeFile;
		this.flagInvioMail = flagInvioMail;
		this.flagInvioFtp = flagInvioFtp;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
		//inizio LP PG200060
		//this.idPSP = idPSP;
		this.idPSP = (idPSP != null ? idPSP : "");
		//fine LP PG200060
	}
	public java.lang.String getChiaveRendicontazione() {
		return chiaveRendicontazione;
	}

	public void setChiaveRendicontazione(java.lang.String chiaveRendicontazione) {
		this.chiaveRendicontazione = chiaveRendicontazione;
	}

	public java.lang.String getTipologiaFlusso() {
		return tipologiaFlusso;
	}

	public void setTipologiaFlusso(java.lang.String tipologiaFlusso) {
		this.tipologiaFlusso = tipologiaFlusso;
	}

	public java.lang.String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(java.lang.String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public java.lang.String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(java.lang.String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public java.lang.String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(java.lang.String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public java.util.Date getDataCreazioneFlusso() {
		return dataCreazioneFlusso;
	}

	public void setDataCreazioneFlusso(java.util.Date dataCreazioneFlusso) {
		this.dataCreazioneFlusso = dataCreazioneFlusso;
	}

	public int getProgressivoFlusso() {
		return progressivoFlusso;
	}

	public void setProgressivoFlusso(int progressivoFlusso) {
		this.progressivoFlusso = progressivoFlusso;
	}

	public java.lang.String getCodiceTiplogiaServizio() {
		return codiceTiplogiaServizio;
	}

	public void setCodiceTiplogiaServizio(java.lang.String codiceTiplogiaServizio) {
		this.codiceTiplogiaServizio = codiceTiplogiaServizio;
	}

	public java.lang.String getCodiceImpostaServizio() {
		return codiceImpostaServizio;
	}

	public void setCodiceImpostaServizio(java.lang.String codiceImpostaServizio) {
		this.codiceImpostaServizio = codiceImpostaServizio;
	}

	public java.lang.String getChiaveGateway() {
		return chiaveGateway;
	}

	public void setChiaveGateway(java.lang.String chiaveGateway) {
		this.chiaveGateway = chiaveGateway;
	}

	public java.lang.String getNumeroContoCorrentePostale() {
		return numeroContoCorrentePostale;
	}

	public void setNumeroContoCorrentePostale(
			java.lang.String numeroContoCorrentePostale) {
		this.numeroContoCorrentePostale = numeroContoCorrentePostale;
	}

	public java.lang.String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(java.lang.String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public java.lang.String getFlagInvioMail() {
		return flagInvioMail;
	}

	public void setFlagInvioMail(java.lang.String flagInvioMail) {
		this.flagInvioMail = flagInvioMail;
	}

	public java.lang.String getFlagInvioFtp() {
		return flagInvioFtp;
	}

	public void setFlagInvioFtp(java.lang.String flagInvioFtp) {
		this.flagInvioFtp = flagInvioFtp;
	}

	public java.util.Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(java.util.Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public java.lang.String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(
			java.lang.String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}
	
	public java.lang.String getIdPSP() {
		return idPSP;
	}

	public void setIdPSP(java.lang.String idPSP) {
		this.idPSP = idPSP;
	}
	
	
	
	//PG200150
	public java.lang.String getChiaveFlussoContabilita() {
		return chiaveFlussoContabilita;
	}


	public void setChiaveFlussoContabilita(java.lang.String chiaveFlussoContabilita) {
		this.chiaveFlussoContabilita = chiaveFlussoContabilita;
	}

	//inizio LP PG21XX04 
	public java.lang.String getFlagInvioWS() {
		return flagInvioWS;
	}


	public void setFlagInvioWS(java.lang.String flagInvioWS) {
		this.flagInvioWS = flagInvioWS;
	}
	//fine LP PG21XX04 

	public static FlussiRen getBean(ResultSet data) throws SQLException
	{
		FlussiRen bean = new FlussiRen();
		bean.chiaveRendicontazione = data.getString("REN_KRENKREN");
		bean.tipologiaFlusso = data.getString("REN_TBOLTFLU");
		bean.codiceSocieta = data.getString("REN_CSOCCSOC");
		bean.codiceUtente = data.getString("REN_CUTECUTE");
		bean.chiaveEnte = data.getString("REN_KANEKENT");
		bean.dataCreazioneFlusso = data.getDate("REN_GRENCREA");
		bean.progressivoFlusso = data.getInt("REN_PRENPROG");
		bean.codiceTiplogiaServizio = data.getString("REN_CTSECTSE");
		bean.codiceImpostaServizio = data.getString("REN_CISECISE");
		bean.chiaveGateway = data.getString("REN_KGTWKGTW");
		bean.numeroContoCorrentePostale = data.getString("REN_NRENNCCP");
		bean.nomeFile = data.getString("REN_DRENFILE");
		bean.flagInvioMail = data.getString("REN_FRENMAIL");
		bean.flagInvioFtp = data.getString("REN_FRENIFTP");
		bean.dataUltimoAggiornamento = data.getDate("REN_GRENGAGG");
		bean.operatoreUltimoAggiornamento = data.getString("REN_CRENCOPE");
		bean.idPSP = data.getString("REN_CRENIDPSP");
		//inizio LP PG21XX04 
		bean.flagInvioWS = data.getString("REN_FRENIWS");
		//fine LP PG21XX04 
		return bean;
	}
	
	//PG200150
	public static FlussiRen  getBean_Extended(ResultSet data) throws SQLException {
		FlussiRen bean = getBean(data);
		bean.chiaveFlussoContabilita = data.getString("REN_KCNBKCNB");
		bean.dataCreazioneFlusso = data.getTimestamp("REN_GRENCREA");
		return bean;
	}
	
	

}
