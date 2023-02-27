package com.seda.payer.core.bean;

import java.io.Serializable;
//import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

import javax.sql.rowset.CachedRowSet;
//import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

//import com.seda.payer.commons.bean.Lifecycle;

public class AnagraficaBollettino extends ModelAttributes implements Serializable{
	private static final long serialVersionUID = 1L;
	//ECA_CSOCCSOC CHAR(5) 'ATTRIBUTO CODICE SOCIETA'!
	private String codiceSocieta;
	//ECA_CUTECUTE CHAR(5) 'ATTRIBUTO CODICE UTENTE'!
	private String codiceUtente;
	//ECA_KANEKENT CHAR(10) 'ATTRIBUTO CHIAVE ENTE/CONSORZIO'!
	private String chiaveEnte;
	//ECA_CECACFIS 
	private String codiceFiscale;
	//ECA_FECAFBRS ANAGRAFICA BORSELLINO PRESENTE Y/N
	private String flgPresent;
	//ECA_FECAFATT FLG BORSELLINO ATTIVO Y/N
	private String flgActivate;
	//ECA_FECAPACC FLG AnagraficaBorsellino PrimoAccesso Y/N
	private String flgFirstAccess;
	//ECA_FECAFWLK
	private String flgFwlk;
	//ECA_DECACOGN
	private String cognome;
	//ECA_DECANOME 
	private String nome;
	//ECA_CECACSMS 
	private String cellulare;
	//ECA_CECAMAIL
	private String mail;
	//ECA_CECAMPEC
	private String mailPec;
	//ECA_DECAINDI
	private String indirizzo;
	//ECA_CECACCAP
	private String cap;
	//ECA_DECADCOM
	private String comune;
	//ECA_CECASPRO
	private String provincia;
	//ECA_BRS_FLAGBORSELLINO Y/N
	private String flagBorsellino;
	//PG170200 GG - inizio
	//ECA_FECANTMP Y/N/''
	private String flgNotificaMail;
	//ECA_FECANSMS Y/N/''
	private String flgNotificaSms;
	//ECA_CECAMPIN
	private String pinCodeMail;
	//ECA_CECAPPIN
	private String pinCodeMailPEC;	//NRO TICKET 2018111388000123
	//ECA_CECASPIN
	private String pinCodeSms;
	//ECA_FECAVMPN Y/N
	private String flgValidazionePinCodeMail;
	//ECA_FECAVPPN Y/N
	private String flgValidazionePinCodeMailPEC; //NRO TICKET 2018111388000123
	//ECA_FECAVSPN Y/N
	private String flgValidazionePinCodeSms;
	//PG170200 GG - fine
	
	private String codiceAttivazione; //PG170280 CT
	private String naturaGiuridica; //PG170280 CT
//	private String ragioneSociale; //PG170280 CT
	private Calendar dataWelcomeKit; //PG170280 CT
	
	
	private String codiceSDI; //EP180840_001
	
