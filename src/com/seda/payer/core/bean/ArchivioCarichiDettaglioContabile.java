package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ArchivioCarichiDettaglioContabile {
	//PYEHDTB
	/*
	"EHC_PEHCFLUS" BIGINT NOT NULL,
	"EHC_TEHCRECO" CHAR(3) NOT NULL,
	"EHC_CUTECUTE" CHAR(5) NOT NULL,
	"EHC_GEHCCREA" DATE NOT NULL,
	"EHC_TEHCSERV" CHAR(2) NOT NULL,
	"EHC_CANECENT" CHAR(5) NOT NULL,
	"EHC_TANETUFF" CHAR(1) NOT NULL,
	"EHC_CANECUFF" CHAR(6) NOT NULL,
	"EHC_CISECISE" CHAR(4) NOT NULL,
	"EHC_CEHCNDOC" VARCHAR(20) NOT NULL,
	"EHC_CEHCCBOL" CHAR(18) NOT NULL,
	"EHC_PEHCCOCO" CHAR(50) NOT NULL,
	"EHC_IEHCIMPO" DECIMAL(15 , 2) NOT NULL,
	"EHC_CEHCCAPI" VARCHAR(50) NULL,
	"EHC_CEHCARTI" VARCHAR(50) NULL,
	"EHC_CEHCANNO" VARCHAR(4) NULL,
	"EHC_CEHCTOMB" CHAR(1) NOT NULL
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
	private String codiceContabilita;
	private BigDecimal importo;
	private String capitolo;
	private String articolo;
	private String anno;
    private String tombstoned;

    public ArchivioCarichiDettaglioContabile() {
    }

	public ArchivioCarichiDettaglioContabile(Long progressivoFlusso, String tipoRecord, String codiceUtente,
			Date dataCreazioneFlusso, String tipoServizio, String codiceEnte, String tipoUfficio, String codiceUfficio,
			String impostaServizio, String numeroDocumento, String numeroBollettino, String codiceContabilita,
			BigDecimal importo, String capitolo, String articolo, String anno, String tombstoned) {
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
		this.codiceContabilita = codiceContabilita;
		this.importo = importo;
		this.capitolo = capitolo;
		this.articolo = articolo;
		this.anno = anno;
		this.tombstoned = tombstoned;
	}

	public ArchivioCarichiDettaglioContabile(ResultSet data) throws SQLException
	{
		super();
		if(data == null)
			return;
		this.progressivoFlusso = data.getLong("EHC_PEHCFLUS");
		this.tipoRecord = data.getString("EHC_TEHCRECO");
		this.codiceUtente = data.getString("EHC_CUTECUTE");
		this.dataCreazioneFlusso = java.sql.Date.valueOf(data.getString("EHC_GEHCCREA"));
		this.tipoServizio = data.getString("EHC_TEHCSERV");
		this.codiceEnte = data.getString("EHC_CANECENT");
		this.tipoUfficio = data.getString("EHC_TANETUFF");
		this.codiceUfficio = data.getString("EHC_CANECUFF");
		this.impostaServizio = data.getString("EHC_CISECISE");
		this.numeroDocumento = data.getString("EHC_CEHCNDOC");
		this.numeroBollettino = data.getString("EHC_CEHCCBOL");
		this.codiceContabilita = data.getString("EHC_CEHCCBOL");
		this.importo = data.getBigDecimal("EHC_IEHCIMPO");
		this.capitolo = data.getString("EHC_CEHCCAPI");
		this.articolo = data.getString("EHC_CEHCARTI");
		this.anno = data.getString("EHC_CEHCANNO");
		this.tombstoned = data.getString("EHC_CEHCTOMB");
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

	public String getCodiceContabilita() {
		return codiceContabilita;
	}

	public void setCodiceContabilita(String codiceContabilita) {
		this.codiceContabilita = codiceContabilita;
	}

	public BigDecimal getImporto() {
		return importo;
	}

	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}

	public String getCapitolo() {
		return capitolo;
	}

	public void setCapitolo(String capitolo) {
		this.capitolo = capitolo;
	}

	public String getArticolo() {
		return articolo;
	}

	public void setArticolo(String articolo) {
		this.articolo = articolo;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}

}
