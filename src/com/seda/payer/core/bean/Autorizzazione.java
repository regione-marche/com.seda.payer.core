package com.seda.payer.core.bean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Autorizzazione implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codiceUtente;	//DAU_CUTECUTE 
	private String codiceSia;	//DAU_CDCSCSIA 
	private String tipoAutorizzazione;	//DAU_CDAUTPAU 
	private String codiceAutorizzazione;	//DAU_CDAUCOAU 
	private String stato;	//DAU_FDAUSTAT 
	private String codiceFiscaleSottoscrittore;		//DAU_CDAUCFIS 
	private String codiceFiscaleIntestatario; 	//DAU_CDAUCFII 
	private String codicePaeseCcAccredito;	//DAU_CDAUCPCC 
	private BigDecimal codiceControlloCcAccredito; //DAU_CDAUCINT 
	private String codiceAbiCcAccredito;	//DAU_CDAUCABI 
	private String codiceCabCcAccredito;	//DAU_CDAUCCAB 
	private String numeroCcAccredito;	//DAU_CDAUCNUM 
	private String codiceCinCcAccredito;	//DAU_CDAUCCIN 
	private Date dataPrecedenteRevoca;		//DAU_GDAUPREV 
	private String canaleProvenienza;	//DAU_GDAUPREV 
	private String tipoProvenienza;		//DAU_CDAUTIPP 
	private String idDocumentoOrigine;		//DAU_CDAUDOCO 
	private Date dataInizioValidita;	//DAU_GDAUDINV 
	private Date dataFineValidita;		//DAU_GDAUDFNV 
	private Date dataRevoca; 	//DAU_GDAUREVO 
	private String tipoPagamento; 	//DAU_CDAUTPAG  
	private String codiceVoceIncasso;	//DAU_CDVIVOCI  
	private String idDebitore;	//DAU_CDAUIDEB 
	private String codiceAbiCCAllineamento;	//DAU_CDVCABIA 
	private String flgSospensione;		//DAU_FDAUSOSP 
	private Date dataSospensione;	//DAU_GDAUSOSP 
	private String operatoreSospensione;	//DAU_CDAUOPES 
	private Date dataRevocaSospensione;		//DAU_GDAUREVS 
	private String operatoreRevocaSospensione; 	//DAU_CDAUOPER 
	private String flgFacoltaRimborso;		//DAU_FDAUFRIM 
	private BigDecimal impPrefissato;		//DAU_IDAUIMPP 
	private String codiceBic;		//DAU_CDAUCBIC 		
	private String flgClassificazioneConto;		//DAU_FDAUCCON 
	private Date dataRicAllineamento;		//DAU_GDAUALLI 
	private String esitoRicAllineamento;		//DAU_FDAUFALL 
	private Date dataEsitoRicAllineamento;		//DAU_GDAUESIC 
	private String tipologiaMandato;	//DAU_CDAUTPMA
	private String descrizioneTipologiaMandato;		//DAU_CDAUDTMA 
	private String tipologiaIncasso;	//DAU_CDAUTPIN 
	private String operatoreInserimento;	//DAU_CDAUOPEI 
	private Date dataInserimento;	//DAU_GDAUINSE 
	private String operatoreVariazione;	//DAU_CDAUOPEV 
	private Date dataVariazione; 	//DAU_GDAUVARI 
	private Date dataSottoscrizione;	//DAU_GDAUSOTT 
	private String email;	//DAU_CDAUMAIL 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCodiceUtente() {
		return codiceUtente;
	}
	public String getCodiceSia() {
		return codiceSia;
	}
	public String getTipoAutorizzazione() {
		return tipoAutorizzazione;
	}
	public String getCodiceAutorizzazione() {
		return codiceAutorizzazione;
	}
	public String getStato() {
		return stato;
	}
	public String getCodiceFiscaleSottoscrittore() {
		return codiceFiscaleSottoscrittore;
	}
	public String getCodiceFiscaleIntestatario() {
		return codiceFiscaleIntestatario;
	}
	public String getCodicePaeseCcAccredito() {
		return codicePaeseCcAccredito;
	}
	public BigDecimal getCodiceControlloCcAccredito() {
		return codiceControlloCcAccredito;
	}
	public String getCodiceAbiCcAccredito() {
		return codiceAbiCcAccredito;
	}
	public String getCodiceCabCcAccredito() {
		return codiceCabCcAccredito;
	}
	public String getNumeroCcAccredito() {
		return numeroCcAccredito;
	}
	public String getCodiceCinCcAccredito() {
		return codiceCinCcAccredito;
	}
	public Date getDataPrecedenteRevoca() {
		return dataPrecedenteRevoca;
	}
	public String getCanaleProvenienza() {
		return canaleProvenienza;
	}
	public String getTipoProvenienza() {
		return tipoProvenienza;
	}
	public String getIdDocumentoOrigine() {
		return idDocumentoOrigine;
	}
	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}
	public Date getDataFineValidita() {
		return dataFineValidita;
	}
	public Date getDataRevoca() {
		return dataRevoca;
	}
	public String getTipoPagamento() {
		return tipoPagamento;
	}
	public String getCodiceVoceIncasso() {
		return codiceVoceIncasso;
	}
	public String getIdDebitore() {
		return idDebitore;
	}
	public String getCodiceAbiCCAllineamento() {
		return codiceAbiCCAllineamento;
	}
	public String getFlgSospensione() {
		return flgSospensione;
	}
	public Date getDataSospensione() {
		return dataSospensione;
	}
	public String getOperatoreSospensione() {
		return operatoreSospensione;
	}
	public Date getDataRevocaSospensione() {
		return dataRevocaSospensione;
	}
	public String getOperatoreRevocaSospensione() {
		return operatoreRevocaSospensione;
	}
	public String getFlgFacoltaRimborso() {
		return flgFacoltaRimborso;
	}
	public BigDecimal getImpPrefissato() {
		return impPrefissato;
	}
	public String getCodiceBic() {
		return codiceBic;
	}
	public String getFlgClassificazioneConto() {
		return flgClassificazioneConto;
	}
	public Date getDataRicAllineamento() {
		return dataRicAllineamento;
	}
	public String getEsitoRicAllineamento() {
		return esitoRicAllineamento;
	}
	public Date getDataEsitoRicAllineamento() {
		return dataEsitoRicAllineamento;
	}
	public String getTipologiaMandato() {
		return tipologiaMandato;
	}
	public String getDescrizioneTipologiaMandato() {
		return descrizioneTipologiaMandato;
	}
	public String getTipologiaIncasso() {
		return tipologiaIncasso;
	}
	public String getOperatoreInserimento() {
		return operatoreInserimento;
	}
	public Date getDataInserimento() {
		return dataInserimento;
	}
	public String getOperatoreVariazione() {
		return operatoreVariazione;
	}
	public Date getDataVariazione() {
		return dataVariazione;
	}
	public Date getDataSottoscrizione() {
		return dataSottoscrizione;
	}
	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}
	public void setCodiceSia(String codiceSia) {
		this.codiceSia = codiceSia;
	}
	public void setTipoAutorizzazione(String tipoAutorizzazione) {
		this.tipoAutorizzazione = tipoAutorizzazione;
	}
	public void setCodiceAutorizzazione(String codiceAutorizzazione) {
		this.codiceAutorizzazione = codiceAutorizzazione;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public void setCodiceFiscaleSottoscrittore(String codiceFiscaleSottoscrittore) {
		this.codiceFiscaleSottoscrittore = codiceFiscaleSottoscrittore;
	}
	public void setCodiceFiscaleIntestatario(String codiceFiscaleIntestatario) {
		this.codiceFiscaleIntestatario = codiceFiscaleIntestatario;
	}
	public void setCodicePaeseCcAccredito(String codicePaeseCcAccredito) {
		this.codicePaeseCcAccredito = codicePaeseCcAccredito;
	}
	public void setCodiceControlloCcAccredito(BigDecimal codiceControlloCcAccredito) {
		this.codiceControlloCcAccredito = codiceControlloCcAccredito;
	}
	public void setCodiceAbiCcAccredito(String codiceAbiCcAccredito) {
		this.codiceAbiCcAccredito = codiceAbiCcAccredito;
	}
	public void setCodiceCabCcAccredito(String codiceCabCcAccredito) {
		this.codiceCabCcAccredito = codiceCabCcAccredito;
	}
	public void setNumeroCcAccredito(String numeroCcAccredito) {
		this.numeroCcAccredito = numeroCcAccredito;
	}
	public void setCodiceCinCcAccredito(String codiceCinCcAccredito) {
		this.codiceCinCcAccredito = codiceCinCcAccredito;
	}
	public void setDataPrecedenteRevoca(Date dataPrecedenteRevoca) {
		this.dataPrecedenteRevoca = dataPrecedenteRevoca;
	}
	public void setCanaleProvenienza(String canaleProvenienza) {
		this.canaleProvenienza = canaleProvenienza;
	}
	public void setTipoProvenienza(String tipoProvenienza) {
		this.tipoProvenienza = tipoProvenienza;
	}
	public void setIdDocumentoOrigine(String idDocumentoOrigine) {
		this.idDocumentoOrigine = idDocumentoOrigine;
	}
	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}
	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}
	public void setDataRevoca(Date dataRevoca) {
		this.dataRevoca = dataRevoca;
	}
	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	public void setCodiceVoceIncasso(String codiceVoceIncasso) {
		this.codiceVoceIncasso = codiceVoceIncasso;
	}
	public void setIdDebitore(String idDebitore) {
		this.idDebitore = idDebitore;
	}
	public void setCodiceAbiCCAllineamento(String codiceAbiCCAllineamento) {
		this.codiceAbiCCAllineamento = codiceAbiCCAllineamento;
	}
	public void setFlgSospensione(String flgSospensione) {
		this.flgSospensione = flgSospensione;
	}
	public void setDataSospensione(Date dataSospensione) {
		this.dataSospensione = dataSospensione;
	}
	public void setOperatoreSospensione(String operatoreSospensione) {
		this.operatoreSospensione = operatoreSospensione;
	}
	public void setDataRevocaSospensione(Date dataRevocaSospensione) {
		this.dataRevocaSospensione = dataRevocaSospensione;
	}
	public void setOperatoreRevocaSospensione(String operatoreRevocaSospensione) {
		this.operatoreRevocaSospensione = operatoreRevocaSospensione;
	}
	public void setFlgFacoltaRimborso(String flgFacoltaRimborso) {
		this.flgFacoltaRimborso = flgFacoltaRimborso;
	}
	public void setImpPrefissato(BigDecimal impPrefissato) {
		this.impPrefissato = impPrefissato;
	}
	public void setCodiceBic(String codiceBic) {
		this.codiceBic = codiceBic;
	}
	public void setFlgClassificazioneConto(String flgClassificazioneConto) {
		this.flgClassificazioneConto = flgClassificazioneConto;
	}
	public void setDataRicAllineamento(Date dataRicAllineamento) {
		this.dataRicAllineamento = dataRicAllineamento;
	}
	public void setEsitoRicAllineamento(String esitoRicAllineamento) {
		this.esitoRicAllineamento = esitoRicAllineamento;
	}
	public void setDataEsitoRicAllineamento(Date dataEsitoRicAllineamento) {
		this.dataEsitoRicAllineamento = dataEsitoRicAllineamento;
	}
	public void setTipologiaMandato(String tipologiaMandato) {
		this.tipologiaMandato = tipologiaMandato;
	}
	public void setDescrizioneTipologiaMandato(String descrizioneTipologiaMandato) {
		this.descrizioneTipologiaMandato = descrizioneTipologiaMandato;
	}
	public void setTipologiaIncasso(String tipologiaIncasso) {
		this.tipologiaIncasso = tipologiaIncasso;
	}
	public void setOperatoreInserimento(String operatoreInserimento) {
		this.operatoreInserimento = operatoreInserimento;
	}
	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	public void setOperatoreVariazione(String operatoreVariazione) {
		this.operatoreVariazione = operatoreVariazione;
	}
	public void setDataVariazione(Date dataVariazione) {
		this.dataVariazione = dataVariazione;
	}
	public void setDataSottoscrizione(Date dataSottoscrizione) {
		this.dataSottoscrizione = dataSottoscrizione;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
