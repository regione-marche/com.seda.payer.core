package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;


import com.seda.payer.commons.bean.Lifecycle;
import com.seda.payer.commons.transform.TransformersIf;

public class TransazioneIci extends Lifecycle implements Serializable, TransformersIf {

	private static final long serialVersionUID = 1L;
	                

	private String chiaveTransazioneIci;                           
	private String chiaveTransazioneGenerale;                      
	private String codiceSocieta;                                  
	private String codiceUtente;                                   
	private String chiaveEnte;                                     
	private String codiceTipologiaServizio;                        
	private String codiceImpostaServizio;                          
	private String codiceServizio;                                 
	private String tipoBollettino;                                 
	private String numeroContoCorrente;                            
	private String descrizioneIntestatarioContoCorrente;           
	private String annoRegistrazione;                              
	private String numeroRegistrazione;                            
	private String codiceFiscale;                                  
	private String denominazione;                                  
	private String domicilioFiscale;                               
	private String indirizzoDomicilioFiscale;                      
	private String flagResidenzaEstero;                            
	private BigDecimal importoMovimento;                           
	private String valuta;                                         
	private BigDecimal importoTerreniAgricoli;                     
	private BigDecimal importoAreeFabbricabili;                    
	private BigDecimal importoAbitazionePrincipale;                
	private BigDecimal importoAltriFabbricati;                     
	private BigDecimal importoDetrazioneComunale;                  
	private BigDecimal importoDetrazioneStatale;                   
	private String comuneDiUbicazioneImmobile;                     
	private String capComuneUbicazioneImmobile;  
	private String codiceProvinciaComuneUbicazioneImmobile;
	private String annoImposta;                                    
	private String numeroFabbricati;                               
	private String flagRata;                                       
	private String numeroProvvedimento;                            
	private java.util.Date dataProvvedimento;                                
	private String flagRavvedimento;                               
	private String chiaveSpedizione;                               
	private String invioSistemaInformativoCentrale;                
	private BigDecimal imponibileIciPerIscop;                      
	private BigDecimal riduzionePerIscop;                          
	private BigDecimal detrazionePerIscop;
	private BigDecimal impostaIciSanzioni;
	private BigDecimal sopratassaIciSanzioni;
	private BigDecimal penaPecuniariaIciSanzioni;
	private BigDecimal interessiIciSanzioni;
	private Long chiavePagamentoScartato;
	private java.util.Date dataultimoAggiornamento;                          
	private String opertoreUltimoAggiornamento;                    
	private String descrizioneBollettino;                    
	//PG150180_001 GG - inizio
	private java.lang.String nodoSpcIuv;
	private java.lang.String nodoSpcRpt;
	private java.lang.String nodoSpcRptFirma;
	private java.lang.String nodoSpcRptEsito;
	private java.lang.String nodoSpcRt;
	private java.lang.String nodoSpcRtFirma;
	private java.lang.String nodoSpcRtEsito;
	//PG150180_001 GG - fine
    //inizio LP PG190220
    private java.lang.String nodoSpcRtAnnullata;
    private java.lang.String nodoSpcRtAnnullataEsito;
    private java.lang.String nodoSpcRr;
    private java.lang.String nodoSpcEr;
    private java.lang.String nodoSpcErEsito;
    private java.lang.String nodoSpcEsitoInvioRevocaEmailAdmin;
    private java.util.Date nodoSpcDataInvioRevocaEmailAdmin;
    private java.lang.String nodoSpcEsitoInvioRevocaEmailContribuente;
    private java.util.Date nodoSpcDataInvioRevocaEmailContribuente;
    //fine LP PG190220
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
	private int progBollettino;
	private java.lang.String codiceIUR;
	private Long progQuadratura;
//PG170300 - 30/1/2018 - FINE
	//inizio LP PG200360
	private java.lang.String tassonomia;
	//fine LP PG200360
	

