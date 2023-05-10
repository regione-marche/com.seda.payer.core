package com.seda.payer.core.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ArchivioCarichiTributo {
	//PYEH7TB
	
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
	private String codiceTributo;
    private String annoTributo;
    private int progressivoTributo;
    private String tipoTributo;
    private BigDecimal impTributo;
    private BigDecimal impPagatoCompresiSgravi;
    private String noteTributo;
    private String tombstoned;
    private String codiceCapitolo;
    private String accertamento;
    //inizio LP PG210130
    private String articolo;
    private String identificativoDominio;
    private String IBANBancario;
    private String IBANPostale;
    //fine LP PG210130
    //inizio LP PG22XX05
    private String codiceTipologiaServizio;
    //fine LP PG22XX05
    
    //inizio SB PAGONET-537
    private String metadatiPagoPATariTefaKey;
    private String metadatiPagoPATariTefaValue;
    //fine SB PAGONET-537	
    public ArchivioCarichiTributo() {
    }

	public ArchivioCarichiTributo(Long progressivoFlusso, String tipoRecord,
			String codiceUtente, Date dataCreazioneFlusso, String tipoServizio,
			String codiceEnte, String tipoUfficio, String codiceUfficio,
			String impostaServizio, String numeroDocumento,
			String codiceTributo, String annoTributo, int progressivoTributo,
			String tipoTributo, BigDecimal impTributo,
			BigDecimal impPagatoCompresiSgravi, String noteTributo,
		    //inizio LP PG210130
			//String tombstoned, String codiceCapitolo, String accertamento) {
			String tombstoned, String codiceCapitolo, String accertamento
			, String articolo
		    , String identificativoDominio
		    , String IBANBancario
		    , String IBANPostale
		    //inizio LP PG22XX05
		    , String codiceTipologiaServizio
		    //fine LP PG22XX05
		  ) {
			//fine LP PG210130
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
		this.codiceTributo = codiceTributo;
		this.annoTributo = annoTributo;
		this.progressivoTributo = progressivoTributo;
		this.tipoTributo = tipoTributo;
		this.impTributo = impTributo;
		this.impPagatoCompresiSgravi = impPagatoCompresiSgravi;
		this.noteTributo = noteTributo;
		this.tombstoned = tombstoned;
		this.codiceCapitolo = codiceCapitolo;
		this.accertamento = accertamento;
	    //inizio LP PG210130
		this.articolo = articolo;
		this.identificativoDominio = identificativoDominio;
		this.IBANBancario = IBANBancario;
		this.IBANPostale = IBANPostale;
	    //fine LP PG210130
	    //inizio LP PG22XX05
		this.setCodiceTipologiaServizio(codiceTipologiaServizio);
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

	public String getCodiceTributo() {
		return codiceTributo;
	}

	public void setCodiceTributo(String codiceTributo) {
		this.codiceTributo = codiceTributo;
	}

	public String getAnnoTributo() {
		return annoTributo;
	}

	public void setAnnoTributo(String annoTributo) {
		this.annoTributo = annoTributo;
	}

	public int getProgressivoTributo() {
		return progressivoTributo;
	}

	public void setProgressivoTributo(int progressivoTributo) {
		this.progressivoTributo = progressivoTributo;
	}

	public String getTipoTributo() {
		return tipoTributo;
	}

	public void setTipoTributo(String tipoTributo) {
		this.tipoTributo = tipoTributo;
	}

	public BigDecimal getImpTributo() {
		return impTributo;
	}

	public void setImpTributo(BigDecimal impTributo) {
		this.impTributo = impTributo;
	}

	public BigDecimal getImpPagatoCompresiSgravi() {
		return impPagatoCompresiSgravi;
	}

	public void setImpPagatoCompresiSgravi(BigDecimal impPagatoCompresiSgravi) {
		this.impPagatoCompresiSgravi = impPagatoCompresiSgravi;
	}

	public String getNoteTributo() {
		return noteTributo;
	}

	public void setNoteTributo(String noteTributo) {
		this.noteTributo = noteTributo;
	}

	public String getTombstoned() {
		return tombstoned;
	}

	public void setTombstoned(String tombstoned) {
		this.tombstoned = tombstoned;
	}

	public String getCodiceCapitolo() {
		return codiceCapitolo;
	}

	public void setCodiceCapitolo(String codiceCapitolo) {
		this.codiceCapitolo = codiceCapitolo;
	}

	public String getAccertamento() {
		return accertamento;
	}

	public void setAccertamento(String accertamento) {
		this.accertamento = accertamento;
	}

    //inizio LP PG210130
	public String getArticolo() {
		return articolo;
	}

	public void setArticolo(String articolo) {
		this.articolo = articolo;
	}

	public String getIdentificativoDominio() {
		return identificativoDominio;
	}

	public void setIdentificativoDominio(String identificativoDominio) {
		this.identificativoDominio = identificativoDominio;
	}

	public String getIBANBancario() {
		return IBANBancario;
	}

	public void setIBANBancario(String iBANBancario) {
		IBANBancario = iBANBancario;
	}

	public String getIBANPostale() {
		return IBANPostale;
	}

	public void setIBANPostale(String iBANPostale) {
		IBANPostale = iBANPostale;
	}
    //fine LP PG210130

    //inizio LP PG22XX05
	public String getCodiceTipologiaServizio() {
		return codiceTipologiaServizio;
	}

	public void setCodiceTipologiaServizio(String codiceTipologiaServizio) {
		this.codiceTipologiaServizio = codiceTipologiaServizio;
	}
    //fine LP PG22XX05

	public String getMetadatiPagoPATariTefaKey() {
		return metadatiPagoPATariTefaKey;
	}

	public String getMetadatiPagoPATariTefaValue() {
		return metadatiPagoPATariTefaValue;
	}

	public void setMetadatiPagoPATariTefaKey(String metadatiPagoPATariTefaKey) {
		this.metadatiPagoPATariTefaKey = metadatiPagoPATariTefaKey;
	}

	public void setMetadatiPagoPATariTefaValue(String metadatiPagoPATariTefaValue) {
		this.metadatiPagoPATariTefaValue = metadatiPagoPATariTefaValue;
	}
}
