package com.seda.payer.core.bean;

import java.io.Serializable;

public class TransazioneDocumentoEC implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Transazione transazione;                                
    private TransazioneIV transazioneIV;     
    private TransazioneFreccia transazioneFreccia;

    public TransazioneDocumentoEC() {    	
    }
    
    public TransazioneDocumentoEC(Transazione transazione, TransazioneIV transazioneIV) {
    	setTransazione(transazione);
    	setTransazioneIV(transazioneIV);
    	setTransazioneFreccia(null);
    }
    
    public TransazioneDocumentoEC(Transazione transazione, TransazioneFreccia transazioneFreccia) {
    	setTransazione(transazione);
    	setTransazioneFreccia(transazioneFreccia);
    	setTransazioneIV(null);
    }

	public void setTransazione(Transazione transazione) {
		this.transazione = transazione;
	}

	public Transazione getTransazione() {
		return transazione;
	}

	public void setTransazioneIV(TransazioneIV transazioneIV) {
		this.transazioneIV = transazioneIV;
	}

	public TransazioneIV getTransazioneIV() {
		return transazioneIV;
	}

	public void setTransazioneFreccia(TransazioneFreccia transazioneFreccia) {
		this.transazioneFreccia = transazioneFreccia;
	}

	public TransazioneFreccia getTransazioneFreccia() {
		return transazioneFreccia;
	}





	
}