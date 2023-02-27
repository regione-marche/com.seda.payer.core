package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;
/**
 * @author aniello.labua
 */
public class TestataFlussoOttico extends Lifecycle implements Serializable{

	private static final long serialVersionUID = 1L;
	private String chiaveFlusso;
	private String codiceSocieta;
	private String codiceUtente;
	private String codiceEnte;

	private Date dataElaborazione;
	private Date dataCreazioneFlusso;
	private String tipologiaDocumento;
	private String statoDocumento; //PG22XX09_YL5

	private int numeroDettagli;
	private int numeroDettagliCaricati;
	private int numeroDettagliScartati;

	private String nomeFileFisicoImg;
	private String nomeFileLog;
	private String nomeFileLogDettaglio;
	private String nomeFileDati;

	private String siglaProvincia;
	
	private Timestamp dataElabDA;
	private Timestamp dataElabA;
	private Timestamp dataCreazDA;
	private Timestamp dataCreazA;
	
	private Timestamp dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	
	private String nomeFileFisicoZip; //PG190360

	/**
	 * Default constructor
	 */
	public TestataFlussoOttico() {
		super();
	}

	public TestataFlussoOttico(String codiceSocieta, String codiceUtente, String codiceEnte, String nomeFileDati){
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.codiceEnte = codiceEnte;
		this.nomeFileDati =  nomeFileDati;
	}
	/**
	 * 
	 * @param data
	 * @throws SQLException
	 */
	public TestataFlussoOttico(ResultSet data)throws SQLException{

		if(data == null){
			return;
		}

		this.chiaveFlusso = data.getString("TOT_KTOTKTOT");
		this.codiceSocieta = data.getString("TOT_CSOCCSOC");
		this.codiceUtente = data.getString("TOT_CUTECUTE");
		this.codiceEnte = data.getString("TOT_KANEKENT");
		this.dataElaborazione = data.getDate("TOT_GTOTGDAT");
		this.dataCreazioneFlusso = data.getDate("TOT_GTOTGCRE");
		this.tipologiaDocumento = data.getString("TOT_CTOTCTIP");
		this.numeroDettagli = data.getInt("TOT_NTOTNREC");;
		this.numeroDettagliCaricati = data.getInt("TOT_NTOTNROK");
		this.numeroDettagliScartati = data.getInt("TOT_NTOTNRKO");
		this.nomeFileFisicoImg = data.getString("TOT_DTOTFILE");
		this.nomeFileLog = data.getString("TOT_DTOTLOGT");
		this.nomeFileLogDettaglio = data.getString("TOT_DTOTLOGD");
		this.nomeFileDati = data.getString("TOT_CTOTNOME");
		this.dataUltimoAggiornamento = data.getTimestamp("TOT_GTOTGAGG");
		this.operatoreUltimoAggiornamento = data.getString("TOT_CTOTCOPE");
		this.nomeFileFisicoZip = data.getString("TOT_DZIPFILE"); //PG190360
	}

	/**
	 * 
	 */
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveFlusso);
		arg.setString(2, this.codiceSocieta);
		arg.setString(3, this.codiceUtente);	
		arg.setString(4, this.codiceEnte);	
		arg.setDate(5, this.dataCreazioneFlusso);
		arg.setString(6, this.tipologiaDocumento);	
		arg.setInt(7, this.numeroDettagli);
		arg.setInt(8, this.numeroDettagliCaricati);
		arg.setInt(9, this.numeroDettagliScartati);
		arg.setString(10, this.nomeFileFisicoImg);
		arg.setString(11, this.nomeFileLog);
		arg.setString(12, this.nomeFileLogDettaglio);
		arg.setString(13, this.nomeFileDati);
		arg.setString(14, this.operatoreUltimoAggiornamento);
	}

	/** Update parziale (di alcuni campi).
	 */
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveFlusso);
		arg.setString(2, this.codiceSocieta);
		arg.setString(3, this.codiceUtente);	
		arg.setString(4, this.codiceEnte);	
		arg.setDate(5, null); // data inserimento
		
    arg.setDate(6, null); // data creazione
		arg.setString(7, this.tipologiaDocumento);	

		// NOTA: non vengono aggiornati passo "null"
