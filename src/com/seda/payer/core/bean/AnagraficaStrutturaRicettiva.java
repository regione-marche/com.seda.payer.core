package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.math.BigDecimal;

import com.seda.payer.commons.bean.Lifecycle;

public class AnagraficaStrutturaRicettiva extends Lifecycle implements Serializable{

	private static final long serialVersionUID = 1L;

	private String chiaveStrutturaRicettiva;
    private String codiceBelfiore;
    private String codiceAutorizzazione;
    private String partitaIVAStruttura;
    private String ragioneSocialeStruttura;
    private String insegnaStruttura;
    private String anagraficaTitolare;
    private String codFiscaleTitolare;
    private String indirizzoStruttura;
    private String capStruttura;
    private String chiaveTipologiaStruttura;
    private String email;
    private String flagPrimoAccesso;
    private String codiceUtente;
    private String codiceEnteGestionaleEntrate;
    private String impostaServizioGestionaleEntrate;
    private String codiceContribuenteGestionaleEntrate;
    private Calendar dataUltimoAggiornamento;
    private String operatoreUltimoAggiornamento;
    private String emailPec;	//PG150420 GG 18052016
	
    //PG170240
    private java.lang.String flagSubentro;
    private java.util.Calendar dataValiditaInizio;
    private java.util.Calendar dataValiditaFine;
    private java.util.Calendar dataObbligoComunicazioneInizio;
    private java.util.Calendar dataObbligoComunicazioneFine;
    
    //PG180170
    private java.lang.String note;

    //PG190300 - inizio
    private java.lang.String codiceAutorizzazionePrincipale;
    private java.lang.String telefono;
    private java.util.Calendar dataNascitaTitolare;
    private java.lang.String provinciaTitolare;
    private java.lang.String comuneTitolare;
    private java.util.Calendar dataNascitaGestore;
    private java.lang.String provinciaGestore;
    private java.lang.String comuneGestore;
    private java.lang.String codFiscaleGestore;
    private java.lang.String anagraficaGestore;
    private java.lang.String pinCodeMail;
    private java.lang.String pinCodeMailPec;
    private java.lang.String flagPinCodeMail;
    private java.lang.String flagPinCodeMailPec;
    private java.lang.String flagPrincipale; 
    //PG190300 - fine
    
    //PG190330 SB - inizio
    private java.lang.String comuneCatastale;
    private java.lang.String particellaEdificiale;
    private java.lang.String subalterno;
    private BigDecimal superficie;
    private Integer postiLetto;
    private java.lang.String piano;
    
    private Integer numAlloggiTotali;
    private Integer numAlloggiAttivi;
    private String statoComunicazione;
    //PG190330 SB - fine
    
    private String codiceIstat;		//PG200390 GG
    
    public AnagraficaStrutturaRicettiva() {}
    
