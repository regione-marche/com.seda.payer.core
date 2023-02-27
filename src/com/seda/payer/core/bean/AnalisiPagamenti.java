package com.seda.payer.core.bean;

import java.io.Serializable;
import java.util.List;

public class AnalisiPagamenti implements Serializable {

	private List<DettaglioAnalisiPagamento> listCF; 
	private List<DettaglioAnalisiPagamento> listIP;
	private List<DettaglioAnalisiPagamento> listEmail;
	private List<DettaglioAnalisiPagamento> listBollettino;
	private List<DettaglioAnalisiPagamento> listCanalePag;
	
	public AnalisiPagamenti () {
		
	}
	
	public AnalisiPagamenti (List<DettaglioAnalisiPagamento> listCF,
							 List<DettaglioAnalisiPagamento> listIP,
							 List<DettaglioAnalisiPagamento> listEmail,
							 List<DettaglioAnalisiPagamento> listBollettino,
							 List<DettaglioAnalisiPagamento> listCanalePag)	{
		this.listCF = listCF;
		this.listIP = listIP;
		this.listEmail = listEmail;
		this.listBollettino = listBollettino;
		this.listCanalePag = listCanalePag;
		
	}

	public void setListCF(List<DettaglioAnalisiPagamento> listCF) {
		this.listCF = listCF;
	}

	public List<DettaglioAnalisiPagamento> getListCF() {
		return listCF;
	}

	public void setListIP(List<DettaglioAnalisiPagamento> listIP) {
		this.listIP = listIP;
	}

	public List<DettaglioAnalisiPagamento> getListIP() {
		return listIP;
	}

	public void setListEmail(List<DettaglioAnalisiPagamento> listEmail) {
		this.listEmail = listEmail;
	}

	public List<DettaglioAnalisiPagamento> getListEmail() {
		return listEmail;
	}

	public void setListBollettino(List<DettaglioAnalisiPagamento> listBollettino) {
		this.listBollettino = listBollettino;
	}

	public List<DettaglioAnalisiPagamento> getListBollettino() {
		return listBollettino;
	}

	public void setListCanalePag(List<DettaglioAnalisiPagamento> listCanalePag) {
		this.listCanalePag = listCanalePag;
	}

	public List<DettaglioAnalisiPagamento> getListCanalePag() {
		return listCanalePag;
	}

}
