package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class AnagraficaRivestizione500 extends ModelAttributes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String identificativoContribuente;
	private String tipoAnagrafica;
	private String cfPiva;
	private String cognome;
	private String nome;
	private String sesso;
	private String dataNascita;
	private String comuneNascita;
	private String provinciaNascita;
	private String denominazione;
	private String indirizzo;
	private String comune;
	private String provincia;
	private String cap;
	
	public AnagraficaRivestizione500(){
	}

	public String getIdentificativoContribuente() {
		return identificativoContribuente;
	}

	public void setIdentificativoContribuente(String identificativoContribuente) {
		this.identificativoContribuente = identificativoContribuente;
	}

	public String getTipoAnagrafica() {
		return tipoAnagrafica;
	}

	public void setTipoAnagrafica(String tipoAnagrafica) {
		this.tipoAnagrafica = tipoAnagrafica;
	}

	public String getCfPiva() {
		return cfPiva;
	}

	public void setCfPiva(String cfPiva) {
		this.cfPiva = cfPiva;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public String getProvinciaNascita() {
		return provinciaNascita;
	}

	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	@Override
	public String toString() {
		return "AnagraficaRivestizione500 [cap=" + cap + ", cfPiva=" + cfPiva
				+ ", cognome=" + cognome + ", comune=" + comune
				+ ", comuneNascita=" + comuneNascita + ", dataNascita="
				+ dataNascita + ", denominazione=" + denominazione
				+ ", identificativoContribuente=" + identificativoContribuente
				+ ", indirizzo=" + indirizzo + ", nome=" + nome
				+ ", provincia=" + provincia + ", provinciaNascita="
				+ provinciaNascita + ", sesso=" + sesso + ", tipoAnagrafica="
				+ tipoAnagrafica + "]";
	}
}
