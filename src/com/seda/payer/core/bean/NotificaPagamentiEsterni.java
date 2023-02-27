package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;

public class NotificaPagamentiEsterni extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
    private String chiaveTransazione;
    private String chiaveDettaglioTransazione; //PG180110 FB
    private String codiceSocieta;
    private String codiceUtente;
    private String chiaveEnte;
    private int numeroTentativoNotifica;
    private String urlPortale;
    private String numeroDocumento;
    private String numeroAvviso;
    private String codiceFiscale;
    private Calendar dataInvioNotifica;
    private Calendar dataRispostaNotifica;
    private String ultimoEsitoNotifica;
    private BigDecimal importoPagato;
    private Calendar dataPagamento;
    private String xmlRicevuta;
    //inizio LP PG190220    	
    private int numeroTentativoNotificaAnnullo = -1;
    private Calendar dataInvioNotificaAnnullo = null;
    private Calendar dataRispostaNotificaAnnullo = null;
    private String ultimoEsitoNotificaAnnullo = "00";
    private String xmlRichiestaRevoca = "";
    //fine LP PG190220    	

    public NotificaPagamentiEsterni() { 
    }

    public NotificaPagamentiEsterni(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

//    	"NEX_KTRAKTRA" VARCHAR(64) NOT NULL,
//    	"NEX_CSOCCSOC" CHAR(5) NOT NULL,
//    	"NEX_CUTECUTE" CHAR(5) NOT NULL,
//    	"NEX_KANEKENT" CHAR(10) NOT NULL,
//    	"NEX_NNEXCORR" INTEGER NOT NULL,			-- Numero tentativi
//    	"NEX_CNEXPORT" VARCHAR(256) NOT NULL,		-- URL del WS esterno chiamato
//    	"NEX_CNEXNDOC" VARCHAR(20) NOT NULL,		-- numero documento
//    	"NEX_CNEXNAVV" VARCHAR(18) NOT NULL,		-- numero avviso pagopa (IUV)
//    	"NEX_CNEXCFIS" VARCHAR(64) NOT NULL,		-- codice fiscale
//    	"NEX_GNEXDNOT" TIMESTAMP NOT NULL,			-- data invio notifica	
//    	"NEX_GNEXDRNO" TIMESTAMP NOT NULL,			-- data risposta notifica	
//    	"NEX_CNEXUESI" CHAR(3) NOT NULL,			-- ultimoEsitoNotifica
//    	"NEX_INEXIMPO" DECIMAL(10,2) NOT NULL,		-- importo pagato
//    	"NEX_GNEXDPAG" TIMESTAMP NOT NULL,			-- data pagamento	
//    	"NEX_CNEXRXML" CLOB(1048576)				-- xml ricevuta
//		"NEX_KTDTKTDT" VARCHAR(64) NOT NULL
//inizio LP PG190220    	
//    	"NEX_NNEXCOAN" INTEGER NOT NULL 0,				-- Numero tentativi annullo
//    	"NEX_GNEXDIAN" TIMESTAMP,						-- data invio notifica annullo
//    	"NEX_GNEXDRAN" TIMESTAMP,						-- data risposta notifica annullo	
//    	"NEX_CNEXUEAN" VARCHAR(2) NOT NULL DEFAULT '00',-- ultimoEsitoNotifica annullo
//    	"NEX_CNEXRRAN" CLOB(1M)	NOT NULL DEFAULT ''		-- xml richiesta revoca
//fine LP PG190220    	
    	
    	
    	chiaveTransazione = data.getString("NEX_KTRAKTRA");
    	codiceSocieta = data.getString("NEX_CSOCCSOC");
    	codiceUtente = data.getString("NEX_CUTECUTE");
    	chiaveEnte = data.getString("NEX_KANEKENT");
    	numeroTentativoNotifica = data.getInt("NEX_NNEXCORR");
    	//inizio LP PG190220
    	//       correzione BUG errore in lettura urlPortale 
    	//urlPortale = data.getString("NEX_NNEXCORR");
    	urlPortale = data.getString("NEX_CNEXPORT");
    	//fine LP PG190220
    	numeroDocumento = data.getString("NEX_CNEXNDOC");
    	numeroAvviso = data.getString("NEX_CNEXNAVV");
    	codiceFiscale = data.getString("NEX_CNEXCFIS");
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(data.getTimestamp("NEX_GNEXDNOT").getTime());
    	dataInvioNotifica = cal;
    	if(data.getTimestamp("NEX_GNEXDRNO") != null) {
	    	Calendar cal2 = Calendar.getInstance();
	    	cal2.setTimeInMillis(data.getTimestamp("NEX_GNEXDRNO").getTime());
	    	dataRispostaNotifica = cal2;
    	}
    	ultimoEsitoNotifica = data.getString("NEX_CNEXUESI");
    	importoPagato = data.getBigDecimal("NEX_INEXIMPO");
    	Calendar cal3 = Calendar.getInstance();
    	cal3.setTimeInMillis(data.getTimestamp("NEX_GNEXDPAG").getTime());
    	dataPagamento = cal3;
    	xmlRicevuta = data.getString("NEX_CNEXRXML");   
    	chiaveDettaglioTransazione = data.getString("NEX_KTDTKTDT"); //PG180110 FB  	
    	//inizio LP PG190220
    	try {
    		numeroTentativoNotificaAnnullo = data.getInt("NEX_NNEXCOAN");
        	if(data.getTimestamp("NEX_GNEXDIAN") != null) {
            	Calendar calAi = Calendar.getInstance();
            	calAi.setTimeInMillis(data.getTimestamp("NEX_GNEXDIAN").getTime());
        		dataInvioNotificaAnnullo = calAi;
        	}
        	if(data.getTimestamp("NEX_GNEXDRAN") != null) {
            	Calendar calAr = Calendar.getInstance();
            	calAr.setTimeInMillis(data.getTimestamp("NEX_GNEXDRAN").getTime());
        		dataRispostaNotificaAnnullo = calAr;
        	}
    		ultimoEsitoNotificaAnnullo = data.getString("NEX_CNEXUEAN");
    		xmlRichiestaRevoca = data.getString("NEX_CNEXRRAN");
    	} catch (Exception e) {
    		numeroTentativoNotificaAnnullo = -1;
    		dataInvioNotificaAnnullo = null;
    		dataRispostaNotificaAnnullo = null;
    		ultimoEsitoNotificaAnnullo = "00";
    		xmlRichiestaRevoca = "";
		}
//fine LP PG190220    	
    }



	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public String getChiaveDettaglioTransazione() {
		return chiaveDettaglioTransazione;
	}

	public void setChiaveDettaglioTransazione(String chiaveDettaglioTransazione) {
		this.chiaveDettaglioTransazione = chiaveDettaglioTransazione;
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

	public int getNumeroTentativoNotifica() {
		return numeroTentativoNotifica;
	}

	public void setNumeroTentativoNotifica(int numeroTentativoNotifica) {
		this.numeroTentativoNotifica = numeroTentativoNotifica;
	}

	public String getUrlPortale() {
		return urlPortale;
	}

	public void setUrlPortale(String urlPortale) {
		this.urlPortale = urlPortale;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroAvviso() {
		return numeroAvviso;
	}

	public void setNumeroAvviso(String numeroAvviso) {
		this.numeroAvviso = numeroAvviso;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Calendar getDataInvioNotifica() {
		return dataInvioNotifica;
	}

	public void setDataInvioNotifica(Calendar dataInvioNotifica) {
		this.dataInvioNotifica = dataInvioNotifica;
	}

	public Calendar getDataRispostaNotifica() {
		return dataRispostaNotifica;
	}

	public void setDataRispostaNotifica(Calendar dataRispostaNotifica) {
		this.dataRispostaNotifica = dataRispostaNotifica;
	}

	public String getUltimoEsitoNotifica() {
		return ultimoEsitoNotifica;
	}

	public void setUltimoEsitoNotifica(String ultimoEsitoNotifica) {
		this.ultimoEsitoNotifica = ultimoEsitoNotifica;
	}

	public BigDecimal getImportoPagato() {
		return importoPagato;
	}

	public void setImportoPagato(BigDecimal importoPagato) {
		this.importoPagato = importoPagato;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getXmlRicevuta() {
		return xmlRicevuta;
	}

	public void setXmlRicevuta(String xmlRicevuta) {
		this.xmlRicevuta = xmlRicevuta;
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTransazione != null ?  this.chiaveTransazione : "");
		arg.setString(2, this.chiaveDettaglioTransazione != null ?  this.chiaveDettaglioTransazione: ""); //PG180110 FB 
		arg.setString(3, this.codiceSocieta != null ?  this.codiceSocieta : "");
		arg.setString(4, this.codiceUtente != null ?  this.codiceUtente : "");
		arg.setString(5, this.chiaveEnte != null ?  this.chiaveEnte : "");
		arg.setInt(6, this.numeroTentativoNotifica);
		arg.setString(7, this.urlPortale != null ?  this.urlPortale : "");
		arg.setString(8, this.numeroDocumento != null ?  this.numeroDocumento : "");
		arg.setString(9, this.numeroAvviso != null ?  this.numeroAvviso : "");
		arg.setString(10, this.codiceFiscale != null ?  this.codiceFiscale : "");
		//arg.setTimestamp(11, new Timestamp(this.dataInvioNotifica.getTimeInMillis()));
		if(this.dataInvioNotifica != null)
			arg.setTimestamp(11, new Timestamp(this.dataInvioNotifica.getTimeInMillis()));
		else
			arg.setNull(11, java.sql.Types.TIMESTAMP);
	    //fine LP PG190220
		if(this.dataRispostaNotifica!= null)
			arg.setTimestamp(12, new Timestamp(this.dataRispostaNotifica.getTimeInMillis()));
		else
			arg.setNull(12, java.sql.Types.TIMESTAMP);
		arg.setString(13, this.ultimoEsitoNotifica != null ?  this.ultimoEsitoNotifica : "");
		arg.setBigDecimal(14, this.importoPagato != null ?  this.importoPagato : new BigDecimal(0));
		arg.setTimestamp(15, new Timestamp(this.dataPagamento.getTimeInMillis()));
		arg.setString(16, this.xmlRicevuta != null ?  this.xmlRicevuta : "");
	    //inizio LP PG190220
		arg.setInt(17, this.numeroTentativoNotificaAnnullo);
		if(this.dataInvioNotificaAnnullo != null)
			arg.setTimestamp(18, new Timestamp(this.dataInvioNotificaAnnullo.getTimeInMillis()));
		else
			arg.setNull(18, java.sql.Types.TIMESTAMP);
		if(this.dataRispostaNotificaAnnullo != null)
			arg.setTimestamp(19, new Timestamp(this.dataRispostaNotificaAnnullo.getTimeInMillis()));
		else
			arg.setNull(19, java.sql.Types.TIMESTAMP);
		arg.setString(20, this.ultimoEsitoNotificaAnnullo != null ?  this.ultimoEsitoNotificaAnnullo : "00");
		arg.setString(21, this.xmlRichiestaRevoca != null ?  this.xmlRichiestaRevoca : "");
	    //fine LP PG190220    	
	}
	
	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTransazione != null ?  this.chiaveTransazione : "");
		arg.setString(2, this.chiaveDettaglioTransazione != null ?  this.chiaveDettaglioTransazione : ""); //PG180110 FB
	    //inizio LP PG190220
		//arg.setString(3, this.codiceSocieta != null ?  this.codiceSocieta : "");
		//arg.setString(4, this.codiceUtente != null ?  this.codiceUtente : "");
		//arg.setString(5, this.chiaveEnte != null ?  this.chiaveEnte : "");
		if(this.codiceSocieta != null) 
			arg.setString(3, this.codiceSocieta);
		else
			arg.setNull(3, java.sql.Types.VARCHAR);
		if(this.codiceUtente != null) 
			arg.setString(4, this.codiceUtente);
		else
			arg.setNull(4, java.sql.Types.VARCHAR);
		if(this.chiaveEnte != null) 
			arg.setString(5, this.chiaveEnte);
		else
			arg.setNull(5, java.sql.Types.VARCHAR);
		//fine LP PG190220
		arg.setInt(6, this.numeroTentativoNotifica);
	    //inizio LP PG190220
		//arg.setString(7, this.urlPortale != null ?  this.urlPortale : "");
		//arg.setString(8, this.numeroDocumento != null ?  this.numeroDocumento : "");
		//arg.setString(9, this.numeroAvviso != null ?  this.numeroAvviso : "");
		//arg.setString(10, this.codiceFiscale != null ?  this.codiceFiscale : "");
		//arg.setTimestamp(11, new Timestamp(this.dataInvioNotifica.getTimeInMillis()));
		//arg.setTimestamp(12, new Timestamp(this.dataRispostaNotifica.getTimeInMillis()));
		//arg.setString(13, this.ultimoEsitoNotifica != null ?  this.ultimoEsitoNotifica : "");
		//arg.setBigDecimal(14, this.importoPagato != null ?  this.importoPagato : new BigDecimal(0));
		//arg.setTimestamp(15, new Timestamp(this.dataPagamento.getTimeInMillis()));
		//arg.setString(16, this.xmlRicevuta != null ?  this.xmlRicevuta : "");
		if(this.urlPortale != null) 
			arg.setString(7, this.urlPortale);
		else
			arg.setNull(7, java.sql.Types.VARCHAR);
		if(this.numeroDocumento != null) 
			arg.setString(8, this.numeroDocumento);
		else
			arg.setNull(8, java.sql.Types.VARCHAR);
		if(this.numeroAvviso != null) 
			arg.setString(9, this.numeroAvviso);
		else
			arg.setNull(9, java.sql.Types.VARCHAR);
		if(this.codiceFiscale != null) 
			arg.setString(10, this.codiceFiscale);
		else
			arg.setNull(10, java.sql.Types.VARCHAR);
		if(this.dataInvioNotifica != null)
			arg.setTimestamp(11, new Timestamp(this.dataInvioNotifica.getTimeInMillis()));
		else
			arg.setNull(11, java.sql.Types.TIMESTAMP);
		if(this.dataRispostaNotifica != null)
			arg.setTimestamp(12, new Timestamp(this.dataRispostaNotifica.getTimeInMillis()));
		else
			arg.setNull(12, java.sql.Types.TIMESTAMP);
	    //fine LP PG190220
		if(this.ultimoEsitoNotifica != null) 
			arg.setString(13, this.ultimoEsitoNotifica);
		else
			arg.setNull(13, java.sql.Types.VARCHAR);
		if(this.importoPagato != null) 
			arg.setBigDecimal(14, this.importoPagato);
		else
			arg.setNull(14, java.sql.Types.DECIMAL);
		if(this.dataPagamento != null) 
			arg.setTimestamp(15, new Timestamp(this.dataPagamento.getTimeInMillis()));
		else
			arg.setNull(15, java.sql.Types.TIMESTAMP);
		if(this.xmlRicevuta != null) 
			arg.setString(16, this.xmlRicevuta);
		else
			arg.setNull(16, java.sql.Types.VARCHAR);
		arg.setInt(17, this.numeroTentativoNotificaAnnullo);
		if(this.dataInvioNotificaAnnullo != null)
			arg.setTimestamp(18, new Timestamp(this.dataInvioNotificaAnnullo.getTimeInMillis()));
		else
			arg.setNull(18, java.sql.Types.TIMESTAMP);
		if(this.dataRispostaNotificaAnnullo != null)
			arg.setTimestamp(19, new Timestamp(this.dataRispostaNotificaAnnullo.getTimeInMillis()));
		else
			arg.setNull(19, java.sql.Types.TIMESTAMP);
		if(this.ultimoEsitoNotificaAnnullo != null) 
			arg.setString(20, this.ultimoEsitoNotificaAnnullo);
		else
			arg.setNull(20, java.sql.Types.VARCHAR);
		if(this.xmlRichiestaRevoca != null) 
			arg.setString(21, this.xmlRichiestaRevoca);
		else
			arg.setNull(21, java.sql.Types.VARCHAR);
	    //fine LP PG190220    	
	}
	
	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}

    //inizio LP PG190220    	
	public int getNumeroTentativoNotificaAnnullo() {
		return numeroTentativoNotificaAnnullo;
	}

	public void setNumeroTentativoNotificaAnnullo(int numeroTentativoNotificaAnnullo) {
		this.numeroTentativoNotificaAnnullo = numeroTentativoNotificaAnnullo;
	}

	public Calendar getDataInvioNotificaAnnullo() {
		return dataInvioNotificaAnnullo;
	}

	public void setDataInvioNotificaAnnullo(Calendar dataInvioNotificaAnnullo) {
		this.dataInvioNotificaAnnullo = dataInvioNotificaAnnullo;
	}

	public Calendar getDataRispostaNotificaAnnullo() {
		return dataRispostaNotificaAnnullo;
	}

	public void setDataRispostaNotificaAnnullo(Calendar dataRispostaNotificaAnnullo) {
		this.dataRispostaNotificaAnnullo = dataRispostaNotificaAnnullo;
	}

	public String getUltimoEsitoNotificaAnnullo() {
		return ultimoEsitoNotificaAnnullo;
	}

	public void setUltimoEsitoNotificaAnnullo(String ultimoEsitoNotificaAnnullo) {
		this.ultimoEsitoNotificaAnnullo = ultimoEsitoNotificaAnnullo;
	}

	public String getXmlRichiestaRevoca() {
		return xmlRichiestaRevoca;
	}

	public void setXmlRichiestaRevoca(String xmlRichiestaRevoca) {
		this.xmlRichiestaRevoca = xmlRichiestaRevoca;
	}
    //fine LP PG190220    	
}