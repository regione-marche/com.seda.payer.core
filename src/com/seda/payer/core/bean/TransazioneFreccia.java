package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.Lifecycle;
import com.seda.payer.commons.transform.TransformersIf;
import java.sql.ResultSet;

public class TransazioneFreccia extends Lifecycle implements Serializable, TransformersIf {

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
    private java.lang.String descrizioneIntestatarioContocorrente; 
    private java.lang.String denominazioneContribuente;            
    private java.lang.String codiceCap;                            
    private java.lang.String indirizzo;                            
    private java.lang.String provincia;                            
    private java.lang.String citta;                                
    private java.lang.String codiceFiscale;                        
    private java.lang.String codiceSia;                            
    private java.lang.String codiceIdentificativoPagamento;   
    private java.lang.String numeroDocumento;
    private java.math.BigDecimal importoTotaleBollettino;          
    private java.lang.String codiceIban;                           
    private java.lang.String motivoDelPagamento;                   
    private java.util.Date dataScadenza;  
    private int progressivoRata;
    private java.math.BigDecimal importoOneri;                                     
	private java.math.BigDecimal importoResiduoTotale;                             
	private java.math.BigDecimal importoResiduoCompenso;                           
	private java.math.BigDecimal importoResiduoMora;                               
	private java.math.BigDecimal importoResiduoSpese;                              
	private java.math.BigDecimal importoResiduoAltreSpese;                         
	private java.math.BigDecimal importoResiduoTributo;                            
	private java.math.BigDecimal importoResiduoNotifica;  
    private java.lang.String cinImporto;                           
    private java.lang.String cinIntermedio;                        
    private java.lang.String cinComplessivo;                       
    private java.lang.String codiceEsenzione;                      
    private java.lang.String codiceDivisa;                         
    private java.lang.String codiceFreccia;                        
    private java.lang.String chiaveSpedizione;                     
    private java.lang.String invioSistemaInformativoCentrale;      //
    private java.lang.Long chiavePagamentoScartato;
    private java.util.Date dataUltimoAggiornamento;                
    private java.lang.String opertoreUltimoAggiornamento;          
    private java.lang.String descrizioneBollettino;
    
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
	private long progQuadratura;
//PG170300 - 30/1/2018 - FINE
	//inizio LP PG200360
	private java.lang.String tassonomia;
	//fine LP PG200360

	public TransazioneFreccia() {    	
    }

