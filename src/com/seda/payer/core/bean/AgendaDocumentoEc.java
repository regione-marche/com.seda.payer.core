package com.seda.payer.core.bean;

public class AgendaDocumentoEc  {
/*
	String AnnoEmissione = crsLista.getString(1).trim();
	String NumeroEmissione = crsLista.getString(2).trim();
	String IdentificativoDocumento = crsLista.getString(3).trim();
	String IdentificativoCartella = crsLista.getString(4).trim();
	String TipologiaServizio = crsLista.getString(5).trim();
	//......
	String ImpostaServizio = crsLista.getString(13).trim();
	//......
	String DescEnte = crsLista.getString(15).trim();
	//......
	String PagamentoAcquisizione = crsLista.getString(20).trim(); //"",Y,N,X
	//......
	String ScadenzaPrimaRata = crsLista.getString(52).trim(); //S , N
	String ProgressivoCoobbligato = crsLista.getString(55).trim();
 */
	private static final long serialVersionUID = 1L;
	
	private String AnnoEmissione;
	private String NumeroEmissione;
	private String IdentificativoDocumento;
	private String IdentificativoCartella; //non usato
	private String TipologiaServizio;
	private String ImpostaServizio;
	private String DescEnte;
	private String CodiceEnte;
	private String PagamentoAcquisizione; //"",Y,N,X
	private String ScadenzaPrimaRata; //S , N
	private String ProgressivoCoobbligato;
	private AgendaEnteEc EnteEC;
	
	public AgendaDocumentoEc() {
		super();
	}
	
	public String getAnnoEmissione() {
		return AnnoEmissione;
	}
	
	public void setAnnoEmissione(String annoEmissione) {
		AnnoEmissione = annoEmissione;
	}
	
	public String getNumeroEmissione() {
		return NumeroEmissione;
	}
	
	public void setNumeroEmissione(String numeroEmissione) {
		NumeroEmissione = numeroEmissione;
	}
	
	public String getIdentificativoDocumento() {
		return IdentificativoDocumento;
	}
	
	public void setIdentificativoDocumento(String identificativoDocumento) {
		IdentificativoDocumento = identificativoDocumento;
	}
	
	public String getIdentificativoCartella() {
		return IdentificativoCartella;
	}
	
	public void setIdentificativoCartella(String identificativoCartella) {
		IdentificativoCartella = identificativoCartella;
	}

	public String getTipologiaServizio() {
		return TipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		TipologiaServizio = tipologiaServizio;
	}

	public String getImpostaServizio() {
		return ImpostaServizio;
	}


	public void setImpostaServizio(String impostaServizio) {
		ImpostaServizio = impostaServizio;
	}

	public String getDescEnte() {
		return DescEnte;
	}

	public void setDescEnte(String descEnte) {
		DescEnte = descEnte;
	}

	public String getCodiceEnte() {
		return CodiceEnte;
	}

	public void setCodiceEnte(String codiceEnte) {
		CodiceEnte = codiceEnte;
	}

	public String getPagamentoAcquisizione() {
		return PagamentoAcquisizione;
	}

	public void setPagamentoAcquisizione(String pagamentoAcquisizione) {
		PagamentoAcquisizione = pagamentoAcquisizione;
	}

	public String getScadenzaPrimaRata() {
		return ScadenzaPrimaRata;
	}

	public void setScadenzaPrimaRata(String scadenzaPrimaRata) {
		ScadenzaPrimaRata = scadenzaPrimaRata;
	}

	public AgendaEnteEc getEnteEC() {
		return EnteEC;
	}

	public void setEnteEC(AgendaEnteEc enteEC) {
		EnteEC = enteEC;
	}

	public String getProgressivoCoobbligato() {
		return ProgressivoCoobbligato;
	}

	public void setProgressivoCoobbligato(String progressivoCoobbligato) {
		ProgressivoCoobbligato = progressivoCoobbligato;
	}
	
}