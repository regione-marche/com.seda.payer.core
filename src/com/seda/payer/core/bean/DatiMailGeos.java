package com.seda.payer.core.bean;

public class DatiMailGeos  implements java.io.Serializable {
   
	private java.lang.String  cutecute;
	private java.lang.String  societa;
	private java.lang.String  ente;			
	private java.lang.String  numeroDoc;
	private java.lang.String  codFiscale;
	
	private java.lang.String annoDocumento;
    private java.lang.String descrizioneImpostServizio;
	//inizio LP PG190410
	private java.lang.String codiceImpostaServizio;
	//fine LP PG190410
    private java.math.BigDecimal importoBollettino;
    private java.lang.String dataScadenzaUltRata;
    private java.lang.String mail;
    private java.lang.String mailPec;
    
    private java.lang.String  codiceAvviso;
    private java.lang.String  denominazione;
    private java.lang.Integer numeroRata;
    private java.lang.Integer numeroTotRate;
    private java.lang.String  causale;
    private java.lang.String  descrizioneEnte;
    
    public DatiMailGeos() {
    }

	public java.lang.String getCutecute() {
		return cutecute;
	}

	public void setCutecute(java.lang.String cutecute) {
		this.cutecute = cutecute;
	}

	public java.lang.String getSocieta() {
		return societa;
	}

	public void setSocieta(java.lang.String societa) {
		this.societa = societa;
	}

	public java.lang.String getEnte() {
		return ente;
	}

	public void setEnte(java.lang.String ente) {
		this.ente = ente;
	}

	public java.lang.String getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(java.lang.String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public java.lang.String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(java.lang.String codFiscale) {
		this.codFiscale = codFiscale;
	}

	public java.lang.String getAnnoDocumento() {
		return annoDocumento;
	}

	public void setAnnoDocumento(java.lang.String annoDocumento) {
		this.annoDocumento = annoDocumento;
	}

	public java.lang.String getDescrizioneImpostServizio() {
		return descrizioneImpostServizio;
	}

	public void setDescrizioneImpostServizio(
			java.lang.String descrizioneImpostServizio) {
		this.descrizioneImpostServizio = descrizioneImpostServizio;
	}

	//inizio LP PG190410
	public java.lang.String getCodiceImpostaServizio() {
		return codiceImpostaServizio;
	}

	public void setCodiceImpostaServizio(java.lang.String codiceImpostaServizio) {
		this.codiceImpostaServizio = codiceImpostaServizio;
	}
	//fine LP PG190410

	public java.math.BigDecimal getImportoBollettino() {
		return importoBollettino;
	}

	public void setImportoBollettino(java.math.BigDecimal importoBollettino) {
		this.importoBollettino = importoBollettino;
	}

	public java.lang.String getDataScadenzaUltRata() {
		return dataScadenzaUltRata;
	}

	public void setDataScadenzaUltRata(java.lang.String dataScadenzaUltRata) {
		this.dataScadenzaUltRata = dataScadenzaUltRata;
	}

	public java.lang.String getMail() {
		return mail;
	}

	public void setMail(java.lang.String mail) {
		this.mail = mail;
	}

	public java.lang.String getMailPec() {
		return mailPec;
	}

	public void setMailPec(java.lang.String mailPec) {
		this.mailPec = mailPec;
	}

	public java.lang.String getCodiceAvviso() {
		return codiceAvviso;
	}

	public void setCodiceAvviso(java.lang.String codiceAvviso) {
		this.codiceAvviso = codiceAvviso;
	}

	public java.lang.String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(java.lang.String denominazione) {
		this.denominazione = denominazione;
	}

	public java.lang.Integer getNumeroRata() {
		return numeroRata;
	}

	public void setNumeroRata(java.lang.Integer numeroRata) {
		this.numeroRata = numeroRata;
	}

	public java.lang.Integer getNumeroTotRate() {
		return numeroTotRate;
	}

	public void setNumeroTotRate(java.lang.Integer numeroTotRate) {
		this.numeroTotRate = numeroTotRate;
	}

	public java.lang.String getCausale() {
		return causale;
	}

	public void setCausale(java.lang.String causale) {
		this.causale = causale;
	}

	public java.lang.String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	public void setDescrizioneEnte(java.lang.String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}
	
}
