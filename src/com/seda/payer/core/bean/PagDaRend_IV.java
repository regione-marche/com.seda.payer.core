package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.transform.TransformersIf;

public class PagDaRend_IV implements Serializable, TransformersIf{

	private static final long serialVersionUID = 1L;
	
	// Campi da PYTDTTB
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
	private java.lang.String citta;                                                
	private java.lang.String provincia;                                            
	private java.lang.String cap;                                                  
	private java.util.Date dataSanzione;                                           
	private java.lang.String targa;                                                
	private java.lang.String chiaveSpedizione;                                     
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
	private java.util.Date dataultimoAggiornamento;                                
	private java.lang.String opertoreUltimoAggiornamento;                          
	private java.lang.String codiceEnte;
	private java.lang.String flagSanzRidotta;
	private java.lang.String codiceTessera;
	// Campo da PYGTWTB
    private java.lang.String codiceCarta;
    private java.lang.String codiceGateway;
    private java.lang.String canalePagamento;
	// Campo da PYTRATB
    private java.util.Date dataEffettivoPagamentoSuGateway;
    private java.util.Date dataInizioTransazione;
    private java.lang.String codiceIdentificativoBanca;
    private java.lang.String codiceAutorizzazioneBanca;
    private java.math.BigDecimal importoTotaleTransazione;
    private java.math.BigDecimal importoCostoTransazione;
	// Campo da PYANETB
	private java.lang.String codiceEnteANE;
	// Campo da PYBOLTB
	private java.lang.String tipoFlusso;
	//Campo da PYMIPTB
	private java.lang.String numeroOperazioneCUP;
	private java.lang.String paymentRequestCUP;

	//Campo da PYREETB o PYRESTB
	private java.lang.String tipologiaRendicontazione; 	//PG110260
	private java.lang.String flagCarico;
	private java.lang.String ente;
	private java.lang.String impostaServizio;
	private java.lang.String flagNumDocCodCont;
	private java.lang.String codTributo;	//PG180260 GG 06122018
	
	//Campo da PYGTWTB
	private java.lang.String tipoGateway;
	
	
	//Campo da PYRPTTB
	private java.lang.String codiceIUV;
	private java.lang.String idPSP; // PG190180_001
	
	//Campo da PYQUNTB
	private java.lang.String idFlussoQuadratura;	//PG170070_002 GG

	private java.lang.String formatoFlusso;
	private java.lang.String identificativoGDC;
	private java.lang.String numeroSospeso;
	private java.util.Date dataContabileAccredito;
	
	//PG200280 GG - inizio
	private java.lang.String flagTrcComandiPolizia;
	private java.lang.String causaleGdc;
	private java.lang.String flagGdc;
	//PG200280 GG - fine
	
	private java.lang.String rendQuattrocento;
	
	private java.lang.String chiaveEnteCor;	//PAGONET-541
	
	private java.lang.String iban;
	private java.lang.String ibanPostale;
	 
	
	
	public PagDaRend_IV() {}
	
	public java.lang.String getiban() {
		return iban;
	}

	public void setiban(java.lang.String iban) {
		this.iban = iban;
	}
	
	public java.lang.String getibanPostale() {
		return ibanPostale;
	}

	public void setibanPostale(java.lang.String ibanPostale) {
		this.ibanPostale = ibanPostale;
	}
	
	public java.lang.String getrendQuattrocento() {
		return rendQuattrocento;
	}

	public void setrendQuattrocento(java.lang.String rendQuattrocento) {
		this.rendQuattrocento = rendQuattrocento;
	}
	
	//PG170070_002 GG - inizio
	public java.lang.String getIdFlussoQuadratura() {
		return idFlussoQuadratura;
	}

	public void setIdFlussoQuadratura(java.lang.String idFlussoQuadratura) {
		this.idFlussoQuadratura = idFlussoQuadratura;
	}
	//PG170070_002 GG - fine
	
    public java.lang.String getCodiceIUV() {
		return codiceIUV;
	}

