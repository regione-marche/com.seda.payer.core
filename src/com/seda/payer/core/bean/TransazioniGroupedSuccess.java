package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransazioniGroupedSuccess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String canale;
	private BigDecimal importoEC = BigDecimal.ZERO;
	private BigDecimal importoPG = BigDecimal.ZERO;
	private BigDecimal importoTotale = BigDecimal.ZERO;
	private int numeroBollettiniEC;
	private int numeroBollettiniPG;
	private int numeroBollettini;
	
	
	public TransazioniGroupedSuccess(ResultSet rs) throws SQLException {
		setCanale(rs.getString("CAN_DCANDCAN"));
		setImportoEC(rs.getBigDecimal("IMPORTO_EC"));
		setImportoPG(rs.getBigDecimal("IMPORTO_PG"));
		setNumeroBollettiniEC(rs.getInt("NUM_BOLL_EC"));
		setNumeroBollettiniPG(rs.getInt("NUM_BOLL_PG"));
		setImportoTotale(rs.getBigDecimal("IMPORTO_TOTAL"));
		setNumeroBollettini(rs.getInt("NUM_BOLL_TOTAL"));
	}

	public String getCanale() {
		return canale;
	}

	public void setCanale(String canale) {
		this.canale = canale;
	}

	public BigDecimal getImportoEC() {
		return importoEC;
	}

	public void setImportoEC(BigDecimal importoEC) {
		this.importoEC = importoEC;
	}

	public BigDecimal getImportoPG() {
		return importoPG;
	}

	public void setImportoPG(BigDecimal importoPG) {
		this.importoPG = importoPG;
	}

	public int getNumeroBollettiniEC() {
		return numeroBollettiniEC;
	}

	public void setNumeroBollettiniEC(int numeroBollettiniEC) {
		this.numeroBollettiniEC = numeroBollettiniEC;
	}

	public int getNumeroBollettiniPG() {
		return numeroBollettiniPG;
	}

	public void setNumeroBollettiniPG(int numeroBollettiniPG) {
		this.numeroBollettiniPG = numeroBollettiniPG;
	}

	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public int getNumeroBollettini() {
		return numeroBollettini;
	}

	public void setNumeroBollettini(int numeroBollettini) {
		this.numeroBollettini = numeroBollettini;
	}

}
