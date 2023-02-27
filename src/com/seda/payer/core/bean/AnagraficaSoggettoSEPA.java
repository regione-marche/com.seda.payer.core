package com.seda.payer.core.bean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AnagraficaSoggettoSEPA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codiceUtente;	//DAS_CUTECUTE 
	private String codiceFiscale;	//DAS_CDASCFIS   
	private String stato;			//DAS_CDASSTAT   
	private String denominazione;	//DAS_DDASDENO   
	private String indirizzo;		//DAS_DDASINDI   
	private String cap;		//DAS_CDASCCAP   
	private String localita; 		//DAS_CDASCCAP   
	private String siglaProvincia;	//DAS_CDASPROV   
	private String telefono; //DAS_CDASNTEL   
	private String email;	//DAS_CDASMAIL   
	private String operatoreInserimento;	//DAS_CDASOPEI 
	private Date dataInserimento;		//DAS_GDASINSE   
	private String operatoreVariazione;	//DAS_CDASOPEV   
	private Date dataVariazione;		//DAS_GDASVARI   
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getStato() {
		return stato;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getCap() {
		return cap;
	}

	public String getLocalita() {
		return localita;
	}

	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public String getOperatoreInserimento() {
		return operatoreInserimento;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public String getOperatoreVariazione() {
		return operatoreVariazione;
	}

	public Date getDataVariazione() {
		return dataVariazione;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public void setSiglaProvincvia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOperatoreInserimento(String operatoreInserimento) {
		this.operatoreInserimento = operatoreInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public void setOperatoreVariazione(String operatoreVariazione) {
		this.operatoreVariazione = operatoreVariazione;
	}

	public void setDataVariazione(Date dataVariazione) {
		this.dataVariazione = dataVariazione;
	}
	
	
	


}
