package com.seda.payer.core.bean;

public class AgendaEnteEc  {
/*
	String codiceEnteDB = crsLista.getString(3).trim();
	String descrizioneEnteDB = crsLista.getString(4).trim();
	//5 CAP
	String codiceBelfioreEnteDB = crsLista.getString(6).trim();
	String tipoUfficioDB = crsLista.getString(7).trim();
	String codiceUfficioDB = crsLista.getString(8).trim();
	//9 APC_PROVCOM
	String tipoIntegrazioneDB = crsLista.getString(10).trim();
	String UrlWSECDB = crsLista.getString(11).trim();
 */
	private static final long serialVersionUID = 1L;
	
	private String codiceEnte; //CANECENT
	private String tipoUfficio;
	private String codiceUfficio;
	
	private String urlEC;
	private String tipoIntegrazione;
	private String codiceUtenteSeda;
	
	private String codiceSocieta;
	private String codiceUtente;//CUTECUTE
	private String chiaveEnte; //KANEKENT
	
	private String codEnteComuneDomicilioFiscale; //ANE_CANECENT
	
	private String descrizioneEnte;
	private String codiceProvinciaComune;
	
	public AgendaEnteEc() {
		super();
	}

	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	public String getCodiceEnte() {
		return codiceEnte != null ? codiceEnte : "";
	}

	public void setTipoUfficio(String tipoUfficio) {
		this.tipoUfficio = tipoUfficio;
	}

	public String getTipoUfficio() {
		return tipoUfficio != null ? tipoUfficio : "";
	}

	public void setCodiceUfficio(String codiceUfficio) {
		this.codiceUfficio = codiceUfficio;
	}

	public String getCodiceUfficio() {
		return codiceUfficio != null ? codiceUfficio : "";
	}

	public void setUrlEC(String urlEC) {
		this.urlEC = urlEC;
	}

	public String getUrlEC() {
		return urlEC != null ? urlEC : "";
	}

	public void setTipoIntegrazione(String tipoIntegrazione) {
		this.tipoIntegrazione = tipoIntegrazione;
	}

	public String getTipoIntegrazione() {
		return tipoIntegrazione != null ? tipoIntegrazione : "D";
	}

	public void setCodiceUtenteSeda(String codiceUtenteSeda) {
		this.codiceUtenteSeda = codiceUtenteSeda;
	}

	public String getCodiceUtenteSeda() {
		return codiceUtenteSeda;
	}

	public void setCodiceSocieta(String codiceSocieta) {
		this.codiceSocieta = codiceSocieta;
	}

	public String getCodiceSocieta() {
		return codiceSocieta != null ? codiceSocieta : "";
	}

	public void setCodiceUtente(String codiceUtente) {
		this.codiceUtente = codiceUtente;
	}

	public String getCodiceUtente() {
		return codiceUtente != null ? codiceUtente : "";
	}

	public void setChiaveEnte(String chiaveEnte) {
		this.chiaveEnte = chiaveEnte;
	}

	public String getChiaveEnte() {
		return chiaveEnte != null ? chiaveEnte : "";
	}

	public void setCodEnteComuneDomicilioFiscale(
			String codEnteComuneDomicilioFiscale) {
		this.codEnteComuneDomicilioFiscale = codEnteComuneDomicilioFiscale;
	}

	public String getCodEnteComuneDomicilioFiscale() {
		return codEnteComuneDomicilioFiscale != null ? codEnteComuneDomicilioFiscale : "";
	}
	
	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	public void setDescrizioneEnte(String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

	public String getCodiceProvinciaComune() {
		return codiceProvinciaComune;
	}

	public void setCodiceProvinciaComune(String codiceProvinciaComune) {
		this.codiceProvinciaComune = codiceProvinciaComune;
	}
}
