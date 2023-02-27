package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

//import com.seda.data.spi.PageInfo;
import com.seda.payer.commons.bean.Lifecycle;



public class Ruolo extends Lifecycle implements Serializable {

	private static final long serialVersionUID = 1L;

	//input
    private String listXml;		 

   	
  
	protected String progrFlusso;			//RRU_KRRUKFLU	bigint
	protected long progrFlussoL;			//RRU_KRRUKFLU	bigint
	protected String codiceSocieta;		//RRU_CSOCCSOC
	protected String codiceUtente;		//RRU_CUTECUTE
	protected String codiceEnte;
	protected String chiaveEnte;			//RRU_KANEKENT   --> prende il codice ente
    protected String concessione;			//RRU_CRRUCAGE
    protected String annoRuolo;   	    //RRU_NRRUANNO int
    protected BigDecimal annoRuoloBD;   	    
    protected String numeroRuolo;			//RRU_NRRUNUME int
    protected BigDecimal numeroRuoloBD;			
    protected String tipoUfficio;     	//RRU_CRRUTUFF
    protected String codiceUfficio;		//RRU_CRRUCUFF
    protected String dataConsegna;		//RRU_GRRUGRUO date 
    protected Date dataConsegnaD;		 
    protected String dataMinuta;			//RRU_GRRUGMIN date
    protected Date dataMinutaD;			
    protected String numeroMinuta;		//RRU_CRRUCMIN int
    protected int numeroMinutaI;		
    protected BigDecimal totcarico;		//RRU_IRRUICAR
    protected BigDecimal totdimcarico;	//RRU_IRRUDCAR
    protected BigDecimal totsgravio;  	//RRU_IRRUSCAR
    protected BigDecimal impCompens;  	//RRU_IRRUCOMP agg
    protected BigDecimal totriscosso; 	//RRU_IRRURISC
    protected BigDecimal impRimb;  		//RRU_IRRURIMB agg 
    protected BigDecimal totmora;			//RRU_IRRUMORA
    protected BigDecimal totvarcarico;	//RRU_IRRUVCAR
	protected String codiceTomb;			//RRU_CRRUTOMB

    protected String numeroPartite;	//NUM_PARTITE
    protected int numeroPartiteI;	//NUM_PARTITE
   
	protected BigDecimal impResiduo; //RRU_IRRURESI

    public Ruolo() { 
        tipoUfficio="";
        codiceUfficio="";
        numeroRuolo = "";
        annoRuolo = "";
        totcarico = new BigDecimal(0);
        totdimcarico = new BigDecimal(0);
        totriscosso= new BigDecimal(0);
        totsgravio = new BigDecimal(0);
        totmora= new BigDecimal(0);
        totvarcarico= new BigDecimal(0);
         
        dataConsegna = "";
        dataMinuta = "";
        numeroPartite = "";
        numeroMinuta = "";
        dataConsegna = "";
        dataMinuta = "";
        numeroMinuta = "";
    	progrFlusso = "";
    	concessione = "";
    	codiceTomb = "";
    	codiceSocieta ="";
    	codiceUtente = "";
    	codiceEnte = "";


    	partite = new ArrayList<PartitaRuolo>();


    }

	protected ArrayList<PartitaRuolo> partite;

	public void add(PartitaRuolo partita)
	{
		partite.add(partita);
		if (dataMinutaD==null)
		{
			//set data minuta
			dataMinutaD = partita.getDataMinuta();
			//set numero minuta
			numeroMinutaI = partita.getNumMinuta();
		}
		
	}

	public boolean contiene(PartitaRuolo partita)
	{
		boolean risultato = false;

		// campi anno e numero ruolo 
		if(annoRuoloBD.equals(partita.getAnnoRuoloBD()) && numeroRuoloBD.equals(partita.getNumeroRuoloBD()))
			risultato = true;
		return risultato;
		
	}


	public void onSave(CallableStatement arg) throws SQLException 
	{
 	arg.setLong(1, progrFlussoL);			//RRU_KRRUKFLU	bigint
	arg.setString(2, codiceSocieta);		//RRU_CSOCCSOC
	arg.setString(3, codiceUtente);		//RRU_CUTECUTE
	arg.setString(4, chiaveEnte);			//RRU_KANEKENT   --> prende il codice ente
    arg.setString(5, concessione);			//RRU_CRRUCAGE
    arg.setBigDecimal(6, annoRuoloBD);   	 //  RRU_NRRUANNO 
    arg.setBigDecimal(7, numeroRuoloBD);	//RRU_NRRUNUME		
    arg.setString(8, tipoUfficio);     	//RRU_CRRUTUFF
    arg.setString(9, codiceUfficio);		//RRU_CRRUCUFF
    arg.setDate(10, new java.sql.Date(dataConsegnaD.getTime()));		//RRU_GRRUGRUO 
    arg.setDate(11, new java.sql.Date(dataMinutaD.getTime()));			//RRU_GRRUGMIN
    arg.setInt(12, numeroMinutaI);			//RRU_CRRUCMIN
    arg.setBigDecimal(13, totcarico);		//RRU_IRRUICAR
    arg.setBigDecimal(14, totdimcarico);	//RRU_IRRUDCAR
    arg.setBigDecimal(15, totsgravio);  	//RRU_IRRUSCAR
    arg.setBigDecimal(16, impCompens);  	//RRU_IRRUCOMP agg
    arg.setBigDecimal(17, totriscosso); 	//RRU_IRRURISC
    arg.setBigDecimal(18, impResiduo);      //RRU_IRRURESI agg
    arg.setBigDecimal(19, impRimb);  		//RRU_IRRURIMB agg 
    arg.setBigDecimal(20, totmora);			//RRU_IRRUMORA
    arg.setBigDecimal(21, totvarcarico);	//RRU_IRRUVCAR
	arg.setString(22, codiceTomb);			//RRU_CRRUTOMB
	 
	}
	