    public AnagraficaStrutturaRicettiva(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setChiaveStrutturaRicettiva(data.getString("SAN_KSANKSAN"));
        setCodiceBelfiore(data.getString("SAN_CANEBELF"));
        setCodiceAutorizzazione(data.getString("SAN_CSANCAUT"));
        setPartitaIVAStruttura(data.getString("SAN_CSANCFPI"));
        setRagioneSocialeStruttura(data.getString("SAN_DSANDRAG"));
        setInsegnaStruttura(data.getString("SAN_DSANDINS"));
        setAnagraficaTitolare(data.getString("SAN_DSANDTIT"));
        setCodFiscaleTitolare(data.getString("SAN_CSANFISC"));
        setIndirizzoStruttura(data.getString("SAN_DSANDIND"));
        setCapStruttura(data.getString("SAN_CSANCCAP"));
        setChiaveTipologiaStruttura(data.getString("SAN_CSSRCSSR"));
        setEmail(data.getString("SAN_DSANMAIL"));
        setFlagPrimoAccesso(data.getString("SAN_FSANFACC"));
        setCodiceUtente(data.getString("SAN_CUTECUTE"));
        setCodiceEnteGestionaleEntrate(data.getString("SAN_CSANCGES"));
        setImpostaServizioGestionaleEntrate(data.getString("SAN_CSANCISE"));
        setCodiceContribuenteGestionaleEntrate(data.getString("SAN_CSANCCON"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTimestamp("SAN_GSANGAGG").getTime());
        setDataUltimoAggiornamento(cal);
        setOperatoreUltimoAggiornamento(data.getString("SAN_CSANCOPE"));
        setEmailPec(data.getString("SAN_DSANEPEC"));
        //PG170240
        setFlagSubentro(data.getString("SAN_FSANFSUB"));
        Calendar calDataValiditaInizio = Calendar.getInstance();
        calDataValiditaInizio.setTimeInMillis(data.getTimestamp("SAN_GSANGVAL_INI").getTime());
        setDataValiditaInizio(calDataValiditaInizio);
        Calendar calDataValiditaFine = Calendar.getInstance();
        calDataValiditaFine.setTimeInMillis(data.getTimestamp("SAN_GSANGVAL_FIN").getTime());
        setDataValiditaFine(calDataValiditaFine);
        Calendar calDataObbligoComunicazioneInizio = Calendar.getInstance();
        calDataObbligoComunicazioneInizio.setTimeInMillis(data.getTimestamp("SAN_GSANGOBC_INI").getTime());
        setDataObbligoComunicazioneInizio(calDataObbligoComunicazioneInizio);
        Calendar calDataObbligoComunicazioneFine = Calendar.getInstance();
        calDataObbligoComunicazioneFine.setTimeInMillis(data.getTimestamp("SAN_GSANGOBC_FIN").getTime());
        setDataObbligoComunicazioneFine(calDataObbligoComunicazioneFine);
        //PG180170
        setNote(data.getString("SAN_DSANNOTE")); 
        //PG190300 - inizio
        setCodiceAutorizzazionePrincipale(data.getString("SAN_CSANCAUT_PRI"));
        setTelefono(data.getString("SAN_CSANNTEL"));
        Calendar calDataNacitaTitolare = Calendar.getInstance();
        calDataNacitaTitolare.setTimeInMillis(data.getTimestamp("SAN_GSANNASC_TIT").getTime());
        setDataNascitaTitolare(calDataNacitaTitolare);
        setProvinciaTitolare(data.getString("SAN_CSANPROV_TIT"));
        setComuneTitolare(data.getString("SAN_CSANCOBF_TIT"));
        Calendar calDataNascitaGestore = Calendar.getInstance();
        calDataNascitaGestore.setTimeInMillis(data.getTimestamp("SAN_GSANNASC_GEST").getTime());
        setDataNascitaGestore(calDataNascitaGestore);
        setProvinciaGestore(data.getString("SAN_CSANPROV_GEST"));
        setComuneGestore(data.getString("SAN_CSANCOBF_GEST"));
        setAnagraficaGestore(data.getString("SAN_DSANDGES"));
        setCodFiscaleGestore(data.getString("SAN_CSANCFIS_GEST"));
        setPinCodeMail(data.getString("SAN_CSANMPIN"));
        setPinCodeMailPec(data.getString("SAN_CSANPPIN"));
        setFlagPinCodeMail(data.getString("SAN_FSANVMPN"));	
        setFlagPinCodeMailPec(data.getString("SAN_FSANVPPN"));
        setFlagPrincipale(data.getString("FLG_PRINCIPALE"));
        //PG190300 - fine
        
        //PG190330 SB - inizio
        setComuneCatastale(data.getString("SAN_CSANCCBF"));
        setParticellaEdificiale(data.getString("SAN_NSANPAED"));
        setSubalterno(data.getString("SAN_NSANSUBA"));
        setSuperficie(data.getBigDecimal("SAN_NSANMQUA"));
        setPostiLetto(data.getInt("SAN_NSANPOLE"));
        setPiano(data.getString("SAN_NSANPIAN"));
        //PG190330 SB - fine
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
		arg.setString(2, codiceAutorizzazione);
		arg.setString(3, partitaIVAStruttura);
		arg.setString(4, ragioneSocialeStruttura);
		arg.setString(5, insegnaStruttura);
		arg.setString(6, anagraficaTitolare);
		arg.setString(7, codFiscaleTitolare);
		arg.setString(8, indirizzoStruttura);
		arg.setString(9, capStruttura);
		arg.setString(10, chiaveTipologiaStruttura);
		arg.setString(11, email);
		arg.setString(12, flagPrimoAccesso);
		arg.setString(13, codiceUtente);
		arg.setString(14, codiceEnteGestionaleEntrate);
		arg.setString(15, impostaServizioGestionaleEntrate);
		arg.setString(16, codiceContribuenteGestionaleEntrate);
		arg.setString(17, operatoreUltimoAggiornamento);
		arg.setString(18, emailPec);	//PG150420 GG 18052016
		//PG170240
		arg.setString(19, flagSubentro== null ? "N" : flagSubentro);
		Calendar calDefault = Calendar.getInstance();
		//calDefault.set(2099, Calendar.DECEMBER, 31);
		calDefault.set(0001, Calendar.JANUARY,01);
		arg.setTimestamp(20, dataValiditaInizio == null ? new Timestamp(calDefault.getTimeInMillis()) : new Timestamp(dataValiditaInizio.getTimeInMillis()));
		arg.setTimestamp(21, dataValiditaFine == null ?  new Timestamp(calDefault.getTimeInMillis()) : new Timestamp(dataValiditaFine.getTimeInMillis()));
		arg.setTimestamp(22, dataObbligoComunicazioneInizio == null ?  new Timestamp(calDefault.getTimeInMillis()) : new Timestamp(dataObbligoComunicazioneInizio.getTimeInMillis()));
		arg.setTimestamp(23, dataObbligoComunicazioneFine == null ?  new Timestamp(calDefault.getTimeInMillis()) : new Timestamp(dataObbligoComunicazioneFine.getTimeInMillis()));
		//PG180170
		arg.setString(24, note== null ? "" : note);
		//PG190300 - inizio
		arg.setString(25, codiceAutorizzazionePrincipale==null ? "" : codiceAutorizzazionePrincipale);
		arg.setString(26, telefono==null ? "" : telefono);
		arg.setTimestamp(27, dataNascitaTitolare==null ? new Timestamp(calDefault.getTimeInMillis())  : new Timestamp(dataNascitaTitolare.getTimeInMillis()));
		arg.setString(28, provinciaTitolare==null ? "" : provinciaTitolare);
		arg.setString(29, comuneTitolare==null ? "" : comuneTitolare);
		arg.setTimestamp(30, dataNascitaGestore==null ? new Timestamp(calDefault.getTimeInMillis())  : new Timestamp(dataNascitaGestore.getTimeInMillis()));
		arg.setString(31, provinciaGestore==null ? "" : provinciaGestore);
		arg.setString(32, comuneGestore==null ? "" : comuneGestore);
		arg.setString(33, codFiscaleGestore==null ? "" : codFiscaleGestore);
		arg.setString(34, anagraficaGestore==null ? "" : anagraficaGestore);
		//PG190300 - fine
		
		//PG190330 SB - inizio
		arg.setString(35, comuneCatastale==null ? "" : comuneCatastale);
		arg.setString(36, particellaEdificiale==null ? "" : particellaEdificiale);
		arg.setString(37, subalterno==null ? "" : subalterno);
		arg.setBigDecimal(38, superficie==null ? new BigDecimal(0.00) : superficie);
		arg.setInt(39, postiLetto==null ? 0 : postiLetto);
		arg.setString(40, piano==null ? "" : piano);
		//PG190330 SB - fine
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, chiaveStrutturaRicettiva);
		arg.setString(2, codiceBelfiore);
		arg.setString(3, codiceAutorizzazione);
		arg.setString(4, partitaIVAStruttura);
		arg.setString(5, ragioneSocialeStruttura);
		arg.setString(6, insegnaStruttura);
		arg.setString(7, anagraficaTitolare);
		arg.setString(8, codFiscaleTitolare);
		arg.setString(9, indirizzoStruttura);
		arg.setString(10, capStruttura);
		arg.setString(11, chiaveTipologiaStruttura);
		arg.setString(12, email);
		arg.setString(13, flagPrimoAccesso);
		arg.setString(14, codiceUtente);
		arg.setString(15, codiceEnteGestionaleEntrate);
		arg.setString(16, impostaServizioGestionaleEntrate);
		arg.setString(17, codiceContribuenteGestionaleEntrate);
		arg.setString(18, operatoreUltimoAggiornamento);
		arg.setString(19, emailPec);	//PG150420 GG 18052016
		//PG170240
		if(flagSubentro != null)	
			arg.setString(20, flagSubentro);
		else 
			arg.setNull(20, java.sql.Types.CHAR);
		
		if(dataValiditaInizio != null)
			arg.setTimestamp(21, new Timestamp(dataValiditaInizio.getTimeInMillis()));
		else
			arg.setNull(21, Types.TIMESTAMP);
		
		if(dataValiditaFine!= null)
			arg.setTimestamp(22, new Timestamp(dataValiditaFine.getTimeInMillis()));
		else
			arg.setNull(22, Types.TIMESTAMP);
		
		if(dataObbligoComunicazioneInizio != null)
			arg.setTimestamp(23, new Timestamp(dataObbligoComunicazioneInizio.getTimeInMillis()));
		else
			arg.setNull(23, Types.TIMESTAMP);
		
		if(dataObbligoComunicazioneFine != null)
			arg.setTimestamp(24, new Timestamp(dataObbligoComunicazioneFine.getTimeInMillis()));
		else
			arg.setNull(24, Types.TIMESTAMP);
		//PG180170
		if(note != null)	
			arg.setString(25, note);
		else 
			arg.setNull(25, java.sql.Types.CLOB);
		
		//PG190300 - inizio
		if(codiceAutorizzazionePrincipale != null)
			arg.setString(26, codiceAutorizzazionePrincipale);
		else
			arg.setNull(26, Types.VARCHAR);
		
		if(telefono != null)
			arg.setString(27, telefono);
		else
			arg.setNull(27, Types.VARCHAR);
		
		if(dataNascitaTitolare != null)
			arg.setTimestamp(28, new Timestamp(dataNascitaTitolare.getTimeInMillis()));
		else
			arg.setNull(28, Types.TIMESTAMP);
		
		if(provinciaTitolare != null)
			arg.setString(29, provinciaTitolare);
		else
			arg.setNull(29, Types.VARCHAR);
		
		if(comuneTitolare != null)
			arg.setString(30, comuneTitolare);
		else
			arg.setNull(30, Types.VARCHAR);
		
		if(dataNascitaGestore != null)
			arg.setTimestamp(31, new Timestamp(dataNascitaGestore.getTimeInMillis()));
		else
			arg.setNull(31, Types.TIMESTAMP);
		
		if(provinciaGestore != null)
			arg.setString(32, provinciaGestore);
		else
			arg.setNull(32, Types.VARCHAR);
		
		if(comuneGestore != null)
			arg.setString(33, comuneGestore);
		else
			arg.setNull(33, Types.VARCHAR);
		
		if(codFiscaleGestore != null)
			arg.setString(34, codFiscaleGestore);
		else
			arg.setNull(34, Types.VARCHAR);
		
		if(anagraficaGestore != null)
			arg.setString(35, anagraficaGestore);
		else
			arg.setNull(35, Types.VARCHAR);
		
		if(pinCodeMail != null)
			arg.setString(36, pinCodeMail);
		else
			arg.setNull(36, Types.VARCHAR);
		
		if(pinCodeMailPec != null)
			arg.setString(37, pinCodeMailPec);
		else
			arg.setNull(37, Types.VARCHAR);
		
		if(flagPinCodeMail != null)
			arg.setString(38, flagPinCodeMail);
		else
			arg.setNull(38, Types.VARCHAR);
		
		if(flagPinCodeMailPec != null)
			arg.setString(39, flagPinCodeMailPec);
		else
			arg.setNull(39, Types.VARCHAR);
		//PG190300 - fine
		
		//PG190330 SB - inizio
		if(comuneCatastale != null)
			arg.setString(40, comuneCatastale);
		else
			arg.setNull(40, Types.VARCHAR);
		if(particellaEdificiale != null)
			arg.setString(41, particellaEdificiale);
		else
			arg.setNull(41, Types.VARCHAR);
		if(subalterno != null)
			arg.setString(42, subalterno);
		else
			arg.setNull(42, Types.VARCHAR);
		if(superficie != null)
			arg.setBigDecimal(43, superficie);
		else
			arg.setNull(43, Types.DECIMAL);
		if(postiLetto != null)
			arg.setInt(44, postiLetto);
		else
			arg.setNull(44, Types.INTEGER);
		if(piano != null)
			arg.setString(45, piano);
		else
			arg.setNull(45, Types.VARCHAR);
		//PG190330 SB - fine
	}

