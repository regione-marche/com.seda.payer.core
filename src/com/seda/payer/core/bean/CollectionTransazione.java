package com.seda.payer.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.seda.payer.commons.transform.TransformersIf;

public class CollectionTransazione implements Serializable, TransformersIf {

	private static final long serialVersionUID = 1L;
	private List<TransazioneIci> listIci;
	private List<TransazioneIV> listIV;
	private List<TransazioneIV_Dett> listIV_Dett;
	private List<TransazioneFreccia> listFreccia;
	private List<Transazione> listTransazioni;	
	private Transazione transazione;                    

	
    

    public CollectionTransazione() {
    	this.transazione = new Transazione();
    	listIci = new ArrayList<TransazioneIci>();
    	listIV = new ArrayList<TransazioneIV>();
    	listIV_Dett = new ArrayList<TransazioneIV_Dett>();
    	listFreccia = new ArrayList<TransazioneFreccia>();
    	listTransazioni = new ArrayList<Transazione>();    	

    }
    
    
	public CollectionTransazione(List<TransazioneIci> listIci,
			List<TransazioneIV> listIV,List<TransazioneIV_Dett> listIV_Dett,
			List<TransazioneFreccia> listFreccia, Transazione transazione) {		
		this.listIci = listIci;
		this.listIV = listIV;
		this.listIV_Dett = listIV_Dett;
		this.listFreccia = listFreccia;
		this.transazione = transazione;
	}


	public List<Transazione> getListTransazioni() {
		return listTransazioni;
	}


	public void setListTransazioni(List<Transazione> listTransazioni) {
		this.listTransazioni = listTransazioni;
	}


	public List<TransazioneIci> getListIci() {
		return listIci;
	}


	public void setListIci(List<TransazioneIci> listIci) {
		this.listIci = listIci;
	}


	public List<TransazioneIV> getListIV() {
		return listIV;
	}


	public void setListIV(List<TransazioneIV> listIV) {
		this.listIV = listIV;
	}


	public List<TransazioneIV_Dett> getListIV_Dett() {
		return listIV_Dett;
	}


	public void setListIV_Dett(List<TransazioneIV_Dett> listIVDett) {
		listIV_Dett = listIVDett;
	}


	public List<TransazioneFreccia> getListFreccia() {
		return listFreccia;
	}


	public void setListFreccia(List<TransazioneFreccia> listFreccia) {
		this.listFreccia = listFreccia;
	}

	
	public Transazione getTransazione() {
		return transazione;
	}


	public void setTransazione(Transazione transazione) {
		this.transazione = transazione;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see com.seda.payer.commons.transform.TransformersIf#beanToBean(java.lang.Object)
	 */
	public Serializable beanToBean(Object arg0) throws Exception{		 
		return this;
	} 
}
