package com.seda.payer.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImpostaSoggiornoDiarioDatiAggregati implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TestataDiarioImpostaSoggiorno testataDiario;
	private AnagraficaStrutturaRicettiva anagraficaStrutturaRicettiva;
	private TariffaImpostaSoggiorno tariffaImpostaSoggiorno;
	private TipologiaStrutturaRicettiva tipologiaStrutturaRicettiva;
	private List<DettaglioDiarioImpostaSoggiorno> listDettaglioDiario;
	
	
	public void setTestataDiario(TestataDiarioImpostaSoggiorno testataDiario) {
		this.testataDiario = testataDiario;
	}
	public TestataDiarioImpostaSoggiorno getTestataDiario() {
		return testataDiario;
	}
	public void setAnagraficaStrutturaRicettiva(
			AnagraficaStrutturaRicettiva anagraficaStrutturaRicettiva) {
		this.anagraficaStrutturaRicettiva = anagraficaStrutturaRicettiva;
	}
	public AnagraficaStrutturaRicettiva getAnagraficaStrutturaRicettiva() {
		return anagraficaStrutturaRicettiva;
	}
	public TariffaImpostaSoggiorno getTariffaImpostaSoggiorno() {
		return tariffaImpostaSoggiorno;
	}
	public void setTariffaImpostaSoggiorno(
			TariffaImpostaSoggiorno tariffaImpostaSoggiorno) {
		this.tariffaImpostaSoggiorno = tariffaImpostaSoggiorno;
	}
	public TipologiaStrutturaRicettiva getTipologiaStrutturaRicettiva() {
		return tipologiaStrutturaRicettiva;
	}
	public void setTipologiaStrutturaRicettiva(
			TipologiaStrutturaRicettiva tipologiaStrutturaRicettiva) {
		this.tipologiaStrutturaRicettiva = tipologiaStrutturaRicettiva;
	}
	public void setListDettaglioDiario(List<DettaglioDiarioImpostaSoggiorno> listDettaglioDiario) {
		this.listDettaglioDiario = listDettaglioDiario;
	}
	public List<DettaglioDiarioImpostaSoggiorno> getListDettaglioDiario() {
		return listDettaglioDiario;
	}
	public void addDettaglioDiario(DettaglioDiarioImpostaSoggiorno dettaglioDiario) {
		if (listDettaglioDiario == null)
			listDettaglioDiario = new ArrayList<DettaglioDiarioImpostaSoggiorno>();
		listDettaglioDiario.add(dettaglioDiario);
	}
	

}
