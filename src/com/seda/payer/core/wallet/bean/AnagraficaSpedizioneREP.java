package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;
public class AnagraficaSpedizioneREP extends ModelAttributes implements Serializable{

	/**
	 *    tipo record 21 tabella PYASPTB
	 */
	private static final long serialVersionUID = 1L;


	private String idWallet;
	private String numeroProgressivoDisposizione;
	private String denominazioneDichiarante;
	private String indirizzoDichiarante;
	private String capDichiarante;
	private String localitaDichiarante;
	private String provinciaDichiarante;
	private String krepkrep;
	
	public String getKrepkrep() {
		return krepkrep;
	}

	public void setKrepkrep(String krepkrep) {
		this.krepkrep = krepkrep;
	}

	public AnagraficaSpedizioneREP(){
		
	}
	
	public AnagraficaSpedizioneREP(
		String identificativoRecord,
		String idWallet,
		String numeroProgressivoDisposizione,
		String denominazioneDichiarante,
		String indirizzoDichiarante,
		String capDichiarante,
		String localitaDichiarante,
		String provinciaDichiarante){
		
		this.idWallet=idWallet;
		this.numeroProgressivoDisposizione=numeroProgressivoDisposizione;
		this.denominazioneDichiarante=denominazioneDichiarante;
		this.indirizzoDichiarante=indirizzoDichiarante;
		this.capDichiarante=capDichiarante;
		this.localitaDichiarante=localitaDichiarante;
		this.provinciaDichiarante=provinciaDichiarante;
		
	}

	

	public String getIdWallet() {
		return idWallet;
	}

	public void setIdWallet(String idWallet) {
		this.idWallet = idWallet;
	}

	public String getNumeroProgressivoDisposizione() {
		return numeroProgressivoDisposizione;
	}

	public void setNumeroProgressivoDisposizione(
			String numeroProgressivoDisposizione) {
		this.numeroProgressivoDisposizione = numeroProgressivoDisposizione;
	}

	public String getDenominazioneDichiarante() {
		return denominazioneDichiarante;
	}

	public void setDenominazioneDichiarante(String denominazioneDichiarante) {
		this.denominazioneDichiarante = denominazioneDichiarante;
	}

	public String getIndirizzoDichiarante() {
		return indirizzoDichiarante;
	}

	public void setIndirizzoDichiarante(String indirizzoDichiarante) {
		this.indirizzoDichiarante = indirizzoDichiarante;
	}

	public String getCapDichiarante() {
		return capDichiarante;
	}

	public void setCapDichiarante(String capDichiarante) {
		this.capDichiarante = capDichiarante;
	}

	public String getLocalitaDichiarante() {
		return localitaDichiarante;
	}

	public void setLocalitaDichiarante(String localitaDichiarante) {
		this.localitaDichiarante = localitaDichiarante;
	}

	public String getProvinciaDichiarante() {
		return provinciaDichiarante;
	}

	public void setProvinciaDichiarante(String provinciaDichiarante) {
		this.provinciaDichiarante = provinciaDichiarante;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AnagraficaSpedizioneREP [capDichiarante=" + capDichiarante
				+ ", denominazioneDichiarante=" + denominazioneDichiarante
				+ ", idWallet=" + idWallet + ",  indirizzoDichiarante="
				+ indirizzoDichiarante + ", localitaDichiarante="
				+ localitaDichiarante + ", numeroProgressivoDisposizione="
				+ numeroProgressivoDisposizione + ", provinciaDichiarante="
				+ provinciaDichiarante + "]";
	}
	
	
}
