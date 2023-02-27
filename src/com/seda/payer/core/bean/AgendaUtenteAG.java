package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;
import java.util.Calendar;

public class AgendaUtenteAG extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private String chiaveUtente;
    private String codiceFiscale;
    private String numeroCell;
    private String mail;
    private String operatore;
    private Calendar dataUltimaSincro;
 
    public AgendaUtenteAG() { 
    }

    public AgendaUtenteAG(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	chiaveUtente = data.getString("AGU_KAGUKUSR");
    	codiceFiscale = data.getString("AGU_CAGUCFIS");
    	numeroCell = data.getString("AGU_NAGUNSMS");
    	mail = data.getString("AGU_CAGUMAIL");
    	operatore = data.getString("AGU_CAGUCOPE");
    	Date ladata = data.getDate("AGU_GAGUSINC");    	
        dataUltimaSincro = getCalendarFromDate(ladata); 
       }


	public void setChiaveUtente(String chiaveUtente) {
		this.chiaveUtente = chiaveUtente;
	}

	public String getChiaveUtente() {
		return chiaveUtente;
	}
	
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
    	arg.setString(1, this.chiaveUtente);
		arg.setString(2, this.codiceFiscale != null ? this.codiceFiscale : "");
		arg.setString(3, this.numeroCell != null ? this.numeroCell : "");
		arg.setString(4, this.mail != null ? this.mail : "");   
		arg.setString(5, this.operatore != null ? this.operatore : "");   
		}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveUtente);
		arg.setString(2, this.codiceFiscale);
		arg.setString(3, this.numeroCell);
		arg.setString(4, this.mail);
		arg.setString(5, this.operatore);
		if (this.dataUltimaSincro != null)
			arg.setDate(6, new java.sql.Date(this.dataUltimaSincro.getTime().getTime()));
		else 
			arg.setDate(6, null);
		}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setNumeroCell(String numeroCell) {
		this.numeroCell = numeroCell;
	}

	public String getNumeroCell() {
		return numeroCell;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public String getOperatore() {
		return operatore;
	}

	public Calendar getDataUltimaSincro() {
		return dataUltimaSincro;
	}

	public void setDataUltimaSincro(Calendar dataUltimaSincro) {
		this.dataUltimaSincro = dataUltimaSincro;
	}
	
	public static Calendar getCalendarFromDate(java.sql.Date date)
    {
          if (date == null) return null;
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          return cal;
    }
}