	public TransazioneFreccia(String chiaveTransazioneDettaglio,
			String chiaveTransazioneGenerale, String codiceSocieta,
			String codiceUtente, String chiaveEnteCon, String chiaveEnteEnt,
			String codiceTipologiaServizio, String codiceImpostaServizio,
			String codiceServizio, String tipoBollettino,
			String descrizioneIntestatarioContocorrente,
			String denominazioneContribuente, String codiceCap,
			String indirizzo, String provincia, String citta,
			String codiceFiscale, String codiceSia,
			String codiceIdentificativoPagamento, String numeroDocumento,
			BigDecimal importoTotaleBollettino, String codiceIban,
			String motivoDelPagamento, Date dataScadenza, int progressivoRata,
		    BigDecimal importoOneri, BigDecimal importoResiduoTotale,                            
			BigDecimal importoResiduoCompenso, BigDecimal importoResiduoMora,                           
			BigDecimal importoResiduoSpese, BigDecimal importoResiduoAltreSpese,                         
			BigDecimal importoResiduoTributo, BigDecimal importoResiduoNotifica,   
			String cinImporto,
			String cinIntermedio, String cinComplessivo,
			String codiceEsenzione, String codiceDivisa, String codiceFreccia,
			String chiaveSpedizione, String invioSistemaInformativoCentrale, Long chiavePagamentoScartato,
			Date dataUltimoAggiornamento, String opertoreUltimoAggiornamento, String descrizioneBollettino,
			int progBollettino,
			String codiceIUR, 
			long progQuadratura
			, String tassonomia //LP PG200360
		) {
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
		this.descrizioneIntestatarioContocorrente = descrizioneIntestatarioContocorrente;
		this.denominazioneContribuente = denominazioneContribuente;
		this.codiceCap = codiceCap;
		this.indirizzo = indirizzo;
		this.provincia = provincia;
		this.citta = citta;
		this.codiceFiscale = codiceFiscale;
		this.codiceSia = codiceSia;
		this.codiceIdentificativoPagamento = codiceIdentificativoPagamento;
		this.numeroDocumento = numeroDocumento;
		this.importoTotaleBollettino = importoTotaleBollettino;
		this.codiceIban = codiceIban;
		this.motivoDelPagamento = motivoDelPagamento;
		this.dataScadenza = dataScadenza;
		this.progressivoRata = progressivoRata;
	    this.importoOneri = importoOneri;                                   
		this.importoResiduoTotale = importoResiduoTotale;                             
		this.importoResiduoCompenso = importoResiduoCompenso;                           
		this.importoResiduoMora = importoResiduoMora;                              
		this.importoResiduoSpese = importoResiduoSpese;                              
		this.importoResiduoAltreSpese = importoResiduoAltreSpese;                         
		this.importoResiduoTributo = importoResiduoTributo;                            
		this.importoResiduoNotifica = importoResiduoNotifica;
		this.cinImporto = cinImporto;
		this.cinIntermedio = cinIntermedio;
		this.cinComplessivo = cinComplessivo;
		this.codiceEsenzione = codiceEsenzione;
		this.codiceDivisa = codiceDivisa;
		this.codiceFreccia = codiceFreccia;
		this.chiaveSpedizione = chiaveSpedizione;
		this.invioSistemaInformativoCentrale = invioSistemaInformativoCentrale;
		this.chiavePagamentoScartato = chiavePagamentoScartato;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
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




	public java.lang.String getDescrizioneIntestatarioContocorrente() {
		return descrizioneIntestatarioContocorrente;
	}




	public void setDescrizioneIntestatarioContocorrente(
			java.lang.String descrizioneIntestatarioContocorrente) {
		this.descrizioneIntestatarioContocorrente = descrizioneIntestatarioContocorrente;
	}




	public java.lang.String getDenominazioneContribuente() {
		return denominazioneContribuente;
	}




	public void setDenominazioneContribuente(
			java.lang.String denominazioneContribuente) {
		this.denominazioneContribuente = denominazioneContribuente;
	}




	public java.lang.String getCodiceCap() {
		return codiceCap;
	}




	public void setCodiceCap(java.lang.String codiceCap) {
		this.codiceCap = codiceCap;
	}




	public java.lang.String getIndirizzo() {
		return indirizzo;
	}




	public void setIndirizzo(java.lang.String indirizzo) {
		this.indirizzo = indirizzo;
	}




	public java.lang.String getProvincia() {
		return provincia;
	}




	public void setProvincia(java.lang.String provincia) {
		this.provincia = provincia;
	}




	public java.lang.String getCitta() {
		return citta;
	}




	public void setCitta(java.lang.String citta) {
		this.citta = citta;
	}




	public java.lang.String getCodiceFiscale() {
		return codiceFiscale;
	}




	public void setCodiceFiscale(java.lang.String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}




	public java.lang.String getCodiceSia() {
		return codiceSia;
	}




	public void setCodiceSia(java.lang.String codiceSia) {
		this.codiceSia = codiceSia;
	}




	public java.lang.String getCodiceIdentificativoPagamento() {
		return codiceIdentificativoPagamento;
	}




	public void setCodiceIdentificativoPagamento(
			java.lang.String codiceIdentificativoPagamento) {
		this.codiceIdentificativoPagamento = codiceIdentificativoPagamento;
	}




	public java.math.BigDecimal getImportoTotaleBollettino() {
		return importoTotaleBollettino;
	}




	public void setImportoTotaleBollettino(
			java.math.BigDecimal importoTotaleBollettino) {
		this.importoTotaleBollettino = importoTotaleBollettino;
	}




	public java.lang.String getCodiceIban() {
		return codiceIban;
	}




	public void setCodiceIban(java.lang.String codiceIban) {
		this.codiceIban = codiceIban;
	}




	public java.lang.String getMotivoDelPagamento() {
		return motivoDelPagamento;
	}




	public void setMotivoDelPagamento(java.lang.String motivoDelPagamento) {
		this.motivoDelPagamento = motivoDelPagamento;
	}




	public java.util.Date getDataScadenza() {
		return dataScadenza;
	}




	public void setDataScadenza(java.util.Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}




	public java.lang.String getCinImporto() {
		return cinImporto;
	}




	public void setCinImporto(java.lang.String cinImporto) {
		this.cinImporto = cinImporto;
	}




	public java.lang.String getCinIntermedio() {
		return cinIntermedio;
	}




	public void setCinIntermedio(java.lang.String cinIntermedio) {
		this.cinIntermedio = cinIntermedio;
	}




	public java.lang.String getCinComplessivo() {
		return cinComplessivo;
	}




	public void setCinComplessivo(java.lang.String cinComplessivo) {
		this.cinComplessivo = cinComplessivo;
	}




	public java.lang.String getCodiceEsenzione() {
		return codiceEsenzione;
	}




	public void setCodiceEsenzione(java.lang.String codiceEsenzione) {
		this.codiceEsenzione = codiceEsenzione;
	}




	public java.lang.String getCodiceDivisa() {
		return codiceDivisa;
	}




	public void setCodiceDivisa(java.lang.String codiceDivisa) {
		this.codiceDivisa = codiceDivisa;
	}




	public java.lang.String getCodiceFreccia() {
		return codiceFreccia;
	}




	public void setCodiceFreccia(java.lang.String codiceFreccia) {
		this.codiceFreccia = codiceFreccia;
	}




	public java.lang.String getChiaveSpedizione() {
		return chiaveSpedizione;
	}




	public void setChiaveSpedizione(java.lang.String chiaveSpedizione) {
		this.chiaveSpedizione = chiaveSpedizione;
	}




	public java.lang.String getInvioSistemaInformativoCentrale() {
		return invioSistemaInformativoCentrale;
	}




	public void setInvioSistemaInformativoCentrale(
			java.lang.String invioSistemaInformativoCentrale) {
		this.invioSistemaInformativoCentrale = invioSistemaInformativoCentrale;
	}




	public java.util.Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}




