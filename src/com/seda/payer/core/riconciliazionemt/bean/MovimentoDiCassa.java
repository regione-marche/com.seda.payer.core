package com.seda.payer.core.riconciliazionemt.bean;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class MovimentoDiCassa {
private static final long serialVersionUID = 1L;
	
	private long idGiornale;
	private long id;
	private String conto;
    private String statoSospeso;
    private String numDocumento;
    private String cliente;
    private BigDecimal importo;
    private BigDecimal importoDa;
    private BigDecimal importoA;
    private String squadratura;
    private BigDecimal importoSquadratura;
    private String rendicontato;
    private String regolarizzato;
    private Calendar dataRegolarizzazione;
    private long progressivoDoc;
    private String numBolletta;
    private Calendar dataMovimento;
    private Calendar dataValuta;
    private String tipoEsecuzione;
    private String codiceRiferimento;
    private String causale;
    private String associazioni;
    
    private String ente;
    private String provenienza;
    private Calendar dataGiornale;
    private String idFlusso;
    private int esercizio;
    private String sospRegolarizzati;
    private String sospRendicontati;
    
    private String nota;
    private Calendar dataRendicontazione;
    
    private String cutecute;
    private String chiaveEnte;
    private String siglaProvincia;
    private String codiceSocieta;
    
    private String operatoreReg; //PG200150
    private String tipoAnomalia; //PG200150
    
    
    
    public MovimentoDiCassa() {
    	
    }


	public void setIdGiornale(long idGiornale) {
		this.idGiornale = idGiornale;
	}


	public long getIdGiornale() {
		return idGiornale;
	}


	public void setConto(String conto) {
		this.conto = conto;
	}


	public String getConto() {
		return conto;
	}


	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}


	public String getNumDocumento() {
		return numDocumento;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public String getCliente() {
		return cliente;
	}

	public void setSquadratura(String squadratura) {
		this.squadratura = squadratura;
	}


	public String getSquadratura() {
		return squadratura;
	}


	public void setRendicontato(String rendicontato) {
		this.rendicontato = rendicontato;
	}


	public String getRendicontato() {
		return rendicontato;
	}


	public void setRegolarizzato(String regolarizzato) {
		this.regolarizzato = regolarizzato;
	}


	public String getRegolarizzato() {
		return regolarizzato;
	}


	public void setDataRegolarizzazione(Calendar dataRegolarizzazione) {
		this.dataRegolarizzazione = dataRegolarizzazione;
	}


	public Calendar getDataRegolarizzazione() {
		return dataRegolarizzazione;
	}


	public void setStatoSospeso(String statoSospeso) {
		this.statoSospeso = statoSospeso;
	}


	public String getStatoSospeso() {
		return statoSospeso;
	}


	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}


	public BigDecimal getImporto() {
		return importo;
	}


	public void setImportoDa(BigDecimal importoDa) {
		this.importoDa = importoDa;
	}


	public BigDecimal getImportoDa() {
		return importoDa;
	}


	public void setImportoA(BigDecimal importoA) {
		this.importoA = importoA;
	}


	public BigDecimal getImportoA() {
		return importoA;
	}


	public void setProgressivoDoc(long progressivoDoc) {
		this.progressivoDoc = progressivoDoc;
	}


	public long getProgressivoDoc() {
		return progressivoDoc;
	}


	public void setNumBolletta(String numBolletta) {
		this.numBolletta = numBolletta;
	}


	public String getNumBolletta() {
		return numBolletta;
	}


	public void setDataMovimento(Calendar dataMovimento) {
		this.dataMovimento = dataMovimento;
	}


	public Calendar getDataMovimento() {
		return dataMovimento;
	}


	public void setDataValuta(Calendar dataValuta) {
		this.dataValuta = dataValuta;
	}


	public Calendar getDataValuta() {
		return dataValuta;
	}


	public void setTipoEsecuzione(String tipoEsecuzione) {
		this.tipoEsecuzione = tipoEsecuzione;
	}


	public String getTipoEsecuzione() {
		return tipoEsecuzione;
	}


	public void setCodiceRiferimento(String codiceRiferimento) {
		this.codiceRiferimento = codiceRiferimento;
	}


	public String getCodiceRiferimento() {
		return codiceRiferimento;
	}


	public void setCausale(String causale) {
		this.causale = causale;
	}


	public String getCausale() {
		return causale;
	}


	public void setImportoSquadratura(BigDecimal importoSquadratura) {
		this.importoSquadratura = importoSquadratura;
	}


	public BigDecimal getImportoSquadratura() {
		return importoSquadratura;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getId() {
		return id;
	}


	public void setAssociazioni(String associazioni) {
		this.associazioni = associazioni;
	}


	public String getAssociazioni() {
		return associazioni;
	}


	public void setEnte(String ente) {
		this.ente = ente;
	}


	public String getEnte() {
		return ente;
	}


	public void setProvenienza(String provenienza) {
		this.provenienza = provenienza;
	}


	public String getProvenienza() {
		return provenienza;
	}


	public void setDataGiornale(Calendar dataGiornale) {
		this.dataGiornale = dataGiornale;
	}


	public Calendar getDataGiornale() {
		return dataGiornale;
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


	public void setNota(String nota) {
		this.nota = nota;
	}


	public String getNota() {
		return nota;
	}


	public void setDataRendicontazione(Calendar dataRendicontazione) {
		this.dataRendicontazione = dataRendicontazione;
	}


	public Calendar getDataRendicontazione() {
		return dataRendicontazione;
	}


	public String getCutecute() {
		return cutecute;
	}


	public String getChiaveEnte() {
		return chiaveEnte;
	}


	public String getSiglaProvincia() {
		return siglaProvincia;
	}


	public void setCutecute(String cutecute) {
		this.cutecute = cutecute;
	}


	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}


	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}

	public String getCodiceSocieta() {
		return codiceSocieta;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}
	//inizio PG200150
	public String getOperatoreReg() {
		return operatoreReg;
	}

	public void setOperatoreReg(String operatoreReg) {
		this.operatoreReg = operatoreReg;
	}

	public String getTipoAnomalia() {
		return tipoAnomalia;
	}

	public void setTipoAnomalia(String tipoAnomalia) {
		this.tipoAnomalia = tipoAnomalia;
	}
	
	public MovimentoDiCassa(ResultSet data)  throws SQLException {
    	if (data == null)
    		return;
    	
		id = data.getLong("MDC_PMDCPKEY");
    	codiceSocieta = data.getString("MDC_CSOCCSOC");
    	cutecute = data.getString("MDC_CUTECUTE");
    	siglaProvincia = data.getString("MDC_KANEKENT");
    	idGiornale = data.getLong("MDC_PGDCPKEY");  	
		conto = data.getString("MDC_CMDCCONT");
		statoSospeso = data.getString("MDC_CMDCSTAT");
		numDocumento = data.getString("MDC_IMDCDOCN");
    	cliente = data.getString("MDC_CMDCCLIE");
		importo = data.getBigDecimal("MDC_DMDCIMPO");
    	rendicontato = data.getString("MDC_CMDCREND");
		regolarizzato = data.getString("MDC_CMDCREGO");
    	progressivoDoc = data.getLong("MDC_IMDCPRDO");
    	numBolletta = data.getString("MDC_CMDCNBOL");
    	dataMovimento = getCalendarFromTimestamp(data.getTimestamp("MDC_GMDCDMOV"));
    	dataValuta = getCalendarFromTimestamp(data.getTimestamp("MDC_GMDCDVAL"));
    	tipoEsecuzione = data.getString("MDC_CMDCESEC");
		codiceRiferimento = data.getString("MDC_CMDCCRIF");
		causale = data.getString("MDC_CMDCCAUS");		
		dataRegolarizzazione = getCalendarFromTimestamp(data.getTimestamp("MDC_GMDCDREG"));
    	squadratura = data.getString("MDC_DMDCSQUA");  	
    	dataRendicontazione = getCalendarFromTimestamp(data.getTimestamp("MDC_GMDCDREN"));
    	nota = data.getString("MDC_CMDCNREN");   	
    	operatoreReg = data.getString("MDC_CMDCCOPE");
    	tipoAnomalia = data.getString("MDC_CMDCTANO");
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
