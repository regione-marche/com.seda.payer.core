package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class PagDaRend_Contabilita implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private String chiaveTransazione;                            
	private String codiceCarta;                                        
	private Calendar dataPagamento; 
	private BigDecimal importoTotale;
	private BigDecimal importoCommissioniContribuente;
	private BigDecimal importoCommissioniGateway;
	private BigDecimal importoSpeseDiNotifica;
	private String tipoBollettino;
	
	public String getChiaveTransazione() {
		return chiaveTransazione;
	}

	public void setChiaveTransazione(String chiaveTransazione) {
		this.chiaveTransazione = chiaveTransazione;
	}

	public String getCodiceCarta() {
		return codiceCarta;
	}

	public void setCodiceCarta(String codiceCarta) {
		this.codiceCarta = codiceCarta;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

	public BigDecimal getImportoCommissioniContribuente() {
		return importoCommissioniContribuente;
	}

	public void setImportoCommissioniContribuente(
			BigDecimal importoCommissioniContribuente) {
		this.importoCommissioniContribuente = importoCommissioniContribuente;
	}

	public BigDecimal getImportoCommissioniGateway() {
		return importoCommissioniGateway;
	}

	public void setImportoCommissioniGateway(BigDecimal importoCommissioniGateway) {
		this.importoCommissioniGateway = importoCommissioniGateway;
	}

	public BigDecimal getImportoSpeseDiNotifica() {
		return importoSpeseDiNotifica;
	}

	public void setImportoSpeseDiNotifica(BigDecimal importoSpeseDiNotifica) {
		this.importoSpeseDiNotifica = importoSpeseDiNotifica;
	}
	
	public String getTipoBollettino() {
		return tipoBollettino;
	}

	public void setTipoBollettino(String tipoBollettino) {
		this.tipoBollettino = tipoBollettino;
	}

	public static PagDaRend_Contabilita getBean(ResultSet data)throws SQLException
	{
		PagDaRend_Contabilita bean = new PagDaRend_Contabilita();
		
		bean.setChiaveTransazione(data.getString("TRA_KTRAKTRA"));                            
		bean.setCodiceCarta(data.getString("GTW_CCARCCAR"));       
		Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(data.getTimestamp("TRA_GTRADPAG").getTime());
		bean.setDataPagamento(cal);
		bean.setImportoTotale(data.getBigDecimal("TRA_ITRAITOT"));
		bean.setImportoCommissioniContribuente(data.getBigDecimal("TRA_ITRACOTR"));
		bean.setImportoCommissioniGateway(data.getBigDecimal("TRA_ITRACOGW"));
		bean.setImportoSpeseDiNotifica(data.getBigDecimal("TRA_ITRACONT"));
		bean.setTipoBollettino(data.getString("TIPO_BOLL"));
				
	    return bean;
		
	}

}
