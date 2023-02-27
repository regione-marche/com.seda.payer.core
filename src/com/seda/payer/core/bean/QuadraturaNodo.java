package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.lang.Long;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.seda.payer.commons.bean.Lifecycle;
import com.seda.payer.core.exception.DaoException;

public class QuadraturaNodo implements Serializable {

	private static final long serialVersionUID = 1L;
    private Long keyQuadratura; 				//QUN_PQUNPKEY		-CHIAVE PER QUADRATURA/RICONCILIAZIONE MOVIMENTI CBI
    private String codSocieta;					//QUN_CSOCCSOC		-CODICE SOCIETA
    private String codUtente;					//QUN_CUTECUTE		-CODICE UTENTE
    private String keyGateway;					//QUN_KGTWKGTW		-CHIAVE GATEWAY DI RIFERIMENTO
    private String nomeFileTxt;					//QUN_DQUNSUPP     	-flusso xml del NODO + timestamp + idjob del bap
    private Timestamp dtInizioRend;				//QUN_GQUNDINI		-DATA INIZIO RENDICONTAZIONE
    private Timestamp dtFineRend;				//QUN_GQUNDEND		-DATA FINE RENDICONTAZIONE'
    private long numTransazioni;				//QUN_NQUNTRAN		-NUMERO TRANSAZIONI'
    private BigDecimal impSquadratura;			//QUN_IQUNSQUA		-IMPORTO SQUADRATURA	
    private String statoSquadratura;			//QUN_CQUNQUAD		-STATO QUADRATURA/RICONCILIAZIONE (''A''=APERTA, ''C''=CHIUSA)
    private String movimentoElab;				//QUN_FQUNELAB		-MOVIMENTO ELABORATO (''Y'',''N'')
    private Timestamp dtChiusuraFlusso;			//QUN_GQUNCHIU		-DATA CHIUSURA FLUSSO
    private Timestamp dtUltAggior;				//QUN_GQUNGAGG		-DATA ULTIMO AGGIORNAMENTO
    private String codOperatore;				//QUN_CQUNCOPE		-OPERATORE ULTIMO AGGIORNAMENTO
    
    private String versOggetto;					//QUN_CQUNVERS		-VERSIONE OGGETTO
    private String idFlusso;					//QUN_CQUNFLUS		-IDENTIFICATIVO FLUSSO
    private Timestamp dtflusso;					//QUN_GQUNFLUS		-DATA+ORA FLUSSO
    private String idUnivocoRegol;				//QUN_GQUNIURE		-ID. UNIVOCO REGILAMENTO
    private Date dtregol;						//QUN_GQUNREGO		-DATA REGOLAMENTO
    private String tipoIdUnivocoMitt;			//QUN_CQUNTIDU_MITT	-TIPO IDENTIFICATIVO UNIVOCO MITTENTE
    private String codIdUnivocoMitt;			//QUN_CQUNCIDU_MITT	-CODICE IDENTIFICATIVO UNIVOCO MITTENTE
    private String denomMitt;					//QUN_DQUNDENO_MITT	-DENOMINAZIONE MITTENTE
    private String tipoIdUnivocoRice;			//QUN_CQUNTIDU_RICE	-TIPO IDENTIFICATIVO UNIVOCO RICEVENTE
    private String codIdUnivocoRice;			//QUN_CQUNCIDU_RICE	-CODICE IDENTIFICATIVO UNIVOCO RICEVENTE
    private String denomRice;					//QUN_DQUNDENO_RICE	-DENOMINAZIONE RICEVENTE
    private long numTotPagamenti;				//QUN_NQUNNTOT		-NUMERO TOTALE PAGAMENTI
    private BigDecimal impTotPagamenti;			//QUN_IQUNITOT		-IMPORTO TOTALE PAGAMENTI
    private String codiceIUV;					//QUN_KIUVKIUV		-I.U.V. IDENTIFICATIVO UNIVOCO PAGAMENTO
    private String codiceRIS;					//QUN_KRISKRIS		-IDENTIFICATIVO UNIVOCO RISCOSSIONE
    private BigDecimal impPagamento;			//QUN_IQUNIPAG		-IMPORTO SINGOLO PAGAMENTO
    private String esitoPagam; 					//QUN_CQUNESIT		-CODICE ESITO SINGOLO PAGAMENTO
    private Date dtEsitoPagam;					//QUN_GQUNESIT		-DATA ESITO SINGOLO PAGAMENTO
    private String esitoInvioQuadratura;        //QUN_FQUNFESI      -ESITO INVIO FLUSSO DI QUADRATURA AL WS
    private String keyEnte; 					//QUN_KANEKENT      -CHIAVE ENTE
    private Long numTransazioniRecuperate;      //QUN_NQUNNREC      -NUMERO TRANSAZIONI RECUPERATE
    //inizio LP PG200200
    private String tipologiaFlusso;				//QUN_CQUNTIPO      -TIPOLOGIA FLUSSO
    //fine LP PG200200
    private String erroreInvioQuadratura; 
	
