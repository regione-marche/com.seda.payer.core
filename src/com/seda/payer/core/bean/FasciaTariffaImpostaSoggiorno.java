package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class FasciaTariffaImpostaSoggiorno implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Long chiaveFasciaTariffa;
	private String chiaveTariffa;
	private String codice;
	private String descrizione;
	private BigDecimal importoTariffa;
	private Calendar dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	
	public FasciaTariffaImpostaSoggiorno() {}
	
	public FasciaTariffaImpostaSoggiorno(String chiaveTariffa) {
		this(chiaveTariffa, "", "", new BigDecimal(0), "" );		
	}

	public FasciaTariffaImpostaSoggiorno(
			String chiaveTariffa,
			String codice,
			String desc,
			BigDecimal importoTariffa,
			String operatoreUltimoAggiornamento) {
		super();
		this.chiaveTariffa = chiaveTariffa;
		this.codice = codice;
		this.descrizione = desc;
		this.importoTariffa = importoTariffa;
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}

	public FasciaTariffaImpostaSoggiorno(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	setChiaveFasciaTariffa(data.getLong("SFI_KSFIISFI"));
    	setChiaveTariffa(data.getString("SFI_KSFIKSTF"));
    	setCodice(data.getString("SFI_CSFICODE"));
    	setDescrizione(data.getString("SFI_CSFIDESC"));
    	setImportoTariffa(data.getBigDecimal("SFI_ISFIITAR"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTimestamp("SFI_GSFIGAGG").getTime());
        setDataUltimoAggiornamento(cal);
        setOperatoreUltimoAggiornamento(data.getString("SFI_CSFICOPE"));
    }

	public Long getChiaveFasciaTariffa() {
		return chiaveFasciaTariffa;
	}

	public void setChiaveFasciaTariffa(Long chiaveFasciaTariffa) {
		this.chiaveFasciaTariffa = chiaveFasciaTariffa;
	}

	public String getChiaveTariffa() {
		return chiaveTariffa;
	}

	public void setChiaveTariffa(String chiaveTariffa) {
		this.chiaveTariffa = chiaveTariffa;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public BigDecimal getImportoTariffa() {
		return importoTariffa;
	}

	public void setImportoTariffa(BigDecimal importoTariffa) {
		this.importoTariffa = importoTariffa;
	}

	public Calendar getDataUltimoAggiornamento() {
		return dataUltimoAggiornamento;
	}

	public void setDataUltimoAggiornamento(Calendar dataUltimoAggiornamento) {
		this.dataUltimoAggiornamento = dataUltimoAggiornamento;
	}

	public String getOperatoreUltimoAggiornamento() {
		return operatoreUltimoAggiornamento;
	}

	public void setOperatoreUltimoAggiornamento(String operatoreUltimoAggiornamento) {
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}
	
}
