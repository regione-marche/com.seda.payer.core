package com.seda.payer.core.bean;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;


import com.seda.payer.commons.bean.Lifecycle;
import com.seda.payer.commons.transform.TransformersIf;


public class Transazione extends Lifecycle implements Serializable, TransformersIf {

	private static final long serialVersionUID = 1L;
	
	private String chiaveTransazione;                                
    private String codiceSocieta;                                    
    private String chiaveGatewayDiPagamento;                         
    private Date dataInizioTransazione;                              
    private Date dataEffettivoPagamentoSuGateway;                    
    private String flagEsitoTransazione;                             
    private String codiceIdentificativoBanca;                        
    private String codiceAutorizzazioneBanca;                        
    private String indirizzoIpTerminalePagamento;                    
    private String emailContribuente;                                
    private String numeroSmsContribuente;                            
    private String denominazioneContribuentePerInvioPostaOrdinaria;  
    private String indirizzoContribuentePerInvioPostaOrdinaria;      
    private String capContribuentePerInvioPostaOrdinaria;            
    private String cittaContribuentePerInvioPostaOrdinaria;          
    private String provinciaContribuentePerInvioPostaOrdinaria;      
    private String invioNotificaBollettiniPerEmail;                  
    private String invioAutorizzazioneBancaPerEmailContribuente;     
    private String invioAutorizzazioneBancaPerEmailAmministratore;   
    private String invioNotificaAutorizzazioneBancaPerSms;           
    private String invioNotificaPerPostaOrdinaria;                   
    private Date dataAllineamentoBatchTransazione;                   
    private String codiceOrdineGateway;                              
    private BigDecimal importoTotaleTransazione;                     
    private BigDecimal importoCostoTransazione;  
    private BigDecimal importoCostoTransazioneEnte;
    private BigDecimal importoCostoGateway;                          
    private BigDecimal importoCostoSpeseDiNotifica;                  
    private String chiaveTransazioneSistemaEsterno;                  
    private String statoRendicontazione;
    private String noteGeneriche;
    private Long chiaveQuadratura;
	private String statoQuadratura;
	private String invioNotificaStatoPagamentoEmailContribuente;
	private String invioNotificaStatoPagamentoEmailAmministratore;
	private String statoRiversamento;
	private Date dataRiversamento;
	private String tipoStorno;
	private Integer numeroTentativiPagamento;
	private String flagRiversamentoAutomatico;
	private String operatoreInserimento;
	private String campoOpzionale1;
	private String campoOpzionale2;
	private String chiaveFlussoRendicontazioneRnincaext;
    private Date dataUltimoAggiornamento;                            
    private String opertoreUltimoAggiornamento;  
    private Date dataAccredito;
    
    //PYPOSTB
    private String idTerminalePOS; 
    
    //PYGTWTB
    private String canalePagamento;
	
	private String paymentRequest; //PG170260 CT
	
	//TRA_CTRANRFO
	private String numeroRiferimentoOrdineGateway; //PG200150
    

	public Transazione() {    	
    }
    


