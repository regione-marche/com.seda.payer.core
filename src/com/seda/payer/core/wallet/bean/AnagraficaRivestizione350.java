package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class AnagraficaRivestizione350 extends ModelAttributes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codiceBelfiore;           
	private String dataFlusso;		 
	private String tipoRecord;		 
	private String tipoAnagrafica;		 
	private String codiceFiscale;		 
	private String cognomeNome;		 
	private String codiceBelfioreNascita;		 
	private String codiceIstatNascita;		 
	private String descrizioneComuneNascita;		 
	private String siglaAutomobilisticaNascita;		 
	private String dataDiNascita;		 
	private String sesso;		 
	private String indirizzo;		 
	private String denominazioneFrazione;		 
	private String cap;		 
	private String dataEmigrazione;		 
	private String codiceBelfioreEmmigrazione;		 
	private String codiceIstatStatoEstero;		 
	private String descrizioneComuneEmigrazione;		 
	private String siglaAutomobilisticaEmigrazione;		 
	private String dataDecesso;		 
	private String statoCivile;		 
	private String dataStatoCivile;		 
	private String identificativoDocumento;		 
	
	public AnagraficaRivestizione350(){
	}

	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}

	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}

	public String getDataFlusso() {
		return dataFlusso;
	}

	public void setDataFlusso(String dataFlusso) {
		this.dataFlusso = dataFlusso;
	}

	public String getTipoRecord() {
		return tipoRecord;
	}

	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}

	public String getTipoAnagrafica() {
		return tipoAnagrafica;
	}

	public void setTipoAnagrafica(String tipoAnagrafica) {
		this.tipoAnagrafica = tipoAnagrafica;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCognomeNome() {
		return cognomeNome;
	}

	public void setCognomeNome(String cognomeNome) {
		this.cognomeNome = cognomeNome;
	}

	public String getCodiceBelfioreNascita() {
		return codiceBelfioreNascita;
	}

	public void setCodiceBelfioreNascita(String codiceBelfioreNascita) {
		this.codiceBelfioreNascita = codiceBelfioreNascita;
	}

	public String getCodiceIstatNascita() {
		return codiceIstatNascita;
	}

	public void setCodiceIstatNascita(String codiceIstatNascita) {
		this.codiceIstatNascita = codiceIstatNascita;
	}

	public String getDescrizioneComuneNascita() {
		return descrizioneComuneNascita;
	}

	public void setDescrizioneComuneNascita(String descrizioneComuneNascita) {
		this.descrizioneComuneNascita = descrizioneComuneNascita;
	}

	public String getSiglaAutomobilisticaNascita() {
		return siglaAutomobilisticaNascita;
	}

	public void setSiglaAutomobilisticaNascita(String siglaAutomobilisticaNascita) {
		this.siglaAutomobilisticaNascita = siglaAutomobilisticaNascita;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getDenominazioneFrazione() {
		return denominazioneFrazione;
	}

	public void setDenominazioneFrazione(String denominazioneFrazione) {
		this.denominazioneFrazione = denominazioneFrazione;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getDataEmigrazione() {
		return dataEmigrazione;
	}

	public void setDataEmigrazione(String dataEmigrazione) {
		this.dataEmigrazione = dataEmigrazione;
	}

	public String getCodiceBelfioreEmmigrazione() {
		return codiceBelfioreEmmigrazione;
	}

	public void setCodiceBelfioreEmmigrazione(String codiceBelfioreEmmigrazione) {
		this.codiceBelfioreEmmigrazione = codiceBelfioreEmmigrazione;
	}

	public String getCodiceIstatStatoEstero() {
		return codiceIstatStatoEstero;
	}

	public void setCodiceIstatStatoEstero(String codiceIstatStatoEstero) {
		this.codiceIstatStatoEstero = codiceIstatStatoEstero;
	}

	public String getDescrizioneComuneEmigrazione() {
		return descrizioneComuneEmigrazione;
	}

	public void setDescrizioneComuneEmigrazione(String descrizioneComuneEmigrazione) {
		this.descrizioneComuneEmigrazione = descrizioneComuneEmigrazione;
	}

	public String getSiglaAutomobilisticaEmigrazione() {
		return siglaAutomobilisticaEmigrazione;
	}

	public void setSiglaAutomobilisticaEmigrazione(
			String siglaAutomobilisticaEmigrazione) {
		this.siglaAutomobilisticaEmigrazione = siglaAutomobilisticaEmigrazione;
	}

	public String getDataDecesso() {
		return dataDecesso;
	}

	public void setDataDecesso(String dataDecesso) {
		this.dataDecesso = dataDecesso;
	}

	public String getStatoCivile() {
		return statoCivile;
	}

	public void setStatoCivile(String statoCivile) {
		this.statoCivile = statoCivile;
	}

	public String getDataStatoCivile() {
		return dataStatoCivile;
	}

	public void setDataStatoCivile(String dataStatoCivile) {
		this.dataStatoCivile = dataStatoCivile;
	}

	public String getIdentificativoDocumento() {
		return identificativoDocumento;
	}

	public void setIdentificativoDocumento(String identificativoDocumento) {
		this.identificativoDocumento = identificativoDocumento;
	}

	@Override
	public String toString() {
		return "AnagraficaRivestizione350 [cap=" + cap + ", codiceBelfiore="
				+ codiceBelfiore + ", codiceBelfioreEmmigrazione="
				+ codiceBelfioreEmmigrazione + ", codiceBelfioreNascita="
				+ codiceBelfioreNascita + ", codiceFiscale=" + codiceFiscale
				+ ", codiceIstatNascita=" + codiceIstatNascita
				+ ", codiceIstatStatoEstero=" + codiceIstatStatoEstero
				+ ", cognomeNome=" + cognomeNome + ", dataDecesso="
				+ dataDecesso + ", dataDiNascita=" + dataDiNascita
				+ ", dataEmigrazione=" + dataEmigrazione + ", dataFlusso="
				+ dataFlusso + ", dataStatoCivile=" + dataStatoCivile
				+ ", denominazioneFrazione=" + denominazioneFrazione
				+ ", descrizioneComuneEmigrazione="
				+ descrizioneComuneEmigrazione + ", descrizioneComuneNascita="
				+ descrizioneComuneNascita + ", identificativoDocumento="
				+ identificativoDocumento + ", indirizzo=" + indirizzo
				+ ", sesso=" + sesso + ", siglaAutomobilisticaEmigrazione="
				+ siglaAutomobilisticaEmigrazione
				+ ", siglaAutomobilisticaNascita="
				+ siglaAutomobilisticaNascita + ", statoCivile=" + statoCivile
				+ ", tipoAnagrafica=" + tipoAnagrafica + ", tipoRecord="
				+ tipoRecord + "]";
	}
}
