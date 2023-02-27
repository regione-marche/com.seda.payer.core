package com.seda.payer.core.bean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;


import com.seda.payer.commons.bean.Lifecycle;
import com.seda.payer.commons.transform.TransformersIf;

public class TransazioneIV_Dett extends Lifecycle implements Serializable, TransformersIf {

	private static final long serialVersionUID = 1L;
	
	                           
	private java.lang.String chiaveTransazioneGenerale;                            
	private java.lang.String codiceSocieta;                                        
	private java.lang.String codiceUtente;                                         
	private java.lang.String chiaveEnteCon;                                                                
	private java.lang.String codiceImpostaServizio;                                        
	private java.lang.String codiceBollettinoPremarcatoMav;                                                             
	private java.lang.String numeroDocumento;                                                                      
	private java.lang.String codiceFiscale;                                                       
	private java.math.BigDecimal importoTotaleBollettino; 
		
	
    public TransazioneIV_Dett(String chiaveTransazioneGenerale,
			String codiceSocieta, String codiceUtente, String chiaveEnteCon,
			String codiceImpostaServizio, String codiceBollettinoPremarcatoMav,
			String numeroDocumento, String codiceFiscale,
			BigDecimal importoTotaleBollettino) {
		super();
		this.chiaveTransazioneGenerale = chiaveTransazioneGenerale;
		this.codiceSocieta = codiceSocieta;
		this.codiceUtente = codiceUtente;
		this.chiaveEnteCon = chiaveEnteCon;
		this.codiceImpostaServizio = codiceImpostaServizio;
		this.codiceBollettinoPremarcatoMav = codiceBollettinoPremarcatoMav;
		this.numeroDocumento = numeroDocumento;
		this.codiceFiscale = codiceFiscale;
		this.importoTotaleBollettino = importoTotaleBollettino;
	}


	public TransazioneIV_Dett() {		
	}

    


	


	public java.lang.String getChiaveTransazioneGenerale() {
		return chiaveTransazioneGenerale;
	}


	public void setChiaveTransazioneGenerale(
			java.lang.String chiaveTransazioneGenerale) {
		this.chiaveTransazioneGenerale = chiaveTransazioneGenerale;
	}


	public java.lang.String getCodiceSocieta() {
		return codiceSocieta;
	}


	public void setCodiceSocieta(java.lang.String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}


	public java.lang.String getCodiceUtente() {
		return codiceUtente;
	}


	public void setCodiceUtente(java.lang.String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}


	public java.lang.String getChiaveEnteCon() {
		return chiaveEnteCon;
	}


	public void setChiaveEnteCon(java.lang.String chiaveEnteCon) {
		this.chiaveEnteCon = chiaveEnteCon;
	}


	public java.lang.String getCodiceImpostaServizio() {
		return codiceImpostaServizio;
	}


	public void setCodiceImpostaServizio(java.lang.String codiceImpostaServizio) {
		this.codiceImpostaServizio = codiceImpostaServizio;
	}


	

	public java.lang.String getCodiceBollettinoPremarcatoMav() {
		return codiceBollettinoPremarcatoMav;
	}


	public void setCodiceBollettinoPremarcatoMav(
			java.lang.String codiceBollettinoPremarcatoMav) {
		this.codiceBollettinoPremarcatoMav = codiceBollettinoPremarcatoMav;
	}



	public java.lang.String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(java.lang.String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	

	public java.lang.String getCodiceFiscale() {
		return codiceFiscale;
	}


	public void setCodiceFiscale(java.lang.String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}


	


	public java.math.BigDecimal getImportoTotaleBollettino() {
		return importoTotaleBollettino;
	}


	public void setImportoTotaleBollettino(
			java.math.BigDecimal importoTotaleBollettino) {
		this.importoTotaleBollettino = importoTotaleBollettino;
	}


	


	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	
	
	


	/* (non-Javadoc)
	 * @see com.seda.payer.commons.transform.TransformersIf#beanToBean(java.lang.Object)
	 */
	public Serializable beanToBean(Object arg0) throws Exception{		 
		return this;
	} 
	public static  TransazioneIV_Dett getBean(ResultSet data)throws SQLException
	{
		TransazioneIV_Dett bean = new TransazioneIV_Dett();
		
		
		     

		bean.chiaveTransazioneGenerale = data.getString("DMD_KTRAKTRA");
		bean.codiceSocieta = data.getString("DMD_CSOCCSOC");
		bean.codiceUtente = data.getString("DMD_CUTECUTE");
		bean.chiaveEnteCon = data.getString("DMD_KANEKENT_CON");
		bean.codiceImpostaServizio = data.getString("DMD_CISECISE");
		bean.codiceBollettinoPremarcatoMav = data.getString("DMD_CTDTCBOL");
		bean.numeroDocumento = data.getString("DMD_CTDTNDOC");
		bean.codiceFiscale = data.getString("DMD_CTDTCFIS");
		bean.importoTotaleBollettino = data.getBigDecimal("DMD_ITDTTOTA");
		
		return bean;
	}


	@Override
	public void onDelete(CallableStatement arg) throws SQLException {

	}


	@Override
	public void onLoad(CallableStatement arg) throws SQLException {

	}


	@Override
	public void onSave(CallableStatement arg) throws SQLException {
		
		
		
		
		arg.setString(1, this.codiceSocieta);
		arg.setString(2, this.codiceUtente);
		arg.setString(3, this.chiaveEnteCon);
		arg.setString(4, this.codiceBollettinoPremarcatoMav);
		arg.setString(5, this.numeroDocumento);
		arg.setString(6, this.codiceImpostaServizio);
		arg.setString(7, this.codiceFiscale);
		arg.setBigDecimal(8, this.importoTotaleBollettino);
		arg.setString(9, this.chiaveTransazioneGenerale);
		
		
	}


	@Override
	public void onUpdate(CallableStatement arg) throws SQLException {

	}


	
}