    private int rowsPerPage;
    private int pageNumber;
    private String order;
    private String siglaProvincia;
    private BigDecimal impPagamento_da;
    private BigDecimal impPagamento_a;
    private String dtflusso_da;    
    private String dtflusso_a;    
    
    public QuadraturaNodo() {

	}
	public Long getKeyQuadratura() {
		return keyQuadratura;
	}
	public void setKeyQuadratura(Long keyQuadratura) {
		this.keyQuadratura = keyQuadratura;
	}
	public String getCodSocieta() {
		return codSocieta;
	}
	public void setCodSocieta(String codSocieta) {
		this.codSocieta = codSocieta;
	}
	public String getCodUtente() {
		return codUtente;
	}
	public void setCodUtente(String codUtente) {
		this.codUtente = codUtente;
	}
	public String getKeyGateway() {
		return keyGateway;
	}
	public void setKeyGateway(String keyGateway) {
		this.keyGateway = keyGateway;
	}
	public String getNomeFileTxt() {
		return nomeFileTxt;
	}
	public void setNomeFileTxt(String nomeFileTxt) {
		this.nomeFileTxt = nomeFileTxt;
	}
	public Timestamp getDtInizioRend() {
		return dtInizioRend;
	}
	public void setDtInizioRend(Timestamp dtInizioRend) {
		this.dtInizioRend = dtInizioRend;
	}
	public Timestamp getDtFineRend() {
		return dtFineRend;
	}
	public void setDtFineRend(Timestamp dtFineRend) {
		this.dtFineRend = dtFineRend;
	}
	public Long getNumTransazioni() {
		return numTransazioni;
	}
	public void setNumTransazioni(Long numTransazioni) {
		this.numTransazioni = numTransazioni;
	}
	public BigDecimal getImpSquadratura() {
		return impSquadratura;
	}
	public void setImpSquadratura(BigDecimal impSquadratura) {
		this.impSquadratura = impSquadratura;
	}
	public String getStatoSquadratura() {
		return statoSquadratura;
	}
	public void setStatoSquadratura(String statoSquadratura) {
		this.statoSquadratura = statoSquadratura;
	}
	public String getMovimentoElab() {
		return movimentoElab;
	}
	public void setMovimentoElab(String movimentoElab) {
		this.movimentoElab = movimentoElab;
	}
	public Timestamp getDtChiusuraFlusso() {
		return dtChiusuraFlusso;
	}
	public void setDtChiusuraFlusso(Timestamp dtChiusuraFlusso) {
		this.dtChiusuraFlusso = dtChiusuraFlusso;
	}
	public Timestamp getDtUltAggior() {
		return dtUltAggior;
	}
	public void setDtUltAggior(Timestamp dtUltAggior) {
		this.dtUltAggior = dtUltAggior;
	}
	public String getCodOperatore() {
		return codOperatore;
	}
	public void setCodOperatore(String codOperatore) {
		this.codOperatore = codOperatore;
	}
	public String getVersOggetto() {
		return versOggetto;
	}
	public void setVersOggetto(String versOggetto) {
		this.versOggetto = versOggetto;
	}
	public String getIdFlusso() {
		return idFlusso;
	}
	public void setIdFlusso(String idFlusso) {
		this.idFlusso = idFlusso;
	}
	public Timestamp getDtflusso() {
		return dtflusso;
	}
	public void setDtflusso(Timestamp dtflusso) {
		this.dtflusso = dtflusso;
	}
	public String getIdUnivocoRegol() {
		return idUnivocoRegol;
	}
	public void setIdUnivocoRegol(String idUnivocoRegol) {
		this.idUnivocoRegol = idUnivocoRegol;
	}
	public Date getDtregol() {
		return dtregol;
	}
	public void setDtregol(Date dtregol) {
		this.dtregol = dtregol;
	}
	public String getTipoIdUnivocoMitt() {
		return tipoIdUnivocoMitt;
	}
	public void setTipoIdUnivocoMitt(String tipoIdUnivocoMitt) {
		this.tipoIdUnivocoMitt = tipoIdUnivocoMitt;
	}
	public String getCodIdUnivocoMitt() {
		return codIdUnivocoMitt;
	}
	public void setCodIdUnivocoMitt(String codIdUnivocoMitt) {
		this.codIdUnivocoMitt = codIdUnivocoMitt;
	}
	public String getDenomMitt() {
		return denomMitt;
	}
	public void setDenomMitt(String denomMitt) {
		this.denomMitt = denomMitt;
	}
	public String getTipoIdUnivocoRice() {
		return tipoIdUnivocoRice;
	}
	public void setTipoIdUnivocoRice(String tipoIdUnivocoRice) {
		this.tipoIdUnivocoRice = tipoIdUnivocoRice;
	}
	public String getCodIdUnivocoRice() {
		return codIdUnivocoRice;
	}
	public void setCodIdUnivocoRice(String codIdUnivocoRice) {
		this.codIdUnivocoRice = codIdUnivocoRice;
	}
	public String getDenomRice() {
		return denomRice;
	}
	public void setDenomRice(String denomRice) {
		this.denomRice = denomRice;
	}
	public Long getNumTotPagamenti() {
		return numTotPagamenti;
	}
	public void setNumTotPagamenti(Long numTotPagamenti) {
		this.numTotPagamenti = numTotPagamenti;
	}
	public BigDecimal getImpTotPagamenti() {
		return impTotPagamenti;
	}
	public void setImpTotPagamenti(BigDecimal impTotPagamenti) {
		this.impTotPagamenti = impTotPagamenti;
	}
	public String getCodiceIUV() {
		return codiceIUV;
	}
	public void setCodiceIUV(String codiceIUV) {
		this.codiceIUV = codiceIUV;
	}
	public String getCodiceRIS() {
		return codiceRIS;
	}
	public void setCodiceRIS(String codiceRIS) {
		this.codiceRIS = codiceRIS;
	}
	public BigDecimal getImpPagamento() {
		return impPagamento;
	}
	public void setImpPagamento(BigDecimal impPagamento) {
		this.impPagamento = impPagamento;
	}
	public String getEsitoPagam() {
		return esitoPagam;
	}
	public void setEsitoPagam(String esitoPagam) {
		this.esitoPagam = esitoPagam;
	}
	public Date getDtEsitoPagam() {
		return dtEsitoPagam;
	}
	public void setDtEsitoPagam(Date dtEsitoPagam) {
		this.dtEsitoPagam = dtEsitoPagam;
	}
	
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSiglaProvincia() {
		return siglaProvincia;
	}
	public void setSiglaProvincia(String siglaProvincia) {
		this.siglaProvincia = siglaProvincia;
	}
	public BigDecimal getImpPagamento_da() {
		return impPagamento_da;
	}
	public void setImpPagamento_da(BigDecimal impPagamentoDa) {
		impPagamento_da = impPagamentoDa;
	}
	public BigDecimal getImpPagamento_a() {
		return impPagamento_a;
	}
	public void setImpPagamento_a(BigDecimal impPagamentoA) {
		impPagamento_a = impPagamentoA;
	}
	public String getDtflusso_da() {
		return dtflusso_da;
	}
	public void setDtflusso_da(String dtflussoDa) {
		dtflusso_da = dtflussoDa;
	}
	public String getDtflusso_a() {
		return dtflusso_a;
	}
	public void setDtflusso_a(String dtflussoA) {
		dtflusso_a = dtflussoA;
	}
	public String getEsitoInvioQuadratura() {
		return esitoInvioQuadratura;
	}
	public void setEsitoInvioQuadratura(String esitoInvioQuadratura) {
		this.esitoInvioQuadratura = esitoInvioQuadratura;
	}
	public String getErroreInvioQuadratura() {
		return erroreInvioQuadratura;
	}
	public void setErroreInvioQuadratura(String erroreInvioQuadratura) {
		this.erroreInvioQuadratura = erroreInvioQuadratura;
	}
	
	
	public String getKeyEnte() {
		return keyEnte;
	}
	public void setKeyEnte(String keyEnte) {
		this.keyEnte = keyEnte;
	}
	public Long getNumTransazioniRecuperate() {
		return numTransazioniRecuperate;
	}
	public void setNumTransazioniRecuperate(Long numTransazioniRecuperate) {
		this.numTransazioniRecuperate = numTransazioniRecuperate;
	}

