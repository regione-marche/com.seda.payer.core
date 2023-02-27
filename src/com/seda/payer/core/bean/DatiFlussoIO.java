package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatiFlussoIO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String codiceTassonomia;
	String descTipologiaServizio;
	String codiceFiscale;
	Integer progressivoBollettino;
	String dataScadenzaRata;
	BigDecimal importoRataDovuto;
	String numeroAvvisoRata;
    String dataScadenzaDocumento;
    BigDecimal importoBollettinoRata;
    BigDecimal importoBollettinoDocumento;
    String numeroAvvisoDocumento;
	/**
	 * @return the codiceTassonomia
	 */
	public String getCodiceTassonomia() {
		return codiceTassonomia;
	}
	/**
	 * @param codiceTassonomia the codiceTassonomia to set
	 */
	public void setCodiceTassonomia(String codiceTassonomia) {
		this.codiceTassonomia = codiceTassonomia;
	}
	/**
	 * @return the descTipologiaServizio
	 */
	public String getDescTipologiaServizio() {
		return descTipologiaServizio;
	}
	/**
	 * @param descTipologiaServizio the descTipologiaServizio to set
	 */
	public void setDescTipologiaServizio(String descTipologiaServizio) {
		this.descTipologiaServizio = descTipologiaServizio;
	}
	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	/**
	 * @return the progressivoBollettino
	 */
	public Integer getProgressivoBollettino() {
		return progressivoBollettino;
	}
	/**
	 * @param progressivoBollettino the progressivoBollettino to set
	 */
	public void setProgressivoBollettino(Integer progressivoBollettino) {
		this.progressivoBollettino = progressivoBollettino;
	}
	/**
	 * @return the dataScadenzaRata
	 */
	public String getDataScadenzaRata() {
		return dataScadenzaRata;
	}
	/**
	 * @param dataScadenzaRata the dataScadenzaRata to set
	 */
	public void setDataScadenzaRata(String dataScadenzaRata) {
		this.dataScadenzaRata = dataScadenzaRata;
	}
	/**
	 * @return the importoRataDovuto
	 */
	public BigDecimal getImportoRataDovuto() {
		return importoRataDovuto;
	}
	/**
	 * @param importoRataDovuto the importoRataDovuto to set
	 */
	public void setImportoRataDovuto(BigDecimal importoRataDovuto) {
		this.importoRataDovuto = importoRataDovuto;
	}
	/**
	 * @return the numeroAvvisoRata
	 */
	public String getNumeroAvvisoRata() {
		return numeroAvvisoRata;
	}
	/**
	 * @param numeroAvvisoRata the numeroAvvisoRata to set
	 */
	public void setNumeroAvvisoRata(String numeroAvvisoRata) {
		this.numeroAvvisoRata = numeroAvvisoRata;
	}
	/**
	 * @return the dataScadenzaDocumento
	 */
	public String getDataScadenzaDocumento() {
		return dataScadenzaDocumento;
	}
	/**
	 * @param dataScadenzaDocumento the dataScadenzaDocumento to set
	 */
	public void setDataScadenzaDocumento(String dataScadenzaDocumento) {
		this.dataScadenzaDocumento = dataScadenzaDocumento;
	}
	/**
	 * @return the importoBollettinoRata
	 */
	public BigDecimal getImportoBollettinoRata() {
		return importoBollettinoRata;
	}
	/**
	 * @param importoBollettinoRata the importoBollettinoRata to set
	 */
	public void setImportoBollettinoRata(BigDecimal importoBollettinoRata) {
		this.importoBollettinoRata = importoBollettinoRata;
	}
	/**
	 * @return the importoBollettinoDocumento
	 */
	public BigDecimal getImportoBollettinoDocumento() {
		return importoBollettinoDocumento;
	}
	/**
	 * @param importoBollettinoDocumento the importoBollettinoDocumento to set
	 */
	public void setImportoBollettinoDocumento(BigDecimal importoBollettinoDocumento) {
		this.importoBollettinoDocumento = importoBollettinoDocumento;
	}
	/**
	 * @return the numeroAvvisoDocumento
	 */
	public String getNumeroAvvisoDocumento() {
		return numeroAvvisoDocumento;
	}
	/**
	 * @param numeroAvvisoDocumento the numeroAvvisoDocumento to set
	 */
	public void setNumeroAvvisoDocumento(String numeroAvvisoDocumento) {
		this.numeroAvvisoDocumento = numeroAvvisoDocumento;
	}   
    
	public DatiFlussoIO (ResultSet data) throws SQLException {
		if(data == null)
			return;

		this.codiceTassonomia = data.getString("COD_TASSONOMIA");
		this.descTipologiaServizio = data.getString("DES_TIPSERVIZIO");
		this.codiceFiscale = data.getString("EH1_CEH1CFIS");
		this.progressivoBollettino = data.getInt("AR_PROGRESSIVO_BOLLETTINO");
		this.dataScadenzaRata = data.getString("AR_DATA_SCADENZA");
		this.importoRataDovuto = data.getBigDecimal("AR_IMPORTO");
		this.numeroAvvisoRata = data.getString("AR_NUMERO_AVVISO");
		this.dataScadenzaDocumento = data.getString("DOC_DATA_SCADENZA");
		this.importoBollettinoRata = data.getBigDecimal("AR_IMPORTO_BOLL");
		this.importoBollettinoDocumento = data.getBigDecimal("DOC_IMPORTO_TOTALE");
		this.numeroAvvisoDocumento = data.getString("DOC_NUM_AVVISO_PAGO_PA");
	}
    
}