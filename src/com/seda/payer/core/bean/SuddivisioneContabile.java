package com.seda.payer.core.bean;

import java.util.List;

public class SuddivisioneContabile {
	private static final long serialVersionUID = 1L;
	
	private List<DatiContabili> datiContabili;
	
	public SuddivisioneContabile() {
		
	}
	
	public SuddivisioneContabile(List<DatiContabili> datiContabili) {
		this.datiContabili = datiContabili;
	}

	public List<DatiContabili> getDatiContabili() {
		return datiContabili;
	}

	public void setDatiContabili(List<DatiContabili> datiContabili) {
		this.datiContabili = datiContabili;
	}

}