	public TransazioneIci() {    	
    }
    public TransazioneIci(String chiaveTransazioneIci,
			String chiaveTransazioneGenerale, String codiceSocieta,
			String codiceUtente, String chiaveEnte,
			String codiceTipologiaServizio, String codiceImpostaServizio,
			String codiceServizio, String tipoBollettino,
			String numeroContoCorrente,
			String descrizioneIntestatarioContoCorrente,
			String annoRegistrazione, String numeroRegistrazione,
			String codiceFiscale, String denominazione,
			String domicilioFiscale, String indirizzoDomicilioFiscale,
			String flagResidenzaEstero, BigDecimal importoMovimento,
			String valuta, BigDecimal importoTerreniAgricoli,
			BigDecimal importoAreeFabbricabili,
			BigDecimal importoAbitazionePrincipale,
			BigDecimal importoAltriFabbricati,
			BigDecimal importoDetrazioneComunale,
			BigDecimal importoDetrazioneStatale,
			String comuneDiUbicazioneImmobile,
			String capComuneUbicazioneImmobile, 
			String codiceProvinciaComuneUbicazioneImmobile,
			String annoImposta,
			String numeroFabbricati, String flagRata,
			String numeroProvvedimento, Date dataProvvedimento,
			String flagRavvedimento, String chiaveSpedizione,
			String invioSistemaInformativoCentrale,
			BigDecimal imponibileIciPerIscop, BigDecimal riduzionePerIscop,
			BigDecimal detrazionePerIscop,
			BigDecimal impostaIciSanzioni, BigDecimal sopratassaIciSanzioni,
			BigDecimal penaPecuniariaIciSanzioni, BigDecimal interessiIciSanzioni,
			Long chiavePagamentoScartato,
			Date dataultimoAggiornamento,
			String opertoreUltimoAggiornamento, String descrizioneBollettino,
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
			int progBollettino,
			String codiceIUR,
			Long progQuadratura
			//PG170300 - 30/1/2018 - FINE
			, String tassonomia //LP PG200360
    		) {
		
		this.chiaveTransazioneIci = chiaveTransazioneIci;
		this.chiaveTransazioneGenerale = chiaveTransazioneGenerale;
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnte = chiaveEnte;
		this.codiceTipologiaServizio = codiceTipologiaServizio;
		this.codiceImpostaServizio = codiceImpostaServizio;
		this.codiceServizio = codiceServizio;
		this.tipoBollettino = tipoBollettino;
		this.numeroContoCorrente = numeroContoCorrente;
		this.descrizioneIntestatarioContoCorrente = descrizioneIntestatarioContoCorrente;
		this.annoRegistrazione = annoRegistrazione;
		this.numeroRegistrazione = numeroRegistrazione;
		this.codiceFiscale = codiceFiscale;
		this.denominazione = denominazione;
		this.domicilioFiscale = domicilioFiscale;
		this.indirizzoDomicilioFiscale = indirizzoDomicilioFiscale;
		this.flagResidenzaEstero = flagResidenzaEstero;
		this.importoMovimento = importoMovimento;
		this.valuta = valuta;
		this.importoTerreniAgricoli = importoTerreniAgricoli;
		this.importoAreeFabbricabili = importoAreeFabbricabili;
		this.importoAbitazionePrincipale = importoAbitazionePrincipale;
		this.importoAltriFabbricati = importoAltriFabbricati;
		this.importoDetrazioneComunale = importoDetrazioneComunale;
		this.importoDetrazioneStatale = importoDetrazioneStatale;
		this.comuneDiUbicazioneImmobile = comuneDiUbicazioneImmobile;
		this.capComuneUbicazioneImmobile = capComuneUbicazioneImmobile;
		this.codiceProvinciaComuneUbicazioneImmobile = codiceProvinciaComuneUbicazioneImmobile;
		this.annoImposta = annoImposta;
		this.numeroFabbricati = numeroFabbricati;
		this.flagRata = flagRata;
		this.numeroProvvedimento = numeroProvvedimento;
		this.dataProvvedimento = dataProvvedimento;
		this.flagRavvedimento = flagRavvedimento;
		this.chiaveSpedizione = chiaveSpedizione;
		this.invioSistemaInformativoCentrale = invioSistemaInformativoCentrale;
		this.imponibileIciPerIscop = imponibileIciPerIscop;
		this.riduzionePerIscop = riduzionePerIscop;
		this.detrazionePerIscop = detrazionePerIscop;
		this.impostaIciSanzioni = impostaIciSanzioni;
		this.sopratassaIciSanzioni = sopratassaIciSanzioni;
		this.penaPecuniariaIciSanzioni = penaPecuniariaIciSanzioni;
		this.interessiIciSanzioni = interessiIciSanzioni;
		this.chiavePagamentoScartato = chiavePagamentoScartato;
		this.dataultimoAggiornamento = dataultimoAggiornamento;
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
		this.descrizioneBollettino = descrizioneBollettino;
		
		//PG150180_001 GG - inizio
		this.nodoSpcIuv = "";
		this.nodoSpcRpt = "";
		this.nodoSpcRptFirma = "";
		this.nodoSpcRptEsito = "";
		this.nodoSpcRt = "";
		this.nodoSpcRtFirma="";
		this.nodoSpcRtEsito = "";
		//PG150180_001 GG - fine
	    //inizio LP PG190220
		this.nodoSpcRtAnnullata = "";
		this.nodoSpcRtAnnullataEsito = "";
		this.nodoSpcRr = "";
		this.nodoSpcEr = "";
		this.nodoSpcErEsito = "";
		this.nodoSpcEsitoInvioRevocaEmailAdmin = "";
		this.nodoSpcDataInvioRevocaEmailAdmin = null;
		this.nodoSpcEsitoInvioRevocaEmailContribuente = "";
		this.nodoSpcDataInvioRevocaEmailContribuente = null;
	    //fine LP PG190220
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
		this.progBollettino = progBollettino;
		this.codiceIUR = codiceIUR;
		this.progQuadratura = progQuadratura;
//PG170300 - 30/1/2018 - FINE
		//inizio LP PG200360
		this.tassonomia = tassonomia;
		//fine LP PG200360

	}


