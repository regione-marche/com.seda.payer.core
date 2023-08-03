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

public class TransazioneIV extends Lifecycle implements Serializable, TransformersIf {

	private static final long serialVersionUID = 1L;
	
	private java.lang.String chiaveTransazioneDettaglio;                           
	private java.lang.String chiaveTransazioneGenerale;                            
	private java.lang.String codiceSocieta;                                        
	private java.lang.String codiceUtente;                                         
	private java.lang.String chiaveEnteCon;                                        
	private java.lang.String chiaveEnteEnt;                                        
	private java.lang.String codiceTipologiaServizio;                              
	private java.lang.String codiceImpostaServizio;                                
	private java.lang.String codiceServizio;                                       
	private java.lang.String tipoBollettino;                                       
	private java.lang.String numeroContoCorrente;                                  
	private java.lang.String descrizioneIntestatarioContoCorrente;                 
	private java.lang.String codiceBollettinoPremarcatoMav;                        
	private java.lang.String annoDocumento;                                        
	private java.lang.String numeroDocumento;                                      
	private java.lang.String tipoDocumentoHost;                                    
	private int progressivoRata;                                                   
	private java.util.Date dataScadenzaRata;                                       
	private java.lang.String codiceFiscale;                                        
	private java.lang.String denominazione;                                        
	private java.lang.String indirizzo;  
	private java.lang.String codiceEnteComuneDomicilioFiscale;
	private java.lang.String citta;                                                
	private java.lang.String provincia;                                            
	private java.lang.String cap;                                                  
	private java.util.Date dataSanzione;                                           
	private java.lang.String targa;                                                
	private java.lang.String chiaveSpedizione;                                     
	private java.lang.String inviosistemaInformativoCentrale;   //                   
	private java.math.BigDecimal importoTotaleBollettino;                          
	private java.math.BigDecimal importoOneri;                                     
	private java.math.BigDecimal importoResiduoTotale;                             
	private java.math.BigDecimal importoResiduoCompenso;                           
	private java.math.BigDecimal importoResiduoMora;                               
	private java.math.BigDecimal importoResiduoSpese;                              
	private java.math.BigDecimal importoResiduoAltreSpese;                         
	private java.math.BigDecimal importoResiduoTributo;                            
	private java.math.BigDecimal importoResiduoNotifica;                           
	private java.lang.String meseScadenzaBolloAuto;                                
	private java.lang.String annoScadenzaBolloAuto;                                
	private java.lang.String mesiValiditaBolloAuto;                                
	private java.lang.String mesiRiduzioneBolloAuto;                               
	private java.lang.String categoriaBolloAuto;                                   
	private java.lang.String notePremarcato;   
	private java.lang.String flagTipoPagamento;
	private java.lang.Long chiavePagamentoScartato;
	private java.lang.String flagResidenzaEstero;
	private java.util.Date dataultimoAggiornamento;                                
	private java.lang.String opertoreUltimoAggiornamento;                          
	private java.lang.String descrizioneBollettino;
	private java.lang.String flagSanzioneRidotta;
	private java.lang.String codiceTessera;

	//PG150180_001 GG - inizio
	private java.lang.String nodoSpcIuv;
	private java.lang.String nodoSpcRpt;
	private java.lang.String nodoSpcRptFirma;
	private java.lang.String nodoSpcRptEsito;
	private java.lang.String nodoSpcRt;
	private java.lang.String nodoSpcRtFirma;
	private java.lang.String nodoSpcRtEsito;
	//PG150180_001 GG - FINE
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
	private java.lang.String codiceIban;	//PG170070 GG
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
	private java.math.BigDecimal importoMarcaDaBolloDigitale;
	private String segnaturaMarcaDaBolloDigitale;
	private String tipoBolloDaErogare;
	private String provinciaResidenza;
	private Integer progBollettino;
	private String codiceIUR;
	private Long progQuadratura;
//PG170300 - 30/1/2018 - FINE
	//inizio LP PG200060
	private String codiceIbanPostale;
	//fine LP PG200060
	//inizio LP PG200360
	private java.lang.String tassonomia;
	//fine LP PG200360
	//inizio LP PG210130
	private java.lang.String dettaglioContabile;
	//fine LP PG210130
    //inizio LP EP22405X
	private java.lang.String causalePreavvisiBRAV;
	//fine LP EP22405X
	private java.lang.String chiaveEnteCor;	//GG PAGONET-541
	
