package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResponseData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String retCode;
	private String retMessage;
	private String info1;
	private String info2;
	private String info3;
	private String info4;
	private String info5;
	
	private int info1int;
	private int info2int;
	private BigDecimal info1bd;
	//inizio LP PG190010_002_LP
	private String descrizioneEnte;
	private String tipoCC;
	private String numeroCC;
	private String intestazioneCC;
	private String autorizzazioneCC;
	private String codiceFiscaleEnte;
	private String codiceCBill;
	private String barcodePagoPA;
	private String qrCodePagoPA;
	private String causaleDocumento;
	private String descrizioneUfficio;
	private String descrizioneTipoServizio;
	private String descrizioneImpostaServizio;
	private String numeroAvvisoPagoPA;
	private String codiceIUV;
	//fine LP PG190010_002_LP
	private String numeroBollettinoCumulativo;	//PG190300
	
	public ResponseData() {}
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMessage() {
		return retMessage;
	}
	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}
	public String getInfo1() {
		return info1;
	}
	public void setInfo1(String info1) {
		this.info1 = info1;
	}
	public String getInfo2() {
		return info2;
	}
	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	public String getInfo3() {
		return info3;
	}
	public void setInfo3(String info3) {
		this.info3 = info3;
	}
	public String getInfo4() {
		return info4;
	}
	public void setInfo4(String info4) {
		this.info4 = info4;
	}

	public String getInfo5() {
		return info5;
	}

	public void setInfo5(String info5) {
		this.info5 = info5;
	}

	public void setInfo1int(int info1int) {
		this.info1int = info1int;
	}

	public int getInfo1int() {
		return info1int;
	}

	public void setInfo2int(int info2int) {
		this.info2int = info2int;
	}

	public int getInfo2int() {
		return info2int;
	}

	public void setInfo1bd(BigDecimal info1bd) {
		this.info1bd = info1bd;
	}

	public BigDecimal getInfo1bd() {
		return info1bd;
	}
	//inizio LP PG190010_002_LP
	public String getDescrizioneEnte() {
		return descrizioneEnte;
	}

	public void setDescrizioneEnte(String descrizioneEnte) {
		this.descrizioneEnte = descrizioneEnte;
	}

	public String getTipoCC() {
		return tipoCC;
	}

	public void setTipoCC(String tipoCC) {
		this.tipoCC = tipoCC;
	}

	public String getNumeroCC() {
		return numeroCC;
	}

	public void setNumeroCC(String numeroCC) {
		this.numeroCC = numeroCC;
	}

	public String getIntestazioneCC() {
		return intestazioneCC;
	}

	public void setIntestazioneCC(String intestazioneCC) {
		this.intestazioneCC = intestazioneCC;
	}

	public String getAutorizzazioneCC() {
		return autorizzazioneCC;
	}

	public void setAutorizzazioneCC(String autorizzazioneCC) {
		this.autorizzazioneCC = autorizzazioneCC;
	}

	public String getCodiceFiscaleEnte() {
		return codiceFiscaleEnte;
	}

	public void setCodiceFiscaleEnte(String codiceFiscaleEnte) {
		this.codiceFiscaleEnte = codiceFiscaleEnte;
	}

	public String getCodiceCBill() {
		return codiceCBill;
	}

	public void setCodiceCBill(String codiceCBill) {
		this.codiceCBill = codiceCBill;
	}

	public String getBarcodePagoPA() {
		return barcodePagoPA;
	}

	public void setBarcodePagoPA(String barcodePagoPA) {
		this.barcodePagoPA = barcodePagoPA;
	}

	public String getQrCodePagoPA() {
		return qrCodePagoPA;
	}

	public void setQrCodePagoPA(String qrCodePagoPA) {
		this.qrCodePagoPA = qrCodePagoPA;
	}

	public String getCausaleDocumento() {
		return causaleDocumento;
	}

	public void setCausaleDocumento(String causaleDocumento) {
		this.causaleDocumento = causaleDocumento;
	}

	public String getDescrizioneUfficio() {
		return descrizioneUfficio;
	}

	public void setDescrizioneUfficio(String descrizioneUfficio) {
		this.descrizioneUfficio = descrizioneUfficio;
	}

	public String getDescrizioneTipoServizio() {
		return descrizioneTipoServizio;
	}

	public void setDescrizioneTipoServizio(String descrizioneTipoServizio) {
		this.descrizioneTipoServizio = descrizioneTipoServizio;
	}

	public String getDescrizioneImpostaServizio() {
		return descrizioneImpostaServizio;
	}

	public void setDescrizioneImpostaServizio(String descrizioneImpostaServizio) {
		this.descrizioneImpostaServizio = descrizioneImpostaServizio;
	}

	public String getNumeroAvvisoPagoPA() {
		return numeroAvvisoPagoPA;
	}

	public void setNumeroAvvisoPagoPA(String numeroAvvisoPagoPA) {
		this.numeroAvvisoPagoPA = numeroAvvisoPagoPA;
	}

	public String getCodiceIUV() {
		return codiceIUV;
	}

	public void setCodiceIUV(String codiceIUV) {
		this.codiceIUV = codiceIUV;
	}
	//fine LP PG190010_002_LP
	
	//PG190300 - inizio
	public String getNumeroBollettinoCumulativo() {
		return numeroBollettinoCumulativo;
	}

	public void setNumeroBollettinoCumulativo(String numeroBollettinoCumulativo) {
		this.numeroBollettinoCumulativo = numeroBollettinoCumulativo;
	}
	//PG190300 - fine
}
