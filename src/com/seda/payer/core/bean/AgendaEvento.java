package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

//LP PG190340 e' stato rimosso l'attributo enteChiave con le conseguenti modifiche

public class AgendaEvento extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private String chiaveEvento;
	private String chiaveUtente;
	private Calendar dataEvento;
	private String tipologiaEventoChiave;
	private String tipologiaEventoDescrizione;
	private String stato;
	private String enteDescrizione;
	private Calendar oraScadenzaEvento;
	private String intervalloEventoChiave;
	private String intervalloEventoDescrizione;
	private String importoDaPagare;
	private String oggetto;
	private String luogo;
	private String note;
	private String cancellazione;
	private String inviatoSMS;
	private String inviatoMAIL;
	private String inviatoPreavviso;
	private String chiaveAutomatico;
	private String operatore;
	//inizio LP PG190340
	private String emailUtente;
	private String numCellUtente;
	//fine LP PG190340
	
	 
    public void setChiaveEvento(String chiaveEvento) {
		this.chiaveEvento = chiaveEvento;
	}

	public String getChiaveEvento() {
		return chiaveEvento;
	}

	public AgendaEvento() { 
    }

    public AgendaEvento(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	this.setChiaveEvento(data.getString("AGC_KAGCKAGC"));
    	this.setChiaveUtente(data.getString("AGC_KAGUKUSR"));
    	// ritorna la stringa ISO dd/MM/YYYY
    	this.setDataEvento(getCalendarFromDate(data.getDate("DATA_ISO")));	
    	this.setTipologiaEventoChiave(data.getString("AGC_KAGEKAGE"));
    	this.tipologiaEventoDescrizione = data.getString("AGE_DAGEDESC"); // non deve essere settata è la descrizione della AGC_KAGEKAGE
    	this.setStato(data.getString("AGC_FAGCFLAG"));
    	//inizio LP PG190340
    	this.setEnteDescrizione(data.getString("AGC_GAGCCDEN"));
    	//fine LP PG190340
    	this.oraScadenzaEvento = getCalendarFromTime(data.getTime("AGC_HAGCHORA"));
    	setIntervalloEventoChiave(data.getString("AGC_KAGIKAGI"));
    	this.intervalloEventoDescrizione = data.getString("AGI_DAGIDESC");  // non deve essere settata è la descrizione della AGC_KAGEKAGE
    	this.setImportoDaPagare(data.getString("AGC_NAGCNIMP"));
    	this.setOggetto(data.getString("AGC_CAGCCOGG"));
    	this.setLuogo(data.getString("AGC_CAGCCLUO"));
    	this.setNote(data.getString("AGC_CAGCCNOT"));
    	this.setCancellazione(data.getString("AGC_FAGCFDEL"));
    	this.setInviatoSMS(data.getString("AGC_FAGCSSMS"));
    	this.setInviatoMAIL(data.getString("AGC_FAGCMAIL"));
    	this.setInviatoPreavviso(data.getString("AGC_FAGCPREM"));
    	this.setChiaveAutomatico(data.getString("AGC_KAGCKKEY"));
    	this.setOperatore(data.getString("AGC_CAGCCOPE"));
    	//inizio LP PG190340
    	try {
	    	this.emailUtente = (data.getString("AGU_CAGUMAIL"));
		   	this.numCellUtente = (data.getString("AGU_NAGUNSMS"));
    	} catch (Exception e) {
			//bypass colonne/attributi non presenti in tutte le sp di ricerca
	    	this.emailUtente = "";
		   	this.numCellUtente = "";
		}   	
    	//fine LP PG190340

    }
	
	
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		
    	//this.SetCallable(arg);
    	arg.setString(1, this.chiaveEvento);
		arg.setString(2, this.chiaveUtente);
		arg.setDate(3, new java.sql.Date(this.dataEvento.getTime().getTime()));
		arg.setString(4, this.ConvertNull(this.tipologiaEventoChiave));
		arg.setString(5, this.ConvertNull(this.stato));
		arg.setTime(6, new java.sql.Time(this.oraScadenzaEvento.getTime().getTime()));
		arg.setString(7, this.ConvertNull(this.intervalloEventoChiave));
		arg.setString(8, this.ConvertNull(this.importoDaPagare));
		arg.setString(9, this.ConvertNull(this.oggetto));
		arg.setString(10, this.ConvertNull(this.luogo));
		arg.setString(11, this.ConvertNull(this.note));
		arg.setString(12, this.ConvertNull(this.cancellazione));
		arg.setString(13, this.ConvertNull(this.inviatoSMS));
		arg.setString(14, this.ConvertNull(this.inviatoMAIL));
		arg.setString(15, this.ConvertNull(this.inviatoPreavviso));
		arg.setString(16, this.ConvertNull(this.chiaveAutomatico));
		arg.setString(17, this.ConvertNull(this.operatore));
    	//inizio LP PG190340
		arg.setString(18, this.ConvertNull(this.enteDescrizione));
    	//fine LP PG190340
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		
		//NB: per il delete logico è necessario lasciare a NULL tutti i campi, 
		//altrimenti con il ConvertNUll usato per il save, vengono tutti svuotati
		//this.SetCallable(arg);
		arg.setString(1, this.chiaveEvento);
		arg.setString(2, this.chiaveUtente);
		
		if (dataEvento != null)
			arg.setDate(3, new java.sql.Date(this.dataEvento.getTime().getTime()));
		else 
			arg.setDate(3, null);
		
		arg.setString(4, this.tipologiaEventoChiave);
		arg.setString(5, this.stato);
		//arg.setString(6, this.enteChiave);
		
		if (oraScadenzaEvento != null)
			arg.setTime(6, new java.sql.Time(this.oraScadenzaEvento.getTime().getTime()));
		else 
			arg.setDate(6, null);
		
		arg.setString(7, this.intervalloEventoChiave);
		arg.setString(8, this.importoDaPagare);
		arg.setString(9, this.oggetto);
		arg.setString(10, this.luogo);
		arg.setString(11, this.note);
		arg.setString(12, this.cancellazione);
		arg.setString(13, this.inviatoSMS);
		arg.setString(14, this.inviatoMAIL);
		arg.setString(15, this.inviatoPreavviso);
		arg.setString(16, this.chiaveAutomatico);
		arg.setString(17, this.operatore);
    	//inizio LP PG190340
		arg.setString(18, this.ConvertNull(this.enteDescrizione));
    	//fine LP PG190340
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
		
		
	}
	
	/*private void SetCallable(CallableStatement arg) throws SQLException
	{
		arg.setString(1, this.chiaveEvento);
		arg.setString(2, this.chiaveUtente);
		arg.setDate(3, new java.sql.Date(this.dataEvento.getTime().getTime()));
		arg.setString(4, this.ConvertNull(this.tipologiaEventoChiave));
		arg.setString(5, this.ConvertNull(this.stato));
		arg.setString(6, this.ConvertNull(this.enteChiave));
		arg.setTime(7, new java.sql.Time(this.oraScadenzaEvento.getTime().getTime()));
		arg.setString(8, this.ConvertNull(this.intervalloEventoChiave));
		arg.setString(9, this.ConvertNull(this.importoDaPagare));
		arg.setString(10, this.ConvertNull(this.oggetto));
		arg.setString(11, this.ConvertNull(this.luogo));
		arg.setString(12, this.ConvertNull(this.note));
		arg.setString(13, this.ConvertNull(this.cancellazione));
		arg.setString(14, this.ConvertNull(this.inviatoSMS));
		arg.setString(15, this.ConvertNull(this.inviatoMAIL));
		arg.setString(16, this.ConvertNull(this.inviatoPreavviso));
		arg.setString(17, this.ConvertNull(this.chiaveAutomatico));
		arg.setString(18, this.ConvertNull(this.operatore));
		
		
	}*/
	
	/**
	 * Converte il NULL in stringa vuota
	 * @param sTemp
	 * @return
	 */
	private String ConvertNull(String sTemp)
	{
		return sTemp != null ? sTemp : "";
	}

	public static Calendar getCalendarFromDate(java.util.Date date)
    {
          if (date == null) return null;
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          return cal;
    }
	
	public static Calendar getCalendarFromTime(Time time)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		return cal;
	}
	
