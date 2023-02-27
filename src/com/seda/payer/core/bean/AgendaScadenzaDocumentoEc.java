package com.seda.payer.core.bean;

public class AgendaScadenzaDocumentoEc  {
/*
	String ProgressivoRata = crsLista.getString(1).trim();
	String DataScadenza = crsLista.getString(2).trim(); //se vuota non va considerata
	String CodiceBollettino = crsLista.getString(3).trim();
	String ImpRataIniziali = crsLista.getString(4).trim();
	String ImpRataResiduo = crsLista.getString(5).trim();
	String ImpRataResiduoNotifica = crsLista.getString(6).trim();
	String ImpRataResiduoComp = crsLista.getString(7).trim();
	String ImpRataInteressiMora = crsLista.getString(8).trim();
	String ImpTotaleResiduo = crsLista.getString(9).trim();
	String TipoRata = crsLista.getString(10).trim();
	String PagamentoAcquisizione = crsLista.getString(11).trim(); //"",X,Y,N
	String rataScaduta = crsLista.getString(12).trim();
	String CodiceBollettinoFreccia = crsLista.getString(13).trim();
	String TipologiaServizio = crsLista.getString(14).trim();
 */
	private static final long serialVersionUID = 1L;
	
	private String ProgressivoRata;
	private String DataScadenza; //se vuota non va considerata
	private String CodiceBollettino;
	private String ImpRataIniziali;
	private String ImpRataResiduo;
	private String ImpRataResiduoNotifica;
	private String ImpRataResiduoComp;
	private String ImpRataInteressiMora;
	private String ImpTotaleResiduo;
	private String TipoRata;
	private String PagamentoAcquisizione; //"",X,Y,N
	private String rataScaduta;
	private String CodiceBollettinoFreccia;
	private String TipologiaServizio;
	private AgendaDocumentoEc DocumentoEc;
	
	public AgendaScadenzaDocumentoEc() {
		super();
	}

	public String getProgressivoRata() {
		return ProgressivoRata;
	}

	public void setProgressivoRata(String progressivoRata) {
		ProgressivoRata = progressivoRata;
	}

	public String getDataScadenza() {
		return DataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		DataScadenza = dataScadenza;
	}

	public String getCodiceBollettino() {
		return CodiceBollettino;
	}

	public void setCodiceBollettino(String codiceBollettino) {
		CodiceBollettino = codiceBollettino;
	}

	public String getImpRataIniziali() {
		return ImpRataIniziali;
	}

	public void setImpRataIniziali(String impRataIniziali) {
		ImpRataIniziali = impRataIniziali;
	}

	public String getImpRataResiduo() {
		return ImpRataResiduo;
	}

	public void setImpRataResiduo(String impRataResiduo) {
		ImpRataResiduo = impRataResiduo;
	}

	public String getImpRataResiduoNotifica() {
		return ImpRataResiduoNotifica;
	}

	public void setImpRataResiduoNotifica(String impRataResiduoNotifica) {
		ImpRataResiduoNotifica = impRataResiduoNotifica;
	}

	public String getImpRataResiduoComp() {
		return ImpRataResiduoComp;
	}

	public void setImpRataResiduoComp(String impRataResiduoComp) {
		ImpRataResiduoComp = impRataResiduoComp;
	}

	public String getImpRataInteressiMora() {
		return ImpRataInteressiMora;
	}

	public void setImpRataInteressiMora(String impRataInteressiMora) {
		ImpRataInteressiMora = impRataInteressiMora;
	}

	public String getImpTotaleResiduo() {
		return ImpTotaleResiduo;
	}

	public void setImpTotaleResiduo(String impTotaleResiduo) {
		ImpTotaleResiduo = impTotaleResiduo;
	}

	public String getTipoRata() {
		return TipoRata;
	}

	public void setTipoRata(String tipoRata) {
		TipoRata = tipoRata;
	}

	public String getPagamentoAcquisizione() {
		return PagamentoAcquisizione;
	}

	public void setPagamentoAcquisizione(String pagamentoAcquisizione) {
		PagamentoAcquisizione = pagamentoAcquisizione;
	}

	public String getRataScaduta() {
		return rataScaduta;
	}

	public void setRataScaduta(String rataScaduta) {
		this.rataScaduta = rataScaduta;
	}

	public String getCodiceBollettinoFreccia() {
		return CodiceBollettinoFreccia;
	}

	public void setCodiceBollettinoFreccia(String codiceBollettinoFreccia) {
		CodiceBollettinoFreccia = codiceBollettinoFreccia;
	}

	public String getTipologiaServizio() {
		return TipologiaServizio;
	}

	public void setTipologiaServizio(String tipologiaServizio) {
		TipologiaServizio = tipologiaServizio;
	}

	public AgendaDocumentoEc getDocumentoEc() {
		return DocumentoEc;
	}

	public void setDocumentoEc(AgendaDocumentoEc documentoEc) {
		DocumentoEc = documentoEc;
	}
	
}
