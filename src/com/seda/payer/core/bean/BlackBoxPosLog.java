package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.seda.payer.commons.bean.Lifecycle;

public class BlackBoxPosLog implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private int idLog;
	private String codiceIdentificativoDominio;
	private String codiceEnte;
	private String numeroAvviso;
	private Calendar dataInserimento;
	private String operazioneEseguita;
	
	public BlackBoxPosLog() {}
	
	public BlackBoxPosLog(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	setIdLog(data.getInt("DOL_CDOLCDID"));
    	setCodiceIdentificativoDominio(data.getString("DOL_CDOCCIDD"));
    	setCodiceEnte(data.getString("DOL_CDOCCENT"));
    	setNumeroAvviso(data.getString("DOL_CDOCCIUV"));
    	setDataInserimento(data.getDate("DOL_CDOLDINS")!=null?sqlDateToCalendar(data.getDate("DOL_CDOLDINS").toString()):null);
    	setOperazioneEseguita(data.getString("DOL_CDOLCMSG"));
    }
	
	
	public BlackBoxPosLog(
			int idLog,
			String codiceIdentificativoDominio,
			String codiceEnte,
			String numeroAvviso,
			Calendar dataInserimento,
			String operazioneEseguita
			) {
		super();
		this.idLog=idLog;
		this.codiceIdentificativoDominio=codiceIdentificativoDominio;
		this.codiceEnte=codiceEnte;
		this.numeroAvviso=numeroAvviso;
		this.dataInserimento=dataInserimento;
		this.operazioneEseguita=operazioneEseguita;
	}

	public int getIdLog() {
		return idLog;
	}
	public void setIdLog(int idLog) {
		this.idLog=idLog;
	}
	public void setCodiceIdentificativoDominio(String codiceIdentificativoDominio) {
		this.codiceIdentificativoDominio = codiceIdentificativoDominio;
	}
	public String getCodiceIdentificativoDominio() {
		return codiceIdentificativoDominio;
	}
	public String getCodiceEnte() {
		return codiceEnte;
	}
	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}
	public String getNumeroAvviso() {
		return numeroAvviso;
	}
	public void setNumeroAvviso(String numeroAvviso) {
		this.numeroAvviso = numeroAvviso;
	}
	public Calendar getDataInserimento() {
		return dataInserimento;
	}
	public void setDataInserimento(Calendar dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public String getOperazioneEseguita() {
		return operazioneEseguita;
	}
	public void setOperazioneEseguita(String operazioneEseguita) {
		this.operazioneEseguita = operazioneEseguita;
	}
		
	private Calendar sqlDateToCalendar(String sqlDateToString) {
        Date date = Date.valueOf(sqlDateToString);
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
	}
	
}
