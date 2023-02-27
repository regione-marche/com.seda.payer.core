package com.seda.payer.core.mercato.bean;

import java.io.Serializable;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class ControlloTariffeResponse extends ModelAttributes implements Serializable{
	private Calendar dataMaxInizioValidita;
	private Calendar dataMinFineValidita;
	private boolean prenotato;
	
	public Calendar getDataMaxInizioValidita() {
		return dataMaxInizioValidita;
	}
	public void setDataMaxInizioValidita(Calendar dataMaxInizioValidita) {
		this.dataMaxInizioValidita = dataMaxInizioValidita;
	}
	public Calendar getDataMinFineValidita() {
		return dataMinFineValidita;
	}
	public void setDataMinFineValidita(Calendar dataMinFineValidita) {
		this.dataMinFineValidita = dataMinFineValidita;
	}
	public boolean isPrenotato() {
		return prenotato;
	}
	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}

}
