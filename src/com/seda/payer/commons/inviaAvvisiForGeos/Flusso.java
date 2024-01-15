package com.seda.payer.commons.inviaAvvisiForGeos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** IDFlusso è un "raggruppamento di pendenze" caricate nel DB, tramite l'invio di un file o tramite webservice. <br/>
 * Una caratteristica associata a questo IDFlusso è il flag "stampa attiva". <br/>
 * Il raggruppamento delle pendenze nel DB è "Flusso/Debitore/Docuemnto/AvvisoRata". <br/>
 * 
 */
public class Flusso {
	/** Esempio utente="val d'aosta" */
	public String cutecute;

	public String idFlusso;

	public String societa;

	public String ccp;

	public String ccpIntest;

	public String type;
	
	public String impServ;  //SB 19042019
	
	public String cbill;  //SB 19042019

	public String codiceAutorizzazione;  //SB 19042019
	
	public int progressivoBollettino;
	/**
	 * ogni flusso estratto dal DB c'ha una lista di debitori/documenti/avvisi
	 */

	@JsonIgnore
	public ArrayList<Debitore> listaDebitori = new ArrayList<Debitore>();

	public void addDebitore(Debitore curDeb) {
		listaDebitori.add(curDeb);
		curDeb.flusso = this;
		
	}
}
