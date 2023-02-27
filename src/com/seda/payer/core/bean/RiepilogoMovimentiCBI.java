package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiepilogoMovimentiCBI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String carta;
	private int numeroMovimenti;
	private BigDecimal importoMovimenti = BigDecimal.ZERO;
	private int numeroTransazioni;
	private BigDecimal importoQuadratura = BigDecimal.ZERO;
	
	
	public RiepilogoMovimentiCBI(ResultSet rs) throws SQLException {
		this.carta = rs.getString(1);
		this.numeroMovimenti= rs.getInt(2);
		this.importoMovimenti = rs.getBigDecimal(3);
		this.numeroTransazioni = rs.getInt(4);
		//inizio LP PG200070
		try {
		//fine LP PG200070
		this.importoQuadratura = rs.getBigDecimal(5);
		//inizio LP PG200070
		} catch (Exception e) {
			// bypass assenza colonne
			// su PYQUASP_LST_GROUPED una colonna non e' presente
			this.importoQuadratura = BigDecimal.ZERO;
		}
		//fine LP PG200070
	}
	
	public BigDecimal getImportoQuadratura() {
		return importoQuadratura;
	}

	public void setImportoQuadratura(BigDecimal importoQuadratura) {
		this.importoQuadratura = importoQuadratura;
	}

	public String getCarta() {
		return carta;
	}

	public int getNumeroMovimenti() {
		return numeroMovimenti;
	}

	public BigDecimal getImportoMovimenti() {
		return importoMovimenti;
	}

	public int getNumeroTransazioni() {
		return numeroTransazioni;
	}
}
