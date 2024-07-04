package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TariffaImpostaSoggiorno implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String chiaveTariffa;
	private String codiceBelfiore;
	private String chiaveTipologiaStruttura;
	private Date dataValiditaTariffa;
	private BigDecimal importoTariffa;
	private Calendar dataUltimoAggiornamento;
	private String operatoreUltimoAggiornamento;
	
	private String descrizioneComune;
	private String descrizioneTipologiaRicettiva;
	
	private String ricercaDataDa;
	private String ricercaDataA;
	
    //inizio LP PG1800XX_016
    private List<FasciaTariffaImpostaSoggiorno> fasceTariffa;
    //fine LP PG1800XX_016
	
	
	public TariffaImpostaSoggiorno() {}


	
	
	public TariffaImpostaSoggiorno(String chiaveTariffa) {
		this(chiaveTariffa, "", "", new Date(), new BigDecimal(0), "" );		
	}




	public TariffaImpostaSoggiorno(String chiaveTariffa, String codiceBelfiore,
			String chiaveTipologiaStruttura, Date dataValiditaTariffa,
			BigDecimal importoTariffa,
			String operatoreUltimoAggiornamento) {
		super();
		this.chiaveTariffa = chiaveTariffa;
		this.codiceBelfiore = codiceBelfiore;
		this.chiaveTipologiaStruttura = chiaveTipologiaStruttura;
		this.dataValiditaTariffa = dataValiditaTariffa;
		this.importoTariffa = importoTariffa;
		this.operatoreUltimoAggiornamento = operatoreUltimoAggiornamento;
	}



	public TariffaImpostaSoggiorno(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setChiaveTariffa(data.getString("STF_KSTFKSTF"));
    	setCodiceBelfiore(data.getString("STF_CANEBELF"));
    	setChiaveTipologiaStruttura(data.getString("STF_CSTFCSSR"));
    	setDataValiditaTariffa(data.getDate("STF_GSTFGVAL"));
    	setImportoTariffa(data.getBigDecimal("STF_ISTFITAR"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTimestamp("STF_GSTFGAGG").getTime());
        setDataUltimoAggiornamento(cal);
        setOperatoreUltimoAggiornamento(data.getString("STF_CSTFCOPE"));

    	setDescrizioneComune("");
       	setDescrizioneTipologiaRicettiva("");

    }
	
	
	public TariffaImpostaSoggiorno(ResultSet data, boolean completo) throws SQLException
    {
    	if (data == null)
    		return;
    	
    	setChiaveTariffa(data.getString("STF_KSTFKSTF"));
    	setCodiceBelfiore(data.getString("STF_CANEBELF"));
    	setChiaveTipologiaStruttura(data.getString("STF_CSTFCSSR"));
    	setDataValiditaTariffa(data.getDate("STF_GSTFGVAL"));
    	setImportoTariffa(data.getBigDecimal("STF_ISTFITAR"));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(data.getTimestamp("STF_GSTFGAGG").getTime());
        setDataUltimoAggiornamento(cal);
        setOperatoreUltimoAggiornamento(data.getString("STF_CSTFCOPE"));

    	setDescrizioneComune(data.getString("APC_DAPCDCOM"));
       	setDescrizioneTipologiaRicettiva(data.getString("SSR_DSSRDSSR"));
    }

	public String getChiaveTariffa() {
		return chiaveTariffa;
	}
	public void setChiaveTariffa(String chiaveTariffa) {
		this.chiaveTariffa = chiaveTariffa;
	}
	public String getCodiceBelfiore() {
		return codiceBelfiore;
	}
	public void setCodiceBelfiore(String codiceBelfiore) {
		this.codiceBelfiore = codiceBelfiore;
	}
	public String getChiaveTipologiaStruttura() {
		return chiaveTipologiaStruttura;
	}
	public void setChiaveTipologiaStruttura(String chiaveTipologiaStruttura) {
		this.chiaveTipologiaStruttura = chiaveTipologiaStruttura;
	}
	public Date getDataValiditaTariffa() {
		return dataValiditaTariffa;
	}
	public void setDataValiditaTariffa(Date dataValiditaTariffa) {
		this.dataValiditaTariffa = dataValiditaTariffa;
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

	public String getDescrizioneComune() {
		return descrizioneComune;
	}

	public void setDescrizioneComune(String descrizioneComune) {
		this.descrizioneComune = descrizioneComune;
	}

	public String getDescrizioneTipologiaRicettiva() {
		return descrizioneTipologiaRicettiva;
	}

	public void setDescrizioneTipologiaRicettiva(
			String descrizioneTipologiaRicettiva) {
		this.descrizioneTipologiaRicettiva = descrizioneTipologiaRicettiva;
	}

	public String getRicercaDataDa() {
		return ricercaDataDa;
	}

	public void setRicercaDataDa(String ricDataDataDa) {
		this.ricercaDataDa = ricDataDataDa;
	}

	public String getRicercaDataA() {
		return ricercaDataA;
	}

	public void setRicercaDataA(String ricercaDataA) {
		this.ricercaDataA = ricercaDataA;
	}

    //inizio LP PG1800XX_016
	public List<FasciaTariffaImpostaSoggiorno> getFasceTariffa() {
		return fasceTariffa;
	}

	public void setFasceTariffa(List<FasciaTariffaImpostaSoggiorno> fasceTariffa) {
		this.fasceTariffa = fasceTariffa;
	}
    //fine LP PG1800XX_016


	@Override
	public String toString() {
		return "TariffaImpostaSoggiorno{" +
				"chiaveTariffa='" + chiaveTariffa + '\'' +
				", codiceBelfiore='" + codiceBelfiore + '\'' +
				", chiaveTipologiaStruttura='" + chiaveTipologiaStruttura + '\'' +
				", dataValiditaTariffa=" + dataValiditaTariffa +
				", importoTariffa=" + importoTariffa +
				", dataUltimoAggiornamento=" + dataUltimoAggiornamento +
				", operatoreUltimoAggiornamento='" + operatoreUltimoAggiornamento + '\'' +
				", descrizioneComune='" + descrizioneComune + '\'' +
				", descrizioneTipologiaRicettiva='" + descrizioneTipologiaRicettiva + '\'' +
				", ricercaDataDa='" + ricercaDataDa + '\'' +
				", ricercaDataA='" + ricercaDataA + '\'' +
				", fasceTariffa=" + fasceTariffa +
				'}';
	}
}
