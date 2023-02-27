package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Calendar;

import com.seda.payer.commons.transform.TransformersIf;

public class RendPos implements Serializable, TransformersIf{

	private static final long serialVersionUID = 1L;
	
	// Campi da PYTRATB
	private java.lang.String cutecute;			//CUTECUTE
	private java.lang.String codiceSocietaMittente;//CUTECSIA
	private Calendar dataTransazione;			//GTRADPAG
	private java.lang.String codicePayer;		//KTRAKTRA
	private java.lang.String idOperazione;		//CTRAIDBA
	private java.math.BigDecimal importo; 		//ITRAITOT
	private java.math.BigDecimal commissioni;	//ITRACOTR
	private int codiceCanalePagamento;//KCANKCAN
	private int codiceStrumento; 	//CPOSCALL
	
	public RendPos() {}
	
	
	
	public java.lang.String getCutecute() {
		return cutecute;
	}



	public void setCutecute(java.lang.String cutecute) {
		this.cutecute = cutecute;
	}



	public java.lang.String getCodiceSocietaMittente() {
		return codiceSocietaMittente;
	}



	public void setCodiceSocietaMittente(java.lang.String codiceSocietaMittente) {
		this.codiceSocietaMittente = codiceSocietaMittente;
	}



	public Calendar getDataTransazione() {
		return dataTransazione;
	}



	public void setDataTransazione(Calendar dataTransazione) {
		this.dataTransazione = dataTransazione;
	}



	public java.lang.String getCodicePayer() {
		return codicePayer;
	}



	public void setCodicePayer(java.lang.String codicePayer) {
		this.codicePayer = codicePayer;
	}



	public java.lang.String getIdOperazione() {
		return idOperazione;
	}



	public void setIdOperazione(java.lang.String idOperazione) {
		this.idOperazione = idOperazione;
	}



	public java.math.BigDecimal getImporto() {
		return importo;
	}



	public void setImporto(java.math.BigDecimal importo) {
		this.importo = importo;
	}



	public java.math.BigDecimal getCommissioni() {
		return commissioni;
	}



	public void setCommissioni(java.math.BigDecimal commissioni) {
		this.commissioni = commissioni;
	}



	public int getCodiceCanalePagamento() {
		return codiceCanalePagamento;
	}



	public void setCodiceCanalePagamento(int codiceCanalePagamento) {
		this.codiceCanalePagamento = codiceCanalePagamento;
	}



	public int getCodiceStrumento() {
		return codiceStrumento;
	}



	public void setCodiceStrumento(int codiceStrumento) {
		this.codiceStrumento = codiceStrumento;
	}


	public static RendPos getBean(ResultSet data) throws Exception {
		RendPos bean = new RendPos();
		int codServ = 0;
		String cposDall = data.getString("POS_CPOSDALL");
//		int indexTerminalId = cposDall.indexOf("TerminalId=");
//		int indexAcquirerId = cposDall.indexOf("AcquirerId=");
		int indexCodStrum = cposDall.indexOf("CardType=");

		bean.cutecute = data.getString("UTE_CUTECUTE");
		bean.codiceSocietaMittente = data.getString("UTE_CUTECSIA");
		Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(data.getTimestamp("TRA_GTRADPAG").getTime());
    	bean.dataTransazione = cal;
		bean.codicePayer = data.getString("TRA_KTRAKTRA");
//		String s = cposDall.substring(indexTerminalId+11, indexTerminalId+19);
//		String s1 = cposDall.substring(indexAcquirerId+11, indexAcquirerId+22);
//		bean.idOperazione = s + s1 + data.getString("TRA_CTRAIDBA");
		bean.idOperazione = data.getString("TRA_CTRAIDBA");
		bean.importo = data.getBigDecimal("TRA_ITRAITOT");
		bean.commissioni = data.getBigDecimal("TRA_ITRACOTR");
		
		int codCanPag = 0;
		String canPag = data.getString("CAN_KCANKCAN");
		if(canPag.equals("POS"))
		{
			codCanPag = 6; 
		}
		bean.codiceCanalePagamento = codCanPag;
		
		String cardType = cposDall.substring(indexCodStrum+9, indexCodStrum+10);
		if(cardType.equals("1"))
		{
			codServ = 7; 	//carta di debito 
		}else if(cardType.equals("2"))
		{
			codServ = 1; 	//carta di credito 
		}
		bean.codiceStrumento = codServ;
		
		return bean;
	}
	
	
	public Serializable beanToBean(Object bean) throws Exception {
		return null;
	}
	
}
