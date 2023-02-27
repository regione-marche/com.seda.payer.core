package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class FlussoONERI extends ModelAttributes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String tipoRecord;
	private String dati;
	
	public FlussoONERI(){
	}


	public String getTipoRecord() {
		return tipoRecord;
	}


	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}


	public String getDati() {
		return dati;
	}


	public void setDati(String dati) {
		this.dati = dati;
	}


	@Override
	public String toString() {
		return "FlussoONERI [dati=" + dati + ", tipoRecord=" + tipoRecord + "]";
	}



}