	public Transazione(String chiaveTransazione, String codiceSocieta,
			String chiaveGatewayDiPagamento, Date dataInizioTransazione,
			Date dataEffettivoPagamentoSuGateway, String flagEsitoTransazione,
			String codiceIdentificativoBanca, String codiceAutorizzazioneBanca,
			String indirizzoIpTerminalePagamento, String emailContribuente,
			String numeroSmsContribuente,
			String denominazioneContribuentePerInvioPostaOrdinaria,
			String indirizzoContribuentePerInvioPostaOrdinaria,
			String capContribuentePerInvioPostaOrdinaria,
			String cittaContribuentePerInvioPostaOrdinaria,
			String provinciaContribuentePerInvioPostaOrdinaria,
			String invioNotificaBollettiniPerEmail,
			String invioAutorizzazioneBancaPerEmailContribuente,
			String invioAutorizzazioneBancaPerEmailAmministratore,
			String invioNotificaAutorizzazioneBancaPerSms,
			String invioNotificaPerPostaOrdinaria,
			Date dataAllineamentoBatchTransazione, String codiceOrdineGateway,
			BigDecimal importoTotaleTransazione,
			BigDecimal importoCostoTransazione, BigDecimal importoCostoTransazioneEnte, 
			BigDecimal importoCostoGateway,
			BigDecimal importoCostoSpeseDiNotifica,
			String chiaveTransazioneSistemaEsterno,
		    String statoRendicontazione,
		    String noteGeneriche,
		    Long chiaveQuadratura,
		    String statoQuadratura,
		    String invioNotificaStatoPagamentoEmailContribuente,
		    String invioNotificaStatoPagamentoEmailAmministratore,
		    String statoRiversamento, Date dataRiversamento,
		    String tipoStorno,
			Integer numeroTentativiPagamento,
			String flagRiversamentoAutomatico,
			String operatoreInserimento,
			String campoOpzionale1,
			String campoOpzionale2,
			Date dataUltimoAggiornamento, String opertoreUltimoAggiornamento, Date dataAccredito) {
		super();
		this.chiaveTransazione = chiaveTransazione;
		this.codiceSocieta = codiceSocieta;
		this.chiaveGatewayDiPagamento = chiaveGatewayDiPagamento;
		this.dataInizioTransazione = dataInizioTransazione;
		this.dataEffettivoPagamentoSuGateway = dataEffettivoPagamentoSuGateway;
		this.flagEsitoTransazione = flagEsitoTransazione;
		this.codiceIdentificativoBanca = codiceIdentificativoBanca;
		this.codiceAutorizzazioneBanca = codiceAutorizzazioneBanca;
		this.indirizzoIpTerminalePagamento = indirizzoIpTerminalePagamento;
		this.emailContribuente = emailContribuente;
		this.numeroSmsContribuente = numeroSmsContribuente;
		this.denominazioneContribuentePerInvioPostaOrdinaria = denominazioneContribuentePerInvioPostaOrdinaria;
		this.indirizzoContribuentePerInvioPostaOrdinaria = indirizzoContribuentePerInvioPostaOrdinaria;
		this.capContribuentePerInvioPostaOrdinaria = capContribuentePerInvioPostaOrdinaria;
		this.cittaContribuentePerInvioPostaOrdinaria = cittaContribuentePerInvioPostaOrdinaria;
		this.provinciaContribuentePerInvioPostaOrdinaria = provinciaContribuentePerInvioPostaOrdinaria;
		this.invioNotificaBollettiniPerEmail = invioNotificaBollettiniPerEmail;
		this.invioAutorizzazioneBancaPerEmailContribuente = invioAutorizzazioneBancaPerEmailContribuente;
		this.invioAutorizzazioneBancaPerEmailAmministratore = invioAutorizzazioneBancaPerEmailAmministratore;
		this.invioNotificaAutorizzazioneBancaPerSms = invioNotificaAutorizzazioneBancaPerSms;
		this.invioNotificaPerPostaOrdinaria = invioNotificaPerPostaOrdinaria;
		this.dataAllineamentoBatchTransazione = dataAllineamentoBatchTransazione;
		this.codiceOrdineGateway = codiceOrdineGateway;
		this.importoTotaleTransazione = importoTotaleTransazione;
		this.importoCostoTransazione = importoCostoTransazione;
		this.importoCostoTransazioneEnte = importoCostoTransazioneEnte;
		this.importoCostoGateway = importoCostoGateway;
		this.importoCostoSpeseDiNotifica = importoCostoSpeseDiNotifica;
		this.chiaveTransazioneSistemaEsterno = chiaveTransazioneSistemaEsterno;
	    this.statoRendicontazione = statoRendicontazione;
	    this.noteGeneriche = noteGeneriche;
	    this.chiaveQuadratura = chiaveQuadratura;
	    this.statoQuadratura = statoQuadratura;
	    this.invioNotificaStatoPagamentoEmailContribuente = invioNotificaStatoPagamentoEmailContribuente;
	    this.invioNotificaStatoPagamentoEmailAmministratore = invioNotificaStatoPagamentoEmailAmministratore;
	    this.statoRiversamento = statoRiversamento;
	    this.dataRiversamento = dataRiversamento;
	    this.tipoStorno = tipoStorno;
	    this.numeroTentativiPagamento = numeroTentativiPagamento;
	    this.flagRiversamentoAutomatico = flagRiversamentoAutomatico;
	    this.operatoreInserimento = operatoreInserimento;
	    this.campoOpzionale1 = campoOpzionale1;
	    this.campoOpzionale2 = campoOpzionale2;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
		this.dataAccredito = dataAccredito;
	}



	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getChiaveGatewayDiPagamento() {
		return chiaveGatewayDiPagamento;
	}

