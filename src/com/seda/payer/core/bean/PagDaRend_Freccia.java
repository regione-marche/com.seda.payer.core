package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.transform.TransformersIf;

public class PagDaRend_Freccia implements Serializable, TransformersIf{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Campi da PYTFRTB
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
    private java.math.BigDecimal importoTotaleBollettino;          
    private java.lang.String codiceIban;                           
    private java.lang.String motivoDelPagamento;                   
    private java.util.Date dataScadenza;                           
    private java.lang.String cinImporto;                           
    private java.lang.String cinIntermedio;                        
    private java.lang.String cinComplessivo;                       
    private java.lang.String codiceEsenzione;                      
    private java.lang.String codiceDivisa;                         
    private java.lang.String codiceFreccia;                        
    private java.lang.String chiaveSpedizione;                     
    private java.util.Date dataUltimoAggiornamento;                
    private java.lang.String opertoreUltimoAggiornamento;          
    private java.lang.String numeroDocumento;
	// Campo da PYGTWTB
    private java.lang.String codiceCarta;
    private java.lang.String codiceGateway;
	// Campo da PYTRATB 
    private java.util.Date dataEffettivoPagamentoSuGateway;
	// Campo da PYANETB
	private java.lang.String codiceEnteANE;
	// Campo da PYBOLTB
	private java.lang.String tipoFlusso;

	//Campo da PYREETB o PYRESTB
	private java.lang.String tipologiaRendicontazione; 	//PG110260
	
	
	private java.lang.String codiceIUV;
	
	//PG180010 - inizio
	//Campi da PYREETB o PYRESTB
	private java.lang.String formatoFlusso;
	private java.lang.String codTributo;
	
	// PYRPTTB
	private java.lang.String flussoPagoPA;
	private java.lang.String idPSP;
	// PYGDCTB
	private java.lang.String identificativoGDC;
	// PYMDCTB
	private java.lang.String numeroSospeso;
	private java.util.Date dataContabileAccredito;
	//PG180010 - fine
	
	public java.lang.String getCodiceIUV() {
		return codiceIUV;
	}

	public void setCodiceIUV(java.lang.String codiceIUV) {
		this.codiceIUV = codiceIUV;
	}
	
    public PagDaRend_Freccia() {}

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

	public java.lang.String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(java.lang.String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public java.lang.String getCodiceCarta() {
		return codiceCarta;
	}

	public void setCodiceCarta(java.lang.String codiceCarta) {
		this.codiceCarta = codiceCarta;
	}

	public java.util.Date getDataEffettivoPagamentoSuGateway() {
		return dataEffettivoPagamentoSuGateway;
	}

	public void setDataEffettivoPagamentoSuGateway(
			java.util.Date dataEffettivoPagamentoSuGateway) {
		this.dataEffettivoPagamentoSuGateway = dataEffettivoPagamentoSuGateway;
	}

	public java.lang.String getCodiceGateway() {
		return codiceGateway;
	}

	public void setCodiceGateway(java.lang.String codiceGateway) {
		this.codiceGateway = codiceGateway;
	}

	public java.lang.String getCodiceEnteANE() {
		return codiceEnteANE;
	}

	public void setCodiceEnteANE(java.lang.String codiceEnteANE) {
		this.codiceEnteANE = codiceEnteANE;
	}
	public java.lang.String getTipoFlusso() {
		return tipoFlusso;
	}

	public void setTipoFlusso(java.lang.String tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}

	//PG110260
	public java.lang.String getTipologiaRendicontazione() {
		return tipologiaRendicontazione;
	}

	//PG110260
	public void setTipologiaRendicontazione(
			java.lang.String tipologiaRendicontazione) {
		this.tipologiaRendicontazione = tipologiaRendicontazione;
	}

	public java.lang.String getFormatoFlusso() {
		return formatoFlusso;
	}

	public void setFormatoFlusso(java.lang.String formatoFlusso) {
		this.formatoFlusso = formatoFlusso;
	}

	public java.lang.String getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(java.lang.String codTributo) {
		this.codTributo = codTributo;
	}

	public java.lang.String getFlussoPagoPA() {
		return flussoPagoPA;
	}

	public void setFlussoPagoPA(java.lang.String flussoPagoPA) {
		this.flussoPagoPA = flussoPagoPA;
	}

	public java.lang.String getIdentificativoGDC() {
		return identificativoGDC;
	}

	public void setIdentificativoGDC(java.lang.String identificativoGDC) {
		this.identificativoGDC = identificativoGDC;
	}

	public java.lang.String getNumeroSospeso() {
		return numeroSospeso;
	}

	public void setNumeroSospeso(java.lang.String numeroSospeso) {
		this.numeroSospeso = numeroSospeso;
	}
	
	public void setDataContabileAccredito(java.util.Date dataContabileAccredito) {
		this.dataContabileAccredito = dataContabileAccredito;
	}

	public java.util.Date getDataContabileAccredito() {
		return dataContabileAccredito;
	}
	
	public void setIdPSP(java.lang.String idPSP) {
		this.idPSP = idPSP;
	}

	public java.lang.String getIdPSP() {
		return idPSP;
	}

	public static  PagDaRend_Freccia getBean(ResultSet data)throws SQLException
	{
		PagDaRend_Freccia bean = new PagDaRend_Freccia();
		
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
		bean.cinImporto = data.getString("TFR_CTFRCIMP");
		bean.cinIntermedio = data.getString("TFR_CTFRCINT");
		bean.cinComplessivo = data.getString("TFR_CTFRCCOM");
		bean.codiceEsenzione = data.getString("TFR_CTFRESEN");
		bean.codiceDivisa = data.getString("TFR_CTFRDIVI");
		bean.codiceFreccia = data.getString("TFR_CTFRFREC");
		bean.chiaveSpedizione = data.getString("TFR_KRENKREN");
		bean.dataUltimoAggiornamento = data.getDate("TFR_GTFRGAGG");
		bean.opertoreUltimoAggiornamento = data.getString("TFR_CTFRCOPE");
	    bean.codiceCarta = data.getString("GTW_CCARCCAR");
	    bean.dataEffettivoPagamentoSuGateway = data.getDate("TRA_GTRADPAG");
	    bean.codiceGateway = data.getString("GTW_KGTWKGTW");
	    bean.codiceEnteANE = data.getString("ANE_CANECENT");
	    bean.tipoFlusso = data.getString("BOL_TBOLTFLU");

	    bean.tipologiaRendicontazione = data.getString("TIPOLOGIA_RENDICONTAZIONE"); //PG110260
	    
	    bean.codiceIUV = data.getString("RPT_KRPTKIUV");
	    
	    //PG180010 - inizio
	    bean.formatoFlusso = data.getString("FORMATO_FLUSSO");
	    bean.codTributo = data.getString("COD_TRIBUTO");
	    bean.flussoPagoPA = data.getString("QUN_CQUNFLUS");
	    bean.identificativoGDC = data.getString("GDC_CGDCIDFL");
	    bean.numeroSospeso = data.getString("MDC_IMDCDOCN");
	    bean.dataContabileAccredito = new java.util.Date(new java.sql.Date(data.getTimestamp("MDC_GMDCDVAL").getTime()).getTime());
	    //PG180010 - fine 
	    
	    bean.idPSP = data.getString("RPT_CRPTIDPSP"); //PG190180_001 - 20190527
	    
		return bean;
		
	}


	public Serializable beanToBean(Object bean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
