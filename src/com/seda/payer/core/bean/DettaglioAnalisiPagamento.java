package com.seda.payer.core.bean;

import java.io.Serializable;
import java.util.List;

public class DettaglioAnalisiPagamento implements Serializable {

	private String chiaveTrx;
	private String data;
	
	public DettaglioAnalisiPagamento() {
	}

	public DettaglioAnalisiPagamento(String chiaveTrx, String data) {
		this.chiaveTrx = chiaveTrx;
		this.data = data;
	}

	public void setChiaveTrx(String chiaveTrx) {
		this.chiaveTrx = chiaveTrx;
	}

	public String getChiaveTrx() {
		return chiaveTrx;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}
	
}