	public void setChiaveGatewayDiPagamento(
			String chiaveGatewayDiPagamento) {
		this.chiaveGatewayDiPagamento = chiaveGatewayDiPagamento;
	}

	public Date getDataInizioTransazione() {
		return dataInizioTransazione;
	}

	public void setDataInizioTransazione(Date dataInizioTransazione) {
		this.dataInizioTransazione = dataInizioTransazione;
	}

	public Date getDataEffettivoPagamentoSuGateway() {
		return dataEffettivoPagamentoSuGateway;
	}

	public void setDataEffettivoPagamentoSuGateway(
			Date dataEffettivoPagamentoSuGateway) {
		this.dataEffettivoPagamentoSuGateway = dataEffettivoPagamentoSuGateway;
	}

	public String getFlagEsitoTransazione() {
		return flagEsitoTransazione;
	}

	public void setFlagEsitoTransazione(String flagEsitoTransazione) {
		this.flagEsitoTransazione = flagEsitoTransazione;
	}

	public String getCodiceIdentificativoBanca() {
		return codiceIdentificativoBanca;
	}

	public void setCodiceIdentificativoBanca(
			String codiceIdentificativoBanca) {
		this.codiceIdentificativoBanca = codiceIdentificativoBanca;
	}

	public String getCodiceAutorizzazioneBanca() {
		return codiceAutorizzazioneBanca;
	}

	public void setCodiceAutorizzazioneBanca(
			String codiceAutorizzazioneBanca) {
		this.codiceAutorizzazioneBanca = codiceAutorizzazioneBanca;
	}

	public String getIndirizzoIpTerminalePagamento() {
		return indirizzoIpTerminalePagamento;
	}

	public void setIndirizzoIpTerminalePagamento(
			String indirizzoIpTerminalePagamento) {
		this.indirizzoIpTerminalePagamento = indirizzoIpTerminalePagamento;
	}

	public String getEmailContribuente() {
		return emailContribuente;
	}

	public void setEmailContribuente(String emailContribuente) {
		this.emailContribuente = emailContribuente;
	}

	public String getNumeroSmsContribuente() {
		return numeroSmsContribuente;
	}

	public void setNumeroSmsContribuente(String numeroSmsContribuente) {
		this.numeroSmsContribuente = numeroSmsContribuente;
	}

	public String getDenominazioneContribuentePerInvioPostaOrdinaria() {
		return denominazioneContribuentePerInvioPostaOrdinaria;
	}

	public void setDenominazioneContribuentePerInvioPostaOrdinaria(
			String denominazioneContribuentePerInvioPostaOrdinaria) {
		this.denominazioneContribuentePerInvioPostaOrdinaria = denominazioneContribuentePerInvioPostaOrdinaria;
	}

	public String getIndirizzoContribuentePerInvioPostaOrdinaria() {
		return indirizzoContribuentePerInvioPostaOrdinaria;
	}

	public void setIndirizzoContribuentePerInvioPostaOrdinaria(
			String indirizzoContribuentePerInvioPostaOrdinaria) {
		this.indirizzoContribuentePerInvioPostaOrdinaria = indirizzoContribuentePerInvioPostaOrdinaria;
	}

	public String getCapContribuentePerInvioPostaOrdinaria() {
		return capContribuentePerInvioPostaOrdinaria;
	}

	public void setCapContribuentePerInvioPostaOrdinaria(
			String capContribuentePerInvioPostaOrdinaria) {
		this.capContribuentePerInvioPostaOrdinaria = capContribuentePerInvioPostaOrdinaria;
	}

