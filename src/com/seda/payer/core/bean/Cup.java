package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import com.seda.payer.commons.bean.Lifecycle;
/**
 * @author aniello.labua
 */
public class Cup extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;
	

	
	private String chiaveTransazione;
    private String codiceSocieta;
	private String codiceUtente;
	private String codiceTabellaBoll;
	private String chiaveTabellaBoll;
	
	private String codiceFiscale;
	private String codicePagamento;
	
	private String flagStato;
	
	private String codiceAziendaIncasso; 
	private String descrizioneAziendaIncasso;
	private String codiceAziendaErogatrice; 
	private String descrizioneAziendaErogatrice;
	private String codiceUnitaErogante; 
	private String descrizioneUnitaErogante;
	private String codiceStrutturaErogante; 
	private String descrizioneStrutturaErogante;
	
	private Date dataAppuntamento;
	private BigDecimal importoTotale;
	
	private String xmlVerificaPagamento;
	private String xmlRegistraPagamento;
	private String xmlRichiestaDocumento;
	
	private Date dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;

	/**
	 * @param codiceFiscale
	 * @param codicePagamento
	 */
	public Cup(String codiceFiscale, String codicePagamento) {
		this.codiceFiscale = codiceFiscale;
		this.codicePagamento = codicePagamento;
	}

	/**
	 * @param chiaveTransazione
	 * @param codiceSocieta
	 * @param codiceUtente
	 * @param codiceTabellaBoll
	 * @param chiaveTabellaBoll
	 * @param codiceFiscale
	 * @param codicePagamento
	 * @param flagStato
	 * @param codiceAziendaIncasso
	 * @param descrizioneAziendaIncasso
	 * @param codiceAziendaErogatrice
	 * @param descrizioneAziendaErogatrice
	 * @param codiceUnitaErogante
	 * @param descrizioneUnitaErogante
	 * @param codiceStrutturaErogante
	 * @param descrizioneStrutturaErogante
	 * @param dataAppuntamento
	 * @param importoTotale
	 * @param xmlVerificaPagamento
	 * @param xmlRegistraPagamento
	 * @param xmlRichiestaDocumento
	 * @param dataUltimoAggiornamento
	 * @param operatoreUltimoAggiornamento
	 */
	public Cup(String chiaveTransazione, String codiceSocieta,
			String codiceUtente, String codiceTabellaBoll,
			String chiaveTabellaBoll, String codiceFiscale,
			String codicePagamento, String flagStato,
			String codiceAziendaIncasso, String descrizioneAziendaIncasso,
			String codiceAziendaErogatrice,
			String descrizioneAziendaErogatrice, String codiceUnitaErogante,
			String descrizioneUnitaErogante, String codiceStrutturaErogante,
			String descrizioneStrutturaErogante, Date dataAppuntamento,
			BigDecimal importoTotale, String xmlVerificaPagamento,
			String xmlRegistraPagamento, String xmlRichiestaDocumento,
			Timestamp dataUltimoAggiornamento,
			String operatoreUltimoAggiornamento) {
		this.chiaveTransazione = chiaveTransazione;
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.codiceTabellaBoll = codiceTabellaBoll;
		this.chiaveTabellaBoll = chiaveTabellaBoll;
		this.codiceFiscale = codiceFiscale;
		this.codicePagamento = codicePagamento;
		this.flagStato = flagStato;
		this.codiceAziendaIncasso = codiceAziendaIncasso;
		this.descrizioneAziendaIncasso = descrizioneAziendaIncasso;
		this.codiceAziendaErogatrice = codiceAziendaErogatrice;
		this.descrizioneAziendaErogatrice = descrizioneAziendaErogatrice;
		this.codiceUnitaErogante = codiceUnitaErogante;
		this.descrizioneUnitaErogante = descrizioneUnitaErogante;
		this.codiceStrutturaErogante = codiceStrutturaErogante;
		this.descrizioneStrutturaErogante = descrizioneStrutturaErogante;
		this.dataAppuntamento = dataAppuntamento;
		this.importoTotale = importoTotale;
		this.xmlVerificaPagamento = xmlVerificaPagamento;
		this.xmlRegistraPagamento = xmlRegistraPagamento;
		this.xmlRichiestaDocumento = xmlRichiestaDocumento;
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}

	/**
	 * Default constructor
	 */
	public Cup() {	}
	

	/**
	 * 
	 * @param data
	 * @throws SQLException
	 */
	public Cup(ResultSet data) throws SQLException {
		if(data == null)
			return;
		this.chiaveTransazione = data.getString("CUP_KTRAKTRA");
		this.codiceSocieta = data.getString("CUP_CSOCCSOC");
		this.codiceUtente = data.getString("CUP_CUTECUTE");
		this.codiceTabellaBoll = data.getString("CUP_CODTCODT");
		this.chiaveTabellaBoll = data.getString("CUP_KKEYKKEY");

		this.codiceFiscale = data.getString("CUP_CCUPCFIS");
		this.codicePagamento = data.getString("CUP_CCUPCPAG");

		this.flagStato = data.getString("CUP_CCUPFLAG");

		this.codiceAziendaIncasso = data.getString("CUP_CCUPCAI");
		this.descrizioneAziendaIncasso = data.getString("CUP_CCUPDAI");
		this.codiceAziendaErogatrice = data.getString("CUP_CCUPCAE");
		this.descrizioneAziendaErogatrice = data.getString("CUP_CCUPDAE");
		this.codiceUnitaErogante = data.getString("CUP_CCUPCUE");
		this.descrizioneUnitaErogante = data.getString("CUP_CCUPDUE");
		this.codiceStrutturaErogante = data.getString("CUP_CCUPCSE");
		this.descrizioneStrutturaErogante = data.getString("CUP_CCUPDSE");

		try {
			this.dataAppuntamento = data.getDate("CUP_GCUPDAP");
		}
		catch (Exception e) {
			this.dataAppuntamento = null;
		}
		this.importoTotale = data.getBigDecimal("CUP_ICUPIMPO");
		
		this.xmlVerificaPagamento = data.getString("CUP_CCUPXVER");
		this.xmlRegistraPagamento = data.getString("CUP_CCUPXPAG");
		this.xmlRichiestaDocumento = data.getString("CUP_CCUPXDOC");

		this.dataUltimoAggiornamento = data.getTimestamp("CUP_GCUPGAGG");
		this.operatoreUltimoAggiornamento = data.getString("CUP_CCUPCOPE");

	}

	
	/**
	 * 
	 */
	public void onSave(CallableStatement arg) throws SQLException {

		arg.setString(1, this.chiaveTransazione);
		arg.setString(2, this.codiceSocieta);	
		arg.setString(3, this.codiceUtente);	
		arg.setString(4, this.codiceTabellaBoll);
		arg.setString(5, this.chiaveTabellaBoll);
		arg.setString(6, this.codiceFiscale);	
		arg.setString(7, this.codicePagamento);
		arg.setString(8, this.flagStato);
		arg.setString(9, this.codiceAziendaIncasso);
		arg.setString(10, this.descrizioneAziendaIncasso);
		arg.setString(11, this.codiceAziendaErogatrice);
		arg.setString(12, this.descrizioneAziendaErogatrice);
		arg.setString(13, this.codiceUnitaErogante);
		arg.setString(14, this.descrizioneUnitaErogante);
		arg.setString(15, this.codiceStrutturaErogante);
		arg.setString(16, this.descrizioneStrutturaErogante);
		arg.setDate(17,  new  java.sql.Date(this.dataAppuntamento.getTime()));
		arg.setBigDecimal(18, this.importoTotale);
		arg.setString(19, this.xmlVerificaPagamento);
		arg.setString(20, this.xmlRegistraPagamento);
		arg.setString(21, this.xmlRichiestaDocumento);
		arg.setDate(22, new  java.sql.Date(this.dataUltimoAggiornamento.getTime()));
		arg.setString(23, this.operatoreUltimoAggiornamento);
	}
	
	/**
	 * 
	 */
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.chiaveTransazione);
		arg.setString(2, this.codiceSocieta);	
		arg.setString(3, this.codiceUtente);	
		arg.setString(4, this.codiceTabellaBoll);
		arg.setString(5, this.chiaveTabellaBoll);
		arg.setString(6, this.codiceFiscale);	
		arg.setString(7, this.codicePagamento);
		arg.setString(8, this.flagStato);
		arg.setString(9, this.codiceAziendaIncasso);
		arg.setString(10, this.descrizioneAziendaIncasso);
		arg.setString(11, this.codiceAziendaErogatrice);
		arg.setString(12, this.descrizioneAziendaErogatrice);
		arg.setString(13, this.codiceUnitaErogante);
		arg.setString(14, this.descrizioneUnitaErogante);
		arg.setString(15, this.codiceStrutturaErogante);
		arg.setString(16, this.descrizioneStrutturaErogante);
		if(this.dataAppuntamento!=null)
			arg.setDate(17,  new  java.sql.Date(this.dataAppuntamento.getTime()));
		else 
			arg.setNull(17, Types.DATE);
		arg.setBigDecimal(18, this.importoTotale);
		arg.setString(19, this.xmlVerificaPagamento);
		arg.setString(20, this.xmlRegistraPagamento);
		arg.setString(21, this.xmlRichiestaDocumento);
		if(this.dataUltimoAggiornamento!=null)
			arg.setDate(22, new  java.sql.Date(this.dataUltimoAggiornamento.getTime()));
		else
			arg.setNull(22, Types.DATE);
		arg.setString(23, this.operatoreUltimoAggiornamento);
	}
	public void onDelete(CallableStatement arg) throws SQLException {
//		arg.setString(1, this.codiceSocieta);
//		arg.setString(2, this.codiceUtente);
//		arg.setString(3, this.codiceEnte);
	}

	/**
	 * 
	 */
	public void onLoad(CallableStatement arg) throws SQLException {
		
		arg.setString(1, this.codiceFiscale);
		arg.setString(2, this.codicePagamento);

	}
	
	/**
	 * 
	 */