	//ECA_FECAIALL Y/N
	private String flgInvioAllegato;  //PG190480_001 SB
	
	
	public String getIntestatario() {
		return cognome + " " + nome;
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
	public boolean getFlgPresent() {
		if (flgPresent.toUpperCase()=="Y")
			return true;
		else
			return false;
	}
	public void setFlgPresent(boolean flgPresent) {
		if (flgPresent)
			this.flgPresent="Y";
		else
			this.flgPresent="N";
	}
	public boolean getFlgActivate() {
		if (flgActivate.toUpperCase().equals("Y"))
			return true;
		else
			return false;
	}
	public void setFlgActivate(boolean flgActivate) {
		if (flgActivate)
			this.flgActivate="Y";
		else
			this.flgActivate="N";
	}
	public boolean getFlgFirstAccess() {
		if (flgFirstAccess.toUpperCase().equals("Y"))
			return true;
		else
			return false;
	}
	public void setFlgFirstAccess(boolean flgFirstAccess) {
		if (flgFirstAccess)
			this.flgFirstAccess="Y";
		else
			this.flgFirstAccess="N";
	}
	public boolean getFlgFwlk() {
		if (flgFwlk.toUpperCase().equals("Y"))
			return true;
		else
			return false;
	}
	public void setFlgFwlk(boolean flgFwlk) {
		if (flgFwlk)
			this.flgFwlk="Y";
		else
			this.flgFwlk="N";
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMailPec() {
		return mailPec;
	}
	public void setMailPec(String mailPec) {
		this.mailPec = mailPec;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public boolean getFlagBorsellino() {
		if (flagBorsellino.toUpperCase().equals("Y"))
			return true;
		else
			return false;
	}
	public void setFlagBorsellino(boolean flagBorsellino) {
		if (flagBorsellino)
			this.flagBorsellino="Y";
		else
			this.flagBorsellino="N";
	}
	
	//PG170200 GG - inizio
	public String getFlgNotificaMail() {
		return flgNotificaMail;
	}
	public void setFlgNotificaMail(String flgNotificaMail) {
		this.flgNotificaMail = flgNotificaMail;
	}
	public String getFlgNotificaSms() {
		return flgNotificaSms;
	}
	public void setFlgNotificaSms(String flgNotificaSms) {
		this.flgNotificaSms = flgNotificaSms;
	}
	public String getPinCodeMail() {
		return pinCodeMail;
	}
	public void setPinCodeMail(String pinCodeMail) {
		this.pinCodeMail = pinCodeMail;
	}
	// INIZIO NRO TICKET 2018111388000123
	public String getPinCodeMailPEC() {
		return pinCodeMailPEC;
	}
	public void setPinCodeMailPEC(String pinCodeMailPEC) {
		this.pinCodeMailPEC = pinCodeMailPEC;
	}
	//	FINE NRO TICKET 2018111388000123
	public String getPinCodeSms() {
		return pinCodeSms;
	}
	public void setPinCodeSms(String pinCodeSms) {
		this.pinCodeSms = pinCodeSms;
	}
	public String getFlgValidazionePinCodeMail() {
		return flgValidazionePinCodeMail;
	}
	public void setFlgValidazionePinCodeMail(String flgValidazionePinCodeMail) {
		this.flgValidazionePinCodeMail = flgValidazionePinCodeMail;
	}
	//	INIZIO NRO TICKET 2018111388000123
	public String getFlgValidazionePinCodeMailPEC() {
		return flgValidazionePinCodeMailPEC;
	}
	public void setFlgValidazionePinCodeMailPEC(String flgValidazionePinCodeMailPEC) {
		this.flgValidazionePinCodeMailPEC = flgValidazionePinCodeMailPEC;
	}
	public String getCodiceSDI() {
		return codiceSDI;
	}
	public void setCodiceSDI(String codiceSDI) {
		this.codiceSDI = codiceSDI;
	}
	//	FINE NRO TICKET 2018111388000123
	public String getFlgValidazionePinCodeSms() {
		return flgValidazionePinCodeSms;
	}
	public void setFlgValidazionePinCodeSms(String flgValidazionePinCodeSms) {
		this.flgValidazionePinCodeSms = flgValidazionePinCodeSms;
	}
	//PG170200 GG - fine
	
	public void setCodiceAttivazione(String codiceAttivazione) {
		this.codiceAttivazione = codiceAttivazione;
	}
	public String getCodiceAttivazione() {
		return codiceAttivazione;
	}
	public String getNaturaGiuridica() {
		return naturaGiuridica;
	}
	public void setNaturaGiuridica(String naturaGiuridica) {
		this.naturaGiuridica = naturaGiuridica;
	}
//	public String getRagioneSociale() {
//		return ragioneSociale;
//	}
//	public void setRagioneSociale(String ragioneSociale) {
//		this.ragioneSociale = ragioneSociale;
//	}
	public Calendar getDataWelcomeKit() {
		return dataWelcomeKit;
	}
	public void setDataWelcomeKit(Calendar dataWelcomeKit) {
		this.dataWelcomeKit = dataWelcomeKit;
	}
	
	public String getFlgInvioAllegato() {
		return flgInvioAllegato;
	}
	public void setFlgInvioAllegato(String flgInvioAllegato) {
		this.flgInvioAllegato = flgInvioAllegato;
	}	
	
	public AnagraficaBollettino() {
		
		Initialize();
	}
	private void Initialize()
	{
		codiceSocieta = "";
		codiceUtente= "";
		chiaveEnte = "";
		codiceFiscale = "";
		flgPresent = "N";
		flgActivate = "N";
		flgFirstAccess = "N";
		flgFwlk="N";
		cognome = "";
		nome = "";
		cellulare = "";
		mail = "";
		mailPec = "";
		indirizzo="";
		cap="";
		comune="";
		provincia="";
		flagBorsellino="N";
		//PG170200 GG 22082017 - inizio
		flgNotificaMail = "N";	
		flgNotificaSms = "";
		pinCodeMail = "";
		pinCodeMailPEC = "";	//NRO TICKET 2018111388000123 
		pinCodeSms = "";
		flgValidazionePinCodeMail = "N";
		flgValidazionePinCodeMailPEC = "N";	//NRO TICKET 2018111388000123 
		flgValidazionePinCodeSms = "N";
		//PG170200 GG 22082017 - fine
		
		codiceAttivazione = ""; //PG170280
		//ragioneSociale = "";
		naturaGiuridica = "F";
		dataWelcomeKit = Calendar.getInstance();
		codiceSDI = "";
		flgInvioAllegato="N";
	}
	public AnagraficaBollettino(ResultSet data) throws SQLException {
    	if (data == null)
    		return;
    	Initialize();
    	
    	if (data.getString("ECA_CSOCCSOC") !=null) codiceSocieta = data.getString("ECA_CSOCCSOC");
    	if (data.getString("ECA_CUTECUTE") !=null) codiceUtente = data.getString("ECA_CUTECUTE");
    	if (data.getString("ECA_KANEKENT") !=null) chiaveEnte = data.getString("ECA_KANEKENT");
    	if (data.getString("ECA_CECACFIS") !=null) codiceFiscale = data.getString("ECA_CECACFIS");
    	if (data.getString("ECA_FECAFBRS") !=null) flgPresent = data.getString("ECA_FECAFBRS");
    	if (data.getString("ECA_FECAFATT") !=null) flgActivate = data.getString("ECA_FECAFATT");
    	if (data.getString("ECA_FECAPACC") !=null) flgFirstAccess = data.getString("ECA_FECAPACC");
    	if (data.getString("ECA_DECACOGN") !=null) cognome = data.getString("ECA_DECACOGN");
    	if (data.getString("ECA_DECANOME") !=null) nome = data.getString("ECA_DECANOME");
    	if (data.getString("ECA_CECACSMS") !=null) cellulare = data.getString("ECA_CECACSMS");
    	if (data.getString("ECA_CECAMAIL") !=null) mail = data.getString("ECA_CECAMAIL");
    	if (data.getString("ECA_CECAMPEC") !=null) mailPec = data.getString("ECA_CECAMPEC");
    	if (data.getString("ECA_FECAFWLK") !=null) flgFwlk = data.getString("ECA_FECAFWLK");
    	if (data.getString("ECA_DECAINDI") !=null) indirizzo = data.getString("ECA_DECAINDI");
    	if (data.getString("ECA_CECACCAP") !=null) cap = data.getString("ECA_CECACCAP");
    	if (data.getString("ECA_DECADCOM") !=null) comune = data.getString("ECA_DECADCOM");
    	if (data.getString("ECA_CECASPRO") !=null) provincia = data.getString("ECA_CECASPRO");
    	if (data.getString("ECA_BRS_FLAGBORSELLINO") !=null) flagBorsellino = data.getString("ECA_BRS_FLAGBORSELLINO");
    	//PG170200 GG 22082017 - inizio
    	if (data.getString("ECA_FECANTMP") !=null) flgNotificaMail = data.getString("ECA_FECANTMP");	
    	if (data.getString("ECA_FECANSMS") !=null) flgNotificaSms = data.getString("ECA_FECANSMS");
    	if (data.getString("ECA_CECAMPIN") !=null) pinCodeMail = data.getString("ECA_CECAMPIN");
    	if (data.getString("ECA_CECAPPIN") !=null) pinCodeMailPEC = data.getString("ECA_CECAPPIN"); //NRO TICKET 2018111388000123 	
    	if (data.getString("ECA_CECASPIN") !=null) pinCodeSms = data.getString("ECA_CECASPIN");
    	if (data.getString("ECA_FECAVMPN") !=null) flgValidazionePinCodeMail = data.getString("ECA_FECAVMPN");	
    	if (data.getString("ECA_FECAVPPN") !=null) flgValidazionePinCodeMailPEC = data.getString("ECA_FECAVPPN");	//NRO TICKET 2018111388000123
    	if (data.getString("ECA_FECAVSPN") !=null) flgValidazionePinCodeSms = data.getString("ECA_FECAVSPN");
    	//PG170200 GG 22082017 - fine
    	
    	if (data.getString("ECA_CECACSDI") !=null) codiceSDI = data.getString("ECA_CECACSDI");
    	
    }
	public AnagraficaBollettino(CachedRowSet rowSet) throws SQLException {
    	if (rowSet == null)
    		return;
    	Initialize();
    	
    	if (rowSet.getString("ECA_CSOCCSOC") !=null) codiceSocieta = rowSet.getString("ECA_CSOCCSOC");
    	if (rowSet.getString("ECA_CUTECUTE") !=null) codiceUtente = rowSet.getString("ECA_CUTECUTE");
    	if (rowSet.getString("ECA_KANEKENT") !=null) chiaveEnte = rowSet.getString("ECA_KANEKENT");
    	if (rowSet.getString("ECA_CECACFIS") !=null) codiceFiscale = rowSet.getString("ECA_CECACFIS");
    	if (rowSet.getString("ECA_FECAFBRS") !=null) flgPresent = rowSet.getString("ECA_FECAFBRS");
    	if (rowSet.getString("ECA_FECAFATT") !=null) flgActivate = rowSet.getString("ECA_FECAFATT");
    	if (rowSet.getString("ECA_FECAPACC") !=null) flgFirstAccess = rowSet.getString("ECA_FECAPACC");
    	if (rowSet.getString("ECA_DECACOGN") !=null) cognome = rowSet.getString("ECA_DECACOGN");
    	if (rowSet.getString("ECA_DECANOME") !=null) nome = rowSet.getString("ECA_DECANOME");
    	if (rowSet.getString("ECA_CECACSMS") !=null) cellulare = rowSet.getString("ECA_CECACSMS");
    	if (rowSet.getString("ECA_CECAMAIL") !=null) mail = rowSet.getString("ECA_CECAMAIL");
    	if (rowSet.getString("ECA_CECAMPEC") !=null) mailPec = rowSet.getString("ECA_CECAMPEC");
    	if (rowSet.getString("ECA_FECAFWLK") !=null) flgFwlk = rowSet.getString("ECA_FECAFWLK");
    	if (rowSet.getString("ECA_DECAINDI") !=null) indirizzo = rowSet.getString("ECA_DECAINDI");
    	if (rowSet.getString("ECA_CECACCAP") !=null) cap = rowSet.getString("ECA_CECACCAP");
    	if (rowSet.getString("ECA_DECADCOM") !=null) comune = rowSet.getString("ECA_DECADCOM");
    	if (rowSet.getString("ECA_CECASPRO") !=null) provincia = rowSet.getString("ECA_CECASPRO");
    	if (rowSet.getString("ECA_BRS_FLAGBORSELLINO") !=null) flagBorsellino = rowSet.getString("ECA_BRS_FLAGBORSELLINO");
    	//PG170200 GG 22082017 - inizio
    	if (rowSet.getString("ECA_FECANTMP") !=null) flgNotificaMail = rowSet.getString("ECA_FECANTMP");
    	if (rowSet.getString("ECA_FECANSMS") !=null) flgNotificaSms = rowSet.getString("ECA_FECANSMS");
    	if (rowSet.getString("ECA_CECAMPIN") !=null) pinCodeMail = rowSet.getString("ECA_CECAMPIN");
    	if (rowSet.getString("ECA_CECAPPIN") !=null) pinCodeMailPEC = rowSet.getString("ECA_CECAPPIN"); //NRO TICKET 2018111388000123 
    	if (rowSet.getString("ECA_CECASPIN") !=null) pinCodeSms = rowSet.getString("ECA_CECASPIN");
    	if (rowSet.getString("ECA_FECAVMPN") !=null) flgValidazionePinCodeMail = rowSet.getString("ECA_FECAVMPN");
    	if (rowSet.getString("ECA_FECAVPPN") !=null) flgValidazionePinCodeMailPEC = rowSet.getString("ECA_FECAVPPN");	//NRO TICKET 2018111388000123
    	if (rowSet.getString("ECA_FECAVSPN") !=null) flgValidazionePinCodeSms = rowSet.getString("ECA_FECAVSPN");
    	//PG170200 GG 22082017 - fine
    	
    	//PG170280 CT
    	if (rowSet.getString("ECA_CECACATT") !=null) codiceAttivazione = rowSet.getString("ECA_CECACATT");
    	//if (rowSet.getString("ECA_DECARAGS") !=null) ragioneSociale = rowSet.getString("ECA_DECARAGS");
    	if (rowSet.getString("ECA_TECAUSER") !=null) naturaGiuridica = rowSet.getString("ECA_TECAUSER");
    	if (rowSet.getString("ECA_GECAGWLK") !=null) 
    		dataWelcomeKit = getCalendarFromDate(rowSet.getDate("ECA_GECAGWLK"));
    	else 
    		dataWelcomeKit = null;
    	
    	if (rowSet.getString("ECA_CECACSDI") !=null) codiceSDI = rowSet.getString("ECA_CECACSDI");
    	
    	if (rowSet.getString("ECA_FECAIALL") !=null) flgInvioAllegato = rowSet.getString("ECA_FECAIALL");

    }
	public static Calendar getCalendarFromDate(java.util.Date date)
    {
          if (date == null) return null;
          Calendar cal = Calendar.getInstance();
          cal.setTime(date);
          return cal;
    }

}