	public String getCittaContribuentePerInvioPostaOrdinaria() {
		return cittaContribuentePerInvioPostaOrdinaria;
	}

	public void setCittaContribuentePerInvioPostaOrdinaria(
			String cittaContribuentePerInvioPostaOrdinaria) {
		this.cittaContribuentePerInvioPostaOrdinaria = cittaContribuentePerInvioPostaOrdinaria;
	}

	public String getProvinciaContribuentePerInvioPostaOrdinaria() {
		return provinciaContribuentePerInvioPostaOrdinaria;
	}

	public void setProvinciaContribuentePerInvioPostaOrdinaria(
			String provinciaContribuentePerInvioPostaOrdinaria) {
		this.provinciaContribuentePerInvioPostaOrdinaria = provinciaContribuentePerInvioPostaOrdinaria;
	}

	public String getInvioNotificaBollettiniPerEmail() {
		return invioNotificaBollettiniPerEmail;
	}

	public void setInvioNotificaBollettiniPerEmail(
			String invioNotificaBollettiniPerEmail) {
		this.invioNotificaBollettiniPerEmail = invioNotificaBollettiniPerEmail;
	}

	public String getInvioAutorizzazioneBancaPerEmailContribuente() {
		return invioAutorizzazioneBancaPerEmailContribuente;
	}

	public void setInvioAutorizzazioneBancaPerEmailContribuente(
			String invioAutorizzazioneBancaPerEmailContribuente) {
		this.invioAutorizzazioneBancaPerEmailContribuente = invioAutorizzazioneBancaPerEmailContribuente;
	}

	public String getInvioAutorizzazioneBancaPerEmailAmministratore() {
		return invioAutorizzazioneBancaPerEmailAmministratore;
	}

	public void setInvioAutorizzazioneBancaPerEmailAmministratore(
			String invioAutorizzazioneBancaPerEmailAmministratore) {
		this.invioAutorizzazioneBancaPerEmailAmministratore = invioAutorizzazioneBancaPerEmailAmministratore;
	}

	public String getInvioNotificaAutorizzazioneBancaPerSms() {
		return invioNotificaAutorizzazioneBancaPerSms;
	}

	public void setInvioNotificaAutorizzazioneBancaPerSms(
			String invioNotificaAutorizzazioneBancaPerSms) {
		this.invioNotificaAutorizzazioneBancaPerSms = invioNotificaAutorizzazioneBancaPerSms;
	}

	public String getInvioNotificaPerPostaOrdinaria() {
		return invioNotificaPerPostaOrdinaria;
	}

	public void setInvioNotificaPerPostaOrdinaria(
			String invioNotificaPerPostaOrdinaria) {
		this.invioNotificaPerPostaOrdinaria = invioNotificaPerPostaOrdinaria;
	}

	public Date getDataAllineamentoBatchTransazione() {
		return dataAllineamentoBatchTransazione;
	}

	public void setDataAllineamentoBatchTransazione(
			Date dataAllineamentoBatchTransazione) {
		this.dataAllineamentoBatchTransazione = dataAllineamentoBatchTransazione;
	}

	public String getCodiceOrdineGateway() {
		return codiceOrdineGateway;
	}

	public void setCodiceOrdineGateway(String codiceOrdineGateway) {
		this.codiceOrdineGateway = codiceOrdineGateway;
	}

	public BigDecimal getImportoTotaleTransazione() {
		return importoTotaleTransazione;
	}

	public void setImportoTotaleTransazione(
			BigDecimal importoTotaleTransazione) {
		this.importoTotaleTransazione = importoTotaleTransazione;
	}

	public BigDecimal getImportoCostoTransazione() {
		return importoCostoTransazione;
	}

	public void setImportoCostoTransazione(
			BigDecimal importoCostoTransazione) {
		this.importoCostoTransazione = importoCostoTransazione;
	}

	public void setImportoCostoTransazioneEnte(
			BigDecimal importoCostoTransazioneEnte) {
		this.importoCostoTransazioneEnte = importoCostoTransazioneEnte;
	}