	//inizio LP PG200200
    public String getTipologiaFlusso() {
    	return tipologiaFlusso;
    }

    public void setTipologiaFlusso(String tipologiaFlusso) {
		this.tipologiaFlusso = tipologiaFlusso;
	}
    //fine LP PG200200

    public QuadraturaNodo(long keyQuadratura, String codSocieta,
			String codUtente, String keyGateway, String nomeFileTxt,
			Timestamp dtInizioRend, Timestamp dtFineRend, long numTransazioni,
			BigDecimal impSquadratura, String statoSquadratura,
			String movimentoElab, Timestamp dtChiusuraFlusso,
			Timestamp dtUltAggior, String codOperatore, String versOggetto,
			String idFlusso, Timestamp dtflusso, String idUnivocoRegol,
			Date dtregol, String tipoIdUnivocoMitt, String codIdUnivocoMitt,
			String denomMitt, String tipoIdUnivocoRice,
			String codIdUnivocoRice, String denomRice, long numTotPagamenti,
			BigDecimal impTotPagamenti, String codiceIUV, String codiceRIS,
			BigDecimal impPagamento, String esitoPagam, Date dtEsitoPagam,
			int rowsPerPage, int pageNumber, String order,
			String siglaProvincia, BigDecimal impPagamentoDa,
			BigDecimal impPagamentoA, String dtflussoDa, String dtflussoA) {
		super();
		this.keyQuadratura = keyQuadratura;
		this.codSocieta = codSocieta;
		this.codUtente = codUtente;
		this.keyGateway = keyGateway;
		this.nomeFileTxt = nomeFileTxt;
		this.dtInizioRend = dtInizioRend;
		this.dtFineRend = dtFineRend;
		this.numTransazioni = numTransazioni;
		this.impSquadratura = impSquadratura;
		this.statoSquadratura = statoSquadratura;
		this.movimentoElab = movimentoElab;
		this.dtChiusuraFlusso = dtChiusuraFlusso;
		this.dtUltAggior = dtUltAggior;
		this.codOperatore = codOperatore;
		this.versOggetto = versOggetto;
		this.idFlusso = idFlusso;
		this.dtflusso = dtflusso;
		this.idUnivocoRegol = idUnivocoRegol;
		this.dtregol = dtregol;
		this.tipoIdUnivocoMitt = tipoIdUnivocoMitt;
		this.codIdUnivocoMitt = codIdUnivocoMitt;
		this.denomMitt = denomMitt;
		this.tipoIdUnivocoRice = tipoIdUnivocoRice;
		this.codIdUnivocoRice = codIdUnivocoRice;
		this.denomRice = denomRice;
		this.numTotPagamenti = numTotPagamenti;
		this.impTotPagamenti = impTotPagamenti;
		this.codiceIUV = codiceIUV;
		this.codiceRIS = codiceRIS;
		this.impPagamento = impPagamento;
		this.esitoPagam = esitoPagam;
		this.dtEsitoPagam = dtEsitoPagam;
		this.rowsPerPage = rowsPerPage;
		this.pageNumber = pageNumber;
		this.order = order;
		this.siglaProvincia = siglaProvincia;
		impPagamento_da = impPagamentoDa;
		impPagamento_a = impPagamentoA;
		dtflusso_da = dtflussoDa;
		dtflusso_a = dtflussoA;
	}
	
	
	