	public void onUpdate(CallableStatement arg) throws SQLException {
	}
	
	public void onLoad(CallableStatement arg) throws SQLException {

	}
	
	public void onDelete(CallableStatement arg) throws SQLException {

	}	
	
	
// GET E SET	
	public String getCodiceSocieta() {
		return codiceSocieta;
	}


	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}


	public String getCodiceUtente() {
		return codiceUtente;
	}


	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}


	public String getCodiceEnte() {
		return codiceEnte;
	}


	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
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


	public String getDataConsegna() {
		return dataConsegna;
	}


	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = dataConsegna;
	}


	public String getDataMinuta() {
		return dataMinuta;
	}


	public void setDataMinuta(String dataMinuta) {
		this.dataMinuta = dataMinuta;
	}


	public String getNumeroPartite() {
		return numeroPartite;
	}


	public void setNumeroPartite(String numeroPartite) {
		this.numeroPartite = numeroPartite;
	}


	public String getNumeroMinuta() {
		return numeroMinuta;
	}


	public void setNumeroMinuta(String numeroMinuta) {
		this.numeroMinuta = numeroMinuta;
	}


	public BigDecimal getTotsgravio() {
		return totsgravio;
	}


	public void setTotsgravio(BigDecimal totsgravio) {
		this.totsgravio = totsgravio;
	}


	public BigDecimal getTotmora() {
		return totmora;
	}


	public void setTotmora(BigDecimal totmora) {
		this.totmora = totmora;
	}


	public BigDecimal getTotvarcarico() {
		return totvarcarico;
	}


	public void setTotvarcarico(BigDecimal totvarcarico) {
		this.totvarcarico = totvarcarico;
	}


	public String getTipoUfficio() {
		return tipoUfficio;
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getCodiceUfficio() {
		return codiceUfficio;
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
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


	public String getListXml() {
		return listXml;
	}

	public void setListXml(String listXml) {
		this.listXml = listXml;
	}

	public BigDecimal getTotcarico() {
		return totcarico;
	}

	public void setTotcarico(BigDecimal totcarico) {
		this.totcarico = totcarico;
	}

	public BigDecimal getTotdimcarico() {
		return totdimcarico;
	}

	public void setTotdimcarico(BigDecimal totdimcarico) {
		this.totdimcarico = totdimcarico;
	}

	public BigDecimal getTotriscosso() {
		return totriscosso;
	}

	public void setTotriscosso(BigDecimal totriscosso) {
		this.totriscosso = totriscosso;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public long getProgrFlussoL() {
		return progrFlussoL;
	}

	public void setProgrFlussoL(long progrFlussoL) {
		this.progrFlussoL = progrFlussoL;
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

	public int getNumeroMinutaI() {
		return numeroMinutaI;
	}

	public void setNumeroMinutaI(int numeroMinutaI) {
		this.numeroMinutaI = numeroMinutaI;
	}

	public BigDecimal getImpCompens() {
		return impCompens;
	}

	public void setImpCompens(BigDecimal impCompens) {
		this.impCompens = impCompens;
	}

	public BigDecimal getImpRimb() {
		return impRimb;
	}

	public void setImpRimb(BigDecimal impRimb) {
		this.impRimb = impRimb;
	}

	public ArrayList<PartitaRuolo> getPartite() {
		return partite;
	}

	public void setPartite(ArrayList<PartitaRuolo> partite) {
		this.partite = partite;
	}

	public Date getDataConsegnaD() {
		return dataConsegnaD;
	}

	public void setDataConsegnaD(Date dataConsegnaD) {
		this.dataConsegnaD = dataConsegnaD;
	}

	public Date getDataMinutaD() {
		return dataMinutaD;
	}

	public void setDataMinutaD(Date dataMinutaD) {
		this.dataMinutaD = dataMinutaD;
	}

	public String getChiaveEnte() {
		return chiaveEnte;
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public int getNumeroPartiteI() {
		return numeroPartiteI;
	}

	public void setNumeroPartiteI(int numeroPartiteI) {
		this.numeroPartiteI = numeroPartiteI;
	}

	public void setNumeroPartiteI(Integer numeroPartiteIn) {
		this.numeroPartiteI = numeroPartiteIn.intValue();
	}

	public BigDecimal getImpResiduo() {
		return impResiduo;
	}
	public void setImpResiduo(BigDecimal impResiduo) {
		this.impResiduo = impResiduo;
	}
	
	
}