	public BigDecimal getImportoCostoTransazioneEnte() {
		return importoCostoTransazioneEnte;
	}

	public BigDecimal getImportoCostoGateway() {
		return importoCostoGateway;
	}

	public void setImportoCostoGateway(BigDecimal importoCostoGateway) {
		this.importoCostoGateway = importoCostoGateway;
	}

	public BigDecimal getImportoCostoSpeseDiNotifica() {
		return importoCostoSpeseDiNotifica;
	}

	public void setImportoCostoSpeseDiNotifica(
			BigDecimal importoCostoSpeseDiNotifica) {
		this.importoCostoSpeseDiNotifica = importoCostoSpeseDiNotifica;
	}

	public String getChiaveTransazioneSistemaEsterno() {
		return chiaveTransazioneSistemaEsterno;
	}

	public void setChiaveTransazioneSistemaEsterno(
			String chiaveTransazioneSistemaEsterno) {
		this.chiaveTransazioneSistemaEsterno = chiaveTransazioneSistemaEsterno;
	}

	public Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOpertoreUltimoAggiornamento() {
		return opertoreUltimoAggiornamento;
	}

	public void setOpertoreUltimoAggiornamento(
			String opertoreUltimoAggiornamento) {
		this.opertoreUltimoAggiornamento = opertoreUltimoAggiornamento;
	}

	public String getStatoRendicontazione() {
		return statoRendicontazione;
	}

	public String getNoteGeneriche() {
		return noteGeneriche;
	}

	public Long getChiaveQuadratura() {
		return chiaveQuadratura;
	}

	public String getStatoQuadratura() {
		return statoQuadratura;
	}

	public void setStatoRendicontazione(String statoRendicontazione) {
		this.statoRendicontazione = statoRendicontazione;
	}

	public void setNoteGeneriche(String noteGeneriche) {
		this.noteGeneriche = noteGeneriche;
	}

	public void setChiaveQuadratura(Long chiaveQuadratura) {
		this.chiaveQuadratura = chiaveQuadratura;
	}

	public void setStatoQuadratura(String statoQuadratura) {
		this.statoQuadratura = statoQuadratura;
	}

	public void setInvioNotificaStatoPagamentoEmailContribuente(
			String invioNotificaStatoPagamentoEmailContribuente) {
		this.invioNotificaStatoPagamentoEmailContribuente = invioNotificaStatoPagamentoEmailContribuente;
	}

	public String getInvioNotificaStatoPagamentoEmailContribuente() {
		return invioNotificaStatoPagamentoEmailContribuente;
	}

	public void setInvioNotificaStatoPagamentoEmailAmministratore(
			String invioNotificaStatoPagamentoEmailAmministratore) {
		this.invioNotificaStatoPagamentoEmailAmministratore = invioNotificaStatoPagamentoEmailAmministratore;
	}

	public String getInvioNotificaStatoPagamentoEmailAmministratore() {
		return invioNotificaStatoPagamentoEmailAmministratore;
	}
	
	public void setStatoRiversamento(String statoRiversamento) {
		this.statoRiversamento = statoRiversamento;
	}

	public String getStatoRiversamento() {
		return statoRiversamento;
	}

	public void setDataRiversamento(Date dataRiversamento) {
		this.dataRiversamento = dataRiversamento;
	}

	public Date getDataRiversamento() {
		return dataRiversamento;
	}

	public void setTipoStorno(String tipoStorno) {
		this.tipoStorno = tipoStorno;
	}

	public String getTipoStorno() {
		return tipoStorno;
	}

	public void setNumeroTentativiPagamento(Integer numeroTentativiPagamento) {
		this.numeroTentativiPagamento = numeroTentativiPagamento;
	}

	public Integer getNumeroTentativiPagamento() {
		return numeroTentativiPagamento;
	}

	public void setFlagRiversamentoAutomatico(String flagRiversamentoAutomatico) {
		this.flagRiversamentoAutomatico = flagRiversamentoAutomatico;
	}



