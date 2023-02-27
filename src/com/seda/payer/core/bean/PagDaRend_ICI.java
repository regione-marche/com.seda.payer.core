package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.transform.TransformersIf;

public class PagDaRend_ICI implements Serializable, TransformersIf{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Campi da PYTICTB
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
	private String annoImposta;                                    
	private String numeroFabbricati;                               
	private String flagRata;                                       
	private String numeroProvvedimento;                            
	private java.util.Date dataProvvedimento;                                
	private String flagRavvedimento;                               
	private String chiaveSpedizione;                               
	private BigDecimal imponibileIciPerIscop;                      
	private BigDecimal riduzionePerIscop;                          
	private BigDecimal detrazionePerIscop;                         
	private java.util.Date dataultimoAggiornamento;                          
	private String opertoreUltimoAggiornamento;    
	private String codiceProvinciaComuneUbicazioneImmobile;
	// Campo da PYGTWTB
    private String codiceCarta;
    private String codiceGateway;
	// Campi da PYTRATB
    private java.util.Date dataEffettivoPagamentoSuGateway;
    private String codiceAutorizzazioneBanca;
    private String emailContribuente;
    private String flagInvioPostaOrdinaria;
    private String denominazioneContribuentePerPostaOrdinaria;
    private String indirizzoContribuentePerPostaOrdinaria;
    private String capContribuentePerPostaOrdinaria;
    private String cittaContribuentePerPostaOrdinaria;
    private String provinciaContribuentePerPostaOrdinaria;
    private BigDecimal importoSpeseNotifica;
    private BigDecimal importoCostoTransazione;
    
	// Campo da PYUTETB
    private String codiceAmbitoCNC;
	// Campi da PYANETB
    private String codiceBelfiore;
	private String codiceEnteANE;
	// Campo da PYBOLTB
	private String tipoFlusso;

	//Campo da PYREETB o PYRESTB
	private String tipologiaRendicontazione; 	//PG110260
	
	//Campi da PYAPCTB
	private String codiceProvincia;
	private String codiceComune;
	
	//Campi da PYRPTTB
	private String idPSP; 
	
	
	private BigDecimal importoInteressi; 
	
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
	
	public BigDecimal getImportointeressi() {
		return importoInteressi;
	}

