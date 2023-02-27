package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class DettaglioBorsellinoPerServizio extends ModelAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descrizioneServizio;
	private String codiceServizioServizio;
	private String annoMeseDelCarico;
	private Integer ImportoCarico;
	private Integer ImportoPagato;
	private Integer ImportoResiduo;
	private Integer ImportoRendicontato;
	
	
	
	public DettaglioBorsellinoPerServizio(){		
	};
	
	public DettaglioBorsellinoPerServizio(
				String descrizioneServizio,
				String codiceServizioServizio,
				String annoMeseDelCarico
				)
		
	{
		this.descrizioneServizio = descrizioneServizio;		
		this.codiceServizioServizio = codiceServizioServizio;
		this.annoMeseDelCarico = annoMeseDelCarico;
				
	}

	public String getDescrizioneServizio() {
		return descrizioneServizio;
	}

	public void setDescrizioneServizio(String descrizioneServizio) {
		this.descrizioneServizio = descrizioneServizio;
	}

	public String getCodiceServizioServizio() {
		return codiceServizioServizio;
	}

	public void setCodiceServizioServizio(String codiceServizioServizio) {
		this.codiceServizioServizio = codiceServizioServizio;
	}

	public String getAnnoMeseDelCarico() {
		return annoMeseDelCarico;
	}

	public void setAnnoMeseDelCarico(String annoMeseDelCarico) {
		this.annoMeseDelCarico = annoMeseDelCarico;
	}

	public Integer getImportoCarico() {
		return ImportoCarico;
	}

	public void setImportoCarico(Integer importoCarico) {
		ImportoCarico = importoCarico;
	}

	public Integer getImportoPagato() {
		return ImportoPagato;
	}

	public void setImportoPagato(Integer importoPagato) {
		ImportoPagato = importoPagato;
	}

	public Integer getImportoResiduo() {
		return ImportoResiduo;
	}

	public void setImportoResiduo(Integer importoResiduo) {
		ImportoResiduo = importoResiduo;
	}

	public Integer getImportoRendicontato() {
		return ImportoRendicontato;
	}

	public void setImportoRendicontato(Integer importoRendicontato) {
		ImportoRendicontato = importoRendicontato;
	}

	@Override
	public String toString() {
		return "DettaglioBorsellinoPerServizio [ImportoCarico=" + ImportoCarico
				+ ", ImportoPagato=" + ImportoPagato + ", ImportoRendicontato="
				+ ImportoRendicontato + ", ImportoResiduo=" + ImportoResiduo
				+ ", annoMeseDelCarico=" + annoMeseDelCarico
				+ ", codiceServizioServizio=" + codiceServizioServizio
				+ ", descrizioneServizio=" + descrizioneServizio + "]";
	};
	
}
