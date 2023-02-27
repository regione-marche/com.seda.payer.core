package com.seda.payer.core.wallet.bean;

public class Tributo {

	// Tabella PYTRBTB
	private static final long serialVersionUID = 1L;
	private String codiceTributo;            // TRB_CTRBCODI 
	private String descrizioneTributo;		 // TRB_DTRBDESC
	
	public Tributo(){
	}
	
	public Tributo(
		String codiceTributo,
		String descrizioneServizio
		){
		this.codiceTributo = codiceTributo;
		this.descrizioneTributo = descrizioneTributo;
		
	}

	public String getCodiceTributo() {
		return codiceTributo;
	}

	public void setCodiceTributo(String codiceTributo) {
		this.codiceTributo = codiceTributo;
	}

	@Override
	public String toString() {
		return "Tributo [codiceTributo=" + codiceTributo
				+ ", descrizioneTributo=" + descrizioneTributo + "]";
	}

	public String getDescrizioneTributo() {
		return descrizioneTributo;
	}

	public void setDescrizioneTributo(String descrizioneTributo) {
		this.descrizioneTributo = descrizioneTributo;
	}

	
}
