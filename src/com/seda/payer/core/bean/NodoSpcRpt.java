package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NodoSpcRpt implements Serializable {
	/**
	 * PSP disponibile per NodoSPC
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger id;
	private String codSocieta;
	private String codUtente;
	private String chiaveTra;
	private String codiceIuv;
	private String chiaveTabella;
	private String codiceTabella;
	private BigDecimal importo;
	private java.util.Date dataIuv;
	private String rpt;
	private String rptEsito;
	private String rptFirma;
	private String rt;
	private String rtEsito;
	private String rtFirma;
	private String codContestoPagamento;	//29072016 GG PG160130
	private String idDominio;				//29072016 GG PG160130
	private String idPSP;					//31012017 GG PG160130_02X
	private String idIntermediarioPSP;		//31012017 GG PG160130_02X
	private String idCanalePSP;				//31012017 GG PG160130_02X
    //inizio LP PG180290
    private String idSessioneCarrello;
    private String identificativoTipoDovuto;
    private String identificativoUnivocoDovuto;
    //fine LP PG180290
    //inizio PG200150
    private BigInteger idQuadratura; 
    private String statoQuadratura;  
    private String statoProtocollo; 
	private String codiceErroreComunicazione; //RPT_CRPTERRC
    private String esitoComunicazione; // RPT_CRPTESIT
    private String idTemporaneo;  //RPT_CRPTISIP
    private String numeroprotocolloRT; //RPT_CRPTNPRT`
    // fine PG200150
    //inizio LP PG190220
	private String rr;
	private String er;
	private String rtAnnullata;
	private String rtAnnullataEsito;
	private String erEsito;
	private String esitoInvioRevocaEmailAdmin;
	private java.util.Date dataInvioRevocaEmailAdmin;
	private String esitoInvioRevocaEmailContribuente;
	private java.util.Date dataInvioRevocaEmailContribuente;
    //fine LP PG190220
//	YLM PG22XX07 INI 
	private String xmlSendRT;
//	YLM PG22XX07 FINE 
    
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getCodSocieta() {
		return codSocieta;
	}

	public void setCodSocieta(String codSocieta) {
		this.codSocieta = codSocieta;
	}

	public String getCodUtente() {
		return codUtente;
	}

	public void setCodUtente(String codUtente) {
		this.codUtente = codUtente;
	}

	public String getChiaveTra() {
		return chiaveTra;
	}

	public void setChiaveTra(String chiaveTra) {
		this.chiaveTra = chiaveTra;
	}

	public String getCodiceIuv() {
		return codiceIuv;
	}

	public void setCodiceIuv(String codiceIuv) {
		this.codiceIuv = codiceIuv;
	}

	public String getChiaveTabella() {
		return chiaveTabella;
	}

	public void setChiaveTabella(String chiaveTabella) {
		this.chiaveTabella = chiaveTabella;
	}

	public String getCodiceTabella() {
		return codiceTabella;
	}

	public void setCodiceTabella(String codiceTabella) {
		this.codiceTabella = codiceTabella;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public java.util.Date getDataIuv() {
		return dataIuv;
	}

	public void setDataIuv(java.util.Date dataIuv) {
		this.dataIuv = dataIuv;
	}

	public String getRpt() {
		return rpt;
	}

	public void setRpt(String rpt) {
		this.rpt = rpt;
	}

	public String getRptEsito() {
		return rptEsito;
	}

	public void setRptEsito(String rptEsito) {
		this.rptEsito = rptEsito;
	}

	public String getRptFirma() {
		return rptFirma;
	}

	public void setRptFirma(String rptFirma) {
		this.rptFirma = rptFirma;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getRtEsito() {
		return rtEsito;
	}

	public void setRtEsito(String rtEsito) {
		this.rtEsito = rtEsito;
	}

	public String getRtFirma() {
		return rtFirma;
	}

	public void setRtFirma(String rtFirma) {
		this.rtFirma = rtFirma;
	}
	
	//29072016 GG PG160130 - inizio
	public String getCodContestoPagamento() {
		return codContestoPagamento;
	}

	public void setCodContestoPagamento(String codContestoPagamento) {
		this.codContestoPagamento = codContestoPagamento;
	}

	public String getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}
	//29072016 GG PG160130 - fine
	
	//31012017 GG PG160130_02X - inizio
	public String getIdPSP() {
		return idPSP;
	}

	public void setIdPSP(String idPSP) {
		this.idPSP = idPSP;
	}

	public String getIdIntermediarioPSP() {
		return idIntermediarioPSP;
	}

	public void setIdIntermediarioPSP(String idIntermediarioPSP) {
		this.idIntermediarioPSP = idIntermediarioPSP;
	}

	public String getIdCanalePSP() {
		return idCanalePSP;
	}

	public void setIdCanalePSP(String idCanalePSP) {
		this.idCanalePSP = idCanalePSP;
	}	
	//31012017 GG PG160130_02X - fine

    //inizio LP PG180290
	public String getIdSessioneCarrello() {
		return idSessioneCarrello;
	}

	public void setIdSessioneCarrello(String idSessioneCarrello) {
		this.idSessioneCarrello = idSessioneCarrello;
	}

	public String getIdentificativoTipoDovuto() {
		return identificativoTipoDovuto;
	}

	public void setIdentificativoTipoDovuto(String identificativoTipoDovuto) {
		this.identificativoTipoDovuto = identificativoTipoDovuto;
	}

	public String getIdentificativoUnivocoDovuto() {
		return identificativoUnivocoDovuto;
	}

	public void setIdentificativoUnivocoDovuto(String identificativoUnivocoDovuto) {
		this.identificativoUnivocoDovuto = identificativoUnivocoDovuto;
	}
    //fine LP PG180290
	//inizio PG200150
	public BigInteger getIdQuadratura() {
		return idQuadratura;
	}

	public void setIdQuadratura(BigInteger idQuadratura) {
		this.idQuadratura = idQuadratura;
	}

	public String getStatoQuadratura() {
		return statoQuadratura;
	}

	public void setStatoQuadratura(String statoQuadratura) {
		this.statoQuadratura = statoQuadratura;
	}

	public String getStatoProtocollo() {
		return statoProtocollo;
	}

	public void setStatoProtocollo(String statoProtocollo) {
		this.statoProtocollo = statoProtocollo;
	}
	
	public String getCodiceErroreComunicazione() {
		return codiceErroreComunicazione;
	}

	public void setCodiceErroreComunicazione(String codiceErroreComunicazione) {
		this.codiceErroreComunicazione = codiceErroreComunicazione;
	}

	public String getEsitoComunicazione() {
		return esitoComunicazione;
	}

	public void setEsitoComunicazione(String esitoComunicazione) {
		this.esitoComunicazione = esitoComunicazione;
	}

	public String getIdTemporaneo() {
		return idTemporaneo;
	}

	public void setIdTemporaneo(String idTemporaneo) {
		this.idTemporaneo = idTemporaneo;
	}

	public String getNumeroprotocolloRT() {
		return numeroprotocolloRT;
	}

	public void setNumeroprotocolloRT(String numeroprotocolloRT) {
		this.numeroprotocolloRT = numeroprotocolloRT;
	}
	//fine PG200150
	
	
	
	public NodoSpcRpt(ResultSet data) throws SQLException 
	{
    	if (data == null)
    		return;
    	
       	setId(BigInteger.valueOf(data.getInt("RPT_PRPTPKEY")));
    	setCodSocieta(data.getString("RPT_CSOCCSOC"));
    	setCodUtente(data.getString("RPT_CUTECUTE"));
    	setChiaveTra(data.getString("RPT_KTRAKTRA"));
    	setCodiceIuv(data.getString("RPT_KRPTKIUV"));
    	setChiaveTabella(data.getString("RPT_KKEYKKEY"));
    	setCodiceTabella(data.getString("RPT_CODTCODT"));
     	setImporto(data.getBigDecimal("RPT_INODIMPO"));
     	setDataIuv(data.getTimestamp("RPT_GRPTGINS"));
     	setRpt(data.getString("RPT_CRPTRPT"));
     	setRptEsito(data.getString("RPT_CRPTRPTESITO"));
     	setRptFirma(data.getString("RPT_CRPTRPTFIR"));
     	setRt(data.getString("RPT_CRPTRT"));
     	setRtEsito(data.getString("RPT_CRPTRTESITO"));
     	setRtFirma(data.getString("RPT_CRPTRTFIR"));
     	setCodContestoPagamento(data.getString("RPT_CRPTCCCP")); 	//29072016 GG PG160130
     	setIdDominio(data.getString("RPT_CRPTCIDD")); 			 	//29072016 GG PG160130
     	setIdPSP(data.getString("RPT_CRPTIDPSP")); 				 	//31012017 GG PG160130_02X
     	setIdIntermediarioPSP(data.getString("RPT_CRPTINTPSP")); 	//31012017 GG PG160130_02X
     	setIdCanalePSP(data.getString("RPT_CRPTCANPSP")); 			//31012017 GG PG160130_02X
        //inizio LP PG180290
     	//non so se tutte le sp ritornano le tre colonne sotto.....
     	try {
     		setIdSessioneCarrello(data.getString("RPT_CRPTMISC"));
     		setIdentificativoTipoDovuto(data.getString("RPT_CRPTMITD"));
     		setIdentificativoUnivocoDovuto(data.getString("RPT_CRPTMIUD"));
     	} catch (Exception e) {
     		setIdSessioneCarrello(null);
     		setIdentificativoTipoDovuto(null);
     		setIdentificativoUnivocoDovuto(null);
		}
        //fine LP PG180290
        //inizio LP PG190220
     	//non so se tutte le sp ritornano le 2 colonne sotto.....
     	try {
     		setRr(data.getString("RPT_CRPTRR"));
     		setEr(data.getString("RPT_CRPTER"));
     	} catch (Exception e) {
     		setRr(null);
     		setEr(null);
		}
     	//non so se tutte le sp ritornano le 3 colonne sotto.....
     	try {
	 		setRtAnnullata(data.getString("RPT_CRPTRTANN"));
	 		setRtAnnullataEsito(data.getString("RPT_CRPTRTANNESITO"));
     		setErEsito(data.getString("RPT_CRPTERESITO"));
     	} catch (Exception e) {
     		setRtAnnullata(null);
     		setRtAnnullataEsito(null);
     		setErEsito(null);
		}
     	//non so se tutte le sp ritornano le 4 colonne sotto.....
     	try {
     		setEsitoInvioRevocaEmailAdmin(data.getString("RPT_CRPTEIENRAM"));
     		setDataInvioRevocaEmailAdmin(data.getTimestamp("RPT_GRPTGIENRAM"));
     		setEsitoInvioRevocaEmailContribuente(data.getString("RPT_CRPTEIENRCT"));
     		setDataInvioRevocaEmailContribuente(data.getTimestamp("RPT_GRPTGIENRCT"));
     	} catch (Exception e) {
     		setEsitoInvioRevocaEmailAdmin(null);
     		setDataInvioRevocaEmailAdmin(null);
     		setEsitoInvioRevocaEmailContribuente(null);
     		setDataInvioRevocaEmailContribuente(null);
		}
     	try {
     		setIdQuadratura(BigInteger.valueOf(data.getInt("RPT_PQUNPKEY")));
     		setStatoQuadratura(data.getString("RPT_CRPTQUAD"));
     	} catch (Exception e) {
     		setIdQuadratura(null);
     		setStatoQuadratura(null);
		}
     	//fine LP PG190220
	}
	
	public NodoSpcRpt()  
	{
    	
	}
	
	//PG200150
	public static NodoSpcRpt getBean_Extended(ResultSet data)throws SQLException
	{
		NodoSpcRpt bean = new NodoSpcRpt(data);
		bean.idQuadratura = BigInteger.valueOf(data.getInt("RPT_PQUNPKEY"));		
		bean.statoQuadratura = data.getString("RPT_CRPTQUAD");
		bean.statoProtocollo = data.getString("RPT_CRPTPROT");
		bean.codiceErroreComunicazione = data.getString("RPT_CRPTERRC");
	    bean.esitoComunicazione = data.getString("RPT_CRPTESIT");
	    bean.idTemporaneo = data.getString("RPT_CRPTISIP");
	    bean.numeroprotocolloRT = data.getString("RPT_CRPTNPRT");
		
		return bean;
	}

    //inizio LP PG190220
	public String getRr() {
		return rr;
	}

	public void setRr(String rr) {
		this.rr = rr;
	}

	public String getEr() {
		return er;
	}

	public void setEr(String er) {
		this.er = er;
	}

	public String getRtAnnullata() {
		return rtAnnullata;
	}

	public void setRtAnnullata(String rtAnnullata) {
		this.rtAnnullata = rtAnnullata;
	}

	public String getRtAnnullataEsito() {
		return rtAnnullataEsito;
	}

	public void setRtAnnullataEsito(String rtAnnullataEsito) {
		this.rtAnnullataEsito = rtAnnullataEsito;
	}

	public String getErEsito() {
		return erEsito;
	}

	public void setErEsito(String erEsito) {
		this.erEsito = erEsito;
	}

	public String getEsitoInvioRevocaEmailAdmin() {
		return esitoInvioRevocaEmailAdmin;
	}

	public void setEsitoInvioRevocaEmailAdmin(String esitoInvioRevocaEmailAdmin) {
		this.esitoInvioRevocaEmailAdmin = esitoInvioRevocaEmailAdmin;
	}

	public java.util.Date getDataInvioRevocaEmailAdmin() {
		return dataInvioRevocaEmailAdmin;
	}

	public void setDataInvioRevocaEmailAdmin(
			java.util.Date dataInvioRevocaEmailAdmin) {
		this.dataInvioRevocaEmailAdmin = dataInvioRevocaEmailAdmin;
	}

	public String getEsitoInvioRevocaEmailContribuente() {
		return esitoInvioRevocaEmailContribuente;
	}

	public void setEsitoInvioRevocaEmailContribuente(
			String esitoInvioRevocaEmailContribuente) {
		this.esitoInvioRevocaEmailContribuente = esitoInvioRevocaEmailContribuente;
	}

	public java.util.Date getDataInvioRevocaEmailContribuente() {
		return dataInvioRevocaEmailContribuente;
	}

	public void setDataInvioRevocaEmailContribuente(
			java.util.Date dataInvioRevocaEmailContribuente) {
		this.dataInvioRevocaEmailContribuente = dataInvioRevocaEmailContribuente;
	}
    //fine LP PG190220

//	YLM PG22XX07 INI 
	public String getXmlSendRT() {
		return xmlSendRT;
	}

	public void setXmlSendRT(String xmlSendRT) {
		this.xmlSendRT = xmlSendRT;
	}

//	YLM PG22XX07 FINE 
}