	public String getFlagRiversamentoAutomatico() {
		return flagRiversamentoAutomatico;
	}



	public void setOperatoreInserimento(String operatoreInserimento) {
		this.operatoreInserimento = operatoreInserimento;
	}

	public String getOperatoreInserimento() {
		return operatoreInserimento;
	}

	public void setCampoOpzionale1(String campoOpzionale1) {
		this.campoOpzionale1 = campoOpzionale1;
	}

	public String getCampoOpzionale1() {
		return campoOpzionale1;
	}

	public void setCampoOpzionale2(String campoOpzionale2) {
		this.campoOpzionale2 = campoOpzionale2;
	}

	public String getCampoOpzionale2() {
		return campoOpzionale2;
	}

	public String getChiaveFlussoRendicontazioneRnincaext() {
		return chiaveFlussoRendicontazioneRnincaext;
	}

	public void setChiaveFlussoRendicontazioneRnincaext(
			String chiaveFlussoRendicontazioneRnincaext) {
		this.chiaveFlussoRendicontazioneRnincaext = chiaveFlussoRendicontazioneRnincaext;
	}
		
	/**
	 * @param idTerminalePOS the idTerminalePOS to set
	 */
	public void setIdTerminalePOS(String idTerminalePOS) {
		this.idTerminalePOS = idTerminalePOS;
	}



	/**
	 * @return the idTerminalePOS
	 */
	public String getIdTerminalePOS() {
		return idTerminalePOS;
	}



	public void setCanalePagamento(String canalePagamento) {
		this.canalePagamento = canalePagamento;
	}



	public String getCanalePagamento() {
		return canalePagamento;
	}



	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	//PG200150
    public String getNumeroRiferimentoOrdineGateway() {
		return numeroRiferimentoOrdineGateway;
	}
	public void setNumeroRiferimentoOrdineGateway(
			String numeroRiferimentoOrdineGateway) {
		this.numeroRiferimentoOrdineGateway = numeroRiferimentoOrdineGateway;
	}

	/* (non-Javadoc)
	 * @see com.seda.payer.commons.transform.TransformersIf#beanToBean(java.lang.Object)
	 */
	public Serializable beanToBean(Object arg0) throws Exception{		 
		return this;
	} 
	
