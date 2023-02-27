package com.seda.payer.core.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.seda.payer.commons.bean.Lifecycle;
/**
 * @author aniello.labua
 */
public class DettaglioFlussoOttico extends Lifecycle implements Serializable { 

	private static final long serialVersionUID = 1L;

	private String chiaveDettaglioFlussoOttico;
	private String chiaveFlussoOttico;
	private String codiceSocieta;
	private String codiceUtente;
	private String codiceEnte;

	private String tipologiaDocumento;

	private String numeroDocumento;
	private int chiaveQuietanza;

	private String codiceBollettino;
	private String numeroQuietanza;
	private String chiaveRelata;

	private String nomeFileFisicoImg;

	private Timestamp dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	private int daPaginaPdf;
	private int aPaginaPdf;
	
	String codiceFiscaleDebitore;
	String codiceImpostaServizio;	//PG22XX01_GG1

	/**/
	public static final int DEFAULT_SCOPE = 0;
    public static final int PROCESS_FLOW_SCOPE = 1;
    private String pathImmagine;
	/**
	 * Default constructor
	 */
	public DettaglioFlussoOttico(){

	}

	/**
	 * 
	 * @param data
	 * @throws SQLException
	 */
	//public DettaglioFlussoOttico(ResultSet data) throws SQLException {
	public DettaglioFlussoOttico(ResultSet data, int scope) throws SQLException {
		if (data == null)
			return;
		this.chiaveDettaglioFlussoOttico = data.getString("DOT_KDOTKDOT");
		this.chiaveFlussoOttico = data.getString("DOT_KTOTKTOT");
		this.codiceSocieta = data.getString("DOT_CSOCCSOC");
		this.codiceUtente = data.getString("DOT_CUTECUTE");
		this.codiceEnte = data.getString("DOT_KANEKENT");	
		this.tipologiaDocumento = data.getString("DOT_CDOTCTIP");
		/*attenzione: errore tabella dot*/
		this.numeroDocumento = data.getString("DOT_NDOTNDOC");
		this.chiaveQuietanza = data.getInt("DOT_NDOTNQUI");		
		this.codiceBollettino = data.getString("DOT_CDOTCBOL");
		this.numeroQuietanza = data.getString("DOT_CDOTCQUI");
		this.chiaveRelata = data.getString("DOT_CDOTCREL");
		this.nomeFileFisicoImg = data.getString("DOT_DDOTDIMM");
		this.dataUltimoAggiornamento = data.getTimestamp("DOT_GDOTGAGG");
		this.operatoreUltimoAggiornamento = data.getString("DOT_CDOTCOPE");
		this.daPaginaPdf = data.getInt("DOT_NDOTPAGI");
		this.aPaginaPdf = data.getInt("DOT_NDOTPAGF");
		this.codiceFiscaleDebitore = data.getString("DOT_CDOTCFIS");
		this.codiceImpostaServizio = data.getString("DOT_CDOTCISE");	//PG22XX01_GG1

		if(scope == PROCESS_FLOW_SCOPE)
			this.pathImmagine = data.getString("CFO_CCFODIRO");
	}

	/**
	 * 
	 */
	public void onSave(CallableStatement arg) throws SQLException {
		
		arg.setString(1,this.chiaveDettaglioFlussoOttico);
		arg.setString(2, this.chiaveFlussoOttico);
		arg.setString(3, this.codiceSocieta);
		arg.setString(4, this.codiceUtente);	
		arg.setString(5, this.codiceEnte);		
		arg.setString(6, this.tipologiaDocumento);	
		arg.setString(7, this.numeroDocumento);	
		arg.setInt(8, this.chiaveQuietanza);
		arg.setString(9, this.codiceBollettino);
		arg.setString(10, this.numeroQuietanza);
		arg.setString(11, this.chiaveRelata);
		arg.setString(12, this.nomeFileFisicoImg);
		arg.setString(13, this.operatoreUltimoAggiornamento);
		arg.setInt(14, this.daPaginaPdf);
		arg.setInt(15, this.aPaginaPdf);
		arg.setString(16, this.codiceFiscaleDebitore);
		arg.setString(17, this.codiceImpostaServizio);
	}

	/**
	 * 
	 */
	public void onDelete(CallableStatement arg) throws SQLException {

	}

	/**
	 * 
	 */
	public void onLoad(CallableStatement arg) throws SQLException {
		
		arg.setString(1,this.chiaveDettaglioFlussoOttico);
		arg.setString(2, this.chiaveFlussoOttico);
		arg.setString(3, this.codiceSocieta);
		arg.setString(4, this.codiceUtente);	
		arg.setString(5, this.codiceEnte);		
		arg.setString(6, this.tipologiaDocumento);	
		arg.setString(7, this.numeroDocumento);	
		arg.setInt(8, this.chiaveQuietanza);
		arg.setString(9, this.codiceBollettino);
		arg.setString(10, this.numeroQuietanza);
		arg.setString(11, this.chiaveRelata);
		arg.setString(12, this.nomeFileFisicoImg);
		arg.setString(13, this.operatoreUltimoAggiornamento);
		arg.setInt(14, this.daPaginaPdf);
		arg.setInt(15, this.aPaginaPdf);
		arg.setString(16, this.codiceFiscaleDebitore);
		arg.setString(17, this.codiceImpostaServizio);	//PG22XX01_GG1
	}

