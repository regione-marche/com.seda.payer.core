package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ArchivioCarichiDettaglioPagamento {
	//PYEHDTB
	/*
	"EHD_PEHDFLUS" BIGINT NOT NULL,
	"EHD_TEHDRECO" CHAR(3) NOT NULL,
	"EHD_CUTECUTE" CHAR(5) NOT NULL,
	"EHD_GEHDCREA" DATE NOT NULL,
	"EHD_TEHDSERV" CHAR(2) NOT NULL,
	"EHD_CANECENT" CHAR(5) NOT NULL,
	"EHD_TANETUFF" CHAR(1) NOT NULL,
	"EHD_CANECUFF" CHAR(6) NOT NULL,
	"EHD_CISECISE" CHAR(4) NOT NULL,
	"EHD_CEHDNDOC" VARCHAR(20) NOT NULL,
	"EHD_CEHDCBOL" CHAR(18) NOT NULL,
	"EHD_PEHDIDDO" VARCHAR(11) NOT NULL,
	"EHD_IEHDIMPO" DECIMAL(15 , 2) NOT NULL,
	"EHD_CEHDIBAN" VARCHAR(27) NOT NULL,
	"EHD_CEHDIBAP" VARCHAR(27) NULL,
	"EHD_CEHDTOMB" CHAR(1) NOT NULL
    //inizio LP PG22XX05
    "EHD_CEHDTCSE" CHAR(3) NOT NULL
    //fine LP PG22XX05
	*/
	private Long progressivoFlusso;
	private String tipoRecord;
	private String codiceUtente;
	private Date dataCreazioneFlusso;
	private String tipoServizio;
	private String codiceEnte;
	private String tipoUfficio;
	private String codiceUfficio;
	private String impostaServizio;
	private String numeroDocumento;
	//private Integer numeroRata;
	private String numeroBollettino;
	private String idDominio;
	private BigDecimal importo;
	private String ibanBancario;
	private String ibanPostale;
    private String tombstoned;
    //inizio LP PG22XX05
    private String codiceTipologiaServizio;
    //fine LP PG22XX05

    public ArchivioCarichiDettaglioPagamento() {
    }

	public ArchivioCarichiDettaglioPagamento(Long progressivoFlusso, String tipoRecord, String codiceUtente,
			Date dataCreazioneFlusso, String tipoServizio, String codiceEnte, String tipoUfficio, String codiceUfficio,
			String impostaServizio, String numeroDocumento, String numeroBollettino, String idDominio,
			BigDecimal importo, String ibanBancario, String ibanPostale, String tombstoned
		    //inizio LP PG22XX05
		    , String codiceTipologiaServizio
		    //fine LP PG22XX05
		) {
		super();
		this.progressivoFlusso = progressivoFlusso;
		this.tipoRecord = tipoRecord;
		this.codiceUtente = codiceUtente;
		this.dataCreazioneFlusso = dataCreazioneFlusso;
		this.tipoServizio = tipoServizio;
		this.codiceEnte = codiceEnte;
		this.tipoUfficio = tipoUfficio;
		this.codiceUfficio = codiceUfficio;
		this.impostaServizio = impostaServizio;
		this.numeroDocumento = numeroDocumento;
		this.numeroBollettino = numeroBollettino;
		this.idDominio = idDominio;
		this.importo = importo;
		this.ibanBancario = ibanBancario;
		this.ibanPostale = ibanPostale;
		this.tombstoned = tombstoned;
	    //inizio LP PG22XX05
	    this.setCodiceTipologiaServizio(codiceTipologiaServizio);
	    //fine LP PG22XX05
	}
	
	public ArchivioCarichiDettaglioPagamento(ResultSet data) throws SQLException
	{
		super();
		if(data == null)
			return;
		this.progressivoFlusso = data.getLong("EHD_PEHDFLUS");
		this.tipoRecord = data.getString("EHD_TEHDRECO");
		this.codiceUtente = data.getString("EHD_CUTECUTE");
		this.dataCreazioneFlusso = java.sql.Date.valueOf(data.getString("EHD_GEHDCREA"));
		this.tipoServizio = data.getString("EHD_TEHDSERV");
		this.codiceEnte = data.getString("EHD_CANECENT");
		this.tipoUfficio = data.getString("EHD_TANETUFF");
		this.codiceUfficio = data.getString("EHD_CANECUFF");
		this.impostaServizio = data.getString("EHD_CISECISE");
		this.numeroDocumento = data.getString("EHD_CEHDNDOC");
		this.numeroBollettino = data.getString("EHD_CEHDCBOL");
		this.idDominio = data.getString("EHD_PEHDIDDO");
		this.importo = data.getBigDecimal("EHD_IEHDIMPO");
		this.ibanBancario = data.getString("EHD_CEHDIBAN");
		this.ibanPostale = data.getString("EHD_CEHDIBAP");
		this.tombstoned = data.getString("EHD_CEHDTOMB");
	    //inizio LP PG22XX05
	    this.setCodiceTipologiaServizio(data.getString("EHD_CEHDTCSE"));
	    //fine LP PG22XX05
	}

	public Long getProgressivoFlusso() {
		return progressivoFlusso;
	}

	public void setProgressivoFlusso(Long progressivoFlusso) {
		this.progressivoFlusso = progressivoFlusso;
	}

	public String getTipoRecord() {
		return tipoRecord;
	}

	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}

	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public Date getDataCreazioneFlusso() {
		return dataCreazioneFlusso;
	}

	public void setDataCreazioneFlusso(Date dataCreazioneFlusso) {
		this.dataCreazioneFlusso = dataCreazioneFlusso;
	}

	public String getTipoServizio() {
		return tipoServizio;
	}

	public void setTipoServizio(String tipoServizio) {
		this.tipoServizio = tipoServizio;
	}

	public String getCodiceEnte() {
		return codiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
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

	public String getImpostaServizio() {
		return impostaServizio;
	}

	public void setImpostaServizio(String impostaServizio) {
		this.impostaServizio = impostaServizio;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroBollettino() {
		return numeroBollettino;
	}

	public void setNumeroBollettino(String numeroBollettino) {
		this.numeroBollettino = numeroBollettino;
	}

	public String getIdDominio() {
		return idDominio;
	}

	public void setIdDominio(String idDominio) {
		this.idDominio = idDominio;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getIbanBancario() {
		return ibanBancario;
	}

	public void setIbanBancario(String ibanBancario) {
		this.ibanBancario = ibanBancario;
	}

	public String getIbanPostale() {
		return ibanPostale;
	}

	public void setIbanPostale(String ibanPostale) {
		this.ibanPostale = ibanPostale;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}

    //inizio LP PG22XX05
	public String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}

	public void setCodiceTipologiaServizio(String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}
    //fine LP PG22XX05
}