	public void setImportointeressi(
			BigDecimal importoInteressi) {
		this.importoInteressi = importoInteressi;
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

	public void setCodiceProvinciaComuneUbicazioneImmobile(
			String codiceProvinciaComuneUbicazioneImmobile) {
		this.codiceProvinciaComuneUbicazioneImmobile = codiceProvinciaComuneUbicazioneImmobile;
	}

	public String getCodiceProvinciaComuneUbicazioneImmobile() {
		return codiceProvinciaComuneUbicazioneImmobile;
	}

	public String getCodiceCarta() {
		return codiceCarta;
	}

	public void setCodiceCarta(String codiceCarta) {
		this.codiceCarta = codiceCarta;
	}

	public java.util.Date getDataEffettivoPagamentoSuGateway() {
		return dataEffettivoPagamentoSuGateway;
	}

	public void setDataEffettivoPagamentoSuGateway(
			java.util.Date dataEffettivoPagamentoSuGateway) {
		this.dataEffettivoPagamentoSuGateway = dataEffettivoPagamentoSuGateway;
	}

	public String getCodiceAutorizzazioneBanca() {
		return codiceAutorizzazioneBanca;
	}

	public void setCodiceAutorizzazioneBanca(String codiceAutorizzazioneBanca) {
		this.codiceAutorizzazioneBanca = codiceAutorizzazioneBanca;
	}

	public String getEmailContribuente() {
		return emailContribuente;
	}

	public void setEmailContribuente(String emailContribuente) {
		this.emailContribuente = emailContribuente;
	}

	public String getFlagInvioPostaOrdinaria() {
		return flagInvioPostaOrdinaria;
	}

	public void setFlagInvioPostaOrdinaria(String flagInvioPostaOrdinaria) {
		this.flagInvioPostaOrdinaria = flagInvioPostaOrdinaria;
	}

	public String getDenominazioneContribuentePerPostaOrdinaria() {
		return denominazioneContribuentePerPostaOrdinaria;
	}

	public void setDenominazioneContribuentePerPostaOrdinaria(
			String denominazioneContribuentePerPostaOrdinaria) {
		this.denominazioneContribuentePerPostaOrdinaria = denominazioneContribuentePerPostaOrdinaria;
	}

	public String getIndirizzoContribuentePerPostaOrdinaria() {
		return indirizzoContribuentePerPostaOrdinaria;
	}

	public void setIndirizzoContribuentePerPostaOrdinaria(
			String indirizzoContribuentePerPostaOrdinaria) {
		this.indirizzoContribuentePerPostaOrdinaria = indirizzoContribuentePerPostaOrdinaria;
	}

	public String getCapContribuentePerPostaOrdinaria() {
		return capContribuentePerPostaOrdinaria;
	}

	public void setCapContribuentePerPostaOrdinaria(
			String capContribuentePerPostaOrdinaria) {
		this.capContribuentePerPostaOrdinaria = capContribuentePerPostaOrdinaria;
	}

	public String getCittaContribuentePerPostaOrdinaria() {
		return cittaContribuentePerPostaOrdinaria;
	}

	public void setCittaContribuentePerPostaOrdinaria(
			String cittaContribuentePerPostaOrdinaria) {
		this.cittaContribuentePerPostaOrdinaria = cittaContribuentePerPostaOrdinaria;
	}

	public String getProvinciaContribuentePerPostaOrdinaria() {
		return provinciaContribuentePerPostaOrdinaria;
	}

	public void setProvinciaContribuentePerPostaOrdinaria(
			String provinciaContribuentePerPostaOrdinaria) {
		this.provinciaContribuentePerPostaOrdinaria = provinciaContribuentePerPostaOrdinaria;
	}

	public BigDecimal getImportoSpeseNotifica() {
		return importoSpeseNotifica;
	}

	public void setImportoSpeseNotifica(BigDecimal importoSpeseNotifica) {
		this.importoSpeseNotifica = importoSpeseNotifica;
	}

	public BigDecimal getImportoCostoTransazione() {
		return importoCostoTransazione;
	}

	public void setImportoCostoTransazione(BigDecimal importoCostoTransazione) {
		this.importoCostoTransazione = importoCostoTransazione;
	}

	public String getCodiceAmbitoCNC() {
		return codiceAmbitoCNC;
	}

	public void setCodiceAmbitoCNC(String codiceAmbitoCNC) {
		this.codiceAmbitoCNC = codiceAmbitoCNC;
	}

	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}

	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}

    public String getCodiceGateway() {
		return codiceGateway;
	}

	public void setCodiceGateway(String codiceGateway) {
		this.codiceGateway = codiceGateway;
	}

	public String getCodiceEnteANE() {
		return codiceEnteANE;
	}

	public void setCodiceEnteANE(String codiceEnteANE) {
		this.codiceEnteANE = codiceEnteANE;
	}

	public String getTipoFlusso() {
		return tipoFlusso;
	}

	public void setTipoFlusso(String tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}
