package com.seda.payer.core.sosta.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import com.seda.data.dao.ModelAttributes;

public class AreaTariffarie  extends ModelAttributes implements Serializable {

	private static final long serialVersionUID = 1L;

	private String zonaTariffata;	//	"SAT_CSATZTAR" VARCHAR(100) NOT NULL,
	private String matricola;		//	"SAT_CSATMATR" VARCHAR(100) NOT NULL,
	private String nomeVia;			//	"SAT_CSATVIA" VARCHAR(100),
	private String orario;			//	"SAT_CSATORAR" VARCHAR(100),
	private long tariffa; 			//	"SAT_ISATTARI" BIGINT,
	private String municipio;		//	"SAT_CSATMUNI" VARCHAR(100),
	private String ambito;			//	"SAT_CSATAMBI" VARCHAR(100),
	private String tratto;			//	"SAT_CSATTRAT" VARCHAR(100),
	private String agevolazioniTariffarie;//	"SAT_CSATAGEV" VARCHAR(50)
	
	
	
	
	
	
	public AreaTariffarie(){
	}






	public AreaTariffarie(String zonaTariffata, String matricola,
			String nomeVia, String orario, long tariffa, String municipio,
			String ambito, String tratto, String agevolazioniTariffarie) {
		super();
		this.zonaTariffata = zonaTariffata;
		this.matricola = matricola;
		this.nomeVia = nomeVia;
		this.orario = orario;
		this.tariffa = tariffa;
		this.municipio = municipio;
		this.ambito = ambito;
		this.tratto = tratto;
		this.agevolazioniTariffarie = agevolazioniTariffarie;
	}






	public String getZonaTariffata() {
		return zonaTariffata;
	}






	public void setZonaTariffata(String zonaTariffata) {
		this.zonaTariffata = zonaTariffata;
	}






	public String getMatricola() {
		return matricola;
	}






	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}






	public String getNomeVia() {
		return nomeVia;
	}






	public void setNomeVia(String nomeVia) {
		this.nomeVia = nomeVia;
	}






	public String getOrario() {
		return orario;
	}






	public void setOrario(String orario) {
		this.orario = orario;
	}






	public long getTariffa() {
		return tariffa;
	}






	public void setTariffa(long tariffa) {
		this.tariffa = tariffa;
	}






	public String getMunicipio() {
		return municipio;
	}






	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}






	public String getAmbito() {
		return ambito;
	}






	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}






	public String getTratto() {
		return tratto;
	}






	public void setTratto(String tratto) {
		this.tratto = tratto;
	}






	public String getAgevolazioniTariffarie() {
		return agevolazioniTariffarie;
	}






	public void setAgevolazioniTariffarie(String agevolazioniTariffarie) {
		this.agevolazioniTariffarie = agevolazioniTariffarie;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
	
	
	
}