	@Override
	public String toString() {
		return "QuadraturaNodo [codIdUnivocoMitt=" + codIdUnivocoMitt
				+ ", codIdUnivocoRice=" + codIdUnivocoRice + ", codOperatore="
				+ codOperatore + ", codSocieta=" + codSocieta + ", codUtente="
				+ codUtente + ", codiceIUV=" + codiceIUV + ", codiceRIS="
				+ codiceRIS + ", denomMitt=" + denomMitt + ", denomRice="
				+ denomRice + ", dtChiusuraFlusso=" + dtChiusuraFlusso
				+ ", dtEsitoPagam=" + dtEsitoPagam + ", dtFineRend="
				+ dtFineRend + ", dtInizioRend=" + dtInizioRend
				+ ", dtUltAggior=" + dtUltAggior + ", dtflusso=" + dtflusso
				+ ", dtflusso_a=" + dtflusso_a + ", dtflusso_da=" + dtflusso_da
				+ ", dtregol=" + dtregol + ", esitoPagam=" + esitoPagam
				+ ", idFlusso=" + idFlusso + ", idUnivocoRegol="
				+ idUnivocoRegol + ", impPagamento=" + impPagamento
				+ ", impPagamento_a=" + impPagamento_a + ", impPagamento_da="
				+ impPagamento_da + ", impSquadratura=" + impSquadratura
				+ ", impTotPagamenti=" + impTotPagamenti + ", keyGateway="
				+ keyGateway + ", keyQuadratura=" + keyQuadratura
				+ ", movimentoElab=" + movimentoElab + ", nomeFileTxt="
				+ nomeFileTxt + ", numTotPagamenti=" + numTotPagamenti
				+ ", numTransazioni=" + numTransazioni + ", order=" + order
				+ ", pageNumber=" + pageNumber + ", rowsPerPage=" + rowsPerPage
				+ ", siglaProvincia=" + siglaProvincia + ", statoSquadratura="
				+ statoSquadratura + ", tipoIdUnivocoMitt=" + tipoIdUnivocoMitt
				+ ", tipoIdUnivocoRice=" + tipoIdUnivocoRice + ", versOggetto="
				+ versOggetto + "]";
	}

