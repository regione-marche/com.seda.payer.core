package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class Rid extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codiceFunzione;
	private String codiceDebitore;
	private String codiceFiscale;
	private String cognomeOrRagSOc;
	private String codiceIBAN;
	private String idOperazione;
	private String nomeSottoscrittore;
	private String cognomeSottoscrittore;
	private String codFiscSottoscrittore;
	private String mailSottoscrittore;
	private String nomeIntestatario;
	private String cognomeIntestatario;
	private String codFiscIntestatario;
	private String userName;
	private String codiceEsitoRispostaS2S; // 1 o 0
	private String descrizioneEsitoRispostaS2S;
	
	private String codiceCausaleAEA;
	private String codiceRispostaS2SHTTP;
	private String descrizioneRispostaS2SHTTP;
	
	private Calendar dataInserimento;
	private Calendar dataAggiornamento;
	private String nomeOperatore;
	
	private String esitoCausaleAEA; 
	private String descrizioneeCausaleAEA;
	
    public Rid() { 
    }

    public Rid(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	this.codiceFunzione = data.getString("RID_CRIDFUNZ");
    	this.codiceDebitore = data.getString("RID_CRIDDEBI");
    	this.codiceFiscale = data.getString("RID_CRIDFISD");
    	this.cognomeOrRagSOc = data.getString("RID_DRIDDEBI");
    	this.codiceIBAN = data.getString("RID_CRIDIBAN");
    	this.idOperazione = data.getString("RID_CRIDOPER");
    	this.nomeSottoscrittore = data.getString("RID_DRIDNOME");
    	this.cognomeSottoscrittore = data.getString("RID_DRIDCOGN");
    	this.codFiscSottoscrittore = data.getString("RID_CRIDFISS");
    	this.mailSottoscrittore = data.getString("RID_ERIDMAIL");
    	this.nomeIntestatario = data.getString("RID_DRIDNOMI");
    	this.cognomeIntestatario = data.getString("RID_DRIDCOGI");
    	this.codFiscIntestatario = data.getString("RID_CRIDFISI");
    	this.userName = data.getString("RID_CRIDUSER");
    	this.codiceEsitoRispostaS2S = data.getString("RID_CRIDESIT"); // 1 o 0
    	this.descrizioneEsitoRispostaS2S = data.getString("RID_DRIDESIT");
    	
    	this.codiceCausaleAEA = data.getString("RID_CAEACODE");
    	this.codiceRispostaS2SHTTP = data.getString("RID_CRIDHTTP");
    	this.descrizioneRispostaS2SHTTP = data.getString("RID_DRIDHTTP");
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(data.getTimestamp("RID_GRIDINSE").getTime());
    	this.dataInserimento = cal;
    	
    	Calendar cal1 = Calendar.getInstance();
    	cal1.setTimeInMillis(data.getTimestamp("RID_GRIDGAGG").getTime());
    	this.dataAggiornamento = cal1;
    	
    	this.nomeOperatore = data.getString("RID_CRIDCOPE");
    	
    	// solo in GET
    	try {
    		this.esitoCausaleAEA = data.getString("AEA_CAEAESIT");
    		this.descrizioneeCausaleAEA = data.getString("AEA_DAEADESC");
    	} catch (Exception e) {}
    }
    
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
    	arg.setString(1, this.ConvertNull(this.codiceFunzione));
		arg.setString(2, this.codiceDebitore);
		arg.setString(3, this.ConvertNull(this.codiceFiscale));
		arg.setString(4, this.ConvertNull(this.cognomeOrRagSOc));  
		arg.setString(5, this.ConvertNull(this.codiceIBAN));
		arg.setString(6, this.ConvertNull(this.idOperazione));
		arg.setString(7, this.ConvertNull(this.nomeSottoscrittore));
		arg.setString(8, this.ConvertNull(this.cognomeSottoscrittore));
		arg.setString(9, this.ConvertNull(this.codFiscSottoscrittore));
		arg.setString(10, this.ConvertNull(this.mailSottoscrittore));
		arg.setString(11, this.ConvertNull(this.nomeIntestatario));
		arg.setString(12, this.ConvertNull(this.cognomeIntestatario));
		arg.setString(13, this.ConvertNull(this.codFiscIntestatario));
		arg.setString(14, this.ConvertNull(this.userName));
		arg.setString(15, this.ConvertNull(this.codiceEsitoRispostaS2S));
		arg.setString(16, this.ConvertNull(this.descrizioneEsitoRispostaS2S));
		arg.setString(17, this.ConvertNull(this.codiceCausaleAEA));
		arg.setString(18, this.ConvertNull(this.codiceRispostaS2SHTTP));
		arg.setString(19, this.ConvertNull(this.descrizioneRispostaS2SHTTP));
		arg.setString(20, this.ConvertNull(this.nomeOperatore));
		
		// solo in GET
		//dataInserimento 
		//this.dataAggiornamento 
    	//this.esitoCausaleAEA 
    	//this.descrizioneeCausaleAEA 
	}
	
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceFunzione);
		arg.setString(2, this.codiceDebitore);
		arg.setString(3, this.codiceFiscale);
		arg.setString(4, this.cognomeOrRagSOc);  
		arg.setString(5, this.codiceIBAN);
		arg.setString(6, this.idOperazione);
		arg.setString(7, this.nomeSottoscrittore);
		arg.setString(8, this.cognomeSottoscrittore);
		arg.setString(9, this.codFiscSottoscrittore);
		arg.setString(10, this.mailSottoscrittore);
		arg.setString(11, this.nomeIntestatario);
		arg.setString(12, this.cognomeIntestatario);
		arg.setString(13, this.codFiscIntestatario);
		arg.setString(14, this.userName);
		arg.setString(15, this.codiceEsitoRispostaS2S);
		arg.setString(16, this.descrizioneEsitoRispostaS2S);
		arg.setString(17, this.codiceCausaleAEA);
		arg.setString(18, this.codiceRispostaS2SHTTP);
		arg.setString(19, this.descrizioneRispostaS2SHTTP);
		arg.setString(20, this.nomeOperatore);
		
		// solo in GET
		// dataInserimento
		//this.dataAggiornamento 
    	//this.esitoCausaleAEA 
    	//this.descrizioneeCausaleAEA 
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	

	/**
	 * Converte il NULL in stringa vuota
	 * @param sTemp
	 * @return
	 */
	private String ConvertNull(String sTemp)
	{
		return sTemp != null ? sTemp : "";
	}
	
	public void setCodiceFunzione(String codiceFunzione) {
		this.codiceFunzione = codiceFunzione;
	}

	public String getCodiceFunzione() {
		return codiceFunzione;
	}

	public void setCodiceDebitore(String codiceDebitore) {
		this.codiceDebitore = codiceDebitore;
	}

	public String getCodiceDebitore() {
		return codiceDebitore;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCognomeOrRagSOc(String cognomeOrRagSOc) {
		this.cognomeOrRagSOc = cognomeOrRagSOc;
	}

	public String getCognomeOrRagSOc() {
		return cognomeOrRagSOc;
	}

	public void setCodiceIBAN(String codiceIBAN) {
		this.codiceIBAN = codiceIBAN;
	}

	public String getCodiceIBAN() {
		return codiceIBAN;
	}

	public void setIdOperazione(String idOperazione) {
		this.idOperazione = idOperazione;
	}

	public String getIdOperazione() {
		return idOperazione;
	}

	public void setNomeSottoscrittore(String nomeSottoscrittore) {
		this.nomeSottoscrittore = nomeSottoscrittore;
	}

	public String getNomeSottoscrittore() {
		return nomeSottoscrittore;
	}

	public void setCognomeSottoscrittore(String cognomeSottoscrittore) {
		this.cognomeSottoscrittore = cognomeSottoscrittore;
	}

	public String getCognomeSottoscrittore() {
		return cognomeSottoscrittore;
	}

	public void setCodFiscSottoscrittore(String codFiscSottoscrittore) {
		this.codFiscSottoscrittore = codFiscSottoscrittore;
	}

	public String getCodFiscSottoscrittore() {
		return codFiscSottoscrittore;
	}

	public void setMailSottoscrittore(String mailSottoscrittore) {
		this.mailSottoscrittore = mailSottoscrittore;
	}

	public String getMailSottoscrittore() {
		return mailSottoscrittore;
	}

	public void setNomeIntestatario(String nomeIntestatario) {
		this.nomeIntestatario = nomeIntestatario;
	}

	public String getNomeIntestatario() {
		return nomeIntestatario;
	}

	public void setCognomeIntestatario(String cognomeIntestatario) {
		this.cognomeIntestatario = cognomeIntestatario;
	}

	public String getCognomeIntestatario() {
		return cognomeIntestatario;
	}

	public void setCodFiscIntestatario(String codFiscIntestatario) {
		this.codFiscIntestatario = codFiscIntestatario;
	}

	public String getCodFiscIntestatario() {
		return codFiscIntestatario;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setCodiceEsitoRispostaS2S(String codiceEsitoRispostaS2S) {
		this.codiceEsitoRispostaS2S = codiceEsitoRispostaS2S;
	}

	public String getCodiceEsitoRispostaS2S() {
		return codiceEsitoRispostaS2S;
	}

	public void setDescrizioneEsitoRispostaS2S(
			String descrizioneEsitoRispostaS2S) {
		this.descrizioneEsitoRispostaS2S = descrizioneEsitoRispostaS2S;
	}

	public String getDescrizioneEsitoRispostaS2S() {
		return descrizioneEsitoRispostaS2S;
	}

	public void setCodiceCausaleAEA(String codiceCausaleAEA) {
		this.codiceCausaleAEA = codiceCausaleAEA;
	}

	public String getCodiceCausaleAEA() {
		return codiceCausaleAEA;
	}

	public void setCodiceRispostaS2SHTTP(String codiceRispostaS2SHTTP) {
		this.codiceRispostaS2SHTTP = codiceRispostaS2SHTTP;
	}

	public String getCodiceRispostaS2SHTTP() {
		return codiceRispostaS2SHTTP;
	}

	public void setDescrizioneRispostaS2SHTTP(String descrizioneRispostaS2SHTTP) {
		this.descrizioneRispostaS2SHTTP = descrizioneRispostaS2SHTTP;
	}

	public String getDescrizioneRispostaS2SHTTP() {
		return descrizioneRispostaS2SHTTP;
	}

	public Calendar getDataInserimento() {
		return dataInserimento;
	}

		public Calendar getDataAggiornamento() {
		return dataAggiornamento;
	}

	public String getNomeOperatore() {
		return nomeOperatore;
	}

	public void setNomeOperatore(String nomeOperatore) {
		this.nomeOperatore = nomeOperatore;
	}

	public String getEsitoCausaleAEA() {
		return esitoCausaleAEA;
	}

	public String getDescrizioneeCausaleAEA() {
		return descrizioneeCausaleAEA;
	}


	
}