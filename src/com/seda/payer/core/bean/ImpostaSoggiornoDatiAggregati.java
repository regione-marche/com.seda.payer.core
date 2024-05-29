package com.seda.payer.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImpostaSoggiornoDatiAggregati implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TestataComunicazioneImpostaSoggiorno testataComunicazione;
	private AnagraficaStrutturaRicettiva anagraficaStrutturaRicettiva;
	private TariffaImpostaSoggiorno tariffaImpostaSoggiorno;
	private TipologiaStrutturaRicettiva tipologiaStrutturaRicettiva;
	private List<DettaglioComunicazioneImpostaSoggiorno> listDettaglioComunicazione;
	
	public TestataComunicazioneImpostaSoggiorno getTestataComunicazione() {
		return testataComunicazione;
	}
	public void setTestataComunicazione(
			TestataComunicazioneImpostaSoggiorno testataComunicazione) {
		this.testataComunicazione = testataComunicazione;
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
	public List<DettaglioComunicazioneImpostaSoggiorno> getListDettaglioComunicazione() {
		return listDettaglioComunicazione;
	}
	public void setListDettaglioComunicazione(
			List<DettaglioComunicazioneImpostaSoggiorno> listDettaglioComunicazione) {
		this.listDettaglioComunicazione = listDettaglioComunicazione;
	}
	public void addDettaglioComunicazione(DettaglioComunicazioneImpostaSoggiorno dettaglioComunicazione) {
		if (listDettaglioComunicazione == null)
			listDettaglioComunicazione = new ArrayList<DettaglioComunicazioneImpostaSoggiorno>();
		listDettaglioComunicazione.add(dettaglioComunicazione);
	}

	@Override
	public String toString() {
		return "ImpostaSoggiornoDatiAggregati{" +
				"testataComunicazione=" + testataComunicazione.toString() +
				", anagraficaStrutturaRicettiva=" + anagraficaStrutturaRicettiva.toString() +
				", tariffaImpostaSoggiorno=" + tariffaImpostaSoggiorno.toString() +
				", tipologiaStrutturaRicettiva=" + tipologiaStrutturaRicettiva.toString() +
				", listDettaglioComunicazione=" + listDettaglioComunicazione.toString() +
				'}';
	}
}