	//
	public QuadraturaNodo(ResultSet data) throws SQLException 
	{
    	if (data == null)
    		return;
    	
    	setKeyQuadratura(data.getLong("QUN_PQUNPKEY"));
    	setCodSocieta(data.getString("QUN_CSOCCSOC"));
    	setCodUtente(data.getString("QUN_CUTECUTE"));
    	setKeyGateway(data.getString("QUN_KGTWKGTW"));
    	
    	setNomeFileTxt(data.getString("QUN_DQUNSUPP")); //PG200150
    	setDtInizioRend(data.getTimestamp("QUN_GQUNDINI"));
    	setDtFineRend(data.getTimestamp("QUN_GQUNDEND"));
        setNumTransazioni(data.getLong("QUN_NQUNTRAN"));
        
        setImpSquadratura(data.getBigDecimal("QUN_IQUNSQUA"));	
    	setStatoSquadratura(data.getString("QUN_CQUNQUAD"));
    	setMovimentoElab(data.getString("QUN_FQUNELAB"));
    	setDtChiusuraFlusso(data.getTimestamp("QUN_GQUNCHIU"));
    	setDtUltAggior(data.getTimestamp("QUN_GQUNGAGG"));
    	setCodOperatore(data.getString("QUN_CQUNCOPE"));
    	
    	setVersOggetto(data.getString("QUN_CQUNVERS"));
    	setIdFlusso(data.getString("QUN_CQUNFLUS"));
    	setDtflusso(data.getTimestamp("QUN_GQUNFLUS"));
    	setIdUnivocoRegol(data.getString("QUN_GQUNIURE"));
        setDtregol(data.getDate("QUN_GQUNREGO"));
    	setTipoIdUnivocoMitt(data.getString("QUN_CQUNTIDU_MITT"));
    	setCodIdUnivocoMitt(data.getString("QUN_CQUNCIDU_MITT"));
    	setDenomMitt(data.getString("QUN_DQUNDENO_MITT"));
    	setTipoIdUnivocoRice(data.getString("QUN_CQUNTIDU_RICE"));
    	setCodIdUnivocoRice(data.getString("QUN_CQUNCIDU_RICE"));
    	setDenomRice(data.getString("QUN_DQUNDENO_RICE"));
        setNumTotPagamenti(data.getLong("QUN_NQUNNTOT"));
        setImpTotPagamenti(data.getBigDecimal("QUN_IQUNITOT"));
        
        setKeyEnte(data.getString("QUN_KANEKENT")); // PG200150
        setNumTransazioniRecuperate(data.getLong("QUN_NQUNNREC")); // PG200150
    	   
    	//PG200150
    	//setCodiceIUV(data.getString("QUN_KIUVKIUV"));
    	//setCodiceRIS(data.getString("QUN_KRISKRIS"));
       // setImpPagamento(data.getBigDecimal("QUN_IQUNIPAG"));
    	//setEsitoPagam(data.getString("QUN_CQUNESIT"));
      //  setDtEsitoPagam(data.getDate("QUN_GQUNESIT"));
        //inizio LP PG200200
        try {
        	setEsitoInvioQuadratura(data.getString("QUN_FQUNFESI"));
        	setErroreInvioQuadratura(data.getString("QUN_CQUNERRO"));
        } catch (Exception x) {
        	//bypass errore colonna non presente
        	setEsitoInvioQuadratura("0");
        	setErroreInvioQuadratura("");
		}
        setTipologiaFlusso(data.getString("QUN_CQUNTIPO"));
        //fine LP PG200200
	}
	
}