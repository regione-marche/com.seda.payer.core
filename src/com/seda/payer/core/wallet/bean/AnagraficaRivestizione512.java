package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class AnagraficaRivestizione512 extends ModelAttributes implements Serializable, Comparable<AnagraficaRivestizione512> {

	private static final long serialVersionUID = 1L;
	
	private String chiave;
	private String stato;
	private String data;
	
	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public AnagraficaRivestizione512(){
	}

	@Override
	public String toString() {
		return "AnagraficaRivestizione512 [chiave=" + chiave + ", data=" + data
				+ ", stato=" + stato + "]";
	}

	public int compareTo(AnagraficaRivestizione512 arg0) {
		return this.chiave.compareTo(arg0.chiave);
	}
}