//	public static Calendar getCalendarFromDateString(String sYYYY_MM_DD)
//	{
//		Integer[] iAAAAmmgg = new Integer[3];
//		if ((sYYYY_MM_DD != null) && (sYYYY_MM_DD.length()==14))
//		{
//			try
//			{
//				iAAAAmmgg[0] = sYYYY_MM_DD.substring(0, 4);
//				iAAAAmmgg[1] = sYYYY_MM_DD.substring(4, 6);
//				iAAAAmmgg[2] = sYYYY_MM_DD.substring(6, 8);
//			}
//			catch (Exception ex)
//			{
//			}
//		}		
//		
//		Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, iAAAAmmgg[0]);
//        cal.set(Calendar.MONTH, iAAAAmmgg[1]);
//        cal.set(Calendar.DAY_OF_MONTH, iAAAAmmgg[2]);
//
//	}
	
	/**
	 * DD/MM/AAAA converte in Calendar
	 * @param sDDMMAAAA
	 * @return
	 */
	/*private static Calendar convertDate(String sDD_MM_YYYY)
	{
		Calendar cal = Calendar.getInstance();
		try
		{
		    SimpleDateFormat formatterIT = new SimpleDateFormat("dd/MM/yyyy");
		    java.util.Date utilDate = formatterIT.parse(sDD_MM_YYYY);
		    cal.setTime(utilDate);			   
		}
		catch (Exception ex)
		{
			cal.set(1, Calendar.JANUARY, 1);  
			
		}
		
		return cal;
	}    */
	
    
    public static java.util.Date getDateFromCalendar(Calendar cal)
    {
          if (cal == null) return null;
          return cal.getTime();
    }

    

	public String getTipologiaEventoDescrizione() {
		return tipologiaEventoDescrizione;
	}
	public String getIntervalloEventoDescrizione() {
		return intervalloEventoDescrizione;
	}

	public void setOraScadenzaEvento(Calendar oraScadenzaEvento) {
		this.oraScadenzaEvento = oraScadenzaEvento;
	}

	public Calendar getOraScadenzaEvento() {
		return oraScadenzaEvento;
	}
	
	public void setChiaveUtente(String chiaveUtente) {
		this.chiaveUtente = chiaveUtente;
	}

	public String getChiaveUtente() {
		return chiaveUtente;
	}

	public void setDataEvento(Calendar dataEvento) {
		this.dataEvento = dataEvento;
	}

	public Calendar getDataEvento() {
		return dataEvento;
	}
	
	public void setTipologiaEventoChiave(String tipologiaEventoChiave) {
		this.tipologiaEventoChiave = tipologiaEventoChiave;
	}

	public String getTipologiaEventoChiave() {
		return tipologiaEventoChiave;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getStato() {
		return stato;
	}

	public void setIntervalloEventoChiave(String intervalloEventoChiave) {
		this.intervalloEventoChiave = intervalloEventoChiave;
	}

	public String getIntervalloEventoChiave() {
		return intervalloEventoChiave;
	}

	public void setImportoDaPagare(String importoDaPagare) {
		this.importoDaPagare = importoDaPagare;
	}

	public String getImportoDaPagare() {
		return importoDaPagare;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getLuogo() {
		return luogo;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setCancellazione(String cancellazione) {
		this.cancellazione = cancellazione;
	}

	public String getCancellazione() {
		return cancellazione;
	}

	public void setInviatoSMS(String inviatoSMS) {
		this.inviatoSMS = inviatoSMS;
	}

	public String getInviatoSMS() {
		return inviatoSMS;
	}

	public void setInviatoMAIL(String inviatoMAIL) {
		this.inviatoMAIL = inviatoMAIL;
	}

	public String getInviatoMAIL() {
		return inviatoMAIL;
	}

	public void setInviatoPreavviso(String inviatoPreavviso) {
		this.inviatoPreavviso = inviatoPreavviso;
	}

	public String getInviatoPreavviso() {
		return inviatoPreavviso;
	}

	public void setChiaveAutomatico(String chiaveAutomatico) {
		this.chiaveAutomatico = chiaveAutomatico;
	}

	public String getChiaveAutomatico() {
		return chiaveAutomatico;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public String getOperatore() {
		return operatore;
	}

	public String getEnteDescrizione() {
		return enteDescrizione;
	}

	public void setEnteDescrizione(String enteDescrizione) {
		this.enteDescrizione = enteDescrizione;
	}

	//inizio LP PG190340
	public String getEmailUtente() {
		return emailUtente;
	}

	public String getNumCellUtente() {
		return numCellUtente;
	}
	//fine LP PG190340
}