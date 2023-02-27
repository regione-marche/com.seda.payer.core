package com.seda.payer.core.riconciliazionemt.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class GiornaleDiCassa {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String codSocieta;
    private String codUtente;
    private String codEnte;
    private String provenienza;
    private String idFlusso;
    private int esercizio;
    private String sospRegolarizzati;
    private String sospRendicontati;
    private Calendar dataGiornale;
    
    private Calendar dataGiornaleDa;
    private Calendar dataGiornaleA;
    private Calendar dataMovimentoDa;
    private Calendar dataMovimentoA;
    private String numDocumento;
    private String psp;
    
    private String chiaveRen;
    
    
    public GiornaleDiCassa() {
    	
    }
    
    
	public void setCodSocieta(String codSocieta) {
		this.codSocieta = codSocieta;
	}
	public String getCodSocieta() {
		return codSocieta;
	}
	public void setCodUtente(String codUtente) {
		this.codUtente = codUtente;
	}
	public String getCodUtente() {
		return codUtente;
	}
	public void setCodEnte(String codEnte) {
		this.codEnte = codEnte;
	}
	public String getCodEnte() {
		return codEnte;
	}
	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}
	public String getProvenienza() {
		return provenienza;
	}
	public void setIdFlusso(String idFlusso) {
		this.idFlusso = idFlusso;
	}
	public String getIdFlusso() {
		return idFlusso;
	}
	public void setEsercizio(int esercizio) {
		this.esercizio = esercizio;
	}
	public int getEsercizio() {
		return esercizio;
	}
	public void setSospRegolarizzati(String sospRegolarizzati) {
		this.sospRegolarizzati = sospRegolarizzati;
	}
	public String getSospRegolarizzati() {
		return sospRegolarizzati;
	}
	public void setSospRendicontati(String sospRendicontati) {
		this.sospRendicontati = sospRendicontati;
	}
	public String getSospRendicontati() {
		return sospRendicontati;
	}
	public void setDataGiornale(Calendar dataGiornale) {
		this.dataGiornale = dataGiornale;
	}
	public Calendar getDataGiornale() {
		return dataGiornale;
	}

	public void setDataGiornaleDa(Calendar dataGiornaleDa) {
		this.dataGiornaleDa = dataGiornaleDa;
	}
	public Calendar getDataGiornaleDa() {
		return dataGiornaleDa;
	}
	public void setDataGiornaleA(Calendar dataGiornaleA) {
		this.dataGiornaleA = dataGiornaleA;
	}
	public Calendar getDataGiornaleA() {
		return dataGiornaleA;
	}
	public void setDataMovimentoDa(Calendar dataMovimentoDa) {
		this.dataMovimentoDa = dataMovimentoDa;
	}
	public Calendar getDataMovimentoDa() {
		return dataMovimentoDa;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setDataMovimentoA(Calendar dataMovimentoA) {
		this.dataMovimentoA = dataMovimentoA;
	}
	public Calendar getDataMovimentoA() {
		return dataMovimentoA;
	}


	public void setPsp(String psp) {
		this.psp = psp;
	}


	public String getPsp() {
		return psp;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Long getId() {
		return id;
	}


	public void setChiaveRen(String chiaveRen) {
		this.chiaveRen = chiaveRen;
	}


	public String getChiaveRen() {
		return chiaveRen;
	}
	//inizio PG200150
	public GiornaleDiCassa(ResultSet data)  throws SQLException {
    	if (data == null)
    		return;
    	
    	id = data.getLong("GDC_PGDCPKEY");
    	codSocieta = data.getString("GDC_CSOCCSOC");		 
    	codUtente =  data.getString("GDC_CUTECUTE");	 
		codEnte =  data.getString("GDC_KANEKENT");	 
		provenienza = data.getString("GDC_CGDCPROV");
		idFlusso = data.getString("GDC_CGDCIDFL");
		esercizio = data.getInt("GDC_CGDCESER");
		dataGiornale = getCalendarFromTimestamp(data.getTimestamp("GDC_GGDCDTCR"));
		dataGiornaleDa = getCalendarFromTimestamp(data.getTimestamp("GDC_GGDCDTIN"));
		dataGiornaleA = getCalendarFromTimestamp(data.getTimestamp("GDC_GGDCDTFI"));
	}
	
	public static Calendar getCalendarFromTimestamp(java.sql.Timestamp date){
		if (date == null) return null;
		long millis = date.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal;
	}
	//fine PG200150

}