	public static  Transazione getBean(ResultSet data)throws SQLException
	{
		Transazione bean = new Transazione();
		
		bean.chiaveTransazione = data.getString("TRA_KTRAKTRA");
		bean.codiceSocieta = data.getString("TRA_CSOCCSOC");
		bean.chiaveGatewayDiPagamento = data.getString("TRA_KGTWKGTW");
		//bean.dataInizioTransazione = data.getTimestamp("TRA_GTRADTRA");
		bean.dataInizioTransazione = new java.util.Date(new java.sql.Date(data.getTimestamp("TRA_GTRADTRA").getTime()).getTime());
		//bean.dataEffettivoPagamentoSuGateway = data.getTimestamp("TRA_GTRADPAG");
		bean.dataEffettivoPagamentoSuGateway=new java.util.Date(new java.sql.Date(data.getTimestamp("TRA_GTRADPAG").getTime()).getTime());
		bean.flagEsitoTransazione = data.getString("TRA_FTRAFESI");
		bean.codiceIdentificativoBanca = data.getString("TRA_CTRAIDBA");
		bean.codiceAutorizzazioneBanca = data.getString("TRA_CTRAAUBA");
		bean.indirizzoIpTerminalePagamento = data.getString("TRA_DTRAINIP");
		bean.emailContribuente = data.getString("TRA_ETRAECON");
		bean.numeroSmsContribuente = data.getString("TRA_CTRANSMS");
		bean.denominazioneContribuentePerInvioPostaOrdinaria = data.getString("TRA_DTRADENO");
		bean.indirizzoContribuentePerInvioPostaOrdinaria = data.getString("TRA_DTRAINDI");
		bean.capContribuentePerInvioPostaOrdinaria = data.getString("TRA_CTRACCAP");
		bean.cittaContribuentePerInvioPostaOrdinaria = data.getString("TRA_DTRACITT");
		bean.provinciaContribuentePerInvioPostaOrdinaria = data.getString("TRA_CTRAPROV");
		bean.invioNotificaBollettiniPerEmail = data.getString("TRA_FTRABOLL");
		bean.invioAutorizzazioneBancaPerEmailContribuente = data.getString("TRA_FTRACONT");
		bean.invioAutorizzazioneBancaPerEmailAmministratore = data.getString("TRA_FTRAAMMI");
		bean.invioNotificaAutorizzazioneBancaPerSms = data.getString("TRA_FTRAFSMS");
		bean.invioNotificaPerPostaOrdinaria = data.getString("TRA_FTRAPOST");
		bean.dataAllineamentoBatchTransazione = new java.util.Date(new java.sql.Date(data.getTimestamp("TRA_GTRADALL").getTime()).getTime());
		bean.codiceOrdineGateway = data.getString("TRA_CTRACORD");
		bean.importoTotaleTransazione = data.getBigDecimal("TRA_ITRAITOT");
		bean.importoCostoTransazione = data.getBigDecimal("TRA_ITRACOTR");
		bean.importoCostoTransazioneEnte = data.getBigDecimal("TRA_ITRACENT");
		bean.importoCostoGateway = data.getBigDecimal("TRA_ITRACOGW");
		bean.importoCostoSpeseDiNotifica = data.getBigDecimal("TRA_ITRACONT");
		bean.chiaveTransazioneSistemaEsterno = data.getString("TRA_KTRAKEST");
		bean.statoRendicontazione = data.getString("TRA_CTRAREND");
		bean.noteGeneriche = data.getString("TRA_DTRANOTE");
		bean.chiaveQuadratura = data.getLong("TRA_PQUAPKEY");
		bean.statoQuadratura = data.getString("TRA_CTRAQUAD");
		bean.invioNotificaStatoPagamentoEmailContribuente = data.getString("TRA_FTRASCAC");
		bean.invioNotificaStatoPagamentoEmailAmministratore = data.getString("TRA_FTRASCAA");
		bean.statoRiversamento = data.getString("TRA_FTRARIVE");
	    bean.dataRiversamento = data.getDate("TRA_GTRARIVE");
		bean.tipoStorno = data.getString("TRA_TTRASTOR");
		bean.numeroTentativiPagamento = data.getInt("TRA_NTRATENT");
		bean.flagRiversamentoAutomatico = data.getString("TRA_FTRARIVA"); 
		bean.operatoreInserimento = data.getString("TRA_CTRAOPIN");
		bean.campoOpzionale1 = data.getString("TRA_CTRAOPT1");
		bean.campoOpzionale2 = data.getString("TRA_CTRAOPT2");
	    
		bean.dataUltimoAggiornamento = new java.util.Date(new java.sql.Date(data.getTimestamp("TRA_GTRAGAGG").getTime()).getTime());
		bean.opertoreUltimoAggiornamento = data.getString("TRA_CTRACOPE");
		bean.dataAccredito = new java.util.Date(new java.sql.Date(data.getTimestamp("TRA_GTRAACCR").getTime()).getTime());
		return bean;
	}
	
	public static Transazione getBean_Extended(ResultSet data)throws SQLException
	{
		Transazione bean = getBean(data);
		bean.idTerminalePOS = data.getString("POS_CPOSTEID");
		bean.canalePagamento = data.getString("GTW_KCANKCAN");
		
		bean.setPaymentRequest(data.getString("MIP_CMIPPREQ")); //PG170560 CT
		return bean;
	}
	