    public TransazioneIV() {		
	}

    
	public TransazioneIV(String chiaveTransazioneDettaglio,
			String chiaveTransazioneGenerale, String codiceSocieta,
			String codiceUtente, String chiaveEnteCon, String chiaveEnteEnt,
			String codiceTipologiaServizio, String codiceImpostaServizio,
			String codiceServizio, String tipoBollettino,
			String numeroContoCorrente,
			String descrizioneIntestatarioContoCorrente,
			String codiceBollettinoPremarcatoMav, String annoDocumento,
			String numeroDocumento, String tipoDocumentoHost,
			int progressivoRata, Date dataScadenzaRata, String codiceFiscale,
			String denominazione, String indirizzo, 
			String codiceEnteComuneDomicilioFiscale, String citta,
			String provincia, String cap, Date dataSanzione, String targa,
			String chiaveSpedizione, String inviosistemaInformativoCentrale,
			BigDecimal importoTotaleBollettino, BigDecimal importoOneri,
			BigDecimal importoResiduoTotale, BigDecimal importoResiduoCompenso,
			BigDecimal importoResiduoMora, BigDecimal importoResiduoSpese,
			BigDecimal importoResiduoAltreSpese,
			BigDecimal importoResiduoTributo,
			BigDecimal importoResiduoNotifica, String meseScadenzaBolloAuto,
			String annoScadenzaBolloAuto, String mesiValiditaBolloAuto,
			String mesiRiduzioneBolloAuto, String categoriaBolloAuto,
			String notePremarcato, String flagTipoPagamento,
			Long chiavePagamentoScartato, String flagResidenzaEstero,
			Date dataultimoAggiornamento,
			String opertoreUltimoAggiornamento, String descrizioneBollettino, String flagSanzioneRidotta,
			String codiceTessera, String codiceIban,
			BigDecimal importoMarcaDaBolloDigitale,
			String segnaturaMarcaDaBolloDigitale,
			String tipoBolloDaErogare,
			String provinciaResidenza,
			Integer progBollettino,
			String codiceIUR,
			Long progQuadratura,
			String codiceIbanPostale //LP PG200060
			, String tassonomia //LP PG200360
			, String dettaglioContabile //LP PG210130
			, String causalePreavvisiBRAV //LP EP22405X
			, String chiaveEnteCor	//GG PAGONET-541
			)
	{
		super();
		this.chiaveTransazioneDettaglio = chiaveTransazioneDettaglio;
		this.chiaveTransazioneGenerale = chiaveTransazioneGenerale;
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnteCon = chiaveEnteCon;
		this.chiaveEnteEnt = chiaveEnteEnt;
		this.codiceTipologiaServizio = codiceTipologiaServizio;
		this.codiceImpostaServizio = codiceImpostaServizio;
		this.codiceServizio = codiceServizio;
		this.tipoBollettino = tipoBollettino;
		this.numeroContoCorrente = numeroContoCorrente;
		this.descrizioneIntestatarioContoCorrente = descrizioneIntestatarioContoCorrente;
		this.codiceBollettinoPremarcatoMav = codiceBollettinoPremarcatoMav;
		this.annoDocumento = annoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.tipoDocumentoHost = tipoDocumentoHost;
		this.progressivoRata = progressivoRata;
		this.dataScadenzaRata = dataScadenzaRata;
		this.codiceFiscale = codiceFiscale;
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
		this.codiceEnteComuneDomicilioFiscale = codiceEnteComuneDomicilioFiscale;
		this.citta = citta;
		this.provincia = provincia;
		this.cap = cap;
		this.dataSanzione = dataSanzione;
		this.targa = targa;
		this.chiaveSpedizione = chiaveSpedizione;
		this.inviosistemaInformativoCentrale = inviosistemaInformativoCentrale;
		this.importoTotaleBollettino = importoTotaleBollettino;
		this.importoOneri = importoOneri;
		this.importoResiduoTotale = importoResiduoTotale;
		this.importoResiduoCompenso = importoResiduoCompenso;
		this.importoResiduoMora = importoResiduoMora;
		this.importoResiduoSpese = importoResiduoSpese;
		this.importoResiduoAltreSpese = importoResiduoAltreSpese;
		this.importoResiduoTributo = importoResiduoTributo;
		this.importoResiduoNotifica = importoResiduoNotifica;
		this.meseScadenzaBolloAuto = meseScadenzaBolloAuto;
		this.annoScadenzaBolloAuto = annoScadenzaBolloAuto;
		this.mesiValiditaBolloAuto = mesiValiditaBolloAuto;
		this.mesiRiduzioneBolloAuto = mesiRiduzioneBolloAuto;
		this.categoriaBolloAuto = categoriaBolloAuto;
		this.notePremarcato = notePremarcato;
		this.flagTipoPagamento = flagTipoPagamento;
		this.chiavePagamentoScartato = chiavePagamentoScartato;
		this.flagResidenzaEstero = flagResidenzaEstero;
		this.dataultimoAggiornamento = dataultimoAggiornamento;
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
		this.descrizioneBollettino =  descrizioneBollettino;
		this.flagSanzioneRidotta =  flagSanzioneRidotta;
		this.codiceTessera =  codiceTessera;
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
		this.codiceIban = codiceIban;	//PG170070 GG
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative ala marca da bollo
		this.importoMarcaDaBolloDigitale = importoMarcaDaBolloDigitale;
		this.segnaturaMarcaDaBolloDigitale = segnaturaMarcaDaBolloDigitale;
		this.tipoBolloDaErogare = tipoBolloDaErogare;
		this.provinciaResidenza = provinciaResidenza;
		this.progBollettino = progBollettino;
		this.codiceIUR = codiceIUR;
		this.progQuadratura = progQuadratura;
//PG170300 - 30/1/2018 - FINE
		//inizio LP PG200060
		this.codiceIbanPostale = codiceIbanPostale;
		//fine LP PG200060
		//inizio LP PG200360
		this.tassonomia = tassonomia;
		//fine LP PG200360
		//inizio LP PG210130
		this.dettaglioContabile = dettaglioContabile; 
		//fine LP PG210130
		this.causalePreavvisiBRAV = causalePreavvisiBRAV; //LP EP22405X
		this.chiaveEnteCor = chiaveEnteCor;	//GG PAGONET-541
	}
	