	public String getChiaveStrutturaRicettiva() {
		return chiaveStrutturaRicettiva;
	}

	public void setChiaveStrutturaRicettiva(String chiaveStrutturaRicettiva) {
		this.chiaveStrutturaRicettiva = chiaveStrutturaRicettiva;
	}

	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}

	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}

	public String getCodiceAutorizzazione() {
		return codiceAutorizzazione;
	}

	public void setCodiceAutorizzazione(String codiceAutorizzazione) {
		this.codiceAutorizzazione = codiceAutorizzazione;
	}

	public String getPartitaIVAStruttura() {
		return partitaIVAStruttura;
	}

	public void setPartitaIVAStruttura(String partitaIVAStruttura) {
		this.partitaIVAStruttura = partitaIVAStruttura;
	}

	public String getRagioneSocialeStruttura() {
		return ragioneSocialeStruttura;
	}

	public void setRagioneSocialeStruttura(String ragioneSocialeStruttura) {
		this.ragioneSocialeStruttura = ragioneSocialeStruttura;
	}

	public String getInsegnaStruttura() {
		return insegnaStruttura;
	}

	public void setInsegnaStruttura(String insegnaStruttura) {
		this.insegnaStruttura = insegnaStruttura;
	}

	public String getAnagraficaTitolare() {
		return anagraficaTitolare;
	}

	public void setAnagraficaTitolare(String anagraficaTitolare) {
		this.anagraficaTitolare = anagraficaTitolare;
	}

	public String getCodFiscaleTitolare() {
		return codFiscaleTitolare;
	}

	public void setCodFiscaleTitolare(String codFiscaleTitolare) {
		this.codFiscaleTitolare = codFiscaleTitolare;
	}

	public String getIndirizzoStruttura() {
		return indirizzoStruttura;
	}

	public void setIndirizzoStruttura(String indirizzoStruttura) {
		this.indirizzoStruttura = indirizzoStruttura;
	}

	public String getCapStruttura() {
		return capStruttura;
	}

	public void setCapStruttura(String capStruttura) {
		this.capStruttura = capStruttura;
	}

	public String getChiaveTipologiaStruttura() {
		return chiaveTipologiaStruttura;
	}

	public void setChiaveTipologiaStruttura(String chiaveTipologiaStruttura) {
		this.chiaveTipologiaStruttura = chiaveTipologiaStruttura;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
	public String getFlagPrimoAccesso() {
		return flagPrimoAccesso;
	}

	public void setFlagPrimoAccesso(String flagPrimoAccesso) {
		this.flagPrimoAccesso = flagPrimoAccesso;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceUtente() {
		return codiceUtente;
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

	public String getCodiceContribuenteGestionaleEntrate() {
		return codiceContribuenteGestionaleEntrate;
	}

	public void setCodiceContribuenteGestionaleEntrate(
			String codiceContribuenteGestionaleEntrate) {
		this.codiceContribuenteGestionaleEntrate = codiceContribuenteGestionaleEntrate;
	}

	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(
			String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public String getEmailPec() {
		return emailPec;
	}

	public void setEmailPec(String emailPec) {
		this.emailPec = emailPec;
	}

	public java.lang.String getFlagSubentro() {
		return flagSubentro;
	}

	public void setFlagSubentro(java.lang.String flagSubentro) {
		this.flagSubentro = flagSubentro;
	}

	public java.util.Calendar getDataValiditaInizio() {
		return dataValiditaInizio;
	}

	public void setDataValiditaInizio(java.util.Calendar dataValiditaInizio) {
		this.dataValiditaInizio = dataValiditaInizio;
	}

	public java.util.Calendar getDataValiditaFine() {
		return dataValiditaFine;
	}

	public void setDataValiditaFine(java.util.Calendar dataValiditaFine) {
		this.dataValiditaFine = dataValiditaFine;
	}

	public java.util.Calendar getDataObbligoComunicazioneInizio() {
		return dataObbligoComunicazioneInizio;
	}

	public void setDataObbligoComunicazioneInizio(
			java.util.Calendar dataObbligoComunicazioneInizio) {
		this.dataObbligoComunicazioneInizio = dataObbligoComunicazioneInizio;
	}

	public java.util.Calendar getDataObbligoComunicazioneFine() {
		return dataObbligoComunicazioneFine;
	}

	public void setDataObbligoComunicazioneFine(
			java.util.Calendar dataObbligoComunicazioneFine) {
		this.dataObbligoComunicazioneFine = dataObbligoComunicazioneFine;
	}

	//PG170180 GG - inizio
	public java.lang.String getNote() {
		return note;
	}

	public void setNote(java.lang.String note) {
		this.note = note;
	}
	//PG170180 GG - fine

	//PG190300 - inizio
	public java.lang.String getCodiceAutorizzazionePrincipale() {
		return codiceAutorizzazionePrincipale;
	}

	public void setCodiceAutorizzazionePrincipale(
			java.lang.String codiceAutorizzazionePrincipale) {
		this.codiceAutorizzazionePrincipale = codiceAutorizzazionePrincipale;
	}
	//PG190300 - fine

	public java.lang.String getTelefono() {
		return telefono;
	}

	public void setTelefono(java.lang.String telefono) {
		this.telefono = telefono;
	}

	public java.util.Calendar getDataNascitaTitolare() {
		return dataNascitaTitolare;
	}

	public void setDataNascitaTitolare(java.util.Calendar dataNascitaTitolare) {
		this.dataNascitaTitolare = dataNascitaTitolare;
	}

	public java.lang.String getProvinciaTitolare() {
		return provinciaTitolare;
	}

	public void setProvinciaTitolare(java.lang.String provinciaTitolare) {
		this.provinciaTitolare = provinciaTitolare;
	}

	public java.lang.String getComuneTitolare() {
		return comuneTitolare;
	}

	public void setComuneTitolare(java.lang.String comuneTitolare) {
		this.comuneTitolare = comuneTitolare;
	}

	public java.util.Calendar getDataNascitaGestore() {
		return dataNascitaGestore;
	}

	public void setDataNascitaGestore(java.util.Calendar dataNascitaGestore) {
		this.dataNascitaGestore = dataNascitaGestore;
	}

	public java.lang.String getProvinciaGestore() {
		return provinciaGestore;
	}

	public void setProvinciaGestore(java.lang.String provinciaGestore) {
		this.provinciaGestore = provinciaGestore;
	}

	public java.lang.String getComuneGestore() {
		return comuneGestore;
	}

	public void setComuneGestore(java.lang.String comuneGestore) {
		this.comuneGestore = comuneGestore;
	}
	
	public java.lang.String getCodFiscaleGestore() {
		return codFiscaleGestore;
	}

	public void setCodFiscaleGestore(java.lang.String codFiscaleGestore) {
		this.codFiscaleGestore = codFiscaleGestore;
	}

	public java.lang.String getAnagraficaGestore() {
		return anagraficaGestore;
	}

	public void setAnagraficaGestore(java.lang.String anagraficaGestore) {
		this.anagraficaGestore = anagraficaGestore;
	}

	public java.lang.String getPinCodeMail() {
		return pinCodeMail;
	}

	public java.lang.String getPinCodeMailPec() {
		return pinCodeMailPec;
	}

	public java.lang.String getFlagPinCodeMail() {
		return flagPinCodeMail;
	}

	public java.lang.String getFlagPinCodeMailPec() {
		return flagPinCodeMailPec;
	}

	public void setPinCodeMail(java.lang.String pinCodeMail) {
		this.pinCodeMail = pinCodeMail;
	}

	public void setPinCodeMailPec(java.lang.String pinCodeMailPec) {
		this.pinCodeMailPec = pinCodeMailPec;
	}

	public void setFlagPinCodeMail(java.lang.String flagPinCodeMail) {
		this.flagPinCodeMail = flagPinCodeMail;
	}

	public void setFlagPinCodeMailPec(java.lang.String flagPinCodeMailPec) {
		this.flagPinCodeMailPec = flagPinCodeMailPec;
	}

	public java.lang.String getFlagPrincipale() {
		return flagPrincipale;
	}

	public void setFlagPrincipale(java.lang.String flagPrincipale) {
		this.flagPrincipale = flagPrincipale;
	}

	public java.lang.String getComuneCatastale() {
		return comuneCatastale;
	}

	public java.lang.String getParticellaEdificiale() {
		return particellaEdificiale;
	}

	public java.lang.String getSubalterno() {
		return subalterno;
	}

	public BigDecimal getSuperficie() {
		return superficie;
	}

	public Integer getPostiLetto() {
		return postiLetto;
	}

	public String getPiano() {
		return piano;
	}

	public void setComuneCatastale(java.lang.String comuneCatastale) {
		this.comuneCatastale = comuneCatastale;
	}

	public void setParticellaEdificiale(java.lang.String particellaEdificiale) {
		this.particellaEdificiale = particellaEdificiale;
	}

	public void setSubalterno(java.lang.String subalterno) {
		this.subalterno = subalterno;
	}

	public void setSuperficie(BigDecimal superficie) {
		this.superficie = superficie;
	}

	public void setPostiLetto(Integer postiLetto) {
		this.postiLetto = postiLetto;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

	public Integer getNumAlloggiTotali() {
		return numAlloggiTotali;
	}

	public Integer getNumAlloggiAttivi() {
		return numAlloggiAttivi;
	}

	public String getStatoComunicazione() {
		return statoComunicazione;
	}

	public void setNumAlloggiTotali(Integer numAlloggiTotali) {
		this.numAlloggiTotali = numAlloggiTotali;
	}

	public void setNumAlloggiAttivi(Integer numAlloggiAttivi) {
		this.numAlloggiAttivi = numAlloggiAttivi;
	}

	public void setStatoComunicazione(String statoComunicazione) {
		this.statoComunicazione = statoComunicazione;
	}

	
	//PG200390 GG - inizio
	public String getCodiceIstat() {
		return codiceIstat;
	}

	public void setCodiceIstat(String codiceIstat) {
		this.codiceIstat = codiceIstat;
	}
	//PG200390 GG - fine
}