	//PG200150
	public static Transazione getBean_ExtendedKPOF(ResultSet data)throws SQLException
	{
		Transazione bean = getBean(data);
		bean.chiaveFlussoRendicontazioneRnincaext = data.getString("TRA_CTRAKPOF");
		bean.numeroRiferimentoOrdineGateway = data.getString("TRA_CTRANRFO");
		
		return bean;
	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
		
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
	
	}
	
	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTransazione);
		arg.setString(2, this.codiceSocieta);
		arg.setString(3, this.chiaveGatewayDiPagamento);
		arg.setTimestamp(4, new java.sql.Timestamp(this.dataInizioTransazione.getTime()));
		arg.setTimestamp(5, new java.sql.Timestamp(this.dataEffettivoPagamentoSuGateway.getTime()));
		arg.setString(6, this.flagEsitoTransazione);
		arg.setString(7, this.codiceIdentificativoBanca);
		arg.setString(8, this.codiceAutorizzazioneBanca);
		arg.setString(9, this.indirizzoIpTerminalePagamento);
		arg.setString(10, this.emailContribuente);
		arg.setString(11, this.numeroSmsContribuente);
		arg.setString(12, this.denominazioneContribuentePerInvioPostaOrdinaria);
		arg.setString(13, this.indirizzoContribuentePerInvioPostaOrdinaria);
		arg.setString(14, this.capContribuentePerInvioPostaOrdinaria);
		arg.setString(15, this.cittaContribuentePerInvioPostaOrdinaria);
		arg.setString(16, this.provinciaContribuentePerInvioPostaOrdinaria);
		arg.setString(17, this.invioNotificaBollettiniPerEmail);
		arg.setString(18, this.invioAutorizzazioneBancaPerEmailContribuente);
		arg.setString(19, this.invioAutorizzazioneBancaPerEmailAmministratore);
		arg.setString(20, this.invioNotificaAutorizzazioneBancaPerSms);
		arg.setString(21, this.invioNotificaPerPostaOrdinaria);
		arg.setTimestamp(22, new java.sql.Timestamp(this.dataAllineamentoBatchTransazione.getTime()));
		arg.setString(23, this.codiceOrdineGateway);
		arg.setBigDecimal(24, this.importoTotaleTransazione);
		arg.setBigDecimal(25, this.importoCostoTransazione);
		arg.setBigDecimal(26, this.importoCostoGateway);
		arg.setBigDecimal(27, this.importoCostoSpeseDiNotifica);
		arg.setString(28, this.chiaveTransazioneSistemaEsterno);
		arg.setString(29, this.statoRendicontazione);
		arg.setString(30, this.noteGeneriche);
		arg.setLong(31, this.chiaveQuadratura);
		arg.setString(32, this.statoQuadratura);
		arg.setString(33, this.invioNotificaStatoPagamentoEmailContribuente);
		arg.setString(34, this.invioNotificaStatoPagamentoEmailAmministratore);
		arg.setString(35, this.statoRiversamento);
		arg.setDate(36, new java.sql.Date(this.dataRiversamento.getTime()));
		arg.setString(37, this.opertoreUltimoAggiornamento);
		arg.setBigDecimal(38, this.importoCostoTransazioneEnte);
		arg.setString(39, this.tipoStorno);
		arg.setInt(40, this.numeroTentativiPagamento);
		arg.setString(41, this.flagRiversamentoAutomatico);
		arg.setString(42, this.operatoreInserimento);
		arg.setString(43, this.campoOpzionale1);
		arg.setString(44, this.campoOpzionale2);
		arg.setString(45, this.campoOpzionale2);
		arg.setTimestamp(46, new java.sql.Timestamp(this.dataAccredito.getTime()));
		arg.setString(47, hashEncode(this.chiaveTransazione));
		arg.registerOutParameter(48, Types.INTEGER);
		arg.registerOutParameter(49, Types.INTEGER);
	}
	
	private String hashEncode(String cTran) {
		String shaKey="";
	    try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] dataBytes = cTran.getBytes("UTF-8");
			md.update(dataBytes);
			byte[] mdbytes = md.digest();
			shaKey=Base64.encode(mdbytes);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Si sono verificati problemi nella generazione del codice hask associato alla chiave della transazione");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return shaKey;
	}



	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {		
	}

	public void setDataAccredito(Date dataAccredito) {
		this.dataAccredito = dataAccredito;
	}

	public Date getDataAccredito() {
		return dataAccredito;
	}



	public void setPaymentRequest(String paymentRequest) {
		this.paymentRequest = paymentRequest;
	}



	public String getPaymentRequest() {
		return paymentRequest;
	}
}