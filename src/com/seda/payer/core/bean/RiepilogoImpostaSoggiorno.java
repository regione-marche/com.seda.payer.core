package com.seda.payer.core.bean;

import java.io.Serializable;
import java.sql.SQLException;

public class RiepilogoImpostaSoggiorno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer comunicazioniInviate;
	private Integer comunicazioniDaInviare;
	private String stato;

	public RiepilogoImpostaSoggiorno() {}
    
    public RiepilogoImpostaSoggiorno(Integer comunicazioniInviate, Integer comunicazioniDaInviare, String stato) throws SQLException
    {
    	this.comunicazioniInviate=comunicazioniInviate;
    	this.comunicazioniDaInviare=comunicazioniDaInviare;
    	this.stato=stato;
    }

	public Integer getComunicazioniInviate() {
		return comunicazioniInviate;
	}

	public Integer getComunicazioniDaInviare() {
		return comunicazioniDaInviare;
	}

	public void setComunicazioniInviate(Integer comunicazioniInviate) {
		this.comunicazioniInviate = comunicazioniInviate;
	}

	public void setComunicazioniDaInviare(Integer comunicazioniDaInviare) {
		this.comunicazioniDaInviare = comunicazioniDaInviare;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
}
