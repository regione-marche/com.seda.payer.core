package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import com.seda.payer.commons.bean.Lifecycle;
import com.seda.payer.commons.transform.TransformersIf;

public class TransazioneAtm extends Lifecycle implements Serializable, TransformersIf {

	private static final long serialVersionUID = 1L;
	private java.lang.String chiaveTransazioneInterna;
	private java.lang.String chiaveTransazione;
	private java.lang.String flagOffline;
	private java.lang.String flagAcconto;
	private java.lang.String idRicevuta;
	private java.util.Date dataRicevuta;
	private java.lang.String sistema;
	private java.lang.String metodo;
	private java.util.Date dataOrdine;
	private java.lang.String idTransazione;
	private java.util.Date dataTransazione;
	private java.util.Date dataUltimoAggiornamento;
	private java.lang.String operatoreUltimoAggiornamento;
	private java.lang.String idOrdine;		//PG130130 GG
	private java.lang.String idAutBanca;	//PG130130 GG

	public TransazioneAtm() { }

	public TransazioneAtm(java.lang.String chiaveTransazioneInterna, java.lang.String chiaveTransazione,
			java.lang.String flagOffline, java.lang.String flagAcconto, java.lang.String idRicevuta,
			java.util.Date dataRicevuta, java.lang.String sistema, java.lang.String metodo,
			java.util.Date dataOrdine, java.lang.String idTransazione, java.util.Date dataTransazione,
			java.util.Date dataUltimoAggiornamento, java.lang.String operatoreUltimoAggiornamento,
			java.lang.String idOrdine, java.lang.String idAutBanca	//PG130130 GG	
		) {
		super();
		this.chiaveTransazioneInterna = chiaveTransazioneInterna;
		this.chiaveTransazione = chiaveTransazione;
		this.flagOffline = flagOffline;
		this.flagAcconto = flagAcconto;
		this.idRicevuta = idRicevuta;
		this.dataRicevuta = dataRicevuta;
		this.sistema = sistema;
		this.metodo = metodo;
		this.dataOrdine = dataOrdine;
		this.idTransazione = idTransazione;
		this.dataTransazione = dataTransazione;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
		this.idOrdine = idOrdine;
		this.idAutBanca = idAutBanca;
	}

	public java.lang.String getChiaveTransazioneInterna() {
		return chiaveTransazioneInterna;
	}

	public void setChiaveTransazioneInterna(
			java.lang.String chiaveTransazioneInterna) {
		this.chiaveTransazioneInterna = chiaveTransazioneInterna;
	}

	public java.lang.String getChiaveTransazione() {
		return chiaveTransazione;
	}

	public void setChiaveTransazione(java.lang.String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public java.lang.String getFlagOffline() {
		return flagOffline;
	}

	public void setFlagOffline(java.lang.String flagOffline) {
		this.flagOffline = flagOffline;
	}

	public java.lang.String getFlagAcconto() {
		return flagAcconto;
	}

	public void setFlagAcconto(java.lang.String flagAcconto) {
		this.flagAcconto = flagAcconto;
	}

	public java.lang.String getIdRicevuta() {
		return idRicevuta;
	}

	public void setIdRicevuta(java.lang.String idRicevuta) {
		this.idRicevuta = idRicevuta;
	}

	public java.util.Date getDataRicevuta() {
		return dataRicevuta;
	}

	public void setDataRicevuta(java.util.Date dataRicevuta) {
		this.dataRicevuta = dataRicevuta;
	}

	public java.lang.String getSistema() {
		return sistema;
	}

	public void setSistema(java.lang.String sistema) {
		this.sistema = sistema;
	}

	public java.lang.String getMetodo() {
		return metodo;
	}

	public void setMetodo(java.lang.String metodo) {
		this.metodo = metodo;
	}

	public java.util.Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(java.util.Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public java.lang.String getIdTransazione() {
		return idTransazione;
	}

	public void setIdTransazione(java.lang.String idTransazione) {
		this.idTransazione = idTransazione;
	}

	public java.util.Date getDataTransazione() {
		return dataTransazione;
	}

	public void setDataTransazione(java.util.Date dataTransazione) {
		this.dataTransazione = dataTransazione;
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

	public java.lang.String getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(java.lang.String idOrdine) {
		this.idOrdine = idOrdine;
	}

	public java.lang.String getIdAutBanca() {
		return idAutBanca;
	}

	public void setIdAutBanca(java.lang.String idAutBanca) {
		this.idAutBanca = idAutBanca;
	}

	/* (non-Javadoc)
	 * @see com.seda.payer.commons.transform.TransformersIf#beanToBean(java.lang.Object)
	 */
	public Serializable beanToBean(Object arg0) throws Exception{		 
		return this;
	} 

	public static TransazioneAtm getBean(ResultSet data) throws SQLException {
		TransazioneAtm bean = new TransazioneAtm(); {
			bean.chiaveTransazioneInterna = data.getString("ATM_KATMKATM");
			bean.chiaveTransazione = data.getString("ATM_KTRAKTRA");
			bean.flagOffline = data.getString("ATM_FATMFOFF");
			bean.flagAcconto = data.getString("ATM_FATMFACC");
			bean.idRicevuta = data.getString("ATM_CATMCRIC");
//			bean.dataPagamento = data.getString("TFR_KTFRKTFR");
			bean.dataRicevuta = data.getTimestamp("ATM_GATMGRIC");
			bean.sistema = data.getString("ATM_CATMCSIS");
			bean.metodo = data.getString("ATM_CATMCMET");
			bean.dataOrdine = data.getTimestamp("ATM_GATMGORD");
//			bean.dataAutBanca = data.getString("TFR_KTFRKTFR");
			bean.idTransazione = data.getString("ATM_CATMCTRA");
			bean.dataTransazione = data.getTimestamp("ATM_GATMGTRA");
			bean.dataUltimoAggiornamento = data.getTimestamp("ATM_GATMGAGG");
			bean.operatoreUltimoAggiornamento = data.getString("ATM_CATMCOPE");
			bean.idOrdine = data.getString("ATM_CATMCORD");		//PG130130 GG
			bean.idAutBanca = data.getString("ATM_CATMIDBA");	//PG130130 GG
		}
		return bean;
	}

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTransazioneInterna);
		arg.setString(2, this.chiaveTransazione);
		arg.setString(3, this.flagOffline);
		arg.setString(4, this.flagAcconto);
		arg.setString(5, this.idRicevuta);
		arg.setTimestamp(6, new java.sql.Timestamp(this.dataRicevuta.getTime()));
		arg.setString(7, this.sistema);
		arg.setString(8, this.metodo);
		arg.setTimestamp(9, new java.sql.Timestamp(this.dataOrdine.getTime()));
		arg.setString(10, this.idTransazione);
		arg.setTimestamp(11, new java.sql.Timestamp(this.dataTransazione.getTime()));
		arg.setString(12, this.operatoreUltimoAggiornamento);
		//PG130130 GG - inizio
		arg.setString(13, this.idOrdine);
		arg.setString(14, this.idAutBanca);
		//arg.registerOutParameter(13, Types.INTEGER);
		arg.registerOutParameter(15, Types.INTEGER);
		//PG130130 GG - fine
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {

	}
}