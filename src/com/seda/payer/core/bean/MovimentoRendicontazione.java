package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MovimentoRendicontazione implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7275714905864086845L;
	Integer chiaveMovimento;
	String codiceSocieta;
	String codiceUtente;
	String chiaveGateway;
	String nomeFileCBI;
	Date dataCreazioneFlussiCBI;
	String nomeSupporto;
	String codiceSIADestinatario;
	String codiceCINBancaMittente;
	String codiceABIBancaMittente;
	String codiceCABBancaMittente;
	String numeroContoCorrente;
	Date dataValuta;
	Date dataContabile;
	BigDecimal importoMovimento;
	String riferimentoBanca;
	Date dataInizioRendicontazione;
	Date dataFineRendicontazione;
	Integer numeroTransazioni;
	String codiceCartaPagamento;
	BigDecimal importoSquadratura;
	String statoQuadratura;
	String statoElaborazione;
	Date dataUltimoAggiornamento;
	String utenteUltimoAggiornamento;	
	
	public MovimentoRendicontazione(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	setChiaveMovimento(data.getInt("QUA_PQUAPKEY"));
		setCodiceSocieta(data.getString("QUA_CSOCCSOC"));
		setCodiceUtente(data.getString("QUA_CUTECUTE"));
		setChiaveGateway(data.getString("QUA_KGTWKGTW"));
		setNomeFileCBI(data.getString("QUA_DQUAFILE"));
		setDataCreazioneFlussiCBI(data.getDate("QUA_GQUACREA"));
		setNomeSupporto(data.getString("QUA_DQUASUPP"));
		setCodiceSIADestinatario(data.getString("QUA_CQUACSIA"));
		setCodiceCINBancaMittente(data.getString("QUA_CQUACCIN"));
		setCodiceABIBancaMittente(data.getString("QUA_CQUACABI"));
		setCodiceCABBancaMittente(data.getString("QUA_CQUACCAB"));
		setNumeroContoCorrente(data.getString("QUA_CQUACCCB"));
		setDataValuta(data.getDate("QUA_GQUAVALU"));
		setDataContabile(data.getDate("QUA_GQUACONT,"));
		setImportoMovimento(data.getBigDecimal("QUA_IQUAMOVI"));
		setRiferimentoBanca(data.getString("QUA_CQUACCRO"));
		setDataInizioRendicontazione(data.getDate("QUA_GQUADINI"));
		setDataFineRendicontazione(data.getDate("QUA_GQUADEND"));
		setNumeroTransazioni(data.getInt("QUA_NQUATRAN"));
		setCodiceCartaPagamento(data.getString("QUA_CCARCCAR"));
		setImportoSquadratura(data.getBigDecimal("QUA_IQUASQUA"));
		setStatoQuadratura(data.getString(")QUA_CQUAQUAD"));
		setStatoElaborazione(data.getString("QUA_FQUAELAB"));
		setDataUltimoAggiornamento(data.getDate("QUA_GQUAGAGG"));
		setUtenteUltimoAggiornamento(data.getString("QUA_CQUACOPE"));
	}


	public MovimentoRendicontazione() {
		// TODO Auto-generated constructor stub
	}


	public Integer getChiaveMovimento() {
		return chiaveMovimento;
	}


	public void setChiaveMovimento(Integer chiaveMovimento) {
		this.chiaveMovimento = chiaveMovimento;
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


	public String getChiaveGateway() {
		return chiaveGateway;
	}


	public void setChiaveGateway(String chiaveGateway) {
		this.chiaveGateway = chiaveGateway;
	}


	public String getNomeFileCBI() {
		return nomeFileCBI;
	}


	public void setNomeFileCBI(String nomeFileCBI) {
		this.nomeFileCBI = nomeFileCBI;
	}


	public Date getDataCreazioneFlussiCBI() {
		return dataCreazioneFlussiCBI;
	}


	public void setDataCreazioneFlussiCBI(Date dataCreazioneFlussiCBI) {
		this.dataCreazioneFlussiCBI = dataCreazioneFlussiCBI;
	}


	public String getNomeSupporto() {
		return nomeSupporto;
	}


	public void setNomeSupporto(String nomeSupporto) {
		this.nomeSupporto = nomeSupporto;
	}


	public String getCodiceSIADestinatario() {
		return codiceSIADestinatario;
	}


	public void setCodiceSIADestinatario(String codiceSIADestinatario) {
		this.codiceSIADestinatario = codiceSIADestinatario;
	}


	public String getCodiceCINBancaMittente() {
		return codiceCINBancaMittente;
	}


	public void setCodiceCINBancaMittente(String codiceCINBancaMittente) {
		this.codiceCINBancaMittente = codiceCINBancaMittente;
	}


	public String getCodiceABIBancaMittente() {
		return codiceABIBancaMittente;
	}


	public void setCodiceABIBancaMittente(String codiceABIBancaMittente) {
		this.codiceABIBancaMittente = codiceABIBancaMittente;
	}


	public String getCodiceCABBancaMittente() {
		return codiceCABBancaMittente;
	}


	public void setCodiceCABBancaMittente(String codiceCABBancaMittente) {
		this.codiceCABBancaMittente = codiceCABBancaMittente;
	}


	public String getNumeroContoCorrente() {
		return numeroContoCorrente;
	}


	public void setNumeroContoCorrente(String numeroContoCorrente) {
		this.numeroContoCorrente = numeroContoCorrente;
	}


	public Date getDataValuta() {
		return dataValuta;
	}


	public void setDataValuta(Date dataValuta) {
		this.dataValuta = dataValuta;
	}


	public Date getDataContabile() {
		return dataContabile;
	}


	public void setDataContabile(Date dataContabile) {
		this.dataContabile = dataContabile;
	}


	public BigDecimal getImportoMovimento() {
		return importoMovimento;
	}


	public void setImportoMovimento(BigDecimal importoMovimento) {
		this.importoMovimento = importoMovimento;
	}


	public String getRiferimentoBanca() {
		return riferimentoBanca;
	}


	public void setRiferimentoBanca(String riferimentoBanca) {
		this.riferimentoBanca = riferimentoBanca;
	}


	public Date getDataInizioRendicontazione() {
		return dataInizioRendicontazione;
	}


	public void setDataInizioRendicontazione(Date dataInizioRendicontazione) {
		this.dataInizioRendicontazione = dataInizioRendicontazione;
	}


	public Date getDataFineRendicontazione() {
		return dataFineRendicontazione;
	}


	public void setDataFineRendicontazione(Date dataFineRendicontazione) {
		this.dataFineRendicontazione = dataFineRendicontazione;
	}


	public Integer getNumeroTransazioni() {
		return numeroTransazioni;
	}


	public void setNumeroTransazioni(Integer numeroTransazioni) {
		this.numeroTransazioni = numeroTransazioni;
	}


	public String getCodiceCartaPagamento() {
		return codiceCartaPagamento;
	}


	public void setCodiceCartaPagamento(String codiceCartaPagamento) {
		this.codiceCartaPagamento = codiceCartaPagamento;
	}


	public BigDecimal getImportoSquadratura() {
		return importoSquadratura;
	}


	public void setImportoSquadratura(BigDecimal importoSquadratura) {
		this.importoSquadratura = importoSquadratura;
	}


	public String getStatoQuadratura() {
		return statoQuadratura;
	}


	public void setStatoQuadratura(String statoQuadratura) {
		this.statoQuadratura = statoQuadratura;
	}


	public String getStatoElaborazione() {
		return statoElaborazione;
	}


	public void setStatoElaborazione(String statoElaborazione) {
		this.statoElaborazione = statoElaborazione;
	}


	public Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}


	public void setDataUltimoAggiornamento(Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}


	public String getUtenteUltimoAggiornamento() {
		return utenteUltimoAggiornamento;
	}


	public void setUtenteUltimoAggiornamento(String utenteUltimoAggiornamento) {
		this.utenteUltimoAggiornamento = utenteUltimoAggiornamento;
	}

	

}
