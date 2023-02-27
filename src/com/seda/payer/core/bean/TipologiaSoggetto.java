package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class TipologiaSoggetto extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chiaveTipologiaSoggetto;
	private String codiceBelfiore;
	private String codiceTipologiaSoggetti;
	private String descrizioneSoggetto;
	private String flagEsenzione;
	private Calendar dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	private String descrizioneAggiuntiva;
	private Integer progressivoOrdinamento;

	public TipologiaSoggetto() {}
	
	public TipologiaSoggetto(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	setChiaveTipologiaSoggetto(data.getString("SSG_KSSGKSSG"));
    	setCodiceBelfiore(data.getString("SSG_CANEBELF"));
    	setCodiceTipologiaSoggetti(data.getString("SSG_CSSGCSSG"));
    	setDescrizioneSoggetto(data.getString("SSG_DSSGDSSG"));
    	setFlagEsenzione(data.getString("SSG_FSSGFESE"));
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(data.getTimestamp("SSG_GSSGGAGG").getTime());
    	setDataUltimoAggiornamento(cal);
    	setOperatoreUltimoAggiornamento(data.getString("SSG_CSSGCOPE"));
    	//PG180050_001 GG - inizio
    	setDescrizioneAggiuntiva(data.getString("SSG_DSSGDESC"));
    	setProgressivoOrdinamento(data.getInt("SSG_PSSGPORD"));
    	//PG180050_001 GG - fine
    }

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getChiaveTipologiaSoggetto());
		arg.setString(2, this.getCodiceBelfiore());
		arg.setString(3, this.getCodiceTipologiaSoggetti());	
		arg.setString(4, this.getDescrizioneSoggetto());
		arg.setString(5, this.getFlagEsenzione());
		arg.setString(6, this.getOperatoreUltimoAggiornamento());  // (OPERATORE ULTIMO AGGIORNAMENTO)
		//PG180050_001 GG - inizio
		arg.setString(7, this.getDescrizioneAggiuntiva());
		arg.setInt(8, this.getProgressivoOrdinamento());
		//PG180050_001 GG - fine
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
	}

	public String getChiaveTipologiaSoggetto() {
		return chiaveTipologiaSoggetto;
	}

	public void setChiaveTipologiaSoggetto(String chiaveTipologiaSoggetto) {
		this.chiaveTipologiaSoggetto = chiaveTipologiaSoggetto;
	}

	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}

	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}

	public String getCodiceTipologiaSoggetti() {
		return codiceTipologiaSoggetti;
	}

	public void setCodiceTipologiaSoggetti(String codiceTipologiaSoggetti) {
		this.codiceTipologiaSoggetti = codiceTipologiaSoggetti;
	}

	public String getDescrizioneSoggetto() {
		return descrizioneSoggetto;
	}

	public void setDescrizioneSoggetto(String descrizioneSoggetto) {
		this.descrizioneSoggetto = descrizioneSoggetto;
	}

	public String getFlagEsenzione() {
		return flagEsenzione;
	}

	public void setFlagEsenzione(String flagEsenzione) {
		this.flagEsenzione = flagEsenzione;
	}

	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}

	public String getDescrizioneAggiuntiva() {
		return descrizioneAggiuntiva;
	}

	public void setDescrizioneAggiuntiva(String descrizioneAggiuntiva) {
		this.descrizioneAggiuntiva = descrizioneAggiuntiva;
	}

	public Integer getProgressivoOrdinamento() {
		return progressivoOrdinamento;
	}

	public void setProgressivoOrdinamento(Integer progressivoOrdinamento) {
		this.progressivoOrdinamento = progressivoOrdinamento;
	}
	
	
}
