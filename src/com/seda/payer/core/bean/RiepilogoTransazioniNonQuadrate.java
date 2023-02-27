package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiepilogoTransazioniNonQuadrate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal importoMovimenti = BigDecimal.ZERO;
	private BigDecimal importoNettoTransazioni= BigDecimal.ZERO;
	private BigDecimal squadratura= BigDecimal.ZERO;
	
	public RiepilogoTransazioniNonQuadrate(BigDecimal importoNettoTransazioni, BigDecimal importoMovimenti, BigDecimal squadratura) {
		this.importoNettoTransazioni = importoNettoTransazioni;
		this.importoMovimenti = importoMovimenti;
		this.squadratura = squadratura;
	}

	public BigDecimal getImportoMovimenti() {
		return importoMovimenti;
	}

	public BigDecimal getImportoNettoTransazioni() {
		return importoNettoTransazioni;
	}

	public BigDecimal getSquadratura() {
		return squadratura;
	}
	
}