	public java.lang.String getChiaveTransazioneDettaglio() {
		return chiaveTransazioneDettaglio;
	}


	public void setChiaveTransazioneDettaglio(
			java.lang.String chiaveTransazioneDettaglio) {
		this.chiaveTransazioneDettaglio = chiaveTransazioneDettaglio;
	}


	public java.lang.String getChiaveTransazioneGenerale() {
		return chiaveTransazioneGenerale;
	}


	public void setChiaveTransazioneGenerale(
			java.lang.String chiaveTransazioneGenerale) {
		this.chiaveTransazioneGenerale = chiaveTransazioneGenerale;
	}


	public java.lang.String getCodiceSocieta() {
		return codiceSocieta;
	}


	public void setCodiceSocieta(java.lang.String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}


	public java.lang.String getCodiceUtente() {
		return codiceUtente;
	}


	public void setCodiceUtente(java.lang.String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}


	public java.lang.String getChiaveEnteCon() {
		return chiaveEnteCon;
	}


	public void setChiaveEnteCon(java.lang.String chiaveEnteCon) {
		this.chiaveEnteCon = chiaveEnteCon;
	}


	public java.lang.String getChiaveEnteEnt() {
		return chiaveEnteEnt;
	}


	public void setChiaveEnteEnt(java.lang.String chiaveEnteEnt) {
		this.chiaveEnteEnt = chiaveEnteEnt;
	}


