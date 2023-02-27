package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class TestataDiarioImpostaSoggiorno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String chiaveTestataCompilazione;
	private String chiaveAnagraficaStrutturaRicettiva;
	private String codiceSocieta;
	private String codiceUtente;
	private String chiaveEnte;
	private Date dataInizioCompilazione;
	private Date dataFineCompilazione;
	private String chiaveTariffaImpostaSoggiorno;
	private String flagChiusura;
	private String noteAggiuntive;
	private Calendar dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	
	public TestataDiarioImpostaSoggiorno() {}
	
	public TestataDiarioImpostaSoggiorno(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setChiaveTestataCompilazione(data.getString("SDT_KSDTKSDT"));
    	setChiaveAnagraficaStrutturaRicettiva(data.getString("SDT_KSANKSAN"));
    	setCodiceSocieta(data.getString("SDT_CSOCCSOC"));
    	setCodiceUtente(data.getString("SDT_CUTECUTE"));
    	setChiaveEnte(data.getString("SDT_KANEKENT"));
    	setDataInizioCompilazione(data.getDate("SDT_GSDTGINI"));
    	setDataFineCompilazione(data.getDate("SDT_GSDTGFIN"));
    	setChiaveTariffaImpostaSoggiorno(data.getString("SDT_KSTFKSTF"));
    	setFlagChiusura(data.getString("SDT_FSDTCHIU"));
    	setNoteAggiuntive(data.getString("SDT_DSDTNOTE"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTimestamp("SDT_GSDTGAGG").getTime());
        setDataUltimoAggiornamento(cal);
        setOperatoreUltimoAggiornamento(data.getString("SDT_CSDTCOPE"));
    }
	
	public String getChiaveTestataCompilazione() {
		return chiaveTestataCompilazione;
	}
	public void setChiaveTestataCompilazione(String chiaveTestataCompilazione) {
		this.chiaveTestataCompilazione = chiaveTestataCompilazione;
	}
	public String getChiaveAnagraficaStrutturaRicettiva() {
		return chiaveAnagraficaStrutturaRicettiva;
	}
	public void setChiaveAnagraficaStrutturaRicettiva(
			String chiaveAnagraficaStrutturaRicettiva) {
		this.chiaveAnagraficaStrutturaRicettiva = chiaveAnagraficaStrutturaRicettiva;
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
	public Date getDataInizioCompilazione() {
		return dataInizioCompilazione;
	}
	public void setDataInizioCompilazione(Date dataInizioCompilazione) {
		this.dataInizioCompilazione = dataInizioCompilazione;
	}
	public Date getDataFineCompilazione() {
		return dataFineCompilazione;
	}
	public void setDataFineCompilazione(Date dataFineCompilazione) {
		this.dataFineCompilazione = dataFineCompilazione;
	}
	public String getChiaveTariffaImpostaSoggiorno() {
		return chiaveTariffaImpostaSoggiorno;
	}
	public void setChiaveTariffaImpostaSoggiorno(
			String chiaveTariffaImpostaSoggiorno) {
		this.chiaveTariffaImpostaSoggiorno = chiaveTariffaImpostaSoggiorno;
	}
	public void setFlagChiusura(String flagChiusura) {
		this.flagChiusura = flagChiusura;
	}
	public String getFlagChiusura() {
		return flagChiusura;
	}
	public String getNoteAggiuntive() {
		return noteAggiuntive;
	}
	public void setNoteAggiuntive(String noteAggiuntive) {
		this.noteAggiuntive = noteAggiuntive;
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
}
