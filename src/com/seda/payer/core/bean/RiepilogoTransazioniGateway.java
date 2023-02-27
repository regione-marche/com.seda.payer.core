
package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.util.List;

public class RiepilogoTransazioniGateway {
	
	List<TransazioniGrouped> listaTransazioni;
	String listaTransazioniXml;
	BigDecimal totale;
	
	public RiepilogoTransazioniGateway(List<TransazioniGrouped> listaTransazioni,String listaTransazioniXml, BigDecimal totale) {
		this.listaTransazioni = listaTransazioni;
		this.listaTransazioniXml = listaTransazioniXml;
		this.totale = totale;
	}

	public List<TransazioniGrouped> getListaTransazioni() {
		return listaTransazioni;
	}

	public String getListaTransazioniXml() {
		return listaTransazioniXml;
	}

	public BigDecimal getTotale() {
		return totale;
	}

}