//PG110260
	public String getTipologiaRendicontazione() {
		return tipologiaRendicontazione;
	}

	//PG110260
	public void setTipologiaRendicontazione(
			String tipologiaRendicontazione) {
		this.tipologiaRendicontazione = tipologiaRendicontazione;
	}

	public void setCodiceProvincia(String codiceProvincia) {
		this.codiceProvincia = codiceProvincia;
	}

	public String getCodiceProvincia() {
		return codiceProvincia;
	}

	public void setCodiceComune(String codiceComune) {
		this.codiceComune = codiceComune;
	}

	public String getCodiceComune() {
		return codiceComune;
	}
	
    public void setIdPSP(String idPSP) {
		this.idPSP = idPSP;
	}

	public String getIdPSP() {
		return idPSP;
	}

	public PagDaRend_ICI() {}

	public static PagDaRend_ICI getBean(ResultSet data)throws SQLException
	{
		PagDaRend_ICI bean = new PagDaRend_ICI();
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
		bean.annoImposta = data.getString("TIC_CTICAIMP");
		bean.numeroFabbricati = data.getString("TIC_NTICNFAB");
		bean.flagRata = data.getString("TIC_FTICFRAT");
		bean.numeroProvvedimento = data.getString("TIC_CTICNPRV");
		bean.dataProvvedimento = data.getDate("TIC_GTICDPRV");
		bean.flagRavvedimento = data.getString("TIC_FTICRAVV");
		bean.chiaveSpedizione = data.getString("TIC_KRENKREN");
		bean.imponibileIciPerIscop = data.getBigDecimal("TIC_ITICIMPO");
		bean.riduzionePerIscop = data.getBigDecimal("TIC_ITICRIDU");
		bean.detrazionePerIscop = data.getBigDecimal("TIC_ITICDETR");
		bean.dataultimoAggiornamento = data.getDate("TIC_GTICGAGG");
		bean.opertoreUltimoAggiornamento = data.getString("TIC_CTICCOPE");
	    bean.codiceCarta = data.getString("GTW_CCARCCAR");
	    bean.dataEffettivoPagamentoSuGateway = data.getDate("TRA_GTRADPAG");
		bean.codiceAmbitoCNC = data.getString("UTE_CUTECCNC");
		bean.codiceBelfiore = data.getString("ANE_CANEBELF");
	    bean.codiceGateway = data.getString("GTW_KGTWKGTW");
	    bean.codiceEnteANE = data.getString("ANE_CANECENT");
	    bean.tipoFlusso = data.getString("BOL_TBOLTFLU");

	    bean.tipologiaRendicontazione = data.getString("TIPOLOGIA_RENDICONTAZIONE"); //PG110260
	    bean.importoInteressi = data.getBigDecimal("TIC_ITICINTE"); //PG110260
	    
	    bean.idPSP = data.getString("RPT_CRPTIDPSP"); //PG190180_001
	    	    
		return bean;
		
	}
	
	public static PagDaRend_ICI getBeanSeda(ResultSet data)throws SQLException
	{
		PagDaRend_ICI bean = getBean(data);
		
		bean.setCodiceProvinciaComuneUbicazioneImmobile(data.getString("TIC_CTICPRCO"));
		bean.setCodiceAutorizzazioneBanca(data.getString("TRA_CTRAAUBA"));
	    bean.setEmailContribuente(data.getString("TRA_ETRAECON"));
	    bean.setFlagInvioPostaOrdinaria(data.getString("TRA_FTRAPOST"));
	    bean.setDenominazioneContribuentePerPostaOrdinaria(data.getString("TRA_DTRADENO"));
	    bean.setIndirizzoContribuentePerPostaOrdinaria(data.getString("TRA_DTRAINDI"));
	    bean.setCapContribuentePerPostaOrdinaria(data.getString("TRA_CTRACCAP"));
	    bean.setCittaContribuentePerPostaOrdinaria(data.getString("TRA_DTRACITT"));
	    bean.setProvinciaContribuentePerPostaOrdinaria(data.getString("TRA_CTRAPROV"));
	    bean.setImportoSpeseNotifica(data.getBigDecimal("TRA_ITRACONT"));
	    bean.setImportoCostoTransazione(data.getBigDecimal("TRA_ITRACOTR"));
	    
	    bean.setCodiceProvincia(data.getString("APC_CAPCCPRO"));
	    bean.setCodiceComune(data.getString("APC_CAPCCCOM"));
	    
	    bean.idPSP = data.getString("RPT_CRPTIDPSP"); //PG190180_001
		
		return bean;
		
	}

	public Serializable beanToBean(Object bean) throws Exception {
		return null;
	}
	

}
