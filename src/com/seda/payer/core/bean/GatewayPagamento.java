package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.seda.payer.commons.bean.Lifecycle;

public class GatewayPagamento extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String codiceNegozio;
	private java.lang.String chiaveGateway;
	private java.lang.String urlApiEndpoint;
	private java.lang.String apiUser;
	private java.lang.String apiPassword;
	private java.lang.String apiSignature;
	private java.lang.String urlApiImage;
	private java.lang.String apiVersion;
	private java.lang.String descrizioneGateway;
	private java.lang.String pathImgLogo;
	private java.lang.String urlSitoWebGateway;
	private java.lang.String tipoGateway;
	private java.lang.String emailNotificaAdmin;
	private java.lang.String urlApiRedirect;
	private java.lang.String urlApiCancel;
	private java.lang.String codiceMacAvvio;
	private java.lang.String codiceMacEsito;
	private java.lang.String tipoAutorizzazione;
	private java.lang.String tipoContabilizzazione;
	private java.lang.String opzioniAggiuntive;
	private java.lang.String flagAttivazione;
	private java.lang.String codiceOperatore;
	private java.lang.String codiceSIAAziendaDestinataria;
	private java.lang.String codiceCINBancaMittente;
	private java.lang.String codiceABIBancaMittente;
	private java.lang.String codiceCABBancaMittente;
	private java.lang.String codiceContoCorrente;
	private int deltaGiorniDataContabile;
	private BigDecimal importoScostamento;
    private User user;
    private CanalePagamento canale;
    private CartaPagamento carta;
    

    
    public GatewayPagamento() { 
    	user = new User();
    	canale = new CanalePagamento();
    	carta = new CartaPagamento();
    }

    public GatewayPagamento(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	codiceNegozio = data.getString("GTW_CGTWCCRN");
    	chiaveGateway = data.getString("GTW_KGTWKGTW");
    	urlApiEndpoint = data.getString("GTW_DGTWAEND");
    	apiUser = data.getString("GTW_DGTWAUSR");
    	apiPassword = data.getString("GTW_DGTWAPWD");
    	apiSignature = data.getString("GTW_DGTWASIG");
    	urlApiImage = data.getString("GTW_DGTWAIMG");
    	apiVersion = data.getString("GTW_DGTWAVER");
    	descrizioneGateway = data.getString("GTW_DGTWDGTW");
    	pathImgLogo = data.getString("GTW_DGTWPIMG");
    	urlSitoWebGateway = data.getString("GTW_DGTWURLS");
    	tipoGateway = data.getString("GTW_TGTWTIPO");
    	emailNotificaAdmin = data.getString("GTW_EGTWNOTI");
    	urlApiRedirect = data.getString("GTW_DGTWARED");
    	urlApiCancel = data.getString("GTW_DGTWACLS");
    	codiceMacAvvio = data.getString("GTW_CGTWMACA");
    	codiceMacEsito = data.getString("GTW_CGTWMACE");
    	tipoAutorizzazione = data.getString("GTW_TGTWAUTO");
    	tipoContabilizzazione = data.getString("GTW_TGTWCONT");
    	opzioniAggiuntive = data.getString("GTW_CGTWOPTI");
    	flagAttivazione = data.getString("GTW_FGTWFATT");
    	codiceOperatore = data.getString("GTW_CGTWCOPE");
    	codiceSIAAziendaDestinataria = data.getString("GTW_CGTWCSIA");
    	codiceCINBancaMittente = data.getString("GTW_CGTWCCIN");
    	codiceABIBancaMittente = data.getString("GTW_CGTWCABI");
    	codiceCABBancaMittente = data.getString("GTW_CGTWCCAB");
    	codiceContoCorrente = data.getString("GTW_CGTWCCCB");
    	deltaGiorniDataContabile = data.getInt("GTW_NGTWDELT");
    	importoScostamento = data.getBigDecimal("GTW_IGTWSCOS");
        user = new User(); {
        	user.getCompany().setCompanyCode(data.getString("GTW_CSOCCSOC"));
        	user.setUserCode(data.getString("GTW_CUTECUTE"));
        }
        canale = new CanalePagamento(); {
        	canale.setChiaveCanalePagamento(data.getString("GTW_KCANKCAN"));
        }
        carta = new CartaPagamento(); {
        	carta.setCodiceCartaPagamento(data.getString("GTW_CCARCCAR"));
        }
        
    }


	public java.lang.String getCodiceNegozio() {
		return codiceNegozio;
	}

	public void setCodiceNegozio(java.lang.String codiceNegozio) {
		this.codiceNegozio = codiceNegozio;
	}

	public java.lang.String getChiaveGateway() {
		return chiaveGateway;
	}

	public void setChiaveGateway(java.lang.String chiaveGateway) {
		this.chiaveGateway = chiaveGateway;
	}

	public java.lang.String getUrlApiEndpoint() {
		return urlApiEndpoint;
	}

	public void setUrlApiEndpoint(java.lang.String urlApiEndpoint) {
		this.urlApiEndpoint = urlApiEndpoint;
	}

	public java.lang.String getApiUser() {
		return apiUser;
	}

	public void setApiUser(java.lang.String apiUser) {
		this.apiUser = apiUser;
	}

	public java.lang.String getApiPassword() {
		return apiPassword;
	}

	public void setApiPassword(java.lang.String apiPassword) {
		this.apiPassword = apiPassword;
	}

	public java.lang.String getApiSignature() {
		return apiSignature;
	}

	public void setApiSignature(java.lang.String apiSignature) {
		this.apiSignature = apiSignature;
	}

	public java.lang.String getUrlApiImage() {
		return urlApiImage;
	}

	public void setUrlApiImage(java.lang.String urlApiImage) {
		this.urlApiImage = urlApiImage;
	}

	public java.lang.String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(java.lang.String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public java.lang.String getDescrizioneGateway() {
		return descrizioneGateway;
	}

	public void setDescrizioneGateway(java.lang.String descrizioneGateway) {
		this.descrizioneGateway = descrizioneGateway;
	}

	public java.lang.String getUrlSitoWebGateway() {
		return urlSitoWebGateway;
	}

	public void setUrlSitoWebGateway(java.lang.String urlSitoWebGateway) {
		this.urlSitoWebGateway = urlSitoWebGateway;
	}

	public java.lang.String getTipoGateway() {
		return tipoGateway;
	}

	public void setTipoGateway(java.lang.String tipoGateway) {
		this.tipoGateway = tipoGateway;
	}

	public java.lang.String getEmailNotificaAdmin() {
		return emailNotificaAdmin;
	}

	public void setEmailNotificaAdmin(java.lang.String emailNotificaAdmin) {
		this.emailNotificaAdmin = emailNotificaAdmin;
	}

	public java.lang.String getUrlApiRedirect() {
		return urlApiRedirect;
	}

	public void setUrlApiRedirect(java.lang.String urlApiRedirect) {
		this.urlApiRedirect = urlApiRedirect;
	}

	public java.lang.String getCodiceMacAvvio() {
		return codiceMacAvvio;
	}

	public void setCodiceMacAvvio(java.lang.String codiceMacAvvio) {
		this.codiceMacAvvio = codiceMacAvvio;
	}

	public java.lang.String getCodiceMacEsito() {
		return codiceMacEsito;
	}

	public void setCodiceMacEsito(java.lang.String codiceMacEsito) {
		this.codiceMacEsito = codiceMacEsito;
	}

	public java.lang.String getTipoAutorizzazione() {
		return tipoAutorizzazione;
	}

	public void setTipoAutorizzazione(java.lang.String tipoAutorizzazione) {
		this.tipoAutorizzazione = tipoAutorizzazione;
	}

	public java.lang.String getTipoContabilizzazione() {
		return tipoContabilizzazione;
	}

	public void setTipoContabilizzazione(java.lang.String tipoContabilizzazione) {
		this.tipoContabilizzazione = tipoContabilizzazione;
	}

	public java.lang.String getOpzioniAggiuntive() {
		return opzioniAggiuntive;
	}

	public void setOpzioniAggiuntive(java.lang.String opzioniAggiuntive) {
		this.opzioniAggiuntive = opzioniAggiuntive;
	}

	public java.lang.String getFlagAttivazione() {
		return flagAttivazione;
	}

	public void setFlagAttivazione(java.lang.String flagAttivazione) {
		this.flagAttivazione = flagAttivazione;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}
	
	public java.lang.String getCodiceSIAAziendaDestinataria() {
		return codiceSIAAziendaDestinataria;
	}

	public void setCodiceSIAAziendaDestinataria(java.lang.String codiceSIAAziendaDestinataria) {
		this.codiceSIAAziendaDestinataria = codiceSIAAziendaDestinataria;
	}

	public java.lang.String getCodiceCINBancaMittente() {
		return codiceCINBancaMittente;
	}

	public void setCodiceCINBancaMittente(java.lang.String codiceCINBancaMittente) {
		this.codiceCINBancaMittente = codiceCINBancaMittente;
	}

	public java.lang.String getCodiceABIBancaMittente() {
		return codiceABIBancaMittente;
	}

	public void setCodiceABIBancaMittente(java.lang.String codiceABIBancaMittente) {
		this.codiceABIBancaMittente = codiceABIBancaMittente;
	}

	public java.lang.String getCodiceCABBancaMittente() {
		return codiceCABBancaMittente;
	}

	public void setCodiceCABBancaMittente(java.lang.String codiceCABBancaMittente) {
		this.codiceCABBancaMittente = codiceCABBancaMittente;
	}

	public java.lang.String getCodiceContoCorrente() {
		return codiceContoCorrente;
	}

	public void setCodiceContoCorrente(java.lang.String codiceContoCorrente) {
		this.codiceContoCorrente = codiceContoCorrente;
	}

	public int getDeltaGiorniDataContabile() {
		return deltaGiorniDataContabile;
	}

	public void setDeltaGiorniDataContabile(int deltaGiorniDataContabile) {
		this.deltaGiorniDataContabile = deltaGiorniDataContabile;
	}

	public BigDecimal getImportoScostamento() {
		return importoScostamento;
	}

	public void setImportoScostamento(BigDecimal importoScostamento) {
		this.importoScostamento = importoScostamento;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CanalePagamento getCanale() {
		return canale;
	}

	public void setCanale(CanalePagamento canale) {
		this.canale = canale;
	}

	public CartaPagamento getCarta() {
		return carta;
	}

	public void setCarta(CartaPagamento carta) {
		this.carta = carta;
	}
	

	public java.lang.String getPathImgLogo() {
		return pathImgLogo;
	}

	public void setPathImgLogo(java.lang.String pathImgLogo) {
		this.pathImgLogo = pathImgLogo;
	}

	public java.lang.String getUrlApiCancel() {
		return urlApiCancel;
	}

	public void setUrlApiCancel(java.lang.String urlApiCancel) {
		this.urlApiCancel = urlApiCancel;
	}
	
	

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.user.getCompany().getCompanyCode());
		arg.setString(2, this.user.getUserCode());
		arg.setString(3, this.canale.getChiaveCanalePagamento());
		arg.setString(4, this.getDescrizioneGateway());
		arg.setString(5, this.getPathImgLogo());		
		arg.setString(6, this.getUrlSitoWebGateway());
		arg.setString(7, this.getTipoGateway());
		arg.setString(8, this.getEmailNotificaAdmin());
		arg.setString(9, this.getUrlApiEndpoint());
		arg.setString(10, this.getApiUser());
		arg.setString(11, this.getApiPassword());
		arg.setString(12, this.getApiSignature());
		arg.setString(13, this.getUrlApiImage());
		arg.setString(14, this.getApiVersion());
		arg.setString(15, this.getUrlApiRedirect());
		arg.setString(16, this.getUrlApiCancel());
		arg.setString(17, this.getCodiceNegozio());
		arg.setString(18, this.getCodiceMacAvvio());
		arg.setString(19, this.getCodiceMacEsito());
		arg.setString(20, this.getTipoAutorizzazione());
		arg.setString(21, this.getTipoContabilizzazione());
		arg.setString(22, this.getOpzioniAggiuntive());
		arg.setString(23, this.carta.getCodiceCartaPagamento());
		arg.setString(24, this.getFlagAttivazione());
		arg.setString(25, this.getCodiceSIAAziendaDestinataria());
		arg.setString(26, this.getCodiceCINBancaMittente());
		arg.setString(27, this.getCodiceABIBancaMittente());
		arg.setString(28, this.getCodiceCABBancaMittente());
		arg.setString(29, this.getCodiceContoCorrente());
		arg.setInt(30, this.getDeltaGiorniDataContabile());
		arg.setBigDecimal(31, this.getImportoScostamento());
		arg.setString(32, this.getCodiceOperatore());  // (OPERATORE ULTIMO AGGIORNAMENTO)
		
		
	}
	
	/*
	@Override
	public void onSaveOLD(CallableStatement arg) throws SQLException {
		arg.setString(1, this.user.getCompany().getCompanyCode());
		arg.setString(2, this.user.getUserCode());
		arg.setString(3, this.canale.getChiaveCanalePagamento());
		arg.setString(4, this.getDescrizioneGateway());
		arg.setString(5, this.getUrlSitoWebGateway());
		arg.setString(6, this.getTipoGateway());
		arg.setString(7, this.getEmailNotificaAdmin());
		arg.setString(8, this.getUrlApiEndpoint());
		arg.setString(9, this.getApiUser());
		arg.setString(10, this.getApiPassword());
		arg.setString(11, this.getApiSignature());
		arg.setString(12, this.getUrlApiImage());
		arg.setString(13, this.getApiVersion());
		arg.setString(14, this.getUrlApiRedirect());
		arg.setString(15, this.getCodiceNegozio());
		arg.setString(16, this.getCodiceMacAvvio());
		arg.setString(17, this.getCodiceMacEsito());
		arg.setString(18, this.getTipoAutorizzazione());
		arg.setString(19, this.getTipoContabilizzazione());
		arg.setString(20, this.getOpzioniAggiuntive());
		arg.setString(21, this.carta.getCodiceCartaPagamento());
		arg.setString(22, this.getFlagAttivazioneProxy());
		arg.setString(23, this.getIndirizzoServerProxy());
		arg.setString(24, this.getPortaServerProxy());
		arg.setString(25, this.getUtenteProxy());
		arg.setString(26, this.getPasswordProxy());
		arg.setString(27, this.getFlagAttivazione());
		arg.setString(28, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
	}*/
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveGateway);
		arg.setString(2, this.user.getCompany().getCompanyCode());
		arg.setString(3, this.user.getUserCode());
		arg.setString(4, this.canale.getChiaveCanalePagamento());
		arg.setString(5, this.getDescrizioneGateway());
		arg.setString(6, this.getPathImgLogo());		
		arg.setString(7, this.getUrlSitoWebGateway());
		arg.setString(8, this.getTipoGateway());
		arg.setString(9, this.getEmailNotificaAdmin());
		arg.setString(10, this.getUrlApiEndpoint());
		arg.setString(11, this.getApiUser());
		arg.setString(12, this.getApiPassword());
		arg.setString(13, this.getApiSignature());
		arg.setString(14, this.getUrlApiImage());
		arg.setString(15, this.getApiVersion());
		arg.setString(16, this.getUrlApiRedirect());
		arg.setString(17, this.getUrlApiCancel());
		arg.setString(18, this.getCodiceNegozio());
		arg.setString(19, this.getCodiceMacAvvio());
		arg.setString(20, this.getCodiceMacEsito());
		arg.setString(21, this.getTipoAutorizzazione());
		arg.setString(22, this.getTipoContabilizzazione());
		arg.setString(23, this.getOpzioniAggiuntive());
		arg.setString(24, this.carta.getCodiceCartaPagamento());
		arg.setString(25, this.getFlagAttivazione());
		arg.setString(26, this.getCodiceSIAAziendaDestinataria());
		arg.setString(27, this.getCodiceCINBancaMittente());
		arg.setString(28, this.getCodiceABIBancaMittente());
		arg.setString(29, this.getCodiceCABBancaMittente());
		arg.setString(30, this.getCodiceContoCorrente());
		arg.setInt(31, this.getDeltaGiorniDataContabile());
		arg.setBigDecimal(32, this.getImportoScostamento());
		arg.setString(33, this.codiceOperatore);  // (OPERATORE ULTIMO AGGIORNAMENTO)
		
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}
}