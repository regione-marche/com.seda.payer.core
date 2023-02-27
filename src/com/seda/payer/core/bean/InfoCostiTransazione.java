package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoCostiTransazione implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipoCosto;
	private BigDecimal importoMinimoFasciaDa;
	private BigDecimal importoMassimoFasciaA;
	private BigDecimal importoFissoFascia;
	private BigDecimal percentualeFascia;
	private String chiaveGateway;
	private String nomeGateway;
	private String imgGateway;
	private String tipoGateway;
	private String zoneGateway;
	
	public InfoCostiTransazione(ResultSet data) throws SQLException {
    	if (data == null)
    		return;

    	setTipoCosto(data.getString("CST_TCSTCOST"));
    	setImportoMinimoFasciaDa(data.getBigDecimal("CST_ICSTFSCD"));
    	setImportoMassimoFasciaA(data.getBigDecimal("CST_ICSTFSCA"));
    	setImportoFissoFascia(data.getBigDecimal("CST_ICSTFISS"));
    	setPercentualeFascia(data.getBigDecimal("CST_NCSTPERC"));
    	setChiaveGateway(data.getString("GTW_KGTWKGTW"));
    	setNomeGateway(data.getString("GTW_DGTWDGTW"));
    	setImgGateway(data.getString("GTW_DGTWPIMG"));
    	setTipoGateway(data.getString("GTW_TGTWTIPO"));
    	setZoneGateway(data.getString("GTW_CGTWOPTI"));
    	
    }
	
	public void setTipoCosto(String tipoCosto) {
		this.tipoCosto = tipoCosto;
	}
	public String getTipoCosto() {
		return tipoCosto;
	}
	public void setImportoMinimoFasciaDa(BigDecimal importoMinimoFasciaDa) {
		this.importoMinimoFasciaDa = importoMinimoFasciaDa;
	}
	public BigDecimal getImportoMinimoFasciaDa() {
		return importoMinimoFasciaDa;
	}
	public void setImportoFissoFascia(BigDecimal importoFissoFascia) {
		this.importoFissoFascia = importoFissoFascia;
	}
	public BigDecimal getImportoFissoFascia() {
		return importoFissoFascia;
	}
	public void setPercentualeFascia(BigDecimal percentualeFascia) {
		this.percentualeFascia = percentualeFascia;
	}
	public BigDecimal getPercentualeFascia() {
		return percentualeFascia;
	}
	public void setImportoMassimoFasciaA(BigDecimal importoMassimoFasciaA) {
		this.importoMassimoFasciaA = importoMassimoFasciaA;
	}
	public BigDecimal getImportoMassimoFasciaA() {
		return importoMassimoFasciaA;
	}

	public void setChiaveGateway(String chiaveGateway) {
		this.chiaveGateway = chiaveGateway;
	}

	public String getChiaveGateway() {
		return chiaveGateway;
	}

	public void setNomeGateway(String nomeGateway) {
		this.nomeGateway = nomeGateway;
	}

	public String getNomeGateway() {
		return nomeGateway;
	}

	public void setImgGateway(String imgGateway) {
		this.imgGateway = imgGateway;
	}

	public String getImgGateway() {
		return imgGateway;
	}

	public void setTipoGateway(String tipoGateway) {
		this.tipoGateway = tipoGateway;
	}

	public String getTipoGateway() {
		return tipoGateway;
	}

	public void setZoneGateway(String zoneGateway) {
		this.zoneGateway = zoneGateway;
	}

	public String getZoneGateway() {
		return zoneGateway;
	}
	
}
