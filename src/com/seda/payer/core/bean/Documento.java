/**
 * Documento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.seda.payer.core.bean;

import java.util.ArrayList;

public class Documento  implements java.io.Serializable {
    private java.lang.String numeroDocumento;
    private java.lang.String annoEmissione;
    private java.lang.String numeroEmissione;
    private java.lang.String dataNotifica;
    private java.lang.String numeroBollettinoPagoPA;
    private java.math.BigDecimal impBollettinoTotaleDocumento;
    private java.lang.String ibanAccredito;
    private java.lang.String ibanAppoggio;
    private java.lang.String flagFatturazioneElettronica;
    private java.lang.String identificativoUnivocoVersamento;
    private java.lang.String codImpostaServizio;
    private java.lang.String descImpostaServizio;
    private java.lang.String codTipologiaServizio;
    private java.lang.String descTipologiaServizio;
    private ArrayList<Scadenza> scad = new ArrayList<Scadenza>(); 

    //inizio LP PG210130
    private ArrayList<DettaglioPagamento> dettPag = new ArrayList<DettaglioPagamento>(); 
    private ArrayList<DettaglioContabile> dettCont = new ArrayList<DettaglioContabile>(); 
    //fine LP PG210130

    private java.lang.String anaFiscale;
    private java.lang.String anaDenom;
    private java.lang.String anaTipoAnag;
    private java.lang.String anBelfNascita;
    private java.lang.String anaDataNascita;
    private java.lang.String anaStato;
    private java.lang.String anaIndirizzo;
    private java.lang.String anaFiscaleAlt;
    private java.lang.String anaMail;
    private java.lang.String anaMailPec;
    private java.lang.String provinciaNascita;
    private java.lang.String provinciaFiscale;

    private java.lang.String codEnte;
    private java.lang.String flagGenerazioneIUV;
    private java.lang.String flagStampaAvviso;
//  23.04.2018 INIZIO - MARINI: aggikunti campi mancanti da visualizzare in jsp 
    private java.lang.String idDominio;
    private java.lang.String auxDigit;
    private java.lang.String applCode;
    private java.lang.String segrCode;
//  23.04.2018 FINE
    // PG200140 - inizio
    private java.lang.String carattServizio;
    // PG200140 - fine
    //PRE_JAVA1.8 SB - inizio
    private java.lang.String causale;
    //PRE_JAVA1.8 SB - fine
    private String tassonomia; //PG200360 LP
    
    private java.lang.String nomeFlusso;

    public Documento() {
    }

    public java.lang.String getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(java.lang.String idDominio) {
		this.idDominio = idDominio;
	}

	public java.lang.String getAuxDigit() {
		return auxDigit;
	}

	public void setAuxDigit(java.lang.String auxDigit) {
		this.auxDigit = auxDigit;
	}

	public java.lang.String getApplCode() {
		return applCode;
	}

	public void setApplCode(java.lang.String applCode) {
		this.applCode = applCode;
	}

	public java.lang.String getSegrCode() {
		return segrCode;
	}

	public void setSegrCode(java.lang.String segrCode) {
		this.segrCode = segrCode;
	}

	public ArrayList<Scadenza> getScad() {
      return scad;
    }

    public void setScad(ArrayList<Scadenza> scad) {
      this.scad = scad;
    }

    //inizio LP PG210130
    public ArrayList<DettaglioPagamento> getDettPag() {
		return dettPag;
	}

	public void setDettPag(ArrayList<DettaglioPagamento> dettPag) {
		this.dettPag = dettPag;
	}

	public ArrayList<DettaglioContabile> getDettCont() {
		return dettCont;
	}

	public void setDettCont(ArrayList<DettaglioContabile> dettCont) {
		this.dettCont = dettCont;
	}
    //fine LP PG210130

	public java.lang.String getNumeroDocumento() {
      return numeroDocumento;
    }

    public void setNumeroDocumento(java.lang.String numeroDocumento) {
      this.numeroDocumento = numeroDocumento;
    }

    public java.lang.String getAnnoEmissione() {
      return annoEmissione;
    }

    public void setAnnoEmissione(java.lang.String annoEmissione) {
      this.annoEmissione = annoEmissione;
    }

    public java.lang.String getNumeroEmissione() {
      return numeroEmissione;
    }

    public void setNumeroEmissione(java.lang.String numeroEmissione) {
      this.numeroEmissione = numeroEmissione;
    }

    public java.lang.String getDataNotifica() {
      return dataNotifica;
    }

    public void setDataNotifica(java.lang.String dataNotifica) {
      this.dataNotifica = dataNotifica;
    }

    public java.lang.String getNumeroBollettinoPagoPA() {
      return numeroBollettinoPagoPA;
    }

    public void setNumeroBollettinoPagoPA(java.lang.String numeroBollettinoPagoPA) {
      this.numeroBollettinoPagoPA = numeroBollettinoPagoPA;
    }

    public java.math.BigDecimal getImpBollettinoTotaleDocumento() {
      return impBollettinoTotaleDocumento;
    }

    public void setImpBollettinoTotaleDocumento(java.math.BigDecimal impBollettinoTotaleDocumento) {
      this.impBollettinoTotaleDocumento = impBollettinoTotaleDocumento;
    }

    public java.lang.String getIbanAccredito() {
      return ibanAccredito;
    }

    public void setIbanAccredito(java.lang.String ibanAccredito) {
      this.ibanAccredito = ibanAccredito;
    }

    public java.lang.String getFlagFatturazioneElettronica() {
      return flagFatturazioneElettronica;
    }

    public void setFlagFatturazioneElettronica(java.lang.String flagFatturazioneElettronica) {
      this.flagFatturazioneElettronica = flagFatturazioneElettronica;
    }

    public java.lang.String getIdentificativoUnivocoVersamento() {
      return identificativoUnivocoVersamento;
    }

    public void setIdentificativoUnivocoVersamento(java.lang.String identificativoUnivocoVersamento) {
      this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
    }

    public java.lang.String getCodImpostaServizio() {
      return codImpostaServizio;
    }

    public void setCodImpostaServizio(java.lang.String codImpostaServizio) {
      this.codImpostaServizio = codImpostaServizio;
    }

    public java.lang.String getDescImpostaServizio() {
      return descImpostaServizio;
    }

    public void setDescImpostaServizio(java.lang.String descImpostaServizio) {
      this.descImpostaServizio = descImpostaServizio;
    }

    public java.lang.String getCodTipologiaServizio() {
      return codTipologiaServizio;
    }

    public void setCodTipologiaServizio(java.lang.String codTipologiaServizio) {
      this.codTipologiaServizio = codTipologiaServizio;
    }

    public java.lang.String getDescTipologiaServizio() {
      return descTipologiaServizio;
    }

    public void setDescTipologiaServizio(java.lang.String descTipologiaServizio) {
      this.descTipologiaServizio = descTipologiaServizio;
    }

	public java.lang.String getAnaFiscale() {
		return anaFiscale;
	}

	public void setAnaFiscale(java.lang.String anaFiscale) {
		this.anaFiscale = anaFiscale;
	}

	public java.lang.String getAnaDenom() {
		return anaDenom;
	}

	public void setAnaDenom(java.lang.String anaDenom) {
		this.anaDenom = anaDenom;
	}

	public java.lang.String getAnaTipoAnag() {
		return anaTipoAnag;
	}

	public void setAnaTipoAnag(java.lang.String anaTipoAnag) {
		this.anaTipoAnag = anaTipoAnag;
	}

	public java.lang.String getAnBelfNascita() {
		return anBelfNascita;
	}

	public void setAnBelfNascita(java.lang.String anBelfNascita) {
		this.anBelfNascita = anBelfNascita;
	}

	public java.lang.String getAnaDataNascita() {
		return anaDataNascita;
	}

	public void setAnaDataNascita(java.lang.String anaDataNascita) {
		this.anaDataNascita = anaDataNascita;
	}

	public java.lang.String getAnaStato() {
		return anaStato;
	}

	public void setAnaStato(java.lang.String anaStato) {
		this.anaStato = anaStato;
	}

	public java.lang.String getAnaIndirizzo() {
		return anaIndirizzo;
	}

	public void setAnaIndirizzo(java.lang.String anaIndirizzo) {
		this.anaIndirizzo = anaIndirizzo;
	}

	public java.lang.String getAnaFiscaleAlt() {
		return anaFiscaleAlt;
	}

	public void setAnaFiscaleAlt(java.lang.String anaFiscaleAlt) {
		this.anaFiscaleAlt = anaFiscaleAlt;
	}

	public java.lang.String getAnaMail() {
		return anaMail;
	}

	public void setAnaMail(java.lang.String anaMail) {
		this.anaMail = anaMail;
	}

	public java.lang.String getAnaMailPec() {
		return anaMailPec;
	}

	public void setAnaMailPec(java.lang.String anaMailPec) {
		this.anaMailPec = anaMailPec;
	}

	public java.lang.String getProvinciaNascita() {
		return provinciaNascita;
	}

	public void setProvinciaNascita(java.lang.String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	public java.lang.String getProvinciaFiscale() {
		return provinciaFiscale;
	}

	public void setProvinciaFiscale(java.lang.String provinciaFiscale) {
		this.provinciaFiscale = provinciaFiscale;
	}

	public java.lang.String getCodEnte() {
		return codEnte;
	}

	public void setCodEnte(java.lang.String codUtenteEnte) {
		this.codEnte = codUtenteEnte;
	}

	public java.lang.String getFlagGenerazioneIUV() {
		return flagGenerazioneIUV;
	}

	public void setFlagGenerazioneIUV(java.lang.String flagGenerazioneIUV) {
		this.flagGenerazioneIUV = flagGenerazioneIUV;
	}

	public java.lang.String getFlagStampaAvviso() {
		return flagStampaAvviso;
	}

	public void setFlagStampaAvviso(java.lang.String flagStampaAvviso) {
		this.flagStampaAvviso = flagStampaAvviso;
	}

	public void setCarattServizio(java.lang.String carattServizio) {
		this.carattServizio = carattServizio;
	}

	public java.lang.String getCarattServizio() {
		return carattServizio;
	}

	public void setIbanAppoggio(java.lang.String ibanAppoggio) {
		this.ibanAppoggio = ibanAppoggio;
	}

	public java.lang.String getIbanAppoggio() {
		return ibanAppoggio;
	}

	public java.lang.String getCausale() {
		return causale;
	}

	public void setCausale(java.lang.String causale) {
		this.causale = causale;
	}

	public java.lang.String getNomeFlusso() {
		return nomeFlusso;
	}

	public void setNomeFlusso(java.lang.String nomeFlusso) {
		this.nomeFlusso = nomeFlusso;
	}
	//inizio LP PG200360
	public String getTassonomia() {
		return tassonomia;
	}

	public void setTassonomia(String tassonomia) {
		this.tassonomia = tassonomia;
	}
	//fine LP PG200360
}