	public String getChiaveTransazioneIci() {
		return chiaveTransazioneIci;
	}


	public void setChiaveTransazioneIci(String chiaveTransazioneIci) {
		this.chiaveTransazioneIci = chiaveTransazioneIci;
	}


	public String getChiaveTransazioneGenerale() {
		return chiaveTransazioneGenerale;
	}


	public void setChiaveTransazioneGenerale(
			String chiaveTransazioneGenerale) {
		this.chiaveTransazioneGenerale = chiaveTransazioneGenerale;
	}


	public String getCodiceSocieta() {
		return codiceSocieta;
	}


	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}


	public String getCodiceUtente() {
		return codiceUtente;
	}


	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}


	public String getChiaveEnte() {
		return chiaveEnte;
	}


	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}


	public String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}


	public void setCodiceTipologiaServizio(String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}


	public String getCodiceImpostaServizio() {
		return codiceImpostaServizio;
	}


	public void setCodiceImpostaServizio(String codiceImpostaServizio) {
		this.codiceImpostaServizio = codiceImpostaServizio;
	}


	public String getCodiceServizio() {
		return codiceServizio;
	}


	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}


	public String getTipoBollettino() {
		return tipoBollettino;
	}


	public void setTipoBollettino(String tipoBollettino) {
		this.tipoBollettino = tipoBollettino;
	}


	public String getNumeroContoCorrente() {
		return numeroContoCorrente;
	}


	public void setNumeroContoCorrente(String numeroContoCorrente) {
		this.numeroContoCorrente = numeroContoCorrente;
	}


	public String getDescrizioneIntestatarioContoCorrente() {
		return descrizioneIntestatarioContoCorrente;
	}


	public void setDescrizioneIntestatarioContoCorrente(
			String descrizioneIntestatarioContoCorrente) {
		this.descrizioneIntestatarioContoCorrente = descrizioneIntestatarioContoCorrente;
	}


	public String getAnnoRegistrazione() {
		return annoRegistrazione;
	}


	public void setAnnoRegistrazione(String annoRegistrazione) {
		this.annoRegistrazione = annoRegistrazione;
	}


	public String getNumeroRegistrazione() {
		return numeroRegistrazione;
	}


	public void setNumeroRegistrazione(String numeroRegistrazione) {
		this.numeroRegistrazione = numeroRegistrazione;
	}


	public String getCodiceFiscale() {
		return codiceFiscale;
	}


	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


	public String getDenominazione() {
		return denominazione;
	}


	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}


	public String getDomicilioFiscale() {
		return domicilioFiscale;
	}


	public void setDomicilioFiscale(String domicilioFiscale) {
		this.domicilioFiscale = domicilioFiscale;
	}


	public String getIndirizzoDomicilioFiscale() {
		return indirizzoDomicilioFiscale;
	}


	public void setIndirizzoDomicilioFiscale(
			String indirizzoDomicilioFiscale) {
		this.indirizzoDomicilioFiscale = indirizzoDomicilioFiscale;
	}


	public String getFlagResidenzaEstero() {
		return flagResidenzaEstero;
	}


	public void setFlagResidenzaEstero(String flagResidenzaEstero) {
		this.flagResidenzaEstero = flagResidenzaEstero;
	}


	public BigDecimal getImportoMovimento() {
		return importoMovimento;
	}


	public void setImportoMovimento(BigDecimal importoMovimento) {
		this.importoMovimento = importoMovimento;
	}


	public String getValuta() {
		return valuta;
	}


	public void setValuta(String valuta) {
		this.valuta = valuta;
	}


	public BigDecimal getImportoTerreniAgricoli() {
		return importoTerreniAgricoli;
	}


	public void setImportoTerreniAgricoli(
			BigDecimal importoTerreniAgricoli) {
		this.importoTerreniAgricoli = importoTerreniAgricoli;
	}


	public BigDecimal getImportoAreeFabbricabili() {
		return importoAreeFabbricabili;
	}


	public void setImportoAreeFabbricabili(
			BigDecimal importoAreeFabbricabili) {
		this.importoAreeFabbricabili = importoAreeFabbricabili;
	}


	public BigDecimal getImportoAbitazionePrincipale() {
		return importoAbitazionePrincipale;
	}


	public void setImportoAbitazionePrincipale(
			BigDecimal importoAbitazionePrincipale) {
		this.importoAbitazionePrincipale = importoAbitazionePrincipale;
	}


	public BigDecimal getImportoAltriFabbricati() {
		return importoAltriFabbricati;
	}


	public void setImportoAltriFabbricati(
			BigDecimal importoAltriFabbricati) {
		this.importoAltriFabbricati = importoAltriFabbricati;
	}


	public BigDecimal getImportoDetrazioneComunale() {
		return importoDetrazioneComunale;
	}


	public void setImportoDetrazioneComunale(
			BigDecimal importoDetrazioneComunale) {
		this.importoDetrazioneComunale = importoDetrazioneComunale;
	}


	public BigDecimal getImportoDetrazioneStatale() {
		return importoDetrazioneStatale;
	}


	public void setImportoDetrazioneStatale(
			BigDecimal importoDetrazioneStatale) {
		this.importoDetrazioneStatale = importoDetrazioneStatale;
	}


	public String getComuneDiUbicazioneImmobile() {
		return comuneDiUbicazioneImmobile;
	}


	public void setComuneDiUbicazioneImmobile(
			String comuneDiUbicazioneImmobile) {
		this.comuneDiUbicazioneImmobile = comuneDiUbicazioneImmobile;
	}


	public String getCapComuneUbicazioneImmobile() {
		return capComuneUbicazioneImmobile;
	}


	public void setCapComuneUbicazioneImmobile(
			String capComuneUbicazioneImmobile) {
		this.capComuneUbicazioneImmobile = capComuneUbicazioneImmobile;
	}


	public void setCodiceProvinciaComuneUbicazioneImmobile(
			String codiceProvinciaComuneUbicazioneImmobile) {
		this.codiceProvinciaComuneUbicazioneImmobile = codiceProvinciaComuneUbicazioneImmobile;
	}
	public String getCodiceProvinciaComuneUbicazioneImmobile() {
		return codiceProvinciaComuneUbicazioneImmobile;
	}
	public String getAnnoImposta() {
		return annoImposta;
	}


	public void setAnnoImposta(String annoImposta) {
		this.annoImposta = annoImposta;
	}


	public String getNumeroFabbricati() {
		return numeroFabbricati;
	}


	public void setNumeroFabbricati(String numeroFabbricati) {
		this.numeroFabbricati = numeroFabbricati;
	}


	public String getFlagRata() {
		return flagRata;
	}


	public void setFlagRata(String flagRata) {
		this.flagRata = flagRata;
	}


	public String getNumeroProvvedimento() {
		return numeroProvvedimento;
	}


	public void setNumeroProvvedimento(String numeroProvvedimento) {
		this.numeroProvvedimento = numeroProvvedimento;
	}


	public java.util.Date getDataProvvedimento() {
		return dataProvvedimento;
	}


	public void setDataProvvedimento(java.util.Date dataProvvedimento) {
		this.dataProvvedimento = dataProvvedimento;
	}


	public String getFlagRavvedimento() {
		return flagRavvedimento;
	}


	public void setFlagRavvedimento(String flagRavvedimento) {
		this.flagRavvedimento = flagRavvedimento;
	}


	public String getChiaveSpedizione() {
		return chiaveSpedizione;
	}


	public void setChiaveSpedizione(String chiaveSpedizione) {
		this.chiaveSpedizione = chiaveSpedizione;
	}


	public String getInvioSistemaInformativoCentrale() {
		return invioSistemaInformativoCentrale;
	}


	public void setInvioSistemaInformativoCentrale(
			String invioSistemaInformativoCentrale) {
		this.invioSistemaInformativoCentrale = invioSistemaInformativoCentrale;
	}


	public BigDecimal getImponibileIciPerIscop() {
		return imponibileIciPerIscop;
	}


	public void setImponibileIciPerIscop(BigDecimal imponibileIciPerIscop) {
		this.imponibileIciPerIscop = imponibileIciPerIscop;
	}
	public BigDecimal getRiduzionePerIscop() {
		return riduzionePerIscop;
	}
	public void setRiduzionePerIscop(BigDecimal riduzionePerIscop) {
		this.riduzionePerIscop = riduzionePerIscop;
	}
	public BigDecimal getDetrazionePerIscop() {
		return detrazionePerIscop;
	}
	public void setDetrazionePerIscop(BigDecimal detrazionePerIscop) {
		this.detrazionePerIscop = detrazionePerIscop;
	}
	public void setImpostaIciSanzioni(BigDecimal impostaIciSanzioni) {
		this.impostaIciSanzioni = impostaIciSanzioni;
	}
	public BigDecimal getImpostaIciSanzioni() {
		return impostaIciSanzioni;
	}
	public void setSopratassaIciSanzioni(BigDecimal sopratassaIciSanzioni) {
		this.sopratassaIciSanzioni = sopratassaIciSanzioni;
	}
	public BigDecimal getSopratassaIciSanzioni() {
		return sopratassaIciSanzioni;
	}
	public void setPenaPecuniariaIciSanzioni(BigDecimal penaPecuniariaIciSanzioni) {
		this.penaPecuniariaIciSanzioni = penaPecuniariaIciSanzioni;
	}
	public BigDecimal getPenaPecuniariaIciSanzioni() {
		return penaPecuniariaIciSanzioni;
	}
	public void setInteressiIciSanzioni(BigDecimal interessiIciSanzioni) {
		this.interessiIciSanzioni = interessiIciSanzioni;
	}
	public BigDecimal getInteressiIciSanzioni() {
		return interessiIciSanzioni;
	}
	public java.util.Date getDataultimoAggiornamento() {
		return dataultimoAggiornamento;
	}
	public void setDataultimoAggiornamento(java.util.Date dataultimoAggiornamento) {
		this.dataultimoAggiornamento = dataultimoAggiornamento;
	}
	public String getOpertoreUltimoAggiornamento() {
		return opertoreUltimoAggiornamento;
	}
	public void setOpertoreUltimoAggiornamento(
			String opertoreUltimoAggiornamento) {
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
	}
	public String getDescrizioneBollettino() {
		return descrizioneBollettino;
	}
	public void setDescrizioneBollettino(String descrizioneBollettino) {
		this.descrizioneBollettino = descrizioneBollettino;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public void setChiavePagamentoScartato(Long chiavePagamentoScartato) {
		this.chiavePagamentoScartato = chiavePagamentoScartato;
	}
	public Long getChiavePagamentoScartato() {
		return chiavePagamentoScartato;
	}

	//PG150180_001 GG - inizio
	public java.lang.String getNodoSpcIuv() {
		return nodoSpcIuv;
	}


	public void setNodoSpcIuv(java.lang.String nodoSpcIuv) {
		this.nodoSpcIuv = nodoSpcIuv;
	}


	public java.lang.String getNodoSpcRpt() {
		return nodoSpcRpt;
	}


	public void setNodoSpcRpt(java.lang.String nodoSpcRpt) {
		this.nodoSpcRpt = nodoSpcRpt;
	}


	public java.lang.String getNodoSpcRptFirma() {
		return nodoSpcRptFirma;
	}


	public void setNodoSpcRptFirma(java.lang.String nodoSpcRptFirma) {
		this.nodoSpcRptFirma = nodoSpcRptFirma;
	}


	public java.lang.String getNodoSpcRptEsito() {
		return nodoSpcRptEsito;
	}


	public void setNodoSpcRptEsito(java.lang.String nodoSpcRptEsito) {
		this.nodoSpcRptEsito = nodoSpcRptEsito;
	}


	public java.lang.String getNodoSpcRt() {
		return nodoSpcRt;
	}


	public void setNodoSpcRt(java.lang.String nodoSpcRt) {
		this.nodoSpcRt = nodoSpcRt;
	}


	public java.lang.String getNodoSpcRtFirma() {
		return nodoSpcRtFirma;
	}


	public void setNodoSpcRtFirma(java.lang.String nodoSpcRtFirma) {
		this.nodoSpcRtFirma = nodoSpcRtFirma;
	}


	public java.lang.String getNodoSpcRtEsito() {
		return nodoSpcRtEsito;
	}


	public void setNodoSpcRtEsito(java.lang.String nodoSpcRtEsito) {
		this.nodoSpcRtEsito = nodoSpcRtEsito;
	}
	//PG150180_001 GG - fine

	//inizio LP PG200360
	public java.lang.String getTassonomia() {
		return tassonomia;
	}

	public void setTassonomia(java.lang.String tassonomia) {
		this.tassonomia = tassonomia;
	}
	//fine LP PG200360
	
	/* (non-Javadoc)
	 * @see com.seda.payer.commons.transform.TransformersIf#beanToBean(Object)
	 */
	public Serializable beanToBean(Object arg0) throws Exception{		 
		return this;
	} 
	
	public int getProgBollettino() {
		return progBollettino;
	}
	public void setProgBollettino(int progBollettino) {
		this.progBollettino = progBollettino;
	}
	public java.lang.String getCodiceIUR() {
		return codiceIUR;
	}
	public void setCodiceIUR(java.lang.String codiceIUR) {
		this.codiceIUR = codiceIUR;
	}
	
	public Long getProgQuadratura() {
		return progQuadratura;
	}
	public void setProgQuadratura(Long progQuadratura) {
		this.progQuadratura = progQuadratura;
	}
	public static  TransazioneIci getBean(ResultSet data)throws SQLException
	{
		TransazioneIci bean = new TransazioneIci();
		
		
		bean.chiaveTransazioneIci = data.getString("TIC_KTICKTIC");
		bean.chiaveTransazioneGenerale = data.getString("TIC_KTRAKTRA");
		bean.codiceSocieta = data.getString("TIC_CSOCCSOC");
		bean.codiceUtente = data.getString("TIC_CUTECUTE");
		bean.chiaveEnte = data.getString("TIC_KANEKENT");
		bean.codiceTipologiaServizio = data.getString("TIC_CTSECTSE");
		bean.codiceImpostaServizio = data.getString("TIC_CISECISE");
		bean.codiceServizio = data.getString("TIC_CSERCSER");
		bean.tipoBollettino = data.getString("TIC_TBOLTBOL");
		bean.numeroContoCorrente = data.getString("TIC_NTICNCCP");
		bean.descrizioneIntestatarioContoCorrente = data.getString("TIC_DTICDINT");
		bean.annoRegistrazione = data.getString("TIC_CTICAREG");
		bean.numeroRegistrazione = data.getString("TIC_CTICNREG");
		bean.codiceFiscale = data.getString("TIC_CTICCFIS");
		bean.denominazione = data.getString("TIC_DTICDENO");
		bean.domicilioFiscale = data.getString("TIC_DTICDOMI");
		bean.indirizzoDomicilioFiscale = data.getString("TIC_DTICINDI");
		bean.flagResidenzaEstero = data.getString("TIC_FTICESTE");
		bean.importoMovimento = data.getBigDecimal("TIC_ITICIMOV");
		bean.valuta = data.getString("TIC_CTICVALU");
		bean.importoTerreniAgricoli = data.getBigDecimal("TIC_ITICAGRI");
		bean.importoAreeFabbricabili = data.getBigDecimal("TIC_ITICFABR");
		bean.importoAbitazionePrincipale = data.getBigDecimal("TIC_ITICABIT");
		bean.importoAltriFabbricati = data.getBigDecimal("TIC_ITICALTR");
		bean.importoDetrazioneComunale = data.getBigDecimal("TIC_ITICDCOM");
		bean.importoDetrazioneStatale = data.getBigDecimal("TIC_ITICDSTA");
		bean.comuneDiUbicazioneImmobile = data.getString("TIC_DTICCOUB");
		bean.capComuneUbicazioneImmobile = data.getString("TIC_CTICCCAP");
		bean.codiceProvinciaComuneUbicazioneImmobile = data.getString("TIC_CTICPRCO");
		bean.annoImposta = data.getString("TIC_CTICAIMP");
		bean.numeroFabbricati = data.getString("TIC_NTICNFAB");
		bean.flagRata = data.getString("TIC_FTICFRAT");
		bean.numeroProvvedimento = data.getString("TIC_CTICNPRV");
		bean.dataProvvedimento = data.getTimestamp("TIC_GTICDPRV");
		bean.flagRavvedimento = data.getString("TIC_FTICRAVV");
		bean.chiaveSpedizione = data.getString("TIC_KRENKREN");
		bean.invioSistemaInformativoCentrale = "";//data.getString("TIC_FTICFSPE");
		bean.imponibileIciPerIscop = data.getBigDecimal("TIC_ITICIMPO");
		bean.riduzionePerIscop = data.getBigDecimal("TIC_ITICRIDU");
		bean.detrazionePerIscop = data.getBigDecimal("TIC_ITICDETR");
		bean.impostaIciSanzioni = data.getBigDecimal("TIC_ITICIMPS");
		bean.sopratassaIciSanzioni = data.getBigDecimal("TIC_ITICSOPR");
		bean.penaPecuniariaIciSanzioni = data.getBigDecimal("TIC_ITICPENA");
		bean.interessiIciSanzioni = data.getBigDecimal("TIC_ITICINTE");
		bean.chiavePagamentoScartato = new Long(0);//data.getLong("TIC_PSCAPKEY");
		bean.dataultimoAggiornamento = data.getTimestamp("TIC_GTICGAGG");
		bean.opertoreUltimoAggiornamento = data.getString("TIC_CTICCOPE");
		bean.descrizioneBollettino = data.getString("BOL_DBOLDBOL");
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
		bean.progBollettino = data.getInt("TIC_PTICPIUR");
		bean.codiceIUR = data.getString("TIC_CTICCIUR");
		bean.progQuadratura = data.getLong("TIC_PTICPQUN");
//PG170300 - 30/1/2018 - FINE
		//inizio LP PG200360
		//inizio LP PG200360 il try/catch qui deve rimanere non tutte le sp ritrovano la colonna.
		try {
			bean.tassonomia = data.getString("TIC_CTASDSPI");
		} catch(Exception e){
			bean.tassonomia = "";
		}
		//fine LP PG200060

		return bean;
	}
	
	//PG150180_001 GG - inizio
	public static TransazioneIci getBean_ExtendedNodoSpc(ResultSet data)throws SQLException
	{
		TransazioneIci bean = getBean(data);
		bean.nodoSpcIuv = data.getString("RPT_KRPTKIUV");
		bean.nodoSpcRpt = data.getString("RPT_CRPTRPT");
		bean.nodoSpcRptEsito = data.getString("RPT_CRPTRPTESITO");
		bean.nodoSpcRptFirma = data.getString("RPT_CRPTRPTFIR");
		bean.nodoSpcRt = data.getString("RPT_CRPTRT");
		bean.nodoSpcRtEsito = data.getString("RPT_CRPTRTESITO");
		bean.nodoSpcRtFirma = data.getString("RPT_CRPTRTFIR");
		//inizio LP PG190220
		try {
			bean.nodoSpcRtAnnullata = data.getString("RPT_CRPTRTANN");
			bean.nodoSpcRtAnnullataEsito = data.getString("RPT_CRPTRTANNESITO");
			bean.nodoSpcRr = data.getString("RPT_CRPTRR");
			bean.nodoSpcEr = data.getString("RPT_CRPTER");
			bean.nodoSpcErEsito = data.getString("RPT_CRPTERESITO");
			bean.nodoSpcEsitoInvioRevocaEmailAdmin = data.getString("RPT_CRPTEIENRAM");
			bean.nodoSpcDataInvioRevocaEmailAdmin = data.getTimestamp("RPT_GRPTGIENRAM");
			bean.nodoSpcEsitoInvioRevocaEmailContribuente = data.getString("RPT_CRPTEIENRCT");
			bean.nodoSpcDataInvioRevocaEmailContribuente = data.getDate("RPT_GRPTGIENRCT");
		} catch (Exception e) {
			bean.nodoSpcRtAnnullata = "";
			bean.nodoSpcRtAnnullataEsito = "";
			bean.nodoSpcRr = "";
			bean.nodoSpcEr = "";
			bean.nodoSpcErEsito = "";
			bean.nodoSpcEsitoInvioRevocaEmailAdmin = "";
			bean.nodoSpcDataInvioRevocaEmailAdmin = null;
			bean.nodoSpcEsitoInvioRevocaEmailContribuente = "";
			bean.nodoSpcDataInvioRevocaEmailContribuente = null;
		}
		//fine LP PG190220
		return bean;
	}
	//PG150180_001 GG - fine

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
	
	}
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
	
	}
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTransazioneIci);
		arg.setString(2, this.chiaveTransazioneGenerale);
		arg.setString(3, this.codiceSocieta);
		arg.setString(4, this.codiceUtente);
		arg.setString(5, this.chiaveEnte);
		arg.setString(6, this.codiceTipologiaServizio);
		arg.setString(7, this.codiceImpostaServizio);
		arg.setString(8, this.codiceServizio);
		arg.setString(9, this.tipoBollettino);
		arg.setString(10, this.numeroContoCorrente);
		arg.setString(11, this.descrizioneIntestatarioContoCorrente);
		arg.setString(12, this.annoRegistrazione);
		arg.setString(13, this.numeroRegistrazione);
		arg.setString(14, this.codiceFiscale);
		arg.setString(15, this.denominazione);
		arg.setString(16, this.domicilioFiscale);
		arg.setString(17, this.indirizzoDomicilioFiscale);
		arg.setString(18, this.flagResidenzaEstero);
		arg.setBigDecimal(19, this.importoMovimento);
		arg.setString(20, this.valuta);
		arg.setBigDecimal(21, this.importoTerreniAgricoli);
		arg.setBigDecimal(22, this.importoAreeFabbricabili);
		arg.setBigDecimal(23, this.importoAbitazionePrincipale);
		arg.setBigDecimal(24, this.importoAltriFabbricati);
		arg.setBigDecimal(25, this.importoDetrazioneComunale);
		arg.setBigDecimal(26, this.importoDetrazioneStatale);
		arg.setString(27, this.comuneDiUbicazioneImmobile);
		arg.setString(28, this.capComuneUbicazioneImmobile);
		arg.setString(29, this.codiceProvinciaComuneUbicazioneImmobile);
		arg.setString(30, this.annoImposta);
		int iNumFabbricati = 0;
		try { iNumFabbricati = Integer.parseInt(this.numeroFabbricati.trim()); } 
		catch (NumberFormatException e) {}
		arg.setInt(31, iNumFabbricati);
		arg.setString(32, this.flagRata);
		arg.setString(33, this.numeroProvvedimento);
		arg.setTimestamp(34, new java.sql.Timestamp(this.dataProvvedimento.getTime()));
		arg.setString(35, this.flagRavvedimento);
		arg.setString(36, this.chiaveSpedizione);
		arg.setBigDecimal(37, this.imponibileIciPerIscop);
		arg.setBigDecimal(38, this.riduzionePerIscop);
		arg.setBigDecimal(39, this.detrazionePerIscop);
		arg.setBigDecimal(40, this.impostaIciSanzioni);
		arg.setBigDecimal(41, this.sopratassaIciSanzioni);
		arg.setBigDecimal(42, this.penaPecuniariaIciSanzioni);
		arg.setBigDecimal(43, this.interessiIciSanzioni);
		arg.setLong(44, this.chiavePagamentoScartato);
		arg.setString(45, this.opertoreUltimoAggiornamento);
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
//		arg.registerOutParameter(46, Types.INTEGER);
		arg.setInt(46, this.progBollettino);
		arg.setString(47, this.codiceIUR);
		arg.setLong(48, this.progQuadratura);
		//inizio LP PG200360
		//arg.registerOutParameter(49, Types.INTEGER);
		if (this.tassonomia == null) {
			arg.setString(49, "");
		} else {
			arg.setString(49, this.tassonomia);
		}
		arg.registerOutParameter(50, Types.INTEGER);
		//fine LP PG200360
