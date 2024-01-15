package com.seda.payer.commons.inviaAvvisiForGeos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Debitore {
	/** Codice Fiscale; */
	public String codiceFiscale;
	
	/** Nome e Cognome o Reg.Sociale; */
	public String nomeCognRagSoc;
	
	/** Indirizzo; */
	public String indirizzo;
	
	/** Comune; */
	public String comune;
	
	/** Provincia; */
	public String provincia;
	
	/** Cap; */
	public String cap;
	
	/** Mail; */
	public String mail;
	
	/** PEC; */
	public String pec;
	
	/** Codice Fiscale; */
	public String codiceFiscaleAlternativo;
	
	/** idDominio; */
	public String idDominio;

	/** lista dei documenti*/
	@JsonIgnore
	public ArrayList<Documento> listaDocumenti = new ArrayList<Documento>();

	/** flusso parent */
	public Flusso flusso;

	public void addDocumento(Documento curDoc) {
		listaDocumenti.add(curDoc);
		curDoc.debitore = this;
		
	}
}
