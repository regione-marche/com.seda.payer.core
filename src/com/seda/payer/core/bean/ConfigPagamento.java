package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seda.payer.commons.bean.Lifecycle;

public class ConfigPagamento extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String codSocieta;
    private java.lang.String codUtente;
    private java.lang.String chiaveEnte;
    private java.lang.String canalePagamento;
    private java.lang.String codTipologiaServizio;
    private java.lang.String descTipologiaServizio;
    private java.lang.String descFunzionePagamento;
    private java.lang.String numeroContoCorrente;
    private java.lang.String intestatarioContoCorrente;
    private java.lang.String tipoDocumento;
    private java.lang.String flagControlloRange;
    private java.lang.String emailDestinatario;
    private java.lang.String emailCCN;
    private java.lang.String emailMittente;
    private java.lang.String descrMittente;
    private java.lang.String flagAllegato;
    private java.lang.String codiceSIA;
    private java.lang.String codiceIBAN;
    private java.lang.String tipoBollettino;
    private java.lang.String funzionePagamentoProtetta;
    private java.lang.String urlServizioWebIntegraEnte;
    private java.lang.String modalitaBollettino;
    private java.lang.String flagTipoPagamento;
    private java.lang.String flagIntegrazioneSeda;
    private java.lang.String codiceUtenteSeda;
    private java.lang.String codiceIBAN2;	//PG160150_002 GG
    private List<ValidazioneRange> listValidazioneRange;
    private List<TemplateFunzioniPagamento> listTemplateFunzioniPagamento;
    private String flagNotificaPagamento; //PG170150 CT
	private String urlServizioWebNotificaPagamento; //PG170150 CT
	private String flagPagoPA;
	//inizio LP PG180290
    private String tipoDovuto;
    //fine LP PG180290
    
    private String flagStampaAvvisoPagoPa;
    private String giorniStampaAvvisoPagoPa;
    private String autorizzazioneStampaAvvisoPagoPa;
	private String cbillStampaAvvisoPagoPa;
	private String infoEnteStampaAvvisoPagoPa;

	//inizio LP PG200360
    private String datiSpecificiIncasso;
    private String causali;
    //fine LP PG1200360
    
    //inizio SB PG210140
    private String articolo;
    private String capitolo;
    private String annoCompetenza;
    private String codiceContabilita;
    //fine SB PG210140
    private String dataDicituraPagamento; //SB PG210170
	
    public ConfigPagamento(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	setCodSocieta(data.getString("CES_CSOCCSOC"));
        setCodUtente(data.getString("CES_CUTECUTE"));
        setChiaveEnte(data.getString("CES_KANEKENT"));
        setCanalePagamento(data.getString("CES_KCANKCAN"));
        setCodTipologiaServizio(data.getString("CES_CTSECTSE"));
        setDescTipologiaServizio(data.getString("TSE_DTSEDTSE"));
        setDescFunzionePagamento(data.getString("FUNZ"));
        setNumeroContoCorrente(data.getString("NCCP"));
        setIntestatarioContoCorrente(data.getString("DINT"));
        setTipoDocumento(data.getString("TDOC"));
        setFlagControlloRange(data.getString("FCTR"));
        setEmailDestinatario(data.getString("EDES"));
        setEmailCCN(data.getString("ECCN"));
        setEmailMittente(data.getString("EMIT"));
        setDescrMittente(data.getString("DMIT"));
        setFlagAllegato(data.getString("FALL"));
        setCodiceSIA(data.getString("CSIA"));
        setCodiceIBAN(data.getString("IBAN"));
        setTipoBollettino(data.getString("TBOL"));
        setFunzionePagamentoProtetta(data.getString("PROT"));  
        setUrlServizioWebIntegraEnte(data.getString("URLSRV"));
        setFlagTipoPagamento(data.getString("FTPAGA"));
        setFlagIntegrazioneSeda(data.getString("FSEDA"));
        setCodiceUtenteSeda(data.getString("CSEDA"));
        setModalitaBollettino(data.getString("BOL_TBOLMODA"));
        setCodiceIBAN2(data.getString("IBA2"));	//PG160150_002 GG
        setFlagNotificaPagamento(data.getString("FNOTI")); //PG170150
        setUrlServizioWebNotificaPagamento(data.getString("URLNOTI"));  //PG170150
        setFlagPagoPA(data.getString("FPPA"));
    	//inizio LP PG180290
        setTipoDovuto(data.getString("MYTD"));
        //fine LP PG180290
        
        setFlagStampaAvvisoPagoPa(data.getString("FSTA"));
        setGiorniStampaAvvisoPagoPa(data.getString("GSTA"));
        setAutorizzazioneStampaAvvisoPagoPa(data.getString("AUTO"));
        setCbillStampaAvvisoPagoPa(data.getString("CBIL"));
        setInfoEnteStampaAvvisoPagoPa(data.getString("INEN"));

    	//inizio LP PG200360
        setDatiSpecificiIncasso(data.getString("DSPI"));
        setCausali(data.getString("CAUS"));
        //fine LP PG200360
        
        //inizio SB PG210140
        try { setArticolo(data.getString("ARTICOLO")); }catch (Exception e) {setArticolo("");}
        try { setCodiceContabilita(data.getString("CODCONT")); }catch (Exception e) {setCodiceContabilita("");}
        try { setCapitolo(data.getString("CAPITOLO")); }catch (Exception e) {setCapitolo("");}
        try { setAnnoCompetenza(data.getString("ANNOCOMP")); }catch (Exception e) {setAnnoCompetenza("");}
        //fine SB PG210140
        try { setDataDicituraPagamento(data.getString("SCAD_PAGAMENTO")); }catch (Exception e) {setDataDicituraPagamento("");}
        
        setListValidazioneRange(new ArrayList<ValidazioneRange>());
        setListTemplateFunzioniPagamento(new ArrayList<TemplateFunzioniPagamento>());
    }
    
	@Override
	public void onDelete(CallableStatement arg0) throws SQLException {
	}
	
	@Override
	public void onLoad(CallableStatement arg0) throws SQLException {
	}
	
	@Override
	public void onSave(CallableStatement arg0) throws SQLException {
	}
	
	@Override
	public void onUpdate(CallableStatement arg0) throws SQLException {
	}

	public void setCodSocieta(java.lang.String codSocieta) {
		this.codSocieta = codSocieta;
	}

	public java.lang.String getCodSocieta() {
		return codSocieta;
	}

	public void setCodUtente(java.lang.String codUtente) {
		this.codUtente = codUtente;
	}

	public java.lang.String getCodUtente() {
		return codUtente;
	}

	public void setChiaveEnte(java.lang.String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public java.lang.String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setCanalePagamento(java.lang.String canalePagamento) {
		this.canalePagamento = canalePagamento;
	}

	public java.lang.String getCanalePagamento() {
		return canalePagamento;
	}

	public void setCodTipologiaServizio(java.lang.String codTipologiaServizio) {
		this.codTipologiaServizio = codTipologiaServizio;
	}

	public java.lang.String getCodTipologiaServizio() {
		return codTipologiaServizio;
	}

	public void setDescTipologiaServizio(java.lang.String descTipologiaServizio) {
		this.descTipologiaServizio = descTipologiaServizio;
	}

	public java.lang.String getDescTipologiaServizio() {
		return descTipologiaServizio;
	}

	public void setDescFunzionePagamento(java.lang.String descFunzionePagamento) {
		this.descFunzionePagamento = descFunzionePagamento;
	}

	public java.lang.String getDescFunzionePagamento() {
		return descFunzionePagamento;
	}

	public void setNumeroContoCorrente(java.lang.String numeroContoCorrente) {
		this.numeroContoCorrente = numeroContoCorrente;
	}

	public java.lang.String getNumeroContoCorrente() {
		return numeroContoCorrente;
	}

	public void setIntestatarioContoCorrente(java.lang.String intestatarioContoCorrente) {
		this.intestatarioContoCorrente = intestatarioContoCorrente;
	}

	public java.lang.String getIntestatarioContoCorrente() {
		return intestatarioContoCorrente;
	}

	public void setTipoDocumento(java.lang.String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public java.lang.String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setFlagControlloRange(java.lang.String flagControlloRange) {
		this.flagControlloRange = flagControlloRange;
	}

	public java.lang.String getFlagControlloRange() {
		return flagControlloRange;
	}

	public void setEmailDestinatario(java.lang.String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	public java.lang.String getEmailDestinatario() {
		return emailDestinatario;
	}

	public void setEmailCCN(java.lang.String emailCCN) {
		this.emailCCN = emailCCN;
	}

	public java.lang.String getEmailCCN() {
		return emailCCN;
	}

	public void setEmailMittente(java.lang.String emailMittente) {
		this.emailMittente = emailMittente;
	}

	public java.lang.String getEmailMittente() {
		return emailMittente;
	}

	public void setDescrMittente(java.lang.String descrMittente) {
		this.descrMittente = descrMittente;
	}

	public java.lang.String getDescrMittente() {
		return descrMittente;
	}

	public void setFlagAllegato(java.lang.String flagAllegato) {
		this.flagAllegato = flagAllegato;
	}

	public java.lang.String getFlagAllegato() {
		return flagAllegato;
	}

	public void setCodiceSIA(java.lang.String codiceSIA) {
		this.codiceSIA = codiceSIA;
	}

	public java.lang.String getCodiceSIA() {
		return codiceSIA;
	}

	public void setCodiceIBAN(java.lang.String codiceIBAN) {
		this.codiceIBAN = codiceIBAN;
	}

	public java.lang.String getCodiceIBAN() {
		return codiceIBAN;
	}

	public void setTipoBollettino(java.lang.String tipoBollettino) {
		this.tipoBollettino = tipoBollettino;
	}

	public java.lang.String getTipoBollettino() {
		return tipoBollettino;
	}

	public void setFunzionePagamentoProtetta(java.lang.String funzionePagamentoProtetta) {
		this.funzionePagamentoProtetta = funzionePagamentoProtetta;
	}

	public void setUrlServizioWebIntegraEnte(java.lang.String urlServizioWebIntegraEnte) {
		this.urlServizioWebIntegraEnte = urlServizioWebIntegraEnte;
	}

	public java.lang.String getUrlServizioWebIntegraEnte() {
		return urlServizioWebIntegraEnte;
	}

	public java.lang.String getFunzionePagamentoProtetta() {
		return funzionePagamentoProtetta;
	}

	public void setModalitaBollettino(java.lang.String modalitaBollettino) {
		this.modalitaBollettino = modalitaBollettino;
	}

	public java.lang.String getModalitaBollettino() {
		return modalitaBollettino;
	}

	public void setListValidazioneRange(List<ValidazioneRange> listValidazioneRange) {
		this.listValidazioneRange = listValidazioneRange;
	}

	public List<ValidazioneRange> getListValidazioneRange() {
		return listValidazioneRange;
	}

	public void setListTemplateFunzioniPagamento(List<TemplateFunzioniPagamento> listTemplateFunzioniPagamento) {
		this.listTemplateFunzioniPagamento = listTemplateFunzioniPagamento;
	}

	public List<TemplateFunzioniPagamento> getListTemplateFunzioniPagamento() {
		return listTemplateFunzioniPagamento;
	}

	public void setFlagTipoPagamento(java.lang.String flagTipoPagamento) {
		this.flagTipoPagamento = flagTipoPagamento;
	}

	public java.lang.String getFlagTipoPagamento() {
		return flagTipoPagamento;
	}

	public void setFlagIntegrazioneSeda(java.lang.String flagIntegrazioneSeda) {
		this.flagIntegrazioneSeda = flagIntegrazioneSeda;
	}

	public java.lang.String getFlagIntegrazioneSeda() {
		return flagIntegrazioneSeda;
	}

	public void setCodiceUtenteSeda(java.lang.String codiceUtenteSeda) {
		this.codiceUtenteSeda = codiceUtenteSeda;
	}

	public java.lang.String getCodiceUtenteSeda() {
		return codiceUtenteSeda;
	}
	//PG160150_002 GG - inizio
	public void setCodiceIBAN2(java.lang.String codiceIBAN2) {
		this.codiceIBAN2 = codiceIBAN2;
	}

	public java.lang.String getCodiceIBAN2() {
		return codiceIBAN2;
	}
	//PG160150_002 GG - fine

	public void setFlagNotificaPagamento(String flagNotificaPagamento) {
		this.flagNotificaPagamento = flagNotificaPagamento;
	}

	public String getFlagNotificaPagamento() {
		return flagNotificaPagamento;
	}

	public void setUrlServizioWebNotificaPagamento(
			String urlServizioWebNotificaPagamento) {
		this.urlServizioWebNotificaPagamento = urlServizioWebNotificaPagamento;
	}

	public String getUrlServizioWebNotificaPagamento() {
		return urlServizioWebNotificaPagamento;
	}

	public String getFlagPagoPA() {
		return flagPagoPA;
	}

	public void setFlagPagoPA(String flagPagoPA) {
		this.flagPagoPA = flagPagoPA;
	}
	//inizio LP PG180290
	public String getTipoDovuto() {
		return tipoDovuto;
	}

	public void setTipoDovuto(String tipoDovuto) {
		this.tipoDovuto = tipoDovuto;
	}
    //fine LP PG180290

	public String getFlagStampaAvvisoPagoPa() {
		return flagStampaAvvisoPagoPa;
	}

	public void setFlagStampaAvvisoPagoPa(String flagStampaAvvisoPagoPa) {
		this.flagStampaAvvisoPagoPa = flagStampaAvvisoPagoPa;
	}

	public String getGiorniStampaAvvisoPagoPa() {
		return giorniStampaAvvisoPagoPa;
	}

	public void setGiorniStampaAvvisoPagoPa(String giorniStampaAvvisoPagoPa) {
		this.giorniStampaAvvisoPagoPa = giorniStampaAvvisoPagoPa;
	}

	public String getAutorizzazioneStampaAvvisoPagoPa() {
		return autorizzazioneStampaAvvisoPagoPa;
	}

	public void setAutorizzazioneStampaAvvisoPagoPa(String autorizzazioneStampaAvvisoPagoPa) {
		this.autorizzazioneStampaAvvisoPagoPa = autorizzazioneStampaAvvisoPagoPa;
	}

	public String getCbillStampaAvvisoPagoPa() {
		return cbillStampaAvvisoPagoPa;
	}

	public void setCbillStampaAvvisoPagoPa(String cbillStampaAvvisoPagoPa) {
		this.cbillStampaAvvisoPagoPa = cbillStampaAvvisoPagoPa;
	}

	public String getInfoEnteStampaAvvisoPagoPa() {
		return infoEnteStampaAvvisoPagoPa;
	}

	public void setInfoEnteStampaAvvisoPagoPa(String infoEnteStampaAvvisoPagoPa) {
		this.infoEnteStampaAvvisoPagoPa = infoEnteStampaAvvisoPagoPa;
	}

	//inizio LP PG200360
	public String getDatiSpecificiIncasso() {
		return datiSpecificiIncasso;
	}

	public void setDatiSpecificiIncasso(String datiSpecificiIncasso) {
		this.datiSpecificiIncasso = datiSpecificiIncasso;
	}

	public String getCausali() {
		return causali;
	}

	public void setCausali(String causali) {
		this.causali = causali;
	}
	//fine LP PG200360

	public String getArticolo() {
		return articolo;
	}

	public String getCapitolo() {
		return capitolo;
	}

	public String getAnnoCompetenza() {
		return annoCompetenza;
	}

	public String getCodiceContabilita() {
		return codiceContabilita;
	}

	public void setArticolo(String articolo) {
		this.articolo = articolo;
	}

	public void setCapitolo(String capitolo) {
		this.capitolo = capitolo;
	}

	public void setAnnoCompetenza(String annoCompetenza) {
		this.annoCompetenza = annoCompetenza;
	}

	public void setCodiceContabilita(String codiceContabilita) {
		this.codiceContabilita = codiceContabilita;
	}

	public String getDataDicituraPagamento() {
		return dataDicituraPagamento;
	}

	public void setDataDicituraPagamento(String dataDicituraPagamento) {
		this.dataDicituraPagamento = dataDicituraPagamento;
	}
}
