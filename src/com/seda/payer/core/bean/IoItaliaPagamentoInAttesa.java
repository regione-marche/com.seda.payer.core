package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class IoItaliaPagamentoInAttesa {

	private final String servizio;
	private final Date dataScadenza;
	private final BigDecimal importo;
	private final String codiceFiscale;
	private final String avvisoPagoPa;
	private final int rata;
	private final int numeroRate;
	private final String scadenzaPagamento;
	
	/**
	 * @param rs
	 * @param sorgente: 0 archivio pagamenti in attesa Pagonet, 1 archivio pagamenti blackbox
	 * @throws SQLException
	 */
	public IoItaliaPagamentoInAttesa(ResultSet rs, int sorgente) throws SQLException {
		
		switch(sorgente) {
		case 0:
		default:
			this.servizio = rs.getString("EH6_DEH6DISE");
			this.dataScadenza = rs.getDate("EH2_GEH2SCAD");
			this.importo = rs.getBigDecimal("EH2_IEH2BOLL");
			this.codiceFiscale = rs.getString("EH1_CEH1CFIS");
			this.avvisoPagoPa = rs.getString("EH2_CEH2CBOL");
			this.rata = rs.getInt("EH2_PEH2RATA");
			this.numeroRate = rs.getInt("NUMERO_RATE");
			break;
		case 1:
			this.servizio = null;
			this.dataScadenza = rs.getDate("DOC_GDOCSCAD");
			this.importo = rs.getBigDecimal("DOC_IDOCIIMP").divide(new BigDecimal(100));
			this.codiceFiscale = rs.getString("DOC_CDOCCFIS");
			this.avvisoPagoPa = rs.getString("DOC_CDOCCIUV");
			this.rata = rs.getInt("DOC_CDOCNRAT");
			this.numeroRate = this.rata;
			break;
		}
		
		this.scadenzaPagamento = "0";
	}
	
	public IoItaliaPagamentoInAttesa(ResultSet rs) throws SQLException {
		this(rs, 0);
	}

	public String getServizio() {
		return servizio;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public boolean isAcconto() {
		return rata != numeroRate;
	}

	public boolean isSaldo() {
		return rata == numeroRate;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getAvvisoPagoPa() {
		return avvisoPagoPa;
	}

	public String getScadenzaPagamento() {
		return scadenzaPagamento;
	}

}