	/**
	 */
	public void onUpdate(CallableStatement arg) throws SQLException {
	  arg.setString(1,this.chiaveDettaglioFlussoOttico);
//    arg.setString(2, this.chiaveFlussoOttico);
    arg.setString(2, this.codiceSocieta);
    arg.setString(3, this.codiceUtente);  
    arg.setString(4, this.codiceEnte);    
    arg.setString(5, this.tipologiaDocumento);  
    arg.setString(6, this.numeroDocumento); 
    arg.setInt(7, this.chiaveQuietanza);
    arg.setString(8, this.codiceBollettino);
    arg.setString(9, this.numeroQuietanza);
    arg.setString(10, this.chiaveRelata);
    arg.setString(11, this.nomeFileFisicoImg);
    arg.setDate(12, null);  // usa time di sitema
    arg.setString(13, this.operatoreUltimoAggiornamento);
    arg.setInt(14, this.daPaginaPdf);
    arg.setInt(15, this.aPaginaPdf);
    arg.setString(16, this.codiceFiscaleDebitore);
    arg.setString(17, this.codiceImpostaServizio);	//PG22XX01_GG1
	}
	public String getChiaveDettaglioFlussoOttico() {
		return chiaveDettaglioFlussoOttico;
	}

	public void setChiaveDettaglioFlussoOttico(String chiaveDettaglioFlussoOttico) {
		this.chiaveDettaglioFlussoOttico = chiaveDettaglioFlussoOttico;
	}

	public String getChiaveFlussoOttico() {
		return chiaveFlussoOttico;
	}

	public void setChiaveFlussoOttico(String chiaveFlussoOttico) {
		this.chiaveFlussoOttico = chiaveFlussoOttico;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getTipologiaDocumento() {
		return tipologiaDocumento;
	}

	public void setTipologiaDocumento(String tipologiaDocumento) {
		this.tipologiaDocumento = tipologiaDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public int getChiaveQuietanza() {
		return chiaveQuietanza;
	}

	public void setChiaveQuietanza(int chiaveQuietanza) {
		this.chiaveQuietanza = chiaveQuietanza;
	}

	public String getCodiceBollettino() {
		return codiceBollettino;
	}

	public void setCodiceBollettino(String codiceBollettino) {
		this.codiceBollettino = codiceBollettino;
	}

	public String getNumeroQuietanza() {
		return numeroQuietanza;
	}

	public void setNumeroQuietanza(String numeroQuietanza) {
		this.numeroQuietanza = numeroQuietanza;
	}

	public String getChiaveRelata() {
		return chiaveRelata;
	}

	public void setChiaveRelata(String chiaveRelata) {
		this.chiaveRelata = chiaveRelata;
	}

	public String getNomeFileFisicoImg() {
		return nomeFileFisicoImg;
	}

	public void setNomeFileFisicoImg(String nomeFileFisicoImg) {
		this.nomeFileFisicoImg = nomeFileFisicoImg;
	}

	public Timestamp getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Timestamp dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}
	
	public String getPathImmagine() {
		return pathImmagine;
	}

	public void setPathImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
	}

	public int getDaPaginaPdf() {
		return daPaginaPdf;
	}

	public void setDaPaginaPdf(int daPaginaPdf) {
		this.daPaginaPdf = daPaginaPdf;
	}

	public int getaPaginaPdf() {
		return aPaginaPdf;
	}

	public void setaPaginaPdf(int aPaginaPdf) {
		this.aPaginaPdf = aPaginaPdf;
	}

	/** Codice Fiscale del Debitore*/
  public void setCodiceFiscaleDebitore(String codiceFiscale) {
    this.codiceFiscaleDebitore = codiceFiscale;    
  }

  public String getCodicFiscaleDebitore() {
    return codiceFiscaleDebitore;
  }

  	//PG22XX01_GG1 - inizio
	/**
	 * @return the codiceImpostaServizio
	 */
	public String getCodiceImpostaServizio() {
		return codiceImpostaServizio;
	}
	
	/**
	 * @param codiceImpostaServizio the codiceImpostaServizio to set
	 */
	public void setCodiceImpostaServizio(String codiceImpostaServizio) {
		this.codiceImpostaServizio = codiceImpostaServizio;
	}
	//PG22XX01_GG1 - fine
  
}
