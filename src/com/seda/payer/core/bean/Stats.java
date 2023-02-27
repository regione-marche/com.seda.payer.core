package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stats implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private int id;
	private int pagamentoNbollettino;
	private int pagamentoNavviso;
	
	public Stats() {}
	
	public Stats(ResultSet data) throws SQLException
    {
    	if (data == null)
    		return;
    	setId(data.getInt("STS_PKEYPKEY"));
    	setPagamentoNbollettino(data.getInt("STS_PBOLPBOL"));
    	setPagamentoNavviso(data.getInt("STS_PDOCPDOC"));
    }
	
	
	public Stats(
			int id,
			int pagamentoNbollettino,
			int pagamentoNavviso
		) {
		super();
		this.id=id;
		this.pagamentoNbollettino=pagamentoNbollettino;
		this.pagamentoNavviso=pagamentoNavviso;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setPagamentoNbollettino(int pagamentoNbollettino) {
		this.pagamentoNbollettino=pagamentoNbollettino;
	}
	public int getPagamentoNbollettino() {
		return pagamentoNbollettino;
	}
	public void setPagamentoNavviso(int pagamentoNavviso) {
		this.pagamentoNavviso=pagamentoNavviso;
	}
	public int getPagamentoNavviso() {
		return pagamentoNavviso;
	}
	  
}
