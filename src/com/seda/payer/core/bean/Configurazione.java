package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;
/**
 * @author aniello.labua
 */
public class Configurazione extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_SCOPE = 0;
    public static final int PROCESS_FLOW_SCOPE = 1;

	private String codiceSocieta;
	private String codiceUtente;
	private String codiceEnte;
	
	private String flagVisualizzaDocumento;
	private String flagVisualizzaRelata;
	private String flagVisualizzaBollettino;
	private String flagVisualizzaQuietanza;
	
	private String flagWebServiceOttico;
	private String indirizzoServerOttico;
	private String userServerOttico;
	private String passwordServerOttico;
	private String indirizzoEmailAmministratore;
	
	private String directoryFlussiDatiOtticoInput;
	private String directorySalvataggoFlussiDatiOtticoInput;
	private String directoryFlussiImmaginiOtticoInput;
	private String directorySalvataggioFlussiImmaginiOtticoInput;
	private String directoryLogElaborazione;
	private String directoryImmaginiOtticoPerEstrattoConto;
	
	private String siglaProvincia;
	private String chiaveEnte;
	
	private Timestamp dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;

	/**
	 * Default constructor
	 */
	public Configurazione() {	}
	
	/**
	 * 
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceEnte
	 */
	public Configurazione(String codiceSocieta, String codiceUtente, String codiceEnte){
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.codiceEnte = codiceEnte;
	}
	
	/**
	 * 
	 * @param data
	 * @throws SQLException
	 */
	public Configurazione(ResultSet data, int scope) throws SQLException {
		if(data == null)
			return;

		this.codiceSocieta = data.getString("CFO_CSOCCSOC");
		this.codiceUtente = data.getString("CFO_CUTECUTE");
		
		if (scope == DEFAULT_SCOPE)
			this.codiceEnte = data.getString("CFO_KANEKENT");
		else if (scope == PROCESS_FLOW_SCOPE) {
			this.chiaveEnte = data.getString("CFO_KANEKENT");
			this.codiceEnte = data.getString("ANE_CANECENT");
		}

		this.dataUltimoAggiornamento = data.getTimestamp("CFO_GCFOGAGG");
		this.directoryFlussiDatiOtticoInput = data.getString("CFO_CCFODIRD");
		this.directoryFlussiImmaginiOtticoInput = data.getString("CFO_CCFODIRI");
		this.directoryImmaginiOtticoPerEstrattoConto = data.getString("CFO_CCFODIRO");
		this.directoryLogElaborazione = data.getString("CFO_CCFODIRL");
		this.directorySalvataggioFlussiImmaginiOtticoInput = data.getString("CFO_CCFODIRI_OLD");
		this.directorySalvataggoFlussiDatiOtticoInput = data.getString("CFO_CCFODIRD_OLD");
		this.flagVisualizzaBollettino = data.getString("CFO_FCFOFBOL");
		this.flagVisualizzaDocumento = data.getString("CFO_FCFOFDOC");
		this.flagVisualizzaQuietanza = data.getString("CFO_FCFOFQUI");
		this.flagVisualizzaRelata = data.getString("CFO_FCFOFREL");
		this.flagWebServiceOttico = data.getString("CFO_FCFOFLAG");
		this.indirizzoEmailAmministratore = data.getString("CFO_CCFOMAIL");
		this.indirizzoServerOttico = data.getString("CFO_CCFOSFTP");
		this.operatoreUltimoAggiornamento = data.getString("CFO_CCFOCOPE");
		this.passwordServerOttico = data.getString("CFO_CCFOPFTP");
		this.userServerOttico = data.getString("CFO_CCFOUFTP");

	}

	/**
	 * 
	 */
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);	
		arg.setString(3, this.codiceEnte);	
		arg.setString(4, this.flagVisualizzaDocumento);
		arg.setString(5, this.flagVisualizzaRelata);
		arg.setString(6, this.flagVisualizzaBollettino);	
		arg.setString(7, this.flagVisualizzaQuietanza);
		arg.setString(8, this.flagWebServiceOttico);
		arg.setString(9, this.indirizzoServerOttico);
		arg.setString(10, this.userServerOttico);
		arg.setString(11, this.passwordServerOttico);
		arg.setString(12, this.indirizzoEmailAmministratore);
		arg.setString(13, this.directoryFlussiDatiOtticoInput);
		arg.setString(14, this.directorySalvataggoFlussiDatiOtticoInput);
		arg.setString(15, this.directoryFlussiImmaginiOtticoInput);
		arg.setString(16, this.directorySalvataggioFlussiImmaginiOtticoInput);
		arg.setString(17, this.directoryLogElaborazione);
		arg.setString(18, this.directoryImmaginiOtticoPerEstrattoConto);
		arg.setTimestamp(19, this.dataUltimoAggiornamento);
		arg.setString(20, this.operatoreUltimoAggiornamento);
	}
	
	/**
	 * 
	 */
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);	
		arg.setString(3, this.codiceEnte);	
		arg.setString(4, this.flagVisualizzaDocumento);
		arg.setString(5, this.flagVisualizzaRelata);
		arg.setString(6, this.flagVisualizzaBollettino);	
		arg.setString(7, this.flagVisualizzaQuietanza);
		arg.setString(8, this.flagWebServiceOttico);
		arg.setString(9, this.indirizzoServerOttico);
		arg.setString(10, this.userServerOttico);
		arg.setString(11, this.passwordServerOttico);
		arg.setString(12, this.indirizzoEmailAmministratore);
		arg.setString(13, this.directoryFlussiDatiOtticoInput);
		arg.setString(14, this.directorySalvataggoFlussiDatiOtticoInput);
		arg.setString(15, this.directoryFlussiImmaginiOtticoInput);
		arg.setString(16, this.directorySalvataggioFlussiImmaginiOtticoInput);
		arg.setString(17, this.directoryLogElaborazione);
		arg.setString(18, this.directoryImmaginiOtticoPerEstrattoConto);
		arg.setString(19, this.operatoreUltimoAggiornamento);
	}
	public void onDelete(CallableStatement arg) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.codiceEnte);
	}

	/**
	 * 
	 */
	public void onLoad(CallableStatement arg) throws SQLException {
		
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.codiceEnte);
	}
	
	/**
	 * 
	 */
	public void onLoad(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException {
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.codiceEnte);
		arg.setString(4, this.siglaProvincia);
		arg.setString(5, this.flagWebServiceOttico);
		arg.setString(6, order);
		arg.setInt(7, rowsPerPage);
		arg.setInt(8, pageNumber);
		/* we register row start */
		arg.registerOutParameter(9, Types.INTEGER);
		/* we register row end */
		arg.registerOutParameter(10, Types.INTEGER);
		/* we register total rows */
		arg.registerOutParameter(11, Types.INTEGER);
		/* we register total pages */
		arg.registerOutParameter(12, Types.SMALLINT);
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

	public String getFlagVisualizzaDocumento() {
		return flagVisualizzaDocumento;
	}

	public void setFlagVisualizzaDocumento(String flagVisualizzaDocumento) {
		this.flagVisualizzaDocumento = flagVisualizzaDocumento;
	}

	public String getFlagVisualizzaRelata() {
		return flagVisualizzaRelata;
	}

	public void setFlagVisualizzaRelata(String flagVisualizzaRelata) {
		this.flagVisualizzaRelata = flagVisualizzaRelata;
	}

	public String getFlagVisualizzaBollettino() {
		return flagVisualizzaBollettino;
	}

	public void setFlagVisualizzaBollettino(String flagVisualizzaBollettino) {
		this.flagVisualizzaBollettino = flagVisualizzaBollettino;
	}

	public String getFlagVisualizzaQuietanza() {
		return flagVisualizzaQuietanza;
	}

	public void setFlagVisualizzaQuietanza(String flagVisualizzaQuietanza) {
		this.flagVisualizzaQuietanza = flagVisualizzaQuietanza;
	}

	public String getFlagWebServiceOttico() {
		return flagWebServiceOttico;
	}

	public void setFlagWebServiceOttico(String flagWebServiceOttico) {
		this.flagWebServiceOttico = flagWebServiceOttico;
	}

	public String getIndirizzoServerOttico() {
		return indirizzoServerOttico;
	}

	public void setIndirizzoServerOttico(String indirizzoServerOttico) {
		this.indirizzoServerOttico = indirizzoServerOttico;
	}

	public String getUserServerOttico() {
		return userServerOttico;
	}

	public void setUserServerOttico(String userServerOttico) {
		this.userServerOttico = userServerOttico;
	}

	public String getPasswordServerOttico() {
		return passwordServerOttico;
	}

	public void setPasswordServerOttico(String passwordServerOttico) {
		this.passwordServerOttico = passwordServerOttico;
	}

	public String getIndirizzoEmailAmministratore() {
		return indirizzoEmailAmministratore;
	}

	public void setIndirizzoEmailAmministratore(String indirizzoEmailAmministratore) {
		this.indirizzoEmailAmministratore = indirizzoEmailAmministratore;
	}

	public String getDirectoryFlussiDatiOtticoInput() {
		return directoryFlussiDatiOtticoInput;
	}

	public void setDirectoryFlussiDatiOtticoInput(
			String directoryFlussiDatiOtticoInput) {
		this.directoryFlussiDatiOtticoInput = directoryFlussiDatiOtticoInput;
	}

	public String getDirectorySalvataggoFlussiDatiOtticoInput() {
		return directorySalvataggoFlussiDatiOtticoInput;
	}

	public void setDirectorySalvataggoFlussiDatiOtticoInput(
			String directorySalvataggoFlussiDatiOtticoInput) {
		this.directorySalvataggoFlussiDatiOtticoInput = directorySalvataggoFlussiDatiOtticoInput;
	}

	public String getDirectoryFlussiImmaginiOtticoInput() {
		return directoryFlussiImmaginiOtticoInput;
	}

	public void setDirectoryFlussiImmaginiOtticoInput(
			String directoryFlussiImmaginiOtticoInput) {
		this.directoryFlussiImmaginiOtticoInput = directoryFlussiImmaginiOtticoInput;
	}

	public String getDirectorySalvataggioFlussiImmaginiOtticoInput() {
		return directorySalvataggioFlussiImmaginiOtticoInput;
	}

	public void setDirectorySalvataggioFlussiImmaginiOtticoInput(
			String directorySalvataggioFlussiImmaginiOtticoInput) {
		this.directorySalvataggioFlussiImmaginiOtticoInput = directorySalvataggioFlussiImmaginiOtticoInput;
	}

	public String getDirectoryLogElaborazione() {
		return directoryLogElaborazione;
	}

	public void setDirectoryLogElaborazione(String directoryLogElaborazione) {
		this.directoryLogElaborazione = directoryLogElaborazione;
	}

	public String getDirectoryImmaginiOtticoPerEstrattoConto() {
		return directoryImmaginiOtticoPerEstrattoConto;
	}

	public void setDirectoryImmaginiOtticoPerEstrattoConto(
			String directoryImmaginiOtticoPerEstrattoConto) {
		this.directoryImmaginiOtticoPerEstrattoConto = directoryImmaginiOtticoPerEstrattoConto;
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

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public String toString() {
		return "Configurazione [codiceEnte=" + codiceEnte + ", codiceSocieta="
		+ codiceSocieta + ", codiceUtente=" + codiceUtente
		+ ", dataUltimoAggiornamento=" + dataUltimoAggiornamento
		+ ", directoryFlussiDatiOtticoInput="
		+ directoryFlussiDatiOtticoInput
		+ ", directoryFlussiImmaginiOtticoInput="
		+ directoryFlussiImmaginiOtticoInput
		+ ", directoryImmaginiOtticoPerEstrattoConto="
		+ directoryImmaginiOtticoPerEstrattoConto
		+ ", directoryLogElaborazione=" + directoryLogElaborazione
		+ ", directorySalvataggioFlussiImmaginiOtticoInput="
		+ directorySalvataggioFlussiImmaginiOtticoInput
		+ ", directorySalvataggoFlussiDatiOtticoInput="
		+ directorySalvataggoFlussiDatiOtticoInput
		+ ", flagVisualizzaBollettino=" + flagVisualizzaBollettino
		+ ", flagVisualizzaDocumento=" + flagVisualizzaDocumento
		+ ", flagVisualizzaQuietanza=" + flagVisualizzaQuietanza
		+ ", flagVisualizzaRelata=" + flagVisualizzaRelata
		+ ", flagWebServiceOttico=" + flagWebServiceOttico
		+ ", indirizzoEmailAmministratore="
		+ indirizzoEmailAmministratore + ", indirizzoServerOttico="
		+ indirizzoServerOttico + ", operatoreUltimoAggiornamento="
		+ operatoreUltimoAggiornamento + ", passwordServerOttico="
		+ passwordServerOttico + ", userServerOttico="
		+ userServerOttico + "]";
	}
	
}