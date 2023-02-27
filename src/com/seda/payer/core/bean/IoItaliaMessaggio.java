package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class IoItaliaMessaggio {

	private String cutecute;
	private String idDominio;
	private String tipologiaServizio;
	private String timestampParsingFile;
	private int posizione;
	private String codiceFiscale;
	private String oggettoMessaggio;
	private String corpoMessaggio;
	private Date dataScadenzaMessaggio;
	private BigDecimal importo;
	private String avvisoPagoPa;
	private String scadenzaPagamento; // "1" o "0"
	private String email;
	private String stato; // "0" inserito, "1" inviato con successo, "2" inviato con errore 
	private String messaggioErrore;
	private Date dataInvioMessaggio;
	private String idInvioMessaggio;
	private long idFornitura;
	private String impostaServizio;
	private long idMessaggio;
	
    public IoItaliaMessaggio() {
    	
    }

    public IoItaliaMessaggio(String cutecute, String idDominio, String tipologiaServizio, String timestampParsingFile,
			int posizione, String codiceFiscale, String oggettoMessaggio, String corpoMessaggio,
			Date dataScadenzaMessaggio, BigDecimal importo, String avvisoPagoPa, String scadenzaPagamento, String email,
			String stato, long idFornitura, String impostaServizio) {
		
		this.cutecute = cutecute;
		this.idDominio = idDominio;
		this.tipologiaServizio = tipologiaServizio;
		this.timestampParsingFile = timestampParsingFile;
		this.posizione = posizione;
		this.codiceFiscale = codiceFiscale;
		this.oggettoMessaggio = oggettoMessaggio;
		this.corpoMessaggio = corpoMessaggio;
		this.dataScadenzaMessaggio = dataScadenzaMessaggio;
		this.importo = importo;
		this.avvisoPagoPa = avvisoPagoPa;
		this.scadenzaPagamento = scadenzaPagamento;
		this.email = email;
		this.stato = stato;
		this.idFornitura = idFornitura;
		this.impostaServizio = impostaServizio;
	}
    
    public IoItaliaMessaggio(ResultSet data)  throws SQLException {
    	if (data == null)
    		return;
    	 cutecute = data.getString("MES_CUTECUTE");
    	 idDominio = data.getString("MES_CMESIDDO");
    	 tipologiaServizio = data.getString("MES_CMESTPSE");
    	 timestampParsingFile = data.getString("MES_CMESTIME");
    	 posizione = data.getInt("MES_CMESPOSI");
    	 codiceFiscale = data.getString("MES_CMESCFIS");
    	 oggettoMessaggio = data.getString("MES_CMESOGME");
    	 corpoMessaggio = data.getString("MES_CMESCRME");
    	 dataScadenzaMessaggio = data.getTimestamp("MES_GMESSCME");
    	 importo = data.getBigDecimal("MES_IMESIMPO");
    	 avvisoPagoPa = data.getString("MES_CMESAVVP");
    	 scadenzaPagamento = data.getString("MES_CMESSCPA");
    	 email = data.getString("MES_CMESMAIL");
    	 stato = data.getString("MES_CMESSTAT");
    	 messaggioErrore = data.getString("MES_CMESMOTI");
    	 dataInvioMessaggio = data.getTimestamp("MES_GMESGINV");
    	 idInvioMessaggio = data.getString("MES_CMESIDME");
    	 idFornitura = data.getLong("MES_KFORKFOR");
    	 impostaServizio = data.getString("MES_CISECISE");
    	 idMessaggio = data.getLong("MES_KMESKMES");
	}

	public String getCutecute() {
		return cutecute;
	}

	public void setCutecute(String cutecute) {
		this.cutecute = cutecute;
	}

	public String getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}

	public String getTipologiaServizio() {
		return tipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		this.tipologiaServizio = tipologiaServizio;
	}

	public String getTimestampParsingFile() {
		return timestampParsingFile;
	}

	public void setTimestampParsingFile(String timestampParsingFile) {
		this.timestampParsingFile = timestampParsingFile;
	}

	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getOggettoMessaggio() {
		return oggettoMessaggio;
	}

	public void setOggettoMessaggio(String oggettoMessaggio) {
		this.oggettoMessaggio = oggettoMessaggio;
	}

	public String getCorpoMessaggio() {
		return corpoMessaggio;
	}

	public void setCorpoMessaggio(String corpoMessaggio) {
		this.corpoMessaggio = corpoMessaggio;
	}

	public Date getDataScadenzaMessaggio() {
		return dataScadenzaMessaggio;
	}

	public void setDataScadenzaMessaggio(Date dataScadenzaMessaggio) {
		this.dataScadenzaMessaggio = dataScadenzaMessaggio;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getAvvisoPagoPa() {
		return avvisoPagoPa;
	}

	public void setAvvisoPagoPa(String avvisoPagoPa) {
		this.avvisoPagoPa = avvisoPagoPa;
	}

	public String getScadenzaPagamento() {
		return scadenzaPagamento;
	}

	public void setScadenzaPagamento(String scadenzaPagamento) {
		this.scadenzaPagamento = scadenzaPagamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getMessaggioErrore() {
		return messaggioErrore;
	}

	public void setMessaggioErrore(String messaggioErrore) {
		this.messaggioErrore = messaggioErrore;
	}

	public Date getDataInvioMessaggio() {
		return dataInvioMessaggio;
	}

	public void setDataInvioMessaggio(Date dataInvioMessaggio) {
		this.dataInvioMessaggio = dataInvioMessaggio;
	}

	public String getIdInvioMessaggio() {
		return idInvioMessaggio;
	}

	public void setIdInvioMessaggio(String idInvioMessaggio) {
		this.idInvioMessaggio = idInvioMessaggio;
	}

	public long getIdFornitura() {
		return idFornitura;
	}

	public void setIdFornitura(long idFornitura) {
		this.idFornitura = idFornitura;
	}

	public String getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	public long getIdMessaggio() {
		return idMessaggio;
	}

	public void setIdMessaggio(long idMessaggio) {
		this.idMessaggio = idMessaggio;
	}
	
}
