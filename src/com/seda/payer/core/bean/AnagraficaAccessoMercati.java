package com.seda.payer.core.bean;

public class AnagraficaAccessoMercati {

    private java.lang.String codSocieta;

    private java.lang.String codUt;

    private java.lang.String codEnte;

    private java.lang.String codiceFiscale;

    private java.lang.String codiceBorsellino;

    private java.lang.String email;

    private java.lang.String telefono;

    private float importoMinimo;

    private java.lang.Float residuoBorsellino;

	public AnagraficaAccessoMercati() {

	}

	public AnagraficaAccessoMercati(String codSocieta, String codUt,
			String codEnte, String codiceFiscale, String codiceBorsellino,
			String email, String telefono, float importoMinimo,
			Float residuoBorsellino) {
		this.codSocieta = codSocieta;
		this.codUt = codUt;
		this.codEnte = codEnte;
		this.codiceFiscale = codiceFiscale;
		this.codiceBorsellino = codiceBorsellino;
		this.email = email;
		this.telefono = telefono;
		this.importoMinimo = importoMinimo;
		this.residuoBorsellino = residuoBorsellino;
	}

	public java.lang.String getCodSocieta() {
		return codSocieta;
	}

	public void setCodSocieta(java.lang.String codSocieta) {
		this.codSocieta = codSocieta;
	}

	public java.lang.String getCodUt() {
		return codUt;
	}

	public void setCodUt(java.lang.String codUt) {
		this.codUt = codUt;
	}

	public java.lang.String getCodEnte() {
		return codEnte;
	}

	public void setCodEnte(java.lang.String codEnte) {
		this.codEnte = codEnte;
	}

	public java.lang.String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(java.lang.String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public java.lang.String getCodiceBorsellino() {
		return codiceBorsellino;
	}

	public void setCodiceBorsellino(java.lang.String codiceBorsellino) {
		this.codiceBorsellino = codiceBorsellino;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getTelefono() {
		return telefono;
	}

	public void setTelefono(java.lang.String telefono) {
		this.telefono = telefono;
	}

	public float getImportoMinimo() {
		return importoMinimo;
	}

	public void setImportoMinimo(float importoMinimo) {
		this.importoMinimo = importoMinimo;
	}

	public java.lang.Float getResiduoBorsellino() {
		return residuoBorsellino;
	}

	public void setResiduoBorsellino(java.lang.Float residuoBorsellino) {
		this.residuoBorsellino = residuoBorsellino;
	}

}