	public void setDataUltimoAggiornamento(java.util.Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
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




	public java.lang.String getNumeroDocumento() {
		return numeroDocumento;
	}




	public void setNumeroDocumento(java.lang.String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}




	public void setChiavePagamentoScartato(java.lang.Long chiavePagamentoScartato) {
		this.chiavePagamentoScartato = chiavePagamentoScartato;
	}




	public java.lang.Long getChiavePagamentoScartato() {
		return chiavePagamentoScartato;
	}


	public void setProgressivoRata(int progressivoRata) {
		this.progressivoRata = progressivoRata;
	}




	public int getProgressivoRata() {
		return progressivoRata;
	}




	public void setImportoOneri(java.math.BigDecimal importoOneri) {
		this.importoOneri = importoOneri;
	}




	public java.math.BigDecimal getImportoOneri() {
		return importoOneri;
	}




	public void setImportoResiduoTotale(java.math.BigDecimal importoResiduoTotale) {
		this.importoResiduoTotale = importoResiduoTotale;
	}




	public java.math.BigDecimal getImportoResiduoTotale() {
		return importoResiduoTotale;
	}




	public void setImportoResiduoCompenso(java.math.BigDecimal importoResiduoCompenso) {
		this.importoResiduoCompenso = importoResiduoCompenso;
	}




	public java.math.BigDecimal getImportoResiduoCompenso() {
		return importoResiduoCompenso;
	}




	public void setImportoResiduoMora(java.math.BigDecimal importoResiduoMora) {
		this.importoResiduoMora = importoResiduoMora;
	}




	public java.math.BigDecimal getImportoResiduoMora() {
		return importoResiduoMora;
	}




	public void setImportoResiduoSpese(java.math.BigDecimal importoResiduoSpese) {
		this.importoResiduoSpese = importoResiduoSpese;
	}




	public java.math.BigDecimal getImportoResiduoSpese() {
		return importoResiduoSpese;
	}




	public void setImportoResiduoAltreSpese(java.math.BigDecimal importoResiduoAltreSpese) {
		this.importoResiduoAltreSpese = importoResiduoAltreSpese;
	}




	public java.math.BigDecimal getImportoResiduoAltreSpese() {
		return importoResiduoAltreSpese;
	}




	public void setImportoResiduoTributo(java.math.BigDecimal importoResiduoTributo) {
		this.importoResiduoTributo = importoResiduoTributo;
	}




	public java.math.BigDecimal getImportoResiduoTributo() {
		return importoResiduoTributo;
	}




	public void setImportoResiduoNotifica(java.math.BigDecimal importoResiduoNotifica) {
		this.importoResiduoNotifica = importoResiduoNotifica;
	}




	public java.math.BigDecimal getImportoResiduoNotifica() {
		return importoResiduoNotifica;
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


	public static long getSerialVersionUID() {
		return serialVersionUID;
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


	public long getProgQuadratura() {
		return progQuadratura;
	}

	public void setProgQuadratura(long progQuadratura) {
		this.progQuadratura = progQuadratura;
	}

	//inizio LP PG200360
	public java.lang.String getTassonomia() {
		return tassonomia;
	}

	public void setTassonomia(java.lang.String tassonomia) {
		this.tassonomia = tassonomia;
	}
	//fine LP PG200360

	/* (non-Javadoc)
	 * @see com.seda.payer.commons.transform.TransformersIf#beanToBean(java.lang.Object)
	 */
	public Serializable beanToBean(Object arg0) throws Exception{		 
		return this;
	} 
	
	public static  TransazioneFreccia getBean(ResultSet data)throws SQLException
	{
		TransazioneFreccia bean = new TransazioneFreccia();
		
		bean.chiaveTransazioneDettaglio = data.getString("TFR_KTFRKTFR");
		bean.chiaveTransazioneGenerale = data.getString("TFR_KTRAKTRA");
		bean.codiceSocieta = data.getString("TFR_CSOCCSOC");
		bean.codiceUtente = data.getString("TFR_CUTECUTE");
		bean.chiaveEnteCon = data.getString("TFR_KANEKENT_CON");
		bean.chiaveEnteEnt = data.getString("TFR_KANEKENT_ENT");
		bean.codiceTipologiaServizio = data.getString("TFR_CTSECTSE");
		bean.codiceImpostaServizio = data.getString("TFR_CISECISE");
		bean.codiceServizio = data.getString("TFR_CSERCSER");
		bean.tipoBollettino = data.getString("TFR_TBOLTBOL");
		bean.descrizioneIntestatarioContocorrente = data.getString("TFR_DTFRDINT");
		bean.denominazioneContribuente = data.getString("TFR_DTFRDENO");
		bean.codiceCap = data.getString("TFR_DTFRCCAP");
		bean.indirizzo = data.getString("TFR_DTFRINDI");
		bean.provincia = data.getString("TFR_CTFRPROV");
		bean.citta = data.getString("TFR_DTFRCITT");
		bean.codiceFiscale = data.getString("TFR_CTFRCFIS");
		bean.codiceSia = data.getString("TFR_CTFRCSIA");
		bean.codiceIdentificativoPagamento = data.getString("TFR_CTFRIDPG");
		bean.numeroDocumento = data.getString("TFR_CTFRNDOC");
		bean.importoTotaleBollettino = data.getBigDecimal("TFR_ITFRTOTA");
		bean.codiceIban = data.getString("TFR_CTFRIBAN");
		bean.motivoDelPagamento = data.getString("TFR_CTFRNOTE");
		bean.dataScadenza = data.getDate("TFR_GTFRSCAD");
		bean.progressivoRata = data.getInt("TFR_PTFRRATA");
		bean.importoOneri = data.getBigDecimal("TFR_ITFRIMP1");                                  
		bean.importoResiduoTotale = data.getBigDecimal("TFR_ITFRIMP2");                             
		bean.importoResiduoCompenso = data.getBigDecimal("TFR_ITFRIMP3");                           
		bean.importoResiduoMora = data.getBigDecimal("TFR_ITFRIMP4");                              
		bean.importoResiduoSpese = data.getBigDecimal("TFR_ITFRIMP5");                              
		bean.importoResiduoAltreSpese = data.getBigDecimal("TFR_ITFRIMP6");                         
		bean.importoResiduoTributo = data.getBigDecimal("TFR_ITFRIMP7");                            
		bean.importoResiduoNotifica = data.getBigDecimal("TFR_ITFRIMP8");
		bean.cinImporto = data.getString("TFR_CTFRCIMP");
		bean.cinIntermedio = data.getString("TFR_CTFRCINT");
		bean.cinComplessivo = data.getString("TFR_CTFRCCOM");
		bean.codiceEsenzione = data.getString("TFR_CTFRESEN");
		bean.codiceDivisa = data.getString("TFR_CTFRDIVI");
		bean.codiceFreccia = data.getString("TFR_CTFRFREC");
		bean.chiaveSpedizione = data.getString("TFR_KRENKREN");
		bean.invioSistemaInformativoCentrale = "";//data.getString("TFR_FTFRFSPE");
		bean.chiavePagamentoScartato = new Long(0); //data.getLong("TFR_PSCAPKEY");
		bean.dataUltimoAggiornamento = data.getDate("TFR_GTFRGAGG");
		bean.opertoreUltimoAggiornamento = data.getString("TFR_CTFRCOPE");
		try { //informazione non presente in tutte le stored
			bean.descrizioneBollettino = data.getString("BOL_DBOLDBOL");
		} catch (Exception e){}

//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
		bean.progBollettino = data.getInt("TFR_PTFRPIUR");
		bean.codiceIUR = data.getString("TFR_CTFRCIUR");
		bean.progQuadratura = data.getLong("TFR_PTFRPQUN");
//PG170300 - 30/1/2018 - FINE
		//inizio LP PG200360 il try/catch qui deve rimanere non tutte le sp ritrovano la colonna.
		try {
			bean.tassonomia = data.getString("TFR_CTASDSPI");
		} catch(Exception e){
			bean.tassonomia = "";
		}
		//fine LP PG200060

		return bean;
		
	}

	//PG150180_001 GG - inizio
	public static TransazioneFreccia getBean_ExtendedNodoSpc(ResultSet data)throws SQLException
	{
		TransazioneFreccia bean = getBean(data);
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
		arg.setString(11, this.descrizioneIntestatarioContocorrente);
		arg.setString(12, this.denominazioneContribuente);
		arg.setString(13, this.codiceCap);
		arg.setString(14, this.indirizzo);
		arg.setString(15, this.provincia);
		arg.setString(16, this.citta);
		arg.setString(17, this.codiceFiscale);
		arg.setString(18, this.codiceSia);
		arg.setString(19, this.codiceIdentificativoPagamento);
		arg.setString(20, this.numeroDocumento);
		arg.setBigDecimal(21, this.importoTotaleBollettino);
		arg.setString(22, this.codiceIban);
		arg.setString(23, this.motivoDelPagamento);
		arg.setDate(24, new java.sql.Date(this.dataScadenza.getTime()));
		arg.setInt(25, this.progressivoRata);
		arg.setBigDecimal(26,this.importoOneri);                                   
		arg.setBigDecimal(27,this.importoResiduoTotale);                             
		arg.setBigDecimal(28,this.importoResiduoCompenso);                           
		arg.setBigDecimal(29,this.importoResiduoMora);                              
		arg.setBigDecimal(30,this.importoResiduoSpese);                              
		arg.setBigDecimal(31,this.importoResiduoAltreSpese);                         
		arg.setBigDecimal(32,this.importoResiduoTributo);                            
		arg.setBigDecimal(33,this.importoResiduoNotifica);
		arg.setString(34, this.cinImporto);
		arg.setString(35, this.cinIntermedio);
		arg.setString(36, this.cinComplessivo);
		arg.setString(37, this.codiceEsenzione);
		arg.setString(38, this.codiceDivisa);
		arg.setString(39, this.codiceFreccia);
		arg.setString(40, this.chiaveSpedizione);
		arg.setLong(41, this.chiavePagamentoScartato);
		arg.setString(42, this.opertoreUltimoAggiornamento);
//PG170300 - 30/1/2018 - INIZIO - Aggiunte informazioni relative la marca da bollo
//		arg.registerOutParameter(43, Types.INTEGER);
		arg.setInt(43, this.progBollettino);
		arg.setString(44, this.codiceIUR);
		arg.setLong(45, this.progQuadratura);
		//inizio LP PG200360
		//arg.registerOutParameter(46, Types.INTEGER);
		if (this.tassonomia == null) {
			arg.setString(46, "");
		} else {
			arg.setString(46, this.tassonomia);
		}
		arg.registerOutParameter(47, Types.INTEGER);
		//fine LP PG200360
//PG170300 - 30/1/2018 - FINE
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {

	}

	//inizio LP PG190220
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
    //fine LP PG190220

}