	public java.lang.String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}


	public void setCodiceTipologiaServizio(java.lang.String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}


	public java.lang.String getCodiceImpostaServizio() {
		return codiceImpostaServizio;
	}


	public void setCodiceImpostaServizio(java.lang.String codiceImpostaServizio) {
		this.codiceImpostaServizio = codiceImpostaServizio;
	}


	public java.lang.String getCodiceServizio() {
		return codiceServizio;
	}


	public void setCodiceServizio(java.lang.String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}


	public java.lang.String getTipoBollettino() {
		return tipoBollettino;
	}


	public void setTipoBollettino(java.lang.String tipoBollettino) {
		this.tipoBollettino = tipoBollettino;
	}


	public java.lang.String getNumeroContoCorrente() {
		return numeroContoCorrente;
	}


	public void setNumeroContoCorrente(java.lang.String numeroContoCorrente) {
		this.numeroContoCorrente = numeroContoCorrente;
	}


	public java.lang.String getDescrizioneIntestatarioContoCorrente() {
		return descrizioneIntestatarioContoCorrente;
	}


	public void setDescrizioneIntestatarioContoCorrente(
			java.lang.String descrizioneIntestatarioContoCorrente) {
		this.descrizioneIntestatarioContoCorrente = descrizioneIntestatarioContoCorrente;
	}


	public java.lang.String getCodiceBollettinoPremarcatoMav() {
		return codiceBollettinoPremarcatoMav;
	}


	public void setCodiceBollettinoPremarcatoMav(
			java.lang.String codiceBollettinoPremarcatoMav) {
		this.codiceBollettinoPremarcatoMav = codiceBollettinoPremarcatoMav;
	}


	public java.lang.String getAnnoDocumento() {
		return annoDocumento;
	}


	public void setAnnoDocumento(java.lang.String annoDocumento) {
		this.annoDocumento = annoDocumento;
	}


	public java.lang.String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(java.lang.String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	public java.lang.String getTipoDocumentoHost() {
		return tipoDocumentoHost;
	}


	public void setTipoDocumentoHost(java.lang.String tipoDocumentoHost) {
		this.tipoDocumentoHost = tipoDocumentoHost;
	}


	public int getProgressivoRata() {
		return progressivoRata;
	}


	public void setProgressivoRata(int progressivoRata) {
		this.progressivoRata = progressivoRata;
	}


	public java.util.Date getDataScadenzaRata() {
		return dataScadenzaRata;
	}


	public void setDataScadenzaRata(java.util.Date dataScadenzaRata) {
		this.dataScadenzaRata = dataScadenzaRata;
	}


	public java.lang.String getCodiceFiscale() {
		return codiceFiscale;
	}


	public void setCodiceFiscale(java.lang.String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


	public java.lang.String getDenominazione() {
		return denominazione;
	}


	public void setDenominazione(java.lang.String denominazione) {
		this.denominazione = denominazione;
	}


	public java.lang.String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(java.lang.String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public java.lang.String getCitta() {
		return citta;
	}


	public void setCitta(java.lang.String citta) {
		this.citta = citta;
	}


	public java.lang.String getProvincia() {
		return provincia;
	}


	public void setProvincia(java.lang.String provincia) {
		this.provincia = provincia;
	}


	public java.lang.String getCap() {
		return cap;
	}


	public void setCap(java.lang.String cap) {
		this.cap = cap;
	}


	public java.util.Date getDataSanzione() {
		return dataSanzione;
	}


	public void setDataSanzione(java.util.Date dataSanzione) {
		this.dataSanzione = dataSanzione;
	}


	public java.lang.String getTarga() {
		return targa;
	}


	public void setTarga(java.lang.String targa) {
		this.targa = targa;
	}


	public java.lang.String getChiaveSpedizione() {
		return chiaveSpedizione;
	}


	public void setChiaveSpedizione(java.lang.String chiaveSpedizione) {
		this.chiaveSpedizione = chiaveSpedizione;
	}


	public java.lang.String getInviosistemaInformativoCentrale() {
		return inviosistemaInformativoCentrale;
	}


	public void setInviosistemaInformativoCentrale(
			java.lang.String inviosistemaInformativoCentrale) {
		this.inviosistemaInformativoCentrale = inviosistemaInformativoCentrale;
	}


	public java.math.BigDecimal getImportoTotaleBollettino() {
		return importoTotaleBollettino;
	}


	public void setImportoTotaleBollettino(
			java.math.BigDecimal importoTotaleBollettino) {
		this.importoTotaleBollettino = importoTotaleBollettino;
	}


	public java.math.BigDecimal getImportoOneri() {
		return importoOneri;
	}


	public void setImportoOneri(java.math.BigDecimal importoOneri) {
		this.importoOneri = importoOneri;
	}


	public java.math.BigDecimal getImportoResiduoTotale() {
		return importoResiduoTotale;
	}


	public void setImportoResiduoTotale(java.math.BigDecimal importoResiduoTotale) {
		this.importoResiduoTotale = importoResiduoTotale;
	}


	public java.math.BigDecimal getImportoResiduoCompenso() {
		return importoResiduoCompenso;
	}


	public void setImportoResiduoCompenso(
			java.math.BigDecimal importoResiduoCompenso) {
		this.importoResiduoCompenso = importoResiduoCompenso;
	}


	public java.math.BigDecimal getImportoResiduoMora() {
		return importoResiduoMora;
	}


	public void setImportoResiduoMora(java.math.BigDecimal importoResiduoMora) {
		this.importoResiduoMora = importoResiduoMora;
	}


	public java.math.BigDecimal getImportoResiduoSpese() {
		return importoResiduoSpese;
	}


	public void setImportoResiduoSpese(java.math.BigDecimal importoResiduoSpese) {
		this.importoResiduoSpese = importoResiduoSpese;
	}


	public java.math.BigDecimal getImportoResiduoAltreSpese() {
		return importoResiduoAltreSpese;
	}


	public void setImportoResiduoAltreSpese(
			java.math.BigDecimal importoResiduoAltreSpese) {
		this.importoResiduoAltreSpese = importoResiduoAltreSpese;
	}


	public java.math.BigDecimal getImportoResiduoTributo() {
		return importoResiduoTributo;
	}


	public void setImportoResiduoTributo(java.math.BigDecimal importoResiduoTributo) {
		this.importoResiduoTributo = importoResiduoTributo;
	}


	public java.math.BigDecimal getImportoResiduoNotifica() {
		return importoResiduoNotifica;
	}


	public void setImportoResiduoNotifica(
			java.math.BigDecimal importoResiduoNotifica) {
		this.importoResiduoNotifica = importoResiduoNotifica;
	}


	public java.lang.String getMeseScadenzaBolloAuto() {
		return meseScadenzaBolloAuto;
	}


	public void setMeseScadenzaBolloAuto(java.lang.String meseScadenzaBolloAuto) {
		this.meseScadenzaBolloAuto = meseScadenzaBolloAuto;
	}


	public java.lang.String getAnnoScadenzaBolloAuto() {
		return annoScadenzaBolloAuto;
	}


	public void setAnnoScadenzaBolloAuto(java.lang.String annoScadenzaBolloAuto) {
		this.annoScadenzaBolloAuto = annoScadenzaBolloAuto;
	}


	public java.lang.String getMesiValiditaBolloAuto() {
		return mesiValiditaBolloAuto;
	}


	public void setMesiValiditaBolloAuto(java.lang.String mesiValiditaBolloAuto) {
		this.mesiValiditaBolloAuto = mesiValiditaBolloAuto;
	}


	public java.lang.String getMesiRiduzioneBolloAuto() {
		return mesiRiduzioneBolloAuto;
	}


	public void setMesiRiduzioneBolloAuto(java.lang.String mesiRiduzioneBolloAuto) {
		this.mesiRiduzioneBolloAuto = mesiRiduzioneBolloAuto;
	}


	public java.lang.String getCategoriaBolloAuto() {
		return categoriaBolloAuto;
	}


	public void setCategoriaBolloAuto(java.lang.String categoriaBolloAuto) {
		this.categoriaBolloAuto = categoriaBolloAuto;
	}


	public java.lang.String getNotePremarcato() {
		return notePremarcato;
	}


	public void setNotePremarcato(java.lang.String notePremarcato) {
		this.notePremarcato = notePremarcato;
	}


	public java.util.Date getDataultimoAggiornamento() {
		return dataultimoAggiornamento;
	}


	public void setDataultimoAggiornamento(java.util.Date dataultimoAggiornamento) {
		this.dataultimoAggiornamento = dataultimoAggiornamento;
	}


	public java.lang.String getOpertoreUltimoAggiornamento() {
		return opertoreUltimoAggiornamento;
	}


	public void setOpertoreUltimoAggiornamento(
			java.lang.String opertoreUltimoAggiornamento) {
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
	}


	public java.lang.String getDescrizioneBollettino() {
		return descrizioneBollettino;
	}


	public void setDescrizioneBollettino(java.lang.String descrizioneBollettino) {
		this.descrizioneBollettino = descrizioneBollettino;
	}

	public java.lang.String getCodiceEnteComuneDomicilioFiscale() {
		return codiceEnteComuneDomicilioFiscale;
	}
	
	public void setCodiceEnteComuneDomicilioFiscale(java.lang.String codiceEnteComuneDomicilioFiscale){
		this.codiceEnteComuneDomicilioFiscale = codiceEnteComuneDomicilioFiscale;
	}
	
	public void setFlagTipoPagamento(java.lang.String flagTipoPagamento) {
		this.flagTipoPagamento = flagTipoPagamento;
	}

	public java.lang.String getFlagTipoPagamento() {
		return flagTipoPagamento;
	}


	public void setChiavePagamentoScartato(java.lang.Long chiavePagamentoScartato) {
		this.chiavePagamentoScartato = chiavePagamentoScartato;
	}


	public java.lang.Long getChiavePagamentoScartato() {
		return chiavePagamentoScartato;
	}


	public void setFlagResidenzaEstero(java.lang.String flagResidenzaEstero) {
		this.flagResidenzaEstero = flagResidenzaEstero;
	}


	public java.lang.String getFlagResidenzaEstero() {
		return flagResidenzaEstero;
	}

	
	
	public java.lang.String getFlagSanzioneRidotta() {
		return flagSanzioneRidotta;
	}


	public void setFlagSanzioneRidotta(java.lang.String flagSanzioneRidotta) {
		this.flagSanzioneRidotta = flagSanzioneRidotta;
	}
	
	public java.lang.String getCodiceTessera() {
		return codiceTessera;
	}


	public void setCodiceTessera(java.lang.String codiceTessera) {
		this.codiceTessera = codiceTessera;
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


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	//PG150180_001 GG - fine
	
	public java.lang.String getCodiceIban() {
		return codiceIban;
	}

	public java.math.BigDecimal getImportoMarcaDaBolloDigitale() {
		return importoMarcaDaBolloDigitale;
	}


	public void setImportoMarcaDaBolloDigitale(
			java.math.BigDecimal importoMarcaDaBolloDigitale) {
		this.importoMarcaDaBolloDigitale = importoMarcaDaBolloDigitale;
	}


	public String getSegnaturaMarcaDaBolloDigitale() {
		return segnaturaMarcaDaBolloDigitale;
	}


	public void setSegnaturaMarcaDaBolloDigitale(
			String segnaturaMarcaDaBolloDigitale) {
		this.segnaturaMarcaDaBolloDigitale = segnaturaMarcaDaBolloDigitale;
	}


	public String getTipoBolloDaErogare() {
		return tipoBolloDaErogare;
	}


	public void setTipoBolloDaErogare(String tipoBolloDaErogare) {
		this.tipoBolloDaErogare = tipoBolloDaErogare;
	}


	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}


	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}


	public void setCodiceIban(java.lang.String codiceIban) {
		this.codiceIban = codiceIban;
	}
	
	//inizio LP PG200060
	public String getCodiceIbanPostale() {
		return codiceIbanPostale;
	}


	public void setCodiceIbanPostale(String codiceIbanPostale) {
		this.codiceIbanPostale = codiceIbanPostale;
	}
	//fine LP PG200060

	//inizio LP PG200360
	public java.lang.String getTassonomia() {
		return tassonomia;
	}

	public void setTassonomia(java.lang.String tassonomia) {
		this.tassonomia = tassonomia;
	}
	//fine LP PG200360

	//inizio LP PG210130
	public java.lang.String getDettaglioContabile() {
		return dettaglioContabile;
	}


	public void setDettaglioContabile(java.lang.String dettaglioContabile) {
		this.dettaglioContabile = dettaglioContabile;
	}
	//fine LP PG210130
	
    //inizio LP EP22405X
    public java.lang.String getCausalePreavvisiBRAV() {
		return causalePreavvisiBRAV;
	}

	public void setCausalePreavvisiBRAV(java.lang.String causalePreavvisiBRAV) {
		this.causalePreavvisiBRAV = causalePreavvisiBRAV;
	}
    //fine LP EP22405X
	
	//GG PAGONET-541 - inizio
	public java.lang.String getChiaveEnteCor() {
		return chiaveEnteCor;
	}


	public void setChiaveEnteCor(java.lang.String chiaveEnteCor) {
		this.chiaveEnteCor = chiaveEnteCor;
	}
	//GG PAGONET-541 - fine

	/* (non-Javadoc)
	 * @see com.seda.payer.commons.transform.TransformersIf#beanToBean(java.lang.Object)
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


	public String getCodiceIUR() {
		return codiceIUR;
	}


	public void setCodiceIUR(String codiceIUR) {
		this.codiceIUR = codiceIUR;
	}



	public Long getProgQuadratura() {
		return progQuadratura;
	}


	public void setProgQuadratura(Long progQuadratura) {
		this.progQuadratura = progQuadratura;
	}


	public static  TransazioneIV getBean(ResultSet data)throws SQLException
	{
		TransazioneIV bean = new TransazioneIV();
		
		
		bean.chiaveTransazioneDettaglio = data.getString("TDT_KTDTKTDT");
		bean.chiaveTransazioneGenerale = data.getString("TDT_KTRAKTRA");
		bean.codiceSocieta = data.getString("TDT_CSOCCSOC");
		bean.codiceUtente = data.getString("TDT_CUTECUTE");
		bean.chiaveEnteCon = data.getString("TDT_KANEKENT_CON");
		bean.chiaveEnteEnt = data.getString("TDT_KANEKENT_ENT");
		bean.codiceTipologiaServizio = data.getString("TDT_CTSECTSE");
		bean.codiceImpostaServizio = data.getString("TDT_CISECISE");
		bean.codiceServizio = data.getString("TDT_CSERCSER");
		bean.tipoBollettino = data.getString("TDT_TBOLTBOL");
		bean.numeroContoCorrente = data.getString("TDT_NTDTNCCP");
		bean.descrizioneIntestatarioContoCorrente = data.getString("TDT_DTDTDINT");
		bean.codiceBollettinoPremarcatoMav = data.getString("TDT_CTDTCBOL");
		bean.annoDocumento = data.getString("TDT_CTDTADOC");
		bean.numeroDocumento = data.getString("TDT_CTDTNDOC");
		bean.tipoDocumentoHost = data.getString("TDT_TTDTTDOC");
		bean.progressivoRata = data.getInt("TDT_PTDTRATA");
		bean.dataScadenzaRata = data.getTimestamp("TDT_GTDTSCAD");
		bean.codiceFiscale = data.getString("TDT_CTDTCFIS");
		bean.denominazione = data.getString("TDT_DTDTDENO");
		bean.indirizzo = data.getString("TDT_DTDTINDI");
		//Inizio LP 20180709
		//bean.codiceEnteComuneDomicilioFiscale = ""; //data.getString("TDT_CTDTENTE");
		bean.codiceEnteComuneDomicilioFiscale = data.getString("TDT_CTDTENTE");
		//Fine LP 20180709
		bean.citta = data.getString("TDT_DTDTCITT");
		bean.provincia = data.getString("TDT_CTDTPROV");
		bean.cap = data.getString("TDT_DTDTCCAP");
		bean.dataSanzione = data.getTimestamp("TDT_GTDTSANZ");
		bean.targa = data.getString("TDT_DTDTTARG");
		bean.chiaveSpedizione = data.getString("TDT_KRENKREN");
		bean.inviosistemaInformativoCentrale = "";//data.getString("TDT_FTDTFSPE");
		bean.importoTotaleBollettino = data.getBigDecimal("TDT_ITDTTOTA");
		bean.importoOneri = data.getBigDecimal("TDT_ITDTIMP1");
		bean.importoResiduoTotale = data.getBigDecimal("TDT_ITDTIMP2");
		bean.importoResiduoCompenso = data.getBigDecimal("TDT_ITDTIMP3");
		bean.importoResiduoMora = data.getBigDecimal("TDT_ITDTIMP4");
		bean.importoResiduoSpese = data.getBigDecimal("TDT_ITDTIMP5");
		bean.importoResiduoAltreSpese = data.getBigDecimal("TDT_ITDTIMP6");
		bean.importoResiduoTributo = data.getBigDecimal("TDT_ITDTIMP7");
		bean.importoResiduoNotifica = data.getBigDecimal("TDT_ITDTIMP8");
		bean.meseScadenzaBolloAuto = data.getString("TDT_CTDTMESE");
		bean.annoScadenzaBolloAuto = data.getString("TDT_CTDTANNO");
		bean.mesiValiditaBolloAuto = data.getString("TDT_CTDTMVAL");
		bean.mesiRiduzioneBolloAuto = data.getString("TDT_CTDTRIDU");
		bean.categoriaBolloAuto = data.getString("TDT_CTDTCATE");
		bean.notePremarcato = data.getString("TDT_DTDTNOTE");
		bean.flagTipoPagamento = "";
		//inizio LP PG190220
		bean.dataultimoAggiornamento = data.getTimestamp("TDT_GTDTGAGG");
		bean.opertoreUltimoAggiornamento = data.getString("TDT_CTDTCOPE");
		//fine LP PG190220
		try {
			if(data.getString("TDT_FTDTPAGA")!=null){
				bean.flagTipoPagamento = data.getString("TDT_FTDTPAGA");
			}
		} catch (Exception e) {
			bean.flagTipoPagamento = "";
		}
		bean.chiavePagamentoScartato = new Long(0);
		try {
			if(data.getString("TDT_PSCAPKEY")!=null){
				bean.chiavePagamentoScartato = data.getLong("TDT_PSCAPKEY");
			}
		} catch (Exception e) {
			bean.chiavePagamentoScartato = new Long(0);
		}
		//inizio LP PG190220
		bean.flagResidenzaEstero = "";
		//fine LP PG190220
		try {
			//inizio LP PG190220
			if(data.getString("TDT_FTDTESTE") != null)
			//fine LP PG190220
			bean.flagResidenzaEstero = data.getString("TDT_FTDTESTE");
		} catch (Exception e) {
			bean.flagResidenzaEstero = "";
		}
		//inizio LP PG190220
		//bean.dataultimoAggiornamento = data.getTimestamp("TDT_GTDTGAGG");
		//bean.opertoreUltimoAggiornamento = data.getString("TDT_CTDTCOPE");
		//if(data.getString("BOL_DBOLDBOL")!=null)
		//{
		bean.descrizioneBollettino = "";
		//fine LP PG190220
			try {
				//inizio LP PG190220
				if(data.getString("BOL_DBOLDBOL") != null)
				//fine LP PG190220
				bean.descrizioneBollettino = data.getString("BOL_DBOLDBOL");
			} catch (Exception e) {
				bean.descrizioneBollettino = "";
			}
		//inizio LP PG190220
		//}
		//else
		//	bean.descrizioneBollettino = "";
		
		//if(data.getString("TDT_FTDTFSZR")!=null)
		//{
		bean.flagSanzioneRidotta = "";
		//fine LP PG190220
			try {
				//inizio LP PG190220
				if(data.getString("TDT_FTDTFSZR") != null)
				//fine LP PG190220
				bean.flagSanzioneRidotta = data.getString("TDT_FTDTFSZR");
			} catch (Exception e) {
				bean.flagSanzioneRidotta = "";
			}
		//inizio LP PG190220
		//}
		//else
		//	bean.flagSanzioneRidotta = "";
		
		
		//if(data.getString("TDT_CTDTCODT")!=null)
		//{
		bean.codiceTessera = "";
		//fine LP PG190220
			try {
				//inizio LP PG190220
				if(data.getString("TDT_CTDTCODT") != null)
				//fine LP PG190220
				bean.codiceTessera = data.getString("TDT_CTDTCODT");
			} catch (Exception e) {
				bean.codiceTessera = "";
			}
		//inizio LP PG190220
		//}
		//else
		//	bean.codiceTessera = "";
		//fine LP PG190220
		
		bean.codiceIban = data.getString("TDT_CTDTIBAN");
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
		bean.importoMarcaDaBolloDigitale = data.getBigDecimal("TDT_ITDTIMBL");
		bean.segnaturaMarcaDaBolloDigitale = data.getString("TDT_DTDTSMBD");
		bean.tipoBolloDaErogare = data.getString("TDT_CTDTTBDE");
		bean.provinciaResidenza = data.getString("TDT_CTDTPDRE");
		bean.progBollettino = data.getInt("TDT_PTDTPIUR");
		bean.codiceIUR = data.getString("TDT_CTDTCIUR");
		bean.progQuadratura = data.getLong("TDT_PTDTPQUN");
//PG170300 - 30/1/2018 - FINE
		//inizio LP PG200060 il try/catch qui deve rimanere non tutte le sp ritrovano la colonna.
		try {
			bean.codiceIbanPostale = data.getString("TDT_CTDTIBAN2");
		} catch(Exception e){
			bean.codiceIbanPostale = "";
		}
		//fine LP PG200060
		//inizio LP PG200360 il try/catch qui deve rimanere non tutte le sp ritrovano la colonna.
		try {
			bean.tassonomia = data.getString("TDT_CTASDSPI");
		} catch(Exception e){
			bean.tassonomia = "";
		}
		//fine LP PG200060
		//inizio LP PG210130 il try/catch qui deve rimanere non tutte le sp ritrovano la colonna.
		bean.dettaglioContabile = "";
		try {
			if(data.getString("TDT_DTDTDCNT") != null)
				bean.dettaglioContabile = data.getString("TDT_DTDTDCNT");
		} catch(Exception e){
			bean.dettaglioContabile = "";
		}
		//fine LP PG210130
		//inizio LP EP22405X il try/catch qui deve rimanere non tutte le sp ritrovano la colonna.
		bean.causalePreavvisiBRAV = "";
		try {
			if(data.getString("TDT_DTDTCBRV") != null)
				bean.causalePreavvisiBRAV = data.getString("TDT_DTDTCBRV");
		} catch(Exception e){
			bean.causalePreavvisiBRAV = "";
		}
		//fine LP EP22405X
		//inizio GG PAGONET-541 il try/catch qui deve rimanere non tutte le sp ritrovano la colonna.
		bean.chiaveEnteCor = "";
		try {
			if(data.getString("TDT_KANEKENT_COR") != null)
				bean.chiaveEnteCor = data.getString("TDT_KANEKENT_COR");
		} catch(Exception e){
			bean.chiaveEnteCor = "";
		}
		//fine GG PAGONET-541
		return bean;
	}

	//PG150180_001 GG - inizio
	public static TransazioneIV getBean_ExtendedNodoSpc(ResultSet data)throws SQLException
	{
		TransazioneIV bean = getBean(data);
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
		arg.setString(1, this.chiaveTransazioneDettaglio);
		arg.setString(2, this.chiaveTransazioneGenerale);
		arg.setString(3, this.codiceSocieta);
		arg.setString(4, this.codiceUtente);
		arg.setString(5, this.chiaveEnteCon);
		arg.setString(6, this.chiaveEnteEnt);
		arg.setString(7, this.codiceTipologiaServizio);
		arg.setString(8, this.codiceImpostaServizio);
		arg.setString(9, this.codiceServizio);
		arg.setString(10, this.tipoBollettino);
		arg.setString(11, this.numeroContoCorrente);
		arg.setString(12, this.descrizioneIntestatarioContoCorrente);
		arg.setString(13, this.codiceBollettinoPremarcatoMav);
		arg.setString(14, this.annoDocumento);
		arg.setString(15, this.numeroDocumento);
		arg.setString(16, this.tipoDocumentoHost);
		arg.setInt(17, this.progressivoRata);
		arg.setTimestamp(18, new java.sql.Timestamp(this.dataScadenzaRata.getTime()));
		
		if (this.codiceFiscale != null)
			arg.setString(19, this.codiceFiscale);
		else
			arg.setString(19, "");
		
		arg.setString(20, this.denominazione);
		arg.setString(21, this.indirizzo);
		arg.setString(22, this.codiceEnteComuneDomicilioFiscale);
		arg.setString(23, this.citta);
		arg.setString(24, this.provincia);
		arg.setString(25, this.cap);
		arg.setTimestamp(26, new java.sql.Timestamp(this.dataSanzione.getTime()));
		arg.setString(27, this.targa);
		arg.setString(28, this.chiaveSpedizione);
		arg.setBigDecimal(29, this.importoTotaleBollettino);
		arg.setBigDecimal(30, this.importoOneri);
		arg.setBigDecimal(31, this.importoResiduoTotale);
		arg.setBigDecimal(32, this.importoResiduoCompenso);
		arg.setBigDecimal(33, this.importoResiduoMora);
		arg.setBigDecimal(34, this.importoResiduoSpese);
		arg.setBigDecimal(35, this.importoResiduoAltreSpese);
		arg.setBigDecimal(36, this.importoResiduoTributo);
		arg.setBigDecimal(37, this.importoResiduoNotifica);
		arg.setString(38, this.meseScadenzaBolloAuto);
		arg.setString(39, this.annoScadenzaBolloAuto);
		arg.setString(40, this.mesiValiditaBolloAuto);
		arg.setString(41, this.mesiRiduzioneBolloAuto);
		arg.setString(42, this.categoriaBolloAuto);
		arg.setString(43, this.notePremarcato);
		arg.setString(44, this.flagTipoPagamento);
		arg.setLong(45, this.chiavePagamentoScartato);
		arg.setString(46, this.flagResidenzaEstero);
		arg.setString(47, this.opertoreUltimoAggiornamento);
		arg.setString(48, this.flagSanzioneRidotta);
		if (this.codiceTessera==null || this.codiceTessera.length() > 10) {
			arg.setString(49, "");
		} else {
			arg.setString(49, this.codiceTessera);
		}
		arg.setString(50, this.codiceIban==null?"":this.codiceIban);		//PG170070 GG
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative ala marca da bollo
		if (this.importoMarcaDaBolloDigitale==null) {
			arg.setBigDecimal(51,new java.math.BigDecimal(0));
		} else {
			arg.setBigDecimal(51,this.importoMarcaDaBolloDigitale);
		}
		if (this.segnaturaMarcaDaBolloDigitale==null) {
			arg.setString(52,"");
		} else {
			arg.setString(52,this.segnaturaMarcaDaBolloDigitale);
		}
		if (this.tipoBolloDaErogare==null) {
			arg.setString(53,"");
		} else {
			arg.setString(53,this.tipoBolloDaErogare);
		}
		if (this.provinciaResidenza==null) {
			arg.setString(54,"");
		} else {
			arg.setString(54,this.provinciaResidenza);
		}
		arg.setInt(55, this.progBollettino);
		if (this.codiceIUR==null) {
			arg.setString(56,"");
		} else {
			arg.setString(56,this.codiceIUR);
		}
		arg.setLong(57, this.progQuadratura);
//		arg.registerOutParameter(51, Types.INTEGER);
		//inizio LP PG200060
		//arg.registerOutParameter(58, Types.INTEGER);
		//PG170300 - 30/1/2018 - FINE
		arg.setString(58, this.codiceIbanPostale);
		//inizio LP PG200360
		//arg.registerOutParameter(59, Types.INTEGER);
		if (this.tassonomia == null) {
			arg.setString(59, "");
		} else {
			arg.setString(59, this.tassonomia);
		}
		//inizio LP PG210130
		//arg.registerOutParameter(60, Types.INTEGER);
		if (this.dettaglioContabile == null) {
			arg.setString(60, "");
		} else {
			arg.setString(60, this.dettaglioContabile);
		}
		//inizio LP EP22405X
		//arg.registerOutParameter(61, Types.INTEGER);
		if (this.causalePreavvisiBRAV == null) {
			arg.setString(61, "");
		} else {
			arg.setString(61, this.causalePreavvisiBRAV);
		}
		//inizio GG PAGONET-541
		//arg.registerOutParameter(62, Types.INTEGER);
		if (this.chiaveEnteCor == null) {
			arg.setString(62, "");
		} else {
			arg.setString(62, this.chiaveEnteCor);
		}
		arg.registerOutParameter(63, Types.INTEGER);
		//fine GG PAGONET-541
		//fine LP EP22405X
		//fine LP PG210130
		//fine LP PG200360
		//fine LP PG200060
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


	public void setProgBollettino(Integer progBollettino) {
		this.progBollettino = progBollettino;
	}


	
}
