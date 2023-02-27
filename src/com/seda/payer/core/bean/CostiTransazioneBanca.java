package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.seda.payer.commons.bean.Lifecycle;

public class CostiTransazioneBanca extends Lifecycle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String chiaveFasciaCosto;//"CST_KCSTKCST" CHAR(10) NOT NULL,
	private java.lang.String chiaveGateway;//"CST_KGTWKGTW" CHAR(10) NOT NULL,
	private java.lang.String tipoCosto;//"CST_TCSTCOST" CHAR(1) NOT NULL,
	private java.math.BigDecimal importoMinFasciaDa;//"CST_ICSTFSCD" DECIMAL(15 , 2) NOT NULL DEFAULT 0,
	private java.math.BigDecimal importoMaxFasciaDa;//"CST_ICSTFSCA" DECIMAL(15 , 2) NOT NULL DEFAULT 0,
	private java.math.BigDecimal importoFissoFascia;//"CST_ICSTFISS" DECIMAL(10 , 2) NOT NULL DEFAULT 0,
	private java.math.BigDecimal percentualeFascia;//"CST_NCSTPERC" DECIMAL(5 , 2) NOT NULL DEFAULT 0,
	private java.lang.String codiceOperatore;//"CST_CCSTCOPE" VARCHAR(50) NOT NULL
	
	
	public CostiTransazioneBanca() {
	}

	public CostiTransazioneBanca(BigDecimal costoPostaOrdinaria,
			String chiavefasciaCosto, String chiaveGateway, String tipoCosto,
			BigDecimal importoMinFasciaDa, BigDecimal importoMaxFasciaDa,
			BigDecimal importoFissoFascia, BigDecimal percentualeFascia,
			String codiceOperatore) {
		this.chiaveFasciaCosto = chiavefasciaCosto;
		this.chiaveGateway = chiaveGateway;
		this.tipoCosto = tipoCosto;
		this.importoMinFasciaDa = importoMinFasciaDa;
		this.importoMaxFasciaDa = importoMaxFasciaDa;
		this.importoFissoFascia = importoFissoFascia;
		this.percentualeFascia = percentualeFascia;
		this.codiceOperatore = codiceOperatore;
	}
	
	public CostiTransazioneBanca(ResultSet data)throws SQLException {
		if (data == null)
			return;
		chiaveFasciaCosto = data.getString("CST_KCSTKCST");
		chiaveGateway = data.getString("CST_KGTWKGTW");
		tipoCosto = data.getString("CST_TCSTCOST");
		importoMinFasciaDa = data.getBigDecimal("CST_ICSTFSCD");
		importoMaxFasciaDa = data.getBigDecimal("CST_ICSTFSCA");
		importoFissoFascia = data.getBigDecimal("CST_ICSTFISS");
		percentualeFascia = data.getBigDecimal("CST_NCSTPERC");
		codiceOperatore = data.getString("CST_CCSTCOPE");
	}
	
	

	public java.lang.String getChiaveFasciaCosto() {
		return chiaveFasciaCosto;
	}

	public void setChiaveFasciaCosto(java.lang.String chiaveFasciaCosto) {
		this.chiaveFasciaCosto = chiaveFasciaCosto;
	}

	public java.lang.String getChiaveGateway() {
		return chiaveGateway;
	}

	public void setChiaveGateway(java.lang.String chiaveGateway) {
		this.chiaveGateway = chiaveGateway;
	}

	public java.lang.String getTipoCosto() {
		return tipoCosto;
	}

	public void setTipoCosto(java.lang.String tipoCosto) {
		this.tipoCosto = tipoCosto;
	}

	public java.math.BigDecimal getImportoMinFasciaDa() {
		return importoMinFasciaDa;
	}

	public void setImportoMinFasciaDa(java.math.BigDecimal importoMinFasciaDa) {
		this.importoMinFasciaDa = importoMinFasciaDa;
	}

	public java.math.BigDecimal getImportoMaxFasciaDa() {
		return importoMaxFasciaDa;
	}

	public void setImportoMaxFasciaDa(java.math.BigDecimal importoMaxFasciaDa) {
		this.importoMaxFasciaDa = importoMaxFasciaDa;
	}

	public java.math.BigDecimal getImportoFissoFascia() {
		return importoFissoFascia;
	}

	public void setImportoFissoFascia(java.math.BigDecimal importoFissoFascia) {
		this.importoFissoFascia = importoFissoFascia;
	}

	public java.math.BigDecimal getPercentualeFascia() {
		return percentualeFascia;
	}

	public void setPercentualeFascia(java.math.BigDecimal percentualeFascia) {
		this.percentualeFascia = percentualeFascia;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

	@Override
	public void onDelete(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoad(CallableStatement arg) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getChiaveGateway());
		arg.setString(2, this.getTipoCosto());
		arg.setBigDecimal(3, this.getImportoMinFasciaDa());
		arg.setBigDecimal(4, this.getImportoMaxFasciaDa());
		arg.setBigDecimal(5, this.getImportoFissoFascia());
		arg.setBigDecimal(6, this.getPercentualeFascia());
		arg.setString(7, this.getCodiceOperatore());
		
	}

	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {
		arg.setString(1, this.getChiaveFasciaCosto());
		arg.setString(2, this.getChiaveGateway());
		arg.setString(3, this.getTipoCosto());
		arg.setBigDecimal(4, this.getImportoMinFasciaDa());
		arg.setBigDecimal(5, this.getImportoMaxFasciaDa());
		arg.setBigDecimal(6, this.getImportoFissoFascia());
		arg.setBigDecimal(7, this.getPercentualeFascia());
		arg.setString(8, this.getCodiceOperatore());
	}

}