//PG170300 - 30/1/2018 - FINE
		
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {

	}
	public java.lang.String getNodoSpcRtAnnullata() {
		return nodoSpcRtAnnullata;
	}
	public void setNodoSpcRtAnnullata(java.lang.String nodoSpcRtAnnullata) {
		this.nodoSpcRtAnnullata = nodoSpcRtAnnullata;
	}
	public java.lang.String getNodoSpcRtAnnullataEsito() {
		return nodoSpcRtAnnullataEsito;
	}
	public void setNodoSpcRtAnnullataEsito(java.lang.String nodoSpcRtAnnullataEsito) {
		this.nodoSpcRtAnnullataEsito = nodoSpcRtAnnullataEsito;
	}
	public java.lang.String getNodoSpcRr() {
		return nodoSpcRr;
	}
	public void setNodoSpcRr(java.lang.String nodoSpcRr) {
		this.nodoSpcRr = nodoSpcRr;
	}
	public java.lang.String getNodoSpcEr() {
		return nodoSpcEr;
	}
	public void setNodoSpcEr(java.lang.String nodoSpcEr) {
		this.nodoSpcEr = nodoSpcEr;
	}
	public java.lang.String getNodoSpcErEsito() {
		return nodoSpcErEsito;
	}
	public void setNodoSpcErEsito(java.lang.String nodoSpcErEsito) {
		this.nodoSpcErEsito = nodoSpcErEsito;
	}
	public java.lang.String getNodoSpcEsitoInvioRevocaEmailAdmin() {
		return nodoSpcEsitoInvioRevocaEmailAdmin;
	}
	public void setNodoSpcEsitoInvioRevocaEmailAdmin(
			java.lang.String nodoSpcEsitoInvioRevocaEmailAdmin) {
		this.nodoSpcEsitoInvioRevocaEmailAdmin = nodoSpcEsitoInvioRevocaEmailAdmin;
	}
	public java.util.Date getNodoSpcDataInvioRevocaEmailAdmin() {
		return nodoSpcDataInvioRevocaEmailAdmin;
	}
	public void setNodoSpcDataInvioRevocaEmailAdmin(
			java.util.Date nodoSpcDataInvioRevocaEmailAdmin) {
		this.nodoSpcDataInvioRevocaEmailAdmin = nodoSpcDataInvioRevocaEmailAdmin;
	}
	public java.lang.String getNodoSpcEsitoInvioRevocaEmailContribuente() {
		return nodoSpcEsitoInvioRevocaEmailContribuente;
	}
	public void setNodoSpcEsitoInvioRevocaEmailContribuente(
			java.lang.String nodoSpcEsitoInvioRevocaEmailContribuente) {
		this.nodoSpcEsitoInvioRevocaEmailContribuente = nodoSpcEsitoInvioRevocaEmailContribuente;
	}
	public java.util.Date getNodoSpcDataInvioRevocaEmailContribuente() {
		return nodoSpcDataInvioRevocaEmailContribuente;
	}
	public void setNodoSpcDataInvioRevocaEmailContribuente(
			java.util.Date nodoSpcDataInvioRevocaEmailContribuente) {
		this.nodoSpcDataInvioRevocaEmailContribuente = nodoSpcDataInvioRevocaEmailContribuente;
	}
	
}