//	public void onLoad(CallableStatement arg, int rowsPerPage, int pageNumber, String order) throws SQLException {
//		arg.setString(1, this.codiceSocieta);
//		arg.setString(2, this.codiceUtente);
//		arg.setString(3, this.codiceEnte);
//		arg.setString(4, this.siglaProvincia);
//		arg.setString(5, this.flagWebServiceOttico);
//		arg.setString(6, order);
//		arg.setInt(7, rowsPerPage);
//		arg.setInt(8, pageNumber);
//		/* we register row start */
//		arg.registerOutParameter(9, Types.INTEGER);
//		/* we register row end */
//		arg.registerOutParameter(10, Types.INTEGER);
//		/* we register total rows */
//		arg.registerOutParameter(11, Types.INTEGER);
//		/* we register total pages */
//		arg.registerOutParameter(12, Types.SMALLINT);
//	}

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

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceTabellaBoll() {
		return codiceTabellaBoll;
	}

	public void setCodiceTabellaBoll(String codiceTabellaBoll) {
		this.codiceTabellaBoll = codiceTabellaBoll;
	}

	public String getChiaveTabellaBoll() {
		return chiaveTabellaBoll;
	}

	public void setChiaveTabellaBoll(String chiaveTabellaBoll) {
		this.chiaveTabellaBoll = chiaveTabellaBoll;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodicePagamento() {
		return codicePagamento;
	}

	public void setCodicePagamento(String codicePagamento) {
		this.codicePagamento = codicePagamento;
	}

	public String getFlagStato() {
		return flagStato;
	}

	public void setFlagStato(String flagStato) {
		this.flagStato = flagStato;
	}

	public String getCodiceAziendaIncasso() {
		return codiceAziendaIncasso;
	}

	public void setCodiceAziendaIncasso(String codiceAziendaIncasso) {
		this.codiceAziendaIncasso = codiceAziendaIncasso;
	}

	public String getDescrizioneAziendaIncasso() {
		return descrizioneAziendaIncasso;
	}

	public void setDescrizioneAziendaIncasso(String descrizioneAziendaIncasso) {
		this.descrizioneAziendaIncasso = descrizioneAziendaIncasso;
	}

	public String getCodiceAziendaErogatrice() {
		return codiceAziendaErogatrice;
	}

	public void setCodiceAziendaErogatrice(String codiceAziendaErogatrice) {
		this.codiceAziendaErogatrice = codiceAziendaErogatrice;
	}

	public String getDescrizioneAziendaErogatrice() {
		return descrizioneAziendaErogatrice;
	}

	public void setDescrizioneAziendaErogatrice(String descrizioneAziendaErogatrice) {
		this.descrizioneAziendaErogatrice = descrizioneAziendaErogatrice;
	}

	public String getCodiceUnitaErogante() {
		return codiceUnitaErogante;
	}

	public void setCodiceUnitaErogante(String codiceUnitaErogante) {
		this.codiceUnitaErogante = codiceUnitaErogante;
	}

	public String getDescrizioneUnitaErogante() {
		return descrizioneUnitaErogante;
	}

	public void setDescrizioneUnitaErogante(String descrizioneUnitaErogante) {
		this.descrizioneUnitaErogante = descrizioneUnitaErogante;
	}

	public String getCodiceStrutturaErogante() {
		return codiceStrutturaErogante;
	}

	public void setCodiceStrutturaErogante(String codiceStrutturaErogante) {
		this.codiceStrutturaErogante = codiceStrutturaErogante;
	}

	public String getDescrizioneStrutturaErogante() {
		return descrizioneStrutturaErogante;
	}

	public void setDescrizioneStrutturaErogante(String descrizioneStrutturaErogante) {
		this.descrizioneStrutturaErogante = descrizioneStrutturaErogante;
	}

	public Date getDataAppuntamento() {
		return dataAppuntamento;
	}

	public void setDataAppuntamento(Date dataAppuntamento) {
		this.dataAppuntamento = dataAppuntamento;
	}

	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public String getXmlVerificaPagamento() {
		return xmlVerificaPagamento;
	}

	public void setXmlVerificaPagamento(String xmlVerificaPagamento) {
		this.xmlVerificaPagamento = xmlVerificaPagamento;
	}

	public String getXmlRegistraPagamento() {
		return xmlRegistraPagamento;
	}

	public void setXmlRegistraPagamento(String xmlRegistraPagamento) {
		this.xmlRegistraPagamento = xmlRegistraPagamento;
	}

	public String getXmlRichiestaDocumento() {
		return xmlRichiestaDocumento;
	}

	public void setXmlRichiestaDocumento(String xmlRichiestaDocumento) {
		this.xmlRichiestaDocumento = xmlRichiestaDocumento;
	}

	public Date getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Date dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}

	@Override
	public String toString() {
		return "Cup [chiaveTabellaBoll=" + chiaveTabellaBoll
				+ ", chiaveTransazione=" + chiaveTransazione
				+ ", codiceAziendaErogatrice=" + codiceAziendaErogatrice
				+ ", codiceAziendaIncasso=" + codiceAziendaIncasso
				+ ", codiceFiscale=" + codiceFiscale + ", codicePagamento="
				+ codicePagamento + ", codiceSocieta=" + codiceSocieta
				+ ", codiceStrutturaErogante=" + codiceStrutturaErogante
				+ ", codiceTabellaBoll=" + codiceTabellaBoll
				+ ", codiceUnitaErogante=" + codiceUnitaErogante
				+ ", codiceUtente=" + codiceUtente + ", dataAppuntamento="
				+ dataAppuntamento + ", dataUltimoAggiornamento="
				+ dataUltimoAggiornamento + ", descrizioneAziendaErogatrice="
				+ descrizioneAziendaErogatrice + ", descrizioneAziendaIncasso="
				+ descrizioneAziendaIncasso + ", descrizioneStrutturaErogante="
				+ descrizioneStrutturaErogante + ", descrizioneUnitaErogante="
				+ descrizioneUnitaErogante + ", flagStato=" + flagStato
				+ ", importoTotale=" + importoTotale
				+ ", operatoreUltimoAggiornamento="
				+ operatoreUltimoAggiornamento + ", xmlRegistraPagamento="
				+ xmlRegistraPagamento + ", xmlRichiestaDocumento="
				+ xmlRichiestaDocumento + ", xmlVerificaPagamento="
				+ xmlVerificaPagamento + "]";
	}
	

	
}