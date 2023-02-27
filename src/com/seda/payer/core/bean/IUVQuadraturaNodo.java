package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.lang.Long;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class IUVQuadraturaNodo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int posizioneNelFlusso;
    private Long keyQuadratura;
    private String identificativoUnivocoVersamento;
    private BigDecimal importo;    
    private BigDecimal importoRpt;    
    private BigDecimal importoSpese;    
    private String esitoPagamento;
    private String identificativoUnivocoRiscossione;
    private Timestamp dtEsitoPagamento;
    private String codiceQuadratura;
    private Timestamp dtMakeRec;
    private Timestamp dtAggRec;
    private String operatore;
    
	public int getPosizioneNelFlusso() {
		return posizioneNelFlusso;
	}

	public void setPosizioneNelFlusso(int posizioneNelFlusso) {
		this.posizioneNelFlusso = posizioneNelFlusso;
	}

	public Long getKeyQuadratura() {
		return keyQuadratura;
	}

	public void setKeyQuadratura(Long keyQuadratura) {
		this.keyQuadratura = keyQuadratura;
	}

	public String getIdentificativoUnivocoVersamento() {
		return identificativoUnivocoVersamento;
	}

	public void setIdentificativoUnivocoVersamento(String identificativoUnivocoVersamento) {
		this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public BigDecimal getImportoRpt() {
		return importoRpt;
	}

	public void setImportoRpt(BigDecimal importoRpt) {
		this.importoRpt = importoRpt;
	}

	public BigDecimal getImportoSpese() {
		return importoSpese;
	}

	public void setImportoSpese(BigDecimal importoSpese) {
		this.importoSpese = importoSpese;
	}

	public String getEsitoPagamento() {
		return esitoPagamento;
	}

	public void setEsitoPagamento(String esitoPagamento) {
		this.esitoPagamento = esitoPagamento;
	}

	public String getIdentificativoUnivocoRiscossione() {
		return identificativoUnivocoRiscossione;
	}

	public void setIdentificativoUnivocoRiscossione(String identificativoUnivocoRiscossione) {
		this.identificativoUnivocoRiscossione = identificativoUnivocoRiscossione;
	}

	public Timestamp getDtEsitoPagamento() {
		return dtEsitoPagamento;
	}

	public void setDtEsitoPagamento(Timestamp dtEsitoPagamento) {
		this.dtEsitoPagamento = dtEsitoPagamento;
	}

	public String getCodiceQuadratura() {
		return codiceQuadratura;
	}

	public void setCodiceQuadratura(String codiceQuadratura) {
		this.codiceQuadratura = codiceQuadratura;
	}

	public Timestamp getDtMakeRec() {
		return dtMakeRec;
	}

	public void setDtMakeRec(Timestamp dtMakeRec) {
		this.dtMakeRec = dtMakeRec;
	}

	public Timestamp getDtAggRec() {
		return dtAggRec;
	}

	public void setDtAggRec(Timestamp dtAggRec) {
		this.dtAggRec = dtAggRec;
	}

	public String getOperatore() {
		return operatore;
	}

	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	public IUVQuadraturaNodo() {
		super();
	}

	public IUVQuadraturaNodo(int posizioneNelFlusso, Long keyQuadratura, String identificativoUnivocoVersamento, BigDecimal importo,
			BigDecimal importoRpt, BigDecimal importoSpese, String esitoPagamento, String identificativoUnivocoRiscossione,
			Timestamp dtEsitoPagamento, String codiceQuadratura, Timestamp dtMakeRec, Timestamp dtAggRec,
			String operatore) {
		super();
		this.posizioneNelFlusso = posizioneNelFlusso;
		this.keyQuadratura = keyQuadratura;
		this.identificativoUnivocoVersamento = identificativoUnivocoVersamento;
		this.importo = importo;
		this.importoRpt = importoRpt;
		this.importoSpese = importoSpese;
		this.esitoPagamento = esitoPagamento;
		this.identificativoUnivocoRiscossione = identificativoUnivocoRiscossione;
		this.dtEsitoPagamento = dtEsitoPagamento;
		this.codiceQuadratura = codiceQuadratura;
		this.dtMakeRec = dtMakeRec;
		this.dtAggRec = dtAggRec;
		this.operatore = operatore;
	}

	public static IUVQuadraturaNodo getBean(ResultSet data) throws SQLException
	{
		if (data == null)
			return null;
		IUVQuadraturaNodo bean = new IUVQuadraturaNodo(); 
	    bean.setPosizioneNelFlusso(data.getInt("QUI_PQUIPPOS"));
	    bean.setKeyQuadratura(data.getLong("QUI_PQUIPKEY"));
	    bean.setIdentificativoUnivocoVersamento(data.getString("QUI_KQUIKIUV"));
	    bean.setImporto(data.getBigDecimal("QUI_INODIMPO"));
	    bean.setImportoRpt(data.getBigDecimal("QUI_IRPTIMPO"));
	    bean.setImportoSpese(data.getBigDecimal("QUI_INODSPES"));
	    bean.setEsitoPagamento(data.getString("QUI_CQUICESI"));
	    bean.setIdentificativoUnivocoRiscossione(data.getString("QUI_CNODCIUR"));
	    bean.setDtEsitoPagamento(data.getTimestamp("QUI_GQUIDATE"));
	    bean.setCodiceQuadratura(data.getString("QUI_CQUICODE"));
	    bean.setDtMakeRec(data.getTimestamp("QUI_GQUIMAKE"));
	    bean.setDtAggRec(data.getTimestamp("QUI_GQUIGAGG"));
	    bean.setOperatore(data.getString("QUI_CQUICOPE"));
	    return bean;
	}
}