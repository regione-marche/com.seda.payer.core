package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
//import java.util.Date;

//import com.seda.data.spi.PageInfo;
import com.seda.payer.commons.bean.Lifecycle;



public class PartitaRuolo extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String listXml;		 

    
	protected String progrFlusso;				//RPA_KRPAKFLU bigint
	protected long progrFlussoL;				
	protected String codiceSocieta;			//RPA_CSOCCSOC
	protected String codiceUtente;			//RPA_CUTECUTE
	protected String codiceEnte;				    
	protected String chiaveEnte;				//RPA_KANEKENT  agg    
	protected String agRiscossione;			//RPA_CRPACAGE	agg
    protected String annoRuolo;				//RPA_NRPAANNO
    protected BigDecimal annoRuoloBD;					
    protected String numeroRuolo;				//RPA_NRPANUME
    protected BigDecimal numeroRuoloBD;
    protected String codicePartita;			//RPA_NRPANPAR int
    protected long codicePartitaL;												
    protected String codIdPartita;			//RPA_CRPACIDE
    protected String codiceFiscale;			//RPA_CRPACFIS
	protected String codiceTomb;				//RPA_CRPATOMB
	
	protected String concessione;
    protected String codiceUfficio;
    protected String tipoUfficio;
    protected String dataConsegna;

	private Date dataMinuta;
	private int numMinuta;
    
	public PartitaRuolo() { 
        tipoUfficio="";
        codiceUfficio="";
        numeroRuolo = "";
        annoRuolo = "";
        
        
        dataConsegna = "";
        dataConsegna = "";
    	progrFlusso = "";
    	concessione = "";
    	codiceTomb = "";
    	codiceSocieta ="";
    	codiceUtente = "";
    	codiceEnte = "";
        codiceFiscale = "";
        codIdPartita = "";
        codicePartita = "";

        
        
		articoli = new ArrayList<ArticoloPartitaRuolo>();
		pagamenti = new ArrayList<PagamentoPartitaRuolo>();
    }

	

	protected AnagraficaPartitaRuolo anagrafica;
	protected ArrayList<ArticoloPartitaRuolo> articoli;
	protected ArrayList<PagamentoPartitaRuolo> pagamenti;

	
	
	public void add(ArticoloPartitaRuolo articolo)
	{
		articoli.add(articolo);
	}

	public void add(PagamentoPartitaRuolo pagamento)
	{
		pagamenti.add(pagamento);
	}

	public void setAnagrafica(AnagraficaPartitaRuolo anagrafica)
	{
		this.anagrafica = anagrafica;
	}
	
	
	public void onSave(CallableStatement arg) throws SQLException {

		arg.setLong(1, progrFlussoL);		//RPA_KRPAKFLU		
		arg.setString(2, codiceSocieta);			//RPA_CSOCCSOC
		arg.setString(3, codiceUtente);			//RPA_CUTECUTE
		arg.setString(4, chiaveEnte);				//RPA_KANEKENT				    
		arg.setString(5, agRiscossione);			//RPA_CRPACAGE	agg
	    arg.setBigDecimal(6, annoRuoloBD);			//RPA_NRPAANNO		
	    arg.setBigDecimal(7, numeroRuoloBD);	//RPA_NRPANUME
	    arg.setLong(8, codicePartitaL);
//	    private long codicePartitaL);				
	    arg.setString(9, codIdPartita);			//RPA_CRPACIDE
	    arg.setString(10, codiceFiscale);			//RPA_CRPACFIS
		arg.setString(11, codiceTomb);				//RPA_CRPATOMB
	
	}
	
	public void onUpdate(CallableStatement arg) throws SQLException {
	}
	
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	public void onDelete(CallableStatement arg) throws SQLException {

	}	

	public boolean isQuadratoPagamenti(ArrayList<String> messaggiLog)
	{
	
		boolean risultato = true;
	    
//test 1
		BigDecimal totImportoTr = getTotSgravio().add(getTotRiscosso()).add(getTotRimb());
	    BigDecimal totImportoTrPag = getTotMovTributoPag();
		if (totImportoTr.compareTo(totImportoTrPag)!=0)
		{
	    	messaggiLog.add("Il totale tributi non e' stato quadrato: somma tributi '" + totImportoTrPag + "' - somma articoli '"+ totImportoTr + "'");
	    	risultato = risultato & false;
		}
// test 2
			BigDecimal totIMoraTr = getTotMora();
		    BigDecimal totIMoraTrPag = getTotMovMoraPag();
			if (totIMoraTr.compareTo(totIMoraTrPag)!=0)
			{
		    	messaggiLog.add("Il totale mora non è stato quadrato: somma tributi '" + totIMoraTrPag + "' - campo articoli '"+ totIMoraTr + "'");
		    	risultato = risultato & false;
			}

		
		return risultato;
	}
	
