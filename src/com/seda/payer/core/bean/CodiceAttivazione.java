package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sql.rowset.CachedRowSet;

import com.seda.payer.commons.bean.Lifecycle;

public class CodiceAttivazione  extends Lifecycle implements Serializable{
	private static final long serialVersionUID = 1L;
	//ECR_CSOCCSOC CHAR(5) 'ATTRIBUTO CODICE SOCIETA'!
	private String codiceSocieta;
	//ECR_CUTECUTE CHAR(5) 'ATTRIBUTO CODICE UTENTE'!
	private String codiceUtente;
	//ECR_KANEKENT CHAR(10) 'ATTRIBUTO CHIAVE ENTE/CONSORZIO'!
	private String chiaveEnte;
	//ECR_CECRCFIS 
	private String codiceFiscale;
	//"ECR_CECRCATT" INTEGER NOT NULL,
	private String codiceAttivazione;
	//"ECR_CECRCOPE" VARCHAR(100) NOT NULL,
	private String operatore;
	//"ECR_GECRGAGG" TIMESTAMP NOT NULL
	private String dataAggiornamento;

	public CodiceAttivazione() {
		Initialize();
	}
	
	private void Initialize()
	{
		codiceSocieta = "";
		codiceUtente= "";
		chiaveEnte = "";
		codiceFiscale = "";
		codiceAttivazione = "";
		dataAggiornamento = "31/12/2099 00:00:00";
	}
	
	public CodiceAttivazione(ResultSet data) throws SQLException {
    	if (data == null)
    		return;
    	Initialize();
    	
    	codiceSocieta = data.getString("ECR_CSOCCSOC");
    	codiceUtente = data.getString("ECR_CUTECUTE");
    	chiaveEnte = data.getString("ECR_KANEKENT");
    	codiceFiscale = data.getString("ECR_CECRCFIS");
    	codiceAttivazione = "" + data.getInt("ECR_CECRCATT");
    	operatore = data.getString("ECR_CECRCOPE");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		dataAggiornamento = dateFormat.format(data.getTimestamp("ECR_GECRGAGG"));  
    }
	
	public CodiceAttivazione(CachedRowSet rowSet) throws SQLException {
    	if (rowSet == null)
    		return;
    	Initialize();
    	
    	if (rowSet.getString("ECR_CSOCCSOC") != null) codiceSocieta = rowSet.getString("ECR_CSOCCSOC");
    	if (rowSet.getString("ECR_CUTECUTE") != null) codiceUtente = rowSet.getString("ECR_CUTECUTE");
    	if (rowSet.getString("ECR_KANEKENT") != null) chiaveEnte = rowSet.getString("ECR_KANEKENT");
    	if (rowSet.getString("ECR_CECRCFIS") != null) codiceFiscale = rowSet.getString("ECR_CECRCFIS");
    	if (rowSet.getString("ECR_CECRCATT") != null) codiceAttivazione = "" + rowSet.getInt("ECR_CECRCATT");
    	if (rowSet.getString("ECR_CECRCOPE") != null) operatore = rowSet.getString("ECR_CECRCOPE");
    	if (rowSet.getString("ECR_GECRGAGG") != null) {
    		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    		dataAggiornamento = dateFormat.format(rowSet.getTimestamp("ECR_GECRGAGG"));  
    	}
    }
	
	public static Calendar getCalendarFromDate(java.util.Date date)
    {
          if (date == null) return null;
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          return cal;
    }
	
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.chiaveEnte);
		arg.setString(4, this.codiceFiscale);
		arg.setString(5, this.operatore);
		arg.registerOutParameter(6, Types.VARCHAR);
		arg.registerOutParameter(7, Types.VARCHAR);
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceAttivazione() {
		return codiceAttivazione;
	}

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public String getDataAggiornamento() {
		return dataAggiornamento;
	}
}
