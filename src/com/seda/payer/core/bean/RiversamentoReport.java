package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import com.seda.data.spi.PageInfo;
/**
 * 
 * @author castellani
 *
 */

public class RiversamentoReport implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
	private java.lang.String codiceSocieta;
	private String dataRiversamentoDa;
    private String dataRiversamentoA;
    
    //output
    
    private String listXml;		 
    private PageInfo pageInfo;
    
    //
	private java.lang.String codiceUtente="";
	/**
	 * @param codiceSocieta
	 * @param dataRiversamentoDa
	 * @param dataRiversamentoA
	 * @param listXml
	 * @param pageInfo
	 * @param codiceUtente
	 * @param codiceEnte
	 * @param tipoUfficio
	 * @param codiceUfficio
	 * @param dataRiversamento
	 * @param enteBeneficiario
	 * @param tipoUfficioBeneficiario
	 * @param codiceUfficioBeneficiario
	 * @param descrEnteBeneficiario
	 * @param descrUfficioBeneficiario
	 * @param enteImpositore
	 * @param tipoUfficioImpositore
	 * @param codiceUfficioImpositore
	 * @param descrEnteImpositore
	 * @param descrUfficioImpositore
	 * @param tipologiaServizio
	 * @param denominazioneAnagrafica
	 * @param codiceFiscale
	 * @param bollettino
	 * @param documento
	 * @param verbale
	 * @param targa
	 * @param categoriaVeicolo
	 * @param scadenza
	 * @param indirizzo
	 * @param causale
	 * @param comuneDomicilioFiscale
	 * @param annoRiferimento
	 * @param canaleIncasso
	 * @param strumentoPagamento
	 * @param gatewayPagamento
	 * @param dataPagamento
	 * @param importoPagato
	 * @param commissioneGateway
	 * @param importoRiversato
	 * @param importoRecuperato
	 * @param commissioniPagate
	 * @param commissioniDaRecuperare
	 * @param chiaveTransazione
	 */
	public RiversamentoReport(String codiceSocieta, String dataRiversamentoDa,
			String dataRiversamentoA, String listXml, PageInfo pageInfo,
			String codiceUtente, String codiceEnte, String tipoUfficio,
			String codiceUfficio, Date dataRiversamento,
			String enteBeneficiario, String tipoUfficioBeneficiario,
			String codiceUfficioBeneficiario, String descrEnteBeneficiario,
			String descrUfficioBeneficiario, String enteImpositore,
			String tipoUfficioImpositore, String codiceUfficioImpositore,
			String descrEnteImpositore, String descrUfficioImpositore,
			String tipologiaServizio, String denominazioneAnagrafica,
			String codiceFiscale, String bollettino, String documento,
			String verbale, Timestamp dataVerbale, String targa, String categoriaVeicolo,
			Timestamp scadenza, String indirizzo, String causale,
			String comuneDomicilioFiscale, String annoRiferimento,
			String canaleIncasso, String strumentoPagamento,
			String gatewayPagamento, Timestamp dataPagamento,
			BigDecimal importoPagato, BigDecimal commissioneGateway,
			BigDecimal importoRiversato, BigDecimal importoRecuperato,
			BigDecimal commissioniPagate, BigDecimal commissioniDaRecuperare,
			String chiaveTransazione, BigDecimal speseNotifica, BigDecimal commissioniGestore) {
		super();
		this.codiceSocieta = codiceSocieta;
		this.dataRiversamentoDa = dataRiversamentoDa;
		this.dataRiversamentoA = dataRiversamentoA;
		this.listXml = listXml;
		this.pageInfo = pageInfo;
		this.codiceUtente = codiceUtente;
		this.codiceEnte = codiceEnte;
		this.tipoUfficio = tipoUfficio;
		this.codiceUfficio = codiceUfficio;
		this.dataRiversamento = dataRiversamento;
		this.enteBeneficiario = enteBeneficiario;
		this.tipoUfficioBeneficiario = tipoUfficioBeneficiario;
		this.codiceUfficioBeneficiario = codiceUfficioBeneficiario;
		this.descrEnteBeneficiario = descrEnteBeneficiario;
		this.descrUfficioBeneficiario = descrUfficioBeneficiario;
		this.enteImpositore = enteImpositore;
		this.tipoUfficioImpositore = tipoUfficioImpositore;
		this.codiceUfficioImpositore = codiceUfficioImpositore;
		this.descrEnteImpositore = descrEnteImpositore;
		this.descrUfficioImpositore = descrUfficioImpositore;
		this.tipologiaServizio = tipologiaServizio;
		this.denominazioneAnagrafica = denominazioneAnagrafica;
		this.codiceFiscale = codiceFiscale;
		this.bollettino = bollettino;
		this.documento = documento;
		this.verbale = verbale;
		this.dataVerbale = dataVerbale;
		this.targa = targa;
		this.categoriaVeicolo = categoriaVeicolo;
		this.scadenza = scadenza;
		this.indirizzo = indirizzo;
		this.causale = causale;
		this.comuneDomicilioFiscale = comuneDomicilioFiscale;
		this.annoRiferimento = annoRiferimento;
		this.canaleIncasso = canaleIncasso;
		this.strumentoPagamento = strumentoPagamento;
		this.gatewayPagamento = gatewayPagamento;
		this.dataPagamento = dataPagamento;
		this.importoPagato = importoPagato;
		this.commissioneGateway = commissioneGateway;
		this.importoRiversato = importoRiversato;
		this.importoRecuperato = importoRecuperato;
		this.commissioniPagate = commissioniPagate;
		this.commissioniDaRecuperare = commissioniDaRecuperare;
		this.speseNotifica = speseNotifica;
		this.commissioniGestore = commissioniGestore;
		this.chiaveTransazione = chiaveTransazione;
	}

	private String codiceEnte="";
	private String tipoUfficio="";
	private String codiceUfficio="";
	
	//
	private Date dataRiversamento;
	private String enteBeneficiario="";
	private String tipoUfficioBeneficiario="";
	private String codiceUfficioBeneficiario="";
	private String descrEnteBeneficiario="";
	private String descrUfficioBeneficiario="";
	private String enteImpositore="";
	private String tipoUfficioImpositore="";
	private String codiceUfficioImpositore="";
	private String descrEnteImpositore="";
	private String descrUfficioImpositore="";
	private String tipologiaServizio="";
	private String denominazioneAnagrafica="";
	private String codiceFiscale="";
	private String bollettino="";
	private String documento="";
	private String verbale="";
	private Timestamp dataVerbale;
	private String targa="";
	private String categoriaVeicolo="";
	private Timestamp scadenza;
	private String indirizzo="";
	private String causale="";
	private String comuneDomicilioFiscale="";
	private String annoRiferimento="";
	private String canaleIncasso="";
	private String strumentoPagamento="";
	private String gatewayPagamento="";
	private Timestamp dataPagamento;
	private BigDecimal importoPagato;
	private BigDecimal commissioneGateway;
	private BigDecimal importoRiversato;
	private BigDecimal importoRecuperato;
	private BigDecimal commissioniPagate;
	private BigDecimal commissioniDaRecuperare;
	private BigDecimal speseNotifica;
	private BigDecimal commissioniGestore;
	private String chiaveTransazione="";
		    
    public RiversamentoReport() { 
    
    }


	public java.lang.String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(java.lang.String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getDataRiversamentoDa() {
		return dataRiversamentoDa;
	}

	public void setDataRiversamentoDa(String dataRiversamentoDa) {
		this.dataRiversamentoDa = dataRiversamentoDa;
	}

	public String getDataRiversamentoA() {
		return dataRiversamentoA;
	}

	public void setDataRiversamentoA(String dataRiversamentoA) {
		this.dataRiversamentoA = dataRiversamentoA;
	}

	public java.lang.String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(java.lang.String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public Date getDataRiversamento() {
		return dataRiversamento;
	}

	public void setDataRiversamento(Date dataRiversamento) {
		this.dataRiversamento = dataRiversamento;
	}

	public String getEnteBeneficiario() {
		return enteBeneficiario;
	}

	public void setEnteBeneficiario(String enteBeneficiario) {
		this.enteBeneficiario = enteBeneficiario;
	}

	public String getTipoUfficioBeneficiario() {
		return tipoUfficioBeneficiario;
	}

	public void setTipoUfficioBeneficiario(String tipoUfficioBeneficiario) {
		this.tipoUfficioBeneficiario = tipoUfficioBeneficiario;
	}

	public String getCodiceUfficioBeneficiario() {
		return codiceUfficioBeneficiario;
	}

	public void setCodiceUfficioBeneficiario(String codiceUfficioBeneficiario) {
		this.codiceUfficioBeneficiario = codiceUfficioBeneficiario;
	}

	public String getDescrEnteBeneficiario() {
		return descrEnteBeneficiario;
	}

	public void setDescrEnteBeneficiario(String descrEnteBeneficiario) {
		this.descrEnteBeneficiario = descrEnteBeneficiario;
	}

	public String getDescrUfficioBeneficiario() {
		return descrUfficioBeneficiario;
	}

	public void setDescrUfficioBeneficiario(String descrUfficioBeneficiario) {
		this.descrUfficioBeneficiario = descrUfficioBeneficiario;
	}

	public String getEnteImpositore() {
		return enteImpositore;
	}

	public void setEnteImpositore(String enteImpositore) {
		this.enteImpositore = enteImpositore;
	}

	public String getTipoUfficioImpositore() {
		return tipoUfficioImpositore;
	}

	public void setTipoUfficioImpositore(String tipoUfficioImpositore) {
		this.tipoUfficioImpositore = tipoUfficioImpositore;
	}

	public String getCodiceUfficioImpositore() {
		return codiceUfficioImpositore;
	}

	public void setCodiceUfficioImpositore(String codiceUfficioImpositore) {
		this.codiceUfficioImpositore = codiceUfficioImpositore;
	}

	public String getDescrEnteImpositore() {
		return descrEnteImpositore;
	}

	public void setDescrEnteImpositore(String descrEnteImpositore) {
		this.descrEnteImpositore = descrEnteImpositore;
	}

	public String getDescrUfficioImpositore() {
		return descrUfficioImpositore;
	}

	public void setDescrUfficioImpositore(String descrUfficioImpositore) {
		this.descrUfficioImpositore = descrUfficioImpositore;
	}

	public String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public String getDenominazioneAnagrafica() {
		return denominazioneAnagrafica;
	}

	public void setDenominazioneAnagrafica(String denominazioneAnagrafica) {
		this.denominazioneAnagrafica = denominazioneAnagrafica;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getBollettino() {
		return bollettino;
	}

	public void setBollettino(String bollettino) {
		this.bollettino = bollettino;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getVerbale() {
		return verbale;
	}

	public void setVerbale(String verbale) {
		this.verbale = verbale;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getCategoriaVeicolo() {
		return categoriaVeicolo;
	}

	public void setCategoriaVeicolo(String categoriaVeicolo) {
		this.categoriaVeicolo = categoriaVeicolo;
	}

	public Timestamp getScadenza() {
		return scadenza;
	}

	public void setScadenza(Timestamp scadenza) {
		this.scadenza = scadenza;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getComuneDomicilioFiscale() {
		return comuneDomicilioFiscale;
	}

	public void setComuneDomicilioFiscale(String comuneDomicilioFiscale) {
		this.comuneDomicilioFiscale = comuneDomicilioFiscale;
	}

	public String getAnnoRiferimento() {
		return annoRiferimento;
	}

	public void setAnnoRiferimento(String annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	public String getCanaleIncasso() {
		return canaleIncasso;
	}

	public void setCanaleIncasso(String canaleIncasso) {
		this.canaleIncasso = canaleIncasso;
	}

	public String getStrumentoPagamento() {
		return strumentoPagamento;
	}

	public void setStrumentoPagamento(String strumentoPagamento) {
		this.strumentoPagamento = strumentoPagamento;
	}

	public String getGatewayPagamento() {
		return gatewayPagamento;
	}

	public void setGatewayPagamento(String gatewayPagamento) {
		this.gatewayPagamento = gatewayPagamento;
	}

	public Timestamp getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Timestamp dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getImportoPagato() {
		return importoPagato;
	}

	public void setImportoPagato(BigDecimal importoPagato) {
		this.importoPagato = importoPagato;
	}

	public BigDecimal getCommissioneGateway() {
		return commissioneGateway;
	}

	public void setCommissioneGateway(BigDecimal commissioneGateway) {
		this.commissioneGateway = commissioneGateway;
	}

	public BigDecimal getImportoRiversato() {
		return importoRiversato;
	}

	public void setImportoRiversato(BigDecimal importoRiversato) {
		this.importoRiversato = importoRiversato;
	}

	public BigDecimal getImportoRecuperato() {
		return importoRecuperato;
	}

	public void setImportoRecuperato(BigDecimal importoRecuperato) {
		this.importoRecuperato = importoRecuperato;
	}

	public BigDecimal getCommissioniPagate() {
		return commissioniPagate;
	}

	public void setCommissioniPagate(BigDecimal commissioniPagate) {
		this.commissioniPagate = commissioniPagate;
	}

	public BigDecimal getCommissioniDaRecuperare() {
		return commissioniDaRecuperare;
	}

	public void setCommissioniDaRecuperare(BigDecimal commissioniDaRecuperare) {
		this.commissioniDaRecuperare = commissioniDaRecuperare;
	}

	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setListXml(String listXml) {
		this.listXml = listXml;
	}

	public String getListXml() {
		return listXml;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}


	public void setDataVerbale(Timestamp dataVerbale) {
		this.dataVerbale = dataVerbale;
	}


	public Timestamp getDataVerbale() {
		return dataVerbale;
	}


	public void setSpeseNotifica(BigDecimal speseNotifica) {
		this.speseNotifica = speseNotifica;
	}


	public BigDecimal getSpeseNotifica() {
		return speseNotifica;
	}


	public void setCommissioniGestore(BigDecimal commissioniGestore) {
		this.commissioniGestore = commissioniGestore;
	}


	public BigDecimal getCommissioniGestore() {
		return commissioniGestore;
	}
    
	

}