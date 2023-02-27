package com.seda.payer.core.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NodoSpcPsp implements Serializable {

	/**
	 * PSP disponibile per NodoSPC
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String nome;
	private String chiave;
	private BigDecimal importo;
	private String idFlusso;
	private String url;
	List<NodoSpcPsc> listaCanali;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}
	
	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}
	public BigDecimal getImporto() {
		return importo;
	}

	public String getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(String idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<NodoSpcPsc> getListaCanali() {
		return listaCanali;
	}

	public void setListaCanali(List<NodoSpcPsc> listaCanali) {
		this.listaCanali = listaCanali;
	}

	public NodoSpcPsp(ResultSet data) throws SQLException 
	{
    	if (data == null)
    		return;
    	
       	setNome(data.getString("PSP_DPSPDESC"));
    	setChiave(data.getString("PSP_KPSPKEY"));
     	setImporto(data.getBigDecimal("PSP_IPSPCOST"));
     	setIdFlusso(data.getString("PSP_CPSPIDFLU"));
     	setUrl(data.getString("PSP_CPSPURL"));
	}
	
	public NodoSpcPsp()  
	{
    	
	}
	



}
