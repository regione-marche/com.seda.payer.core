package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigurazioneImpostaSoggiorno extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codiceBelfiore;
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
	private String codiceEnteGestionaleEntrate;
	private String impostaServizioGestionaleEntrate;
	private String codiceTributoGestionaleEntrate;
	private Calendar dataOraUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	private String annoDocumentoGestionaleEntrate;		//PG140470 GG 16062015
	/**Tabella PYANETB***/
	private String descrizioneEnte;

	public ConfigurazioneImpostaSoggiorno() {}
	
	public ConfigurazioneImpostaSoggiorno(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setCodiceBelfiore(data.getString("SRG_CANEBELF"));
    	setCodiceSocieta(data.getString("SRG_CSOCCSOC"));
    	setCodiceUtente(data.getString("SRG_CUTECUTE"));
    	setChiaveEnte(data.getString("SRG_KANEKENT"));
    	setCodiceEnteGestionaleEntrate(data.getString("SRG_CSRGCGES"));
    	setImpostaServizioGestionaleEntrate(data.getString("SRG_CSRGCISE"));
    	setCodiceTributoGestionaleEntrate(data.getString("SRG_CSRGTRIB"));
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(data.getTimestamp("SRG_GSRGGAGG").getTime());
    	setDataOraUltimoAggiornamento(cal);
    	setOperatoreUltimoAggiornamento(data.getString("SRG_CSRGCOPE"));
    	/**Tabella PYANETB***/
    	setDescrizioneEnte(data.getString("ANE_DANEDENT"));
    	setAnnoDocumentoGestionaleEntrate(data.getString("SRG_CSRGANNO"));
    }
	

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, codiceBelfiore);
		arg.setString(2, codiceSocieta);
		arg.setString(3, codiceUtente);
		arg.setString(4, chiaveEnte);
		arg.setString(5, codiceEnteGestionaleEntrate);
		arg.setString(6, impostaServizioGestionaleEntrate);
		arg.setString(7, codiceTributoGestionaleEntrate);
		arg.setString(8, operatoreUltimoAggiornamento);
		arg.setString(9, annoDocumentoGestionaleEntrate);
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, codiceBelfiore);
		arg.setString(2, codiceSocieta);
		arg.setString(3, codiceUtente);
		arg.setString(4, chiaveEnte);
		arg.setString(5, codiceEnteGestionaleEntrate);
		arg.setString(6, impostaServizioGestionaleEntrate);
		arg.setString(7, codiceTributoGestionaleEntrate);
		arg.setString(8, operatoreUltimoAggiornamento);
		arg.setString(9, annoDocumentoGestionaleEntrate);
	}
	
	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}

	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public String getCodiceEnteGestionaleEntrate() {
		return codiceEnteGestionaleEntrate;
	}

	public void setCodiceEnteGestionaleEntrate(String codiceEnteGestionaleEntrate) {
		this.codiceEnteGestionaleEntrate = codiceEnteGestionaleEntrate;
	}

	public String getImpostaServizioGestionaleEntrate() {
		return impostaServizioGestionaleEntrate;
	}

	public void setImpostaServizioGestionaleEntrate(
			String impostaServizioGestionaleEntrate) {
		this.impostaServizioGestionaleEntrate = impostaServizioGestionaleEntrate;
	}

	public String getCodiceTributoGestionaleEntrate() {
		return codiceTributoGestionaleEntrate;
	}

	public void setCodiceTributoGestionaleEntrate(
			String codiceTributoGestionaleEntrate) {
		this.codiceTributoGestionaleEntrate = codiceTributoGestionaleEntrate;
	}

	public Calendar getDataOraUltimoAggiornamento() {
		return dataOraUltimoAggiornamento;
	}

	public void setDataOraUltimoAggiornamento(Calendar dataOraUltimoAggiornamento) {
		this.dataOraUltimoAggiornamento = dataOraUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}
	
	//PG140470 GG 16062015 - inizio
	public String getAnnoDocumentoGestionaleEntrate() {
		return annoDocumentoGestionaleEntrate;
	}

	public void setAnnoDocumentoGestionaleEntrate(String annoDocumentoGestionaleEntrate) {
		this.annoDocumentoGestionaleEntrate = annoDocumentoGestionaleEntrate;
	}
	//PG140470 GG 16062015 - fine

	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	public void setDescrizioneEnte(String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

}