//importo trib pagamenti	
	public BigDecimal getTotMovTributoPag()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(PagamentoPartitaRuolo pagamento: pagamenti)
		{
			if (pagamento.getSegnoRiscossione().equals("-"))
				risultato = risultato.subtract(pagamento.getImportoMovTributo());
			else
				risultato = risultato.add(pagamento.getImportoMovTributo());
		}
		return risultato;
		
	}

	public BigDecimal getTotMovMoraPag()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(PagamentoPartitaRuolo pagamento: pagamenti)
		{
			if (pagamento.getSegnoRiscossione().equals("-"))
				risultato = risultato.subtract(pagamento.getImportoMovInteressiMora());
			else
				risultato = risultato.add(pagamento.getImportoMovInteressiMora());
				
		}
		return risultato;
		
	}
	
// articoli	
	public BigDecimal getTotCarico()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(ArticoloPartitaRuolo articolo: articoli)
		{
			risultato = risultato.add(articolo.getImpCarico());
		}
		return risultato;
	}

	public BigDecimal getTotDimCarico()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(ArticoloPartitaRuolo articolo: articoli)
		{
			risultato = risultato.add(articolo.getImpDiscaricato());
		}
		return risultato;
	}

	public BigDecimal getTotSgravio()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(ArticoloPartitaRuolo articolo: articoli)
		{
			risultato = risultato.add(articolo.getImpSgravato());
		}
		return risultato;
	}

	public BigDecimal getTotRiscosso()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(ArticoloPartitaRuolo articolo: articoli)
		{
			risultato = risultato.add(articolo.getImpRiscosso());
		}
		return risultato;
	}

	public BigDecimal getTotRimb()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(ArticoloPartitaRuolo articolo: articoli)
		{
			risultato = risultato.add(articolo.getImpRimborso());
		}
		return risultato;
	}

	public BigDecimal getTotMora()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(ArticoloPartitaRuolo articolo: articoli)
		{
			risultato = risultato.add(articolo.getImpMora());
		}
		return risultato;
	}

	public BigDecimal getTotRendicontato()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(ArticoloPartitaRuolo articolo: articoli)
		{
			risultato = risultato.add(articolo.getImpRendicontato());
		}
		return risultato;
	}

	public BigDecimal getTotResiduo()
	{
		BigDecimal risultato = new BigDecimal(0);
		for(ArticoloPartitaRuolo articolo: articoli)
		{
			risultato = risultato.add(articolo.getImpResiduo());
		}
		return risultato;
	}

    
	
	
