package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransazioniGrouped implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String carta;
	private BigDecimal importiTotali = BigDecimal.ZERO;
	private BigDecimal costiTransazioni = BigDecimal.ZERO;
	private BigDecimal speseNotifica = BigDecimal.ZERO;
	private BigDecimal costiBanca = BigDecimal.ZERO;
	private BigDecimal totaleNettoBanca = BigDecimal.ZERO;
	private BigDecimal totaleNettoBollettini = BigDecimal.ZERO;
	
	public TransazioniGrouped(ResultSet rs) throws SQLException {
		setCarta(rs.getString(1));
		setImportiTotali(rs.getBigDecimal(2));
		setCostiTransazioni(rs.getBigDecimal(3));
		setSpeseNotifica(rs.getBigDecimal(4));
		setCostiBanca(rs.getBigDecimal(5));
		setTotaleNettoBanca(rs.getBigDecimal(6));
		setTotaleNettoBollettini(rs.getBigDecimal(7));
	}
	
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}
	public BigDecimal getImportiTotali() {
		return importiTotali;
	}
	public void setImportiTotali(BigDecimal importiTotali) {
		this.importiTotali = importiTotali;
	}
	public BigDecimal getCostiTransazioni() {
		return costiTransazioni;
	}
	public void setCostiTransazioni(BigDecimal costiTransazioni) {
		this.costiTransazioni = costiTransazioni;
	}
	public BigDecimal getSpeseNotifica() {
		return speseNotifica;
	}
	public void setSpeseNotifica(BigDecimal speseNotifica) {
		this.speseNotifica = speseNotifica;
	}
	public BigDecimal getCostiBanca() {
		return costiBanca;
	}
	public void setCostiBanca(BigDecimal costiBanca) {
		this.costiBanca = costiBanca;
	}
	public BigDecimal getTotaleNettoBanca() {
		return totaleNettoBanca;
	}
	public void setTotaleNettoBanca(BigDecimal totaleNettoBanca) {
		this.totaleNettoBanca = totaleNettoBanca;
	}
	public BigDecimal getTotaleNettoBollettini() {
		return totaleNettoBollettini;
	}
	public void setTotaleNettoBollettini(BigDecimal totaleNettoBollettini) {
		this.totaleNettoBollettini = totaleNettoBollettini;
	}
	
}