//		arg.setInt(8, this.numeroDettagli);
//		arg.setInt(9, this.numeroDettagliCaricati);
//		arg.setInt(10, this.numeroDettagliScartati);
    arg.setNull(8, Types.INTEGER);
    arg.setNull(9, Types.INTEGER);
    arg.setNull(10, Types.INTEGER);
		
		arg.setString(11, this.nomeFileFisicoImg);
		arg.setString(12, this.nomeFileLog);
		arg.setString(13, this.nomeFileLogDettaglio);
		arg.setString(14, this.nomeFileDati);
    arg.setTimestamp(15, null); // data aggiornamento
		
		arg.setString(16, this.operatoreUltimoAggiornamento);
		arg.setString(17, this.nomeFileFisicoZip); //PG190360
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
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.codiceEnte);
		arg.setString(4, this.nomeFileDati);
	}

	/**
	 * 
	 */
	public void onLoad(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.siglaProvincia);
		arg.setString(4, this.codiceEnte);
		
		if (this.dataElabDA != null)
			arg.setString(5, new java.sql.Date(this.dataElabDA.getTime()).toString());
		else arg.setNull(5, Types.VARCHAR);
		
		if (this.dataElabA != null)
			arg.setString(6, new java.sql.Date(this.dataElabA.getTime()).toString());
		else arg.setNull(6, Types.VARCHAR);
		
		if (this.dataCreazDA != null)
			arg.setString(7, new java.sql.Date(this.dataCreazDA.getTime()).toString());
		else arg.setNull(7, Types.VARCHAR);

		if (this.dataCreazA != null)
			arg.setString(8, new java.sql.Date(this.dataCreazA.getTime()).toString());
		else arg.setNull(8, Types.VARCHAR);
		
		arg.setString(9, this.tipologiaDocumento);
		arg.setString(10, this.statoDocumento); //PG22XX09_YL5
		/**/
		arg.setString(11, order);
		arg.setInt(12, rowsPerPage);
		arg.setInt(13, pageNumber);
		/* we register row start */
		arg.registerOutParameter(14, Types.INTEGER);
		/* we register row end */
		arg.registerOutParameter(15, Types.INTEGER);
		/* we register total rows */
		arg.registerOutParameter(16, Types.INTEGER);
		/* we register total pages */
		arg.registerOutParameter(17, Types.SMALLINT);
	}
	
	public void setChiaveFlusso(String chiaveFlusso) {
		this.chiaveFlusso = chiaveFlusso;
	}

	public String getChiaveFlusso() {
		return chiaveFlusso;
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

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String chiaveEnte) {
		this.codiceEnte = chiaveEnte;
	}

	public Date getDataElaborazione() {
		return dataElaborazione;
	}

	public void setDataElaborazione(Date dataElaborazione) {
		this.dataElaborazione = dataElaborazione;
	}

	public Date getDataCreazioneFlusso() {
		return dataCreazioneFlusso;
	}

	public void setDataCreazioneFlusso(Date dataCreazioneFlusso) {
		this.dataCreazioneFlusso = dataCreazioneFlusso;
	}

	public String getTipologiaDocumento() {
		return tipologiaDocumento;
	}

	public void setTipologiaDocumento(String tipologiaDocumento) {
		this.tipologiaDocumento = tipologiaDocumento;
	}

	public int getNumeroDettagli() {
		return numeroDettagli;
	}

	public void setNumeroDettagli(int numeroDettagli) {
		this.numeroDettagli = numeroDettagli;
	}

	public int getNumeroDettagliCaricati() {
		return numeroDettagliCaricati;
	}

	public void setNumeroDettagliCaricati(int numeroDettagliCaricati) {
		this.numeroDettagliCaricati = numeroDettagliCaricati;
	}

	public int getNumeroDettagliScartati() {
		return numeroDettagliScartati;
	}

	public void setNumeroDettagliScartati(int numeroDettagliScartati) {
		this.numeroDettagliScartati = numeroDettagliScartati;
	}

	public String getNomeFileFisicoImg() {
		return nomeFileFisicoImg;
	}

	public void setNomeFileFisicoImg(String nomeFileFisicoImg) {
		this.nomeFileFisicoImg = nomeFileFisicoImg;
	}

	public String getNomeFileLog() {
		return nomeFileLog;
	}

	public void setNomeFileLog(String nomeFileLog) {
		this.nomeFileLog = nomeFileLog;
	}

	public String getNomeFileLogDettaglio() {
		return nomeFileLogDettaglio;
	}

	public void setNomeFileLogDettaglio(String nomeFileLogDettaglio) {
		this.nomeFileLogDettaglio = nomeFileLogDettaglio;
	}

	public String getNomeFileDati() {
		return nomeFileDati;
	}

	public void setNomeFileDati(String nomeFileDati) {
		this.nomeFileDati = nomeFileDati;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public Timestamp getDataElabDA() {
		return dataElabDA;
	}

	public void setDataElabDA(Timestamp dataElabDA) {
		this.dataElabDA = dataElabDA;
	}

	public Timestamp getDataElabA() {
		return dataElabA;
	}

	public void setDataElabA(Timestamp dataElabA) {
		this.dataElabA = dataElabA;
	}

	public Timestamp getDataCreazDA() {
		return dataCreazDA;
	}

	public void setDataCreazDA(Timestamp dataCreazDA) {
		this.dataCreazDA = dataCreazDA;
	}

	public Timestamp getDataCreazA() {
		return dataCreazA;
	}

	public void setDataCreazA(Timestamp dataCreazA) {
		this.dataCreazA = dataCreazA;
	}

	public Timestamp getDataUltimoAgg() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAgg(Timestamp dataUltimoAgg) {
		this.dataUltimoAggiornamento = dataUltimoAgg;
	}

	public String getOperatoreUltimoAgg() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAgg(String operatoreUltimoAgg) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAgg;
	}
	
	//PG190360
	public String getNomeFileFisicoZip() {
		return nomeFileFisicoZip;
	}

	public void setNomeFileFisicoZip(String nomeFileFisicoZip) {
		this.nomeFileFisicoZip = nomeFileFisicoZip;
	}
	//FINE PG190360
	// INI PG22XX09_YL5
	/**
	 * @return the statoDocumento
	 */
	public String getStatoDocumento() {
		return statoDocumento;
	}

	/**
	 * @param statoDocumento the statoDocumento to set
	 */
	public void setStatoDocumento(String statoDocumento) {
		this.statoDocumento = statoDocumento;
	}
	//FINE PG22XX09_YL5
	
}