	public void setCodiceIUV(java.lang.String codiceIUV) {
		this.codiceIUV = codiceIUV;
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

	public java.lang.String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(java.lang.String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}
	
	public java.lang.String getFlagSanzRidotta() {
		return flagSanzRidotta;
	}

	public void setFlagSanzRidotta(java.lang.String flagSanzRidotta) {
		this.flagSanzRidotta = flagSanzRidotta;
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

	public void setDataInizioTransazione(java.util.Date dataInizioTransazione) {
		this.dataInizioTransazione = dataInizioTransazione;
	}

	public java.util.Date getDataInizioTransazione() {
		return dataInizioTransazione;
	}

	public void setCodiceIdentificativoBanca(java.lang.String codiceIdentificativoBanca) {
		this.codiceIdentificativoBanca = codiceIdentificativoBanca;
	}

	public java.lang.String getCodiceIdentificativoBanca() {
		return codiceIdentificativoBanca;
	}

	public void setCodiceAutorizzazioneBanca(java.lang.String codiceAutorizzazioneBanca) {
		this.codiceAutorizzazioneBanca = codiceAutorizzazioneBanca;
	}

	public java.lang.String getCodiceAutorizzazioneBanca() {
		return codiceAutorizzazioneBanca;
	}

	public void setImportoTotaleTransazione(java.math.BigDecimal importoTotaleTransazione) {
		this.importoTotaleTransazione = importoTotaleTransazione;
	}

	public void setImportoCostoTransazione(java.math.BigDecimal importoCostoTransazione) {
		this.importoCostoTransazione = importoCostoTransazione;
	}

	public java.math.BigDecimal getImportoCostoTransazione() {
		return importoCostoTransazione;
	}

	public java.math.BigDecimal getImportoTotaleTransazione() {
		return importoTotaleTransazione;
	}

	public java.lang.String getCodiceGateway() {
		return codiceGateway;
	}

	public void setCodiceGateway(java.lang.String codiceGateway) {
		this.codiceGateway = codiceGateway;
	}

	public void setCanalePagamento(java.lang.String canalePagamento) {
		this.canalePagamento = canalePagamento;
	}

	public java.lang.String getCanalePagamento() {
		return canalePagamento;
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

	public void setNumeroOperazioneCUP(java.lang.String numeroOperazioneCUP) {
		this.numeroOperazioneCUP = numeroOperazioneCUP;
	}

	public java.lang.String getNumeroOperazioneCUP() {
		return numeroOperazioneCUP;
	}

	/**
	 * @param paymentRequestCUP the paymentRequestCUP to set
	 */
	public void setPaymentRequestCUP(java.lang.String paymentRequestCUP) {
		this.paymentRequestCUP = paymentRequestCUP;
	}

	/**
	 * @return the paymentRequestCUP
	 */
	public java.lang.String getPaymentRequestCUP() {
		return paymentRequestCUP;
	}

	
	public java.lang.String getTipologiaRendicontazione() {
		return tipologiaRendicontazione;
	}

	public void setTipologiaRendicontazione(
			java.lang.String tipologiaRendicontazione) {
		this.tipologiaRendicontazione = tipologiaRendicontazione;
	}

	public java.lang.String getFlagCarico() {
		return flagCarico;
	}

	public void setFlagCarico(java.lang.String flagCarico) {
		this.flagCarico = flagCarico;
	}

	public java.lang.String getEnte() {
		return ente;
	}

	public void setEnte(java.lang.String ente) {
		this.ente = ente;
	}

	public java.lang.String getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(java.lang.String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	public java.lang.String getFlagNumDocCodCont() {
		return flagNumDocCodCont;
	}

	public void setFlagNumDocCodCont(java.lang.String flagNumDocCodCont) {
		this.flagNumDocCodCont = flagNumDocCodCont;
	}
	

	public java.lang.String getCodiceTessera() {
		return codiceTessera;
	}

	public void setCodiceTessera(java.lang.String codiceTessera) {
		this.codiceTessera = codiceTessera;
	}
	
	

	public java.lang.String getTipoGateway() {
		return tipoGateway;
	}

	public void setTipoGateway(java.lang.String tipoGateway) {
		this.tipoGateway = tipoGateway;
	}
	
	//PG180260 GG 06122018 - inizio
	public java.lang.String getCodTributo() {
		return codTributo;
	}

	public void setCodTributo(java.lang.String codTributo) {
		this.codTributo = codTributo;
	}
	//PG180260 GG 06122018 - fine

	public java.lang.String getFormatoFlusso() {
		return formatoFlusso;
	}

	public void setFormatoFlusso(java.lang.String formatoFlusso) {
		this.formatoFlusso = formatoFlusso;
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
	
	public java.lang.String getIdPSP() {
		return idPSP;
	}
	public void setIdPSP(java.lang.String idPSP) {
		this.idPSP = idPSP;
	}
	
	//PG200280 GG - inizio
	public java.lang.String getFlagTrcComandiPolizia() {
		return flagTrcComandiPolizia;
	}

	public void setFlagTrcComandiPolizia(java.lang.String flagTrcComandiPolizia) {
		this.flagTrcComandiPolizia = flagTrcComandiPolizia;
	}
	
	public java.lang.String getCausaleGdc() {
		return causaleGdc;
	}

	public void setCausaleGdc(java.lang.String causaleGdc) {
		this.causaleGdc = causaleGdc;
	}

	public java.lang.String getFlagGdc() {
		return flagGdc;
	}

	public void setFlagGdc(java.lang.String flagGdc) {
		this.flagGdc = flagGdc;
	}
	//PG200280 GG - fine

	//PAGONET-541 - inizio
	public java.lang.String getChiaveEnteCor() {
		return chiaveEnteCor;
	}

	public void setChiaveEnteCor(java.lang.String chiaveEnteCor) {
		this.chiaveEnteCor = chiaveEnteCor;
	}
	//PAGONET-541 - fine

	
	public static PagDaRend_IV getBean(ResultSet data)throws SQLException
	{
		PagDaRend_IV bean = new PagDaRend_IV();
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
		bean.dataScadenzaRata = data.getDate("TDT_GTDTSCAD");
		bean.codiceFiscale = data.getString("TDT_CTDTCFIS");
		bean.denominazione = data.getString("TDT_DTDTDENO");
		bean.indirizzo = data.getString("TDT_DTDTINDI");
		bean.codiceEnte = data.getString("TDT_CTDTENTE");
		bean.flagSanzRidotta = data.getString("TDT_FTDTFSZR");	
		bean.codiceTessera = data.getString("TDT_CTDTCODT");
		bean.citta = data.getString("TDT_DTDTCITT");
		bean.provincia = data.getString("TDT_CTDTPROV");
		bean.cap = data.getString("TDT_DTDTCCAP");
		bean.dataSanzione = data.getDate("TDT_GTDTSANZ");
		bean.targa = data.getString("TDT_DTDTTARG");
		bean.chiaveSpedizione = data.getString("TDT_KRENKREN");
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
		//bean.dataultimoAggiornamento = data.getDate("TDT_GTDTGAGG");
		bean.dataultimoAggiornamento = new java.util.Date(new java.sql.Date(data.getTimestamp("TDT_GTDTGAGG").getTime()).getTime());
		bean.opertoreUltimoAggiornamento = data.getString("TDT_CTDTCOPE");
	    bean.codiceCarta = data.getString("GTW_CCARCCAR");
	    //bean.dataEffettivoPagamentoSuGateway = data.getDate("TRA_GTRADPAG");
	    bean.dataEffettivoPagamentoSuGateway = new java.util.Date(new java.sql.Date(data.getTimestamp("TRA_GTRADPAG").getTime()).getTime());
	    bean.codiceGateway = data.getString("GTW_KGTWKGTW");
	    bean.canalePagamento = data.getString("GTW_KCANKCAN");
	    bean.codiceEnteANE = data.getString("ANE_CANECENT");
	    bean.tipoFlusso = data.getString("BOL_TBOLTFLU");
	
	    bean.tipologiaRendicontazione = data.getString("TIPOLOGIA_RENDICONTAZIONE");  //PG110260
	    bean.flagCarico = data.getString("FLAG_CARICO");
	    bean.ente = data.getString("ENTE");
	    bean.impostaServizio = data.getString("IMPOSTA_SERVIZIO");
	    bean.flagNumDocCodCont = data.getString("FLAG_CODCONTRIB");
	    
	    bean.tipoGateway = data.getString("GTW_TGTWTIPO");
	    
	    bean.importoTotaleTransazione = data.getBigDecimal("IMPO_LORDO");
	    bean.importoCostoTransazione = data.getBigDecimal("IMPO_AGGIO");
	    bean.codiceIUV = data.getString("RPT_KRPTKIUV");
	    bean.idFlussoQuadratura = data.getString("QUN_CQUNFLUS");	//PG170070_002 GG
	    bean.codTributo = data.getString("COD_TRIBUTO");	//PG180260 GG 06122018
	    
	    //PG180010 - inizio
	    bean.formatoFlusso = data.getString("FORMATO_FLUSSO");
	    bean.identificativoGDC = data.getString("GDC_CGDCIDFL");
	    //inizio LP PG200060
	    //bean.numeroSospeso = data.getString("MDC_IMDCDOCN");
	    bean.numeroSospeso = (data.getString("MDC_IMDCDOCN") != null ? data.getString("MDC_IMDCDOCN") : "");
	    //fine LP PG200060
	    bean.dataContabileAccredito = new java.util.Date(new java.sql.Date(data.getTimestamp("MDC_GMDCDVAL").getTime()).getTime());
	    // PG180010 - fine
	    
	    bean.idPSP = data.getString("RPT_CRPTIDPSP"); //PG190180_001 - 20190527
	    
	    //PG200280 GG - inizio
	    bean.flagTrcComandiPolizia = data.getString("FLAG_TRACCIATO_COMANDI");
	    bean.causaleGdc = data.getString("MDC_CMDCCAUS");
	    bean.flagGdc = data.getString("ENT_FENTFGDC");
	    //PG200280 GG - fine
	    
	    try {
	    	bean.rendQuattrocento = data.getString("REN_QTRCNTO");
	    } catch (Exception ex) {
	    	bean.rendQuattrocento = "";
	    }
	    
	    try {
	    	bean.chiaveEnteCor = data.getString("TDT_KANEKENT_COR");
	    } catch (Exception ex) {
	    	bean.chiaveEnteCor = "";
	    }
	    
	    bean.iban = data.getString("TDT_CTDTIBAN");
	    
	    bean.ibanPostale = data.getString("TDT_CTDTIBAN2");

	    return bean;
		
	}
	

	public static PagDaRend_IV getBeanCUP(ResultSet data)throws SQLException
	{
		//setto tutti i campi del bean standard e aggiungo quelli del CUP
		PagDaRend_IV bean = getBean(data);
		
    	//campi gestiti solo per rendicontazione CUP
    	bean.dataInizioTransazione = new java.util.Date(new java.sql.Date(data.getTimestamp("TRA_GTRADTRA").getTime()).getTime());
    	bean.codiceIdentificativoBanca = data.getString("TRA_CTRAIDBA");
    	bean.codiceAutorizzazioneBanca = data.getString("TRA_CTRAAUBA");
    	bean.importoTotaleTransazione = data.getBigDecimal("TRA_ITRAITOT");
    	bean.importoCostoTransazione = data.getBigDecimal("TRA_ITRACOTR");
    	bean.numeroOperazioneCUP = data.getString("MIP_KMIPOPER");
    	bean.paymentRequestCUP = data.getString("MIP_CMIPPREQ");
	   
	    return bean;
		
	}

	public Serializable beanToBean(Object bean) throws Exception {
		return null;
	}


}