//GET e SET	
	
	public String getCodicePartita() {
		return codicePartita;
	}

	public void setCodicePartita(String codicePartita) {
		this.codicePartita = codicePartita;
	}

	public String getListXml() {
		return listXml;
	}

	public void setListXml(String listXml) {
		this.listXml = listXml;
	}

	public String getProgrFlusso() {
		return progrFlusso;
	}

	public void setProgrFlusso(String progrFlusso) {
		this.progrFlusso = progrFlusso;
	}

	public String getConcessione() {
		return concessione;
	}

	public void setConcessione(String concessione) {
		this.concessione = concessione;
	}

	public String getCodiceTomb() {
		return codiceTomb;
	}

	public void setCodiceTomb(String codiceTomb) {
		this.codiceTomb = codiceTomb;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
		for(ArticoloPartitaRuolo articolo: articoli)
			articolo.setCodiceSocieta(codiceSocieta);
		for(PagamentoPartitaRuolo pagamento: pagamenti)
			pagamento.setCodiceSocieta(codiceSocieta);
		if (anagrafica!=null)
			anagrafica.setCodiceSocieta(codiceSocieta);

	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
		for(ArticoloPartitaRuolo articolo: articoli)
			articolo.setCodiceUtente(codiceUtente);
		for(PagamentoPartitaRuolo pagamento: pagamenti)
			pagamento.setCodiceUtente(codiceUtente);
		if (anagrafica!=null)
			anagrafica.setCodiceUtente(codiceUtente);
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
		for(ArticoloPartitaRuolo articolo: articoli)
			articolo.setCodiceEnte(codiceEnte);
		for(PagamentoPartitaRuolo pagamento: pagamenti)
			pagamento.setCodiceEnte(codiceEnte);
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getNumeroRuolo() {
		return numeroRuolo;
	}

	public void setNumeroRuolo(String numeroRuolo) {
		this.numeroRuolo = numeroRuolo;
	}

	public String getAnnoRuolo() {
		return annoRuolo;
	}

	public void setAnnoRuolo(String annoRuolo) {
		this.annoRuolo = annoRuolo;
	}

	public String getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCodIdPartita() {
		return codIdPartita;
	}

	public void setCodIdPartita(String codIdPartita) {
		this.codIdPartita = codIdPartita;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getProgrFlussoL() {
		return progrFlussoL;
	}

	public void setProgrFlussoL(long progrFlussoL) {
		this.progrFlussoL = progrFlussoL;
		for(ArticoloPartitaRuolo articolo: articoli)
			articolo.setProgrFlussoL(progrFlussoL);
		for(PagamentoPartitaRuolo pagamento: pagamenti)
			pagamento.setProgrFlussoL(progrFlussoL);
		if (anagrafica!=null)
			anagrafica.setProgrFlussoL(progrFlussoL);
	}

	public String getAgRiscossione() {
		return agRiscossione;
	}

	public void setAgRiscossione(String agRiscossione) {
		this.agRiscossione = agRiscossione;
	}

	
	public BigDecimal getAnnoRuoloBD() {
		return annoRuoloBD;
	}

	public void setAnnoRuoloBD(BigDecimal annoRuoloBD) {
		this.annoRuoloBD = annoRuoloBD;
	}

	public BigDecimal getNumeroRuoloBD() {
		return numeroRuoloBD;
	}

	public void setNumeroRuoloBD(BigDecimal numeroRuoloBD) {
		this.numeroRuoloBD = numeroRuoloBD;
	}

	public long getCodicePartitaL() {
		return codicePartitaL;
	}

	public void setCodicePartitaL(long codicePartitaL) {
		this.codicePartitaL = codicePartitaL;
		for (ArticoloPartitaRuolo articolo: articoli)
		{
			articolo.setNumeroPartita(codicePartitaL);
		}
		for (PagamentoPartitaRuolo pagamento: pagamenti)
		{
			pagamento.setNumeroPartita(codicePartitaL);
		}
	}


	public ArrayList<ArticoloPartitaRuolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(ArrayList<ArticoloPartitaRuolo> articoli) {
		this.articoli = articoli;
	}

	public ArrayList<PagamentoPartitaRuolo> getPagamenti() {
		return pagamenti;
	}

	public void setPagamenti(ArrayList<PagamentoPartitaRuolo> pagamenti) {
		this.pagamenti = pagamenti;
	}

	public AnagraficaPartitaRuolo getAnagrafica() {
		return anagrafica;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
		for(ArticoloPartitaRuolo articolo: articoli)
			articolo.setChiaveEnte(chiaveEnte);
		for(PagamentoPartitaRuolo pagamento: pagamenti)
			pagamento.setChiaveEnte(chiaveEnte);
		if (anagrafica!=null)
			anagrafica.setChiaveEnte(chiaveEnte);

	}

	public Date getDataMinuta() {
		return dataMinuta;
	}

	public void setDataMinuta(Date dataMinuta) {
		this.dataMinuta = dataMinuta;
	}

	public int getNumMinuta() {
		return numMinuta;
	}

	public void setNumMinuta(int numMinuta) {
		this.numMinuta = numMinuta;
	}



}